package net.agrotom.sonomod.client.gui.container;

import java.util.Optional;

import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.style.ITexturedRect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public interface ILayout extends IElement {
	
	void setBackground(@Nullable ITexturedRect background);
	Optional<ITexturedRect> getBackground();
	
	void setPadding(int padding);
	int getPadding();
	
	void setMargin(int margin);
	int getMargin();
	
	void constructLayout();
	
}
