package net.agrotom.sonomod.client.gui.skin;

import net.minecraft.client.gui.widget.Widget;

public class Skins
{

	public static final ISkin DEFAULT_BUTTON_SKIN = ButtonSkin.newBuilder().setImagePath(Widget.WIDGETS_LOCATION)
			.setSpriteWidth(200).setSpriteHeight(20).setBaseOffset(0, 66).setDisabledOffset(0, 46).setHoverOffset(0, 86).build();

}
