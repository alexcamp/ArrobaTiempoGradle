/*
 * Constants.java $Id: Constants.java,v 1.1.2.1 2011/03/30 22:15:08 lfmartinez Exp $
 * 
 * @author amartoq
 * 
 * Created on Aug 9, 2005
 * Copyright (C) 2005 www.xperience.cl www.interplanet.cl
 */
package com.telefonica_chile.atiempo.utiles;

/**
 * <P>
 * Constantes definidas para los proyectos ATIEMPO
 * </P> 
 * 
 * <P>
 * Contiene definiciones en tiempo de compilacion para varias variables importantes.
 * Muchas clases utilizan estas constantes para definir variables de configuracion, etc.
 * </P>
 * 
 * <P>
 * Extienda alguna de estas variables en su codigo para cambiar facilmente las rutas de instalacion de estos proyectos.
 * </P>
 * 
 * @author Aldrin Martoq <a href="amartoq@interplanet.cl">amartoq@interplanet.cl</a> 
 * @since Created on Aug 9, 2005 @ 4:22:57 PM
 * @version nueva Modificada por Leonardo Caldera A.
 */
public class Constants
{
	/**
	 * Ruta base de instalacion del proyecto. Por omision es <code>/home/atiempo</code>.
	 */
	public static final String INSTALL_PATH = "/home/atiemweb/";

	/**
	 * Ruta para archivos de configuracion. Por omision es <code>/home/atiempo/etc</code>.
	 */
	public static final String INSTALL_ETC_PATH = INSTALL_PATH + "etc/";
	
	/**
	 * Nombres de Archivos de Configuracion
	 */
	public static final String actividadFactoryConfigName="actividadFactory.properties";
	public static final String actividadFactorySTConfigName="actividadFactoryST.properties";
	public static final String vpistbbaConfigName="configVpiStbBa.properties";
	public static final String stConfigName="configST.properties";
	public static final String grabadoresConfigName="grabadores.properties";
	public static final String grabadoresSTConfigName="grabadoresST.properties";
	public static final String actividadesConfigName="actividadesVpiStbBaWF.properties";
	public static final String actividadesSTConfigName="actividadesStStbBaWF.properties";
	public static final String psConfigName="ps.properties";
	public static final String ServicioConfigName="ivr.properties";
	public static final String grabacionesClaveConfigName="claves.properties";
	public static final String grabacionesClavesMasivasConfigName="clavesMasivas.properties";
	
	public static final String HDATASOURCES="configHdataSources.properties";	 
}
