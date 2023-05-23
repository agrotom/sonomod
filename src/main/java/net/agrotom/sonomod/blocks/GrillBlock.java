package net.agrotom.sonomod.blocks;

import net.agrotom.sonomod.tileentity.GrillTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class GrillBlock extends Block implements IWaterLoggable
{

	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty ASSEMBLE = BooleanProperty.create("assemble");

	private static final VoxelShape SHAPE = VoxelShapes
			.or(VoxelShapes.box(0, 0, 0, 1, 0.13, 1), VoxelShapes.box(0, 0, 0, 1, 0.13, 1)).optimize();

	public GrillBlock(Properties properties)
	{
		super(properties);

		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ASSEMBLE, false)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult raytrace)
	{

		if (!level.isClientSide)
		{
			final GrillTileEntity te = (GrillTileEntity) level.getBlockEntity(pos);

			if (te != null)
				te.operate(player, raytrace.getLocation());
		}

		return ActionResultType.SUCCESS;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new GrillTileEntity();
	}

	@Override
	public BlockState getStateForPlacement(final BlockItemUseContext useContext)
	{
		final BlockPos clickedPos = useContext.getClickedPos();
		final FluidState fluidState = useContext.getLevel().getFluidState(clickedPos);

		return super.getStateForPlacement(useContext)
				.setValue(FACING, useContext.getHorizontalDirection().getOpposite()).setValue(ASSEMBLE, false)
				.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot)
	{
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror)
	{
		return rotate(state, mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public void onPlace(BlockState state, World level, BlockPos pos, BlockState state2, boolean p_220082_5_)
	{
		if (!level.isClientSide)
		{
			level.setBlock(pos, state.setValue(ASSEMBLE, canAssemble(level, pos)), 3);
		}
	}

	private boolean canAssemble(final IWorld level, final BlockPos pos)
	{
		final Block block = level.getBlockState(pos.below()).getBlock();
		
		return block instanceof CampfireBlock;
	}

	@Override
	public void playerDestroy(World level, PlayerEntity player, BlockPos pos, BlockState state, TileEntity tile,
			ItemStack stack)
	{
		if (tile instanceof GrillTileEntity)
			InventoryHelper.dropContents(level, pos, ((GrillTileEntity) tile).getItemsAsInventory());

		super.playerDestroy(level, player, pos, state, tile, stack);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction p_196271_2_, BlockState state2, IWorld level,
			BlockPos pos, BlockPos pos2)
	{
		if (!level.isClientSide())
		{
			level.setBlock(pos, state.setValue(ASSEMBLE, canAssemble(level, pos)), 3);
		}

		return state;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state)
	{
		state.add(FACING, ASSEMBLE, WATERLOGGED);
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state)
	{
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
}
