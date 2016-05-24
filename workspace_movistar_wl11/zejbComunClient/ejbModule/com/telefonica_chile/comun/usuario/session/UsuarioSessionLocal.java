package com.telefonica_chile.comun.usuario.session;
import java.util.ArrayList;

import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.comun.asigna.dto.usuario;
import com.telefonica_chile.comun.usuario.dto.UsuarioDTO;
/**
 * Local interface for Enterprise Bean: UsuarioSession
 */
public interface UsuarioSessionLocal extends javax.ejb.EJBLocalObject {
	public ArrayList obtenerUsuariosPorRol(Long idRol);
	public usuario getUsuarioByIdca(String idca);
	public String getnombreUsuario(String idca);
	public ArrayList generarHabilidad(
		ArrayList usrSupervisados,
		Long Habilidad);
	
	public ArrayList generarLista(
		ArrayList usrSupervisados,
		Long Habilidad);
	
	public String getNombreById(String idUsuario);
	//public ArrayList getAllUsuario();
	public ArrayList getIdNombreUsuarios(Long idRol);
	public ArrayList getUsuarios(ArrayList listaUsers);
	public UsuarioDTO recuperaUsuario(Long idUsuario);
	public ArrayList recuperaTecnicos(Long idUsuario, UsuarioWeb usuario, String nropeticion);
}
