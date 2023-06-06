package net.agrotom.sonomod.client.gui;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.containers.Layout;
import net.agrotom.sonomod.client.gui.events.ActionEvent;
import net.agrotom.sonomod.client.gui.events.HoverEvent;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.util.RenderEvent;
import net.agrotom.sonomod.util.RenderUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class GUIBase extends Screen {
	private Layout root;

	private Optional<Element> lastHoveredElement = Optional.empty();
	
	private int relativeX = 0;
	private int relativeY = 0;

	protected GUIBase(ITextComponent title) {
		super(title);
	}

	public abstract @Nonnull Layout getRootLayout();
	
	@Override
	public void init() {
		root = getRootLayout();
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int id) {
		root.mouseAt(mouseX - relativeX, mouseY - relativeY)
			.ifPresent(element -> element.onAction(new ActionEvent(mouseX - relativeX, mouseY - relativeY, id)));

		root.onUpdate(new UpdateEvent());
		
		return true;
	}

	@Override
	public void mouseMoved(double mouseX, double mouseY) {
		// System.out.println("moving mouse!");

		final Optional<Element> element = root.mouseAt(mouseX - relativeX, mouseY - relativeY);
		final HoverEvent event = new HoverEvent();

		element.ifPresent(newElement ->
		{
			if (lastHoveredElement.isPresent()) {
				if (newElement.isHovered() && lastHoveredElement.get().isHovered()) {
				    	newElement.onHover(event);
					return;
				} else {
					lastHoveredElement.get().onExit(event);
					newElement.onEnter(event);
					lastHoveredElement = element;
				}
			} else {
				lastHoveredElement = element;
				newElement.onEnter(event);
			}
		});

		if (!element.isPresent() && lastHoveredElement.isPresent()) {
			lastHoveredElement.get().onExit(event);
			lastHoveredElement = Optional.empty();
		}

	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTick) {
		super.renderBackground(matrix);

		relativeX = RenderUtils.getRelativeX(width, root.getWidth());
		relativeY = RenderUtils.getRelativeY(height, root.getHeight());

		root.render(new RenderEvent(matrix, relativeX, relativeY));
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
