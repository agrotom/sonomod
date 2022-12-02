package net.agrotom.sonomod.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.agrotom.sonomod.blocks.GrillBlock;
import net.agrotom.sonomod.tileentity.GrillTileEntity;
import net.agrotom.sonomod.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
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
				
				RenderUtils.renderNameTag(tile, "" + itemToRender.getCount(), matrix, bufferIn, combinedLightIn, xi, 0.8f, zi);
				
				matrix.pushPose();
				matrix.translate(xi, 0.2, zi);
				matrix.scale(0.5f, 0.5f, 0.5f);
				
				ir.render(itemToRender, TransformType.FIXED, false, matrix, bufferIn, combinedLightIn, combinedOverlayIn,
						ir.getModel(itemToRender, tile.getLevel(), null));
				matrix.popPose();
			}
	}
}
