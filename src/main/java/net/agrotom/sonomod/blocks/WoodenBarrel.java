package net.agrotom.sonomod.blocks;

import net.agrotom.sonomod.tileentity.WoodenBarrelTileEntity;
import net.agrotom.sonomod.util.ATUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class WoodenBarrel extends Block
{
	private static final VoxelShape INSIDE = box(1.0D, 1.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	protected static final VoxelShape SHAPE = VoxelShapes.join(VoxelShapes.block(),
			VoxelShapes.or(box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), INSIDE), IBooleanFunction.ONLY_FIRST).optimize();
	
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
	
	public void entityInside(BlockState state, World level, BlockPos pos, Entity entity) {
	      int i = state.getValue(FLUID_LEVEL);
	      float f = (float)pos.getY() + (6.0F + (float)(3 * i)) / 16.0F;
	      if (!level.isClientSide && entity.isOnFire() && i > 0 && entity.getY() <= (double)f) {
	    	  entity.clearFire();
	      }

	   }
	
	@Override
	public void handleRain(final World level, final BlockPos pos) {
	      if (level.random.nextInt(20) == 1) {
	         float f = level.getBiome(pos).getTemperature(pos);
	         if (!(f < 0.15F)) {
	            final BlockState blockstate = level.getBlockState(pos);
	            if (blockstate.getValue(FLUID_LEVEL) < 4) {
	            	level.setBlock(pos, blockstate.cycle(FLUID_LEVEL), 2);
	            }

	         }
	      }
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
	public void onPlace(BlockState state, World level, BlockPos pos, BlockState state2,
			boolean p_220082_5_)
	{
		if(!level.isClientSide)
		{		
			final TileEntity blockEntity = level.getBlockEntity(pos);
			
			if(blockEntity instanceof WoodenBarrelTileEntity)
			{
				((WoodenBarrelTileEntity) blockEntity).updateState();
			}
		}
	}
	
	@Override
	public BlockState getStateForPlacement(final BlockItemUseContext useContext)
	{
		int waterLevel = 0;
		
		final TileEntity blockEntity = useContext.getLevel().getBlockEntity(useContext.getClickedPos().above());
		
		if(blockEntity instanceof WoodenBarrelTileEntity)
		{
			waterLevel = ((WoodenBarrelTileEntity) blockEntity).getWaterLevel();
			ATUtils.logToAllPlayers(useContext.getLevel(), waterLevel);
		}
		
		return super.getStateForPlacement(useContext).setValue(FLUID_LEVEL, Integer.valueOf(waterLevel));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos,
			ISelectionContext context)
	{
		return SHAPE;
	}
	
	@Override
	public VoxelShape getInteractionShape(BlockState state, IBlockReader level, BlockPos pos)
	{
		return INSIDE;
	}
	
}
