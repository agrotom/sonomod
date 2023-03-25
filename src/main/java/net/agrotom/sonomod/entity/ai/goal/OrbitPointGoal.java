package net.agrotom.sonomod.entity.ai.goal;

import net.agrotom.sonomod.entity.flyingentity.AbstractFlyingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class OrbitPointGoal extends MoveGoal
{
	private float angle;
	private float distance;
	private float height;
	private float clockwise;

	public OrbitPointGoal(AbstractFlyingEntity mob)
	{
		super(mob);
	}

	public boolean canUse()
	{
		return this.mob.getTarget() == null
				|| this.mob.attackPhase == AttackPhase.CIRCLE;
	}

	public void start()
	{
		this.distance = 5.0F + this.mob.getRandom().nextFloat() * 10.0F;
		this.height = -4.0F + this.mob.getRandom().nextFloat() * 9.0F;
		this.clockwise = this.mob.getRandom().nextBoolean() ? 1.0F : -1.0F;
		this.selectNext();
	}

	public void tick() {
		if (this.mob.getRandom().nextInt(350) == 0) {
			this.height = -4.0F + this.mob.getRandom().nextFloat() * 9.0F;
		}

		if (this.mob.getRandom().nextInt(250) == 0) {
			++this.distance;
			if (this.distance > 15.0F) {
				this.distance = 5.0F;
				this.clockwise = -this.clockwise;
			}
		}

		if (this.mob.getRandom().nextInt(450) == 0) {
			this.angle = this.mob.getRandom().nextFloat() * 2.0F * (float)Math.PI;
			this.selectNext();
		}

		if (this.touchingTarget()) {
			this.selectNext();
		}

		if (this.mob.moveTargetPoint.y < this.mob.getY() && !this.mob.level.isEmptyBlock(this.mob.blockPosition().below(1))) {
			this.height = Math.max(1.0F, this.height);
			this.selectNext();
		}

		if (this.mob.moveTargetPoint.y > this.mob.getY() && !this.mob.level.isEmptyBlock(this.mob.blockPosition().above(1))) {
			this.height = Math.min(-1.0F, this.height);
			this.selectNext();
		}

	}
	
	private void selectNext() {
        if (BlockPos.ZERO.equals(this.mob.anchorPoint)) {
        	this.mob.anchorPoint = this.mob.blockPosition();
        }

        this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
        this.mob.moveTargetPoint = Vector3d.atLowerCornerOf(this.mob.anchorPoint).add((double)(this.distance * MathHelper.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * MathHelper.sin(this.angle)));
     }
}
