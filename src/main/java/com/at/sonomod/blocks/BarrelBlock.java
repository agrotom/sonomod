package com.at.sonomod.blocks;

import java.util.List;

import com.at.sonomod.blocks.tileentity.BarrelTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BarrelBlock extends BaseModelBlock<BarrelTileEntity>
{

	public BarrelBlock(String name, Material materialIn, SoundType sound) {
		super(name, materialIn, sound);
		
		this.usesMinecraftTexture = true;
		this.setTextureName("planks_oak");
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask, List list, Entity collider)
    {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
        super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
        float f = 0.125F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
        this.setBlockBoundsForItemRender();
    }
	
	public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

}
