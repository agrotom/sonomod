package net.agrotom.sonomod.capabilities;

import net.agrotom.sonomod.skills.ISkillsHandler;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class SkillsCapability {

	@CapabilityInject(ISkillsHandler.class)
	public static final Capability<ISkillsHandler> SKILLS_HANDLER;
	
	static {
		SKILLS_HANDLER = null;
	}

}
