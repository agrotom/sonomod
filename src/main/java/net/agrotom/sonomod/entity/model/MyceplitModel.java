package net.agrotom.sonomod.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.agrotom.sonomod.entity.MyceplitEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class MyceplitModel <T extends MyceplitEntity> extends EntityModel<T> {
	private final ModelRenderer leg1;
	private final ModelRenderer leg_r1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg_r2;
	private final ModelRenderer torso;
	private final ModelRenderer torso_m_r1;
	private final ModelRenderer wings;
	private final ModelRenderer wing_r_r1;
	private final ModelRenderer wing_l_r1;
	private final ModelRenderer head;

	public MyceplitModel() {
		texWidth = 64;
		texHeight = 64;

		leg1 = new ModelRenderer(this);
		leg1.setPos(4.0F, 12.0F, 6.0F);
		leg1.texOffs(0, 6).addBox(-1.0F, 12.0F, -11.0F, 3.0F, 0.0F, 6.0F, 0.0F, false);

		leg_r1 = new ModelRenderer(this);
		leg_r1.setPos(0.0F, 12.0F, -5.0F);
		leg1.addChild(leg_r1);
		setRotationAngle(leg_r1, -0.3927F, 0.0F, 0.0F);
		leg_r1.texOffs(39, 0).addBox(-1.0F, -15.0F, 0.0F, 3.0F, 15.0F, 0.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-4.0F, 12.0F, 6.0F);
		leg2.texOffs(0, 0).addBox(-2.0F, 12.0F, -11.0F, 3.0F, 0.0F, 6.0F, 0.0F, false);

		leg_r2 = new ModelRenderer(this);
		leg_r2.setPos(-1.0F, 12.0F, -5.0F);
		leg2.addChild(leg_r2);
		setRotationAngle(leg_r2, -0.3927F, 0.0F, 0.0F);
		leg_r2.texOffs(0, 0).addBox(-1.0F, -15.0F, 0.0F, 3.0F, 15.0F, 0.0F, 0.0F, false);

		torso = new ModelRenderer(this);
		torso.setPos(0.0F, 11.0F, 2.0F);
		torso.texOffs(28, 32).addBox(-3.0F, -11.0F, -10.0F, 6.0F, 11.0F, 4.0F, 0.0F, false);

		torso_m_r1 = new ModelRenderer(this);
		torso_m_r1.setPos(0.0F, 0.0F, 0.0F);
		torso.addChild(torso_m_r1);
		setRotationAngle(torso_m_r1, 0.0873F, 0.0F, 0.0F);
		torso_m_r1.texOffs(0, 0).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 7.0F, 15.0F, 0.0F, false);

		wings = new ModelRenderer(this);
		wings.setPos(0.0F, 0.0F, 0.0F);
		torso.addChild(wings);
		

		wing_r_r1 = new ModelRenderer(this);
		wing_r_r1.setPos(-5.0F, -3.0F, -4.0F);
		wings.addChild(wing_r_r1);
		setRotationAngle(wing_r_r1, -0.2657F, -0.1685F, 0.0456F);
		wing_r_r1.texOffs(0, 23).addBox(-0.8652F, -3.1022F, -0.2353F, 0.0F, 6.0F, 13.0F, 0.0F, false);

		wing_l_r1 = new ModelRenderer(this);
		wing_l_r1.setPos(7.0F, -3.0F, -4.0F);
		wings.addChild(wing_l_r1);
		setRotationAngle(wing_l_r1, -0.2657F, 0.1685F, -0.0456F);
		wing_l_r1.texOffs(24, 9).addBox(-1.1348F, -3.1022F, -0.2353F, 0.0F, 6.0F, 13.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, -5.0F);
		head.texOffs(0, 22).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 6.0F, 8.0F, 0.0F, false);
		head.texOffs(32, 28).addBox(-3.0F, 0.0F, -10.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);
	}
	
	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick)
	{
		this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.wing_l_r1.yRot = MathHelper.sin(limbSwing * 0.6662F) * 0.4F * limbSwingAmount + ((float)Math.PI/12);
        this.wing_r_r1.yRot = MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F * limbSwingAmount - ((float)Math.PI/12);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
	    this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	    this.wing_l_r1.yRot = MathHelper.sin(limbSwing * 0.6662F) * 0.4F * limbSwingAmount + ((float)Math.PI/12);
	    this.wing_r_r1.yRot = MathHelper.sin(limbSwing * 0.6662F) * 0.4F * limbSwingAmount - ((float)Math.PI/12);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}