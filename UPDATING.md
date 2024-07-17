# Updating Gluon

**Note: Not done yet, contents of this file are not final!**

Whether it's bumping the Gluon version of porting Gluon to a new Minecraft version, the following steps should be taken:

## Updating the Changelog
Don't forget to update the [`CHANGELOG.md`](./CHANGELOG.md) with all additions, removals, changes, and fixes.

## New Minecraft/Loader version

Change the constants in the [Versions] class. All modules and libraries will use the specified Minecraft and loader
versions in this file.

[Versions]: ./build-logic/src/main/java/gluon/internal/Versions.java
