package se.kth.csc.umbra.view;

import static se.kth.csc.umbra.model.LimaProcurator.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import se.kth.csc.umbra.model.LimaInputiActio;
import se.kth.csc.umbra.model.LimaProcurator;

/**
 * @author Max Nordlund
 * 
 */
public class Despectatio {
	private JFrame frame;
	private JTextArea text;
	private LimaProcurator saveFile;

	public Despectatio(LimaProcurator limaProcurator) {
		this.saveFile = limaProcurator;

		text = new JTextArea(5, 5);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		text.setUI(new UmbraIllusio());
		
		final JScrollPane scroll = new JScrollPane(text);

		final Dimension preferredSize = new Dimension(300, 200);
		final JMenuBar menubar = makeMenuBar();

		frame = new JFrame("Proposit Umbra");
		frame.setPreferredSize(preferredSize);
		frame.setMinimumSize(preferredSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(menubar);
		frame.setContentPane(scroll);

		frame.pack();
	}

	/**
	 * @param b
	 * @see java.awt.Window#setVisible(boolean)
	 */
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

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
							log("Successfully wrote to "
									+ saveFile);
						} else {
							log("Writing to " + saveFile
									+ " failed.");
						}
					}
				});

		// JButton help = makeButton("help.png", "[H]");
		// save.setToolTipText("Shows the help dialog.");
		// save.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// StringBuilder sb = new
		// StringBuilder("Is your computer unable to export to pdf?");
		// String s = sb.toString();
		// JOptionPane.showMessageDialog(frame, s);
		//
		// // TODO Help event
		// }
		// });

		final JMenuBar menubar = new JMenuBar();
		menubar.setName("menubar");
		menubar.add(newFile);
		menubar.add(open);
		menubar.add(save);
		// menubar.add(help);
		return menubar;
	}

	private static JButton makeButton(String path, String desc, String tooltip,
			ActionListener listner) {
		Icon icon = (Icon) getResource(path, new LimaInputiActio() {
			@Override
			public Object act(InputStream input) throws IOException {
				Icon icon = null;
				BufferedImage img = ImageIO.read(input);
				icon = new ImageIcon(img);
				return icon;
			}
		});

		final JButton button;
		if (icon == null) {
			button = new JButton(desc);
		} else {
			button = new JButton(icon);
		}

		button.setToolTipText(tooltip);
		button.addActionListener(listner);

		return button;
	}

	private JFileChooser makeFileChooser() {
		final JFileChooser chooser = new JFileChooser();
		final FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text files", "txt");
		chooser.setFileFilter(filter);
		return chooser;
	}
}
