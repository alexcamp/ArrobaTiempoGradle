/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.soltec.servicios;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.soltec.actividades.ProcesoSolTec;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;

import com.telefonica_chile.atiempo.actividades.IProceso;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocal;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocalHome;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.servicios.util.Utiles;

/**
 * @author TCS
 */
public abstract class ServicioBasicoWF extends ServicioBasico {

	public void iniciarFlujo(Object obj) throws ATiempoAppEx {

		try {

			String listaVar = STConfig.getVariable(obtenerVariableWF());
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

			String nroIncidente = "inci_";
			nroIncidente= nroIncidente + (Long) obj;			  
			IProceso procesoSOLTEC = new ProcesoSolTec(nroIncidente, datosWF);

			WFSessionLocalHome home = (WFSessionLocalHome) HomeFactory.getHome(WFSessionLocalHome.JNDI_NAME);
			WFSessionLocal wfSession = (WFSessionLocal) home.create();

			wfSession.iniciarProceso(procesoSOLTEC);
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
