package net.agrotom.sonomod.data.recipes;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import net.agrotom.sonomod.blocks.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class GrillRecipe implements IGrillRecipe
{
	
	private final ResourceLocation id;
	private final ItemStack output;
	private final Ingredient recipeItem;
	private final int burnTime;
	
	public GrillRecipe(final ResourceLocation id, final ItemStack output,
			final Ingredient recipeItem, final int burnTime)
	{
		this.id = id;
		this.output = output;
		this.recipeItem = recipeItem;
		this.burnTime = burnTime;
	}
	
	public Ingredient getIngredient()
	{
		return recipeItem;
	}
	
	public int getBurnTime()
	{
		return burnTime;
	}
	
	@Override
	public boolean matches(IInventory inventory, World level)
	{
		return recipeItem.test(inventory.getItem(0));
	}

	@Override
	public ItemStack assemble(IInventory inventory)
	{
		return output.copy();
	}

	@Override
	public ItemStack getResultItem()
	{
		return output;
	}

	public ItemStack getIcon()
	{
		return new ItemStack(ModBlocks.GRILL_BLOCK.get());
	}
	
	@Override
	public ResourceLocation getId()
	{
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return ModRecipeTypes.GRILL_SERIALIZER.get();
	}

	public static class GrillRecipeType implements IRecipeType<GrillRecipe>
	{
		@Override
		public String toString()
		{
			return GrillRecipe.TYPE_ID.toString();
		}
	}
	
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<GrillRecipe>
	{

		@Override
		public GrillRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
			
			final JsonObject ingredient = JSONUtils.getAsJsonObject(json, "ingredient");
			
			Ingredient input = Ingredient.EMPTY;
			
			input = Ingredient.fromJson(ingredient);
			
			final int time = JSONUtils.getAsInt(json, "burnTime");
			
			return new GrillRecipe(recipeId, output, input, time);
		}

		@Nullable
		@Override
		public GrillRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer)
		{
			final int time = buffer.readInt();
			
			Ingredient input = Ingredient.EMPTY;
			
			input = Ingredient.fromNetwork(buffer);
			
			final ItemStack output = buffer.readItem();
			
			return new GrillRecipe(recipeId, output, input, time);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, GrillRecipe recipe)
		{
			buffer.writeInt(recipe.getBurnTime());
			
			recipe.getIngredient().toNetwork(buffer);
			
			buffer.writeItemStack(recipe.getResultItem(), false);
		}
		
	}
}
