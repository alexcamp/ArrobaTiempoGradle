package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultaModemServicio;

/**
 * Bean implementation class for Enterprise Bean: ConsultaModemMDB
 */
public class ConsultaModemMDBBean extends MDServicioBean
{

	public IServicio getServicio()
	{
		return new ConsultaModemServicio();
	}
	
}
