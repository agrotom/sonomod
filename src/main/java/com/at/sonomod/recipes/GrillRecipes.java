package com.at.sonomod.recipes;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * 
 * Contains all grill recipes.
 * 
 * @author agro_tom
 * 
 */
public class GrillRecipes {
	private static final Map<ItemStack, ItemStack> smeltingRecipes = new HashMap<>();
	private static final Map<ItemStack, Float> expRecipes = new HashMap<>();
	
	private GrillRecipes() {}
	
	public static void addBlockSmelting(final Block block, final ItemStack result, final float xp)
	{
		addItemStackSmelting(new ItemStack(block), result, xp);
	}
	
	public static void addItemSmelting(final Item item, final ItemStack result, final float xp)
	{
		addItemStackSmelting(new ItemStack(item, 1, 32767), result, xp);
	}
	
	public static void addItemStackSmelting(final ItemStack itemStack, final ItemStack result, final float xp)
	{
		smeltingRecipes.put(itemStack, result);
		expRecipes.put(itemStack, xp);
	}
	
	public static Map<ItemStack, ItemStack> getRecipes()
	{
		return smeltingRecipes;
	}
	
	/**
	 * Compares two ItemStacks.
	 */
	public static boolean itemStacksEquals(final ItemStack itemStack, final ItemStack itemStack2)
	{
		if(itemStack == null || itemStack2 == null)
			return false;
		
        return itemStack2.getItem() == itemStack.getItem() && (itemStack2.getMetadata() == 32767 || itemStack2.getMetadata() == itemStack.getMetadata());
	}
	
	/**
	 * Finds result at map by given key (ingredient).
	 * @return null if ItemStack key was not found in map.
	 */
	@Nullable
	public static ItemStack getSmeltingResult(final ItemStack ingredient)
	{
		if(ingredient != null)
			for(Map.Entry<ItemStack, ItemStack> entry : smeltingRecipes.entrySet())
				if(itemStacksEquals(ingredient, entry.getKey()))
					return entry.getValue();
		
		return null;
	}
	
	// Put in proxy
	public static void registerRecipes()
	{
		addItemSmelting(Items.porkchop, new ItemStack(Items.cooked_porkchop), 0.7f);
	}
}