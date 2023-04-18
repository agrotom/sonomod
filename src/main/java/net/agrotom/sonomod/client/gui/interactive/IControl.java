package net.agrotom.sonomod.client.gui.interactive;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.events.ActionEvent;
import net.agrotom.sonomod.client.gui.events.HoverEvent;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;

public interface IControl
{
	void setOnAction(@Nullable Consumer<ActionEvent> listener);
	void onAction(ActionEvent event);
	
	void setOnUpdate(@Nullable Consumer<UpdateEvent> listener);
	void onUpdate(UpdateEvent event);
	
	void setOnEnter(@Nullable Consumer<HoverEvent> listener);
	void onEnter(HoverEvent event);
	
	void setOnExit(@Nullable Consumer<HoverEvent> listener);
	void onExit(HoverEvent event);
	
	boolean isHovered();
}
