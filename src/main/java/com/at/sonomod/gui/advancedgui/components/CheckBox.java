package com.at.sonomod.gui.advancedgui.components;

import com.at.sonomod.SonoMod;
import com.at.sonomod.gui.advancedgui.AdvancedGui;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class CheckBox extends GuiComponent implements IClickable {
    protected boolean status = false;
    protected final TexturedModalRect texture = new TexturedModalRect(0, xPos, yPos, 8, 8, new ResourceLocation(SonoMod.MODID, "textures/gui/arrows.png"), 0, 20);

    protected ClickEvent leftClickListener;
    protected ClickEvent rightClickListener;
    protected ClickEvent wheelClickListener;

    public CheckBox(int id, int x, int y, int width, int height) {
        super(id, x, y, width, height);
    }

    public CheckBox(int id, int x, int y, int width, int height, boolean status) {
        super(id, x, y, width, height);
        this.status = status;
    }

    @Override
    public void draw(Minecraft mc, int scX, int scY, int mouseX, int mouseY) {
        texture.offX = status ? 8 : 0;
        texture.draw(mc, scX, scY, mouseX, mouseY);
    }

    @Override
    public void onLeftClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY) {
        System.out.println("clicked!");
        status = !status;

        if(leftClickListener != null)
            leftClickListener.onButtonClick(gui, player);
    }

    @Override
    public void onRightClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY) {
        status = !status;

        if(rightClickListener != null)
            rightClickListener.onButtonClick(gui, player);
    }

    @Override
    public void onWheelClick(AdvancedGui gui, EntityPlayer player, int mouseX, int mouseY) {
        status = !status;

        if(wheelClickListener != null)
            wheelClickListener.onButtonClick(gui, player);
    }

    @Override
    public void setOnLeftClick(ClickEvent event) {
         leftClickListener = event;
    }

    @Override
    public void setOnRightClick(ClickEvent event) {
         rightClickListener = event;
    }

    @Override
    public void setOnWheelClick(ClickEvent event) {
         wheelClickListener = event;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        return mouseX >= xPos && mouseY >= yPos && mouseX < xPos + 8 && mouseY < yPos + 8;
    }
}