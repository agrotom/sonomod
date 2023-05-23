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
import net.agrotom.sonomod.util.props.IProperty;
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
	
	private final IProperty<Integer> x = new IntegerProperty(0);
	private final IProperty<Integer> y = new IntegerProperty(0);
	
	private int prefWidth = AUTO;
	private int prefHeight = AUTO;
	
	//private int width = AUTO;
	private int maxWidth = MAX_VALUE;
	private final IProperty<Integer> maxWidthProperty = new IntegerProperty(MAX_VALUE, newValue -> {
		return newValue >= 0;
	});
	private int minWidth = MIN_VALUE;
	private final IProperty<Integer> minWidthProperty = new IntegerProperty(MIN_VALUE, newValue -> {
		return newValue >= 0;
	});

	//private int height = AUTO;
	private int maxHeight = MAX_VALUE;
	private final IProperty<Integer> maxHeightProperty = new IntegerProperty(MAX_VALUE, newValue -> {
		return newValue >= 0;
	});
	private int minHeight = MIN_VALUE;
	private final IProperty<Integer> minHeightProperty = new IntegerProperty(MIN_VALUE, newValue -> {
		return newValue >= 0;
	});
	
	private final IProperty<Integer> widthProperty = new IntegerProperty(MIN_VALUE, newValue -> {
	    return MathHelper.clamp(newValue, minWidth, maxWidth) == newValue;
	}).setOnUpdate((self, caller) -> {
		self.setValue(self.getValue()); // Updating a value according to new boundaries
	});
	private final IProperty<Integer> heightProperty = new IntegerProperty(MIN_VALUE, newValue -> {
	    return MathHelper.clamp(newValue, minHeight, maxHeight) == newValue;
	}).setOnUpdate((self, caller) -> {
		self.setValue(self.getValue());
	});

	private Optional<Consumer<ActionEvent>> actionListener = Optional.empty();
	private Optional<Consumer<UpdateEvent>> updateListener = Optional.empty();
	private Optional<Consumer<HoverEvent>> enterListener = Optional.empty();
	private Optional<Consumer<HoverEvent>> exitListener = Optional.empty();
	private Optional<Consumer<HoverEvent>> hoverListener = Optional.empty();
	
	private ElementState state = ElementState.USUAL;
	
	{
		try {
			maxWidthProperty.bindDependency(widthProperty);
			minWidthProperty.bindDependency(widthProperty);
			
			maxHeightProperty.bindDependency(heightProperty);
			minHeightProperty.bindDependency(heightProperty);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void blit(RenderEvent event, int offX, int offY) {
	    AbstractGui.blit(event.getMatrix(), event.getRelativeX() + x.getValue(), event.getRelativeY() + y.getValue(), offX,
			offY, widthProperty.getValue(), heightProperty.getValue(), 256, 256);
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
		if (mouseX >= x.getValue() && mouseX <= x.getValue() + widthProperty.getValue() && 
			mouseY >= y.getValue() && mouseY <= y.getValue() + heightProperty.getValue())
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
	
	public IProperty<Integer> xProperty() {
	    return x;
	}
	
	public IProperty<Integer> yProperty() {
	    return y;
	}
	
	public IProperty<Integer> widthProperty() {
	    return widthProperty;
	}
	
	public IProperty<Integer> heightProperty() {
	    return heightProperty;
	}

	public int getPrefWidth() {
	    return prefWidth;
	}

	public void setPrefWidth(int prefWidth) {
	    this.prefWidth = ATUtils.clampToPositive(prefWidth);
	}

	public int getPrefHeight() {
	    return prefHeight;
	}

	public void setPrefHeight(int prefHeight) {
	    this.prefHeight = ATUtils.clampToPositive(prefHeight);
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
		return widthProperty.getValue();
	}

	public void setWidth(int width)
	{
		this.widthProperty.setValue(width);;
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
		return heightProperty.getValue();
	}

	public void setHeight(int height)
	{
		this.heightProperty.setValue(height);
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
