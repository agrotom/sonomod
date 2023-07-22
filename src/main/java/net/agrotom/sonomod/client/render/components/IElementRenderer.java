package net.agrotom.sonomod.client.render.components;

import java.util.Arrays;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.Frame;
import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.events.GUIRenderEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public interface IElementRenderer<T extends IElement> {
	
	/**
	 * Need to be called before specific element etc. for tooltips.
	 * @param element to pre render
	 * @param event render event
	 */
	@SuppressWarnings("resource")
	public static void preRender(@Nonnull IElement element, @Nonnull GUIRenderEvent event) {
		if (element.isHovered()) {
			element.getTooltip().ifPresent(tooltip -> {
				final Frame parentFrame = event.getFrame();
				
				GuiUtils.drawHoveringText(event.getMatrix(), Arrays.asList(tooltip), event.getMouseX(), event.getMouseY(),
						parentFrame.width, parentFrame.height, -1, event.getMinecraft().font);
			});
		}
	}
	
	void render(@Nonnull T element, @Nonnull GUIRenderEvent event);

}
