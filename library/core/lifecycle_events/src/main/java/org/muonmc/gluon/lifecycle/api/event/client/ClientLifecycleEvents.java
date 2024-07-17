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
 * Events describing the lifecycle of a client.
 *
 * <h1>Client Lifecycle</h1>
 * <p>
 * Note that we do not have a preload event. Use {@link org.muonmc.gluon.base.api.entrypoint.client.ClientModInitializer} if you need to run code before the
 * loading screen appears.
 *
 * @see org.muonmc.gluon.base.api.entrypoint.client.ClientModInitializer
 */
public final class ClientLifecycleEvents {
	/**
	 * <h2>Ready</h2>
	 * The first event triggered is {@link Ready}. This event triggers when the loading screen appears. This indicates that the client begins rendering and
	 * ticking.
	 */
	public static final Event<Ready> READY = Event.create(Ready.class, callbacks -> minecraft -> {
		for (var callback : callbacks) {
			callback.onReady(minecraft);
		}
	});

	/**
	 * <h2>Loaded</h2>
	 * The next event is {@link Loaded}. This event triggers after loading has finished. The main menu appears after this event.
	 */
	public static final Event<Loaded> LOADED = Event.create(Loaded.class, callbacks -> minecraft -> {
		for (var callback : callbacks) {
			callback.onLoaded(minecraft);
		}
	});

	/**
	 * <h2>Stopping</h2>
	 * This event is triggered when the player initially quits the game.
	 *
	 * <h4>Warning</h4>
	 * Note that if the game crashes irrecoverably, this event may not trigger.
	 */
	public static final Event<Stopping> STOPPING = Event.create(Stopping.class, callbacks -> minecraft -> {
		for (var callback : callbacks) {
			callback.onStopping(minecraft);
		}
	});

	/**
	 * <h2>Stopped</h2>
	 * This event is triggered when the client has completely quit and unloaded.
	 *
	 * <h4>Warning</h4>
	 * Note that if the game crashes irrecoverably, this event may not trigger.
	 */
	public static final Event<Stopped> STOPPED = Event.create(Stopped.class, callbacks -> () -> {
		for (var callback : callbacks) {
			callback.onStopped();
		}
	});

	private ClientLifecycleEvents() {
	}

	/**
	 * @see #READY
	 */
	@FunctionalInterface
	public interface Ready extends ClientEventAwareListener {
		/**
		 * @see #READY
		 */
		void onReady(Minecraft minecraft);
	}

	/**
	 * @see #LOADED
	 */
	@FunctionalInterface
	public interface Loaded extends ClientEventAwareListener {
		/**
		 * @see #LOADED
		 */
		void onLoaded(Minecraft minecraft);
	}

	/**
	 * @see #STOPPING
	 */
	@FunctionalInterface
	public interface Stopping extends ClientEventAwareListener {
		/**
		 * @see #STOPPING
		 */
		void onStopping(Minecraft minecraft);
	}

	/**
	 * @see #STOPPED
	 */
	@FunctionalInterface
	public interface Stopped extends ClientEventAwareListener {
		/**
		 * @see #STOPPED
		 */
		void onStopped();
	}
}
