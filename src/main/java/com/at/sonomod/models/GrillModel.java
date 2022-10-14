package com.at.sonomod.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GrillModel extends ModelBase {
	private final ModelRenderer bb_main;

	public GrillModel() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -1.0F, -6.0F, 12, 1, 12, 0.0F));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -2.0F, -6.0F, 1, 1, 12, 0.0F));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 5.0F, -2.0F, -6.0F, 1, 1, 12, 0.0F));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -2.0F, -6.0F, 10, 1, 1, 0.0F));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -2.0F, 5.0F, 10, 1, 1, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}
	
	public void renderModel(float f5)
	{
		bb_main.render(f5);
	}
}