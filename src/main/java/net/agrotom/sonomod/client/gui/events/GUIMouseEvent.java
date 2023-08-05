package net.agrotom.sonomod.client.gui.events;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.client.gui.elements.IElement;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;

@OnlyIn(value = Dist.CLIENT)
public class GUIMouseEvent extends Event {

	private final Minecraft minecraft;
	
	private final int x;
	private final int y;
	
	private final IElement element;
	
	protected GUIMouseEvent(@Nonnull Minecraft minecraft, int x, int y, @Nonnull IElement element) {
		this.minecraft = minecraft;
		this.x = x;
		this.y = y;
		this.element = element;
	}
	
	public Minecraft getMinecraft() {
		return minecraft;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public IElement getElement() {
		return element;
	}

	public static class EnterEvent extends GUIMouseEvent {

		public EnterEvent(@Nonnull Minecraft minecraft, int x, int y, @Nonnull IElement element) {
			super(minecraft, x, y, element);
		}
		
	}
	
	public static class ExitEvent extends GUIMouseEvent {

		public ExitEvent(@Nonnull Minecraft minecraft, int x, int y, @Nonnull IElement element) {
			super(minecraft, x, y, element);
		}
		
	}
	
	public static class HoverEvent extends GUIMouseEvent {
		
		public HoverEvent(@Nonnull Minecraft minecraft, int x, int y, @Nonnull IElement element) {
			super(minecraft, x, y, element);
		}
		
	}
	
	public static class DragEvent extends GUIMouseEvent {

		public DragEvent(@Nonnull Minecraft minecraft, int x, int y, @Nonnull IElement element) {
			super(minecraft, x, y, element);
		}
		
		@Override
		public boolean isCancelable() {
			return true;
		}
		
	}
	
	public static class ActionEvent extends GUIMouseEvent {

		private final int keyID;
		
		public ActionEvent(@Nonnull Minecraft minecraft, int x, int y, int keyID, IElement element) {
			super(minecraft, x, y, element);
			this.keyID = keyID;
		}
		
		public int getKeyID() {
			return keyID;
		}
		
		@Override
		public boolean isCancelable() {
			return true;
		}
		
	}

}
