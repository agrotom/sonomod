package net.agrotom.sonomod.tileentity;

import org.spongepowered.asm.mixin.Final;

import net.agrotom.sonomod.blocks.GrillBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;

public class GrillTileEntity extends TileEntity implements ITickableTileEntity {
	
	private final Inventory slots = new Inventory(2);
	private short burnTime = 0;
	
	private final String INV_TAG = "inventory";
	private final String TIME_TAG = "time";
	
	public GrillTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public GrillTileEntity()
	{
		this(ModTE.GRILL_TILEENTITY.get());
	}
	
	public void operate(final PlayerEntity player, final Vector3d sub)
	{
		final Direction dir = getBlockState().getValue(GrillBlock.FACING);
		final float angle;
		
		switch(dir)
		{
		case EAST:
			angle = 90F;
			break;
		case SOUTH:
			angle = 90F * 2;
			break;
		case WEST:
			angle = 90F * 3;
			break;
		default:
			angle = 0;
			break;
		}
		
		double offZ = Math.abs(sub.z) - Math.floor(Math.abs(sub.z));
		double offX = Math.abs(sub.x) - Math.floor(Math.abs(sub.x));
		
		if(offZ > 0.5)
		{
			final ItemStack hand = player.getMainHandItem();
			
			if(hasItem())
			{
				final ItemStack item = slots.getItem(0);
				
				if(!hand.isEmpty() && slots.getItem(0).getItem().equals(hand.getItem()) && slots.getItem(0).getCount() < 64)
				{
					item.setCount(item.getCount() + 1);
					hand.setCount(hand.getCount() - 1);
				}
				else
				{
					if(!player.inventory.add(item))
						player.drop(item, false, false);
					else
						player.inventoryMenu.broadcastChanges();
					
					slots.removeItem(0, 64);
				}
			}
			else if(!hand.isEmpty())
			{
				final ItemStack copy = hand.copy();
				copy.setCount(1);
				
				slots.setItem(0, copy);
				
				hand.setCount(hand.getCount() - 1);
			}
		}
		
		level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 2);
		
		/*if(offZ > 0.5)
		{			
			if(!slots.getItem(1).isEmpty())
			{
				if(!player.inventory.add(slots.removeItem(1, 64)))
					player.drop(slots.getItem(1), false);
				else
					player.inventory.setChanged();
			}
		}
		else if(offZ <= 0.5)
		{
			final ItemStack hand = player.getMainHandItem();
			
			if(hasItem())
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
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();*/
	}
	
	public boolean hasBakedItem()
	{
		return !slots.getItem(1).isEmpty();
	}
	
	public boolean hasItem()
	{
		return !slots.getItem(0).isEmpty();
	}
	
	public ItemStack getItemCopy()
	{
		return slots.getItem(0).copy();
	}
	
	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		if(nbt.contains(TIME_TAG))
			burnTime = nbt.getShort(TIME_TAG);
		else
			burnTime = 0;
		
		if(nbt.contains(INV_TAG))
			slots.fromTag(nbt.getList(INV_TAG, 0));
		else
			slots.clearContent();
		
		super.load(state, nbt);
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		final ListNBT nbtList = slots.createTag();
		
		nbt.put(INV_TAG, nbtList);
		
		nbt.putShort(TIME_TAG, burnTime);
		
		return super.save(nbt);
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		final CompoundNBT nbtTag = new CompoundNBT();
		
		save(nbtTag);
		
	    return new SUpdateTileEntityPacket(getBlockPos(), -1, nbtTag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		load(level.getBlockState(pkt.getPos()), pkt.getTag());
	}

	@Override
	public void tick() {
		
	}
	
	
}
