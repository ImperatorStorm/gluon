/*
 * Copyright 2022 The Quilt Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.quiltmc.qsl.networking.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientNetworkHandler;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.networking.impl.NetworkHandlerExtensions;
import org.quiltmc.qsl.networking.impl.client.ClientNetworkingImpl;
import org.quiltmc.qsl.networking.impl.client.ClientPlayNetworkAddon;

// We want to apply a bit earlier than other mods which may not use us in order to prevent refCount issues
@ClientOnly
@Mixin(value = ClientPlayNetworkHandler.class, priority = 999)
abstract class ClientPlayNetworkHandlerMixin extends AbstractClientNetworkHandler implements NetworkHandlerExtensions {
	@Unique
	private ClientPlayNetworkAddon addon;

	protected ClientPlayNetworkHandlerMixin(MinecraftClient client, ClientConnection connection, ClientConnectionState clientConnectionState) {
		super(client, connection, clientConnectionState);
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	private void initAddon(CallbackInfo ci) {
		this.addon = new ClientPlayNetworkAddon((ClientPlayNetworkHandler) (Object) this, this.client);
		// A bit of a hack but it allows the field above to be set in case someone registers handlers during INIT event which refers to said field
		ClientNetworkingImpl.setClientPlayAddon(this.addon);
		this.addon.lateInit();
	}

	@Inject(method = "onGameJoin", at = @At("RETURN"))
	private void handleServerPlayReady(GameJoinS2CPacket packet, CallbackInfo ci) {
		this.addon.onServerReady();
	}

	@Override
	public ClientPlayNetworkAddon getAddon() {
		return this.addon;
	}
}
