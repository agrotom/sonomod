package net.agrotom.sonomod.util;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
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
	
	public static boolean ifMinecraftReady(@Nonnull Voider lambda) {
		if(isMinecraftReady()) {
			lambda.apply();
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isMinecraftReady() {
		return Minecraft.getInstance() != null;
	}

}
