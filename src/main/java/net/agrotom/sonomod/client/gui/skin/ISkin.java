package net.agrotom.sonomod.client.gui.skin;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.agrotom.sonomod.client.gui.interactive.ElementState;

public interface ISkin
{

	void render(@Nonnull MatrixStack matrix, int x, int y, int width, int height, @Nonnull ElementState state);
	
}
