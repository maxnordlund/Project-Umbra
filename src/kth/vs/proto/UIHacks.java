package kth.vs.proto;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UIHacks {
	private final static boolean WORKING = true;

	private Dimension d; // Screen size

	private JFrame vF; // Visible frame
	private JFrame sF; // Secret frame
	private JPanel vC;
	private JPanel sC;

	private BufferedImage vi;
	private BufferedImage si;
	private ImageComponent vip;
	private ImageComponent sip;
	private JScrollPane scrollP;
	private JTextArea text;

	public static void main(String[] args) {
		new UIHacks();
	}

	public UIHacks() {
		initialize();
	}

	private void initialize() {
		d = Toolkit.getDefaultToolkit().getScreenSize();
		createFrames();
		createJPanels();
		createImagePanels();
		rotate90Left(si, vi);
		packAndShow();
		vC.revalidate();
		System.out.println();
	}

	private void createFrames() {
		vF = new JFrame("Visible");
		vF.setDefaultCloseOperation(3);
		sF = new JFrame("Secret");
		sF.setDefaultCloseOperation(3);
	}

	private void createJPanels() {
		vC = (JPanel) vF.getContentPane();
		vC.setBorder(new EmptyBorder(6, 6, 6, 6));
		sC = (JPanel) sF.getContentPane();
		sC.setBorder(new EmptyBorder(6, 6, 6, 6));

		createImages();
	}

	private void createImages() {
		vi = new BufferedImage(300, 500, 1);

		text = new JTextArea();
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		// text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		text.setText("It's not working yet, is it?");
		text.setPreferredSize(new Dimension(vi.getHeight(), vi.getWidth()));

		// si = text.getImage();

		// createSecretImage();

		si = new BufferedImage(vi.getHeight(), vi.getWidth(), 1);
		set9by9ColorSquare(si, 200, 100, Color.YELLOW.getRGB());
		set9by9ColorSquare(si, 2, 2, Color.WHITE.getRGB());
		// si.createGraphics().drawImage(vi, 0, 0, null);

		// g2.translate(-text.getWidth(), -text.getHeight());
		// text.paint(g2);
		// g2.dispose();
		//
		// si = (BufferedImage) text.createImage(text.WIDTH, text.HEIGHT);

	}

	private void set9by9ColorSquare(BufferedImage image, int a, int b, int rgb) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				si.setRGB(i + a, j + b, rgb);
			}
		}
	}

	private void rotate90Left(BufferedImage si, BufferedImage vi) {
		for (int i = 0; i < si.getHeight(); i++) {
			for (int j = 0; j < si.getWidth(); j++) {
				vi.setRGB(i, si.getWidth() - (j + 1), si.getRGB(j, i));
			}
		}
	}

	private void createImagePanels() {
		vip = new ImageComponent();
		vip.setImage(vi);
		// vip.addMouseListener(createML());
		// vip.addMouseMotionListener(createMML());
		vC.add(vip);

		scrollP = new JScrollPane(text);
		scrollP.setAutoscrolls(true);
		scrollP.setVisible(true);

		Graphics2D g = (Graphics2D) si.getGraphics();
		scrollP.paint(g);
		g.dispose();

		sip = new ImageComponent();
		sip.setImage(si);

		sC.add(sip);
		// sC.add(scrollP);
	}

	private void packAndShow() {
		if (WORKING) {
			sF.pack();
			sF.setLocation(200 + d.width / 2, (d.height / 2)
					- (sF.getHeight() / 2));
			sF.setVisible(true);
		}

		vF.pack();
		vF.setLocation(d.width / 2 - vF.getWidth() / 2,
				d.height / 2 - vF.getHeight() / 2);
		vF.setVisible(true);
	}

	private MouseListener createML() {
		MouseListener ml = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				translatePointToSecret(e);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				translatePointToSecret(e);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				translatePointToSecret(e);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				translatePointToSecret(e);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				translatePointToSecret(e);

			}

		};
		return ml;
	}

	private MouseMotionListener createMML() {
		MouseMotionListener mml = new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				translatePointToSecret(e);

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				translatePointToSecret(e);

			}
		};
		return mml;
	}

	private void translatePointToSecret(MouseEvent e) {
		Point p = e.getPoint();
		// System.out.println("-----------------\n" + p.x + " : " + p.y);
		int x = p.x;
		int y = p.y;
		e.translatePoint(vi.getHeight() - y - x, x - y);
		// System.out.println(e.getX() + " : " + e.getY());
	}

}
