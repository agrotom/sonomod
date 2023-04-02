package net.agrotom.sonomod.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorld;

public class ATUtils
{

	public static <T> void logToAllPlayers(final IWorld world, final T msg)
	{
		if(world == null)
			return;
		
		world.players().forEach(player -> player.sendMessage(new StringTextComponent(msg.toString()), player.getUUID()));
	}
	
	public static int clampToPositive(int value) {
	    return MathHelper.clamp(value, 0, Integer.MAX_VALUE);
	}

}
