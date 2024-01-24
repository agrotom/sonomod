package net.agrotom.sonomod.capabilities;

import net.agrotom.sonomod.skills.ISkillsCompound;
import net.agrotom.sonomod.skills.impl.SkillsCompound;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

	public static void register() {
		CapabilityManager.INSTANCE.register(ISkillsCompound.class, new SkillsStorage(), SkillsCompound::new);
	}

}
