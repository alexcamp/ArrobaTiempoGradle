/*
 * Creado el 25/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba.naked.ejb;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CamaraKey;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocal;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scKey;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapKey;
import co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapLocal;
import co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaKey;
import co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaLocal;
import co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.ElementoLocal;
import co.com.telefonica.atiempo.ejb.eb.ElementoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ElementoNoSerializadoLocal;
import co.com.telefonica.atiempo.ejb.eb.ElementoNoSerializadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_homologacionKey;
import co.com.telefonica.atiempo.ejb.eb.Estado_homologacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_homologacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ModemKey;
import co.com.telefonica.atiempo.ejb.eb.ModemLocal;
import co.com.telefonica.atiempo.ejb.eb.ModemLocalHome;
import co.com.telefonica.atiempo.ejb.eb.MunicipioKey;
import co.com.telefonica.atiempo.ejb.eb.MunicipioLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqKey;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocal;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baKey;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.SegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocalHome;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoKey;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocal;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EAccessTime;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EAdressData;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EContactData;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701ECoordinatedActions;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701ECustomer;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EDateData;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EMassiveBreakdowns;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701ENotes;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EProduct;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EProductService;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701ETechnicalData;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711SMaterials;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioInterfaz;
import co.com.telefonica.atiempo.vpistbba.servicios.CreaActuacionSCMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazKey;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal;

/**
 * Bean implementation class for Enterprise Bean: AgendaServicio
 */
public class AgendaServicioBean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter implements javax.ejb.SessionBean, AgendaServicioInterfaz {

	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	private DBManager dbSeq ;
	private SimpleDateFormat df ;
	private static int cantDecosDesinsSTD = 0;
	private static int cantDecosDesinsHD = 0;
	private static int cantDecosDesinsPVR = 0;
	
	
	public void setSessionContext (SessionContext ctx)
	throws EJBException, RemoteException
	{
		super.setSessionContext (ctx) ;
		// Creacion de DataSource
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
		
		df= new SimpleDateFormat("yyyyMMddHHmmss");
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioInterfaz#enviarCreacionActuacion(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarCreacionActuacion(Long idPeticion, Timestamp fechaReagendamiento, String tipoOC, String codActividad, ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		log.info("Se ingresa al método de envío de mensaje de alta de actuación: TR701E");
		try{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
			Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome)HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Recursos_baLocalHome recursos_baLocalHome = 	(Recursos_baLocalHome) HomeFactory.getHome( Recursos_baLocalHome.JNDI_NAME);
			Mensaje_estado_baLocalHome mensaje_estado_baLocalHome = 	(Mensaje_estado_baLocalHome) HomeFactory.getHome( Mensaje_estado_baLocalHome.JNDI_NAME);
			LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome)HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			Agenda_scLocalHome agenda_SCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			SubsegmentoLocalHome subsegmentoLocalHome = (SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
			SegmentoLocalHome segmentoLocalHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME); 
			Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Producto_servicio_peticionLocalHome productoServicioPeticionLocalHome = (Producto_servicio_peticionLocalHome)HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			ElementoLocalHome elementoLocalHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
			Ps_Tipo_EqLocalHome psTipoEquipoLocalHome = (Ps_Tipo_EqLocalHome)HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
			Elemento_agenda_scLocalHome elementoAgendSCLocalHome = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
			Mensaje_agenda_scLocalHome mensajeAgendaSCLocalHome = (Mensaje_agenda_scLocalHome) HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
			Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
			
			RecursosBADelegate recursosbaDelegate = new RecursosBADelegate();
		
			SimpleDateFormat formatterRegistro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Recursos_linea_basicaLocal rlb = null;
			try{
				rlb = recursos_linea_basicaLocalHome.findByPeticion(idPeticion);
				Recursos_baKey Recursos_baKey=new Recursos_baKey();
			}
		    catch (FinderException e) {
				log.debug("No encontró recursos LB en la petición en alta actuación de Agenda SC: "+e.toString());
			}
			
			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
			Collection equipos = peticionLocal.getElemento_peticion();
			Collection psPeticion=peticionLocal.getProducto_servicio_peticion();
			Iterator psPeticionIt=null;
			int contadorDecos=0;
			String numDecos="0";
			boolean solucionTV = false;
			boolean esTraslado = false;
			String tipoDeco="";
			boolean tieneHD=false;
			boolean tienePVR=false;
			boolean tieneSTD=false;
			boolean puertosModificados = true;
			
			for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
				Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
				if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV
						||familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV
						||familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
					log.debug("El ps pertenece a una familia de tipo Deco");
					contadorDecos++;
					if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
						tieneHD=true;						
						log.debug("Tiene deco HD");
					}else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV){
						tienePVR=true;						
						log.debug("Tiene deco PVR");
					}
					}
					else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV){
						tieneSTD=true;
						log.debug("Tiene deco STD");
				}
				if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaPaqueteTV)
					solucionTV = true;
				if(familia_producto_servicioKey.faps_id.intValue() != ComunInterfaces.altaMigracionPS &&
						familia_producto_servicioKey.faps_id.intValue() != ComunInterfaces.bajaMigracionPS)
					esTraslado = true;
			}
			
			if(tieneHD){
				tipoDeco=ComunInterfaces.desHCDecoHDTV;
			}else if(tienePVR){
				tipoDeco=ComunInterfaces.desHCDecoPVR;
			}else if(tieneSTD){
				tipoDeco=ComunInterfaces.desHCDecoSTD;
			}
			log.debug("El tipo de deco resultante es: "+tipoDeco);
			
			Peticion_atisLocal peticion_atisLocal=peticionLocal.getFk_01_pet_atis();
			String infoContactYMedia="";
			Collection agrupacionAtisList=peticion_atisLocal.getAgrupacion_atis();
			Iterator agrupacionAtisListIt=null;
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			
			ArrayList listaSubpeticiones=peticionesDelegate.obtenerSubpeticionesDesdePeticion(idPeticion);
			Iterator listaSubpeticionesIt=null;
			
			CaracteristicaPSLocal caracteristicaPSEquipo=null;
			Subpeticion_caracteristicasLocal caracteristicaPSCuota=null;
			
			boolean isCaracteristica = false;
			int plazoFinanciamiento =0;
			List caracteristicas = new ArrayList();
			PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
			String[] id_ps = incidentesDelegate.recuperarParametroFromPropertiesBD("PSIDS_CAMARA_MONITOREO").split(",");
			for(listaSubpeticionesIt=listaSubpeticiones.iterator();listaSubpeticionesIt.hasNext();){
				Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal)listaSubpeticionesIt.next();
				CaracteristicaPSLocal caracteristicaPS = recursosbaDelegate.obtenerProductoServicio(subpeticion_atisLocal);
				//Se sacan las caracteristicas pertenecientes a cámaras
				for (int i = 0; i < id_ps.length; i++) {
					Long idPsCamara = new Long(id_ps[i]);
					if(caracteristicaPS!= null && idPsCamara.equals(caracteristicaPS.getPsPeticion())){
						caracteristicas.add(caracteristicaPS);
						log.debug("caracteristica camara agregada: "+caracteristicaPS.getCaracteristicaPS()+" PS_PETICION: "+caracteristicaPS.getPsPeticion()+" PS_ID: "+ caracteristicaPS.getPsId());
						break;
					}
				}
				
				if(caracteristicaPSEquipo==null)
					caracteristicaPSEquipo=recursosbaDelegate.obtenerProductoServicio(subpeticion_atisLocal);
				Subpeticion_caracteristicasLocalHome subpeticionCaracteristicasLocalHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
				Subpeticion_caracteristicasKey sck=new Subpeticion_caracteristicasKey(new Long(ComunInterfaces.CODIGO_CARACTERISTICA_CUOTAS_QW),(Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey());
				try{
					if(!isCaracteristica)
						caracteristicaPSCuota=subpeticionCaracteristicasLocalHome.findByPrimaryKey(sck);
					
					if(caracteristicaPSCuota.getCod_val_crc_cd().intValue()>0 ){
						isCaracteristica = true;
						plazoFinanciamiento = caracteristicaPSCuota.getCod_val_crc_cd().intValue();
					}
					
				}catch(FinderException e){
					log.debug ("La subpeticion "+((Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey()).cod_sub_cd +" no tiene la caracteristica de cuotas"+e.getMessage());
				}
				if(subpeticion_atisLocal!=null){  
					if(subpeticion_atisLocal.getObs_sub_ds()!=null&&!subpeticion_atisLocal.getObs_sub_ds().equals("")){
						infoContactYMedia=subpeticion_atisLocal.getObs_sub_ds();
					}
				}
			}
			Long idPsCaracteristica = null;
			Long idPs = null;
			Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
			TR701EProduct tr701EProduct=new TR701EProduct();
			tr701EProduct.setAtisRequestNumber(petAtisK.cod_pet_cd.toString());
			tr701EProduct.setProductCode(new Long(0));//ME FALTA ESTE, también está en el catálogo de agenda.
			if(caracteristicaPSEquipo!=null){
				Producto_servicioLocal ps=caracteristicaPSEquipo.getProducto_servicio();
				tr701EProduct.setNombrePSProducto(ps.getPs_nombre());
				tr701EProduct.setCaracteristicaProducto(caracteristicaPSEquipo.getCaracteristicaPS());
				idPsCaracteristica = caracteristicaPSEquipo.getPsId();
				idPs = caracteristicaPSEquipo.getPsPeticion();
				tr701EProduct.setProductCode(idPsCaracteristica);
			}
			if(caracteristicaPSCuota!=null && caracteristicaPSCuota.getCod_val_crc_cd()!=null){
				tr701EProduct.setPlazoFinanciacion(plazoFinanciamiento);
			}
			//Yumbleiner CR 6842 Agregar IdPCTV en la tr-701
			tr701EProduct.setIdPcTv("0");
			if(peticionLocal.getNum_ide_nu_tv()!=null && peticionLocal.getNum_ide_nu_tv().length()>0){
				tr701EProduct.setIdPcTv(peticionLocal.getNum_ide_nu_tv());
			}
//			Se extrae el email de contacto y el medio de contacto.
			String emailContacto="";
			String medioDeContacto="";
			String telefonoContacto="";
			String celularContacto="";
			if(!infoContactYMedia.equals("")){
				String[] datosGrupo=infoContactYMedia.split("\\|");
				if(datosGrupo.length == 2){						
					medioDeContacto=datosGrupo[0];
					if(medioDeContacto.equals("2")){
						emailContacto=datosGrupo[1];
					}else if(medioDeContacto.equals("1")){
						celularContacto=datosGrupo[1];
					}else if(medioDeContacto.equals("3")){
						telefonoContacto=datosGrupo[1];
					}else if(medioDeContacto.equals("4")){
						celularContacto=datosGrupo[1];
					}
				}else{
					//Si infoContactYMedia no tiene nada, entonces se hace lógica para extraer el medio de contacto a través del teléfono de contacto.
					String telefono="";
					if(peticionLocal.getTel_cot_ds()!=null){
						telefono=peticionLocal.getTel_cot_ds();
					}			
					if(telefono.matches("3"+"\\d{9}")){
						log.debug("Es celular, el contact media será SMS...");
						medioDeContacto=CONTACT_MEDIA_SMS;
						celularContacto=telefono;
					}else if(telefono.matches("\\d{6}||\\d{7}||\\d{8}||\\d{9}")){
						log.debug("Es un fijo de 7,8 o 9 dígitos");
						medioDeContacto=CONTACT_MEDIA_TELEFONO;
						telefonoContacto=telefono;
					}else if(telefono.matches("[^3]\\d{9}")){
						log.debug("Es un fijo de 10 caracteres sin 3 al comienzo porque sería celular...");
						medioDeContacto=CONTACT_MEDIA_TELEFONO;
						telefonoContacto=telefono;
					}else{
						medioDeContacto=CONTACT_MEDIA_TELEFONO;
						log.debug("No es ni fijo ni celular");
					}
				}					
			}else{
				//	Si no viene info separada por |, entonces se hace lógica para extraer el medio de contacto a través del teléfono de contacto.
				String telefono="";
				if(peticionLocal.getTel_cot_ds()!=null){
					telefono=peticionLocal.getTel_cot_ds();
				}			
				if(telefono.matches("3"+"\\d{9}")){
					log.debug("Es celular, el contact media será SMS...");
					medioDeContacto=CONTACT_MEDIA_SMS;
					celularContacto=telefono;
				}else if(telefono.matches("\\d{6}||\\d{7}||\\d{8}||\\d{9}")){
					log.debug("Es un fijo de 6,7,8 o 9 dígitos");
					medioDeContacto=CONTACT_MEDIA_TELEFONO;
					telefonoContacto=telefono;
				}else if(telefono.matches("[^3]\\d{9}")){
					log.debug("Es un fijo de 10 caracteres sin 3 al comienzo porque sería celular...");
					medioDeContacto=CONTACT_MEDIA_TELEFONO;
					telefonoContacto=telefono;
				}else{
					medioDeContacto=CONTACT_MEDIA_TELEFONO;
					log.debug("No es ni fijo ni celular");
				}
			}
//			Se extraen datos de ADSL
			InformacionAdslDTO adsl=recursosbaDelegate.obtenerDatosAdsl(idPeticion);
			
			if (adsl == null){
				adsl = recursosbaDelegate.obtenerDatosActualAdsl(idPeticion);
			}else if (adsl != null && adsl.getDirecIpDslam()== null){
				adsl = recursosbaDelegate.obtenerDatosActualAdsl(idPeticion);
			}else if (adsl != null && adsl.getDirecIpDslam() != null && adsl.getDirecIpDslam().length() <= 0){
				adsl = recursosbaDelegate.obtenerDatosActualAdsl(idPeticion);
			}
			if (adsl != null && adsl.getFatherEmail() != null){
				adsl.setUsuarioAcc(adsl.getFatherEmail());
			}
			
//			Se obtienen los PS de la petíción
			String listaPs="";
			String listaTematicosTV="";
			String emailCaracteristicas="";
			Collection productoServicioPetList=peticionLocal.getProducto_servicio_peticion();
			Iterator listaProductoServicioPetIt=null;
			
			Collection recursosBACollection = peticionLocal.getRecursos_ba();
			for (Iterator recursosBAIterator = recursosBACollection.iterator();recursosBAIterator.hasNext();){
				Recursos_baLocal recursosBaLocal = (Recursos_baLocal)recursosBAIterator.next();
				
				if (recursosBaLocal.getPort_modification_flag()!= null && !recursosBaLocal.getPort_modification_flag().equalsIgnoreCase("si")){
					puertosModificados = false;
					break;
				}
			}
			for(listaProductoServicioPetIt=productoServicioPetList.iterator();listaProductoServicioPetIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal)listaProductoServicioPetIt.next();
				
				//Se extrae la info de email
				Subpeticion_atisLocal subPet = producto_servicio_peticionLocal.getFk_01_subp_atis();
				Iterator iterCarac=subPet.getSubpeticion_caracteristicas().iterator();

				while (iterCarac.hasNext())
				{
					Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
					Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
					Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
					if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue()){
						log.info("Información : Se obtuvo Father Email " + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
						emailCaracteristicas=subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
						break;
					}
				}

				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Producto_servicioKey  producto_servicioKey=(Producto_servicioKey)producto_servicioLocal.getPrimaryKey();
				
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey)producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey();
				if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaTematicoTV){
					listaTematicosTV+=" " + producto_servicioLocal.getPs_nombre();
					
				}
				
				if (puertosModificados){
					listaPs = listaPs+producto_servicioLocal.getPs_nombre()+"-";			
				}else{
					Familia_producto_servicioLocal familiaProductoServicioLocal = producto_servicioLocal.getFamilia_producto_servicio();
					Familia_producto_servicioKey familiaProductoServicioKey = (Familia_producto_servicioKey)familiaProductoServicioLocal.getPrimaryKey();
					
						listaPs = listaPs+producto_servicioLocal.getPs_nombre()+"-";
				}
			}
			if (listaPs.length()>0){
				listaPs = listaPs.substring(0,listaPs.length()-1);
			}

			TR701E tr701e = new TR701E();
			tr701e.setTransactionType("");
		
			TR701EAccessTime tr701EAccessTime = new TR701EAccessTime();
			
			if (peticionLocal.getJornada_agnd_sc()!= null  && peticionLocal.getJornada_agnd_sc().length()>0){
				tr701EAccessTime.setJorney(peticionLocal.getJornada_agnd_sc());
			}else{
				log.debug("No se encuentra especificada la jornada se setea 14.00.00 por defecto para evitar conflicots con agenda SC");
				tr701EAccessTime.setJorney("14.00.00");
			}

			TR701EContactData tr701EContactData = new TR701EContactData();
			tr701EContactData.setContactCellPhone(celularContacto);
			tr701EContactData.setContactEmail(emailContacto);
			tr701EContactData.setContactMedia(medioDeContacto);
			tr701EContactData.setContactName(peticionLocal.getNom_ds());
			tr701EContactData.setContactPhone(telefonoContacto);
			tr701EContactData.setDatosAgendamiento(infoContactYMedia);
			
