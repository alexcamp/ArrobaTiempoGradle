/*
 * Created on Sep 26, 2005
 *
 */
package com.tecnonautica.batch;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tecnonautica.utiles.basicos.ArrayUtil;

/**
 * @author Marco Alarcón "Dono"
 * Clase que maneja la definición de tablas a mantener y sus parámetros
 * Se encarga de parsear el archvo XML de configuración
 */
public class DefinicionDeTablas {

	public static final String CONFIG_REGISTROS = "config-registros";
	
	public static final String HORA_INICIO = "hora_inicio";
	public static final String PERIODICIDAD = "periodicidad";
	public static final String DATASOURCE = "datasource";
	
	public static final String TABLAS = "tablas";
	
	public static final String TABLA = "tabla";
	public static final String NOMBRE_TABLA = "nombre_tabla";
	public static final String NOMBRE_CAMPO = "nombre_campo";
	public static final String TIPO_CAMPO = "tipo_campo";
	public static final String DURACION = "duracion";
	
	public static final String NOMBRE_ESTADO = "nombre_estado";
	public static final String TIPO_ESTADO = "tipo_estado";
	public static final String VALOR_ESTADO = "valor_estado";
	
	public static final int INDEX_NOMBRE_TABLA = 0;
	public static final int INDEX_NOMBRE_CAMPO = 1;
	public static final int INDEX_TIPO_CAMPO = 2;
	public static final int INDEX_DURACION = 3;
	public static final int INDEX_NOMBRE_ESTADO = 4;
	public static final int INDEX_TIPO_ESTADO = 5;
	public static final int INDEX_VALOR_ESTADO = 6;
	
	public static final int TOT_INDEXES = 7;
	
	public static final String TIPOS_CAMPOS[] = {"DATE", "TIMESTAMP"};
	public static final String TIPOS_ESTADOS[] = {"SMALLINT", "INTEGER", "BIGINT"};
	
	private String hora_inicio = "";
	private String periodicidad = "";
	private String datasource = "";
	private Collection tablasParams = new Vector();
	private int errCod = 0;
	private String errMsg = "";
	
