package net.agrotom.sonomod.entity;

import java.util.UUID;

import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public abstract class AngerableAbstract implements IAngerable
{

	public AngerableAbstract()
	{
	}

	@Override
	public int getRemainingPersistentAngerTime()
	{
		return 0;
	}

	@Override
	public void setRemainingPersistentAngerTime(int p_230260_1_)
	{
	}

	@Override
	public UUID getPersistentAngerTarget()
	{
		return null;
	}

	@Override
	public void setPersistentAngerTarget(UUID p_230259_1_)
	{
	}

	@Override
	public void startPersistentAngerTimer()
	{
	}

	@Override
	public void setLastHurtByMob(LivingEntity p_70604_1_)
	{
	}

	@Override
	public void setLastHurtByPlayer(PlayerEntity p_230246_1_)
	{
	}

	@Override
	public void setTarget(LivingEntity p_70624_1_)
	{
	}

	@Override
	public LivingEntity getTarget()
	{
		return null;
	}

}
