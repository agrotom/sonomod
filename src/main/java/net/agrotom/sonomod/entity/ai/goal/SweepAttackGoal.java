package net.agrotom.sonomod.entity.ai.goal;

import java.util.List;

import net.agrotom.sonomod.entity.flyingentity.AbstractFlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.vector.Vector3d;

public class SweepAttackGoal extends MoveGoal
{

	public SweepAttackGoal(AbstractFlyingEntity mob)
	{
		super(mob);
	}

	public boolean canUse()
	{
		return this.mob.getTarget() != null
				&& this.mob.attackPhase == AttackPhase.SWOOP;
	}

	public boolean canContinueToUse()
	{
		LivingEntity livingentity = this.mob.getTarget();
		if (livingentity == null)
		{
			return false;
		} else if (!livingentity.isAlive())
		{
			return false;
		} else if (!(livingentity instanceof PlayerEntity)
				|| !((PlayerEntity) livingentity).isSpectator() && !((PlayerEntity) livingentity).isCreative())
		{
			if (!this.canUse())
			{
				return false;
			} else
			{
				if (this.mob.tickCount % 20 == 0)
				{
					List<CatEntity> list = this.mob.level.getEntitiesOfClass(CatEntity.class,
							this.mob.getBoundingBox().inflate(16.0D), EntityPredicates.ENTITY_STILL_ALIVE);
					if (!list.isEmpty())
					{
						for (CatEntity catentity : list)
						{
							catentity.hiss();
						}

						return false;
					}
				}

				return true;
			}
		} else
		{
			return false;
		}
	}

	public void start()
	{
	}

	public void stop()
	{
		this.mob.setTarget((LivingEntity) null);
		this.mob.attackPhase = AttackPhase.CIRCLE;
	}

	public void tick()
	{
		LivingEntity livingentity = this.mob.getTarget();
		this.mob.moveTargetPoint = new Vector3d(livingentity.getX(), livingentity.getY(0.5D),
				livingentity.getZ());
		if (this.mob.getBoundingBox().inflate((double) 0.2F).intersects(livingentity.getBoundingBox()))
		{
			this.mob.doHurtTarget(livingentity);
			this.mob.attackPhase = AttackPhase.CIRCLE;
			if (!this.mob.isSilent())
			{
				this.mob.level.levelEvent(1039, this.mob.blockPosition(), 0);
			}
		} else if (this.mob.horizontalCollision || this.mob.hurtTime > 0)
		{
			this.mob.attackPhase = AttackPhase.CIRCLE;
		}

	}

}
