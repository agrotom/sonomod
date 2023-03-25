package net.agrotom.sonomod.client.gui.containers;

import net.agrotom.sonomod.client.gui.Node;
import net.agrotom.sonomod.util.BlitData;
import net.agrotom.sonomod.util.BlitHelper;
import net.minecraft.client.Minecraft;

public class BorderPane extends Region
{
	public ImageHolder background_image = null;
	
	private Node top;
	private Node left;
	private Node center;
	private Node right;
	private Node bottom;
	
	
	public BorderPane(Minecraft minecraft, int posX, int posY, int width, int height)
	{
		super(minecraft, posX, posY, width, height);
	}
	
	@Override
	public void blit(BlitData data)
	{
		
		if(background_image != null)
		{
			final int leftPos = BlitHelper.relateX(this, background_image);
			final int topPos = BlitHelper.relateY(this, background_image);
			
			background_image.blit(data.setX(leftPos).setY(topPos));
		}
		
		blitChildren(data);
	}
	
	private void blitChildren(BlitData data)
	{
		int leftPos;
		int topPos;
		
		if(top != null)
		{
			leftPos = this.posX + this.width / 2;
			topPos = this.posY;
			
			top.blit(data.setX(leftPos).setY(topPos));
		}
		
		if(left != null)
		{
			leftPos = this.posX;
			topPos = this.posY + this.height / 2;
			
			left.blit(data.setX(leftPos).setY(topPos));
		}
		
		if(right != null)
		{
			leftPos = this.posX + this.width;
			topPos = this.posY + this.height / 2;
			
			right.blit(data.setX(leftPos).setY(topPos));
		}
		
		if(center != null)
		{
			leftPos = this.posX + this.width / 2;
			topPos = this.posY + this.height / 2;
			
			center.blit(data.setX(leftPos).setY(topPos));
		}
		
		if(bottom != null)
		{
			leftPos = this.posX + this.width / 2;
			topPos = this.posY + this.height;
			
			bottom.blit(data.setX(leftPos).setY(topPos));
		}
	}

	public void setTop(Node top)
	{
		this.top = top;
	}

	public void setLeft(Node left)
	{
		this.left = left;
	}
	
	public void setCenter(Node center)
	{
		this.center = center;
	}

	public void setRight(Node right)
	{
		this.right = right;
	}

	public void setBottom(Node bottom)
	{
		this.bottom = bottom;
	}

}
