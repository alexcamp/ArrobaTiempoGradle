package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultaRecursosDTHServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudConfiguracionDTH
 */
public class SolicitudConfiguracionDTHBean extends MDServicioBean
{

	public IServicio getServicio()
	{
		return new ConsultaRecursosDTHServicio();
	}
	
}
