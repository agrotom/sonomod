package com.at.sonomod.blocks;

import com.at.sonomod.blocks.tileentity.BlockTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public abstract class BaseModelBlock<T extends TileEntity> extends BlockTileEntity<T>
{
	protected BaseModelBlock(String name, Material materialIn, SoundType sound) {
		super(name, materialIn, sound);
		ModBlocks.customModelBlocks.add(this);
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
}
