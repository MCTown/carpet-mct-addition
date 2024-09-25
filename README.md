# Carpet-MCT-Addition

<!-- TODO -->

**English** | [中文](README_CN.md)

A carpet extension powering [MCTown](https://www.mctown.tech/) server.

The default configuration will not change any vanilla game behavior.

## Table of Contents

<!-- TOC -->

- [Carpet-MCT-Addition](#carpet-mct-addition)
    - [Table of Contents](#table-of-contents)
    - [Rules](#rules)
        - [runCommandOnSign](#runcommandonsign)
        - [editableSign](#editablesign)
        - [flintAndSteelActivatesObserver](#flintandsteelactivatesobserver)

<!-- /TOC -->

## Rules

### runCommandOnSign

Execute the command on the sign if it's right-clicked by player who is not sneaking nor holding anything in the main hand. Texts must start with '/'.

- Type: `boolean`
- Default: `false`
- Suggested options: `false`, `true`
- Categories: `CMA`, `SURVIVAL`

### editableSign

Player can edit the sign when it's right-clicked if the player is sneaking and not holding anything in the main hand.

- Type: `boolean`
- Default: `false`
- Suggested options: `false`, `true`
- Categories: `CMA`, `SURVIVAL`

### flintAndSteelActivatesObserver

Observer will be activied when player uses Flint and Steel on it. Sneak to light fire on observers.

- Type: `boolean`
- Default: `false`
- Suggested options: `false`, `true`
- Categories: `CMA`, `CREATIVE`
