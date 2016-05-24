package co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

import co.com.atiempo.dto.DatosSAPDTO;
import co.com.atiempo.dto.ElementoDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CamaraKey;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocal;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.ElementoLocal;
import co.com.telefonica.atiempo.ejb.eb.ElementoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ElementoNoSerializadoKey;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_equipoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_equipoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocal;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocal;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020EPositionHeader;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020ERequestHeader;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025SDecoCard;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Dslam_apsc_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ElementoNoSerializadoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Familia_producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Servicio_basico_supl_apsc_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_modemLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;

import co.com.telefonica.atiempo.soltec.mensajeria.salida.ActualizacionInventarioEquipoMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.STNotificacionSAPMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.STVistaGlobalMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudConfiguracionEquipoMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudEquipoMQ;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicios.Tmp_Notificacion_SAPLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Tmp_Notificacion_SAPLocalHome;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: EquipoST
 */
public class EquipoSTBean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter {
	protected Logger log = LoggerFactory.getLogger (getClass ()) ;
	private Mensaje_estado_stLocalHome msgEstadoLineaLocalHome ;
	private Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome;
	private Peticion_stLocalHome peticionLocalHome ;
	private Mensaje_estadoLocalHome mensajeEstadoLocalHome ;

	private Recursos_baLocalHome recursos_baLocalHome;
	private Mensaje_estadoLocalHome mensaje_estadoLocalHome;
	private Dslam_apsc_lineaLocalHome dslam_apscLocalHome;
	private Servicio_basico_supl_apsc_lineaLocalHome servicio_basico_supl_apsc_lineaLocalHome;
	private ConectorLocalHome conectorLocalHome;
	private LocalidadLocalHome localidadLocalHome;
	private DepartamentoLocalHome departamentoLocalHome;
	private ModemLocalHome modemLocalHome;
	private Tmp_modemLocalHome tmp_modemHome;
	private DBManager dbSeq ;
	private Elemento_PeticionLocalHome elemento_peticionLocalHome;
	private SimpleDateFormat df ;
	private Tmp_equipoLocalHome tmp_equipoLocalHome;
	private Producto_servicioLocalHome producto_servicioLocalHome;
	private Mensaje_descarga_SAP_STLocalHome mensajeDescargaSAPLocalHome;

	/*
	 * Metodo modificador del Session EJB
	 *  (non-Javadoc)
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext (SessionContext ctx)
		throws EJBException, RemoteException
		{
			super.setSessionContext (ctx) ;
			// Creacion de DataSource
			dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
	
			// TODO: revisar este formato
			df = new SimpleDateFormat ("dd/MM/yyyy") ;
	
			buscaHome ();
		}
	
	  private void buscaHome (){
      	
      		try {
      	
      		//	Creacion de los Home
      		msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
      		recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
      		mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
      		peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);

      		dslam_apscLocalHome = (Dslam_apsc_lineaLocalHome) HomeFactory.getHome(Dslam_apsc_lineaLocalHome.JNDI_NAME);
      		servicio_basico_supl_apsc_lineaLocalHome = (Servicio_basico_supl_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_apsc_lineaLocalHome.JNDI_NAME);
      		conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
      		
      		recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
      		tmp_equipoLocalHome = (Tmp_equipoLocalHome) HomeFactory.getHome(Tmp_equipoLocalHome.JNDI_NAME);
      		localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
      		departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
      		elemento_peticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
      		modemLocalHome=(ModemLocalHome)HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
      		tmp_modemHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
      		producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
      		} catch (NamingException e) {
      			log.error(" Creacion de Local Home Nulls");
      		}
      	}
	  private void validaHome ()
		throws ATiempoAppEx
	{
		// Validacion de los Home
		if (
			msgEstadoLineaLocalHome == null || recursos_linea_basicaLocalHome == null
			|| mensajeEstadoLocalHome == null || peticionLocalHome == null 
			|| localidadLocalHome == null 
			|| departamentoLocalHome == null 
			|| conectorLocalHome == null 
			|| modemLocalHome == null
			|| recursos_baLocalHome == null
			)
			throw new ATiempoAppEx (ATiempoAppEx.NAMING);
	}

    
    
    public void enviarConfiguracionActualEquipo(String peticion, String id_actividad) throws ATiempoAppEx{
        
        
        
          	
    		try{
    			validaHome ();

    			Mensaje_estadoLocal mensajeEsperaLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
    			
    			boolean esRefrescar=false;
    			if(id_actividad.equals(""))
    			{
    				esRefrescar=true;
    				id_actividad=STConfig.getVariable("COD_ACTIVIDAD_REC_BA_AUTOMATICA");
    			}
    							
    			Peticion_stKey peticion_stkey = new Peticion_stKey(new Long(peticion));
    			Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
    			Collection producto_servicio_peticionstArray = peticionLocal.getProducto_servicio_peticion();

    			//Proceso para la validacion de los tipos de ps, que estan en el mensaje
    			 StringBuffer append = new StringBuffer();
    			 boolean bip = true;
    			 Producto_servicio_peticionLocal producto_servicio_peticionstLocal  = null;
    			Producto_servicio_stKey productoServicostKey = null;

    			 for(Iterator iter = producto_servicio_peticionstArray.iterator();iter.hasNext();){

    				producto_servicio_peticionstLocal= (Producto_servicio_peticionLocal) iter.next();
    				productoServicostKey=(Producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getPrimaryKey();

    				 if(bip){
    					 append.append(productoServicostKey.ps_id);
    					 bip = false;
    				 }else{
    					 append.append(" ,"+ productoServicostKey.ps_id .toString());
    				 }

    			 }

    			// TODO:  IMPLEMENTAR --> Condicion que valida los tipos de PS

    			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
    			TR027E tr027e = new TR027E();
    			tr027e.setId(String.valueOf(IdCorrelativo));

    			String phoneNumber = peticionLocal.getNum_ide_nu();
    			if (phoneNumber!=null && phoneNumber.trim()!= ""){
	    			if(!peticionLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.IT)){
	    				if (phoneNumber!=null && phoneNumber.trim()!= ""){
	        				if (phoneNumber.length()>8){ 
	        					phoneNumber=phoneNumber.substring(0,8);
	        				}
	        			}  
	    			}
    			}else{
    				phoneNumber="0";
    			}	
    						
    			tr027e.setPcId(phoneNumber);
    			tr027e.setAtisRequestNumber(((Peticion_stKey)peticionLocal.getPrimaryKey()).cod_ave_cd.longValue());
    			Peticion_stKey peticionKey = (Peticion_stKey) peticionLocal.getPrimaryKey();
    			tr027e.setAtiempoRequestNumber(peticionKey.cod_ave_cd.longValue());
    			tr027e.setProductServiceCode(peticionLocal.getCod_pro_ser_cd().longValue());
    			
    			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
    			Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
    			mensajeEstadoLocal.setPeticion_st(peticionLocal);

    			Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
    			mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));

    			
    			if(esRefrescar)
    				mensajeEstadoLocal.setAccion("R");
    						
    			mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
    			Mensaje_estadoKey mensaje_estadoKey =  (Mensaje_estadoKey) mensajeEsperaLocal.getPrimaryKey();
    			mensajeEstadoLocal.setCod_estado(new Integer(mensaje_estadoKey.cod_estado.intValue()));
    			mensajeEstadoLocal.setCod_actividad_generadora(id_actividad);
    			//TODO: Revisar luego estos campos y su mapeo.
    			if(peticionLocal.getCod_are_ges_cd()!=null)
    				mensajeEstadoLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
    			if(peticionLocal.getTel_cot_ds()!=null)
    				mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());

    			SolicitudConfiguracionEquipoMQ  configuracionActualBAMQ = new SolicitudConfiguracionEquipoMQ();
    			configuracionActualBAMQ.enviarMensaje(tr027e);

    		} catch (NumberFormatException e) {
    			log.error("NumberFormatException:",e);
    			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

    		} catch (CreateException e) {
    			log.error("CreateException:",e);
    			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

    		} catch (FinderException e) {
    			log.error("FinderException:",e);
    			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
    		} 
    		catch(Exception e)
    		{
    			log.error("Exception:",e);
    			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
    		}
    			
    	}
    
    public void recepcionConfiguracionActualEquipo(TR027S tr027s) throws ATiempoAppEx{

    	
    	BackendExecution bExecution = null;
    	try
    		{	
    			bExecution = BackendTraceUtil.initExecution(tr027s, log);
    			bExecution.setIdMensajeOp(tr027s.getId());
    			bExecution.startOperation();
    			validaHome ();

    			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
    			Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
    			Mensaje_estadoLocal mensajeManualLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEsperaManual)));
    			Mensaje_estado_stKey key = new Mensaje_estado_stKey(new Long(tr027s.getId()));
    			Mensaje_estado_stLocal mensajeEstadoLineaLocal = null;

    			try {
    				mensajeEstadoLineaLocal = msgEstadoLineaLocalHome.findByPrimaryKey(key);
    			} catch (FinderException fex) {
    				mensajeEstadoLineaLocal = null ;
    			}


    			/*
    			 * Validacion de existencia de la respuesta en la Base de Datos 
    			 * en la tabla Mensaje_Estado_Linea
*/
    			//Recursos_baLocal recursos_baLocal;
    			if (mensajeEstadoLineaLocal == null) {
    				log.warn("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr027s));
    				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr027s.getId(),ATiempoAppEx.EXCEPTION);
    			}

    			int cod_estado=mensajeEstadoLineaLocal.getCod_estado().intValue();
    			//Validacion del estado de la respuesta 
    			if (cod_estado == ComunInterfaces.estadoOk || cod_estado == ComunInterfaces.estadoEsperaManual) {
    				log.warn("El estado de la respuesta es diferente para ser procesado\n " + XMLUtilities.marshall(tr027s));
    				return;
    			}

    			Peticion_stLocal petLocal= mensajeEstadoLineaLocal.getPeticion_st();
    			Peticion_stKey pk= (Peticion_stKey) petLocal.getPrimaryKey();

    			boolean errorCode = false;
    			if (tr027s.getErrorCode()!= 0){
    				errorCode=true;
    			}    			
    			Peticion_stLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion_st();
    			String phoneNumber = peticionLocal.getNum_ide_nu();	
    			//int codigoAccion = Integer.parseInt(tr027s.getAction());
    			Ps_Tipo_EqLocalHome ps_TipoHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
    			if (tr027s.getErrorCode() == 0 && tr027s.getEquipments() !=null && tr027s.getEquipments().size()>0){
    			    //Correccion Defecto 00029962 'TR027S' doesn't have the trace
    				if (log==null)
    					log = LoggerFactory.getLogger (getClass ()) ;
    			    log.debug("No tengo Error en el mensaje");
    				 for (Iterator iterator = tr027s.getEquipments().iterator(); iterator.hasNext();) {
    				        TR027SEquipment elementoVpiDTO = (TR027SEquipment) iterator.next();
    				        String ps = String.valueOf(elementoVpiDTO.getProductServiceCode());
    				        Ps_Tipo_EqLocal psLocal = ps_TipoHome.findTipoByPs(elementoVpiDTO.getProductServiceCode());
    				        String tipo_e = psLocal.getId_tipo_eq().toString();
    				        
    		                PeticionesDelegate delegate = new PeticionesDelegate();
    		                String cameraEquipmentType = delegate.recuperarParametroFromPropertiesBD(ComunInterfaces.CAMERA_EQUIPMENT_TYPE);
    		                log.debug("cameraEquipmentType: "+cameraEquipmentType);
    		                log.debug("tipo_e: "+tipo_e);
    				        //Sí el tipo corresponde a cámara
    				        if(cameraEquipmentType.indexOf(tipo_e) != -1){
    				        	CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
    				        	CamaraLocal camara = camaraLocalHome.create(pk.cod_ave_cd,elementoVpiDTO.getElementSerial());
    				        	setValoresCamara(camara,elementoVpiDTO, phoneNumber, Integer.valueOf("0").intValue(), ps);
    				        }else{
    		                
    				        	//DAVID: 2011-02-11, si no es venta equipos se maneja como antes.
	    		                if(!peticionLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.IT)){
	    		                	Elemento_PeticionLocal elemento_peticionLocal = elemento_peticionLocalHome.create(elementoVpiDTO.getElementSerial(), 
	    	    		                       peticionLocal,new Long(phoneNumber), new Short("0"), 
	    	    		                       //AT-2509 Error ST PDTI - Despliegue de Equipos - 26/08/2009
	    	    		                       //elementoVpiDTO.getEquipmentType(), elementoVpiDTO.getInventoryType(),new Long(tipo_e) ,new Long(ps));
	    	    		                       elementoVpiDTO.getEquipmentType(), elementoVpiDTO.getInventoryType(),new Long(elementoVpiDTO.getElementType()) ,new Long(ps));
	    		                	/*RQ.8595 - mfmendez*/
	    		                	elemento_peticionLocal.setFec_cont_sap(elementoVpiDTO.getInvPostingDateSAP());
	    		                	elemento_peticionLocal.setClase_mov_sap(elementoVpiDTO.getInvMoveTypeSAP());
	    							if(elementoVpiDTO.getInvMaterialCodeSAP() != null){
	    								elemento_peticionLocal.setPos_doc_sap(Integer.parseInt(elementoVpiDTO.getInvMaterialCodeSAP()));
	    							}else{
	    								elemento_peticionLocal.setPos_doc_sap(0);
	    							}
	    							elemento_peticionLocal.setNum_material_sap(elementoVpiDTO.getInvMaterialSAP());
	    							elemento_peticionLocal.setCentro_sap(elementoVpiDTO.getInvPlantSAP());
	    							elemento_peticionLocal.setAlmacen_sap(elementoVpiDTO.getInvStorageSAP());
	    							elemento_peticionLocal.setCod_lote_sap(elementoVpiDTO.getInvBatchCodeSAP());
	    							elemento_peticionLocal.setUnd_medida_sap(elementoVpiDTO.getInvMeasurementUnitSAP());
	    							elemento_peticionLocal.setCentr_cost_sap(elementoVpiDTO.getInvCostCenterSAP());
	    							elemento_peticionLocal.setArea_func_sap(elementoVpiDTO.getInvFuncAreaLongSAP());
	    							elemento_peticionLocal.setElement_pep_sap(elementoVpiDTO.getInvPepElementSAP());
	    							elemento_peticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
	    							elemento_peticionLocal.setFlag_mat_sap(elementoVpiDTO.getInvFlagMatSAP());
	    							/*FIN - RQ.8595 - mfmendez*/
	    		                }else{
	    		                	Elemento_PeticionLocal elemento_peticionLocal = elemento_peticionLocalHome.create(elementoVpiDTO.getElementSerial(), 
	    	    		                       peticionLocal,new Long(0), new Short("0"),elementoVpiDTO.getEquipmentType(), elementoVpiDTO.getInventoryType(),
											   new Long(elementoVpiDTO.getElementType()) ,new Long(ps));
	    		                	//DAVID: 2011-02-11, Para venta equipos ST el phoneNumber puede ser String, se guarda en un campo nuevo llamado telefono_it.
	    		                	//Este es el que se extrae en la tr 028 si es venta equipos.
	    		                	elemento_peticionLocal.setTelefono_it(phoneNumber);
	    		                	/*RQ.8595 - mfmendez*/
	    		                	elemento_peticionLocal.setFec_cont_sap(elementoVpiDTO.getInvPostingDateSAP());
	    		                	elemento_peticionLocal.setClase_mov_sap(elementoVpiDTO.getInvMoveTypeSAP());
	    							if(elementoVpiDTO.getInvMaterialCodeSAP() != null){
	    								elemento_peticionLocal.setPos_doc_sap(Integer.parseInt(elementoVpiDTO.getInvMaterialCodeSAP()));
	    							}else{
	    								elemento_peticionLocal.setPos_doc_sap(0);
	    							}
	    							elemento_peticionLocal.setNum_material_sap(elementoVpiDTO.getInvMaterialSAP());
	    							elemento_peticionLocal.setCentro_sap(elementoVpiDTO.getInvPlantSAP());
	    							elemento_peticionLocal.setAlmacen_sap(elementoVpiDTO.getInvStorageSAP());
	    							elemento_peticionLocal.setCod_lote_sap(elementoVpiDTO.getInvBatchCodeSAP());
	    							elemento_peticionLocal.setUnd_medida_sap(elementoVpiDTO.getInvMeasurementUnitSAP());
	    							elemento_peticionLocal.setCentr_cost_sap(elementoVpiDTO.getInvCostCenterSAP());
	    							elemento_peticionLocal.setArea_func_sap(elementoVpiDTO.getInvFuncAreaLongSAP());
	    							elemento_peticionLocal.setElement_pep_sap(elementoVpiDTO.getInvPepElementSAP());
	    							elemento_peticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
	    							elemento_peticionLocal.setFlag_mat_sap(elementoVpiDTO.getInvFlagMatSAP());
	    							/*FIN - RQ.8595 - mfmendez*/
	    		                }
    				        }
    		            }
    				 mensajeEstadoLineaLocal.setTelefono(String.valueOf(phoneNumber));
    				
