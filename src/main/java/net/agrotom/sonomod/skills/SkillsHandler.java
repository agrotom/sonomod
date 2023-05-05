package net.agrotom.sonomod.skills;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import net.minecraft.nbt.CompoundNBT;

public class SkillsHandler implements ISkillsHandler {

	/**
	 * Provides identity of skills.
	 */
	public final Map<String, ISkill> skills = new HashMap<>();
	
	//public final List<ISkill> skills = new ArrayList<ISkill>();
	
	public final ISkill warrior = new BasicSkill("warrior", skills);

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
	public void deserializeNBT(CompoundNBT nbt) {
		skills.forEach((name, skill) -> {
			if(nbt.contains(name)) {
				skill.deserializeNBT(nbt.getCompound(name));
			}
		});
	}
	
	@Override
	public Optional<ISkill> getSkillByName(String skillName) {
		return Optional.ofNullable(skills.get(skillName));
	}

}
