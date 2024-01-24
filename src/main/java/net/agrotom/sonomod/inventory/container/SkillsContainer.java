package net.agrotom.sonomod.inventory.container;

import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.skills.ISkillsCompound;
import net.agrotom.sonomod.util.ATUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;

public class SkillsContainer extends Container {

	public ISkillsCompound playerSkills;
	public final PlayerEntity player;
	
	public SkillsContainer(int id, PlayerInventory inventory, PacketBuffer buffer) {
		this(id, inventory);
	}
	
	public SkillsContainer(int id, PlayerInventory inventory) {
		super(ModContainer.SKILLS_CONTAINER.get(), id);
		
		this.player = inventory.player;
		this.playerSkills = ATUtils.getCapabilityOrException(player, SkillsCapability.SKILLS_HANDLER).get();
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return true;
	}

}
