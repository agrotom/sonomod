package net.agrotom.sonomod.client;

import java.awt.event.KeyEvent;

import net.agrotom.sonomod.SonoMod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@OnlyIn(Dist.CLIENT)
public class ModKeybinds
{
	public static KeyBinding openGUIKey;
	
	public static void register(final FMLClientSetupEvent event)
	{
		openGUIKey = create("openGUIKey", KeyEvent.VK_G);
		
		ClientRegistry.registerKeyBinding(openGUIKey);
	}
	
	private static KeyBinding create(String name, int key)
	{
		return new KeyBinding("key." + SonoMod.MOD_ID + "." + name, key, "key.category." + SonoMod.MOD_ID);
	}

}
