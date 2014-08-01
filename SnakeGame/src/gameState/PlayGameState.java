package gameState;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import Assets.Vector2D;
import characters.*;
import tileMap.Background;

public class PlayGameState extends GameState{
	private Background bg;
	
	private PlayerSnake snake;
	private Cheese cheese;
	
	public static final int SCALE = 2;
	public static final int IMAGE_SIZE = 16;
	public static final int TILESIZE = IMAGE_SIZE * SCALE;
	
	
	public PlayGameState(GameStateManager gsm, JPanel panel) {
		super(gsm, panel);
	}
	
	public void loadSprites(String sheetPath) {
		
		snake = new PlayerSnake(sheetPath ,new Vector2D(-1, 0));
		cheese = new Cheese(sheetPath);
	}

	@Override
	protected void initActions() {
		controls.clearBindings();
		Action up = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.setDirection(0, -1);
				
			}
		};
		controls.storeAction("up", up);
		
		Action down = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.setDirection(0, -1);
			}
		};
		controls.storeAction("down", down);
		
		Action left = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.setDirection(-1, 0);
			}
		};
		controls.storeAction("left", left);
		
		Action right = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.setDirection(1, 0);
			}
		};
		controls.storeAction("right", right);
	}

	@Override
	public void init() {
		
		
		controls.createKeyBinding("UP", "up");
		controls.createKeyBinding("DOWN", "down");
		controls.createKeyBinding("LEFT", "left");
		controls.createKeyBinding("RIGHT", "right");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(snake.getSprites().get(0), 0, 0, IMAGE_SIZE * SCALE, IMAGE_SIZE * SCALE, null);
		g.drawImage(cheese.getSprites(), cheese.getPos().x(), cheese.getPos().y(), 
				IMAGE_SIZE * SCALE, IMAGE_SIZE * SCALE, null);
		
	}
	
}
