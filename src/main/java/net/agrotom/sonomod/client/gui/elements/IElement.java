package net.agrotom.sonomod.client.gui.elements;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.sonomod.client.gui.ElementType;
import net.agrotom.sonomod.client.gui.container.ILayout;
import net.agrotom.sonomod.client.gui.events.GUIMouseEvent;
import net.agrotom.sonomod.client.gui.events.GUIUpdateEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * The interface of the simplest element of GUI.
 */
@OnlyIn(value = Dist.CLIENT)
public interface IElement {
	
	void setX(int x);                                                    
	int getX();                                                          
	                                                                     
	void setY(int y);                                                    
	int getY();                                                          
	                                                                     
	void setWidth(int width);                                            
	int getWidth();                                                      
	                                                                     
	void setHeight(int height);                                          
	int getHeight();                                                     
	                  
	boolean isElementAt(int x, int y);
	Optional<IElement> at(int x, int y);       
	
	void setTooltip(@Nullable ITextComponent tooltip);
	Optional<ITextComponent> getTooltip();
	
	void unhide();
	/**
	 * Prevents the element to render.
	 */
	void hide();
	boolean isHidden();
	
	void enable();
	/**
	 * Prevents the user to interact with that element.
	 */
	void disable();
	boolean isDisabled();
	
	boolean isHovered();
	                                                                     
	void setOnMouseAction(@Nonnull Consumer<GUIMouseEvent.ActionEvent> listener);
	void setOnMouseEnter(@Nonnull Consumer<GUIMouseEvent.EnterEvent> listener);  
	void setOnMouseExit(@Nonnull Consumer<GUIMouseEvent.ExitEvent> listener);    
	void setOnMouseDrag(@Nonnull Consumer<GUIMouseEvent.DragEvent> listener);
	void setOnMouseHover(@Nonnull Consumer<GUIMouseEvent.HoverEvent> listener);
	
	void setOnUpdate(@Nonnull Consumer<GUIUpdateEvent> listener);
	
	void mouseAction(@Nonnull GUIMouseEvent.ActionEvent event);
	void mouseEnter(@Nonnull GUIMouseEvent.EnterEvent event);
	void mouseExit(@Nonnull GUIMouseEvent.ExitEvent event);
	void mouseDrag(@Nonnull GUIMouseEvent.DragEvent event);
	void mouseHover(@Nonnull GUIMouseEvent.HoverEvent event);
	
	void update(@Nonnull GUIUpdateEvent event);
	
	void setParent(@Nonnull ILayout parent);
	Optional<ILayout> getParent();
	
	@Nonnull
	List<IElement> getChildren();
	
	ElementType<?> getType();
	
}