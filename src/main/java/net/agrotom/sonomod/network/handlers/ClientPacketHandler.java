package net.agrotom.sonomod.network.handlers;

import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.network.packets.SkillsGUIAnswerPacket;
import net.agrotom.sonomod.network.packets.SkillsGUIRequestPacket;
import net.agrotom.sonomod.skills.ISkillsCompound;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClientPacketHandler {
	public static void onPacketReceived(@Nonnull SkillsGUIAnswerPacket packet, @Nonnull Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		LogicalSide receivedSide = context.getDirection().getReceptionSide();
		
		context.setPacketHandled(true);
		
		if (receivedSide != LogicalSide.CLIENT) {
			LOGGER.warn("SkillsCompound packet received on wrong side: " + context.getDirection().getReceptionSide());
			return;
		}
		
		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(receivedSide);
		if (!clientWorld.isPresent()) {
			LOGGER.warn("SkillsCompound packet's context could not provide a ClientWorld!");
			return;
		}
		
		context.enqueueWork(() -> {
			
		});
	}
	
	private static void processPacket(@Nonnull ISkillsCompound worldClient, @Nonnull SkillsGUIRequestPacket packet) {
		
	}
	
	public static boolean isProtocolAccepted(@Nonnull String protocolVersion) {
		return SonoMod.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
	
	private static final Logger LOGGER = LogManager.getLogger();
}
