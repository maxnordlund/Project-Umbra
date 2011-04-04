package se.kth.csc.umbra.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.JButton;

import se.kth.csc.umbra.Main;

public class File {
	
	public File(){
		
	}
	
	public static String Open(String path){
		
		
		return null;
		
	}
	
	public static String Save(String path){
		
		
		return null;
	}
	
	public static Icon getIcon(String path){
		
		
		return null;
	}
	
	private static void getResource() {

		InputStream input = null;
		try {
			input = new FileInputStream("./" + path);
			icon = readIcon(input);
		} catch (FileNotFoundException e) {
			input = Main.class.getResourceAsStream("/" + path);
			try {
				icon = readIcon(input);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (icon == null) {
			return new JButton(desc);
		}
		return new JButton(icon);
	}
}
