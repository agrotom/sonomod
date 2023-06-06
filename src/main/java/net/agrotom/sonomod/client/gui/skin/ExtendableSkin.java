package net.agrotom.sonomod.client.gui.skin;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.client.gui.interactive.ElementState;
import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.Vector2i;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExtendableSkin implements ISkin {

	private ResourceLocation image_location;

	private int spriteWidth = 200;
	private int spriteHeight = 20;

	private int spriteBorder = 3;

	private Vector2i baseOffset = new Vector2i(0, 0);
	private Vector2i hoverOffset = new Vector2i(0, 0);
	private Vector2i disabledOffset = new Vector2i(0, 0);

	private ExtendableSkin() {

	}

	public static Builder newBuilder() {
		return new ExtendableSkin().new Builder();
	}

	public void render(@Nonnull MatrixStack matrix, int x, int y, int width, int height, @Nonnull ElementState state) {
		ATUtils.ifMinecraftReady(minecraft -> {
			final TextureManager manager = minecraft.getTextureManager();
			manager.bind(image_location);

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
		final int computedSpriteH = this.spriteHeight - this.spriteBorder;
		final int computedOffY = offY + this.spriteBorder;
		
		int v = Math.min(this.spriteHeight, height);
		
		renderRow(matrix, x, y, offX, offY, width, v);
		
		for(; v < height - computedSpriteH; v += computedSpriteH - this.spriteBorder) {
			renderRow(matrix, x, y + v - this.spriteBorder, offX, computedOffY, width, computedSpriteH);
		}
		
		final int remainder = height - v - 1;
		
		if (remainder > 0) {
			renderRow(matrix, x, y + v - this.spriteBorder, offX, offY + this.spriteHeight - remainder - this.spriteBorder, width, remainder + this.spriteBorder);
		}
		else {
			renderRow(matrix, x, y + height - this.spriteBorder, offX, offY + this.spriteHeight - this.spriteBorder, width, this.spriteBorder);
		}
		
	}
	
	private void renderRow(@Nonnull MatrixStack matrix, int x, int y, int offX, int offY, int width,
			int height) {
		final int computedSpriteW = this.spriteWidth - this.spriteBorder;
		final int computedOffX = offX + this.spriteBorder;
		
		int u = Math.min(this.spriteWidth, width);
		
		AbstractGui.blit(matrix, x, y, offX, offY, u, height, 256, 256);

		for (; u < width - computedSpriteW; u += computedSpriteW - this.spriteBorder) {
			AbstractGui.blit(matrix, x + u - this.spriteBorder, y, computedOffX, offY, computedSpriteW,
					height, 256, 256);
		}

		final int remainder = width - u;

		if (remainder > 0) {
			AbstractGui.blit(matrix, x + u - this.spriteBorder, y,
					offX + this.spriteWidth - remainder - this.spriteBorder, offY, remainder + this.spriteBorder,
					height, 256, 256);
		}
		else {
			AbstractGui.blit(matrix, x + width - this.spriteBorder, y,
					offX + this.spriteWidth - this.spriteBorder,
					offY, this.spriteBorder, height, 256, 256);
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

		public ExtendableSkin build() {
			return ExtendableSkin.this;
		}
	}

}
