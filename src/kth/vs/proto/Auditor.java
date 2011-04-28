/**
 * 
 */
package kth.vs.proto;

import static kth.csc.umbra.model.LimaProcurator.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


/**
 * @author max.nordlund
 * @version 2011..
 */
class Auditor implements CaretListener, KeyListener, MouseInputListener, MouseWheelListener {
	private JComponent source;
	private JComponent target;
	private Despectatio master;
	private JTextArea text;

	public Auditor(JComponent source, JComponent target, Despectatio master, JTextArea text) {
		this.source = source;
		this.target = target;
		this.master = master;
		this.text = text;
		
		source.addMouseWheelListener(this);
		source.addMouseListener(this);
		source.addMouseMotionListener(this);
		source.addKeyListener(this);
		
		text.addCaretListener(this);
	}

	/**
	 * Translates the point of the event 90 degrees clockwise.
	 * 
	 * @param event
	 */
	private void translate(MouseEvent event){
		int x = event.getX();
		int y = event.getY();
		event.translatePoint(source.getHeight() - y - x, x - y);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		translate(event);
		target.dispatchEvent(event);
//		log(event.toString());
		master.updateImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {
		translate(event);
		target.dispatchEvent(event);
//		log(event.toString());
		master.updateImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {
		translate(event);
		target.dispatchEvent(event);
//		log(event.toString());
		master.updateImage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {
		translate(event);
		target.dispatchEvent(event);
//		log(event.toString());
		master.updateImage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		translate(event);
		target.dispatchEvent(event);
//		log(event.toString());
		master.updateImage();

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
		translate(event);
		target.dispatchEvent(event);
//		log(event.toString());
		master.updateImage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent event) {
		translate(event);
		target.dispatchEvent(event);
//		log(event.toString());
		master.updateImage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent event) {
//		log(event.toString());
		System.out.println(event.getKeyCode());
		System.out.println(text.getCaretPosition());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) {
//		log(event.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent event) {
//		log(event.toString());
//		switch(event.getKeyCode()){
//		case 8 : text.
//		(text.getCaretPosition()); 
//			break;
//		default: text.dispatchEvent(event);
//		}
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
		master.updateImage();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
//		log(Integer.toString(e.getWheelRotation()));
		target.dispatchEvent(e);
		master.updateImage();
		
	}
}
