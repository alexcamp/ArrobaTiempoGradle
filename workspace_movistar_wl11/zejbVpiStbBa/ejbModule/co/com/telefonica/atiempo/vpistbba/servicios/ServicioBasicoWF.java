/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.vpistbba.actividades.ProcesoVPI;

import com.telefonica_chile.atiempo.actividades.IProceso;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocal;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocalHome;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * @author TCS
 */
public abstract class ServicioBasicoWF extends ServicioBasico {

	public void iniciarFlujo(Object obj) throws ATiempoAppEx {

		try {

			String listaVar = VpistbbaConfig.getVariable(obtenerVariableWF());
			if (listaVar == null || (listaVar != null && listaVar.length() <= 0))
				throw new ATiempoAppEx(ATiempoAppEx.LOAD_FILE);

			/** CODIGO NAVIGO, "ADAPTADO" */
			Map datosWF = new HashMap();
			String[] var = Utiles.split(listaVar, ';');
			for (int i = 0; var != null && i < var.length; i++) {
				if (var[i] == null || var[i].length() == 0)
					continue;
				datosWF.put(var[i], "");
			}

			Long nroPeticion=(Long) obj;			  
			IProceso procesoVPISTBBA = new ProcesoVPI(nroPeticion, datosWF);

			WFSessionLocalHome home = (WFSessionLocalHome) HomeFactory.getHome(WFSessionLocalHome.JNDI_NAME);
			WFSessionLocal wfSession = (WFSessionLocal) home.create();

			wfSession.iniciarProceso(procesoVPISTBBA);
		} catch (NamingException e) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (CreateException e) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (TnProcesoExcepcion e) {
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}

	}

	protected abstract String obtenerVariableWF();

}
