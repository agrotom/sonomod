package net.agrotom.sonomod.util;

public class Vector2i
{
	public int x;
	public int y;

	public Vector2i(int x, int y) {
	      this.x = x;
	      this.y = y;
	   }

	public boolean equals(Vector2i vec)
	{
		return this.x == vec.x && this.y == vec.y;
	}

}
