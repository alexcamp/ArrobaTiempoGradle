package com.telefonica_chile.bandeja.datos.usuarios;
import java.util.ArrayList;
import java.util.List;
/**
 * Local interface for Enterprise Bean: UsuariosSession
 */
public interface UsuariosSessionLocal extends javax.ejb.EJBLocalObject {
	public ArrayList recuperarRolesUsuario(Long usuaId) throws UsuariosException;
	public ArrayList recuperarMenusUsuario(Long usuaId) throws UsuariosException;
	
	//public ArrayList recuperarSegmentos() throws UsuariosException;
	
	public boolean actualizarEstadoUsuario(Long idUsuario, String habilitado);
	public boolean actualizarEstadoRolUsuario(Long idUsuario,Long rol,String habilitado);
	public ArrayList recuperarValoresVariables(Long usuaId)	throws UsuariosException;
	public ArrayList recuperarValoresVariables(Long usuaId, ArrayList listIdBi) throws UsuariosException;
	/**
	 * Recupera la lista de supervisores de un rol dado.
	 * 
	 * @param rolId codigo del rol
	 * @return una lista de UsuarioDTO, o una lista vacia si no se encontraron supervisores para el rol.
	 * @throws UsuariosException en caso de algun error.
	 * @author amartoq
	 */
	public List recuperarSupervisoresRol(Long rolId) throws UsuariosException;
	public ArrayList recuperaRolesUsuariosQueSuperviso(Long usuarioSupId) throws UsuariosException;
	public UsuarioDTO retornaUsuario(String login, String newToken) throws UsuariosException;
	public void deleteToKen(String subTok) throws UsuariosException;
	public void updateToKen(String string, String newToken)
		throws UsuariosException;
}
