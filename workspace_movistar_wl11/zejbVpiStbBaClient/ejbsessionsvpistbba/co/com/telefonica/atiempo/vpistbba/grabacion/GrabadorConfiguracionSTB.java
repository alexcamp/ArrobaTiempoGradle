/*
 * Created on 26-05-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.grabacion;

import javax.servlet.http.HttpServletRequest;

import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

import com.telefonica_chile.vpistbba.grabacion.GrabadorComun;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GrabadorConfiguracionSTB extends GrabadorComun {

	/* (non-Javadoc)
	 * @see com.telefonica_chile.vpistbba.grabacion.GrabadorComun#grabarDatosPropios(javax.servlet.http.HttpServletRequest)
	 */
	protected void grabarDatosPropios(HttpServletRequest request) {
		try
		{
			String numPetString = request.getParameter("folio");
			Long nroPet = new Long(numPetString);
			String lecContadorDesde=request.getParameter("lecContadorDesde");
			String lecContadorHasta=request.getParameter("lecContadorHasta");
			RecursosDelegate recursosDelegate=new RecursosDelegate();
			int valEstado=new Integer(request.getParameter("estadoPetViene")).intValue();
			if(valEstado!=1)
				recursosDelegate.eliminarLecturaContador(nroPet);
			if(lecContadorDesde!=null || lecContadorHasta!=null)
				recursosDelegate.actualizarLecturaContador(nroPet,lecContadorDesde,lecContadorHasta);
		}
		catch (Exception e)
		{
			log.debug("Excepcion Controlada, Lectura Contador",e);
		}
	}

}
