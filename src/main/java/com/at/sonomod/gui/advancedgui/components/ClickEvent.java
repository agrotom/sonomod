package com.at.sonomod.gui.advancedgui.components;

import com.at.sonomod.gui.advancedgui.AdvancedGui;
import net.minecraft.entity.player.EntityPlayer;

public interface ClickEvent {
    void onButtonClick(AdvancedGui gui, EntityPlayer player);
}
