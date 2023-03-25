package net.agrotom.sonomod.entity;

import java.util.Random;

import javax.annotation.Nullable;

import net.agrotom.sonomod.entity.ai.goal.AttackPlayerGoal;
import net.agrotom.sonomod.entity.ai.goal.OrbitPointGoal;
import net.agrotom.sonomod.entity.ai.goal.PickAttackGoal;
import net.agrotom.sonomod.entity.ai.goal.SweepAttackGoal;
import net.agrotom.sonomod.entity.flyingentity.AbstractFlyingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class GimosEntity extends AbstractFlyingEntity
{

	public GimosEntity(EntityType<? extends GimosEntity> p_i50200_1_, World p_i50200_2_)
	{
		super(p_i50200_1_, p_i50200_2_);
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes()
	{
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, 0.33D)
				.add(Attributes.ATTACK_DAMAGE, 13.0D);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new PickAttackGoal(this));
		this.goalSelector.addGoal(2, new SweepAttackGoal(this));
		this.goalSelector.addGoal(3, new OrbitPointGoal(this));
		this.targetSelector.addGoal(1, new AttackPlayerGoal(this));
	}

	public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_,
			SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_)
	{
		this.anchorPoint = this.blockPosition().above(5);
		return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
	}

	public SoundCategory getSoundSource()
	{
		return SoundCategory.HOSTILE;
	}

	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.GUARDIAN_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_184601_1_)
	{
		return SoundEvents.GUARDIAN_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.GUARDIAN_DEATH;
	}

	public static boolean isDarkEnoughToSpawn(IServerWorld p_223323_0_, BlockPos p_223323_1_, Random p_223323_2_)
	{
		if (p_223323_0_.getBrightness(LightType.SKY, p_223323_1_) > p_223323_2_.nextInt(32))
		{
			return false;
		} else
		{
			int i = p_223323_0_.getLevel().isThundering() ? p_223323_0_.getMaxLocalRawBrightness(p_223323_1_, 10)
					: p_223323_0_.getMaxLocalRawBrightness(p_223323_1_);
			return i <= p_223323_2_.nextInt(8);
		}
	}

	public static boolean checkMonsterSpawnRules(EntityType<? extends GimosEntity> p_223325_0_,
			IServerWorld p_223325_1_, SpawnReason p_223325_2_, BlockPos p_223325_3_, Random p_223325_4_)
	{
		return p_223325_1_.getDifficulty() != Difficulty.PEACEFUL
				&& isDarkEnoughToSpawn(p_223325_1_, p_223325_3_, p_223325_4_)
				&& checkMobSpawnRules(p_223325_0_, p_223325_1_, p_223325_2_, p_223325_3_, p_223325_4_);
	}

	public static boolean checkAnyLightMonsterSpawnRules(EntityType<? extends GimosEntity> p_223324_0_,
			IWorld p_223324_1_, SpawnReason p_223324_2_, BlockPos p_223324_3_, Random p_223324_4_)
	{
		return p_223324_1_.getDifficulty() != Difficulty.PEACEFUL
				&& checkMobSpawnRules(p_223324_0_, p_223324_1_, p_223324_2_, p_223324_3_, p_223324_4_);
	}

}
