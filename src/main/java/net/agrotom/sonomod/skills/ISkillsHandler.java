package net.agrotom.sonomod.skills;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISkillsHandler extends INBTSerializable<CompoundNBT> {
	Optional<ISkill> getSkillByID(@Nonnull String skillName);
	void copy(@Nonnull ISkillsHandler handler);
}
