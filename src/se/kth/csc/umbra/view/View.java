package se.kth.csc.umbra.view;

import static se.kth.csc.umbra.model.FileUtil.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import se.kth.csc.umbra.model.FileInputAction;

/**
 * @author Max Nordlund
 * 
 */
public class View {
	private JFrame frame;
	private JTextArea text;

	public View() {
		frame = new JFrame("Project Umbra");
		text = new JTextArea();
		text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		Container contentPane = new JScrollPane(text);
		frame.setContentPane(contentPane);

		JMenuBar menubar = makeMenuBar();
		frame.setJMenuBar(menubar);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void show() {
		frame.pack();
		frame.setBounds(50, 50, 300, 500);
		frame.setVisible(true);
	}

	private static JButton makeButton(String path, String desc, String tooltip,
			ActionListener listner) {
		Icon icon = (Icon) getResource(path, new FileInputAction() {
			@Override
			public Object act(InputStream input) throws IOException {
				Icon icon = null;
				BufferedImage img = ImageIO.read(input);
				icon = new ImageIcon(img);
				return icon;
			}
		});

		JButton button;
		if (icon == null) {
			button = new JButton(desc);
		}
		button = new JButton(icon);

		button.setToolTipText(tooltip);
		button.addActionListener(listner);

		return button;
	}

	/**
	 * @param frame
	 */
	private  JMenuBar makeMenuBar() {
		JMenuBar menubar = new JMenuBar();

		JButton newFile = makeButton("newFile.png", "[N]",
				"Creates a new document.", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Check for save first
						text.setText(null);
					}
				});
		menubar.add(newFile);

		JButton open = makeButton("open.png", "[O]", "Open a document.",
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Actually do something with the file opened
						JFileChooser chooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"Text files", "txt");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(frame);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							System.out.println("You chose to open this file: "
									+ chooser.getSelectedFile().getName());
						}
					}
				});
		menubar.add(open);

		JButton save = makeButton("save.png", "[S]",
				"Save the current document.", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Actually save to the file specified
						JFileChooser chooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"Text files", "txt");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showSaveDialog(frame);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							System.out
									.println("You chose to save to this file: "
											+ chooser.getSelectedFile()
													.getName());
						}
					}
				});
		menubar.add(save);

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
		// menubar.add(help);
		return menubar;
	}
}
