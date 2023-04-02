package net.agrotom.sonomod.client.gui.interactive;

import java.util.ArrayList;
import java.util.List;

import net.agrotom.sonomod.client.gui.base.Element;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.INestedGuiEventHandler;

public abstract class Control extends Element implements INestedGuiEventHandler {

    private final List<? extends IGuiEventListener> children = new ArrayList<>();
    private IGuiEventListener focusListener = null;
    
    private boolean isDragging = false;
    
    
    @Override
    public List<? extends IGuiEventListener> children() {
	return children;
    }

    @Override
    public boolean isDragging() {
	return isDragging;
    }

    @Override
    public void setDragging(boolean isDragging) {
	this.isDragging = isDragging;
    }

    @Override
    public IGuiEventListener getFocused() {
	return focusListener;
    }

    @Override
    public void setFocused(IGuiEventListener focused) {
	focusListener = focused;
    }

    
    
}
