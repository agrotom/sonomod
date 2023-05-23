package net.agrotom.sonomod.items;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.items.groups.ModGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SonoMod.MOD_ID);
	
	public static final RegistryObject<Item> ONYX = ITEMS.register("onyx",
            () -> new Item(new Item.Properties().tab(ModGroups.MATERIAL_GROUP)));
	
	public static final RegistryObject<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot",
            () -> new Item(new Item.Properties().tab(ModGroups.MATERIAL_GROUP)));
	
	public static final RegistryObject<Item> TUNGSTEN_NUGGET = ITEMS.register("tungsten_nugget",
			() -> new Item(new Item.Properties().tab(ModGroups.MATERIAL_GROUP)));
	
	public static final RegistryObject<Item> MARUK_SCALES = ITEMS.register("maruk_scales",
			() -> new Item(new Item.Properties().tab(ModGroups.MATERIAL_GROUP)));
	
	public static final RegistryObject<Item> MYCEPLIT_FEATHER = ITEMS.register("myceplit_feather",
			() -> new Item(new Item.Properties().tab(ModGroups.MATERIAL_GROUP)));
	
	public static final RegistryObject<Item> SUNKEN_REPTILIAN = ITEMS.register("sunken_reptilian",
			() -> new SwordItem(ItemTier.DIAMOND, 7, 3.1F, new Item.Properties().rarity(Rarity.RARE).tab(ModGroups.COMBAT_GROUP)));

	
	public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
