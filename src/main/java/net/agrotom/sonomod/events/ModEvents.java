package net.agrotom.sonomod.events;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.entity.GimosEntity;
import net.agrotom.sonomod.entity.ModEntityTypes;
import net.agrotom.sonomod.entity.MyceplitEntity;
import net.agrotom.sonomod.entity.ReptileEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SonoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents
{
	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event)
	{
		for(int i = 0; i <= 100; i++) {
    		System.out.println("FIRING ATTRIBUTES");
    	}
		
		event.put(ModEntityTypes.REPTILE.get(), ReptileEntity.setCustomAttributes().build());
		event.put(ModEntityTypes.MYCEPLIT.get(), MyceplitEntity.setCustomAttributes().build());
		event.put(ModEntityTypes.GIMOS.get(), GimosEntity.setCustomAttributes().build());
	}
}
