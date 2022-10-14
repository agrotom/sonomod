package com.at.sonomod;

import com.at.sonomod.blocks.ModBlocks;
import com.at.sonomod.blocks.gens.OreGen;
import com.at.sonomod.blocks.tileentity.TileEntityCounter;
import com.at.sonomod.items.ModItems;
import com.at.sonomod.recipes.Recipes;
import com.at.sonomod.renderer.GrillRenderer;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.item.Item;

@Mod(modid = SonoMod.MODID, version = SonoMod.VERSION)
public class SonoMod
{
    public static final String MODID = "sonomod";
    public static final String VERSION = "1.0";

    public static final String BLANK = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum" +
                                       "volutpat dui ante, eu ultricies sapien aliquam a. Sed pharetra urna" +
                                       "nec arcu condimentum, eu" +
                                       "pellentesque tortor pellentesque. Vestibulum ut mollis ligula.";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        for(Item item : ModItems.items)
            GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));

        for(Block block : ModBlocks.blocks)
            GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
        
        TileEntitySpecialRenderer renderGrill = new GrillRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCounter.class, renderGrill);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(new OreGen(ModBlocks.TUNGSTEN_ORE, 6, 5, 40, 20),0);
        GameRegistry.registerWorldGenerator(new OreGen(ModBlocks.ONYX_ORE, 3, 5, 15, 5),0);
        Recipes.registerFurnace();
    }
}
