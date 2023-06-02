package net.agrotom.sonomod.client.gui.interactive;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Button extends InteractiveElement
{
	private final Label button_label = new Label("");

	public Button()
	{

	}

	public Button(@Nonnull String text)
	{
		button_label.setText(text);
	}

	public Button(@Nonnull ITextComponent text)
	{
		button_label.setText(text);
	}

	@Override
	public void render(RenderEvent event)
	{
	    	super.render(event);
	    
		skin.render(event.getMatrix(), event.getRelativeX() + this.getX(), event.getRelativeY() + this.getY(),
				this.getWidth(), this.getHeight(), this.getState());
		button_label.render(event);
	}

	@Override
	public void onUpdate(UpdateEvent event)
	{
		super.onUpdate(event);

		stretchChildren();
	}

	private void stretchChildren()
	{
		button_label.setX(this.getX() + (int) (this.getWidth() / 2f));
		button_label.setY(this.getY() + (int) (this.getHeight() / 3f));
		button_label.setWidth(this.getWidth());
		button_label.setHeight(this.getHeight());

		button_label.setWrapped(false);
		button_label.setCentered(true);
	}

	public void setText(@Nonnull String text)
	{
		button_label.setText(text);
	}

	public void setText(@Nonnull ITextComponent text)
	{
		button_label.setText(text);
	}

	
}
