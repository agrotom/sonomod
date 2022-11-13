package com.at.sonomod.blocks.tileentity;

import java.util.ArrayList;
import java.util.HashMap;

import javax.vecmath.Vector2d;

import org.lwjgl.opengl.GL11;

import com.at.sonomod.recipes.GrillRecipes;
import com.at.sonomod.renderer.SpecialRender;
import com.at.sonomod.renderer.TextRenderer;
import com.google.gson.annotations.Since;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class GrillTileEntity extends TileEntity implements SpecialRender {
	private ItemStack item = null;
	private ItemStack bakedItem = null;
	
	private int burnTime = 0;
	
	private static final String INV_TAG = "inventory";
	private static final String RES_TAG = "result";
	private static final String BTIME_TAG = "burnTime";
	
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
			{
				bakedItem = result.copy();
				item.stackSize--;
			}
			else if(bakedItem.stackSize < 64)
			{
				bakedItem.stackSize++;
				item.stackSize--;
			}
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
	
	public void operate(EntityPlayer player, ItemStack playerHand, int side, float x, float z)
	{
		if(side == 1)
		{
			float subCoord = getBlockMetadata() % 2 == 0 ? z : x;
			boolean reverse = getBlockMetadata() == 2 || getBlockMetadata() == 1;
			
			if((!reverse && subCoord >= 0.5f && subCoord <= 1.0f) || (reverse && subCoord >= 0.0f && subCoord < 0.5f))
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
			else if((!reverse && subCoord >= 0.0f && subCoord < 0.5f) || (reverse && subCoord >= 0.5f && subCoord <= 1.0f))
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
        

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, getBlockMetadata(), tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
    	
        NBTTagCompound tagCompound = packet.getNbtCompound();

        this.readFromNBT(tagCompound);
    }

    @SideOnly(Side.CLIENT)
	@Override
	public void specialRenderFunc(double x, double y, double z) {
    	EntityItem e = null;
    	
    	/*HashMap<Integer, Vector2d> dir = new HashMap<>();
    	dir.put(0, new Vector2d(1, 0));
    	dir.put(1, new Vector2d(0, 1));
    	dir.put(2, new Vector2d(-1, 0));
    	dir.put(3, new Vector2d(0, -1));*/
    	
		if(item != null && (e == null || !item.getItem().equals(e.getEntityItem().getItem()))) {
			final int dir = getBlockMetadata();
			final float alpha = dir * 90.0f;
			
			boolean localX = getBlockMetadata() % 2 == 0;
			boolean reverse = getBlockMetadata() == 2 || getBlockMetadata() == 1;
			
			e = new EntityItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, item);
			e.hoverStart = 0;
			e.rotationYaw = 0;
			e.motionX = 0;
			e.motionY = 0;
			e.motionZ = 0;
			
			if(Minecraft.getMinecraft().thePlayer.isSneaking())
			{
				/*if(reverse)
					//TextRenderer.renderLabel(this, "" + item.stackSize, x + (localX ? 0.5F : 0.8F), y + 0.8F, z + (localX ? 1.15F : 0.85F));
					TextRenderer.renderLabel(this, "" + item.stackSize, (float)x + (localX ? 0.5F : 0.8F), (float)y + 0.8F, (float)z + (localX ? 0.8F : 0.5F));
				else
					TextRenderer.renderLabel(this, "" + item.stackSize, (float)x + (localX ? 0.5F : 0.27F), (float)y + 0.8F, (float)z + (localX ? 0.17F : 0.5F));*/
				
				TextRenderer.renderLabel(this, "" + item.stackSize, (float)x + (localX ? 0.5F : 0.8F), (float)y + 0.8F, (float)z + (localX ? 0.8F : 0.5F));
			}
			
			GL11.glPushMatrix();
			/*if(reverse)
				GL11.glTranslatef((float)x + (localX ? 0.5F : 0.8F), (float)y + 0.1F, (float)z + (localX ? 0.8F : 0.5F));
			else
				GL11.glTranslatef((float)x + (localX ? 0.5F : 0.27F), (float)y + 0.1F, (float)z + (localX ? 0.17F : 0.5F));*/
			
			final float zi = (float) (0.5f + z - 0.25f * Math.cos(Math.toRadians(alpha)));
			final float xi = (float) (0.5f + x + 0.25f * Math.sin(Math.toRadians(alpha)));
			
			GL11.glTranslatef((float)xi, (float)y + 0.1F, (float)zi);
			RenderHelper.enableStandardItemLighting();
			RenderManager.instance.renderEntityWithPosYaw(e, 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
		
		if(bakedItem != null && (e == null || !bakedItem.getItem().equals(e.getEntityItem().getItem()))) {
			final int dir = getBlockMetadata();
			final float alpha = dir * 90.0f;
			boolean localX = getBlockMetadata() % 2 == 0;
			boolean reverse = getBlockMetadata() == 2 || getBlockMetadata() == 1;
			
			e = new EntityItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, bakedItem);
			e.hoverStart = 0;
			e.rotationYaw = 0;
			e.motionX = 0;
			e.motionY = 0;
			e.motionZ = 0;
			
			if(Minecraft.getMinecraft().thePlayer.isSneaking())
			{
				if(reverse)
					TextRenderer.renderLabel(this, "" + bakedItem.stackSize, (float)x + (localX ? 0.5F : 0.27F), (float)y + 0.8F, (float)z + (localX ? 0.27F : 0.5F));
				else
					TextRenderer.renderLabel(this, "" + bakedItem.stackSize, (float)x + (localX ? 0.5F : 0.8F), (float)y + 0.8F, (float)z + (localX ? 0.8F : 0.5F));
			}
			
			GL11.glPushMatrix();
			/*if(reverse)
				GL11.glTranslatef((float)x + (localX ? 0.5F : 0.27F), (float)y + 0.1F, (float)z + (localX ? 0.27F : 0.5F));
			else
				GL11.glTranslatef((float)x + (localX ? 0.5F : 0.8F), (float)y + 0.1F, (float)z + (localX ? 0.8F : 0.5F));*/
				
			final float zi = (float) (0.5f + z + 0.25f * Math.cos(Math.toRadians(alpha)));
			final float xi = (float) (0.5f + x - 0.25f * Math.sin(Math.toRadians(alpha)));
			
			GL11.glTranslatef((float)xi, (float)y + 0.1F, (float)zi);
			
			RenderHelper.enableStandardItemLighting();
			RenderManager.instance.renderEntityWithPosYaw(e, 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
		/*
		 * @SideOnly(Side.CLIENT)
	@Override
	public void specialRenderFunc(double x, double y, double z) {
    	EntityItem e = null;
    	
		if(item != null && (e == null || !item.getItem().equals(e.getEntityItem().getItem()))) {
			boolean localX = getBlockMetadata() % 2 == 0;
			boolean reverse = getBlockMetadata() == 2 || getBlockMetadata() == 1;
			
			e = new EntityItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, item);
			e.hoverStart = 0;
			e.rotationYaw = 0;
			e.motionX = 0;
			e.motionY = 0;
			e.motionZ = 0;
			
			if(Minecraft.getMinecraft().thePlayer.isSneaking())
			{
				if(reverse)
					//TextRenderer.renderLabel(this, "" + item.stackSize, x + (localX ? 0.5F : 0.8F), y + 0.8F, z + (localX ? 1.15F : 0.85F));
					TextRenderer.renderLabel(this, "" + item.stackSize, (float)x + (localX ? 0.5F : 0.8F), (float)y + 0.8F, (float)z + (localX ? 0.8F : 0.5F));
				else
					TextRenderer.renderLabel(this, "" + item.stackSize, (float)x + (localX ? 0.5F : 0.27F), (float)y + 0.8F, (float)z + (localX ? 0.17F : 0.5F));
			}
			
			GL11.glPushMatrix();
			if(reverse)
				GL11.glTranslatef((float)x + (localX ? 0.5F : 0.8F), (float)y + 0.1F, (float)z + (localX ? 0.8F : 0.5F));
			else
				GL11.glTranslatef((float)x + (localX ? 0.5F : 0.27F), (float)y + 0.1F, (float)z + (localX ? 0.17F : 0.5F));
			RenderHelper.enableStandardItemLighting();
			RenderManager.instance.renderEntityWithPosYaw(e, 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
		
		if(bakedItem != null && (e == null || !bakedItem.getItem().equals(e.getEntityItem().getItem()))) {
			boolean localX = getBlockMetadata() % 2 == 0;
			boolean reverse = getBlockMetadata() == 2 || getBlockMetadata() == 1;
			
			e = new EntityItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, bakedItem);
			e.hoverStart = 0;
			e.rotationYaw = 0;
			e.motionX = 0;
			e.motionY = 0;
			e.motionZ = 0;
			
			if(Minecraft.getMinecraft().thePlayer.isSneaking())
			{
				if(reverse)
					TextRenderer.renderLabel(this, "" + bakedItem.stackSize, (float)x + (localX ? 0.5F : 0.27F), (float)y + 0.8F, (float)z + (localX ? 0.27F : 0.5F));
				else
					TextRenderer.renderLabel(this, "" + bakedItem.stackSize, (float)x + (localX ? 0.5F : 0.8F), (float)y + 0.8F, (float)z + (localX ? 0.8F : 0.5F));
			}
			
			GL11.glPushMatrix();
			if(reverse)
				GL11.glTranslatef((float)x + (localX ? 0.5F : 0.27F), (float)y + 0.1F, (float)z + (localX ? 0.27F : 0.5F));
			else
				GL11.glTranslatef((float)x + (localX ? 0.5F : 0.8F), (float)y + 0.1F, (float)z + (localX ? 0.8F : 0.5F));
				
			RenderHelper.enableStandardItemLighting();
			RenderManager.instance.renderEntityWithPosYaw(e, 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
		 */
		
	}
}
