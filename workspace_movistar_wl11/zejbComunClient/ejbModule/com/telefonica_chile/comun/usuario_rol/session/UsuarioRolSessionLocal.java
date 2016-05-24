package com.telefonica_chile.comun.usuario_rol.session;
import java.util.ArrayList;
/**
 * Local interface for Enterprise Bean: UsuarioRolSession
 */
public interface UsuarioRolSessionLocal extends javax.ejb.EJBLocalObject {
	public ArrayList rolesUsuarios(Long usuaRol);
	/*
	 * Retorna Todos Los usuarios Que PErtenecen al ROL dado.
	 * OJO: Es lo mimso que el de arriba, pero se llenana mas datos del DTO
	 * y en el RolID se setea el id del ROL.
	 */
	public ArrayList usuariosByRol(Long idRol, String tipoHab);
}
