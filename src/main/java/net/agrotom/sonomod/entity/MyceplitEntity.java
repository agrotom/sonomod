package net.agrotom.sonomod.entity;

import java.util.UUID;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MyceplitEntity extends CreatureEntity implements IAngerable
{
	private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
	private static final DataParameter<Integer> DATA_REMAINING_ANGER_TIME = EntityDataManager.defineId(MyceplitEntity.class, DataSerializers.INT);
	private UUID persistentAngerTarget;
	
	public float flap;
	public float flapSpeed;
	public float oFlapSpeed;
	public float oFlap;
	public float flapping = 1.0F;

	public MyceplitEntity(EntityType<? extends CreatureEntity> entity, World level)
	{
		super(entity, level);
	}
	
	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.33D)
                .add(Attributes.ATTACK_DAMAGE, 13.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, new ResetAngerGoal<>(this, true));
    }
    
    @Override
    protected int getExperienceReward(PlayerEntity palyerEntity)
    {
    	return 3 + this.level.random.nextInt(5);
    }
    
    @Override
	public void aiStep()
	{
		super.aiStep();
		
		if (!this.level.isClientSide)
		{
	         this.updatePersistentAnger((ServerWorld)this.level, true);
		}
		
		startFlapping();
	}
    
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
     }
	
	public boolean doHurtTarget(Entity entity) {
	      boolean flag = entity.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
	      if (flag) {
	         this.doEnchantDamageEffects(this, entity);
	      }

	      return flag;
	}
	
	protected void startFlapping()
	{
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed = (float)((double)this.flapSpeed + (double)(this.onGround ? -1 : 4) * 0.3D);
		this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
		
		if (!this.onGround && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}
	
		this.flapping = (float)((double)this.flapping * 0.9D);
		Vector3d vector3d = this.getDeltaMovement();
		
		if (!this.onGround && vector3d.y < 0.0D) {
			this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
		}
	
		this.flap += this.flapping * 2.0F;
	}
	
	
    
    //<====================================================== Cosmetic ======================================================>

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.PARROT_AMBIENT;
    }


    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.CHICKEN_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.CHICKEN_STEP, 1F, 1F);
    }
	
//<====================================================== Data ======================================================>
    
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
     }

     public void addAdditionalSaveData(CompoundNBT compoundNBT) {
        super.addAdditionalSaveData(compoundNBT);
        this.addPersistentAngerSaveData(compoundNBT);
     }

     public void readAdditionalSaveData(CompoundNBT compoundNBT) {
        super.readAdditionalSaveData(compoundNBT);
        if(!level.isClientSide)
        {
        	this.readPersistentAngerSaveData((ServerWorld)this.level, compoundNBT);
        }
     }

	@Override
	public int getRemainingPersistentAngerTime()
	{
		return this.entityData.get(DATA_REMAINING_ANGER_TIME);
	}

	@Override
	public void setRemainingPersistentAngerTime(int p_230260_1_)
	{
		this.entityData.set(DATA_REMAINING_ANGER_TIME, p_230260_1_);
	}

	@Override
	public UUID getPersistentAngerTarget()
	{
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(UUID uuid)
	{
		this.persistentAngerTarget = uuid;
	}

	@Override
	public void startPersistentAngerTimer()
	{
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
	}

}
