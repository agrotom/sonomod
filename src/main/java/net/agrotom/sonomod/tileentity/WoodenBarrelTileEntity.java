package net.agrotom.sonomod.tileentity;

import java.util.Optional;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.NBTIDs;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class WoodenBarrelTileEntity extends AbstractTileEntity {

	private final FluidTank tank = createHandler();
	private final LazyOptional<IFluidTank> handler = LazyOptional.of(() -> tank);
	
	private final String TANK_TAG = "tank";
	
	private FluidTank createHandler()
	{
		return new FluidTank(FluidAttributes.BUCKET_VOLUME * 4)
		{
			@Override
			protected void onContentsChanged()
			{
				markUpdate();
			}
			
			@Override
			public boolean isFluidValid(final FluidStack stack)
			{
				return true;
				//return stack.getFluid().getAttributes().getTemperature() < 100;
			}
		};
	}
	
	public WoodenBarrelTileEntity(final TileEntityType<?> tile)
	{
		super(tile);
	}
	
	public WoodenBarrelTileEntity()
	{
		this(ModTE.WOODEN_BARREL_TILEENTITY.get());
	}

	//<=======================================FLUID=METHODS=======================================>

	/**
	 * 
	 * @param fluid Fluid to fill.
	 * @param amount Amount to fill.
	 * @return Amount of fluid, that was filled. If fluid was not filled, returns zero.
	 */
	public int fill(final Fluid fluid, final int amount)
	{
		return tank.fill(new FluidStack(fluid, amount), FluidAction.EXECUTE);
	}
	
	public FluidStack drain(final int amount)
	{
		return tank.drain(new FluidStack(tank.getFluid(), amount), FluidAction.EXECUTE);
	}
	
	public void operate(final PlayerEntity player, final Hand hand)
	{
		if(!level.isClientSide)
		{
			final ItemStack bucket = player.getMainHandItem();
			
			if(bucket.isEmpty())
			{
				ATUtils.logToAllPlayers(level, tank.getFluidAmount());
				return;
			}
			
			final IItemHandler itemHandler = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseGet(null);
			
			
			
			player.setItemInHand(hand, .result);
			
			player.setItemInHand(hand, FluidUtil.tryFillContainerAndStow(bucket, tank, itemHandler, getCapacity(), player, true).result);
		}
	}
	
	public Fluid getFluid()
	{
		return tank.getFluid().getRawFluid();
	}
	
	public int getCurrentAmount()
	{
		return tank.getFluidAmount();
	}
	
	public int getFreeSpace()
	{
		return tank.getSpace();
	}
	
	public int getCapacity()
	{
		return tank.getCapacity();
	}
	
	/**
	 * Eg. a bucket has a locked capacity with 1000ml of a fluid, so the method is exist.
	 */
	public boolean canFillWith(final int amount)
	{
		return tank.getSpace() >= 1000;
	}
	
	/**
	 * Eg. a bucket has a locked capacity with 1000ml of a fluid, so the method is exist.
	 */
	public boolean canDrainWith(final int amount)
	{
		return tank.getFluidAmount() >= 1000;
	}
	
	
	//<=======================================DATA=METHODS=======================================>
	
	@Override
	public void load(final BlockState state, final CompoundNBT nbt)
	{
		super.load(state, nbt);
		
		if(nbt.contains(TANK_TAG, NBTIDs.COMPOUND))
		{
			tank.readFromNBT(nbt.getCompound(TANK_TAG));
		}
	}
	
	@Override
	public CompoundNBT save(final CompoundNBT nbt)
	{
		super.save(nbt);
		
		final CompoundNBT tankNBT = new CompoundNBT();
		
		nbt.put(TANK_TAG, tankNBT);
		
		return nbt;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return handler.cast();
		
		return super.getCapability(cap, side);
	}
	
}

	
