package net.agrotom.sonomod.util;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class RenderUtils
{
	@SuppressWarnings("resource")
	public static <T extends TileEntity> void renderNameTag(T tile, String text, MatrixStack matrix, IRenderTypeBuffer buffer, int p_225629_5_,
			float x, float y, float z)
	{
		if(Minecraft.getInstance().player.isShiftKeyDown())
		{
			final EntityRendererManager entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
			
			matrix.pushPose();
		    matrix.translate(x, y, z);
		    matrix.mulPose(entityRenderDispatcher.cameraOrientation());
		    matrix.scale(-0.025F, -0.025F, 0.025F);
		    
		    Matrix4f matrix4f = matrix.last().pose();
		    
		    float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
		    int j = (int)(f1 * 255.0F) << 24;
		    final FontRenderer font = Minecraft.getInstance().font;
		    float f2 = (float)(-font.width(text) / 2);
		    
	    	font.drawInBatch(text, f2, 0, -1, false, matrix4f, buffer, false, j, p_225629_5_);
		
		    matrix.popPose();
		}
	}
	
	public static int getRelativeX(int screenWidth, int rootWidth) {
		return (screenWidth - rootWidth) / 2;
	}
	
	public static int getRelativeY(int screenHeight, int rootHeight) {
		return (screenHeight - rootHeight) / 2;
	}
	
	public static void drawWrapShadow(@Nonnull FontRenderer font, @Nonnull ITextComponent text, int x, int y, int width, int height)
	{
		font.drawWordWrap(new StringTextComponent(text.getContents()).withStyle(TextFormatting.DARK_GRAY), x + 1, y + 1,
				width - 1, height - 1);
		font.drawWordWrap(text, x, y, width, height);
	}
}