    				//actividadEJB.terminar(actDto);
    			}else
    			   {
    			    if (tr027s.getErrorCode() !=0 || errorCode) {
    					mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado);
    					if(mensajeEstadoLineaLocal.getAccion()!=null && mensajeEstadoLineaLocal.getAccion().equals("R"))
    						return;
    					 ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
    		             IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
    		             ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoLineaLocal.getCod_actividad_generadora());

    		             String codError = String.valueOf(tr027s.getErrorCode());
    		             String nombreClase = TR028S.class.getName();
    		             nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
    		             actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"S");

    		             //Raúl Triviño: Req 5935_Ajuste_Flujo_IT_Cons_Rec_APSC
    					 //actDto.setDato( DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
    					 
    		             //actDto.setObservacion("Error en la Actualizacion de Equipos.: " + tr027s.getErrorCode()+ ". " + tr027s.getErrorMessage());
    		             //actividadEJB.terminar(actDto);
    						//REQ TOA FASE III Dcardena
    						//METODO QUE ENVIA A PGI LA AVERIA
    						RecursosDelegate rd = new RecursosDelegate();
    						rd.hayPGIAveria(Long.toString(tr027s.getErrorCode()),tr027s.getErrorMessage(),"TR027S",mensajeEstadoLineaLocal.getPeti_numero().toString(),mensajeEstadoLineaLocal.getCod_actividad_generadora()  ,mensajeEstadoLineaLocal);
    						return;
    						//FIN REQ TOA FASE III 
    			}
    			    
    			   }
    			mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado);
    			if(mensajeEstadoLineaLocal.getAccion()!=null && mensajeEstadoLineaLocal.getAccion().equals("R"))
    				return;
    			
    			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
    			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
    			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoLineaLocal.getCod_actividad_generadora());
    			actividadEJB.terminar(actDto);
    		}		
    		catch(FinderException e){
    			bExecution.setErrorOp(true);	
    			e.printStackTrace();	
    			log.warn("FinderException",e);
    			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
    		}
    		catch (TnProcesoExcepcion e){
    			bExecution.setErrorOp(true);	
    			e.printStackTrace();
    			log.error("",e);
    			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
    		} 
    		catch(Exception e){
    			bExecution.setErrorOp(true);	
    			e.printStackTrace();
    			log.debug("EXCEPTION",e);
    			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
    		}finally{  
    			bExecution.endAll();
    		
    		}
    }
    
    /**
     * Coloca los valores del objeto equipment en el objeto camara
     * @param camara Camara
     * @param equipment Equipo
     * @param phoneNumber Número de teléfono
     * @param accionModem Acción
     * @param ps PS
     */
    private void setValoresCamara(CamaraLocal camara, TR027SEquipment equipment, String phoneNumber, int accionModem, String ps){
    	camara.setCameraState(ComunInterfaces.CAMARA_ACTIVA);
    	camara.setCameraDescription("Activa");
    	if(phoneNumber!=null)
    		camara.setTelefono(new Long(phoneNumber.trim()));
   		camara.setAccion(new Short(new Integer(accionModem).shortValue()));
        camara.setTipoEquipo(equipment.getEquipmentType());
        camara.setTipoInventario(equipment.getInventoryType());
        if(equipment.getElementType()!=null)
        	camara.setTipoElemento(new Long(equipment.getElementType()));
        if(ps!=null)
        	camara.setPsId(new Long(ps.trim()));
        camara.setFecContSap(equipment.getInvPostingDateSAP());
    	camara.setClaseMovSap(equipment.getInvMoveTypeSAP());
		if(equipment.getInvMaterialCodeSAP() != null){
			camara.setPosDocSap(Integer.valueOf(equipment.getInvMaterialCodeSAP()));
		}else{
			camara.setPosDocSap(Integer.valueOf("0"));
		}
		camara.setNumMaterialSap(equipment.getInvMaterialSAP());
		camara.setCentroSap(equipment.getInvPlantSAP());
		camara.setAlmacenSap(equipment.getInvStorageSAP());
		camara.setCodLoteSap(equipment.getInvBatchCodeSAP());
		camara.setUndMedidaSap(equipment.getInvMeasurementUnitSAP());
		camara.setCentrCostSap(equipment.getInvCostCenterSAP());
		camara.setAreaFuncSap(equipment.getInvFuncAreaLongSAP());
		camara.setElementPepSap(equipment.getInvPepElementSAP());	
		camara.setFlagPetCurso(ComunInterfaces.FLAG_EQUIPO_PETICION);
		camara.setFlagMapSap(equipment.getInvFlagMatSAP());
    }
    
    
    public Collection getEquiposST(Long idPeticion) throws ATiempoAppEx {
        Collection labels = new ArrayList();
        EquipoSTLocal elocal = null;
        Peticion_stLocalHome pLocalHome = null;
        Producto_servicio_peticionLocal producto_servicio_peticionLocal = null;
        Producto_servicio_stKey productoServicioKey = null;
        try {
            elocal = ((EquipoSTLocalHome) HomeFactory.getHome(EquipoSTLocalHome.JNDI_NAME)).create();
            pLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
        } catch (CreateException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        Peticion_stKey pkey = new Peticion_stKey(idPeticion);
        Peticion_stLocal plocal = null;
        try {
            plocal = pLocalHome.findByPrimaryKey(pkey);
            // Obtengo los ps de la peticion e itero sobre la lista devuelta
            Collection psList = plocal.getProducto_servicio_peticion();            
            for (Iterator iter = psList.iterator(); iter.hasNext();) {
                producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iter.next();
                productoServicioKey = (Producto_servicio_stKey) producto_servicio_peticionLocal.getProducto_servicio_st().getPrimaryKey();
                //Long ps = productoServicioKey.ps_id;
                Long ps= plocal.getCod_pro_ser_cd();
                Collection retorno = elocal.pestanaEquipos(ps);    
                if(retorno != null && retorno.size()>0){ 
                labels.add("PS");// Valor necesario para utilizarlo en la
                // creación de la pantalla dinámica
                // equipoST.jsp                
                labels.add(ps);
                labels.addAll(retorno);
                
                }
            }
        } catch (FinderException e1) {
            e1.printStackTrace();
        }

        return labels;
    }
    
    public Collection esTipoPDTI(Long ps_id) throws ATiempoAppEx {
        Collection retorno = new ArrayList();
        Ps_Tipo_EqLocal ps_tipo_eqLocal = null;
        Ps_Tipo_EqLocalHome psTipoEqLocalHome = null;
        Tipo_Eq_ElementoLocalHome tipo_eqHome = null;
        Tipo_Eq_ElementoLocal tipo_eqLocal = null;
        ElementoLocalHome elementoHome = null;
        try {
            psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
            elementoHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
            tipo_eqHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
        } catch (NamingException e2) {

        }

        try {

            ps_tipo_eqLocal  = psTipoEqLocalHome.findTipoByPs(ps_id.longValue());
            long idTipoEq = ps_tipo_eqLocal.getId_tipo_eq().longValue();
            Collection elementos = tipo_eqHome.findElementoByTipo(idTipoEq);
            return elementos;
        } catch (FinderException e1) {
            log.debug("No es un ps de PDTI");
        }
        return retorno;
    }

    /**
     * Retorna una lista de equipos para un ps
     */
    public Collection obtenerElementos(Long ps_id) throws ATiempoAppEx {
        Collection retorno = new ArrayList();
        Collection elementos = esTipoPDTI(ps_id);
        ElementoLocalHome elementoHome = null;
        try {
            elementoHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
        } catch (NamingException e2) {

        }
        Tipo_Eq_ElementoLocal tipo_eqLocal = null;
        if (elementos != null) {
            for (Iterator it = elementos.iterator(); it.hasNext();) {
                tipo_eqLocal = (Tipo_Eq_ElementoLocal) it.next();
                long idElemento = tipo_eqLocal.getId_elemento().longValue();
                try {
                    ElementoLocal e = (ElementoLocal) elementoHome.findElemento(idElemento);
                    retorno.add(e);
                } catch (FinderException e) {
                    e.printStackTrace();
                }
            }
        }
        return retorno;
    }

    /**
     * Retorna una lista con la descripción de los equipos para un ps
     */
    public Collection pestanaEquipos(Long ps_id) throws ATiempoAppEx {
        Collection retorno = new ArrayList();
        Collection aux = obtenerElementos(ps_id);
        if (aux != null) {
            ElementoLocal e = null;
            for (Iterator i = aux.iterator(); i.hasNext();) {
                e = (ElementoLocal) i.next();
                retorno.add(e.getDesc_elemento());
            }
        }

        return retorno;
    }
    
    public ArrayList recuperaEquiposST(Long nroPeticion)
	{
		
//			log.debug("Voy a recuperar los modems para la peticion:"+nroPeticion);
			boolean esAlta=false;
			boolean esTraslado=false;
			boolean esCambioPlan = false;
			ArrayList retorno=new ArrayList();
			ArrayList list=new ArrayList();
			ElementoLocalHome eHome = null;
			 try {
			if(peticionLocalHome==null)               
                    peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			
			eHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
			Peticion_stLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new Peticion_stKey(nroPeticion));
			Collection collection=peticionLocal.getElemento_peticion();			
			for(Iterator iterator=collection.iterator();iterator.hasNext();)
			{
				Elemento_PeticionLocal elementoLocal=(Elemento_PeticionLocal) iterator.next();
				ElementoDTO equipoDTO=new ElementoDTO();
				equipoDTO.setPs(elementoLocal.getPs_id());
				Producto_servicioLocal psLocal = (Producto_servicioLocal)producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(elementoLocal.getPs_id()));
				equipoDTO.setDesPs(psLocal.getPs_nombre());
				equipoDTO.setSerial(elementoLocal.getSerial());
				equipoDTO.setAccion(elementoLocal.getAccion().shortValue());
				equipoDTO.setTipo_equipo(elementoLocal.getTipo_equipo());
				equipoDTO.setTipo_elemento(elementoLocal.getTipo_elemento());
				
				//AT-2509 Error ST PDTI - Despliegue de Equipos - 26/08/2009
				//ElementoLocal elocal = eHome.findElemento(Long.parseLong(elementoLocal.getTipo_equipo()));
				ElementoLocal elocal = eHome.findElemento(elementoLocal.getTipo_elemento().longValue());
				
				equipoDTO.setDesElemento(elocal.getDesc_elemento());
				
				list.add(equipoDTO);
				log.debug("Se agrega a la lista el equipo");
				log.debug("Serial "+elementoLocal.getSerial());
				log.debug("Tipo_equipo "+elementoLocal.getTipo_equipo());
				log.debug("Tipo_elemento "+elementoLocal.getTipo_elemento());
				log.debug("DesElemento "+elocal.getDesc_elemento());
			}
			 } catch (FinderException e) {
                
                 e.printStackTrace();
             } catch (NamingException e) {
                
                 e.printStackTrace();
             }
             Collections.sort(list,new ElementoDTO());
			 return list;
	}
    
    //Correccion pdti 2.01.019 2.01.020 17/09/2009
    public Long enviaEquipoPorUtilizar(long codAveCd, String ult4Digitos,String tipoEquipo, String tipoElemento, Long idEmContratista)
    throws ATiempoAppEx {
        validaHome();
		
		try
		{
			if(tmp_modemHome==null)
				tmp_modemHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
			if(mensaje_estadoLocalHome==null)
				mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estadoLocal mensajeOk=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
	
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));

			Peticion_stKey key = new Peticion_stKey (new Long (codAveCd)) ;

			Peticion_stLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            Collection colTmpEquipo = tmp_equipoLocalHome.findByCodAveCd(new Long(codAveCd));
      
            Iterator iterTmpEquipo = colTmpEquipo.iterator();
        
            while (iterTmpEquipo.hasNext()) {
                Tmp_equipoLocal tmp_equipoLocal = (Tmp_equipoLocal) iterTmpEquipo.next();
                try {
                    tmp_equipoLocal.remove();
                } catch (EJBException e1) {
                    log.error("Error al enviar Equipo.", e1);
                } catch (RemoveException e1) {
                    log.error("Error al enviar Equipo.", e1);
                }
    }
   
    Producto_servicioLocalHome psHome = null;
    try {
        psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
    } catch (NamingException e) {
        log.error("NamingException. ProductoServicioLocalHome", e);
    }
    Long idPS = peticion.getCod_pro_ser_cd();
    
    /**
     * DAVID: Si es venta equipos y viene un ps tipo PC se debe extraer el ps no tipo PC de la tabla elemento peticion, para luego buscar en la tabla 
     * Grpe_PsLocal, de otra forma sale error.
     */
    Long idPsPcIT=null;
    
    String psITtipoPC = STConfig.getVariable("PS_IT_TIPO_PC");
	String[] listaPsITtipoPC = psITtipoPC.split(",");
	
	for (int contPsITtipoPC = 0; contPsITtipoPC <= listaPsITtipoPC.length - 1; contPsITtipoPC++) {
		int psITtipoPCInt = Integer.parseInt(listaPsITtipoPC[contPsITtipoPC]);
		if (idPS.intValue() == psITtipoPCInt) {
			log.debug("Es un ps tipo PC de IT, venta equipos..."+psITtipoPCInt);
			Collection elementoPetList=peticion.getElemento_peticion();
			Elemento_PeticionLocal elemento_PeticionLocal = null;
			if(elementoPetList!=null&&elementoPetList.size()>0){
				elemento_PeticionLocal=(Elemento_PeticionLocal)elementoPetList.iterator().next();//Para estos casos solo hay un registro porque solo se recupera un equipo.
				idPsPcIT=elemento_PeticionLocal.getPs_id();
			}
			break;
		}
	}    
    //Fin: DAVID.
    Producto_servicioLocal psLocal = null;
    try {
        psLocal = psHome.findByPrimaryKey(new Producto_servicioKey(idPS));
    } catch (FinderException e) {
        log.error("FinderException. PS No encontrado. [" + idPS + "]");
    } catch (Exception e) {
        log.error("Exception. PS No encontrado. [" + idPS + "]:" + e.getMessage());
    }
    
    DepartamentoKey departamentoKey = (DepartamentoKey) departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticion.getCod_dpt())).getPrimaryKey();
    Long id_grupo = new Long(0);        
    try {
    	
    	/**
         * DAVID: Si idPsPcIT no es nulo, es porque venía un ps tipo pc de IT y se extrajo el ps que no es tipo pc
         * de la tabla elementoPeticion.
         * 
         */
        if(idPsPcIT!=null){    	
        	idPS=idPsPcIT;
        }
        //Fin: DAVID.
        Grpe_PsLocalHome grpeLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
        Grpe_PsLocal grupo = grpeLocalHome.findGrupoByPS(idPS);
        id_grupo = grupo.getGrpe_id();
        
        
    } catch (NamingException e1) {
        e1.printStackTrace();
    }

    Collection equipos = new ArrayList();

    
    
    TR026EEquipment eq = new TR026EEquipment();
    eq.setElementType(tipoElemento);
    eq.setEquipmentType(tipoEquipo);
    eq.setElementSerial(ult4Digitos);
    equipos.add(eq);
        
    LocalidadKey localidadKey= (LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticion.getCod_loc())).getPrimaryKey();

    TR026E tr026e = new TR026E();
    tr026e.setAtisRequestNumber(key.cod_ave_cd.longValue());
    tr026e.setAtiempoRequestNumber(key.cod_ave_cd.longValue());
    tr026e.setInventoryType(id_grupo.toString());
    tr026e.setEquipments(equipos);
    //Correccion pdti 2.01.019 2.01.020 17/09/2009
    tr026e.setContractorId(idEmContratista.longValue());
    tr026e.setDepartment(departamentoKey.cod_dpt);
    tr026e.setLocation(localidadKey.cod_loc);
    tr026e.setProductServiceCode(idPS.longValue());
    tr026e.setId(String.valueOf(idCorrelativoMensaje));
  //  tr026e.setAtisRequestNumber(idPeticion);

    Mensaje_estado_stLocal msgLocal = msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
    msgLocal.setPeticion_st(peticion);
    msgLocal.setFecha_envio(df.format(new java.util.Date()));
    msgLocal.setCod_estado(new Integer(ComunInterfaces.estadoOk));

    new SolicitudEquipoMQ().enviarMensaje(tr026e);

   
    return (idCorrelativoMensaje);
} catch (NumberFormatException e) {
    log.error("Error al enviar Modem.", e);
    throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
} catch (FinderException e) {
    log.error("Error al enviar Modem.", e);
    throw new ATiempoAppEx(ATiempoAppEx.FINDER);
} catch (NamingException e) {
	log.error("Error Envio Modem.",e);
	throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
} catch (CreateException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
return null;
}
    public TR026S buscarRespuestaMensajeEquipo(Long codAveCd, Long idMensaje)
    throws ATiempoAppEx {
        validaHome();
		try
		{
		    if(msgEstadoLineaLocalHome==null)
				msgEstadoLineaLocalHome=(Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocal msgMoLocal = msgEstadoLineaLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(idMensaje));
			if (msgMoLocal == null)
				return null;

			Peticion_stLocal pLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(codAveCd));
            Collection c = pLocal.getTmp_equipo();
            for (Iterator it = c.iterator(); it.hasNext();) {
                Tmp_equipoLocal tmpdtLocal = (Tmp_equipoLocal) it.next();
                TR026S tr026s = (TR026S) XMLUtilities.unmarshall(tmpdtLocal.getXml());
                return tr026s;
            }
		} catch (FinderException e) {
		    log.error("Error al buscar Respuesta Equipo.", e);
		    throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (NamingException e) {
		    log.error("Error al buscar Respuesta Equipo.", e);
		    throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}
		return null;
    }
    
    public ArrayList loadJSP(TR026S tr026s){
        
        Collection titulos = 	null;
    	ElementoLocalHome elementoHome = null;
        ArrayList retorno = new ArrayList();
    	try {
    		elementoHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
    		titulos = 	elementoHome.findAll();	
    	
    	int tamanoLista = 0 ; 
    	int cantidadTipos = 0;
    	//Correccion AT-2525 10/09/2009
   	 	if (tr026s.getErrorCode() == 0) {
	    	 if(tr026s.getEquipments()!=null)
	    	 {
	    		 tamanoLista = tr026s.getEquipments().size();
	    	     ArrayList [] columnas = new ArrayList[titulos.size()+1];
	    	     String [] titles = new String[titulos.size()+1];	   
	    	
	    		 for(java.util.Iterator iterator=tr026s.getEquipments().iterator();iterator.hasNext();)
	    		 {
	    		 	TR026SEquipment equipo=(TR026SEquipment)iterator.next();
	    		 	ElementoDTO eDTO = new ElementoDTO();
	    		 	eDTO.setMarca(equipo.getBrand());
	    		 	eDTO.setModelo(equipo.getModel());
	    		 	eDTO.setSerial(equipo.getElementSerial());
	    		 	/*fmendez - rq6895 - interfaz SAP*/
				 	eDTO.setNumCelular(equipo.getCellphone());
				 	eDTO.setMaterialSAP(equipo.getMaterialSAP());
				 	eDTO.setCentroSAP(equipo.getPlantSAP());
				 	eDTO.setAlmacenSAP(equipo.getStorageSAP());
				 	eDTO.setNumDocComprasSAP(equipo.getNumPurchasingDocSAP());
				 	eDTO.setNumPosDocComprasSAP(equipo.getNumPositionPurchDocSAP());
				 	/*Fin fmendez - rq6895 - interfaz SAP*/
	    		 	 ElementoLocal el = elementoHome.findByPrimaryKey(new Integer(equipo.getEquipmentType()));
	    		 	eDTO.setIdElemento(el.getId_elemento().intValue());
	    		 	 eDTO.setTipo_equipo(el.getDesc_elemento());
	    		 	 retorno.add(eDTO);	
	    		 }
	    		  Collections.sort(retorno, new ElementoDTO());
	
	    	}
   	 	}else{
   	 	    return null;
   	 	}
    	} catch (NamingException e2) {
    		
    	} catch (FinderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	 return retorno; 
        }
    
    
    public void actualizaEquipoPorUtilizar(TR026S tr026s) throws ATiempoAppEx {

        // CR15338 - @Trace - ana santos - 04/08 - Inicio
        BackendExecution bExecution = null;

        try {
            bExecution = BackendTraceUtil.initExecution(tr026s, log);
            bExecution.setNumPetAtiempo(new Long(tr026s.getAtisRequestNumber()));
            bExecution.setIdMensajeOp(tr026s.getId());
            bExecution.startOperation();

            validaHome();
            // busca el registro del mensaje
            log.debug("Primero si es mensaje de error lo obviamos.");
            if (tr026s.getErrorCode() != 0) {
                log.debug("Respuesta con error:"+ XMLUtilities.marshall(tr026s));
                //Correccion Pdti 18/09/2009
                //return;
            }

            Mensaje_estado_stLocal mensajeEstadoSt = null;

			try
			{
				mensajeEstadoSt = msgEstadoLineaLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(tr026s.getId())));
			}
			catch (FinderException fex)
			{
				mensajeEstadoSt = null ;
			}

			if (mensajeEstadoSt == null)
			{
				log.debug (
					"No Existe Respuesta en la base de enviados\n "
					+ XMLUtilities.marshall (tr026s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr026s.getId(),ATiempoAppEx.EXCEPTION);
			}

			// Validacion del estado de la respuesta


			if (mensajeEstadoSt.getCod_estado().intValue() !=ComunInterfaces.estadoOk)
			{
				log.debug ("Es estado de la respuesta es diferente para ser procesado\n "
					+ XMLUtilities.marshall (tr026s));
				return;
			}

            // todo ok, se graba la respuesta

            Long idTmpEquipo = new Long(dbSeq.seqNextValLong("CORRELATIVO_TMP_MODEM"));
            if (tmp_equipoLocalHome == null)
                tmp_equipoLocalHome = (Tmp_equipoLocalHome) HomeFactory.getHome(Tmp_equipoLocalHome.JNDI_NAME);

            Tmp_equipoLocal tmpequipoLocal = tmp_equipoLocalHome.create(idTmpEquipo, mensajeEstadoSt.getPeticion_st(), XMLUtilities.marshall(tr026s));

        } catch (NumberFormatException e) {
            bExecution.setErrorOp(true);
            throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
        } catch (CreateException e) {
            bExecution.setErrorOp(true);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
        } catch (NamingException e) {
            bExecution.setErrorOp(true);
            throw new ATiempoAppEx(ATiempoAppEx.NAMING);
        } finally {
            bExecution.endAll();
            // CR15338 - @Trace - ana santos - 04/08 - FIN
        }

    }
    
    public void grabaEquiposST(Long nroPeticion, Long telAsignado, ArrayList equipos) {
        try {
            if (peticionLocalHome == null)
                peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
            if (elemento_peticionLocalHome == null)
                elemento_peticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
            Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(nroPeticion));
            Object [] lista = peticionLocal.getElemento_peticion().toArray();
            for (int i = 0; i< lista.length;i++) {
                Elemento_PeticionLocal elemento_peticionLocal = (Elemento_PeticionLocal) lista[i];
                elemento_peticionLocal.remove();
            }            
            for (Iterator iterator = equipos.iterator(); iterator.hasNext();) {            
                ElementoDTO elementoVpiDTO = (ElementoDTO) iterator.next();                
                //DAVID: 2011-02-11, Para venta equipos ST 
                if(!peticionLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.IT)){
	                Elemento_PeticionLocal elemento_peticionLocal = elemento_peticionLocalHome.create(elementoVpiDTO.getSerial(), peticionLocal,telAsignado, new Short(elementoVpiDTO.getAccion()), elementoVpiDTO
	                                        .getTipo_equipo(), elementoVpiDTO
	                                        .getTipo_inventario(), elementoVpiDTO
	                                        .getTipo_elemento(),elementoVpiDTO.getPs());
                }              
                
                else{
                	Elemento_PeticionLocal elemento_peticionLocal = elemento_peticionLocalHome.create(elementoVpiDTO.getSerial(), peticionLocal,telAsignado, new Short(elementoVpiDTO.getAccion()), elementoVpiDTO
                            .getTipo_equipo(), elementoVpiDTO
                            .getTipo_inventario(), elementoVpiDTO
                            .getTipo_elemento(),elementoVpiDTO.getPs());
                	//DAVID: 2011-02-11, Para venta equipos ST el phoneNumber puede ser String, se guarda en un campo nuevo llamado telefono_it.
                	//Este es el que se extrae en la tr 028 si es venta equipos.
                	elemento_peticionLocal.setTelefono_it(peticionLocal.getNum_ide_nu());
                }
                
                
            }
        } catch (Exception e) {
            log.debug("Exception", e);
        }
    }
    
    public boolean noHayEquipoParaActualizarInventarioST(Long nroPeticion)
    throws ATiempoAppEx {
        try {
            Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(nroPeticion));
            Collection collection = peticionLocal.getElemento_peticion();
            if (collection.size() == 0)
                return true;
            for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
                Elemento_PeticionLocal elementoLocal = (Elemento_PeticionLocal) iterator.next();
                if (elementoLocal.getAccion().shortValue() != 0)
                    return false;
            	}
            return true;
        	} catch (FinderException finderException) {
        	    throw new ATiempoAppEx(ATiempoAppEx.FINDER, finderException);
        	} catch (Exception e) {
        	    log.debug("Exception", e);
        	    throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
        	}
    }
    
    public void enviaActualizaInventarioEquipo(String peticion,String id_actividad) throws ATiempoAppEx {

        try {

            validaHome();
           // Mensaje_estadoLocal mesajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));

            Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(new Long(peticion)));
            Collection producto_servicio_peticionstArray = peticionLocal.getProducto_servicio_peticion();
            log.debug("Sacmos la Peticion para actualizar Intentario Equipo");
            // Proceso para la validacion de los tipos de ps, que estan en el
            // mensaje
            StringBuffer append = new StringBuffer();
            boolean bip = true;
			 Producto_servicio_peticionLocal producto_servicio_peticionstLocal  = null;
			Producto_servicio_stKey productoServicostKey = null;

			 for(Iterator iter = producto_servicio_peticionstArray.iterator();iter.hasNext();){

				producto_servicio_peticionstLocal= (Producto_servicio_peticionLocal) iter.next();
				productoServicostKey=(Producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getPrimaryKey();

					 if(bip){
						 append.append(productoServicostKey.ps_id);
						 bip = false;
					 }else{
						 append.append(" ,"+ productoServicostKey.ps_id .toString());
					 }

			 }
            

            Peticion_stKey peticionKey = (Peticion_stKey) peticionLocal.getPrimaryKey();

            log.debug("Sacamos la PS para actualizar Intentario BA");

            Collection equipos = peticionLocal.getElemento_peticion();
            
            //Se pregunta por la cámaras de esa petición, para ver sí hay ese tipo de equipos
            CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
            Collection camaras = camaraLocalHome.findByPeticion(new Long(peticion));
            boolean hayCamaras = camaras!=null && camaras.size()>0;
            if (!hayCamaras && equipos.size() == 0) {
                log.debug("INFO: No existen ni cámaras ni Modems Asociados a la Peticion");
                return;
            }
            TR028E tr028e = new TR028E();

//            String phoneNumber = peticionLocal.getNum_ide_nu();
//            if (phoneNumber != null && phoneNumber.trim() != "") {
//                if (phoneNumber.length() > 8) {
//                    phoneNumber = phoneNumber.substring(0, 8);
//                }
//            } else {
//                phoneNumber = "0";
//            }
            
            //DAVID: 2011-02-11, se valida que cuando es venta equipos, el teléfono pueda ser mayor que 8 en longitud.
            String phoneNumber = peticionLocal.getNum_ide_nu();
			if (phoneNumber!=null && phoneNumber.trim()!= ""){
    			if(!peticionLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.IT)){
    				if (phoneNumber!=null && phoneNumber.trim()!= ""){
        				if (phoneNumber.length()>8){ 
        					phoneNumber=phoneNumber.substring(0,8);
        				}
        			}  
    			}
			}else{
				phoneNumber="0";
			}	
			
			
            Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));

            tr028e.setId(String.valueOf(IdCorrelativo));
            //Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
    		tr028e.setAtisRequestNumber(peticionKey.cod_ave_cd.longValue());
            tr028e.setAtiempoRequestNumber(peticionKey.cod_ave_cd.longValue());
            ArrayList listaEquipos = new ArrayList();
            
            //De encontrar cámaras para la petición se envían esos equipos
            if(hayCamaras){
            	for (Iterator iter = camaras.iterator(); iter.hasNext();) {
					CamaraLocal camara = (CamaraLocal) iter.next();
                    if(!camara.getAccion().toString().equals("0")){
                        if (camara.getTelefono().intValue() != new Integer(phoneNumber).intValue()){
                        	tr028e.setPcId(phoneNumber);
                        }else{
                        	tr028e.setPcId(camara.getTelefono().toString());
                        }
	                    TR028EEquipment tr028elemento = new TR028EEquipment();
	                    setValoresCamara(tr028elemento,camara);
	                    listaEquipos.add(tr028elemento);
                    }
                }
            }
            
            for (Iterator iter = equipos.iterator(); iter.hasNext();) {

                Elemento_PeticionLocal elementoLocal = (Elemento_PeticionLocal) iter.next();
                
                //DAVID: 2011-02-11. Si es venta de equipos, se usa el teléfono tipo string guardado en el campo Telefono_it.
                if(!peticionLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.IT)){
	                if (elementoLocal.getTelefono().intValue() != new Integer(phoneNumber).intValue())
	                    tr028e.setPcId(phoneNumber);
	                else
	                    tr028e.setPcId(elementoLocal.getTelefono().toString());
                }else{
                	tr028e.setPcId(elementoLocal.getTelefono_it().toString());
                }

                TR028EEquipment tr028elemento = new TR028EEquipment();
                Elemento_PeticionKey elementoKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey();
                if (elementoLocal.getAccion().shortValue() == 0)
                    continue;
                if (elementoLocal.getAccion().shortValue() == ComunInterfaces.accionModemOcupar
                		|| elementoLocal.getAccion().shortValue() == ComunInterfaces.accionModemNoRecuperado//DAVID: Se adiciona esta opción para equipo no recuperado en venta de equipos ST, Feb 14 2011.
                        || elementoLocal.getAccion().shortValue() == ComunInterfaces.accionModemCambioNoRec
                        || elementoLocal.getAccion().shortValue() == ComunInterfaces.accionModemCambioAveriado)
                    tr028elemento.setElementSerial(elementoKey.serial);
                else
                    tr028elemento.setElementSerial("");
                
                tr028elemento.setEquipmentState(elementoLocal.getAccion().toString());
                tr028elemento.setElementType(elementoLocal.getTipo_elemento().toString());
                tr028elemento.setEquipmentType(elementoLocal.getTipo_equipo());
                tr028elemento.setInventoryType(elementoLocal.getTipo_inventario());
                tr028elemento.setProductServiceCode(peticionLocal.getCod_pro_ser_cd().longValue());

                /*RQ.8595 - mfmendez*/
				if(elementoLocal.getFec_cont_sap() != null)
					tr028elemento.setInvPostingDateSAP(elementoLocal.getFec_cont_sap());
				else
					tr028elemento.setInvPostingDateSAP("");
				
				if(elementoLocal.getClase_mov_sap() != null)
					tr028elemento.setInvMoveTypeSAP(elementoLocal.getClase_mov_sap());
				else
					tr028elemento.setInvMoveTypeSAP("");
				
				tr028elemento.setInvMaterialCodeSAP(Integer.toString(elementoLocal.getPos_doc_sap()));
				
				if(elementoLocal.getNum_material_sap() != null)
					tr028elemento.setInvMaterialSAP(elementoLocal.getNum_material_sap());
				else
					tr028elemento.setInvMaterialSAP("");
					
				if(elementoLocal.getCentro_sap() != null)
					tr028elemento.setInvPlantSAP(elementoLocal.getCentro_sap());
				else
					tr028elemento.setInvPlantSAP("");
				
				if(elementoLocal.getAlmacen_sap() != null)
					tr028elemento.setInvStorageSAP(elementoLocal.getAlmacen_sap());
				else
					tr028elemento.setInvStorageSAP("");
				
				if(elementoLocal.getCod_lote_sap() != null)
					tr028elemento.setInvBatchCodeSAP(elementoLocal.getCod_lote_sap());
				else
					tr028elemento.setInvBatchCodeSAP("");
				
				if(elementoLocal.getUnd_medida_sap() != null)
					tr028elemento.setInvMeasurementUnitSAP(elementoLocal.getUnd_medida_sap());
				else
					tr028elemento.setInvMeasurementUnitSAP("");
				
				if(elementoLocal.getCentr_cost_sap() != null)
					tr028elemento.setInvCostCenterSAP(elementoLocal.getCentr_cost_sap());
				else
					tr028elemento.setInvCostCenterSAP("");
				
				if(elementoLocal.getArea_func_sap() != null)
					tr028elemento.setInvFuncAreaLongSAP(elementoLocal.getArea_func_sap());
				else
					tr028elemento.setInvFuncAreaLongSAP("");
				
				if(elementoLocal.getElement_pep_sap() != null)
					tr028elemento.setInvPepElementSAP(elementoLocal.getElement_pep_sap());
				else
					tr028elemento.setInvPepElementSAP("");
				
				if(elementoLocal.getFlag_mat_sap() != null)
					tr028elemento.setInvFlagMatSAP(elementoLocal.getFlag_mat_sap());
				else
					tr028elemento.setInvFlagMatSAP("");
				/*FIN - RQ.8595 - mfmendez*/
				
                listaEquipos.add(tr028elemento);

            }
            tr028e.setEquipments(listaEquipos);
            //			Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado
            // Linea
            Mensaje_estado_stLocal mensajeEstadoSt = msgEstadoLineaLocalHome.create(IdCorrelativo);
            mensajeEstadoSt.setPeticion_st(peticionLocal);
            Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
            mensajeEstadoSt.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));
            mensajeEstadoSt.setFecha_envio(df.format(new java.util.Date()));            
            mensajeEstadoSt.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
            mensajeEstadoSt.setCod_actividad_generadora(id_actividad);
            
            boolean vieneSerialConLetras = false;
            try{
            	Integer.parseInt(phoneNumber);
            }catch(NumberFormatException e){
            	vieneSerialConLetras=true;
            	log.debug("El phoneNumber no es numérico, la petición es de venta equipos con el serial de equipo como phoneNumber. Pasa para peticiones con equipo solamente.");
            }
            
            int areaPhone = 0;
            int numeroPhone = 0;
            
            if(!vieneSerialConLetras){
            	if (phoneNumber.length() > 1) {
                    areaPhone = Integer.parseInt(phoneNumber.substring(0, 1));
                    numeroPhone = Integer.parseInt(phoneNumber.substring(1));
                }
                mensajeEstadoSt.setArea(new Integer(areaPhone));
                mensajeEstadoSt.setTelefono(String.valueOf(numeroPhone));
            }

            ActualizacionInventarioEquipoMQ actualizaInventarioEquipoMQ = new ActualizacionInventarioEquipoMQ();
            actualizaInventarioEquipoMQ.enviarMensaje(tr028e);

        } catch (NumberFormatException e) {
            log.warn("NumberFormatException", e);
            throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
        } catch (CreateException e) {
            log.warn("CreateException", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

        } catch (FinderException e) {
            log.warn("FinderException", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

        } catch (Exception e) {
            log.debug("Exception", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
        }

    }
    
    /**
     * Coloca los valores del objeto camara en el objeto equipo
     * @param equipment Objeto Equipo
     * @param camara Objeto Cámara
     */
    private void setValoresCamara(TR028EEquipment equipment, CamaraLocal camara){
    	CamaraKey key = (CamaraKey) camara.getPrimaryKey();
        equipment.setElementSerial(key.cameraSerial);
        if(camara.getAccion()!=null)
        	equipment.setEquipmentState(camara.getAccion().toString());
        if(camara.getTipoElemento()!=null)
        	equipment.setElementType(camara.getTipoElemento().toString());
        equipment.setEquipmentType(camara.getTipoEquipo());
        equipment.setInventoryType(camara.getTipoInventario());
        if(camara.getPsId()!=null)
        	equipment.setProductServiceCode(camara.getPsId().longValue());
		
        if(camara.getFecContSap() != null)
			equipment.setInvPostingDateSAP(camara.getFecContSap());
		else
			equipment.setInvPostingDateSAP("");
		
		if(camara.getClaseMovSap() != null)
			equipment.setInvMoveTypeSAP(camara.getClaseMovSap());
		else
			equipment.setInvMoveTypeSAP("");
		
		if(camara.getPosDocSap() != null)
			equipment.setInvMaterialCodeSAP(camara.getPosDocSap().toString());
		else
			equipment.setInvMaterialCodeSAP("");
		
		if(camara.getNumMaterialSap() != null)
			equipment.setInvMaterialSAP(camara.getNumMaterialSap());
		else
			equipment.setInvMaterialSAP("");
			
		if(camara.getCentroSap() != null)
			equipment.setInvPlantSAP(camara.getCentroSap());
		else
			equipment.setInvPlantSAP("");
		
		if(camara.getAlmacenSap() != null)
			equipment.setInvStorageSAP(camara.getAlmacenSap());
		else
			equipment.setInvStorageSAP("");
		
		if(camara.getCodLoteSap() != null)
			equipment.setInvBatchCodeSAP(camara.getCodLoteSap());
		else
			equipment.setInvBatchCodeSAP("");
		
		if(camara.getUndMedidaSap() != null)
			equipment.setInvMeasurementUnitSAP(camara.getUndMedidaSap());
		else
			equipment.setInvMeasurementUnitSAP("");
		
		if(camara.getCentrCostSap() != null)
			equipment.setInvCostCenterSAP(camara.getCentrCostSap());
		else
			equipment.setInvCostCenterSAP("");
		
		if(camara.getAreaFuncSap() != null)
			equipment.setInvFuncAreaLongSAP(camara.getAreaFuncSap());
		else
			equipment.setInvFuncAreaLongSAP("");
		
		if(camara.getElementPepSap() != null)
			equipment.setInvPepElementSAP(camara.getElementPepSap());
		else
			equipment.setInvPepElementSAP("");
		
		if(camara.getFlagMapSap() != null)
			equipment.setInvFlagMatSAP(camara.getFlagMapSap());
		else
			equipment.setInvFlagMatSAP("");
    }
	
	public boolean getEstadoMultipleMensajes(Mensaje_estado_stLocal mensajeEstadoLineaLocal, Integer estado){
		
		Mensaje_estado_stLocal mensaje_estado_stLocal2 = null;  
		Mensaje_estado_stKey mensaje_estado_stKey = (Mensaje_estado_stKey) mensajeEstadoLineaLocal.getPrimaryKey();
		       
	   try {
	   	
	   		//Arreglo de registros de peticion pendientes
			Collection estadoMensajes = msgEstadoLineaLocalHome.findEstadoPeticion(mensajeEstadoLineaLocal.getPeti_numero(), estado, mensajeEstadoLineaLocal.getCod_actividad_generadora());
			
			//Si queda el 1, siempre sera el ultimo
			if(estadoMensajes.size() == 1){
				
				for (Iterator iter= estadoMensajes.iterator();iter.hasNext();){
					
					mensaje_estado_stLocal2 = (Mensaje_estado_stLocal)iter.next();
					Mensaje_estado_stKey mensaje_estado_stKey2 = (Mensaje_estado_stKey) mensaje_estado_stLocal2.getPrimaryKey();
				//Comparacion de corralativos de mensajes
					if(mensaje_estado_stKey2.correlativo.longValue() == mensaje_estado_stKey.correlativo.longValue()){
						
						return true;
					}
					
				}
				
				return false;
				
			}else{
				
				return false;
			}
	   }
	   catch (FinderException fex)
	   {
			return false;
	   }
		
		
	}
	
	private boolean hayMsgError(Long idPeticion, String codActividad) throws ATiempoAppEx{
		boolean hayHorror = false;
		try{
			Mensaje_estado_stLocalHome msgHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Collection mensajes = msgHome.findEstadoPeticion(idPeticion, new Integer(ComunInterfaces.estadoError),codActividad);
			if (mensajes == null || mensajes.size()<1){
				hayHorror = false;
			}else{
				hayHorror = true;	
			}
		}catch(Exception e){
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);			
		}
		return hayHorror;
	}
	
	
	
	public void recepcionActualizacionInventarioEquipoST(TR028S tr028s) throws ATiempoAppEx{
		// Se agrega Trace - German P. 31/07/09
		BackendExecution bExecution = null;
		try{		
			bExecution = BackendTraceUtil.initExecution(tr028s, log);
			bExecution.setIdMensajeOp(tr028s.getId());
			bExecution.setNumPetAtiempo(new Long(tr028s.getAtiempoRequestNumber()));
			bExecution.setNumPetAtis(new Long(tr028s.getAtisRequestNumber()));
			bExecution.startOperation();
			validaHome ();

			
			Mensaje_estado_stKey key = new Mensaje_estado_stKey(new Long(tr028s.getId()));
	
			Mensaje_estado_stLocal mensajeEstadoLineaLocal = null;

			try {
				mensajeEstadoLineaLocal = msgEstadoLineaLocalHome.findByPrimaryKey(key);
			} catch (FinderException fex) {
				mensajeEstadoLineaLocal = null ;
			}
   
			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */

			if (mensajeEstadoLineaLocal == null) {
				log.warn(
					"No Existe Respuesta en la base de enviados\n "
					+ XMLUtilities.marshall(tr028s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr028s.getId(),ATiempoAppEx.EXCEPTION);
			}


			Peticion_stLocal petLocal= mensajeEstadoLineaLocal.getPeticion_st();
			Peticion_stKey pk= (Peticion_stKey) petLocal.getPrimaryKey();


			if (tr028s.getErrorCode() == 0){
				Recursos_baLocal recursos_baLocal = null;
				Mensaje_estado_stLocal mensajeEstadoLineaLocalFor = null;
				boolean insert = false;
				
				Collection recursosLineaBaBasica = petLocal.getRecursos_ba();
				log.debug("Existen Recursos " + recursosLineaBaBasica.size());
				if ( recursosLineaBaBasica.size() == 0){
					insert = true;
				}else{
					log.debug("Actualiza Recursos BA");
					insert = false;
					recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
				}
			
				if (insert){
					log.debug("Inserta Recursos BA");
					Long idConDos =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					recursos_baLocal = recursos_baLocalHome.create(idConDos,petLocal);
				}
				Mensaje_estado_stKey mensaje_estado_stKey = (Mensaje_estado_stKey) mensajeEstadoLineaLocal.getPrimaryKey();	

			}
			mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());

			if (tr028s.getErrorCode() !=0) {
	              
			         ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
	                IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
	                ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoLineaLocal.getCod_actividad_generadora());

	                String codError = String.valueOf(tr028s.getErrorCode());
	                String nombreClase = TR028S.class.getName();
	                nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
	                actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"S");
	                actDto.setObservacion("Error en la Actualizacion de Equipos.: " + tr028s.getErrorCode()+ ". " + tr028s.getErrorMessage());
	                actividadEJB.terminar(actDto);

	                return;
			}

				
			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoLineaLocal.getCod_actividad_generadora());
			actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.RECUPERA_INF_BA.fluj_mult_rec_inf_ba,"NActInvEquipo");
			actividadEJB.terminar(actDto);
			
			mensajeEstadoLineaLocal.setFecha_cierre(df.format(new java.util.Date()));
			mensajeEstadoLineaLocal.setCod_estado(new Integer(ComunInterfaces.estadoOk));

			} catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			} catch (CreateException e) {
				bExecution.setErrorOp(true);
				log.error("CreateException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			}  catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);
				log.error("TnProcesoExcepcion:",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			}catch(Exception e)
			{
				bExecution.setErrorOp(true);
				log.error("Exception:",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}finally{  
				bExecution.endAll();
			
			}
			
	}
	
	public String elementoPeticionDePSPCIT(Long idPeticion) throws ATiempoAppEx{
		String psPcITNombre=null;
		try{
		Peticion_stLocalHome peticion_stLocalHome=(Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
		Producto_servicioLocalHome producto_servicioLocalHome=(Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		Peticion_stKey key = new Peticion_stKey (idPeticion) ;
		Peticion_stLocal peticionSt = peticion_stLocalHome.findByPrimaryKey (key) ;
		
		Long idPS = peticionSt.getCod_pro_ser_cd();

		String psITtipoPC = STConfig.getVariable("PS_IT_TIPO_PC");
		String[] listaPsITtipoPC = psITtipoPC.split(",");
		
		for (int contPsITtipoPC = 0; contPsITtipoPC <= listaPsITtipoPC.length - 1; contPsITtipoPC++) {
			int psITtipoPCInt = Integer.parseInt(listaPsITtipoPC[contPsITtipoPC]);
			if (idPS.intValue() == psITtipoPCInt) {
				log.debug("Es un ps tipo PC de IT, venta equipos..."+psITtipoPCInt);
				Collection elementoPetList=peticionSt.getElemento_peticion();

				Iterator elementoPetListIt=elementoPetList.iterator();
				if(elementoPetListIt.hasNext()){
					//Para estos casos solo hay un registro porque solo se recupera un equipo.
					Elemento_PeticionLocal elemento_PeticionLocal = (Elemento_PeticionLocal)elementoPetListIt.next();
					Producto_servicioKey producto_servicioKey = new Producto_servicioKey(elemento_PeticionLocal.getPs_id());
					Producto_servicioLocal producto_servicioLocal=producto_servicioLocalHome.findByPrimaryKey(producto_servicioKey);
					psPcITNombre=producto_servicioLocal.getPs_nombre();
					break;
				}
			}
		} 
		
		return psPcITNombre;
		}catch(NamingException e){
			log.debug("Error de nombrado en método elementoPeticionDePSPCIT().."+e.toString());
			return psPcITNombre;			
		}catch(FinderException e){
			log.debug("No se encontró objeto en método elementoPeticionDePSPCIT().."+e.toString());
			return psPcITNombre;
		}catch(Exception e){
			log.debug("Error en método elementoPeticionDePSPCIT().."+e.toString());
			return psPcITNombre;
		}
		
	}
	
	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaVistaGlobalST(TR025S tr025s) throws ATiempoAppEx{
		
		BackendExecution bExecution = null;

		try {
	        bExecution = BackendTraceUtil.initExecution(tr025s, log);
	        bExecution.setNumPetAtiempo(new Long(tr025s.getAtiempoRequestNumber()));
	        bExecution.setIdMensajeOp(tr025s.getId());
	        bExecution.startOperation();
	        
	        Mensaje_estado_stLocal mensajeEstadoSt;
	        try {
	        	mensajeEstadoSt = msgEstadoLineaLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(tr025s.getId())));
	        } catch (FinderException e1) {
	        	mensajeEstadoSt = null;
	        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr025s));
	            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr025s.getId(), ATiempoAppEx.FINDER);
	        }
	        			        
	        ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB () ;
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoSt.getCod_actividad_generadora ()) ;
	    	ActividadEJBDTO actDto ;	   		    	
	    	
	    	if(tr025s != null && tr025s.getError() != null && tr025s.getError().equals("0")){
	    		/*obtiene la peticion Local de acuerdo al numero atiempo que llega en el mensaje*/	    		
		        Peticion_stKey key = new Peticion_stKey(new Long(tr025s.getAtiempoRequestNumber()));
		        Peticion_stLocal peticionStLocal = peticionLocalHome.findByPrimaryKey(key);
		        
		        /*Analisis de los modems (Modem) para marcar los que se deben enviar a SAP*/
		        Collection mBD = peticionStLocal.getModem() != null ? peticionStLocal.getModem() : new ArrayList(); //Modem de la petición soltec en BD
		        String modemSerialTr = tr025s.getModemSerial(); //Serial del modem de la vista global, en la tr
		        		        	
	        	for (Iterator modemsBdIter = mBD.iterator(); modemsBdIter.hasNext();){
	        		ModemLocal modemLocal = (ModemLocal) modemsBdIter.next();
					ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
					log.debug("Se valida mòdem con serial: " +modemKey.serial + "y el modem de vista global: "+ modemSerialTr);
					/*Se valida si el modem se debe enviar a SAP o no. Se adiciona la validacion del nuevo flag flag_mat_sap 
					 * que debe llegar de Agenda*/
					if(modemKey.serial != null && 
							(modemLocal.getAccion().intValue() == ComunInterfaces.accionParActivar 
									|| modemLocal.getAccion().intValue() == ComunInterfaces.accionModemConfigurado)&&
							modemSerialTr == null&&
							modemLocal.getFlag_mat_sap() != null &&
							modemLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
						
						modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
					}else if(modemKey.serial != null && 
							(modemLocal.getAccion().intValue() == ComunInterfaces.accionParActivar 
									|| modemLocal.getAccion().intValue() == ComunInterfaces.accionModemConfigurado)&&
							modemSerialTr != null && 
							!modemKey.serial.equals(modemSerialTr) &&
							modemLocal.getFlag_mat_sap() != null &&
							modemLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
						
						modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
					}		        	
	        	}
		        	
		        /*Analisis de los equipos (Elemento_Peticion) para marcar los que se deben enviar a SAP*/
		        Collection ePBD = peticionStLocal.getElemento_peticion() != null ? peticionStLocal.getElemento_peticion() : new ArrayList(); //Elemento_Peticion de la petición soltec en BD
		        Collection ePTr = tr025s.getEquipments() != null ? tr025s.getEquipments() : new ArrayList(); //Elemento_Peticion de la vista global, en la tr
		        
		        boolean elementoPeticionEstaEnHC = false;
		        for(Iterator elPetBdIter = ePBD.iterator();elPetBdIter.hasNext();){
		        	Elemento_PeticionLocal elemPetLocal = (Elemento_PeticionLocal) elPetBdIter.next();
		        	Elemento_PeticionKey elemPetKey = (Elemento_PeticionKey) elemPetLocal.getPrimaryKey();
		        	elementoPeticionEstaEnHC = false;
		        	forTr: for(Iterator elPetTrIter = ePTr.iterator();elPetTrIter.hasNext();){
		        		TR025SEquipment equipoTr = (TR025SEquipment) elPetTrIter.next();
		        		if(elemPetKey.serial.equals(equipoTr.getElementSerial())){
		        			elementoPeticionEstaEnHC = true;
		        			break forTr;
		        		}
		        	}
		        	/*Se valida si el Elemento_Peticion se debe enviar a SAP o no*/
		        	if(!elementoPeticionEstaEnHC &&
		        			elemPetLocal.getAccion().intValue() == ComunInterfaces.accionParActivar &&
		        			elemPetLocal.getFlag_mat_sap() != null && 
							elemPetLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        		
		        		elemPetLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
		        	}
		        }
		        
		        /*Analisis de los decos y Tarjetas (Deco_tarjeta) para marcar los que se deben enviar a SAP*/
		        Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome = (Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
		        Collection dTBD = peticionStLocal.getDeco_tarjeta() != null ? peticionStLocal.getDeco_tarjeta() : new ArrayList(); //Deco_Tarjeta de la petición soltec en BD		        
		        Collection dTTr = tr025s.getDecosCards() != null ? tr025s.getDecosCards() : new ArrayList(); //Deco_Tarjeta de la vista global, en la tr
		        
		        boolean decoTarjetaEstaEnHC = false;
		        for(Iterator decTarBdIter = dTBD.iterator();decTarBdIter.hasNext();){
		        	Deco_tarjetaLocal decoTarjLocal = (Deco_tarjetaLocal) decTarBdIter.next();
		        	
		        	/*Busqueda y validacion del flag_mat_sap para el DECO*/
		        	boolean esDecoSAP = false;
		        	try{
		        		Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco, new Long(tr025s.getAtiempoRequestNumber()));
		        		Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
		        		
		        		if(infoSAPDeco != null &&
		        				infoSAPDeco.getFlag_mat_sap() != null &&
		        				infoSAPDeco.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        			
		        			esDecoSAP = true;
		        		}else{
		        			esDecoSAP = false;
		        			log.debug("El Deco asociado al Id en soltec: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco+", y a la peticion: "+tr025s.getAtiempoRequestNumber()+", no tiene el FLAG_MAT_SAP o no es un equipo de SAP.");
		        		}
		        	}catch(FinderException e){
		        		log.debug("No se encontro ningun Deco asociado al Id en soltec: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco+", y a la peticion: "+tr025s.getAtiempoRequestNumber());
		        		esDecoSAP = false;
		        	}catch(Exception e){
		        		log.debug("EquipoBean: procesarRespuestaVistaGlobalST: Exception: Error desconocido intentando obtener datos del Deco: "+ e);
		        		esDecoSAP = false;
		        	}
		        	
		        	/*Busqueda y validacion del flag_mat_sap para la TARJETA*/
		        	boolean esTarjetaSAP = false;
		        	try{		        		
		        		Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta, new Long(tr025s.getAtiempoRequestNumber()));
		        		Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
		        		
		        		if(infoSAPCard != null &&
		        				infoSAPCard.getFlag_mat_sap() != null &&
		        				infoSAPCard.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        			
		        			esTarjetaSAP = true;
		        		}else{
		        			esTarjetaSAP = false;
		        			log.debug("La tarjeta asociado al Id en soltec: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta+", y a la peticion: "+tr025s.getAtiempoRequestNumber()+", no tiene el FLAG_MAT_SAP o no es un equipo de SAP.");
		        		}
		        	}catch(FinderException e){
		        		log.debug("No se encontro ninguna Tarjeta asociado al Id en soltec: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta+", y a la peticion: "+tr025s.getAtiempoRequestNumber());
		        		esTarjetaSAP = false;
		        	}catch(Exception e){
		        		log.debug("EquipoBean: procesarRespuestaVistaGlobalST: Exception: Error desconocido intentando obtener datos de la tarjeta: "+ e);
		        		esTarjetaSAP = false;
		        	}

		        	/*Solo se puede marcar con el FLAG_ENVIO_EQUIPO_SAP si el Deco es de SAP o la tarjeta es de SAP*/
		        	if(esDecoSAP || esTarjetaSAP){
		        		
		        		Deco_tarjetaKey decoTarjKey = (Deco_tarjetaKey) decoTarjLocal.getPrimaryKey();
			        	decoTarjetaEstaEnHC = false;
			        	forTrDT: for(Iterator decoTarjTrIter = dTTr.iterator();decoTarjTrIter.hasNext();){
			        		TR025SDecoCard decoCardTr = (TR025SDecoCard) decoTarjTrIter.next();
			        		if (decoTarjKey.id_deco.equals(decoCardTr.getDecoCasId()) && decoTarjKey.id_tarjeta.equals(decoCardTr.getCardSerial()) ){
			        			decoTarjetaEstaEnHC = true;
			        			break forTrDT;
			        		}
			        	}
			        	
			        	/*Se valida si el Par Deco Tarjeta se debe enviar a SAP.
			        	 * En la notificación SAP se validan por separado el Deco y la Tarjeta ya que cada uno tendra un FLAG_MAT_SAP*/
			        	if(!decoTarjetaEstaEnHC && decoTarjLocal.getAccion().intValue() == ComunInterfaces.accionParActivar){
			        		decoTarjLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
			        	}
		        	}else{
		        		log.debug("En la vista global de soltec, ni el Deco ni la Tarjeta son de SAP por lo cual no se marca el Deco-Tarjeta para el envio a SAP.");
		        	}
		        	
		        }
	        	/*Cierre de la actividad exitoso*/
				actDto = actividadEJB.getActividadEJBDTO(((Peticion_stKey)mensajeEstadoSt.getPeticion_st().getPrimaryKey()).cod_ave_cd, mensajeEstadoSt.getCod_actividad_generadora());
				String observacion = "Err:"+tr025s.getError()+". MsgErr:"+tr025s.getErrorMessage();
				mensajeEstadoSt.setObservaciones(observacion);
				mensajeEstadoSt.setFecha_cierre(df.format(new java.util.Date()));
				actDto.setObservacion("Se recibe correctamente la información de la Vista Global ST.", true);
				actividadEJB.terminar (actDto);
	        }else{	        	
	        	/*Cierre de la actividad con error*/	        	
	        	actDto = actividadEJB.getActividadEJBDTO(((Peticion_stKey)mensajeEstadoSt.getPeticion_st().getPrimaryKey()).cod_ave_cd, mensajeEstadoSt.getCod_actividad_generadora());
				log.info("Se deriva a solución recursos ST, por error en la respuesta de la TR025");
				actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
				String observacionError = "Err:"+tr025s.getError()+". MsgErr:"+tr025s.getErrorMessage();
				mensajeEstadoSt.setObservaciones(observacionError);
				mensajeEstadoSt.setFecha_cierre(df.format(new java.util.Date()));
				actDto.setObservacion("Error al recibir la información de la Vista Global ST, se deriva a Solucion Recursos ST. Respuesta: "+tr025s.getError()+" - "+tr025s.getErrorMessage(), true);
				actividadEJB.terminar (actDto);
	        }
		} catch (NamingException e) {
			log.error("NamingException: Error procesando la respuesta TR025S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S o cerrando la peticion. con id: " + tr025s.getId() +".", e);
		} catch (EJBException e) {
			log.error("EJBException: Error procesando la respuesta TR025S en ST o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S en ST o cerrando la peticion con id: " + tr025s.getId() +".", e);
		} catch (TnProcesoExcepcion e) {
			log.error("TnProcesoExcepcion: Error procesando la respuesta TR025S en ST o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S en ST o cerrando la peticion. con id: " + tr025s.getId() +".", e);
		} catch (FinderException e) {
			log.error("FinderException: Error procesando la respuesta TR025S en ST o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S en ST o cerrando la peticion. con id: " + tr025s.getId() +".", e);
		} catch (ATiempoAppEx e) {
			throw e;
		} finally{
			bExecution.endAll();			
		}		
	}
    
	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaNotificacionVentaMinoristasSAPST(TR020S tr020s) throws ATiempoAppEx{
		
		BackendExecution bExecution = null;
		String estado = "Cerrado";
		Collection elementosSAP;

		try {
	        bExecution = BackendTraceUtil.initExecution(tr020s, log);
	        String strNumATconATM = tr020s.getAtiempoRequestNumber();
	        bExecution.setNumPetAtiempo(new Long(strNumATconATM.substring(3)));
	        bExecution.setIdMensajeOp(tr020s.getId());
	        bExecution.startOperation();
	        
	        mensajeDescargaSAPLocalHome = (Mensaje_descarga_SAP_STLocalHome)HomeFactory.getHome(Mensaje_descarga_SAP_STLocalHome.JNDI_NAME);

	        Mensaje_estado_stLocal mensajeEstadoSt;
	        
	        Mensaje_descarga_SAP_STLocal mensaje_descarga_SAPLocal;
	        
	        try {
	        	mensajeEstadoSt = msgEstadoLineaLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(tr020s.getId())));
	        	elementosSAP =mensajeDescargaSAPLocalHome.findByCod_ave_cd(new Long(tr020s.getAtiempoRequestNumber().substring(3)));
	        } catch (FinderException e1) {
	        	mensajeEstadoSt = null;
	        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr020s));
	            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr020s.getId(), ATiempoAppEx.FINDER);
	        }
	        			        
	        ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB () ;
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoSt.getCod_actividad_generadora ()) ;
	    	ActividadEJBDTO actDto ;	   		    	
	    	
	    	if(tr020s != null && tr020s.getError() != null && tr020s.getError().equals("0")){
	        	/*Cierre de la actividad exitoso*/
				actDto = actividadEJB.getActividadEJBDTO(((Peticion_stKey)mensajeEstadoSt.getPeticion_st().getPrimaryKey()).cod_ave_cd, mensajeEstadoSt.getCod_actividad_generadora());
				String observacion = "Err:"+tr020s.getError()+". MsgErr:"+tr020s.getErrorMessage();
				mensajeEstadoSt.setObservaciones(observacion);
				mensajeEstadoSt.setFecha_cierre(df.format(new java.util.Date()));
				mensajeEstadoSt.setCod_estado(new Integer(ComunInterfaces.estadoOk));
				
				for(Iterator iterElemSAP = elementosSAP.iterator(); iterElemSAP.hasNext();){
					mensaje_descarga_SAPLocal = (Mensaje_descarga_SAP_STLocal) iterElemSAP.next();
					mensaje_descarga_SAPLocal.setEst_pedido(estado);
					mensaje_descarga_SAPLocal.setFecha(tr020s.getFechaEjecucionSAP());
					mensaje_descarga_SAPLocal.setMotivo(tr020s.getErrorMessage());
					mensaje_descarga_SAPLocal.setDoc_mat(tr020s.getDocumentoMaterial());
					mensaje_descarga_SAPLocal.setFecha_doc(tr020s.getFechaDocumento());
					mensaje_descarga_SAPLocal.setHora_eje_inter(tr020s.getHoraEjecucionInterfaz());
				}
				
				//se almacena el mensaje que se envia a SAP en la tabla TMP_NOTIFICACION_SAP
	        	Long idCorrelativo = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_NOTIFICACION_SAP"));
	        	
	        	//se obtiene la fecha actual
	        	Date date = new Date();
	        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
	        	String fecha = sdf.format(date); 
	        	
	        	Tmp_Notificacion_SAPLocalHome mensajeHome = (Tmp_Notificacion_SAPLocalHome) HomeFactory.getHome(Tmp_Notificacion_SAPLocalHome.JNDI_NAME);
	        	Tmp_Notificacion_SAPLocal mensajeLocal = mensajeHome.create(idCorrelativo);
	        	mensajeLocal.setPeti_numero(new Long(strNumATconATM.substring(3)));
	        	mensajeLocal.setCod_pet_cd(new Long(strNumATconATM.substring(3)));
	        	mensajeLocal.setFec_ingreso( Timestamp.valueOf(fecha));
	        	mensajeLocal.setMensaje(XMLUtilities.marshall (tr020s));
	        	
	        	
				actDto.setObservacion("Se recibe confirmación del correcto envío de la información de ventas minoristas hacia SAP en ST.", true);
				actividadEJB.terminar (actDto);
	        }else{	        	
	        	/*Cierre de la actividad con error*/	        	
	        	actDto = actividadEJB.getActividadEJBDTO(((Peticion_stKey)mensajeEstadoSt.getPeticion_st().getPrimaryKey()).cod_ave_cd, mensajeEstadoSt.getCod_actividad_generadora());
				log.info("Se deriva a solución recursos ST, por error en la respuesta de la TR020");
				actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
				String observacionError = "Err:"+tr020s.getError()+". MsgErr:"+tr020s.getErrorMessage();
				mensajeEstadoSt.setObservaciones(observacionError);
				mensajeEstadoSt.setFecha_cierre(df.format(new java.util.Date()));
				mensajeEstadoSt.setCod_estado(new Integer(ComunInterfaces.estadoError));
				for(Iterator iterElemSAP = elementosSAP.iterator(); iterElemSAP.hasNext();){
					mensaje_descarga_SAPLocal = (Mensaje_descarga_SAP_STLocal) iterElemSAP.next();
					mensaje_descarga_SAPLocal.setFecha(tr020s.getFechaEjecucionSAP());
					mensaje_descarga_SAPLocal.setMotivo(tr020s.getErrorMessage());
					mensaje_descarga_SAPLocal.setDoc_mat(tr020s.getDocumentoMaterial());
					mensaje_descarga_SAPLocal.setFecha_doc(tr020s.getFechaDocumento());
					mensaje_descarga_SAPLocal.setHora_eje_inter(tr020s.getHoraEjecucionInterfaz());
				}
				
				actDto.setObservacion("Error al recibir respuesta del envío de la información de ventas minoristas hacia SAP en ST, se deriva a Solucion Recursos ST. Respuesta: "+tr020s.getError()+" - "+tr020s.getErrorMessage(), true);
				actividadEJB.terminar (actDto);
	        }
		} catch (EJBException e) {
			log.error("Error procesando la respuesta TR020S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR020S en ST o cerrando la peticion con id: " + tr020s.getId() +".", e);
		} catch (TnProcesoExcepcion e) {
			log.error("Error procesando la respuesta TR020S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR020S en ST o cerrando la peticion. con id: " + tr020s.getId() +".", e);
		} catch (ATiempoAppEx e) {
			throw e;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error procesando la respuesta TR020S o cerrando la peticion." + e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} finally{
			bExecution.endAll();		
		}		
	}
	
	/*RQ.8595 - mfmendez*/
	public void enviarSolicitudVistaGlobalST(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		try{
			log.debug("Ingresa a enviarSolicitudVistaGlobalST, para la petición Atiempo No. "+nroPeticion);
			if(mensaje_estadoLocalHome==null){
				mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			}
			Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
	        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
	        
	        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
	        Peticion_stKey key = new Peticion_stKey(nroPeticion);
	        Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
	        
	        TR025E object = new TR025E();
	        /*Datos del encabezado*/
			object.setId(idCorrelativoMensaje.toString());
			object.setDestination("ESB");
			object.setSource(ComunInterfaces.sistemaAtiempoSt);
			object.setInterfaz(ComunInterfaces.INTERFAZ_ST_VISTA_GLOBAL);
			object.setVersion("1.0");		
			
			/*Datos del body*/
			object.setAtiempoRequestNumber(nroPeticion.longValue());	      	      			
			object.setAtisRequestNumber(nroPeticion.longValue());		
			
			/*VALIDACION DE SI EXISTEN EQUIPOS ASOCIADOS A LA PETICION. SI NO HAY NINGUNO SE INHIBE LA ACTIVIDAD*/
			 /*Valida si la petición tiene equipos(Elemento_Peticion) configurados para la petición*/
			boolean tieneElementoPeticion = false;
			Collection equipos = peticionLocal.getElemento_peticion();
	        if (equipos == null || (equipos != null && equipos.size() == 0)) {
	            log.debug("INFO: No existen Equipos(Elemento_Peticion) Asociados a la Peticion: "+ nroPeticion);         
	        }else {
	        	for(Iterator it=equipos.iterator();it.hasNext();){
	        		Elemento_PeticionLocal elemento_PeticionLocal = (Elemento_PeticionLocal)it.next();
	        		if(elemento_PeticionLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
	        			tieneElementoPeticion= true;
	        			break;
	        		}
	        	}
	        }
	        /*Valida si la petición tiene decos y tarjetas (Deco_tarjeta) configurados para la petición*/
			boolean tieneDecoTarjeta = true;
			Collection decosTarjetas = peticionLocal.getDeco_tarjeta();
	        if (decosTarjetas == null || (decosTarjetas != null && decosTarjetas.size() == 0)) {
	            log.debug("INFO: No existen Decos y Tarjetas (Deco_Tarjeta) Asociados a la Peticion: "+ nroPeticion);
	            tieneDecoTarjeta = false;	            
	        }
	        /*Valida si la petición tiene decos y tarjetas (Deco_tarjeta) configurados para la petición*/
			boolean tieneModem=false;
			Collection modems = peticionLocal.getModem();
	        if (modems == null || (modems != null && modems.size() == 0)) {
	            log.debug("INFO: No existen modems (Modem) Asociados a la Peticion: "+ nroPeticion);
	            tieneModem = false;	            
	        }else {
	        	for(Iterator it=modems.iterator();it.hasNext();){
	        		ModemLocal modemLocal = (ModemLocal)it.next();
	        		if(modemLocal.getFlag_mat_sap()!=null && modemLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
	        			tieneModem= true;
	        			break;
	        		}
	        	}
	        }
						
	        /**/
	        if(!tieneElementoPeticion && !tieneDecoTarjeta && !tieneModem){
	        	log.error("Se inhibe la actividad de Vista Global Soltec por no tener ningun equipo (equipo, deco-tarjeta, modem), para la petición: "+ nroPeticion);				
				act.setObservacion("Se inhibe la actividad por no tener configurado ningun equipo.", true);
				act.setRealizarTerminoInmediato(true);
	        }else{
				if((peticionLocal.getNum_ide_nu() != null && !peticionLocal.getNum_ide_nu().trim().equals(""))
						|| (peticionLocal.getNum_ide_nu_tv() != null && !peticionLocal.getNum_ide_nu_tv().trim().equals(""))){
					if(peticionLocal.getIde_pro_cmr_cd().equals("TV")){
						object.setPhoneNumber("");
						object.setIdPC(peticionLocal.getNum_ide_nu_tv());
					}else{
						object.setPhoneNumber(peticionLocal.getNum_ide_nu());
						object.setIdPC("");
					}

					Mensaje_estado_stLocal msgLocal = msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
			        msgLocal.setPeticion_st(peticionLocal);
			        msgLocal.setCod_actividad_generadora(actGeneradora);
			        msgLocal.setFecha_envio(df.format(new java.util.Date()));
			        msgLocal.setCod_estado(new Integer(ComunInterfaces.estadoOk));
			        // mensajeEstadoSt.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
			        
			        STVistaGlobalMQ vistaGlobal = new STVistaGlobalMQ();
			        vistaGlobal.enviarMensaje(object);
			        
				}else{
					log.error("Error al enviar la solicitud de Vista Global de SOLTEC por no tener número de abonado ni idPC, para la petición: "+ nroPeticion +". Se deriva a solución recursos ST.");				
					
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
					act.setObservacion("Error al enviar la solicitud de Vista Global por no contener los datos necesarios para la consulta, se deriva a solución recursos ST.", true);
					act.setRealizarTerminoInmediato(true);
				}	      		
	        }
		}catch (FinderException e) {
            log.error("Error al enviar solicitud de vista global.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (CreateException e) {
        	log.error("Error al enviar solicitud de vista global.", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (ATiempoAppEx e) {
			log.error("Error al enviar solicitud de vista global.", e);
            throw e;
		} catch (Exception e) {
			log.error("Error al enviar solicitud de vista global.", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		} 		
	}
	
	/*RQ.8595 - mfmendez*/
	public void enviarInformacionEquiposVentaMinoristasSAPST(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{	
		try{
			log.debug("Ingresa a enviarInformacionEquiposVentaMinoristasSAPST, para la petición Atiempo No. "+nroPeticion);

	        Collection elemPetiSAP = null;
			Collection decoTarSAP = null;
			Collection modemSAP = null;
			Collection elemNoSerialSAP = null;
			
			String comentariosElementos="";
	        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
	        Peticion_stKey key = new Peticion_stKey(nroPeticion);
	        Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
	        
	        /*VALIDACION DE SI EXISTEN EQUIPOS ASOCIADOS A LA PETICION. SI NO HAY NINGUNO SE INHIBE LA ACTIVIDAD*/
			 /*Valida si la petición tiene equipos(Elemento_Peticion) configurados para la petición*/
			boolean tieneElementoPeticion = false;
			Collection equipos = peticionLocal.getElemento_peticion();
	        if (equipos == null || (equipos != null && equipos.size() == 0)) {
	            log.debug("INFO: No existen Equipos(Elemento_Peticion) Asociados a la Peticion: "+ nroPeticion);
	            
	        }else{
	        	elemPetiSAP = new ArrayList();
	        	for(Iterator iterEqu = equipos.iterator();iterEqu.hasNext();){
	        		Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal) iterEqu.next();
	        		/*Se seleccionan los elemento-peticion que deben ser enviados a SAP*/
	        		if(elementoPeticionLocal != null && elementoPeticionLocal.getFlag_pet_curso() != null 
	        				&& elementoPeticionLocal.getFlag_pet_curso().equals(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP)){
	        			elemPetiSAP.add(elementoPeticionLocal);
	        			tieneElementoPeticion = true;
	        		}else{
	        			comentariosElementos+="El equipo con PS "+elementoPeticionLocal.getPs_id()+" no puede enviarse a SAP. ";
	        		}
	        	}
	        }
	        /*Valida si la petición tiene decos y tarjetas (Deco_tarjeta) configurados para la petición*/
			boolean tieneDecoTarjeta = false;
			Collection decosTarjetas = peticionLocal.getDeco_tarjeta();
	        if (decosTarjetas == null || (decosTarjetas != null && decosTarjetas.size() == 0)) {
	            log.debug("INFO: No existen Decos y Tarjetas (Deco_Tarjeta) Asociados a la Peticion: "+ nroPeticion);
	            
	        }else{
	        	decoTarSAP = new ArrayList();
	        	for(Iterator iterDecTar = decosTarjetas.iterator();iterDecTar.hasNext();){
	        		Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) iterDecTar.next();
	        		/*Se seleccionan los deco-tarjeta que deben ser enviados a SAP*/
	        		if(decoTarjetaLocal != null && decoTarjetaLocal.getFlag_pet_curso() != null 
	        				&& decoTarjetaLocal.getFlag_pet_curso().equals(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP)){
	        			decoTarSAP.add(decoTarjetaLocal);
	        			tieneDecoTarjeta = true;
	        		}else{
	        			comentariosElementos+="El Deco-Tarjeta "+decoTarjetaLocal.getCodigo_deco()+" no puede enviarse a SAP. ";
	        		}
	        	}
	        }
	        /*Valida si la petición tiene modems (Modem) configurados para la petición*/
			boolean tieneModem= false;
			Collection modems = peticionLocal.getModem();
	        if (modems == null || (modems != null && modems.size() == 0)) {
	            log.debug("INFO: No existen modems (Modem) Asociados a la Peticion: "+ nroPeticion);
	            
	        }else{
	        	modemSAP = new ArrayList();
	        	for(Iterator iterMod = modems.iterator();iterMod.hasNext();){
	        		ModemLocal modemLocal = (ModemLocal) iterMod.next();
	        		/*Se seleccionan los modems que deben ser enviados a SAP*/
	        		if(modemLocal != null && modemLocal.getFlag_pet_curso() != null 
	        				&& modemLocal.getFlag_pet_curso().equals(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP)){
	        			modemSAP.add(modemLocal);
	        			tieneModem = true;
	        		}else{
	        			comentariosElementos+="El modem "+modemLocal.getCod_material()+" no puede enviarse a SAP ";
	        		}
	        	}
	        }
			
	        /*Valida si la petición tiene elementos no serializados(ELEMENTO_NO_SERIALIZADO) configurados para la petición*/
			boolean tieneElementoNoSerializado = false;
			Collection elemNoSerial = peticionLocal.getElementonoserializado();
	        if (elemNoSerial == null || (elemNoSerial != null && elemNoSerial.size() == 0)) {
	            log.debug("INFO: No existen Elementos no serializados(Elemento_No_Serializado) Asociados a la Peticion: "+ nroPeticion);

	        }else{
	        	elemNoSerialSAP = new ArrayList();
	        	for(Iterator iterEleNoSerial = elemNoSerial.iterator();iterEleNoSerial.hasNext();){
	        		ElementoNoSerializadoLocal elementoNoSerializadoLocal = (ElementoNoSerializadoLocal) iterEleNoSerial.next();
	        		/*Se seleccionan los elemento_no_serializado que deben ser enviados a SAP*/
	        		if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getFlagMatSap() != null 
	        				&& elementoNoSerializadoLocal.getFlagMatSap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
	        			elemNoSerialSAP.add(elementoNoSerializadoLocal);
	        			tieneElementoNoSerializado=true;
					}else{
						
						ElementoNoSerializadoKey idElemNoSerial = (ElementoNoSerializadoKey)elementoNoSerializadoLocal.getPrimaryKey();
						if(idElemNoSerial != null){
							log.debug("El elemento no serializado con ID "+idElemNoSerial.id+" no puede enviarse a SAP.");
							comentariosElementos+="El elemento no serializado con ID "+idElemNoSerial.id+" no puede enviarse a SAP. ";
						} else{
							log.debug("El elemento no serializado con ID 0 no puede enviarse a SAP.");
							comentariosElementos+="El elemento no serializado con ID 0 no puede enviarse a SAP. ";
						}
	        		}
	        	}
	        }
	        /**/
	        if(!tieneElementoPeticion && !tieneDecoTarjeta && !tieneModem && !tieneElementoNoSerializado){
	        	log.error("Se inhibe la actividad de Notificación SAP Soltec por no tener ningun equipo (equipo, deco-tarjeta, modem, elemento_no_serializado), para la petición: "+ nroPeticion);				
				act.setObservacion("Se inhibe la actividad por no tener configurado ningun equipo.", true);
				act.setRealizarTerminoInmediato(true);
	        }else{
	        	if(!comentariosElementos.equals("")){
	        		act.setObservacion(comentariosElementos);
	        	}
	        	
	        	crearYEnviarMensajeVentaMinoristasSAPST(act, nroPeticion, actGeneradora, peticionLocal, elemPetiSAP, decoTarSAP, modemSAP, elemNoSerialSAP);
	        }
		} catch (FinderException e) {
            log.error("Error al enviar informacion de equipos a MM SAP, en Soltec.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (CreateException e) {
        	log.error("Error al enviar informacion de equipos a MM SAP, en Soltec.", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (ATiempoAppEx e) {
			log.error("Error al enviar informacion de equipos a MM SAP, en Soltec.", e);
            throw e;
		} catch (Exception e) {
			log.error("Error al enviar informacion de equipos a MM SAP, en Soltec.", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		} 
	}
	
	/**
	 * Metodo principal para armar y enviar el mensaje de Venta Minoristas a SAP
	 * @param act
	 * @param nroPeticion
	 * @param actGeneradora
	 * @param peticionLocal
	 * @param elemPetiSAP, colección con los elemento_peticion que se deben enviar a SAP
	 * @param decoTarSAP, colección con los deco_tarjeta que se deben enviar a SAP
	 * @param modemSAP, colección con los modem que se deben enviar a SAP
	 * @throws CreateException
	 * @throws FinderException
	 * @throws ATiempoAppEx
	 * @throws Exception
	 */
	private void crearYEnviarMensajeVentaMinoristasSAPST(ActividadEJBDTO act, Long nroPeticion, 
			String actGeneradora, Peticion_stLocal peticionLocal, Collection elemPetiSAP,
			Collection decoTarSAP, Collection modemSAP, Collection elemNoSerialSAP) throws CreateException, FinderException, ATiempoAppEx, Exception{
		
		TR020E tr020e = new TR020E();
				
		Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome = (Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
		if(mensaje_estadoLocalHome==null){
			mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
		}
		Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
        
        mensajeDescargaSAPLocalHome = (Mensaje_descarga_SAP_STLocalHome)HomeFactory.getHome(Mensaje_descarga_SAP_STLocalHome.JNDI_NAME);

        /* Ingresa los datos del Encabezado de Agenda*/
        tr020e.setId(idCorrelativoMensaje.toString());
        tr020e.setSource(ComunInterfaces.sistemaAtiempoSt);
        tr020e.setInterfaz(ComunInterfaces.INTERFAZ_ST_NOTIFICACION_SAP);
        tr020e.setDestination("ESB");
        tr020e.setVersion("1.0");
        if(elemPetiSAP!=null && !elemPetiSAP.isEmpty()){
        	tr020e.setFlagMatGesrec("1");
        }else{
        	tr020e.setFlagMatGesrec("0");
        }
        tr020e.setPeticionAtis(nroPeticion.toString());
        
		boolean codMaterialElemtoPeticion= false;
		boolean codMaterialDecoTarjeta= false;
		boolean codMaterialDecoTarjeta2= false;
		boolean codMaterialModem= false;
		boolean codMaterialElementoNoSerializado= false;
        
        /*Ingresa los datos de fecha de ejecucin de la interfaz*/
        Fecha fechaEnvioInterfaz = new Fecha();
        tr020e.setExecutionDate(fechaEnvioInterfaz.getYYYYMMDD());
        tr020e.setExecutionTime(fechaEnvioInterfaz.getHHMMSS());
        
        Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);        
        String peticionAtiempoConATM = ComunInterfaces.INDICADOR_ATIEMPO_MENSAJE_A_SAP + nroPeticion.toString();
        String fechaContNew = new String("");
        
        /*Validacion de la fecha de cierre contable de cada mes, parametrizable en la base de datos (es un valor entero entre 1 y 31)*/
        try{
        	/*Se obtiene propiedad con dia de inicio para el cierre contable de SAP de todos los meses*/
        	Properties_BDLocal propertiesBDLocalDiaCierre = propertiesBDLocalHome.findByPrimaryKey("DIA_INICIO_CIERRE_SAP");
    		
    		int diaIniCierreCont = Integer.parseInt(propertiesBDLocalDiaCierre.getValor());
            String fechaContabilizacionStr = fechaEnvioInterfaz.getYYYYMMDD();
            if(fechaContabilizacionStr != null && !fechaContabilizacionStr.equals("")){
            	int day = Integer.parseInt(fechaContabilizacionStr.substring(6));
            	int month = Integer.parseInt(fechaContabilizacionStr.substring(4,6));
            	int year = Integer.parseInt(fechaContabilizacionStr.substring(0,4));
            	
            	if(day >= diaIniCierreCont){
            		String dayNewStr = "01";
            		
            		String monthNewStr = "";
            		int monthNew = month + 1;
            		int yearNew = year;
            		if(monthNew >= 13){
            			monthNewStr = "01";
            			yearNew++;
            		}else if(monthNew <= 9){
            			monthNewStr = "0" + String.valueOf(monthNew);
            		}else{
            			monthNewStr = String.valueOf(monthNew);
            		}
            		String yearNewStr = String.valueOf(yearNew);
            		fechaContNew = yearNewStr + monthNewStr + dayNewStr;            		
            	}else{
            		fechaContNew = fechaContabilizacionStr;
            	}
            }else{
            	fechaContNew = ComunInterfaces.FECHA_CONTABILIZACION_INVALIDO;
            }
        }catch(Exception e){
        	fechaContNew = ComunInterfaces.FECHA_CONTABILIZACION_INVALIDO;
        }
		
        if(!fechaContNew.equals(ComunInterfaces.FECHA_CONTABILIZACION_INVALIDO) 
        		&& ((elemPetiSAP != null && elemPetiSAP.size() > 0)
        				|| (decoTarSAP != null && decoTarSAP.size() > 0)
						|| (modemSAP != null && modemSAP.size() > 0)
						|| (elemNoSerialSAP != null && elemNoSerialSAP.size() > 0))){
        	
        	HashMap cabecerasPeticion = new HashMap();
        	String identificadorTipoEquipo = new String("");
        	/*MANEJO DE LA CARGA DE LOS DATOS DE LOS EQUIPOS - ELEMENTO_PETICION*/
        	if(elemPetiSAP != null && elemPetiSAP.size() > 0){
        		identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_ELEMENTO_PETICION_SAP;
        		
        		for(Iterator iterElemPet = elemPetiSAP.iterator(); iterElemPet.hasNext();){
        			Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal) iterElemPet.next();
        			codMaterialElemtoPeticion= true;
        			
        			if(cabecerasPeticion.size() <= 0){
        				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
        				requestHeaderNew.setPositionsHeader(new ArrayList());
        				
        				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
        				requestHeaderNew.setAccountingDate(fechaContNew);
        				requestHeaderNew.setMoveType(elementoPeticionLocal.getClase_mov_sap());
        				/*Se asignan los datos de la cabecera y de la posicion de elemento_peticion*/
        				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, elementoPeticionLocal, null, null, null, null, requestHeaderNew);
        				/*Se incluye la nueva Cabecera en el HashMap de Cabeceras*/
        				cabecerasPeticion.put(elementoPeticionLocal.getClase_mov_sap(), requestHeaderNew);
        			}else{
        				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(elementoPeticionLocal.getClase_mov_sap());
        				
        				if(existeCabeceraPeticion){
        					/*Se asignan los datos de la cabecera y de la posicion de elemento_peticion*/
        					asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, elementoPeticionLocal, null, null, null, null, (TR020ERequestHeader)cabecerasPeticion.get(elementoPeticionLocal.getClase_mov_sap()));
        				}else{
        					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
        					requestHeaderNewDos.setPositionsHeader(new ArrayList());
            				
        					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
        					requestHeaderNewDos.setAccountingDate(fechaContNew);
        					requestHeaderNewDos.setMoveType(elementoPeticionLocal.getClase_mov_sap());
        					/*Se asignan los datos de la cabecera y de la posicion  de elemento_peticion*/
            				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, elementoPeticionLocal, null, null, null, null, requestHeaderNewDos);
            				/*Se incluye la nueva Cabecera en el HashMap de Cabeceras*/
            				cabecerasPeticion.put(elementoPeticionLocal.getClase_mov_sap(), requestHeaderNewDos);
        				}
        			}
        		}
        	}
        	/*MANEJO DE LA CARGA DE LOS DATOS DE LOS DECOS Y LAS TARJETAS*/
        	log.debug("Armando Mensaje de Envio a SAP para los Pares Deco Tarjeta");
        	if(decoTarSAP != null && decoTarSAP.size() > 0){
        		
        		for(Iterator iterDecoTarj = decoTarSAP.iterator(); iterDecoTarj.hasNext();){
        			Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) iterDecoTarj.next();
        			     		
        			try{			        				
        				Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco, nroPeticion);
        				Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
        				identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_DECO_SAP;
        				codMaterialDecoTarjeta= true;
        				
        				/*Se incluye validacion del FLAG_MAT_SAP para cada deco*/
        				if(infoSAPDeco != null &&
        						infoSAPDeco.getFlag_mat_sap() != null &&
								infoSAPDeco.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        					
        					if(cabecerasPeticion.size() <= 0){
                				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
                				requestHeaderNew.setPositionsHeader(new ArrayList());
                				
                				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
                				requestHeaderNew.setAccountingDate(fechaContNew);
                				requestHeaderNew.setMoveType(infoSAPDeco.getClase_mov_sap());
                				/*Se asignan los datos de la cabecera y de la posicion  de deco_tarjeta (deco)*/
                				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, infoSAPDeco, decoTarjetaLocal, null, requestHeaderNew);
                				/*Se incluye la nueva Cabecera en el HashMap de Cabeceras*/
                				cabecerasPeticion.put(infoSAPDeco.getClase_mov_sap(), requestHeaderNew);
                			}else{
                				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(infoSAPDeco.getClase_mov_sap());
                				
                				if(existeCabeceraPeticion){
                					/*Se asignan los datos de la cabecera y de la posicion  de deco_tarjeta (deco)*/
                					asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, infoSAPDeco, decoTarjetaLocal, null, (TR020ERequestHeader)cabecerasPeticion.get(infoSAPDeco.getClase_mov_sap()));
                				}else{
                					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
                					requestHeaderNewDos.setPositionsHeader(new ArrayList());
                    				
                					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
                					requestHeaderNewDos.setAccountingDate(fechaContNew);
                					requestHeaderNewDos.setMoveType(infoSAPDeco.getClase_mov_sap());
                					/*Se asignan los datos de la cabecera y de la posicion  de deco_tarjeta (deco)*/
                    				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, infoSAPDeco, decoTarjetaLocal, null, requestHeaderNewDos);
                    				/*Se incluye la nueva Cabecera en el HashMap de Cabeceras*/
                    				cabecerasPeticion.put(infoSAPDeco.getClase_mov_sap(), requestHeaderNewDos);
                				}
                			}
        				}
        			} catch (FinderException e) {
        				log.debug("No se encontraron datos de SAP para el Deco con id: "+ ((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco+", para la peticion: "+ nroPeticion.toString());
        			} catch (Exception e){
        				log.debug("No se encontraron datos de SAP para el Deco con id: "+ ((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco+", para la peticion: "+ nroPeticion.toString());
        			}

        			try{			        				
        				Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_tarjeta, nroPeticion);
        				Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
        				identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_TARJETA_SAP;
        				codMaterialDecoTarjeta2 = true;
        				
        				/*Se incluye validacion del FLAG_MAT_SAP para cada tarjeta*/
        				if(infoSAPCard != null &&
        						infoSAPCard.getFlag_mat_sap() != null &&
        						infoSAPCard.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        					
        					if(cabecerasPeticion.size() <= 0){
                				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
                				requestHeaderNew.setPositionsHeader(new ArrayList());
                				
                				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
                				requestHeaderNew.setAccountingDate(fechaContNew);
                				requestHeaderNew.setMoveType(infoSAPCard.getClase_mov_sap());
                				/*Se asignan los datos de la cabecera y de la posicion  de deco_tarjeta (tarjeta)*/
                				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, infoSAPCard, decoTarjetaLocal, null, requestHeaderNew);
                				/*Se incluye la nueva Cabecera en el HashMap de Cabeceras*/
                				cabecerasPeticion.put(infoSAPCard.getClase_mov_sap(), requestHeaderNew);
                			}else{
                				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(infoSAPCard.getClase_mov_sap());
                				
                				if(existeCabeceraPeticion){
                					/*Se asignan los datos de la cabecera y de la posicion  de deco_tarjeta (tarjeta)*/
                					asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, infoSAPCard, decoTarjetaLocal, null, (TR020ERequestHeader)cabecerasPeticion.get(infoSAPCard.getClase_mov_sap()));
                				}else{
                					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
                					requestHeaderNewDos.setPositionsHeader(new ArrayList());
                    				
                					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
                					requestHeaderNewDos.setAccountingDate(fechaContNew);
                					requestHeaderNewDos.setMoveType(infoSAPCard.getClase_mov_sap());
                					/*Se asignan los datos de la cabecera y de la posicion  de deco_tarjeta (tarjeta)*/
                    				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, infoSAPCard, decoTarjetaLocal, null, requestHeaderNewDos);
                    				/*Se incluye la nueva Cabecera en el HashMap de Cabeceras*/
                    				cabecerasPeticion.put(infoSAPCard.getClase_mov_sap(), requestHeaderNewDos);
                				}
                			}
        				}
        			} catch (FinderException e) {
        				log.debug("No se encontraron datos de SAP para la Tarjeta con id: "+ ((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_tarjeta+", para la peticion: "+ nroPeticion.toString());
        			} catch (Exception e){
        				log.debug("No se encontraron datos de SAP para la Tarjeta con id: "+ ((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_tarjeta+", para la peticion: "+ nroPeticion.toString());
        			}
        		}
        	}
        	/*MANEJO DE LA CARGA DE LOS DATOS DE LOS MODEMS*/
        	log.debug("Armando Mensaje de Envio a SAP para los Modems");
        	if(modemSAP != null && modemSAP.size() > 0){
        		identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_MODEM_SAP;
        		
        		for(Iterator iterModem = modemSAP.iterator(); iterModem.hasNext();){
        			ModemLocal modemLocal = (ModemLocal) iterModem.next();
        			codMaterialModem = true;
        			
        			if( cabecerasPeticion.size() <= 0){
        				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
        				requestHeaderNew.setPositionsHeader(new ArrayList());
        				
        				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
        				requestHeaderNew.setAccountingDate(fechaContNew);
        				requestHeaderNew.setMoveType(modemLocal.getClase_mov_sap());
        				/*Se asignan los datos de la cabecera y de la posicion  de modem*/
        				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, modemLocal, null, null, null, requestHeaderNew);
        				/*Se incluye la nueva Cabecera en el HashMap de Cabeceras*/
        				cabecerasPeticion.put(modemLocal.getClase_mov_sap(), requestHeaderNew);
        			}else{
        				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(modemLocal.getClase_mov_sap());
        				
        				if(existeCabeceraPeticion){
        					/*Se asignan los datos de la cabecera y de la posicion  de modem*/
        					asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, modemLocal, null, null, null, (TR020ERequestHeader)cabecerasPeticion.get(modemLocal.getClase_mov_sap()));
        				}else{
        					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
        					requestHeaderNewDos.setPositionsHeader(new ArrayList());
            				
        					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
        					requestHeaderNewDos.setAccountingDate(fechaContNew);
        					requestHeaderNewDos.setMoveType(modemLocal.getClase_mov_sap());
        					/*Se asignan los datos de la cabecera y de la posicion  de modem*/
            				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, modemLocal, null, null, null, requestHeaderNewDos);
            				/*Se incluye la nueva Cabecera en el HashMap de Cabeceras*/
            				cabecerasPeticion.put(modemLocal.getClase_mov_sap(), requestHeaderNewDos);
        				}
        			}
        		}
        	}
        	/*MANEJO DE LA CARGA DE LOS DATOS DE LOS ELEMENTOS NO SERIALIZADOS - ELEMENTO_NO_SERIALIZADO*/
        	log.debug("Armando Mensaje de Envio a SAP para los Equipos No serializados");
        	if(elemNoSerialSAP != null && elemNoSerialSAP.size() > 0){
        		identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP;

        		log.debug("Va ingresar a la Iteracion de Equipos No serializados");
        		for(Iterator iterElemNoSer = elemNoSerialSAP.iterator(); iterElemNoSer.hasNext();){
        			ElementoNoSerializadoLocal elementoNoSerializadoLocal = (ElementoNoSerializadoLocal) iterElemNoSer.next();
        			log.debug("Entro a Iterar sobre los Elementos no Serializados" + elementoNoSerializadoLocal.getNumMaterialSap());
        			codMaterialElementoNoSerializado = true;
        			
        			if(cabecerasPeticion.size() <= 0){
            			log.debug("Entro a Crear la Cabecera" + elementoNoSerializadoLocal.getNumMaterialSap());
        				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
        				requestHeaderNew.setPositionsHeader(new ArrayList());
        				
        				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
        				requestHeaderNew.setAccountingDate(fechaContNew);
        				requestHeaderNew.setMoveType(elementoNoSerializadoLocal.getClaseMovSap());
        				
        				asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, null, null, elementoNoSerializadoLocal, requestHeaderNew);
        				
        				cabecerasPeticion.put(elementoNoSerializadoLocal.getClaseMovSap(), requestHeaderNew);
        			}else{
        				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(elementoNoSerializadoLocal.getClaseMovSap());
            			log.debug("Entro a Agregar los Equipos" + elementoNoSerializadoLocal.getNumMaterialSap());
        				if(existeCabeceraPeticion){
        					asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, null, null, elementoNoSerializadoLocal, (TR020ERequestHeader)cabecerasPeticion.get(elementoNoSerializadoLocal.getClaseMovSap()));
        				}else{
        					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
        					requestHeaderNewDos.setPositionsHeader(new ArrayList());
            				
        					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
        					requestHeaderNewDos.setAccountingDate(fechaContNew);
        					requestHeaderNewDos.setMoveType(elementoNoSerializadoLocal.getClaseMovSap());
            				
        					asignarDatosCabeceraVentaMinoristasSAPST(identificadorTipoEquipo, null, null, null, null, elementoNoSerializadoLocal, requestHeaderNewDos);
            				
            				cabecerasPeticion.put(elementoNoSerializadoLocal.getClaseMovSap(), requestHeaderNewDos);
        				}
        			}
        		}
        	}

        	/*Se convierten las cabeceras en una coleccion y se asignan al mensaje*/
        	Collection arrayCabecerasPeticion = cabecerasPeticion.values();
        	
        	tr020e.setRequestsHeader(arrayCabecerasPeticion);	
        	
