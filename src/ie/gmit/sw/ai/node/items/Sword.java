package ie.gmit.sw.ai.node.items;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Sword extends Item {


	public Sword(Node position) {
		super(NodeType.Sword, position);
		this.setBomb(false);
		this.setDamage(20);
		this.setRadius(0);
		this.setSelfdamage(30);
	}
	
}
