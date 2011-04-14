package se.kth.csc.umbra.view;

import java.awt.Component;
import java.awt.event.KeyEvent;

/**
 * Translated KeyEvents from the user into KeyEvents for our TextArea to act upon.
 * 
 * @author Oskar Segersvärd
 */
public class Moribus {
	public static KeyEvent translate(KeyEvent event) {
		int key = event.getKeyCode();
		int keyCode = 0;
		switch (key) {
		case 1:
			keyCode = 2;
		default:
			keyCode = 0;
		}
		Component source = (Component) event.getSource();
		int id = event.getID();
		long when = event.getWhen();
		int modifiers = event.getModifiers();
		char keyChar = event.getKeyChar();
		int keyLocation = event.getKeyLocation();
		return new KeyEvent(source, id, when, modifiers, keyCode, keyChar,
				keyLocation);
	}

}
