package net.agrotom.sonomod.client.render.color;

import net.agrotom.sonomod.blocks.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModColorBlocks
{

	public static void register()
	{
		final BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		
		blockColors.register((state, world, pos, tintIndex) ->{
			return state != null && world != null ? BiomeColors.getAverageWaterColor(world, pos) : -1;
		}, ModBlocks.WOODEN_BARREL.get());
	}
	
}
