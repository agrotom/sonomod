package net.agrotom.sonomod.client.gui.interactive;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.elements.AbstractElement;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent.ActionEvent;
import net.agrotom.sonomod.client.gui.style.skin.ISkin;
import net.agrotom.sonomod.client.gui.style.skin.Skins;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.SoundEvents;

public abstract class InteractiveElement extends AbstractElement implements IInteractive {

	private ISkin skin = Skins.instance.VANILLA_SKIN;
	
	public void playButtonSound(@Nonnull SoundHandler handler) {
		handler.play(SimpleSound.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}
	
	@Override
	public void setSkin(@Nonnull ISkin skin) {
		this.skin = skin;
	}
	
	@Override
	@Nonnull
	public ISkin getSkin() {
		return skin;
	}

	@Override
	public void mouseAction(@Nonnull ActionEvent event) {
		super.mouseAction(event);
		playButtonSound(event.getMinecraft().getSoundManager());
	}

	
	
}
