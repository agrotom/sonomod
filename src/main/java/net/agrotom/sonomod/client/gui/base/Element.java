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
import net.agrotom.sonomod.util.props.IntegerProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class Element implements IControl
{

	public static final int AUTO = -1;
	public static final int MAX_VALUE = Integer.MAX_VALUE;
	public static final int MIN_VALUE = 0;
	
	private final IntegerProperty x = new IntegerProperty(0);
	private final IntegerProperty y = new IntegerProperty(0);
	
	private int prefWidth = AUTO;
	private int prefHeight = AUTO;
	
	//private int width = AUTO;
	private int maxWidth = MAX_VALUE;
	private int minWidth = MIN_VALUE;

	//private int height = AUTO;
	private int maxHeight = MAX_VALUE;
	private int minHeight = MIN_VALUE;
	
	private final IntegerProperty width = new IntegerProperty(AUTO, newValue -> {
	    return MathHelper.clamp(newValue, minWidth, maxWidth) == newValue;
	});
	private final IntegerProperty height = new IntegerProperty(AUTO, newValue -> {
	    return MathHelper.clamp(newValue, minHeight, maxHeight) == newValue;
	});

	private Optional<Consumer<ActionEvent>> actionListener = Optional.empty();
	private Optional<Consumer<UpdateEvent>> updateListener = Optional.empty();
	private Optional<Consumer<HoverEvent>> enterListener = Optional.empty();
	private Optional<Consumer<HoverEvent>> exitListener = Optional.empty();
	private Optional<Consumer<HoverEvent>> hoverListener = Optional.empty();
	
	private ElementState state = ElementState.USUAL;
	
	protected void blit(RenderEvent event, int offX, int offY) {
	    AbstractGui.blit(event.getMatrix(), event.getRelativeX() + x.getValue(), event.getRelativeY() + y.getValue(), offX,
			offY, width.getValue(), height.getValue(), 256, 256);
	}

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
	
	public void render(RenderEvent event) {
	    
	}

	public Optional<Element> mouseAt(double mouseX, double mouseY)
	{
		if (mouseX >= x.getValue() && mouseX <= x.getValue() + width.getValue() && 
			mouseY >= y.getValue() && mouseY <= y.getValue() + height.getValue())
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
	
	@Override
	public void setOnHover(Consumer<HoverEvent> listener) {
	    hoverListener = Optional.ofNullable(listener);
	}
	
	@Override
	public void onHover(HoverEvent event) {
	    hoverListener.ifPresent(listener -> listener.accept(event));
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
	
	public IntegerProperty xProperty() {
	    return x;
	}
	
	public IntegerProperty yProperty() {
	    return y;
	}
	
	public IntegerProperty widthProperty() {
	    return width;
	}
	
	public IntegerProperty heightProperty() {
	    return height;
	}

	public int getPrefWidth() {
	    return prefWidth;
	}

	public void setPrefWidth(int prefWidth) {
	    this.prefWidth = prefWidth;
	}

	public int getPrefHeight() {
	    return prefHeight;
	}

	public void setPrefHeight(int prefHeight) {
	    this.prefHeight = prefHeight;
	}

	public void setX(int x) {
		this.x.setValue(x);
	}
	
	public int getX() {
		return x.getValue();
	}
	
	public void setY(int y) {
		this.y.setValue(y);
	}
	
	public int getY() {
		return y.getValue();
	}

	public int getWidth()
	{
		return width.getValue();
	}

	public void setWidth(int width)
	{
		this.width.setValue(width);;
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
		return height.getValue();
	}

	public void setHeight(int height)
	{
		this.height.setValue(height);
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
	
}
