package net.darkhax.colouredtooltips;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ConfigurationHandler {

    private final ForgeConfigSpec spec;
    
    // #505000ff (80, 0, 255, 80)
    private final ConfigValue<String> borderStart;
    
    // #5028007f (40, 0, 127, 80)
    private final ConfigValue<String> borderEnd;
    
    // #f0100010 (16, 0, 16, 240)
    private final ConfigValue<String> background;
    
    public ConfigurationHandler () {

        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        // General Configs
        builder.comment("General settings for the mod.");
        builder.push("general");

        builder.comment("The color at the top of the tooltip gradient.");
        borderStart = builder.define("borderStart", "505000ff");
        
        builder.comment("The color at the bottom of the tooltip gradient.");
        borderEnd = builder.define("borderEnd", "5028007f");
        
        builder.comment("The color for the background of the tooltip.");
        background = builder.define("background", "f0100010");
        
        this.spec = builder.build();
    }
    
    public ForgeConfigSpec getSpec() {
    	
    	return this.spec;
    }
    
    public int getStartColor() {
    	
    	return (int) getColor(this.borderStart);
    }
    
    public int getEndColor() {
    	
    	return (int) getColor(this.borderEnd);
    }
    
    public int getBackgroundColor() {
    	
    	return (int) getColor(this.background);
    }
    
    private long getColor(ConfigValue<String> stringValue) {
    	
    	try {
    		
    		return Long.decode("0x" + stringValue.get());
    	}
    	
    	catch (NumberFormatException e) {
    		
    		ColouredTooltips.LOG.error("The color value in the configuration file is not valid. The color white will be used instead. This is something the pack dev needs to fix.", e);
    		return 0xffffffff;
    	}
    }
}