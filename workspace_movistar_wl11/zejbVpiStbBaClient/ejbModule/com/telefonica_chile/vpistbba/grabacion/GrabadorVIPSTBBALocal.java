package com.telefonica_chile.vpistbba.grabacion;
import javax.servlet.http.HttpServletRequest;
/**
 * Local interface for Enterprise Bean: GrabadorVIPSTBBA
 */
public interface GrabadorVIPSTBBALocal extends javax.ejb.EJBLocalObject {
	public void grabar(HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
}
