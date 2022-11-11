package com.at.sonomod.gui;

import com.at.sonomod.gui.advancedgui.components.TexturedButton;
import com.at.sonomod.gui.guidebookcomponents.IPage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiGuideBook extends GuiBase {
    private final int guiWidth = 256;
    private final int guiHeight = 180;

    private List<IPage> staticContent = new ArrayList<IPage>();
    private List<IPage> content = new ArrayList<IPage>();
    private int scroll = 0;

    public GuiGuideBook(EntityPlayer player, ItemStack item) {
        super(player, item);
    }

    @Override
    public void drawScreen(int x, int y, float ticks) {
        Initialize(guiWidth, guiHeight, "mygui");

        for(int i = scroll * 2; i < content.size() && i <= scroll * 2 + 1; i++)
            content.get(i).drawComponent(mc, ((width - guiWidth) / 2), ((height - guiHeight) / 2), (i % 2 != 0));

        //drawHoveringText(Arrays.asList(new String[]{"X: " + ((width - guiWidth) / 2), "Y: " + ((height - guiHeight) / 2)}), x, y, mc.fontRenderer);
        super.drawScreen(x, y, ticks);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void initGui() {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;

        for(IPage item : staticContent)
            content.add(item);

        for(int i = scroll * 2; i < content.size() && i <= scroll * 2 + 1; i++)
            content.get(i).initComponent(mc, guiX, guiY, (i % 2 != 0));

        buttonList.add(new TexturedButton(0, guiX + 225, guiY + 147, 18, 10, true, "arrows"));
        buttonList.add(new TexturedButton(1, guiX + 10, guiY + 147, 18, 10, true, "arrows", 0, 10));

        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void setWorldAndResolution(Minecraft p_146280_1_, int p_146280_2_, int p_146280_3_) {
        content.clear();
        super.setWorldAndResolution(p_146280_1_, p_146280_2_, p_146280_3_);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch(button.id) {
            case 1:
                if(scroll > 0) {
                    scroll--;
                    content.clear();
                    initGui();
                }
                break;
            case 0:
                if(scroll < (int)Math.ceil(content.size() / 2.0) - 1) {
                    scroll++;
                    content.clear();
                    initGui();
                }
                break;
        }
    }

    public void addPage(IPage component) {
        staticContent.add(component);
    }
}
