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

package org.quiltmc.qsl.networking.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.ConnectedClientData;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

import org.quiltmc.qsl.networking.impl.server.ServerNetworkingImpl;

@Mixin(PlayerManager.class)
abstract class PlayerManagerMixin {
	@Inject(
		method = "onPlayerConnect",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/PlayerManager;sendToAll(Lnet/minecraft/network/packet/Packet;)V"
		)
	)
	private void handlePlayerConnection(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData connectedClientData, CallbackInfo ci) {
		ServerNetworkingImpl.getAddon(player.networkHandler).onClientReady();
	}
}
