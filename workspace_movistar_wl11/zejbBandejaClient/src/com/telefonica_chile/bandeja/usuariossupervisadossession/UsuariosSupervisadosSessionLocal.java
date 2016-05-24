package com.telefonica_chile.bandeja.usuariossupervisadossession;
import java.util.ArrayList;

import com.telefonica_chile.bandeja.datos.bandeja.BandejaException;
/**
 * Local interface for Enterprise Bean: UsuariosSupervisadosSession
 */
public interface UsuariosSupervisadosSessionLocal
	extends javax.ejb.EJBLocalObject {
	
	public ArrayList getUsuarios(Long idRol, Long usuario)
		throws BandejaException;
		
	//public ArrayList getUsuariosAll() throws BandejaException;
				
	/**
	 * Este metodo permite mover un usuario desde una plataforma de ATST a otra.
	 * Antes de mover, revisa que el usuario no este conectado. Para ello, revisa que el estado 
	 * del usuario no sea DISPONIBLE. 
	 * @param usuaId Id de usuario
	 * @param plataformaId Id de plataforma
	 * @throws BandejaException Si no se puede mover al usuario
	 */
	public void moverUsuarioDePlataforma(int usuaId, int plataformaId) throws BandejaException;
}
