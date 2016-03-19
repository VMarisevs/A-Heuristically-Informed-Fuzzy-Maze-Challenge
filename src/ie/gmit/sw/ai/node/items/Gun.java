package ie.gmit.sw.ai.node.items;

import java.awt.Color;
import java.util.Random;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Gun extends Item{

	public Gun(Node position) {
		super(NodeType.Gun, position);
		this.setPower(new Random().nextInt(4) +4);
		this.setColor(Color.ORANGE);
	}

}
