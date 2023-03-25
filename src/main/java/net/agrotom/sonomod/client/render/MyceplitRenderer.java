package net.agrotom.sonomod.client.render;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.entity.MyceplitEntity;
import net.agrotom.sonomod.entity.model.MyceplitModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MyceplitRenderer extends MobRenderer<MyceplitEntity, MyceplitModel<MyceplitEntity>>
{
	protected static final ResourceLocation TEXTURE =
			new ResourceLocation(SonoMod.MOD_ID, "textures/entity/myceplit.png");
	
	public MyceplitRenderer(EntityRendererManager renderIn)
	{
		super(renderIn, new MyceplitModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(MyceplitEntity p_110775_1_)
	{
		return TEXTURE;
	}

}
