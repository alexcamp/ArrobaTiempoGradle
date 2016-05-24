package com.telefonica_chile.vpistbba.datos_publicacion.session;
import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;
/**
 * Local interface for Enterprise Bean: DatosPublicacion
 */
public interface DatosPublicacionLocal extends javax.ejb.EJBLocalObject {

	public DatosPeticion getDatosPublicacion(
		Long idPeticion,
		String codigoActividad);
}
