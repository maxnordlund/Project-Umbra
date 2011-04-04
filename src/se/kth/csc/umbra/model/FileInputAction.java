package se.kth.csc.umbra.model;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Max Nordlund
 *
 */
public interface FileInputAction {
	Object act(InputStream input) throws IOException;
}
