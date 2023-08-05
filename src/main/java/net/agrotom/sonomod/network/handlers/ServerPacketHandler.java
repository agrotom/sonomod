package net.agrotom.sonomod.network.handlers;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.client.gui.SkillsFrame;
import net.agrotom.sonomod.network.packets.SkillsGUIRequestPacket;
import net.agrotom.sonomod.skills.ISkillsCompound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

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
		
		final LazyOptional<ISkillsCompound> optionalCompound = sendingPlayer.getCapability(SkillsCapability.SKILLS_HANDLER);
		
		if (optionalCompound.isPresent()) {
			context.enqueueWork(() -> {
				proccessPacket(sendingPlayer, optionalCompound.resolve().get());
			});
		}
		else {
			LOGGER.error("Player " + sendingPlayer.getName().getString() + " has no ISkillsCompund capability!");
		}
	}
	
	private static void proccessPacket(@Nonnull PlayerEntity player, @Nonnull ISkillsCompound compound) {
		
	}
	
	public static boolean isProtocolAccepted(@Nonnull String protocolVersion) {
		return SonoMod.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
	
	private static final Logger LOGGER = LogManager.getLogger();
}
