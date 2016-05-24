package co.com.telefonica.atiempo.vpistbba.grabacion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

import com.telefonica_chile.vpistbba.grabacion.GrabadorComun;

public class GrabadorDesinstalarDTH extends GrabadorComun
{

	protected void grabarDatosPropios(HttpServletRequest request)
	{
		try
		{
			PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
			ArrayList listaPares=new ArrayList();	
			Long petiNumero=new Long(request.getParameter("folio"));
			int cantidadParesPostBaja=new Integer(request.getParameter("cantidadParesPostBaja")).intValue();
			int cantidadParesTiene=new Integer(request.getParameter("cantidadParesTiene")).intValue();
			long opCoPostPar=new Long(request.getParameter("opCoPostPar")).longValue();
			if(cantidadParesPostBaja>0)
			{
				for(int i=0;i<cantidadParesTiene;i++)
				{
					String par=request.getParameter("par_"+i);
					if(par!=null)
					{
						String deco=par.substring(0,par.indexOf("_"));
						String tarjeta=par.substring(par.indexOf("_")+1); 
						DecoTarDTO decoTarDTO=new DecoTarDTO(deco,tarjeta);
						log.debug("Voy a grabar par:"+decoTarDTO.toString());
						listaPares.add(decoTarDTO);
					}
				}
				RecursosTVDelegate delegate=new RecursosTVDelegate();
				delegate.grabaDecosPostVenta(petiNumero,listaPares,opCoPostPar);
			}
			else
				log.debug("No tengo pares de Baja/Post Venta que desconfigurar");
			log.debug("Grabacion Desinstalar DTH Realizada");
			Long id_usuario=new Long(request.getParameter("usuario_id_quiebre"));
			log.info("Voy a marcar la peticion "+petiNumero+" para el usuario:"+id_usuario);
			peticionesDelegate.marcaPeticionUsuario(petiNumero,id_usuario);
		}
		catch(Exception e)
		{
			log.debug("Excepcion",e);
		}
	}
}
