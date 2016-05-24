package com.telefonica_chile.bandeja.datos.bandeja;
import java.util.ArrayList;
import java.util.Collection;
/**
 * Local interface for Enterprise Bean: BandejaSession
 */
public interface BandejaSessionLocal extends javax.ejb.EJBLocalObject {
	public ActividadDTO recuperaActividadPorCodigoActividadCodigoAplicacion(
		String codigoActividad,
		String codigoAplicacion);
	/**Retornamos todas las Agencias*/
	public ArrayList recuperarAcciones(Long idRol);
	
	public ArrayList recuperarAcciones(Collection roles);
}
