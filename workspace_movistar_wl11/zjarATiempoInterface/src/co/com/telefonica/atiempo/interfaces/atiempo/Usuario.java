//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: Usuario.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
* @author jsilva
* @version $Revision: 1.0 $
*/
public class Usuario implements Serializable {
	
	private long usuaId;
	private String usuaLogin;
	private String usuaNombre;
	private String usuaDireccion;
	private String usuaEmail;
	private String usuaTelefono;
	private String usuaCargo;
	private String usuaHabilidad;
	private long emprId;
	private String usuaPassword;
	private String usuaIdca;
	private String usuaFechaAlta;
	
	/**
	 * @return Devuelve emprId.
	 */
	public long getEmprId() {
		return emprId;
	}
	/**
	 * @param emprId El emprId a establecer.
	 */
	public void setEmprId(long emprId) {
		this.emprId = emprId;
	}
	/**
	 * @return Devuelve usuaCargo.
	 */
	public String getUsuaCargo() {
		return usuaCargo;
	}
	/**
	 * @param usuaCargo El usuaCargo a establecer.
	 */
	public void setUsuaCargo(String usuaCargo) {
		this.usuaCargo = usuaCargo;
	}
	/**
	 * @return Devuelve usuaDireccion.
	 */
	public String getUsuaDireccion() {
		return usuaDireccion;
	}
	/**
	 * @param usuaDireccion El usuaDireccion a establecer.
	 */
	public void setUsuaDireccion(String usuaDireccion) {
		this.usuaDireccion = usuaDireccion;
	}
	/**
	 * @return Devuelve usuaEmail.
	 */
	public String getUsuaEmail() {
		return usuaEmail;
	}
	/**
	 * @param usuaEmail El usuaEmail a establecer.
	 */
	public void setUsuaEmail(String usuaEmail) {
		this.usuaEmail = usuaEmail;
	}
	/**
	 * @return Devuelve usuaFechaAlta.
	 */
	public String getUsuaFechaAlta() {
		return usuaFechaAlta;
	}
	/**
	 * @param usuaFechaAlta El usuaFechaAlta a establecer.
	 */
	public void setUsuaFechaAlta(String usuaFechaAlta) {
		this.usuaFechaAlta = usuaFechaAlta;
	}
	/**
	 * @return Devuelve usuaHabilidad.
	 */
	public String getUsuaHabilidad() {
		return usuaHabilidad;
	}
	/**
	 * @param usuaHabilidad El usuaHabilidad a establecer.
	 */
	public void setUsuaHabilidad(String usuaHabilidad) {
		this.usuaHabilidad = usuaHabilidad;
	}
	/**
	 * @return Devuelve usuaId.
	 */
	public long getUsuaId() {
		return usuaId;
	}
	/**
	 * @param usuaId El usuaId a establecer.
	 */
	public void setUsuaId(long usuaId) {
		this.usuaId = usuaId;
	}
	/**
	 * @return Devuelve usuaIdca.
	 */
	public String getUsuaIdca() {
		return usuaIdca;
	}
	/**
	 * @param usuaIdca El usuaIdca a establecer.
	 */
	public void setUsuaIdca(String usuaIdca) {
		this.usuaIdca = usuaIdca;
	}
	/**
	 * @return Devuelve usuaLogin.
	 */
	public String getUsuaLogin() {
		return usuaLogin;
	}
	/**
	 * @param usuaLogin El usuaLogin a establecer.
	 */
	public void setUsuaLogin(String usuaLogin) {
		this.usuaLogin = usuaLogin;
	}
	/**
	 * @return Devuelve usuaNombre.
	 */
	public String getUsuaNombre() {
		return usuaNombre;
	}
	/**
	 * @param usuaNombre El usuaNombre a establecer.
	 */
	public void setUsuaNombre(String usuaNombre) {
		this.usuaNombre = usuaNombre;
	}
	/**
	 * @return Devuelve usuaPassword.
	 */
	public String getUsuaPassword() {
		return usuaPassword;
	}
	/**
	 * @param usuaPassword El usuaPassword a establecer.
	 */
	public void setUsuaPassword(String usuaPassword) {
		this.usuaPassword = usuaPassword;
	}
	/**
	 * @return Devuelve usuaTelefono.
	 */
	public String getUsuaTelefono() {
		return usuaTelefono;
	}
	/**
	 * @param usuaTelefono El usuaTelefono a establecer.
	 */
	public void setUsuaTelefono(String usuaTelefono) {
		this.usuaTelefono = usuaTelefono;
	}
	

	public int hashCode() {
			return (int) usuaId;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof Usuario) {
			Usuario other = (Usuario) arg0;
			return 
					usuaId == other.usuaId &&
				    EqualityUtilities.equals(usuaLogin, other.usuaLogin) &&
					EqualityUtilities.equals(usuaNombre, other.usuaNombre) &&
					EqualityUtilities.equals(usuaDireccion, other.usuaDireccion) &&
					EqualityUtilities.equals(usuaEmail, other.usuaEmail) &&
					EqualityUtilities.equals(usuaTelefono, other.usuaTelefono) &&
					EqualityUtilities.equals(usuaCargo, other.usuaCargo) &&
					usuaHabilidad == other.usuaHabilidad &&
					emprId == other.emprId &&
					EqualityUtilities.equals(usuaPassword, other.usuaPassword) &&
					EqualityUtilities.equals(usuaIdca, other.usuaIdca) &&
					EqualityUtilities.equals(usuaFechaAlta, other.usuaFechaAlta) ;
		}
		return false;
	}
}
