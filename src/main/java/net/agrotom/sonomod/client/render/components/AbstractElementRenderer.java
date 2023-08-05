package net.agrotom.sonomod.client.render.components;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.elements.IElement;

public abstract class AbstractElementRenderer<T extends IElement> implements IElementRenderer<T> {
	public final ElementRendererDispatcher dispatcher;
	
	protected AbstractElementRenderer(@Nonnull ElementRendererDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
}
