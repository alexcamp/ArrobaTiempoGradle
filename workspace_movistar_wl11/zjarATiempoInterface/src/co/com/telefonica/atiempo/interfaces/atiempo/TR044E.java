/*
 * Created on Jul 6, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR044E extends RequestHeader{
	private String usuario;
	private TR044EClient cliente;
	private String numeroAtis;
	private long numeroPedido;
	private Long operacionComercial;
	private Collection productos;
	private String segmento;
	private String tipoOperacion;
	
	
	public int hashCode() {
		return (int)numeroPedido;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR044E) {
			TR044E other = (TR044E) arg0;
			return super.equals(arg0)
				&& numeroPedido == other.numeroPedido
				&& EqualityUtilities.equals(cliente,other.cliente)
				&& EqualityUtilities.equals(usuario,other.usuario)
				&& EqualityUtilities.equals(productos,other.productos)
				&& EqualityUtilities.equals(segmento,other.segmento)
				&& EqualityUtilities.equals(numeroAtis,other.numeroAtis)
				&& EqualityUtilities.equals(tipoOperacion,other.tipoOperacion);
			}
		return false;
	}


	
	public TR044EClient getCliente() {
		return cliente;
	}
	public void setCliente(TR044EClient cliente) {
		this.cliente = cliente;
	}
	public Collection getProductos() {
		return productos;
	}
	public void setProductos(Collection productos) {
		this.productos = productos;
	}
	public long getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNumeroAtis() {
		return numeroAtis;
	}
	public void setNumeroAtis(String numeroAtis) {
		this.numeroAtis = numeroAtis;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	/**
	 * @return Returns the operacionComercial.
	 */
	public Long getOperacionComercial() {
		return operacionComercial;
	}
	/**
	 * @param operacionComercial The operacionComercial to set.
	 */
	public void setOperacionComercial(Long operacionComercial) {
		this.operacionComercial = operacionComercial;
	}
}
