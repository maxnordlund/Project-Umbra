package kth.vs.proto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window implements ComponentListener, MouseListener {

	public static void main(String[] args) {
		new Window();
	}

	private JFrame frame;
	private ImagePanel panel;
	private BufferedImage image;
	private Graphics2D graphics;
	private JTextArea text;
	private Dimension size;

	public Window() {
		size = new Dimension(200, 200);
		frame = new JFrame("Window");
		frame.setSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
		frame.addComponentListener(this);

		text = new JTextArea(size.width - 16, size.height - 36);
		text.setText("Mrs. Moore, please don't have anymore.//"
				+ "The more you have the more you'll want they say//"
				+ "But enough is as good as a feast anyday.");

		initializeImage();

		frame.setVisible(true);
	}

	private void initializeImage() {
		size = frame.getSize();

		image = new BufferedImage(size.width - 16, size.height - 36, 1);
		graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

		graphics.setColor(Color.BLACK);
		drawText(graphics);
		graphics.dispose();
		
		panel = new ImagePanel(image);

		frame.add(panel);
		frame.pack();
	}

	private void updateImage() {
		frame.remove(panel);
		initializeImage();

	}

	private void drawText(Graphics2D g) {
		g.drawString("Don't have anymore, Mrs. Moore!", 0, 10);
//		Graphics tg = text.getGraphics();
		text.print(g);
		
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// Do nothing

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// Do nothing

	}

	@Override
	public void componentResized(ComponentEvent e) {
		// System.out.println("resize!");
		updateImage();

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// Do nothing
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		updateImage();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
