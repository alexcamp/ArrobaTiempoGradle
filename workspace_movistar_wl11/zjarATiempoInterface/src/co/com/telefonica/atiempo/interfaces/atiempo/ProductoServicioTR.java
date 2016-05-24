/*
 * Created on Jan 26, 2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductoServicioTR {
	
	private String ps;
	private String operacionComercial;

	public int hashCode() {
		return ps.hashCode()+operacionComercial.hashCode();
	}
	
	/**
	 * @return Returns the operacionComercial.
	 */
	public String getOperacionComercial() {
		return operacionComercial;
	}
	/**
	 * @param operacionComercial The operacionComercial to set.
	 */
	public void setOperacionComercial(String operacionComercial) {
		this.operacionComercial = operacionComercial;
	}
	/**
	 * @return Returns the ps.
	 */
	public String getPs() {
		return ps;
	}
	/**
	 * @param ps The ps to set.
	 */
	public void setPs(String ps) {
		this.ps = ps;
	}
}
