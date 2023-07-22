package net.agrotom.sonomod.client.gui.style.image;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public abstract class AbstractImage implements IImage {
	
	protected int width = 0;
	protected int height = 0;

	protected AbstractImage(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
