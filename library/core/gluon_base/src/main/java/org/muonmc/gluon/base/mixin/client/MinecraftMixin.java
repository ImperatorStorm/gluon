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

package org.muonmc.gluon.base.mixin.client;

import org.muonmc.gluon.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.loader.api.entrypoint.EntrypointUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;

import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	@Inject(
			method = "<init>",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V")
	)
	private void onInit(GameConfig gameConfig, CallbackInfo ci) {
		EntrypointUtil.invoke(ClientModInitializer.ENTRYPOINT_KEY, ClientModInitializer.class, ClientModInitializer::onInitializeClient);
	}
}
