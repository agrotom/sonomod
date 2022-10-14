package com.at.sonomod.gui.advancedgui.components;

import com.at.sonomod.gui.advancedgui.AdvancedGui;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public interface IClickable {
    void onLeftClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY);
    void onRightClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY);
    void onWheelClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY);

    void setOnLeftClick(ClickEvent event);
    void setOnRightClick(ClickEvent event);
    void setOnWheelClick(ClickEvent event);

    boolean mousePressed(Minecraft mc, int mouseX, int mouseY);
}
