package ie.gmit.sw.ai.node.items;

import java.awt.Color;
import java.util.Random;

import ie.gmit.sw.ai.node.Node;

public class Sword extends Item {


	public Sword(Node position) {
		this.setPower(new Random().nextInt(4) +2);
		this.setColor(Color.YELLOW);
	}

	@Override
	public String toString() {
		return "Sword";
	}
	
}
