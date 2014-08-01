package characters;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Assets.Vector2D;

public class PlayerSnake {
	private final int IMAGE_SIZE = 16;
	private final int SCALE = 2;
	
	private BufferedImage[] sprites = new BufferedImage[2 * 4]; // rows * cols
	
	private Vector2D direction;
	
	public PlayerSnake(Vector2D startDirection) {
		direction = startDirection;
	}
	
	public void loadSprites(String sheetPath) {
		int rows = 2;
		int cols = 4;
		BufferedImage sheet = null;
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream(sheetPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				sprites[(i * cols) + j] = sheet.getSubimage(
						j * IMAGE_SIZE,
						i * IMAGE_SIZE, 
						IMAGE_SIZE, IMAGE_SIZE);
			}
		}
	}
	
	public BufferedImage[] getSprites() {
		return sprites;
	}
	
}
