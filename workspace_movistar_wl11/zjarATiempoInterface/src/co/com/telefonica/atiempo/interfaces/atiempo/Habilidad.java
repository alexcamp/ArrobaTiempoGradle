//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: Habilidad.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
* @author jsilva
* @version $Revision: 1.0 $
*/
public class Habilidad implements Serializable {
	
	private long usuaId;
	private long husuId;
	private long habiId;
	private String husuValor;
	
		

	/**
	 * @return Devuelve habiId.
	 */
	public long getHabiId() {
		return habiId;
	}
	/**
	 * @param habiId El habiId a establecer.
	 */
	public void setHabiId(long habiId) {
		this.habiId = habiId;
	}
	/**
	 * @return Devuelve husuId.
	 */
	public long getHusuId() {
		return husuId;
	}
	/**
	 * @param husuId El husuId a establecer.
	 */
	public void setHusuId(long husuId) {
		this.husuId = husuId;
	}
	/**
	 * @return Devuelve husuValor.
	 */
	public String getHusuValor() {
		return husuValor;
	}
	/**
	 * @param husuValor El husuValor a establecer.
	 */
	public void setHusuValor(String husuValor) {
		this.husuValor = husuValor;
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
			return (int) husuId;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof Habilidad) {
			Habilidad other = (Habilidad) arg0;
			return 
					usuaId == other.usuaId &&
					husuId == other.husuId &&
					habiId == other.habiId &&
				    EqualityUtilities.equals(husuValor, other.husuValor);
		}
		return false;
	}
}
