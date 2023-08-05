package net.agrotom.sonomod.client.render.components;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.events.GUIRenderEvent;
import net.agrotom.sonomod.client.gui.interactive.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.ITextComponent;

public class ButtonRenderer extends AbstractElementRenderer<Button> {

	public ButtonRenderer(@Nonnull ElementRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(@Nonnull Button element, @Nonnull GUIRenderEvent event) {
		final int resultX = event.getScreenX() + element.getX();
		final int resultY = event.getScreenY() + element.getY();
		
		final int width = element.getWidth();
		final int height = element.getHeight();
		
		final Minecraft mc = event.getMinecraft();
		
		
		element.getSkin().blit(mc, event.getMatrix(), resultX, resultY, width, height, element.isHovered(), false);

		final ITextComponent label = element.getLabel();
		AbstractGui.drawCenteredString(event.getMatrix(), mc.font, label,
				resultX + width / 2, (height - mc.font.lineHeight) / 2 + resultY, label.getStyle().getColor().getValue());
	}

}