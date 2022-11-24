package net.agrotom.sonomod.blocks;

import com.google.common.base.Supplier;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.items.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = 
			DeferredRegister.create(ForgeRegistries.BLOCKS, SonoMod.MOD_ID);
	
	public static final RegistryObject<Block> GRILL_BLOCK = registerBlock("grill_block",
			() -> new GrillBlock(AbstractBlock.Properties.of(Material.STONE).noOcclusion().strength(2.0F, 6.0F)
					.sound(SoundType.STONE).harvestLevel(0).harvestTool(ToolType.PICKAXE)
					.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> ONYX_ORE = registerBlock("onyx_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(4.0F, 6.0F)
					.sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)
					.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TUNGSTEN_ORE = registerBlock("tungsten_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(8.0F, 8.0F)
					.sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE)
					.requiresCorrectToolForDrops()));
	
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> block)
	{
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		
		registerBlockItem(name, toReturn);
		
		return toReturn;
	}
	
	private static <T extends Block> void registerBlockItem(final String name, final RegistryObject<T> block)
	{
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties()));
	}
	
	public static void register(final IEventBus eventBus)
	{
		BLOCKS.register(eventBus);
	}
}
