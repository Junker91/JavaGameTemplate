package gameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Assets.Vector2D;
import characters.*;
import tileMap.Background;

public class PlayGameState extends GameState{
	private Background bg;
	
	private PlayerSnake snake;
	private Cheese cheese;
	
	private final int SCALE = 2;
	private final int IMAGE_SIZE = 16;
	public static final int TILESIZE = 16 * 2;
	
	
	public PlayGameState(GameStateManager gsm, JPanel panel) {
		super(gsm, panel);
	}
	
	public void loadSprites(String sheetPath) {
		
		snake.loadSprites(sheetPath);
		cheese.loadSprites(sheetPath);
	}

	@Override
	protected void initActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		snake = new PlayerSnake(new Vector2D(1, 0));
		cheese = new Cheese();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(snake.getSprites()[1], 0, 0, IMAGE_SIZE * SCALE, IMAGE_SIZE * SCALE, null);
		g.drawImage(cheese.getSprites(), cheese.getPos().x(), cheese.getPos().y(), 
				IMAGE_SIZE * SCALE, IMAGE_SIZE * SCALE, null);
		
	}
	
}
