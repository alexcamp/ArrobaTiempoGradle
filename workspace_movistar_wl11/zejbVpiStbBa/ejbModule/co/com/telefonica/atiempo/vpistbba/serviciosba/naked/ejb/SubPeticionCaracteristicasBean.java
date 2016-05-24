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
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.SubPeticionCaracteristicasInterfaz;


/**
 * @author Dcardena
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class SubPeticionCaracteristicasBean  extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
implements SubPeticionCaracteristicasInterfaz
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
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.SubPeticionCaracteristicasInterfaz#obtenerCaracteristica(co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal, java.lang.Long)
	 */
	public String obtenerCaracteristica(Producto_servicio_peticionLocal psp, Long numeroCararcteristicas) throws ATiempoAppEx {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}

	
	


}
