/**
 * Project Umbra is a Mhasiran text editor.
 * 
 * It is a text editor for the fictional language Mhasira, which text is written
 * in a special font in columns from left to right.
 * 
 * The editor is capable of displaying text vertically and allows the user to
 * write simple texts with relative ease. The editor displays all correct
 * variations of the characters, something other programs such as word is
 * incapable of.
 */
package se.kth.csc.umbra;

import javax.swing.SwingUtilities;

import se.kth.csc.umbra.model.LimaProcurator;
import se.kth.csc.umbra.view.Despectatio;

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
	private LimaProcurator file;
	private Despectatio frame;

	private Main(LimaProcurator file) {
		this.file = file;
		this.frame = new Despectatio(this.file);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		frame.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LimaProcurator file;
		if (args.length == 0) {
			file = new LimaProcurator();
		} else {
			file = new LimaProcurator(args[0]);
		}
		SwingUtilities.invokeLater(new Main(file));
	}
}
