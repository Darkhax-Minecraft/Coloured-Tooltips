package net.darkhax.colouredtooltips.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.colouredtooltips.Constants;
import net.minecraft.world.item.crafting.Ingredient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigSchema {

    public static ConfigSchema load(File configFile) {

        ConfigSchema config = new ConfigSchema();

        // Attempt to load existing config file
        if (configFile.exists()) {

            try (FileReader reader = new FileReader(configFile)) {

                config = Constants.GSON.fromJson(reader, ConfigSchema.class);

                Constants.LOG.info("Successfully loaded config file. {} overrides loaded.", config.overrides.size());
            }

            catch (IOException e) {

                Constants.LOG.error("Could not read config file {}. Defaults will be used.", configFile.getAbsolutePath());
                Constants.LOG.catching(e);
            }
        }

        else {

            Constants.LOG.info("Creating a new config file at {}.", configFile.getAbsolutePath());
            configFile.getParentFile().mkdirs();

            try (FileWriter writer = new FileWriter(configFile)) {

                Constants.GSON.toJson(config, writer);
                Constants.LOG.info("Saving config file.");
            }

            catch (IOException e) {

                Constants.LOG.error("Could not write config file '{}'!", configFile.getAbsolutePath());
                Constants.LOG.catching(e);
            }
        }

        return config;
    }

    @Expose
    @SerializedName("default")
    public ColorOptions defaultColors = new ColorOptions();

    @Expose
    public List<IngredientColorOptions> overrides = new ArrayList<>();

    public static class ColorOptions {

        @Expose
        public HexColor borderStart = Constants.DefaultColors.START.hexColor;

        @Expose
        public HexColor borderEnd = Constants.DefaultColors.END.hexColor;

        @Expose
        public HexColor background = Constants.DefaultColors.BACKGROUND.hexColor;

        @Override
        public String toString() {
            return "ColorOptions{" +
                    "borderStart=" + borderStart +
                    ", borderEnd=" + borderEnd +
                    ", background=" + background +
                    '}';
        }
    }

    public static class IngredientColorOptions {

        @Expose
        public Ingredient target;

        @Expose
        public ColorOptions color = new ColorOptions();
    }

}
