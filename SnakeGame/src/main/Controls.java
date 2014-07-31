package main;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.JPanel;

public class Controls {
	
	private Map<String, Action> keyBindings;
	
	private JPanel panel;
	
	public Controls(JPanel panel) {
		this.panel = panel;
		
		keyBindings = new HashMap<String, Action>();
	}
	
	public void storeAction(String stringAction, Action action) {
		keyBindings.put(stringAction, action);
	}
	
	public void createKeyBinding(String keyStroke, String stringAction) {
		panel.getInputMap().put(KeyStroke.getKeyStroke(keyStroke), stringAction);
		panel.getActionMap().put(stringAction, keyBindings.get(stringAction));	
	}
	
	public void clearBindings() {
		keyBindings.clear();
	}

}
