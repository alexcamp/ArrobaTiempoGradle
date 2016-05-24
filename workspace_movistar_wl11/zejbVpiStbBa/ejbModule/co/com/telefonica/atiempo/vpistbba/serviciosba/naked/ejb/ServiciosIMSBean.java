/*
 * Creado el 25/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba.naked.ejb;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import javax.ejb.EJBException;
import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR602S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.ServiciosIMSInterfaz;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author Dcardena
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ServiciosIMSBean  extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
implements ServiciosIMSInterfaz
{
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	private DBManager dbSeq ;
	private SimpleDateFormat df ;
	
	public void setSessionContext (SessionContext ctx)
	throws EJBException, RemoteException
	{
		super.setSessionContext (ctx) ;
		// Creacion de DataSource
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
		
		// TODO: revisar este formato
		df = new SimpleDateFormat ("dd/MM/yyyy") ;
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ServiciosIMSInterfaz#enviarAsignacionRecursos(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarAsignacionRecursos(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ServiciosIMSInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.interfaces.atiempo.TR602S)
	 */
	public void recibirAsignacionRecursos(TR602S tr602s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}


	

}
