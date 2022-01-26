package net.darkhax.colouredtooltips.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkhax.colouredtooltips.ColouredTooltipsFabric;
import net.darkhax.colouredtooltips.config.ConfigSchema;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class MixinScreen {

    @Inject(method = "renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/item/ItemStack;II)V", at = @At("HEAD"))
    protected void renderTooltipPre(PoseStack poseStack, ItemStack stack, int i, int j, CallbackInfo ci) {

        ColouredTooltipsFabric.currentColor = ColouredTooltipsFabric.config.defaultColors;

        if (stack != null && !stack.isEmpty()) {

            for (ConfigSchema.IngredientColorOptions override : ColouredTooltipsFabric.config.overrides) {

                if (override.target.test(stack)) {

                    ColouredTooltipsFabric.currentColor = override.color;
                    break;
                }
            }
        }
    }

    @Inject(method = "renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/item/ItemStack;II)V", at = @At("RETURN"))
    protected void renderTooltipPost(PoseStack poseStack, ItemStack itemStack, int i, int j, CallbackInfo ci) {

        ColouredTooltipsFabric.currentColor = null;
    }
}