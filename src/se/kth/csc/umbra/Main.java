/**
 * 
 */
package se.kth.csc.umbra;

/**
 * @author max.nordlund
 *
 */
public final class Main {

	private static final String USAGE = "Project-Umbra path";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 1) {
			System.out.println(USAGE);
			System.exit(0);
		}
	}

}
