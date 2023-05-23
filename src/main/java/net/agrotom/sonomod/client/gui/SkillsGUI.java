package net.agrotom.sonomod.client.gui;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.gui.containers.ColumnArea;
import net.agrotom.sonomod.client.gui.containers.Layout;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.client.gui.interactive.Button;
import net.agrotom.sonomod.client.gui.interactive.ImageHolder;
import net.agrotom.sonomod.client.gui.misc.ImageFill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SkillsGUI extends GUIBase
{
	@SuppressWarnings("unused")
	private static final ResourceLocation SKILLS_LOCATION = new ResourceLocation(SonoMod.MOD_ID, "textures/gui/skills.png");
	
	private final ColumnArea root = new ColumnArea();
	private Button button = new Button("Start");
	private Button button1 = new Button("Play");
	private ImageHolder holder = new ImageHolder(new ImageFill(256, 256));

	public SkillsGUI(ITextComponent title)
	{
		super(title);
	}
	
	@Override
	public void init()
	{
		super.init();
		
		button.setPrefWidth(100);
		button.setPrefHeight(20);
		
		button1.setPrefWidth(100);
		button1.setPrefHeight(20);
		
		root.setWidth(178);
		root.setHeight(168);
		
		root.setPadding(5);
		root.setMargin(5);
		
		holder.setMaxWidth(178);
		holder.setMaxHeight(168);
		
		root.setBackground(holder);
		
		root.addElement(button, button1);
		
		root.onUpdate(new UpdateEvent());
	}

	@Override
	public Layout getRootLayout()
	{
		return root;
	}

}
