package com.telefonica_chile.comun.usuario_rol.session;
import java.util.Collection;
/**
 * Remote interface for Enterprise Bean: UsuarioRolSession
 */
public interface UsuarioRolSession extends javax.ejb.EJBObject {
	public Collection rolesUsuarios(Long usuaRol)
		throws java.rmi.RemoteException;
}
