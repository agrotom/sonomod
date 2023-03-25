package net.agrotom.sonomod.util;

import com.mojang.blaze3d.matrix.MatrixStack;

public class BlitData
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
	
	public static BlitData create(MatrixStack matrix)
	{
		return new BlitData().setMatrix(matrix);
	}

	public MatrixStack getMatrix()
	{
		return matrix;
	}

	public BlitData setMatrix(MatrixStack matrix)
	{
		this.matrix = matrix;
		
		return this;
	}

	public int getX()
	{
		return x;
	}

	public BlitData setX(int x)
	{
		this.x = x;
		return this;
	}

	public int getY()
	{
		return y;
	}

	public BlitData setY(int y)
	{
		this.y = y;
		return this;
	}

	public int getOffX()
	{
		return offX;
	}

	public BlitData setOffX(int offX)
	{
		this.offX = offX;
		
		return this;
	}

	public int getOffY()
	{
		return offY;
	}

	public BlitData setOffY(int offY)
	{
		this.offY = offY;
		
		return this;
	}

	public int getWidth()
	{
		return width;
	}

	public BlitData setWidth(int width)
	{
		this.width = width;
		
		return this;
	}

	public int getHeight()
	{
		return height;
	}

	public BlitData setHeight(int height)
	{
		this.height = height;
		
		return this;
	}

	public int getMouseX()
	{
		return mouseX;
	}

	public BlitData setMouseX(int mouseX)
	{
		this.mouseX = mouseX;
		
		return this;
	}

	public int getMouseY()
	{
		return mouseY;
	}

	public BlitData setMouseY(int mouseY)
	{
		this.mouseY = mouseY;
		
		return this;
	}

}
