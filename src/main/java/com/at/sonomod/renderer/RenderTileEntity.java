package com.at.sonomod.renderer;

import org.lwjgl.opengl.GL11;

import com.at.sonomod.SonoMod;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderTileEntity extends TileEntitySpecialRenderer {

	public ResourceLocation texture;
	public ResourceLocation objModelLocation;
	public IModelCustom model;
	
	public RenderTileEntity(String textureName, String objModelLocation)
	{
		this.texture = new ResourceLocation(SonoMod.MODID + ":" + "textures/models/" + textureName + ".png");
		this.objModelLocation = new ResourceLocation(SonoMod.MODID + ":" + "models/" + objModelLocation + ".obj");;
        model = AdvancedModelLoader.loadModel(this.objModelLocation);
	}
	
	public RenderTileEntity(ResourceLocation textureLocation, String objModelLocation)
	{
		this.texture = textureLocation;
		this.objModelLocation = new ResourceLocation(SonoMod.MODID + ":" + "models/" + objModelLocation + ".obj");;
        model = AdvancedModelLoader.loadModel(this.objModelLocation);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		
		if(tile instanceof SpecialRender)
		{
			final SpecialRender sr = (SpecialRender) tile;
			sr.specialRenderFunc(x, y, z);
		}
		
		bindTexture(texture);

		GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
			GL11.glScalef(1, 1, 1);
			GL11.glPushMatrix();
				GL11.glRotatef(0, 0F, 1F, 0.5F);
				
				int i;
		
				if(!tile.hasWorldObj())
				{
					i = 0;
				}
				else
				{
					i = tile.getBlockMetadata();
				}
				double rotate = (double)((double)i * 90D);
		
				GL11.glRotated(rotate, 0, 1D, 0);
				
				model.renderAll();
			GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
}
