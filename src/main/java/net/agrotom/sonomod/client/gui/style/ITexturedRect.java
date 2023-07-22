package net.agrotom.sonomod.client.gui.style;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.style.image.IImage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public interface ITexturedRect extends IElement {
	
	void setStretchPolicy(@Nonnull StretchPolicy policy);
	@Nonnull
	StretchPolicy getStretchPolicy();
	
	void setImage(@Nonnull IImage image);
	@Nonnull
	IImage getImage();

}
