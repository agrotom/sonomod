package net.agrotom.sonomod.client.gui.interactive;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Label extends Element {

    protected final FontRenderer font;

    private ITextComponent text = new StringTextComponent("");
    
    private boolean isCentered = false;
    private boolean isWrapped = false;

    @SuppressWarnings("resource")
    public Label(ITextComponent text) {
	this.text = text;

	font = Minecraft.getInstance().font;

	this.width = font.width(text.getContents());
	this.height = font.lineHeight;
    }

    public Label(String text) {
	this(new StringTextComponent(text).withStyle(TextFormatting.WHITE));
    }

    @Override
    public void render(RenderEvent event) {
	final int resultX = event.getRelativeX() + x;
	final int resultY = event.getRelativeY() + y;

	final int stretchedWidth = Math.min(width, maxWidth);
	final int stretchedHeight = Math.min(height, maxHeight);

	if (isWrapped) { 
	    drawWrapShadow(resultX, resultY, stretchedWidth, stretchedHeight);
	} else {
	    if (isCentered) {
		
		AbstractGui.drawCenteredString(event.getMatrix(), font, text, resultX, resultY, 0);
	    }
	    else {
		AbstractGui.drawString(event.getMatrix(), font, text, resultX, resultY, 0);
	    }
	}
    }

    public void setText(ITextComponent text) {
	this.text = text;
    }

    public ITextComponent getText() {
	return text;
    }

    public void setCentered(boolean isCentered) {
	this.isCentered = isCentered;
    }

    public boolean isCentered() {
	return isCentered;
    }

    public void setWrapped(boolean isWrapped) {
	if (!isWrapped) {
	    this.width = font.width(text.getContents());
	    this.height = font.lineHeight;
	}

	this.isWrapped = isWrapped;
    }

    public boolean isWrapped() {
	return isWrapped;
    }
    
    private void drawWrapShadow(int x, int y, int width, int height) {
	font.drawWordWrap(new StringTextComponent(text.getContents())
		    .withStyle(TextFormatting.DARK_GRAY), x + 1, y + 1, width - 1, height - 1);
	font.drawWordWrap(text, x, y, width, height);
    }

}
