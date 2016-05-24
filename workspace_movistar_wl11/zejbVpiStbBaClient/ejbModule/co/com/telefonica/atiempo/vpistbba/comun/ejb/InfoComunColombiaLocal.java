package co.com.telefonica.atiempo.vpistbba.comun.ejb;

import java.util.ArrayList;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
/**
 * Local interface for Enterprise Bean: InfoComunColombia
 */
public interface InfoComunColombiaLocal extends javax.ejb.EJBLocalObject, InfoComunColombiaBusinessInterface
{
	public ArrayList getProductoServicio(Long idPeticion) throws ATiempoAppEx;
	
	// CR26747 - Funcion para obtener el codigo de APSC de la tabla de mapeo
	public Long obtenerCodigoAPSC(Long codLoc, String subLocalidad);
}
