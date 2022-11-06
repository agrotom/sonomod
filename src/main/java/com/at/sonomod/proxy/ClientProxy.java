package com.at.sonomod.proxy;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.at.sonomod.SonoMod;
import com.at.sonomod.blocks.BaseModelBlock;
import com.at.sonomod.blocks.ModBlocks;
import com.at.sonomod.recipes.GrillRecipes;
import com.at.sonomod.renderer.RenderItemBlock;
import com.at.sonomod.renderer.RenderTileEntity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ClientProxy extends CommonProxy {
	public int[] displayList = new int[ModBlocks.customModelBlocks.size()];//for 1 model

	public void preInit() {
		super.preInit();
		
		registerModels();
		GrillRecipes.registerRecipes();
	}

	public void init() {

		super.init();
		
		
		
		/*final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(SonoMod.MODID, "models/grill.obj"));
		displayList[0] = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayList[0], GL11.GL_COMPILE);
		model.renderAll();
		GL11.glEndList();
		//Blocks
		GameRegistry.registerTileEntity(GrillTileEntity.class, "grill");
		ClientRegistry.bindTileEntitySpecialRenderer(GrillTileEntity.class, new RenderTileEntity());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.GRILL), new RenderItemBlock());*/

	}
	
	@SideOnly(Side.CLIENT)
	public void registerModels()
	{
		final List<BaseModelBlock<?>> blocks = ModBlocks.customModelBlocks;
		
		for(int i = 0; i < ModBlocks.customModelBlocks.size(); i++)
		{
			final BaseModelBlock<?> block = blocks.get(i);
			final String blockName = blocks.get(i).getName();
			
			final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(SonoMod.MODID, "models/" + blockName + ".obj"));

			displayList[i] = GLAllocation.generateDisplayLists(1);
			GL11.glNewList(displayList[i], GL11.GL_COMPILE);
			model.renderAll();
			GL11.glEndList();

			final RenderTileEntity render = new RenderTileEntity(block.getTextureLocation(), blockName); 
			
			GameRegistry.registerTileEntity(blocks.get(i).getTileEntityClass(), blockName);
			ClientRegistry.bindTileEntitySpecialRenderer(blocks.get(i).getTileEntityClass(), render);
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(blocks.get(i)), new RenderItemBlock(i, render));
		}
	}
}