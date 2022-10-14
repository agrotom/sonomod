package com.at.sonomod.gui.advancedgui.components;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiLabelAT extends GuiComponent  {
    public int colour;
    public String labelText;

    public GuiLabelAT(int id, int x, int y, int width, int height, int colour, String text) {
        super(id, x, y, width, height);

        this.colour = colour;
        labelText = text;
    }

    public void drawLabel(Minecraft mc, int mouseX, int mouseY) {
        List<String> formattedString = (List<String>)mc.fontRendererObj.listFormattedStringToWidth(labelText, width);
        int currentY = yPos;

        for(String item : formattedString) {
            drawString(mc.fontRendererObj, item, xPos, currentY, colour);
            if((currentY += mc.fontRendererObj.FONT_HEIGHT) >= yPos + height)
                break;
        }
    }

    @Override
    public void draw(Minecraft mc, int scX, int scY, int mouseX, int mouseY) {
        xPos += scX;
        yPos += scY;

        drawLabel(mc, mouseX, mouseY);

        xPos -= scX;
        yPos -= scY;
    }
}
