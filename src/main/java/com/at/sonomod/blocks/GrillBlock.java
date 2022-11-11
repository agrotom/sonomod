package com.at.sonomod.blocks;

import java.util.Random;

import com.at.sonomod.blocks.tileentity.GrillTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GrillBlock extends BaseModelBlock<GrillTileEntity> {
	
	private final Random rand = new Random();
	
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
	
	public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta)
    {
        GrillTileEntity grillTileEntity = (GrillTileEntity)worldIn.getTileEntity(x, y, z);

        if (grillTileEntity != null)
        {
            for (final ItemStack itemstack : grillTileEntity.getDrops())
            {

                if (itemstack != null)
                {
                    float f = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = this.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; worldIn.spawnEntityInWorld(entityitem))
                    {
                        int j1 = this.rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(worldIn, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getMetadata()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            worldIn.updateNeighborsAboutBlockChange(x, y, z, blockBroken);
        }

        super.breakBlock(worldIn, x, y, z, blockBroken, meta);
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
			float subY, float subZ) {
		
		
		
		if(!worldIn.isRemote)
		{
			final GrillTileEntity te = (GrillTileEntity) worldIn.getTileEntity(x, y, z);
			
			final ItemStack playerItem = player.getHeldItem();
			
			te.operate(player, playerItem, side, subX, subZ);
		}
		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack)
    {
		//This gets the direction the player is facing as an int from 0 to 3
		int dir = MathHelper.floor_double((player.rotationYaw * 4F) / 360F + 0.5D) & 3;
		//You can use the block metadata to save the direction
		world.setBlockMetadataWithNotify(x, y, z, dir, 3);
		//Or you can save it in a tile entity if you are using one
		createTileEntity(world, world.getBlockMetadata(x, y, z));
	}
}
