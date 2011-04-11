package se.kth.csc.umbra.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import se.kth.csc.umbra.Main;

/**
 * 
 * @author Max Nordlund
 * @version 2011.04.09
 */
public class LimaProcurator {
	private File location;
	
	public LimaProcurator() {
		this.location = null;
	}

	public LimaProcurator(String path) {
		this.location = new File(path);
	}

	public String open() {
		if(location == null) {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
		return sb.toString();
	}

	public boolean save(String text) {
		if(location == null) {
			return false;
		}
		Writer writer = null;
		boolean success = true;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(location),
					"UTF-8");
			writer.write(text);
		} catch (FileNotFoundException e) {
			success = false;
			e.printStackTrace();
		} catch (IOException e) {
			success = false;
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					success = false;
					e.printStackTrace();
				}
			}
		}
		return success;
	}
	
	public boolean hasLocation() {
		return (this.location != null);
	}

	public File getLocation() {
		return location;
	}

	public void setLocation(File location) {
		this.location = location;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
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
	
	public static void log(String str) {
		System.out.println(str);
	}
}
