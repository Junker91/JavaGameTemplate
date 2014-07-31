package main;

import java.awt.Point;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) {
		
		JFrame gameWindow = new JFrame("Snake! - By Casida");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.add(new GamePanel()); 
		
		gameWindow.setResizable(false); //Always call setResizeable BEFORE setVisible
		gameWindow.pack();
			
		//gameWindow.setLocationRelativeTo(null);
		gameWindow.setLocation(new Point(250,0)); // works better an Mac
		gameWindow.setVisible(true);

	}
}
