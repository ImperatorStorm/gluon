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

package org.muonmc.gluon.lifecycle.test;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.muonmc.gluon.lifecycle.api.event.ServerLevelLoadEvents;
import org.muonmc.gluon.lifecycle.api.event.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerLifecycleTests implements ServerLifecycleEvents.Starting, ServerLifecycleEvents.Ready, ServerLifecycleEvents.Stopping, ServerLifecycleEvents.Stopped,
	ServerLevelLoadEvents.Load, ServerLevelLoadEvents.Unload {
	private static final Logger LOGGER = LoggerFactory.getLogger("gluon_lifecycle_events/ServerLifecycleTests");

	@Override
	public void onServerStarting(MinecraftServer server) {
		LOGGER.info("Server starting");
	}

	@Override
	public void onServerReady(MinecraftServer server) {
		LOGGER.info("Server ready");
	}

	@Override
	public void onServerStopping(MinecraftServer server) {
		LOGGER.info("Server stopping");
	}

	@Override
	public void onServerStopped(MinecraftServer server) {
		LOGGER.info("Server stopped");
	}

	@Override
	public void onLoad(MinecraftServer server, ServerLevel level) {
		LOGGER.info("level {} loaded", level.dimension().location());
	}

	@Override
	public void onUnload(MinecraftServer server, ServerLevel level) {
		LOGGER.info("level {} unloaded", level.dimension().location());
	}
}
