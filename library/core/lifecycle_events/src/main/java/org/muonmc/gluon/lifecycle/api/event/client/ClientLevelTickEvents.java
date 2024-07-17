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

package org.muonmc.gluon.lifecycle.api.event.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import org.muonmc.gluon.base.api.event.Event;
import org.muonmc.gluon.base.api.event.client.ClientEventAwareListener;

/**
 * Events related to a ticking Minecraft client's level.
 *
 * <h2>Entity Ticking</h2>
 * <p>
 * Note that these events are only called in the method that handles time and world border ticking. For entity ticking events, see
 * {@link ClientEntityTickEvents}.
 *
 * <h2>A note of warning</h2>
 * <p>
 * Callbacks registered to any of these events should ensure as little time as possible is spent executing, since the tick loop is a very hot code path.
 *
 * @see ClientEntityTickEvents
 */
public final class ClientLevelTickEvents {
	/**
	 * This event is invoked at the beginning of a tick.
	 *
	 * <p>
	 * This event triggers before level ticking occurs.
	 */
	public static final Event<Begin> BEGIN = Event.create(Begin.class, callbacks -> (minecraft, level) -> {
		for (var callback : callbacks) {
			callback.onLevelTickBegin(minecraft, level);
		}
	});

	/**
	 * This event is invoked at the end of a tick.
	 *
	 * <p>
	 * This event triggers after level ticking occurs.
	 *
	 * <h2>Tick Count</h2>
	 * Note that the tick count has increased since the beginning of the tick. That means that the current tick count is ahead by one.
	 */
	public static final Event<End> END = Event.create(End.class, callbacks -> (minecraft, level) -> {
		for (var callback : callbacks) {
			callback.onLevelTickEnd(minecraft, level);
		}
	});

	private ClientLevelTickEvents() {
	}

	/**
	 * @see #BEGIN
	 */
	@FunctionalInterface
	public interface Begin extends ClientEventAwareListener {
		/**
		 * @see #BEGIN
		 */
		void onLevelTickBegin(Minecraft minecraft, ClientLevel level);
	}

	/**
	 * @see #END
	 */
	@FunctionalInterface
	public interface End extends ClientEventAwareListener {
		/**
		 * @see #END
		 */
		void onLevelTickEnd(Minecraft minecraft, ClientLevel level);
	}
}
