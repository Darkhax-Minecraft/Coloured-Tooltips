package net.darkhax.colouredtooltips;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod("colouredtooltips")
public class ColouredTooltips {
    
    public static final Logger LOG = LogManager.getLogger("Coloured Tooltips");
    public static final ConfigurationHandler CONFIG = new ConfigurationHandler();
    
    public ColouredTooltips() {
        
        if (FMLEnvironment.dist == Dist.CLIENT) {
            
            ModLoadingContext.get().registerConfig(Type.CLIENT, CONFIG.getSpec());
        }
    }
}