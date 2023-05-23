package net.agrotom.sonomod.skills;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.util.ICopyable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISkillsCompound extends INBTSerializable<CompoundNBT>, ICopyable<ISkillsCompound> {
	Map<String, IImmutableSkillHandler> getSkillsUnmodifiable();
	
	void copyFrom(@Nonnull ISkillsCompound compound);
	
	Optional<ISkillHandler> getSkillByID(@Nonnull String instance);
	Optional<ISkillHandler> getSkill(@Nonnull ISkillInstance instance);
}
