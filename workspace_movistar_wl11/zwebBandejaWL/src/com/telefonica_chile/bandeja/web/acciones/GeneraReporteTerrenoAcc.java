package com.telefonica_chile.bandeja.web.acciones;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaDelegate;
import co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaInterface;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

public class GeneraReporteTerrenoAcc extends Accion
{
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	protected void ejecutar(HttpServletRequest request) throws Evento
	{
		String urlReportes=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_TER");
		request.setAttribute("URL_ACCION_ARCHIV_TER",urlReportes);
		if(request.getParameter("f1")!=null && request.getParameter("f2")!=null && request.getParameter("opcocat")!=null)
		{
			try
			{
				Fecha fecha1=new Fecha(request.getParameter("f1"),"dd/MM/yyyy");
				Fecha fecha2=new Fecha(request.getParameter("f2"),"dd/MM/yyyy");
				log.debug("Fecha1:"+fecha1.getFechaconFormato());
				log.debug("Fecha2:"+fecha2.getFechaconFormato());
				String tipo=request.getParameter("opcocat");
				if(fecha1.compareTo(fecha2)>0)
					request.setAttribute("fechasMalas","1");
				else
				{
					int diferencia=fecha1.differenceInDays(fecha2);
					log.debug("Diferencia:"+diferencia);
					if(diferencia>3)
						request.setAttribute("fechasMalas","1");
					else
					{
						AccionMasivaInterface aMI = new AccionMasivaDelegate();
						ArrayList pet=new ArrayList();
						AccionMasivaMSGDTO aM = new AccionMasivaMSGDTO();
						aM.setNumeroPeticion(new Long( request.getParameter("usuaId") ));//Se pone el id del usuario
						aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_TERRENO")));
						String param=request.getParameter("f1")+"#"+request.getParameter("f2")+"#"+tipo;
						log.debug("Param:"+param);
						aM.setCodigoActividad(request.getParameter("userAccion")+"#"+param);
						aM.setInstanciaActividad("");
						pet.add(aM);
						aMI.enviaAccion(pet);
						request.setAttribute("generandoArchivo","1");						
					}
				}
			}
			catch(Exception e)
			{
				log.debug("Exception",e);
				request.setAttribute("generandoArchivoMal","1");
			}
					
		}
	}
	
}
