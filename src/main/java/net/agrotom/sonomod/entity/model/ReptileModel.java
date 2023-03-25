package net.agrotom.sonomod.entity.model;
// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.agrotom.sonomod.entity.ReptileEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ReptileModel <T extends ReptileEntity> extends EntityModel<T> {
	private final ModelRenderer backLegs;
	private final ModelRenderer backLegs2;
	private final ModelRenderer torso;
	private final ModelRenderer tail_r1;
	private final ModelRenderer spike3_r1;
	private final ModelRenderer spike2_r1;
	private final ModelRenderer spike1_r1;
	private final ModelRenderer torso_r1;
	private final ModelRenderer head;

	public ReptileModel() {
		texWidth = 64;
		texHeight = 64;

		backLegs = new ModelRenderer(this);
		backLegs.setPos(0.0F, 15.0F, 10.0F);
		backLegs.texOffs(0, 51).addBox(3.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		backLegs.texOffs(0, 51).addBox(-7.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		backLegs2 = new ModelRenderer(this);
		backLegs2.setPos(0.0F, 11.0F, -5.0F);
		backLegs2.texOffs(0, 47).addBox(4.0F, 0.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, false);
		backLegs2.texOffs(0, 47).addBox(-8.0F, 0.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, false);

		torso = new ModelRenderer(this);
		torso.setPos(0.0F, 11.0F, 2.0F);
		

		tail_r1 = new ModelRenderer(this);
		tail_r1.setPos(0.0F, 2.0F, 11.0F);
		torso.addChild(tail_r1);
		setRotationAngle(tail_r1, -0.6077F, 0.0152F, -0.0054F);
		tail_r1.texOffs(0, 55).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);

		spike3_r1 = new ModelRenderer(this);
		spike3_r1.setPos(0.0F, -3.0F, 5.0F);
		torso.addChild(spike3_r1);
		setRotationAngle(spike3_r1, -0.1309F, 0.0F, 0.0F);
		spike3_r1.texOffs(0, 0).addBox(-1.0F, -1.2232F, -0.8517F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike2_r1 = new ModelRenderer(this);
		spike2_r1.setPos(0.0F, -3.0F, 1.0F);
		torso.addChild(spike2_r1);
		setRotationAngle(spike2_r1, -0.1309F, 0.0F, 0.0F);
		spike2_r1.texOffs(0, 0).addBox(-1.0F, -3.7453F, -0.8859F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		spike1_r1 = new ModelRenderer(this);
		spike1_r1.setPos(0.0F, -4.0F, -3.0F);
		torso.addChild(spike1_r1);
		setRotationAngle(spike1_r1, -0.1309F, 0.0F, 0.0F);
		spike1_r1.texOffs(0, 0).addBox(-1.0F, -4.276F, -0.7896F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		torso_r1 = new ModelRenderer(this);
		torso_r1.setPos(0.0F, 0.0F, 1.0F);
		torso.addChild(torso_r1);
		setRotationAngle(torso_r1, -0.1745F, 0.0F, 0.0F);
		torso_r1.texOffs(0, 37).addBox(-4.0F, -3.6766F, -9.697F, 8.0F, 7.0F, 20.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 11.0F, -7.0F);
		head.texOffs(8, 2).addBox(-4.0F, -3.0F, -6.0F, 8.0F, 6.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
	    this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}
	
	@Override
	public void prepareMobModel(T entity, float p_212843_2_, float p_212843_3_, float p_212843_4_)
	{
		this.backLegs.xRot = MathHelper.cos(p_212843_2_ * 0.6662F) * 1.4F * p_212843_3_;
        this.backLegs2.xRot = MathHelper.cos(p_212843_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_212843_3_;
        this.tail_r1.xRot = MathHelper.cos(p_212843_2_ * 0.6662F) * 1.4F * p_212843_3_;;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		backLegs.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		backLegs2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}