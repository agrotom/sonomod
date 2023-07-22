package net.agrotom.sonomod.client.gui.interactive;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.elements.AbstractElement;
import net.agrotom.sonomod.client.gui.style.skin.ISkin;

public abstract class InteractiveElement extends AbstractElement implements IInteractive {

	private ISkin skin;
	
	@Override
	public void setSkin(@Nonnull ISkin skin) {
		this.skin = skin;
	}
	
	@Override
	@Nonnull
	public ISkin getSkin() {
		return skin;
	}

}
