package com.at.sonomod.blocks;

import com.at.sonomod.blocks.tileentity.GrillTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class GrillBlock extends BaseModelBlock<GrillTileEntity> {
	
	protected GrillBlock(String name, Material materialIn, SoundType sound) {
		super(name, materialIn, sound);
		
		this.fullBlock = false;
		this.setHardness(1.0f);
		
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.09F, 1.0F);
	}

	public Class<GrillTileEntity> getTileEntityClass() {
		return GrillTileEntity.class;
	}

	@Override
	public GrillTileEntity createTileEntity(World world, int metadata) {
		return new GrillTileEntity();
	}
}
