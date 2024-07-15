/*
 * Copyright 2021, 2022, 2023, 2024 The Quilt Project
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

/**
 * <h2>The Quilt Base APIs.</h2>
 * <p>
 * The Quilt Base API contains most of the founding stones for Gluon.
 * This module does not depend on other modules.
 *
 * <p>
 * <h3>{@link org.muonmc.gluon.base.api.event Event APIs}</h3>
 * This module offers an event framework used in most of Gluon.
 * An event is created through the {@link org.muonmc.gluon.base.api.event.Event} class.
 *
 * <p>
 * <h3>{@link org.muonmc.gluon.base.api.entrypoint Entrypoints}</h3>
 * This module provides the most basic entrypoints:
 * <ul>
 *     <li>{@link org.muonmc.gluon.base.api.entrypoint.ModInitializer the common initializer}</li>
 *     <li>{@link org.muonmc.gluon.base.api.entrypoint.client.ClientModInitializer the client initializer}</li>
 *     <li>{@link org.muonmc.gluon.base.api.entrypoint.server.DedicatedServerModInitializer the dedicated server initializer}</li>
 * </ul>
 * More information can be found in {@link org.muonmc.gluon.base.api.entrypoint the package itself}.
 *
 * <p>
 * <h3>{@link org.muonmc.gluon.base.api.phase Phase Sorting APIs}</h3>
 * This module offers a phase sorter, which is a very simple non-cyclic graph solver.
 * This is used for ordering event phases and may be used for other stuff.
 * To sort phases {@linkplain org.muonmc.gluon.base.api.phase.PhaseSorting#sortPhases(java.util.List) a sort method is provided}.
 *
 * <p>
 * <h3>Other Utilities</h3>
 * This module provides some common utilities found in {@link org.muonmc.gluon.base.api.util the util package}.
 */

package org.muonmc.gluon.base.api;
