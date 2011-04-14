/**
 * The 'view' package contains all aspects of Swing
 * in the project.
 */
package se.kth.csc.umbra.view;

import static se.kth.csc.umbra.model.LimaProcurator.*;
import se.kth.csc.umbra.model.LimaProcurator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Despectatio is the main class the GraphicUserInterface, and handles all tasks
 * related to this. Creating windows, buttons etc.
 * 
 * @author Max Nordlund
 * @author Oskar Segersvärd
 */
public class Despectatio {
	private JFrame frame;
	private JTextArea text;
	private LimaProcurator saveFile;

	public Despectatio(LimaProcurator saveFile) {
		text = new JTextArea(5, 5);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		text.setUI(new UmbraIllusio(text));
		
		this.saveFile = saveFile;
		if (saveFile.hasLocation()) {
			text.setText(this.saveFile.open());
		}

		final JScrollPane scroll = new JScrollPane(text);

		final Dimension preferredSize = new Dimension(300, 400);
		final JMenuBar menubar = makeMenuBar();

		frame = new JFrame("Proposit Umbra");
		// frame.setIgnoreRepaint(true);
		// frame.createBufferStrategy(2);
		frame.setPreferredSize(preferredSize);
		frame.setMinimumSize(preferredSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(menubar);
		frame.setContentPane(scroll);

		frame.pack();
	}

	/**
	 * Displays the frame and initiates the update-loop.
	 */
	public void show() {
		frame.setVisible(true);

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
							log("Writing to " + saveFile + " failed.");
						}
					}
				});

		/*
		 * final JButton help = makeButton("help.png", "[H]",
		 * "Shows the help dialog.", new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { //TODO
		 * event StringBuilder sb = new StringBuilder(
		 * "Is your computer unable to export to pdf?"); String s =
		 * sb.toString(); JOptionPane.showMessageDialog(frame, s); } }); //
		 */

		final JMenuBar menubar = new JMenuBar();
		menubar.setName("menubar");
		menubar.add(newFile);
		menubar.add(open);
		menubar.add(save);
		// menubar.add(help);
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
		Icon icon = getIcon(path);

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
