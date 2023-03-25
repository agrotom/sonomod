package net.agrotom.sonomod.util;

import net.agrotom.sonomod.client.gui.Node;
import net.agrotom.sonomod.client.gui.containers.Parent;

public class BlitHelper
{

	public static int relateX(Parent parent, Node child)
	{
		if (parent.isAutoAlignment)
		{
			return (parent.width - child.width) / child.attachPos.u + parent.posX + child.posX;
		}
		else
		{
			return child.posX + parent.posX;
		}
	}
	
	public static int relateY(Parent parent, Node child)
	{
		if (parent.isAutoAlignment)
		{
			return (parent.height - child.height) / child.attachPos.v + parent.posY + child.posY;
		}
		else
		{
			return child.posY + parent.posY;
		}
	}

}
