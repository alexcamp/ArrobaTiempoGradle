/*
 * Created on Nov 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.web.utiles.UtilesWeb;
/**
 * @author gavalen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class verGenerarTorreControlAcc  extends Accion {
	
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	protected void ejecutar(HttpServletRequest request) throws Evento {
	
//		try {
//			HttpSession session = request.getSession(true);
//			AtiempoControladorDeAplicacion control =(AtiempoControladorDeAplicacion) getControladorDeAplicacion();
//			UsuarioWeb usuario = control.getUsuario();
//
//			String str = "";
//			Enumeration e = request.getParameterNames();
//			while (e.hasMoreElements()) {
//				String key = (String) e.nextElement();
//				String valor = request.getParameter(key);
//				str += "'" + key + "'='" + valor + "'\n";
//			}
//			log.debug("Parametros Recibidos:\n" + str);
//
//			String actividadTC = request.getParameter("actividadId");
//			String agencia = request.getParameter("agenciaId");
//
////			AgenciaLocal AgenLocal=null;
////			try {
////				AgenciaLocalHome PSHome = (AgenciaLocalHome)HomeFactory.getHome(AgenciaLocalHome.JNDI_NAME);
////				AgenLocal = PSHome.findByPrimaryKey( new Long(agencia) );
////			} catch (Exception e2) {
////				log.debug("Error Agencia =" + e2.toString());	
////			}
//
//			String descAgencia  = "";//AgenLocal.getDescripcion();
//			String descActividad = "";
//			
//			TorreControlSessionLocalHome tcHome = (TorreControlSessionLocalHome) HomeFactory.getHome(TorreControlSessionLocalHome.JNDI_NAME);
//			TorreControlSessionLocal tcLocal = tcHome.create();
//			
//			HashMap filtro = new HashMap();
//			filtro.put("ACTC_ID", actividadTC);
//			
//			HashMap map = recuperaFiltrosFijos(request);
//			if (map == null)
//				map = new HashMap();
//
//			ArrayList tcTituloAux = tcLocal.getTitulosCuadroMando( filtro );
//			for (int j=0; tcTituloAux!=null && j<tcTituloAux.size(); j++) {
//				CuadroMandoDTO cDto = (CuadroMandoDTO) tcTituloAux.get(j);
//				if (cDto==null)
//					continue;
//				descActividad = cDto.getNombreActividad();
//			}
//			
//			//Invocamos al método
//			int largoFiltro = Integer.parseInt(request.getParameter("largoPeticos"));
//			
//			ArrayList listaPetico =   filtraCheck(request, largoFiltro);
//			
//			//si filtraCheck es mayor a cero, condicionamos
//			if(listaPetico.size() <= 0){
//				
//				listaPetico = tcLocal.getListaPeticiones( map );
//				
//				
//			}
//			
//			if ( listaPetico == null )
//				listaPetico = new ArrayList();
//
//			String largo = "" + listaPetico.size();
//
//			//TODO: Se saca el valor de los segundos de bloqueo del vpistbbaConfig.properties
//			ParametroDTO paraDto = new ParametroDTO();
//			paraDto.setNombreParametro("Segundos");
//			paraDto.setValorParametro(VpistbbaConfig.getVariable("Segundos"));
//			
//			int cantidadPeticiones = Integer.parseInt(largo);
//			//filtraCheck(request,cantidadPeticiones);
//			
//			
//			// ENVÍO LAS URL CORRESPONDIENTE A CADA APLICACION para los menú de la cabecera
//			UrlAplicaciones url = new UrlAplicaciones();
//			String urlVPISTBBA = url.getUrl("VPISTBBA");
//			HashMap mapAplicaciones = url.mapAplicaciones();
//			request.setAttribute("urlVPISTBBA", urlVPISTBBA);			
//			request.setAttribute("mapAplicaciones", mapAplicaciones);
//			
//			request.setAttribute("subsegundos", paraDto);
//			request.setAttribute("largo", largo);
//			request.setAttribute("Descagencia", descAgencia);
//			request.setAttribute("descActividad", descActividad);
//			request.setAttribute("peticiones", listaPetico);
//						
//			
//		}
//		 catch (Exception e) {
//		}

	}
	
	private HashMap recuperaFiltrosFijos(HttpServletRequest request) {
		
			String actividad = request.getParameter("actividadId");
			String agencia = request.getParameter("agenciaId");
			

			// Si no tiene ningun valor, deje de ejecutar
			if (actividad == null || agencia == null)
				return null;
			//Guardar los valores en HashMap
			HashMap map = new HashMap();
			
			String fam = UtilesBandeja.sinNull(request.getParameter("familia"), "");
			if ( fam!=null && fam.length()>0 )
				fam = "'%" + fam + "%'";
			
			map.put( "BI_FAMILIA_PS", fam );
			
			
			
			map.put( "BI_NRO_PETICION", UtilesBandeja.sinNull(request.getParameter("numPeticion"), "") );
			map.put( "AGEN_ID", UtilesBandeja.sinNull(request.getParameter("agenciaId"), "") );
			map.put( "ACT_TC_ID", UtilesBandeja.sinNull(request.getParameter("actividadId"), "") );
			map.put( "TICA_ID", UtilesBandeja.sinNull(request.getParameter("TICA"), "") );
			map.put( "PCOM", UtilesBandeja.sinNull(request.getParameter("PCOM"), "") );
			map.put( "BI_FECHA_COMPROMISO", UtilesBandeja.sinNull(request.getParameter("fecha"), "") );
			map.put( "BI_ESTADO_PETICION", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );
			map.put( "ACTIVIDAD_ID", UtilesBandeja.sinNull(request.getParameter("actividadFiltro"), "") );

			return (map);
		}
		
	private ArrayList filtraCheck(HttpServletRequest request, int largo) {
			
		ArrayList arrChecked = new ArrayList();
			
		for(int a = 0 ; a < largo ; a++){
				
			String checkActivo = UtilesWeb.Revisa_Valor_CheckBox(request.getParameter("checkbox"+a));
				

			if( checkActivo.equalsIgnoreCase("1")){

				PeticionDTO p = new PeticionDTO();
				
				p.setBiId(new Long(request.getParameter("foliotc"+a)));
				p.setBiNroPeticion(new Long(request.getParameter("petiId_"+a)));
				p.setUsuaId(new Long(request.getParameter("duenoPeticion_"+a)));
				p.setActividadId(request.getParameter("actividadId_"+a));

				arrChecked.add(p);
			}
				
		}
			
		return (arrChecked);
	}
	
}	

