package net.agrotom.sonomod.client.gui.misc;

import javax.annotation.Nonnull;

public class BackgroundImage {
    
    public enum FillPolicy {
	NOFILL,
	STRETCH,
	REPEAT
    }
    
    private Image image;
    
    public BackgroundImage(@Nonnull Image image) {
	this.image = image;
    }

    public void setImage(@Nonnull Image image) {
	this.image = image;
    }
    
    public Image getImage() {
	return image;
    }
}
