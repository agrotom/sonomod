package net.agrotom.sonomod.client.gui.containers;

import java.util.List;

import net.agrotom.sonomod.client.gui.base.Element;
import net.agrotom.sonomod.client.gui.events.UpdateEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlowLayout extends Layout {
    
    public FlowLayout(List<Element> list) {
	super(list);
    }
    
    public FlowLayout(Element... children) {
	super(children);
    }
    
    @Override
    public void onUpdate(UpdateEvent event) {
        super.onUpdate(event);
        
        int currentWidth = 0;
        int currentHeight = 0;
        int maxHeightInCurrentRow = 0;
        
	for(Element child : children) {
	    if(currentHeight >= this.getHeight()) {
		break;
	    }
	    
	    child.setX(currentWidth);
	    child.setY(currentHeight);
	    
	    currentWidth += child.getWidth() + padding;
	    maxHeightInCurrentRow = Math.max(maxHeightInCurrentRow, child.getHeight());
	    
	    if(currentWidth > this.getWidth()) {
		currentWidth = 0;
		currentHeight += padding + maxHeightInCurrentRow;
	    }
	    
        }
    }
    
}
