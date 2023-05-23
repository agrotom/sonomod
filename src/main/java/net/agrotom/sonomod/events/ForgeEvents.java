package net.agrotom.sonomod.events;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.capabilities.SkillsCapabilityProvider;
import net.agrotom.sonomod.command.impl.GetSkillValueCommand;
import net.agrotom.sonomod.command.impl.SetSkillValueCommand;
import net.agrotom.sonomod.skills.SkillsCompound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = SonoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ForgeEvents {

	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> e) {

		if (e.getObject() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) e.getObject();

			if (!player.getCapability(SkillsCapability.SKILLS_HANDLER).isPresent()) {
				e.addCapability(new ResourceLocation(SonoMod.MOD_ID, "skills"),
						SkillsCapabilityProvider.createProvider(new SkillsCompound()));
			}
		}
	}

	@SubscribeEvent
	public static void onCommandsRegister(RegisterCommandsEvent event) {
		new SetSkillValueCommand(event.getDispatcher());
		GetSkillValueCommand.register(event.getDispatcher());

		ConfigCommand.register(event.getDispatcher());
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		final PlayerEntity player = event.getPlayer();

		event.getOriginal().getCapability(SkillsCapability.SKILLS_HANDLER).ifPresent(originalCap -> {
			player.getCapability(SkillsCapability.SKILLS_HANDLER).ifPresent(cap -> {
				cap.copyFrom(originalCap);
			});
		});
	}

}
