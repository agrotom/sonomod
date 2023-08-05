package net.agrotom.sonomod.client.gui.elements;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.ElementType;
import net.agrotom.sonomod.util.ATUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class TextArea extends AbstractElement {

	private boolean wrapped = false;
	private boolean centered = false;
	private boolean locked = false;
	
	private ITextComponent text;
	
	public TextArea(@Nonnull String text) {
		this.setText(text);
	}
	
	public TextArea(@Nonnull ITextComponent text) {
		this.setText(text);
	}
	
	public void setText(@Nonnull String text) {
		setText(new StringTextComponent(text).withStyle(TextFormatting.WHITE));
	}
	
	public void setText(@Nonnull ITextComponent text) {
		this.text = text;
		if (!locked) {
			ATUtils.ifMinecraftReady(minecraft -> {
				this.setHeight(minecraft.font.wordWrapHeight(text.getContents(), this.getWidth()));
			});
		}
	}
	
	public ITextComponent getText() {
		return text;
	}
	
	public void setWrapped(boolean wrapped) {
		this.wrapped = wrapped;
	}
	
	public boolean isWrapped() {
		return wrapped;
	}
	
	public void setCentered(boolean centered) {
		this.centered = centered;
	}
	
	public boolean isCentered() {
		return centered;
	}
	
	/**
	 * Locks an auto resize of an element's height when using wrapped areas.
	 */
	public void lockAutoResize() {
		locked = true;
	}
	
	public void unlockAutoResize() {
		locked = false;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	@Override
	public ElementType<TextArea> getType() {
		return ElementType.TEXT_AREA_TYPE;
	}

}
