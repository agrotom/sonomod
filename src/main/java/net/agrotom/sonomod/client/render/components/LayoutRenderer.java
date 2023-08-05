package net.agrotom.sonomod.client.render.components;

import java.util.List;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.container.ILayout;
import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.events.GUIRenderEvent;

public class LayoutRenderer extends AbstractElementRenderer<ILayout> {
	
	public LayoutRenderer(@Nonnull ElementRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(@Nonnull ILayout element, @Nonnull GUIRenderEvent event) {
		final List<IElement> queue = event.getQueue();
		queue.addAll(element.getChildren());
		
		element.getBackground().ifPresent(background -> {
			queue.add(background);
		});
	}

}
