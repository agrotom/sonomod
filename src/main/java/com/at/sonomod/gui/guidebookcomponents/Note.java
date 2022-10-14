package com.at.sonomod.gui.guidebookcomponents;

import com.at.sonomod.gui.advancedgui.components.GuiLabelAT;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public class Note implements IPage {
    protected String name;
    protected String text;

    protected GuiLabelAT label;

    public Note(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Override
    public void initComponent(Minecraft mc, int guiX, int guiY, boolean isRight) {
         label = new GuiLabelAT(label.xPos, guiX + 14 + (isRight ? 125 : 0), guiY + 15, 100, 120, 0xffffff, text);
    }

    @Override
    public void drawComponent(Minecraft mc, int guiX, int guiY, boolean isRight) {
        label.drawLabel(mc, label.xPos, label.yPos);
    }

    public String getName() { return name; }
    public String getText() { return text; }
}
