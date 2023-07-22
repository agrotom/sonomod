package net.agrotom.sonomod.client.gui.events;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.client.gui.Frame;
import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.Event;

public class GUIRenderEvent extends Event {

	private final int screenX;
	private final int screenY;
	
	private final Minecraft mc;
	
	private final MatrixStack matrix;
	private final int mouseX;
	private final int mouseY;
	private final float partialTick;
	
	private final Frame frame;
	
	public GUIRenderEvent(int screenX, int screenY, @Nonnull Minecraft minecraft, @Nonnull MatrixStack matrix, int mouseX,
			int mouseY, float partialTick, @Nonnull Frame parentFrame) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.mc = minecraft;
		this.matrix = matrix;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.partialTick = partialTick;
		this.frame = parentFrame;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}
	
	@Nonnull
	public Minecraft getMinecraft() {
		return mc;
	}

	@Nonnull
	public MatrixStack getMatrix() {
		return matrix;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public float getPartialTick() {
		return partialTick;
	}
	
	@Nonnull
	public Frame getFrame() {
		return frame;
	}
}
