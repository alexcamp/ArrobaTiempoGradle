//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: XMLUtilities.java,v 1.1 2011/03/30 18:26:38 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.InputStream;

import co.com.telefonica.atiempo.XMLException;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class XMLUtilities {
	
	private static final String MAPPING_FILE = "/co/com/telefonica/atiempo/interfaces/atiempo/mapping.xml";
	
	public static Object unmarshal(InputStream is) throws XMLException {
		return co.com.telefonica.atiempo.util.XMLUtilities.unmarshal(MAPPING_FILE, is);		
	}
	
	public static Object unmarshal(String xml) throws XMLException {
		return co.com.telefonica.atiempo.util.XMLUtilities.unmarshal(MAPPING_FILE, xml);	
	}
	
	public static String marshal(Object object) throws XMLException {
		return co.com.telefonica.atiempo.util.XMLUtilities.marshal(MAPPING_FILE, object);
	}

}
