/*
 * Created on 07-07-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.actividades.parser;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface IParserMSGService {
	
	public Object unmarshall(String xmlEntrada) throws ATiempoAppEx;
	public String marshall(Object msgDTO) throws ATiempoAppEx;
}
