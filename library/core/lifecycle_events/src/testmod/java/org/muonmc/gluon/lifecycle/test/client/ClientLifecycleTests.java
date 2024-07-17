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
import org.muonmc.gluon.lifecycle.api.event.client.ClientLifecycleEvents;
import org.quiltmc.loader.api.ModInternal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ModInternal
public final class ClientLifecycleTests implements ClientLifecycleEvents.Ready, ClientLifecycleEvents.Loaded, ClientLifecycleEvents.Stopping, ClientLifecycleEvents.Stopped {
	private static final Logger LOGGER = LoggerFactory.getLogger("gluon_lifecycle_events/ClientLifecycleTests");

	@Override
	public void onReady(Minecraft minecraft) {
		LOGGER.info("Client ready");
	}

	@Override
	public void onLoaded(Minecraft minecraft) {
		LOGGER.info("Client loaded");
	}

	@Override
	public void onStopping(Minecraft minecraft) {
		LOGGER.info("Client stopping");
	}

	@Override
	public void onStopped() {
		LOGGER.info("Client stopped");
	}
}
