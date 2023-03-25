package net.agrotom.sonomod.client.gui;

import net.agrotom.sonomod.util.BlitData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class Node extends AbstractGui
{

	protected final Minecraft minecraft;
	
	public AttachPos attachPos = AttachPos.LEFT_TOP;
	
	public int posX = 0;
	public int posY = 0;
	
	public int width = 0;
	public int height = 0;
	
	public Node(Minecraft minecraft, int posX, int posY, int width, int height)
	{
		assert minecraft != null;
		
		this.minecraft = minecraft;
		
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	
	public void init()
	{
		
	}
	
	public void blit(BlitData data)
	{
		super.blit(data.getMatrix(), data.getX(), data.getY(), data.getOffX(), data.getOffY(), data.getWidth(), data.getHeight());
	}

}
