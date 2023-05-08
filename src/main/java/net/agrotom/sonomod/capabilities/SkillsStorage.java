package net.agrotom.sonomod.capabilities;

import net.agrotom.sonomod.skills.ISkillsHandler;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SkillsStorage implements Capability.IStorage<ISkillsHandler> {
	
	@Override
	public INBT writeNBT(Capability<ISkillsHandler> capability, ISkillsHandler instance, Direction side) {
	    
		return instance.serializeNBT();
	}

	@Override
	public void readNBT(Capability<ISkillsHandler> capability, ISkillsHandler instance, Direction side,
			INBT nbt) {
		
		final CompoundNBT nbtCompound = (CompoundNBT) nbt;
		
		instance.deserializeNBT(nbtCompound);
	}

}
