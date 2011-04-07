package se.kth.csc.umbra.model;

import java.io.*;

import se.kth.csc.umbra.Main;

public class FileUtil {

	public FileUtil() {

	}

	public static String Open(FileUtil file) {

		return null;

	}

	public static String Save(FileUtil file) {

		return null;
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
	 * @param action
	 *            The action to perform on the input stream.
	 * @return An object from the supplied action using the file from the
	 *         supplied path.
	 */
	public static Object getResource(String path, FileInputAction action) {
		InputStream input = null;
		Object returnObject = null;
		try {
			input = new FileInputStream("./" + path);
			returnObject = action.act(input);
		} catch (FileNotFoundException e) {
			input = Main.class.getResourceAsStream("/" + path);
			try {
				returnObject = action.act(input);
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
		return returnObject;
	}
}
