package com.telefonica_chile.bandeja.usuariossupervisadossession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaException;
import com.telefonica_chile.bandeja.datos.usuarios.RolDTO;
/**
 * Bean implementation class for Enterprise Bean: UsuariosSupervisadosSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 18, 2009
public class UsuariosSupervisadosSessionBean implements javax.ejb.SessionBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8364128953289406440L;

	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	/*
	public ArrayList getUsuariosAll() throws BandejaException{
		
		ArrayList usuarios = new ArrayList();	
		try {
			UsuarioLocalHome home=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Collection col= home.findAll();
			
			for (Iterator iter = col.iterator(); iter.hasNext();) {
				UsuarioLocal usua = (UsuarioLocal)iter.next();
				RolDTO dto = new RolDTO();
				UsuarioKey key=(UsuarioKey) usua.getPrimaryKey();
				dto.setId(key.usua_id);
				String name = usua.getUsua_nombre();
				dto.setNombre(usua.getUsua_nombre());
				usuarios.add(dto);
				// Generamos el listado Ordenado.
				ArrayList aux = new ArrayList();
				RolDTO dtoAux = new RolDTO();
				boolean agregado = false;
				for (int j=0; j<usuarios.size(); j++) {
					dtoAux = (RolDTO) usuarios.get(j);
					if ( dtoAux == null)
						continue;
					if ( !agregado && dtoAux.getNombre().compareTo( name ) > 0 ) {
						aux.add(dto);
						agregado = true;
					}
					aux.add(dtoAux);
				}
				if ( !agregado )
					aux.add(dto);
					
				usuarios = aux;
			}	
		
		} catch (Exception e) {
			log.warn("Problemas recuperando home UsuarioLocalHome: " + UsuarioLocalHome.JNDI_NAME, e);
			
		}
		return usuarios;
	}	
	*/	
		
	public ArrayList getUsuarios(Long idRol ,Long usuario) throws BandejaException{
	
		ArrayList usuarios = new ArrayList();
		try
		{
			Jer_usuario_rolLocalHome home=(Jer_usuario_rolLocalHome) HomeFactory.getHome(Jer_usuario_rolLocalHome.JNDI_NAME);
			Collection col= home.findUsuariosSupervisores(idRol ,usuario);
			
			for (Iterator iter = col.iterator(); iter.hasNext();)
			{
				Jer_usuario_rolLocal local = (Jer_usuario_rolLocal)iter.next();
				RolDTO dto = new RolDTO();
				String name = (String)local.getFk_jerusuarol_usu2().getUsua_nombre();
				UsuarioKey usuarioKey=(UsuarioKey) local.getFk_jerusuarol_usu2().getPrimaryKey();
				dto.setId(usuarioKey.usua_id);
				dto.setNombre( name );

				// Generamos el listado Ordenado.
				ArrayList aux = new ArrayList();
				RolDTO dtoAux = new RolDTO();
				boolean agregado = false;
				for (int j=0; j<usuarios.size(); j++) {
					dtoAux = (RolDTO) usuarios.get(j);
					if ( dtoAux == null)
						continue;
					if ( !agregado && dtoAux.getNombre().compareTo( name ) > 0 ) {
						aux.add(dto);
						agregado = true;
					}
					aux.add(dtoAux);
				}
				if ( !agregado )
					aux.add(dto);
					
				usuarios = aux;
			}	
		
		} catch (Exception e) {
			log.warn("Problemas recuperando home JerarquiaEntityLocalHome: " + Jer_usuario_rolLocalHome.JNDI_NAME, e);
			throw new BandejaException("Problemas recuperando Colecci�n de Peticiones", e);
		}
		return usuarios;
	}
	
	/**
	 * Este metodo permite mover un usuario desde una plataforma de ATST a otra.
	 * Antes de mover, revisa que el usuario no este conectado. Para ello, revisa que el estado 
	 * del usuario no sea DISPONIBLE. 
	 * @param usuaId Id de usuario
	 * @param plataformaId Id de plataforma
	 * @throws BandejaException Si no se puede mover al usuario
	 */
	public void moverUsuarioDePlataforma(int usuaId, int plataformaId) throws BandejaException {
		// TODO: Verifica que el usuario no este con estado DISPONIBLE
		
		// TODO: Realiza movimiento de usuario desde una plataforma a otra.
		// Los pasos a realizar son:
		// 1. Obtener el id del supervisor de la nueva plataforma
		// 2. Cambiar la jerarquia del usuario en la tabla JER_USUARIO_ROL
		// 3. Cambiar la relacion de la tabla PLATAFORMA_SOLUCION_USUARIO
	}	
	
}
