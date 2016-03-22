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
	
	private static final int IMAGE_COUNT = 15;
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
		
		images[7] = ImageIO.read(new java.io.File("resources/dragon/dragon_down_0.png"));
		images[8] = ImageIO.read(new java.io.File("resources/dragon/dragon_down_1.png"));
		images[9] = ImageIO.read(new java.io.File("resources/dragon/dragon_left_0.png"));
		images[10] = ImageIO.read(new java.io.File("resources/dragon/dragon_left_1.png"));
		images[11] = ImageIO.read(new java.io.File("resources/dragon/dragon_right_0.png"));
		images[12] = ImageIO.read(new java.io.File("resources/dragon/dragon_right_1.png"));
		images[13] = ImageIO.read(new java.io.File("resources/dragon/dragon_up_0.png"));
		images[14] = ImageIO.read(new java.io.File("resources/dragon/dragon_up_1.png"));
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

	public BufferedImage getDragonDown0(){return images[7];}
	public BufferedImage getDragonDown1(){return images[8];}
	public BufferedImage getDragonLeft0(){return images[9];}
	public BufferedImage getDragonLeft1(){return images[10];}
	public BufferedImage getDragonRight0(){return images[11];}
	public BufferedImage getDragonRight1(){return images[12];}
	public BufferedImage getDragonUp0(){return images[13];}
	public BufferedImage getDragonUp1(){return images[14];}
}
