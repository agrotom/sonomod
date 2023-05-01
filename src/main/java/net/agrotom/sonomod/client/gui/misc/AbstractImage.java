package net.agrotom.sonomod.client.gui.misc;

import net.agrotom.sonomod.util.RenderEvent;

public abstract class AbstractImage {
    
    protected int width = 0;
    protected int height = 0;
    
    public int getWidth() {
   	return width;
       }
       
    public int getHeight() {
	return height;
    }
    
    public abstract void render(RenderEvent event, int maxWidth, int maxHeight);
    
}
