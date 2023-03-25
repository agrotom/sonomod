package net.agrotom.sonomod.items.groups;

import net.agrotom.sonomod.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroups
{
	
	public static final ItemGroup MATERIAL_GROUP = new ItemGroup("material_group")
			{
				@Override
				public ItemStack makeIcon()
				{
					return new ItemStack(ModItems.MARUK_SCALES.get());
				}
			};
			
	public static final ItemGroup COMBAT_GROUP = new ItemGroup("combat_group")
			{
				@Override
				public ItemStack makeIcon()
				{
					return new ItemStack(ModItems.SUNKEN_REPTILIAN.get());
				}
			};

}
