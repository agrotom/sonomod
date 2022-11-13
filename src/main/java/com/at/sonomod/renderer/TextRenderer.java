package com.at.sonomod.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class TextRenderer {
	
	public static void renderLabel(TileEntity entity, String s, double d, double d1, double d2)
	{
		renderLabel(entity, s, d, d1, d2, 0.3F);
	}
	
	public static void renderLabel(TileEntity entity, String s, double d, double d1, double d2, float scale) {
		if(!(scale > 0))
			scale = 0.3F;
		
	    FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
	    float f1 = 1.6F;
	    float f2 = 0.01666667F * f1;
	    double angle = Math.toRadians(0);
		float sin = (float)(Math.sin(angle));
		float cos = (float)(Math.cos(angle));
		sin = Math.round(sin*100)/100;
		cos = Math.round(cos*100)/100;
	    
	    GL11.glPushMatrix();
	    //GL11.glTranslatef((float)d+(float)(sin*0.35), (float)d1, (float)d2-(float)(cos*0.35));
	    GL11.glTranslatef((float)d, (float)d1, (float)d2);
	    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
	    GL11.glRotatef(0, 0, 1, 0);
	    GL11.glScalef(scale, scale, scale);
	    GL11.glRotatef(-Minecraft.getMinecraft().renderViewEntity.rotationYaw, 0.0F, 1.0F, 0.0F);
	    GL11.glRotatef(Minecraft.getMinecraft().renderViewEntity.rotationPitch, 1.0F, 0.0F, 0.0F);
	    GL11.glScalef(-f2, -f2, f2);
	    GL11.glDisable(2896);
	    GL11.glDepthMask(false);
	    GL11.glDisable(2929);
	    GL11.glEnable(3042);
	    GL11.glBlendFunc(770, 771);
	    Tessellator tessellator = Tessellator.instance;
	    byte byte0 = 0;
	    GL11.glDisable(3553);
	    tessellator.disableColor();
	    tessellator.startDrawingQuads();
	    int j = fontrenderer.getStringWidth(s) / 2;
	    tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.35F);
	    tessellator.addVertex(-j - 1, -1 + byte0, 0.0D);
	    tessellator.addVertex(-j - 1, 8 + byte0, 0.0D);
	    tessellator.addVertex(j + 1, 8 + byte0, 0.0D);
	    tessellator.addVertex(j + 1, -1 + byte0, 0.0D);
	    tessellator.draw();
	    //tessellator.func_78381_a();
	    GL11.glEnable(3553);
	    fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 553648127);
	    GL11.glEnable(2929);
	    GL11.glDepthMask(true);
	    fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
	    GL11.glEnable(2896);
	    GL11.glDisable(3042);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    GL11.glPopMatrix();
	  }
}
