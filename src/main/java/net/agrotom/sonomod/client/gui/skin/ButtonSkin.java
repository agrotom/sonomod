package net.agrotom.sonomod.client.gui.skin;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.client.gui.interactive.ElementState;
import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.Vector2i;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.IntIdentityHashBiMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButtonSkin implements ISkin {

    private ResourceLocation image_location;

    private int spriteWidth = 200;
    private int spriteHeight = 20;

    private int spriteBorder = 1;

    private Vector2i baseOffset = new Vector2i(0, 0);
    private Vector2i hoverOffset = new Vector2i(0, 0);
    private Vector2i disabledOffset = new Vector2i(0, 0);

    private ButtonSkin() {

    }

    public static Builder newBuilder() {
	return new ButtonSkin().new Builder();
    }

    public void render(@Nonnull MatrixStack matrix, int x, int y, int width, int height, @Nonnull ElementState state) {
	ATUtils.ifMinecraftReady(minecraft -> {
	    final TextureManager manager = minecraft.getTextureManager();
	    manager.bind(Widget.WIDGETS_LOCATION);

	    switch (state) {
	    case USUAL:
		renderCurrentState(matrix, x, y, baseOffset.x, baseOffset.y, width, height);
		break;

	    case HOVER:
		renderCurrentState(matrix, x, y, hoverOffset.x, hoverOffset.y, width, height);
		break;

	    case DISABLED:
		renderCurrentState(matrix, x, y, disabledOffset.x, disabledOffset.y, width, height);
		break;

	    default:
		break;
	    }
	});
    }

    private void renderCurrentState(@Nonnull MatrixStack matrix, int x, int y, int offX, int offY, int width,
	    int height) {

	this.spriteBorder = 2;
	
	final int computedOffX = offX + this.spriteBorder,
		computedOffY = offY + this.spriteBorder;
	
	final int computedSpriteW = this.spriteWidth - this.spriteBorder;
	final int computedSpriteH = this.spriteHeight - 2 * this.spriteBorder;
	
	int u = 0, v = this.spriteHeight;
	
	for(; ;) {
	
        	AbstractGui.blit(matrix, x, y, offX, offY, this.spriteWidth,
        		this.spriteHeight, 256, 256);
        	
        	u += this.spriteWidth;
        	
        	int i = 1;
        	
        	for(; u < width - this.spriteWidth;
        		u += computedSpriteW - this.spriteBorder, i++) {
        	    AbstractGui.blit(matrix, x + u - this.spriteBorder, y, computedOffX, offY, computedSpriteW,
        			this.spriteHeight, 256, 256);
        	}
        	
        	final int remainder = width - u;
        	
        	AbstractGui.blit(matrix, x + u - this.spriteBorder, y, computedOffX + this.spriteWidth - remainder - i * this.spriteBorder, offY, remainder + i * this.spriteBorder, this.spriteHeight, 256, 256);
        	
	}
    }

    public class Builder {

	public Builder() {

	}

	public Builder setImagePath(@Nonnull ResourceLocation path) {
	    image_location = path;

	    return this;
	}

	public Builder setBaseOffset(int x, int y) {
	    baseOffset.x = x;
	    baseOffset.y = y;

	    return this;
	}

	public Builder setHoverOffset(int x, int y) {
	    hoverOffset.x = x;
	    hoverOffset.y = y;

	    return this;
	}

	public Builder setDisabledOffset(int x, int y) {
	    disabledOffset.x = x;
	    disabledOffset.y = y;

	    return this;
	}

	public Builder setSpriteWidth(int width) {
	    spriteWidth = width;

	    return this;
	}

	public Builder setSpriteHeight(int height) {
	    spriteHeight = height;

	    return this;
	}

	public Builder setSpriteBorder(int border) {
	    spriteBorder = border;

	    return this;
	}

	public ButtonSkin build() {
	    return ButtonSkin.this;
	}
    }

}
