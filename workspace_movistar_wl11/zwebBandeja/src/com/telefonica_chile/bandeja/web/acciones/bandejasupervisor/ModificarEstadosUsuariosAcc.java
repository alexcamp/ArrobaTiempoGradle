/*
 * Created on Mar 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones.bandejasupervisor;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.datos.usuarios.RolDTO;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosException;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;
import com.telefonica_chile.bandeja.web.utiles.UtilesWeb;

/**
 * @author rbpizar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ModificarEstadosUsuariosAcc extends Accion {
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	protected void ejecutar(HttpServletRequest request) throws Evento {
		
		
		try{
			RolDTO rolDto = new RolDTO();
			
			String rol = request.getParameter("idRol");
			
			int cantidadUsuarios = Integer.parseInt(request.getParameter("cantidad"));
			Long idRolLong = ("".equals(rol)? null : new Long(rol));
			log.debug(">>>>ROL="+idRolLong);
			
			for (int i = 0; i < cantidadUsuarios; i++) {								
				String radioActivo = UtilesWeb.Revisa_Valor_CheckBox(request.getParameter("activo_"+i));				
				String idUsuario = request.getParameter("usuario_"+i);				
				String decision = retornaDecision(radioActivo);
				boolean updateEstadoUsuario = actualizarEstadoUsuario(new Long(idUsuario),decision);
				
				/*Se modificará el estado del usuario sólo para el Rol especificado!!
				 * */
				boolean updateEstadoRolUsuario = actualizarEstadoRolUsuario(new Long(idUsuario),idRolLong,decision);
				//ArrayList roles = getRolesUsuario(new Long(idUsuario));
				//log.debug("Cantidad de roles para usuario ["+idUsuario+"] ="+roles.size());
				
				/*for (int j=0; j<roles.size();j++)
				{
					rolDto = (RolDTO)roles.get(j);
					Long idRol = rolDto.getId();
					log.debug("Rol["+idRol+"], USUARIO["+idUsuario+"]");
					boolean updateEstadoRolUsuario = actualizarEstadoRolUsuario(new Long(idUsuario),idRol,decision);
				}*/			
			}
			setResultado("exitoUpdateEstadoUsuario");
			
		}catch (Exception e) {
			request.setAttribute("msj",new String("Error al Intentar Actualizar Estados de Usuario"));
			setResultado("errorUpdateEstadoUsuario");	
		}
	}

	/**
	 * @param idUsuario
	 * @param decision
	 * @return
	 */
	private boolean actualizarEstadoUsuario(Long idUsuario, String habilitado) throws NamingException, CreateException {		
		UsuariosSessionLocal usuariosSessionLocal = getUsuariosSession();
		return (usuariosSessionLocal.actualizarEstadoUsuario(idUsuario,habilitado));
		
	}
	private boolean actualizarEstadoRolUsuario(Long idUsuario, Long rol,String habilitado) throws NamingException, CreateException {		
		UsuariosSessionLocal usuariosSessionLocal = getUsuariosSession();
		return (usuariosSessionLocal.actualizarEstadoRolUsuario(idUsuario,rol,habilitado));
		
	}	
	
	
	private UsuariosSessionLocal getUsuariosSession() throws NamingException, CreateException {
			UsuariosSessionLocalHome home =
						(UsuariosSessionLocalHome) HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
			UsuariosSessionLocal local = home.create();
			return local; 
	}

	private ArrayList getRolesUsuario(Long idUsuario) throws NamingException, CreateException {
			ArrayList roles = new ArrayList();
			try {
				UsuariosSessionLocalHome home =
							(UsuariosSessionLocalHome) HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
				UsuariosSessionLocal local = home.create();
				 roles = local.recuperarRolesUsuario(idUsuario);
				 log.debug("CANTIDAD ROLES USUARIO=>"+roles.size());
			} catch (UsuariosException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			}
			return roles; 
	}
		

	/**
	 * @param radioActivo
	 * @param radioDesactivo
	 * @return
	 */
	private String retornaDecision(String radioActivo) {
		if(radioActivo.equals(new String("1"))){
			return ("S");
		}
		else{
			return ("N");
		}
	}

	

}
