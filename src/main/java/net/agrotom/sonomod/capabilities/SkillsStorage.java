package net.agrotom.sonomod.capabilities;

import net.agrotom.sonomod.skills.ISkillsCompound;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SkillsStorage implements Capability.IStorage<ISkillsCompound> {
	
	@Override
	public INBT writeNBT(Capability<ISkillsCompound> capability, ISkillsCompound instance, Direction side) {
	    
		return instance.serializeNBT();
	}

	@Override
	public void readNBT(Capability<ISkillsCompound> capability, ISkillsCompound instance, Direction side,
			INBT nbt) {
		
		final CompoundNBT nbtCompound = (CompoundNBT) nbt;
		
		instance.deserializeNBT(nbtCompound);
	}

}
