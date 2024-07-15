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

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.muonmc.gluon.lifecycle.api.event.ServerLevelTickEvents;
import org.muonmc.gluon.lifecycle.api.event.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerTickTests implements ServerLevelTickEvents.Begin, ServerLevelTickEvents.End, ServerTickEvents.Begin {
	private static final Logger LOGGER = LoggerFactory.getLogger("gluon_lifecycle_events/ServerTickTests");

	private final Object2IntMap<ResourceKey<Level>> TICKS = new Object2IntOpenHashMap<>();

	@Override
	public void onTickBegin(MinecraftServer server) {
		if (server.getTickCount() > 20 * 2) {
			// stop the server once we're at 40 ticks (2 seconds @ 20 TPS)
			server.halt(false);
		} else if (server.getTickCount() % 20 == 0) {
			LOGGER.info("Server tick begin at {}t", server.getTickCount());
		}
	}

	@Override
	public void onLevelTickBegin(MinecraftServer server, ServerLevel level) {
		final int ticks = TICKS.computeIfAbsent(level.dimension(), k -> 0);
		if (ticks % 20 == 0) {
			LOGGER.info("Level {} tick begin at {}t", level.dimension().location(), ticks);
		}
	}

	@Override
	public void onLevelTickEnd(MinecraftServer server, ServerLevel level) {
		final int ticks = TICKS.computeIfAbsent(level.dimension(), k -> 0);
		if (ticks % 20 == 0) {
			LOGGER.info("Level {} tick end at {}t", level.dimension().location(), ticks);
		}

		TICKS.put(level.dimension(), ticks + 1);
	}
}
