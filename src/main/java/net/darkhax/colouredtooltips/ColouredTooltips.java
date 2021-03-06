package net.darkhax.colouredtooltips;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod("colouredtooltips")
public class ColouredTooltips {
    
    public static final Logger LOG = LogManager.getLogger("Coloured Tooltips");
    private final ConfigurationHandler config = new ConfigurationHandler();
    
    public ColouredTooltips() {
        
        if (FMLEnvironment.dist == Dist.CLIENT) {
            
            ModLoadingContext.get().registerConfig(Type.CLIENT, this.config.getSpec());
            MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onTooltipColour);
        }
    }
    
    private void onTooltipColour (RenderTooltipEvent.Color event) {
        
        // Check to see if tooltip colour was modified at all. If it has, we wont touch it.
        if (event.getOriginalBackground() == event.getBackground() && event.getOriginalBorderEnd() == event.getBorderEnd() && event.getOriginalBorderStart() == event.getBorderStart()) {
            
            event.setBorderStart(this.config.getStartColor());
            event.setBorderEnd(this.config.getEndColor());
            event.setBackground(this.config.getBackgroundColor());
        }
    }
}