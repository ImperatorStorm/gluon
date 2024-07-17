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

package org.muonmc.gluon.lifecycle.test.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import org.muonmc.gluon.lifecycle.api.event.client.ClientEntityTickEvents;
import org.muonmc.gluon.lifecycle.api.event.client.ClientLevelTickEvents;
import org.muonmc.gluon.lifecycle.api.event.client.ClientTickEvents;
import org.muonmc.gluon.lifecycle.mixin.client.ClientTickCountAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientTickTests implements ClientTickEvents.Begin, ClientTickEvents.End, ClientLevelTickEvents.Begin, ClientLevelTickEvents.End, ClientEntityTickEvents.Begin, ClientEntityTickEvents.End {
	private static final Logger LOGGER = LoggerFactory.getLogger("gluon_lifecycle_events/ClientTickTests");

	// note that we subtract 1 on each of the tick end events since the tick counter was incremented

	@Override
	public void onEntityTickBegin(Minecraft minecraft, ClientLevel level) {
		long ticks = level.getGameTime();
		if (ticks % 40 == 0) {
			LOGGER.info("Entity tick begin at {}t", ticks);
		}
	}

	@Override
	public void onEntityTickEnd(Minecraft minecraft, ClientLevel level) {
		long ticks = level.getGameTime();
		if (ticks % 40 == 0) {
			LOGGER.info("Entity tick end at {}t", ticks);
		}
	}

	@Override
	public void onLevelTickBegin(Minecraft minecraft, ClientLevel level) {
		long ticks = level.getGameTime();
		if (ticks % 40 == 0) {
			LOGGER.info("Level tick begin at {}t", ticks);
		}
	}

	@Override
	public void onLevelTickEnd(Minecraft minecraft, ClientLevel level) {
		long ticks = level.getGameTime() - 1;
		if (ticks % 40 == 0) {
			LOGGER.info("Level tick end at {}t", ticks);
		}
	}

	@Override
	public void onTickBegin(Minecraft minecraft) {
		long ticks = ((ClientTickCountAccessor) minecraft).getClientTickCount();
		if (ticks % 40 == 0) {
			LOGGER.info("Client tick begin at {}t", ticks);
		}
	}

	@Override
	public void onTickEnd(Minecraft minecraft) {
		long ticks = ((ClientTickCountAccessor) minecraft).getClientTickCount() - 1;
		if (ticks % 40 == 0) {
			LOGGER.info("Client tick end at {}t", ticks);
		}
	}
}
