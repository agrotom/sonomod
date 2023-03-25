package net.agrotom.sonomod.client.gui;

/**
 * 
 * Represents UV attach
 *
 */
public enum AttachPos
{
	LEFT_TOP(3, 3),
	LEFT_CENTER(3, 2),
	LEFT_BOTTOM(3, 1),
	CENTER(2, 2),
	CENTER_TOP(2, 3),
	CENTER_BOTTOM(2, 1),
	RIGHT_TOP(1, 3),
	RIGHT_CENTER(1, 2),
	RIGHT_BOTTOM(1, 1);
	
	private AttachPos(int u, int v)
	{
		this.u = u;
		this.v = v;
	}
	
	public final int u;
	public final int v;
}
