package net.agrotom.sonomod.client.gui.interactive;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.misc.AbstractImage;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImageHolder extends Element
{

	private AbstractImage image;

	public ImageHolder(@Nonnull AbstractImage image)
	{
		this.image = image;
	}

	@Override
	public void render(RenderEvent event)
	{
	    if(image != null) {
		image.render(new RenderEvent(event.getMatrix(), event.getRelativeX() + this.getX(), event.getRelativeY() + this.getY()),
			this.getMaxWidth(), this.getMaxHeight());
	    }
	}

}
