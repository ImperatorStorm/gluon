/*
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

package org.muonmc.gluon.lifecycle.mixin.client;

import net.minecraft.client.Minecraft;
import org.muonmc.gluon.lifecycle.api.event.client.ClientLifecycleEvents;
import org.muonmc.gluon.lifecycle.api.event.client.ClientTickEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
abstract class MinecraftMixin {
	// Client Lifecycle

	@Inject(
		method = "run",
		at = @At("HEAD")
	)
	private void onRun(CallbackInfo ci) {
		ClientLifecycleEvents.READY.invoker().onReady((Minecraft) (Object) this);
	}

	@Inject(
		method = "onGameLoadFinished",
		at = @At("TAIL")
	)
	private void onGameLoadFinished(CallbackInfo ci) {
		ClientLifecycleEvents.LOADED.invoker().onLoaded((Minecraft) (Object) this);
	}

	@Inject(
		method = "destroy",
		at = @At("HEAD")
	)
	private void onDestroy(CallbackInfo ci) {
		ClientLifecycleEvents.STOPPING.invoker().onStopping((Minecraft) (Object) this);
	}

	@Inject(
		method = "close",
		at = @At("TAIL")
	)
	private void onCloseTail(CallbackInfo ci) {
		ClientLifecycleEvents.STOPPED.invoker().onStopped();
	}

	// Ticking

	@Inject(
		method = "tick",
		at = @At("HEAD")
	)
	private void onTickBegin(CallbackInfo ci) {
		ClientTickEvents.BEGIN.invoker().onTickBegin((Minecraft) (Object) this);
	}

	@Inject(
		method = "tick",
		at = @At("TAIL")
	)
	private void onTickEnd(CallbackInfo ci) {
		ClientTickEvents.END.invoker().onTickEnd((Minecraft) (Object) this);
	}
}
