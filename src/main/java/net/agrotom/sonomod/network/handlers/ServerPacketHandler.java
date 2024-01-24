package net.agrotom.sonomod.network.handlers;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.inventory.container.SkillsContainerProvider;
import net.agrotom.sonomod.network.packets.SkillsGUIRequestPacket;
import net.agrotom.sonomod.network.packets.server.SSyncSkillsCapPacket;
import net.agrotom.sonomod.util.ATUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;

public class ServerPacketHandler {
	public static void onPacketReceived(@Nonnull SkillsGUIRequestPacket packet,
			@Nonnull Supplier<NetworkEvent.Context> contextSupplier) {
		final NetworkEvent.Context context = contextSupplier.get();
		
		final LogicalSide sideReceived = context.getDirection().getReceptionSide();
		context.setPacketHandled(true);

		if (sideReceived != LogicalSide.SERVER) {
			LOGGER.error("SkillsGUIRequestPacket received on wrong side:" + context.getDirection().getReceptionSide());
			return;
		}

		final ServerPlayerEntity sendingPlayer = context.getSender();
		if (sendingPlayer == null) {
			LOGGER.error("EntityPlayerMP was null when SkillsGUIRequestPacket was received");
		}
		
		SonoMod.modChannel.send(PacketDistributor.PLAYER.with(() -> sendingPlayer),
				new SSyncSkillsCapPacket(ATUtils.getCapabilityOrException(sendingPlayer, SkillsCapability.SKILLS_HANDLER).get()));

		context.enqueueWork(() -> {
			NetworkHooks.openGui(sendingPlayer, new SkillsContainerProvider());
		});
		
		context.setPacketHandled(true);
	}
	
	public static boolean isProtocolAccepted(@Nonnull String protocolVersion) {
		return SonoMod.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
	
	private static final Logger LOGGER = LogManager.getLogger();
}
