package net.agrotom.sonomod.client.gui.skin;

import net.agrotom.sonomod.SonoMod;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;

public class Skins
{

	public static final ISkin DEFAULT_BUTTON_SKIN = ExtendableSkin.newBuilder().setImagePath(Widget.WIDGETS_LOCATION)
			.setSpriteWidth(200).setSpriteHeight(20).setBaseOffset(0, 66).setDisabledOffset(0, 46).setHoverOffset(0, 86)
			.setSpriteBorder(3).build();
	
	public static final ISkin CLASSIC_BUTTON_SKIN = ExtendableSkin.newBuilder()
			.setImagePath(new ResourceLocation(SonoMod.MOD_ID, "textures/gui/classic_buttons.png")).setSpriteWidth(200).setSpriteHeight(20)
			.setBaseOffset(0, 20).setDisabledOffset(0, 0).setHoverOffset(0, 40).setSpriteBorder(3).build();

}
