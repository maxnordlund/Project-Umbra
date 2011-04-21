package se.kth.csc.umbra.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicTextAreaUI;

import kth.vs.proto.ImagePanel;

/**
 * UmbraIllusio is responsible for displaying the text vertically instead of
 * horizontally.
 * 
 * @deprecated Unnecessary due to converting JScrollPanel to a BufferedImage
 */
class UmbraIllusio extends BasicTextAreaUI {
	private JTextArea text;
	private BufferedImage image;
	private ImagePanel ip;
	private int i = 0;

	UmbraIllusio(JTextArea text) {
		this.text = text;
	}

	/**
	 * This is where the magic happens. The TextArea is rotated etc.
	 */
	@Override
	public void paintSafely(Graphics g) {
		Dimension d = text.getSize();
		Graphics2D g2d = (Graphics2D) g;
		super.paintSafely(g2d);

	}
}
