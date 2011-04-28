package kth.csc.umbra.view;

import java.awt.*;

import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicTextAreaUI;

/**
 * UmbraIllusio is responsible for displaying the text vertically instead of
 * horizontally.
 * 
 * @deprecated Unnecessary due to converting JScrollPanel to a BufferedImage
 */
@Deprecated
class UmbraIllusio extends BasicTextAreaUI {
	@SuppressWarnings("unused")
	private JTextArea text;

	UmbraIllusio(JTextArea text) {
		this.text = text;
	}

	/**
	 * This is where the magic happens. The TextArea is rotated etc.
	 */
	@Override
	public void paintSafely(Graphics g) {
		// Dimension d = text.getSize();
		Graphics2D g2d = (Graphics2D) g;
		super.paintSafely(g2d);

	}
}
