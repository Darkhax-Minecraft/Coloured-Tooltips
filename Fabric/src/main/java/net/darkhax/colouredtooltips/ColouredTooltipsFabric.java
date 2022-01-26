package net.darkhax.colouredtooltips;

import net.darkhax.colouredtooltips.config.ConfigSchema;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class ColouredTooltipsFabric implements ClientModInitializer {

    public static ConfigSchema config;
    public static ConfigSchema.ColorOptions currentColor;

    @Override
    public void onInitializeClient() {

        config = ConfigSchema.load(FabricLoader.getInstance().getConfigDir().resolve(Constants.MOD_ID + ".json").toFile());
    }

    public static int getReplacementColor(int original) {

        if (currentColor != null) {

            if (original == Constants.DefaultColors.START.decimal) {

                return currentColor.borderStart.getDecimal();
            }

            if (original == Constants.DefaultColors.END.decimal) {

                return currentColor.borderEnd.getDecimal();
            }

            else if (original == Constants.DefaultColors.BACKGROUND.decimal) {

                return currentColor.background.getDecimal();
            }
        }
        
        return original;
    }
}