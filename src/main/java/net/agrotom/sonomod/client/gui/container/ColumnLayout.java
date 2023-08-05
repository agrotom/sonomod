package net.agrotom.sonomod.client.gui.container;

import java.util.Collection;
import java.util.List;

import net.agrotom.sonomod.client.gui.ElementType;
import net.agrotom.sonomod.client.gui.elements.IElement;

public class ColumnLayout extends AbstractLayout {
	
	public ColumnLayout(Collection<IElement> elements) {
		super(elements);
	}

	public ColumnLayout(IElement... elements) {
		super(elements);
	}

	@Override
	public void constructLayout() {
		final List<IElement> children = this.getChildren();
		
		if (children.isEmpty()) {
			return;
		}
		
		final int padding = this.getPadding();
		final int margin = this.getMargin();
		
		final int layoutXWithPadding = this.getX() + padding;
		final int layoutY = this.getY();
		
		final int maxHeight = layoutY + this.getHeight();
		final int maxWidth = this.getWidth() - 2 * padding;
		
		int currentHeight = layoutY + 2 * padding - margin;
		
		for (final IElement element : children) {
			currentHeight += margin + element.getHeight() - padding;
			
			if (currentHeight > maxHeight) {
				return;
			}
			
			if (element.getWidth() > maxWidth) {
				element.setWidth(maxWidth);
				continue;
			}
			
			element.setX(layoutXWithPadding);
			element.setY(currentHeight);
			
			currentHeight += padding;
		}
	}

	@Override
	public ElementType<ILayout> getType() {
		return ElementType.LAYOUT_TYPE;
	}

}
