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

package org.muonmc.gluon.lifecycle.api.event;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.muonmc.gluon.base.api.event.Event;
import org.muonmc.gluon.base.api.event.EventAwareListener;

/**
 * Server events that trigger when the level loads and unloads.
 *
 * <p>
 * This can be useful for mods that support dynamically loading levels which need to initialize or clean up references to worlds upon loading or exiting a
 * world.
 *
 * <h2>Warning</h2>
 * <p>
 * You must call these events if you plan to load or unload a level by yourself (i.e. not via Vanilla server code).
 */
public final class ServerLevelLoadEvents {
	/**
	 * An event that triggers when a {@link ServerLevel} is loading.
	 *
	 * <p>
	 * Note that a {@link ServerLevel} is not prepared nor fully loaded when this event is invoked.
	 */
	public static final Event<Load> LOAD = Event.create(Load.class, callbacks -> (server, world) -> {
		for (var callback : callbacks) {
			callback.onLoad(server, world);
		}
	});

	/**
	 * An event that triggers when a {@link ServerLevel} unloads.
	 *
	 * <p>
	 * When this event triggers, the {@link ServerLevel} is just about to be entirely unloaded and has been saved.
	 */
	public static final Event<Unload> UNLOAD = Event.create(Unload.class, callbacks -> (server, world) -> {
		for (var callback : callbacks) {
			callback.onUnload(server, world);
		}
	});

	private ServerLevelLoadEvents() {
	}

	/**
	 * @see #LOAD
	 */
	@FunctionalInterface
	public interface Load extends EventAwareListener {
		/**
		 * @see #LOAD
		 */
		void onLoad(MinecraftServer server, ServerLevel level);
	}

	/**
	 * @see #UNLOAD
	 */
	@FunctionalInterface
	public interface Unload extends EventAwareListener {
		/**
		 * @see #UNLOAD
		 */
		void onUnload(MinecraftServer server, ServerLevel level);
	}
}
