package net.agrotom.sonomod.client.render.components;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.events.GUIRenderEvent;
import net.agrotom.sonomod.client.gui.style.TexturedRect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class TexturedRectRenderer extends AbstractElementRenderer<TexturedRect> {
	
	protected TexturedRectRenderer(@Nonnull ElementRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(@Nonnull TexturedRect element, @Nonnull GUIRenderEvent event) {
		final int resultX = event.getScreenX() + element.getX();
		final int resultY = event.getScreenY() + element.getY();
		
		element.getImage().blit(event.getMinecraft(), event.getMatrix(), resultX, resultY);
	}

}
