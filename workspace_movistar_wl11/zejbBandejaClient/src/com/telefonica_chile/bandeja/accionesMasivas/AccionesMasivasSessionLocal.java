package com.telefonica_chile.bandeja.accionesMasivas;
import com.telefonica_chile.bandeja.BandejaException;
/**
 * Local interface for Enterprise Bean: AccionesMasivasSession
 */
public interface AccionesMasivasSessionLocal extends javax.ejb.EJBLocalObject {
	public boolean updateUsuarioBIntegrada(
		Long nuevoUsuario,
		Long idPeticion,
		Long idActividad,
		Long antiguoUsuario)
		throws BandejaException;
	public String getNombreAplicacionPeticion(Long idPeticion);
}
