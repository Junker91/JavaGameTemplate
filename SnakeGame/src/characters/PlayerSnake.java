package characters;

import gameState.PlayGameState;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Assets.Vector2D;

public class PlayerSnake {
	
	private BufferedImage[] sprites = new BufferedImage[2 * 4]; // rows * cols
	
	private Vector2D direction;
	
	private ArrayList<BufferedImage> snake;
	private Vector2D lastHeadPos;
	private ArrayList<Vector2D> bodyPartPos;
	
	private final int STEP_SIZE = PlayGameState.IMAGE_SIZE * PlayGameState.SCALE;
	public static enum faceWay {up, down, left, right};
	
	public PlayerSnake(String sheetPath) {
		direction = new Vector2D(STEP_SIZE, 0);
		snake = new ArrayList<BufferedImage>();
		bodyPartPos = new ArrayList<Vector2D>();
		loadSprites(sheetPath);
		
		bodyPartPos.add(new Vector2D(0, 0));
		lastHeadPos = new Vector2D(0, 0);
		
		snake.add(sprites[0]);
		setHeadSprite();
	}
	
	public void move() {
		moveHead();
		
		setHeadSprite();
		
		moveBodyAndSetSprites();
	}
	
	private void moveHead() {
		lastHeadPos.setCoords(bodyPartPos.get(0).x(), bodyPartPos.get(0).y());
		
		int oldX = bodyPartPos.get(0).x();
		int oldY = bodyPartPos.get(0).y();
		
		bodyPartPos.get(0).setCoords(oldX + direction.x(), oldY + direction.y());
		
	}
	
	private void setHeadSprite() {
		if(direction.y() < 0) {
			snake.set(0, sprites[0]);
		} else if(direction.y() > 0) {
			snake.set(0, sprites[1]);
		} else if(direction.x() < 0) {
			snake.set(0, sprites[2]);
		} else if(direction.x() > 0) {
			snake.set(0, sprites[3]);
		}
	}
	
	private void moveBodyAndSetSprites() {
		
		Vector2D currentBodyPos = new Vector2D(lastHeadPos.x(), lastHeadPos.y());
		Vector2D lastBodyPos = new Vector2D(0, 0);
		
		for(int i = 1; i < snake.size(); i++) {
			
			lastBodyPos.setCoords(bodyPartPos.get(i).x(), bodyPartPos.get(i).y());
			
			bodyPartPos.get(i).setCoords(currentBodyPos.x(), currentBodyPos.y());
			
			if(currentBodyPos.y() - lastBodyPos.y() < 0) {
				snake.set(i, sprites[4]);
			} else if(currentBodyPos.y() - lastBodyPos.y() > 0) {
				snake.set(i, sprites[5]);
			} else if(currentBodyPos.x() - lastBodyPos.x() < 0) {
				snake.set(i, sprites[6]);
			} else if(currentBodyPos.x() - lastBodyPos.x() > 0) {
				snake.set(i, sprites[7]);
			}
			
			currentBodyPos.setCoords(lastBodyPos.x(), lastBodyPos.y());
			
		}
		
		
	}
	
	public void setDirection(faceWay way) {
		
		Vector2D dif = new Vector2D(bodyPartPos.get(0).x() - lastHeadPos.x(), 
				bodyPartPos.get(0).y() - lastHeadPos.y());
		
		if(way == faceWay.up && dif.y() <= 0 ) {
			direction.setCoords(0, -STEP_SIZE);
		} else if(way == faceWay.down && dif.y() >= 0) {
			direction.setCoords(0, STEP_SIZE);
		} else if(way == faceWay.left && dif.x() <= 0) {
			direction.setCoords(-STEP_SIZE, 0);
		} else if(way == faceWay.right && dif.x() >= 0){
			direction.setCoords(STEP_SIZE, 0);
		}
		
		setHeadSprite();
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
	
	public void addBodyPart() {
		snake.add(null);
		bodyPartPos.add(new Vector2D(lastHeadPos.x(), lastHeadPos.y()));
	}
	
	public ArrayList<Vector2D> getSnakePartPos() {
		return bodyPartPos;
	}
	
	public ArrayList<BufferedImage> getSprites() {
		return snake;
	}
	
}
