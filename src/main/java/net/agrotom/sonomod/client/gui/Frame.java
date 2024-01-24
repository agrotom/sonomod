package net.agrotom.sonomod.client.gui;

import java.util.Optional;
import java.util.Stack;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.client.gui.container.ILayout;
import net.agrotom.sonomod.client.gui.elements.IElement;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent.ActionEvent;
import net.agrotom.sonomod.client.gui.events.GUIRenderEvent;
import net.agrotom.sonomod.client.render.components.ElementRendererDispatcher;
import net.agrotom.sonomod.client.render.components.IElementRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;

@OnlyIn(value = Dist.CLIENT)
public abstract class Frame extends Screen {
	private IElement lastHoveredElement = null;
	private int screenX = 0;
	private int screenY = 0;
	
	private ILayout root = null;
	
	protected Frame(@Nonnull ITextComponent title) {
		super(title);
	}
	
	/**
	 * Implement 
	 * @return custom root (if null, {@link net.agrotom.sonomod.client.gui.Frame#init init} method throws exception)
	 */
	protected abstract ILayout getRoot();
	
	public abstract void initialize();
	
	private void finalizeInit() {
		root.constructLayout();
	}
	
	@Override
	protected void init() {
		root = getRoot();
		
		if (root == null) {
			throw new RuntimeException("\"getRoot\" method returned null!");
		}
		
		initialize();
		
		finalizeInit();
	}
	
	@Override
	public void tick() {
		screenX = (this.minecraft.screen.width - root.getWidth()) / 2;
		screenY = (this.minecraft.screen.height - root.getHeight()) / 2;
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int keyID) {
		final int intMouseX = (int) mouseX - screenX;
		final int intMouseY = (int) mouseY - screenY;
		final Optional<IElement> elementPresence = root.at(intMouseX, intMouseY);
		
		elementPresence.ifPresent(element -> element.mouseAction(new ActionEvent(minecraft, intMouseX, intMouseY, keyID, element)));
		
		return true;
	}
	
	@Override
	public void mouseMoved(double mouseX, double mouseY) {
		final int intMouseX = (int) mouseX - screenX;
		final int intMouseY = (int) mouseY - screenY;
		
		final Optional<IElement> elementPresence = root.at(intMouseX, intMouseY);

		elementPresence.ifPresent(newElement ->
		{
			if (lastHoveredElement != null) {
				if (newElement.isHovered() && lastHoveredElement.isHovered()) {
					final GUIMouseEvent.HoverEvent event = new GUIMouseEvent.HoverEvent(minecraft, intMouseX, intMouseY, lastHoveredElement);
			    	newElement.mouseHover(event);
			    	
			    	MinecraftForge.EVENT_BUS.post(event);
					return;
				}
				else {
					final GUIMouseEvent.ExitEvent exitEvent = new GUIMouseEvent.ExitEvent(minecraft, intMouseX, intMouseY, lastHoveredElement);
					final GUIMouseEvent.EnterEvent enterEvent = new GUIMouseEvent.EnterEvent(minecraft, intMouseX, intMouseY, newElement);
					
					lastHoveredElement.mouseExit(exitEvent);
					newElement.mouseEnter(enterEvent);
					lastHoveredElement = newElement;
					
					MinecraftForge.EVENT_BUS.post(exitEvent);
					MinecraftForge.EVENT_BUS.post(enterEvent);
				}
			}
			else {
				final GUIMouseEvent.EnterEvent event = new GUIMouseEvent.EnterEvent(minecraft, intMouseX, intMouseY, newElement);
				
				MinecraftForge.EVENT_BUS.post(event);
				
				lastHoveredElement = newElement;
				newElement.mouseEnter(event);
			}
		});

		if (!elementPresence.isPresent() && lastHoveredElement != null) {
			final GUIMouseEvent.ExitEvent event = new GUIMouseEvent.ExitEvent(minecraft, intMouseX, intMouseY, lastHoveredElement);
			
			MinecraftForge.EVENT_BUS.post(event);
			
			lastHoveredElement.mouseExit(event);
			lastHoveredElement = null;
		}
	}
	
	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTick) {
		
		final Stack<IElement> stack = new Stack<>();
		stack.push(root);
		
		final GUIRenderEvent renderEvent = new GUIRenderEvent(screenX, screenY, this.minecraft, matrix, mouseX, mouseY, partialTick, this, stack);
		
		while (!stack.isEmpty()) {
			final IElement top = stack.pop();
			
			ElementRendererDispatcher.instance.get(top.getType()).render(top, renderEvent);
			IElementRenderer.preRender(top, renderEvent);
		}
	}

}
