package net.agrotom.sonomod.blocks;

import java.util.Set;

import com.google.common.base.Supplier;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.items.ModItems;
import net.agrotom.sonomod.mixins.access.EntityTypeAccess;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = 
			DeferredRegister.create(ForgeRegistries.BLOCKS, SonoMod.MOD_ID);
	
	public static final RegistryObject<Block> GRILL_BLOCK = registerBlock("grill_block",
			() -> new GrillBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).noOcclusion().strength(2.0F, 6.0F)
					.sound(SoundType.STONE).harvestLevel(0).harvestTool(ToolType.PICKAXE)
					.requiresCorrectToolForDrops()), ItemGroup.TAB_DECORATIONS);
	
	public static final RegistryObject<Block> WOODEN_BARREL = registerBlock("wooden_barrel",
			() -> new WoodenBarrel(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.0F, 1.0F).noOcclusion()
					.sound(SoundType.WOOD)), ItemGroup.TAB_DECORATIONS);
	
	public static final RegistryObject<Block> ONYX_ORE = registerBlock("onyx_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(4.0F, 6.0F)
					.sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)
					.requiresCorrectToolForDrops()), ItemGroup.TAB_BUILDING_BLOCKS);
	
	public static final RegistryObject<Block> TUNGSTEN_ORE = registerBlock("tungsten_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(8.0F, 8.0F)
					.sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE)
					.requiresCorrectToolForDrops()), ItemGroup.TAB_BUILDING_BLOCKS);
	
	public static final RegistryObject<Block> HOT_HOPPER = registerBlock("hot_hopper",
			() -> {
				final Block block = new HopperBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE)
						.requiresCorrectToolForDrops().strength(3.0F, 4.8F).sound(SoundType.METAL).noOcclusion());
						
				EntityTypeAccess builderAccess = (EntityTypeAccess) TileEntityType.HOPPER;
		        Set<Block> validBlocks = new ObjectOpenHashSet<>(builderAccess.getValidBlocks());
		        validBlocks.add(block);
		        builderAccess.setValidBlocks(validBlocks);
				
				return block;
			}, ItemGroup.TAB_DECORATIONS);
	
	@SuppressWarnings("unused")
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> block)
	{
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		
		registerBlockItem(name, toReturn);
		
		return toReturn;
	}
	
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> block, final ItemGroup itemGroup)
	{
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		
		registerBlockItem(name, toReturn, itemGroup);
		
		return toReturn;
	}
	
	private static <T extends Block> void registerBlockItem(final String name, final RegistryObject<T> block)
	{
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties()));
	}
	
	private static <T extends Block> void registerBlockItem(final String name, final RegistryObject<T> block, final ItemGroup itemGroup)
	{
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().tab(itemGroup)));
	}
	
	public static void register(final IEventBus eventBus)
	{
		BLOCKS.register(eventBus);
	}
}
