package gameState;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import characters.*;
import tileMap.Background;

public class PlayGameState extends GameState{
	private Background bg;
	
	private PlayerSnake snake;
	private Cheese cheese;
	
	public static final int SCALE = 2;
	public static final int IMAGE_SIZE = 16;
	public static final int TILESIZE = IMAGE_SIZE * SCALE;
	
	private int moveCount = 20;
	private int count = 0;
	
	
	public PlayGameState(GameStateManager gsm, JPanel panel) {
		super(gsm, panel);
	}
	
	public void loadSprites(String sheetPath) {
		
		snake = new PlayerSnake(sheetPath);
		cheese = new Cheese(sheetPath);
	}

	@Override
	public void init() {
		
		bg = new Background("/Backgrounds/playBGGrass.jpg", 0);
		
		controls.createKeyBinding("UP", "up");
		controls.createKeyBinding("DOWN", "down");
		controls.createKeyBinding("LEFT", "left");
		controls.createKeyBinding("RIGHT", "right");
	}

	@Override
	public void update() {
		
		count++;
		
		if(count == moveCount) {
			snake.move();
			count = 0;
			
			if(snake.getSnakePartPos().get(0).x() == cheese.getPos().x() &&
					snake.getSnakePartPos().get(0).y() == cheese.getPos().y()) {
				snake.addBodyPart();
				cheese.setPos();
			}
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		for(int i = 0; i < snake.getSnakePartPos().size(); i++) {
			g.drawImage(snake.getSprites().get(i), snake.getSnakePartPos().get(i).x(), snake.getSnakePartPos().get(i).y(),
				IMAGE_SIZE * SCALE, IMAGE_SIZE * SCALE, null);
		}
		
		g.drawImage(cheese.getSprites(), cheese.getPos().x(), cheese.getPos().y(), 
				IMAGE_SIZE * SCALE, IMAGE_SIZE * SCALE, null);
		
	}
	
	@Override
	protected void initActions() {
		controls.clearBindings();
		Action up = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.setDirection(PlayerSnake.faceWay.up);
				
			}
		};
		controls.storeAction("up", up);
		
		Action down = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.setDirection(PlayerSnake.faceWay.down);
			}
		};
		controls.storeAction("down", down);
		
		Action left = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.setDirection(PlayerSnake.faceWay.left);
			}
		};
		controls.storeAction("left", left);
		
		Action right = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.setDirection(PlayerSnake.faceWay.right);
			}
		};
		controls.storeAction("right", right);
	}
}
