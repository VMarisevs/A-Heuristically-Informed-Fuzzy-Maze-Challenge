package ie.gmit.sw.ai.node.items;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Clue extends Item{

	public Clue() {
		this.setPower(0);
		this.setColor(Color.LIGHT_GRAY);
		this.setImage(this.getResources().getClue());
	}

	@Override
	public String toString() {
		return "Clue";
	}

	@Override
	public BufferedImage getWeapon() {
		return this.getImage();
	}

}
