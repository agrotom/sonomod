package net.agrotom.sonomod.client.gui.container;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.ElementType;
import net.agrotom.sonomod.client.gui.elements.AbstractElement;
import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.style.ITexturedRect;
import net.agrotom.sonomod.util.DynamicList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public abstract class AbstractLayout extends AbstractElement implements ILayout {

	private Alignment alignment = Alignment.TOP_LEFT;
	
	private final List<IElement> children = new DynamicList<IElement>(new Vector<IElement>(5, 3),
		addedElements -> {
			addedElements.forEach(element -> {
				element.setParent(this);
			});
			constructLayout();
	});
	
	private ITexturedRect background = null;
	
	private int padding = 0;
	private int margin = 0;
	
	protected AbstractLayout(@Nonnull Collection<IElement> elements) {
		children.addAll(elements);
	}
	
	protected AbstractLayout(@Nonnull IElement... elements) {
		this(Arrays.asList(elements));
	}
	
	@Override
	public void setBackground(@Nullable ITexturedRect background) {
		this.background = background;
		
		if (this.background != null) {
			background.setX(this.getX());
			background.setY(this.getY());
		}
	}
	
	@Override
	public Optional<ITexturedRect> getBackground() {
		return Optional.ofNullable(background);
	}
	
	@Override
	public void setPadding(int padding) {
		this.padding = padding;
	}
	
	@Override
	public int getPadding() {
		return padding;
	}
	
	@Override
	public void setMargin(int margin) {
		this.margin = margin;
	}
	
	@Override
	public int getMargin() {
		return margin;
	}

	@Override
	public void setAlignment(@Nonnull Alignment alignment) {
		this.alignment = alignment;
	}

	@Override
	public Alignment getAlignment() {
		return alignment;
	}

	@Override
	@Nonnull
	public List<IElement> getChildren() {
		return children;
	}
	
	@Override
	public Optional<IElement> at(int x, int y) {
		for (final IElement element : children) {
			if (element.isElementAt(x, y)) {
				return Optional.of(element);
			}
		}
		
		return super.at(x, y);
	}

	@Override
	public ElementType<? extends ILayout> getType() {
		return ElementType.LAYOUT_TYPE;
	}
	
}
