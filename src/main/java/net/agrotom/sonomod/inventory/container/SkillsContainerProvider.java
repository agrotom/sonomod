package net.agrotom.sonomod.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SkillsContainerProvider implements INamedContainerProvider {
	
	@Override
	public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
		return new SkillsContainer(id, inventory);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("Skills Menu");
	}

}
