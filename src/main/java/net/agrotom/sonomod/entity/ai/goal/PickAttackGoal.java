package net.agrotom.sonomod.entity.ai.goal;

import net.agrotom.sonomod.entity.flyingentity.AbstractFlyingEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;

public class PickAttackGoal extends Goal
{
	private final AbstractFlyingEntity mob;
	private int nextSweepTick;

    public PickAttackGoal(AbstractFlyingEntity mob)
    {
    	this.mob = mob;
    }

    public boolean canUse() {
       LivingEntity livingentity = mob.getTarget();
       return livingentity != null ? mob.canAttack(mob.getTarget(), EntityPredicate.DEFAULT) : false;
    }

    public void start() {
       this.nextSweepTick = 10;
       mob.attackPhase = AttackPhase.CIRCLE;
       this.setAnchorAboveTarget();
    }

    public void stop() {
       mob.anchorPoint = mob.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, mob.anchorPoint).above(10 + mob.getRandom().nextInt(20));
    }

    public void tick() {
       if (mob.attackPhase == AttackPhase.CIRCLE) {
          --this.nextSweepTick;
          if (this.nextSweepTick <= 0) {
             mob.attackPhase = AttackPhase.SWOOP;
             this.setAnchorAboveTarget();
             this.nextSweepTick = (8 + mob.getRandom().nextInt(4)) * 20;
             mob.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + mob.getRandom().nextFloat() * 0.1F);
          }
       }

    }

    private void setAnchorAboveTarget() {
       mob.anchorPoint = mob.getTarget().blockPosition().above(20 + mob.getRandom().nextInt(20));
       if (mob.anchorPoint.getY() < mob.level.getSeaLevel()) {
          mob.anchorPoint = new BlockPos(mob.anchorPoint.getX(), mob.level.getSeaLevel() + 1, mob.anchorPoint.getZ());
       }

    }

}
