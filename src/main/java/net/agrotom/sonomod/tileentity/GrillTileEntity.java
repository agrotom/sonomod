package net.agrotom.sonomod.tileentity;

import java.util.Optional;

import net.agrotom.sonomod.blocks.GrillBlock;
import net.agrotom.sonomod.data.recipes.GrillRecipe;
import net.agrotom.sonomod.data.recipes.ModRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CampfireCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class GrillTileEntity extends TileEntity implements ITickableTileEntity {
	
	private final ItemStackHandler items = createHandler();
	private final LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> items);
	
	private short burnTime = 0;
	
	private final String INV_TAG = "inventory";
	private final String TIME_TAG = "time";
	
	private ItemStackHandler createHandler()
	{
		return new ItemStackHandler(2) {
			@Override
			protected void onContentsChanged(int slot)
			{
				if(!level.isClientSide)
				{
					markUpdate();
				}
			}
			
			@Override
			public boolean isItemValid(int slot, ItemStack stack)
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
		final Direction dir = getBlockState().getValue(GrillBlock.FACING);
		final double axis = dir.getAxis() == Axis.X ? sub.x : sub.z;
		final double offset = Math.abs(axis) - Math.floor(Math.abs(axis));
		boolean isReverse = dir == Direction.NORTH || dir == Direction.WEST;
		
		if(((!isReverse && offset > 0.5d) || (isReverse && offset <= 0.5d)) && hasBakedItem())
		{
			pullItem(1, player);
		}
		else if((!isReverse && offset <= 0.5d) || (isReverse && offset > 0.5d))
		{
			final ItemStack hand = player.getMainHandItem();
			
			if(hasItem())
			{
				ItemStack item = items.getStackInSlot(0);
				
				if(!hand.isEmpty() && item.getItem().equals(hand.getItem()) && item.getCount() < 64)
				{
					final ItemStack handCopy = hand.copy();
					handCopy.setCount(1);
					
					items.insertItem(0, handCopy, false);
					hand.setCount(hand.getCount() - 1);
				}
				else
				{
					pullItem(0, player);
				}
			}
			else if(!hand.isEmpty())
			{
				final ItemStack copy = hand.copy();
				copy.setCount(1);
				
				items.insertItem(0, copy, false);
				
				hand.setCount(hand.getCount() - 1);
			}
		}
	}
	
	@Override
	public void tick() {
		if (!this.hasLevel()) return;
		
		World world = this.getLevel();
		
		if (world.isClientSide) return;
		
		@SuppressWarnings("unused")
		ServerWorld serverWorld = (ServerWorld)world;
		
		if(!serverWorld.isClientSide)
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
		
		for(int i = 0; i < items.getSlots(); i++)
			inventory.setItem(i, items.getStackInSlot(i).copy());
		
		return inventory;
	}
	
	public ItemStack[] getItems()
	{
		return new ItemStack[]{ items.getStackInSlot(0), items.getStackInSlot(1) };
	}
	
	//<=================================================SERVICE=METHODS=================================================>
	
	private Optional<GrillRecipe> getRecipe()
	{
		final IInventory inventory = new Inventory(1);
		
		inventory.setItem(0, items.getStackInSlot(0));
		
		return level.getRecipeManager().getRecipeFor(ModRecipeTypes.GRILL_RECIPE, inventory, level);
	}
	
	private Optional<CampfireCookingRecipe> getCampfireRecipe()
	{
		final IInventory inventory = new Inventory(1);
		
		inventory.setItem(0, items.getStackInSlot(0));
		
		return level.getRecipeManager().getRecipeFor(IRecipeType.CAMPFIRE_COOKING, inventory, level);
	}
	
	
	private void pullItem(final int slotIndex, final PlayerEntity player)
	{
		final ItemStack item = items.extractItem(slotIndex, 64, false);
		
		if(!player.inventory.add(item))
			InventoryHelper.dropContents(this.level, this.getBlockPos(), new Inventory(item));
		else
			player.inventoryMenu.broadcastChanges();
	}
	
	
	private boolean hasBakedItem()
	{
		return !items.getStackInSlot(1).isEmpty();
	}
	
	private boolean hasItem()
	{
		return !items.getStackInSlot(0).isEmpty();
	}
	
	
	private boolean canBake()
	{
		final Optional<GrillRecipe> recipe = getRecipe();
		
		boolean sameItem = true;
		boolean hasRecipe = false;
		
		if(recipe.isPresent())
		{
			final ItemStack result = recipe.get().getResultItem();
			
			sameItem = items.getStackInSlot(1).getItem().equals(result.getItem());
			
			hasRecipe = true;
		}
		else
		{
			final Optional<CampfireCookingRecipe> campfireRecipe = getCampfireRecipe();
			
			if(campfireRecipe.isPresent())
			{
				final ItemStack result = campfireRecipe.get().getResultItem();
				
				sameItem = items.getStackInSlot(1).getItem().equals(result.getItem());
				
				if(result.getItem().isEdible())
					hasRecipe = true;
			}
		}
		
		return level.getBlockState(worldPosition.below()).getBlock() instanceof CampfireBlock && (items.getStackInSlot(1).isEmpty() || sameItem) && hasRecipe;
	}
	
	private void bake()
	{	
		final Optional<GrillRecipe> recipe = getRecipe();
		
		recipe.ifPresent(iRecipe -> 
			{
				final ItemStack output = iRecipe.getResultItem();
				
				items.extractItem(0, 1, false);
				items.insertItem(1, output, false);
				
				setChanged();
			});
		
		if(!recipe.isPresent())
		{
			final Optional<CampfireCookingRecipe> campfireRecipe = getCampfireRecipe();
			
			campfireRecipe.ifPresent(iRecipe ->
				{
					final ItemStack output = iRecipe.getResultItem();
					
					items.extractItem(0, 1, false);
					items.insertItem(1, output, false);
					
					setChanged();
				});
		}
	}
	
	
	//<=================================================DATA=METHODS=================================================>
	
	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag)
	{
		this.load(state, tag);
	}
	
	@Override
	public CompoundNBT getUpdateTag()
	{
		final CompoundNBT nbt = new CompoundNBT();

		save(nbt);
		
		return nbt;
	}
	
	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		
		if(nbt.contains(TIME_TAG))
			burnTime = nbt.getShort(TIME_TAG);
		else
			burnTime = 0;
		
		if(nbt.contains(INV_TAG))
			items.deserializeNBT(nbt.getCompound(INV_TAG));
		else
		{
			items.setStackInSlot(0, ItemStack.EMPTY);
			items.setStackInSlot(1, ItemStack.EMPTY);
		}
		
		final int NBT_INT_ID = 10;
		if (nbt.contains(INV_TAG, NBT_INT_ID)) {
			items.deserializeNBT(nbt.getCompound(INV_TAG));;
		}
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		
		nbt.put(INV_TAG, items.serializeNBT());
		
		nbt.putShort(TIME_TAG, burnTime);
		
		return nbt;
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		final CompoundNBT nbtTag = new CompoundNBT();
		
		this.save(nbtTag);
		
	    return new SUpdateTileEntityPacket(this.worldPosition, -1, nbtTag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.load(level.getBlockState(pkt.getPos()), pkt.getTag());
	}
	
	private void markUpdate()
	{
		setChanged();
		level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();
		}
		
		return super.getCapability(cap, side);
	}
}
