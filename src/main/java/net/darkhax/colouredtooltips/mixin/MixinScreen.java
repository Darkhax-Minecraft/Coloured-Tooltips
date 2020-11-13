package net.darkhax.colouredtooltips.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.darkhax.colouredtooltips.ColouredTooltips;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.IReorderingProcessor;

@Mixin(Screen.class)
public class MixinScreen {
    
    @ModifyConstant(constant = @Constant(intValue = -267386864), method = { "renderToolTip(Lcom/mojang/blaze3d/matrix/MatrixStack;Ljava/util/List;IILnet/minecraft/client/gui/FontRenderer;)V" }, remap = false)
    public int getBackgroundColor (int previousValue, MatrixStack transforms, List<? extends IReorderingProcessor> lines, int x, int y, FontRenderer font) {
        
        return previousValue != -267386864 ? previousValue : ColouredTooltips.CONFIG.getBackgroundColor();
    }
    
    @ModifyConstant(constant = @Constant(intValue = 1347420415), method = { "renderToolTip(Lcom/mojang/blaze3d/matrix/MatrixStack;Ljava/util/List;IILnet/minecraft/client/gui/FontRenderer;)V" }, remap = false)
    public int getGradientStartColor (int previousValue, MatrixStack transforms, List<? extends IReorderingProcessor> lines, int x, int y, FontRenderer font) {
        
        return previousValue != 1347420415 ? previousValue : ColouredTooltips.CONFIG.getStartColor();
    }
    
    @ModifyConstant(constant = @Constant(intValue = 1344798847), method = { "renderToolTip(Lcom/mojang/blaze3d/matrix/MatrixStack;Ljava/util/List;IILnet/minecraft/client/gui/FontRenderer;)V" }, remap = false)
    public int getGradientEndColor (int previousValue, MatrixStack transforms, List<? extends IReorderingProcessor> lines, int x, int y, FontRenderer font) {
        
        return previousValue != 1344798847 ? previousValue : ColouredTooltips.CONFIG.getEndColor();
    }
}