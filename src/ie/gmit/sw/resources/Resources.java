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
	
	private static final int IMAGE_COUNT = 3;
	private BufferedImage[] images;
	
	private Resources() throws IOException {
		images = new BufferedImage[IMAGE_COUNT]; 
		images[0] = ImageIO.read(new java.io.File("resources/gun.png"));
		images[1] = ImageIO.read(new java.io.File("resources/sword.png"));
		images[2] = ImageIO.read(new java.io.File("resources/grenade.png"));
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
}
