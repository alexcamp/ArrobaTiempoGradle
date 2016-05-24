package com.telefonica_chile.bandeja.web.seguridad;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tecnonautica.seguridad.ControladorDeRecurso;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.datos.usuarios.MenuDTO;
import com.telefonica_chile.bandeja.datos.usuarios.RolDTO;
import com.telefonica_chile.bandeja.datos.usuarios.UsuarioDTO;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosException;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;
import com.telefonica_chile.bandeja.ejbutiles.RolWeb;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;


/**
 * Controlador asociado a recursos que no requieren
 * que el usuario este autenticado.
 */
public class ControladorDeSeguridadPorDefecto extends ControladorDeRecurso {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	private HashMap mapeo;
	
	public ControladorDeSeguridadPorDefecto()
	{
		mapeo=new HashMap();
		
		// el número debe coincidir con el campo MN_ID de la tabla menú
		mapeo.put("verNuevaTorreControl.acc",new Integer(4));
		mapeo.put("verMarcoTorreControl.acc",new Integer(4));
		mapeo.put("verNuevaBandejaTorreControl.acc",new Integer(4));
		mapeo.put("verControlTecnico.acc",new Integer(5));
		mapeo.put("verBandejaBackOC.acc",new Integer(6));
		mapeo.put("verBandejaSupCo.acc",new Integer(8));
		mapeo.put("peticionesSupervisado.acc",new Integer(8));
		mapeo.put("verGestionInbound.acc",new Integer(9));
		mapeo.put("verGestionOutbound.acc",new Integer(10));
		mapeo.put("verBandejaEsperaBA.acc",new Integer(11));
		mapeo.put("verBandejaEsperaTerra.acc",new Integer(16));
		
		// adocarmo - CR9664 - inicio
		mapeo.put("verBandejaEsperaAula365.acc",new Integer(18));
		// adocarmo - CR9664 - fin		
		
		mapeo.put("ListadoMasivo.acc",new Integer(12));
		mapeo.put("verBuscadorGesOS.acc",new Integer(13));
		mapeo.put("verReporteTerreno.acc",new Integer(15));
		mapeo.put("iframeReporteTerreno.acc",new Integer(15));
		mapeo.put("verBandejaEsperaSigres.acc",new Integer(17));
		mapeo.put("verInstalarEquipos.acc",new Integer(22));
		
	}
	/**
	 * @param req
	 * @param recurso El recurso que requiere autorizacion
	 * 
	 * @return true Si el usuario esta autenticado y tiene permiso para entrar al recurso, falso si no.
	 */
	public boolean autoriza(String recurso, ServletRequest request) {
//		log.debug("Nombre Recurso:"+recurso);
		UsuarioWeb usuario = null;
		boolean ok = false;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceAdapter.getAccion(ControladorDeSeguridadPorDefecto.class,"bandeja"));
		traceOp.setColumn(TraceManager.COL_AUTORIZA,recurso);
		try{		
		  traceOp.init(log);		
		  traceManager.traceOpStart(traceOp);
		// Fin @Trace
		HttpSession sesion = ((HttpServletRequest) request).getSession();
		// Primero veo si el usuario ya esta en session
		AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion) sesion.getAttribute("controladorDeAplicacion");
		usuario = control.getUsuario();
			
