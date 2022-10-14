package com.at.sonomod.gui.advancedgui;

import com.at.sonomod.gui.advancedgui.components.IClickable;
import com.at.sonomod.gui.advancedgui.components.GuiComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class AdvancedGui extends GuiScreen {
    protected int guiWidth;
    protected int guiHeight;
    protected ResourceLocation backgroundTexture;

    protected boolean canPause = false;

    protected List<GuiComponent> components = new ArrayList<>();

    public AdvancedGui(int guiWidth, int guiHeight, ResourceLocation backgroundTexture) {
        this.guiWidth = guiWidth;
        this.guiHeight = guiHeight;
        this.backgroundTexture = backgroundTexture;
    }

    @Override
    public void drawScreen(int x, int y, float ticks) {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1, 1, 1, 1);

        drawDefaultBackground();

        // Background drawing
        mc.renderEngine.bindTexture(backgroundTexture);
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);

        // Every component drawing
        for(GuiComponent item : components)
            item.draw(mc, guiX, guiY, x, y);

        super.drawScreen(x, y, ticks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int key) {
        if (key == 0 || key == 1 || key == 2)
        {
            for (GuiComponent component : this.components) {
                if (component instanceof IClickable) {
                    int guiX = (width - guiWidth) / 2;
                    int guiY = (height - guiHeight) / 2;

                    IClickable clk = (IClickable) component;

                    component.xPos += guiX;
                    component.yPos += guiY;

                    if (clk.mousePressed(mc, mouseX, mouseY)) {


                        switch (key) {
                            case 0:
                                clk.onLeftClick(this, mc.thePlayer, mouseX, mouseY);
                                break;
                            case 1:
                                clk.onRightClick(this, mc.thePlayer, mouseX, mouseY);
                                break;
                            case 2:
                                clk.onWheelClick(this, mc.thePlayer, mouseX, mouseY);
                                break;
                        }
                    }

                    component.xPos -= guiX;
                    component.yPos -= guiY;
                }
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return canPause;
    }

    public void addComponent(GuiComponent component) {
        for(int i = 0; i < components.size(); i++)
            if(components.get(i).id == component.id) {
                components.set(i, component);
                return;
            }

        components.add(component);
    }

    public GuiComponent getComponent(int id) {
        for(GuiComponent item : components)
            if(item.id == id)
                return item;

        return null;
    }

    public void removeComponent(int id) {
        components.removeIf(i -> i.id == id);
    }

    public void setCanPause(boolean doesPause) {
        canPause = doesPause;
    }
}
