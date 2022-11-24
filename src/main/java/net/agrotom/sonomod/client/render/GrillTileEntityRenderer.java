package net.agrotom.sonomod.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.agrotom.sonomod.blocks.GrillBlock;
import net.agrotom.sonomod.tileentity.GrillTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrillTileEntityRenderer extends TileEntityRenderer<GrillTileEntity> {
	
	public GrillTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(GrillTileEntity tile, float ticks, MatrixStack matrix,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
	{
		
		final ItemStack[] items = tile.getItems();
		
		for(int i = 0; i < items.length; i++)
			if(!items[i].isEmpty())
			{
				final ItemRenderer ir = Minecraft.getInstance().getItemRenderer();
				
				final ItemStack itemToRender = items[i];
				
				final Direction dir = tile.getBlockState().getValue(GrillBlock.FACING);
				final float angle = (dir.get2DDataValue() - 2) * 90F;
				
				final double negate = Math.pow(-1, i);
				
				final float zi = (float) (0.5f - 0.25f * negate * MathHelper.cos((float)Math.toRadians(angle)));
				final float xi = (float) (0.5f + 0.25f * negate * MathHelper.sin((float)Math.toRadians(angle)));
				
				renderNameTag(tile, "" + itemToRender.getCount(), matrix, bufferIn, combinedLightIn, xi, 0.8f, zi);
				
				matrix.pushPose();
				matrix.translate(xi, 0.2, zi);
				matrix.scale(0.5f, 0.5f, 0.5f);
				
				ir.render(itemToRender, TransformType.FIXED, false, matrix, bufferIn, combinedLightIn, combinedOverlayIn,
						ir.getModel(itemToRender, tile.getLevel(), null));
				matrix.popPose();
			}
	}

	@SuppressWarnings("resource")
	private void renderNameTag(GrillTileEntity tile, String text, MatrixStack matrix, IRenderTypeBuffer buffer, int p_225629_5_,
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
}
