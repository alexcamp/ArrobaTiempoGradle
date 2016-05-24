package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.WFMessageSTServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * @author VictorMan
 * 
 * Bean implementation class for Enterprise Bean: WFMessageST
 */
public class WFMessageSTBean
	extends MDServicioBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	//public IServicio getServicio() {
	public IServicio getServicio() {		
		// TODO Auto-generated method stub
		return new WFMessageSTServicio();
	}


}
