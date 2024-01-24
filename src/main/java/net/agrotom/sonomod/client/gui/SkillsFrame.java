package net.agrotom.sonomod.client.gui;

import net.agrotom.sonomod.client.gui.container.Alignment;
import net.agrotom.sonomod.client.gui.container.ColumnLayout;
import net.agrotom.sonomod.client.gui.container.ILayout;
import net.agrotom.sonomod.client.gui.elements.TextArea;
import net.agrotom.sonomod.client.gui.interactive.Button;
import net.agrotom.sonomod.client.gui.style.TexturedRect;
import net.agrotom.sonomod.client.gui.style.image.ColorFill;
import net.agrotom.sonomod.client.gui.style.image.ImageFile;
import net.agrotom.sonomod.inventory.container.SkillsContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class SkillsFrame extends FrameContainer<SkillsContainer> {
	
	private final SkillsContainer container;
	
	private TexturedRect backgroundTexture = new TexturedRect(new ColorFill(64, 207));
	
	private Button button = new Button("1");
	private Button button2 = new Button("2");
	private TextArea textArea = new TextArea("SOMETHING TEXT");
	private ColumnLayout rootLayout = new ColumnLayout();
	
	public SkillsFrame(SkillsContainer container, PlayerInventory inventory,
			ITextComponent text) {
		
		super(container, inventory, text);
		
		this.container = container;
		
	}

	@Override
	protected ILayout getRoot() {
		return rootLayout;
	}

	@Override
	public void initialize() {
		button.setWidth(50);
		button2.setWidth(50);
		textArea.setWidth(176);
		
		rootLayout.setBackground(backgroundTexture);
		
		rootLayout.setWidth(64);
		rootLayout.setHeight(207);
		rootLayout.setMargin(3);
		
		rootLayout.setAlignment(Alignment.CENTER);
		
		container.playerSkills.getSkillsUnmodifiable().forEach((key, skill) -> {
			String[] path = key.split(":");
			final TexturedRect icon = new TexturedRect(new ImageFile(
					new ResourceLocation(path[0], "textures/gui/skills_icons/" 
							+ skill.getInstance().getName() + ".png"), 32, 32));
			
			
			
			final TextArea skillInfo = new TextArea(key + ": " + skill.getXP());
			skillInfo.setWidth(130);
			skillInfo.setHeight(20);
			
			rootLayout.getChildren().add(icon);
		});
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
