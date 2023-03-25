package net.agrotom.sonomod.client.gui.interactive;

import net.agrotom.sonomod.client.gui.Node;
import net.agrotom.sonomod.util.BlitData;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Label extends Node
{
	
	private ITextComponent text = new StringTextComponent("Text");
	private boolean isCentered = false;

	public Label(Minecraft minecraft, int posX, int posY, int width, int height)
	{
		super(minecraft, posX, posY, width, height);
	}
	
	public Label(Minecraft minecraft, int posX, int posY, int width, int height, ITextComponent text)
	{
		this(minecraft, posX, posY, width, height);
		this.text = text;
	}
	
	@Override
	public void blit(BlitData data)
	{
		if(isCentered)
		{
			drawCenteredString(data.getMatrix(), minecraft.font, text, data.getX(), data.getY(), text.getStyle().getColor().getValue());
		}
		else
		{
			drawString(data.getMatrix(), minecraft.font, text, data.getX(), data.getY(), text.getStyle().getColor().getValue());
		}
	}

	public void setText(ITextComponent text)
	{
		this.text = text;
	}
	
	public ITextComponent getText()
	{
		return text;
	}
	
	public void enableCentered()
	{
		isCentered = true;
	}
	
	public void disableCentered()
	{
		isCentered = false;
	}
	
	public boolean isCentered()
	{
		return isCentered;
	}
	
}
