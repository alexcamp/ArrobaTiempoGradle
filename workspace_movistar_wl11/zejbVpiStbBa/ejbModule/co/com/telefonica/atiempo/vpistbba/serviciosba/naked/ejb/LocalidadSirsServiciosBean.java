/*
 * Creado el 25/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba.naked.ejb;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import org.apache.log4j.Logger;
import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR510S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR512S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadSirsInterfaz;


/**
 * @author dcardena
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class LocalidadSirsServiciosBean  extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
implements LocalidadSirsInterfaz
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
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadSirsInterfaz#enviarAsignacionRecursos(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarAsignacionRecursos(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadSirsInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.interfaces.atiempo.TR510S)
	 */
	public void recibirAsignacionRecursos(TR510S tr510s) throws ATiempoAppEx {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadSirsInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void recibirAsignacionRecursos(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadSirsInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.interfaces.atiempo.TR512S)
	 */
	public void recibirAsignacionRecursos(TR512S tr512s) throws ATiempoAppEx {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}
	
}
