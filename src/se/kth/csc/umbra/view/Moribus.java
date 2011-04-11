package se.kth.csc.umbra.view;

import java.awt.event.KeyEvent;

public class Moribus{

	public Moribus(){
		
	}
		
	public char keySwitch(KeyEvent event){
		int key = event.getKeyCode();
        switch (key) {
            case 1:  return '\u0627';
            default: return 'a';
        }
	}
	
}
