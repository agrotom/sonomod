package net.agrotom.sonomod.client.render;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.entity.GimosEntity;
import net.agrotom.sonomod.entity.model.GimosModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GimosRenderer extends MobRenderer<GimosEntity, GimosModel<GimosEntity>>
{

	protected static final ResourceLocation TEXTURE =
			new ResourceLocation(SonoMod.MOD_ID, "textures/entity/gimos.png");

	public GimosRenderer(EntityRendererManager renderIn)
	{
		super(renderIn, new GimosModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(GimosEntity p_110775_1_)
	{
		return TEXTURE;
	}

}
