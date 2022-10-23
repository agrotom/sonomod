package com.at.sonomod.blocks;

import java.util.ArrayList;
import java.util.List;

import com.at.sonomod.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
    public static List<Block> blocks = new ArrayList<>();
    public static List<BaseModelBlock<?>> customModelBlocks = new ArrayList<>();

    public static final Block TUNGSTEN_ORE = new BlockBase("tungsten_ore", Material.ground, Block.soundTypeStone);
    public static final Block ONYX_ORE = new BaseOre("onyx_ore", 4.5f, 3, ModItems.ONYX);
    public static final Block COUNTER = new BlockCounter("counter", Material.ground, Block.soundTypeStone);
    
    public static final Block GRILL = new GrillBlock("grill", Material.rock, Block.soundTypeStone);
    public static final Block BARREL = new BarrelBlock("barrel", Material.wood, Block.soundTypeWood);
}
