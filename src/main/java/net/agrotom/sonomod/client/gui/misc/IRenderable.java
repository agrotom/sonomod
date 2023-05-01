package net.agrotom.sonomod.client.gui.misc;

import javax.annotation.Nonnull;

import net.agrotom.sonomod.util.RenderEvent;

public interface IRenderable {
    void render(@Nonnull RenderEvent event);
}
