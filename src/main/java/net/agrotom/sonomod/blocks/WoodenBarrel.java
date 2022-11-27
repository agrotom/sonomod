package net.agrotom.sonomod.blocks;

import net.agrotom.sonomod.tileentity.WoodenBarrelTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class WoodenBarrel extends Block
{

	public static final IntegerProperty FLUID_LEVEL = IntegerProperty.create("fluid_level", 0, 4);
	
	public WoodenBarrel(final Properties properties)
	{
		super(properties);
		
		this.registerDefaultState(this.stateDefinition.any().setValue(FLUID_LEVEL, Integer.valueOf(0)));
	}
	
	@Override
	public ActionResultType use(final BlockState state, final World level, final BlockPos pos,
			final PlayerEntity player, final Hand hand, final BlockRayTraceResult ray)
	{
		if(hand == Hand.MAIN_HAND)
		{
				final WoodenBarrelTileEntity tile = (WoodenBarrelTileEntity) level.getBlockEntity(pos);
				
				if(tile != null)
				{
					tile.operate(player, hand);
				}
		}
		
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public boolean hasTileEntity(final BlockState state)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
	{
		return new WoodenBarrelTileEntity();
	}
	
	@Override
	protected void createBlockStateDefinition(final Builder<Block, BlockState> state)
	{
		state.add(FLUID_LEVEL);
	}
	
	@Override
	public BlockState getStateForPlacement(final BlockItemUseContext useContext)
	{
		return super.getStateForPlacement(useContext).setValue(FLUID_LEVEL, Integer.valueOf(0));
	}
	
}
