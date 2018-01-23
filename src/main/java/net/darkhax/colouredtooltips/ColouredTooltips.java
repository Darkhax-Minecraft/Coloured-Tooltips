package net.darkhax.colouredtooltips;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "colouredtooltips", name = "Coloured Tooltips", version = "@VERSION@", certificateFingerprint = "@FINGERPRINT@")
public class ColouredTooltips {

    public static Logger LOG = LogManager.getLogger("Coloured Tooltips");
    public static ConfigurationHandler config;

    @EventHandler
    public void onPreInit (FMLPreInitializationEvent event) {

        config = new ConfigurationHandler(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void onFingerprintViolation (FMLFingerprintViolationEvent event) {

        LOG.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTooltipColour (RenderTooltipEvent.Color event) {

        // Check to see if tooltip colour was modified at all. If it has, we wont touch it.
        if (event.getOriginalBackground() == event.getBackground() && event.getOriginalBorderEnd() == event.getBorderEnd() && event.getOriginalBorderStart() == event.getBorderStart()) {
            
            event.setBorderStart((int) ConfigurationHandler.start);
            event.setBorderEnd((int) ConfigurationHandler.end);
            event.setBackground((int) ConfigurationHandler.background);
        }
    }
}