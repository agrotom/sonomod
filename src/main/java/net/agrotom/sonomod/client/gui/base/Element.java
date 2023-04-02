package net.agrotom.sonomod.client.gui.base;

import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.events.ActionEvent;
import net.agrotom.sonomod.client.gui.events.HoverEvent;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class Element {
    
    private static final AbstractGui RENDERER = new AbstractGui() {};
    
    public static final int AUTO = Integer.MIN_VALUE;
    
    public int x = 0;
    public int y = 0;
    
    public int width = AUTO;
    public int maxWidth = AUTO;
    public int minWidth = AUTO;
    
    public int height = AUTO;
    public int maxHeight = AUTO;
    public int minHeight = AUTO;
    
    private Optional<Consumer<ActionEvent>> actionListener = Optional.empty();
    private Optional<Consumer<UpdateEvent>> updateListener = Optional.empty();
    private Optional<Consumer<HoverEvent>> hoverListener = Optional.empty();
    
    public void render(RenderEvent event) {
	RENDERER.blit(event.getMatrix(), event.getRelativeX() + x, event.getRelativeY() + y, event.getOffX(),
		event.getOffY(), width, height);
    }
    
    public Optional<Element> mouseAt(double mouseX, double mouseY) {
	if(mouseX >= x && mouseX <= x + width
		&& mouseY >= y && mouseY <= y + height) {
	    return Optional.of(this);
	}
	
	return Optional.empty();
    }
    
    public void setOnAction(@Nullable Consumer<ActionEvent> listener) {
	actionListener = Optional.ofNullable(listener);
    }
    
    public void onAction(ActionEvent event) {
	actionListener.ifPresent(listener -> listener.accept(event));
    }
    
    public void setOnUpdate(@Nullable Consumer<UpdateEvent> listener) {
	updateListener = Optional.ofNullable(listener);
    }
    
    public void onUpdate(UpdateEvent event) {
	updateListener.ifPresent(listener -> listener.accept(event));
    }
    
    public void setOnHover(@Nullable Consumer<HoverEvent> listener) {
	hoverListener = Optional.ofNullable(listener);
    }
    
    public void onHover(HoverEvent event) {
	hoverListener.ifPresent(listener -> listener.accept(event));
    }
    
}
