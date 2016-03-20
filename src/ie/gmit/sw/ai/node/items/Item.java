package ie.gmit.sw.ai.node.items;


import java.awt.Color;

import ie.gmit.sw.ai.node.Node;

public abstract class Item {
	
	private int power;
	private Color color;

	public int getPower() {
		return power;
	}

	protected void setPower(int power) {
		this.power = power;
	}

	public Color getColor() {
		return color;
	}

	protected void setColor(Color color) {
		this.color = color;
	}
	
	public abstract String toString();
	
	
}
