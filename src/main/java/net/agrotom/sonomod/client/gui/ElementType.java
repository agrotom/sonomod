package net.agrotom.sonomod.client.gui;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.container.AnchorLayout;
import net.agrotom.sonomod.client.gui.container.ColumnLayout;
import net.agrotom.sonomod.client.gui.container.ILayout;
import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.elements.TextArea;
import net.agrotom.sonomod.client.gui.interactive.Button;
import net.agrotom.sonomod.client.gui.style.TexturedRect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class ElementType<T extends IElement> {
	
	public static final ElementType<TexturedRect> TEXTURED_RECT_TYPE = create("texturedRect");
	public static final ElementType<AnchorLayout> ANCHOR_LAYOUT = create("anchorLayout");
	public static final ElementType<Button> BUTTON_TYPE = create("button");
	public static final ElementType<ColumnLayout> COLUMN_LAYOUT_TYPE = create("columnLayout");
	public static final ElementType<ILayout> LAYOUT_TYPE = create("layout");
	public static final ElementType<TextArea> TEXT_AREA_TYPE = create("textArea");
	
	public final String id;
	
	protected ElementType(@Nonnull String id) {
		this.id = id;
	}
	
	public static <T extends IElement> ElementType<T> create(@Nonnull String id) {
		return new ElementType<T>(id);
	}

}