//        	se almacena el mensaje que se envia a SAP en la tabla TMP_NOTIFICACION_SAP
        	Long idCorrelativo = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_NOTIFICACION_SAP"));
        	
        	//se obtiene la fecha actual
        	Date date = new Date();
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
        	String fecha = sdf.format(date); 
        	
        	
        	Tmp_Notificacion_SAPLocalHome mensajeHome = (Tmp_Notificacion_SAPLocalHome) HomeFactory.getHome(Tmp_Notificacion_SAPLocalHome.JNDI_NAME);
        	Tmp_Notificacion_SAPLocal mensajeLocal = mensajeHome.create(idCorrelativo);
        	mensajeLocal.setPeti_numero(nroPeticion);
        	mensajeLocal.setCod_pet_cd(nroPeticion);
        	mensajeLocal.setFec_ingreso( Timestamp.valueOf(fecha));
        	mensajeLocal.setMensaje(XMLUtilities.marshall (tr020e));

            /**/
			Mensaje_estado_stLocal msgLocal = msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
	        msgLocal.setPeticion_st(peticionLocal);
	        msgLocal.setCod_actividad_generadora(actGeneradora);
	        msgLocal.setFecha_envio(df.format(new java.util.Date()));
	        msgLocal.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
	        // mensajeEstadoSt.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
	        
	        STNotificacionSAPMQ enviarSAPInfEqMQ = new STNotificacionSAPMQ();
			enviarSAPInfEqMQ.enviarMensaje(tr020e);			
			
			String estado = "Pendiente";
			if(codMaterialElemtoPeticion){
	        	for(Iterator iterElemPet = elemPetiSAP.iterator(); iterElemPet.hasNext();){
        			Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal) iterElemPet.next();
        			materialSAP(elementoPeticionLocal.getNum_material_sap(),  tr020e, estado, fechaContNew, nroPeticion);
	        	}
	        }
	        
	        if(codMaterialDecoTarjeta){
	        	for(Iterator iterDecoTarj = decoTarSAP.iterator(); iterDecoTarj.hasNext();){
        			Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) iterDecoTarj.next();
        			Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco, nroPeticion);
    				Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
    				if(infoSAPDeco.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
    					materialSAP(infoSAPDeco.getNum_material_sap(),  tr020e, estado, fechaContNew, nroPeticion);
    				}
	        	}
	        }
	        if(codMaterialDecoTarjeta2){
	        	for(Iterator iterDecoTarj = decoTarSAP.iterator(); iterDecoTarj.hasNext();){
	        		Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) iterDecoTarj.next();
	    			Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco, nroPeticion);
		        	Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_tarjeta, nroPeticion);
					Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
					if(infoSAPCard.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
						materialSAP(infoSAPCard.getNum_material_sap(),  tr020e, estado, fechaContNew, nroPeticion);
					}
	        	}
	        	
	        }
	        if(codMaterialModem){
	        	for(Iterator iterModem = modemSAP.iterator(); iterModem.hasNext();){
        			ModemLocal modemLocal = (ModemLocal) iterModem.next();
        			if(modemLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        				materialSAP(modemLocal.getNum_material_sap(),  tr020e, estado, fechaContNew, nroPeticion);
        			}
	        	}
	        	
	        }
	        if(codMaterialElementoNoSerializado){
	        	for(Iterator iterElemNoSer = elemNoSerialSAP.iterator(); iterElemNoSer.hasNext();){
        			ElementoNoSerializadoLocal elementoNoSerializadoLocal = (ElementoNoSerializadoLocal) iterElemNoSer.next();
        			if(elementoNoSerializadoLocal.getFlagMatSap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        				materialSAP(elementoNoSerializadoLocal.getNumMaterialSap(),  tr020e, estado, fechaContNew, nroPeticion);
        			}
	        	}
	        }
        } else if(fechaContNew.equals(ComunInterfaces.FECHA_CONTABILIZACION_INVALIDO)){
			log.error("Se envía la petición a PGI en la actividad de Notificación SAP ST por tener una fecha de contabilizacion invalida, para la petición: "+ nroPeticion);				

			act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
			act.setObservacion("Se presentó un problema al intentar calcular la fecha de contabilización que se envía a SAP, se deriva a solución recursos ST.", true);
			act.setRealizarTerminoInmediato(true);
        } else if((elemPetiSAP == null || elemPetiSAP.size() <= 0) && 
        		(decoTarSAP == null || decoTarSAP.size() <= 0) && 
				(modemSAP == null || modemSAP.size() <= 0) &&
				(elemNoSerialSAP==null || elemNoSerialSAP.size() <= 0)){
        	log.error("Se inhibe la actividad de Notificación SAP ST por no tener equipos marcados para el envio a SAP, para la petición: "+ nroPeticion);				
			act.setObservacion("Se inhibe la actividad por no tener equipos marcados para el envio a SAP.", true);
			act.setRealizarTerminoInmediato(true);
        }           
	}
	
	/**
	 * Metodo para asignar los datos de una cabecera
	 * @param identificadorTipoEquipo
	 * @param elementoLocal
	 * @param modemLocal
	 * @param decoTarjInfoSAPLocal
	 * @param decoTarjetaLocal
	 * @param tr020eRequest
	 */
	private void asignarDatosCabeceraVentaMinoristasSAPST(String identificadorTipoEquipo, 
			Elemento_PeticionLocal elementoLocal, ModemLocal modemLocal, 
			Deco_Tarjeta_Info_SapLocal decoTarjInfoSAPLocal, Deco_tarjetaLocal decoTarjetaLocal,
			ElementoNoSerializadoLocal elementoNoSerializadoLocal,
			TR020ERequestHeader tr020eRequest){
		
		TR020EPositionHeader tr020ePosition = new TR020EPositionHeader();
        Collection seriales = new ArrayList();	
        tr020ePosition.setMatQuantity("0");
		
		if(tr020eRequest.getPositionsHeader() != null && tr020eRequest.getPositionsHeader().size() <= 0){
			tr020ePosition = new TR020EPositionHeader();
        	seriales = new ArrayList();
        	tr020ePosition.setSerials(seriales);
        	tr020ePosition.setMatQuantity("0");
        	
        	asignarDatosPosicionVentaMinoristasSAPST(identificadorTipoEquipo, elementoLocal, modemLocal, decoTarjInfoSAPLocal, decoTarjetaLocal, elementoNoSerializadoLocal, tr020ePosition);
        	/*Se asigna la posicion a la cabecera*/
        	tr020eRequest.getPositionsHeader().add(tr020ePosition);
        }else{
        	boolean encontro = false;
        	DatosSAPDTO datosSapDto = new DatosSAPDTO();
        	
        	for (Iterator it = tr020eRequest.getPositionsHeader().iterator(); it.hasNext() && !encontro;) {
        		TR020EPositionHeader posicion = (TR020EPositionHeader) it.next();
        		/*Inicializa las variables contra las que va a comparar para saber si es la misma Posicion*/
        		asignarDatosComparacionSAPST(identificadorTipoEquipo, elementoLocal, modemLocal, decoTarjInfoSAPLocal, decoTarjetaLocal, elementoNoSerializadoLocal, 
        				datosSapDto);
        		
        		/*Manejo para los Elemento_Peticion*/
        		if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEMENTO_PETICION_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            	        long canMatNum = Long.parseLong(cantMaterialStr) + 1;
            	        
            	        posicion.setMatQuantity(Long.toString(canMatNum));
            	        
            	        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey(); 	        
            	        if(elemPeticionKey != null && elemPeticionKey.serial != null){
            	        	if(elemPeticionKey.serial.length() > 18){
            	        		posicion.getSerials().add(elemPeticionKey.serial.substring(0,18));
            	        	}else{
            	        		posicion.getSerials().add(elemPeticionKey.serial);
            	        	}               
            	        }

            	        encontro = true;
            		}                        
        		}        		        		
        		/*Manejo para los Modem*/
        		else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_MODEM_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            	        long canMatNum = Long.parseLong(cantMaterialStr) + 1;
            	        
            	        posicion.setMatQuantity(Long.toString(canMatNum));
            	        
            	        ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
            	        if(modemKey != null && modemKey.serial != null){
            	        	if(modemKey.serial.length() > 18){
            	        		posicion.getSerials().add(modemKey.serial.substring(0,18));
            	        	}else{
            	        		posicion.getSerials().add(modemKey.serial);
            	        	}               
            	        }

            	        encontro = true;
            		}
        		}        		        	
        		/*Manejo para los Deco de Deco_Tarjeta*/
        		else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_DECO_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            	        long canMatNum = Long.parseLong(cantMaterialStr) + 1;
            	        
            	        posicion.setMatQuantity(Long.toString(canMatNum));
            	        
            	        if(decoTarjetaLocal != null && decoTarjetaLocal.getSerial_deco() != null){
            	        	if(decoTarjetaLocal.getSerial_deco().length() > 18){
            	        		posicion.getSerials().add(decoTarjetaLocal.getSerial_deco().substring(0,18));
            	        	}else{
            	        		posicion.getSerials().add(decoTarjetaLocal.getSerial_deco());
            	        	}
            	        }
            	        
            	        encontro = true;
            		}
        		}
        		/*Manejo para las Tarjeta de Deco_Tarjeta*/
        		else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_TARJETA_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            	        long canMatNum = Long.parseLong(cantMaterialStr) + 1;
            	        
            	        posicion.setMatQuantity(Long.toString(canMatNum));
            	        
            	        Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
            	        if(decoTarjetaKey != null && decoTarjetaKey.id_tarjeta != null){
            	        	if(decoTarjetaKey.id_tarjeta.length() > 18){
            	        		posicion.getSerials().add(decoTarjetaKey.id_tarjeta.substring(0,18));
            	        	}else{
            	        		posicion.getSerials().add(decoTarjetaKey.id_tarjeta);
            	        	}               
            	        }

            	        encontro = true;
            		}
        		}
        		/*Manejo para los Elemento_No_Serializado*/
        		else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            			Long longCantidadElemNoSerial = elementoNoSerializadoLocal.getCantidad();
            			if(longCantidadElemNoSerial != null){
            				long canMatNum = Long.parseLong(cantMaterialStr) + longCantidadElemNoSerial.longValue();
            				
            				posicion.setMatQuantity(Long.toString(canMatNum));
            			}

            			encontro = true;
            			
