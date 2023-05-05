package net.agrotom.sonomod.util;

import net.minecraft.nbt.CompoundNBT;

public interface ISelfSerializable {
	void saveToNBT(CompoundNBT nbt);
	void loadFromNBT(CompoundNBT nbt);
}
