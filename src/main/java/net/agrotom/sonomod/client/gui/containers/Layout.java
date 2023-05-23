package net.agrotom.sonomod.client.gui.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.client.gui.interactive.ImageHolder;
import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Layout extends Element {
	public static final int OUT_OF_BOUNDS_X = Integer.MIN_VALUE;
	
	public final List<Element> children = new ArrayList<Element>();
	
	private int padding = 0;
	private int margin = 0;
	
	private StretchPolicy widthPolicy = StretchPolicy.MAX;
	private StretchPolicy heightPolicy = StretchPolicy.MIN;

	private Optional<ImageHolder> background = Optional.empty();
	
	private void bindAllChildren() {
		this.children.forEach(element -> {
			try {
				this.xProperty().bindTwoDirectional(element.xProperty());
				this.yProperty().bindTwoDirectional(element.yProperty());

				this.widthProperty().bindTwoDirectional(element.widthProperty());
				this.heightProperty().bindTwoDirectional(element.heightProperty());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Layout(@Nonnull List<Element> list) {
		this.children.addAll(list);

		bindAllChildren();
	}

	public Layout(@Nonnull Element... children) {
		this(Arrays.asList(children));
	}

	@Override
	public void render(@Nonnull RenderEvent event) {
		background.ifPresent(image -> {
			image.render(event);
		});

		children.forEach(child -> {
			if(child.getX() != OUT_OF_BOUNDS_X) {
				child.render(event);
			}
		});
	}

	@Override
	public void onUpdate(@Nonnull UpdateEvent event) {
		super.onUpdate(event);
		background.ifPresent(image -> {
			image.onUpdate(event);
		});
		
		children.forEach(child -> child.onUpdate(event));
	}

	@Override
	public Optional<Element> mouseAt(double mouseX, double mouseY) {
		for (Element child : children) {
			final Optional<Element> childPresent = child.mouseAt(mouseX - this.getX(), mouseY - this.getY());

			// System.out.println(childPresent.isPresent());

			if (childPresent.isPresent()) {
				return childPresent;
			}
		}

		return super.mouseAt(mouseX, mouseY);
	}
	
	public void addElement(@Nonnull Element element) {
		children.add(element);
		
		this.onUpdate(new UpdateEvent());
	}
	
	public void addElement(@Nonnull Element... element) {
		children.addAll(Arrays.asList(element));
		
		this.onUpdate(new UpdateEvent());
	}
	
	public void removeElement(@Nonnull Element element) {
		children.remove(element);
	}
	
	public void setBackground(@Nullable ImageHolder imageHolder) {
		background = Optional.ofNullable(imageHolder);
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = ATUtils.clampToPositive(padding);
	}
	
	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = ATUtils.clampToPositive(margin);
	}

	public StretchPolicy getWidthPolicy() {
		return widthPolicy;
	}

	public void setWidthPolicy(@Nonnull StretchPolicy widthPolicy) {
		this.widthPolicy = widthPolicy;
	}

	public StretchPolicy getHeightPolicy() {
		return heightPolicy;
	}

	public void setHeightPolicy(@Nonnull StretchPolicy heightPolicy) {
		this.heightPolicy = heightPolicy;
	}
}
