package net.agrotom.sonomod.client.gui.base;

import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.events.ActionEvent;
import net.agrotom.sonomod.client.gui.events.HoverEvent;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.client.gui.interactive.ElementState;
import net.agrotom.sonomod.client.gui.interactive.IControl;
import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.RenderEvent;
import net.agrotom.sonomod.util.Voider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class Element implements IControl
{

	public static final int AUTO = Integer.MIN_VALUE;
	
	private int x = 0;
	private int y = 0;

	private int width = AUTO;
	private int maxWidth = AUTO;
	private int minWidth = AUTO;

	private int height = AUTO;
	private int maxHeight = AUTO;
	private int minHeight = AUTO;

	private Optional<Consumer<ActionEvent>> actionListener = Optional.empty();
	private Optional<Consumer<UpdateEvent>> updateListener = Optional.empty();
	private Optional<Consumer<HoverEvent>> enterListener = Optional.empty();
	private Optional<Consumer<HoverEvent>> exitListener = Optional.empty();
	
	private ElementState state = ElementState.USUAL;
	

	public boolean ifEnabled(@Nonnull Voider lambda) {
		if(!isDisabled()) {
			lambda.apply();
			return true;
		}
		else {
			return false;
		}
	}
	
	public void playButtonSound(ISound sound) {
		ATUtils.ifMinecraftReady(() -> {
			this.ifEnabled(() -> {
				Minecraft.getInstance().getSoundManager().play(sound);
			});
		});
	}
	
	public void render(RenderEvent event)
	{
		AbstractGui.blit(event.getMatrix(), event.getRelativeX() + x, event.getRelativeY() + y, event.getOffX(),
				event.getOffY(), width, height, 256, 256);
	}

	public Optional<Element> mouseAt(double mouseX, double mouseY)
	{
		if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height)
		{
			return Optional.of(this);
		}

		return Optional.empty();
	}

	@Override
	public boolean isHovered()
	{
		return state == ElementState.HOVER;
	}

	@Override
	public void setOnAction(@Nullable Consumer<ActionEvent> listener)
	{
		actionListener = Optional.ofNullable(listener);
	}

	@Override
	public void onAction(ActionEvent event)
	{
		actionListener.ifPresent(listener -> listener.accept(event));
	}

	@Override
	public void setOnUpdate(@Nullable Consumer<UpdateEvent> listener)
	{
		updateListener = Optional.ofNullable(listener);
	}

	@Override
	public void onUpdate(UpdateEvent event)
	{
		updateListener.ifPresent(listener -> listener.accept(event));
	}

	@Override
	public void setOnEnter(@Nullable Consumer<HoverEvent> listener)
	{
		enterListener = Optional.ofNullable(listener);;
	}
	
	@Override
	public void onEnter(HoverEvent event) {
		if(state != ElementState.DISABLED) {
			state = ElementState.HOVER;
		}
		
		enterListener.ifPresent(listener -> listener.accept(event));
	}

	@Override
	public void setOnExit(@Nullable Consumer<HoverEvent> listener)
	{
		exitListener = Optional.ofNullable(listener);;
	}
	
	@Override
	public void onExit(HoverEvent event) {
		if(state != ElementState.DISABLED) {
			state = ElementState.USUAL;
		}
		
		exitListener.ifPresent(listener -> listener.accept(event));
	}
	
	public void setDisabled() {
		state = ElementState.DISABLED;
	}
	
	public void setEnabled() {
		if(state == ElementState.DISABLED) {
			state = ElementState.USUAL;
		}
	}
	
	public boolean isDisabled() {
		return state == ElementState.DISABLED;
	}
	
	public ElementState getState() {
		return state;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getMaxWidth()
	{
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth)
	{
		this.maxWidth = maxWidth;
	}

	public int getMinWidth()
	{
		return minWidth;
	}

	public void setMinWidth(int minWidth)
	{
		this.minWidth = minWidth;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getMaxHeight()
	{
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight)
	{
		this.maxHeight = maxHeight;
	}

	public int getMinHeight()
	{
		return minHeight;
	}

	public void setMinHeight(int minHeight)
	{
		this.minHeight = minHeight;
	}

	public static int getAuto()
	{
		return AUTO;
	}
	
}
