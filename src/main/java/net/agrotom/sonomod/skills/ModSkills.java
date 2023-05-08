package net.agrotom.sonomod.skills;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.util.ICopyableWrapper;
import net.agrotom.sonomod.util.SkillWrapper;

public class ModSkills {

    private static final Map<String, ICopyableWrapper<ISkill>> registeredSkills = new HashMap<>();
    
    private ModSkills() {}
    
    public static final ICopyableWrapper<ISkill> COMBAT_SKILL = register(SonoMod.MOD_ID, "combat", "combat");
    
    /**
     * @return unmodifiable map of registered skills.
     */
    public static Map<String, ICopyableWrapper<ISkill>> getSkills() {
	return Collections.unmodifiableMap(registeredSkills);
    }
    
    public static ICopyableWrapper<ISkill> register(@Nonnull String modid, @Nonnull String id, @Nonnull String name) {
	final String fullID = modid + ":skills." + id;
	
	final ICopyableWrapper<ISkill> wrapper = SkillWrapper.create(new BasicSkill(name, modid));
	
	if(registeredSkills.keySet().contains(fullID)) {
	    SonoMod.LOGGER.error("Skill with id \"" + fullID + "\" is already taken!");
	}
	else {
	    registeredSkills.put(fullID, wrapper);
	}
	
	return wrapper;
    }
}
