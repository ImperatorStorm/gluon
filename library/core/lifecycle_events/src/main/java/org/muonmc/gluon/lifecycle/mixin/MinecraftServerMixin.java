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

package org.muonmc.gluon.lifecycle.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.muonmc.gluon.lifecycle.api.event.ServerLifecycleEvents;
import org.muonmc.gluon.lifecycle.api.event.ServerLevelLoadEvents;
import org.muonmc.gluon.lifecycle.api.event.ServerLevelTickEvents;
import org.muonmc.gluon.lifecycle.api.event.ServerTickEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	// Lifecycle

	@Inject(
		method = "runServer",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"
		)
	)
	private void onStarting(CallbackInfo ci) {
		ServerLifecycleEvents.STARTING.invoker().onServerStarting((MinecraftServer) (Object) this);
	}

	@Inject(
		method = "runServer",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/server/MinecraftServer;status:Lnet/minecraft/network/protocol/status/ServerStatus;",
			// Inject after the field write so that users can access the status field.
			shift = At.Shift.AFTER
		)
	)
	private void onReady(CallbackInfo ci) {
		ServerLifecycleEvents.READY.invoker().onServerReady((MinecraftServer) (Object) this);
	}

	@Inject(
		method = "stopServer",
		at = @At("HEAD")
	)
	private void onStopping(CallbackInfo ci) {
		ServerLifecycleEvents.STOPPING.invoker().onServerStopping((MinecraftServer) (Object) this);
	}

	/**
	 * @implNote see {@link ServerLifecycleEvents#STOPPED}
	 */
	@Inject(
		method = "stopServer",
		at = @At("TAIL")
	)
	private void onStopped(CallbackInfo ci) {
		MinecraftServer self = (MinecraftServer) (Object) this;
		// run the STOPPED event if this is a third-party implementation of MinecraftServer
		if (!(self instanceof DedicatedServer) && !(self instanceof IntegratedServer)) {
			ServerLifecycleEvents.STOPPED.invoker().onServerStopped(self);
		}
	}

	// Level Load

	/**
	 * @implNote We wrap each of the method calls so that we can access the {@link ServerLevel}. This is because {@link Map#put(Object, Object)} is called twice: once for the Overworld and again for the other levels.
	 */
	@WrapOperation(
		method = "createLevels",
		at = @At(
			value = "INVOKE",
			target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
		)
	)
	private Object onLoadLevel(Map<ResourceKey<Level>, ServerLevel> instance, Object resourceKey, Object level, Operation<Map<ResourceKey<Level>, ServerLevel>> original) {
		ServerLevelLoadEvents.LOAD.invoker().onLoad((MinecraftServer) (Object) this, (ServerLevel) level);
		return original.call(instance, resourceKey, level);
	}

	@Inject(
		method = "stopServer",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/level/ServerLevel;close()V"
		)
	)
	private void onUnloadLevel(CallbackInfo ci, @Local ServerLevel level) {
		ServerLevelLoadEvents.UNLOAD.invoker().onUnload((MinecraftServer) (Object) this, level);
	}

	// Level Ticking

	@Inject(
		method = "tickChildren",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/level/ServerLevel;tick(Ljava/util/function/BooleanSupplier;)V",
			shift = At.Shift.BEFORE
		)
	)
	private void beforeTickLevel(CallbackInfo ci, @Local ServerLevel level) {
		ServerLevelTickEvents.BEGIN.invoker().onLevelTickBegin((MinecraftServer) (Object) this, level);
	}

	@Inject(
		method = "tickChildren",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/level/ServerLevel;tick(Ljava/util/function/BooleanSupplier;)V",
			shift = At.Shift.AFTER
		)
	)
	private void afterTickLevel(CallbackInfo ci, @Local ServerLevel level) {
		ServerLevelTickEvents.END.invoker().onLevelTickEnd((MinecraftServer) (Object) this, level);
	}

	// Server Ticking

	@Inject(
		method = "tickServer",
		at = @At("HEAD")
	)
	private void beforeTick(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
		ServerTickEvents.BEGIN.invoker().onTickBegin((MinecraftServer) (Object) this);
	}

	@Inject(
		method = "tickServer",
		at = @At("TAIL")
	)
	private void afterTick(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
		ServerTickEvents.END.invoker().onTickEnd((MinecraftServer) (Object) this);
	}
}
