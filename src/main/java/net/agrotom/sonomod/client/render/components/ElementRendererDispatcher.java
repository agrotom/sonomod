package net.agrotom.sonomod.client.render.components;

import java.util.Map;
import javax.annotation.Nonnull;

import com.google.common.collect.Maps;

import net.agrotom.sonomod.client.gui.ElementType;
import net.agrotom.sonomod.client.gui.elements.IElement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class ElementRendererDispatcher {

	public static final ElementRendererDispatcher instance = new ElementRendererDispatcher();	
	
	private Map<String, IElementRenderer<?>> renderers = Maps.newHashMapWithExpectedSize(10);
	
	public ElementRendererDispatcher() {
		this.registerRenderer(ElementType.TEXTURED_RECT_TYPE, new TexturedRectRenderer(this));
		this.registerRenderer(ElementType.BUTTON_TYPE, new ButtonRenderer(this));
		this.registerRenderer(ElementType.LAYOUT_TYPE, new LayoutRenderer(this));
		this.registerRenderer(ElementType.TEXT_AREA_TYPE, new TextAreaRenderer(this));
	}
	
	public <T extends IElement> void registerRenderer(@Nonnull ElementType<?> type, @Nonnull IElementRenderer<T> renderer) {
		renderers.put(type.id, renderer);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends IElement> IElementRenderer<T> get(@Nonnull ElementType<?> type) {
		return (IElementRenderer<T>) renderers.get(type.id);
	}

}
