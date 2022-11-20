package net.agrotom.sonomod.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.blocks.GrillBlock;
import net.agrotom.sonomod.items.ModItems;
import net.agrotom.sonomod.tileentity.GrillTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

public class GrillTileEntityRenderer extends TileEntityRenderer<GrillTileEntity> {
	
	public GrillTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(GrillTileEntity tile, float ticks, MatrixStack matrix,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		ItemRenderer ir = Minecraft.getInstance().getItemRenderer();
		
		if(tile.hasItem())
		{
			final ItemStack toRender = tile.getItemCopy();
			
			final Direction dir = tile.getBlockState().getValue(GrillBlock.FACING);
			final float angle;
			
			switch(dir)
			{
			case EAST:
				angle = 90F;
				break;
			case SOUTH:
				angle = 90F * 2;
				break;
			case WEST:
				angle = 90F * 3;
				break;
			default:
				angle = 0;
				break;
			}
			
			final float zi = (float) (0.5f - 0.25f * Math.cos(Math.toRadians(angle)));
			final float xi = (float) (0.5f + 0.25f * Math.sin(Math.toRadians(angle)));
			
			matrix.pushPose();
			matrix.translate(xi, 0.4, zi);
			matrix.scale(0.5f, 0.5f, 0.5f);
			ir.render(toRender, TransformType.FIXED, false, matrix, bufferIn, combinedLightIn, combinedOverlayIn,
					ir.getModel(toRender, tile.getLevel(), null));
			matrix.popPose();
		}
	}

}
