package net.agrotom.sonomod.capabilities;

import net.agrotom.sonomod.skills.ISkillsHandler;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class SkillsCapabilityProvider implements ICapabilityProvider {
	
	private final LazyOptional<ISkillsHandler> capability;
	
	private SkillsCapabilityProvider(ISkillsHandler handler) {
		this.capability = LazyOptional.of(() -> handler);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return SkillsCapability.SKILLS_HANDLER.orEmpty(cap, this.capability);
	}
	
	public static ICapabilityProvider createProvider(ISkillsHandler handler) {
		return new SkillsCapabilityProvider(handler);
	}
	
}
