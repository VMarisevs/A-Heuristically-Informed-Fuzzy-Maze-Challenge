package ie.gmit.sw.ai.node.items;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Grenade extends Bomb{

	private static final int DEPTH = 3;
	public Grenade() {
		this.setPower(0);
		this.setColor(Color.CYAN);
		this.setImage(this.getResources().getGrenade());
		this.setDepth(DEPTH);
		this.setActivated(this.getResources().getActivatedGrenade());
	}
	
	@Override
	public BufferedImage getWeapon() {
		return this.getImage();
	}

	@Override
	public String toString() {
		return "Grenade";
	}

}
