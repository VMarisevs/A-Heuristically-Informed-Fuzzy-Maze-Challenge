package ie.gmit.sw.ai.node.items;

import java.awt.image.BufferedImage;

public abstract class Bomb extends Item{
	private int depth;
	private BufferedImage activated;
	private int delay = 3000;
	
	
	
	public int getDelay() {
		return delay;
	}

	protected void setDelay(int delay) {
		this.delay = delay;
	}

	public BufferedImage getActivated() {
		return activated;
	}

	protected void setActivated(BufferedImage activated) {
		this.activated = activated;
	}

	public int getDepth() {
		return depth;
	}

	protected void setDepth(int depth) {
		this.depth = depth;
	}
	
	

}
