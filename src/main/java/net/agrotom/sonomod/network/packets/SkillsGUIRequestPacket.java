package net.agrotom.sonomod.network.packets;

import javax.annotation.Nonnull;

import net.minecraft.network.PacketBuffer;

public class SkillsGUIRequestPacket {

	public SkillsGUIRequestPacket() {}
	
	public void encode(@Nonnull PacketBuffer buffer) {}
	
	public static SkillsGUIRequestPacket decode(@Nonnull PacketBuffer buffer) {
		return new SkillsGUIRequestPacket();
	}
}