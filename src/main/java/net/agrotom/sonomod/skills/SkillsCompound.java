package net.agrotom.sonomod.skills;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.util.NBTIDs;
import net.minecraft.nbt.CompoundNBT;

public class SkillsCompound implements ISkillsCompound {

	/**
	 * Provides identity of skills.
	 */
	private final Map<String, ISkillHandler> skills = new HashMap<>();

	public SkillsCompound() {
		ModSkills.getInstancesUnmodifiable().forEach((name, instance) -> {
			skills.put(instance.getUnlocalizedName(), BasicSkillHandler.create(instance));
		});
	}

	@Override
	public CompoundNBT serializeNBT() {
		final CompoundNBT nbt = new CompoundNBT();

		skills.forEach((name, skill) -> {
			// TODO: Override safety

			nbt.put(name, skill.serializeNBT());
		});

		return nbt;
	}

	@Override
	public void deserializeNBT(@Nonnull CompoundNBT nbt) {
		skills.forEach((name, skill) -> {
			if (nbt.contains(name, NBTIDs.COMPOUND)) {
				skill.deserializeNBT(nbt.getCompound(name));
			}
		});
	}

	@Override
	public Optional<ISkillHandler> getSkillByID(@Nonnull String instance) {
		return Optional.ofNullable(this.skills.get(instance));
	}
	
	@Override
	public Optional<ISkillHandler> getSkill(@Nonnull ISkillInstance instance) {
		return Optional.ofNullable(skills.get(instance.getUnlocalizedName()));
	}

	@Override
	public ISkillsCompound copy() {
		final SkillsCompound copied = new SkillsCompound();
		
		this.skills.forEach((name, skill) -> {
			copied.skills.put(name, skill.copy());
		});
		
		return copied;
	}

	@Override
	public void copyFrom(@Nonnull ISkillsCompound compound) {
		this.skills.clear();
		
		compound.getSkillsUnmodifiable().forEach((name, skill) -> {
			this.skills.put(name, skill.copy());
		});
	}

	@Override
	public Map<String, IImmutableSkillHandler> getSkillsUnmodifiable() {
		return Collections.unmodifiableMap(skills);
	}

}
