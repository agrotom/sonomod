package net.agrotom.sonomod.client.gui.containers;

import net.agrotom.sonomod.util.Voider;
import net.minecraft.client.Minecraft;

public abstract class Region extends Parent
{
	private Voider onHoverListener = null;

	public Region(Minecraft minecraft, int posX, int posY, int width, int height)
	{
		super(minecraft, posX, posY, width, height);
	}

	public void setOnHoverEvent(Voider listener)
	{
		onHoverListener = listener;
	}
	
	public void removeOnHoverEvent()
	{
		onHoverListener = null;
	}
	
}
