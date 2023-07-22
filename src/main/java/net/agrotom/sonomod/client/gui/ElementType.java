package net.agrotom.sonomod.client.gui;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.container.AnchorLayout;
import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.style.TexturedRect;

public class ElementType<T extends IElement> {
	
	public static final ElementType<TexturedRect> TEXTURED_RECT_TYPE = create("texturedRect");
	public static final ElementType<AnchorLayout> ANCHOR_LAYOUT = create("anchorLayout");
	
	public final String id;
	
	protected ElementType(@Nonnull String id) {
		this.id = id;
	}
	
	public static <T extends IElement> ElementType<T> create(@Nonnull String id) {
		return new ElementType<T>(id);
	}

}
