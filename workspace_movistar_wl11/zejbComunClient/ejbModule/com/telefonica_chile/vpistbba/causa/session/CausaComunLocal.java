package com.telefonica_chile.vpistbba.causa.session;
/**
 * Local interface for Enterprise Bean: CausaComun
 */
public interface CausaComunLocal extends javax.ejb.EJBLocalObject {
	/**
	 * recuperarCausa
	 */
	public com.telefonica_chile.comun.causa.dto.CausaDTO recuperarCausa(
		java.lang.Long idCausa);
}