//            	        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey(); 	        
//            	        if(elemPeticionKey != null && elemPeticionKey.serial != null){
//            	        	if(elemPeticionKey.serial.length() > 18){
//            	        		posicion.getSerials().add(elemPeticionKey.serial.substring(0,18));
//            	        	}else{
//            	        		posicion.getSerials().add(elemPeticionKey.serial);
//            	        	}               
//            	        }

            		}                        
        		}
        	}
        	
        	if(!encontro){
            	tr020ePosition = new TR020EPositionHeader();
            	seriales = new ArrayList();
            	tr020ePosition.setSerials(seriales);
            	tr020ePosition.setMatQuantity("0");
            	
            	asignarDatosPosicionVentaMinoristasSAPST(identificadorTipoEquipo, elementoLocal, modemLocal, decoTarjInfoSAPLocal, decoTarjetaLocal, elementoNoSerializadoLocal, tr020ePosition);
            	
            	tr020eRequest.getPositionsHeader().add(tr020ePosition);
        	}
        }          			
	}
	
	/**
	 * Metodo para asignar los datos de la posición
	 * @param identificadorTipoEquipo
	 * @param elementoLocal
	 * @param modemLocal
	 * @param decoTarjInfoSAPLocal
	 * @param decoTarjetaLocal
	 * @param tr020ePosition
	 */
	private void asignarDatosPosicionVentaMinoristasSAPST(String identificadorTipoEquipo, 
			Elemento_PeticionLocal elementoLocal, ModemLocal modemLocal, 
			Deco_Tarjeta_Info_SapLocal decoTarjInfoSAPLocal, Deco_tarjetaLocal decoTarjetaLocal,
			ElementoNoSerializadoLocal elementoNoSerializadoLocal,
			TR020EPositionHeader tr020ePosition){
		
		if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEMENTO_PETICION_SAP)){
			if(elementoLocal != null && elementoLocal.getPos_doc_sap() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(elementoLocal.getPos_doc_sap()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(elementoLocal != null && elementoLocal.getNum_material_sap() != null)
				tr020ePosition.setMaterial(elementoLocal.getNum_material_sap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(elementoLocal != null && elementoLocal.getCentro_sap() != null)
	        	tr020ePosition.setPlant(elementoLocal.getCentro_sap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(elementoLocal != null && elementoLocal.getAlmacen_sap() != null)
	        	tr020ePosition.setStorage(elementoLocal.getAlmacen_sap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(elementoLocal != null && elementoLocal.getCod_lote_sap() != null)
	        	tr020ePosition.setBatchCode(elementoLocal.getCod_lote_sap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        String cantMaterialStr = tr020ePosition.getMatQuantity();
	        if(cantMaterialStr != null){
	        	long canMatNum = Long.parseLong(cantMaterialStr) + 1;		        
		        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
	        }else{
	        	tr020ePosition.setMatQuantity("1");
	        }
	        
	        if(elementoLocal != null && elementoLocal.getUnd_medida_sap() != null)
	        	tr020ePosition.setMeasurementUnit(elementoLocal.getUnd_medida_sap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(elementoLocal != null && elementoLocal.getCentr_cost_sap() != null)
	        	tr020ePosition.setCostCenter(elementoLocal.getCentr_cost_sap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(elementoLocal != null && elementoLocal.getArea_func_sap() != null)        	
	        	tr020ePosition.setFuncAreaLong(elementoLocal.getArea_func_sap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(elementoLocal != null && elementoLocal.getElement_pep_sap() != null)        	
	        	tr020ePosition.setPepElement(elementoLocal.getElement_pep_sap());
	        else
	        	tr020ePosition.setPepElement("");
	        
	        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey(); 	        
	        if(elemPeticionKey != null && elemPeticionKey.serial != null){
	        	if(elemPeticionKey.serial.length() > 18){
	        		tr020ePosition.getSerials().add(elemPeticionKey.serial.substring(0,18));
	        	}else{
	        		tr020ePosition.getSerials().add(elemPeticionKey.serial);
	        	}               
	        }
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_MODEM_SAP)){
			if(modemLocal != null && modemLocal.getPos_doc_sap() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(modemLocal.getPos_doc_sap()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(modemLocal != null && modemLocal.getNum_material_sap() != null)
				tr020ePosition.setMaterial(modemLocal.getNum_material_sap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(modemLocal != null && modemLocal.getCentro_sap() != null)
	        	tr020ePosition.setPlant(modemLocal.getCentro_sap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(modemLocal != null && modemLocal.getAlmacen_sap() != null)
	        	tr020ePosition.setStorage(modemLocal.getAlmacen_sap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(modemLocal != null && modemLocal.getCod_lote_sap() != null)
	        	tr020ePosition.setBatchCode(modemLocal.getCod_lote_sap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        String cantMaterialStr = tr020ePosition.getMatQuantity();
	        if(cantMaterialStr != null){
	        	long canMatNum = Long.parseLong(cantMaterialStr) + 1;		        
		        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
	        }else{
	        	tr020ePosition.setMatQuantity("1");
	        }
	        
	        if(modemLocal != null && modemLocal.getUnd_medida_sap() != null)
	        	tr020ePosition.setMeasurementUnit(modemLocal.getUnd_medida_sap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(modemLocal != null && modemLocal.getCentr_cost_sap() != null)
	        	tr020ePosition.setCostCenter(modemLocal.getCentr_cost_sap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(modemLocal != null && modemLocal.getArea_func_sap() != null)        	
	        	tr020ePosition.setFuncAreaLong(modemLocal.getArea_func_sap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(modemLocal != null && modemLocal.getElement_pep_sap() != null)        	
	        	tr020ePosition.setPepElement(modemLocal.getElement_pep_sap());
	        else
	        	tr020ePosition.setPepElement("");
	        
	        ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
	        if(modemKey != null && modemKey.serial != null){
	        	if(modemKey.serial.length() > 18){
	        		tr020ePosition.getSerials().add(modemKey.serial.substring(0,18));
	        	}else{
	        		tr020ePosition.getSerials().add(modemKey.serial);
	        	}               
	        }
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_DECO_SAP)){
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getPos_doc_sap() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(decoTarjInfoSAPLocal.getPos_doc_sap()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getNum_material_sap() != null)
				tr020ePosition.setMaterial(decoTarjInfoSAPLocal.getNum_material_sap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentro_sap() != null)
	        	tr020ePosition.setPlant(decoTarjInfoSAPLocal.getCentro_sap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getAlmacen_sap() != null)
	        	tr020ePosition.setStorage(decoTarjInfoSAPLocal.getAlmacen_sap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCod_lote_sap() != null)
	        	tr020ePosition.setBatchCode(decoTarjInfoSAPLocal.getCod_lote_sap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        String cantMaterialStr = tr020ePosition.getMatQuantity();
	        if(cantMaterialStr != null){
	        	long canMatNum = Long.parseLong(cantMaterialStr) + 1;		        
		        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
	        }else{
	        	tr020ePosition.setMatQuantity("1");
	        }int canMatNum = Integer.parseInt(cantMaterialStr) + 1;
	        
	        tr020ePosition.setMatQuantity(Integer.toString(canMatNum));
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getUnd_medida_sap() != null)
	        	tr020ePosition.setMeasurementUnit(decoTarjInfoSAPLocal.getUnd_medida_sap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentr_cost_sap() != null)
	        	tr020ePosition.setCostCenter(decoTarjInfoSAPLocal.getCentr_cost_sap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getArea_func_sap() != null)        	
	        	tr020ePosition.setFuncAreaLong(decoTarjInfoSAPLocal.getArea_func_sap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getElement_pep_sap() != null)        	
	        	tr020ePosition.setPepElement(decoTarjInfoSAPLocal.getElement_pep_sap());
	        else
	        	tr020ePosition.setPepElement("");
	        
	        if(decoTarjetaLocal != null && decoTarjetaLocal.getSerial_deco() != null){
	        	if(decoTarjetaLocal.getSerial_deco().length() > 18){
	        		tr020ePosition.getSerials().add(decoTarjetaLocal.getSerial_deco().substring(0,18));
	        	}else{
	        		tr020ePosition.getSerials().add(decoTarjetaLocal.getSerial_deco());
	        	}
	        }
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_TARJETA_SAP)){
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getPos_doc_sap() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(decoTarjInfoSAPLocal.getPos_doc_sap()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getNum_material_sap() != null)
				tr020ePosition.setMaterial(decoTarjInfoSAPLocal.getNum_material_sap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentro_sap() != null)
	        	tr020ePosition.setPlant(decoTarjInfoSAPLocal.getCentro_sap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getAlmacen_sap() != null)
	        	tr020ePosition.setStorage(decoTarjInfoSAPLocal.getAlmacen_sap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCod_lote_sap() != null)
	        	tr020ePosition.setBatchCode(decoTarjInfoSAPLocal.getCod_lote_sap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        String cantMaterialStr = tr020ePosition.getMatQuantity();
	        if(cantMaterialStr != null){
	        	long canMatNum = Long.parseLong(cantMaterialStr) + 1;		        
		        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
	        }else{
	        	tr020ePosition.setMatQuantity("1");
	        }
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getUnd_medida_sap() != null)
	        	tr020ePosition.setMeasurementUnit(decoTarjInfoSAPLocal.getUnd_medida_sap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentr_cost_sap() != null)
	        	tr020ePosition.setCostCenter(decoTarjInfoSAPLocal.getCentr_cost_sap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getArea_func_sap() != null)        	
	        	tr020ePosition.setFuncAreaLong(decoTarjInfoSAPLocal.getArea_func_sap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getElement_pep_sap() != null)        	
	        	tr020ePosition.setPepElement(decoTarjInfoSAPLocal.getElement_pep_sap());
	        else
	        	tr020ePosition.setPepElement("");

	        Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
	        if(decoTarjetaKey != null && decoTarjetaKey.id_tarjeta != null){
	        	if(decoTarjetaKey.id_tarjeta.length() > 18){
	        		tr020ePosition.getSerials().add(decoTarjetaKey.id_tarjeta.substring(0,18));
	        	}else{
	        		tr020ePosition.getSerials().add(decoTarjetaKey.id_tarjeta);
	        	}               
	        }
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP)){
			
			if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getPosDocSap() != null && elementoNoSerializadoLocal.getPosDocSap().intValue() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(elementoNoSerializadoLocal.getPosDocSap().intValue()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getNumMaterialSap() != null)
				tr020ePosition.setMaterial(elementoNoSerializadoLocal.getNumMaterialSap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCentroSap() != null)
	        	tr020ePosition.setPlant(elementoNoSerializadoLocal.getCentroSap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getAlmacenSap() != null)
	        	tr020ePosition.setStorage(elementoNoSerializadoLocal.getAlmacenSap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCodLoteSap() != null)
	        	tr020ePosition.setBatchCode(elementoNoSerializadoLocal.getCodLoteSap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        Long longCantidadElemNoSerial = elementoNoSerializadoLocal.getCantidad();	        
			if(longCantidadElemNoSerial != null){
				
				String cantMaterialStr = tr020ePosition.getMatQuantity();
		        if(cantMaterialStr != null){
		        	long canMatNum = Long.parseLong(cantMaterialStr) + longCantidadElemNoSerial.longValue();		        
			        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
		        }else{
		        	tr020ePosition.setMatQuantity(longCantidadElemNoSerial.toString());
		        }
			}else{
				log.debug("Uno de los elementos no serializados no tiene asignada la cantidad. No se realiza ninguna accion sobre la catidad de la posicion.");
			}

	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getUndMedidaSap() != null)
	        	tr020ePosition.setMeasurementUnit(elementoNoSerializadoLocal.getUndMedidaSap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCentrCostSap() != null)
	        	tr020ePosition.setCostCenter(elementoNoSerializadoLocal.getCentrCostSap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getAreaFuncSap() != null)        	
	        	tr020ePosition.setFuncAreaLong(elementoNoSerializadoLocal.getAreaFuncSap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getElementPepSap() != null)        	
	        	tr020ePosition.setPepElement(elementoNoSerializadoLocal.getElementPepSap());
	        else
	        	tr020ePosition.setPepElement("");
	        
//	        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey(); 	        
//	        if(elemPeticionKey != null && elemPeticionKey.serial != null){
//	        	if(elemPeticionKey.serial.length() > 18){
//	        		tr020ePosition.getSerials().add(elemPeticionKey.serial.substring(0,18));
//	        	}else{
//	        		tr020ePosition.getSerials().add(elemPeticionKey.serial);
//	        	}               
//	        }
		}
	}
	
	/**
	 * Metodo para asignar los datos de comparación a las variables
	 * @param identificadorTipoEquipo
	 * @param elementoLocal
	 * @param modemLocal
	 * @param decoTarjInfoSAPLocal
	 * @param decoTarjetaLocal
	 * @param datosSapDto
	 */
	private void asignarDatosComparacionSAPST(String identificadorTipoEquipo, 
			Elemento_PeticionLocal elementoLocal, ModemLocal modemLocal, 
			Deco_Tarjeta_Info_SapLocal decoTarjInfoSAPLocal, Deco_tarjetaLocal decoTarjetaLocal,
			ElementoNoSerializadoLocal elementoNoSerializadoLocal,
			DatosSAPDTO datosSapDto){

		if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEMENTO_PETICION_SAP)){
			if(elementoLocal != null && elementoLocal.getPos_doc_sap() != 0)
				datosSapDto.setMaterialCodeSap(Integer.toString(elementoLocal.getPos_doc_sap()));
	        else
	        	datosSapDto.setMaterialCodeSap("");
			
			if(elementoLocal != null && elementoLocal.getNum_material_sap() != null)
				datosSapDto.setMaterialSap(elementoLocal.getNum_material_sap());
	        else
	        	datosSapDto.setMaterialSap("");
	        
	        if(elementoLocal != null && elementoLocal.getCentro_sap() != null)
	        	datosSapDto.setPlantSap(elementoLocal.getCentro_sap());
	        else
	        	datosSapDto.setPlantSap("");
	        
	        if(elementoLocal != null && elementoLocal.getAlmacen_sap() != null)
	        	datosSapDto.setStorageSap(elementoLocal.getAlmacen_sap());
	        else
	        	datosSapDto.setStorageSap("");
	        
	        if(elementoLocal != null && elementoLocal.getCod_lote_sap() != null)
	        	datosSapDto.setBatchCodeSap(elementoLocal.getCod_lote_sap());
	        else
	        	datosSapDto.setBatchCodeSap("");
	        
	        if(elementoLocal != null && elementoLocal.getUnd_medida_sap() != null)
	        	datosSapDto.setMeasurementUnitSap(elementoLocal.getUnd_medida_sap());
	        else
	        	datosSapDto.setMeasurementUnitSap("");
	        
	        if(elementoLocal != null && elementoLocal.getCentr_cost_sap() != null)
	        	datosSapDto.setCostCenterSap(elementoLocal.getCentr_cost_sap());
	        else
	        	datosSapDto.setCostCenterSap("");
	        
	        if(elementoLocal != null && elementoLocal.getArea_func_sap() != null)        	
	        	datosSapDto.setFuncAreaLongSap(elementoLocal.getArea_func_sap());
	        else
	        	datosSapDto.setFuncAreaLongSap("");
	        
	        if(elementoLocal != null && elementoLocal.getElement_pep_sap() != null)        	
	        	datosSapDto.setPepElementSap(elementoLocal.getElement_pep_sap());
	        else
	        	datosSapDto.setPepElementSap("");
	        
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_MODEM_SAP)){
			if(modemLocal != null && modemLocal.getPos_doc_sap() != 0)				
				datosSapDto.setMaterialCodeSap(Integer.toString(modemLocal.getPos_doc_sap()));
	        else
	        	datosSapDto.setMaterialCodeSap("");
			
			if(modemLocal != null && modemLocal.getNum_material_sap() != null)				
				datosSapDto.setMaterialSap(modemLocal.getNum_material_sap());
	        else
	        	datosSapDto.setMaterialSap("");
	        
	        if(modemLocal != null && modemLocal.getCentro_sap() != null)
	        	datosSapDto.setPlantSap(modemLocal.getCentro_sap());
	        else
	        	datosSapDto.setPlantSap("");
	        
	        if(modemLocal != null && modemLocal.getAlmacen_sap() != null)
	        	datosSapDto.setStorageSap(modemLocal.getAlmacen_sap());
	        else
	        	datosSapDto.setStorageSap("");
	        
	        if(modemLocal != null && modemLocal.getCod_lote_sap() != null)	        	
	        	datosSapDto.setBatchCodeSap(modemLocal.getCod_lote_sap());
	        else
	        	datosSapDto.setBatchCodeSap("");
	        
	        if(modemLocal != null && modemLocal.getUnd_medida_sap() != null)	        	
	        	datosSapDto.setMeasurementUnitSap(modemLocal.getUnd_medida_sap());
	        else
	        	datosSapDto.setMeasurementUnitSap("");
	        
	        if(modemLocal != null && modemLocal.getCentr_cost_sap() != null)	        	
	        	datosSapDto.setCostCenterSap(modemLocal.getCentr_cost_sap());
	        else
	        	datosSapDto.setCostCenterSap("");
	        
	        if(modemLocal != null && modemLocal.getArea_func_sap() != null)        	
	        	datosSapDto.setFuncAreaLongSap(modemLocal.getArea_func_sap());
	        else
	        	datosSapDto.setFuncAreaLongSap("");
	        
	        if(modemLocal != null && modemLocal.getElement_pep_sap() != null)        	
	        	datosSapDto.setPepElementSap(modemLocal.getElement_pep_sap());
	        else
	        	datosSapDto.setPepElementSap("");

		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_DECO_SAP) 
				|| identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_TARJETA_SAP)){
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getPos_doc_sap() != 0)				
				datosSapDto.setMaterialCodeSap(Integer.toString(decoTarjInfoSAPLocal.getPos_doc_sap()));
	        else
	        	datosSapDto.setMaterialCodeSap("");
			
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getNum_material_sap() != null)				
				datosSapDto.setMaterialSap(decoTarjInfoSAPLocal.getNum_material_sap());
	        else
	        	datosSapDto.setMaterialSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentro_sap() != null)
	        	datosSapDto.setPlantSap(decoTarjInfoSAPLocal.getCentro_sap());
	        else
	        	datosSapDto.setPlantSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getAlmacen_sap() != null)
	        	datosSapDto.setStorageSap(decoTarjInfoSAPLocal.getAlmacen_sap());
	        else
	        	datosSapDto.setStorageSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCod_lote_sap() != null)
	        	datosSapDto.setBatchCodeSap(decoTarjInfoSAPLocal.getCod_lote_sap());
	        else
	        	datosSapDto.setBatchCodeSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getUnd_medida_sap() != null)	        	
	        	datosSapDto.setMeasurementUnitSap(decoTarjInfoSAPLocal.getUnd_medida_sap());
	        else
	        	datosSapDto.setMeasurementUnitSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentr_cost_sap() != null)
	        	datosSapDto.setCostCenterSap(decoTarjInfoSAPLocal.getCentr_cost_sap());
	        else
	        	datosSapDto.setCostCenterSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getArea_func_sap() != null)        	
	        	datosSapDto.setFuncAreaLongSap(decoTarjInfoSAPLocal.getArea_func_sap());
	        else
	        	datosSapDto.setFuncAreaLongSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getElement_pep_sap() != null)        	
	        	datosSapDto.setPepElementSap(decoTarjInfoSAPLocal.getElement_pep_sap());
	        else
	        	datosSapDto.setPepElementSap("");
	        
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP)){
			
			if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getPosDocSap() != null && elementoNoSerializadoLocal.getPosDocSap().intValue() != 0)
				datosSapDto.setMaterialCodeSap(Integer.toString(elementoNoSerializadoLocal.getPosDocSap().intValue()));
	        else
	        	datosSapDto.setMaterialCodeSap("");
			
			if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getNumMaterialSap() != null)
				datosSapDto.setMaterialSap(elementoNoSerializadoLocal.getNumMaterialSap());
	        else
	        	datosSapDto.setMaterialSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCentroSap() != null)
	        	datosSapDto.setPlantSap(elementoNoSerializadoLocal.getCentroSap());
	        else
	        	datosSapDto.setPlantSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getAlmacenSap() != null)
	        	datosSapDto.setStorageSap(elementoNoSerializadoLocal.getAlmacenSap());
	        else
	        	datosSapDto.setStorageSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCodLoteSap() != null)
	        	datosSapDto.setBatchCodeSap(elementoNoSerializadoLocal.getCodLoteSap());
	        else
	        	datosSapDto.setBatchCodeSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getUndMedidaSap() != null)
	        	datosSapDto.setMeasurementUnitSap(elementoNoSerializadoLocal.getUndMedidaSap());
	        else
	        	datosSapDto.setMeasurementUnitSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCentrCostSap() != null)
	        	datosSapDto.setCostCenterSap(elementoNoSerializadoLocal.getCentrCostSap());
	        else
	        	datosSapDto.setCostCenterSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getAreaFuncSap() != null)        	
	        	datosSapDto.setFuncAreaLongSap(elementoNoSerializadoLocal.getAreaFuncSap());
	        else
	        	datosSapDto.setFuncAreaLongSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getElementPepSap() != null)        	
	        	datosSapDto.setPepElementSap(elementoNoSerializadoLocal.getElementPepSap());
	        else
	        	datosSapDto.setPepElementSap("");
	        
		}
	}
	public void materialSAP(String codmaterial, TR020E tr020e, String estado, String fechaContNew,Long nroPeticion ){
		Mensaje_descarga_SAP_STLocal mensajeDescargaSAPLocal;
		String consecutivo= null;
		log.debug("Ingresa a materialSAP, para el material"+codmaterial+" y la petición: "+ nroPeticion);

		try{
			mensajeDescargaSAPLocal = mensajeDescargaSAPLocalHome.create(new Long(dbSeq.seqNextValLong("SOLTEC.CORRELATIVO_DESCARGA_SAP")));
			mensajeDescargaSAPLocal.setCod_ave_cd(nroPeticion);
	    	mensajeDescargaSAPLocal.setEst_pedido(estado);
	    	mensajeDescargaSAPLocal.setFecha_eje_sap(fechaContNew);
	    	mensajeDescargaSAPLocal.setId_cod_mat(codmaterial);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error a Crear la informacion en Notificación SAP", e);
		} 
		
	}
}