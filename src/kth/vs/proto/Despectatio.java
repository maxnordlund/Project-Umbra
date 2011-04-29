/**
 * The 'view' package contains all aspects of Swing in the project.
 */
package kth.vs.proto;

import static kth.csc.umbra.model.LimaProcurator.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;

import kth.csc.umbra.model.LimaProcurator;
import kth.vs.proto.ImagePanel;

/**
 * Despectatio is the main class the GraphicUserInterface, and handles all tasks
 * related to this. Creating windows, buttons etc.
 * 
 * @author Max Nordlund
 * @author Oskar Segersvärd
 */
public class Despectatio {
	private JFrame frame;
	private BufferedImage middleImage;
	private BufferedImage rotatedImage;
	private JTextArea text;
	private LimaProcurator saveFile;
	private final JScrollPane scroll;
	private ImagePanel panel;
	private JFrame hiddenFrame;
	private Auditor relay;

	public Despectatio(LimaProcurator saveFile) {
		UIManager.put("TextArea.font", new FontUIResource(Font.MONOSPACED,
				Font.ITALIC, 20));

		final Dimension preferredSize = new Dimension(300, 350);
		final int i = 5;

		text = new JTextArea(5, 5);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		text.setBorder(new EmptyBorder(i, i, i, i));
		text.setText("اشقنئش سك عةش يثوى"
				+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nNakanaka!");

		// text.setUI(new UmbraIllusio(text));
		// text.setFont(null);

		this.saveFile = saveFile;
		if (saveFile.hasLocation()) {
			text.setText(this.saveFile.open());
		}

		scroll = new JScrollPane(text);
		scroll.setPreferredSize(preferredSize);

		final JMenuBar menubar = makeMenuBar();

		frame = new JFrame("Proposit Umbra");
		// frame.setIgnoreRepaint(true);
		// frame.createBufferStrategy(2);
		// frame.setPreferredSize(secondarySize);
		// frame.setMinimumSize(secondarySize);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(menubar);

		hiddenFrame = makeHiddenFrame(scroll);

		middleImage = new BufferedImage(scroll.getWidth(), scroll.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		rotatedImage = new BufferedImage(middleImage.getHeight(),
				middleImage.getWidth(), BufferedImage.TYPE_INT_RGB);

		panel = new ImagePanel(rotatedImage);
		panel.setFocusable(true);

		frame.setContentPane(panel);
		frame.pack();

		relay = new Auditor(panel, scroll, this, text);
	}

	/**
	 * Displays the frame and initiates the update-loop.
	 */
	public void show() {

		updateImage();
		frame.setVisible(true);
		// hiddenFrame.setVisible(true);
	}

	public void updateImage() {
		Graphics2D secondary = middleImage.createGraphics();
		scroll.paint(secondary);
		secondary.dispose();

		rotateLeft(middleImage, rotatedImage);
		
		panel.repaint(100);
	}

	private void rotateLeft(BufferedImage sourceImage,
			BufferedImage generatedImage) {
		for (int i = 0; i < sourceImage.getHeight(); i++) {
			for (int j = 0; j < sourceImage.getWidth(); j++) {
				generatedImage.setRGB(i, sourceImage.getWidth() - (j + 1),
						sourceImage.getRGB(j, i));
			}
		}
	}

	private JFrame makeHiddenFrame(JScrollPane scroll) {
		JFrame hiddenFrame = new JFrame("Hidden Window");
		hiddenFrame.add(scroll);
		hiddenFrame.pack();
		return hiddenFrame;
	}

	/**
	 * The created menubar contains: New-button, that creates a new file
	 * Open-button, opens a dialog to find a file for editing Save-button, tries
	 * to save a file, if no 'location' has been set a save dialog will ask you
	 * to input one
	 * 
	 * @return a menubar with basic buttons
	 */
	private JMenuBar makeMenuBar() {
		final JButton newFile = makeButton("newFile.png", "[N]",
				"Creates a new document.", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						saveFile.setLocation(null);
						text.setText(null);
					}
				});

		final JButton open = makeButton("open.png", "[O]", "Open a document.",
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = makeFileChooser();
						int returnVal = chooser.showOpenDialog(frame);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							saveFile.setLocation(chooser.getSelectedFile());
							text.setText(saveFile.open());
							text.repaint();
						}
					}
				});

		final JButton save = makeButton("save.png", "[S]",
				"Save the current document.", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!saveFile.hasLocation()) {
							JFileChooser chooser = makeFileChooser();
							int returnVal = chooser.showSaveDialog(frame);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								saveFile.setLocation(chooser.getSelectedFile());
							}
						}
						boolean success = saveFile.save(text.getText());
						if (success) {
							log("Successfully wrote to " + saveFile);
						} else {
							if (saveFile.hasLocation()) {
								log("Writing to " + saveFile + " failed.");
							} else {
								log("User choose no file.");
							}
						}
					}
				});

		/* final JButton help = makeButton("help.png", "[H]", "Shows the help dialog.",
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) { // TODO event
						// StringBuilder sb = new StringBuilder(
						// "Is your computer unable to export to pdf?");
						// String s = sb.toString();
						// JOptionPane.showMessageDialog(frame, s);
					}
				}); // */

		final JMenuBar menubar = new JMenuBar();
		menubar.setName("menubar");
		menubar.add(newFile);
		menubar.add(open);
		menubar.add(save);
//		menubar.add(help);
		return menubar;
	}

	/**
	 * Creates a button with the supplied tooltip and
	 * {@link java.awt.event.ActionListener ActionListener}. If an icon is
	 * located on the supplied path, than that is used as well. Otherwise, the
	 * description is used.
	 */
	private static JButton makeButton(String path, String desc, String tooltip,
			ActionListener listner) {
		final JButton button;
		Icon icon = getIcon(path);

		if (icon == null) {
			button = new JButton(desc);
		} else {
			button = new JButton(icon);
		}

		button.setToolTipText(tooltip);
		button.addActionListener(listner);
		button.setFocusable(false);

		return button;
	}

	/**
	 * Creates a JFileChooser, sets a 'text format' filter and returns this
	 * JFileChooser.
	 * 
	 * @return the created file chooser
	 */
	private JFileChooser makeFileChooser() {
		final JFileChooser chooser = new JFileChooser();
		final FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text files", "txt");
		chooser.setFileFilter(filter);
		return chooser;
	}

}
