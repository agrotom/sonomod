package net.agrotom.sonomod.client.gui.interactive;

import java.util.Optional;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImageHolder extends Element {

    private Optional<ResourceLocation> image_location = Optional.empty();
    private int offX = 0;
    private int offY = 0;
    
    public ImageHolder(ResourceLocation image_location) {
	this.image_location = Optional.ofNullable(image_location);
    }
    
    public ImageHolder(ResourceLocation image_location, int offX, int offY) {
	this(image_location);
	this.offX = offX;
	this.offY = offY;
    }
    
    @Override
    public void render(RenderEvent event) {
	image_location.ifPresent(image -> {
	    Minecraft.getInstance().getTextureManager().bind(image);
	});
	
        super.render(event.setOffX(offX).setOffY(offY));
    }
    
    public void setOffX(int offX) {
	if(offX < 0) {
	    this.offX = 0;
	}
	else {
	    this.offX = offX;
	}
    }
    
    public int getOffX() {
	return offX;
    }
    
    public void setOffY(int offY) {
	if(offY < 0) {
	    this.offY = 0;
	}
	else {
	    this.offY = offY;
	}
    }
    
    public int getOffY() {
	return offY;
    }
    
}
