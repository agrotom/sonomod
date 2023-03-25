package net.agrotom.sonomod.entity.flyingentity;

import net.agrotom.sonomod.entity.ai.controller.BodyHelperController;
import net.agrotom.sonomod.entity.ai.controller.LookHelperController;
import net.agrotom.sonomod.entity.ai.controller.MoveHelperController;
import net.agrotom.sonomod.entity.ai.goal.AttackPhase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class AbstractFlyingEntity extends FlyingEntity
{
	public Vector3d moveTargetPoint = Vector3d.ZERO;
	public BlockPos anchorPoint = BlockPos.ZERO;
	public AttackPhase attackPhase = AttackPhase.CIRCLE;

	protected AbstractFlyingEntity(EntityType<? extends AbstractFlyingEntity> entity, World level)
	{
		super(entity, level);
		this.moveControl = new MoveHelperController(this);
		this.lookControl = new LookHelperController(this);
	}

	protected BodyController createBodyControl()
	{
		return new BodyHelperController(this);
	}

	public void readAdditionalSaveData(CompoundNBT nbt)
	{
		super.readAdditionalSaveData(nbt);
		if (nbt.contains("AX"))
		{
			this.anchorPoint = new BlockPos(nbt.getInt("AX"), nbt.getInt("AY"), nbt.getInt("AZ"));
		}
	}

	public void addAdditionalSaveData(CompoundNBT nbt)
	{
		super.addAdditionalSaveData(nbt);
		nbt.putInt("AX", this.anchorPoint.getX());
		nbt.putInt("AY", this.anchorPoint.getY());
		nbt.putInt("AZ", this.anchorPoint.getZ());
	}
}
