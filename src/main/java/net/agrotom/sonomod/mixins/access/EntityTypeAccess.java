package net.agrotom.sonomod.mixins.access;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;

@Mixin(TileEntityType.class)
public interface EntityTypeAccess
{
	@Accessor
    Set<Block> getValidBlocks();

    @Accessor
    void setValidBlocks(Set<Block> blocks);
}
