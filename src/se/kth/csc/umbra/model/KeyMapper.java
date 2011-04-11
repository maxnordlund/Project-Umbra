package se.kth.csc.umbra.model;

import java.awt.event.KeyEvent;

public class KeyMapper{

	public KeyMapper(){
		
	}
		
	public char keySwitch(KeyEvent event){
		int key = event.getKeyCode();
        switch (key) {
            case 1:  return '\u0627';
            default: return 'a';
        }
	}
	
}
