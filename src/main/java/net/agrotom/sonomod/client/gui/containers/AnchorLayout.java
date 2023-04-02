package net.agrotom.sonomod.client.gui.containers;

import java.util.List;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;

/**
 * 
 * Places child elements by their position. No dynamic placement.
 *
 */
public class AnchorLayout extends Layout {
    
    public AnchorLayout(Element... children) {
	super(children);
    }
    
    public AnchorLayout(List<Element> children) {
	super(children);
    }
    
    @Override
    public void onUpdate(UpdateEvent event) {
	super.onUpdate(event);
	
	children.forEach(child -> {
	    child.maxWidth = this.width - child.x;
	    child.maxHeight = this.height - child.y;
	});
    }
}
