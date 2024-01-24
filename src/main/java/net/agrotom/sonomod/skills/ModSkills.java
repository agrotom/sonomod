package net.agrotom.sonomod.skills;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.skills.impl.BasicSkillInstance;
import net.minecraft.util.ResourceLocation;

public class ModSkills {

	private static final Map<ResourceLocation, ISkillInstance> registeredSkills = new HashMap<>();

	private ModSkills() {
	}

	public static final ISkillInstance COMBAT_SKILL = register("combat");
	public static final ISkillInstance AGILITY_SKILL = register("agility");
	public static final ISkillInstance MINING_SKILL = register("mining");
	public static final ISkillInstance FARMING_SKILL = register("farming");
	public static final ISkillInstance ENDURANCE_SKILL = register("endurance");
	public static final ISkillInstance SPEECHCRAFT_SKILL = register("speechcraft");

	/**
	 * @return unmodifiable map of registered skills.
	 */
	public static Map<ResourceLocation, ISkillInstance> getInstancesUnmodifiable() {
		return Collections.unmodifiableMap(registeredSkills);
	}

	public static ISkillInstance register(@Nonnull String modid, @Nonnull String name) {
		final ISkillInstance instance = BasicSkillInstance.create(name, modid);
		
		final String fullID = instance.getUnlocalizedName();
		
		final ResourceLocation path = ResourceLocation.of(fullID, ':');
		
		if (registeredSkills.put(path, instance) != null) {
			SonoMod.LOGGER.error("Skill with id \"" + fullID + "\" is already taken!\nThe skill will be replaced!");
		}

		return instance;
	}
	
	private static ISkillInstance register(@Nonnull String name) {
		return register(SonoMod.MOD_ID, name);
	}
}
