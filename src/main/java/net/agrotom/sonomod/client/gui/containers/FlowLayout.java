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
	    if(currentHeight >= this.height) {
		break;
	    }
	    
	    child.x = currentWidth;
	    child.y = currentHeight;
	    
	    currentWidth += child.width + padding;
	    maxHeightInCurrentRow = Math.max(maxHeightInCurrentRow, child.height);
	    
	    if(currentWidth > this.width) {
		currentWidth = 0;
		currentHeight += padding + maxHeightInCurrentRow;
	    }
	    
        }
    }
    
}
