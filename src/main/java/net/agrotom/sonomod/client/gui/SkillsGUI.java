package net.agrotom.sonomod.client.gui;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.gui.containers.AnchorLayout;
import net.agrotom.sonomod.client.gui.containers.Layout;
import net.agrotom.sonomod.client.gui.interactive.Button;
import net.agrotom.sonomod.client.gui.interactive.ImageHolder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SkillsGUI extends GUIBase
{
	private static final ResourceLocation SKILLS_LOCATION = new ResourceLocation(SonoMod.MOD_ID, "textures/gui/skills.png");
	
	private final AnchorLayout root = new AnchorLayout();
	private Button button = new Button("Start");

	public SkillsGUI(ITextComponent title)
	{
		super(title);
	}
	
	@Override
	public void init()
	{
		super.init();
		
		button.setX(50);
		button.setY(50);
		
		button.setWidth(100);
		button.setHeight(20);
		
		root.setWidth(256);
		root.setHeight(256);
		
		root.setBackground(new ImageHolder(SKILLS_LOCATION));
		
		root.children.add(button);
	}

	@Override
	public Layout getRootLayout()
	{
		return root;
	}

}
