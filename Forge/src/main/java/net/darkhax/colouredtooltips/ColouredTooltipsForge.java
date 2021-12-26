package net.darkhax.colouredtooltips;

import net.darkhax.colouredtooltips.config.ConfigSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fmllegacy.network.FMLNetworkConstants;

@Mod(Constants.MOD_ID)
public class ColouredTooltipsForge {

    private ConfigSchema config;

    public ColouredTooltipsForge() {

        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));

        if (FMLEnvironment.dist == Dist.CLIENT) {

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);
            MinecraftForge.EVENT_BUS.addListener(this::onTooltipColor);
        }
    }

    private void onLoadComplete(FMLLoadCompleteEvent event) {

        this.config = ConfigSchema.load(FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID + ".json").toFile());
    }

    private void onTooltipColor(RenderTooltipEvent.Color event) {

        if (this.config != null) {

            final ItemStack stack = event.getStack();
            ConfigSchema.ColorOptions displayColor = this.config.defaultColors;

            if (stack != null && !stack.isEmpty()) {

                for (ConfigSchema.IngredientColorOptions override : this.config.overrides) {

                    if (override.target.test(stack)) {

                        displayColor = override.color;
                        break;
                    }
                }
            }

            if (displayColor != null) {

                event.setBorderStart(displayColor.borderStart.getDecimal());
                event.setBorderEnd(displayColor.borderEnd.getDecimal());
                event.setBackground(displayColor.background.getDecimal());
            }
        }
    }
}