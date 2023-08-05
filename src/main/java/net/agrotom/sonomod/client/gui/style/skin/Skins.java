package net.agrotom.sonomod.client.gui.style.skin;

import net.minecraft.client.gui.widget.Widget;

public class Skins {

	public static final Skins instance = new Skins();
	
	public final ISkin VANILLA_SKIN = ButtonSkin.newBuilder(Widget.WIDGETS_LOCATION)
			.setSpriteSize(200, 20).setDefaultOffset(0, 66)
			.setHoverOffset(0, 86).setDisabledOffset(0, 46).build();
	
	private Skins() {};

}
