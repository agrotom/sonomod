package com.at.sonomod.blocks.tileentity;

import com.at.sonomod.blocks.BlockBase;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BlockTileEntity<T extends TileEntity> extends BlockBase {

    public BlockTileEntity(String name, Material material, SoundType soundType) {

        super(name, material, soundType);

        GameRegistry.registerTileEntity(this.getTileEntityClass(), this.getUnlocalizedName());
    }

    public abstract Class<T> getTileEntityClass();

    public T getTileEntity(World world, int xPos, int yPos, int zPos) {

        return (T) world.getTileEntity(xPos, yPos, zPos);
    }

    @Override
    public int getRenderType()
    {
    	return -1;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
    	return false;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
    	return false;
    }
    
    @Override
    public boolean hasTileEntity(int metadata) {

        return true;
    }

    @Nullable
    @Override
    public abstract T createTileEntity(World world, int metadata);
}
