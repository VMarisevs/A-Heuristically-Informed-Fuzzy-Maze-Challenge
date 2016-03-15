package ie.gmit.sw.ai.node.items;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Item {
	private int radius;
	private int damage;
	private int selfdamage;
	private boolean bomb;
	
	private NodeType type;
	private Node position;
	
	public Item(NodeType type, Node position) {
		this.type = type;
		this.position = position;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getSelfdamage() {
		return selfdamage;
	}
	public void setSelfdamage(int selfdamage) {
		this.selfdamage = selfdamage;
	}
	public boolean isBomb() {
		return bomb;
	}
	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}
	public NodeType getType() {
		return type;
	}
	
	
}
