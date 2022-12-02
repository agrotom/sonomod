package net.agrotom.sonomod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class AbstractTileEntity extends TileEntity
{
	protected void markUpdate()
	{
		if (!this.hasLevel()) return;
		
		if(!level.isClientSide)
		{
			setChanged();
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
		}
	}
	
	public AbstractTileEntity(final TileEntityType<?> tile)
	{
		super(tile);
	}
	
	@Override
	public void handleUpdateTag(final BlockState state, final CompoundNBT tag)
	{
		load(state, tag);
	}
	
	@Override
	public CompoundNBT getUpdateTag()
	{
		final CompoundNBT nbt = new CompoundNBT();

		save(nbt);
		
		return nbt;
	}
	
	@Override
	public void load(final BlockState state, final CompoundNBT nbt)
	{
		super.load(state, nbt);
	}
	
	@Override
	public CompoundNBT save(final CompoundNBT nbt)
	{
		return super.save(nbt);
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		final CompoundNBT nbtTag = new CompoundNBT();
		
		save(nbtTag);
		
	    return new SUpdateTileEntityPacket(this.worldPosition, -1, nbtTag);
	}
	
	@Override
	public void onDataPacket(final NetworkManager net, final SUpdateTileEntityPacket pkt)
	{
		load(level.getBlockState(pkt.getPos()), pkt.getTag());
	}
}
