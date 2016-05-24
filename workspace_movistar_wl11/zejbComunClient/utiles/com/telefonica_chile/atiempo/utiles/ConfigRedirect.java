/*
 * Created on Oct 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.utiles;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;


/**
 * @author amartoq
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfigRedirect {

	private static final String PACKAGE_PATH =
		"/com/telefonica_chile/atiempo/utiles";
	private static final String DUMMY_FILE = "dummy.properties";
	private static final String DUMMY_WASD_PATH =
		"bin" + PACKAGE_PATH + "/" + DUMMY_FILE;
	private static final String DUMMY_WAS_PATH =
		"zejbComunClient.jar!" + PACKAGE_PATH + "/" + DUMMY_FILE;
	private static final String REDIRECT_FILE =
		"/etc/configredirect.properties";
	private static Properties properties = null;

	static {
		init();
	}

	static void init() {
		try {

			URL dummyURL = ConfigRedirect.class.getResource("dummy.properties");
			String path = dummyURL.getPath();
			if (path.startsWith("file:")) {
				path = path.substring("file:".length());
			}

			System.out.println("XXXX INITIALIZING REDIRECTS INI XXXX");
			System.out.println("local path        = [" + path + "]");


			/* check if we are on WASD o WAS */
			if (path.endsWith(DUMMY_WASD_PATH)) {
				path =
					path.substring(0, path.length() - DUMMY_WASD_PATH.length());
				System.out.println("matched WASD path = [" + path + "]");
			} else if (path.endsWith(DUMMY_WAS_PATH)) {
				path =
					path.substring(0, path.length() - DUMMY_WAS_PATH.length());
				System.out.println("matched WAS  path = [" + path + "]");
			} else {
				path = null;
				System.out.println("BUUUUUU");
			}

			if (path != null) {
				path = path + REDIRECT_FILE;
				System.out.println("Loading properties from path=[" + path + "]");
				FileInputStream fis = new FileInputStream(path);
				properties = new Properties();
				properties.load(fis);
			}
			System.out.println("XXXX INITIALIZING REDIRECTS END XXXX");
		} catch (Exception e) {
			System.out.println("ERROR INICIALIZANDO: " + e);
		}
	}

	public static String getRedirect(String original) {
		if (properties == null) {
			return original;
		}
		String key = "redirect." + original;
		String ret = properties.getProperty(key);

		if (ret == null) {
			return original;
		}
		
		return ret;
	}
}
