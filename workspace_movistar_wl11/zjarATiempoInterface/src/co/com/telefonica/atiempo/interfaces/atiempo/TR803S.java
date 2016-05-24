/*
 * Created on Jun 16, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR803S extends RequestHeaderAgendaSC  implements ITRxxxBase{
	private String idSistemaOrigen;
	private String idSchedule;
	private String apptNumber;
	private String idPedidoAtis;
	
	public int hashCode() {
		return idSchedule.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR803S) {
			TR803S other = (TR803S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(idSistemaOrigen, other.idSistemaOrigen)
				&& EqualityUtilities.equals(idSchedule, other.idSchedule)
				&& EqualityUtilities.equals(apptNumber, other.apptNumber)
				&& EqualityUtilities.equals(idPedidoAtis, other.idPedidoAtis);
			}
		return false;
	}

	public String getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(String idSchedule) {
		this.idSchedule = idSchedule;
	}

	/**
	 * @return Devuelve apptNumber.
	 */
	public String getApptNumber() {
		return apptNumber;
	}
	/**
	 * @param apptNumber El apptNumber a establecer.
	 */
	public void setApptNumber(String apptNumber) {
		this.apptNumber = apptNumber;
	}
	/**
	 * @return Devuelve idPedidoAtis.
	 */
	public String getIdPedidoAtis() {
		return idPedidoAtis;
	}
	/**
	 * @param idPedidoAtis El idPedidoAtis a establecer.
	 */
	public void setIdPedidoAtis(String idPedidoAtis) {
		this.idPedidoAtis = idPedidoAtis;
	}
	/**
	 * @return Devuelve idSistemaOrigen.
	 */
	public String getIdSistemaOrigen() {
		return idSistemaOrigen;
	}
	/**
	 * @param idSistemaOrigen El idSistemaOrigen a establecer.
	 */
	public void setIdSistemaOrigen(String idSistemaOrigen) {
		this.idSistemaOrigen = idSistemaOrigen;
	}
}
