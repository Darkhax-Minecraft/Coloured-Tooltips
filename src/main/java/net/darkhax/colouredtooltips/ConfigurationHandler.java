package net.darkhax.colouredtooltips;

import java.awt.Color;
import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    public static Configuration config;

    // #505000ff (80, 0, 255, 80)
    public static long start;

    // #5028007f (40, 0, 127, 80)
    public static long end;

    // #f0100010 (16, 0, 16, 240)
    public static long background;

    public ConfigurationHandler (File file) {

        config = new Configuration(file);
        config.setCategoryComment(Configuration.CATEGORY_GENERAL, "Color values are ARGB hex. Do not add a # to the codes. If you want opaque RGB use FF for first two chars.");
        this.syncConfigData();
    }

    private void syncConfigData () {

        start = this.getColor("borderStart", "505000ff", "top");
        end = this.getColor("borderEnd", "5028007f", "bottom");
        background = this.getColor("background", "f0100010", "background");

        if (config.hasChanged()) {
            config.save();
        }
    }

    public long getColor (String name, String defaultValue, String explain) {

        final String colorValue = "0x" + config.getString(name, Configuration.CATEGORY_GENERAL, defaultValue, "The color for the " + explain + " of the tooltip. This should be 8 characters.");

        try {

            return Long.decode(colorValue);
        }

        catch (final Exception e) {

            ColouredTooltips.LOG.trace("Could not read color for " + name + ". Invalid color: " + colorValue + " Default: " + defaultValue, e);
        }

        return Color.WHITE.getRGB();
    }
}