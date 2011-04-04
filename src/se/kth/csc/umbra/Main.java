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

	@Override
	public void run() {
		start();
	}

	/**
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		start();
	}
	
	private static void start() {
		View view = new View();
		view.show();
	}
}
