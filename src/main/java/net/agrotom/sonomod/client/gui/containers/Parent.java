package net.agrotom.sonomod.client.gui.containers;

import java.util.LinkedList;
import java.util.List;

import net.agrotom.sonomod.client.gui.Node;
import net.minecraft.client.Minecraft;

public abstract class Parent extends Node
{
	public final List<Node> children = new LinkedList<Node>();
	
	public boolean isAutoAlignment = true;

	public Parent(Minecraft minecraft, int posX, int posY, int width, int height)
	{
		super(minecraft, posX, posY, width, height);
	}

	@Override
	public void init()
	{
		super.init();
	}
	
}
