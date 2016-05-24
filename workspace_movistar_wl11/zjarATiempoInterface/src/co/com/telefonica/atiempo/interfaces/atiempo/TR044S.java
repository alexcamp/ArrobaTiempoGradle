/*
 * Created on Jul 6, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR044S extends ResponseHeader {
	private String pdf;
	private long numeroPedido;
	private int codigoPS;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR044S) {
				TR044S other = (TR044S) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(pdf, other.pdf)
					&& numeroPedido == other.numeroPedido
					&& codigoPS == other.codigoPS;
			}
			return false;
		}

	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	
	public int getCodigoPS() {
		return codigoPS;
	}
	public void setCodigoPS(int codigoPS) {
		this.codigoPS = codigoPS;
	}
	public long getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
}
