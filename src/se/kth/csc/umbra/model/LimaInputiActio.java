package se.kth.csc.umbra.model;

import java.io.IOException;
import java.io.InputStream;

/**
 * LimaInputiActio is an interface for Objects to perform an
 * action on an InputStream and allows it to return an object.
 * When implementing this interface, you should not handle
 * eventual IOExceptions, these should be handled by the
 * calling method.
 * 
 * @author Max Nordlund
 */
public interface LimaInputiActio {
	Object act(InputStream input) throws IOException;
}
