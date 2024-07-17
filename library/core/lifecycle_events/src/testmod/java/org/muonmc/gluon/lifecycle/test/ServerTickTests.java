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
import org.muonmc.gluon.lifecycle.api.event.ServerLevelTickEvents;
import org.muonmc.gluon.lifecycle.api.event.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerTickTests implements ServerLevelTickEvents.Begin, ServerLevelTickEvents.End, ServerTickEvents.Begin {
	private static final Logger LOGGER = LoggerFactory.getLogger("gluon_lifecycle_events/ServerTickTests");

	@Override
	public void onTickBegin(MinecraftServer server) {
		if (server.getTickCount() > 20 * 6) {
			// stop the server once we're at 120 ticks (6 seconds @ 20 TPS)
			server.halt(false);
		} else if (server.getTickCount() % 20 == 0) {
			LOGGER.info("Server tick begin at {}t", server.getTickCount());
		}
	}

	@Override
	public void onLevelTickBegin(MinecraftServer server, ServerLevel level) {
		long ticks = level.getLevelData().getGameTime();
		if (ticks % 20 == 0) {
			LOGGER.info("Level {} tick begin at {}t", level.dimension().location(), ticks);
		}
	}

	@Override
	public void onLevelTickEnd(MinecraftServer server, ServerLevel level) {
		long ticks = level.getLevelData().getGameTime() - 1;
		if (ticks % 20 == 0) {
			LOGGER.info("Level {} tick end at {}t", level.dimension().location(), ticks);
		}
	}
}
