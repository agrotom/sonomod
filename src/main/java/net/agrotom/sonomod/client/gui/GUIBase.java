package net.agrotom.sonomod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.SonoMod;
import net.agrotom.sonomod.client.gui.containers.AnchorLayout;
import net.agrotom.sonomod.client.gui.containers.Window;
import net.agrotom.sonomod.client.gui.events.ActionEvent;
import net.agrotom.sonomod.client.gui.events.HoverEvent;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.agrotom.sonomod.client.gui.interactive.ImageHolder;
import net.agrotom.sonomod.client.gui.interactive.Label;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GUIBase extends Screen
{
	private static final ResourceLocation SKILLS_LOCATION = new ResourceLocation(SonoMod.MOD_ID, "textures/gui/skills.png");

	private ImageHolder imageHolder;
	private AnchorLayout root;
	private Window window;
	

	public GUIBase(ITextComponent title)
	{
		super(title);
	}

	@Override
	public void tick() {
	    root.onUpdate(new UpdateEvent());
	}
	
	@Override
	protected void init()
	{
		super.init();
		
		
		final ITextComponent t = new StringTextComponent(SonoMod.BLANK).withStyle(TextFormatting.WHITE);
		final Label l = new Label(t);
		l.x = 50;
		l.y = 50;
		l.setWrapped(true);
		
		imageHolder = new ImageHolder(SKILLS_LOCATION);
		imageHolder.width = 178;
		imageHolder.height = 168;
		
		root = new AnchorLayout(l);
		root.setBackground(imageHolder);
		window = new Window(root);
		window.setWidth(178);
		window.setHeight(168);
		
		root.setOnAction(event -> {
		    System.out.println("sfsdfsdf");
		});
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int id) {
	    root.mouseAt(mouseX, mouseY).ifPresent(element -> element.onAction(new ActionEvent(mouseX, mouseY, id)));;
	    
	    return true;
	}
	
	@Override
	public void mouseMoved(double mouseX, double mouseY) {
	    root.mouseAt(mouseX, mouseY).ifPresent(element -> element.onHover(new HoverEvent()));
	}
	
	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTick)
	{
		super.renderBackground(matrix);
		window.renderAll(matrix, (width - window.getWidth()) / 2, (height - window.getHeight()) / 2, mouseX, mouseY);
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

}
