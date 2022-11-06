package com.at.sonomod.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface SpecialRender {
	@SideOnly(Side.CLIENT)
	void specialRenderFunc(double x, double y, double z);
}
