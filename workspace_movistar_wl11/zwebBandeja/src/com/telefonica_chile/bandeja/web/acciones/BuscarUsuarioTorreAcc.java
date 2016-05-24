/*
 * Created on Feb 16, 2005
 *
 * 
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.torreControl.ParametrosTCSessionLocal;
import com.telefonica_chile.bandeja.torreControl.ParametrosTCSessionLocalHome;
import com.telefonica_chile.comun.usuario_rol.session.UsuarioRolSessionLocal;
import com.telefonica_chile.comun.usuario_rol.session.UsuarioRolSessionLocalHome;
//import com.telefonica_chile.bandeja.web.UsuarioWeb;

//import com.ibm.

/**
 * @author JVelasco
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BuscarUsuarioTorreAcc extends Accion {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());


	private HashMap recuperaFiltros(HttpServletRequest request) {

		HashMap auxMap = new HashMap();

		String nombre = request.getParameter("nombre");
		String tipoCombo = request.getParameter("tipoCombo");
		String valorCombo = request.getParameter("valorCombo");

		if ( tipoCombo!=null && tipoCombo.length()!=0 )
			auxMap.put(tipoCombo, valorCombo);
		
		auxMap.put("NOMBRE", nombre);

		return auxMap;
		
	}
	/* (non-Javadoc)
	 * @see com.tecnonautica.mvc.Accion#ejecutar(javax.servlet.http.HttpServletRequest)
	 */
	protected void ejecutar(HttpServletRequest request) throws Evento {
		try {

			// Sacamos la pagina Actual y la Paginacion. 
			int pagina = getInt(request.getParameter("PAGINA_ACTUAL"), 1);
			if ( pagina < 1 )
				++pagina;
			int intPaginacion = getInt(request.getParameter("dpp"), 20);
			if ( intPaginacion < 20 )
				intPaginacion = 20;;

			// Se agrega parametro para determinar aplicacion a la cual se le mostrara su torre de control
			
			String rol = request.getParameter("idRol");
			Long idRol = new Long( rol );
			HashMap filtro = recuperaFiltros( request );
			String tipoCombo = request.getParameter("tipoCombo");
			if ( tipoCombo == null || tipoCombo.length()==0 )
				tipoCombo = findHabilidad( idRol );

			UsuarioRolSessionLocalHome urHome = (UsuarioRolSessionLocalHome) HomeFactory.getHome(UsuarioRolSessionLocalHome.JNDI_NAME);
			UsuarioRolSessionLocal urLocal = urHome.create();
			// Obtenemos los Usuarios ya con las Habilidades.
			ArrayList listaUsuarios = urLocal.usuariosByRol( idRol, tipoCombo );
			request.setAttribute("listaUsuarios", listaUsuarios);

			ArrayList listaFiltro = null;
			if ( "TODO".equals( tipoCombo ) ) {
				// Busco todos los Usuarios del ROL.
			} else if ( "PTO_VTA".equals( tipoCombo ) ) {
				// Buscamos los Punto de Venta y los Usuarios que tengan Punto Venta
				listaFiltro = getListaPuntoVenta();
				
			} else if ( "AGENCIA".equals( tipoCombo ) ) {
				// Buscamos las Agencias y los Usuarios que tengan AGENCIA
				listaFiltro = getListaAgencias();
			}

			request.setAttribute("tipoCombo", tipoCombo);
			request.setAttribute("listaFiltro", listaFiltro);
		} catch (Exception e) {
		}
	}

	protected ParametrosTCSessionLocal getParametrosTCSession()
			throws NamingException, CreateException {
				ParametrosTCSessionLocalHome home =
				(ParametrosTCSessionLocalHome) HomeFactory.getHome(
				ParametrosTCSessionLocalHome.JNDI_NAME);
				ParametrosTCSessionLocal local = home.create();
			return local;
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


	private String findHabilidad( Long idRol ) {
		String str = "TODO";
		if ( idRol.intValue() == 105 ) // Rol Instalaciones
			return "AGENCIA";
		if ( idRol.intValue() == 103 ) // Rol MDF
			return "AGENCIA";
		if ( idRol.intValue() == 110 ) // Rol Comercial
			return "PTO_VTA";

		return str;
	}

	public ArrayList getListaPuntoVenta() {
		ArrayList lista = null;
//		try {
//			PuntoVentaLocalHome pvHome = (PuntoVentaLocalHome) HomeFactory.getHome(PuntoVentaLocalHome.JNDI_NAME);
//			Collection col = pvHome.findAllPuntoVenta();
//
//			if ( col == null )
//				return lista;
//
		lista = new ArrayList();
//			for (Iterator it=col.iterator(); it.hasNext(); ) {
//				PuntoVentaDTO dtoPV = new PuntoVentaDTO(); 
//				PuntoVentaLocal pvLocal = (PuntoVentaLocal) it.next();	
//				dtoPV.setIdPtoVenta( (Integer) pvLocal.getPrimaryKey() );
//				dtoPV.setNombre( pvLocal.getNombre() );
//				lista.add( dtoPV );
//			}
//		} catch (EJBException e) {
//			log.error("EJBException. Buscando PtoVenta: '" + e.getMessage() + "'", e);
//		} catch (NamingException e) {
//			log.error("NamingException. Buscando PtoVenta: '" + e.getMessage() + "'", e);
//		} catch (FinderException e) {
//			log.error("FinderException. Buscando PtoVenta: '" + e.getMessage() + "'", e);
//		}

		return lista; 
	}

	public ArrayList getListaAgencias() {
		ArrayList lista = null;
//		try {
//			AgenciaEntityLocalHome aHome = (AgenciaEntityLocalHome) HomeFactory.getHome(AgenciaEntityLocalHome.JNDI_NAME);
//			Collection col = aHome.findAll();
//
//			if ( col == null )
//				return lista;

			lista = new ArrayList();
//			for (Iterator it=col.iterator(); it.hasNext(); ) {
//				AgenciaDTO dtoAg = new AgenciaDTO(); 
//				AgenciaEntityLocal aLocal = (AgenciaEntityLocal) it.next();	
//				dtoAg.setAgenId( (Long) aLocal.getPrimaryKey() );
//				dtoAg.setAgenDescripcion( aLocal.getDescripcion() );
//				lista.add( dtoAg );
//			}
//		} catch (EJBException e) {
//			log.error("EJBException. Buscando Agencia: '" + e.getMessage() + "'", e);
//		} catch (NamingException e) {
//			log.error("NamingException. Buscando Agencia: '" + e.getMessage() + "'", e);
//		} catch (FinderException e) {
//			log.error("FinderException. Buscando Agencia: '" + e.getMessage() + "'", e);
//		}

		return lista; 
	}
	
	
	/*
	private ArrayList getListaActividades()  {

		ArrayList lista = null;
		try {
			ActividadLocalHome aHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			Collection col = aHome.findAll();

			if ( col == null )
				return lista;

			lista = new ArrayList();
			for (Iterator it=col.iterator(); it.hasNext(); ) {
				ActividadDTO dtoAct = new ActividadDTO(); 
				ActividadLocal aLocal = (ActividadLocal) it.next();	
				dtoAct.setCodigo( String.valueOf( aLocal.getPrimaryKey() ) );
				dtoAct.setDescripcion( aLocal.getAct_descripcion() );
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
*/

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

		try
		{
			if ( "APLICACION".equals( tipo ) )
				listado = sbLocal.recuperarAplicaciones();
		} catch (BandejaException e) {
			log.info("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
		}

		return listado;
	}

}
