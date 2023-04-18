package net.agrotom.sonomod.util;

public class UniVector2<T extends Number>
{

	public final int x;
	public final int y;

	public UniVector2(int x, int y) {
	      this.x = x;
	      this.y = y;
	   }

	
	@Override
	public boolean equals(Object vec)
	{
		if(!(vec instanceof UniVector2<?>)) {
			return false;
		}
		
		UniVector2<?> vector = (UniVector2<?>) vec;
		
		return this.x == vector.x && this.y == vector.x;
	}
	
	public boolean equals(UniVector2<T> vec)
	{
		return this.x == vec.x && this.y == vec.y;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}

}
