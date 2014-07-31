package tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Background {
	private double posX;
	private double posY;
	private double directionX;
	private double directionY;
	
	private double moveScale;
	
	private BufferedImage image;
	
	public Background(String path, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
			moveScale = ms;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void setPosition(double x, double y) {
		posX = (x * moveScale) % GamePanel.WIDTH;
		posY = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		directionX = dx;
		directionY = dy;
	}
	
	public void update() {
		posX += directionX;
		posY += directionY;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)posX, (int)posY, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		
		if(posX < 0) {
			g.drawImage(image, (int)posX + GamePanel.WIDTH, (int)posY, 
					GamePanel.WIDTH, GamePanel.HEIGHT, null);
		}
		else if(posX > 0) {
			g.drawImage(image, (int)posX - GamePanel.WIDTH, (int)posY, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		}
			
	}
}
