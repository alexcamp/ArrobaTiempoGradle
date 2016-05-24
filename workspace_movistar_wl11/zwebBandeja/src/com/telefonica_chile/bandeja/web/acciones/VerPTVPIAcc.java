package com.telefonica_chile.bandeja.web.acciones;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.dto.AuxBandejaDTO;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;
/**
 * @author lcaldera
 *
 */
public class VerPTVPIAcc extends Accion
{
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	protected void ejecutar(HttpServletRequest request) throws Evento
	{
		try
		{
			HttpSession session = request.getSession(true);
			AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion)getControladorDeAplicacion();
			UsuarioWeb usuario = control.getUsuario();
			Long petiNumero=new Long(request.getParameter("idPeticion"));
			log.debug("PetiNumero en VerPTVPIAcc:"+petiNumero);
			BIntegradaSessionLocalHome home=(BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
			BIntegradaSessionLocal local=home.create();
			
			//CR9664 - adocarmo - inicio
			String aula=new String(request.getParameter("aula"));
			//CR9664 - adocarmo - fin
			
			//AuxBandejaDTO auxBandejaDTO=local.recuperaAuxBandeja(petiNumero);
			AuxBandejaDTO auxBandejaDTO=local.recuperaAuxBandeja(petiNumero,aula);
						
			request.setAttribute("auxBandeja",auxBandejaDTO);
			request.setAttribute("URL_VPI", ApplicationConfig.getUrlBase(ApplicationConfig.APP_VPISTBBA));
			request.setAttribute("usuario", usuario);
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			setResultado("mensajeErrorAutorizacion");
		}
	}
}
