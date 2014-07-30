package gameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Stack;

public class GameStateManager {

	private Stack<GameState> gameStates;
	
	
	public GameStateManager() {
		gameStates = new Stack<GameState>();
		
	}
	
	public void update() {
		gameStates.peek().update();
	}
	
	public void draw(Graphics g) {
		gameStates.peek().draw((Graphics2D)g);
	}
	
}
