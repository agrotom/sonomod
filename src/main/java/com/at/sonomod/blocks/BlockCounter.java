package com.at.sonomod.blocks;

import com.at.sonomod.blocks.tileentity.BlockTileEntity;
import com.at.sonomod.blocks.tileentity.TileEntityCounter;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class BlockCounter extends BlockTileEntity<TileEntityCounter> {

    public BlockCounter(String name, Material material, SoundType soundType) {

        super(name, material, soundType);

        this.setHarvestLevel("pickaxe", 3);
        this.setTextureName("grill");
    }

    @Override
    public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {

            TileEntityCounter tileEntity = this.getTileEntity(world, xPos, yPos, zPos);

            switch (side) {

                case 0://Bottom
                    tileEntity.decrementCount();
                    break;

                case 1://Top
                    tileEntity.incrementCount();
                    break;
            }

            player.addChatMessage(new ChatComponentTranslation("tile.counter.current", tileEntity.getCount()));
            System.out.println(tileEntity.getCount());
        }

        return true;
    }

    @Override
    public Class<TileEntityCounter> getTileEntityClass() {

        return TileEntityCounter.class;
    }

    @Override
    public TileEntityCounter createTileEntity(World world, int meta) {

        return new TileEntityCounter();
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
    
}