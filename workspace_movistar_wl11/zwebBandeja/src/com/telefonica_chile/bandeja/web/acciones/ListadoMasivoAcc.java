package com.telefonica_chile.bandeja.web.acciones;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;


public class ListadoMasivoAcc extends Accion
{

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento
	{
		String urlReportes=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_CEN");
		request.setAttribute("URL_ACCION_ARCHIV_CEN",urlReportes);
	}	
}
