package net.agrotom.sonomod.util;

import com.mojang.blaze3d.matrix.MatrixStack;

public class RenderEvent
{
    
	private MatrixStack matrix;
	
	private int x = 0;
	private int y = 0;
	
	public RenderEvent(MatrixStack matrix, int relativeX, int relativeY)
	{
	    this.matrix = matrix;
	    
	    this.x = relativeX;
	    this.y = relativeY;
	}

	public MatrixStack getMatrix()
	{
		return matrix;
	}

	public int getRelativeX()
	{
		return x;
	}

	public int getRelativeY()
	{
		return y;
	}
	
	public RenderEvent copy() {
	    RenderEvent copied = new RenderEvent(matrix, x, y);
	    
	    return copied;
	}
	
}
