package net.agrotom.sonomod.capabilities;

import net.agrotom.sonomod.skills.ISkillsHandler;
import net.agrotom.sonomod.skills.SkillsHandler;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

	public static void register() {
		CapabilityManager.INSTANCE.register(ISkillsHandler.class, new SkillsStorage(), SkillsHandler::new);
	}

}
