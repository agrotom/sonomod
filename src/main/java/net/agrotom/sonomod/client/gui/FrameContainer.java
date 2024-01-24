package net.agrotom.sonomod.client.gui;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public abstract class FrameContainer<T extends Container> extends Frame implements IHasContainer<T> {

	protected final T container;
	
	public FrameContainer(T container, PlayerInventory inventory,
			ITextComponent text) {
		
		super(text);
		
		this.container = container;
		
	}
	
	@Override
	public T getMenu() {
		return container;
	}

}
