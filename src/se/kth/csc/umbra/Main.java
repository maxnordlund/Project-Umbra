/**
 * Project Umbra is a Mhasiran text editor.
 * 
 * It is a text editor for the fictional language Mhasira, which text is written
 * in a special font in vertical columns from left to right.
 * 
 * The editor is capable of displaying text vertically and allows the user to
 * write simple texts with relative ease. The editor displays all correct
 * variations of the characters, something other programs such as word is
 * incapable of.
 */
package se.kth.csc.umbra;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This class is responsible for setting up the object graph and starting the
 * application.
 * 
 * @author Max Nordlund
 * @version 2011.04.01
 */
public final class Main implements Runnable {
	private static final String USAGE = "Project-Umbra path";

	@Override
	public void run() {
		Main.main(new String[0]);
	}

	/**
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		/*if (args.length < 1) {
			System.out.println(USAGE);
			System.exit(0);
		}*/

		JFrame frame = new JFrame("This is Umbra");
		Container contentPane = frame.getContentPane();

		JTextArea text = new JTextArea();
		contentPane.add(text);

		makeMenuBar(frame);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setBounds(50, 50, 300, 500);
		frame.setVisible(true);
	}

	/**
	 * @param frame
	 */
	private static void makeMenuBar(JFrame frame) {
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		JButton newFile = makeButton("newFile", "[N]");
		newFile.setToolTipText("Creates a new document.");
		newFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		menubar.add(newFile);

		JButton open = makeButton("open", "[O]");
		open.setToolTipText("Open a document.");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		menubar.add(open);

		JButton save = makeButton("save", "[S]");
		save.setToolTipText("Save the current document.");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		menubar.add(save);
	}

	/**
	 * Tries to read the contents from the file located at the supplied path.
	 * Important <em>not</em> to prefix the path with any slash ('/'), e.g.
	 * <code>"open.png"</code> and not <code>"/open.png"</code> or
	 * <code>"./open.png"</code>.
	 * 
	 * @param path
	 *            The relative path from this program or the absolute path from
	 *            within this programs jar.
	 * @return An icon from the supplid path.
	 */
	private static JButton makeButton(String path, String desc) {
		Icon icon = null;
		InputStream input = null;
		try {
			input = new FileInputStream("./" + path + ".png");
			icon = readIcon(input);
		} catch (FileNotFoundException e) {
			input = Main.class.getResourceAsStream("/" + path + ".png");
			try {
				icon = readIcon(input);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (icon == null) {
			return new JButton(desc);
		}
		return new JButton(icon);
	}

	/**
	 * @param input
	 * @return
	 * @throws IOException
	 */
	private static Icon readIcon(InputStream input) throws IOException {
		Icon icon = null;
		BufferedImage img = ImageIO.read(input);
		icon = new ImageIcon(img);
		return icon;
	}
}
