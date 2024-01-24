package net.agrotom.sonomod.util;

import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.capabilities.Capability;

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
	
	/**
	 * Safe way to get Minecraft instance.
	 * @param lambda - consumer, that accepts Minecraft instance if it's ready.
	 * @return Minecraft is ready or not.
	 */
	public static boolean ifMinecraftReady(@Nonnull Consumer<Minecraft> lambda) {
		if(isMinecraftReady()) {
			lambda.accept(Minecraft.getInstance());
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isMinecraftReady() {
		return Minecraft.getInstance() != null;
	}
	
	public static <T> Optional<T> getCapabilityOrException(@Nonnull PlayerEntity player, @Nonnull Capability<T> cap) {
		T value = null;
		try {
			value = player.getCapability(cap).orElseThrow(() -> new Exception("Error was occured while getting cap from player"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(value);
	}

}
