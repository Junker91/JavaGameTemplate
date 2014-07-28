package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;



public class TestGraphics {
	
	private int renderWidth;
	private int renderHeight;
	
	private BufferedImage image;
	
	public TestGraphics(int imageWidth, int imageWeight) {
		renderWidth = imageWidth;
		renderHeight = imageWeight;
		
		image = new BufferedImage(imageWidth, imageWeight, BufferedImage.TYPE_INT_RGB);
		
		
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT/2);
	}
	
	public void drawToScreen(Graphics g) {
		draw(g);
		
	}
}
