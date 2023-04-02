package net.agrotom.sonomod.client.gui.events;

public class ActionEvent {
    
    public final double mouseX;
    public final double mouseY;
    public final int mouseButton;
    
    public ActionEvent(double mouseX, double mouseY, int mouseButton) {
	this.mouseX = mouseX;
	this.mouseY = mouseY;
	this.mouseButton = mouseButton;
    }
    
}
