package net.agrotom.sonomod.client.gui.misc;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;

import net.agrotom.sonomod.util.ATUtils;
import net.agrotom.sonomod.util.RenderEvent;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class Image extends AbstractImage {

	private ResourceLocation imagePath;
	private int offX = 0;
	private int offY = 0;

	private void update() {
		ATUtils.ifMinecraftReady(minecraft -> {
			try {
				InputStream imageFile;
				imageFile = minecraft.getResourceManager().getResource(imagePath).getInputStream();
				
				final BufferedImage image = ImageIO.read(imageFile);
	
				this.width = image.getWidth();
				this.height = image.getHeight();
			} catch (IOException e) {
				e.printStackTrace();
				logger.log(Level.SEVERE, "Can't open image by path \"" + imagePath.getPath() + "\"");
			}
		});
	}

	public Image(@Nonnull ResourceLocation path) {
		this.setImagePath(path);
	}

	public void setImagePath(@Nonnull ResourceLocation imagePath) {
		this.imagePath = imagePath;

		update();
	}

	public ResourceLocation getImagePath() {
		return imagePath;
	}

	public void setOffX(int offX) {
		this.offX = offX;
	}

	public int getOffX() {
		return offX;
	}

	public void setOffY(int offY) {
		this.offY = offY;
	}

	public int getOffY() {
		return offY;
	}

	@Override
	public void render(RenderEvent event, int maxWidth, int maxHeight) {
		
		ATUtils.ifMinecraftReady(minecraft -> {
			minecraft.getTextureManager().bind(imagePath);
			
			AbstractGui.blit(event.getMatrix(), event.getRelativeX(), event.getRelativeY(), offX, offY,
					width > maxWidth ? maxWidth : width, height > maxHeight ? maxHeight : height, 256,
					256);
		});
	}

	private Logger logger = Logger.getLogger("Image Logger");

}
