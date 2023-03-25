package net.agrotom.sonomod.tileentity;

import java.util.Optional;

import net.agrotom.sonomod.blocks.GrillBlock;
import net.agrotom.sonomod.blocks.ModBlocks;
import net.agrotom.sonomod.data.recipes.GrillRecipe;
import net.agrotom.sonomod.data.recipes.ModRecipeTypes;
import net.agrotom.sonomod.util.NBTIDs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SmokingRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class GrillTileEntity extends AbstractTileEntity implements ITickableTileEntity {
	
	private final ItemStackHandler input = createHandler();
	private final ItemStackHandler output = createHandler();
	private final LazyOptional<ItemStackHandler> handlerInput = LazyOptional.of(() -> input);
	private final LazyOptional<ItemStackHandler> handlerOutput = LazyOptional.of(() -> output);
	
	private short burnTime = 0;
	
	private final String INPUT_TAG = "input";
	private final String OUTPUT_TAG = "output";
	private final String TIME_TAG = "time";
	
	private ItemStackHandler createHandler()
	{
		return new ItemStackHandler(1) {
			@Override
			protected void onContentsChanged(final int slot)
			{
				markUpdate();
			}
			
			@Override
			public boolean isItemValid(final int slot, final ItemStack stack)
			{
				return super.isItemValid(slot, stack);
			}
		};
	}
	
	public GrillTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public GrillTileEntity()
	{
		this(ModTE.GRILL_TILEENTITY.get());
	}
	
	/**
	 * Operates with grill block: pushes and pulls items.
	 * @param player Player used the block
	 * @param sub Player's subhit
	 */
	public void operate(final PlayerEntity player, final Vector3d sub)
	{
		if (!this.hasLevel()) return;
		
		final Direction dir = getBlockState().getValue(GrillBlock.FACING);
		final double axis = dir.getAxis() == Axis.X ? sub.x : sub.z;
		final double offset = Math.abs(axis) - Math.floor(Math.abs(axis));
		boolean isReverse = dir == Direction.NORTH || dir == Direction.WEST;
		
		if(((!isReverse && offset > 0.5d) || (isReverse && offset <= 0.5d)) && hasBakedItem())
		{
			pullItem(true, player);
		}
		else if((!isReverse && offset <= 0.5d) || (isReverse && offset > 0.5d))
		{
			final ItemStack hand = player.getMainHandItem();
			
			if(hasItem())
			{
				final ItemStack item = input.getStackInSlot(0);
				
				if(!hand.isEmpty() && item.getItem().equals(hand.getItem()) && item.getCount() < 64)
				{
					final ItemStack handCopy = hand.copy();
					handCopy.setCount(1);
					
					input.insertItem(0, handCopy, false);
					hand.setCount(hand.getCount() - 1);
				}
				else
				{
					pullItem(false, player);
				}
			}
			else if(!hand.isEmpty())
			{
				final ItemStack copy = hand.copy();
				copy.setCount(1);
				
				input.insertItem(0, copy, false);
				
				hand.setCount(hand.getCount() - 1);
			}
		}
	}
	
	@Override
	public void tick() {
		if (!this.hasLevel()) return;
		
		World world = this.getLevel();
		
		if (world.isClientSide) return;
		
		if(!world.isClientSide)
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
				markUpdate();
			}
			else
			{
				if(canBake())
				{
					burnTime = 120;
					
					getRecipe().ifPresent(recipe -> burnTime = (short) recipe.getBurnTime());
					
					markUpdate();
				}
			}
		}
	}
	
	public IInventory getItemsAsInventory()
	{
		final IInventory inventory = new Inventory(2);
		
		inventory.setItem(0, getInputItem());
		inventory.setItem(1, getOutputItem());
		
		return inventory;
	}
	
	public ItemStack[] getItems()
	{
		return new ItemStack[]{ getInputItem(), getOutputItem() };
	}
	
	//<=================================================SERVICE=METHODS=================================================>
	
	private Optional<GrillRecipe> getRecipe()
	{
		final IInventory inventory = new Inventory(1);
		
		inventory.setItem(0, getInputItem());
		
		return level.getRecipeManager().getRecipeFor(ModRecipeTypes.GRILL_RECIPE, inventory, level);
	}
	
	private Optional<SmokingRecipe> getCampfireRecipe()
	{
		final IInventory inventory = new Inventory(1);
		
		inventory.setItem(0, getInputItem());
		
		return level.getRecipeManager().getRecipeFor(IRecipeType.SMOKING, inventory, level);
	}
	
	
	private void pullItem(final boolean isOutput, final PlayerEntity player)
	{
		if (!this.hasLevel()) return;
		
		final ItemStack item = isOutput ? output.extractItem(0, 64, false) : input.extractItem(0, 64, false);
		
		if(!player.inventory.add(item))
			InventoryHelper.dropContents(this.level, this.getBlockPos(), new Inventory(item));
		else
			player.inventoryMenu.broadcastChanges();
	}
	
	
	private boolean hasBakedItem()
	{
		return !output.getStackInSlot(0).isEmpty();
	}
	
	private boolean hasItem()
	{
		return !input.getStackInSlot(0).isEmpty();
	}
	
	private ItemStack getInputItem()
	{
		return input.getStackInSlot(0);
	}
	
	private ItemStack getOutputItem()
	{
		return output.getStackInSlot(0);
	}
	
	private boolean canBake()
	{
		final Optional<GrillRecipe> recipe = getRecipe();
		
		boolean sameItem = true;
		boolean hasRecipe = false;
		
		if(recipe.isPresent())
		{
			final ItemStack result = recipe.get().getResultItem();
			
			sameItem = getOutputItem().getItem().equals(result.getItem());
			
			hasRecipe = true;
		}
		else
		{
			final Optional<SmokingRecipe> campfireRecipe = getCampfireRecipe();
			
			if(campfireRecipe.isPresent())
			{
				final ItemStack result = campfireRecipe.get().getResultItem();
				
				sameItem = getOutputItem().getItem().equals(result.getItem());
				
				if(result.getItem().isEdible())
					hasRecipe = true;
			}
		}
		
		return hasFuel() && (getOutputItem().isEmpty() || sameItem) && hasRecipe;
	}
	
	private boolean hasFuel()
	{
		//if (!this.hasLevel()) return false;
		
		final Block blockUnderneath = level.getBlockState(worldPosition.below()).getBlock();
		
		return blockUnderneath instanceof CampfireBlock || blockUnderneath.equals(ModBlocks.HOT_HOPPER.get());
	}
	
	private void bake()
	{	
		final Optional<GrillRecipe> recipe = getRecipe();
		
		recipe.ifPresent(iRecipe -> 
			{
				final ItemStack outputStack = iRecipe.assemble(new Inventory(getInputItem()));
				
				input.extractItem(0, 1, false);
				output.insertItem(0, outputStack, false);
			});

		
		if(!recipe.isPresent())
		{
			final Optional<SmokingRecipe> campfireRecipe = getCampfireRecipe();
			
			campfireRecipe.ifPresent(iRecipe ->
				{
					final ItemStack outputStack = iRecipe.assemble(new Inventory(getInputItem()));
					
					input.extractItem(0, 1, false);
					output.insertItem(0, outputStack, false);
				});
		}
	}
	
	
	//<=================================================DATA=METHODS=================================================>
	
	@Override
	public void load(final BlockState state, final CompoundNBT nbt) {
		super.load(state, nbt);
		
		if(nbt.contains(TIME_TAG))
			burnTime = nbt.getShort(TIME_TAG);
		else
			burnTime = 0;
		
		if (nbt.contains(INPUT_TAG, NBTIDs.COMPOUND))
		{
			input.deserializeNBT(nbt.getCompound(INPUT_TAG));;
		}
		
		if(nbt.contains(OUTPUT_TAG, NBTIDs.COMPOUND))
		{
			output.deserializeNBT(nbt.getCompound(OUTPUT_TAG));
		}
	}
	
	@Override
	public CompoundNBT save(final CompoundNBT nbt) {
		super.save(nbt);
		
		nbt.put(INPUT_TAG, input.serializeNBT());
		nbt.put(OUTPUT_TAG, output.serializeNBT());
		
		nbt.putShort(TIME_TAG, burnTime);
		
		return nbt;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(final Capability<T> cap, final Direction side)
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && cap != null && side != null)
		{
			if(side == Direction.DOWN)
				return handlerOutput.cast();
			else
				return handlerInput.cast();
		}
			

		return super.getCapability(cap, side);
	}
}