//			Estos campos de dependence no son necesarios en atiempo.
			TR701ECoordinatedActions tr701ECoordinatedActions = new TR701ECoordinatedActions();
			tr701ECoordinatedActions.setDependeceId(new Long(0));
			tr701ECoordinatedActions.setDependenceType("");

			TR701EDateData tr701EDateData = new TR701EDateData();
			tr701EDateData.setBreakdownDate(df.format(peticionLocal.getPeti_fecha_ingreso()));
			tr701EDateData.setBreakdownCommitmentDate("");
			
			TR701EMassiveBreakdowns tr701EMassiveBreakdowns=new TR701EMassiveBreakdowns();
			tr701EMassiveBreakdowns.setBreakdownMassiveType("");
			tr701EMassiveBreakdowns.setFinalRange("");
			tr701EMassiveBreakdowns.setInitialRange("");

			TR701ETechnicalData tr701ETechnicalData = setTechnicalData(peticionLocal,numDecos,listaTematicosTV,idPeticion,adsl,rlb,tipoDeco, recursosbaDelegate);
			
			Collection decosTarjetaPeticionList = peticionLocal.getDeco_tarjeta();
			Collection listaEquipos = new ArrayList();
			listaEquipos =obtenerListaEquipos(decosTarjetaPeticionList, listaEquipos, deco_tar_inf_sapLocalHome, idPeticion, recursosbaDelegate, peticionLocal, psTipoEquipoLocalHome, elementoAgendSCLocalHome, elementoLocalHome);
			
			tr701e.setEquipments(listaEquipos);
			

			tr701e.setAccessTime(tr701EAccessTime);
			tr701e.setActionName(peticionLocal.getPeti_id_instancia());//Es la familia o grupo de familias (LBBA,BA,TV,etc) 
			tr701e.setAdressData(setAdressData(peticionLocal));
			tr701e.setAffectedArea("20");//Si es planta interna o externa, Se pone 20 por ahora (planta interna)
			tr701e.setCodeAction("");//Código tipo tarea, depende de catálogo no definido todavía
			tr701e.setCodeScheduleType(peticionLocal.getTica_id());//Código tipo actuación
			tr701e.setContactData(tr701EContactData);
			tr701e.setCoordinatedActions(tr701ECoordinatedActions);
			tr701e.setCustomer(setCustomer(peticionLocal,codActividad,peticionesDelegate, emailCaracteristicas, subsegmentoLocalHome, segmentoLocalHome));
			tr701e.setDateData(tr701EDateData);
			tr701e.setDescriptionAction("");//Inicialmente vacío
			tr701e.setDestination("ESB");
			
			String tmpCarac;
			for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
				tmpCarac = recursosbaDelegate.obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (VpistbbaConfig.getVariable("CODPROJECT")));
				if (tmpCarac == null){
					tr701e.setProjectCode("");
				}else{
					tr701e.setProjectCode(tmpCarac);
					break;
				}
			}

			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			tr701e.setId(IdCorrelativo.toString());
			
			Date dateAhora=new Date();
			if(fechaReagendamiento!=null && fechaReagendamiento.before(dateAhora)){
				dateAhora=fechaReagendamiento;
			}
			
			Timestamp timestampAhora=new Timestamp(dateAhora.getTime());
			insertarAgendamiento(tr701e.getIdSchedule(), peticionLocal,idPeticion, tipoOC, tr701e, fechaReagendamiento, agenda_SCLocalHome, dateAhora);
			
			tr701e.setIdSystemOrigin("ATIEMPO");
			tr701e.setInterfaz("ACT_ALTA");
			tr701e.setMassiveBreakdowns(tr701EMassiveBreakdowns);
			tr701e.setNotes(setNotes(agrupacionAtisList, psPeticion, peticionLocal,agrupacionAtisListIt, psPeticionIt, recursosbaDelegate, idPeticion, infoContactYMedia));
			tr701e.setProduct(tr701EProduct);
			tr701e.setSource("ATIEMPO");
			tr701e.setVersion("1.0");
			
			if (listaPs.length()>240){
				listaPs = listaPs.substring(0, 240);
			}
				
			tr701e.setSummary(listaPs);//Todos los ps's
			log.debug("Se informa a Agenda que la red es de reuso (par dedicado) : " + tr701ETechnicalData.getParDedicated());
			tr701e.setTechnicalData(tr701ETechnicalData);
			
			tr701e.setProductsService(getProductsService(peticionLocal, peticionesDelegate, peticionFlujoLocalHome, idPeticion, productoServicioPeticionLocalHome, idPsCaracteristica, id_ps, caracteristicas, idPs, tipoOC, puertosModificados));
			
			Mensaje_agenda_scLocal mensajeAgendaSCLocal = mensajeAgendaSCLocalHome.create(IdCorrelativo);
			mensajeAgendaSCLocal.setCod_actividad_generadora(codActividad);
			mensajeAgendaSCLocal.setPeti_numero(idPeticion);
			mensajeAgendaSCLocal.setId_agenda(tr701e.getIdSchedule());
			mensajeAgendaSCLocal.setTipo_mensaje(ComunInterfaces.TR701);
			
			Date dateRegistro=new Date();
			Timestamp timestampRegistro=new Timestamp(dateRegistro.getTime());
			mensajeAgendaSCLocal.setFecha_envio(formatterRegistro.format(timestampRegistro));
			
			CreaActuacionSCMQ creacionActuacionSCMQ = new CreaActuacionSCMQ();
			creacionActuacionSCMQ.enviarMensaje(tr701e);
		}
		catch (FinderException e) {
			log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
		}
		catch (ATiempoAppEx e) {
			log.error("Error de aplicación de atiempo en alta actuación de Agenda SC: ",e);
		}catch (NamingException e) {
			log.error("Error buscando objeto de BD alta actuación de Agenda SC: ",e);
		}
		
		catch (Exception e){
			log.error("Error: RecursosBABean: creacionActuacionAgendaSC. ",e);
		}
	}

	/**
	 * @param peticionLocal
	 * @param peticionesDelegate
	 * @param peticionFlujoLocalHome
	 * @param idPeticion
	 * @param productoServicioPeticionLocalHome
	 * @param idPsCaracteristica
	 * @param id_ps
	 * @param caracteristicas
	 * @param idPs
	 * @param tipoOC
	 * @param puertosModificados
	 * @return
	 */
	private Collection getProductsService(PeticionLocal peticionLocal, PeticionesDelegate peticionesDelegate, Peticion_flujoLocalHome peticionFlujoLocalHome, Long idPeticion, Producto_servicio_peticionLocalHome productoServicioPeticionLocalHome, Long idPsCaracteristica, String[] id_ps, List caracteristicas, Long idPs, String tipoOC, boolean puertosModificados) {
		// TODO Apéndice de método generado automáticamente
		
		Collection productsService = new ArrayList();
		try{
			RecursosBADelegate recursosBA = new RecursosBADelegate();
			Collection peticionFlujo = peticionLocal.getPeticion_flujo();
			
			Long ocAutoInstalacion=new Long(peticionesDelegate.recuperarParametroFromPropertiesBD(OPCO_AUTOINSTALACION));
			Collection psActividadCollection = peticionFlujoLocalHome.findByActividad(idPeticion,new Integer(ID_ACTIVIDAD_INSTALACION),new Integer(ID_ACTIVIDAD_DESINSTALACION));
			boolean tieneCambioProd = 	recursosBA.validaBajAltaMigrDeco(peticionLocal.getProducto_servicio_peticion());
			int psCamara = 0;
			Long idPsCaracteristicaTemp = idPsCaracteristica;
			for (Iterator psActividadIterator=psActividadCollection.iterator(); psActividadIterator.hasNext();){
				Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal) psActividadIterator.next();
				boolean psConQuiebre = false;
				int famOpLocal = 0;
			
				Operacion_comercialKey opcoKey = (Operacion_comercialKey)peticionFlujoLocal.getFk_opco_2_pefl().getPrimaryKey();
				Collection productosCollection = productoServicioPeticionLocalHome.findByPetiNumeroPsYOpCo(idPeticion, peticionFlujoLocal.getPrse_id(), opcoKey.opco_id);
				for (Iterator productosIterator = productosCollection.iterator(); productosIterator.hasNext(); ){
					Producto_servicio_peticionLocal productoLocal =  (Producto_servicio_peticionLocal)productosIterator.next();
					idPsCaracteristica = idPsCaracteristicaTemp;
					TR701EProductService productService = new TR701EProductService();
					
					psCamara1: for (int i = 0; i < id_ps.length; i++) {
						Long idPsCamara = new Long(id_ps[i]);
						log.debug("idPsCamara: "+idPsCamara+", productoLocal.getPsId:"+productoLocal.getPsId());
						if(idPsCamara.equals(productoLocal.getPsId())){
							if(caracteristicas!=null && caracteristicas.size()>psCamara){
								CaracteristicaPSLocal caracteristicaPS = (CaracteristicaPSLocal)caracteristicas.get(psCamara);
								idPsCaracteristica = caracteristicaPS.getPsId();
								log.debug("idPsCaracteristica: "+ idPsCaracteristica);
								log.debug("psCamara: "+ psCamara);
								psCamara++;
								break psCamara1;
							}
						}
					}
					
					famOpLocal = productoLocal.getFamiliaKey().faps_id.intValue();		
					Collection estadoPSCollection = productoLocal.getEstado_ps_peticion();
					Familia_producto_servicioKey familia_producto_servicioKey =  productoLocal.getFamiliaKey();
					Operacion_comercialLocal operacion_comercialLocal = productoLocal.getOperacion_comercial();
					Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey();
					
					if(estadoPSCollection != null && estadoPSCollection.size()>0){
						for (Iterator estadoPSIterator = estadoPSCollection.iterator(); estadoPSIterator.hasNext();){
							Estado_ps_peticionLocal estadoPSLocal = (Estado_ps_peticionLocal)estadoPSIterator.next();
							log.debug("Petición tiene PS en estado_ps_peticon se valda que no se encuentren quebrados");
							if (estadoPSLocal.getCod_estado_cierre() != null && estadoPSLocal.getCod_estado_cierre().intValue() == ComunInterfaces.estadoCierreError){
								psConQuiebre = true;
								Long familiaPsId = familia_producto_servicioKey.faps_id;
//								REQ BA NAKED adicion familia PC naked
								if((familiaPsId.intValue()==ComunInterfaces.familiaPcBA && operacion_comercialKey.opco_id.longValue() == ocAutoInstalacion.longValue())
										||(familiaPsId.intValue()==ComunInterfaces.familiaPcPsBANaked && operacion_comercialKey.opco_id.longValue() == ocAutoInstalacion.longValue())){
									productService.setPsId(productoLocal.getPsId().toString());
									productService.setOperationComercial(OC_REVERSA_AUT_INST);
									productsService.add(productService);
								}
								
							}else{
								
								//productService.setPsId(productoLocal.getPsId().toString());
								productService.setPsId(peticionFlujoLocal.getPrse_id().equals(idPs) ? idPsCaracteristica.toString() : peticionFlujoLocal.getPrse_id().toString());
								
								if( tipoOC.equals("esPGACS") ){
									productService.setOperationComercial(OC_PGACS);
								}else if(tipoOC.equals("esReversaAutoInst")){
									productService.setOperationComercial(OC_REVERSA_AUT_INST);
								}else{
									productService.setOperationComercial(operacion_comercialKey.opco_id.toString());
									//productService.setOperationComercial(opcoKey.opco_id.toString());
								}
								
								//REQ migracion deco Cesar Remigio
							
								if(peticionLocal.getTica_id().equals("P") 
										&& tieneCambioProd && (familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoHDTV
												|| familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoPVRTV
												|| familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoTV)){
								
									if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoHDTV
											&& (operacion_comercialLocal.getOpco_tipo().equals("B") || operacion_comercialLocal.getOpco_tipo().equals("BCP")))
										cantDecosDesinsHD++;
									if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoPVRTV
											&& (operacion_comercialLocal.getOpco_tipo().equals("B") || operacion_comercialLocal.getOpco_tipo().equals("BCP")))
										cantDecosDesinsPVR++;
									if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoTV
											&& (operacion_comercialLocal.getOpco_tipo().equals("B") || operacion_comercialLocal.getOpco_tipo().equals("BCP")))
										cantDecosDesinsSTD++;
									if(operacion_comercialLocal.getOpco_tipo().equals("A") || operacion_comercialLocal.getOpco_tipo().equals("ACP"))
										productService.setAccion("I");
									if(operacion_comercialLocal.getOpco_tipo().equals("B") || operacion_comercialLocal.getOpco_tipo().equals("BCP"))
										productService.setAccion("D");
									//productsService.add(productService);
								}
//								REQ BA Naked 06/04/2015
								productService.setAccion(recursosBA.accionModemNaked(productService.getOperationComercial(),famOpLocal));
								//FIN BA Naked
								if (puertosModificados){
									productsService.add(productService);
//									REQ BA NAKED adicion familia PC naked
								}else if(famOpLocal != familiaPcBA 
										&& famOpLocal != familiaPcPsBANaked){
										productsService.add(productService);	
								}
								
							}
						}
					}else{
						log.debug("Peticion con todos los ps en estado OK con OC "+famOpLocal);
						//productService.setPsId(productoLocal.getPsId().toString());
						productService.setPsId(peticionFlujoLocal.getPrse_id().equals(idPs) ? idPsCaracteristica.toString() : peticionFlujoLocal.getPrse_id().toString());
						if( tipoOC.equals("esPGACS") ){
							productService.setOperationComercial(OC_PGACS);
						}else if(tipoOC.equals("esReversaAutoInst")){
							productService.setOperationComercial(OC_REVERSA_AUT_INST);
						}else{
							productService.setOperationComercial(operacion_comercialKey.opco_id.toString());
						}
//						REQ migracion deco Cesar Remigio
						
							if(peticionLocal.getTica_id().equals("P") 
									&& tieneCambioProd && (familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoHDTV
											|| familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoPVRTV
											|| familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoTV)){
							log.debug("Trae Equipo de TV para un proceso de posventa");
								if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoHDTV
										&& (operacion_comercialLocal.getOpco_tipo().equals("B") || operacion_comercialLocal.getOpco_tipo().equals("BCP")))
									cantDecosDesinsHD++;
								if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoPVRTV
										&& (operacion_comercialLocal.getOpco_tipo().equals("B") || operacion_comercialLocal.getOpco_tipo().equals("BCP")))
									cantDecosDesinsPVR++;
								if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaDecoTV
										&& (operacion_comercialLocal.getOpco_tipo().equals("B") || operacion_comercialLocal.getOpco_tipo().equals("BCP")))
									cantDecosDesinsSTD++;
								if(operacion_comercialLocal.getOpco_tipo().equals("A") || operacion_comercialLocal.getOpco_tipo().equals("ACP"))
									productService.setAccion("I");
								if(operacion_comercialLocal.getOpco_tipo().equals("B") || operacion_comercialLocal.getOpco_tipo().equals("BCP"))
									productService.setAccion("D");
							}
						if (puertosModificados){
							log.debug("Se ingresa nuevo producto "+productService.getPsId());
							productsService.add(productService);
//							REQ BA NAKED adicion familia PC naked
						}else if(famOpLocal != familiaPcBA
								&& famOpLocal != familiaPcBANaked){
							log.debug("Se ingresa nuevo producto "+productService.getPsId());
								productsService.add(productService);	
						}
						//fin mfmendez
					}
				}
			}
		}catch (FinderException e) {
			log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
		}
		return productsService;
	}

	/**
	 * @param idSchedule
	 * @param peticionLocal
	 * @param idPeticion
	 * @param tipoOC
	 * @param tr701e
	 * @param fechaReagendamiento
	 * @param agenda_SCLocalHome
	 * @param dateAhora
	 */
	private void insertarAgendamiento(String idSchedule, PeticionLocal peticionLocal, Long idPeticion, String tipoOC, TR701E tr701e, Timestamp fechaReagendamiento, Agenda_scLocalHome agenda_SCLocalHome, Date dateAhora) {
		// TODO Apéndice de método generado automáticamente
		try{
			if(tipoOC.equals("esPGACS")||tipoOC.equals("esReversaAutoInst")){
				tr701e.setIdSchedule("AP"+idPeticion.toString()+"-"+df.format(fechaReagendamiento));
				tr701e.setScheduleDate(df.format(fechaReagendamiento));
			
			Agenda_scLocal agenda_SCLocal=agenda_SCLocalHome.create(tr701e.getIdSchedule().toString());
			agenda_SCLocal.setPeticion(peticionLocal);
			agenda_SCLocal.setEstado(new Integer(1));
			agenda_SCLocal.setPeti_numero(idPeticion);
			agenda_SCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			}else if(tipoOC.equals("esActuacionCCF")){
				try{

					Collection agendamientos = agenda_SCLocalHome.findByPetiNumero(idPeticion);
					for (Iterator agendamientoIterator = agendamientos.iterator(); agendamientoIterator.hasNext();){
						Agenda_scLocal agendaSCLocal = (Agenda_scLocal)agendamientoIterator.next();
						if (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
								|| agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
							/*mfmendez*/
							Agenda_scKey agendaScKey = (Agenda_scKey) agendaSCLocal.getPrimaryKey();
							String [] campos = agendaScKey.id_actuacion.split("-");
							/*FIN mfmendez*/
							if (df.format(agendaSCLocal.getFecha_mod()) != campos[1]){
								log.debug("El Codigo de actuacion de CCF  : " + agendaSCLocal.getPrimaryKey().toString());
								tr701e.setIdSchedule(agendaScKey.id_actuacion);
								tr701e.setScheduleDate(df.format(agendaSCLocal.getFecha_mod()));
								break;
							}
						}
					}
				}catch(FinderException e){
					log.debug("FinderException en esActuacionCCF(): "+e.toString());
				}

				tr701e.setTransactionType("MOD");
			}else{
				tr701e.setIdSchedule("AP"+idPeticion.toString()+"-"+df.format(dateAhora));
				tr701e.setScheduleDate(df.format(dateAhora));
			
			Agenda_scLocal agenda_SCLocal=agenda_SCLocalHome.create(tr701e.getIdSchedule().toString());
			agenda_SCLocal.setPeticion(peticionLocal);
			agenda_SCLocal.setEstado(new Integer(1));
			agenda_SCLocal.setPeti_numero(idPeticion);
			agenda_SCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			}
	}catch (CreateException e) {
			log.error("Error creando objeto de BD en alta actuación de Agenda SC: ",e);
		}
	}

	/**
	 * @param decosTarjetaPeticionList
	 * @param listaEquipos
	 * @param deco_tar_inf_sapLocalHome
	 * @param idPeticion
	 * @param recursosbaBean
	 * @param peticionLocal
	 * @param psTipoEquipoLocalHome
	 * @param elementoAgendSCLocalHome
	 * @param elementoLocalHome
	 * @return
	 */
	private Collection obtenerListaEquipos(Collection decosTarjetaPeticionList, Collection listaEquipos, Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, Long idPeticion, RecursosBADelegate recursosbaBean, PeticionLocal peticionLocal, Ps_Tipo_EqLocalHome psTipoEquipoLocalHome, Elemento_agenda_scLocalHome elementoAgendSCLocalHome, ElementoLocalHome elementoLocalHome) {
		// TODO Apéndice de método generado automáticamente
			try{
				for (Iterator decosTarjetasPeticionIter = decosTarjetaPeticionList.iterator(); decosTarjetasPeticionIter.hasNext();){
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decosTarjetasPeticionIter.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey();
					
					if (decoTarjetaLocal.getSerial_deco() != null && decoTarjetaLocal.getSerial_deco().length() > 0 && !decoTarjetaLocal.getSerial_deco().equals("0")){
						TR701EEquipment tr701eEquipment = new TR701EEquipment();
						tr701eEquipment.setSerialNumber(decoTarjetaLocal.getSerial_deco());
						tr701eEquipment.setBrand(decoTarjetaLocal.getDeco_marca());
						if (decoTarjetaLocal.getDeco_reference()!=null && decoTarjetaLocal.getDeco_reference().length()>0){
							tr701eEquipment.setModel(decoTarjetaLocal.getDeco_reference());
						}else{
							tr701eEquipment.setModel("");
						}
						
						
						if (desHCDecoSTD.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
							tr701eEquipment.setType(ComunInterfaces.DECODTHSTD);
						}else if (desHCDecoPVR.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
							tr701eEquipment.setType(ComunInterfaces.DECODTHPVR);
						}else if (desHCDecoHDTV.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
							tr701eEquipment.setType(ComunInterfaces.DECOHD);
						}
						
						tr701eEquipment.setCassId(decoTarjetaKey.id_deco);
						tr701eEquipment.setCardSerialNumber(decoTarjetaKey.id_tarjeta);
						tr701eEquipment.setInventoryType("");
						tr701eEquipment.setPartType("");
						tr701eEquipment.setId(ComunInterfaces.ID_DECO_TARJETA);
						
						/*RQ.8595 - mfmendez*/
						tr701eEquipment = recursosbaBean.setDatosSAPDecoTarjeta(deco_tar_inf_sapLocalHome, tr701eEquipment, decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta, idPeticion);
						
						listaEquipos.add(tr701eEquipment);
					}
					
				}
				
//				Para los elementos de la petición
				Collection elementoPeticionList = peticionLocal.getElemento_peticion();
				for (Iterator elementoPeticionIter = elementoPeticionList.iterator(); elementoPeticionIter.hasNext();){
					Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal)elementoPeticionIter.next();
										
					
					if (elementoPeticionLocal.getSerial() != null && elementoPeticionLocal.getSerial().length() > 0 && !elementoPeticionLocal.getSerial().equals("0")){
						TR701EEquipment tr701eEquipment = new TR701EEquipment();
						tr701eEquipment.setSerialNumber(elementoPeticionLocal.getSerial());
						tr701eEquipment.setBrand("");
						tr701eEquipment.setModel("");
					
					try{
						Ps_Tipo_EqKey psTipoEquipoKey = new Ps_Tipo_EqKey(new Integer(elementoPeticionLocal.getPs_id().toString()),new Integer(elementoPeticionLocal.getTipo_elemento().toString()));
						Ps_Tipo_EqLocal psTipoEquipoLocal = psTipoEquipoLocalHome.findByPrimaryKey(psTipoEquipoKey);

						Elemento_agenda_scKey elementoAgendaSCKey = new Elemento_agenda_scKey(psTipoEquipoLocal.getId_elemento_agenda());

						if (elementoAgendaSCKey.id_correlativo != null){
							Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendSCLocalHome.findByPrimaryKey(elementoAgendaSCKey);
							tr701eEquipment.setType(elementoAgendaSCLocal.getDesc_equipo());
						}else{
							log.debug("No existe un equipo de agenda asociado al ps:"+elementoPeticionLocal.getPs_id()+" y tipo de elemento:"+elementoPeticionLocal.getTipo_elemento());
						tr701eEquipment.setType("");
						}
					}catch(Exception ex){
						log.debug("Ocurrió un problema detectando el tipo de equipos en la tr-701-e, se setea vacio:"+ex);
						tr701eEquipment.setType("");
					}

						tr701eEquipment.setCassId("");
						tr701eEquipment.setCardSerialNumber("");
						tr701eEquipment.setInventoryType(elementoPeticionLocal.getTipo_inventario());
						
						ElementoLocal elementoLocal = elementoLocalHome.findElemento(elementoPeticionLocal.getTipo_elemento().longValue());
						tr701eEquipment.setPartType(elementoLocal.getDesc_elemento());

						tr701eEquipment.setId(ComunInterfaces.ID_OTROS);
						
						/*RQ.8595 - mfmendez*/
						// datos del equipo
						if(elementoPeticionLocal.getFec_cont_sap() != null)
							tr701eEquipment.setPostingDateSAP(elementoPeticionLocal.getFec_cont_sap());
						else
							tr701eEquipment.setPostingDateSAP("");
						
						if(elementoPeticionLocal.getClase_mov_sap() != null)
							tr701eEquipment.setMoveTypeSAP(elementoPeticionLocal.getClase_mov_sap());
						else
							tr701eEquipment.setMoveTypeSAP("");
						
						tr701eEquipment.setMaterialCodeSAP(Integer.toString(elementoPeticionLocal.getPos_doc_sap()));
						
						if(elementoPeticionLocal.getNum_material_sap() != null)
							tr701eEquipment.setMaterialSAP(elementoPeticionLocal.getNum_material_sap());
						else
							tr701eEquipment.setMaterialSAP("");
							
						if(elementoPeticionLocal.getCentro_sap() != null)
							tr701eEquipment.setPlantSAP(elementoPeticionLocal.getCentro_sap());
						else
							tr701eEquipment.setPlantSAP("");
						
						if(elementoPeticionLocal.getAlmacen_sap() != null)
							tr701eEquipment.setStorageSAP(elementoPeticionLocal.getAlmacen_sap());
						else
							tr701eEquipment.setStorageSAP("");
						
						if(elementoPeticionLocal.getCod_lote_sap() != null)
							tr701eEquipment.setBatchCodeSAP(elementoPeticionLocal.getCod_lote_sap());
						else
							tr701eEquipment.setBatchCodeSAP("");
						
						if(elementoPeticionLocal.getUnd_medida_sap() != null)
							tr701eEquipment.setMeasurementUnitSAP(elementoPeticionLocal.getUnd_medida_sap());
						else
							tr701eEquipment.setMeasurementUnitSAP("");
						
						if(elementoPeticionLocal.getCentr_cost_sap() != null)
							tr701eEquipment.setCostCenterSAP(elementoPeticionLocal.getCentr_cost_sap());
						else
							tr701eEquipment.setCostCenterSAP("");
						
						if(elementoPeticionLocal.getArea_func_sap() != null)
							tr701eEquipment.setFuncAreaLongSAP(elementoPeticionLocal.getArea_func_sap());
						else
							tr701eEquipment.setFuncAreaLongSAP("");
						
						if(elementoPeticionLocal.getElement_pep_sap() != null)
							tr701eEquipment.setPepElementSAP(elementoPeticionLocal.getElement_pep_sap());
						else
							tr701eEquipment.setPepElementSAP("");
						
						if(elementoPeticionLocal.getFlag_mat_sap() != null)
							tr701eEquipment.setFlagMatSAP(elementoPeticionLocal.getFlag_mat_sap());
						else
							tr701eEquipment.setFlagMatSAP("");
						
						// Datos de la tarjeta vacios
						tr701eEquipment.setCardPostingDateSAP("");
						tr701eEquipment.setCardMoveTypeSAP("");
						tr701eEquipment.setCardMaterialCodeSAP("");
						tr701eEquipment.setCardMaterialSAP("");
						tr701eEquipment.setCardPlantSAP("");
						tr701eEquipment.setCardStorageSAP("");
						tr701eEquipment.setCardBatchCodeSAP("");
						tr701eEquipment.setCardMeasurementUnitSAP("");
						tr701eEquipment.setCardCostCenterSAP("");
						tr701eEquipment.setCardFuncAreaLongSAP("");
						tr701eEquipment.setCardPepElementSAP("");
						tr701eEquipment.setCardFlagMatSAP("");
						/*FIN - RQ.8595 - mfmendez*/
						
						listaEquipos.add(tr701eEquipment);

					}
				}
				
				//Para las cámaras
				CamaraLocalHome camaraLH = (CamaraLocalHome)HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
				Collection camaras = camaraLH.findByPeticion(idPeticion);
				if(camaras!=null && !camaras.isEmpty()){
					for (Iterator iter = camaras.iterator(); iter.hasNext();) {
						CamaraLocal camara = (CamaraLocal) iter.next();
						TR701EEquipment tr701eEquipment = new TR701EEquipment();
						recursosbaBean.setValoresCamara(tr701eEquipment,camara);
						listaEquipos.add(tr701eEquipment);
					}
				}
				
				//Para los modems
				Collection modemPeticionList = peticionLocal.getModem();
				for (Iterator modemPeticionIter = modemPeticionList.iterator(); modemPeticionIter.hasNext();){
					ModemLocal modemLocal = (ModemLocal)modemPeticionIter.next();
					ModemKey modemKey = (ModemKey)modemLocal.getPrimaryKey();
					
					if (modemKey.serial != null && modemKey.serial.length() > 0 && !modemKey.serial.equals("0") && !modemKey.serial.equals("NO SERIAL")){	
						TR701EEquipment tr701eEquipment = new TR701EEquipment();
						tr701eEquipment.setSerialNumber(modemKey.serial);
						tr701eEquipment.setBrand(modemLocal.getModem_marca());
						if (modemLocal.getModelo()!=null && modemLocal.getModelo().length()>0){
							tr701eEquipment.setModel(modemLocal.getModelo());
						}else{
							tr701eEquipment.setModel("");
						}

						if (modemLocal.getTipo() != null && modemLocal.getTipo().intValue()==ComunInterfaces.identificadorWiFi){
							tr701eEquipment.setType(ComunInterfaces.MODEM_WIFI);
						}else{
							tr701eEquipment.setType(ComunInterfaces.MODEM_STD);
						}
						
						tr701eEquipment.setCassId("");
						tr701eEquipment.setCardSerialNumber("");
						tr701eEquipment.setInventoryType("");
						tr701eEquipment.setPartType("");
						tr701eEquipment.setId(ComunInterfaces.ID_MODEM);
						
						/*RQ.8595 - mfmendez*/
						// datos del modem
						if(modemLocal.getFec_cont_sap() != null)
							tr701eEquipment.setPostingDateSAP(modemLocal.getFec_cont_sap());
						else
							tr701eEquipment.setPostingDateSAP("");
						
						if(modemLocal.getClase_mov_sap() != null)
							tr701eEquipment.setMoveTypeSAP(modemLocal.getClase_mov_sap());
						else
							tr701eEquipment.setMoveTypeSAP("");
						
						tr701eEquipment.setMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
						
						if(modemLocal.getNum_material_sap() != null)
							tr701eEquipment.setMaterialSAP(modemLocal.getNum_material_sap());
						else
							tr701eEquipment.setMaterialSAP("");
							
						if(modemLocal.getCentro_sap() != null)
							tr701eEquipment.setPlantSAP(modemLocal.getCentro_sap());
						else
							tr701eEquipment.setPlantSAP("");
						
						if(modemLocal.getAlmacen_sap() != null)
							tr701eEquipment.setStorageSAP(modemLocal.getAlmacen_sap());
						else
							tr701eEquipment.setStorageSAP("");
						
						if(modemLocal.getCod_lote_sap() != null)
							tr701eEquipment.setBatchCodeSAP(modemLocal.getCod_lote_sap());
						else
							tr701eEquipment.setBatchCodeSAP("");
						
						if(modemLocal.getUnd_medida_sap() != null)
							tr701eEquipment.setMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
						else
							tr701eEquipment.setMeasurementUnitSAP("");
						
						if(modemLocal.getCentr_cost_sap() != null)
							tr701eEquipment.setCostCenterSAP(modemLocal.getCentr_cost_sap());
						else
							tr701eEquipment.setCostCenterSAP("");
						
						if(modemLocal.getArea_func_sap() != null)
							tr701eEquipment.setFuncAreaLongSAP(modemLocal.getArea_func_sap());
						else
							tr701eEquipment.setFuncAreaLongSAP("");
						
						if(modemLocal.getElement_pep_sap() != null)
							tr701eEquipment.setPepElementSAP(modemLocal.getElement_pep_sap());
						else
							tr701eEquipment.setPepElementSAP("");
						
						if(modemLocal.getFlag_mat_sap() != null)
							tr701eEquipment.setFlagMatSAP(modemLocal.getFlag_mat_sap());
						else
							tr701eEquipment.setFlagMatSAP("");
						
						// Datos de la tarjeta vacios
						tr701eEquipment.setCardPostingDateSAP("");
						tr701eEquipment.setCardMoveTypeSAP("");
						tr701eEquipment.setCardMaterialCodeSAP("");
						tr701eEquipment.setCardMaterialSAP("");
						tr701eEquipment.setCardPlantSAP("");
						tr701eEquipment.setCardStorageSAP("");
						tr701eEquipment.setCardBatchCodeSAP("");
						tr701eEquipment.setCardMeasurementUnitSAP("");
						tr701eEquipment.setCardCostCenterSAP("");
						tr701eEquipment.setCardFuncAreaLongSAP("");
						tr701eEquipment.setCardPepElementSAP("");
						tr701eEquipment.setCardFlagMatSAP("");
						/*FIN - RQ.8595 - mfmendez*/
						
						listaEquipos.add(tr701eEquipment);
					}
				}
			}catch (FinderException e) {
				log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.error("Error buscando objeto de BD alta actuación de Agenda SC: ",e);
			} catch (Exception e) {
				// TODO Bloque catch generado automáticamente
				log.error("Error: RecursosBABean: creacionActuacionAgendaSC. ",e);
			}
		return listaEquipos;
	}

	/**
	 * @param peticionLocal
	 * @param numDecos
	 * @param listaTematicosTV
	 * @param idPeticion
	 * @param adsl
	 * @param rlb
	 * @param tipoDeco
	 * @param recursosbaBean
	 * @return
	 */
	private TR701ETechnicalData setTechnicalData(PeticionLocal peticionLocal, String numDecos, String listaTematicosTV, Long idPeticion, InformacionAdslDTO adsl, Recursos_linea_basicaLocal rlb, String tipoDeco, RecursosBADelegate recursosbaBean) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		TR701ETechnicalData tr701ETechnicalData = new TR701ETechnicalData();
		tr701ETechnicalData.setNomInterlocutor(peticionLocal.getNom_int_ds());
		tr701ETechnicalData.setTelInterlocutor(peticionLocal.getTel_cot_ds());
		tr701ETechnicalData.setTel1Contacto(peticionLocal.getTel_cot_ds());
		tr701ETechnicalData.setTel2Contacto(peticionLocal.getTel_cot_ds_1());
		tr701ETechnicalData.setTel3Contacto(peticionLocal.getTel_cot_ds_2());
		tr701ETechnicalData.setAdsl("");
		tr701ETechnicalData.setBox("");
		tr701ETechnicalData.setBoxAddress("");
		tr701ETechnicalData.setBoxDistance("");
		tr701ETechnicalData.setBoxPair("");
		tr701ETechnicalData.setBoxType("");
		tr701ETechnicalData.setCable("");
		tr701ETechnicalData.setCablePair("");
		tr701ETechnicalData.setCard("");
		tr701ETechnicalData.setCentralCode("");
		tr701ETechnicalData.setCloset("");
		tr701ETechnicalData.setClosetAddress("");
		tr701ETechnicalData.setDecosNumber(numDecos);
		tr701ETechnicalData.setDecosType(tipoDeco);	
		tr701ETechnicalData.setDistributorAddress("");
		tr701ETechnicalData.setDistributorCode("");
		tr701ETechnicalData.setDistributorDescription("");
		tr701ETechnicalData.setFrame("");
		tr701ETechnicalData.setHorizontalPosicion("");
		tr701ETechnicalData.setIpDslam("");
		tr701ETechnicalData.setIpLan("");
		tr701ETechnicalData.setIpLanMask("");
		tr701ETechnicalData.setIpType("");
		tr701ETechnicalData.setIpWan("");
		tr701ETechnicalData.setLatitude("");
		tr701ETechnicalData.setLen("");
		tr701ETechnicalData.setLongitude("");
		tr701ETechnicalData.setPackages(listaTematicosTV);
		tr701ETechnicalData.setPhoneNumber("");
		tr701ETechnicalData.setPortId("");
		tr701ETechnicalData.setPots("");
		tr701ETechnicalData.setRack("");			
		tr701ETechnicalData.setSpeed("");
		tr701ETechnicalData.setStrip("");
		tr701ETechnicalData.setStripPair("");
		tr701ETechnicalData.setSubrack("");
		tr701ETechnicalData.setUserAccess("");
		tr701ETechnicalData.setVpiVci("");
		tr701ETechnicalData.setVpiVciNetwork("");
		tr701ETechnicalData.setZone("");

		String velocidadPlanYPS = recursosbaBean.extraerVelocidadPlanYPSPrioridadAlta(idPeticion);
		String velocidadPlan="";
		String psPlan="";
		if(velocidadPlanYPS!=null && !velocidadPlanYPS.equals("")){
			String[] velYPS = velocidadPlanYPS.split("#");
			velocidadPlan = velYPS[0];
			psPlan = velYPS[1];
		}
		
		if(velocidadPlan != null && !velocidadPlan.equals(""))
			tr701ETechnicalData.setSpeed(velocidadPlan);
		else
			tr701ETechnicalData.setSpeed("");
		if(adsl!=null){
			if(adsl.getAdsl()!=null){
				String adslAux[] = adsl.getAdsl().split("-");
				tr701ETechnicalData.setAdsl(adsl.getAdsl());
				tr701ETechnicalData.setRack(adslAux[0]);
			}else{
				tr701ETechnicalData.setAdsl("");
			}if(adsl.getSlot()!=null){
				tr701ETechnicalData.setCard(adsl.getSlot());
			}else{
				tr701ETechnicalData.setCard("");
			}if(adsl.getFrame()!=null){
				tr701ETechnicalData.setFrame(adsl.getFrame());
				tr701ETechnicalData.setSubrack(adsl.getFrame());
			}else{
				tr701ETechnicalData.setFrame("");
			}if(adsl.getDirecIpDslam()!=null){
				tr701ETechnicalData.setIpDslam(adsl.getDirecIpDslam());
			}else{
				tr701ETechnicalData.setIpDslam("");
			}if(adsl.getDirecIpLan()!=null){
				tr701ETechnicalData.setIpLan(adsl.getDirecIpLan());
			}else{
				tr701ETechnicalData.setIpLan("");
			}if(adsl.getMascaraLan()!=null){
				tr701ETechnicalData.setIpLanMask(adsl.getMascaraLan());
			}else{
				tr701ETechnicalData.setIpLanMask("");
			}if(adsl.getDirecIpWan()!=null){
				tr701ETechnicalData.setIpWan(adsl.getDirecIpWan());
			}else{
				tr701ETechnicalData.setIpWan("");
			}if(adsl.getPuerto()!=null){
				tr701ETechnicalData.setPortId(adsl.getPuerto());
			}else{
				tr701ETechnicalData.setPortId("");
			}if(adsl.getPost()!=null){
				tr701ETechnicalData.setPots(adsl.getPost());
			}else{
				tr701ETechnicalData.setPots("");
			}if(adsl.getUsuarioAcc()!=null){
				tr701ETechnicalData.setUserAccess(adsl.getUsuarioAcc());
			}else{
				tr701ETechnicalData.setUserAccess("");
			}if(adsl.getVpiVciCliente()!=null){
				tr701ETechnicalData.setVpiVci(adsl.getVpiVciCliente());
			}else{
				tr701ETechnicalData.setVpiVci("");
			}if(adsl.getVpiVciRed()!=null){
				tr701ETechnicalData.setVpiVciNetwork(adsl.getVpiVciRed());
			}else{
				tr701ETechnicalData.setVpiVciNetwork("");
			}if(adsl.getCodZonaAtend()!=null){
				tr701ETechnicalData.setZone(adsl.getCodZonaAtend());
			}else{
				tr701ETechnicalData.setZone("");
			}
		}if(rlb!=null){
			
			if (rlb.getTelefono_asg()!= null) 
				tr701ETechnicalData.setPhoneNumber(rlb.getTelefono_asg().toString());
			else 
				tr701ETechnicalData.setPhoneNumber("");
			if (rlb.getLen()!=null) 
				tr701ETechnicalData.setLen(rlb.getLen());
			else 
				tr701ETechnicalData.setLen("");
			if (rlb.getPosicion_horizontal_asg()!=null) 
				tr701ETechnicalData.setHorizontalPosicion(rlb.getPosicion_horizontal_asg());
			else 
				tr701ETechnicalData.setHorizontalPosicion("");
			if (rlb.getDist_prim_asg()!=null)
				tr701ETechnicalData.setDistributorCode(rlb.getDist_prim_asg().toString());
			else
				tr701ETechnicalData.setDistributorCode("");
			if (rlb.getDesc_dist_prim_asg()!=null)
				tr701ETechnicalData.setDistributorDescription(rlb.getDesc_dist_prim_asg());
			else
				tr701ETechnicalData.setDistributorDescription("");
			if (rlb.getDir_distribuidor()!=null)
				tr701ETechnicalData.setDistributorAddress(rlb.getDir_distribuidor());
			else
				tr701ETechnicalData.setDistributorAddress("");
			if (rlb.getListon_asg()!=null)
				tr701ETechnicalData.setStrip(rlb.getListon_asg());
			else
				tr701ETechnicalData.setDistributorAddress("");
			if (rlb.getPar_liston_asg()!=null)
				tr701ETechnicalData.setStripPair(rlb.getPar_liston_asg().toString());
			else
				tr701ETechnicalData.setStripPair("");
			
			if (rlb.getInd_dedicado() != null && rlb.getInd_dedicado().intValue() == 1){
				tr701ETechnicalData.setParDedicated("Si");
				if(rlb.getCaja_dedicado()!=null){
					tr701ETechnicalData.setBox(rlb.getCaja_dedicado());
				}else{
					tr701ETechnicalData.setBox("");
				}if(rlb.getArmario_dedicado()!=null){
					tr701ETechnicalData.setCloset(rlb.getArmario_dedicado());
				}else{
					tr701ETechnicalData.setCloset("");
				}if(rlb.getPar_caja_dedicado()!=null){
					tr701ETechnicalData.setBoxPair(""+rlb.getPar_caja_dedicado());
				}else{
					tr701ETechnicalData.setBoxPair("");
				}if(rlb.getCable_dedicado()!=null){
					tr701ETechnicalData.setCable(rlb.getCable_dedicado());
				}else{
					tr701ETechnicalData.setCable("");
				}if(rlb.getPar_cable_dedicado()!=null){
					tr701ETechnicalData.setCablePair(""+rlb.getPar_cable_dedicado());
				}else{
					tr701ETechnicalData.setCablePair("");
				}if(rlb.getDir_armario_dedicado()!=null){
					tr701ETechnicalData.setClosetAddress(rlb.getDir_armario_dedicado());
				}else{
					tr701ETechnicalData.setClosetAddress("");
				}if(rlb.getDir_caja_dedicado()!=null){
					tr701ETechnicalData.setBoxAddress(rlb.getDir_caja_dedicado());
				}else{
					tr701ETechnicalData.setBoxAddress("");
				}if(rlb.getTipo_caja_dedicado()!=null){
					tr701ETechnicalData.setBoxType(""+rlb.getTipo_caja_dedicado());
				}else{
					tr701ETechnicalData.setBoxType("");
				}if(rlb.getCod_central_dedicado()!=null){
					tr701ETechnicalData.setCentralCode(""+rlb.getCod_central_dedicado());
				}else{
					tr701ETechnicalData.setCentralCode("");
				}
				log.debug("Envia información de red dedicada");
				
			}else{
				tr701ETechnicalData.setParDedicated("No");
				if(rlb.getCaja_asg()!=null){
					tr701ETechnicalData.setBox(rlb.getCaja_asg());
				}else{
					tr701ETechnicalData.setBox("");
				}if(rlb.getArmario_asg()!=null){
					tr701ETechnicalData.setCloset(rlb.getArmario_asg());
				}else{
					tr701ETechnicalData.setCloset("");
				}if(rlb.getPar_caja_asg()!=null){
					tr701ETechnicalData.setBoxPair(""+rlb.getPar_caja_asg());
				}else{
					tr701ETechnicalData.setBoxPair("");
				}if(rlb.getCable()!=null){
					tr701ETechnicalData.setCable(rlb.getCable());
				}else{
					tr701ETechnicalData.setCable("");
				}if(rlb.getPar_cable()!=null){
					tr701ETechnicalData.setCablePair(""+rlb.getPar_cable());
				}else{
					tr701ETechnicalData.setCablePair("");
				}if(rlb.getDir_armario()!=null){
					tr701ETechnicalData.setClosetAddress(rlb.getDir_armario());
				}else{
					tr701ETechnicalData.setClosetAddress("");
				}if(rlb.getDir_caja()!=null){
					tr701ETechnicalData.setBoxAddress(rlb.getDir_caja());
				}else{
					tr701ETechnicalData.setBoxAddress("");
				}if(rlb.getTipo_caja()!=null){
					tr701ETechnicalData.setBoxType(""+rlb.getTipo_caja());
				}else{
					tr701ETechnicalData.setBoxType("");
				}if(rlb.getCod_central()!=null){
					tr701ETechnicalData.setCentralCode(""+rlb.getCod_central());
				}else{
					tr701ETechnicalData.setCentralCode("");
				}
				log.debug("Envia información de red origen");
				
			}
		}
		return tr701ETechnicalData;
	}

	/**
	 * @param agrupacionAtisList
	 * @param psPeticion
	 * @param peticionLocal
	 * @param agrupacionAtisListIt
	 * @param psPeticionIt
	 * @param recursosbaBean
	 * @param idPeticion
	 * @param infoContactYMedia
	 * @return
	 * @throws ATiempoAppEx
	 * @throws NumberFormatException
	 */
	private TR701ENotes setNotes(Collection agrupacionAtisList, Collection psPeticion, PeticionLocal peticionLocal, Iterator agrupacionAtisListIt, Iterator psPeticionIt, RecursosBADelegate recursosbaBean, Long idPeticion, String infoContactYMedia) throws NumberFormatException, ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		TR701ENotes tr701ENotes=new TR701ENotes();
		try{
			RecursosBADelegate recursosBA = new RecursosBADelegate();
			String estrato = "";
			if(agrupacionAtisList!=null){
				for(agrupacionAtisListIt=agrupacionAtisList.iterator();agrupacionAtisListIt.hasNext();){
					Agrupacion_atisLocal agrupacion_atisLocal = (Agrupacion_atisLocal) agrupacionAtisListIt.next();
					if(agrupacion_atisLocal.getNom_tip_uso_no()!=null&&!agrupacion_atisLocal.getNom_tip_uso_no().equals("")){
						estrato = agrupacion_atisLocal.getNom_tip_uso_no();
						break;
					}
				}
					String tipoIncidencia = null;
					for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
						Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
						tipoIncidencia = recursosbaBean.obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (ComunInterfaces.CARACINCIDENCIA));
					}
					String notaDescripcion = "";
					String nombreCliente = Utiles.sinNull(peticionLocal.getNom_ds(),"") 
					+ " " +Utiles.sinNull(peticionLocal.getPri_ape_ds(),"") 
					+ " " + Utiles.sinNull(peticionLocal.getSeg_ape_ds(),"");
					String nombreSolicitante = peticionLocal.getNom_int_ds() 
						+ " " + peticionLocal.getPri_ape_int_ds()
						+ " " + peticionLocal.getSeg_ape_int_ds();
					notaDescripcion = notaDescripcion+ComunInterfaces.NOMBRE_SOLICITANTE+nombreSolicitante+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.TEL_SOLICITANTE+peticionLocal.getTel_cot_ds()+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.SEG_TEL_SOLICITANTE+peticionLocal.getTel_cot_ds_1()+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.DATOS_AGENDAMIENTO+infoContactYMedia+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.NOMBRE_CLIENTE+nombreCliente+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.TEL_CLIENTE+peticionLocal.getTel_cot_ds_1()+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.SEG_TEL_CLIENTE+peticionLocal.getTel_cot_ds_2()+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.OBSERVACIONES+peticionLocal.getObs_pet_ds()+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.ESTRATO+estrato+";";
					notaDescripcion = notaDescripcion+ComunInterfaces.TIPOINCIDENCIA+tipoIncidencia+";";
					
				Bitacora_peticionLocalHome bitacoraPeticionLocalHome = (Bitacora_peticionLocalHome)HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
				Collection listaPeticionesList = bitacoraPeticionLocalHome.findByPetiOrden(idPeticion);
				String observacionActividad = "";
					
				for (Iterator listaPeticionesIterator = listaPeticionesList.iterator();listaPeticionesIterator.hasNext();){
					Bitacora_peticionLocal bitacoraPeticionLocal = (Bitacora_peticionLocal)listaPeticionesIterator.next();

					if (bitacoraPeticionLocal.getBipe_fecha_fin() != null){
						observacionActividad = bitacoraPeticionLocal.getBipe_observacion();
					}
				}

				notaDescripcion = notaDescripcion+ComunInterfaces.OBSERVACION_BITACORA+observacionActividad;
				
				if(cantDecosDesinsHD>0){
					int catidadHD =recursosBA.cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoHDTV,cantDecosDesinsHD);	
					if (catidadHD < cantDecosDesinsHD)
					{	
							notaDescripcion=notaDescripcion+";Cantidad DECOs HC HD "+catidadHD+" Cantidad DECOs HD a Des-instalar "+(catidadHD - cantDecosDesinsHD)+" favor gestionar regularizacion.";
					
					}
				}
				 if(cantDecosDesinsPVR>0){
					int cantidadPVR =recursosBA.cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoPVRTV,cantDecosDesinsPVR);	
					if (cantidadPVR < cantDecosDesinsPVR)
					{
						notaDescripcion=notaDescripcion+";Cantidad DECOs HC PVR "+cantidadPVR+" Cantidad DECOs PVR a Des-instalar "+(cantidadPVR - cantDecosDesinsPVR)+" favor gestionar regularizacion.";				
					}
				}
				if(cantDecosDesinsSTD>0){
					int cantidadSTD =recursosBA.cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoTV,cantDecosDesinsSTD);	
					if (cantidadSTD < cantDecosDesinsSTD)
					{
					notaDescripcion=notaDescripcion+";Cantidad DECOs HC STD "+cantidadSTD+" Cantidad DECOs STD a Des-instalar "+(cantidadSTD - cantDecosDesinsSTD)+" favor gestionar regularizacion.";
					}
					
				}	
					tr701ENotes.setNoteDescription(notaDescripcion);
					tr701ENotes.setNoteDetail("");
					tr701ENotes.setNoteType("ACT_COMENT");
				
			}
		}catch (FinderException e) {
			log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error buscando objeto de BD alta actuación de Agenda SC: ",e);
		}
		
		return tr701ENotes;
	}

	/**
	 * @param peticionLocal
	 * @param codActividad
	 * @param peticionesDelegate
	 * @param emailCaracteristicas
	 * @param subsegmentoLocalHome
	 * @param segmentoLocalHome
	 * @return
	 */
	private TR701ECustomer setCustomer(PeticionLocal peticionLocal, String codActividad, PeticionesDelegate peticionesDelegate, String emailCaracteristicas, SubsegmentoLocalHome subsegmentoLocalHome, SegmentoLocalHome segmentoLocalHome) {
		// TODO Apéndice de método generado automáticamente
		TR701ECustomer tr701ECustomer = new TR701ECustomer();
		try{
			tr701ECustomer.setCode(new Long(0));
			if(peticionLocal.getNum_doc_cli_cd()!=null){
				tr701ECustomer.setCode(new Long(peticionLocal.getNum_doc_cli_cd()));
			}				
			tr701ECustomer.setEmail(emailCaracteristicas);
			tr701ECustomer.setId(peticionLocal.getNum_doc_cli_cd());
			tr701ECustomer.setIdType(peticionLocal.getTip_doc_cd());
			tr701ECustomer.setName(peticionLocal.getNom_ds());
			
			tr701ECustomer.setPhoneNumber(new Long(0));
			if(peticionLocal.getNum_ide_nu_stb()!=null && peticionLocal.getNum_ide_nu_stb().length()>0){
				tr701ECustomer.setPhoneNumber(new Long(peticionLocal.getNum_ide_nu_stb()));
			}
			
			SubsegmentoKey subsegmentoKey = new SubsegmentoKey(peticionLocal.getCod_sbg_cta_cd());
			SubsegmentoLocal subsegmentoLocal = subsegmentoLocalHome.findByPrimaryKey(subsegmentoKey);
			tr701ECustomer.setSubSeg(subsegmentoLocal.getDescripcion());	
			SegmentoKey segmentoKey = new SegmentoKey(peticionLocal.getCod_sgm_cta_cd());
			SegmentoLocal segmentoLocal = segmentoLocalHome.findByPrimaryKey(segmentoKey);
			
			String sufijoSegmento = codActividad.equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_PGACS")) ?
				peticionesDelegate.recuperarParametroFromPropertiesBD("AUTOINS_SEGMENTO") : "" ;

			tr701ECustomer.setType(segmentoLocal.getSegm_descripcion() + sufijoSegmento);
		}catch (FinderException e) {
			log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
		}
		return tr701ECustomer;
	}

	/**
	 * @param peticionLocal
	 * @return
	 */
	private TR701EAdressData setAdressData(PeticionLocal peticionLocal) {
		// TODO Apéndice de método generado automáticamente
		TR701EAdressData tr701EAdressData = new TR701EAdressData();
		tr701EAdressData.setAddress(peticionLocal.getNom_cal_ds()+" "+peticionLocal.getNum_cal_nu()+" "+peticionLocal.getDsc_cmp_pri_ds()+" "+peticionLocal.getNom_slo_no());
		tr701EAdressData.setAgencyName("");//Aplica para SISGOT, se fija en vacío
		
		LocalidadLocal  localidadLocal=(LocalidadLocal)peticionLocal.getFk_01_localidad();
		LocalidadKey localidadKey=(LocalidadKey)localidadLocal.getPrimaryKey();
		
		MunicipioLocal municipioLocal=localidadLocal.getMunicipio();
		MunicipioKey municipioKey=(MunicipioKey)municipioLocal.getPrimaryKey();
		DepartamentoKey deptoKey=(DepartamentoKey)peticionLocal.getFk_02_departamento().getPrimaryKey();
		
		tr701EAdressData.setCity(municipioKey.cod_mun);
		if (peticionLocal.getCoord_x_agnd_sc() != null && peticionLocal.getCoord_x_agnd_sc().length()>0){
			tr701EAdressData.setCoordinateX(peticionLocal.getCoord_x_agnd_sc());
		}else{
			log.debug("No se encuatra especificada la coordenada X se setea 0 por defecto para evitar conflicots con agenda SC");
			tr701EAdressData.setCoordinateX("0");
		}
		
		if(peticionLocal.getCoord_y_agnd_sc() != null && peticionLocal.getCoord_y_agnd_sc().length()>0){
			tr701EAdressData.setCoordinateY(peticionLocal.getCoord_y_agnd_sc());
		}else{
			log.debug("No se encuatra especificada la coordenada Y se setea 0 por defecto para evitar conflicots con agenda SC");
			tr701EAdressData.setCoordinateY("0");
		}
		
		tr701EAdressData.setLocation(localidadKey.cod_loc);
		tr701EAdressData.setNeighborhood(peticionLocal.getNom_slo_no());
		tr701EAdressData.setState(deptoKey.cod_dpt);
		tr701EAdressData.setSubCity(peticionLocal.getNom_slo_no());
		return tr701EAdressData;
	}

	public void recepcionCreacionActuacionAgendaSC(TR701S tr701s)throws ATiempoAppEx{	
		String codActividad=ComunInterfaces.ACT_INSTALAR;
		Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
		BackendExecution bExecution = null;
		long idActividad = ComunInterfaces.idActividadInstalar;
		RecursosBADelegate recursosbaBean = new RecursosBADelegate();
		
		try{		
			bExecution = BackendTraceUtil.initExecution(tr701s, log);
			bExecution.setIdMensajeOp(tr701s.getId());
			bExecution.startOperation();
			
			Agenda_scLocalHome  agenda_scLocalHome= (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Mensaje_estado_baLocalHome mensajeEstadoBALocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			
			Mensaje_agenda_scLocalHome mensajeAgendaSCLocalHome = (Mensaje_agenda_scLocalHome)HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
			ActividadLocalHome actividadLocalHome = (ActividadLocalHome)HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			
			Mensaje_agenda_scKey mensajeAgendaSCKey = new Mensaje_agenda_scKey(new Long(tr701s.getId().toString()));
			Mensaje_agenda_scLocal mensajeAgendaSCLocal = mensajeAgendaSCLocalHome.findByPrimaryKey(mensajeAgendaSCKey); 
			
			if (mensajeAgendaSCLocal.getCod_actividad_generadora() != null){
				codActividad = mensajeAgendaSCLocal.getCod_actividad_generadora();
			}
			ActividadLocal actividadLocal  = actividadLocalHome.findByCodigoActividadIdAplicacion(codActividad, idAplicacion);
			ActividadKey actividadKey = (ActividadKey)actividadLocal.getPrimaryKey();
			idActividad = actividadKey.act_id.longValue();
			//End Todo
			Agenda_scKey agenda_scKey=new Agenda_scKey(tr701s.getIdSchedule());
			Agenda_scLocal agenda_scLocal=agenda_scLocalHome.findByPrimaryKey(agenda_scKey);
			
			PeticionLocal peticionLocal = agenda_scLocal.getPeticion();
			
			Long peti_numero=agenda_scLocal.getPeti_numero();
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByIdActIdPetiAp(new Long(idActividad),peti_numero,idAplicacion);
			String idCorrelativo=bintegradaLocal.getBi_url_detalle();
			int idInicio=idCorrelativo.indexOf("instanciaActividad");
			
			if(idInicio!=-1){
				idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
				int idFin=idCorrelativo.indexOf("&");
				if(idFin!=-1){
					idCorrelativo=idCorrelativo.substring(19,idFin);
				}
			}
				
			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
		ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
		IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peti_numero, codActividad,idCorrelativo,null);
		this.setearDatosActividad(actDto);
			log.debug("El codigo de error es: "+tr701s.getErrorCode());
			if ( tr701s.getErrorCode().equals("0")){
				
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				boolean esAutoInstalacionSoloBA=peticionesDelegate.esAutoInstalacionSoloBA(peti_numero);
				
				if(peticionLocal.getEspe_id().intValue() == ComunInterfaces.estadoPeticionCancelada)
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa,"S");
				
				if (codActividad.equals(ACT_INSTALAR) || codActividad.equals(ACT_DES_INSTALAR)){
					if(esAutoInstalacionSoloBA){
						actDto.setObservacion("Se envía la petición directamente a Control Cruzada BA por respuesta desde AgendaSC: "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
						actDto.setRealizarTerminoInmediato(true);
//						Integer ordenFlujo = new Integer(actDto.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden));
//						Integer new_orden = new Integer(ordenFlujo.intValue() + 1);
//						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, new_orden.toString());
					}else{
						actDto.setObservacion("Se envía la petición directamente a Control instalación por respuesta desde AgendaSC: "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
					}
						actividadEJB.terminar(actDto);
				}else{
					actDto.setObservacion("Se creó una nueva actuación ID: "+tr701s.getIdSchedule()+" . "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
					actividadEJB.grabarSinTerminar(actDto);
				}

		}else {
				//Hay error
				if (codActividad.equals(ACT_INSTALAR)|| codActividad.equals(ACT_DES_INSTALAR)){
//					PeticionLocal peticionLocal = agenda_scLocal.getPeticion();
					
					ErrorLegadoLocal errorLegado = recursosbaBean.getErrorLegado(tr701s.getErrorCode(),"TR701S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr701s.getErrorMessage());
							actividadEJB.terminar(actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= recursosbaBean.getNombreBandeja(plataforma); 
					}
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actDto.setObservacion("Error en respuesta desde Agenda SC, se deriva a "+bandeja+": "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());					
					recursosbaBean.insertarCausalesCnaPeticion(peticionLocal, codActividad, new Long(742), actDto.getIdActividadFlujo());
					actDto.setRealizarTerminoInmediato(true);
					actividadEJB.terminar(actDto);
				}else{
					actDto.setObservacion("Error en respuesta desde Agenda SC: "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
					actividadEJB.grabarSinTerminar(actDto);
				}
				mensajeAgendaSCLocal.setError(tr701s.getErrorCode());
			String error =tr701s.getErrorMessage();
			if(error!=null && error.length()>24){
				error=error.substring(0,23);
			}
			mensajeAgendaSCLocal.setError(error);
				agenda_scLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
			}

		} catch (NamingException e) {
			bExecution.setErrorOp(true);
			log.error(" Creacion de Local Home Nulls",e);

		}  catch (FinderException e) {
			bExecution.setErrorOp(true);
			log.warn("FinderException",e);

		} catch(Exception e)
		{
			bExecution.setErrorOp(true);
			log.debug("Exception",e);
		}	
		finally{  
			bExecution.endAll();
		}
	}
	
	public void recepcionCierreActuacion(TR711S tr711s) throws ATiempoAppEx {
		try{
			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			String idPeticion = tr711s.getIdSchedule().substring(2,tr711s.getIdSchedule().indexOf("-"));
			
			HashMap estadosHomologados = new HashMap();
			Estado_homologacionLocalHome estHomLocalHome = (Estado_homologacionLocalHome)HomeFactory.getHome(Estado_homologacionLocalHome.JNDI_NAME);
			PeticionesDelegate peticiones = new PeticionesDelegate();
			String codigoApp = peticiones.recuperarParametroFromPropertiesBD("EST_AGENDSC");
			Collection estados = estHomLocalHome.findByCodApp(codigoApp);
			RecursosBADelegate recursosbaBean = new RecursosBADelegate();
			for (Iterator iter = estados.iterator(); iter.hasNext();) {
				Estado_homologacionLocal element = (Estado_homologacionLocal) iter.next();
				Estado_homologacionKey key = (Estado_homologacionKey)element.getPrimaryKey();
				estadosHomologados.put(key.cod_estado, element.getCod_homologado());
			}
			ArrayList listaEquipos = new ArrayList();
			ArrayList listaCamaras = new ArrayList();
			ArrayList listaDecos = new ArrayList();
			ArrayList listaModems = new ArrayList();
			ArrayList listaFranqueoOK = new ArrayList();
			ArrayList listaEquiposNoSerializados = new ArrayList();
			boolean esEquipo = false;
			boolean esCamara = false;
			boolean esDeco = false;
			boolean esModem = false;
			boolean franqueoOK = false;
			boolean cierroActividad = true;
			boolean esPostventa = true;
			boolean tieneEquipoValido = false;
			boolean envioTr701EPGC = true;
			
//			Obtención de los tipos de equipo que se tendrán en cuanta para el envio de mensajes
			String materialesEquipos = VpistbbaConfig.getVariable("MATERIALES_EQUIPOS");
			String arrayMaterialesEquipos[] = materialesEquipos.split(",");
			
			String materialesDecos = VpistbbaConfig.getVariable("MATERIALES_DECOS");
			String arrayMaterialesDecos[] = materialesDecos.split(",");
			
			String materialesModems = VpistbbaConfig.getVariable("MATERIALES_MODEMS");
			String arrayMaterialesModems[] = materialesModems.split(",");
			
			String materialesCamara = VpistbbaConfig.getVariable("MATERIALES_CAMARA");
			log.debug("MATERIALES_CAMARA"+materialesCamara);
			String arrayMaterialesCamara[] = materialesCamara.split(",");
			
			Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findFechasByPetiNumFecha(new Long(idPeticion));
			
			String strObservaciones = "";
			PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
			Collection productoSrvicioPeticionCollection = peticionLocal.getProducto_servicio_peticion();
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
			String idCorrelativo=bintegradaLocal.getBi_url_detalle();
			int idInicio=idCorrelativo.indexOf("instanciaActividad");
			if(idInicio!=-1){
				idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
				int idFin=idCorrelativo.indexOf("&");
				if(idFin!=-1){
					idCorrelativo=idCorrelativo.substring(19,idFin);
				}
			}
			String codActividad = bintegradaLocal.getBi_url_detalle();
			idInicio = codActividad.indexOf("actividad");
			if(idInicio!=-1){
				codActividad=codActividad.substring(idInicio,codActividad.length());
				int idFin=codActividad.indexOf("&");
				if(idFin!=-1){
					codActividad=codActividad.substring(10,idFin);
					codActividad = codActividad.replace('+', ' ');
				}
			}
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
			
			Collection estado_ps_peticion = null;
			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
			this.setearDatosActividad(actDto);
			Codigos_franqueo_ok_agd_scLocalHome codFranqueoOKLocalHome = (Codigos_franqueo_ok_agd_scLocalHome) HomeFactory.getHome(Codigos_franqueo_ok_agd_scLocalHome.JNDI_NAME);
			if (tr711s.getItAnswer() != null && tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM)){
				log.debug("Voy a ingresar los siguientes datos a la peticion: Area de traspaso:"+tr711s.getItComplement()+" y diagnóstico:"+tr711s.getItClosing());
				Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
				Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByDescripcion(tr711s.getItClosing()) ;
				if(catalogCausa != null){
					Catalogo_causalKey catalogo_causalKey= (Catalogo_causalKey) catalogCausa.getPrimaryKey();
					recursosbaBean.insertarCausalesCnaPeticion(peticionLocal, actDto.getCodigoActividad(),catalogo_causalKey.cod_causal, actDto.getIdActividadFlujo());
				}
			}
			String codBandeja = null;
			try{
				Codigos_franqueo_ok_agd_scKey codFranqueoOkKey = new Codigos_franqueo_ok_agd_scKey(tr711s.getPostageCode());
				Codigos_franqueo_ok_agd_scLocal codFranqueoOKLocal = codFranqueoOKLocalHome.findByPrimaryKey(codFranqueoOkKey);
				codBandeja = codFranqueoOKLocal.getBandeja();
				franqueoOK = true;
			}catch(FinderException ex){
				log.debug("El codigo de franqueo: "+tr711s.getPostageCode()+" no es considerado como exitoso, se valida si tiene marca y si es así se envía a configurar inventarios");
				franqueoOK = false;
			}
			if (agendaSCLocal.getEstado() != null &&
					(agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA || agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA)){
				if (franqueoOK){
					if(tr711s.getBreaks()!= null && tr711s.getBreaks().getBreakPairs() != null && tr711s.getBreaks().getBreakPairs().size() > 0){
						recursosbaBean.setQuiebrePcAgendaSc( tr711s.getBreaks().getBreakPairs(), peticionKey.peti_numero, productoSrvicioPeticionCollection, actDto.getCodigoActividad() );
					}
					Collection materiales = tr711s.getMaterials();
					if (materiales != null){
						granFor: for(Iterator materialesIterator = materiales.iterator(); materialesIterator.hasNext();){
							esEquipo = false;
							esDeco = false;
							esModem = false;
							esCamara = false;
							TR711SMaterials material = (TR711SMaterials) materialesIterator.next();
							
							if (!esDeco && !esModem){
								for (int i=0; i<arrayMaterialesEquipos.length;i++){
									if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesEquipos[i]) != -1){							
										listaEquipos.add(material);
										esEquipo = true;
										log.debug("El agendamiento:"+tr711s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un equipo");
										continue granFor;
									}
								}
							}
							
							if (!esEquipo && !esModem){
								for (int i=0; i<arrayMaterialesDecos.length;i++){
									if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesDecos[i])  != -1){
										listaDecos.add(material);
										esDeco = true;
										log.debug("El agendamiento:"+tr711s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un deco");
										continue granFor;
									}
								}
							}
							
							if (!esEquipo && !esDeco){
								for (int i=0; i<arrayMaterialesModems.length;i++){
									if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesModems[i]) != -1){
										listaModems.add(material);
										esModem = true;
										log.debug("El agendamiento:"+tr711s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un modem");
										continue granFor;
									}
								}
							}
							log.debug("esEquipo: "+esEquipo+" ,esModem: "+esModem+", esDeco: "+esDeco+", material.getTypeEquipment: "+material.getTypeEquipment());
							if(!esEquipo && !esModem && !esDeco){
								for (int i=0; i<arrayMaterialesCamara.length;i++){
									if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesCamara[i]) != -1){
										listaCamaras.add(material);
										esCamara = true;
										log.debug("El agendamiento:"+tr711s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es una cámara");
										continue granFor;
									}
								}
							}
							
							if (!esEquipo && !esDeco && !esModem){
								listaEquiposNoSerializados.add(material);
							}
						}
					}
					
					PeticionesDelegate pDelegate = new PeticionesDelegate();
					String deltaTicaId=pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.DELTA_TICA_ID);
					boolean hacerDelta = false;
					if(deltaTicaId != null){
						String[] arrayDeltaTicaId = deltaTicaId.trim().split(",");
						String ticaId = peticionLocal.getTica_id();
						for(int i = 0; i < arrayDeltaTicaId.length; i++){
							if(ticaId.equalsIgnoreCase(arrayDeltaTicaId[i])){
								hacerDelta = true;
								break;
							}
						}
					}
					if(tr711s.getItAnswer() == null || !tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM)){
						boolean esOCAutoInstalacion = recursosbaBean.esAutoinstalacion(agendaSCLocal);
						if(!esOCAutoInstalacion){
							//se asigna la variable cierro actividad con el valor del envio del mensaje ACS unicamente si se envia el mensaje queda false por la exclamacion que invierte el booleano Dcardena 
							//para que la actividad quede en espera del mensaje de acs
							cierroActividad= !insertarModem(listaModems,tr711s, peticionKey, estadosHomologados, actDto, esOCAutoInstalacion, peticionLocal);
							
						}
						insertarDecos(listaDecos,tr711s, peticionKey, estadosHomologados, actDto, peticionLocal, actividadEJB, codBandeja, hacerDelta);
						insertarCamaras(listaCamaras,tr711s, peticionKey, estadosHomologados, actDto, peticionLocal, actividadEJB, codBandeja, hacerDelta);
						insertarElemento(listaEquipos,tr711s, peticionKey, estadosHomologados, actDto, peticionLocal, actividadEJB, codBandeja, tieneEquipoValido);
						insertarElemNoSerial(listaEquiposNoSerializados,tr711s, peticionKey, estadosHomologados, actDto, peticionLocal, actividadEJB, codBandeja);
					}
					peticionLocal.setFlag_cp(tr711s.getCambioPlan());
					agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
					peticionLocal.setEstado_agend_sc(new Integer(ComunInterfaces.ESTADO_AGENDA_SC_SIN_MARCA));
					if (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION)){
						if(strObservaciones.length() > 1000){
							strObservaciones = strObservaciones.substring(0, 999);
						}
						actDto.setObservacion(strObservaciones);
					}
