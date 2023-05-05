package net.agrotom.sonomod.events;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.capabilities.SkillsCapability;
import net.agrotom.sonomod.capabilities.SkillsCapabilityProvider;
import net.agrotom.sonomod.commands.SetSkillValueCommand;
import net.agrotom.sonomod.skills.SkillsHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = SonoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ForgeEvents {

	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> e) {
		for(int i = 0; i <= 100; i++) {
    		System.out.println("FIRING EVENT");
    	}
		
        if (e.getObject() instanceof ServerPlayerEntity) {
        	ServerPlayerEntity player = (ServerPlayerEntity) e.getObject();
        	
        	for(int i = 0; i <= 100; i++) {
        		System.out.println("FIRING EVENT2");
        	}
        	
        	if(!player.getCapability(SkillsCapability.SKILLS_HANDLER).isPresent()) {
        		for(int i = 0; i <= 100; i++) {
            		System.out.println("ATTACH CAPABILITY");
            	}
        		
        		e.addCapability(new ResourceLocation(SonoMod.MOD_ID, "skills"),
        				SkillsCapabilityProvider.createProvider(new SkillsHandler()));
        	}
        }
    }
	
	@SubscribeEvent
	public static void onCommandsRegister(RegisterCommandsEvent event) {
		new SetSkillValueCommand(event.getDispatcher());
		
		ConfigCommand.register(event.getDispatcher());
	}

}
