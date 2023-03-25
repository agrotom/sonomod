package net.agrotom.sonomod.tileentity;

import net.agrotom.sonomod.blocks.WoodenBarrel;
import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.NBTIDs;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
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

public class WoodenBarrelTileEntity extends AbstractTileEntity
{

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
				return stack.getFluid().getAttributes().getTemperature() < 1000;
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
			
			final int maxCapacity = getCapacity();
			
			FluidActionResult result = FluidUtil.tryEmptyContainerAndStow(bucket, tank, itemHandler, maxCapacity, player, true);
			
			if(result.success)
			{
				player.setItemInHand(hand, result.result);
			}
			else
			{
				result = FluidUtil.tryFillContainerAndStow(bucket, tank, itemHandler, maxCapacity, player, true);
				
				if(result.success)
					player.setItemInHand(hand, result.result);
			}
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
	
	public int getWaterLevel()
	{
		final int currentAmount = getCurrentAmount();
		int waterLevel = (int) Math.floor(currentAmount / 1000);
		
		waterLevel = currentAmount > 0 && waterLevel == 0 ? 1 : waterLevel;
		
		return waterLevel;
	}
	
	
	//<=======================================DATA=METHODS=======================================>
	
	@Override
	protected void markUpdate()
	{
		updateState();
		
		super.markUpdate();
	}
	
	public void updateState()
	{
		if(!this.hasLevel()) return;
		
		if(!level.isClientSide)
		{
			final int waterLevel = getWaterLevel();
			
			if(waterLevel != getBlockState().getValue(WoodenBarrel.FLUID_LEVEL))
				level.setBlock(worldPosition, getBlockState().setValue(WoodenBarrel.FLUID_LEVEL, waterLevel), 3);
		}
	}
	
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
		
		tank.writeToNBT(tankNBT);
		
		nbt.put(TANK_TAG, tankNBT);
		
		return nbt;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return handler.cast();
		
		return super.getCapability(cap, side);
	}
	
}

	
