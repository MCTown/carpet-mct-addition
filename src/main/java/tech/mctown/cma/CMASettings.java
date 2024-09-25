package tech.mctown.cma;

import carpet.api.settings.Rule;
import carpet.api.settings.CarpetRule;
import static carpet.api.settings.RuleCategory.*;

public class CMASettings {
    static final String CMA = CMAExtension.MODID;
    @Rule(
//            extra = {"Texts must start with '/'."},
            categories = {CMA, SURVIVAL}
    )
    public static boolean runCommandOnSign = false;

    @Rule(
//            desc = "Observer will be activied when player uses Flint and Steel on it.",
//            extra = {"Sneak to light fire on observers."},
            categories = {CMA, CREATIVE}
    )
    public static boolean flintAndSteelActivatesObserver = false;

    @Rule(
//            desc = "Enable the usage of /dumpentity",
            categories = {CMA, CREATIVE, COMMAND}
    )
    public static String commandDumpEntity = "true";

    @Rule(
//            desc = "Op players can spawn bots in different gamemodes.",
            categories = {CMA, SURVIVAL}
    )
    public static boolean fakePlayerGamemode = true;
}