	/**
	 * Constructor que analiza y transforma el archivo de configuración XML a un formato utilizable dentro de la aplicación
	 * @param inputXML URL del archivo XML de configuración
	 * @throws ThreadEliminacionRegistrosException
	 */
	public DefinicionDeTablas(URL inputXML) throws ThreadEliminacionRegistrosException {
		InputStream in = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			in = inputXML.openStream();
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(in);
			NodeList nodeCR = (NodeList) document.getElementsByTagName(CONFIG_REGISTROS).item(0).getChildNodes();
			for (int i = 0; i < nodeCR.getLength(); i++) {
				Node address = nodeCR.item(i);
				if (address.getNodeType() == Node.ELEMENT_NODE) {
					String nameChild = address.getNodeName();
					NodeList childAddress = address.getChildNodes();
					for (int j = 0; j < childAddress.getLength(); j++) {
						Node childAdd = childAddress.item(j);
						if (childAdd.getNodeType() == Node.TEXT_NODE) {
							String textChild = childAdd.getNodeValue();
							if (textChild == null) textChild = "";
							if (HORA_INICIO.equals(nameChild)) {
								this.hora_inicio = textChild;
							} else if (PERIODICIDAD.equals(nameChild)) {
								this.periodicidad = textChild;
							} else if (DATASOURCE.equals(nameChild)) {
								this.datasource = textChild;
							}
						}
					}
				}
			}
			
			if (hora_inicio.equals("")) {
				errCod = 1;
				errMsg = "El archivo de configuración no tiene el campo <hora_inicio> definido, o el campo no contiene datos";
			} else if (periodicidad.equals("")) {
				errCod = 1;
				errMsg = "El archivo de configuración no tiene el campo <periodicidad> definido, o el campo no contiene datos";
			} else if (datasource.equals("")) {
				errCod = 1;
				errMsg = "El archivo de configuración no tiene el campo <datasource> definido, o el campo no contiene datos";
			}
			
			if (document.getElementsByTagName(TABLAS).getLength() == 0) {
				errCod = 2;
				errMsg = "El archivo de configuración no tiene tablas definidas para supervisar (tag <tablas> no contiene datos o no está definido).";
			}
			
			if (errCod != 0) throw new ThreadEliminacionRegistrosException(errMsg);
			
			NodeList nodeTB = (NodeList) document.getElementsByTagName(TABLAS).item(0).getChildNodes();
			for (int i = 0; i < nodeTB.getLength(); i++) {
				Node address = nodeTB.item(i);
				if (address.getNodeType() == Node.ELEMENT_NODE) {
					String nameChild = address.getNodeName();
					if (TABLA.equals(nameChild)) {
						String[] configTabla = new String[TOT_INDEXES];
						NamedNodeMap nodeMap = address.getAttributes();
						for (int j = 0; j < nodeMap.getLength(); j++) {
							Node childAdd = nodeMap.item(j);
							String attrName = childAdd.getNodeName();
							if (childAdd.getNodeType() == Node.ATTRIBUTE_NODE) {
								String textChild = childAdd.getNodeValue();
								if (NOMBRE_TABLA.equals(attrName)) {
									configTabla[INDEX_NOMBRE_TABLA] = textChild;
								} else if (NOMBRE_CAMPO.equals(attrName)) {
									configTabla[INDEX_NOMBRE_CAMPO] = textChild;
								} else if (TIPO_CAMPO.equals(attrName)) {
									configTabla[INDEX_TIPO_CAMPO] = textChild;
								} else if (DURACION.equals(attrName)) {
									configTabla[INDEX_DURACION] = textChild;
								} else if (NOMBRE_ESTADO.equals(attrName)) {
									configTabla[INDEX_NOMBRE_ESTADO] = textChild;
								} else if (TIPO_ESTADO.equals(attrName)) {
									configTabla[INDEX_TIPO_ESTADO] = textChild;
								} else if (VALOR_ESTADO.equals(attrName)) {
									configTabla[INDEX_VALOR_ESTADO] = textChild;
								}
							}
						}
						// se verifica que el tipo de campo sea uno de los soportados
						if (ArrayUtil.contains(TIPOS_CAMPOS, configTabla[INDEX_TIPO_CAMPO])) {
							// se verifica que (de no ser nulo el nombre del campo de estado) el tipo de estado sea uno de los soportados
							if (configTabla[INDEX_NOMBRE_ESTADO] != null) {
								if (ArrayUtil.contains(TIPOS_ESTADOS, configTabla[INDEX_TIPO_ESTADO])) {
									tablasParams.add(configTabla);
								}
							} else {
								tablasParams.add(configTabla);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			throw new ThreadEliminacionRegistrosException(e.toString());
		} catch (ParserConfigurationException e) {
			throw new ThreadEliminacionRegistrosException(e.toString());
		} catch (SAXException e) {
			throw new ThreadEliminacionRegistrosException(e.toString());
		}
	}
	
	/**
	 * @return
	 */
	public String getDatasource() {
		return datasource;
	}

	/**
	 * @return
	 */
	public String getHora_inicio() {
		return hora_inicio;
	}

	/**
	 * @return
	 */
	public String getPeriodicidad() {
		return periodicidad;
	}

	/**
	 * @param string
	 */
	public void setDatasource(String string) {
		datasource = string;
	}

	/**
	 * @param string
	 */
	public void setHora_inicio(String string) {
		hora_inicio = string;
	}

	/**
	 * @param string
	 */
	public void setPeriodicidad(String string) {
		periodicidad = string;
	}

	/**
	 * @return
	 */
	public Collection getTablasParams() {
		return tablasParams;
	}

	/**
	 * @param collection
	 */
	public void setTablasParams(Collection collection) {
		tablasParams = collection;
	}

	/**
	 * @return
	 */
	public int getErrCod() {
		return errCod;
	}

	/**
	 * @return
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param i
	 */
	public void setErrCod(int i) {
		errCod = i;
	}

	/**
	 * @param string
	 */
	public void setErrMsg(String string) {
		errMsg = string;
	}

}
