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

import javax.swing.SwingUtilities;

import se.kth.csc.umbra.model.FileManager;
import se.kth.csc.umbra.view.View;

/**
 * This class is responsible for setting up the object graph and starting the
 * application.
 * 
 * @author Max Nordlund
 * @author Oskar Segersvärd
 * @version 2011.04.01
 */
public final class Main implements Runnable {
	@SuppressWarnings("unused")
	private static final String USAGE = "Project-Umbra path";
	private FileManager file;
	private View frame;

	private Main(FileManager file) {
		this.file = file;
		this.frame = new View(this.file);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileManager file;
		if (args.length == 0) {
			file = new FileManager();
		} else {
			file = new FileManager(args[0]);
		}
		SwingUtilities.invokeLater(new Main(file));
	}
}
