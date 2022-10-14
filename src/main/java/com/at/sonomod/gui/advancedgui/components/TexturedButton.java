package com.at.sonomod.gui.advancedgui.components;

import com.at.sonomod.SonoMod;
import com.at.sonomod.gui.advancedgui.AdvancedGui;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TexturedButton extends GuiComponent implements IClickable {

    protected final boolean idk;

    protected String texturePath;

    protected int offX = 0;
    protected int offY = 0;

    private ClickEvent leftClickListener = null;
    private ClickEvent rightClickListener = null;
    private ClickEvent wheelClickListener = null;

    public TexturedButton(int id, int x, int y, int w, int h, boolean idk, String texture) {
        super(id, x, y, w, h);
        this.idk = idk;
        texturePath = texture;
    }

    public TexturedButton(int id, int x, int y, int w, int h, boolean idk, String texture, int offX, int offY) {
        this(id, x, y, w, h, idk, texture);
        this.offX = offX;
        this.offY = offY;
    }

    @Override
    public void onLeftClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY) { if(leftClickListener != null) leftClickListener.onButtonClick(gui, player); }

    @Override
    public void onRightClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY) { if(rightClickListener != null) rightClickListener.onButtonClick(gui, player); }

    @Override
    public void onWheelClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY) { if(wheelClickListener != null) wheelClickListener.onButtonClick(gui, player); }

    @Override
    public void draw(Minecraft mc, int scX, int scY, int mouseX, int mouseY) {
        xPos += scX;
        yPos += scY;

        boolean flag = mouseX >= xPos && mouseY >= yPos && mouseX < xPos + this.width && mouseY < yPos + this.height;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation(SonoMod.MODID, "textures/gui/" + texturePath + ".png"));
        int u = offX;
        int v = offY;

        if (flag)
        {
            u += width;
        }

        if (!this.idk)
        {
            v += height;
        }

        this.drawTexturedModalRect(xPos, yPos, u, v, width, height);

        xPos -= scX;
        yPos -= scY;
    }

    @Override
    public void setOnLeftClick(ClickEvent listener) {
        leftClickListener = listener;
    }

    @Override
    public void setOnRightClick(ClickEvent listener) {
        rightClickListener = listener;
    }

    @Override
    public void setOnWheelClick(ClickEvent listener) {
        wheelClickListener = listener;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        return mouseX >= xPos && mouseY >= yPos && mouseX < xPos + width && mouseY < yPos + height;
    }

}
