package net.agrotom.sonomod.client.gui.elements;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.events.GUIMouseEvent;
import net.agrotom.sonomod.client.gui.events.GUIUpdateEvent;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent.ActionEvent;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent.DragEvent;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent.EnterEvent;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent.ExitEvent;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent.HoverEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public abstract class AbstractElement implements IElement {

	private int x = 0;
	private int y = 0;
	
	private int width = 0;
	private int height = 0;
	
	private ITextComponent tooltip = null;
	
	private boolean isHovered = false;
	
	private Consumer<GUIMouseEvent.ActionEvent> actionListener = null;
	private Consumer<GUIMouseEvent.EnterEvent> enterListener = null;
	private Consumer<GUIMouseEvent.ExitEvent> exitListener = null;
	private Consumer<GUIMouseEvent.DragEvent> dragListener = null;
	private Consumer<GUIMouseEvent.HoverEvent> hoverListener = null;
	private Consumer<GUIUpdateEvent> updateListener = null;
	
	@Override
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public int getY() {
		return y;
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
	
	@Override
	public boolean isElementAt(int x, int y) {
		return x >= this.x && x < this.x + width && y >= this.y && y < this.y + height;
	}
	
	@Override
	public Optional<IElement> at(int x, int y) {
		if (isElementAt(x, y)) {
			return Optional.of(this);
		}
		
		return Optional.empty();
	}
	
	@Override
	public void setTooltip(@Nullable ITextComponent tooltip) {
		this.tooltip = tooltip;
	}
	
	@Override
	public Optional<ITextComponent> getTooltip() {
		return Optional.ofNullable(tooltip);
	}
	
	@Override
	public void setOnMouseAction(@Nonnull Consumer<ActionEvent> listener) {
		this.actionListener = listener;
	}
	
	@Override
	public void setOnMouseDrag(@Nonnull Consumer<DragEvent> listener) {
		this.dragListener = listener;
	}
	
	@Override
	public void setOnMouseEnter(@Nonnull Consumer<EnterEvent> listener) {
		this.enterListener = listener;
	}
	
	@Override
	public void setOnMouseExit(@Nonnull Consumer<ExitEvent> listener) {
		this.exitListener = listener;
	}
	
	@Override
	public void setOnMouseHover(@Nonnull Consumer<HoverEvent> listener) {
		this.hoverListener = listener;
	}
	
	@Override
	public void setOnUpdate(Consumer<GUIUpdateEvent> listener) {
		this.updateListener = listener;
	}
	
	@Override
	public void mouseAction(@Nonnull ActionEvent event) {
		if (actionListener != null) {
			actionListener.accept(event);
		}
	}
	
	@Override
	public void mouseDrag(@Nonnull DragEvent event) {
		if (dragListener != null) {
			dragListener.accept(event);
		}
	}
	
	@Override
	public void mouseEnter(@Nonnull EnterEvent event) {
		isHovered = true;
		
		if (enterListener != null) {
			enterListener.accept(event);
		}
	}
	
	@Override
	public void mouseExit(@Nonnull ExitEvent event) {
		isHovered = false;
		
		if (exitListener != null) {
			exitListener.accept(event);
		}
	}
	
	@Override
	public void mouseHover(@Nonnull HoverEvent event) {
		if (hoverListener != null) {
			hoverListener.accept(event);
		}
	}
	
	@Override
	public void update(@Nonnull GUIUpdateEvent event) {
		if (updateListener != null) {
			updateListener.accept(event);;
		}
	}
	
	@Override
	public List<IElement> getChildren() {
		return Collections.emptyList();
	}
	
	@Override
	public boolean isHovered() {
		return isHovered;
	}
}
