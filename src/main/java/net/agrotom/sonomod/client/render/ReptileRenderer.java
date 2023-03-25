package net.agrotom.sonomod.client.render;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.entity.ReptileEntity;
import net.agrotom.sonomod.entity.model.ReptileModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ReptileRenderer extends MobRenderer<ReptileEntity, ReptileModel<ReptileEntity>>
{
	protected static final ResourceLocation TEXTURE =
			new ResourceLocation(SonoMod.MOD_ID, "textures/entity/reptile.png");

	public ReptileRenderer(EntityRendererManager renderIn)
	{
		super(renderIn, new ReptileModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(ReptileEntity p_110775_1_)
	{
		return TEXTURE;
	}

}
