package ie.gmit.sw.ai.node.items;


import java.awt.Color;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Item {
	
	private int power;
	private NodeType type;
	private Node position;
	private Color color;
	
	public Item(NodeType type, Node position) {
		this.type = type;
		this.position = position;
	}

	public NodeType getType() {
		return type;
	}

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
	
	
}
