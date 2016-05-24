package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.DepartamentoDTO;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.dto.AgenciaDTO;
import com.telefonica_chile.bandeja.torreControl.CuadroMandoDTO;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocal;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocalHome;

public class VerMarcoGestionInboundAcc extends Accion {

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento {
		try
		{
			HttpSession session = request.getSession(true);

			TorreControlSessionLocalHome tcHome = 
				(TorreControlSessionLocalHome) HomeFactory.getHome(TorreControlSessionLocalHome.JNDI_NAME);
			TorreControlSessionLocal tcLocal = tcHome.create();
 
			String tipo = request.getParameter("fTC_Tipo");
			String cuadro = request.getParameter("fTC_Cuadro");

			int intAppIdVpi = 3;
//			int intAppIdST =2;

			int intCuadro = 1;
			try {
				intCuadro = Integer.parseInt( cuadro );
			} catch (Exception e) {
				intCuadro = 1;
			}

			// Obtengo los Datos de para los Filtros.
			HashMap filtro = new HashMap();
			//filtro.put("ACTC_APID", ""+intAppId);

			// Obtengo los titulos para el Cuadro de Mando.
			ArrayList tcTituloAux = tcLocal.getTitulosCuadroMandoGI( filtro );
			ArrayList tcTitulo = new ArrayList();
//			ArrayList tcTitulo2 = new ArrayList();
			
			for (int j=0; tcTituloAux!=null && j<tcTituloAux.size(); j++) {
				CuadroMandoDTO cDto = (CuadroMandoDTO) tcTituloAux.get(j);
				if (cDto==null)
					continue;
				if ( 1 == cDto.getCuadro() && cDto.getAppID().intValue()==intAppIdVpi )
					tcTitulo.add( cDto );
//				else if ( 2 == cDto.getCuadro() && cDto.getAppID().intValue()==intAppIdST )
//					tcTitulo2.add ( cDto);
			}

			request.setAttribute("titulosCuadroMando", tcTitulo);
//			request.setAttribute("titulosCuadroMando2", tcTitulo2);

			if ( tipo==null || "TITULO".equals(tipo) )
				return ;			

			// Recuperamos los filtros.
			filtro = recuperaFiltrosFijos( request );

			// Obtengo Listado de Agencias.
			ArrayList listaAux = getLista( "DEPARTAMENTO" );
			ArrayList agListaAux = new ArrayList();
			
			for (int i=0; listaAux!=null && i<listaAux.size(); i++) {
				DepartamentoDTO mAgDto = (DepartamentoDTO) listaAux.get(i);
				if ( mAgDto == null )
					continue;
				AgenciaDTO agAux = new AgenciaDTO();
				agAux.setAgenId( mAgDto.getCodDpt() );
				agAux.setAgenDescripcion( mAgDto.getNombreDepartamento() );
				agAux.setAgenCodigo( mAgDto.getCodDpt() );
				agListaAux.add( agAux );
			}
			
			ArrayList agLista = null;
			String agenId = (String) filtro.get("COD_DPT");
			if ( agenId != null && agenId.length() > 0 && !agenId.equals("00")) {
				agLista = new ArrayList();

					for (int i=0; agListaAux!=null && i<agListaAux.size(); i++) {
						AgenciaDTO mAgDto = (AgenciaDTO) agListaAux.get(i);
						if ( mAgDto == null )
							continue;
						if ( agenId.equals( mAgDto.getAgenId() ) )
							agLista.add( mAgDto );
					}
			} else
				agLista = agListaAux;
			//log.debug( "LISTA AGENCIAS["+ agLista + "]");
			// Ahora obtengo los Datos para el Cuadro.
			Tabla t = tcLocal.getDatosCuadroMandoGI(  filtro );
			ArrayList datosCM = (ArrayList) t.getElementos();

			// Obtengo las Actividades por Cuadro de Mando.
			ArrayList actcLista = tcLocal.getActividadesCuadroMandoGI( filtro );


			// Traspaso a Hashmap las Agencias.
			HashMap AG = new HashMap();
			for (int j=0; datosCM!=null && j<datosCM.size(); j++) {
				AgenciaDTO mAgDto = (AgenciaDTO) datosCM.get(j);
				if ( mAgDto == null )
					continue;
				//log.debug( "Revisando Registro ["+ mAgDto.getAgenId() + "]");
				AG.put( mAgDto.getAgenId(), mAgDto );
			}

			// Sumamos los totales para las actividades de Torre de Control.  
			for (int i=0; agLista!=null && i<agLista.size(); i++) {
				AgenciaDTO mAgDto = (AgenciaDTO) agLista.get(i);
				if ( mAgDto == null )
					continue;
				AgenciaDTO auxAgDto = (AgenciaDTO) AG.get(mAgDto.getAgenId());

				if ( auxAgDto == null )
					continue;
				for (int j=0; actcLista!=null && j<actcLista.size(); j++) {
					CuadroMandoDTO cDto = (CuadroMandoDTO) actcLista.get(j);
					if (cDto==null)
						continue;
					ArrayList lAct = cDto.getListaActividades();
					int tot = 0;
					for (int k=0; lAct!=null && k<lAct.size(); k++) {
						Long idAct = (Long) lAct.get(k);
						tot += auxAgDto.getCantidad(""+idAct).intValue();
					}
					mAgDto.addCantidad(""+cDto.getActividadTC(), tot);
				}
			}

			request.setAttribute("listaAgencias", agLista);

		} catch (Exception e) {
			log.error("Exception en VerNuevaTorreControl", e);
		}
		
		setResultado("nuevoMarcoGI");
	}

