package net.agrotom.sonomod.entity.model;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.agrotom.sonomod.entity.ReptileEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;



public class ReptileModelE <T extends ReptileEntity> extends EntityModel<T> {
	private final ModelRenderer backLegs;
	private final ModelRenderer frontLegs;
	private final ModelRenderer torso;
	private final ModelRenderer torso_r1;

	public ReptileModelE() {
		texWidth = 32;
		texHeight = 32;

		backLegs = new ModelRenderer(this);
		backLegs.setPos(0.0F, 24.0F, 0.0F);
		backLegs.setTexSize(0, 0).addBox(1.0F, -5.0F, 3.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		backLegs.setTexSize(0, 0).addBox(-3.0F, -5.0F, 3.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		frontLegs = new ModelRenderer(this);
		frontLegs.setPos(0.0F, 24.0F, 0.0F);
		frontLegs.setTexSize(0, 0).addBox(2.0F, -7.0F, -5.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		frontLegs.setTexSize(0, 0).addBox(-4.0F, -7.0F, -5.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		torso = new ModelRenderer(this);
		torso.setPos(0.0F, 24.0F, 0.0F);
		torso.setTexSize(16, 24).addBox(-2.0F, -9.0F, -9.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		torso_r1 = new ModelRenderer(this);
		torso_r1.setPos(0.0F, 0.0F, -1.0F);
		torso.addChild(torso_r1);
		setRotationAngle(torso_r1, -0.1745F, 0.0F, 0.0F);
		torso_r1.setTexSize(0, 0).addBox(-2.0F, -9.0F, -6.0F, 4.0F, 4.0F, 11.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		backLegs.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegs.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_,
			float p_225597_6_)
	{
	}
}