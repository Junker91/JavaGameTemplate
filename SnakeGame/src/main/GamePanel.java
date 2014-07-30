package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Dimensions
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	private boolean running = false;
	private Thread thread;
	
	public GamePanel() {
		
		Dimension gameDimension = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(gameDimension);
		setFocusable(true);
		requestFocus();
		
		Action close = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
				
			}
		};
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "close");
		this.getActionMap().put("close", close );
		
		start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
	}
	
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double FPS = 60.0;
		double ns = 1000000000 / FPS;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				delta--;
				updates++;
			}
			
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " updates pr. second, frames " + frames + ", size:" + getWidth() + " x " + getHeight());
				frames = 0;
				updates = 0;
			}
			
		}
		
		stop();
	}
	
	private void update() {
		
	}
	
	private void render() {		
		
		this.repaint();
	}	
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.exit(1);
		
	}
}
