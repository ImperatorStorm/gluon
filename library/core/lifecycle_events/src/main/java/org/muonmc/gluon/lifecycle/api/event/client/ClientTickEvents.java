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
import org.muonmc.gluon.base.api.event.Event;
import org.muonmc.gluon.base.api.event.client.ClientEventAwareListener;

/**
 * Events that trigger during the lifespan of a tick.
 *
 * <p>
 * These events are unrelated to {@link ClientLevelTickEvents}.
 *
 * @see ClientLevelTickEvents
 */
public final class ClientTickEvents {
	/**
	 * An event that triggers each time the client starts ticking.
	 *
	 * <p>
	 * This event triggers before client ticking occurs.
	 */
	public static final Event<Begin> BEGIN = Event.create(Begin.class, callbacks -> minecraft -> {
		for (var callback : callbacks) {
			callback.onTickBegin(minecraft);
		}
	});

	/**
	 * An event that triggers at the end of a client tick.
	 *
	 * <p>
	 * This event triggers after client ticking occurs.
	 *
	 * <h2>Tick Count</h2>
	 * Note that the tick count has increased since the beginning of the tick. That means that the current tick count is ahead by one.
	 */
	public static final Event<End> END = Event.create(End.class, callbacks -> minecraft -> {
		for (var callback : callbacks) {
			callback.onTickEnd(minecraft);
		}
	});

	private ClientTickEvents() {}

	/**
	 * @see #BEGIN
	 */
	@FunctionalInterface
	public interface Begin extends ClientEventAwareListener {
		/**
		 * @see #BEGIN
		 */
	  void onTickBegin(Minecraft minecraft);
	}

	/**
	 * @see #END
	 */
	@FunctionalInterface
	public interface End extends ClientEventAwareListener {
		/**
		 * @see #END
		 */
		void onTickEnd(Minecraft minecraft);
	}
}
