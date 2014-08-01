package gameState;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import main.Controls;

public abstract class GameState {
	protected GameStateManager gsm;
	protected JPanel panel;
	protected Controls controls;
	
	public GameState(GameStateManager gsm, JPanel panel) {
		this.panel = panel;
		this.gsm =gsm;
		controls = new Controls(panel);
		
		initActions();
		init();
	}
	
	protected abstract void initActions();
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
}
