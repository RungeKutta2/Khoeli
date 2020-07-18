package models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage image;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Sprite(String path, int x, int y, int width, int height) {
		try {
			this.image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	
}
