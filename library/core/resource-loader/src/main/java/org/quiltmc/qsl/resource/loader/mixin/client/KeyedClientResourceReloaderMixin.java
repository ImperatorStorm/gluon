/*
 * Copyright 2021 QuiltMC
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

package org.quiltmc.qsl.resource.loader.mixin.client;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

import org.quiltmc.qsl.resource.loader.api.reloader.IdentifiableResourceReloader;
import org.quiltmc.qsl.resource.loader.api.reloader.ResourceReloaderKeys;

@Mixin({
		/* public */
		SoundManager.class, BakedModelManager.class, LanguageManager.class, TextureManager.class,
		/* private */
		WorldRenderer.class, BlockRenderManager.class, ItemRenderer.class
})
public abstract class KeyedClientResourceReloaderMixin implements IdentifiableResourceReloader {
	@Unique
	private Identifier id;
	@Unique
	private Collection<Identifier> dependencies;

	@Override
	@SuppressWarnings({"ConstantConditions", "RedundantCast"})
	public Identifier getQuiltId() {
		if (this.id == null) {
			Object self = this;

			if (self instanceof SoundManager) {
				this.id = ResourceReloaderKeys.Client.SOUNDS;
			} else if (self instanceof BakedModelManager) {
				this.id = ResourceReloaderKeys.Client.MODELS;
			} else if (self instanceof LanguageManager) {
				this.id = ResourceReloaderKeys.Client.LANGUAGES;
			} else if (self instanceof TextureManager) {
				this.id = ResourceReloaderKeys.Client.TEXTURES;
			} else {
				this.id = new Identifier("private/" + self.getClass().getSimpleName().toLowerCase(Locale.ROOT));
			}
		}

		return this.id;
	}

	@Override
	@SuppressWarnings({"ConstantConditions", "RedundantCast"})
	public Collection<Identifier> getQuiltDependencies() {
		if (this.dependencies == null) {
			Object self = this;

			if (self instanceof BakedModelManager || self instanceof WorldRenderer) {
				this.dependencies = Collections.singletonList(ResourceReloaderKeys.Client.TEXTURES);
			} else if (self instanceof ItemRenderer || self instanceof BlockRenderManager) {
				this.dependencies = Collections.singletonList(ResourceReloaderKeys.Client.MODELS);
			} else {
				this.dependencies = Collections.emptyList();
			}
		}

		return this.dependencies;
	}
}
