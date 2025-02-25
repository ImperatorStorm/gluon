/*
 * Copyright 2021, 2022, 2023, 2024 The Quilt Project
 * Copyright 2024 MuonMC
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

package org.muonmc.gluon.base.mixin;

import net.minecraft.server.MinecraftServer;
import org.muonmc.gluon.base.impl.GluonBaseImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Shadow
	public abstract void halt(boolean waitForServer);

	@Unique
	private int muon$autoTestTicks = 0;

	@Inject(method = "tickServer", at = @At("TAIL"))
	private void onEndTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
		// Check whether we are in auto test mode, after the designated tick times we do an audit of Mixins then shutdown.
		if (GluonBaseImpl.AUTO_TEST_SERVER_TICK_TIME != null) {
			this.muon$autoTestTicks++;

			if (this.muon$autoTestTicks == GluonBaseImpl.AUTO_TEST_SERVER_TICK_TIME) {
				MixinEnvironment.getCurrentEnvironment().audit();
				this.halt(false);
			}
		}
	}
}
