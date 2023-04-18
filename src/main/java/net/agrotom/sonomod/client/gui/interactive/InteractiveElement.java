package net.agrotom.sonomod.client.gui.interactive;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.events.ActionEvent;
import net.agrotom.sonomod.client.gui.skin.ISkin;
import net.agrotom.sonomod.client.gui.skin.Skins;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.SoundEvents;

public abstract class InteractiveElement extends Element {

	protected ISkin skin = Skins.DEFAULT_BUTTON_SKIN;
	protected ISound sound = SimpleSound.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0f);

	public void setSkin(@Nonnull ISkin skin) {
		this.skin = skin;
	}

	public ISkin getSkin() {
		return skin;
	}
	
	public void setSound(@Nonnull ISound sound) {
		this.sound = sound;
	}
	
	public ISound getSound() {
		return sound;
	}
	
	@Override
	public void onAction(ActionEvent event)
	{
		super.onAction(event);

		this.playButtonSound(sound);
	}

}
