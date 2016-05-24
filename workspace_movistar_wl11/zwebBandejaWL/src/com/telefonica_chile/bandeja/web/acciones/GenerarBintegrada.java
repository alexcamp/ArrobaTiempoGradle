/*
 * Created on Aug 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.bandeja.ActividadDTO;
import com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO;
import com.telefonica_chile.bandeja.dto.PeticionDTO;

public class GenerarBintegrada extends HttpServlet {

	private static DataSource datasource = null;
	private static ServletContext servletContext = null;
	private static boolean inicializado = false;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public GenerarBintegrada() {
		super();
	}

	public void setInit(boolean param) {
		inicializado = param;
	}

	public void init(ServletConfig config) {

		if (inicializado)
			return;
	}

	public static DataSource getDS() {
		return datasource;
	}
	
	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doGet( req, res );
		
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		//HttpSession session = req.getSession();
//		HttpSession sesion = ((HttpServletRequest) req).getSession();
//		
//
//		Map datosSalida =new HashMap();
//		//String  formato = req.getParameter("formato");
//		String  usuario = req.getParameter("usuario");
//		String  rolUsr = req.getParameter("rolUsr");
//		Long  rolUsuario= new Long(0);
//
//		if(!rolUsr.equals("")){
//			rolUsuario = new Long(rolUsr);
//		} 
//		Long  longUsuario= new Long(0);
//		if(!usuario.equals("")){
//			longUsuario = new Long(usuario);
//		}
////		String listaPet = req.getParameter("listaPet");
//		log.info(" <<===  INGRESO A  Generar Archivo Bintegrada ( rolUsr , Usuario ) == (" + rolUsr + " , "  + usuario+" )");
//											
//		
//		String listaBiId = "";
//		if ( req.getParameter("checkAll")!=null ) {
//			// Sacamos todas las Peticos.
//		}else{
//			Enumeration e = req.getParameterNames();
//			String coma = "";
//			while (e.hasMoreElements()){
//				String nombreParam = (String) e.nextElement();
//				if (nombreParam.startsWith("checkbox")) {
//					try {
//						listaBiId += coma + nombreParam.substring( "checkbox".length() );
//						coma = ",";
//					} catch (Exception ex){
//						log.warn("No pude obtener un folio del String '" + nombreParam + "' para una Accion Masiva");
//					}
//				}
//			}
//		}
//		
////		TablaBandeja tabBandeja = new TablaBandeja();
//		List listaPeticiones = new ArrayList();
//	
//		try {
//			HashMap filtro = recuperaFiltrosFijos( req );
//			filtro.put("LISTA_BI_ID", listaBiId);
////			Recuperamos el Session Bean BIntegrada
//			if ( sbLocal == null )
//				getSBLocal();
////			Buscamos el listado de Peticiones
//			listaPeticiones = sbLocal.getListadoBIntegrada(filtro);
//	
//			// Cargamos Agencias, TICA y CCN, solo si hay peticos. 
//			if ( listaPeticiones!=null && listaPeticiones.size()>0 ) {
//				
//				// Buscamos los Valores Variables
//				HashMap dataVV = sbLocal.getListadoVV( filtro );
//				
//				// Cargamos las Agencias y Grupo Segmento.
//				HashMap data = cargarDatos("");
//				for (int i=0; i<listaPeticiones.size(); i++) {
//					PeticionDTO p = (PeticionDTO) listaPeticiones.get(i);
//					p.setActividadDescripcion( UtilesBandeja.sinNull((String) data.get("AC_" + p.getActividadId()), ""+p.getActividadId()) );
//					p.setNombreBloqueSegmento( UtilesBandeja.sinNull((String) data.get("SEG_" + p.getIdBloqueSegmento()), "") );
//					HashMap aux = (HashMap) dataVV.get( ""+p.getBiId() );
//					if (aux == null)
//						continue;
//					p.setIdTica( UtilesBandeja.sinNull((String) aux.get("103"), "") );
//					p.setCentral( UtilesBandeja.sinNull((String) aux.get("100"), "") );
//				}
//			}
//		} catch (NamingException e) {
//			log.info("NamingException. No se pudo Cargar Bean[BIntegradaSessionBean]");
//		} catch (CreateException e) {
//			log.info("CreateException. No se pudo Cargar Bean[BIntegradaSessionBean]");
//		} catch (Exception e) {
//			log.info("hubo un Error en el NumberFormatException de Generar Excel = " + e.toString());
//		}		
//		res.setContentType("application/ms-excel");
//		res.setHeader("Content-Disposition", "attachment;filename=GenerarBintegrada.xls");
//		Escribir(req, res,listaPeticiones);
//		
//		log.info(" <<===  SALIO de Generar Archivo Bintegrada ===>> ");
		
}

	//CSV
	private void Escribir(HttpServletRequest req, HttpServletResponse res, List datos)
		throws IOException, ServletException {
		
		PrintWriter out = res.getWriter();
		String tabular="\t";

		// Imprimo los datos con tabulacion
		out.print("Peticion\t");
		out.print("Actividad\t");
		out.print("Cliente\t");
		out.print("Area\t");
		out.print("Tica\t");
		out.print("Número\t");
		out.print("Ccn\t");
		out.print("Segmento\t");
		out.print("Fecha de Inicio\t");
		out.print("Fecha de Apertura\t");
		out.print("Fecha de Compromiso\t");
		out.print("Tipo Agendamiento\t");
		out.print("Hora Desde\t");
		out.print("Hora Hasta\t");

		out.print("\n");
		
		imprimir(datos, tabular, out);
	}
	
	private void imprimir(List datos, String car, PrintWriter out){
		
		HashMap hashTipoAG = new HashMap();
		hashTipoAG.put(new Long(0), "Llamada");
		hashTipoAG.put(new Long(1), "Plazo Maximo");
		hashTipoAG.put(new Long(2), "Rango");
		hashTipoAG.put(new Long(3), "Fecha Especifica");
		hashTipoAG.put(new Long(4), "Fecha Excepcion");
		
		log.info(" ======== Inicio imprimirDatos Excel =========" );
		PeticionDTO archivo=null;

		String fComp = "";
		String hDesde = "";
		String hHasta = "";
		for(int i=0;i<datos.size();i++) {
			archivo = (PeticionDTO) datos.get(i);
			log.info(" Imprimiendo A Archivo (Peticion) = (" +archivo.getBiNroPeticion() + " - " + i + ")");			
			out.print(archivo.getBiNroPeticion()+ car);
			out.print(archivo.getActividadDescripcion()+ car);
			out.print(archivo.getBiClienteNombre() + " " + archivo.getBiClienteApellidos() + car);
			out.print(archivo.getBiClienteArea()+ car);
			out.print(archivo.getIdTica()+ car);
			out.print(archivo.getBiClienteServicio()+ car);
			out.print(archivo.getCentral()+ car);
			out.print(archivo.getNombreBloqueSegmento()+ car);
			out.print( archivo.getBiFechaInicio() + car);
			out.print( archivo.getBiFechaApertura() + car);
			

			fComp = formatoFecha(archivo.getBiFechaCompromiso(), "dd/MM/yyyy", "");
			if ( "01/01/0001".equals(fComp))
				out.print( "" + car );
			else
				out.print( archivo.getBiFechaCompromiso() + car );

			out.print( sinNull( (String)hashTipoAG.get(archivo.getIdTipoAgendamiento()), "") + car );

			if ( archivo.getIdTipoAgendamiento() != null && 
					 archivo.getIdTipoAgendamiento().intValue()!=1 ) {
				hDesde = archivo.getHoraDesde();
				hHasta = archivo.getHoraHasta();
			} else {
				hDesde = "";
				hHasta = "";
			}
	
			out.print( sinNull( hDesde, "") + car );
			out.print( sinNull( hHasta, "") + car );
			out.print("\n");			
		}		
	}

	private String sinNull(String str, String porDefecto) {
		if (str == null)
			return porDefecto;
		return str;
	}

	private String formatoFecha(Date fecha, String formato, String porDefecto) {
		String salida = porDefecto;
		if (fecha == null)
			return salida;

		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
			//TimeZone tz = TimeZone.getTimeZone("America/Santiago");
			//formatoFecha.setTimeZone(tz);
			salida = formatoFecha.format(fecha);
		} catch (Exception e) {
		}
		return salida;
	}

	private HashMap recuperaFiltrosFijos(HttpServletRequest request) {
	
		//Guardar los valores en HashMap
		HashMap map = new HashMap();
		map.put( "USUA_ID", UtilesBandeja.sinNull(request.getParameter("usuario"), "") );
		
		map.put( "BI_NRO_PETICION", UtilesBandeja.sinNull(request.getParameter("numPeticion"), "") );
		map.put( "AP_ID", UtilesBandeja.sinNull(request.getParameter("aplicacion"), "") );
		
		map.put( "AGEN_ID", UtilesBandeja.sinNull(request.getParameter("agencia"), "") );
	
		map.put( "BI_CLIENTE_RUT", UtilesBandeja.sinNull(request.getParameter("rutCliente"), "") );
		map.put( "BI_CLIENTE_RUTDV", UtilesBandeja.sinNull(request.getParameter("dvCliente"), "") );
		
		String fam = UtilesBandeja.sinNull(request.getParameter("familia"), "");
		if ( fam!=null && fam.length()>0 )
			fam = "'%" + fam + "%'";
		map.put( "BI_FAMILIA_PS", fam );
	
		map.put( "SEGM_ID", UtilesBandeja.sinNull(request.getParameter("segmento"), "") );
		
		String prodServicio = request.getParameter("prodServicio");
		if (prodServicio != null && prodServicio.trim().length() > 0)
			map.put("BI_FAMILIA_PS", prodServicio);
	
		map.put( "ROL_ID", UtilesBandeja.sinNull(request.getParameter("rol"), "") );
		map.put( "ISP_ID", UtilesBandeja.sinNull(request.getParameter("isp"), "") );
		map.put( "BI_ESTADO_PETICION", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );
		map.put( "BI_CLIENTE_SERVICIO", UtilesBandeja.sinNull(request.getParameter("numFono"), "") );
		map.put( "BI_CLIENTE_AREA", UtilesBandeja.sinNull(request.getParameter("areaFono"), "") );
	
		return (map);
	}

	private HashMap cargarDatos( String tipo ) {
		HashMap map = new HashMap();

		ArrayList listaAct = getLista( "ACTIVIDADES" );
		ArrayList listaGSeg = getLista( "GRUPO_SEGMENTO" );

		for (int i=0; listaGSeg!=null && i<listaGSeg.size(); i++) {
			SegmentoDTO sDto = (SegmentoDTO) listaGSeg.get(i);
			if ( sDto == null || sDto.getSegmId()==null )
				continue;
			map.put( "SEG_"+sDto.getSegmId(), sDto.getSegmDescripcion() );
		}
		for (int i=0; listaAct!=null && i<listaAct.size(); i++) {
			ActividadDTO acDto = (ActividadDTO) listaAct.get(i);
			if ( acDto == null || acDto.getId()==null )
				continue;
			map.put( "AC_"+acDto.getId(), acDto.getDescripcion() );
		}

		return map;
		
	}
	private void getSBLocal() throws NamingException, CreateException {
		BIntegradaSessionLocalHome biHome =  
					(BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		sbLocal = biHome.create();
	}

	BIntegradaSessionLocal sbLocal = null;

	private ArrayList getLista( String tipo ) {
		ArrayList listado = new ArrayList();

//		try {
//			if ( sbLocal == null )
//				getSBLocal();
//		} catch (NamingException e) {
//			log.info("NamingException. No se pudo Cargar Listado[" + tipo + "]");
//			return listado;
//		} catch (CreateException e) {
//			log.info("CreateException. No se pudo Cargar Listado[" + tipo + "]");
//			return listado;
//		}
//
//		try {
////			if ( "AGENCIA".equals( tipo ) )
////				listado = sbLocal.recuperarAgencias();
//			if ( "ACTIVIDADES".equals( tipo ) )
//				listado = sbLocal.recuperarActividades();
//			if ( "GRUPO_SEGMENTO".equals( tipo ) )
//				listado = sbLocal.recuperarGrupoSegmento();
//			if ( "ISP".equals( tipo ) )
//				listado = sbLocal.recuperarIsps();
//			if ( "APLICACION".equals( tipo ) )
//				listado = sbLocal.recuperarAplicaciones();
//			if ( "CAMPO_VARIABLE".equals( tipo ) )
//				listado = sbLocal.recuperarCamposVariables();
//
//		} catch (BandejaException e) {
//			log.info("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
//		}

		return listado;
	}

}