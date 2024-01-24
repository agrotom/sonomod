package net.agrotom.sonomod.client.gui.interactive;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.ElementType;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent.ActionEvent;
import net.agrotom.sonomod.util.ATUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class Button extends InteractiveElement {

	private ITextComponent label = null;
	
	public Button(@Nonnull ITextComponent label) {
		this.label = label;
	}
	
	public Button(@Nonnull String label) {
		this(new StringTextComponent(label).withStyle(TextFormatting.WHITE));
	}
	
	public void setLabel(@Nonnull ITextComponent label) {
		this.label = label;
		
		ATUtils.ifMinecraftReady(minecraft -> {
			this.setHeight(minecraft.font.wordWrapHeight(label.getContents(), this.getWidth()));
		});
	}
	
	public void setLabel(@Nonnull String label) {
		setLabel(new StringTextComponent(label));
	}
	
	public ITextComponent getLabel() {
		return label;
	}

	@Override
	public void mouseAction(@Nonnull ActionEvent event) {
		super.mouseAction(event);
		
	}

	@Override
	public ElementType<Button> getType() {
		return ElementType.BUTTON_TYPE;
	}

}
