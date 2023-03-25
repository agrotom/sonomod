package net.agrotom.sonomod.entity.ai.controller;

import net.agrotom.sonomod.entity.flyingentity.AbstractFlyingEntity;
import net.minecraft.entity.ai.controller.BodyController;

public class BodyHelperController extends BodyController
{
	private final AbstractFlyingEntity mob;

	public BodyHelperController(AbstractFlyingEntity mob)
	{
		super(mob);
		this.mob = mob;
	}
	
	public void clientTick() {
        mob.yHeadRot = mob.yBodyRot;
        mob.yBodyRot = mob.yRot;
     }

}
