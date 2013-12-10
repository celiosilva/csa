package br.com.delogic.cbsa.manager;

import java.io.InputStream;

public interface UploadManager {

	/**
	 * Stores the content with a new name.
	 * 
	 * @param inputStream
	 *            - content to be stored
	 * @param fileName
	 *            - fileName which will be used to extract the extension and use
	 *            the metadata
	 * @return a new file name named file-something.extension
	 */
	String create(InputStream inputStream, String fileName);

	/**
	 * Replaces the content.
	 * 
	 * @param inputStream
	 *            - content to be stored
	 * @param fileName
	 *            - fileName which will be used to extract the extension and use
	 *            the metadata as well as use the same name for the file
	 */
	void update(InputStream inputStream, String fileName);

	/**
	 * Returns the path to the content. It may return the relative path to the
	 * application, like /context/path/to or the full URL including the protocol
	 * like http://mydomain.com/path/to
	 * 
	 * @param name
	 *            of the content
	 * @return content access path on the web
	 */
	String get(String name);

}
