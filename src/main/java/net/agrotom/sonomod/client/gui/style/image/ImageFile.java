package net.agrotom.sonomod.client.gui.style.image;

import javax.annotation.Nonnull;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class ImageFile extends AbstractImage {

	private ResourceLocation imagePath;
	
	private int offX = 0;
	private int offY = 0;
	
	public ImageFile(@Nonnull ResourceLocation imagePath, int width, int height) {
		super(width, height);
		this.imagePath = imagePath;
	}
	
	public void setImage(@Nonnull ResourceLocation imagePath) {
		this.imagePath = imagePath;
	}

	public ResourceLocation getImage() {
		return imagePath;
	}

	@Override
	public void blit(@Nonnull Minecraft minecraft, @Nonnull MatrixStack matrix, int x, int y) {
		minecraft.textureManager.bind(imagePath);
		
		AbstractGui.blit(matrix, x, y, offX, offY, width, height, width, height);
	}

}
