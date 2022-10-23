package com.at.sonomod.blocks;

import com.at.sonomod.SonoMod;
import com.at.sonomod.blocks.tileentity.BlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public abstract class BaseModelBlock<T extends TileEntity> extends BlockTileEntity<T>
{
	protected boolean usesMinecraftTexture = false;
	
	/**
	 * 
	 * @param textureLocation - set null to set default texture path.
	 */
	protected BaseModelBlock(String name, Material materialIn, SoundType sound) {
		super(name, materialIn, sound);
		
		ModBlocks.customModelBlocks.add(this);
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	 public ResourceLocation getTextureLocation()
    {
		if(usesMinecraftTexture)
			return new ResourceLocation("minecraft:textures/blocks/" + getTextureName() + ".png");
		else
			return new ResourceLocation(SonoMod.MODID, "textures/models/" + getName() + ".png");
    }
}
