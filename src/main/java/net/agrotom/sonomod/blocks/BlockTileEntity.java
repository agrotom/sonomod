package net.agrotom.sonomod.blocks;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.shapes.VoxelShape;

public abstract class BlockTileEntity<T extends TileEntity> extends Block {

	public BlockTileEntity(final Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

}
