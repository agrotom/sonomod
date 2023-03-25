package net.agrotom.sonomod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.gui.containers.ImageHolder;
import net.agrotom.sonomod.client.gui.containers.BorderPane;
import net.agrotom.sonomod.client.gui.interactive.Label;
import net.agrotom.sonomod.util.BlitData;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GUIBase extends Screen
{
	private static final ResourceLocation SKILLS_LOCATION = new ResourceLocation(SonoMod.MOD_ID, "textures/gui/skills.png");
	private BorderPane mainPane;
	private Label label;


	public GUIBase(ITextComponent title)
	{
		super(title);
	}

	@Override
	protected void init()
	{
		super.init();
		//imageHolder = new ImageHolder(minecraft, 0, 0, 166, 176, SKILLS_LOCATION);
		mainPane = new BorderPane(minecraft, 0, 0, 176, 176);
		mainPane.attachPos = AttachPos.CENTER;
		mainPane.background_image = new ImageHolder(minecraft, 0, 0, 176, 166, SKILLS_LOCATION);
		mainPane.background_image.attachPos = AttachPos.CENTER;
		label = new Label(minecraft, 0, 0, 5, 10, new StringTextComponent("Skills Menu").withStyle(TextFormatting.WHITE));
		label.attachPos = AttachPos.RIGHT_TOP;
		label.enableCentered();
		mainPane.setTop(label);
		mainPane.setLeft(label);
		mainPane.setRight(label);
		mainPane.setCenter(label);
		mainPane.setBottom(label);
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTick)
	{
		super.renderBackground(matrix);
		//this.minecraft.getTextureManager().bind(SKILLS_LOCATION);
		
		final int leftPos = (this.width - mainPane.width) / mainPane.attachPos.u;
		final int topPos = (this.height - mainPane.height) / mainPane.attachPos.v;
		
		//imageHolder.blit(matrix, leftPos, topPos, 0, 0);
		
		mainPane.posX = leftPos;
		mainPane.posY = topPos;
		
		mainPane.blit(BlitData.create(matrix).setX(leftPos).setY(topPos).setMouseX(mouseX).setMouseY(mouseY));
		
		//this.blit(matrix, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

}
