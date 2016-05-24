package com.telefonica_chile.bandeja.web.acciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * La responsabilidad de esta clase es controlar las interacciones de la vista
 * operacionMasivaModem y respuestaMasivaModem.
 * 
 * @author cacano
 *  
 */
public class OperacionMasivaModemAcc extends Accion {

	private static transient Logger log = LoggerFactory
			.getLogger(OperacionMasivaModemAcc.class);

	private static String PROPERTIES_PATH = "/home/atiemweb/etc/acs.properties";

	private static final String TEMP_PATH = System
			.getProperty("java.io.tmpdir");

	private static volatile Thread thread = null;

	protected void ejecutar(HttpServletRequest request) throws Evento {
		String operation = null;
		DiskFileUpload parser = null;
		try {
			if (FileUpload.isMultipartContent(request)) {
				Properties properties = loadProperties(PROPERTIES_PATH);
				parser = new DiskFileUpload();
				FileItem file = null;
				List fileItems = parser.parseRequest(request);
				for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()
							&& "operacion".equals(item.getFieldName())) {
						operation = item.getString();
					} else if ("file".equals(item.getFieldName())) {
						file = item;
					}
				}
				if (operation != null) {
					String nameFile = Calendar.getInstance().getTimeInMillis()
							+ ".txt";
					String pathFile = TEMP_PATH + nameFile;
					String operationName = file.getName();
					File fullFile = new File(file.getName());
					File savedFile = new File(pathFile);
					file.write(savedFile);
					executeThread(pathFile, operation, operationName,
							properties);
					request
							.setAttribute(
									"respuesta",
									"Operación lanzada para ejecución. Para ver los resultados consulte la bandeja de reportes.");
				}
			}
			setResultado(operation != null ? "respuestaMasivaModem"
					: "operacionMasivaModem");
		} catch (Exception e) {
			throw new Evento(e.getMessage());
		}
	}

	/**
	 * Crea una instancia runnable y se asigna a un hilo para que el proceso de
	 * llamada del shell se haga en un subproceso.
	 * 
	 * @param pathFile
	 *            Archivo de datos que se le pasa al shell
	 * @param operation
	 *            (Alta|Baja) masiva
	 * @param nameOperation
	 *            nombre de la operación a colocar en el archivo de salida.
	 *            (Relación)
	 * @param properties
	 *            Archivo de propiedades de la aplicación
	 */
	private static void executeThread(final String pathFile,
			final String operation, final String nameOperation,
			final Properties properties) {
		thread = new Thread(new Runnable() {

			public void run() {
				FileOutputStream fos = null;
				try {
					String proceso =  properties.getProperty("SHELL_COMMAND").trim() + " "
							+ properties.getProperty("SHELL") + " " + pathFile
							+ " " + operation + " ArchivoEntrada:" + nameOperation;
					Process p = Runtime.getRuntime().exec(proceso);
					StreamGobbler errorGobbler = new StreamGobbler(p
							.getErrorStream(), "ERROR");
					fos = new FileOutputStream(TEMP_PATH
							+ Calendar.getInstance().getTimeInMillis() + ".txt");
					StreamGobbler outputGobbler = new StreamGobbler(p
							.getInputStream(), "OUTPUT", fos);
					errorGobbler.start();
					outputGobbler.start();
					p.waitFor();
				} catch (Exception e) {
					e.printStackTrace(System.out);
				} finally {
					if (fos != null) {
						try {
							fos.flush();
							fos.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}

		}, "HiloOpeModem");
		thread.start();
	}

	/**
	 * Carga el archivo properties
	 * 
	 * @param pathFile
	 *            Archivo properties
	 * @return Archivo con las propiedades
	 */
	public Properties loadProperties(String pathFile) {
		Properties properties = new Properties();
		FileInputStream input = null;
		try {
			input = new FileInputStream(pathFile);
			properties.load(input);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return properties;
	}
}
/**
 * Clase que no permite colgar la llamada al SHELL por los flujos de entrada y
 * salida.
 * 
 * Debido a que algunas plataformas nativas sólo proporcionan un tamaño de búfer
 * limitado para la entrada estándar y los flujos de salida, si no se escribe
 * rápidamente el flujo de entrada o se lee la secuencia de salida del
 * subproceso puede provocar que el subproceso se cuelgue, hasta generar un
 * deadlock.
 * 
 * @author cacano
 *  
 */

class StreamGobbler extends Thread {
	private InputStream is;

	private String type;

	private OutputStream os;

	StreamGobbler(InputStream is, String type) {
		this(is, type, null);
	}

	StreamGobbler(InputStream is, String type, OutputStream redirect) {
		this.is = is;
		this.type = type;
		this.os = redirect;
	}

	public void run() {
		try {
			PrintWriter pw = null;
			if (os != null)
				pw = new PrintWriter(os);

			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (pw != null)
					pw.println(line);
				System.out.println(type + ">" + line);
			}
			if (pw != null)
				pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}