# Gluon

![Java 21](https://img.shields.io/badge/language-Java%2021-9B599A.svg?style=plastic)
[![GitHub License](https://img.shields.io/github/license/MuonMC/gluon?style=plastic)]()
[![Mod loader: Muon]][muon]
![Version](https://img.shields.io/github/v/tag/MuonMC/gluon?style=plastic&label=version)

Essential standard libraries for [the Muon ecosystem](https://muonmc.org/).

The Gluon gives modders Muon-exclusive tools to add new and exciting features to their mods.

**Note: At the moment, Gluon is in beta, meaning issues may arise and should still be treated as experimental.
Please make an issue or talk to the Gluon team on [discord](https://discord.muonmc.org/) before writing any PRs.**

## Repository structure

The repository has 2 main parts:

- The `library` folder. This contains all the libraries that are part of Gluon.
- The `build-logic` folder. This is an included build in Gradle and contains most of the buildscript used inside the
  libraries. This keeps the buildscripts inside the `library` folder as minimal as possible; definitions of data rather
  than logic.

<!--
## Features

Here are multiple charts of features available in Gluon which also serves as a comparison chart with Fabric API.

The charts are organized by Gluon libraries.

Quick legend:

- ✔ = Included
- ❌ = Not Included/Not Yet
- 🙅 = No plans
- 🚧 = Work In Progress
-->

[muon]: https://muonmc.org
[Mod loader: Muon]: https://img.shields.io/badge/modloader-Muon-62cd4b.svg?style=plastic
