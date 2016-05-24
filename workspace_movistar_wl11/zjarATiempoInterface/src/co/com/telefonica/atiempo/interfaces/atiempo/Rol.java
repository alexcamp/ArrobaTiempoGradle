//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: Rol.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

/**
* @author jsilva
* @version $Revision: 1.0 $
*/
public class Rol implements Serializable {
	
	private long usuaId;
	private long rolId;
	private int habilitado;
	

	/**
	 * @return Devuelve habilitado.
	 */
	public int getHabilitado() {
		return habilitado;
	}
	/**
	 * @param habilitado El habilitado a establecer.
	 */
	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	/**
	 * @return Devuelve rolId.
	 */
	public long getRolId() {
		return rolId;
	}
	/**
	 * @param rolId El rolId a establecer.
	 */
	public void setRolId(long rolId) {
		this.rolId = rolId;
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
			return (int) rolId;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof Rol) {
			Rol other = (Rol) arg0;
			return 
					usuaId == other.usuaId &&
					rolId == other.rolId &&
					habilitado == other.habilitado ;
		}
		return false;
	}
}
