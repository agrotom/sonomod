package net.agrotom.sonomod.skills;

import java.util.Map;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;

public class BasicSkill implements ISkill {

	public static final String NBT_TAG = "xp";
	
	public static final float MIN_XP = 0.0f;
	public static final float MAX_XP = 100000000.0f;
	
	private final String name;
	private float xp = 0.0f;
	
	public BasicSkill(String name, Map<String, ISkill> toRegister) {
		this.name = name;
		toRegister.put(name, this);
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void setXP(float amount) {
		amount = MathHelper.clamp(amount, MIN_XP, MAX_XP);
	}
	
	/**
	 * If a given parameter is a negative float, method will call the {@link #subXP(float) subXP} method with an abs(param).
	 * You may use addXP to subtract XP, but it is recommended to use {@link #subXP(float) subXP} for a code's clearness and effectiveness (due to the abs() method).
	 * @param amount to add
	 */
	@Override
	public void addXP(float amount) {
		if(amount < 0.0f) {
			subXP(Math.abs(amount));
			
			return;
		}
		
		if(this.xp + amount > MAX_XP || this.xp + amount < this.xp) {
			this.xp = MAX_XP;
		}
		else {
			this.xp += amount;
		}
	}
	
	@Override
	public void subXP(float amount) {
		if(this.xp - amount < MIN_XP || this.xp - amount > this.xp) {
			this.xp = MIN_XP;
		}
		else {
			this.xp -= amount;
		}
	}
	
	@Override
	public float getXP() {
		return this.xp;
	}

	@Override
	public CompoundNBT serializeNBT() {
		final CompoundNBT nbt = new CompoundNBT();
		
		nbt.putFloat(NBT_TAG, this.xp);
		
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if(nbt.contains(NBT_TAG)) {
			this.setXP(nbt.getFloat(NBT_TAG));
		}
		else {
			this.xp = MIN_XP;
		}
	}

}
