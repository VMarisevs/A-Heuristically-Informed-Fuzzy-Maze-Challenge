package ie.gmit.sw.ai.node.items;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ABomb extends Bomb {

	private static final int DEPTH = 10;
	
	public ABomb() {
		this.setPower(6);
		this.setColor(Color.BLUE);
		this.setImage(this.getResources().getAbomb());
		this.setDepth(DEPTH);
		this.setActivated(this.getResources().getActivatedAbomb());
	}

	@Override
	public BufferedImage getWeapon() {
		return this.getImage();
	}

	@Override
	public String toString() {
		return "A-Bomb";
	}
}
