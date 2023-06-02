package net.agrotom.sonomod.client.gui.interactive;

import net.agrotom.sonomod.client.gui.events.ActionEvent;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CheckBox extends InteractiveElement
{
	private boolean isOn = false;
	
	@Override
	public void render(RenderEvent event)
	{
	    	super.render(event);
	    
		final ElementState currentState;
		
		if(this.isDisabled()) {
			currentState = ElementState.DISABLED;
		}
		else if(!isOn) {
			if(this.isHovered()) {
				currentState = ElementState.HOVER;
			}
			else {
				currentState = ElementState.USUAL;
			}
			
		}
		else {
			currentState = ElementState.DISABLED;
		}
		
		skin.render(event.getMatrix(), event.getRelativeX() + this.getX(), event.getRelativeY() + this.getY(),
				this.getWidth(), this.getHeight(), currentState);
	}
	
	@Override
	public void onAction(ActionEvent event)
	{
		super.onAction(event);
		
		this.ifEnabled(() -> {
			isOn = !isOn;
		});
	}
	
	public void setIsOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	public boolean isOn() {
		return this.isOn;
	}

}
