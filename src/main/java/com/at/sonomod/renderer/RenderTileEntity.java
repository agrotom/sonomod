package com.at.sonomod.renderer;

import org.lwjgl.opengl.GL11;

import com.at.sonomod.SonoMod;
import com.at.sonomod.blocks.tileentity.GrillTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import proxy.ClientProxy;

public class RenderTileEntity extends TileEntitySpecialRenderer {

	public static ResourceLocation texture;
	public static ResourceLocation objModelLocation;
	public static IModelCustom model;
	
	public RenderTileEntity()
	{
		texture = new ResourceLocation(SonoMod.MODID, "textures/models/grill.png");
        objModelLocation = new ResourceLocation(SonoMod.MODID, "models/grill.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
	}
	
	//public static final ResourceLocation texture = new ResourceLocation(SonoMod.MODID, "textures/models/grill.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		
		
		GrillTileEntity te2 = (GrillTileEntity) tile;
		//float rotation = te2. + (timeSinceLastTick / 2F);
		//float scale = te2.scale;
		
		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		GL11.glScalef(1, 1, 1);
		GL11.glPushMatrix();
		GL11.glRotatef(0, 0F, 1F, 0.5F);
		model.renderAll();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	/*private void render(GrillTileEntity tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslatef(0.5F, 0.0F, 0.5F);
		bindTexture(texture);
		GL11.glCallList(ClientProxy.displayList[0]);
		GL11.glPopMatrix();
	}*/
	
}
