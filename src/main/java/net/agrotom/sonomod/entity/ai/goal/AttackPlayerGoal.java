package net.agrotom.sonomod.entity.ai.goal;

import java.util.Comparator;
import java.util.List;

import net.agrotom.sonomod.entity.flyingentity.AbstractFlyingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

public class AttackPlayerGoal extends Goal
{
	private final AbstractFlyingEntity mob;
	private final EntityPredicate attackTargeting = (new EntityPredicate()).range(64.0D);
	private int nextScanTick = 20;

	public AttackPlayerGoal(AbstractFlyingEntity mob)
	{

		this.mob = mob;
	}

	public boolean canUse()
	{
		if (this.nextScanTick > 0)
		{
			--this.nextScanTick;
			return false;
		} else
		{
			this.nextScanTick = 60;
			List<PlayerEntity> list = this.mob.level.getNearbyPlayers(this.attackTargeting, this.mob,
					this.mob.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
			if (!list.isEmpty())
			{
				list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

				for (PlayerEntity playerentity : list)
				{
					if (this.mob.canAttack(playerentity, EntityPredicate.DEFAULT))
					{
						this.mob.setTarget(playerentity);
						return true;
					}
				}
			}

			return false;
		}

	}

	public boolean canContinueToUse()
	{
		LivingEntity livingentity = this.mob.getTarget();
		return livingentity != null ? this.mob.canAttack(livingentity, EntityPredicate.DEFAULT) : false;
	}
}
