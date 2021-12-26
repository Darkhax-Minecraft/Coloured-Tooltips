package net.darkhax.colouredtooltips;

import net.darkhax.colouredtooltips.config.ConfigSchema;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class ColouredTooltipsFabric implements ClientModInitializer {

    public static ConfigSchema config;

    @Override
    public void onInitializeClient() {

        config = ConfigSchema.load(FabricLoader.getInstance().getConfigDir().resolve(Constants.MOD_ID + ".json").toFile());
    }
}