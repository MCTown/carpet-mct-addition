package tech.mctown.cma;

import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;

public class CMAEntry implements ModInitializer {
    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(CMAExtension.INSTANCE);
    }
}
