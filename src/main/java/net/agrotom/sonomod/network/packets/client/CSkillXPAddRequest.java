package net.agrotom.sonomod.network.packets.client;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.skills.ISkillsCompound;
import net.agrotom.sonomod.skills.impl.SkillsCompound;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class CSkillXPAddRequest extends CAbstractPacket {

	private final ISkillsCompound compound;
	
	public CSkillXPAddRequest(@Nonnull ISkillsCompound compound) {
		this.compound = compound;
		isValid = true;
	}
	
	@Override
	public void encode(@Nonnull PacketBuffer buffer) {
		if (isValid) {
			buffer.writeNbt(compound.serializeNBT());
		}
		else {
			LOGGER.error("Can't write a \"" + CSkillXPAddRequest.class.getName() + "\" packet to buffer, because it's not valid!");
		}
	}
	
	public static CSkillXPAddRequest decode(@Nonnull PacketBuffer buffer) {
		final ISkillsCompound newCompound = new SkillsCompound();
		newCompound.deserializeNBT(buffer.readNbt());
		final CSkillXPAddRequest packet = new CSkillXPAddRequest(newCompound);
		
		return packet;
	}
	
	public static void onPacketRecieved(@Nonnull CSkillXPAddRequest packet,
			@Nonnull Supplier<NetworkEvent.Context> contextSupplier) {
		
		if (!checkValidness(packet, contextSupplier.get())) {
			return;
		}
		
		final ServerPlayerEntity player = contextSupplier.get().getSender();
		
		try {
			player.getCapability(SkillsCapability.SKILLS_HANDLER).orElseThrow(() -> new Exception("")).copyFrom(packet.compound);;
		} catch (Exception e) {
			LOGGER.error("Player " + player.getName() + ":" + player.getStringUUID() + " hasn't skills capability!");
		}
		
	}

}
