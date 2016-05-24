package com.telefonica_chile.atiempo.javaWf;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.WFMessageServicio;

/**
 * Bean implementation class for Enterprise Bean: WFMessageBajas
 */
public class WFMessageBajasBean extends MDServicioBean {
	public IServicio getServicio() {
		return new WFMessageServicio();
	}
}