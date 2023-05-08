package net.agrotom.sonomod.capabilities;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.skills.ISkillsHandler;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SkillsCapabilityProvider implements ICapabilitySerializable<INBT> {
	
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

	@Override
	public INBT serializeNBT() {
	    final Capability<ISkillsHandler> cap = SkillsCapability.SKILLS_HANDLER;
	    
	    if(cap != null) {
		return cap.getStorage().writeNBT(cap, capability.orElse(null), null);
	    }
	    
	    return null;
	}

	@Override
	public void deserializeNBT(@Nonnull INBT nbt) {
	    final Capability<ISkillsHandler> cap = SkillsCapability.SKILLS_HANDLER;
	    
	    if(cap != null) {
		cap.getStorage().readNBT(cap, capability.orElse(null), null, nbt);
	    }
	}
	
}
