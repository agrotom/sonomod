package net.agrotom.sonomod.world.gen;

import javax.annotation.Nullable;

import net.agrotom.sonomod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreType
{
	ONYX(Lazy.of(ModBlocks.ONYX_ORE), 4, 10, 20),
	TUNGSTEN(Lazy.of(ModBlocks.TUNGSTEN_ORE), 8, 10, 35);
	
	private final Lazy<Block> block;
	private final int maxVeinSize;
	private final int minHeight;
	private final int maxHeight;
	
	private OreType(final Lazy<Block> block, final int maxVeinSize,
			final int minHeight, final int maxHeight)
	{
		this.block = block;
		this.maxVeinSize = maxVeinSize;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}

	public Lazy<Block> getBlock()
	{
		return block;
	}

	public int getMaxVeinSize()
	{
		return maxVeinSize;
	}

	public int getMinHeight()
	{
		return minHeight;
	}

	public int getMaxHeight()
	{
		return maxHeight;
	}
	
	@Nullable
	public static OreType get(final Block block)
	{
		for(final OreType ore : values())
		{
			if(block == ore.block)
			{
				return ore;
			}
		}
		
		return null;
	}
}
