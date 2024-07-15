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
 * Server events that trigger during ticking.
 *
 * <p>
 * These events are unrelated to {@link ServerLevelTickEvents}.
 *
 * @see ServerLevelTickEvents
 */
public final class ServerTickEvents {
	/**
	 * An event that triggers each time the server starts ticking.
	 *
	 * <p>
	 * This event triggers before level ticking occurs.
	 */
	public static final Event<Begin> BEGIN = Event.create(Begin.class, callbacks -> server -> {
		for (var callback : callbacks) {
			callback.onTickBegin(server);
		}
	});

	/**
	 * An event that triggers at the end of a server tick.
	 *
	 * <p>
	 * This event triggers after level ticking occurs.
	 */
	public static final Event<End> END = Event.create(End.class, callbacks -> server -> {
		for (var callback : callbacks) {
			callback.onTickEnd(server);
		}
	});

	private ServerTickEvents() {
	}

	/**
	 * @see #BEGIN
	 */
	@FunctionalInterface
	public interface Begin extends EventAwareListener {
		/**
		 * @see #BEGIN
		 */
		void onTickBegin(MinecraftServer server);
	}

	/**
	 * @see #END
	 */
	@FunctionalInterface
	public interface End extends EventAwareListener {
		/**
		 * @see #END
		 */
		void onTickEnd(MinecraftServer server);
	}
}
