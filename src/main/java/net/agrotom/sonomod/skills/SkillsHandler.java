package net.agrotom.sonomod.skills;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.util.NBTIDs;
import net.minecraft.nbt.CompoundNBT;

public class SkillsHandler implements ISkillsHandler {

	/**
	 * Provides identity of skills.
	 */
	public final Map<String, ISkill> skills = new HashMap<>();
	
	public SkillsHandler() {
	    ModSkills.getSkills().forEach((name, skill) -> {
		skills.put(name, skill.copyObject());
	    });
	}

	@Override
	public CompoundNBT serializeNBT() {
		final CompoundNBT nbt = new CompoundNBT();
		
		skills.forEach((name, skill) -> {
			//TODO: Override safety
			
			nbt.put(name, skill.serializeNBT());
		});
		
		return nbt;
	}

	@Override
	public void deserializeNBT(@Nonnull CompoundNBT nbt) {
		skills.forEach((name, skill) -> {
			if(nbt.contains(name, NBTIDs.COMPOUND)) {
			    skill.deserializeNBT(nbt.getCompound(name));
			}
		});
	}
	
	@Override
	public Optional<ISkill> getSkillByID(@Nonnull String skillName) {
		return Optional.ofNullable(skills.get(skillName));
	}

	@Override
	public void copy(@Nonnull ISkillsHandler handler) {
	    
	}

}
