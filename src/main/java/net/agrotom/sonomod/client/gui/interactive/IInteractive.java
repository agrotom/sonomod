package net.agrotom.sonomod.client.gui.interactive;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.style.skin.ISkin;

public interface IInteractive extends IElement {
	
	void setSkin(@Nonnull ISkin skin);
	@Nonnull
	ISkin getSkin();
	
}
