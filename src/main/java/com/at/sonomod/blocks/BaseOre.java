package com.at.sonomod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BaseOre extends BlockBase {
    protected Item itemToDrop = Item.getItemFromBlock(this);

    public BaseOre(String name, float hardness, int harvestLevel) {
        super(name, Material.rock, Block.soundTypeStone);
        this.setHardness(hardness);
        this.setHarvestLevel("pickaxe", harvestLevel);
    }

    public BaseOre(String name, float hardness, int harvestLevel, Item itemToDrop) {
        this(name, hardness, harvestLevel);
        this.itemToDrop = itemToDrop;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return itemToDrop;
    }
}
