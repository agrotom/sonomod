package net.agrotom.sonomod.client.render.components;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.elements.TextArea;
import net.agrotom.sonomod.client.gui.events.GUIRenderEvent;
import net.agrotom.sonomod.util.RenderUtils;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class TextAreaRenderer extends AbstractElementRenderer<TextArea> {

	public TextAreaRenderer(@Nonnull ElementRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(@Nonnull TextArea element, @Nonnull GUIRenderEvent event) {
		@SuppressWarnings("resource")
		final FontRenderer font = event.getMinecraft().font;
		final ITextComponent textToRender = element.getText();
		
		final int resultX = element.getX() + event.getScreenX();
		final int resultY = element.getY() + event.getScreenY();
		
		if (element.isWrapped()) {
			RenderUtils.drawWrapShadow(font, textToRender, resultX, resultY, element.getWidth(), element.getHeight());
		}
		else if(element.isCentered()) {
			AbstractGui.drawCenteredString(event.getMatrix(), font, textToRender, resultX, resultY, TextFormatting.WHITE.getColor());
		}
		else {
			AbstractGui.drawString(event.getMatrix(), font, textToRender, resultX, resultY, TextFormatting.WHITE.getColor());
		}
	}

}
