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
 * Events related to a ticking Minecraft server's levels.
 * <h2>A note of warning</h2>
 * <p>
 * Callbacks registered to any of these events should ensure as little time as possible is spent executing, since the tick
 * loop is a very hot code path.
 */
public final class ServerLevelTickEvents {
	/**
	 * This event is invoked at the beginning of a tick.
	 */
	public static final Event<Begin> BEGIN = Event.create(Begin.class, callbacks -> (server, world) -> {
		for (var callback : callbacks) {
			callback.onLevelTickBegin(server, world);
		}
	});

	/**
	 * This event is invoked at the end of a tick.
	 */
	public static final Event<End> END = Event.create(End.class, callbacks -> (server, world) -> {
		for (var callback : callbacks) {
			callback.onLevelTickEnd(server, world);
		}
	});

	private ServerLevelTickEvents() {
	}

	/**
	 * @see #BEGIN
	 */
	@FunctionalInterface
	public interface Begin extends EventAwareListener {
		/**
		 * @see #BEGIN
		 */
		void onLevelTickBegin(MinecraftServer server, ServerLevel level);
	}

	/**
	 * @see #END
	 */
	@FunctionalInterface
	public interface End extends EventAwareListener {
		/**
		 * @see #END
		 */
		void onLevelTickEnd(MinecraftServer server, ServerLevel level);
	}
}
