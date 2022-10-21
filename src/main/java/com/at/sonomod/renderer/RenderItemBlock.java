package com.at.sonomod.renderer;

import org.lwjgl.opengl.GL11;

import com.at.sonomod.SonoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemBlock implements IItemRenderer
{
	
	private final int index;
	private final ResourceLocation texture;
	
	public RenderItemBlock(int index, String texture)
	{
		this.index = index;
		this.texture = new ResourceLocation(SonoMod.MODID + ":textures/models/" + texture + ".png");
	}
	
	@Override
	public boolean handleRenderType(ItemStack is, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack is, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.35F, 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glCallList(SonoMod.proxy.displayList[index]);
		GL11.glPopMatrix();
	}

}
