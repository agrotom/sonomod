package net.agrotom.sonomod.client.gui.containers;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.util.RenderEvent;

public class Window {

    public Layout rootLayout;
    
    private int width = 0;
    private int height = 0;
    
    public boolean resizeable = false;
    
    public Window(Layout root) {
	rootLayout = root;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
	if(width > 0) {
	    this.width = width;
	    rootLayout.width = width;
	    rootLayout.maxWidth = width;
	}
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
	if(height > 0) {
	    this.height = height;
	    rootLayout.height = height;
	    rootLayout.maxHeight = height;
	}
    }
    
    public void renderAll(MatrixStack matrix, int x, int y, int mouseX, int mouseY) {
	rootLayout.x = x;
	rootLayout.y = y;
	rootLayout.render(new RenderEvent(matrix).setRelativeX(x).setRelativeY(y).setMouseX(mouseX).setMouseY(mouseY));
    }
    
}
