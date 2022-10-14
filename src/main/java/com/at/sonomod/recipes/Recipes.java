package com.at.sonomod.recipes;

import com.at.sonomod.blocks.ModBlocks;
import com.at.sonomod.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack; 

public class Recipes {
    public static void registerFurnace(){
        GameRegistry.addSmelting(new ItemStack(ModBlocks.TUNGSTEN_ORE, 1), new ItemStack(ModItems.TUNGSTEN_INGOT, 1), 1.0f);
    }
}
