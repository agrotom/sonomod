package com.at.sonomod.blocks.tileentity;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.at.sonomod.recipes.GrillRecipes;
import com.at.sonomod.renderer.SpecialRender;
import com.at.sonomod.renderer.TextRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import scala.tools.nsc.settings.Final;

public class GrillTileEntity extends TileEntity implements SpecialRender {
	private ItemStack item = null;
	private ItemStack bakedItem = null;
	
	private final EntityItem[] ets = { null, null };
	
	private int burnTime = 0;
	
	private static final String INV_TAG = "inventory";
	private static final String RES_TAG = "result";
	private static final String BTIME_TAG = "burnTime";
	
	public void onItemsUpdate()
	{
		if(hasItemIn())
		{
			ets[0] = new EntityItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, item);
			ets[0].hoverStart = 0; //this is making it be off-center
			ets[0].rotationYaw = 0; //this is what's making it rotate for you
			ets[0].motionX = 0; //zero out these just in case
			ets[0].motionY = 0;
			ets[0].motionZ = 0;
		}
		else
		{
			ets[0] = null;
		}
		
		if(hasBakedItemIn())
		{
			ets[1] = new EntityItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, bakedItem);
			ets[1].hoverStart = 0; //this is making it be off-center
			ets[1].rotationYaw = 0; //this is what's making it rotate for you
			ets[1].motionX = 0; //zero out these just in case
			ets[1].motionY = 0;
			ets[1].motionZ = 0;
		}
		else
		{
			ets[1] = null;
		}
	}
	
	public ItemStack getItemStack()
	{
		return item;
	}
	
	public boolean hasItemIn()
	{
		return item != null;
	}
	
	public boolean hasBakedItemIn()
	{
		return bakedItem != null;
	}
	
	private boolean canBake()
	{
		final ItemStack result = GrillRecipes.getSmeltingResult(item);
		
		return result != null && (bakedItem == null || GrillRecipes.itemStacksEquals(result, bakedItem));
	}
	
	private boolean bake()
	{
		final ItemStack result = GrillRecipes.getSmeltingResult(item);
		
		if(result != null)
		{
			if(bakedItem == null)
				bakedItem = result.copy();
			else if(bakedItem.stackSize < 64)
				bakedItem.stackSize++;
			else 
				return false;
			
			if(item.stackSize == 0)
				item = null;
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote && worldObj.getBlock(xCoord, yCoord - 1, zCoord).equals(Blocks.fire))
		{
			if(burnTime > 0)
			{
				if(canBake())
				{
					burnTime--;
					
					if(burnTime == 0)
					{
						bake();
						onItemsUpdate();
					}
				}
				else
				{
					burnTime = 0;
				}
			}
			else
			{
				if(canBake())
				{
					burnTime = 200;
				}
			}
			
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public void operate(EntityPlayer player, ItemStack playerHand, int side, float x)
	{
		if(side == 1)
		{
			if(x >= 0.5f && x <= 1.0f)
			{			
				if(hasBakedItemIn())
				{
					if(!player.inventory.addItemStackToInventory(bakedItem))
						player.dropPlayerItemWithRandomChoice(bakedItem, false);
					else
						player.inventoryContainer.detectAndSendChanges();
					
					bakedItem = null;
				}
			}
			else if(x >= 0.0f && x < 0.5f)
			{
				if(hasItemIn())
				{
					if(playerHand != null && item.getItem().equals(playerHand.getItem()) && item.stackSize < 64)
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
			}
			
			onItemsUpdate();
		}
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();
	}
	
	public ArrayList<ItemStack> getDrops()
	{
		final ArrayList<ItemStack> drops = new ArrayList<>();
		
		if(item != null)
			drops.add(item);
		if(bakedItem != null)
			drops.add(bakedItem);
		
		return drops;
			
	}

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {

    	if(item != null)
    	{
    		final NBTTagCompound slot1 = new NBTTagCompound();
	    	
	        item.writeToNBT(slot1);

	        tagCompound.setTag(INV_TAG, slot1);
    	}
    	
    	if(bakedItem != null)
    	{
    		final NBTTagCompound slot2 = new NBTTagCompound();
    		bakedItem.writeToNBT(slot2);
    		tagCompound.setTag(RES_TAG, slot2);
    	}
    	
    	tagCompound.setInteger(BTIME_TAG, burnTime);
        
        super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {

    	if(tagCompound.hasKey(INV_TAG))
    		item = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag(INV_TAG));
    	else
    		item = null;
    	
    	if(tagCompound.hasKey(BTIME_TAG))
    		burnTime = tagCompound.getInteger(BTIME_TAG);
    	
    	if(tagCompound.hasKey(RES_TAG))
    		bakedItem = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag(RES_TAG));
    	else
    		bakedItem = null;

        super.readFromNBT(tagCompound);
    }

    @Override
    public Packet getDescriptionPacket() {
    	
        NBTTagCompound tagCompound = new NBTTagCompound();

        this.writeToNBT(tagCompound);
        

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
    	
        NBTTagCompound tagCompound = packet.getNbtCompound();

        this.readFromNBT(tagCompound);
    }

    @SideOnly(Side.CLIENT)
	@Override
	public void specialRenderFunc(double x, double y, double z) {
		//item = new ItemStack(Items.porkchop);
		
    	EntityItem e;
    	
		if(item != null) {
			e = new EntityItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, item);
			e.hoverStart = 0; //this is making it be off-center
			e.rotationYaw = 0; //this is what's making it rotate for you
			e.motionX = 0; //zero out these just in case
			e.motionY = 0;
			e.motionZ = 0;
			
			EntityPlayer player = (EntityPlayer) worldObj.playerEntities.get(0);
			player.addChatComponentMessage(new ChatComponentText("ss"));
			
			if(Minecraft.getMinecraft().thePlayer.isSneaking())
				TextRenderer.renderLabel(this, "" + item.stackSize, x + 0.2F, y + 0.8F, z + 0.8F, 0);
			
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.2F, (float)y + 0.1F, (float)z + 0.5F);
			//GL11.glRotatef(180, 1, 0, 0);
			RenderHelper.enableStandardItemLighting();
			RenderManager.instance.renderEntityWithPosYaw(e, 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
		
		if(bakedItem != null) {
			e = new EntityItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, bakedItem);
			e.hoverStart = 0; //this is making it be off-center
			e.rotationYaw = 0; //this is what's making it rotate for you
			e.motionX = 0; //zero out these just in case
			e.motionY = 0;
			e.motionZ = 0;
			
			//player.addChatComponentMessage(new ChatComponentText("ss"));
			
			if(Minecraft.getMinecraft().thePlayer.isSneaking())
				TextRenderer.renderLabel(this, "" + bakedItem.stackSize, x + 0.8F, y + 0.8F, z + 0.8F, 0);
			
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.8F, (float)y + 0.1F, (float)z + 0.5F);
			//GL11.glRotatef(180, 1, 0, 0);
			RenderHelper.enableStandardItemLighting();
			RenderManager.instance.renderEntityWithPosYaw(e, 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
	}
}
