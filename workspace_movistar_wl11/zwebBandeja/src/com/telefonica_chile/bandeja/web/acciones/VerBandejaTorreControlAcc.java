/*
 * Created on Feb 16, 2005
 *
 * Clase ejecutora que ejerce la tarea de ver todas las cruzadas existentes
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolKey;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.bandeja.ActividadDTO;
import com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosException;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;
import com.telefonica_chile.bandeja.dto.AplicacionDTO;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.bandeja.semaforos.Alertas;
import com.telefonica_chile.bandeja.semaforos.AlertasException;
import com.telefonica_chile.bandeja.torreControl.CuadroMandoDTO;
import com.telefonica_chile.bandeja.torreControl.TablaTorreFiltro;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocal;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocalHome;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;
import com.telefonica_chile.comun.parametro.session.ParametroDTO;
import com.telefonica_chile.comun.usuario.dto.UsuarioDTO;
import com.telefonica_chile.comun.usuario.session.UsuarioSessionLocal;
import com.telefonica_chile.comun.usuario.session.UsuarioSessionLocalHome;
//import com.telefonica_chile.bandeja.web.UsuarioWeb;

//import com.ibm.

/**
 * @author Lai Chun-Hau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VerBandejaTorreControlAcc extends Accion {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.tecnonautica.mvc.Accion#ejecutar(javax.servlet.http.HttpServletRequest)
	 */
	protected void ejecutar(HttpServletRequest request) throws Evento {
		try {
			HttpSession session = request.getSession(true);
			SesionTorreControlDTO filtroTorre = (SesionTorreControlDTO) session.getAttribute("filtroTC");

			// Sacamos la pagina Actual y la Paginacion. 
			int pagina = getInt(request.getParameter("PAGINA_ACTUAL"), 1);
			if ( pagina < 1 )
				++pagina;
			int intPaginacion = getInt(request.getParameter("dpp"), 10);
			if ( intPaginacion < 10 )
				intPaginacion = 10;;

			//Se agrega parametro para determinar aplicacion a la cual se le mostrara su torre de control
			// Creo que no se ocupada. 
			String appId = request.getParameter("aplicacion");
			request.setAttribute("appid", appId);

			// Variables comunes...
			UrlAplicaciones url = new UrlAplicaciones();
			String urlVPISTBBA = url.getUrl("VPISTBBA");
			HashMap mapAplicaciones = url.mapAplicaciones();
			request.setAttribute("urlVPISTBBA", urlVPISTBBA);			
			request.setAttribute("mapAplicaciones", mapAplicaciones);

			// Obtengo el lisatdo de la Bandeja Integrada.
			getListaBandeja( request, pagina, intPaginacion, filtroTorre );
			
			setearNombreParametros( request, filtroTorre );

			// Eliminar Despues. (Primero Limpiar JSP. )
			ArrayList usrSupervisados = new ArrayList();
			request.setAttribute("usrSupervisados",usrSupervisados);

		} catch (Exception e) {
			log.error("Problemas Recuperando la Torre de Control.", e);
		}
	}

	private int getInt(String str, int def) {
		if ( str==null )
			return def;

		try { 
			return ( Integer.parseInt( str ) );
		} catch (Exception e) {
		}
		return def;
	}

	private boolean getListaBandeja(HttpServletRequest request, int pagina, int paginacion, SesionTorreControlDTO filtroTorre ) {
		HashMap map = recuperaFiltrosFijos(request, filtroTorre);

		if (map == null) {
			request.setAttribute("segundos", new ParametroDTO() );
			request.setAttribute("peticiones", new TablaTorreFiltro());
			return false;
		}

		//Si existe parametros, entones completa la tabla de cruzada filtrada
		AtiempoControladorDeAplicacion control =
			(AtiempoControladorDeAplicacion) getControladorDeAplicacion();
		UsuarioWeb usuario = control.getUsuario();
		
		if ( usuario==null || usuario.getId()==null) {
			request.setAttribute("segundos", new ParametroDTO() );
			request.setAttribute("peticiones", new TablaTorreFiltro());
			return false;
		}

		Long idUsuario = null;
		idUsuario = ( usuario.getId() == null ) ? new Long(0) : usuario.getId();

		try {
			TorreControlSessionLocalHome tcHome = (TorreControlSessionLocalHome) HomeFactory.getHome(TorreControlSessionLocalHome.JNDI_NAME);
			TorreControlSessionLocal tcLocal = tcHome.create();

			Tabla tabla = (Tabla) tcLocal.setearDatoTablaPeticion(map, pagina, paginacion);

			ArrayList listaGSeg = getLista("GRUPO_SEGMENTO");
			ArrayList listaAp = getLista("APLICACION");
			ArrayList listaAct = getListaActividades();

			request.setAttribute("listaAct", listaAct);


			// Deberiamos setear los Nombres del GSEGMENTO a las peticos obtenidas.
			HashMap PR = new HashMap(); 
			for (int i=0; listaGSeg!=null && i<listaGSeg.size(); i++) {
				SegmentoDTO sDto = (SegmentoDTO) listaGSeg.get(i);
				if ( sDto == null || sDto.getSegmId()==null )
					continue;
				PR.put( "SEG_"+sDto.getSegmId(), sDto.getSegmDescripcion() );
			}

			for (int i=0; listaAp!=null && i<listaAp.size(); i++) {
				AplicacionDTO apDto = (AplicacionDTO) listaAp.get(i);
				if ( apDto == null || apDto.getApId()==null )
					continue;
				PR.put( "AP_"+apDto.getApId(), apDto.getApNombre() );
			}

			// Ahora las Actividades.
			HashMap ACT = new HashMap();
			for (int i=0; listaAct!=null && i<listaAct.size(); i++) {
				ActividadDTO aDto = (ActividadDTO) listaAct.get(i);
				if ( aDto == null || aDto.getId()==null )
					continue;
				ACT.put( "ACT_" + aDto.getId(), aDto );
			}

			// Le completamos los Datos que Faltan.
			Alertas alerta = null;
			try {
				alerta = new Alertas();
			} catch (AlertasException e) {
				log.error("Problemas recuperando alertas Bandeja TC");
			}

			
			Long idRol = null; 
			ArrayList listIdBi = new ArrayList();
			ArrayList listaUsuaId = new ArrayList();
			for (Iterator it = tabla.iterator(); it.hasNext();)	{
				PeticionDTO pDto = (PeticionDTO) it.next();
				if ( pDto == null )
					continue;

				if ( pDto.getUsuaId() == null )
					continue;

				// Obtener el Nombre de Usuario.
				listaUsuaId.add( pDto.getUsuaId() );

				if ( pDto.getIdBloqueSegmento()==null )
					continue;
				pDto.setNombreBloqueSegmento( (String) PR.get( "SEG_"+pDto.getIdBloqueSegmento() ) );
				pDto.setAplicacionNombre( (String) PR.get( "AP_"+ pDto.getApId() ) );

				// Calculamos los DAtos de Actividad.
				ActividadDTO aDto = (ActividadDTO) ACT.get("ACT_" + pDto.getActividadId());
				if ( aDto == null || aDto.getId()==null )
					continue;
				pDto.setActividadCodigo( aDto.getCodigo() );
				pDto.setActividadDescripcion( aDto.getDescripcion() );
				pDto.setActividadNombreReversa( aDto.getNombreReversa() );
				pDto.setRolId( ""+aDto.getIdRol() );
				idRol = aDto.getIdRol();

				alerta.setRol( pDto.getRolId() );
				alerta.setActividadSemaforo( pDto.getActividadCodigo() );
				alerta.setApId( ""+pDto.getApId() );

				if ( pDto.getBiFechaCompromiso() != null ) {
					alerta.setFechaFinal( pDto.getBiFechaCompromiso() ); 
					pDto.setBiSemaforoCompromiso( alerta.getSemaforoCompromiso() );
				}

				if ( pDto.getBiFechaAsignacion() != null ) {
					alerta.setFechaFinal( pDto.getBiFechaAsignacion() ); 
					pDto.setBiSemaforoActividad( alerta.getSemaforoActividad() );
				}

				listIdBi.add( pDto.getBiId() );
			}

			// Buscamos los nombres de los Usuarios.
			if ( listaUsuaId.size() > 0 ) {
				try {
					UsuarioSessionLocalHome usHome = (UsuarioSessionLocalHome) HomeFactory.getHome(UsuarioSessionLocalHome.JNDI_NAME);
					UsuarioSessionLocal usLocal = usHome.create();
					
					ArrayList listaUsr = usLocal.getUsuarios( listaUsuaId );
					HashMap US = new HashMap(); 
					for (int i=0; listaUsr!=null && i<listaUsr.size(); i++) {
						UsuarioDTO uDto = (UsuarioDTO) listaUsr.get(i);
						if ( uDto == null || uDto.getIdUsuario()==null )
							continue;
						US.put( uDto.getIdUsuario(), uDto.getNombre() );
					}
					// Recien le cambiamos el nombre.
					for (Iterator it = tabla.iterator(); it.hasNext();)	{
						PeticionDTO pDto = (PeticionDTO) it.next();
						if ( pDto == null )
							continue;
					
						if ( pDto.getUsuaId() == null )
							continue;
						pDto.setUsuaNombre( (String) US.get(pDto.getUsuaId()) );
					}
				} catch (NamingException e1) {
					log.error("NamingException. No se pudo Obtener Usuarios de Bandeja. [" + idUsuario + "]");
				} catch (CreateException e1) {
					log.error("CreateException. No se pudo Obtener Usuarios de Bandeja. [" + idUsuario + "]");
				}
			}
			
			//TODO: Se saca el valor de los segundos de bloqueo del vpistbbaConfig.properties
			ParametroDTO paraDto = new ParametroDTO();
			paraDto.setNombreParametro("Segundos");
			paraDto.setValorParametro(VpistbbaConfig.getVariable("Segundos"));
			
			// TODO: Cambiar esto. Tambien Revisar la Bandeja Integrada y Bandeja Supervisor 
			// 1.- sacar los Campos Variables del Usuario.
			// 2.- Sacar los Valores Variables de el Listado de Peticiones.
			// 3.- Hacer Merge... 
			UsuariosSessionLocalHome uHome = (UsuariosSessionLocalHome) HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
			UsuariosSessionLocal uLocal = uHome.create();
			ArrayList listValoresVariables = (ArrayList) uLocal.recuperarValoresVariables(idUsuario, listIdBi);

			request.setAttribute("segundos", paraDto);
			request.setAttribute("listValoresVariables", listValoresVariables);
			request.setAttribute("peticiones", tabla);
			request.setAttribute("nombreTabla", "peticiones");
			request.setAttribute("idRol", idRol);
			//request.setAttribute("listSegmentos", listaGSeg);
			

			return true;

		} catch (NamingException e) {
			log.error("NamingException. No se pudo Obtener Torre de Control. [" + idUsuario + "]");
		} catch (CreateException e) {
			log.error("CreateException. No se pudo Obtener Torre de Control. [" + idUsuario + "]");
		} catch (BandejaException e) {
			log.error("BandejaException. Torre de Control. [" + idUsuario + "] : " + e.getMessage(), e);
		} catch (UsuariosException e) {
			log.error("UsuariosException. Torre de Control. [" + idUsuario + "]: " + e.getMessage(), e);
		}
		
		return false;

	}

	private void setearNombreParametros(HttpServletRequest request, SesionTorreControlDTO filtroTorre) {
		// Obtenemos el Nombre de la Agencia y el Nombre de la Actividad TC.
		if ( filtroTorre==null || "".equals(filtroTorre.getCampo("actividadId")) ) {
			request.setAttribute("nombreActividad", "NO SELECCIONADA");
			request.setAttribute("nombreAgencia", "NO SELECCIONADA");
			return;
		}

		HashMap filtro = new HashMap();
		filtro.put("ACTC_ID",  filtroTorre.getCampo("actividadId") );
		Long agenId = null;
		try {
			agenId = new Long( filtroTorre.getCampo("agenciaId") );
		} catch( Exception e ) {
			agenId = new Long(0);
		}

		String descActividad = "";
		String nombreAgencia = "";
		try {
			TorreControlSessionLocalHome tcHome = (TorreControlSessionLocalHome) HomeFactory.getHome(TorreControlSessionLocalHome.JNDI_NAME);
			TorreControlSessionLocal tcLocal = tcHome.create();
			
			ArrayList tcTituloAux = tcLocal.getTitulosCuadroMando( filtro );
			for (int j=0; tcTituloAux!=null && j<tcTituloAux.size(); j++) {
				CuadroMandoDTO cDto = (CuadroMandoDTO) tcTituloAux.get(j);
				if (cDto==null)
					continue;
				descActividad = cDto.getNombreActividad();
			}
			
//			AgenciaLocalHome agHome = (AgenciaLocalHome) HomeFactory.getHome(AgenciaLocalHome.JNDI_NAME);
//			AgenciaLocal agLocal = agHome.findByPrimaryKey( agenId );
			nombreAgencia = "";//agLocal.getDescripcion();
//		} catch (NamingException e) {
//		} catch (CreateException e) {
//		} catch (FinderException e) {
		}
		catch(Exception e){
		}
		request.setAttribute("nombreActividad", descActividad);
		request.setAttribute("nombreAgencia", nombreAgencia);
	}

	private String findHabilidad( Long idRol ) {
		String str = "TODO";
		if ( idRol.intValue() == 105 ) // Rol Instalaciones
			return "AGENCIA";
		if ( idRol.intValue() == 110 ) // Rol MDF
			return "AGENCIA";
		if ( idRol.intValue() == 110 ) // Rol Comercial
			return "PUNTO_VENTA";

		return str;
	}

	//Obtener el id de agencia y actividad
	private HashMap recuperaFiltrosFijos(HttpServletRequest request, SesionTorreControlDTO filtroTorre) {
		if ( filtroTorre == null )
			filtroTorre = new SesionTorreControlDTO();
		String actividad = UtilesBandeja.sinNull(request.getParameter("actividadId"), filtroTorre.getCampo("actividadId") );
		String agencia = UtilesBandeja.sinNull(request.getParameter("agenciaId"), filtroTorre.getCampo("agenciaId") );

		// Si no tiene ningun valor, deje de ejecutar
		if (actividad == null || agencia == null) {
			return null;
		}

		// Si no tiene ningun valor, deje de ejecutar
		if (actividad.length() == 0 || agencia.length() == 0) {
			return null;
		}

		HashMap mapAux = new HashMap();
		mapAux.put( "BI_NRO_PETICION", "numPeticion");
	
		mapAux.put( "AP_ID", "aplicacion");
		mapAux.put( "AGEN_ID", "agenciaId");
		mapAux.put( "ACT_TC_ID", "actividadId");
		mapAux.put( "BI_TIPO_TRABAJO", "tipotrabajo");
		mapAux.put( "BI_FAMILIA_PS", "familia");
		mapAux.put( "SEGM_ID", "segmento");
		mapAux.put( "BI_FECHA_COMPROMISO", "fecha");
		mapAux.put( "BI_ESTADO_PETICION", "otrostipos");
		mapAux.put( "ACTIVIDAD_ID", "actividadFiltro");
		mapAux.put( "TICA_ID", "TICA");
		mapAux.put( "PCOM", "PCOM");
		mapAux.put( "CCN", "CCN");

		HashMap map = new HashMap();
		Iterator it = mapAux.keySet().iterator();
		for (; it.hasNext(); ) {
			String key = (String) it.next();
			String key2 = (String) mapAux.get(key);
			String valor = UtilesBandeja.sinNull(request.getParameter(key2), filtroTorre.getCampo(key2));
			filtroTorre.setCampo(key2, valor);
			if ( "familia".equals( key2 ) && valor.length()>0) {
				valor = "'%" + valor + "%'";
			}
			
			map.put( key, valor );
		}
		
		request.getSession().setAttribute("filtroTC", filtroTorre);
		return (map);
	}
	

	private BIntegradaSessionLocal getBandejaSession() throws NamingException, CreateException {
		BIntegradaSessionLocalHome home =  
					(BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		BIntegradaSessionLocal local = home.create();
		return local;
	}

	private ArrayList getListaActividades()  {

		ArrayList lista = null;
		try {
			ActividadLocalHome  aHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			Collection col = aHome.findAll();

			if ( col == null )
				return lista;

			lista = new ArrayList();
			for (Iterator it=col.iterator(); it.hasNext(); ) {
				ActividadDTO dtoAct = new ActividadDTO(); 
				ActividadLocal aLocal = (ActividadLocal) it.next();	
				dtoAct.setCodigo( String.valueOf( aLocal.getPrimaryKey() ) );
				dtoAct.setDescripcion( aLocal.getAct_descripcion() );
				dtoAct.setNombreReversa( aLocal.getAct_nombre_reversa() );
				dtoAct.setId( (Long) aLocal.getPrimaryKey() );
				RolKey rolKey=(RolKey) aLocal.getFk_act_rol().getPrimaryKey();
				dtoAct.setIdRol( rolKey.rol_id );
				lista.add( dtoAct );
			}
		} catch (EJBException e) {
			log.error("EJBException. Buscando Actividades: '" + e.getMessage() + "'", e);
		} catch (NamingException e) {
			log.error("NamingException. Buscando Actividades: '" + e.getMessage() + "'", e);
		} catch (FinderException e) {
			log.error("FinderException. Buscando Actividades: '" + e.getMessage() + "'", e);
		}

		return lista; 
	}


	BIntegradaSessionLocal sbLocal = null;

	private void getSBLocal() throws NamingException, CreateException {
		BIntegradaSessionLocalHome biHome =  
					(BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		sbLocal = biHome.create();
	}

	private ArrayList getLista( String tipo ) {
		ArrayList listado = new ArrayList();

		try {
			if ( sbLocal == null )
				getSBLocal();
		} catch (NamingException e) {
			log.info("NamingException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		} catch (CreateException e) {
			log.info("CreateException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		}

		try {
			if ( "APLICACION".equals( tipo ) )
				listado = sbLocal.recuperarAplicaciones();

		} catch (BandejaException e) {
			log.info("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
		}

		return listado;
	}

}
