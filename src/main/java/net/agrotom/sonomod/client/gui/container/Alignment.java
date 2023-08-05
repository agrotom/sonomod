package net.agrotom.sonomod.client.gui.container;

import javax.annotation.Nonnull;

public enum Alignment {
	TOP_LEFT(Horizontal.LEFT, Vertical.TOP),
	TOP_CENTER(Horizontal.CENTER, Vertical.TOP),
	TOP_RIGHT(Horizontal.RIGHT, Vertical.TOP),
	BOTTOM_LEFT(Horizontal.LEFT, Vertical.BOTTOM),
	BOTTOM_CENTER(Horizontal.CENTER, Vertical.BOTTOM),
	BOTTOM_RIGHT(Horizontal.RIGHT, Vertical.BOTTOM),
	CENTER_LEFT(Horizontal.LEFT, Vertical.CENTER),
	CENTER_RIGHT(Horizontal.RIGHT, Vertical.CENTER),
	CENTER(Horizontal.CENTER, Vertical.CENTER);
	
	public final Horizontal posH;
	public final Vertical posV;
	
	private Alignment(@Nonnull Horizontal posH, @Nonnull Vertical posV) {
		this.posH = posH;
		this.posV = posV;
	}
	
	enum Horizontal {
		LEFT,
		CENTER,
		RIGHT
	}
	
	enum Vertical {
		TOP,
		CENTER,
		BOTTOM
	}
}
