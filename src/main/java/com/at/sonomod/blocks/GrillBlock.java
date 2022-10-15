package com.at.sonomod.blocks;

import java.util.logging.LogManager;

import com.at.sonomod.blocks.tileentity.BlockTileEntity;
import com.at.sonomod.blocks.tileentity.GrillTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GrillBlock extends BlockTileEntity<GrillTileEntity> {
	
	protected GrillBlock(String name, Material materialIn, SoundType sound) {
		super(name, materialIn, sound);
		
		this.fullBlock = false;
		
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.09F, 1.0F);
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

	public Class<GrillTileEntity> getTileEntityClass() {
		return GrillTileEntity.class;
	}

	@Override
	public GrillTileEntity createTileEntity(World world, int metadata) {
		return new GrillTileEntity();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
			float subY, float subZ) {
		
		player.openGui(player, side, worldIn, x, y, z);
		return true;
	}
}
