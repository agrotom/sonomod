package com.at.sonomod.blocks;

import com.at.sonomod.SonoMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockBase extends Block {
    public BlockBase(String name, Material material, SoundType soundType) {
        super(material);
        setUnlocalizedName(name);
        setStepSound(soundType);
        setTextureName(SonoMod.MODID + ":" + name);

        ModBlocks.blocks.add(this);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(SonoMod.MODID + ':' + this.getUnlocalizedName().substring(5));
    }
}
