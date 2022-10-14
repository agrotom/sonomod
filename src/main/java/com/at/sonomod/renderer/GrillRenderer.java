package com.at.sonomod.renderer;

import org.lwjgl.opengl.GL11;

import com.at.sonomod.SonoMod;
import com.at.sonomod.models.GrillModel;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GrillRenderer extends TileEntitySpecialRenderer
{
	
	ResourceLocation texture = new ResourceLocation(SonoMod.MODID + ":" + "textures/models/Grill.png");

	private GrillModel model;
	
	public GrillRenderer()
	{
		this.model = new GrillModel();
	}

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z,
			float f)
	{
		GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z + 0.5f);
			GL11.glRotatef(180, 0f, 0f, 1f);
			
			this.bindTexture(texture);
			
			GL11.glPushMatrix();
				this.model.renderModel(0.0625f);
			GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
