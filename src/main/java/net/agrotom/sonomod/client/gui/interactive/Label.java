package net.agrotom.sonomod.client.gui.interactive;

import javax.annotation.Nonnull;

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
public class Label extends Element
{

	protected final FontRenderer font;

	private ITextComponent text = new StringTextComponent("");

	private boolean isCentered = false;
	private boolean isWrapped = false;

	@SuppressWarnings("resource")
	public Label(ITextComponent text)
	{
		this.text = text;

		font = Minecraft.getInstance().font;

		this.setWidth(font.width(text.getContents()));
		this.setHeight(font.lineHeight);
	}

	public Label(String text)
	{
		this(new StringTextComponent(text).withStyle(TextFormatting.WHITE));
	}

	@Override
	public void render(RenderEvent event)
	{
	    	super.render(event);
	    
		final int resultX = event.getRelativeX() + this.getX();
		final int resultY = event.getRelativeY() + this.getY();

		final int stretchedWidth = Math.min(this.getWidth(), this.getMaxWidth());
		final int stretchedHeight = Math.min(this.getHeight(), this.getMaxHeight());

		if (isWrapped)
		{
			drawWrapShadow(resultX, resultY, stretchedWidth, stretchedHeight);
		} else
		{
			if (isCentered)
			{

				AbstractGui.drawCenteredString(event.getMatrix(), font, text, resultX, resultY, 0);
			} else
			{
				AbstractGui.drawString(event.getMatrix(), font, text, resultX, resultY, 0);
			}
		}
	}
	
	private void drawWrapShadow(int x, int y, int width, int height)
	{
		font.drawWordWrap(new StringTextComponent(text.getContents()).withStyle(TextFormatting.DARK_GRAY), x + 1, y + 1,
				width - 1, height - 1);
		font.drawWordWrap(text, x, y, width, height);
	}
	
	@Override
	public String toString() {
		return text.getContents();
	}

	public void setText(@Nonnull ITextComponent text)
	{
		this.text = text;
	}
	
	public void setText(@Nonnull String text)
	{
		this.text = new StringTextComponent(text).withStyle(TextFormatting.WHITE);
	}

	public ITextComponent getText()
	{
		return text;
	}

	public void setCentered(boolean isCentered)
	{
		this.isCentered = isCentered;
	}

	public boolean isCentered()
	{
		return isCentered;
	}

	public void setWrapped(boolean isWrapped)
	{
		if (!isWrapped)
		{
			this.setWidth(font.width(text.getContents()));
			this.setHeight(font.lineHeight);
		}

		this.isWrapped = isWrapped;
	}

	public boolean isWrapped()
	{
		return isWrapped;
	}

}
