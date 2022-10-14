package com.at.sonomod.items;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static List<Item> items = new ArrayList<Item>();

    public static final Item TUNGSTEN_INGOT = new ItemMaterial("tungsten_ingot");
    public static final Item ONYX = new ItemMaterial("onyx");
    public static final Item GUIDEBOOK = new GuideBook("guidebook");
}
