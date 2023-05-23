package net.agrotom.sonomod.client.gui.containers;

import java.util.List;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ColumnArea extends Layout {
	
	private void stretchChildWidth(@Nonnull Element child) {
		final int borderWidth = this.getWidth() - 2 * this.getPadding();
		final int childBorderWidth = this.getWidthPolicy() == StretchPolicy.MAX ? child.getMaxWidth() : child.getMinWidth();
		final int childPrefWidth = child.getPrefWidth();
		
		if(childPrefWidth == AUTO) {
			if(childBorderWidth > borderWidth) {
				child.setWidth(borderWidth);
			}
			else {
				child.setWidth(childBorderWidth);
			}
		}
		else {
			if(childPrefWidth > borderWidth) {
				child.setWidth(borderWidth);
			}
			else {
				child.setWidth(childPrefWidth);
			}
		}
	}
	
	private void stretchChildHeight(@Nonnull Element child) {
		final int borderHeight = this.getHeight() - 2 * this.getPadding();
		final int childBorderHeight = this.getWidthPolicy() == StretchPolicy.MAX ? child.getMaxHeight() : child.getMinHeight();
		final int childPrefHeight = child.getPrefHeight();
		
		if(childPrefHeight == AUTO) {
			if(childBorderHeight > borderHeight) {
				child.setWidth(borderHeight);
			}
			else {
				child.setWidth(childBorderHeight);
			}
		}
		else {
			if(childPrefHeight > borderHeight) {
				child.setWidth(borderHeight);
			}
			else {
				child.setWidth(childPrefHeight);
			}
		}
	}
	
	private void stretchChild(@Nonnull Element child) {
		stretchChildWidth(child);
		stretchChildHeight(child);
	}
	
	public ColumnArea(@Nonnull Element... children) {
		super(children);
	}

	public ColumnArea(@Nonnull List<Element> list) {
		super(list);
	}
	
	@Override
	public void onUpdate(@Nonnull UpdateEvent event) {
		super.onUpdate(event);
		
		final int layoutX = this.getX();
		final int layoutY = this.getY();
		final int layoutHeight = this.getHeight();
		
		final int padding = this.getPadding();
		final int margin = this.getMargin();
		int currentHeight = padding;
		
		for(final Element child : children) {
			if(currentHeight >= layoutHeight) {
				child.setX(OUT_OF_BOUNDS_X);
			}
			else {
				final int resultX = layoutX + padding;
				final int resultY = currentHeight + layoutY;
				
				child.setX(resultX);
				child.setY(resultY);
				
				stretchChild(child);
				
				currentHeight += child.getHeight() + margin;
			}
		}
	}

}
