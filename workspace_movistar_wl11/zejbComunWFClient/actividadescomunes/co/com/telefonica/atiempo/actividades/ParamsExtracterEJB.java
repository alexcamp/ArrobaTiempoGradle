/*
 * Created on 09-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.actividades;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ParamsExtracterEJB extends DefaultHandler {
//		private String mensaje;
//		//private StringBuffer outMensaje;
//		private Map outMap = new HashMap();
//		private String nombreEstructura;
//		private boolean enEstructra = false;
//		private String llave;
//		private IActividadEJB actividad;
//		private static final int FUNCION_GETMAPA = 0;
//		private static final int FUNCION_SETMAPA = 1;
//		private int funcion = ParamsExtracterEJB.FUNCION_GETMAPA;
//		private String valorActual = "";
//
//		public ParamsExtracterEJB(String XMLMensaje, IActividadEJB aActividad) {
//			this.actividad = aActividad;
//			this.mensaje = XMLMensaje;
//			this.nombreEstructura = actividad.getNombreEstructuraDatos();
//		}
//
//		public void setMapDatos() throws ParserConfigurationException, SAXException, IOException {
//			this.funcion = ParamsExtracterEJB.FUNCION_SETMAPA;
//			SAXParserFactory factory = SAXParserFactory.newInstance();
//			SAXParser saxParser = factory.newSAXParser();
//			saxParser.parse(new InputSource(new StringReader(this.mensaje)),this);			
//		}
//	
//		public Map getMapDatos() throws ParserConfigurationException, SAXException, IOException {
//			this.funcion = ParamsExtracterEJB.FUNCION_GETMAPA;
//			SAXParserFactory factory = SAXParserFactory.newInstance();
//			SAXParser saxParser = factory.newSAXParser();
//			saxParser.parse(new InputSource(new StringReader(this.mensaje)),this);			
//			return this.outMap;
//		}
//
//		/* (non-Javadoc)
//		* @see org.xml.sax.ContentHandler#characters(char[], int, int)
//		*/
//		public void characters(char[] ch, int start, int length)
//			throws SAXException {
//			if (null != this.llave && this.enEstructra) {
//				String valor = new String(ch, start, length);
//				this.valorActual = valor;
//			}
//		}
//
//		/* (non-Javadoc)
//		 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
//		 */
//		public void endElement(String arg0, String arg1, String arg2)
//			throws SAXException {
//			if (this.nombreEstructura.equals(arg2)) {
//				this.enEstructra = false;
//			} else if(this.enEstructra) {
//				switch (this.funcion) {
//					case ParamsExtracterEJB.FUNCION_GETMAPA :
//					//this.outMensaje.append("<" + this.llave + ">" + valor + "</" + this.llave + ">\n");
//					this.outMap.put(this.llave, this.valorActual);		
//						break;
//
//					case ParamsExtracterEJB.FUNCION_SETMAPA :
//					this.actividad.setDato(this.llave, this.valorActual);
//						break;
//				}
//			}
//			this.llave = null;
//			this.valorActual = "";
//		}
//
//		/* (non-Javadoc)
//		 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
//		 */
//		public void startElement(
//			String arg0,
//			String arg1,
//			String arg2,
//			Attributes arg3)
//			throws SAXException {
//			if (this.nombreEstructura.equals(arg2)) {
//				this.enEstructra = true;
//			} else if (this.enEstructra) {
//				this.llave = arg2;
//			}
//			this.valorActual = "";
//		}

	private String mensaje;
	//private StringBuffer outMensaje;
	private Map outMap = new HashMap();
	private String nombreEstructura;
	private boolean enEstructra = false;
	private String llave;
	private ActividadEJBDTO actividad;
	private static final int FUNCION_GETMAPA = 0;
	private static final int FUNCION_SETMAPA = 1;
	private int funcion = ParamsExtracterEJB.FUNCION_GETMAPA;
	private String valorActual = "";

	public ParamsExtracterEJB(String XMLMensaje, ActividadEJBDTO aActividad) {
		this.actividad = aActividad;
		this.mensaje = XMLMensaje;
		this.nombreEstructura = actividad.getNombreEstructuraDatos();
	}

	public void setMapDatos() throws ParserConfigurationException, SAXException, IOException {
		this.funcion = ParamsExtracterEJB.FUNCION_SETMAPA;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(new InputSource(new StringReader(this.mensaje)),this);			
	}

	public Map getMapDatos() throws ParserConfigurationException, SAXException, IOException {
		this.funcion = ParamsExtracterEJB.FUNCION_GETMAPA;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(new InputSource(new StringReader(this.mensaje)),this);			
		return this.outMap;
	}

	/* (non-Javadoc)
	* @see org.xml.sax.ContentHandler#characters(char[], int, int)
	*/
	public void characters(char[] ch, int start, int length)
		throws SAXException {
		if (null != this.llave && this.enEstructra) {
			String valor = new String(ch, start, length);
			this.valorActual = valor;
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String arg0, String arg1, String arg2)
		throws SAXException {
		if (this.nombreEstructura.equals(arg2)) {
			this.enEstructra = false;
		} else if(this.enEstructra) {
			switch (this.funcion) {
				case ParamsExtracterEJB.FUNCION_GETMAPA :
				//this.outMensaje.append("<" + this.llave + ">" + valor + "</" + this.llave + ">\n");
				this.outMap.put(this.llave, this.valorActual);		
					break;

				case ParamsExtracterEJB.FUNCION_SETMAPA :
				this.actividad.setDato(this.llave, this.valorActual);
					break;
			}
		}
		this.llave = null;
		this.valorActual = "";
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(
		String arg0,
		String arg1,
		String arg2,
		Attributes arg3)
		throws SAXException {
		if (this.nombreEstructura.equals(arg2)) {
			this.enEstructra = true;
		} else if (this.enEstructra) {
			this.llave = arg2;
		}
		this.valorActual = "";
	}

}
