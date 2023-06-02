package net.agrotom.sonomod.client.gui.containers;

import java.util.List;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;

/**
 * 
 * Places child elements by their position. No dynamic placement.
 *
 */
public class AnchorLayout extends Layout {

    public AnchorLayout(Element... children) {
	super(children);
    }

    public AnchorLayout(List<Element> children) {
	super(children);
    }

    private void stretchWidth(@Nonnull Element child) {
	int childWidth = child.getPrefWidth() == AUTO ? child.getMaxWidth() : child.getPrefWidth();
	final int childX = child.getX();

	final int computedBorderWidth = this.getWidth() - childX;

	if (childWidth > computedBorderWidth) {
	    child.setWidth(computedBorderWidth);
	} else {
	    child.setWidth(childWidth);
	}

	if (childWidth > computedBorderWidth) {
	    child.setWidth(computedBorderWidth);
	} else {
	    child.setWidth(childWidth);
	}
    }

    private void stretchHeight(@Nonnull Element child) {
	int childHeight = child.getPrefHeight() == AUTO ? child.getMaxHeight() : child.getPrefHeight();
	final int childY = child.getY();

	final int computedBorderHeight = this.getHeight() - childY;

	if (childHeight > computedBorderHeight) {
	    child.setHeight(computedBorderHeight);
	} else {
	    child.setHeight(childHeight);
	}

	if (childHeight > computedBorderHeight) {
	    child.setHeight(computedBorderHeight);
	} else {
	    child.setHeight(childHeight);
	}
    }

    @Override
    public void onUpdate(UpdateEvent event) {
	super.onUpdate(event);

	children.forEach(child -> {
	    stretchWidth(child);
	    stretchHeight(child);
	});
    }
}
