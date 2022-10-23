package com.at.sonomod.blocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class GrillTileEntity extends TileEntity {
	private ItemStack item = null;
	
	private static final String INV_TAG = "inventory";
	
	public ItemStack getItemStack()
	{
		return item;
	}
	
	public boolean hasItemIn()
	{
		return item != null;
	}
	
	public void operate(EntityPlayer player, ItemStack playerHand)
	{
		if(hasItemIn())
		{
			if(playerHand != null && item.getItem().equals(playerHand.getItem()))
			{
				item.stackSize++;
				--playerHand.stackSize;
			}
			else
			{
				if(!player.inventory.addItemStackToInventory(item))
					player.dropPlayerItemWithRandomChoice(item, false);
				else
					player.inventoryContainer.detectAndSendChanges();
				
				item = null;
			}
		}
		else if(playerHand != null)
		{	
			final ItemStack copy = playerHand.copy();
			copy.stackSize = 1;
			item = copy;
			
			--playerHand.stackSize;
		}
		
		this.markDirty();
	}

    @Override
    public boolean canUpdate() {

        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {

    	if(item != null)
    	{
	    	final NBTTagCompound inventory = new NBTTagCompound();
	    	
	        item.writeToNBT(inventory);
	
	        tagCompound.setTag(INV_TAG, inventory);
    	}
        
        super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {

    	if(tagCompound.hasKey(INV_TAG))
    		item = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag(INV_TAG));

        super.readFromNBT(tagCompound);
    }

    @Override
    public Packet getDescriptionPacket() {

        NBTTagCompound tagCompound = new NBTTagCompound();

        this.writeToNBT(tagCompound);

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {

        NBTTagCompound tagCompound = packet.getNbtCompound();

        this.readFromNBT(tagCompound);
    }
}
