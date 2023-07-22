package net.agrotom.sonomod.client.gui.style.skin;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class ButtonSkin implements ISkin {

	private ResourceLocation path;
	
	private int defaultOffX = 0, defaultOffY = 0;
	private int hoverOffX = 0, hoverOffY = 0;
	private int disabledOffX = 0, disabledOffY = 0;
	
	private ButtonSkin() {}
	
	public static Builder newBuilder(@Nonnull ResourceLocation resourceLocation) {
		return new ButtonSkin().new Builder(resourceLocation);
	}
	
	@Override
	public void blit(@Nonnull Minecraft minecraft, @Nonnull MatrixStack matrix, int x, int y, int width, int height, boolean isHovered, boolean isDisabled) {
		minecraft.textureManager.bind(path);
		
		if (isDisabled) {
			blit(minecraft, matrix, x, y, width, height, isHovered, isDisabled, disabledOffX, disabledOffY);
		}
		else if(isHovered) {
			blit(minecraft, matrix, x, y, width, height, isHovered, isDisabled, hoverOffX, hoverOffY);
		}
		else {
			blit(minecraft, matrix, x, y, width, height, isHovered, isDisabled, defaultOffX, defaultOffY);
		}
	}
	
	private void blit(@Nonnull Minecraft minecraft, @Nonnull MatrixStack matrix, int x, int y, int width, int height,
			boolean isHovered, boolean isDisabled, int offX, int offY) {
		AbstractGui.blit(matrix, x, y, offX, offY, width, height, 256, 256);
	}
	
	public class Builder {
		
		private Builder(@Nonnull ResourceLocation resourceLocation) {
			path = resourceLocation;
		}
		
		public Builder setDefaultOffset(int x, int y) {
			defaultOffX = x;
			defaultOffY = y;
			
			return this;
		}
		
		public Builder setHoverOffset(int x, int y) {
			hoverOffX = x;
			hoverOffY = y;
			
			return this;
		}
		
		public Builder setDisabledOffset(int x, int y) {
			disabledOffX = x;
			disabledOffY = y;
			
			return this;
		}
		
		public ButtonSkin build() {
			return ButtonSkin.this;
		}
		
	}

}
