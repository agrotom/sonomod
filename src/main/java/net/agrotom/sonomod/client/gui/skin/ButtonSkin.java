package net.agrotom.sonomod.client.gui.skin;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.client.gui.interactive.ElementState;
import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.Vector2i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class ButtonSkin implements ISkin
{

	private ResourceLocation image_location;
	
	private int spriteWidth = 0;
	private int spriteHeight = 0;
	
	private Vector2i baseOffset = new Vector2i(0, 0);
	private Vector2i hoverOffset = new Vector2i(0, 0);
	private Vector2i disabledOffset = new Vector2i(0, 0);
	
	private ButtonSkin() {
		
	}
	
	public static Builder newBuilder() {
		return new ButtonSkin().new Builder();
	}
	
	public void render(@Nonnull MatrixStack matrix, int x, int y, int width, int height, @Nonnull ElementState state) {
		if(ATUtils.isMinecraftReady()) {
			final TextureManager manager = Minecraft.getInstance().getTextureManager();
			manager.bind(image_location);
			
			switch(state) {
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
		}
	}
	
	private void renderCurrentState(@Nonnull MatrixStack matrix, int x, int y, int offX, int offY, int width, int height) {
		final int halfWidth = width / 2;
		final int halfHeight = height / 2;
		
		AbstractGui.blit(matrix, x, y, offX, offY, halfWidth, halfHeight, 256, 256);
		
		AbstractGui.blit(matrix, x + halfWidth, y, offX + (this.spriteWidth - halfWidth), offY,
				halfWidth, halfHeight, 256, 256);
		
		AbstractGui.blit(matrix, x, y + halfHeight, offX, offY + (this.spriteHeight - halfHeight),
				halfWidth, halfHeight, 256, 256);
		
		AbstractGui.blit(matrix, x + halfWidth, y + halfHeight, offX + (this.spriteWidth - halfWidth),
				offY + (this.spriteHeight - halfHeight), halfWidth, halfHeight, 256, 256);
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
		
		public ButtonSkin build() {
			return ButtonSkin.this;
		}
	}

}
