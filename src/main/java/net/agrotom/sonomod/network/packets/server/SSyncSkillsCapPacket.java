package net.agrotom.sonomod.network.packets.server;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.skills.ISkillsCompound;
import net.agrotom.sonomod.skills.impl.SkillsCompound;
import net.agrotom.sonomod.util.ATUtils;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class SSyncSkillsCapPacket extends SAbstractPacket {

	private final ISkillsCompound compound;
	
	public SSyncSkillsCapPacket(@Nonnull ISkillsCompound compound) {
		this.compound = compound;
		this.isValid = true;
	}
	
	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeNbt(compound.serializeNBT());
	}
	
	public static SSyncSkillsCapPacket decode(PacketBuffer buffer) {
		final ISkillsCompound newCompound = new SkillsCompound();
		newCompound.deserializeNBT(buffer.readNbt());
		
		return new SSyncSkillsCapPacket(newCompound);
	}
	
	public static void onPacketRecieved(@Nonnull SSyncSkillsCapPacket packet,
			@Nonnull Supplier<NetworkEvent.Context> contextSupplier) {
		
		if (!checkValidness(packet, contextSupplier.get())) {
			return;
		}
		
		final NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			ATUtils.ifMinecraftReady(mc -> {
				try {
					mc.player.getCapability(SkillsCapability.SKILLS_HANDLER).orElseThrow(() -> new Exception(""))
						.copyFrom(packet.compound);
					LOGGER.info("Recieved");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		});
		
		context.setPacketHandled(true);
		
	}
}
