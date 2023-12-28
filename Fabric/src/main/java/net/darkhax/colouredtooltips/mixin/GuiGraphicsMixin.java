package net.darkhax.colouredtooltips.mixin;

import net.darkhax.colouredtooltips.ColouredTooltipsFabric;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

    @ModifyVariable(method = "fillGradient(Lcom/mojang/math/Matrix4f;Lcom/mojang/blaze3d/vertex/BufferBuilder;IIIIIII)V", at = @At("HEAD"), ordinal = 5, argsOnly = true)
    private static int colouredtooltips$replaceA(int originalA) {
        return ColouredTooltipsFabric.getReplacementColor(originalA);
    }

    @ModifyVariable( method = "fillGradient(Lcom/mojang/math/Matrix4f;Lcom/mojang/blaze3d/vertex/BufferBuilder;IIIIIII)V", at = @At("HEAD"), ordinal = 6, argsOnly = true)
    private static int colouredtooltips$replaceB(int originalB) {
        return ColouredTooltipsFabric.getReplacementColor(originalB);
    }
}
