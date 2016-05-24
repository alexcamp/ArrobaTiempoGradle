package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.ConsultaEquipoServicio;

/**
 * Bean implementation class for Enterprise Bean: ConsultaEquipo
 */
public class ConsultaEquipoBean extends co.com.telefonica.atiempo.utiles.MDServicioBean {
    public IServicio getServicio()
	{
		return new ConsultaEquipoServicio();
	}
}
