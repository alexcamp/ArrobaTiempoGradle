/*
 * Creado el 2/06/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios.web.comun.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.EQUIPOSCLIENTE;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.TR800E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XAREPAIRINFO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800S.TR800S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800S.TR800SParser;
import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicios.web.comun.CommunicatorWS;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CommunicatorWSMainTest {
	
	public static void main (String[] args) {
		String tr800sString = "";
		TR800E tr800e = initializeTR();
		CommunicatorWS communicatorWS = new CommunicatorWS();
		tr800sString = communicatorWS.sendMessageByTRxxxE(tr800e);
		System.out.println("TR800S: " + tr800sString);
		TR800SParser dpe = new TR800SParser();
		TR800S res = dpe.parse(tr800sString);
		System.out.println(res);
//		testParseToString();
	}
	
	public static TR800E initializeTR (){
		TR800E tr800e = new TR800E();
		
		tr800e.setAcoord_x(20.0);
		tr800e.setAcoord_y(15.2);
		tr800e.setAddress("Direccion");
		tr800e.setAworktype_label("Work Type Label");
		ArrayList equipos_Cli = new ArrayList();
		EQUIPOSCLIENTE equiposcliente = new EQUIPOSCLIENTE();
		EQUIPOSCLIENTE equiposcliente2 = new EQUIPOSCLIENTE();
		equiposcliente.setInvsn("INVAN");
		equiposcliente2.setInvsn("INVAN2");
		equipos_Cli.add(equiposcliente);
		equipos_Cli.add(equiposcliente2);
		tr800e.setEQUIPOS_CLIENTE(equipos_Cli);
		XAREPAIRINFO xarepairinfo = new XAREPAIRINFO();
		xarepairinfo.setCategoria_Averia("Tipo de Averia 1");
		xarepairinfo.setCodigo_Apertura("CODAP01");
		tr800e.setXA_REPAIR_INFO(xarepairinfo);
		List XA_ID_PCTV = new ArrayList();
		XA_ID_PCTV.add("TV_12345678_1");
		//XA_ID_PCTV.add("TV_12345678_2");
		tr800e.setXA_ID_PCTV(XA_ID_PCTV);
		
		return tr800e;
	}

	public static void testParseToString() {
		
		TR800E tr800e = initializeTR();
		
		ITRxxxBase trXXXe = tr800e;
		
		String xmlTR800E = trXXXe.toString();
		String classTR = trXXXe.getClass().getName().split("\\.")[trXXXe.getClass().getName().split("\\.").length-1];
		System.out.println("Clase Original: " + classTR);
		System.out.println(xmlTR800E);
	}
	
	public static void testParseXMLUtilities() {
		
		TR800E tr800e = initializeTR();
		
		ITRxxxBase trXXXe = tr800e;
		
		try {
			String xmlTR800E = XMLUtilities.marshall(trXXXe);
			String classTR = trXXXe.getClass().getName().split("\\.")[trXXXe.getClass().getName().split("\\.").length-1];
			System.out.println("Clase Original: " + classTR);
			System.out.println(xmlTR800E);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public static String testParseReflection(TR800E trXXXBase) {
	    StringBuffer result = new StringBuffer();
	    String newLine = System.getProperty("line.separator");

	    result.append(trXXXBase.getClass().getName());
	    result.append(" Object {");
	    result.append(newLine);

	    //determine fields declared in this class only (no fields of superclass)
	    Field[] fields = trXXXBase.getClass().getDeclaredFields();
	    int cantFields = trXXXBase.getClass().getDeclaredFields().length;

	    //print field names paired with their values
	    for (int pos = 0; pos < cantFields ; pos++) {
	      result.append("  ");
	      try {
	        result.append(fields[pos].getName());
	        result.append(": ");
	        //requires access to private field:
	        result.append(fields[pos].get(trXXXBase));
	      }
	      catch (IllegalAccessException ex) {
	        System.out.println(ex);
	      }
	      result.append(newLine);
	    }
	    result.append("}");

	    return result.toString();
	  }
	
	
}
