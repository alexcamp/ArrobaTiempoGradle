/*
 * Created on May 20, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;

/**
 * @author 804226
 *
 * For TCS ( TATA Consultancy Services).
 */
public class VerUploadFileAcc extends Accion{

	
	protected void ejecutar(HttpServletRequest request) throws Evento {
		
//		Obtenemos Mapas de Aplciaciones (?).
		UrlAplicaciones url = new UrlAplicaciones();
	 	String urlVPISTBBA = url.getUrl("VPISTBBA");
	 	HashMap mapAplicaciones = url.mapAplicaciones();
	 	
		request.setAttribute("mapAplicaciones", mapAplicaciones);
		
	}

}
