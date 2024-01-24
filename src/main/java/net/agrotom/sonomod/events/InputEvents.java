package net.agrotom.sonomod.events;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.capabilities.SkillsCapabilityProvider;
import net.agrotom.sonomod.client.ModKeybinds;
import net.agrotom.sonomod.inventory.container.SkillsContainer;
import net.agrotom.sonomod.network.packets.SkillsGUIRequestPacket;
import net.agrotom.sonomod.skills.impl.SkillsCompound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SonoMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents
{

	@SubscribeEvent
	public static void onKeyPress(@Nonnull InputEvent.KeyInputEvent event) {
		final Minecraft mc = Minecraft.getInstance();
		ClientPlayerEntity player = mc.player;
		
		if (mc.level == null) { 
			return;
		}
		
		if (player != null && player.containerMenu != null &&
		        !(player.containerMenu instanceof SkillsContainer) &&
		        event.getKey() == ModKeybinds.openGUIKey.getKey().getValue() &&
		        		event.getAction() == 1) {
		      player.closeContainer();
		      
		}
	}
	
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase != TickEvent.Phase.END) {
			return;
		}
		
		final Minecraft mc = Minecraft.getInstance();
		
		if (ModKeybinds.openGUIKey.consumeClick() && mc.isWindowActive()) {
			SonoMod.modChannel.sendToServer(new SkillsGUIRequestPacket());
	    }
	}
	
	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> e) {

		if (e.getObject() instanceof ClientPlayerEntity) {
			ClientPlayerEntity player = (ClientPlayerEntity) e.getObject();

			if (!player.getCapability(SkillsCapability.SKILLS_HANDLER).isPresent()) {
				e.addCapability(new ResourceLocation(SonoMod.MOD_ID, "skills"),
						SkillsCapabilityProvider.createProvider(new SkillsCompound()));
			}
		}
	}

}
