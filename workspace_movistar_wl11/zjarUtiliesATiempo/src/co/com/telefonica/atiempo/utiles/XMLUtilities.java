//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: XMLUtilities.java,v 1.1.2.1 2011/03/30 22:45:19 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.utiles;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.castor.mapping.BindingType;
import org.castor.mapping.MappingUnmarshaller;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.mapping.MappingLoader;
import org.exolab.castor.xml.ClassDescriptorResolver;
import org.exolab.castor.xml.ClassDescriptorResolverFactory;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLClassDescriptorResolver;
import org.xml.sax.InputSource;

/**
 * @author ylapchik
 * @version $Revision: 1.1.2.1 $
 */
public class XMLUtilities {
	
	private static final String MAPPING_FILE = "/co/com/telefonica/atiempo/interfaces/atiempo/mapping.xml";
	private static Mapping mapping;
	private static Logger log=Logger.getLogger(XMLUtilities.class);
	//	AT-1147 - CR11097 - 24-Abr-2008, se mantiene castor 1.0.5 - guido
	private static ClassDescriptorResolver classDescriptorResolver = null;
	
	/**
	 * @param is Is the input stream to the data to get the object
	 * @return An object that comes from the XML
	 */
	public static Object unmarshall(InputStream is) throws ATiempoAppEx {
		try {
			ensureInit();	
//OLD		Unmarshaller unmarshaller = new Unmarshaller(mapping);
//AT-1147 - CR11097 - 24-Abr-2008, se mantiene castor 1.0.5 - guido
			Unmarshaller unmarshaller = new Unmarshaller();
			unmarshaller.setResolver((XMLClassDescriptorResolver) classDescriptorResolver);
			return unmarshaller.unmarshal(new InputSource(is));
			
		} catch (Exception ex) {
			log.error("Exception on unmarshall() i", ex);
			throw new ATiempoAppEx(ATiempoAppEx.UNMARSHALL, ex);
		}		
	}
	
	/**
	 * @param xml Is the xml to convert in object
	 * @return An object that comes from the XML
	 */
	public static Object unmarshall(String xml) throws ATiempoAppEx {
		try {
			ensureInit();	
//OLD		Unmarshaller unmarshaller = new Unmarshaller(mapping);
//AT-1147 - CR11097 - 24-Abr-2008, se mantiene castor 1.0.5 - guido
			Unmarshaller unmarshaller = new Unmarshaller();
			unmarshaller.setResolver((XMLClassDescriptorResolver) classDescriptorResolver);
			return unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception ex) {
			log.error("Exception on unmarshall() s", ex);
			throw new ATiempoAppEx(ATiempoAppEx.UNMARSHALL, ex);
		}		
	}
	
	public static String marshall(Object object) throws ATiempoAppEx {
		try {
			ensureInit();
			StringWriter sw = new StringWriter();			
			Marshaller marshaller = new Marshaller(sw);
			marshaller.setMapping(mapping);
			marshaller.marshal(object);
			String message = sw.toString();
			sw.close();
			return message;
		} catch (Exception ex) {
			log.error("Exception on marshall()", ex);
			throw new ATiempoAppEx(ATiempoAppEx.MARSHALL, ex);
		}
	}
	
	private static synchronized void ensureInit() throws IOException, MappingException {
	if (mapping == null) {
		log.debug("ensureInit() Creando mapping en cache");
		mapping = new Mapping(XMLUtilities.class.getClassLoader());
		mapping.loadMapping(new InputSource(XMLUtilities.class.getResourceAsStream(MAPPING_FILE)));
//AT-1147 - CR11097 - 24-Abr-2008, se mantiene castor 1.0.5
		classDescriptorResolver = ClassDescriptorResolverFactory.createClassDescriptorResolver(BindingType.XML);
		MappingUnmarshaller mappingUnmarshaller = new MappingUnmarshaller();
		MappingLoader mappingLoader =mappingUnmarshaller.getMappingLoader(mapping, BindingType.XML);
		classDescriptorResolver.setMappingLoader(mappingLoader);
//Fin AT-1147
	}
}

}
