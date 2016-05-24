//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: Perfil.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

/**
* @author jsilva
* @version $Revision: 1.0 $
*/
public class Perfil implements Serializable {
	
	private long usuaId;
	private long perfilId;
	private long estado;
	

	/**
	 * @return Devuelve estado.
	 */
	public long getEstado() {
		return estado;
	}
	/**
	 * @param estado El estado a establecer.
	 */
	public void setEstado(long estado) {
		this.estado = estado;
	}
	/**
	 * @return Devuelve perfilId.
	 */
	public long getPerfilId() {
		return perfilId;
	}
	/**
	 * @param perfilId El perfilId a establecer.
	 */
	public void setPerfilId(long perfilId) {
		this.perfilId = perfilId;
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
	public int hashCode() {
			return (int) perfilId;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof Perfil) {
			Perfil other = (Perfil) arg0;
			return 
					usuaId == other.usuaId &&
					perfilId == other.perfilId &&
					estado == other.estado ;
		}
		return false;
	}
}
