/*
 * Created on Feb 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.grabacion;


import javax.servlet.http.HttpServletRequest;

import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

import com.telefonica_chile.vpistbba.grabacion.GrabadorComun;

/**
 * @author jvelasco
 *
 */
public class GrabadorInstalacion extends GrabadorComun
{
	protected void grabarDatosPropios(HttpServletRequest request)
	{
		try
		{
			PeticionesDelegate delegate=new PeticionesDelegate();
			Long id_usuario=new Long(request.getParameter("usuario_id_quiebre"));
			Long id_peticion=new Long(request.getParameter("folio"));
			log.info("Voy a marcar la peticion "+id_peticion+" para el usuario:"+id_usuario);
			delegate.marcaPeticionUsuario(id_peticion,id_usuario);
			if(request.getParameter("limpiarInvDTH")!=null)
			{
				log.info("Limpiando peticion "+id_peticion);
				RecursosTVDelegate delegate2=new RecursosTVDelegate();
				delegate2.limpiarInventarioDTH(id_peticion);
			}
		}
		catch(Exception e)
		{
			log.error("Exception",e);
		}
	}
}
