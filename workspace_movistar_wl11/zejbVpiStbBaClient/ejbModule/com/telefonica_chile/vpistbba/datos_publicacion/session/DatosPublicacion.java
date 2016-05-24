package com.telefonica_chile.vpistbba.datos_publicacion.session;
import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;
/**
 * Remote interface for Enterprise Bean: DatosPublicacion
 */
public interface DatosPublicacion extends javax.ejb.EJBObject {
	public DatosPeticion getDatosPublicacion(Long idPeticion, String codigoActividad) throws java.rmi.RemoteException;
}
