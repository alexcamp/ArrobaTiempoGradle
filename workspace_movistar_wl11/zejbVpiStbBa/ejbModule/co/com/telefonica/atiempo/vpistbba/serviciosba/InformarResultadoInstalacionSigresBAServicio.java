//CR4860 SIGRES - GUSTAVOG - 02/05/2008


package co.com.telefonica.atiempo.vpistbba.serviciosba;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR034S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
import com.telefonica_chile.vpistbba.bitacora.dto.BitacoraDTO;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocal;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocalHome;


/**
 * @author TCS
 */
public class InformarResultadoInstalacionSigresBAServicio extends ServicioBasico {

	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		RecursosBAInterfaces delegate = new RecursosBADelegate();
		PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
		TR034S tr034s = (TR034S) obj[0];		
		delegate.recepcionInformarResultadoInstalacionSigres(tr034s);
		
		try{
			Mensaje_estado_baLocalHome mensajeEstadoBALocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			
			Mensaje_estado_baKey mensajeEstadoBAKey = new Mensaje_estado_baKey(new Long(tr034s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBALocal = mensajeEstadoBALocalHome.findByPrimaryKey(mensajeEstadoBAKey);
			
			
			if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBALocal.getCod_actividad_generadora())){
				if (tr034s.getAction() != null && (tr034s.getAction().equals("0"))||tr034s.getAction().equals("3")){
					Recursos_linea_basicaLocalHome linea_basicaHome= (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
					Recursos_linea_basicaLocal linea_basicaLocal = linea_basicaHome.findByPeticion(new Long(tr034s.getAtiempoRequestNumber()));
					
					if (linea_basicaLocal.getReinstalacion_internet() != null && (linea_basicaLocal.getReinstalacion_internet().indexOf("true") != -1
							|| linea_basicaLocal.getReinstalacion_internet().indexOf("change") != -1)){
						envioReinstalacionInternet(linea_basicaLocal.getPeti_numero());
					}
				}
			}
		}catch(NamingException ex){
			log.debug("Error al buscar mensaje Estado BA: "+ ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error al buscar mensaje Estado BA: "+ ex);
			ex.printStackTrace();
		}catch (TnProcesoExcepcion ex3){
			log.error("Error de proceso de configurar Internet ");
			ex3.printStackTrace();
		}
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO-TCS: MARSHALL TR_013_S (CREAR DTO)

		Object[] obj = new Object[1];
		TR034S tr034s = (TR034S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr034s;
		return obj;
	}

	
	private void envioReinstalacionInternet(Long peti_numero) throws TnProcesoExcepcion{
		
		try{
			ArrayList listadoPsOk = new ArrayList();
			ArrayList listadoPsDown = new ArrayList();
			RecursosBADelegate delegate=new RecursosBADelegate();
			
	        InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
	        ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(peti_numero);
					
	        Recursos_linea_basicaLocalHome linea_basicaHome= (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
			Recursos_linea_basicaLocal linea_basicaLocal = linea_basicaHome.findByPeticion(peti_numero);
			
			if (linea_basicaLocal.getReinstalacion_internet().equals("true-tr030")){
				for (int i= 0; i<listadoPS.size(); i++){
		        	ProductoDTO productoLocal = (ProductoDTO)listadoPS.get(i);
//		        	REQ BA NAKED adicion familia PC/P y PS naked
		        	if ((productoLocal.getIdFaps().intValue() == ComunInterfaces.familiaBandaAncha)
		        			|| (productoLocal.getIdFaps().intValue() == ComunInterfaces.familiaPcBA)
							|| (productoLocal.getIdFaps().intValue() == ComunInterfaces.familiaPcPsBANaked)
							|| (productoLocal.getIdFaps().intValue() == ComunInterfaces.familiaBandaAnchaNaked)){
		        		PsVsOcVO psVsTemp = new PsVsOcVO();
		        		
		        		psVsTemp.setPsId(productoLocal.getId());
		        		psVsTemp.setOpComId(productoLocal.getIdOpComercial());
		        		psVsTemp.setOpComTipo(productoLocal.getTipoOperacionComercial());
		        		psVsTemp.setFaPsId(productoLocal.getIdFaps());
		        		psVsTemp.setCorrelativo(productoLocal.getCorrelativo());
		        		psVsTemp.setOk(productoLocal.isEstaOK());
		        		
		        		
		        		listadoPsOk.add(psVsTemp);
		        	}
		        }
				

				try
				{
					//TODO: 10022010 - Ajuste para asignación correcta de IP_Fija - RETA 
					//TODO: 12032010 - YCP -- Ajuste para cuando viene en una Alta la peticion de ip Fija y Modificaciones.
					
					String psIpFija = VpistbbaConfig.getVariable("PS_IPFIJA");
					long psIPFijaPeticion = 0;
					boolean peticionTipoAlta = false;
					boolean peticionTipoTraslado = false;
					boolean esIPFijaPeticion = false; 
					boolean entroPeticion = false;
					boolean esPsIPFija = false;
					
					Iterator iterador = listadoPsOk.iterator();
					while(iterador.hasNext()){
						PsVsOcVO psTemp= (PsVsOcVO) iterador.next();
					    
						if(psIpFija != null && psIpFija.length() > 0){
							String[] tokens = null;
							log.debug("Los valores de psIpFija: " + psIpFija);
							tokens = psIpFija.split(",");
							for (int i = 0 ; i < tokens.length ; i++) {
								log.debug("se compara: "+ tokens[i] + " con: " + psTemp.getPsId());
								if(tokens[i].equals(String.valueOf(psTemp.getPsId()))){
									psIPFijaPeticion  = psTemp.getPsId().longValue();
									esPsIPFija = true;
									log.debug("es PsIPFija");
								}
							}
						}
//						REQ BA NAKED adicion familia PC y PS naked
						if ( ((psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaBandaAncha)
								|| (psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaPcBA)
								|| (psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaPcPsBANaked)
								|| (psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaBandaAnchaNaked))){
							
							if(psTemp.getOpComTipo().equals(ComunInterfaces.opCoTipoAlta)){
								peticionTipoAlta = true;
								log.debug("es peticionTipoAlta");
							}else if(psTemp.getOpComTipo().equals(ComunInterfaces.opCoTipoAltaTraslado)){
								peticionTipoTraslado = true;
								log.debug("es peticionTipoAltaTraslado");
							}
							
							if (listadoPsOk.size() == 1 && psTemp.getPsId().longValue() == psIPFijaPeticion){
								peticionTipoAlta = false;
								log.debug("no es peticionTipoAlta ni peticionTipoAltaTraslado, porque trae un solo PS de IP Fija "+peti_numero);;
							}
						}
					}
					//End TODO
							
					if (peticionTipoAlta)	{
						log.debug("Entra a la opción peticionTipoAlta = true "+peti_numero);
						entroPeticion = true;
						Iterator iterTemp = listadoPsOk.iterator();
						
						while (iterTemp.hasNext() ){
							//Obtengo el primer PS
							PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
							if (psTemp.getPsId().longValue() != psIPFijaPeticion){
//								REQ BA NAKED adicion familia pc naked
								if ((psTemp.getFaPsId().intValue() == ComunInterfaces.familiaPcBA)|| (psTemp.getFaPsId().intValue() == ComunInterfaces.familiaPcPsBANaked)){
										log.debug("Inicio Actividad Configurar Internet [" + peti_numero.toString() + "]");
										delegate.solicitudConfiguracionSigresBA(peti_numero.toString(),
												"Director de Flujos.Instalacion.Control_Instalacion",
												psTemp,psIPFijaPeticion,false,new Integer(0), null);
										break;	//REQ Cambio recursos Tecnicos BA Dcardena con el break no se genera doble TR
								}
							}
						}
					}else if(peticionTipoTraslado){
						log.debug("Entra a la opción peticionTipoTraslado = true "+peti_numero);
						entroPeticion = true;
						Iterator iterTemp = listadoPsOk.iterator();
						while (iterTemp.hasNext() ){
							//Obtengo el primer PS
							PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
							if (psTemp.getPsId().longValue() != psIPFijaPeticion){
//								REQ BA NAKED adicion familia PC naked
								if ((psTemp.getFaPsId().intValue() == ComunInterfaces.familiaPcBA)||(psTemp.getFaPsId().intValue() == ComunInterfaces.familiaPcPsBANaked) ){
										log.debug("Inicio Actividad Configurar Traslado [" + peti_numero.toString() + "]");
										delegate.solicitudConfiguracionSigresTraslado(peti_numero.toString(),
												"Director de Flujos.Instalacion.Control_Instalacion",
												psTemp,false,new Integer(0), null);
										break;	//REQ Cambio recursos Tecnicos BA Dcardena con el break no se genera doble TR
								}
							}
						}
					}else{
						log.debug("Entra a la opción peticionTipoAlta = false "+peti_numero);
						entroPeticion = true;
						// la unica posibilidad que entre aki es para los cambios producto.
						// pork dos peses invocan la actividad.
						log.debug("Tamaño getPsOk:"+listadoPsOk.size());
						Iterator iterTemp = listadoPsOk.iterator();
						while(iterTemp.hasNext()) {
							PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
							//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
														
							if ((psTemp.getPsId().longValue() != psIPFijaPeticion) 
									|| (listadoPsOk.size() == 1 && psTemp.getPsId().longValue() == psIPFijaPeticion)){
									
								//TODO: 29042010 - Raúl Ernesto Triviño Alvarado - Ajuste para mandar este mensjae en caso que sea un IP fija de baja 
								if (listadoPsOk.size() != 1 && psTemp.getPsId().longValue() != psIPFijaPeticion){
									String opComTipo = psTemp.getOpComTipo();
									if(opComTipo==null)
										continue;
									if(!opComTipo.equals("A") && !opComTipo.equals("ACP"))
										continue;	
								}
								//End TODO
									
								log.debug("Inicio Actividad Configurar Internet Cambio de Plan[" + peti_numero.toString() + "]");
								delegate.solicitudConfiguracionSigresCambioPlan(peti_numero.toString(),
										"Director de Flujos.Instalacion.Control_Instalacion",
										psTemp,psIPFijaPeticion, false,new Integer(0), false, null);
							}
						}
					} 
				 
					
					if (!entroPeticion){
						log.debug("No entro a ninguna petición");
						String m = "onInicioActividadVPI() No se configura internet sigres ps=0";
						log.error(m);
						throw new TnProcesoExcepcion(m);
					}
				} catch (ATiempoAppEx e){
					log.warn("Error en Actividad Configurar Internet Sigres",e);
					throw new TnProcesoExcepcion("Error en Actividad Configurar Internet Sigres", e);
				}
				log.debug("Fin Actividad Configurar Internet Sigres BA [" + peti_numero.toString() + "]");
			
			}else if (linea_basicaLocal.getReinstalacion_internet().indexOf("change") != -1){
				String planNuevo = linea_basicaLocal.getReinstalacion_internet();
				planNuevo = planNuevo.substring(planNuevo.indexOf("-")+1,planNuevo.length());
				Producto_servicio_en_vueloLocal productoEnVueloLocal = null;
				Long psOriginal = new Long(0);
							
				//PASO 4: Aquí agrego el elemento nuevo
				for (int i= 0; i<listadoPS.size(); i++){
					ProductoDTO productoLocal = (ProductoDTO)listadoPS.get(i);
		        	
		        	PsVsOcVO psVsTemp = new PsVsOcVO();
		        		
		        	psVsTemp.setPsId(productoLocal.getId());
		        	psVsTemp.setOpComId(productoLocal.getIdOpComercial());
		        	psVsTemp.setOpComTipo(productoLocal.getTipoOperacionComercial());
		        	psVsTemp.setFaPsId(productoLocal.getIdFaps());
		        	psVsTemp.setCorrelativo(productoLocal.getCorrelativo());
		        	psVsTemp.setOk(productoLocal.isEstaOK());
//		        	REQ BA NAKED adicion familia PC y PS naked
		        	if (psVsTemp.getFaPsId().intValue() == ComunInterfaces.familiaPcBA
		        			|| psVsTemp.getFaPsId().intValue() == ComunInterfaces.familiaBandaAncha
							|| psVsTemp.getFaPsId().intValue() == ComunInterfaces.familiaBandaAnchaNaked
							|| psVsTemp.getFaPsId().intValue() == ComunInterfaces.familiaPcPsBANaked){
		        		
		        		Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome) HomeFactory.getHome (Producto_servicio_en_vueloLocalHome.JNDI_NAME);				
				        Collection novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(peti_numero, ComunInterfaces.BA_EN_VUELO_OLD);		        		
		        		
				        if (!novedadEnVuelo.isEmpty()){
				        	Iterator iterPss = novedadEnVuelo.iterator();
				        	String codigoPsAnt = new String();
				        	
				        	while (iterPss.hasNext ()){
								Producto_servicio_en_vueloLocal psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
								
								codigoPsAnt = psevDTO.getPs_id().toString();
				        	}
				        	psOriginal = psVsTemp.getPsId();
				        	psVsTemp.setPsId(new Long(codigoPsAnt));
				        }else{
				        	psOriginal = psVsTemp.getPsId();
				        }
				        
				        listadoPsDown.add(psVsTemp);
		        	}
		        }
				
				PsVsOcVO psTemp = new PsVsOcVO();
				PsVsOcVO psOld = new PsVsOcVO();
				
				psOld = (PsVsOcVO) listadoPsDown.iterator().next();
					
				productoEnVueloLocal = delegate.almacenaProductoPeticionEnVuelo(new Integer(peti_numero.toString()),
							new Integer(planNuevo), ComunInterfaces.BA_EN_VUELO, psOld.getPsId().toString());
			
		        
				Operacion_comercialLocalHome operacion_ComercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
				productoEnVueloLocal.setPs_id(psOriginal);
				psOld.setPsId(psOriginal);
				Operacion_comercialKey OCKey = new Operacion_comercialKey(new Long(productoEnVueloLocal.getOpco_id().toString()));
				Operacion_comercialLocal operacion_comercialLocal = operacion_ComercialHome.findByPrimaryKey(OCKey); 
		        
				Producto_servicioLocalHome ProdServicioHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioKey PSKey = new Producto_servicioKey(new Long(planNuevo));
				Producto_servicioLocal ProdServicioLocal = ProdServicioHome.findByPrimaryKey(PSKey);
		        
				psTemp.setPsId(new Long(productoEnVueloLocal.getPs_id().toString()));
				psTemp.setOpComId(new Long(productoEnVueloLocal.getOpco_id().toString()));
				psTemp.setOpComTipo(operacion_comercialLocal.getOpco_tipo());
				productoEnVueloLocal.setPs_id(new Long(planNuevo));
				//psTemp.setFaPsId(new Long(ProdServicioLocal.getFamilia_producto_servicio().getFaps_codigo()));
		         
				delegate.solicitudConfiguracionSigresCambioPlanEnVuelo(peti_numero.toString(),"Director de Flujos.Instalacion.Control_Instalacion",psTemp,false,new Integer(-999), psOld);
			}
		}catch(NamingException ex){
			log.error("Error de instacia del bean: ");
			ex.printStackTrace();
		}catch(FinderException ex2){
			log.error("Error de consulta del bean: ");
			ex2.printStackTrace();
		}catch(ATiempoAppEx ex){
			log.error("Error en el proceso de reinstalación de internet ");
			ex.printStackTrace();
		}
	}
}