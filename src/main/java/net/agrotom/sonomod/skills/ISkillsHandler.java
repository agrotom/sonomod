package net.agrotom.sonomod.skills;

import java.util.Optional;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISkillsHandler extends INBTSerializable<CompoundNBT> {
	Optional<ISkill> getSkillByName(String skillName);
}
