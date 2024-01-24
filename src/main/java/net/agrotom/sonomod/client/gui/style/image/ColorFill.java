package net.agrotom.sonomod.client.gui.style.image;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;

public class ColorFill extends AbstractImage {

	private int color = 0xFFFFFFFF;
	
	public ColorFill(int width, int height) {
		super(width, height);
	}
	
	public ColorFill(int width, int height, int color) {
		super(width, height);
		this.color = color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}

	@Override
	public void blit(Minecraft minecraft, MatrixStack matrix, int x, int y) {
		Screen.fill(matrix, x, y, x + width, y + height, color);
	}

}
