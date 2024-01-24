package net.agrotom.sonomod.client.gui.events;

import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.client.gui.Frame;
import net.agrotom.sonomod.client.gui.elements.IElement;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;

@OnlyIn(value = Dist.CLIENT)
public class GUIRenderEvent extends Event {

	private final int screenX;
	private final int screenY;
	
	private final Minecraft mc;
	
	private final MatrixStack matrix;
	private final int mouseX;
	private final int mouseY;
	private final float partialTick;
	
	private final Frame frame;
	
	private final List<IElement> toRenderQueue;
	
	public GUIRenderEvent(int screenX, int screenY, @Nonnull Minecraft minecraft, @Nonnull MatrixStack matrix, int mouseX,
			int mouseY, float partialTick, @Nonnull Frame parentFrame, @Nonnull List<IElement> toRenderQueue) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.mc = minecraft;
		this.matrix = matrix;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.partialTick = partialTick;
		this.frame = parentFrame;
		this.toRenderQueue = toRenderQueue;
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
	
	@Nonnull
	public List<IElement> getQueue() {
		return toRenderQueue;
	}
	
	public static class Pre extends GUIRenderEvent {

		public Pre(int screenX, int screenY, Minecraft minecraft, MatrixStack matrix, int mouseX, int mouseY,
				float partialTick, Frame parentFrame, List<IElement> toRenderQueue) {
			super(screenX, screenY, minecraft, matrix, mouseX, mouseY, partialTick, parentFrame, toRenderQueue);
		}
		
	}
}
