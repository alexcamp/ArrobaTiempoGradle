package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.RefrescarRecursosSTBServicio;

/**
 * Bean implementation class for Enterprise Bean: RefrescarRecursosSTBBAMDB
 */
public class RefrescarRecursosSTBBAMDBBean 
extends MDServicioBean
{
	public IServicio getServicio() {
		return new RefrescarRecursosSTBServicio();
		}
}
