package net.agrotom.sonomod.client.gui.interactive;

import java.util.Optional;

import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.events.HoverEvent;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraft.util.ResourceLocation;

public class Button extends Element {
    public static final ResourceLocation DEFAULT_BUTTON = new ResourceLocation("textures/gui/icons.png");
    
    private ImageHolder button_image = new ImageHolder(DEFAULT_BUTTON, 0, 66);
    private Optional<Label> button_label = Optional.ofNullable(null);
    
    private int baseOffX = 0;
    private int baseOffY = 66;
    
    private int hoverOffX = 0;
    private int hoverOffY = 86;
    
    public Button(ImageHolder buttonTexture) {
	this(buttonTexture, null);
    }
    
    public Button(Label text) {
	this(null, text);
    }
    
    public Button(@Nullable ImageHolder buttonTexture, @Nullable Label text) {
	if(buttonTexture != null) {
	    button_image = buttonTexture;
	}
	
	button_label = Optional.ofNullable(text);
    }
    
    @Override
    public void render(RenderEvent event) {
        super.render(event);
        
        button_image.render(event);
        button_label.ifPresent(label -> {
            label.render(event);
        });
    }
    
    @Override
    public void onUpdate(UpdateEvent event) {
        super.onUpdate(event);
        
        button_label.ifPresent(label -> {
            label.setWrapped(false);
            label.setCentered(true);
        });
    }
    
    @Override
    public void onHover(HoverEvent event) {
        super.onHover(event);
        
         
        
        
    }
    
    public void setTexture(ImageHolder buttonTexture) {
	if(buttonTexture != null) {
	    button_image = buttonTexture;
	}
	else {
	    button_image = new ImageHolder(DEFAULT_BUTTON, 0, 66);
	}
    }
    
    public void setLabel(Label text) {
	button_label = Optional.ofNullable(text);
    }

    public int getHoverOffX() {
        return hoverOffX;
    }

    public void setHoverOffX(int hoverOffX) {
        this.hoverOffX = ATUtils.clampToPositive(hoverOffX);
    }

    public int getHoverOffY() {
        return hoverOffY;
    }

    public void setHoverOffY(int hoverOffY) {
        this.hoverOffY = ATUtils.clampToPositive(hoverOffY);
    }
    
}
