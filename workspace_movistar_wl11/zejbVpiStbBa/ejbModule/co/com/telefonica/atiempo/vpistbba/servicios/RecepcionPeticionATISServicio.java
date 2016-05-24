/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import java.util.ArrayList;
import java.util.Iterator;

import com.tecnonautica.utiles.db.DBManager;

import co.com.telefonica.atiempo.interfaces.atiempo.TR001S;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.tecnonautica.utiles.db.DBManager;

/**
 * @author TCS
 * 
 * Mensaje: TR_001_S
 */
public class RecepcionPeticionATISServicio extends ServicioBasicoWF {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasicoWF#obtenerVariableWF()
	 */
	protected String obtenerVariableWF() {
		return "WF-INICIAR-VARIABLE";
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR001S peticionAtis = (TR001S) obj[0];
		PeticionesInterfaces peticionesServices = new PeticionesDelegate();
		ArrayList nrosPets=peticionesServices.salvarPeticionATIS(peticionAtis);
		for(Iterator iterator=nrosPets.iterator();iterator.hasNext();)
		{
			Long nroPet=(Long) iterator.next();
			iniciarFlujo(nroPet);	
		}
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		TR001S tr001s = (TR001S) XMLUtilities.unmarshall(mensaje);		
		
		// si el emulador está activo genero el id de la peticion de atis
		
		if (ServiceLocatorEmulator.emuladorActivado()) {
			
			DBManager dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_VPISTBBA); // verificar que es el datasource correcto
			
			long proximo = dbSeq.seqNextValLong("CORRELATIVO_MENSAJE");	
			tr001s.setId(String.valueOf(proximo));	
			tr001s.setRequestNumber(proximo);
		}
		Object[] obj = new Object[1];
		obj[0] = tr001s;		
		return obj;
	}

}
