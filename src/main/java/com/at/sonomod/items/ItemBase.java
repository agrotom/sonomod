package com.at.sonomod.items;

import com.at.sonomod.SonoMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase(String name){
        this.setUnlocalizedName(name);
        ModItems.items.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        this.itemIcon = register.registerIcon(SonoMod.MODID + ':' + this.getUnlocalizedName().substring(5));
    }
}
