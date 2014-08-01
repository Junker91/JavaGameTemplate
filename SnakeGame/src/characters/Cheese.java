package characters;

import gameState.PlayGameState;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Assets.Vector2D;
import main.GamePanel;

public class Cheese {
	private final int IMAGE_SIZE = 16;
	
	private BufferedImage sprite;
	
	private Vector2D pos;
	
	public Cheese() {
		pos = new Vector2D(0, 0);
		setPos();
	}
	
	public Vector2D getPos() {
		return pos;
	}
	
	public void setPos() {
		int tileSize = PlayGameState.TILESIZE;
		
		Random randNum = new Random();
		int x = randNum.nextInt(GamePanel.WIDTH - tileSize);
		int y = randNum.nextInt(GamePanel.HEIGHT - tileSize);
		
		int realX = (x != 0 ? (x / tileSize) * tileSize : 0);
		int realY = (y != 0 ? (y / tileSize) * tileSize : 0);
		
		pos.setCoords(realX, realY);
	}
	
	public BufferedImage getSprites() {
		return sprite;
	}
	
	public void loadSprites(String sheetPath) {

		BufferedImage sheet = null;
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream(sheetPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sprite = sheet.getSubimage(4 * IMAGE_SIZE, 0, IMAGE_SIZE, IMAGE_SIZE);
	}
}
