package com.at.sonomod.gui.advancedgui.components;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

@SideOnly(Side.CLIENT)
public abstract class GuiComponent extends Gui {
    public int id;

    public int xPos;
    public int yPos;

    public int width;
    public int height;

    public GuiComponent(int id, int x, int y, int width, int height) {
        this.id = id;

        xPos = x;
        yPos = y;

        this.width = width;
        this.height = height;
    }

    public abstract void draw(Minecraft mc, int scX, int scY, int mouseX, int mouseY);
}
