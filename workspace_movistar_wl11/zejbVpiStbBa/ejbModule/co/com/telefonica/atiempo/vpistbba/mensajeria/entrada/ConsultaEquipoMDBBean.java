package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultaEquipoServicio;

/**
 * Bean implementation class for Enterprise Bean: ConsultaEquipoMDB
 */
public class ConsultaEquipoMDBBean extends MDServicioBean {

	public IServicio getServicio()
	{		
		return new ConsultaEquipoServicio();
	}
	}
