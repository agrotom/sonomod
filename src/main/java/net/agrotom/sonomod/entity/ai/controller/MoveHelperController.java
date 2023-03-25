package net.agrotom.sonomod.entity.ai.controller;

import net.agrotom.sonomod.entity.flyingentity.AbstractFlyingEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class MoveHelperController extends MovementController
{

	private float speed = 0.1F;

	public MoveHelperController(AbstractFlyingEntity mob)
	{
		super(mob);
	}

	public void tick()
	{
		if (this.mob.horizontalCollision)
		{
			this.mob.yRot += 180.0F;
			this.speed = 0.1F;
		}
		
		AbstractFlyingEntity flyingMob = (AbstractFlyingEntity) this.mob;

		float f = (float) (flyingMob.moveTargetPoint.x - this.mob.getX());
		float f1 = (float) (flyingMob.moveTargetPoint.y - this.mob.getY());
		float f2 = (float) (flyingMob.moveTargetPoint.z - this.mob.getZ());
		double d0 = (double) MathHelper.sqrt(f * f + f2 * f2);
		double d1 = 1.0D - (double) MathHelper.abs(f1 * 0.7F) / d0;
		f = (float) ((double) f * d1);
		f2 = (float) ((double) f2 * d1);
		d0 = (double) MathHelper.sqrt(f * f + f2 * f2);
		double d2 = (double) MathHelper.sqrt(f * f + f2 * f2 + f1 * f1);
		float f3 = this.mob.yRot;
		float f4 = (float) MathHelper.atan2((double) f2, (double) f);
		float f5 = MathHelper.wrapDegrees(this.mob.yRot + 90.0F);
		float f6 = MathHelper.wrapDegrees(f4 * (180F / (float) Math.PI));
		this.mob.yRot = MathHelper.approachDegrees(f5, f6, 4.0F) - 90.0F;
		this.mob.yBodyRot = this.mob.yRot;
		if (MathHelper.degreesDifferenceAbs(f3, this.mob.yRot) < 3.0F)
		{
			this.speed = MathHelper.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
		} else
		{
			this.speed = MathHelper.approach(this.speed, 0.2F, 0.025F);
		}

		float f7 = (float) (-(MathHelper.atan2((double) (-f1), d0) * (double) (180F / (float) Math.PI)));
		this.mob.xRot = f7;
		float f8 = this.mob.yRot + 90.0F;
		double d3 = (double) (this.speed * MathHelper.cos(f8 * ((float) Math.PI / 180F))) * Math.abs((double) f / d2);
		double d4 = (double) (this.speed * MathHelper.sin(f8 * ((float) Math.PI / 180F))) * Math.abs((double) f2 / d2);
		double d5 = (double) (this.speed * MathHelper.sin(f7 * ((float) Math.PI / 180F))) * Math.abs((double) f1 / d2);
		Vector3d vector3d = this.mob.getDeltaMovement();
		this.mob.setDeltaMovement(vector3d.add((new Vector3d(d3, d5, d4)).subtract(vector3d).scale(0.2D)));
	}

}
