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
		
		final int layoutX = this.getX() + padding;
		
		final int width = this.getWidth();
		final Alignment.Horizontal alignmentH = this.getAlignment().posH;
		
		final int layoutY = this.getY();
		
		final int maxHeight = layoutY + this.getHeight() - padding;
		final int maxWidth = this.getWidth() - 2 * padding;
		
		int currentHeight = layoutY + padding - margin;
		
		for (final IElement element : children) {
			final int elementHeight = element.getHeight();
			final int elementWidth = element.getWidth();
			
			currentHeight += margin;
			
			if (currentHeight + elementHeight > maxHeight) {
				currentHeight -= margin;
				continue;
			}
			
			if (elementWidth > maxWidth) {
				//element.setWidth(maxWidth);
				continue;
			}
			
			switch(alignmentH) {
			case LEFT:
				element.setX(layoutX);
				break;
			case CENTER:
				element.setX(layoutX + width / 2 - elementWidth / 2);
				break;
			case RIGHT:
				element.setX(2 * layoutX + elementWidth);
				break;
			}
			
			element.setY(currentHeight);
			
			currentHeight += elementHeight;

		}
	}

	@Override
	public ElementType<ILayout> getType() {
		return ElementType.LAYOUT_TYPE;
	}

}
