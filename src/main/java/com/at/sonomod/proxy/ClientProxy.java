package com.at.sonomod.proxy;

import org.lwjgl.opengl.GL11;

import com.at.sonomod.SonoMod;
import com.at.sonomod.blocks.ModBlocks;
import com.at.sonomod.blocks.tileentity.GrillTileEntity;
import com.at.sonomod.renderer.RenderItemBlock;
import com.at.sonomod.renderer.RenderTileEntity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ClientProxy extends CommonProxy {
	public static int[] displayList = new int[1];//for 1 model

	public void preInit() {
		super.preInit();
	}

	public void init() {

		super.init();
		
		final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(SonoMod.MODID, "models/grill.obj"));

		displayList[0] = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayList[0], GL11.GL_COMPILE);
		model.renderAll();
		GL11.glEndList();

		//Blocks
		GameRegistry.registerTileEntity(GrillTileEntity.class, "grill");
		ClientRegistry.bindTileEntitySpecialRenderer(GrillTileEntity.class, new RenderTileEntity());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.GRILL), new RenderItemBlock());

	}
}
