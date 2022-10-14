package com.at.sonomod.gui;

import com.at.sonomod.SonoMod;
import com.at.sonomod.gui.advancedgui.components.GuiLabelAT;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiBase extends GuiScreen {
    protected EntityPlayer player;
    protected ItemStack item;

    public List<GuiLabelAT> labelATList = new ArrayList<GuiLabelAT>();

    public GuiBase(EntityPlayer player, ItemStack item) {
        this.player = player;
        this.item = item;
    }

    //Put it in "drawScreen()"
    public void Initialize(int guiWidth, int guiHeight, String texture) {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;

        GL11.glColor4f(1, 1, 1, 1);

        drawDefaultBackground();

        mc.renderEngine.bindTexture(new ResourceLocation(SonoMod.MODID, "textures/gui/" + texture + ".png"));

        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);

        for(GuiLabelAT item : labelATList)
            item.drawLabel(mc, item.xPos, item.yPos);
    }

    @Override
    public void setWorldAndResolution(Minecraft p_146280_1_, int p_146280_2_, int p_146280_3_) {
        labelATList.clear();
        super.setWorldAndResolution(p_146280_1_, p_146280_2_, p_146280_3_);
    }

    @Override
    public void drawScreen(int x, int y, float ticks) {
        super.drawScreen(x, y, ticks);
    }
}
