package net.agrotom.sonomod.util;

import com.mojang.blaze3d.matrix.MatrixStack;

public class RenderEvent
{

	private MatrixStack matrix;
	
	private int x = 0;
	private int y = 0;
	
	private int offX = 0;
	private int offY = 0;
	
	private int width = 0;
	private int height = 0;
	
	private int mouseX = 0;
	private int mouseY = 0;
	
	public RenderEvent(MatrixStack matrix)
	{
	    this.matrix = matrix;
	}

	public MatrixStack getMatrix()
	{
		return matrix;
	}

	public RenderEvent setMatrix(MatrixStack matrix)
	{
		this.matrix = matrix;
		
		return this;
	}

	public int getRelativeX()
	{
		return x;
	}

	public RenderEvent setRelativeX(int x)
	{
		this.x = x;
		return this;
	}

	public int getRelativeY()
	{
		return y;
	}

	public RenderEvent setRelativeY(int y)
	{
		this.y = y;
		return this;
	}

	public int getOffX()
	{
		return offX;
	}

	public RenderEvent setOffX(int offX)
	{
		this.offX = ATUtils.clampToPositive(offX);
		
		return this;
	}

	public int getOffY()
	{
		return offY;
	}

	public RenderEvent setOffY(int offY)
	{
		this.offY = ATUtils.clampToPositive(offY);
		
		return this;
	}

	public int getWidth()
	{
		return width;
	}

	public RenderEvent setWidth(int width)
	{
		this.width = ATUtils.clampToPositive(width);;
		
		return this;
	}

	public int getHeight()
	{
		return height;
	}

	public RenderEvent setHeight(int height)
	{
		this.height = ATUtils.clampToPositive(height);
		
		return this;
	}

	public int getMouseX()
	{
		return mouseX;
	}

	public RenderEvent setMouseX(int mouseX)
	{
		this.mouseX = mouseX;
		
		return this;
	}

	public int getMouseY()
	{
		return mouseY;
	}

	public RenderEvent setMouseY(int mouseY)
	{
		this.mouseY = mouseY;
		
		return this;
	}

}
