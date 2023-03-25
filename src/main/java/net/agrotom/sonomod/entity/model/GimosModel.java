package net.agrotom.sonomod.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.agrotom.sonomod.entity.GimosEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class GimosModel <T extends GimosEntity> extends EntityModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer t1;
	private final ModelRenderer t2;
	private final ModelRenderer t3;
	private final ModelRenderer t4;
	
	private final float animationSpeed = 0.2f;
	private final float animationLimit = 0.5f;

	public GimosModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 16.0F, 0.0F);
		head.texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-2.0F, -2.0F, -9.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);

		t1 = new ModelRenderer(this);
		t1.setPos(3.0F, 3.0F, 8.0F);
		head.addChild(t1);
		t1.texOffs(0, 32).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);

		t2 = new ModelRenderer(this);
		t2.setPos(-5.0F, 3.0F, 8.0F);
		head.addChild(t2);
		t2.texOffs(0, 32).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);

		t3 = new ModelRenderer(this);
		t3.setPos(-2.0F, -3.0F, 8.0F);
		head.addChild(t3);
		t3.texOffs(0, 32).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);

		t4 = new ModelRenderer(this);
		t4.setPos(4.0F, -4.0F, 8.0F);
		head.addChild(t4);
		t4.texOffs(0, 32).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		t1.xRot = MathHelper.cos(limbSwing * animationSpeed) * animationLimit * limbSwingAmount;
		t2.xRot = MathHelper.cos(limbSwing * animationSpeed + (float)Math.PI) * animationLimit * limbSwingAmount;
		t3.xRot = MathHelper.cos(limbSwing * animationSpeed) * animationLimit * limbSwingAmount;
		t4.xRot = MathHelper.cos(limbSwing * animationSpeed + (float)Math.PI) * animationLimit * limbSwingAmount;
		
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
	    this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}