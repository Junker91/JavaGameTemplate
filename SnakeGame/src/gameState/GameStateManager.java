package gameState;

import java.awt.Graphics;

import javax.swing.JPanel;

import java.util.Stack;

public class GameStateManager {

	private Stack<GameState> gameStates;
	
	private JPanel panel;
	
	public GameStateManager(JPanel panel) {
		
		this.panel = panel;
		
		gameStates = new Stack<GameState>();
		gameStates.push(new MenuState(this, panel));
		
	}
	
	public void update() {
		gameStates.peek().update();
	}
	
	public void draw(Graphics g) {
		gameStates.peek().draw(g);
	}
	
}
