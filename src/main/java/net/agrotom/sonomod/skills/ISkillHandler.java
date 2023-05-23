package net.agrotom.sonomod.skills;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISkillHandler extends INBTSerializable<CompoundNBT>, IImmutableSkillHandler {
	
	void setXP(float amount);
	
	void addXP(float amount);
	
	void subXP(float amount);
	
	ISkillInstance getInstance();
	
}
