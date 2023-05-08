package net.agrotom.sonomod.skills;

import net.agrotom.sonomod.util.ICopyable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISkill extends INBTSerializable<CompoundNBT>, ICopyable<ISkill> {
	
	String getName();
	
	String getUnlocalizedName();
	
	void setXP(float amount);
	
	void addXP(float amount);
	
	void subXP(float amount);
	
	float getXP();
}
