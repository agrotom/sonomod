package net.agrotom.sonomod.network.packets.client;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.agrotom.sonomod.SonoMod;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public abstract class CAbstractPacket {

	protected boolean isValid = false;
	
	public boolean isValid() {
		return isValid;
	}
	
	public abstract void encode(@Nonnull PacketBuffer buffer);
	
	public static boolean isProtocolAccepted(@Nonnull String protocolVersion) {
		return SonoMod.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
	
	protected static <T extends CAbstractPacket> boolean checkValidness(@Nonnull T packet, @Nonnull NetworkEvent.Context context) {
		final LogicalSide sideReceived = context.getDirection().getReceptionSide();
		context.setPacketHandled(true);

		if (sideReceived != LogicalSide.SERVER) {
			LOGGER.error(packet.getClass().getSimpleName() + " received on the wrong side:" + context.getDirection().getReceptionSide());
			return false;
		}

		final ServerPlayerEntity sendingPlayer = context.getSender();
		
		if (sendingPlayer == null) {
			LOGGER.error("EntityPlayerMP was null when SkillsGUIRequestPacket was received");
			return false;
		}
		
		return true;
	}
	
	protected static final Logger LOGGER = LogManager.getLogger();
	
}
