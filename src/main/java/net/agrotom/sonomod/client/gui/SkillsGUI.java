package net.agrotom.sonomod.client.gui;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.gui.containers.AnchorLayout;
import net.agrotom.sonomod.client.gui.containers.FlowLayout;
import net.agrotom.sonomod.client.gui.containers.Layout;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.client.gui.interactive.Button;
import net.agrotom.sonomod.client.gui.interactive.CheckBox;
import net.agrotom.sonomod.client.gui.interactive.ImageHolder;
import net.agrotom.sonomod.client.gui.misc.ImageFill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SkillsGUI extends GUIBase
{
	@SuppressWarnings("unused")
	private static final ResourceLocation SKILLS_LOCATION = new ResourceLocation(SonoMod.MOD_ID, "textures/gui/skills.png");
	
	private final AnchorLayout root = new AnchorLayout();
	private Button button = new Button("Start");
	private Button button1 = new Button("Play");
	private CheckBox checkBox = new CheckBox();
	private ImageHolder holder = new ImageHolder(new ImageFill(256, 256));

	public SkillsGUI(ITextComponent title)
	{
		super(title);
	}
	
	@Override
	public void init()
	{
		super.init();
		
		button.setPrefWidth(278);
		button.setPrefHeight(200);
		
		button.setX(0);
		button.setY(0);
		
		button1.setPrefWidth(100);
		button1.setPrefHeight(20);
		
		button1.setX(0);
		button1.setY(50);
		
		checkBox.setPrefWidth(20);
		checkBox.setPrefHeight(20);
		
		checkBox.setX(0);
		checkBox.setY(100);
		
		root.setWidth(278);
		root.setHeight(300);
		
		root.setPadding(5);
		root.setMargin(5);
		
		holder.setMaxWidth(178);
		holder.setMaxHeight(168);
		
		root.addElement(button);
		
		root.onUpdate(new UpdateEvent());
	}

	@Override
	public Layout getRootLayout()
	{
		return root;
	}

}
