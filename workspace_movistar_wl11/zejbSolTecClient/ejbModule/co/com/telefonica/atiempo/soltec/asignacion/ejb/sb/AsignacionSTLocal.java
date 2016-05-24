package co.com.telefonica.atiempo.soltec.asignacion.ejb.sb;
import java.util.HashMap;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
/**
 * Local interface for Enterprise Bean: AsignacionST
 */
public interface AsignacionSTLocal extends javax.ejb.EJBLocalObject {
	
	public Long getIdUsuario(String idPeticion,String codigoActividad);
		
	public HashMap getHabilidades(Long idPeticion, String codigoActividad)
		throws ATiempoAppEx;
}
