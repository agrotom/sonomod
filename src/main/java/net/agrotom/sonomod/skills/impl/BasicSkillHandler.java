package net.agrotom.sonomod.skills.impl;

import net.agrotom.sonomod.skills.ISkillHandler;
import net.agrotom.sonomod.skills.ISkillInstance;
import net.agrotom.sonomod.util.NBTIDs;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;

public class BasicSkillHandler implements ISkillHandler {

	public static final String XP_TAG = "xp";

	public static final float MIN_XP = 0.0f;
	public static final float MAX_XP = 100000000.0f;
	
	private final ISkillInstance instance;
	
	private float xp = 0.0f;
	
	public BasicSkillHandler(ISkillInstance instance) {
		this.instance = instance;
	}
	
	public static ISkillHandler create(ISkillInstance instance) {
		return new BasicSkillHandler(instance);
	}

	@Override
	public void setXP(float amount) {
		this.xp = MathHelper.clamp(amount, MIN_XP, MAX_XP);
	}

	/**
	 * If a given parameter is a negative float, method will call the
	 * {@link #subXP(float) subXP} method with an abs(param). You may use addXP to
	 * subtract XP, but it is recommended to use {@link #subXP(float) subXP} for a
	 * code's clearness and effectiveness (due to the abs() method).
	 * 
	 * @param amount - XP to add
	 */
	@Override
	public void addXP(float amount) {
		if (amount < 0.0f) {
			subXP(Math.abs(amount));

			return;
		}

		if (this.xp + amount > MAX_XP || this.xp + amount < this.xp) {
			this.xp = MAX_XP;
		} else {
			this.xp += amount;
		}
	}

	@Override
	public void subXP(float amount) {
		if (this.xp - amount < MIN_XP || this.xp - amount > this.xp) {
			this.xp = MIN_XP;
		} else {
			this.xp -= amount;
		}
	}

	@Override
	public float getXP() {
		return this.xp;
	}

	@Override
	public ISkillInstance getInstance() {
		return instance;
	}
	
	@Override
	public CompoundNBT serializeNBT() {
		final CompoundNBT nbt = new CompoundNBT();

		nbt.putFloat(XP_TAG, this.xp);

		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if (nbt.contains(XP_TAG, NBTIDs.FLOAT)) {
			this.setXP(nbt.getFloat(XP_TAG));
		} else {
			this.xp = MIN_XP;
		}
	}

	@Override
	public BasicSkillHandler copy() {
		final BasicSkillHandler copied = new BasicSkillHandler(instance);
		copied.xp = this.xp;
		
		return copied;
	}

}
