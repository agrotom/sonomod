package net.agrotom.sonomod.client.gui.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;

@OnlyIn(value = Dist.CLIENT)
public class GUIUpdateEvent extends Event {
	
	public GUIUpdateEvent() {
		
	}

}
