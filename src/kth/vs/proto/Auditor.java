/**
 * 
 */
package kth.vs.proto;

import static kth.csc.umbra.model.LimaProcurator.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;

/**
 * @author max.nordlund
 * @version 2011..
 */
class Auditor implements CaretListener, KeyListener, MouseInputListener,
		MouseWheelListener, ComponentListener {
	private ImageComponent imageComponent;
	private JScrollPane scroll;
	private Despectatio despectatio;
	private JTextArea text;

	public Auditor(ImageComponent imageComponent, JScrollPane scroll, JTextArea text,
			JFrame frame, Despectatio despectatio) {
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
		// log(event.toString());
		// master.updateImage();
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
		// log(event.toString());
		// master.updateImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {
		processEvent(event);
		// log(event.toString());
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
		// log(event.toString());
		// master.updateImage();

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
		// log(event.toString());
		// master.updateImage();

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
		// log(event.toString());
		// master.updateImage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent event) {
		processEvent(event);
		// log(event.toString());
		// master.updateImage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		// log(event.toString());
		// log(event.getKeyCode() + " " + text.getCaretPosition());
		text.dispatchEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		// log(event.toString());
		text.dispatchEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent event) {
		// log(event.toString());
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// log(Integer.toString(e.getWheelRotation()));
		scroll.dispatchEvent(e);
		despectatio.updateImage();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		despectatio.resize();
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