		String miniTok=sacaTok(request);
		String username = getUsername((HttpServletRequest) request);
		if ( usuario != null && username != null && username.equals( usuario.getUsername()) )
		{
			log.debug("Usuario ya se  Encuentra Habilitado [" + usuario.getUsername() + "," + username + "]");
			ok = true;
		} else {
			if (username != null) {
				try {
					username = username.toUpperCase();
					usuario = getUsuarioWeb(username,miniTok);
				} catch (Exception e)
				{
//					log.error("Problemas recuperando usuario " + username, e);
					request.setAttribute("accion","usuarioNoExistente");
					ok=false;
				}
				if (usuario != null) {
					control.setUsuario(usuario);
					if(usuario.isTokenValido())
						ok=true;
					else
					{
						ok=false;
						request.setAttribute("accion","usuarioDuplicado");
						request.setAttribute("usuario_token",username.toUpperCase());
					}
				}
			}
		}
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}
		if(!tienePermiso(recurso,usuario))
			return false;
			
		return ok;
	}

	/**
	 * @param recurso
	 * @return
	 */
	private boolean tienePermiso(String recurso,UsuarioWeb usuario)
	{
		try
		{
			Integer valor=(Integer) mapeo.get(recurso);
			if(valor==null)
				return true;
			for(Iterator iterator=usuario.getMenues().iterator();iterator.hasNext();)
			{
				MenuDTO menu=(MenuDTO) iterator.next();
				for(Iterator iterator2=menu.getMenuHijos().iterator();iterator2.hasNext();)
				{
					MenuDTO hijo=(MenuDTO) iterator2.next();
					if(hijo.getMenuId().intValue()==valor.intValue())
						return true;	
				}
			}
			return false;
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			return false;
		}
	}
	/**
	 * @param request
	 * @return
	 */
	private String sacaTok(ServletRequest request)
	{
		return sinNull(request.getRemoteAddr(),"_")+sinNull(request.getRemoteHost(),"_");
	}

	/**
	 * @param string
	 * @param string2
	 */
	private String sinNull(String string, String del)
	{
		if(string==null)
			return ""+del;
		return string+del;
	}

	/**
	 * Seteo el usuario, agregando roles, permisos, etc...
	 * TODO Sacar datos desde la BD (via session bean's)
	 */
	private UsuarioWeb getUsuarioWeb(String username,String miniTok) throws RemoteException, NamingException, CreateException, UsuariosException {
		UsuarioWeb usuarioWeb = null;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceAdapter.getAccion(ControladorDeSeguridadPorDefecto.class));		
		try{		
			traceOp.init(log);		
			traceManager.traceOpStart(traceOp);
		// Fin @Trace
		
		UsuariosSessionLocalHome usuariosHome = (UsuariosSessionLocalHome) HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
		UsuariosSessionLocal usuariosSession =  usuariosHome.create();
		UsuarioDTO usuario = usuariosSession.retornaUsuario(username,miniTok);

		usuarioWeb = new UsuarioWeb(usuario.getId());
		usuarioWeb.setUsername(username);
		usuarioWeb.setNombre(usuario.getNombre());
		usuarioWeb.setTelefono(usuario.getFono());
		usuarioWeb.setTokenValido(usuario.isTokenValido());

		ArrayList rolesUsuarios = usuariosSession.recuperarRolesUsuario(usuario.getId());
		traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(rolesUsuarios.size())); //@Trace
		for (Iterator iter = rolesUsuarios.iterator(); iter.hasNext();) {
			RolDTO element = (RolDTO) iter.next();
			RolWeb rol = new RolWeb(element.getId(), element.getNombre());
			usuarioWeb.addRol(rol);
		}

		ArrayList listaMenuesUsuario = (ArrayList) usuariosSession.recuperarMenusUsuario(usuario.getId());
		usuarioWeb.setMenues(listaMenuesUsuario);
//		log.debug("Retorna Usuario con Token:"+usuarioWeb.isTokenValido());
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}
		return usuarioWeb;
	}

	/**
	 * Rescata el usuario resultado de la autenticacion global
	 * 
	 * @param request
	 * @return El username del usuario.
	 */
	public String getUsername(HttpServletRequest request) {
		String username = null;
		try
		{
			if (request.getUserPrincipal() != null) {
				username = request.getUserPrincipal().getName();
				//username = request.getUserPrincipal().getName();
				// username = "cn=YELIAS,ou=intranet,ou=Workflow,o=ctc,c=cl"
				//log.debug("el usuario conectado es:" + username);
				int idxIni = username.indexOf('=');
				int idxFin = username.indexOf(',');
				if (idxIni > 0 && idxFin > 0 && idxFin - idxIni > 1) {
					username = username.substring(idxIni + 1, idxFin);
				}/* else  //TODO: LCA: no es necesario aca
				{
					//TODO Sacar YELIAS a fierro, cuando no se encuentra usuario
					log.warn("Problemas recuperando userPrincipal!. Default: clave DUMMY VPISTBBA.properties");
					username = VpistbbaConfig.getVariable("DUMMY");
				}*/
			} else {
				//TODO Sacar YELIAS a fierro, cuando no se encuentra usuario
				//log.warn("Problemas recuperando userPrincipal!. REquest.getUserPrincipal==null. Default: clave DUMMY VPISTBBA.properties");
				username = VpistbbaConfig.getVariable("DUMMY");
			}
		} catch (Exception e) {
			// TODO Sacar YELIAS a fierro, cuando no se encuentra usuario
			log.warn("Problemas recuperando userPrincipal!. Default: clave DUMMY VPISTBBA.properties", e);
			username = VpistbbaConfig.getVariable("DUMMY");
		}
		return username;
	}
}
