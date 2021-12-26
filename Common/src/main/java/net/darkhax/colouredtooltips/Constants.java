package net.darkhax.colouredtooltips;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.darkhax.colouredtooltips.config.HexColor;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.Arrays;

public class Constants {

    public static final String MOD_ID = "colouredtooltips";
    public static final String MOD_NAME = "Coloured Tooltips";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().registerTypeAdapter(HexColor.class, new HexColorSerializer()).registerTypeAdapter(Ingredient.class, new IngredientSerializer()).create();

    private static final class IngredientSerializer implements JsonDeserializer<Ingredient> {

        @Override
        public Ingredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            return Ingredient.fromJson(json);
        }
    }

    private static final class HexColorSerializer implements JsonDeserializer<HexColor>, JsonSerializer<HexColor> {

        @Override
        public HexColor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            try {

                if (json instanceof JsonPrimitive primitive) {

                    // Read color as a hex string.
                    if (primitive.isString()) {

                        String hex = json.getAsString();

                        // Remove # if the user specified it.
                        if (hex.startsWith("#")) {

                            hex = hex.substring(1);
                        }

                        return new HexColor(hex, Integer.parseUnsignedInt(hex, 16));
                    }

                    // Read color as an unsigned decimal.
                    else if (primitive.isNumber()) {

                        final int decimal = primitive.getAsInt();
                        final String hex = Integer.toHexString(decimal);
                        return new HexColor(hex, decimal);
                    }
                }

                else if (json instanceof JsonObject obj) {

                    int color = 0;
                    color |= (getColorChannel(obj, 255, "alpha", "a", "transparency", "t", "opacity", "o") & 255) << 24;
                    color |= (getColorChannel(obj, "red", "r") & 255) << 16;
                    color |= (getColorChannel(obj, "green", "g") & 255) << 8;
                    color |= (getColorChannel(obj, "blue", "b") & 255);

                    final String hex = Integer.toHexString(color);
                    return new HexColor(hex, color);
                }

                else if (json instanceof JsonArray array) {

                    if (array.size() == 3) {

                        final int[] rgb = getColorChannels(array);

                        int color = 0;
                        color |= 255 << 24;
                        color |= (rgb[0] & 255) << 16;
                        color |= (rgb[1] & 255) << 8;
                        color |= (rgb[2] & 255);

                        final String hex = Integer.toHexString(color);
                        return new HexColor(hex, color);
                    }

                    else if (array.size() == 4) {

                        final int[] argb = getColorChannels(array);

                        int color = 0;
                        color |= (argb[0] & 255) << 24;
                        color |= (argb[1] & 255) << 16;
                        color |= (argb[2] & 255) << 8;
                        color |= (argb[3] & 255);

                        final String hex = Integer.toHexString(color);
                        return new HexColor(hex, color);
                    }
                }
            }

            catch (Exception e) {

                Constants.LOG.error("Failed to load color data for JSON value {}.", json.toString());
                Constants.LOG.catching(e);
                throw new JsonParseException("Invalid user input for color!", e);
            }

            throw new JsonParseException("The provided data could not be parsed as a color. " + json.toString());
        }

        @Override
        public JsonElement serialize(HexColor src, Type typeOfSrc, JsonSerializationContext context) {

            return new JsonPrimitive(src.getHex());
        }

        private static int getColorChannel(JsonObject obj, String... names) {

            return getColorChannel(obj, null, names);
        }

        private static int[] getColorChannels(JsonArray array) {

            int[] channels = new int[array.size()];

            for (int i = 0; i < array.size(); i++) {

                final int channel = array.get(i).getAsInt();

                if (channel < 0 || channel > 255) {

                    throw new JsonParseException("Color channel for array " + array.toString() + " is out of range. Expected 0-255 got " + channel + ".");
                }

                channels[i] = channel;
            }

            return channels;
        }

        private static int getColorChannel(JsonObject obj, Integer fallback, String... names) {

            for (String name : names) {

                if (obj.has(name)) {

                    final int channel = obj.get(name).getAsInt();

                    if (channel < 0 || channel > 255) {

                        throw new JsonParseException("Color channel must be 0-255. Channel '" + name + "' was '" + channel + "'.");
                    }

                    return obj.get(name).getAsInt();
                }
            }

            if (fallback != null) {

                return fallback;
            }

            throw new JsonParseException("Missing color channel. Expected one of the following to be specified. " + Arrays.toString(names));
        }
    }

    public enum DefaultColors {

        START("505000ff", 1347420415, 80, 0, 255, 80),
        END("5028007f", 1344798847, 40, 0, 127, 80),
        BACKGROUND("f0100010", -267386864, 16, 0, 16, 240);

        public final String hex;
        public final int decimal;
        public final int r;
        public final int g;
        public final int b;
        public final int a;
        public final HexColor hexColor;

        DefaultColors(String hex, int decimal, int r, int g, int b, int a) {

            this.hex = hex;
            this.decimal = decimal;
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
            this.hexColor = new HexColor(hex, decimal);
        }
    }
}