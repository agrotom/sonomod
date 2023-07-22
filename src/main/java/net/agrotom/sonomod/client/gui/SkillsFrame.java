package net.agrotom.sonomod.client.gui;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.gui.container.AnchorLayout;
import net.agrotom.sonomod.client.gui.container.ILayout;
import net.agrotom.sonomod.client.gui.style.TexturedRect;
import net.agrotom.sonomod.client.gui.style.image.ImageFile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class SkillsFrame extends Frame {
	
	private TexturedRect backgroundTexture = new TexturedRect(
			new ImageFile(new ResourceLocation(SonoMod.MOD_ID, "textures/gui/skills.png"), 176, 166));
	private AnchorLayout rootLayout = new AnchorLayout(backgroundTexture);
	
	
	public SkillsFrame(@Nonnull ITextComponent title) {
		super(title);
	}

	@Override
	protected ILayout getRoot() {
		return rootLayout;
	}

	@Override
	public void initialize() {
		rootLayout.setWidth(176);
		rootLayout.setHeight(166);
		
		backgroundTexture.setTooltip(new StringTextComponent("LOREM IPSAJDASLHFDKGFJSDHFGHDhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"));
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
