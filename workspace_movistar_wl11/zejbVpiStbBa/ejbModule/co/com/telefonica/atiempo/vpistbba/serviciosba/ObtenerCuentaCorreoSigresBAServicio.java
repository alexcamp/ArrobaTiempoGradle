//CR4860 SIGRES - GUSTAVOG - 28/04/2008


package co.com.telefonica.atiempo.vpistbba.serviciosba;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;


/**
 * @author TCS
 */
public class ObtenerCuentaCorreoSigresBAServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		RecursosBAInterfaces delegate = new RecursosBADelegate();		
		TR043S tr043S = (TR043S) obj[0];		
		delegate.recepcionObtenerCuentaCorreoSigres(tr043S);
		
		try{
			Recursos_linea_basicaLocalHome recursosLineaBasicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
			Recursos_linea_basicaLocal recursosLineaBasica =  recursosLineaBasicaLocalHome.findByPeticion(new Long(tr043S.getAtiempoRequestNumber()));
			
			recursosLineaBasica.setReinstalacion_internet("");
		}catch (NamingException ex){
			
		}catch (FinderException ex){
			
		}
		
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO-TCS: MARSHALL TR_013_S (CREAR DTO)

		Object[] obj = new Object[1];
		TR043S tr043S = (TR043S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr043S;
		return obj;
	}

}