//					Cierro la actividad solamente si no ejecute una actualización de decos y si estoy en control instalación
					if(codBandeja != null)
						cierroActividad = true;
					
					if (cierroActividad && (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION)||codActividad.equals(ComunInterfaces.ACT_CTRL_DESINSTALACION))){
						String[] llaveWF = null;
						if (codBandeja != null){
							log.debug("Se deriva a: "+codBandeja);
							llaveWF = codBandeja.split("-");					
							actDto.setDato(llaveWF[0],llaveWF[1]);
						}
						actividadEJB.terminar(actDto);
					}else{
						if (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION)){
							log.debug("Quedo en espera de la respuesta de la activación de decos y tarjetas (TR-017-S)");
						}else{
							log.debug("Me encuentro en la actividad :"+codActividad+" por lo tanto no la cierro automáticamente, debe hacerse por la aplicación");
						}
					}
				}else{
					String codCancelacion = VpistbbaConfig.getVariable("CAUSALES_CANCELACION");
					String codigos[]=codCancelacion.split(",");
					boolean esCancelacion = false;
					
					for(int i =0; i<codigos.length;i++){
						if (tr711s.getPostageCode().equals(codigos[i])){
							esCancelacion=true;
							break;
						}
					}
					
					if (esCancelacion){
						log.debug("Se cancela la actuación pues el mensaje trae un código de franqueo de cancelación, Se debe derivar a PGC : Franqueo: "+tr711s.getPostageCode());
						actDto.setObservacion("Se cancela la actuación pues el mensaje trae un código de franqueo de cancelación: Franqueo: "+tr711s.getPostageCode()
								+" Por favor revise, se deriva a bandeja PGC");
						agendaSCLocal.setEstado(new Integer(ACTUACION_CANCELADA));
						
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
						actividadEJB.terminar(actDto);
					}else{
						log.debug("Se cierra la actuación pues el mensaje trae un código de franqueo no existoso: Franqueo: "+tr711s.getPostageCode());
						actDto.setObservacion("Se cierra la actuación pues el mensaje trae un código de franqueo no existoso: Franqueo: "+tr711s.getPostageCode()
								+" Por favor revise, y realice la actualización de inventarios de forma manual");
						agendaSCLocal.setEstado(new Integer(ACTUACION_CERRADA));
					}
				}
			}else{
				log.debug("El mensaje de cierre con ID: "+tr711s.getId()+" no se tiene en cuenta, porque la actuación:"+tr711s.getIdSchedule()+" se encuentra cerrada");
				actDto.setObservacion("El mensaje de cierre con ID: "+tr711s.getId()+" no se tiene en cuenta, porque la actuación:"+tr711s.getIdSchedule()+" se encuentra cerrada");
			}
		}catch(NamingException ex){
			log.error("Error en la instancia en la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.error("Error en la búsqueda en la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		}catch(TnProcesoExcepcion ex){
			log.error("Error en los procesos de la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		}catch (EJBException ex) {
			log.error("Error en creación de la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
		} 
		
	}
	/**
	 * @param actDto
	 */
	public void setearDatosActividad(ActividadEJBDTO actDto) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
//		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
		try {
			Estructura_InterfazLocalHome estructura_InterfazLocalHome= (Estructura_InterfazLocalHome)HomeFactory.getHome(Estructura_InterfazLocalHome.JNDI_NAME);
			Collection estructuraCollection = estructura_InterfazLocalHome.findByAci_id("WF", new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
			log.debug("Se recorre encontraron un npùmero de campos: "+estructuraCollection.size());
			for (Iterator iter = estructuraCollection.iterator(); iter.hasNext();) {
				Estructura_InterfazLocal element = (Estructura_InterfazLocal) iter.next();
				Estructura_InterfazKey estructura_InterfazKey = (Estructura_InterfazKey) element.getPrimaryKey();
				actDto.setDato(estructura_InterfazKey.aci_tag,element.getAci_default_value());
			}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al instanciar el BEAN");
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al formatear proceso en el Bean");
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error en la busqueda del la estructura");
		}
	}

	/**
	 * @param listaEquiposNoSerializados
	 * @param tr711s
	 * @param peticionKey
	 * @param estadosHomologados
	 * @param actDto
	 * @param peticionLocal
	 * @param actividadEJB
	 * @param codBandeja
	 */
	private void insertarElemNoSerial(ArrayList listaEquiposNoSerializados, TR711S tr711s, PeticionKey peticionKey, HashMap estadosHomologados, ActividadEJBDTO actDto, PeticionLocal peticionLocal, IActividadEJB actividadEJB, String codBandeja) {
		// TODO Apéndice de método generado automáticamente
		try{
			for (Iterator iter = listaEquiposNoSerializados.iterator(); iter.hasNext();) {
				TR711SMaterials materialTR = (TR711SMaterials) iter.next();
				log.debug("Voy a configurar y registrar equipos no serializados para el agendamiento:"+tr711s.getIdSchedule());
				DBManager manager;
				manager=new DBManager();
				manager.setDataSource(DBManager.JDBC_VPISTBBA);
				Long bi_id=new Long(manager.seqNextValLong("VPI_ELEM_NO_SERIAL_SEQ"));
				ElementoNoSerializadoLocalHome ensl = (ElementoNoSerializadoLocalHome)HomeFactory.getHome(ElementoNoSerializadoLocalHome.JNDI_NAME);
				ElementoNoSerializadoLocal elemento = ensl.create(bi_id,peticionLocal);
				elemento.setPeticion(peticionLocal);
				elemento.setAlmacenSap(materialTR.getStorageSAP());
				elemento.setAreaFuncSap(materialTR.getFuncAreaLongSAP());
				Integer cantidad = materialTR.getMaterialAmount();
				if(cantidad!=null){
					elemento.setCantidad(new Long(cantidad.longValue()));
				}
				elemento.setCentrCostSap(materialTR.getCostCenterSAP());
				elemento.setCentroSap(materialTR.getPlantSAP());
				elemento.setClaseMovSap(materialTR.getMoveTypeSAP());
				elemento.setCodLoteSap(materialTR.getBatchCodeSAP());
				elemento.setElementPepSap(materialTR.getPepElementSAP());
				elemento.setFecContSap(materialTR.getPostingDateSAP());
				elemento.setFlagMatSap(materialTR.getFlagMatSAP());
				elemento.setMarca(materialTR.getBrandEquipment());
				elemento.setModelo(materialTR.getModelEquipment());
				elemento.setNumMaterialSap(materialTR.getMaterialSAP());
				String posDocSap= materialTR.getMaterialCodeSAP();
				if(posDocSap!=null && !posDocSap.equals("")){
					elemento.setPosDocSap(new Integer(posDocSap));
				}
				elemento.setUndMedidaSap(materialTR.getMeasurementUnitSAP());
			}
		}catch(NamingException ex){
			log.error("Error en la instancia en la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		} catch(CreateException ex){
			log.error("Error en creación de la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		}
	}

	/**
	 * @param listaEquipos
	 * @param tr711s
	 * @param peticionKey
	 * @param estadosHomologados
	 * @param actDto
	 * @param peticionLocal
	 * @param actividadEJB
	 * @param codBandeja
	 * @param hacerDelta
	 */
	private void insertarElemento(ArrayList listaEquipos, TR711S tr711s, PeticionKey peticionKey, HashMap estadosHomologados, ActividadEJBDTO actDto, PeticionLocal peticionLocal, IActividadEJB actividadEJB, String codBandeja, boolean tieneEquipoValido) {
		// TODO Apéndice de método generado automáticamente
		try{
			if (listaEquipos != null && listaEquipos.size()>0){
				log.debug("Voy a configurar y registrar equipos para el agendamiento:"+tr711s.getIdSchedule());							
				Elemento_agenda_scLocalHome elementoAgendaSC = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
				Tipo_Eq_ElementoLocalHome tipoEqElementoLocalHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
				Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
				Elemento_PeticionLocalHome elementoPeticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
				
				for(int i = 0; listaEquipos.size() > i; i++){
					TR711SMaterials equipo = (TR711SMaterials)listaEquipos.get(i);
					
					Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendaSC.findByDescEquipo(equipo.getTypeEquipment());
					Collection tipoEqElementoCollection = tipoEqElementoLocalHome.findByIdElemento(elementoAgendaSCLocal.getId_elemento_atiempo());
					
					boolean tienePSTipoEqElem = false;
					
					for(Iterator tipoEqElementoIterator = tipoEqElementoCollection.iterator(); tipoEqElementoIterator.hasNext();){
						Tipo_Eq_ElementoLocal tipoEqElementoLocal = (Tipo_Eq_ElementoLocal)  tipoEqElementoIterator.next();
						Tipo_Eq_ElementoKey tipoEqElementoKey = (Tipo_Eq_ElementoKey)tipoEqElementoLocal.getPrimaryKey();
						Integer elementoAtiempo = elementoAgendaSCLocal.getId_elemento_atiempo();
						
						Collection psTipoEquipoCollection = psTipoEqLocalHome.findByIdTipoEquipo(tipoEqElementoKey.id_tipo_eq);
						Collection productoServicioPeticion = peticionLocal.getProducto_servicio_peticion();
						
						forPS: for(Iterator psTipoEquipoIterator = psTipoEquipoCollection.iterator(); psTipoEquipoIterator.hasNext();){
							Ps_Tipo_EqLocal psTipoEquipoLocal = (Ps_Tipo_EqLocal) psTipoEquipoIterator.next();
							
							Ps_Tipo_EqKey psTipoKey = (Ps_Tipo_EqKey)psTipoEquipoLocal.getPrimaryKey();
							
							for (Iterator productoServicioPeticionIterator = productoServicioPeticion.iterator(); productoServicioPeticionIterator.hasNext();){
								Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)productoServicioPeticionIterator.next();
								Short accion = new Short("0");
								
								if (psTipoKey.ps_id.intValue() == productoServicioPeticionLocal.getPsId().intValue()){
									String serial = equipo.getEquipmentSerialNumber();
									Long noTel = null;
									
									if (peticionLocal.getNum_ide_nu_stb()!= null)
										noTel = new Long (peticionLocal.getNum_ide_nu_stb());
									else
										noTel = new Long (0);
									
									if(estadosHomologados.containsKey(equipo.getActionType())){
										accion = new Short(estadosHomologados.get(equipo.getActionType()).toString());												
									}
									
									String tipoEquipo = tipoEqElementoKey.id_tipo_eq.toString();
									Long elemento = new Long (elementoAgendaSCLocal.getId_elemento_atiempo().longValue());
									Long psId = new Long (psTipoKey.ps_id.longValue());
									
									/* mfmendez - RQ5606 - Internet Movil*/
									String tipoInventario = "";
									Grpe_PsLocalHome grpePsLocalHome = null;
									Grpe_PsLocal grpePsLocal = null;
									try{
										grpePsLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
										grpePsLocal = grpePsLocalHome.findGrupoByPS(new Long(psTipoKey.ps_id.longValue()));													
									}catch(FinderException ex){
										log.debug("El PS "+psTipoKey.ps_id+" no se encuentra configurado en la tabla GRPE_PS en donde se asocia al tipo de inventario. " + ex);
										ex.printStackTrace();
									}finally{
										
										if(grpePsLocal != null && grpePsLocal.getGrpe_id() != null)
											tipoInventario = grpePsLocal.getGrpe_id().toString();
										else
											tipoInventario = "7";
									}
									
									
									Elemento_PeticionLocal elementoPeticionLocal = elementoPeticionLocalHome.create(serial,peticionLocal,noTel, accion,tipoEquipo,tipoInventario,elemento,psId);
									
									if(equipo.getBrandEquipment() != null)
										elementoPeticionLocal.setMarca(equipo.getBrandEquipment());
									
									if(equipo.getModelEquipment() != null)
										elementoPeticionLocal.setModelo(equipo.getModelEquipment());
									
									if(equipo.getExternalCodeEquipment() != null){
										elementoPeticionLocal.setNum_celular(equipo.getExternalCodeEquipment());
									}
									
									/* FIN mfmendez - RQ5606 - Internet Movil*/
									/*RQ.8595 - mfmendez*/
									elementoPeticionLocal.setFec_cont_sap(equipo.getPostingDateSAP());
									elementoPeticionLocal.setClase_mov_sap(equipo.getMoveTypeSAP());
									if(equipo.getMaterialCodeSAP() != null){
										elementoPeticionLocal.setPos_doc_sap(Integer.parseInt(equipo.getMaterialCodeSAP()));
									}else{
										elementoPeticionLocal.setPos_doc_sap(0);
									}
									elementoPeticionLocal.setNum_material_sap(equipo.getMaterialSAP());
									elementoPeticionLocal.setCentro_sap(equipo.getPlantSAP());
									elementoPeticionLocal.setAlmacen_sap(equipo.getStorageSAP());
									elementoPeticionLocal.setCod_lote_sap(equipo.getBatchCodeSAP());
									elementoPeticionLocal.setUnd_medida_sap(equipo.getMeasurementUnitSAP());
									elementoPeticionLocal.setCentr_cost_sap(equipo.getCostCenterSAP());
									elementoPeticionLocal.setArea_func_sap(equipo.getFuncAreaLongSAP());
									elementoPeticionLocal.setElement_pep_sap(equipo.getPepElementSAP());
									elementoPeticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
									elementoPeticionLocal.setFlag_mat_sap(equipo.getFlagMatSAP());
									/*FIN - RQ.8595 - mfmendez*/
									tienePSTipoEqElem = true;
									tieneEquipoValido = true;
									break forPS;
								}
							}
						}
					}
					/*mfmendez - Cambio solicitado por padilla para que guarde todos los equipos que lleguen en el cierre*/
					if(!tienePSTipoEqElem){
						log.debug("TR711S: ENTRO POR LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE.");
						Collection prodServPeticion = peticionLocal.getProducto_servicio_peticion();
						boolean encontroPS = false;
						Long psId = null;
						Ps_Tipo_EqLocal psTipEqLocal = null;
						
						if(prodServPeticion != null && prodServPeticion.size() > 0){
							
							/*Logica para buscar si alguno de los PS de la petición se encuentra en la tabla PS_TIPO_EQ,
							 * en caso de encontrar alguno guarda el equipo con ese PS*/
							for(Iterator iter = prodServPeticion.iterator(); iter.hasNext() && !encontroPS;){
								Producto_servicio_peticionLocal prodSerPetLocal = (Producto_servicio_peticionLocal) iter.next();
								if(prodSerPetLocal != null && prodSerPetLocal.getProducto_servicio() != null){
									Producto_servicioLocal prodSerLocal = (Producto_servicioLocal) prodSerPetLocal.getProducto_servicio();
									Producto_servicioKey prodSerKey = (Producto_servicioKey) prodSerLocal.getPrimaryKey();
									if(prodSerKey != null && prodSerKey.ps_id != null){
										try{
											psTipEqLocal = psTipoEqLocalHome.findTipoByPs(prodSerKey.ps_id.longValue());
											if(psTipEqLocal != null){
												log.debug("TR711S: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, ENCONTRO PS QUE ESTA EN PS_TIPO_EQ.");
												encontroPS = true;
												psId = prodSerKey.ps_id;
											}
										}catch(FinderException e){
											log.debug("FinderException: El PS "+prodSerKey.ps_id+" no se encuentra configurado en la tabla PS_TIPO_EQ.");
										}catch(Exception e){
											log.debug("Problema desconocido: El PS "+prodSerKey.ps_id+" no se encuentra configurado en la tabla PS_TIPO_EQ.");
										}
									}
								}
							}
						}
						
						/*Valida si encontró un PS de la petición en la tabla PS_TIPO_EQ*/
						if(encontroPS && psId != null && psTipEqLocal != null){
							log.debug("TR711S: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE VA A INTENTAR GUARDAR EL EQUIPO EN ELEMENTO_PETICION.");
							
							Short accion = new Short("0");
							Long noTel = null;
							
							if (peticionLocal.getNum_ide_nu_stb()!= null && !peticionLocal.getNum_ide_nu_stb().equals(""))
								noTel = new Long (peticionLocal.getNum_ide_nu_stb());
							else
								noTel = new Long (0);
							
							if(estadosHomologados.containsKey(equipo.getActionType())){
								accion = new Short(estadosHomologados.get(equipo.getActionType()).toString());
							}
							
							String tipoEquipo = "";
							if(psTipEqLocal != null && psTipEqLocal.getId_tipo_eq() != null){
								tipoEquipo = psTipEqLocal.getId_tipo_eq().toString();
							}
							Long elemento = new Long (elementoAgendaSCLocal.getId_elemento_atiempo().longValue());
							
							/*Por indicacion de Padilla por ahora se dena el tipo de inventario en 7, por corresponder a PDTI*/
							String tipoInventario = "7";
							//										
							String serialEq = equipo.getEquipmentSerialNumber();
							
							log.debug("TR711S: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE VA A INTENTAR CREAR EL REGISTRO EN LA TABLA.");
							Elemento_PeticionLocal elementoPeticionLocal = elementoPeticionLocalHome.create(serialEq,peticionLocal,noTel, accion,tipoEquipo,tipoInventario,elemento,psId);
							
							log.debug("TR711S: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE CREO EL REGISTRO EN LA TABLA Y SE VAN A GUARDAR LOS DEMAS DATOS.");
							if(equipo.getBrandEquipment() != null)
								elementoPeticionLocal.setMarca(equipo.getBrandEquipment());
							
							if(equipo.getModelEquipment() != null)
								elementoPeticionLocal.setModelo(equipo.getModelEquipment());
							
							if(equipo.getExternalCodeEquipment() != null){
								elementoPeticionLocal.setNum_celular(equipo.getExternalCodeEquipment());
							}
							elementoPeticionLocal.setFec_cont_sap(equipo.getPostingDateSAP());
							elementoPeticionLocal.setClase_mov_sap(equipo.getMoveTypeSAP());
							if(equipo.getMaterialCodeSAP() != null){
								elementoPeticionLocal.setPos_doc_sap(Integer.parseInt(equipo.getMaterialCodeSAP()));
							}else{
								elementoPeticionLocal.setPos_doc_sap(0);
							}
							elementoPeticionLocal.setNum_material_sap(equipo.getMaterialSAP());
							elementoPeticionLocal.setCentro_sap(equipo.getPlantSAP());
							elementoPeticionLocal.setAlmacen_sap(equipo.getStorageSAP());
							elementoPeticionLocal.setCod_lote_sap(equipo.getBatchCodeSAP());
							elementoPeticionLocal.setUnd_medida_sap(equipo.getMeasurementUnitSAP());
							elementoPeticionLocal.setCentr_cost_sap(equipo.getCostCenterSAP());
							elementoPeticionLocal.setArea_func_sap(equipo.getFuncAreaLongSAP());
							elementoPeticionLocal.setElement_pep_sap(equipo.getPepElementSAP());
							elementoPeticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
							elementoPeticionLocal.setFlag_mat_sap(equipo.getFlagMatSAP());
							
							tieneEquipoValido = true;
						}
					}
					/*FIN mfmendez - Cambio solicitado por padilla para que guarde todos los equipos que lleguen en el cirre*/
				}
			}else{//si no llegan equipos en la tr-711-s se eliminan los actuales de la base de datos
				log.debug("No vienen equipos, Voy a eliminar los equipos para el agendamiento:"+tr711s.getIdSchedule());
				Collection equiposBDList=peticionLocal.getElemento_peticion();
				if(equiposBDList != null && !equiposBDList.isEmpty()){
					Iterator equiposBDIt=null;
					List equiposEliminarList = new ArrayList();
					for(equiposBDIt=equiposBDList.iterator();equiposBDIt.hasNext();){
						Elemento_PeticionLocal elemento_PeticionLocal = (Elemento_PeticionLocal)equiposBDIt.next();
						equiposEliminarList.add(elemento_PeticionLocal);
					}
					Iterator equiposEliminarIterator = equiposEliminarList.iterator();
					while(equiposEliminarIterator.hasNext()){
						Elemento_PeticionLocal elemento_PeticionLocal = 
							(Elemento_PeticionLocal)equiposEliminarIterator.next();
						elemento_PeticionLocal.remove();
					}
				}else{
					log.debug("No se encontraron equipos para el agendamiento:"+tr711s.getIdSchedule());
				}
			}

		}catch (RemoveException ex) {
			log.error("Error en eliminación de registro en la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		} catch(CreateException ex){
			log.error("Error en creación de la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		}catch(NamingException ex){
			log.error("Error en la instancia en la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		} catch(FinderException ex){
			log.error("Error en la búsqueda en la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		}
	}

	/**
	 * @param listaCamaras
	 * @param tr711s
	 * @param peticionKey
	 * @param estadosHomologados
	 * @param actDto
	 * @param peticionLocal
	 * @param actividadEJB
	 * @param codBandeja
	 * @param hacerDelta
	 */
	private void insertarCamaras(ArrayList listaCamaras, TR711S tr711s, PeticionKey peticionKey, HashMap estadosHomologados, ActividadEJBDTO actDto, PeticionLocal peticionLocal, IActividadEJB actividadEJB, String codBandeja, boolean hacerDelta) {
		// TODO Apéndice de método generado automáticamente
		try{
			if(listaCamaras != null && listaCamaras.size()>0){
				log.debug("Voy a configurar y registrar cámaras para el agendamiento:"+tr711s.getIdSchedule());
				CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
				Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
				for (int i = 0; i < listaCamaras.size(); i++) {
					TR711SMaterials materialCamara = (TR711SMaterials)listaCamaras.get(i);
					CamaraKey camaraKey = new CamaraKey(peticionKey.peti_numero,materialCamara.getEquipmentSerialNumber());
					try{
						CamaraLocal camaraLocal = camaraLocalHome.findByPrimaryKey(camaraKey);
						if(ComunInterfaces.CAMARA_ACTIVA.equals(camaraLocal.getCameraState()) || ComunInterfaces.CAMARA_CAMBIO_PLAN_ACTIVO.equals(camaraLocal.getCameraState())){
							
							Elemento_agenda_scLocalHome elementoAgendaSC = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
							Tipo_Eq_ElementoLocalHome tipoEqElementoLocalHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
							Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendaSC.findByDescEquipo(materialCamara.getTypeEquipment());
							Collection tipoEqElementoCollection = tipoEqElementoLocalHome.findByIdElemento(elementoAgendaSCLocal.getId_elemento_atiempo());
							for (Iterator iter = tipoEqElementoCollection.iterator(); iter.hasNext();) {
								Tipo_Eq_ElementoLocal tipoEqElementoLocal = (Tipo_Eq_ElementoLocal)  iter.next();
								Tipo_Eq_ElementoKey tipoEqElementoKey = (Tipo_Eq_ElementoKey)tipoEqElementoLocal.getPrimaryKey();
								camaraLocal.setTipoEquipo(tipoEqElementoLocal.getId_tipo_eq().toString());
								camaraLocal.setTipoElemento(Long.valueOf(tipoEqElementoLocal.getId_elemento().toString()));
								
								Collection psTipoEquipoCollection = psTipoEqLocalHome.findByIdTipoEquipo(tipoEqElementoKey.id_tipo_eq);
								Collection productoServicioPeticion = peticionLocal.getProducto_servicio_peticion();
								
								forPS: for(Iterator psTipoEquipoIterator = psTipoEquipoCollection.iterator(); psTipoEquipoIterator.hasNext();){
									Ps_Tipo_EqLocal psTipoEquipoLocal = (Ps_Tipo_EqLocal) psTipoEquipoIterator.next();
									
									Ps_Tipo_EqKey psTipoKey = (Ps_Tipo_EqKey)psTipoEquipoLocal.getPrimaryKey();
									
									for (Iterator productoServicioPeticionIterator = productoServicioPeticion.iterator(); productoServicioPeticionIterator.hasNext();){
										Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)productoServicioPeticionIterator.next();
										Short accion = new Short("0");
										
										log.debug("materialCamara.getActionType: "+materialCamara.getActionType());
										if(materialCamara.getActionType()!=null){
											 accion = new Short(estadosHomologados.get(materialCamara.getActionType()).toString());
										}
										camaraLocal.setAccion(accion);
										
										log.debug("psTipoKey.ps_id.intValue: "+psTipoKey.ps_id.intValue()+", productoServicioPeticionLocal.getPsId: "+productoServicioPeticionLocal.getPsId().intValue());
										if (psTipoKey.ps_id.intValue() == productoServicioPeticionLocal.getPsId().intValue()){

											Long psId = new Long (psTipoKey.ps_id.longValue());
											String tipoInventario = "";
											Grpe_PsLocalHome grpePsLocalHome = null;
											Grpe_PsLocal grpePsLocal = null;
											try{
												grpePsLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
												grpePsLocal = grpePsLocalHome.findGrupoByPS(new Long(psTipoKey.ps_id.longValue()));													
											}catch(FinderException ex){
												log.debug("El PS "+psTipoKey.ps_id+" no se encuentra configurado en la tabla GRPE_PS en donde se asocia al tipo de inventario. " + ex);
												ex.printStackTrace();
											}finally{
												
												if(grpePsLocal != null && grpePsLocal.getGrpe_id() != null)
													tipoInventario = grpePsLocal.getGrpe_id().toString();
												else
													tipoInventario = "8";
											}
											camaraLocal.setPsId(psId);
											camaraLocal.setTipoInventario(tipoInventario);

											if(materialCamara.getBrandEquipment() != null)
												camaraLocal.setMarca(materialCamara.getBrandEquipment());
											
											if(materialCamara.getModelEquipment() != null)
												camaraLocal.setModelo(materialCamara.getModelEquipment());
											
											if(materialCamara.getExternalCodeEquipment() != null){
												camaraLocal.setNumCelular(materialCamara.getExternalCodeEquipment());
											}
											camaraLocal.setFecContSap(materialCamara.getPostingDateSAP());
											camaraLocal.setClaseMovSap(materialCamara.getMoveTypeSAP());
											if(materialCamara.getMaterialCodeSAP() != null){
												camaraLocal.setPosDocSap(Integer.valueOf(materialCamara.getMaterialCodeSAP()));
											}else{
												camaraLocal.setPosDocSap(Integer.valueOf("0"));
											}
											camaraLocal.setNumMaterialSap(materialCamara.getMaterialSAP());
											camaraLocal.setCentroSap(materialCamara.getPlantSAP());
											camaraLocal.setAlmacenSap(materialCamara.getStorageSAP());
											camaraLocal.setCodLoteSap(materialCamara.getBatchCodeSAP());
											camaraLocal.setUndMedidaSap(materialCamara.getMeasurementUnitSAP());
											camaraLocal.setCentrCostSap(materialCamara.getCostCenterSAP());
											camaraLocal.setAreaFuncSap(materialCamara.getFuncAreaLongSAP());
											camaraLocal.setElementPepSap(materialCamara.getPepElementSAP());
											camaraLocal.setFlagPetCurso(ComunInterfaces.FLAG_EQUIPO_PETICION);
											camaraLocal.setFlagMapSap(materialCamara.getFlagMatSAP());
											break forPS;
										}
									}
								}

							}
							
							
						}else{
							log.error("La cámara con serial " +  materialCamara.getEquipmentSerialNumber() + " no se encuentra activa.");
						}
					}catch(FinderException ex){
						log.error("La cámara con serial " +  materialCamara.getEquipmentSerialNumber() + " no fue encontrada en la tabla ATIEMPO.CAMARA");
					}
				}
			}
		}catch(NamingException ex){
			log.error("Error en la instancia en la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		}
	}

	/**
	 * @param listaModems
	 * @param tr711s
	 * @param peticionKey
	 * @param estadosHomologados
	 * @param actDto
	 * @param peticionLocal
	 * @param actividadEJB
	 * @param codBandeja
	 * @param hacerDelta
	 */
	private void insertarDecos(ArrayList listaDecos, TR711S tr711s, PeticionKey peticionKey, HashMap estadosHomologados, ActividadEJBDTO actDto, PeticionLocal peticionLocal, IActividadEJB actividadEJB, String codBandeja, boolean hacerDelta) {
		// TODO Apéndice de método generado automáticamente
		try{
			if (listaDecos != null && listaDecos.size()>0){
				log.debug("Voy a configurar y registrar decos para el agendamiento:"+tr711s.getIdSchedule());
				RecursosTVDelegate recursosTVDelegate = new RecursosTVDelegate();
				RecursosBADelegate recursosbaBean = new RecursosBADelegate();
				if (!recursosbaBean.esPostventa(peticionLocal))
					recursosTVDelegate.marcarNovedadQuiebrePeticionesVueloAgendaSC(peticionKey.peti_numero,listaDecos);
				recursosbaBean.agendamientoDecosTR711(listaDecos, peticionLocal, tr711s, actDto, actividadEJB,true ,codBandeja);
			}else if(hacerDelta){
				log.debug("No vienen decos, Voy a eliminar los decos para el agendamiento:"+tr711s.getIdSchedule());
				Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
				Collection decoTarjeta = peticionLocal.getDeco_tarjeta();
				ArrayList decoTarjetaEliminar = new ArrayList();
				for(Iterator decoTarjetaIterator = decoTarjeta.iterator(); decoTarjetaIterator.hasNext();){
					Deco_tarjetaLocal decoTarjetaAuxLocal = (Deco_tarjetaLocal)decoTarjetaIterator.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaAuxLocal.getPrimaryKey();
					Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
					Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
					try{			
						Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_deco, peticionKey.peti_numero);
						Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
						infoSAPDeco.remove();
					} catch (FinderException e) {
						log.debug("No se encontraron Decos para deco con id: "+decoTarjetaKey.id_deco+". Y id de peticion: "+peticionKey.peti_numero);
					} catch (Exception e) {
						log.error("RecursosBABean: Se presento Error borrando los datos de SAP para un Deco. "+e);
					}
					try{			
						Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_tarjeta, peticionKey.peti_numero);
						Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
						infoSAPCard.remove();
					} catch (FinderException e) {
						log.debug("No se encontraron Tarjetas para deco con id: "+decoTarjetaKey.id_tarjeta+". Y id de peticion: "+peticionKey.peti_numero);
					} catch (Exception e) {
						log.error("RecursosBABean: Se presento Error borrando los datos de SAP para una Tarjeta. "+e);
					}
					decoTarjetaAuxLocal.setAccion(new Integer(ComunInterfaces.accionParEliminar));
					decoTarjetaAuxLocal.setEstado(new Integer(ComunInterfaces.estadoParOk));
					decoTarjetaAuxLocal.setOpco_id(new Long(operacionParDesactivar));
					DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
					decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
					decoTarjetaDTO.setDeco_reference(decoTarjetaAuxLocal.getDeco_reference());
					decoTarjetaDTO.setOperationComercial(new Long(operacionParDesactivar));
					decoTarjetaDTO.setDecoSerial(decoTarjetaAuxLocal.getSerial_deco());
					decoTarjetaDTO.setDecoBrand(decoTarjetaAuxLocal.getDeco_marca());		
					decoTarjetaDTO.setDecoType(decoTarjetaAuxLocal.getDeco_reference());
					decoTarjetaEliminar.add(decoTarjetaDTO);
				}
				if(decoTarjetaEliminar != null && !decoTarjetaEliminar.isEmpty()){
					String idAgendaSC = tr711s.getIdSchedule()+"@"+tr711s.getId()+"@tr711"+"@"+true;
					RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
					recursosTVDelegate.enviaConfiguracionServiciosTVAgendaSC(peticionKey.peti_numero.longValue(), decoTarjetaEliminar, true, idAgendaSC);
				}
			}
		}catch (ATiempoAppEx e) {
			log.error("Error en eliminación de registro en la recepción del cierre de actuación " , e);
			e.printStackTrace();
		} catch (NamingException e) {
			log.error("Error en eliminación de registro en la recepción del cierre de actuación " , e);
			e.printStackTrace();
		}
	}

	/**
	 * @param listaModems
	 * @param tr711s
	 * @param peticionKey
	 * @param estadosHomologados
	 * @param actDto
	 * @param esOCAutoInstalacion
	 * @param peticionLocal
	 */
	

	private boolean insertarModem(ArrayList listaModems, TR711S tr711s, PeticionKey peticionKey, HashMap estadosHomologados, ActividadEJBDTO actDto, boolean esOCAutoInstalacion, PeticionLocal peticionLocal) {
		// TODO Apéndice de método generado automáticamente
		boolean envioMensACS=false;
		try {
			
			ACSServicioDelegate acsServicio = new ACSServicioDelegate();
			ModemLocalHome modemLocalhome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
			Collection modemsCollection = modemLocalhome.findPeticion(peticionKey.peti_numero);
			if (listaModems != null && listaModems.size()>0){
				log.debug("Voy a configurar y registrar modems para el agendamiento:"+tr711s.getIdSchedule());
				boolean yaSeGuardoModem = false;
				TR711SMaterials materialModemUtilizado = null;
				for(int i = 0; listaModems.size() > i; i++){
					TR711SMaterials modem = (TR711SMaterials)listaModems.get(i);
					Short accion = new Short("0");
					if (modem.getActionType().equalsIgnoreCase("Utilizado") 
							|| modem.getActionType().equalsIgnoreCase("Reutilizado")){
						materialModemUtilizado = modem;
					}
					String serialModemEnviado = null;
					if(materialModemUtilizado != null){
						serialModemEnviado = materialModemUtilizado.getEquipmentSerialNumber();
					}
					String serialModemActual = null;
					ModemKey modemKey = null;
					//Se obtiene el modem de la bd
					ModemLocal modemLocal = null;
					ModemLocal modemLocal2 = null;
					Collection modemsActualBD = modemLocalhome.findPeticion(peticionKey.peti_numero);
					for(Iterator modemsActualBDIterator=modemsActualBD.iterator();modemsActualBDIterator.hasNext();){
						modemLocal = (ModemLocal)modemsActualBDIterator.next();
						modemKey = (ModemKey) modemLocal.getPrimaryKey();
						serialModemActual = modemKey.serial;
					}
//					Si el modem se remplaza, se elimina el actual y se crea el nuevo modem
					if(serialModemActual!=null && serialModemEnviado!=null 
							&& !serialModemActual.equalsIgnoreCase(serialModemEnviado)){
						try {
							modemLocal.remove();
							this.crearModem(modem, modemLocal2, modemLocalhome, peticionKey.peti_numero,  serialModemEnviado, peticionKey, peticionLocal);
							ModemVpiDTO modemDTO = this.asignarModemDTO(modem, peticionKey);
							acsServicio.enviarAutoConfiguracion(modemDTO, actDto, peticionKey.peti_numero, esOCAutoInstalacion);
							yaSeGuardoModem = true;
							envioMensACS=true;
							break;
						} catch (EJBException e) {
							// TODO Bloque catch generado automáticamente
							log.error("Error en la instancia en la recepción del cierre de actuación " , e);
							e.printStackTrace();
						} catch (RemoveException e) {
							// TODO Bloque catch generado automáticamente
							log.error("Error en la instancia en la recepción del cierre de actuación " , e);
							e.printStackTrace();
						}
						}else{
							if(serialModemActual!=null && serialModemEnviado!=null 
									&& serialModemActual.equalsIgnoreCase(serialModemEnviado)){
								this.actualizarInfoModem( modemLocal, modem);
								if (modem.getActionType().equalsIgnoreCase("Utilizado") 
										|| modem.getActionType().equalsIgnoreCase("Reutilizado")){
									modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
									log.debug("Se cambia el estado del mòdem");
								}
								yaSeGuardoModem = true;
							}
						}
						if (!yaSeGuardoModem){
							if(serialModemActual==null && serialModemEnviado!=null ){
								this.crearModem(modem, modemLocal2, modemLocalhome, peticionKey.peti_numero,  serialModemEnviado, peticionKey,peticionLocal);
							}
							ModemVpiDTO modemDTO = this.asignarModemDTO(modem, peticionKey);
							acsServicio.enviarAutoConfiguracion(modemDTO, actDto, peticionKey.peti_numero, esOCAutoInstalacion);
							envioMensACS=true;
						}
					}
			}else{//Se eliminan los modems previamente configurados
				log.debug("No vienen Modems, voy a eliminar modems para el agendamiento:"+tr711s.getIdSchedule());
				for (Iterator modemsIter = modemsCollection.iterator(); modemsIter.hasNext();){
					ModemLocal modemLocal = (ModemLocal) modemsIter.next();
					ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
					ModemVpiDTO modemDTO = new ModemVpiDTO();
					modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemLiberar).shortValue()));
					modemDTO.setAccion(new Integer(ComunInterfaces.accionModemLiberar).shortValue());
					modemDTO.setPeti_numero(peticionKey.peti_numero);
					modemDTO.setMarca(modemLocal.getModem_marca());
					modemDTO.setModelo(modemLocal.getModelo());
					modemDTO.setSerial(modemKey.serial);
					acsServicio.eliminarModemACS(modemDTO, actDto, peticionKey.peti_numero,actDto.getCodigoActividad(), actDto.getIdActividadFlujo());

				}
			}
			
		} catch (NamingException e) {
			log.error("Error en la instancia en la recepción del cierre de actuación " , e);
			e.printStackTrace();
		} catch (FinderException e) {
			log.error("Error en la búsqueda en la recepción del cierre de actuación " , e);
			e.printStackTrace();
		} catch (ATiempoAppEx e) {
			log.error("Error en eliminación de registro en la recepción del cierre de actuación " , e);
			e.printStackTrace();
		}
	return envioMensACS;
	}

	/**
	 * @param modem
	 * @param peticionKey
	 * @return
	 * @throws ATiempoAppEx
	 */
	private ModemVpiDTO asignarModemDTO(TR711SMaterials modem, PeticionKey peticionKey) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		ModemVpiDTO modemDTO = new ModemVpiDTO();
		modemDTO.setPeti_numero(peticionKey.peti_numero);
		modemDTO.setMarca(modem.getBrandEquipment());
		modemDTO.setSerial(modem.getEquipmentSerialNumber());
		modemDTO.setModelo(modem.getModelEquipment());
		modemDTO.setCodMaterial(modem.getCodeMaterial());
		
		if (modem.getTypeEquipment().equals("MODEMSTD2P") || modem.getTypeEquipment().equals("MODEMSTD4P"))
			modemDTO.setTipo(new Integer(ComunInterfaces.identificadorConvencional).shortValue());
		else if (modem.getTypeEquipment().equals("MODEMWIFI"))
			modemDTO.setTipo(new Integer(ComunInterfaces.identificadorWiFi).shortValue());
		else
			modemDTO.setTipo(new Integer(0).shortValue());
		InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
		InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(peticionKey.peti_numero);
		Long telefono = null;
		if (informacionTecnicaDTO.LineaNueva.getTelefono()!= null && informacionTecnicaDTO.LineaNueva.getTelefono().length()>0)
			modemDTO.setTelefono(new Long(informacionTecnicaDTO.LineaNueva.getTelefono()));
		else
			modemDTO.setTelefono(new Long("0"));
		modemDTO.setAccion(new Integer(ComunInterfaces.accionModemNoAction).shortValue());
		modemDTO.setPostingDateSAP(modem.getPostingDateSAP());
		modemDTO.setMoveTypeSAP(modem.getMoveTypeSAP());
		modemDTO.setMaterialCodeSAP(modem.getMaterialCodeSAP());
		modemDTO.setMaterialSAP(modem.getMaterialSAP());
		modemDTO.setPlantSAP(modem.getPlantSAP());
		modemDTO.setStorageSAP(modem.getStorageSAP());
		modemDTO.setBatchCodeSAP(modem.getBatchCodeSAP());
		modemDTO.setMeasurementUnitSAP(modem.getMeasurementUnitSAP());
		modemDTO.setCostCenterSAP(modem.getCostCenterSAP());
		modemDTO.setFuncAreaLongSAP(modem.getFuncAreaLongSAP());
		modemDTO.setPepElementSAP(modem.getPepElementSAP());
		modemDTO.setFlagMatSAP(modem.getFlagMatSAP());
		return modemDTO;
	}

	/**
	 * @param modemLocal
	 * @param modem
	 */
	private void actualizarInfoModem(ModemLocal modemLocal, TR711SMaterials modem) {
		// TODO Apéndice de método generado automáticamente
		log.debug("Se actualiza la informacion SAP para el modem con serial: " + modem.getEquipmentSerialNumber());
//		modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
		modemLocal.setFec_cont_sap(modem.getPostingDateSAP());
		modemLocal.setClase_mov_sap(modem.getMoveTypeSAP());
		if(modem.getMaterialCodeSAP() != null){
			modemLocal.setPos_doc_sap(Integer.parseInt(modem.getMaterialCodeSAP()));
		}else{
			modemLocal.setPos_doc_sap(0);
		}
		modemLocal.setNum_material_sap(modem.getMaterialSAP());
		modemLocal.setCentro_sap(modem.getPlantSAP());
		modemLocal.setAlmacen_sap(modem.getStorageSAP());
		modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
		modemLocal.setUnd_medida_sap(modem.getMeasurementUnitSAP());
		modemLocal.setCentr_cost_sap(modem.getCostCenterSAP());
		modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
		modemLocal.setElement_pep_sap(modem.getPepElementSAP());
		modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
		modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
		/*FIN - RQ.8595 - mfmendez*/
	}

	/**
	 * @param modem
	 * @param modemLocal2
	 * @param modemLocalhome
	 * @param peti_numero
	 * @param peticionKey
	 * @param serialModemEnviado
	 * @param peticionLocal
	 */
	public void crearModem(TR711SMaterials modem, ModemLocal modemLocal, ModemLocalHome modemLocalhome, Long peti_numero, String serialModemEnviado, PeticionKey peticionKey, PeticionLocal peticionLocal) {
		try {
			// TODO Apéndice de método generado automáticamente
			log.debug("Se entra a crear Módem con serial:"+serialModemEnviado +" y petición: "+peti_numero);
			modemLocal = modemLocalhome.create(serialModemEnviado, peticionLocal, new Long(peticionLocal.getNum_ide_nu_stb()), new Short("1"));
			modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemOcupar).shortValue()));
			modemLocal.setModem_marca(modem.getBrandEquipment());
			modemLocal.setModelo(modem.getModelEquipment());
			modemLocal.setFec_cont_sap(modem.getPostingDateSAP());
			modemLocal.setClase_mov_sap(modem.getMoveTypeSAP());
			modemLocal.setTipo(new Integer(ComunInterfaces.identificadorWiFi));
			if(modem.getMaterialCodeSAP() != null)
				modemLocal.setPos_doc_sap(Integer.parseInt(modem.getMaterialCodeSAP()));
			else
				modemLocal.setPos_doc_sap(0);
			modemLocal.setNum_material_sap(modem.getMaterialSAP());
			modemLocal.setCentro_sap(modem.getPlantSAP());
			modemLocal.setAlmacen_sap(modem.getStorageSAP());
			modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
			modemLocal.setUnd_medida_sap(modem.getMeasurementUnitSAP());
			modemLocal.setCentr_cost_sap(modem.getCostCenterSAP());
			modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
			modemLocal.setElement_pep_sap(modem.getPepElementSAP());
			modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
			modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error en eliminación de registro en la recepción del cierre de actuación " , e);
			e.printStackTrace();
		}
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioInterfaz#recibirAutoConfiguracion(co.com.telefonica.atiempo.interfaces.atiempo.TR701S)
	 */
	public void recibirAutoConfiguracion(TR701S tr701s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}



}
