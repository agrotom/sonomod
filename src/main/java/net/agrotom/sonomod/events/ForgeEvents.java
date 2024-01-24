package net.agrotom.sonomod.events;

import com.mojang.brigadier.CommandDispatcher;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.capabilities.SkillsCapabilityProvider;
import net.agrotom.sonomod.command.impl.GetSkillValueCommand;
import net.agrotom.sonomod.command.impl.ResetSkillsCommand;
import net.agrotom.sonomod.command.impl.SetSkillValueCommand;
import net.agrotom.sonomod.skills.impl.SkillsCompound;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
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
	public static void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<World> e) {

		if (e.getObject() instanceof World) {
			World world = (World) e.getObject();

			if (!world.getCapability(SkillsCapability.SKILLS_HANDLER).isPresent()) {
				e.addCapability(new ResourceLocation(SonoMod.MOD_ID, "skills"),
						SkillsCapabilityProvider.createProvider(new SkillsCompound()));
			}
		}
	}

	@SubscribeEvent
	public static void onCommandsRegister(RegisterCommandsEvent event) {
		final CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
		
		new SetSkillValueCommand(dispatcher);
		GetSkillValueCommand.register(dispatcher);
		ResetSkillsCommand.register(dispatcher);

		ConfigCommand.register(dispatcher);
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
	
	@SubscribeEvent
	public static void onWorldTick(TickEvent.WorldTickEvent event) {
		/*final World world = event.world;
		
		if (world.random.nextFloat() > 0.99F) {
			world.getServer().getPlayerList().getPlayers().forEach(player -> player.sendMessage(null, null));
		}*/
	}

}
