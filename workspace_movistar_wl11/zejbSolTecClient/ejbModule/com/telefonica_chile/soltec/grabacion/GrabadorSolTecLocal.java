package com.telefonica_chile.soltec.grabacion;
import javax.servlet.http.HttpServletRequest;

import co.com.telefonica.atiempo.soltec.dto.PruebaLineaDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
/**
 * Local interface for Enterprise Bean: GrabadorSolTec
 */
public interface GrabadorSolTecLocal extends javax.ejb.EJBLocalObject {
	public void grabar(HttpServletRequest request)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException;
	public void setGrabaPruebaLinea(PruebaLineaDTO pruebaLineaDTO)
		throws ATiempoAppEx;
}
