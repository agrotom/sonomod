package com.at.sonomod.blocks;

import java.util.ArrayList;

import com.at.sonomod.blocks.tileentity.GrillTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.world.World;

public class GrillBlock extends BaseModelBlock<GrillTileEntity> {
	
	protected GrillBlock(String name, Material materialIn, SoundType sound) {
		super(name, materialIn, sound);
		
		this.fullBlock = false;
		this.usesMinecraftTexture = true;
		this.setHardness(1.0f);
		
		this.setTextureName("cobblestone");
		
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.09F, 1.0F);
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
		
		
		
		if(!worldIn.isRemote)
		{
			final GrillTileEntity te = (GrillTileEntity) worldIn.getTileEntity(x, y, z);
			
			final ItemStack playerItem = player.getHeldItem();
			
			te.operate(player, playerItem);
		}
		
		return true;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
        GrillTileEntity te = getTileEntity(world, x, y, z);
        if (te != null && te.hasItemIn())
            ret.add(te.getItemStack());
        return ret;
    }
	
	@Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
    {
        if (willHarvest) return true; //If it will harvest, delay deletion of the block until after getDrops
        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        super.harvestBlock(world, player, x, y, z, meta);
        world.setBlockToAir(x, y, z);
    }
}
