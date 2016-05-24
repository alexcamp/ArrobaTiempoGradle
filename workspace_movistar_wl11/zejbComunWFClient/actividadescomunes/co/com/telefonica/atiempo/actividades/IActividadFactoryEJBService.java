/*
 * Created on 12-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.actividades;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface IActividadFactoryEJBService {
	
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion;

}
