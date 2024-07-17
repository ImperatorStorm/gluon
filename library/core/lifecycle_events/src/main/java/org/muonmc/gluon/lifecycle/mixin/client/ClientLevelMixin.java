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
import net.minecraft.client.multiplayer.ClientLevel;
import org.muonmc.gluon.lifecycle.api.event.client.ClientEntityTickEvents;
import org.muonmc.gluon.lifecycle.api.event.client.ClientLevelTickEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
abstract class ClientLevelMixin {
	@Shadow
	@Final
	private Minecraft minecraft;

	// Level Ticking
	// A note of confusion: The original QSL claims that level ticking does almost nothing but advance the time, therefore it invokes the level ticking
	// events during entity ticking. This is technically true, but we don't want to confuse users. For now, we'll assume the beginning of a level tick is when
	// the unpaused client ticks rather than when the entities tick.

	@Inject(
		method = "tick", at = @At("HEAD")
	)
	private void onTickBegin(CallbackInfo ci) {
		ClientLevelTickEvents.BEGIN.invoker().onLevelTickBegin(this.minecraft, (ClientLevel) (Object) this);
	}

	@Inject(
		method = "tick", at = @At("TAIL")
	)
	private void onTickEnd(CallbackInfo ci) {
		ClientLevelTickEvents.END.invoker().onLevelTickEnd(this.minecraft, (ClientLevel) (Object) this);
	}

	// Entity Ticking

	@Inject(
		method = "tickEntities", at = @At("HEAD")
	)
	private void onEntityTickBegin(CallbackInfo ci) {
		ClientEntityTickEvents.BEGIN.invoker().onEntityTickBegin(this.minecraft, (ClientLevel) (Object) this);
	}

	@Inject(
		method = "tickEntities", at = @At("TAIL")
	)
	private void onEntityTickEnd(CallbackInfo ci) {
		ClientEntityTickEvents.END.invoker().onEntityTickEnd(this.minecraft, (ClientLevel) (Object) this);
	}
}
