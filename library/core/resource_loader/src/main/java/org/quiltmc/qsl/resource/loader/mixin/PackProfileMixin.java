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

package org.quiltmc.qsl.resource.loader.mixin;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.resource.pack.PackLocationInfo;
import net.minecraft.resource.pack.PackProfile;
import net.minecraft.resource.pack.PackSource;
import net.minecraft.text.Text;
import net.minecraft.unmapped.C_yzksgymh;

import org.quiltmc.qsl.resource.loader.api.QuiltPackProfile;
import org.quiltmc.qsl.resource.loader.api.PackActivationType;

@Mixin(PackProfile.class)
public class PackProfileMixin implements QuiltPackProfile {
	@Unique
	private PackActivationType quilt$activationType;

	@Inject(method = "<init>", at = @At("RETURN"))
	private void quilt$onInit(PackLocationInfo locationInfo, PackProfile.PackFactory packFactory, PackProfile.Metadata info, C_yzksgymh c_yzksgymh, CallbackInfo ci) {
		try (var pack = packFactory.open(locationInfo, info)) {
			this.quilt$activationType = pack.getActivationType();
		}
	}

	@Override
	public @NotNull PackActivationType getActivationType() {
		return this.quilt$activationType;
	}
}
