package net.agrotom.sonomod.events;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.ModKeybinds;
import net.agrotom.sonomod.network.packets.SkillsGUIRequestPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SonoMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents
{

	@SubscribeEvent
	public static void onKeyPress(@Nonnull InputEvent.KeyInputEvent event) {
		final Minecraft mc = Minecraft.getInstance();
		
		if (mc.level == null) { 
			return;
		}
		
		onInput(mc, event.getKey(), event.getAction());
	}
	
	/*@SubscribeEvent
	public static void onMouseClick(@Nonnull InputEvent.MouseInputEvent event) {
		final Minecraft mc = Minecraft.getInstance();
		
		if (mc.level == null) {
			return;
		}
		
	}*/
	
	private static void onInput(@Nonnull Minecraft mc, int key, int action)
	{
		if (ModKeybinds.openGUIKey.isDown())
		{
			SonoMod.modChannel.sendToServer(new SkillsGUIRequestPacket());
		}
	}

}
