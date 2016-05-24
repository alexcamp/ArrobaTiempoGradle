package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.ConsultaModemServicio;

/**
 * Bean implementation class for Enterprise Bean: ConsultaModem
 */
public class ConsultaModemBean extends co.com.telefonica.atiempo.utiles.MDServicioBean
{

	public IServicio getServicio()
	{
		return new ConsultaModemServicio();
	}
}
