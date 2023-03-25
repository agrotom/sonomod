package net.agrotom.sonomod.entity.ai.goal;

import java.util.EnumSet;

import net.agrotom.sonomod.entity.flyingentity.AbstractFlyingEntity;
import net.minecraft.entity.ai.goal.Goal;

public abstract class MoveGoal extends Goal
{
	protected final AbstractFlyingEntity mob;
	
	public MoveGoal(AbstractFlyingEntity mob) {
		this.mob = mob;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	protected boolean touchingTarget() {
		return mob.moveTargetPoint.distanceToSqr(mob.getX(), mob.getY(), mob.getZ()) < 4.0D;
	}

}
