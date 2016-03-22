package ie.gmit.sw.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resources {

	private static Resources instance;
	
	public static Resources getInstance(){
		if (instance == null){
			
			try {
				instance = new Resources();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
	private static final int IMAGE_COUNT = 7;
	private BufferedImage[] images;
	
	private Resources() throws IOException {
		images = new BufferedImage[IMAGE_COUNT]; 
		images[0] = ImageIO.read(new java.io.File("resources/gun.png"));
		images[1] = ImageIO.read(new java.io.File("resources/sword.png"));
		images[2] = ImageIO.read(new java.io.File("resources/grenade.png"));
		images[3] = ImageIO.read(new java.io.File("resources/active_grenade.png"));
		images[4] = ImageIO.read(new java.io.File("resources/explosion.png"));
		images[5] = ImageIO.read(new java.io.File("resources/abomb.png"));
		images[6] = ImageIO.read(new java.io.File("resources/active_abomb.png"));
	}
	
	public BufferedImage getGun(){
		return images[0];
	}
	
	public BufferedImage getSword(){
		return images[1];
	}
	
	public BufferedImage getGrenade(){
		return images[2];
	}
	
	public BufferedImage getActivatedGrenade(){
		return images[3];
	}
	
	public BufferedImage getExplosion(){
		return images[4];
	}
	
	public BufferedImage getAbomb(){
		return images[5];
	}
	
	public BufferedImage getActivatedAbomb(){
		return images[6];
	}
}
