package ie.gmit.sw.ai.node.items;


import java.awt.Color;
import java.awt.image.BufferedImage;

import ie.gmit.sw.resources.Resources;

public abstract class Item {
	
	private int power;
	private Color color;
	private BufferedImage image;
	private Resources resources;
	
	public Item() {
		resources = Resources.getInstance();
	}

	
	protected Resources getResources() {
		return resources;
	}

	protected BufferedImage getImage() {
		return image;
	}

	protected void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getPower() {
		return power;
	}

	protected void setPower(int power) {
		this.power = power;
	}

	public Color getColor() {
		return color;
	}

	protected void setColor(Color color) {
		this.color = color;
	}
	
	public abstract BufferedImage getWeapon();
	public abstract String toString();
	
	
}