	//
	private HashMap recuperaFiltrosFijos(HttpServletRequest request) {
		HashMap map = new HashMap();
		map.put( "COD_DPT", UtilesBandeja.sinNull(request.getParameter("departamento"), "") );
		map.put( "COD_LOC", UtilesBandeja.sinNull(request.getParameter("localidad"), "") );
		map.put( "USUA_ID", UtilesBandeja.sinNull(request.getParameter("fTC_Contratista"), "") );
		map.put( "SEGM_ID", UtilesBandeja.sinNull(request.getParameter("segmento"), "") );
		map.put( "BI_ESTADO_PETICION", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );
		map.put( "CCN", UtilesBandeja.sinNull(request.getParameter("CCN"), "") );
		
		if(request.getParameter("familiaPetiAtis")!=null && !request.getParameter("familiaPetiAtis").equals("00"))
			map.put( "BI_FAMILIA_PS", UtilesBandeja.sinNull(request.getParameter("familiaPetiAtis"), "") );
		map.put( "BI_FECHA_COMPROMISO", UtilesBandeja.sinNull(request.getParameter("fTC_FechaCompromiso"), "") );
		map.put( "BI_ESTADO_PETICION", UtilesBandeja.sinNull(request.getParameter("fTC_Flujo"), "") );
		map.put( "CUADRO_MANDO", UtilesBandeja.sinNull(request.getParameter("fTC_Cuadro"), "") );
		if(request.getParameter("opcocat")!=null && !request.getParameter("opcocat").equals("0"))
			map.put("opcocat",request.getParameter("opcocat"));
		return (map);
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
			if ( "DEPARTAMENTO".equals( tipo ) )
				listado = sbLocal.recuperarDepartamento();
			if ( "APLICACION".equals( tipo ) )
				listado = sbLocal.recuperarAplicaciones();

		} catch (Exception e) {
			log.info("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
		}

		return listado;
	}
	
	private ArrayList getListaMunicipio(String cod_dpt)
	{
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
			listado = sbLocal.recuperaMunicipiosPorDepto(cod_dpt);
		} catch (NamingException e) {
			log.info("NamingException. No se pudo Cargar Listado");
			return listado;
		} catch (CreateException e) {
			log.info("CreateException. No se pudo Cargar Listado");
			return listado;
		}
		return listado;
	}

	private ArrayList getListaLocalidad(String cod_mun)
	{
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
			listado = sbLocal.recuperaLocalidadesPorMunicipio(cod_mun);
		} catch (NamingException e) {
			log.info("NamingException. No se pudo Cargar Listado");
			return listado;
		} catch (CreateException e) {
			log.info("CreateException. No se pudo Cargar Listado");
			return listado;
		}
		return listado;
	}
}
