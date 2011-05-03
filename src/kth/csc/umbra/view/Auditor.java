/**
 * 
 */
package kth.csc.umbra.view;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * SuperListener. Once created, it adds itself to to the different things it
 * received as input.
 * 
 * @author Max Nordlund
 * @author Oskar Segersvärd
 * @version 2011.05.03
 */
class Auditor implements CaretListener, KeyListener, MouseInputListener,
		MouseWheelListener, ComponentListener {
	private ImageComponent imageComponent;
	private JScrollPane scroll;
	private Despectatio despectatio;
	private JTextArea text;

	public Auditor(ImageComponent imageComponent, JScrollPane scroll,
			JTextArea text, JFrame frame, Despectatio despectatio) {
		this.imageComponent = imageComponent;
		this.scroll = scroll;
		this.despectatio = despectatio;
		this.text = text;

		imageComponent.addMouseWheelListener(this);
		imageComponent.addMouseListener(this);
		imageComponent.addMouseMotionListener(this);
		imageComponent.addKeyListener(this);

		text.addCaretListener(this);

		frame.addComponentListener(this);
	}

	/**
	 * Translates the point of the event 90 degrees clockwise.
	 * 
	 * @param event
	 */
	private void translate(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		event.translatePoint(imageComponent.getHeight() - y - x, x - y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		processEvent(event);
	}

	/**
	 * Calls <code>translate(event)</code> to move the point to the correct
	 * location relative the target JScrollPanel and JTextArea. The dispatches
	 * the event to both. Lastly calls <code>despectatio.updateImage()</code>.
	 * 
	 * @param event
	 */
	private void processEvent(MouseEvent event) {
		translate(event);
		event.setSource(scroll);
		scroll.dispatchEvent(event);

		event.setSource(text);
		event.translatePoint(0, scroll.getVerticalScrollBar().getValue());
		text.dispatchEvent(event);

		despectatio.updateImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {
		processEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {
		// master.updateImage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {
		processEvent(event);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		processEvent(event);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent event) {
		processEvent(event);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent event) {
		// processEvent(event);
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		text.dispatchEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		text.dispatchEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent event) {
		text.dispatchEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.CaretListener#caretUpdate(javax.swing.event.CaretEvent)
	 */
	@Override
	public void caretUpdate(CaretEvent e) {
		despectatio.updateImage();
	}

	/**
	 * Sends a message to the JScrollPane to react to a MouseWheelEvent and then
	 * updates the displayed image.
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll.dispatchEvent(e);
		despectatio.updateImage();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// Do nothing

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// Do nothing

	}

	/**
	 * Once the frame has been resized, the resize() function is called to make
	 * sure that the displayed image is of the correct size.
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
		despectatio.resize();

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// Do nothing
	}
}
