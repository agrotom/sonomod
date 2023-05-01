package net.agrotom.sonomod.client.gui.misc;

import java.awt.Color;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.util.RenderEvent;
import net.minecraft.client.gui.AbstractGui;

public class ImageFill extends AbstractImage {

    private Color colorFill = Color.WHITE;
    
    public ImageFill(int width, int height) {
	this.width = width;
	this.height = height;
    }
    
    public ImageFill(int width, int height, @Nonnull Color color) {
	this(width, height);
	
	this.colorFill = color;
    }
    
    public void setColor(@Nonnull Color color) {
	this.colorFill = color;
    }
    
    public Color getColor() {
	return colorFill;
    }
    
    public void setWidth(int width) {
	this.width = width;
    }
    
    public void setHeight(int height) {
	this.height = height;
    }
    
    @Override
    public void render(RenderEvent event, int maxWidth, int maxHeight) {
	AbstractGui.fill(event.getMatrix(), event.getRelativeX(), event.getRelativeY(),
		width < maxWidth ? width : maxWidth, height < maxHeight ? height : maxHeight, colorFill.getRGB());
    }
    
    

}
