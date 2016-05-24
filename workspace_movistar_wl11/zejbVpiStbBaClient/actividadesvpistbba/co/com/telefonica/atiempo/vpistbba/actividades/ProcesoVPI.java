/*
 * Created on 13-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.actividades;

import java.util.Map;

import com.telefonica_chile.atiempo.actividades.DefaultProceso;
import com.telefonica_chile.atiempo.actividades.IProceso;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProcesoVPI extends DefaultProceso implements IProceso {

	public static final String ID_TEMPLATE = VpistbbaConfig.getVariable("ID_TEMPLATE");
	public static final String ESTRUCTURA_DATOS = VpistbbaConfig.getVariable("ESTRUCTURA_DATOS");
	/**
	 * @param idTemplateWf
	 * @param datos
	 */
	public ProcesoVPI(Map datos) {
		super(ProcesoVPI.ID_TEMPLATE, datos);
		
	}
	
	public ProcesoVPI(Long numeroPeticion, Map datos) {
		super(ProcesoVPI.ID_TEMPLATE, numeroPeticion.toString(), datos);
		setNombreEstructuraDatos(ESTRUCTURA_DATOS);
	}

	public void onInicio() throws TnProcesoExcepcion{
	}

}
