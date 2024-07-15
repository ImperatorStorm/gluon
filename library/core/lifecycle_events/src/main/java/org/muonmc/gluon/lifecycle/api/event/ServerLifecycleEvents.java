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
import org.muonmc.gluon.base.api.event.Event;
import org.muonmc.gluon.base.api.event.EventAwareListener;

/**
 * Events triggered during the lifecycle of a Minecraft server.
 *
 * <p>
 * A Minecraft server's lifecycle is dictated by its starting, ready¸ stopping, and stopped states. Once ready, the
 * server ticks until it exits (stopped).
 *
 * <p>
 * For code that needs to run every tick, see {@link ServerTickEvents}.
 *
 * @see ServerTickEvents
 */
public final class ServerLifecycleEvents {
	/**
	 * An event indicating the start of a server.
	 *
	 * <p>
	 * At this point, no player manager, server status, or world is loaded.
	 */
	public static final Event<Starting> STARTING = Event.create(Starting.class, callbacks -> server -> {
		for (var callback : callbacks) {
			callback.onServerStarting(server);
		}
	});

	/**
	 * An event indicating that the server is ready to tick.
	 *
	 * <p>
	 * This event triggers when the server is done loading its status, the world is loaded, and player data is initialized.
	 */
	public static final Event<Ready> READY = Event.create(Ready.class, callbacks -> server -> {
		for (var callback : callbacks) {
			callback.onServerReady(server);
		}
	});

	/**
	 * An event indicating the server is stopping.
	 *
	 * <p>
	 * This event triggers when the server begins stopping, before server data is cleaned.
	 */
	public static final Event<Stopping> STOPPING = Event.create(Stopping.class, callbacks -> server -> {
		for (var callback : callbacks) {
			callback.onServerStopping(server);
		}
	});

	/**
	 * An event indicating that the server has stopped.
	 *
	 * <p>
	 * This event triggers when the server has finished cleaning up relevant server data.
	 *
	 * <h2>Compatibility Warning</h2>
	 * Any implementations of {@link MinecraftServer} that require overriding {@link MinecraftServer#stopServer()} must use this event. This ensures that other
	 * mods run their code **after** all relevant data is cleaned up.
	 */
	public static final Event<Stopped> STOPPED = Event.create(Stopped.class, callbacks -> server -> {
		for (var callback : callbacks) {
			callback.onServerStopped(server);
		}
	});

	private ServerLifecycleEvents() {
	}

	/**
	 * @see #STARTING
	 */
	@FunctionalInterface
	public interface Starting extends EventAwareListener {
		/**
		 * @see #STARTING
		 */
		void onServerStarting(MinecraftServer server);
	}

	/**
	 * @see #READY
	 */
	@FunctionalInterface
	public interface Ready extends EventAwareListener {
		/**
		 * @see #READY
		 */
		void onServerReady(MinecraftServer server);
	}

	/**
	 * @see #STOPPING
	 */
	@FunctionalInterface
	public interface Stopping extends EventAwareListener {
		/**
		 * @see #STOPPING
		 */
		void onServerStopping(MinecraftServer server);
	}

	/**
	 * @see #STOPPED
	 */
	@FunctionalInterface
	public interface Stopped extends EventAwareListener {
		/**
		 * @see #STOPPED
		 */
		void onServerStopped(MinecraftServer server);
	}
}
