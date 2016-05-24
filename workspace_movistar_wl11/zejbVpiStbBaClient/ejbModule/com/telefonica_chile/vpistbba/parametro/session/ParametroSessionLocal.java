package com.telefonica_chile.vpistbba.parametro.session;
import com.telefonica_chile.comun.parametro.session.ParametroDTO;
/**
 * Local interface for Enterprise Bean: ParametroSession
 */
public interface ParametroSessionLocal extends javax.ejb.EJBLocalObject {
	public ParametroDTO buscarPorNombre(String nombre);
	public Integer obtenerFlujoVacio();
}
