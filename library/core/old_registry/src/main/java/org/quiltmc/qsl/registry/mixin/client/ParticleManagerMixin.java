/*
 * Copyright 2021, 2022, 2023, 2024 The Quilt Project
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

package org.quiltmc.qsl.registry.mixin.client;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.Registries;

import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.registry.impl.sync.client.RebuildableIdModelHolder;
import org.quiltmc.qsl.registry.impl.sync.registry.SynchronizedInt2ObjectMap;

@ClientOnly
@Mixin(ParticleManager.class)
public class ParticleManagerMixin implements RebuildableIdModelHolder {
	@Mutable
	@Final
	@Shadow
	private Int2ObjectMap<ParticleFactory<?>> factories;

	@Inject(method = "<init>", at = @At("RETURN"))
	private void quilt$onInit(ClientWorld clientWorld, TextureManager textureManager, CallbackInfo ci) {
		this.factories = new SynchronizedInt2ObjectMap<>(Registries.PARTICLE_TYPE, this.factories);
	}

	@Override
	public void quilt$rebuildIds() {
		SynchronizedInt2ObjectMap.attemptRebuildIds(this.factories);
	}
}
