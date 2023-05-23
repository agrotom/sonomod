package net.agrotom.sonomod.capabilities;

import net.agrotom.sonomod.skills.ISkillsCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class SkillsCapability {

	@CapabilityInject(ISkillsCompound.class)
	public static final Capability<ISkillsCompound> SKILLS_HANDLER;
	
	static {
		SKILLS_HANDLER = null;
	}

}
