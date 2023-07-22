package net.agrotom.sonomod.client.gui.style.skin;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;

public interface ISkin {
	
	void blit(@Nonnull Minecraft minecraft, @Nonnull MatrixStack matrix, int x, int y, int width, int height, boolean isHovered, boolean isDisabled);
	
}
