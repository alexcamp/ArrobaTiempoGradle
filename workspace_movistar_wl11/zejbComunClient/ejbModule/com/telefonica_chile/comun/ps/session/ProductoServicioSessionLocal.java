package com.telefonica_chile.comun.ps.session;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
/**
 * Local interface for Enterprise Bean: ProductoServicioSession
 */
public interface ProductoServicioSessionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * recuperarPS
	 */
	public com.telefonica_chile.comun.ps.dto.ProductoDTO recuperarPS(java.lang.Long idPS);
	/**
	 * recuperarAllPs
	 */
	//public java.util.ArrayList recuperarAllPs();
	/** Método que me retorna datos de Producto y Servicios **/
	public ProductoDTO recuperarPS(Long idPS, boolean pcoObligatorio);
}
