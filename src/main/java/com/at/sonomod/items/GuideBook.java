package com.at.sonomod.items;

import com.at.sonomod.SonoMod;
import com.at.sonomod.gui.advancedgui.components.CheckBox;
import com.at.sonomod.gui.advancedgui.components.TexturedButton;
import com.at.sonomod.gui.advancedgui.AdvancedGui;
import com.at.sonomod.gui.advancedgui.components.TexturedModalRect;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuideBook extends ItemBase {

    public GuideBook(String name) {
        super(name);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
        AdvancedGui advGui = new AdvancedGui(256, 180, new ResourceLocation(SonoMod.MODID, "textures/gui/mygui.png"));
        advGui.addComponent(new TexturedModalRect(0, 10, 10, 15, 15, new ResourceLocation(SonoMod.MODID, "textures/gui/arrows.png")));
        advGui.setCanPause(false);
        advGui.addComponent(new TexturedButton(1, 225,147, 18, 10, true, "arrows"));
        ((TexturedButton)advGui.getComponent(1)).setOnLeftClick(GuideBook::onLeftClick);
        ((TexturedButton)advGui.getComponent(1)).setOnRightClick(GuideBook::onRightClick);
        ((TexturedButton)advGui.getComponent(1)).setOnWheelClick(GuideBook::onWheelClick);
        advGui.addComponent(new CheckBox(2, 30, 30, 8, 8));

        Minecraft.getMinecraft().displayGuiScreen(advGui);

        return super.onItemRightClick(item, world, player);
    }

    private static void onLeftClick(AdvancedGui gui, EntityPlayer player) {
        gui.getComponent(1).xPos = gui.getComponent(1).xPos + 5;
        System.out.println("I moved the button!");
    }

    private static void onRightClick(AdvancedGui gui, EntityPlayer player) {
        gui.getComponent(1).xPos = gui.getComponent(1).xPos - 5;
        System.out.println("I moved the button!");
    }

    private static void onWheelClick(AdvancedGui gui, EntityPlayer player) {
        gui.getComponent(1).yPos = gui.getComponent(1).yPos + 5;
        System.out.println("I moved the button!");
    }
}
