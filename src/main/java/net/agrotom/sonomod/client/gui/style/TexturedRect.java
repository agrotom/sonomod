package net.agrotom.sonomod.client.gui.style;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.ElementType;
import net.agrotom.sonomod.client.gui.elements.AbstractElement;
import net.agrotom.sonomod.client.gui.style.image.IImage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class TexturedRect extends AbstractElement implements ITexturedRect {

	private IImage image = null;
	private StretchPolicy stretchPolicy = StretchPolicy.SINGLE;
	
	public TexturedRect(@Nonnull IImage image) {
		this.image = image;
		setWidth(image.getWidth());
		setHeight(image.getHeight());
	}

	@Override
	public ElementType<?> getType() {
		return ElementType.TEXTURED_RECT_TYPE;
	}

	@Override
	public void setStretchPolicy(@Nonnull StretchPolicy policy) {
		this.stretchPolicy = policy;
	}

	@Override
	@Nonnull
	public StretchPolicy getStretchPolicy() {
		return stretchPolicy;
	}

	@Override
	public void setImage(@Nonnull IImage image) {
		this.image = image;
	}

	@Override
	@Nonnull
	public IImage getImage() {
		return image;
	}

}
