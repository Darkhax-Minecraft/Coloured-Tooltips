package net.darkhax.colouredtooltips;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod("colouredtooltips")
public class ColouredTooltips {

	public static final Logger LOG = LogManager.getLogger("Coloured Tooltips");
    public static ConfigurationHandler config = new ConfigurationHandler();

    public ColouredTooltips() {
    	
    	DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
    		
        	ModLoadingContext.get().registerConfig(Type.CLIENT, config.getSpec());
    		MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onTooltipColour);
    	});
    }

    private void onTooltipColour (RenderTooltipEvent.Color event) {

        // Check to see if tooltip colour was modified at all. If it has, we wont touch it.
        if (event.getOriginalBackground() == event.getBackground() && event.getOriginalBorderEnd() == event.getBorderEnd() && event.getOriginalBorderStart() == event.getBorderStart()) {

            event.setBorderStart(config.getStartColor());
            event.setBorderEnd(config.getEndColor());
            event.setBackground(config.getBackgroundColor());
        }
    }
}