package com.at.sonomod.blocks;

import com.at.sonomod.blocks.tileentity.BarrelTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BarrelBlock extends BaseModelBlock<BarrelTileEntity>
{

	public BarrelBlock(String name, Material materialIn, SoundType sound) {
		super(name, materialIn, sound);
		
		this.setHardness(1.0f);
	}

	@Override
	public Class<BarrelTileEntity> getTileEntityClass()
	{
		// TODO Auto-generated method stub
		return BarrelTileEntity.class;
	}

	@Override
	public BarrelTileEntity createTileEntity(World world, int metadata)
	{
		// TODO Auto-generated method stub
		return new BarrelTileEntity();
	}

}
