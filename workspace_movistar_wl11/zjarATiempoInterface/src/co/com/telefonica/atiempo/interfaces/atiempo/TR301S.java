//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR301S.java,v 1.1 2011/03/30 18:23:28 jsilva Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
* @author jsilva
* @version $Revision: 1.0 $
*/
public class TR301S extends RequestHeaderAgendaSC {

	private Usuario usuario;
	private Collection roles;
	private Collection perfiles;
	private Collection habilidades;
	private String operacion;
	private JerUsuarioRol jur;
	
	
	
	/**
	 * Objeto que contieme la informacion de supervicion del usuario.
	 * @return Retorna el JerUsuarioRol.
	 */
	public JerUsuarioRol getJur() {
		return jur;
	}
	/**
	 * Objeto que contieme la informacion de supervicion del usuario.
	 * @param JerUsuarioRol.
	 */
	public void setJur(JerUsuarioRol jur) {
		this.jur = jur;
	}
	/**
	 * @return Devuelve habilidades.
	 */
	public Collection getHabilidades() {
		return habilidades;
	}
	/**
	 * @param habilidades El habilidades a establecer.
	 */
	public void setHabilidades(Collection habilidades) {
		this.habilidades = habilidades;
	}
	/**
	 * @return Devuelve operacion.
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion El operacion a establecer.
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	/**
	 * @return Devuelve perfiles.
	 */
	public Collection getPerfiles() {
		return perfiles;
	}
	/**
	 * @param perfiles El perfiles a establecer.
	 */
	public void setPerfiles(Collection perfiles) {
		this.perfiles = perfiles;
	}
	/**
	 * @return Devuelve roles.
	 */
	public Collection getRoles() {
		return roles;
	}
	/**
	 * @param roles El roles a establecer.
	 */
	public void setRoles(Collection roles) {
		this.roles = roles;
	}
	/**
	 * @return Devuelve usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario El usuario a establecer.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int hashCode() {
		if(usuario!=null){
			return usuario.hashCode();
		}else{
			return jur.hashCode();
		}
	}	
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR301S) {
			TR301S other = (TR301S) arg0;
			return super.equals(arg0) &&
				EqualityUtilities.equals(operacion, other.operacion) &&
				EqualityUtilities.equals(usuario, other.usuario) &&
				EqualityUtilities.equals(perfiles, other.perfiles) &&
				EqualityUtilities.equals(roles, other.roles) &&
				EqualityUtilities.equals(habilidades, other.habilidades) ;				
		}
		
		return false;
	}

}
