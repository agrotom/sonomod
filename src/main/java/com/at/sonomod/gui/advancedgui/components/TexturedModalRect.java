package com.at.sonomod.gui.advancedgui.components;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TexturedModalRect extends GuiComponent {
    public int offX = 0;
    public int offY = 0;

    protected ResourceLocation texture;

    public TexturedModalRect(int id, int xPos, int yPos, int width, int height, ResourceLocation texture) {
        super(id, xPos, yPos, width, height);
        this.texture = texture;
    }

    public TexturedModalRect(int id, int xPos, int yPos, int width, int height, ResourceLocation texture, int offX, int offY) {
        this(id, xPos, yPos, width, height, texture);
        this.offX = offX;
        this.offY = offY;
    }

    @Override
    public void draw(Minecraft mc, int scX, int scY, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(scX + xPos, scY + yPos, offX, offY, width, height);
    }
}
