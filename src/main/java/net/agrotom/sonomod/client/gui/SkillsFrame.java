package net.agrotom.sonomod.client.gui;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.gui.container.ColumnLayout;
import net.agrotom.sonomod.client.gui.container.ILayout;
import net.agrotom.sonomod.client.gui.elements.TextArea;
import net.agrotom.sonomod.client.gui.interactive.Button;
import net.agrotom.sonomod.client.gui.style.TexturedRect;
import net.agrotom.sonomod.client.gui.style.image.ImageFile;
import net.agrotom.sonomod.skills.ISkillsCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class SkillsFrame extends Frame {
	
	private ISkillsCompound playerSkills = null;
	
	private TexturedRect backgroundTexture = new TexturedRect(
			new ImageFile(new ResourceLocation(SonoMod.MOD_ID, "textures/gui/skills.png"), 176, 166));
	
	private Button button = new Button("1");
	private Button button2 = new Button("2");
	private TextArea textArea = new TextArea("SOMETHING TEXT");
	private ColumnLayout rootLayout = new ColumnLayout(button, button2, textArea);
	
	
	public SkillsFrame(@Nonnull ISkillsCompound compound) {
		super(new StringTextComponent("Skills menu"));
		
		playerSkills = compound;
	}

	@Override
	protected ILayout getRoot() {
		return rootLayout;
	}

	@Override
	public void initialize() {
		rootLayout.setBackground(backgroundTexture);
		rootLayout.setWidth(176);
		rootLayout.setHeight(166);
		rootLayout.setPadding(5);
		rootLayout.setMargin(5);
		
		playerSkills.getSkillsUnmodifiable().forEach((key, skill) -> {
			rootLayout.getChildren().add(new TextArea(key + ": " + skill.getXP()));
		});
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
