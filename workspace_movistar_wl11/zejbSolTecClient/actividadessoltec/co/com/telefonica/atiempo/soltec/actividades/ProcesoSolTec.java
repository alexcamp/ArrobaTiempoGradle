package co.com.telefonica.atiempo.soltec.actividades;

import java.util.Map;

import com.telefonica_chile.atiempo.actividades.DefaultProceso;
import com.telefonica_chile.atiempo.actividades.IProceso;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.STConfig;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProcesoSolTec extends DefaultProceso implements IProceso {

	//public static final String ID_TEMPLATE = SolTecConfig.getVariable("ID_TEMPLATE");
	public static final String ID_TEMPLATE = STConfig.getVariable("ID_TEMPLATE");
	
	public static final String ESTRUCTURA_DATOS = STConfig.getVariable("ESTRUCTURA_DATOS");
	/**
	 * @param idTemplateWf
	 * @param datos
	 */
	public ProcesoSolTec(Map datos) {
		super(ProcesoSolTec.ID_TEMPLATE, datos);
		// TODO Auto-generated constructor stub
	}
	
	public ProcesoSolTec(String numeroPeticion, Map datos) {
		super(ProcesoSolTec.ID_TEMPLATE, numeroPeticion, datos);
		setNombreEstructuraDatos(ESTRUCTURA_DATOS);
	}

	public void onInicio() throws TnProcesoExcepcion{
	}

}
