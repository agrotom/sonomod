package net.agrotom.sonomod.client.gui.containers;

import net.agrotom.sonomod.client.gui.Node;
import net.agrotom.sonomod.util.BlitData;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ImageHolder extends Node
{
	private ResourceLocation bgLocation = null;
	
	public ImageHolder(Minecraft minecraft, int posX, int posY, int width, int height, ResourceLocation bgLocation)
	{
		super(minecraft, posX, posY, width, height);
		this.bgLocation = bgLocation;
	}
	
	public void setImage(ResourceLocation newImage)
	{
		bgLocation = newImage;
	}

	@Override
	public void blit(BlitData data)
	{
		if(hasImage())
		{
			this.minecraft.getTextureManager().bind(bgLocation);
		}
		
		super.blit(data.setWidth(width).setHeight(height));
	}
	
	public boolean hasImage()
	{
		return bgLocation != null;
	}

}
