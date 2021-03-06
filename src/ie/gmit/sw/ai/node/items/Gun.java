package ie.gmit.sw.ai.node.items;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Gun extends Item{

	public Gun() {
		this.setPower(new Random().nextInt(4) +4);
		this.setColor(Color.ORANGE);
		this.setImage(this.getResources().getGun());
	}

	@Override
	public String toString() {
		return "Gun";
	}

	@Override
	public BufferedImage getWeapon() {
		return this.getImage();
	}

}
