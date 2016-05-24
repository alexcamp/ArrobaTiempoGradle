package co.com.telefonica.atiempo.vpistbba.grabacion;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.vpistbba.grabacion.GrabadorComun;
/**
 * @author lcaldera
 *
 */
public class GrabadorConfigurarAula365 extends GrabadorComun {

	/* (non-Javadoc)
	 * @see com.telefonica_chile.vpistbba.grabacion.GrabadorComun#grabarDatosPropios(javax.servlet.http.HttpServletRequest)
	 */
	private static Logger log=Logger.getLogger(GrabadorConfigurarAula365.class);
	
	protected void grabarDatosPropios(HttpServletRequest request)
	{
		this.setObservacion("Actividad Configurar Aula365 Cerrada Manualmente.");
		try
		{
			Long numPet = new Long(request.getParameter("instanciaProceso"));
			RecursosBAInterfaces recursosBAInterfaces=new RecursosBADelegate();
			recursosBAInterfaces.marcarNovedadAutomaticaCTAula(numPet);
		}
		catch (ATiempoAppEx e)
		{
			log.error("ATiempoAppEx",e);
		}
		catch(Exception e)
		{
			log.error("Exception",e); 	
		}
	}

}
