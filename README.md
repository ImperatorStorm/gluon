# Gluon (Gluon)

![Java 17](https://img.shields.io/badge/language-Java%2017-9B599A.svg?style=flat-square)
[![GitHub license](https://img.shields.io/github/license/QuiltMC/quilt-standard-libraries?style=flat-square)](https://raw.githubusercontent.com/QuiltMC/quilt-standard-libraries/1.19/LICENSE)
[![Mod loader: Quilt]][quilt]
![Version](https://img.shields.io/github/v/tag/QuiltMC/quilt-standard-libraries?label=version&style=flat-square)

Essential standard libraries for [the Quilt ecosystem](https://quiltmc.org/).

The Gluon gives modders Muon-exclusive tools to add new and exciting features to their mods.

**Note: At the moment, Gluon is in beta, meaning issues may arise and should still be treated as experimental.
Please make an issue or talk to the Gluon team on [discord](https://discord.quiltmc.org/toolchain) or on [the forum](https://forum.quiltmc.org) before
writing any PRs.**

## Repository structure

The repository has 2 main parts:

- The `library` folder. This contains all the libraries that are part of Gluon.
- The `build-logic` folder. This is an included build in Gradle and contains most of the buildscript used inside the
  libraries. This keeps the buildscripts inside the `library` folder as minimal as possible; definitions of data rather
  than logic.

## Features

Here are multiple charts of features available in Gluon which also serves as a comparison chart with Fabric API.

The charts are organized by Gluon libraries.

Quick legend:

- ✔ = Included
- ❌ = Not Included/Not Yet
- 🙅 = No plans
- 🚧 = Work In Progress

### Core Library

| Feature                           | Gluon |  Fabric API   |
|:----------------------------------|:---:|:-------------:|
| Auto Test Server argument         |  ✔  |       ❌       |
| Event API                         |  ✔  |       ✔       |
| Event API - Phases                |  ✔  |       ✔       |
| Event API - Events as Entrypoints |  ✔  |       ❌       |
| Gametest API                      |  ✔  |       ✔       |
| Initializer Entrypoints           |  ✔  | ✔ (in loader) |
| Networking API                    |  ✔  |       ✔       |

#### Crash Report

| Feature                        | Gluon | Fabric API |
|:-------------------------------|:---:|:----------:|
| Crash report extra context     |  ✔  |     ✔      |
| Crash report extra context API |  ✔  |     ❌      |

### Core - Lifecycle Events

| Feature                    | Gluon | Fabric API |
|:---------------------------|:---:|:----------:|
| Client Lifecycle Events    |  ✔  |     ✔      |
| Client Tick Events         |  ✔  |     ✔      |
| Client World Tick Events   |  ✔  |     ✔      |
| Client Block Entity Events |  ❌  |     ✔      |
| Client Chunk Events        |  ❌  |     ✔      |
| Common Lifecycle Events    |  ❌  |     ✔      |
| Server Lifecycle Events    |  ✔  |     ✔      |
| Server Tick Events         |  ✔  |     ✔      |
| Server World Load Events   |  ✔  |     ✔      |
| Server World Tick Events   |  ✔  |     ✔      |
| Server Block Entity Events |  ❌  |     ✔      |
| Server Chunk Events        |  ❌  |     ✔      |

### Core - Registry

| Feature                                     | Gluon | Fabric API |
|:--------------------------------------------|:---:|:----------:|
| Addition Events                             |  ✔  |     ✔      |
| Addition Events Helper                      |  ✔  |     ❌      |
| Registry Syncing                            |  ✔  |     ✔      |
| Registry Syncing - Exclude Specific Entries |  ✔  |     ❌      |

### Core - Resource Loader

| Feature                               | Gluon |            Fabric API            |
|:--------------------------------------|:---:|:--------------------------------:|
| Load mod resources.                   |  ✔  |                ✔                 |
| Resource Loader Events                |  ✔  | ✔ (in lifecycle, non equivalent) |
| Built-in resource pack API            |  ✔  |                ✔                 |
| Programmer Art API                    |  ✔  |                ✔                 |
| Group resource pack API               |  ✔  |                🙅                |
| Resource Pack Provider API            |  ✔  |                ❌                 |
| Resource Reloaders                    |  ✔  |                ✔                 |
| Resource Reloaders - Advanced Sorting |  ✔  |                ❌                 |
| Virtual Resource Packs                |  ✔  |                ❌                 |

### Block Library

| Feature                                                 | Gluon | Fabric API |
|:--------------------------------------------------------|:---:|:----------:|
| Extended Block Settings                                 |  ✔  |     ✔      |
| Extended Material Builder                               |  ✔  |     ✔      |
| Block Render Layers API                                 |  ✔  |     ✔      |
| All Block Constructors Are Public                       |  ✔  |     ✔      |
| Block Entity Type registration helper                   |  ✔  |     ✔      |
| Block Entity Type post-creation supported block editing |  ✔  |     🙅     |
| Block Entity Syncing Helper                             |  ✔  |     ❌      |
| Block Content Registry - Flammable                      |  ✔  |     ✔      |
| Block Content Registry - Flammable (data-driven)        |  ✔  |     🙅     |
| Block Content Registry - Flattenable                    |  ✔  |     ✔      |
| Block Content Registry - Flattenable (data-driven)      |  ✔  |     🙅     |
| Block Content Registry - Oxidation                      |  ✔  |     ✔      |
| Block Content Registry - Oxidation (data-driven)        |  ✔  |     🙅     |
| Block Content Registry - Sculk Frequency                |  ✔  |     ✔      |
| Block Content Registry - Sculk Frequency (data-driven)  |  ✔  |     🙅     |
| Block Content Registry - Strippable                     |  ✔  |     ✔      |
| Block Content Registry - Strippable (data-driven)       |  ✔  |     🙅     |
| Block Content Registry - Tileable                       |  ❌  |     ✔      |
| Block Content Registry - Tileable (data-driven)         |  ❌  |     🙅     |
| Block Content Registry - Waxing                         |  ✔  |     ✔      |
| Block Content Registry - Waxing (data-driven)           |  ✔  |     🙅     |

### Data Library

| Feature                                                               |                                      Gluon                                       |    Fabric API     |
|:----------------------------------------------------------------------|:------------------------------------------------------------------------------:|:-----------------:|
| Advancement Criterion Registration Helper                             |                                       ✔                                        |         ✔         |
| Recipe API                                                            |                                       ✔                                        |        🙅         |
| Registry Entry Attachments                                            |                                       ✔                                        |        🙅         |
| Client-fallback/Client-only tags                                      |                                       ✔                                        | ✔ (fallback only) |
| Client-fallback/Client-only tags - integration within Vanilla methods |                                       ✔                                        |        🙅         |
| Convention Tags                                                       |                                       ❌                                        |         ✔         |
| Data Generation                                                       |                                 :construction:                                 |         ✔         |
| Loot Table API                                                        |                                       ❌                                        |         ✔         |
| Resource Conditions                                                   |                                       ❌                                        |         ✔         |
| Component API (like CCA or Forge capabilities)                        | [:construction:](https://github.com/QuiltMC/quilt-standard-libraries/pull/146) |         ❌         |

### Entity Library

| Feature                         | Gluon | Fabric API |
|:--------------------------------|:---:|:----------:|
| EntityType registration helpers |  ✔  |     ✔      |
| Entity Events                   |  ✔  |     ✔      |
| Multipart Entity API            |  ✔  |     ❌      |
| Point of interest helper        |  ✔  |     ✔      |
| Status Effects API              |  ✔  |     ❌      |
| Tracked Data Handler Registry   |  ✔  |     ❌      |
| Trade offer API                 |  ✔  |     ✔      |

### GUI Library

| Feature                   |      Gluon       | Fabric API |
|:--------------------------|:--------------:|:----------:|
| Screen API                |       ✔        |     ✔      |
| Item Tooltip Event        |       ✔        |     ✔      |
| Tooltip Component - Event |       ✔        |     ✔      |
| Key Binds API             | :construction: |     ✔      |
| Screen Handler API        |       ❌        |     ✔      |

### Item Library

| Feature                                         | Gluon | Fabric API |
|:------------------------------------------------|:---:|:----------:|
| Item Extension - Bow                            |  ✔  |     ❌      |
| Item Extension - Crossbow                       |  ✔  |     ❌      |
| Item Groups                                     |  ❌  |     ✔      |
| Item Settings                                   |  ✔  |     ✔      |
| Item Settings - Custom Item Setting             |  ✔  |     ❌      |
| Item Content Registry - Composter               |  ✔  |     ✔      |
| Item Content Registry - Composter (data-driven) |  ✔  |     🙅     |
| Item Content Registry - Fuel                    |  ✔  |     ✔      |
| Item Content Registry - Fuel (data-driven)      |  ✔  |     🙅     |

### Management Library

| Feature                 | Gluon | Fabric API |
|:------------------------|:---:|:----------:|
| Commands                |  ✔  |     ✔      |
| Client Commands         |  ✔  |     ✔      |
| Game Rules              |  ❌  |     ✔      |
| Entity Selector Options |  ✔  |     ✔      |
| Message API             |  ✔  |     ✔      |

### Rendering Library

| Feature                                           |      Gluon       |    Fabric API     |
|:--------------------------------------------------|:--------------:|:-----------------:|
| Renderer API                                      |       ❌        |         ✔         |
| Data Attachment                                   |       ❌        |         ✔         |
| Hud Render API                                    |       ❌        | ✔ (limited Event) |
| Built-in Item Rendering                           |       ❌        |         ✔         |
| Block Entity Renderer Registry                    |       ❌        |         ✔         |
| Armor Rendering                                   |       ✔        |         ✔         |
| Color Provider Registry                           |       ❌        |         ✔         |
| Entity Renderer Registry                          |       ❌        |         ✔         |
| Entity Model Layer Registry                       |       ❌        |         ✔         |
| Living Entity Feature Renderer Registration Event |       ❌        |         ✔         |
| Data-driven Entity Models                         | :construction: |        🙅         |
| Data-driven Animations                            | :construction: |        🙅         |
| World Render Events                               |       ❌        |         ✔         |
| Fluid Rendering                                   |       ❌        |         ✔         |

### Transfer Library

| Feature      |      Gluon       |    Fabric API     |
|:-------------|:--------------:|:-----------------:|
| Transfer API |       ❌        |         ✔         |

### Worldgen Library

| Feature                 | Gluon | Fabric API |
|:------------------------|:---:|:----------:|
| Biome Modifications API |  ✔  |     ✔      |
| Add Nether Biomes       |  ✔  |     ✔      |
| Add End Biomes          |  ✔  |     ✔      |
| Dimension API           |  ✔  |     ✔      |
| Surface Rule API        |  ✔  |     ❌      |

### Miscellaneous Library

| Feature        |                                                  Gluon                                                   | Fabric API |
|:---------------|:------------------------------------------------------------------------------------------------------:|:----------:|
| Modded DFU API |                                                   ❌                                                    |     ❌      |
| API Lookup API | :construction: [(through Component API)](https://github.com/QuiltMC/quilt-standard-libraries/pull/146) |     ✔      |

[quilt]: https://quiltmc.org
[Mod loader: Quilt]: https://img.shields.io/badge/modloader-Quilt-9115ff?style=flat-square
