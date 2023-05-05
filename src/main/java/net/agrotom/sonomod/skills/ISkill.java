package net.agrotom.sonomod.skills;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISkill extends INBTSerializable<CompoundNBT> {
	
	String getName();
	
	void setXP(float amount);
	
	void addXP(float amount);
	
	void subXP(float amount);
	
	float getXP();
}
