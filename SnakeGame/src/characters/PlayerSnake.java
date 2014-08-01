package characters;

import gameState.PlayGameState;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;

import Assets.Vector2D;

public class PlayerSnake {
	
	private BufferedImage[] sprites = new BufferedImage[2 * 4]; // rows * cols
	private BufferedImage currentHeadSprite;
	
	private Vector2D direction;
	
	private ArrayList<BufferedImage> snake;
	
	public PlayerSnake(String sheetPath, Vector2D startDirection) {
		direction = startDirection;
		loadSprites(sheetPath);
		snake = new ArrayList<BufferedImage>();
		setCurrentSprites();
		
		snake.add(currentHeadSprite);
	}
	
	public void setDirection(int x, int y) {
		direction.setCoords(x, y);
		setCurrentSprites();
	}
	
	private void setCurrentSprites() {
		
		setHeadSprite();
			
//		for(int i = 0; i < snake.size(); i++) {
//			
//		}
		
		
	}
	
	public void setHeadSprite() {
		if(direction.y() < 0) {
			currentHeadSprite = sprites[0];
		} else if(direction.y() > 0) {
			currentHeadSprite = sprites[1];
		} else if(direction.x() < 0) {
			currentHeadSprite = sprites[2];
		} else if(direction.x() > 0) {
			currentHeadSprite = sprites[3];
		}
	}
	
	private void loadSprites(String sheetPath) {
		int rows = 2;
		int cols = 4;
		BufferedImage sheet = null;
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream(sheetPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int imageSize = PlayGameState.IMAGE_SIZE;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				sprites[(i * cols) + j] = sheet.getSubimage(
						j * imageSize,
						i * imageSize, 
						imageSize, imageSize);
			}
		}
	}
	
	public ArrayList<BufferedImage> getSprites() {
		return snake;
	}
	
}
