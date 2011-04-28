/**
 * The 'model' package contains all aspects of file handling in the project.
 */
package kth.csc.umbra.model;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * LimaProcurator is the file manager. Handles opening and closing files.
 * 
 * 
 * @author Max Nordlund
 * @version 2011.04.14
 */
public class LimaProcurator {
	private File location;

	public LimaProcurator() {
		this.location = null;
	}

	/**
	 * Sets the file path to the input String.
	 * 
	 * @param file
	 *            path
	 */
	public LimaProcurator(String path) {
		this.location = new File(path);
	}

	/**
	 * @return <code>true</code> if a file location has been set. Otherwise
	 *         <code>false</code>.
	 */
	public boolean hasLocation() {
		return (this.location != null);
	}

	/**
	 * @return the path to the file
	 */
	public File getLocation() {
		return location;
	}

	/**
	 * Sets the file path to the supplied path.
	 * 
	 * @param file
	 *            path
	 */
	public void setLocation(File location) {
		this.location = location;
	}

	/**
	 * Opens the file at the specified location, reads the file as a UTF-8
	 * encoded text file. The content is then returned. If there is no location,
	 * an empty String is returned.
	 * 
	 * @return the contents of the opened file as a String
	 */
	public String open() {
		if (location == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream(location), "UTF-8");
			while (scan.hasNextLine()) {
				sb.append(scan.nextLine());
				if (scan.hasNextLine()) {
					sb.append('\n');
				}
			}
		} catch (FileNotFoundException fnfe) {
			log(fnfe);
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
		return sb.toString();
	}

	/**
	 * Saves the input String to a file at the specified file location using
	 * UTF-8 encoding.
	 * 
	 * @param String
	 *            to be saved
	 * @return <code>true</code> if the save was successful. Otherwise
	 *         <code>false</code>.
	 */
	public boolean save(String text) {
		if (location == null) {
			return false;
		}
		Writer writer = null;
		boolean success = true;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(location),
					"UTF-8");
			writer.write(text);
		} catch (FileNotFoundException fnfe) {
			success = false;
			log(fnfe);
		} catch (IOException ioe) {
			success = false;
			log(ioe);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ioe) {
					success = false;
					log(ioe);
				}
			}
		}
		return success;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(location == null) {
			return "";
		}
		return location.toString();
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
	public static Object getResource(String path, LimaInputiActio action) {
		InputStream input = null;
		Object returnObject = null;
		try {
			input = new FileInputStream("./" + path);
			returnObject = action.act(input);
		} catch (FileNotFoundException fnfe) {
			input = LimaProcurator.class.getResourceAsStream("/" + path);
			try {
				returnObject = action.act(input);
			} catch (IOException ioe) {
				log(ioe);
			}
		} catch (IOException ioe) {
			log(ioe);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ioe) {
				log(ioe);
			}
		}
		return returnObject;
	}

	public static Icon getIcon(String path) {
		return (Icon) getResource(path, new LimaInputiActio() {
			@Override
			public Object act(InputStream input) throws IOException {
				Icon icon = null;
				BufferedImage img = ImageIO.read(input);
				if(img == null) {
					return null;
				}
				icon = new ImageIcon(img);
				return icon;
			}
		});
	}

	/**
	 * Logs the input string.
	 * 
	 * @param String
	 *            to be logged.
	 */
	public static void log(String str) {
		System.out.println(str);
	}

	/**
	 * Logs the input Exception.
	 * 
	 * @param Exception
	 *            to be logged.
	 */
	public static void log(Exception e) {
		e.printStackTrace();
	}
}
