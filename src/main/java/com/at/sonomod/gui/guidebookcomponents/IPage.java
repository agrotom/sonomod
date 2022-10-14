package com.at.sonomod.gui.guidebookcomponents;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public interface IPage {
    void initComponent(Minecraft mc, int guiX, int guiY, boolean isRight);
    void drawComponent(Minecraft mc, int guiX, int guiY, boolean isRight);
}
