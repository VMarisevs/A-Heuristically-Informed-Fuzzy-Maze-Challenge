package ie.gmit.sw.ai.node.items;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import ie.gmit.sw.ai.node.Node;

public class Sword extends Item {


	public Sword(Node position) {
		this.setPower(new Random().nextInt(4) +2);
		this.setColor(Color.YELLOW);
		this.setImage(this.getResources().getSword());
	}

	@Override
	public String toString() {
		return "Sword";
	}

	@Override
	public BufferedImage getWeapon() {
		return this.getImage();
	}
	
}
