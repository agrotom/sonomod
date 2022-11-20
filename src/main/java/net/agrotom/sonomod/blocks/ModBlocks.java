package net.agrotom.sonomod.blocks;

import com.google.common.base.Supplier;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.items.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SonoMod.MOD_ID);
	
	public static final RegistryObject<Block> GRILL_BLOCK = registerBLock("grill_block",
			() -> new GrillBlock(AbstractBlock.Properties.of(Material.STONE).noOcclusion()));
	
	private static <T extends Block> RegistryObject<T> registerBLock(String name, Supplier<T> block)
	{
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		
		registerBlockItem(name, toReturn);
		
		return toReturn;
	}
	
	private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
	{
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	}
	
	public static void register(IEventBus eventBus)
	{
		BLOCKS.register(eventBus);
	}
}
