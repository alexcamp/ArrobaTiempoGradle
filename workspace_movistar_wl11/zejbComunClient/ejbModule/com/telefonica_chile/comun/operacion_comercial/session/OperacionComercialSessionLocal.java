package com.telefonica_chile.comun.operacion_comercial.session;
import com.telefonica_chile.comun.opearacion_comercial.dto.OperacionComercialDTO;
/**
 * Local interface for Enterprise Bean: OperacionComercialSession
 */
public interface OperacionComercialSessionLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * recuperarOC
	 */
	public com.telefonica_chile.comun.ps.dto.ProductoDTO recuperarOC(
		java.lang.Long idOC);
	/**
	 * getCodigoTipoTrabajo
	 */
	public java.lang.Integer getCodigoTipoTrabajo(
		java.lang.Long idOperacionComercial);
	/**
	 * recuperarAllOc
	 */
	//public java.util.ArrayList recuperarAllOc();
	public OperacionComercialDTO recuperarOP(Long idOC);
}
