package tech.mctown.cma;

import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Language;

public class CMAEntry implements ModInitializer {
    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(CMAExtension.INSTANCE);
    }
}
