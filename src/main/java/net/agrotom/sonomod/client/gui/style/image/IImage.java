package net.agrotom.sonomod.client.gui.style.image;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public interface IImage {
	
	void setWidth(int width);
	int getWidth();
	
	void setHeight(int height);
	int getHeight();
	
	void blit(@Nonnull Minecraft minecraft, @Nonnull MatrixStack matrix, int x, int y);
	
}
