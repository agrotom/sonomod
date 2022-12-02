package net.agrotom.sonomod.data.recipes;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.blocks.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface IGrillRecipe extends IRecipe<IInventory>
{

	ResourceLocation TYPE_ID = new ResourceLocation(SonoMod.MOD_ID, "grill");
	
	@Override
	default boolean canCraftInDimensions(int width, int height)
	{
		return true;
	}

	@Override
	default boolean isSpecial()
	{
		return true;
	}
	
	@Override
	default ItemStack getToastSymbol()
	{
		return new ItemStack(ModBlocks.GRILL_BLOCK.get());
	}

	@Override
	default IRecipeType<?> getType()
	{
		return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
	}

}
