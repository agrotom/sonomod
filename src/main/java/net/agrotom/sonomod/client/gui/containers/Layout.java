package net.agrotom.sonomod.client.gui.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.client.gui.interactive.ImageHolder;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Layout extends Element {
	public final List<Element> children = new ArrayList<Element>();
	protected int padding = 5;

	private Optional<ImageHolder> background = Optional.empty();

	public Layout(Element... children) {
		this.children.addAll(Arrays.asList(children));
	}

	public Layout(List<Element> list) {
		this.children.addAll(list);
	}

	@Override
	public void render(RenderEvent event) {
		background.ifPresent(image ->
		{
			image.render(event);
		});

		children.forEach(child -> child.render(event));
	}

	@Override
	public void onUpdate(UpdateEvent event) {
		super.onUpdate(event);
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

	public void setBackground(@Nullable ImageHolder imageHolder) {
		background = Optional.ofNullable(imageHolder);
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		if (padding > 0) {
			this.padding = padding;
		}
	}
}
