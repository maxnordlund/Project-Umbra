package kth.csc.umbra.view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * An ImagePanel is a Swing component that can display a BufferedImage. It is
 * constructed as a subclass of JComponent with the added functionality of
 * setting an BufferedImage that will be displayed on the surface of this
 * component.
 * 
 * @author Oskar Segersvärd
 */
class ImageComponent extends JComponent {
	
	private static final long serialVersionUID = -2499902649358062175L;

	int width, height;

	private BufferedImage panelImage;

	/**
	 * Create a new, empty ImagePanel.
	 */
	public ImageComponent() {
		width = 360;
		height = 240;
		panelImage = null;
	}

	public ImageComponent(BufferedImage image) {
		setImage(image);
	}

	/**
	 * Set the image that this panel should show.
	 * 
	 * @param image
	 *            The image to be displayed.
	 */
	public void setImage(BufferedImage image) {
		if (image != null) {
			width = image.getWidth();
			height = image.getHeight();
			panelImage = image;
			repaint();
		}
	}

	/**
	 * Clear the image on this panel.
	 */
	public void clearImage() {
		Graphics imageGraphics = panelImage.getGraphics();
		imageGraphics.setColor(Color.LIGHT_GRAY);
		imageGraphics.fillRect(0, 0, width, height);
		repaint();
	}

	// The following methods are redefinitions of methods
	// inherited from superclasses.

	/**
	 * Tell the layout manager how big we would like to be. (This method gets
	 * called by layout managers for placing the components.)
	 * 
	 * @return The preferred dimension for this component.
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	/**
	 * This component needs to be redisplayed. Copy the internal image to
	 * screen. (This method gets called by the Swing screen painter every time
	 * it want this component displayed.)
	 * 
	 * @param g
	 *            The graphics context that can be used to draw on this
	 *            component.
	 */
	@Override
	public void paintComponent(Graphics g) {
		Dimension size = getSize();
		g.clearRect(0, 0, size.width, size.height);
		if (panelImage != null) {
			g.drawImage(panelImage, 0, 0, null);
		}
	}
}
