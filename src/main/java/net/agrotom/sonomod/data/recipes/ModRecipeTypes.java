package net.agrotom.sonomod.data.recipes;

import net.agrotom.sonomod.SonoMod;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes
{
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
			DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SonoMod.MOD_ID);
	
	public static final RegistryObject<GrillRecipe.Serializer> GRILL_SERIALIZER =
			RECIPE_SERIALIZER.register("grill_cooking", GrillRecipe.Serializer::new);
	
	public static IRecipeType<GrillRecipe> GRILL_RECIPE =
			new GrillRecipe.GrillRecipeType();
	
	public static void register(IEventBus eventBus)
	{
		RECIPE_SERIALIZER.register(eventBus);
		
		Registry.register(Registry.RECIPE_TYPE, GrillRecipe.TYPE_ID, GRILL_RECIPE);
	}
}
