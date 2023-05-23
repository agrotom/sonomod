package net.agrotom.sonomod.skills;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

import net.agrotom.sonomod.SonoMod;
import net.minecraft.util.ResourceLocation;

public class ModSkills {

	private static final Map<ResourceLocation, ISkillInstance> registeredSkills = new HashMap<>();

	private ModSkills() {
	}

	public static final ISkillInstance COMBAT_SKILL = register(SonoMod.MOD_ID, "combat");
	public static final ISkillInstance AGILITY_SKILL = register(SonoMod.MOD_ID, "agility");
	public static final ISkillInstance MINING_SKILL = register(SonoMod.MOD_ID, "mining");
	public static final ISkillInstance FARMING_SKILL = register(SonoMod.MOD_ID, "farming");
	public static final ISkillInstance ENDURANCE_SKILL = register(SonoMod.MOD_ID, "endurance");

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

		if (registeredSkills.keySet().contains(path)) {
			SonoMod.LOGGER.error("Skill with id \"" + fullID + "\" is already taken!");
			throw new RuntimeException();
		} else {
			registeredSkills.put(path, instance);
		}

		return instance;
	}
}
