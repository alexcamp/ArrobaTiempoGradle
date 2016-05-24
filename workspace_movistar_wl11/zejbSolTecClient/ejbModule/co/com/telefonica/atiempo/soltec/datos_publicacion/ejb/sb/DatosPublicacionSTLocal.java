package co.com.telefonica.atiempo.soltec.datos_publicacion.ejb.sb;

import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;

/**
 * Local interface for Enterprise Bean: DatosPublicacionST
 */
public interface DatosPublicacionSTLocal extends javax.ejb.EJBLocalObject {
	public DatosPeticion getDatosPublicacion(
		Long idPeticion,
		String codigoActividad);
}
