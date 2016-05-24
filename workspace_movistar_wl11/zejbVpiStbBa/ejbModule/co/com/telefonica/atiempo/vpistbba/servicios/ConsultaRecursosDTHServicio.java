package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR019S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

public class ConsultaRecursosDTHServicio extends ServicioBasico{

	protected void procesar(Object[] obj) throws ATiempoAppEx
	{
		RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
		recursosTVDelegate.recibeSolicitudInformacionTecnicaTV((TR019S) obj[0]);
	}

	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx
	{
		TR019S tr019s = (TR019S) XMLUtilities.unmarshall (mensaje);
		Object[] obj = new Object[1];
        
		obj[0] = tr019s ;
        
		return obj;
	}
}
