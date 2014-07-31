package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import main.GamePanel;
import tileMap.Background;

@SuppressWarnings("serial")
public class MenuState extends GameState{

	private Background bg;
	private String[] options = {"Start","Options","Quit"};
	
	private int selected;
	
	private Font menuFont;
	
	public MenuState(GameStateManager gsm, JPanel panel) {
		super(gsm, panel);
	}
	
	@Override
	public void init() {
		bg = new Background("/Backgrounds/menubg.gif", 0.0);
		bg.setPosition(0, 0);
		bg.setVector(-0.2, 0.0);
		
		selected = 0;
		controls.createKeyBinding("UP", "up");
		controls.createKeyBinding("DOWN", "down"); 
		
		try {
			File fontFile = new File("Ressources/Fonts/LCD_Solid.ttf");
			menuFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			menuFont = menuFont.deriveFont(Font.PLAIN, 36);
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(menuFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void initActions() {
		controls.clearBindings();
		
		Action down = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selected++;
				if(selected > options.length - 1)
					selected = 0;
			}
		};
		controls.storeAction("down", down);
		
		Action up = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selected--;
				if(selected < 0)
					selected = options.length - 1;
					
			}
		};
		controls.storeAction("up", up);
	}

	@Override
	public void update() {
		bg.update();
		
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		bg.draw(g2d);
		
		g2d.setFont(menuFont);
		
		for(int i = 0; i < options.length; i++) {
			
			if(selected == i)
				g2d.setColor(Color.RED);
			else
				g2d.setColor(Color.BLACK);
			
			g2d.drawString(options[i], getMiddleWidth(g2d, options[i], menuFont), GamePanel.HEIGHT / 2 + i * 50);
		}
		
	}
	
	private int getMiddleWidth(Graphics2D g, String string, Font font) {
		
		FontMetrics metrics = g.getFontMetrics(font);
		
		int stringMiddle = metrics.stringWidth(string) / 2;
		int screenMiddle = GamePanel.WIDTH / 2;
		return screenMiddle - stringMiddle;
	}

	
	
}
