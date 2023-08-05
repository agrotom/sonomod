package net.agrotom.sonomod.client.gui.container;

import java.util.Collection;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.elements.IElement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class AnchorLayout extends AbstractLayout {

	public AnchorLayout(@Nonnull Collection<IElement> elements) {
		super(elements);
	}
	
	public AnchorLayout(@Nonnull IElement... elements) {
		super(elements);
	}

	@Override
	public void constructLayout() {
		
	}

}
