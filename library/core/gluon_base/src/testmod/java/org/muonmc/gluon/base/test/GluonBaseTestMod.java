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

package org.muonmc.gluon.base.test;

import org.muonmc.gluon.base.api.entrypoint.ModInitializer;
import org.muonmc.gluon.base.test.event.EventListenAllTests;
import org.muonmc.gluon.base.test.event.EventTests;
import org.quiltmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class GluonBaseTestMod implements ModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(GluonBaseTestMod.class);

	@Override
	public void onInitialize(ModContainer mod) {
		List.of(new EventTests(), new EventListenAllTests())
			.forEach(test -> {
				LOGGER.info("Testing " + test.getClass().getSimpleName() + "...");
				test.run();
			});
	}
}
