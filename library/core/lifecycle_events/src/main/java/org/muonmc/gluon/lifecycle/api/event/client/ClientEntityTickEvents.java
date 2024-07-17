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
 * Events related to a ticking Minecraft client's entities.
 *
 * <p>
 * Note that entities are ticked before the level is ticked. If you need to increment anything time-related, see {@link ClientLevelTickEvents}. However, block
 * entity rendering should be handled in these events.
 *
 * <h2>A note of warning</h2>
 * <p>
 * Callbacks registered to any of these events should ensure as little time as possible is spent executing, since the tick
 * loop is a very hot code path.
 *
 * @see ClientLevelTickEvents
 */
public final class ClientEntityTickEvents {
	/**
	 * This event is invoked at the beginning of a tick.
	 */
	public static final Event<Begin> BEGIN = Event.create(Begin.class, callbacks -> (minecraft, level) -> {
		for (var callback : callbacks) {
			callback.onEntityTickBegin(minecraft, level);
		}
	});

	/**
	 * This event is invoked at the end of a tick.
	 *
	 * <h2>Tick Count</h2>
	 * The level tick count has not increased yet, so it should be accurate when this event is invoked.
	 */
	public static final Event<End> END = Event.create(End.class, callbacks -> (minecraft, level) -> {
		for (var callback : callbacks) {
			callback.onEntityTickEnd(minecraft, level);
		}
	});

	private ClientEntityTickEvents() {
	}

	/**
	 * @see #BEGIN
	 */
	@FunctionalInterface
	public interface Begin extends ClientEventAwareListener {
		/**
		 * @see #BEGIN
		 */
		void onEntityTickBegin(Minecraft minecraft, ClientLevel level);
	}

	/**
	 * @see #END
	 */
	@FunctionalInterface
	public interface End extends ClientEventAwareListener {
		/**
		 * @see #END
		 */
		void onEntityTickEnd(Minecraft minecraft, ClientLevel level);
	}
}
