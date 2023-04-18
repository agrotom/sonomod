package net.agrotom.sonomod.events;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.ModKeybinds;
import net.agrotom.sonomod.client.gui.SkillsGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SonoMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents
{

	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		
		if (mc.level == null) return;
		
		onInput(mc, event.getKey(), event.getAction());
	}
	
	@SubscribeEvent
	public static void onMouseClick(InputEvent.MouseInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		
		if (mc.level == null) return;
	}
	
	private static void onInput(Minecraft mc, int key, int action)
	{
		if (ModKeybinds.openGUIKey.isDown())
		{
			if (mc.screen == null)
			{
				mc.setScreen(new SkillsGUI(new StringTextComponent("something")));
			}
			else
			{
				mc.setScreen(null);
			}
		}
	}

}
