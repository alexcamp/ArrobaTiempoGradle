package co.com.telefonica.atiempo.soltec.mensajeria;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.RefrescarRecursosSTBServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: RefrescarRecursosSTBMDB
 */
public class RefrescarRecursosSTBMDBBean
extends MDServicioBean
		{
	public IServicio getServicio() {
		return new RefrescarRecursosSTBServicio();
	}
}