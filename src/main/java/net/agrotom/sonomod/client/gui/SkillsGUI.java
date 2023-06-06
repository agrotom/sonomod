package net.agrotom.sonomod.client.gui;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.gui.containers.Alignment;
import net.agrotom.sonomod.client.gui.containers.ColumnArea;
import net.agrotom.sonomod.client.gui.containers.Layout;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.client.gui.interactive.Button;
import net.agrotom.sonomod.client.gui.interactive.CheckBox;
import net.agrotom.sonomod.client.gui.interactive.ImageHolder;
import net.agrotom.sonomod.client.gui.misc.ImageFill;
import net.agrotom.sonomod.client.gui.skin.Skins;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class SkillsGUI extends GUIBase
{
	@SuppressWarnings("unused")
	private static final ResourceLocation SKILLS_LOCATION = new ResourceLocation(SonoMod.MOD_ID, "textures/gui/skills.png");
	
	private final Layout root = new ColumnArea();
	private Button button = new Button("Start");
	private Button button1 = new Button("Play");
	private Button button2 = new Button("Mods and texturepacks");
	private CheckBox checkBox = new CheckBox();
	private ImageHolder holder = new ImageHolder(new ImageFill(256, 256));

	private final ClientPlayerEntity player;
	
	public SkillsGUI(@Nonnull ClientPlayerEntity player)
	{
		super(new StringTextComponent("Skills Info"));
		
		this.player = player;
	}
	
	@Override
	public void init()
	{
		super.init();
		
		final int width = 200;
		final int height = 20;
		
		button2.setPrefHeight(200);
		
		button.setSkin(Skins.CLASSIC_BUTTON_SKIN);
		button.setSound(SimpleSound.forUI(SoundEvents.ARMOR_EQUIP_ELYTRA, 1.0f));
		
		button.setOnEnter(e -> {
			button.setText(button.getText().copy().withStyle(TextFormatting.YELLOW));
		});
		
		button.setOnExit(e -> {
			button.setText(button.getText().copy().withStyle(TextFormatting.WHITE));
		});
		
		button.setPrefWidth(width);
		button.setPrefHeight(height);
		
		button1.setPrefWidth(100);
		button1.setPrefHeight(20);
		
		checkBox.setPrefWidth(20);
		checkBox.setPrefHeight(20);
		
		checkBox.setSkin(Skins.CLASSIC_BUTTON_SKIN);
		
		root.setWidth(256);
		root.setHeight(256);
		
		root.setPadding(5);
		root.setMargin(30);
		
		root.setBackground(holder);
		
		root.setAlignment(Alignment.CENTER);
		
		holder.setMaxWidth(256);
		holder.setMaxHeight(256);
		
		root.addAllElements(button, button1, checkBox, button2);
		
		root.onUpdate(new UpdateEvent());
	}

	@Override
	public Layout getRootLayout()
	{
		return root;
	}

}
