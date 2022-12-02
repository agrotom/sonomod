package net.agrotom.sonomod.events;

import net.minecraftforge.fml.common.Mod;
import net.agrotom.sonomod.SonoMod;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(modid = SonoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ModEvents
{
}
