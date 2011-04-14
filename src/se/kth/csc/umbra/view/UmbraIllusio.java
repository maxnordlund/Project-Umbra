package se.kth.csc.umbra.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicTextAreaUI;

/**
 * UmbraIllusio is responsible for displaying the text vertically
 * instead of horizontally.
 * 
 */
class UmbraIllusio extends BasicTextAreaUI {
	private JTextArea text;

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
		g2d.rotate(-Math.PI / 2);
		g2d.translate(-d.width, 0);
		super.paintSafely(g2d);

		// BufferedImage image =
		// new BufferedImage(d.height, d.width, BufferedImage.TYPE_INT_RGB);
		// Graphics2D g2d = image.createGraphics();
		// super.paintSafely(g2d);
		// g2d.rotate(-Math.PI/2);
		// g2d.translate(-d.width, 0);
		// g.drawImage(image, 0, 0, null);
	}
}
