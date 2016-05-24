package co.com.telefonica.atiempo.vpistbba.instalacion.ejb;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.*;
import co.com.telefonica.atiempo.interfaces.atiempo.ErrorRefreshTOA;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017EPackage;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR802E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR802S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR803S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR804S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811SBreakPair;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811SMaterials;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.EQUIPOSCLIENTE;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.OTHERELEMENT;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.OTHERTYPE;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.TR800E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XABROADBANDDATA;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XAEQUIPMENT;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XATELEPHONEDATA;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XATVDATA;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800S.TR800S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800S.TR800SParser;
import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.CommunicatorWSDelegate;
import co.com.telefonica.atiempo.vpistbba.TOA.GestionServiciosActivar;
import co.com.telefonica.atiempo.vpistbba.TOA.GestionServiciosActivarBA;
import co.com.telefonica.atiempo.vpistbba.TOA.GestionServiciosSTBBA;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InfoPostVentaTV;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.RefrescarRecursosTVTOAMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudConfiguracionServiciosTVMQ;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioDelegate;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: TOAServicio
 */
public class TOAServicioBean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter implements TOAInterfaces {

	//Se crean variables requridas para el proceso
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	private TR800E tr800e = new TR800E();
	private OTHERTYPE otherType = new OTHERTYPE();
	private OTHERELEMENT otherElement = new OTHERELEMENT();
	private PeticionLocalHome peticionLocalHome;
	//private PeticionLocal peticionLocal;
	private SubsegmentoKey subsegmentoKey;
	private SubsegmentoLocal subsegmentoLocal;
	private SegmentoKey segmentoKey;
	private SegmentoLocal segmentoLocal;
	private SubsegmentoLocalHome subsegmentoLocalHome;
	private SegmentoLocalHome segmentoLocalHome;
	private PeticionKey peticionKey;
	private Peticion_atisLocal peticion_atisLocal;
	private Peticion_atisKey peticion_atisKey;
	private Peticion_atisLocalHome peticion_atisLocalHome;
	private Bitacora_peticionLocalHome bitacora;
	private Collection bitacoraLocal;
//	private ArrayList datosOpcionalesArray;
	private ArrayList otrosDatosArray;
	private ArrayList xa_number_decoders;
	private Recursos_linea_basicaLocal rlb;
	private Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome; 
	private Long idPeticion;
	private XATELEPHONEDATA xaTelephoneData;
	private RecursosBADelegate recursosBADelegate;
	private XABROADBANDDATA xaBroadBandData; 
	private Producto_servicioLocal producto_servicioLocal;
	private XATVDATA xaTvData;
	private EQUIPOSCLIENTE equiposCliente;
	private XAEQUIPMENT xaequipment;
	private PeticionesDelegate peticionesDelegate;
	private static int cantDecosInst = 0;
	private static int cantDecosDesins = 0;
	private Agenda_scLocalHome agenda_SCLocalHome;
	private UsuarioLocalHome usuarioHome;
	private Catalogo_causalLocalHome catalogo_causalHome;
	private Estado_psLocalHome estado_psHome;
	private Causal_peticionLocalHome causal_peticionHome;
	private Estado_ps_peticionLocalHome estado_ps_peticionHome;
	private Long idAplicacion;
	private BintegradaLocalHome bintegradaLocalHome;
	private Mensaje_estado_tvLocalHome mensaje_estado_tvHome;
	public static final int estadoEspera= 3 ;
	public static final int estadoOK= 1 ;
	
	private DBManager dbSeq ;
	private SimpleDateFormat df;
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#crearActuacionTOA(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void setSessionContext (SessionContext ctx)
	throws EJBException, RemoteException
	{
		super.setSessionContext (ctx) ;
		// Creacion de DataSource
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
		df = new SimpleDateFormat ("yyyy/MM/dd") ;
	}
	
	private void inicializarVariables (){
		try {
			
			dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
			df = new SimpleDateFormat ("yyyy/MM/dd") ;
		
			peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			segmentoLocalHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
			subsegmentoLocalHome = (SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
			recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
			agenda_SCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			xaTelephoneData = new XATELEPHONEDATA();
			xaBroadBandData = new XABROADBANDDATA();
			xaTvData = new XATVDATA();
			equiposCliente = new EQUIPOSCLIENTE();
			xaequipment = new XAEQUIPMENT();
			ArrayList datosOpcionalesArray = new ArrayList();
			idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			mensaje_estado_tvHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome(Mensaje_estado_tvLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public void crearActuacionTOA(ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		log.debug("ingresa al procedimiento de crearActuacionTOA: " + act.getNumeroPeticion());
		
		try {
			//Se instancias las variables Globales
			peticionLocalHome = null;
			PeticionLocal peticionLocal = null;
			inicializarVariables ();
			idPeticion=act.getNumeroPeticion();
			peticionesDelegate = new PeticionesDelegate();
			peticionLocal = peticionLocalHome.findByPetiNumero(idPeticion);
			peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			peticion_atisLocal = peticionLocal.getFk_01_pet_atis();
			peticion_atisKey = (Peticion_atisKey) peticion_atisLocal.getPrimaryKey();
			subsegmentoKey = new SubsegmentoKey(peticionLocal.getCod_sbg_cta_cd());
			subsegmentoLocal = subsegmentoLocalHome.findByPrimaryKey(subsegmentoKey);
			segmentoKey = new SegmentoKey(peticionLocal.getCod_sgm_cta_cd());
			segmentoLocal = segmentoLocalHome.findByPrimaryKey(segmentoKey);
			Collection psPeticion=peticionLocal.getProducto_servicio_peticion();
			String sufijoSegmento = act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_PGACS")) ?
					peticionesDelegate.recuperarParametroFromPropertiesBD("AUTOINS_SEGMENTO") : "" ;
			
			//Se setean los valores a la tr
			tr800e.setXA_FAMILIA(peticionLocal.getPeti_id_instancia());
			tr800e.setXA_SOURCE_SYSTEM(sistemaAtiempo);
			tr800e.setCname(Utiles.sinNull(peticionLocal.getNom_ds(),"") + " " +Utiles.sinNull(peticionLocal.getPri_ape_ds(),"") + " " + Utiles.sinNull(peticionLocal.getSeg_ape_ds(),""));
			tr800e.setXA_CUSTOMER_ID_TYPE(peticionLocal.getTip_doc_cd());
			tr800e.setCustomer_number("0");
			if(peticionLocal.getNum_doc_cli_cd()!=null)
				tr800e.setCustomer_number(peticionLocal.getNum_doc_cli_cd());
			tr800e.setXA_CUSTOMER_SEGMENT(segmentoLocal.getSegm_descripcion() + sufijoSegmento);
			tr800e.setXA_CUSTOMER_SUBSEGMENT(subsegmentoLocal.getDescripcion());
			
			ArrayList listaSubpeticiones=peticionesDelegate.obtenerSubpeticionesDesdePeticion(idPeticion);
			String infoContactYMedia = "";
			for(Iterator listaSubpeticionesIt=listaSubpeticiones.iterator();listaSubpeticionesIt.hasNext();){
				Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal)listaSubpeticionesIt.next();
				if(subpeticion_atisLocal.getObs_sub_ds() != null && !subpeticion_atisLocal.getObs_sub_ds().equals(""))
					infoContactYMedia = subpeticion_atisLocal.getObs_sub_ds();
			}
			tr800e.setXA_DAT_AGDMTO_1(infoContactYMedia);//Pendiente Setear
			tr800e.setXA_DAT_AGDMTO_2(infoContactYMedia);//Pendiente Setear
			
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
			tr800e.setXA_CONTACT_NAME(peticionLocal.getNom_int_ds()+" "+peticionLocal.getPri_ape_int_ds()+" "+ peticionLocal.getSeg_ape_int_ds());
			tr800e.setXA_DAT_OP_CLIENT(null);
			tr800e.setCell(peticionLocal.getTel_cot_ds_1());
			tr800e.setXA_CONTACT_PHONE_NUMBER_2("0");
			tr800e.setPhone("0");
			if(peticionLocal.getNum_ide_nu_stb()!=null && peticionLocal.getNum_ide_nu_stb().length()>0){
				//tr800e.setXA_CONTACT_PHONE_NUMBER_2(peticionLocal.getNum_ide_nu_stb());
				tr800e.setPhone(peticionLocal.getNum_ide_nu_stb());
			}
			tr800e.setXA_CONTACT_PHONE_NUMBER_2(peticionLocal.getTel_cot_ds_2());
			tr800e.setXA_CONTACT_PHONE_NUMBER_3("");//Pendiente setear
			tr800e.setXA_CONTACT_PHONE_NUMBER_4(peticionLocal.getTel_cot_ds_2());
			tr800e.setXA_CONTACT_PHONE_NUMBER_5(peticionLocal.getTel_cot_ds());
			tr800e.setXA_CONTACT_PHONE_NUMBER_6(peticionLocal.getTel_cot_ds_1());
			tr800e.setEmail("");//Pendiente setear
			tr800e.setXA_DAT_OP_CONTACT(null);
			tr800e.setAddress(peticionLocal.getNom_cal_ds()+" "+peticionLocal.getNum_cal_nu()+" "+peticionLocal.getDsc_cmp_pri_ds()+" "+peticionLocal.getNom_slo_no());
			
			tr800e.setXA_CUSTOMER_VIP("0");//Aplica para ST pero es un campo obligatorio
			setearDiireccion(peticionLocal);
			setTipoAccionTOA(peticionLocal, act);
			
//seteo Campos Desde IDActuacion @dcardenas---------------------------------------------------------------------------------------------------
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat formatterRegistro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatterDate= new SimpleDateFormat("yyyy-MM-dd");
			Timestamp fechaReagendamiento;//es enviado como Parametro de la funcion creacionActuacionAgendaSC
			String tipoOC;//tambien es enviado como parametro en la funcion creacionActuacionAgendaSC
			Date dateAhora=new Date();			
			//validar por que se hace esta validacion
			Timestamp timestampAhora=new Timestamp(dateAhora.getTime());
			
				//se agrega el formato a la actuacion que se pide AT
				tr800e.setId_actuacion("AT"+idPeticion.toString()+"-"+formatter.format(dateAhora));//@dcardena
				//}
				Agenda_scLocal agenda_SCLocal=agenda_SCLocalHome.create(tr800e.getId_actuacion());
				agenda_SCLocal.setPeticion(peticionLocal);
				agenda_SCLocal.setEstado(new Integer(1));
				agenda_SCLocal.setPeti_numero(idPeticion);
				agenda_SCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
				tr800e.setXA_ORDER_ATIS(peticion_atisKey.cod_pet_cd.toString());//@dcardena
				tr800e.setXA_ORDER_NUMBER(idPeticion.toString());//@dcardena
				tr800e.setXA_CREATION_DATE(formatterDate.format(dateAhora));//@dcardena
				//este tag se encuentra vacio tr701EDateData.setBreakdownCommitmentDate("");
				tr800e.setDate(formatterDate.format(peticionLocal.getPeti_fecha_ingreso()));//"dcardena
				
//logica para la generacion de el arregloe XA_DAT_OP_DATO_CITA el cual contiene arreglos de propriedades junto con su key y value
				ArrayList datosOpcionalesArray = new ArrayList();
				otherElement.setKey("");
				otherElement.setValue("");
				datosOpcionalesArray.add(otherElement.getKey());
				datosOpcionalesArray.add(otherElement.getValue());
				otherType.setPropiedad(datosOpcionalesArray);
				tr800e.setXA_DAT_OP_DATO_CITA(otherType);
//fin arreglo XA_DAT_OP_DATO_CITA
				
				tr800e.setXA_PROJECT_CODE("");//@dcardena
				
//mapeo campo XA_REINJECTED se debe contar cuantas veces ha caido a PGC-------------------------------------------------------
				tr800e.setXA_REINJECTED(Integer.toString(cantidadReintentosPGC()));
//----------------------------------------------------------------------------------------------------------------------------
//				logica para la generacion de el arregloe XA_DAT_OP_DATO_INST el cual contiene arreglos de propriedades junto con su key y value
				datosOpcionalesArray = new ArrayList();
				otherElement.setKey("");
				otherElement.setValue("");
				datosOpcionalesArray.add(otherElement.getKey());
				datosOpcionalesArray.add(otherElement.getValue());
				otherType.setPropiedad(datosOpcionalesArray);
				tr800e.setXA_DAT_OP_DATO_INST(otherType);
//fin arreglo XA_DAT_OP_DATO_INST
				
//				tr800e.setXA_MASSIVE_TYPE("");//Este tag se encuentra vacio tr701EMassiveBreakdowns.setBreakdownMassiveType("");
				
//logica para la generacion de el arregloe XA_DAT_OP_DATO_AVER el cual contiene arreglos de propriedades junto con su key y value
				datosOpcionalesArray = new ArrayList();
				otherElement.setKey("");
				otherElement.setValue("");
				datosOpcionalesArray.add(otherElement.getKey());
				datosOpcionalesArray.add(otherElement.getValue());
				otherType.setPropiedad(datosOpcionalesArray);
				tr800e.setXA_DAT_OP_DATO_AVER(otherType);
//fin arreglo XA_DAT_OP_DATO_AVER		
				
//Seeteo del tag XA_CENTRAL 
				try{
					rlb = null;
					recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);		
					rlb = recursos_linea_basicaLocalHome.findByPeticion(idPeticion);
					Recursos_baKey Recursos_baKey=new Recursos_baKey();
				}
			    catch (FinderException e) {
					log.debug("No encontró recursos LB en la petición en alta actuación de TOA: "+e.toString());
				}catch (NamingException e) {
					// TODO Bloque catch generado automáticamente
					log.debug("No se pudo levantar el localhome de RECURSOS_LINEA_BASICA en alta actuación de TOA: "+e.toString());
				}
			    
			    if(rlb!=null){
					if(rlb.getCod_central()!=null){
						tr800e.setXA_CENTRAL(""+rlb.getCod_central());
					}else{
						tr800e.setXA_CENTRAL("");
					}
//fin seteo tag XA_CENTRAL
//Seeteo del tag XA_BOX_TYPE 			   
				    if(rlb.getTipo_caja_dedicado()!=null){
				    	tr800e.setXA_BOX_TYPE(""+rlb.getTipo_caja_dedicado());
				    }else{
				    	tr800e.setXA_BOX_TYPE("");
				    }
//fin seteo tag XA_BOX_TYPE 
//seteo del arreglo XA_TELEPHONE_DATA-------------------------------------------------------------------------------------------------
			    
				xaTelephoneData = setXA_TELEPHONE_DATA(rlb);
			    }else{
			    	tr800e.setXA_CENTRAL("");
			    	tr800e.setXA_BOX_TYPE("");
			    	xaTelephoneData.setCentral("");
			    	xaTelephoneData.setTelefono("");
			    	xaTelephoneData.setLen("");
			    	xaTelephoneData.setPosicion_Horizontal("");
			    	xaTelephoneData.setDistribuidor("");
			    	xaTelephoneData.setDescripcion_Distribuidor("");
			    	xaTelephoneData.setDireccion_Distribuidor("");
			    	xaTelephoneData.setListon("");
			    	xaTelephoneData.setPar_liston("");
			    	xaTelephoneData.setCable("");
			    	xaTelephoneData.setPar_Cable("");
			    	xaTelephoneData.setArmario("");
			    	xaTelephoneData.setDireccion_Armario("");
			    	xaTelephoneData.setCaja("");
			    	xaTelephoneData.setPar_caja("");
			    	xaTelephoneData.setDireccion_caja("");
			    	xaTelephoneData.setTipo_caja("");
			    }
			    //			  Se extraen datos de ADSL
			    recursosBADelegate = new RecursosBADelegate();
			    InformacionAdslDTO adsl=recursosBADelegate.obtenerDatosAdsl(idPeticion);
//logica para la generacion de el arregloe setOtros_Telephon_Data el cual contiene arreglos de propriedades junto con su key y value
				datosOpcionalesArray = new ArrayList();
				otherElement.setKey("");
				otherElement.setValue("");
				datosOpcionalesArray.add(otherElement.getKey());
				datosOpcionalesArray.add(otherElement.getValue());
				otherType.setPropiedad(datosOpcionalesArray);
				xaTelephoneData.setOtros_Telephon_Data(otherType);
				
				tr800e.setXA_TELEPHONE_DATA(xaTelephoneData);
//fin arreglo setOtros_Telephon_Data
//Fin seteo Arreglo XA_TELEPHONE_DATA----------------------------------------------------------------------------------------------------
				
//INICIO ARREGLO XA_BROADBAND_DATA
				/*Inclusión del envío de la velocidad cuando el PS la tiene definida en la tabla PRODUCTO_SERVICIO*/
				String velocidadPlanYPS = recursosBADelegate.extraerVelocidadPlanYPSPrioridadAlta(idPeticion);
				String velocidadPlan="";
				String psPlan="";
				if(velocidadPlanYPS!=null && !velocidadPlanYPS.equals("")){
					String[] velYPS = velocidadPlanYPS.split("#");
					velocidadPlan = velYPS[0];
					psPlan = velYPS[1];
				}
				if(velocidadPlan != null && !velocidadPlan.equals("")){
					xaBroadBandData.setVelocidad(velocidadPlan);
				}else{
					xaBroadBandData.setVelocidad("");
				}
				/*FIN Inclusión del envío de la velocidad cuando el PS la tiene definida en la tabla PRODUCTO_SERVICIO*/

				xaBroadBandData.setTipo_IP("");//tr701ETechnicalData.setIpType("");
				if (adsl != null){
					if(adsl.getPuerto()!=null){
						xaBroadBandData.setPuerto(adsl.getPuerto());
					}else{
						xaBroadBandData.setPuerto("");
					}
					if(adsl.getPost()!=null){
						xaBroadBandData.setPOTs(adsl.getPost());
					}else{
						xaBroadBandData.setPOTs("");
					}
					if(adsl.getAdsl()!=null){
						String adslAux[] = adsl.getAdsl().split("-");
						xaBroadBandData.setADSL(adsl.getAdsl());
						xaBroadBandData.setRACK(adslAux[0]);
					}else{
						xaBroadBandData.setADSL("");
					}
					if(adsl.getDirecIpDslam()!=null){
						xaBroadBandData.setDireccion_IP_DISLAM(adsl.getDirecIpDslam());
					}else{
						xaBroadBandData.setDireccion_IP_DISLAM("");
					}
					if(adsl.getDirecIpWan()!=null){
						xaBroadBandData.setDireccion_IP_WAN(adsl.getDirecIpWan());
					}else{
						xaBroadBandData.setDireccion_IP_WAN("");
					}
					if(adsl.getDirecIpLan()!=null){
						xaBroadBandData.setDireccion_IP_LAN(adsl.getDirecIpLan());
					}else{
						xaBroadBandData.setDireccion_IP_LAN("");
					}
					if(adsl.getMascaraLan()!=null){
						xaBroadBandData.setMascara_LAN(adsl.getMascaraLan());
					}else{
						xaBroadBandData.setMascara_LAN("");
					}
					if(adsl.getFrame()!=null){
						xaBroadBandData.setFrame(adsl.getFrame());
						xaBroadBandData.setSUBRACK(adsl.getFrame());
					}else{
						xaBroadBandData.setFrame("");
					}
					if(adsl.getSlot()!=null){
						xaBroadBandData.setTarjeta_Slot(adsl.getSlot());
					}else{
						xaBroadBandData.setTarjeta_Slot("");
					}
					if(adsl.getVpiVciCliente()!=null){
						xaBroadBandData.setVPI_VCI_Cliente(adsl.getVpiVciCliente());
					}else{
						xaBroadBandData.setVPI_VCI_Cliente("");
					}
					if(adsl.getVpiVciRed()!=null){
						xaBroadBandData.setVPI_VCI_Red(adsl.getVpiVciRed());
					}else{
						xaBroadBandData.setVPI_VCI_Red("");
					}
					if(adsl.getUsuarioAcc()!=null){
						xaBroadBandData.setUsuario_Acceso(adsl.getUsuarioAcc());
					}else{
						xaBroadBandData.setUsuario_Acceso("");
					}
				}
//logica para la generacion de el arregloe setOtros_Datos_BA el cual contiene arreglos de propriedades junto con su key y value
				datosOpcionalesArray = new ArrayList();
				otherElement.setKey("");
				otherElement.setValue("");
				datosOpcionalesArray.add(otherElement.getKey());
				datosOpcionalesArray.add(otherElement.getValue());
				otherType.setPropiedad(datosOpcionalesArray);
				xaBroadBandData.setOtros_Datos_BA(otherType);
				tr800e.setXA_BROADBAND_DATA(xaBroadBandData);
//fin arreglo setOtros_Datos_BA			
//FIN ARREGLO XA_BROADBAND_DATA
//seteo TAG XA_ID_PC_TV				
				datosOpcionalesArray = new ArrayList();
//				datosOpcionalesArray.add("0");
				//tr800e.setXA_ID_PCTV(datosOpcionalesArray);
				if(peticionLocal.getNum_ide_nu_tv()!=null && peticionLocal.getNum_ide_nu_tv().length()>0){
					datosOpcionalesArray.add(peticionLocal.getNum_ide_nu_tv());
					tr800e.setXA_ID_PCTV(datosOpcionalesArray);
				}
//fin SETEO TAG XA_ID_PC_TV
//inicio mapeo arreglo XA_TV_DATA
				//seteo TAG TEMATICO
				xaTvData.setTematicos(listaTematicos(peticionLocal));
				//FIN SETEO ag tematico
				//SETEO TAG DECOS_TYPE
				xaTvData.setTipo_Decos(listaTematicos(peticionLocal));
				//FIN SETEO DECOS_TYPE
				//SETEO TAG CANTIDAD_DECOS
				xaTvData.setCantidad_Decos(cantidadDeDecos(peticionLocal));
				//FIN SETEO TAG CANTIDAD_DECOS
				
				//logica para la generacion de el arregloe setOtros_Datos_BA el cual contiene arreglos de propriedades junto con su key y value
				otrosDatosArray = new ArrayList();
				otherElement.setKey("");
				otherElement.setValue("");
				otrosDatosArray.add(otherElement.getKey());
				otrosDatosArray.add(otherElement.getValue());
				otherType.setPropiedad(otrosDatosArray);
				xaTvData.setOtros_Datos_TV(otherType);
				tr800e.setXA_BROADBAND_DATA(xaBroadBandData);
				//fin arreglo setOtros_Datos_BA	
				
				datosOpcionalesArray = new ArrayList();
				datosOpcionalesArray.add(xaTvData);
				tr800e.setXA_TV_DATA(datosOpcionalesArray);
//XA_TV_DATA
				tr800e.setXA_NOTES(notaCliente(peticionLocal));
				
				tr800e.setXA_EQUIPMENT(this.setEquipament(peticionLocal,act));
//Areeglo equipos_cliente		
				tr800e.setEQUIPOS_CLIENTE(equiposCliente(peticionLocal));
//fin Arreglo Equipos_Cliente
//Fin seteo @dcardena----------------------------------------------------------------------------------------------------
			
				finalizarProceso(tr800e, act);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("A ocurrido un error al consultar la peticion" +idPeticion+ " " +e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("A ocurrido un error al levantar el home la peticion" +idPeticion+ " " +e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("A ocurrido un error al levantar el home la peticion" +idPeticion+ " " +e);
		} 
	}

	/**
	 * @param rlb2
	 * @return
	 */
	private XATELEPHONEDATA setXA_TELEPHONE_DATA(Recursos_linea_basicaLocal recursos_linea_basicaLocal) {
		// TODO Apéndice de método generado automáticamente
		
		log.debug("Entro a setear los valores de XATELEPHONEDATA");
		xaTelephoneData = new XATELEPHONEDATA();
		if(recursos_linea_basicaLocal.getCod_central()!=null){
	    	xaTelephoneData.setCentral(""+recursos_linea_basicaLocal.getCod_central());
	    }else{
	    	xaTelephoneData.setCentral("");
	    }
	    if (recursos_linea_basicaLocal.getTelefono_asg()!= null){
	    	xaTelephoneData.setTelefono(recursos_linea_basicaLocal.getTelefono_asg().toString());
	    }else{
	    	xaTelephoneData.setTelefono("");
	    }
	    if (recursos_linea_basicaLocal.getLen()!=null){
	    	 xaTelephoneData.setLen("<![CDATA["+recursos_linea_basicaLocal.getLen()+"]]>");
	    }else {
	    	 xaTelephoneData.setLen("");
	    }
		if (recursos_linea_basicaLocal.getPosicion_horizontal_asg()!=null){
			 xaTelephoneData.setPosicion_Horizontal(recursos_linea_basicaLocal.getPosicion_horizontal_asg());
		}else{
			xaTelephoneData.setPosicion_Horizontal("");
		}
		if (recursos_linea_basicaLocal.getDist_prim_asg()!=null){
			xaTelephoneData.setDistribuidor(recursos_linea_basicaLocal.getDist_prim_asg().toString());
			
		}else{
			xaTelephoneData.setDistribuidor("");
		}
		if (recursos_linea_basicaLocal.getDesc_dist_prim_asg()!=null){
			 xaTelephoneData.setDescripcion_Distribuidor(recursos_linea_basicaLocal.getDesc_dist_prim_asg());
		}else{
			xaTelephoneData.setDescripcion_Distribuidor("");
		}
		
		if (recursos_linea_basicaLocal.getDir_distribuidor()!=null){
			xaTelephoneData.setDireccion_Distribuidor(recursos_linea_basicaLocal.getDir_distribuidor());
		}else{
			xaTelephoneData.setDireccion_Distribuidor("");
		}
		if (recursos_linea_basicaLocal.getListon_asg()!=null){
			xaTelephoneData.setListon(recursos_linea_basicaLocal.getListon_asg());
		}else{
			xaTelephoneData.setListon("");
		}
		if (recursos_linea_basicaLocal.getPar_liston_asg()!=null){
			xaTelephoneData.setPar_liston(recursos_linea_basicaLocal.getPar_liston_asg().toString());
		}else{
			xaTelephoneData.setPar_liston("");
		}
		if(recursos_linea_basicaLocal.getCable()!=null){
			 xaTelephoneData.setCable(recursos_linea_basicaLocal.getCable());
		}else{
			 xaTelephoneData.setCable("");
		}
		if(recursos_linea_basicaLocal.getPar_cable()!=null){
			 xaTelephoneData.setPar_Cable(""+recursos_linea_basicaLocal.getPar_cable());
		}else{
			 xaTelephoneData.setPar_Cable("");
		}
		if(recursos_linea_basicaLocal.getArmario_asg()!=null){
			xaTelephoneData.setArmario(recursos_linea_basicaLocal.getArmario_asg());
		}else{
			xaTelephoneData.setArmario("");
		}
		if(recursos_linea_basicaLocal.getDir_armario()!=null){
			xaTelephoneData.setDireccion_Armario(recursos_linea_basicaLocal.getDir_armario());
		}else{
			xaTelephoneData.setDireccion_Armario("");
		}
		if(recursos_linea_basicaLocal.getCaja_asg()!=null){
			xaTelephoneData.setCaja(recursos_linea_basicaLocal.getCaja_asg());
		}else{
			xaTelephoneData.setCaja("");
		}
		if(recursos_linea_basicaLocal.getPar_caja_asg()!=null){
			xaTelephoneData.setPar_caja(""+recursos_linea_basicaLocal.getPar_caja_asg());
		}else{
			xaTelephoneData.setPar_caja("");
		}
		if(recursos_linea_basicaLocal.getDir_caja()!=null){
			 xaTelephoneData.setDireccion_caja(recursos_linea_basicaLocal.getDir_caja());
		}else{
			 xaTelephoneData.setDireccion_caja("");
		}
		if(recursos_linea_basicaLocal.getTipo_caja()!=null){
			xaTelephoneData.setTipo_caja(""+recursos_linea_basicaLocal.getTipo_caja());
		}else{
			xaTelephoneData.setTipo_caja("");
		}
 
		xaTelephoneData.setDistancia_caja("");
		xaTelephoneData.setLatitud("");
		xaTelephoneData.setLongitud("");
		
		try {
			recursosBADelegate = new RecursosBADelegate();
			InformacionAdslDTO adsl=recursosBADelegate.obtenerDatosAdsl(idPeticion);
				if (adsl == null){
					adsl = recursosBADelegate.obtenerDatosActualAdsl(idPeticion);
				}else if (adsl != null && adsl.getDirecIpDslam()== null){
					adsl = recursosBADelegate.obtenerDatosActualAdsl(idPeticion);
				}else if (adsl != null && adsl.getDirecIpDslam() != null && adsl.getDirecIpDslam().length() <= 0){
					adsl = recursosBADelegate.obtenerDatosActualAdsl(idPeticion);
				}	
			    //ADSL
				if (adsl != null){
					if(adsl.getCodZonaAtend()!=null){
				    	xaTelephoneData.setZona_cobertura(adsl.getCodZonaAtend());
				    }else{
				    		xaTelephoneData.setZona_cobertura("");
				    }
				}
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

		return xaTelephoneData;
	}
	private XABROADBANDDATA setXA_BROADBAND_DATA(Long idPeticion)
			throws ATiempoAppEx {
		recursosBADelegate = new RecursosBADelegate();
		InformacionAdslDTO adsl = recursosBADelegate.obtenerDatosActualAdsl(idPeticion);
		String velocidadPlanYPS = recursosBADelegate.extraerVelocidadPlanYPSPrioridadAlta(idPeticion);
		xaBroadBandData = new XABROADBANDDATA();
		if (adsl != null) {
			log.debug("Entro a setear los valores de XABROADBANDDATA");

			String velocidadPlan = "";
			String psPlan = "";
			if (velocidadPlanYPS != null && !velocidadPlanYPS.equals("")) {
				String[] velYPS = velocidadPlanYPS.split("#");
				velocidadPlan = velYPS[0];
				psPlan = velYPS[1];
			}
			if (velocidadPlan != null && !velocidadPlan.equals("")) {
				xaBroadBandData.setVelocidad(velocidadPlan);
			} else {
				xaBroadBandData.setVelocidad("");
			}

			if (adsl.getAdsl() != null) {
				xaBroadBandData.setADSL(adsl.getAdsl());
			} else {
				xaBroadBandData.setADSL("");
			}
			if (adsl.getPuerto() != null) {
				xaBroadBandData.setPuerto(adsl.getPuerto());
			} else {
				xaBroadBandData.setPuerto("");
			}
			if (adsl.getPost() != null) {
				xaBroadBandData.setPOTs(adsl.getPost());
			} else {
				xaBroadBandData.setPOTs("");
			}
			if (adsl.getAdsl() != null) {
				String adslAux[] = adsl.getAdsl().split("-");
				xaBroadBandData.setADSL(adsl.getAdsl());
				xaBroadBandData.setRACK(adslAux[0]);
			} else {
				xaBroadBandData.setADSL("");
			}
			if (adsl.getDirecIpDslam() != null) {
				xaBroadBandData.setDireccion_IP_DISLAM(adsl.getDirecIpDslam());
			} else {
				xaBroadBandData.setDireccion_IP_DISLAM("");
			}
			if (adsl.getDirecIpWan() != null) {
				xaBroadBandData.setDireccion_IP_WAN(adsl.getDirecIpWan());
			} else {
				xaBroadBandData.setDireccion_IP_WAN("");
			}
			if (adsl.getDirecIpLan() != null) {
				xaBroadBandData.setDireccion_IP_LAN(adsl.getDirecIpLan());
			} else {
				xaBroadBandData.setDireccion_IP_LAN("");
			}
			if (adsl.getMascaraLan() != null) {
				xaBroadBandData.setMascara_LAN(adsl.getMascaraLan());
			} else {
				xaBroadBandData.setMascara_LAN("");
			}
			if (adsl.getFrame() != null) {
				xaBroadBandData.setFrame(adsl.getFrame());
				xaBroadBandData.setSUBRACK(adsl.getFrame());
			} else {
				xaBroadBandData.setFrame("");
			}
			if (adsl.getSlot() != null) {
				xaBroadBandData.setTarjeta_Slot(adsl.getSlot());
			} else {
				xaBroadBandData.setTarjeta_Slot("");
			}
			if (adsl.getVpiVciCliente() != null) {
				xaBroadBandData.setVPI_VCI_Cliente(adsl.getVpiVciCliente());
			} else {
				xaBroadBandData.setVPI_VCI_Cliente("");
			}
			if (adsl.getVpiVciRed() != null) {
				xaBroadBandData.setVPI_VCI_Red(adsl.getVpiVciRed());
			} else {
				xaBroadBandData.setVPI_VCI_Red("");
			}
			if (adsl.getUsuarioAcc() != null) {
				xaBroadBandData.setUsuario_Acceso(adsl.getUsuarioAcc());
			} else {
				xaBroadBandData.setUsuario_Acceso("");
			}
		}
		return xaBroadBandData;

	}

	/**
	 * @param peticionLocal2
	 * @return
	 */
	private List setEquipament(PeticionLocal peticionLocal2,ActividadEJBDTO act) {
		try {
			log.debug("funcion setEquipament"+ act.getNumeroPeticion()+" peticion "+ act.getNumeroPeticion());
			// TODO Apéndice de método generado automáticamente
			Collection listaEquipos = new ArrayList();
			Collection pspArray = peticionLocal2.getProducto_servicio_peticion();
			Equipos_TOALocalHome equiposTOAHome  = (Equipos_TOALocalHome) HomeFactory.getHome(Equipos_TOALocalHome.JNDI_NAME);
			//variable que almacena la accion del equipo que se va agregar en el grupo
			String operacionEquipoAValidar="";
			//variable que almacena la accion de cada equipo que se esta validando en el segundo for
			String operacionEquipoValidado="";
			//variable que guarda la cantidad de equipos por cada tipo equipo
			int cantidadEquipos=0;
						
			//se recorre por primera vez cada equipo (papa)
			for(Iterator iter = pspArray.iterator();iter.hasNext();){
				
				Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal) iter.next();
				Operacion_comercialLocal opcoLocal = (Operacion_comercialLocal)pspLocal.getOperacion_comercial();
				Operacion_comercialKey opcoKey = (Operacion_comercialKey) opcoLocal.getPrimaryKey();
				//se recontruye (limpia) la clase pojo para que no se repitan los datos en el arreglo
				xaequipment = new XAEQUIPMENT();
				
				try {
					
					Equipos_TOALocal equiposTOALocal = equiposTOAHome.findByPrimaryKey(new Equipos_TOAKey(pspLocal.getPsId(), opcoKey.opco_id));
					//se valida la accion del equipo papa a agregar en el arreglo
					if(opcoLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja) ||opcoLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd)){
						// se mapea la accion al equipo papa
						operacionEquipoAValidar="D";
					}else{
						// se mapea la accion al equipo papa
						operacionEquipoAValidar="I";
					}
					log.debug("se validara equipo "+equiposTOALocal.getTipo_equipo()+" con la accion "+operacionEquipoAValidar+" peticion"+ act.getNumeroPeticion());
					// se obtiene un segundo arreglo que es el que se va a comparar contra el PAPA y poder hacer conteo de equipos 
					Collection pspArrayHijo = peticionLocal2.getProducto_servicio_peticion();
					
					// se recorre el segundo arreglo para comprara contra el primero ty obtener la cantidad de quipos por cada accion
					for(Iterator iterHijo = pspArrayHijo.iterator();iterHijo.hasNext();){
						
						Producto_servicio_peticionLocal pspLocalHijo = (Producto_servicio_peticionLocal) iterHijo.next();
						Operacion_comercialLocal opcoLocalHijo = (Operacion_comercialLocal)pspLocalHijo.getOperacion_comercial();
						Operacion_comercialKey opcoKeyhijo = (Operacion_comercialKey) opcoLocalHijo.getPrimaryKey();
						
					try{
						// se consulta para obtener informacion del equipo evaluado
						Equipos_TOALocal equiposTOALocalhijo = equiposTOAHome.findByPrimaryKey(new Equipos_TOAKey(pspLocalHijo.getPsId(), opcoKeyhijo.opco_id));
						
						//se obitine la accion del equipo evaluado
						if(opcoLocalHijo.getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja) ||opcoLocalHijo.getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd)){
							operacionEquipoValidado="D";
						}else{
							operacionEquipoValidado="I";
						}
						//si el equipo papa y su accion es igual al equipo hijo y su accion se suma la cantidad de equipos
						log.debug("se validara equipo HIJO "+equiposTOALocal.getTipo_equipo()+" con la accion HIJO"+operacionEquipoValidado+" VS Equipo PAPA "+ 
								equiposTOALocal.getTipo_equipo()+" con la accion PAPA "+operacionEquipoAValidar+" peticion "+ act.getNumeroPeticion());
						
						if(equiposTOALocal.getTipo_equipo().equals(equiposTOALocalhijo.getTipo_equipo())&&operacionEquipoValidado.equals(operacionEquipoAValidar)){
							cantidadEquipos++;	
						}
						log.debug("el equipo "+equiposTOALocal.getTipo_equipo()+" con la accion "+operacionEquipoAValidar+" Existe la cantidad "+cantidadEquipos+" peticion"+ act.getNumeroPeticion());
						
						} catch (FinderException e1) {
							// TODO Bloque catch generado automáticamente
							log.debug("El PS: "+pspLocal.getPsId()+ " con operacion comercial "+ opcoKey.opco_id+ " no corresponde a un equipo TOA");
						}
					}
					//finalizado los conteos se valida si el equipo y su accion ya se valido anterior mente ya que puede que se repita el arrelo.
					log.debug("se valida si el equipo ya esta en el arreglo agrupado "+equiposTOALocal.getTipo_equipo()+" con la accion "+operacionEquipoAValidar+" peticion"+ act.getNumeroPeticion());
					
					if(!seValidoEquipoAccion(listaEquipos,operacionEquipoAValidar,equiposTOALocal.getTipo_equipo())){
						//si el equpo no esta en e arreglo se agrega el equipo con su cantidad de quipos que se hallan evaluado
						log.debug("El equipo no existe se agrega al arreglo agrupado "+equiposTOALocal.getTipo_equipo()+" con la accion "+operacionEquipoAValidar+" cantidad de equipos "+cantidadEquipos+" peticion "+ act.getNumeroPeticion());
						xaequipment.setAccion(operacionEquipoAValidar);
						xaequipment.setFamilia(equiposTOALocal.getFamilia());
						xaequipment.setTipo_Equipo(equiposTOALocal.getTipo_equipo().toString());
						xaequipment.setNumero(Integer.toString(cantidadEquipos));
						listaEquipos.add(xaequipment);
					}
					cantidadEquipos=0;
				} catch (FinderException e1) {
					// TODO Bloque catch generado automáticamente
					log.debug("El PS: "+pspLocal.getPsId()+ " con operacion comercial "+ opcoKey.opco_id+ " no corresponde a un equipo TOA peticion "+ act.getNumeroPeticion());
				}
				
				
				
			}
			
			
		
			return (List) listaEquipos;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			return null;
		}
	}
	
	//funcion que valida que en el arreglo de equipos argregados, el nuevo equipo y accion ya se encuentra en el arreglo para no replicar informacion
	private boolean seValidoEquipoAccion (Collection equiposAgregados ,String accion,String tipoEquipo){
		// se recorren equipos agregados vs el equipo a agregar si no existe se deveulve false para que sea argegado en el arreglo de equipos agrupado
		for(Iterator iter = equiposAgregados.iterator();iter.hasNext();){
			log.debug(" se valida si existe el equiopor en el arreglo de equipos El equipo "+tipoEquipo+" con la accion "+accion);
			XAEQUIPMENT	xaequipmentAgregados = (XAEQUIPMENT) iter.next();
			if(xaequipmentAgregados.getTipo_Equipo().equals(tipoEquipo)){
				if(xaequipmentAgregados.getAccion().equals(accion)){
					log.debug(" el equipo existe en el arreglo de equipos, El equipo "+tipoEquipo+" con la accion "+accion);
					return true;
				}
			}
		}
		
		log.debug("el equipo no existe se debe agregar "+tipoEquipo+" con la accion "+accion);
		return false;
	}
	
	
	private String notaCliente (PeticionLocal peticionLocal) throws ATiempoAppEx, FinderException, NamingException{
		
		
		Collection agrupacionAtisList=peticion_atisLocal.getAgrupacion_atis();
		Collection psPeticion=peticionLocal.getProducto_servicio_peticion();
		int cantDecosInst = 0;
		int cantDecosDesins = 0;
		int cantDecosDesinsSTD = 0;
		int cantDecosDesinsHD = 0;
		int cantDecosDesinsPVR = 0;
		
		//Estrato
		String estrato = "";
		if(agrupacionAtisList!=null){
			for(Iterator agrupacionAtisListIt=agrupacionAtisList.iterator();agrupacionAtisListIt.hasNext();){
				Agrupacion_atisLocal agrupacion_atisLocal = (Agrupacion_atisLocal) agrupacionAtisListIt.next();
				if(agrupacion_atisLocal.getNom_tip_uso_no()!=null&&!agrupacion_atisLocal.getNom_tip_uso_no().equals("")){
					estrato = agrupacion_atisLocal.getNom_tip_uso_no();
					break;
				}
			}
		}
		//fin estrato

		peticionesDelegate = new PeticionesDelegate();
		ArrayList listaSubpeticiones=peticionesDelegate.obtenerSubpeticionesDesdePeticion(idPeticion);
		String infoContactYMedia="";
		for(Iterator listaSubpeticionesIt=listaSubpeticiones.iterator();listaSubpeticionesIt.hasNext();){
			Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal)listaSubpeticionesIt.next();
			if(subpeticion_atisLocal!=null){  
				if(subpeticion_atisLocal.getObs_sub_ds()!=null&&!subpeticion_atisLocal.getObs_sub_ds().equals("")){
					infoContactYMedia=subpeticion_atisLocal.getObs_sub_ds();
				}
				
			}
		}
		String tipoIncidencia = null;
		for(Iterator psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
			Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
			tipoIncidencia = recursosBADelegate.obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (ComunInterfaces.CARACINCIDENCIA));
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
		notaDescripcion = notaDescripcion+ComunInterfaces.SEG_TEL_SOLICITANTE+peticionLocal.getTel_cot_ds()+";";
		notaDescripcion = notaDescripcion+ComunInterfaces.DATOS_AGENDAMIENTO+infoContactYMedia+";";
		notaDescripcion = notaDescripcion+ComunInterfaces.NOMBRE_CLIENTE+nombreCliente+";";
		notaDescripcion = notaDescripcion+ComunInterfaces.TEL_CLIENTE+peticionLocal.getTel_cot_ds_1()+";";
		notaDescripcion = notaDescripcion+ComunInterfaces.SEG_TEL_CLIENTE+peticionLocal.getTel_cot_ds_2()+";";		notaDescripcion = notaDescripcion+ComunInterfaces.OBSERVACIONES+peticionLocal.getObs_pet_ds()+";";
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
	
//	REQ migracion deco dcardena 15/01/2015
	Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
	Producto_servicio_peticionLocalHome productoServicioPeticionLocalHome = (Producto_servicio_peticionLocalHome)HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
	Collection psActividadCollection = peticionFlujoLocalHome.findByActividad(idPeticion,new Integer(ID_ACTIVIDAD_INSTALACION),new Integer(ID_ACTIVIDAD_DESINSTALACION));
	boolean hayAltBajMigrDEco=false;	
	hayAltBajMigrDEco=validaBajAltaMigrDeco(peticionLocal.getProducto_servicio_peticion());	
	
	for (Iterator psActividadIterator=psActividadCollection.iterator(); psActividadIterator.hasNext();){
		
		Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal) psActividadIterator.next();
		boolean psConQuiebre = false;
		int famOpLocal = 0;
		String oc;
		Operacion_comercialKey opcoKey = (Operacion_comercialKey)peticionFlujoLocal.getFk_opco_2_pefl().getPrimaryKey();
		Collection productosCollection = productoServicioPeticionLocalHome.findByPetiNumeroPsYOpCo(idPeticion, peticionFlujoLocal.getPrse_id(), opcoKey.opco_id);
		for (Iterator productosIterator = productosCollection.iterator(); productosIterator.hasNext(); ){
			Producto_servicio_peticionLocal productoLocal =  (Producto_servicio_peticionLocal)productosIterator.next();	
			
				oc=opcoKey.opco_id.toString();
				
				if(hayAltBajMigrDEco){
					String accion = accionDecoAdicional(oc, famOpLocal);
					
					if(accion != null && famOpLocal==ComunInterfaces.familiaDecoHDTV&&accion.equals("D")){
						cantDecosDesinsHD++;
					}
					
					if(accion !=null && famOpLocal==ComunInterfaces.familiaDecoPVRTV&&accion.equals("D")){
						
						cantDecosDesinsPVR++;
					}
					
					if(accion !=null && famOpLocal==ComunInterfaces.familiaDecoTV&&accion.equals("D"))
					{
						cantDecosDesinsSTD++;
					}
				}
				//fin REQ DECO
			}
		}		
	
//	REQ migracion deco dcardena 20/01/2015
	if(cantDecosDesinsHD>0){
		boolean esDecoDesconciliado =cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoHDTV,cantDecosDesinsHD);	
		if (esDecoDesconciliado)
		{	
			notaDescripcion=notaDescripcion+";Cantidad DECOs HC HD "+cantDecosInst+" Cantidad DECOs HD a Des-instalar "+cantDecosDesins+" favor gestionar regularizacion.";
		
		}
	}
	 if(cantDecosDesinsPVR>0){
		boolean esDecoDesconciliado =cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoPVRTV,cantDecosDesinsPVR);	
		if (esDecoDesconciliado)
		{
			notaDescripcion=notaDescripcion+";Cantidad DECOs HC PVR "+cantDecosInst+" Cantidad DECOs PVR a Des-instalar "+cantDecosDesins+" favor gestionar regularizacion.";				
		}
	}
	if(cantDecosDesinsSTD>0){
		boolean esDecoDesconciliado =cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoTV,cantDecosDesinsSTD);	
		if (esDecoDesconciliado)
		{
			notaDescripcion=notaDescripcion+";Cantidad DECOs HC STD "+cantDecosInst+" Cantidad DECOs STD a Des-instalar "+cantDecosDesins+" favor gestionar regularizacion.";
		}
		
	}	
	cantDecosInst = 0;
	cantDecosDesins = 0;
	cantDecosDesinsSTD=0;
	cantDecosDesinsPVR=0;
	cantDecosDesinsHD=0;
		//fin req

	notaDescripcion = notaDescripcion+ComunInterfaces.OBSERVACION_BITACORA+observacionActividad;
	return notaDescripcion;
	}

	
	//REQ migracion deco dcardena 15/01/2015
	private String accionDecoAdicional(String opc,int famOpLocal) throws FinderException, NamingException{
		
		Operacion_comercialLocalHome  operacion_comercialLocalHome = (Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
		Operacion_comercialKey Operacion_comercialKey = new Operacion_comercialKey(new Long (opc));
		Operacion_comercialLocal operacion_comercialLocal = operacion_comercialLocalHome.findByPrimaryKey(Operacion_comercialKey);
		String accion = "";
		if((famOpLocal==ComunInterfaces.familiaDecoHDTV)|| (famOpLocal==ComunInterfaces.familiaDecoPVRTV)||(famOpLocal==ComunInterfaces.familiaDecoTV))
		{
			if(operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)||operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)){
		
			accion="I";
			
			}else if(operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd)|| operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja)){ 
			accion="D";
			
			
			}else{
			accion=null;
			}
		}else{
			accion=null;
		}
		return accion;
	}
	
	
	
	private boolean cantidadDecosHC (Long petiNum,int famOpLocal,int cantDesisntalar) throws NamingException, FinderException{
		
		Deco_tarjetaLocalHome  deco_tarjetaLocalHome = (Deco_tarjetaLocalHome)HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
		
		Collection decosInstalados = deco_tarjetaLocalHome.findByPeticion(petiNum);
		String tipodeco="";
		int cantidadDecos=0;
		if(famOpLocal==ComunInterfaces.familiaDecoHDTV){
			tipodeco="HD";
		}
		else if(famOpLocal==ComunInterfaces.familiaDecoPVRTV){
			
			tipodeco="PVR";
		}else if(famOpLocal==ComunInterfaces.familiaDecoTV)
		{
			tipodeco="STD";
		}
		
		if(decosInstalados != null && decosInstalados.size()>0){
			for (Iterator decosInstaladosIter = decosInstalados.iterator(); decosInstaladosIter.hasNext();){
				Deco_tarjetaLocal deco_tarjetaLocal = (Deco_tarjetaLocal)decosInstaladosIter.next();
				
				if (deco_tarjetaLocal.getDeco_reference().equals(tipodeco)){
					cantidadDecos++;
				}
			}
		}
		
		log.debug("El deco de tipo "+tipodeco + " tiene la siguiente cantidad de decos instalados "+cantidadDecos+ ", se esta mandando a desisntalar la siguiente cantidad de decos "+cantDesisntalar);
		if(cantDesisntalar>cantidadDecos){	
			log.debug("el deco de tipo "+tipodeco+" esta descompensado se envia nota");
			cantDecosInst = cantidadDecos;
			cantDecosDesins=cantDesisntalar;
			return true;
		}else{
			log.debug("el deco de tipo "+tipodeco+" no esta descompensado");
		return false;

		}
	}
	
	private boolean validaBajAltaMigrDeco(Collection productosCollection){
		log.debug("Entra a validar si hay alta con migracion de deco o baja con migracion de deco");
		boolean hayAlta= false;
		boolean hayBaja= false;
		boolean hayAltaMigracion = false;
		boolean hayBajaMigracion = false;
		int contador=0;
		log.debug("hay "+productosCollection.size()+" Ps");
		
		for (Iterator productosIterator = productosCollection.iterator(); productosIterator.hasNext(); ){
			Producto_servicio_peticionLocal productoLocal =  (Producto_servicio_peticionLocal)productosIterator.next();
			if(productoLocal.getOperacion_comercial().getOpco_tras()!= null && 
					productoLocal.getOperacion_comercial().getOpco_tras().equals(ComunInterfaces.opCoTras_Traslado)){
				log.debug("Contiene operacion de Traslado no se realiza validacion");
				return false;
			}else{
				if(productoLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)){
					log.debug("Hay alta Deco");
					hayAlta=true;
						
				}else if(productoLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja)){ 
					log.debug("Hay Baja Deco");
					hayBaja=true;

				}else if (productoLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd)){
					log.debug("Hay BajaMigracion Deco");
					hayBajaMigracion=true;
					
				}else if(productoLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)){
					log.debug("Hay AltaMigracion Deco");
					hayAltaMigracion=true;
				}
					contador++;		
			}
		}	
		
		if(contador==productosCollection.size()&&((hayAlta&&hayAltaMigracion&&hayBajaMigracion)||(hayBaja&&hayAltaMigracion&&hayBajaMigracion))){
			log.debug("Hay entre los PS Alta con migracion de deco o Baja con migracion de deco");
			return true;
			
		}else{
			log.debug("No hay entre los PS Alta con migracion de deco o Baja con migracion de deco");
			return false;
		}
		
	}
	//FIN REQ Migracion Deco 

	private ArrayList equiposCliente(PeticionLocal peticionLocal) throws NamingException{
		
		Collection decosTarjetaPeticionList = peticionLocal.getDeco_tarjeta();
		Collection listaEquipos = new ArrayList();
		ElementoLocal elementoLocal =null;
		Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
		Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
		Deco_Tarjeta_Info_SapLocal infoSAPCard = null;
		Deco_Tarjeta_Info_SapKey keyInfoSAPCard = null;
		Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
		
		for (Iterator decosTarjetasPeticionIter = decosTarjetaPeticionList.iterator(); decosTarjetasPeticionIter.hasNext();){
			Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decosTarjetasPeticionIter.next();
			Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey();
			//se recontruye (limpia) la clase pojo para que no se repitan los datos en el arreglo
			equiposCliente = new EQUIPOSCLIENTE();
			//
			if (decoTarjetaLocal.getSerial_deco() != null && decoTarjetaLocal.getSerial_deco().length() > 0 && !decoTarjetaLocal.getSerial_deco().equals("0")){

				equiposCliente.setInvsn(decoTarjetaLocal.getSerial_deco());
				equiposCliente.setXI_BRAND(decoTarjetaLocal.getDeco_marca());
				
				if (desHCDecoSTD.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
					equiposCliente.setInv_type(ComunInterfaces.DECODTHSTD);
				}else if (desHCDecoPVR.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
					equiposCliente.setInv_type(ComunInterfaces.DECODTHPVR);
				}else if (desHCDecoHDTV.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
					equiposCliente.setInv_type(ComunInterfaces.DECOHD);
				}
				if (decoTarjetaLocal.getDeco_reference()!=null && decoTarjetaLocal.getDeco_reference().length()>0){
					equiposCliente.setXI_MODEL(decoTarjetaLocal.getDeco_reference());
				}else{
					equiposCliente.setXI_MODEL("");
				}
				//---------------------------------------------------
				ArrayList datosOpcionalesArray = new ArrayList();
				datosOpcionalesArray.add(decoTarjetaKey.id_tarjeta.toString());
				equiposCliente.setXI_CARD_SERIAL_NUMBER(datosOpcionalesArray);
				//--------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				datosOpcionalesArray.add(decoTarjetaKey.id_deco.toString());
				equiposCliente.setXI_CAS_ID(datosOpcionalesArray);
				//--------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				datosOpcionalesArray.add("");
				equiposCliente.setXI_MATERIAL_CODE(datosOpcionalesArray);
				//---------------------------------------------------
				
				// Datos del Deco
				try{			
					keyInfoSAPTmp = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_deco, idPeticion);
					infoSAPTmp = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPTmp);
			
					//------------------------------------------------------
					if(infoSAPTmp.getCentro_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPTmp.getCentro_sap());
						equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					}
					//------------------------------------------------------				
					//------------------------------------------------------
					if(infoSAPTmp.getCod_lote_sap() != null){
//						ArrayList datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPTmp.getCod_lote_sap());
						equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
					}else{
//						ArrayList datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
					}
					//------------------------------------------------------
					//------------------------------------------------------
					if(infoSAPTmp.getElement_pep_sap() != null){
//						ArrayList datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPTmp.getElement_pep_sap());
						equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
					}else{
//						ArrayList datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
					}
					//------------------------------------------------------
					//------------------------------------------------------
					if(infoSAPTmp.getFlag_mat_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPTmp.getFlag_mat_sap());
						equiposCliente.setXI_SAP(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP(datosOpcionalesArray);
					}
					//------------------------------------------------------

					
				} catch (FinderException e) {
					log.debug("No se encontraron Decos para deco con id: "+decoTarjetaKey.id_deco+". Y id de peticion: "+idPeticion);
					datosOpcionalesArray = new ArrayList();
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					
					datosOpcionalesArray = new ArrayList();
					datosOpcionalesArray.add("");
					equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
					
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
					
					datosOpcionalesArray = new ArrayList();
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
					
					datosOpcionalesArray = new ArrayList();
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP(datosOpcionalesArray);
				} catch (Exception e) {
					log.error("TOAServiciosBean: Se presento Error seteando los datos de SAP para un Deco. "+e);
				}

				// Datos de la tarjeta
				try{
					keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_tarjeta, idPeticion);
					infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
					//------------------------------------------------------
					if(infoSAPCard.getFec_cont_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPCard.getCentro_sap());
						equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					}
					//------------------------------------------------------	
					//------------------------------------------------------
					if(infoSAPCard.getNum_material_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPCard.getNum_material_sap());
						equiposCliente.setXI_SAP_CODE_TJ(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_CODE_TJ(datosOpcionalesArray);
					}
					//------------------------------------------------------	
					//------------------------------------------------------
					if(infoSAPCard.getCentro_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPCard.getCentro_sap());
						equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					}
					//------------------------------------------------------	
					//------------------------------------------------------
					if(infoSAPCard.getAlmacen_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPCard.getAlmacen_sap());
						equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
					}
					//------------------------------------------------------
					//------------------------------------------------------
					if(infoSAPCard.getCod_lote_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPCard.getCod_lote_sap());
						equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
					}
					//------------------------------------------------------
					//------------------------------------------------------
					if(infoSAPCard.getElement_pep_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPCard.getElement_pep_sap());
						equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
					}
					//------------------------------------------------------
					//------------------------------------------------------
					if(infoSAPCard.getFlag_mat_sap() != null){
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(infoSAPCard.getFlag_mat_sap());
						equiposCliente.setXI_SAP(datosOpcionalesArray);
					}else{
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP(datosOpcionalesArray);
					}
					//------------------------------------------------------

				}catch (FinderException e) {
					log.debug("No se encontraron Tarjetas para Card con id: "+decoTarjetaKey.id_tarjeta+". Y id de peticion: "+idPeticion);
					//------------------------------------------------------
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					//------------------------------------------------------	
					//------------------------------------------------------
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
					//------------------------------------------------------	
					//------------------------------------------------------
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
					//------------------------------------------------------
					//------------------------------------------------------
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
					//------------------------------------------------------
					//-----------------------------------------------------
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
					//------------------------------------------------------
					//------------------------------------------------------
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_SAP(datosOpcionalesArray);
					//------------------------------------------------------
				} catch (Exception e) {
					log.error("TOAServiciosBean: Se presento Error seteando los datos de SAP para una Tarjeta. "+e);
				}			
				/*FIN - RQ.8595 - mfmendez*/
				listaEquipos.add(equiposCliente);
			}
			
		}
		
		//Para los elementos de la petición
		Collection elementoPeticionList = peticionLocal.getElemento_peticion();
		for (Iterator elementoPeticionIter = elementoPeticionList.iterator(); elementoPeticionIter.hasNext();){
			Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal)elementoPeticionIter.next();
			equiposCliente = new EQUIPOSCLIENTE();		
			
								
			//se recontruye (limpia) la clase pojo para que no se repitan los datos en el arreglo
			equiposCliente = new EQUIPOSCLIENTE();
			if (elementoPeticionLocal.getSerial() != null && elementoPeticionLocal.getSerial().length() > 0 && !elementoPeticionLocal.getSerial().equals("0")){
				
				equiposCliente.setInvsn(elementoPeticionLocal.getSerial());
				equiposCliente.setXI_BRAND("");
				equiposCliente.setXI_MODEL("");
				equiposCliente.setInv_type("");
				
			//--------------------------------------------------
			ArrayList datosOpcionalesArray = new ArrayList();
			datosOpcionalesArray.add("");
			equiposCliente.setXI_CARD_SERIAL_NUMBER(datosOpcionalesArray);
			//--------------------------------------------------
			datosOpcionalesArray = new ArrayList();
			datosOpcionalesArray.add("");
			equiposCliente.setXI_CAS_ID(datosOpcionalesArray);
			//--------------------------------------------------
			ElementoLocalHome elementoLocalHome;
			try {
				elementoLocalHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
				elementoLocal = elementoLocalHome.findElemento(elementoPeticionLocal.getTipo_elemento().longValue());
			
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("a ocurrido un error al intentar inicializar el ElementoLocalHome " + idPeticion+ " " +e);
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("a ocurrido un error al intentar consultar en la tabla Elemento " + idPeticion+ " " +e);
			}
			
			datosOpcionalesArray = new ArrayList();
			datosOpcionalesArray.add(elementoLocal.getDesc_elemento());
			equiposCliente.setXI_MATERIAL_CODE(datosOpcionalesArray);
			//--------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(elementoPeticionLocal.getClase_mov_sap() != null){
					datosOpcionalesArray.add(elementoPeticionLocal.getClase_mov_sap());
					equiposCliente.setXI_SAP_MOVIMIENTO(datosOpcionalesArray);

				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_MOVIMIENTO(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(elementoPeticionLocal.getCentro_sap() != null){
					datosOpcionalesArray.add(elementoPeticionLocal.getCentro_sap());
					equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);

				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(elementoPeticionLocal.getAlmacen_sap() != null){
					datosOpcionalesArray.add(elementoPeticionLocal.getAlmacen_sap());
					equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(elementoPeticionLocal.getCod_lote_sap() != null){
					datosOpcionalesArray.add(elementoPeticionLocal.getCod_lote_sap());
					equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);

				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(elementoPeticionLocal.getElement_pep_sap() != null){
					datosOpcionalesArray.add(elementoPeticionLocal.getElement_pep_sap());
					equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);

				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(elementoPeticionLocal.getFlag_mat_sap() != null){
					datosOpcionalesArray.add(elementoPeticionLocal.getFlag_mat_sap());
					equiposCliente.setXI_SAP(datosOpcionalesArray);

				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				
				listaEquipos.add(equiposCliente);

			}
		}
		
		//Para los modems
		Collection modemPeticionList = peticionLocal.getModem();
		for (Iterator modemPeticionIter = modemPeticionList.iterator(); modemPeticionIter.hasNext();){
			ModemLocal modemLocal = (ModemLocal)modemPeticionIter.next();
			ModemKey modemKey = (ModemKey)modemLocal.getPrimaryKey();
			
			//se recontruye (limpia) la clase pojo para que no se repitan los datos en el arreglo
			equiposCliente = new EQUIPOSCLIENTE();
			//
			
			if (modemKey.serial != null && modemKey.serial.length() > 0 && !modemKey.serial.equals("0") && !modemKey.serial.equals("NO SERIAL")){	
				
				equiposCliente.setInvsn(modemKey.serial);
				equiposCliente.setXI_BRAND(modemLocal.getModem_marca());
				if (modemLocal.getModelo()!=null && modemLocal.getModelo().length()>0){
					equiposCliente.setXI_MODEL(modemLocal.getModelo());
				}else{
					equiposCliente.setXI_MODEL("");
				}
				
				if (modemLocal.getTipo() != null && modemLocal.getTipo().intValue()==ComunInterfaces.identificadorWiFi){
					ArrayList datosOpcionalesArray = new ArrayList();
					datosOpcionalesArray.add(ComunInterfaces.MODEM_WIFI);
					equiposCliente.setInv_type(ComunInterfaces.MODEM_WIFI);
					
				}else{
					ArrayList datosOpcionalesArray = new ArrayList();
					datosOpcionalesArray.add(ComunInterfaces.MODEM_STD);
					equiposCliente.setInv_type(ComunInterfaces.MODEM_STD);
					
				}
				//-----------------------------
				ArrayList datosOpcionalesArray = new ArrayList();
				datosOpcionalesArray.add("");
				equiposCliente.setXI_CARD_SERIAL_NUMBER(datosOpcionalesArray);
				//----------------------------
				datosOpcionalesArray = new ArrayList();
				datosOpcionalesArray.add("");
				equiposCliente.setXI_MATERIAL_CODE(datosOpcionalesArray);
				//----------------------------
				datosOpcionalesArray = new ArrayList();
				datosOpcionalesArray.add("");
				equiposCliente.setXI_CAS_ID(datosOpcionalesArray);
				//--------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				datosOpcionalesArray.add("");
				equiposCliente.setXI_MATERIAL_CODE(datosOpcionalesArray);
				//--------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(modemLocal.getClase_mov_sap() != null){
					datosOpcionalesArray.add(modemLocal.getClase_mov_sap());
					equiposCliente.setXI_SAP_MOVIMIENTO(datosOpcionalesArray);

				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_MOVIMIENTO(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(modemLocal.getAlmacen_sap() != null){
					datosOpcionalesArray.add(modemLocal.getAlmacen_sap());
					equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(modemLocal.getCod_lote_sap() != null){
					datosOpcionalesArray.add(modemLocal.getCod_lote_sap());
					equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(modemLocal.getElement_pep_sap() != null){
					datosOpcionalesArray.add(modemLocal.getElement_pep_sap());
					equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);

				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
				}
				//-------------------------------------------------------------
				//-------------------------------------------------------------
				datosOpcionalesArray = new ArrayList();
				if(modemLocal.getFlag_mat_sap() != null){
					datosOpcionalesArray.add(modemLocal.getFlag_mat_sap());
					equiposCliente.setXI_SAP(datosOpcionalesArray);

				}else{
					datosOpcionalesArray.add("");
					equiposCliente.setXI_SAP(datosOpcionalesArray);
				}
				//-------------------------------------------------------------				
				listaEquipos.add(equiposCliente);
			}
		}
//		logica para la generacion de el arregloe setOtros_Datos_BA el cual contiene arreglos de propriedades junto con su key y value
//		datosOpcionalesArray = new ArrayList();
//		otherElement.setKey("");
//		otherElement.setValue("");
//		datosOpcionalesArray.add(otherElement.getKey());
//		datosOpcionalesArray.add(otherElement.getValue());
//		otherType.setPropiedad(datosOpcionalesArray);
//		equiposCliente.setXI_SAP_OTROS(otherType);

		//no esta cumpliendo accion alguna y puede replicar informacion
		//listaEquipos.add(equiposCliente);
//fin arreglo setOtros_Datos_BA
		return (ArrayList) listaEquipos;
		
	}
	
	
	private String cantidadDeDecos(PeticionLocal peticionLocal){
		
		Collection psPeticion=peticionLocal.getProducto_servicio_peticion();
		Iterator psPeticionIt=null;
		
		int contadorDecos=0;
		String numDecos="0";
		boolean solucionTV = false;
		boolean esTraslado = false;
		for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
			Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
			Operacion_comercialLocal opcoLocal = producto_servicio_peticionLocal.getOperacion_comercial();
			Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
			if((familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV
					||familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV
					||familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV)
					&& opcoLocal.getOpco_tipo() != null
					&& (opcoLocal.getOpco_tipo().equals("A")|| opcoLocal.equals("ACP"))){
				log.debug("El ps pertenece a una familia de tipo Deco");
				contadorDecos++;
				
			}
			if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaPaqueteTV)
				solucionTV = true;
			if(familia_producto_servicioKey.faps_id.intValue() != ComunInterfaces.altaMigracionPS &&
					familia_producto_servicioKey.faps_id.intValue() != ComunInterfaces.bajaMigracionPS)
				esTraslado = true;
			/*if(producto_servicio_peticionLocal.getPsId().intValue()==ComunInterfaces.psTVACCESOBASE){
			//	contadorDecos++;
			}*/
		}
		numDecos=""+contadorDecos;
		
		xa_number_decoders.add(numDecos);
		tr800e.setXA_NUMBER_DECODERS(xa_number_decoders);
		log.debug("El número de decos resultante es: "+numDecos);
		
		return numDecos;
	}
	
	private String tiposDeDeco(PeticionLocal peticionLocal)
	{
		Iterator psPeticionIt=null;
		Collection psPeticion=peticionLocal.getProducto_servicio_peticion();
		String tipoDeco="";
		boolean tieneHD=false;
		boolean tienePVR=false;
		boolean tieneSTD=false;
		for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
			Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
			Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
			if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
				tieneHD=true;						
				log.debug("Tiene deco HD");
			}else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV){
				tienePVR=true;						
				log.debug("Tiene deco PVR");
			}
			else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV){
				tieneSTD=true;
				log.debug("Tiene deco STD");
			}
		}
		
		if(tieneHD){
			tipoDeco=ComunInterfaces.desHCDecoHDTV;
		}else if(tienePVR){
			tipoDeco=ComunInterfaces.desHCDecoPVR;
		}else if(tieneSTD){
			tipoDeco=ComunInterfaces.desHCDecoSTD;
		}
		log.debug("El tipo de deco resultante es: "+tipoDeco);
		return tipoDeco;
	}
	
	
	private String listaTematicos(PeticionLocal peticionLocal){
		//Se obtienen los PS de la petíción
		String listaTematicosTV="";
		
		Collection productoServicioPetList=peticionLocal.getProducto_servicio_peticion();
		Iterator listaProductoServicioPetIt=null;
		
		for(listaProductoServicioPetIt=productoServicioPetList.iterator();listaProductoServicioPetIt.hasNext();){
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal)listaProductoServicioPetIt.next();
			Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
			Producto_servicioKey  producto_servicioKey=(Producto_servicioKey)producto_servicioLocal.getPrimaryKey();
			
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey)producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey();
			if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaTematicoTV){
				listaTematicosTV+=" " + producto_servicioLocal.getPs_nombre();
				
			}
		}
		return listaTematicosTV;
	}
	private int cantidadReintentosPGC(){
		//se declara una variable que cuenta los reintentos de la peticion en pgc
		int canPGC=0;
		try {
			bitacora= (Bitacora_peticionLocalHome)HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			bitacoraLocal= bitacora.findbyNumeroPeticion(idPeticion); 

			//recorremos el la collection de la tabla bitacora dodne sabremos las veces q ha pasado la peticion por PGC
			for(Iterator iter = bitacoraLocal.iterator();iter.hasNext();){
  
	        Bitacora_peticionLocal bitLocal = (Bitacora_peticionLocal)iter.next();
	        log.debug("Actividades a las que ha llegado la peticion:"+idPeticion+" es "+bitLocal.getIdActividad());
	        // se contaviliza las veces que ha pasado por PGC
	        if(bitLocal.getIdActividad().equals(new Long(ComunInterfaces.idActividadPGC)))
	        	canPGC++;                     		
			}  
			log.debug("Numero de llegadas a PGC: "+canPGC);
			
		} catch (NamingException e) {
			log.debug("Ha ocurrido un error levantado el home del BEAN de Bitacora_peticionLocalHome:"+idPeticion);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Ha ocurrido un error levantado el home del BEAN de Bitacora_peticionLocalHome:"+idPeticion);
		}
		return canPGC;
	}
	/**
	 * @param peticionLocal2
	 * @param act
	 */
	private void setTipoAccionTOA(PeticionLocal peticionLocal2, ActividadEJBDTO act) {
		// TODO Apéndice de método generado automáticamente
	
		try {
			Collection psp = peticionLocal2.getProducto_servicio_peticion();
			int prioridadAnt = 6;
			String actividad = null;
			String psLinea = "";
			String psBA = "";
			String psTV = "";
			String psVoip = "";
			String psNK = "";
			String psEquipo = "";
			String actividadFinal = "";
			String listaPs="";
			boolean isVisitaTecnica = false;
			
			Properties_BDLocalHome propertiesBDHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesBDLocal = propertiesBDHome.findByPrimaryKey(ComunInterfaces.PS_VISITA_TECNICA);
			String ps_visita_tecnica [] = propertiesBDLocal.getValor().split(",");
			//se agrega validacion de alta o baja con migracion de deco para agregar tarea de migracionIQT 
			log.debug("Se va a validar si es alta o baja migracion deco en setTipoAccionTOA "+act.getNumeroPeticion());
			boolean hayAltaBajaMigracionDeco =validaBajAltaMigrDeco(psp);
			log.debug("hay alta baja migracion deco "+hayAltaBajaMigracionDeco+"setTipoAccionTOA "+act.getNumeroPeticion());
			for (Iterator iter = psp.iterator(); iter.hasNext();) {
				Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal) iter.next();
				Producto_servicioLocal psLocal = pspLocal.getProducto_servicio();
				Familia_producto_servicioKey familiakey = pspLocal.getFamiliaKey();
				Operacion_comercialLocal opcoLocal = (Operacion_comercialLocal) pspLocal.getOperacion_comercial();
				Operacion_comercialKey opcoKey = (Operacion_comercialKey) opcoLocal.getPrimaryKey();
				Producto_servicioKey psKey = (Producto_servicioKey) psLocal.getPrimaryKey();
				for(int i=0; i < ps_visita_tecnica.length;i++){
					int ps = Integer.parseInt(ps_visita_tecnica[i]);
					if(psKey.ps_id.intValue() == ps)
						isVisitaTecnica = true;
				}
				
				Parametro_actLocal parametroActlocal = null;
				boolean tieneTipoTarea = false;
				try {
					Parametro_actLocalHome parametroActhome = (Parametro_actLocalHome) HomeFactory.getHome(Parametro_actLocalHome.JNDI_NAME); 
//					parametroActlocal = parametroActhome.findByPrimaryKey(new Parametro_actKey(opcoKey.opco_id));
					boolean esAutoInstalacionSoloBA=peticionesDelegate.esAutoInstalacionSoloBA(act.getNumeroPeticion());
					
					if(isVisitaTecnica){
						parametroActlocal = parametroActhome.findByPrimaryKey(new Parametro_actKey(new Long (1002)));
						tr800e.setXA_FAMILIA(psLocal.getFa_ps());
					}else{
						if(esAutoInstalacionSoloBA && act.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)){
//							parametroActlocal = parametroActhome.findOC_act_id(opcoKey.opco_id, new Long(0));
							parametroActlocal = parametroActhome.findByPrimaryKey(new Parametro_actKey(new Long (1001)));
						}else{
							parametroActlocal = parametroActhome.findByPrimaryKey(new Parametro_actKey(opcoKey.opco_id));
						}
					}
					
					
					tieneTipoTarea = true;
				} catch (FinderException e) {
					// TODO Bloque catch generado automáticamente
					log.debug("PS: "+ opcoKey.opco_id + " no tiene configuracion parametrizada en la tabla");
					tieneTipoTarea = false;
				}
				
				if(!tieneTipoTarea){
					//continua flujo
					try {
						Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
						ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
						ActividadLocal actividadLocal = actividadLocalHome.findByCodigoActividadIdAplicacion(act.getCodigoActividad(), idAplicacion);
						ActividadKey actividadKey = (ActividadKey) actividadLocal.getPrimaryKey();
						Bitacora_peticionLocalHome btHome =  (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
						Bitacora_peticionLocal btLocal = btHome.findbyMaxActivity(act.getNumeroPeticion(), actividadKey.act_id);
						log.debug("No se encontro tipo de Actuaciòn");
						act.setObservacion("No se encontro tipo de Actuaciòn");
						btLocal.setBipe_observacion("No se encontro tipo de Actuaciòn");
						ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
						IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(act.getCodigoActividad());
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,VpistbbaConfig.getVariable("IDPGI"));
						act.setRealizarTerminoInmediato(true);
						actividadEJB.terminar(act);
						return;
					} catch (FinderException e1) {
						// TODO Bloque catch generado automáticamente
						log.debug("PS: "+ opcoKey.opco_id + " no tiene configuracion parametrizada en la tabla");
					} catch (ATiempoAppEx e1) {
						// TODO Bloque catch generado automáticamente
						log.debug("PS: "+ opcoKey.opco_id + " no tiene configuracion parametrizada en la tabla");
					} catch (TnProcesoExcepcion e1) {
						// TODO Bloque catch generado automáticamente
						log.debug("PS: "+ opcoKey.opco_id + " no tiene configuracion parametrizada en la tabla");
					}
				}
				//se agrega validacion de altamigracion deco donde se ingnora la operacion comercail de alta o baja para que tome la accion de migracion sfegun su prioridad
				if(parametroActlocal.getAct_prioridad().intValue()< prioridadAnt 
						&& !(hayAltaBajaMigracionDeco
								&&(opcoLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)
								||opcoLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja)))){
					
					actividad = parametroActlocal.getAct_descripcion();
					actividadFinal = parametroActlocal.getAct_tipo_actuacion();
					prioridadAnt = parametroActlocal.getAct_prioridad().intValue();
				}
				
				if(psLocal.getFa_ps() != null){
					if(familiakey.faps_id.intValue() == familiaLinea || familiakey.faps_id.intValue() == familiaPcLinea)
						psLinea = psLocal.getFa_ps();
					if(familiakey.faps_id.intValue() == familiaBandaAncha || familiakey.faps_id.intValue() == familiaPcBA)
						psBA = psLocal.getFa_ps(); 
					if(familiakey.faps_id.intValue() == familiaBandaAnchaNaked)
						psVoip = psLocal.getFa_ps();
					if(familiakey.faps_id.intValue() == familiaPcBANaked || familiakey.faps_id.intValue() == familiaPcPsBANaked)
						psNK = psLocal.getFa_ps();
					if( familiakey.faps_id.intValue() == familiaTV
							|| familiakey.faps_id.intValue() == familiaPaqueteTV
							|| familiakey.faps_id.intValue() == familiaPcTV
							|| familiakey.faps_id.intValue() == familiaTematicoTV)
						psTV = psLocal.getFa_ps();
					if(familiakey.faps_id.intValue() == familiaDecoTV 
							|| familiakey.faps_id.intValue() == familiaDecoHDTV
							|| familiakey.faps_id.intValue() == familiaDecoPVRTV)
						psEquipo = psLocal.getFa_ps();
						
				}
				listaPs=listaPs+psLocal.getPs_nombre();
			}
			
			if (listaPs.length()>0){
				listaPs = listaPs.substring(0,listaPs.length()-1);
			}
			
			actividadFinal = actividadFinal+psLinea+psBA+psVoip+psNK+psTV+psEquipo;
			tr800e.setAworktype_label(actividad);
			tr800e.setXA_WORK_TYPE(actividadFinal);
			tr800e.setXA_DESCRIPTION(listaPs);
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error EJBException setTipoAccionTOA "+e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error NamingException setTipoAccionTOA "+e  );
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error Exception setTipoAccionTOA "+e);
		}
	}
	
	/**
	 * @param peticionLocal2
	 */
	private void setearDiireccion(PeticionLocal peticionLocal2) {
		// TODO Apéndice de método generado automáticamente
		LocalidadLocal localidad = peticionLocal2.getFk_01_localidad();
		LocalidadKey localidadKey = (LocalidadKey) localidad.getPrimaryKey();
		DepartamentoLocal departamento = peticionLocal2.getFk_02_departamento();
		DepartamentoKey departamentoKey = (DepartamentoKey) departamento.getPrimaryKey();
		
		xa_number_decoders=  new ArrayList();
		tr800e.setXA_CITY_CODE(localidadKey.cod_loc);
		tr800e.setCity(localidad.getDescripcion_localidad());
		tr800e.setXA_STATE_CODE(departamentoKey.cod_dpt);
		tr800e.setState(departamento.getDescripcion_departamento());
		tr800e.setXA_NEIGHBORHOOD(peticionLocal2.getNom_slo_no());
		tr800e.setXA_QUADRANT("");
		if (peticionLocal2.getCoord_x_agnd_sc() != null && peticionLocal2.getCoord_x_agnd_sc().length()>0){
			tr800e.setAcoord_x(Double.parseDouble(peticionLocal2.getCoord_x_agnd_sc()));
		}else{
			log.debug("No se encuatra especificada la coordenada X se setea 0 por defecto para evitar conflicots con agenda SC");
			tr800e.setAcoord_x(0.0);
		}
		if (peticionLocal2.getCoord_x_agnd_sc() != null && peticionLocal2.getCoord_x_agnd_sc().length()>0){
			tr800e.setAcoord_x(Double.parseDouble(peticionLocal2.getCoord_x_agnd_sc()));
		}else{
			log.debug("No se encuatra especificada la coordenada X se setea 0 por defecto para evitar conflicots con agenda SC");
			tr800e.setAcoord_x(0.0);
		}
		if (peticionLocal2.getCoord_y_agnd_sc() != null && peticionLocal2.getCoord_y_agnd_sc().length()>0){
			tr800e.setAcoord_y(Double.parseDouble(peticionLocal2.getCoord_y_agnd_sc()));
		}else{
			log.debug("No se encuatra especificada la coordenada Y se setea 0 por defecto para evitar conflicots con agenda SC");
			tr800e.setAcoord_y(0.0);
		}
		Collection recursosLineaArray = peticionLocal2.getRecursos_linea_basica();
		Iterator iter = recursosLineaArray.iterator();
		Recursos_linea_basicaLocal recursosLineaLocal = null;
		
		if(iter != null && iter.hasNext()){
			recursosLineaLocal = (Recursos_linea_basicaLocal) iter.next();
			if(recursosLineaLocal.getDist_prim_asg() != null && !recursosLineaLocal.getDist_prim_asg().equals("")
					&& recursosLineaLocal.getArmario_asg() != null && !recursosLineaLocal.getArmario_asg().equals(""))
				tr800e.setXA_WORK_ZONE_KEY(recursosLineaLocal.getDist_prim_asg() +"_"+recursosLineaLocal.getArmario_asg());
			
		}else
			tr800e.setXA_WORK_ZONE_KEY(localidad.getDescripcion_localidad()+"-"+localidadKey.cod_loc+"_"+peticionLocal2.getNom_slo_no());
		
	}
	/**
	 * 
	 */
	private OTHERTYPE setAX_DAT_OP_CLIENT() {
		return null;
		// TODO Apéndice de método generado automáticamente
		
	}
	
	/**
	 * @param tr800e2
	 * @throws ATiempoAppEx
	 */
	private void finalizarProceso(TR800E tr800e, ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
		CommunicatorWSDelegate delegate = new CommunicatorWSDelegate();
		log.debug("Se envia TR800e hacia TOA: "+tr800e.toString());
		String respuesta = delegate.recibirMensaje(tr800e);
//		String respuesta = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header/><soapenv:Body><man:createResponseWorkOrder xmlns:man=\"http://service.telefonica.com/Operations/ResourceManagementOperations/WorkforceManagement/ManageWorkOrderLifecycleScheduler_PS/\"><XA_SOURCE_SYSTEMR>AGENDADOR</XA_SOURCE_SYSTEMR>";// para proceso de pruebas unitarias
		log.debug("Se recibe respuesta: "+respuesta);
		recepcionCrearActuacionTOA(respuesta, act, tr800e.getId_actuacion());
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionCrearActuacionTOA()
	 */
	public void recepcionCrearActuacionTOA(String respuesta, ActividadEJBDTO act, String idActuacion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try {
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(act.getCodigoActividad());
			Bitacora_peticionLocalHome btHome =  (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
//			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ActividadLocal actividadLocal = actividadLocalHome.findByCodigoActividadIdAplicacion(act.getCodigoActividad(), idAplicacion);
			ActividadKey actividadKey = (ActividadKey) actividadLocal.getPrimaryKey();
			log.debug("Se busca bitacora para peticion: " + act.getNumeroPeticion() + " y actividad: "+act.getIdActividadFlujo());
			Bitacora_peticionLocal btLocal = null;
			
			try {
				btLocal = btHome.findbyMaxActivity(act.getNumeroPeticion(), actividadKey.act_id);
			} catch (FinderException e1) {
				// TODO Bloque catch generado automáticamente
				log.debug("No se encontrò Bitacora: "+ idActuacion);
			}
			
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			boolean esAutoInstalacionSoloBA=peticionesDelegate.esAutoInstalacionSoloBA(act.getNumeroPeticion());
				
			if(respuesta == null){
				log.debug("Se deriva a PGI porque no se pudo conectar con el servicio SOA");
				act.setObservacion("Se deriva a PGI porque no se pudo conectar con el servicio SOA");
				
				if(btLocal != null)
					btLocal.setBipe_observacion("Se deriva a PGI porque no se pudo conectar con el servicio SOA");
				
				try {
					Agenda_scLocal agendaLocal =  agenda_SCLocalHome.findByPrimaryKey(new Agenda_scKey (idActuacion));
					agendaLocal.setEstado(new Integer(2));
//					BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaLocal.getPeti_numero(),idAplicacion);
				} catch (FinderException e1) {
					// TODO Bloque catch generado automáticamente
					log.debug("No se encontrò actuacion: "+ idActuacion);
				}
				//solucion quiebres automaticos a los PS 
				Long causalAuto=new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO"));
				try{
				insertarCausalesCnaPeticion(btLocal.getFk_peticion(), act.getCodigoActividad(),causalAuto, act.getIdActividadFlujo());
				}catch (Exception e){
					log.debug("Ha ocurrido un error al crear los causales TOA 800S peticion "+act.getNumeroPeticion()+" "+e);
				}
			//	btLocal.get
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,VpistbbaConfig.getVariable("IDPGI"));
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
				act.setRealizarTerminoInmediato(true);
				actividadEJB.terminar (act);

			}else{
				
				if(esAutoInstalacionSoloBA && act.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)){
					act.setRealizarTerminoInmediato(true);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"AutoInstalacion");
					actividadEJB.terminar (act);
					return;
				}
				TR800SParser parser = new TR800SParser();
				TR800S tr800s = parser.parse(respuesta);
				String descripcionError = tr800s.getXAORDERDESCERR();
				if(descripcionError != null && descripcionError.length() > 170)
					descripcionError = descripcionError.substring(0,170);
				if (tr800s.getXAORDERCODERR().equals("0")){
					//continua flujo
					Agenda_scLocal agendaLocal =  agenda_SCLocalHome.findByPrimaryKey(new Agenda_scKey (idActuacion));
//					BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaLocal.getPeti_numero(),idAplicacion);
					log.debug("Se recibe respuesta sin error: " + descripcionError);
					act.setObservacion("Se recibe respuesta sin error: " + descripcionError);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,"X");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"S");
					if(btLocal != null)
						btLocal.setBipe_observacion("Se recibe respuesta sin error: " + descripcionError);
//					act.setRealizarTerminoInmediato(true);
					if(act.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA))
						actividadEJB.terminar(act);
				}else{
					//PGI;
					log.debug("Se deriva a PGI: " + descripcionError);
					act.setObservacion("Se deriva a PGI: " + descripcionError);
					if(btLocal != null)
						btLocal.setBipe_observacion("Se deriva a PGI: " + descripcionError);
					try {
						Agenda_scLocal agendaLocal =  agenda_SCLocalHome.findByPrimaryKey(new Agenda_scKey (idActuacion));
						agendaLocal.setEstado(new Integer(2));
//						BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaLocal.getPeti_numero(),idAplicacion);
					} catch (FinderException e1) {
						// TODO Bloque catch generado automáticamente
						log.debug("No se encontrò actuacion: "+ idActuacion);
					}
					//solucion quiebres automaticos a los PS 
					Long causalAuto=new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO"));
					try{
					insertarCausalesCnaPeticion(btLocal.getFk_peticion(), act.getCodigoActividad(),causalAuto, act.getIdActividadFlujo());
					}catch (Exception e){
						log.debug("Ha ocurrido un error al crear los causales TOA 800S peticion "+act.getNumeroPeticion()+" "+e);
					}
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,VpistbbaConfig.getVariable("IDPGI"));
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
					act.setRealizarTerminoInmediato(true);
					actividadEJB.terminar (act);
				}
			}
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera error al finalizar la actividad" + e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera error al finalizar la actividad" + e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera error al finalizar la actividad" + e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera error al finalizar la actividad" + e);
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionActivarDecosTarjetasTOA(co.com.telefonica.atiempo.interfaces.atiempo.TR801S)
	 */
	public void recepcionActivarDecosTarjetasTOA(TR801S tr801s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try {
			log.debug("Entro a procesar los Decos Informados");
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			Mensaje_agenda_scLocalHome mensaje_agenda_home = (Mensaje_agenda_scLocalHome)HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
			
			Agenda_scLocal agendaSCLocal = null;
		
			try{
				Agenda_scKey agendaSCKey = new Agenda_scKey(tr801s.getIdSchedule());
				agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
			}catch(FinderException ex){
				log.debug("No se encuentra el código de agendamiento, debe haber ocurrido un reagendamiento se procede a cerrar las actuaciones abiertas y crear esta");
				String idPeticionAux = tr801s.getIdSchedule().substring(2,tr801s.getIdSchedule().indexOf("-"));
				
				Collection agendaSCCollection = agendaSCLocalHome.findByPetiNumero(new Long(idPeticionAux));
				for (Iterator agendaSCIterator = agendaSCCollection.iterator(); agendaSCIterator.hasNext();){
					Agenda_scLocal agendaSCLocalAux = (Agenda_scLocal) agendaSCIterator.next();
					
					if (agendaSCLocalAux.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA){
						agendaSCLocalAux.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
					}
				}
				
				PeticionKey peticionKey = new PeticionKey(new Long(idPeticionAux));
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
									
				agendaSCLocal = agendaSCLocalHome.create(tr801s.getIdSchedule());
				agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
				agendaSCLocal.setPeti_numero(new Long(idPeticionAux));
				agendaSCLocal.setPeticion(peticionLocal);
				agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			}
													
			PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
			
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
							
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);

			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
			
			Long IdCorrelativoAgenda = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			this.almacenarMensajeAgendaSC(IdCorrelativoAgenda, tr801s.getIdSchedule(), tr801s.getApptNumber(), actDto, peticionKey);


			if ((agendaSCLocal.getEstado().intValue() == ACTUACION_ABIERTA || agendaSCLocal.getEstado().intValue() == ACTUACION_REAGENDADA)
					&& tr801s.getEquipments()!=null){
				Collection decoTarjetaOriginal = peticionLocal.getDeco_tarjeta();
				ArrayList decoTarjetaInstall = new ArrayList();
				ArrayList decoTarjetaOld = new ArrayList();
				
				//Aqui se valida que los decos que traen el mensaje hallan para bajar es decir en la tabla hay 3 
				//y el mensaje trae tres se baja el que no trae el mensaje 
				for (Iterator decoTarjetaOldIterator = decoTarjetaOriginal.iterator(); decoTarjetaOldIterator.hasNext();){
					boolean estaInstalado = false;
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
					
					DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
					decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
					decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
					decoTarjetaDTO.setOperationComercial(new Long(operacionParDesactivar));
					decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
					decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
					decoTarjetaDTO.setAccion(decoTarjetaLocal.getAccion());
					decoTarjetaDTO.setEstado(decoTarjetaLocal.getEstado());
					
					decoTarjetaOld.add(decoTarjetaDTO);
						
					Collection equipos = tr801s.getEquipments();
					for (Iterator equiposIterator=equipos.iterator(); equiposIterator.hasNext();){
						TR801SEquipment equipo = (TR801SEquipment)equiposIterator.next();
							
						if (!decoTarjetaKey.id_deco.equals(equipo.getDecoCassId()) && !decoTarjetaKey.id_tarjeta.equals(equipo.getCardCode())){
							estaInstalado = false;
						}else{
							estaInstalado = true;
							break;
						}
					}
					
					if (!estaInstalado && decoTarjetaLocal.getEstado().intValue() != estadoParNoOk && 
							decoTarjetaLocal.getAccion().intValue() != ComunInterfaces.accionParEliminar){
						log.debug("El deco viene para eliminar");
						decoTarjetaLocal.setEstado(new Integer(ComunInterfaces.estadoOk));	//Se deja en estado par desactivado.				
						decoTarjetaLocal.setAccion(new Integer(ComunInterfaces.accionParEliminar));
						
						decoTarjetaInstall.add(decoTarjetaDTO);
					}
				}
					
				Collection decoTarjetaList = peticionLocal.getDeco_tarjeta();
				Collection decoTarjetaEliminarList = new ArrayList();
//				Aqui se elimina el deco que se dio de baja en la primera parte
				for (Iterator decoTarjetaInstallIterator = decoTarjetaList.iterator(); decoTarjetaInstallIterator.hasNext();){
					Deco_tarjetaLocal decoTarjetaDTO = (Deco_tarjetaLocal)decoTarjetaInstallIterator.next();
					
					if (decoTarjetaDTO.getEstado() != null && decoTarjetaDTO.getEstado().intValue() == estadoParNoOk){
						Deco_tarjetaKey decoTarjetaKeyEliminar = (Deco_tarjetaKey)decoTarjetaDTO.getPrimaryKey();
						Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKeyEliminar);					
						decoTarjetaEliminarList.add(decoTarjetaKeyEliminar);
					}
				}
				
				for (Iterator decoTarjetaEliminarIterator = decoTarjetaEliminarList.iterator(); decoTarjetaEliminarIterator.hasNext();){
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaEliminarIterator.next();
					
					Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
					decoTarjetaAuxLocal.setAccion(new Integer(ComunInterfaces.accionParEliminar));
					
					
				}
				
				//Aqui se valida que el mensaje traiga nuevos decos, se da de alta el nuevo
				Collection equipos = tr801s.getEquipments();
				Collection ps=peticionLocal.getProducto_servicio_peticion();
				
				for(Iterator equiposIterator=equipos.iterator(); equiposIterator.hasNext();){
					TR801SEquipment equipo = (TR801SEquipment)equiposIterator.next();
					boolean estaSinInstalar = true;
					boolean estaNoOK = false;
					for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
						DecoTarDTO decoTarjetaLocal = (DecoTarDTO)decoTarjetaOldIterator.next();
						//Se verifica si un equipo que se envia en la tr708s ya existia previamente y si tiene un estado de activacion fallido
						//En ese caso se vuelve a enviar a activar
						if (decoTarjetaLocal.getDeco().equals(equipo.getDecoCassId()) && decoTarjetaLocal.getTarjeta().equals(equipo.getCardSerialNumber())
								&& decoTarjetaLocal.getEstado().intValue() == ComunInterfaces.estadoParNoOk){
							estaNoOK = true;
						}
						
						if (decoTarjetaLocal.getDeco().equals(equipo.getDecoCassId()) && decoTarjetaLocal.getTarjeta().equals(equipo.getCardSerialNumber())
								&& decoTarjetaLocal.getEstado().intValue() != ComunInterfaces.estadoParNoOk){
							estaSinInstalar = false;
							break;
						}
					}
					
					if (estaSinInstalar){
						Deco_tarjetaLocal decoTarjetaLocal = null;
						if (estaNoOK){
							Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionKey);
							decoTarjetaLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
						}else{
							decoTarjetaLocal = decoTarjetaLocalHome.create(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionLocal);
						}						
						////														
						decoTarjetaLocal.setAccion(new Integer(ComunInterfaces.accionParActivar));
						decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
						decoTarjetaLocal.setOpco_id(new Long(ComunInterfaces.accionParActivar));
						decoTarjetaLocal.setEstado(new Integer(ComunInterfaces.accionParActivar));
						
						if (equipo.getDecoType().indexOf(desHCDecoSTD) != -1){
							decoTarjetaLocal.setDeco_reference(desHCDecoSTD);
						}else if (equipo.getDecoType().indexOf(desHCDecoPVR) != -1){
							decoTarjetaLocal.setDeco_reference(desHCDecoPVR);
						}else if (equipo.getDecoType().indexOf(desHCDecoHDTV) != -1){
							decoTarjetaLocal.setDeco_reference(desHCDecoHDTV);
						}
						
						decoTarjetaLocal.setDeco_marca(equipo.getDecoBrand());
						decoTarjetaLocal.setSerial_deco(equipo.getDecoSerialNumber());
						decoTarjetaLocal.setSerial_tarjeta(equipo.getCardCode());
						decoTarjetaLocal.setCodigo_deco(equipo.getDecoCode());
						
						DecoTarDTO decoTarjetaDTO = new DecoTarDTO(equipo.getDecoCassId(), equipo.getCardSerialNumber());
						decoTarjetaDTO.setAccion(new Integer(ComunInterfaces.accionParActivar));
						decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
						decoTarjetaDTO.setOperationComercial(decoTarjetaLocal.getOpco_id());
						decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
						decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
						
						decoTarjetaInstall.add(decoTarjetaDTO);
					}else{
						try{
							Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionKey);
							Deco_tarjetaLocal decoTarjetaLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
							
							if (decoTarjetaLocal.getEstado() != null && decoTarjetaLocal.getEstado().intValue() == ComunInterfaces.accionParEliminar){
								decoTarjetaLocal.setAccion(new Integer(ComunInterfaces.accionParActivar));
								decoTarjetaLocal.setCodigo_error(new Integer(0));
								decoTarjetaLocal.setMensaje_error("");
								decoTarjetaLocal.setOpco_id(new Long(ComunInterfaces.accionParActivar));
								decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
								
								
								DecoTarDTO decoTarjetaDTO = new DecoTarDTO(equipo.getDecoCassId(), equipo.getCardSerialNumber());
								decoTarjetaDTO.setAccion(new Integer(ComunInterfaces.accionParActivar));
								decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
								decoTarjetaDTO.setOperationComercial(decoTarjetaLocal.getOpco_id());
								decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
								decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
								
								decoTarjetaInstall.add(decoTarjetaDTO);
							}	
						}catch(FinderException ex){
							
						}
						
					}
				}
				
				if (decoTarjetaInstall != null && decoTarjetaInstall.size() > 0){
					boolean haEnviadoMensaje = false;
					Collection mensajesEstadoTV = peticionLocal.getMensaje_estado_tv();
					
					for(Iterator mensajeEstadoTVIterator = mensajesEstadoTV.iterator(); mensajeEstadoTVIterator.hasNext();){
						Mensaje_estado_tvLocal mensajeEstadoTVLocal = (Mensaje_estado_tvLocal) mensajeEstadoTVIterator.next();
						
						if (mensajeEstadoTVLocal.getDesc_error() != null && mensajeEstadoTVLocal.getDesc_error().indexOf("@tr801") != -1){
							haEnviadoMensaje = true;
							break;
						}
					}
					
										
					String idTOAservicio = tr801s.getIdSchedule()+"@"+tr801s.getId()+"-"+IdCorrelativoAgenda+"@tr801";
					
					this.enviaConfiguracionServiciosTVAgendaSC(peticionKey.peti_numero.longValue(), decoTarjetaInstall, haEnviadoMensaje, idTOAservicio);
				}else{
					log.debug("No se envía el mensaje: "+ tr801s.getId() +" a HC porque los equipos recibidos ya están instalados");
					String observaciones = actDto.getObservacion(); 
					if(observaciones != null && !observaciones.equals("")){
						actDto.setObservacion(observaciones + " No se envía el mensaje: "+ tr801s.getId() +" a HC porque los equipos recibidos ya están instalados");
					}else{
						actDto.setObservacion("No se envía el mensaje: "+ tr801s.getId() +" a HC porque los equipos recibidos ya están instalados");
					}
											
					this.enviaActivarDecosTarjetasTOA(tr801s.getIdSchedule(), tr801s.getId()+"-"+IdCorrelativoAgenda);
					
					actividadEJB.grabarSinTerminar(actDto);
				}
			}else{
				log.debug("El mensaje: "+tr801s.getId()+" no se puede procesar porque la actuación: "+tr801s.getIdSchedule()+" ya se encuentra cerrada, o el mensaje no trae equipos asociados");
				actDto.setObservacion("El mensaje: "+tr801s.getId()+" no se puede procesar porque la actuación: "+tr801s.getIdSchedule()+" ya se encuentra cerrada, o el mensaje no trae equipos asociados");
				
				this.enviaActivarDecosTarjetasTOA(tr801s.getIdSchedule(), tr801s.getId()+"-"+IdCorrelativoAgenda);
				
				actividadEJB.grabarSinTerminar(actDto);
			}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera error al finalizar la actividad" + e);
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error NumberFormatException recepcionActivarDecosTarjetasTOA "+e);
		}catch(FinderException ex){
			log.debug("Error buscando elementos en la recepción de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}catch(CreateException ex){
			log.debug("Error creando elementos en la recepción de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error creando elementos en la recepción de activar decos Tarjetas: " + e);
			e.printStackTrace();
		}
		
	}

	/**
	 * @param idCorrelativo
	 * @param actDto
	 * @param 
	 * @param appNumber
	 * @param idSchedule
	 * @param peticionKey2
	 */
	private Mensaje_agenda_scLocal almacenarMensajeAgendaSC(Long idCorrelativo, String idSchedule, String appNumber, ActividadEJBDTO actDto, PeticionKey peticionKey2) {
		// TODO Apéndice de método generado automáticamente
		try {
			Mensaje_agenda_scLocalHome mensajeAgendaHome = (Mensaje_agenda_scLocalHome) HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
			Mensaje_agenda_scLocal mensajeAgendaLocal = mensajeAgendaHome.create(idCorrelativo);
			mensajeAgendaLocal.setPeti_numero(peticionKey2.peti_numero);
			mensajeAgendaLocal.setId_agenda(idSchedule);
			mensajeAgendaLocal.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
			mensajeAgendaLocal.setCod_actividad_generadora(actDto.getCodigoActividad());
			mensajeAgendaLocal.setApptNumber(appNumber);
			mensajeAgendaLocal.setReintentos(new Long(0));
			return mensajeAgendaLocal;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
			return null;
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
			return null;
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionActivarModemTOA(co.com.telefonica.atiempo.interfaces.atiempo.TR802S)
	 */
	public void recepcionActivarModemTOA(TR802S tr802s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		log.debug("Entro a recepcionActivarModemsAgendaSC de la actuación:"+tr802s.getId_schedule());

		try{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome)HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome)HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome)HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			ModemLocalHome modemLocalHome = (ModemLocalHome)HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal = null;
			Agenda_scLocal agendaSCLocal = null;
			
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			
			//Obtención de la información del agendamiento
			try{
				Agenda_scKey agendaSCKey = new Agenda_scKey(tr802s.getId_schedule());
				agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
				peticionKey = new PeticionKey(agendaSCLocal.getPeti_numero());
				peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			}catch(FinderException ex){
				log.debug("No se encuentra el código de agendamiento, debe haber ocurrido un reagendamiento se procede a cerrar las actuaciones abiertas y crear esta");
				String idPeticionAux = tr802s.getId_schedule().substring(2,tr802s.getId_schedule().indexOf("-"));
				
				Collection agendaSCCollection = agendaSCLocalHome.findByPetiNumero(new Long(idPeticionAux));
				for (Iterator agendaSCIterator = agendaSCCollection.iterator(); agendaSCIterator.hasNext();){
					Agenda_scLocal agendaSCLocalAux = (Agenda_scLocal) agendaSCIterator.next();
					
					if (agendaSCLocalAux.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA){
						agendaSCLocalAux.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
					}
				}
				
				peticionKey = new PeticionKey(new Long(idPeticionAux));
				peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
									
				agendaSCLocal = agendaSCLocalHome.create(tr802s.getId_schedule());
				agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
				agendaSCLocal.setPeti_numero(new Long(idPeticionAux));
				agendaSCLocal.setPeticion(peticionLocal);
				agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			}
			
			//Obtención de la actividad
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaSCLocal.getPeti_numero(),idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
							
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
			
			/**
			 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
			 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
			 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
			 * 
			 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
			 * tabla bintegrada, y se deben reconvertir a + o /.
			 */
			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(agendaSCLocal.getPeti_numero(), codActividad,idCorrelativo,null);
			
			Long IdCorrelativoAgenda = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			this.almacenarMensajeAgendaSC(IdCorrelativoAgenda, tr802s.getId_schedule(), tr802s.getApptNumber(), actDto, peticionKey);

			if ((agendaSCLocal.getEstado().intValue() == ACTUACION_ABIERTA || agendaSCLocal.getEstado().intValue() == ACTUACION_REAGENDADA) && tr802s.getModem_serial()!=null){
				RecursosBAInterfaces rbaInterfaces = new RecursosBADelegate();
				
				//Obtención de la información del teléfono asociado
				InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(agendaSCLocal.getPeti_numero());
				
				//Asignación de variables del modem
				ModemVpiDTO modem = new ModemVpiDTO();
				modem.setPeti_numero(agendaSCLocal.getPeti_numero());
				modem.setMarca(tr802s.getModem_brand());
				modem.setSerial(tr802s.getModem_serial());
				
				//Se adiciona este cambio para tener en cuenta el codigo de material y el modelo del modem
				modem.setModelo(tr802s.getModel_modem());
				modem.setCodMaterial(tr802s.getMaterial_code());
				
				if (tr802s.getModem_type()!= null && tr802s.getModem_type().equals(ComunInterfaces.MODEM_WIFI)){
					modem.setTipo(new Integer(ComunInterfaces.identificadorWiFi).shortValue());
				}else if (tr802s.getModem_type()!= null && 
						(tr802s.getModem_type().equals(ComunInterfaces.MODEM_STD)||tr802s.getModem_type().equals(ComunInterfaces.MODEM_STD_2)
								|| tr802s.getModem_type().equals(ComunInterfaces.MODEM_STD1P))){
					modem.setTipo(new Integer(ComunInterfaces.identificadorConvencional).shortValue());
				}else{
					modem.setTipo(new Integer(ComunInterfaces.identificadorWiFi).shortValue());
				}
				
				if (informacionTecnicaDTO.LineaNueva.getTelefono()!= null && informacionTecnicaDTO.LineaNueva.getTelefono().length()>0){
					modem.setTelefono(new Long(informacionTecnicaDTO.LineaNueva.getTelefono()));
				}else{
					modem.setTelefono(new Long("0"));
				}
				
				modem.setAccion(new Integer(ComunInterfaces.accionModemNoAction).shortValue());
				
				Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(new Long(tr802s.getId()));
				mensajeEstadoBALocal.setPeticion(agendaSCLocal.getPeticion());
				mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
				
				Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);					
				mensajeEstadoBALocal.setCod_actividad_generadora(codActividad);
				
				//Envío el mensaje de autoinstalación
			//rbaInterfaces.llamadoConfModemAutoInstalacion(modem, actDto.getCodigoActividad(), tr802s.getId()+"#"+tr802s.getIdSchedule(),false, false);
			//REQ BA NAKED 
			//se cambia el direccionamiento de ejecucion del antiguo llamado por webservice hacia direccionamiento por cola
				
				Collection modemArray = peticionLocal.getModem();
				for(Iterator iter = modemArray.iterator();iter.hasNext();){
					ModemLocal modemLocal = (ModemLocal) iter.next();
					ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
					if(!modemKey.serial.equals(modem.getSerial())){
						//
						modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemLiberar).shortValue()));
					}
				}
				Long  telAsignado = new Long (peticionLocal.getNum_ide_nu_stb());
				ModemLocal modemLocal=modemLocalHome.create(modem.getSerial(),peticionLocal,telAsignado,new Short(modem.getAccion()),
						modem.getMarca(),modem.getModelo(),new Integer((int)modem.getTipo()));
				modemLocal.setModem_marca(modem.getMarca());
				modemLocal.setModelo(modem.getModelo());
				modemLocal.setCodigo_material(modem.getCodMaterial());
				/*RQ.8595 - mfmendez*/
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
				
				log.debug("Se adiciona campos de sap para el modem  "+modem.getSerial());
			ACSServicioDelegate aCSServicioDelegate = new ACSServicioDelegate();
			aCSServicioDelegate.enviarAutoConfiguracion(modem,actDto.getCodigoActividad(), tr802s.getId()+"#"+tr802s.getId_schedule()+"@"+IdCorrelativoAgenda);
			//FIN REQ NAKED
			}else{
				log.debug("El mensaje: "+tr802s.getId()+" no se puede procesar porque la actuación: "+tr802s.getId_schedule()+" ya se encuentra cerrada, o el mensaje no trae un serial de modem explícito");
				actDto.setObservacion("El mensaje: "+tr802s.getId()+" no se puede procesar porque la actuación: "+tr802s.getId_schedule()+" ya se encuentra cerrada, o el mensaje no trae un serial de modem explícito");
					
				this.enviaActivarModemTOA(tr802s.getId_schedule(), tr802s.getId()+"-"+IdCorrelativoAgenda);
				
				actividadEJB.grabarSinTerminar(actDto);
			}
		}catch(NamingException ex){
			log.error("NamingException en recepcionActivarModemsAgendaSC()"+ex.toString());
			ex.printStackTrace();
		}catch(FinderException ex){
			log.error("FinderException en recepcionActivarModemsAgendaSC()"+ex.toString());
			ex.printStackTrace();				
		}catch(CreateException ex){
			log.error("CreateException en recepcionActivarModemsAgendaSC()"+ex.toString());
			ex.printStackTrace();				
		}catch(TnProcesoExcepcion ex){
			log.error("CreateException en recepcionActivarModemsAgendaSC()"+ex.toString());
			ex.printStackTrace();
		}

	} 
	public void refrescarRecursosSTB_BA( ITRxxxBase trXXXs){
		inicializarVariables();
		String nroPeticion = "";
		String trXXXsClassName = trXXXs.getClass().getName().split("\\.")[trXXXs.getClass().getName().split("\\.").length-1];
		String id="";
		String idSchedule="";
		Object xml=null;
		
		//Se valida el tipo de tr que se instancia
		if(trXXXsClassName.endsWith("TR803S")){
			nroPeticion= ((TR803S)trXXXs).getIdSchedule();
			TR803S tr803s=(TR803S)trXXXs;
			id=tr803s.getId();
			idSchedule=tr803s.getIdSchedule();
			xml=tr803s;
		}
			else if(trXXXsClassName.endsWith("TR804S")){
			nroPeticion= ((TR804S)trXXXs).getIdSchedule();
			TR804S tr804s=(TR804S)trXXXs;
			id=tr804s.getId();
			idSchedule=tr804s.getIdSchedule();
			xml=tr804s;
		}
		else{
			log.debug("El mensaje no es una TR803S o una TR804S");
			return;	
		}
		
		log.debug("Me llega en peticion NP:"+nroPeticion);
		Long idPeticion = new Long(nroPeticion.substring(2,nroPeticion.indexOf("-")));	
		RecursosInterfaces recursosInterfaces =  null;
		RecursosBAInterfaces recursosBAInterfaces = null;
		PeticionesInterfaces pI = null;				
		boolean esGranite = false;
		
//		 SE VALIDA QUE TIPO DE LINEA ES
		try {
			pI = new PeticionesDelegate();				
			esGranite = pI.usaGranite(idPeticion);
		}
		catch (ATiempoAppEx e)
		{
			log.error(e);
		}
		try{
			recursosBAInterfaces = new RecursosBADelegate();
			
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(idPeticion,idAplicacion);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
			recursosInterfaces = new co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate();						
//			  CR-14657 Granite - adocarmo - inicio			
			if (esGranite) {
				recursosInterfaces.consultaRecursoGraniteSTB_BA(idPeticion,"F", codActividad);
			}
			else {
				if(trXXXsClassName.endsWith("TR803S"))
					recursosInterfaces.consultaRecursoSTB_BA(idPeticion, "F",codActividad);					
			}
			
			if(trXXXsClassName.endsWith("TR804S")){
				recursosBAInterfaces = new RecursosBADelegate();
				//fin sigres
				recursosInterfaces.consultaRecursoSTB_BA(idPeticion, "", codActividad);
				recursosBAInterfaces.enviaRefrescoBA(idPeticion.toString(), "RF", codActividad);
			}
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			
			//Varios Refresh - sfandino
			boolean noHayRegistro=false;
			try{
				
				Tmp_agenda_scLocal tmpAgendaLocal= tmpAgendaSCLocalHome.findbyPeti_numero(idPeticion);
				log.debug("Ya existe un registro con peti_num "+idPeticion+" se procede a eliminarlo y crear uno nuevo");
				tmpAgendaLocal.remove();
				noHayRegistro=true;
			}
			catch(ObjectNotFoundException e){
				log.debug("No existe registro en la tabla Tmp_agenda_sc con peti_num "+idPeticion+" se procede a crearlo");
				//Si no hay registros en la tabla se crea uno nuevo
				noHayRegistro=true;
			}  catch (RemoveException e) {
				
				log.debug("Error al eliminar el registro de la tabla tmp_agenda_sc: "+e);
				e.printStackTrace();
			}
			finally{
				if(noHayRegistro){
					Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.create(new Long (id));
					tmpAgendaSCLocal.setId_schedule(idSchedule);
					tmpAgendaSCLocal.setPeti_numero(idPeticion);
					tmpAgendaSCLocal.setXml(XMLUtilities.marshall (xml));
				}
				else{
				log.debug("Ocurrio un error al intentar eliminar el registro ya existente con peti_num = "+idPeticion+" de la tabla tmp_agenda_sc");	
				}
			}
			
			
		}
		catch (ATiempoAppEx e)
		{
			log.debug(e);
		}
		catch (NamingException e) {
		// TODO Bloque catch generado automáticamente
			log.debug(e);
		}
		catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug(e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug(e);
		} catch (FinderException e) {
			log.debug(e);
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	public void refrescarRecursosSTB(TR803S tr803s) throws ATiempoAppEx{
		this.refrescarRecursosSTB_BA(tr803s);	
	}
	public void recepcionActualizaInventarioBA(TR804S tr804s)
			throws ATiempoAppEx {
		this.refrescarRecursosSTB_BA(tr804s);

	}
	public void recibeActualizacionInventarioTV (TR805E tr805e) throws ATiempoAppEx
    {
		log.debug("Se recibe tr805e con id:"+tr805e.getId());
		try {
			inicializarVariables();
			Long idPeticion = new Long(tr805e.getIdSchedule().substring(2,tr805e.getIdSchedule().indexOf("-")));
			PeticionLocal peticionLocal = peticionLocalHome.findByPetiNumero(idPeticion);
			Mensaje_estadoLocal mensaje_estadoLocal = this.buscaMensajeEstado(estadoEspera);
			
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			Mensaje_estado_tvLocal mensaje_estado_tvLocal = mensaje_estado_tvHome.create(idCorrelativoMensaje);
			mensaje_estado_tvLocal.setPeticion(peticionLocal);
			mensaje_estado_tvLocal.setFecha_envio (new Fecha().getTimestamp()) ;
			mensaje_estado_tvLocal.setDesc_error(tr805e.getId());
			mensaje_estado_tvLocal.setMensaje_estado(mensaje_estadoLocal);
			
			tr805e.setId(idCorrelativoMensaje.toString());
			
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(idPeticion,idAplicacion);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
			mensaje_estado_tvLocal.setCod_actividad_generadora(codActividad);
			
			RefrescarRecursosTVTOAMQ enviarMensaje = new RefrescarRecursosTVTOAMQ();
			enviarMensaje.enviarMensaje (tr805e) ;
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al ejecutar recibeActualizacionInventarioTV:"+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al ejecutar recibeActualizacionInventarioTV:"+e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al ejecutar recibeActualizacionInventarioTV:"+e);
		}
    
    }

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#enviaActivarDecosTarjetasTOA(java.lang.String, java.lang.String)
	 */
	public void enviaActivarDecosTarjetasTOA(String idActuacion, String idMensajePeticion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try{
			inicializarVariables();
			log.debug("Empiezo el proceso para la configuraciòn de los Decos en TOA");
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Peticion_atisLocalHome peticionAtisLocalHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			Mensaje_agenda_scLocalHome mensaje_agenda_home = (Mensaje_agenda_scLocalHome)HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
			
			ArrayList equipos = new ArrayList();
			boolean tieneErrores = false;
			TR801E tr801e = new TR801E();
						
			tr801e.setIdSourceSystem(sistemaAtiempo);
			tr801e.setIdSchedule(idActuacion);
			
			Agenda_scKey agendaSCKey = new Agenda_scKey(idActuacion);
			Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
			PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
			Collection decos = peticionLocal.getDeco_tarjeta();
			ArrayList decosEliminar = new ArrayList();
			
			for (Iterator decosIterator = decos.iterator(); decosIterator.hasNext();){
				Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decosIterator.next();
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey();
				
				
				if (decoTarjetaLocal.getEstado() != null && decoTarjetaLocal.getEstado().intValue() == estadoParNoOk
						&& decoTarjetaLocal.getCodigo_error() != null && decoTarjetaLocal.getCodigo_error().intValue() != ComunInterfaces.ERROR_OK_FALLIDA_OK){
					String mensajeError=decoTarjetaLocal.getMensaje_error();
					if(mensajeError!=null & mensajeError.length()>63){
						mensajeError=mensajeError.substring(0,63);
					}
					// Envio descripcion del error que se pierde al momento de borrar los decos
					tr801e.setDescripcionError(decoTarjetaLocal.getSerial_deco() + " "+ mensajeError);
					tieneErrores = true;
					if(decoTarjetaLocal.getEstado().intValue()==ComunInterfaces.estadoParNoOk &&
							(decoTarjetaLocal.getAccion().intValue()==ComunInterfaces.accionParActivar	
								||	decoTarjetaLocal.getAccion().intValue()==ComunInterfaces.accionParEliminar)){
						
						Deco_tarjetaLocalHome dtlc= (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
						 decosEliminar.add(decoTarjetaLocal);
						
					}
				}				
			}
			
			for (Iterator iter = decosEliminar.iterator(); iter.hasNext();) {
				Deco_tarjetaLocal dtl = (Deco_tarjetaLocal) iter.next();
				try { 
				log.debug("Deco a eliminar "+dtl.getSerial_deco()+" tarjeta "+dtl.getSerial_tarjeta());
				 dtl.remove();
		        
				}catch (Exception e) {
					log.error("Error al eliminar el decoTarjeta "+dtl.getPrimaryKey(),e);
				}
			}
				
			if (tieneErrores){
				tr801e.setResponse("ERROR");
				tr801e.setError("1");
				tr801e.setErrorMessage("La petición presentó un error en el proceso");
			}else{
				tr801e.setResponse("OK");	
				tr801e.setError("0");
				tr801e.setErrorMessage("");
			}
			String idAux [] = idMensajePeticion.split("-");
			Long idMensajeAgenda = new Long(idAux [1]);
			Mensaje_agenda_scLocal mensaje_agenda_local = mensaje_agenda_home.findByPrimaryKey(new Mensaje_agenda_scKey (idMensajeAgenda));
			tr801e.setApptNumber(mensaje_agenda_local.getApptNumber());
//			mensaje_agenda_local.setDesc_error(tr801e.getDescripcionError());
			tr801e.setId(idAux [0]);
			tr801e.setDestination(sistemaAgendaSC);
			tr801e.setSource(sistemaAtiempo);
			tr801e.setInterfaz("ACT_RES_ACTIVACION");
			tr801e.setVersion("1.0");
			mensaje_agenda_local.setReintentos(new Long(0));
			Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			
			GestionServiciosActivar gestionServiciosVariado = new GestionServiciosActivar();
			String repuestaInBound = gestionServiciosVariado.servicioInBound(tr801e,idAplicacion, mensaje_agenda_local);
			
			log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
			
			if(repuestaInBound != null){
				mensaje_agenda_local.setReintentos(new Long(0));
				gestionServiciosVariado.servicioOutBound(tr801e,idAplicacion, mensaje_agenda_local);
			}
			
			log.debug("Se ha enviado con exito la tr-801-e, quedo en espera de recibir un cierre de actuación....");
		}catch(NamingException ex){
			log.debug("Error instanciando el bean en el envío de activar decos tarjetas:" + ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error buscando elementos en el envío de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#enviaActivarModemTOA(java.lang.String, java.lang.String)
	 */
	public void enviaActivarModemTOA(String idActuacion, String idMensajePeticion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try{
			inicializarVariables();
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			log.debug("entro a generar la tr802e para la actuacion: "+idActuacion +" y id mensaje: "+idMensajePeticion);
			Peticion_atisLocalHome peticionAtisLocalHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome)HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome)HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			Mensaje_agenda_scLocalHome mensaje_agenda_home = (Mensaje_agenda_scLocalHome)HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
			
			boolean tieneErrores = false;
			ArrayList equipos = new ArrayList();
			TR802E tr802e = new TR802E();
			String idModem = new String();
			String codeMaterial = new String();
			String error = new String();
			String descError = new String();
			
			Agenda_scKey agendaSCKey = new Agenda_scKey(idActuacion);
			Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
			PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
			
			//Obtención de la actividad
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaSCLocal.getPeti_numero(),idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
							
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
			
			/**
			 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
			 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
			 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
			 * 
			 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
			 * tabla bintegrada, y se deben reconvertir a + o /.
			 */
			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(agendaSCLocal.getPeti_numero(), codActividad,idCorrelativo,null);

			Mensaje_estado_baKey mensajeEstadoBaKey;
			Mensaje_estado_baLocal mensajeEstadoBALocal;
			
			tr802e.setIdSourceSystem(sistemaAtiempo);
			tr802e.setIdSchedule(idActuacion);
			
			Collection modems = peticionLocal.getModem();
			
			log.debug("obtengo informaciòn de Modem");
			ModemLocal modemLocal = null;
			for (Iterator modemsIterator = modems.iterator(); modemsIterator.hasNext();){
				modemLocal = (ModemLocal) modemsIterator.next();

				ModemKey modemKey = (ModemKey)modemLocal.getPrimaryKey();
				idModem = modemKey.serial;
				
				//Se cambia la obtención del mensaje ya no de modelo si no del campo codigo material 
				codeMaterial = modemLocal.getCodigo_material();
				
				if (modemLocal.getAccion().intValue() != ComunInterfaces.accionModemConfigurado){
					tieneErrores = true;
					break;
				}
			}
			
			if (tieneErrores){
				tr802e.setResponse("ERROR");
				tr802e.setError(error);
				tr802e.setErrorMessage(descError);
				tr802e.setDescripcionError("Error en la configuracion del modem");
				tr802e.setModem_serial(idModem);
				tr802e.setMaterial_code(codeMaterial);

				actDto.setObservacion("El modem "+idModem+" presentó errores en la configuración: "+descError, true);
				
				Mensaje_estadoLocal mensajeError=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
			//mensajeEstadoBALocal.setMensaje_estado(mensajeError);
				
				actividadEJB.grabarSinTerminar(actDto);
				
			}else{
				log.debug("No hay errores para reportar para el modem con serial: "+idModem + " y codigo: "+codeMaterial);
				tr802e.setResponse("OK");	
				tr802e.setDescripcionError("");
				tr802e.setError("0");
				tr802e.setErrorMessage("");
				tr802e.setModem_serial(idModem);
				tr802e.setMaterial_code(codeMaterial);
				
				if(mensaje_estadoLocalHome==null)
					mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeOK=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
			//mensajeEstadoBALocal.setMensaje_estado(mensajeOK);
				
				actividadEJB.grabarSinTerminar(actDto);
			}
			
			String idAux [] = idMensajePeticion.split("-");
			Long idMensajeAgenda = new Long(idAux [1]);
			Mensaje_agenda_scLocal mensaje_agenda_local = mensaje_agenda_home.findByPrimaryKey(new Mensaje_agenda_scKey (idMensajeAgenda));
			tr802e.setApptNumber(mensaje_agenda_local.getApptNumber());
			//mensaje_agenda_local.setDesc_error(tr802e.getDescripcionError());
			tr802e.setId(idAux [0]);
			tr802e.setDestination(sistemaAgendaSC);
			tr802e.setSource(sistemaAtiempo);
			tr802e.setInterfaz("RES_CONFIG_MODEM");
			tr802e.setVersion("1.0");
			mensaje_agenda_local.setReintentos(new Long(0));
			
			if(modemLocal != null){
				modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemConfiguradoSOA).shortValue()));
			}
			
			GestionServiciosActivarBA gestionServiciosVariado = new GestionServiciosActivarBA();
			String repuestaInBound = gestionServiciosVariado.servicioInBound(tr802e,idAplicacion, mensaje_agenda_local);
			log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
			if(modemLocal != null){
				modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemConfiguradoTOA).shortValue()));
			}
			if(repuestaInBound != null){
				mensaje_agenda_local.setReintentos(new Long(1));
				gestionServiciosVariado.servicioOutBound(tr802e,idAplicacion, mensaje_agenda_local);
			}
			if(modemLocal != null){
				modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemConfigurado).shortValue()));
			}
			Mensaje_estado_baLocal mensaje_estado_ba;
			   try {
			   	log.debug("Se realiza parte final del proceso");
			   	if(mensajeEstadoBaLocalHome==null)
					mensajeEstadoBaLocalHome=(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			   	if(mensaje_estadoLocalHome==null)
					mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			    mensaje_estado_ba = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(idAux [0])));
			    Mensaje_estadoLocal mensajeOK=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
			    mensaje_estado_ba.setMensaje_estado(mensajeOK);
			    df = new SimpleDateFormat ("yyyy/MM/dd") ;
			    mensaje_estado_ba.setFecha_cierre(df.format (new java.util.Date ()));
			   } catch (FinderException e1){
			    log.error("No se encontro el mensaje Estado BA local con id "+idMensajePeticion,e1);
			   }
			
			log.debug("Se ha enviado con exito la tr-802-e, quedo en espera de recibir un cierre de actuación....");
		}catch(NamingException ex){
			log.debug("Error instanciando el bean en el envío de activar modems:" + ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error buscando elementos en el envío de activar modems: " + ex);
			ex.printStackTrace();
		}catch(TnProcesoExcepcion ex){
			log.debug("Error Ubicando actividad en el envío de activar modems: " + ex);
			ex.printStackTrace();
		}
	}
	
	 private Mensaje_estado_tvLocal buscaMensajeEstadoTv (String correlativo)
	    {
	        try
	        {
	        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
	            Mensaje_estado_tvKey key = new Mensaje_estado_tvKey (new Long (correlativo)) ;
	            
	            Mensaje_estado_tvLocal mensajeEstadoTv = msgEstadoTvLocalHome.findByPrimaryKey (key) ;
	            
	            return (mensajeEstadoTv) ;
	        }
	        catch (FinderException fex)
	        {
	        	log.debug("No se encontro el MensajeEstadoTv de llave=" + correlativo + " Exception: " + fex);
	            return (null) ;
	        }catch (NamingException e){
				log.error(" Creacion de Local Home Nulls",e);
				return (null) ;
	        }
	        
	    }
		

	private void insertarCausalesCnaPeticion(PeticionLocal peticionLocal, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, CreateException, FinderException 
	{
//		PeticionLocal peticionLocal=mensajeEstadoBaLocal.getPeticion();
		PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
		if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!=estadoPeticionEnCurso)
		{
			log.info("En reversa no se almacenan Quiebres Automáticos.PetAtiempo:"+peticionKey.peti_numero);
			return;
		}
		Fecha fecha=new Fecha();
		PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		DBManager manager=new DBManager ();
		manager.setDataSource (DBManager.JDBC_VPISTBBA);
		if(usuarioHome==null)
			usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		if(catalogo_causalHome==null)
			catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		if(estado_psHome==null)
			estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		if(estado_ps_peticionHome==null)
			estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
		if(causal_peticionHome==null)
			causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);


		ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
		ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
		Long idAct=actividadSessionLocal.getIdActividad(cod_actividad,new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
		if(idAct==null)
			return;								
		
		log.debug("Estoy insertando los causales de CNA para la peticion:"+peticionKey.peti_numero);
		for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
			Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
			Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
			Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
			Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
			if(peticionesDelegate.pasaPSyOcXActividad(peticionKey.peti_numero,producto_servicioKey.ps_id,operacion_comercialKey.opco_id,idActividadFlujo))
			{
				//Quiere decir que tengo que asociarle la causal a los ps que invocaron esta actividad.

				Collection listaEstadoPsPet=producto_servicio_peticionLocal.getEstado_ps_peticion();
				if(listaEstadoPsPet.size()>0)
				{	
				
					Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(codCausal);
					Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
				
					//Tengo una causa asociada tengo que actualizar
					Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPsPet.iterator().next();
					estado_ps_peticionLocal.setCod_actividad(idAct);
					estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
					estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
					estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());

					Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
					Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
					long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
					
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
				else
				{
				
					Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(codCausal);
					Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
				
					long correlativo=manager.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
					Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
					estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
					estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
					estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					estado_ps_peticionLocal.setCod_actividad(idAct);

				
					Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
					Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
					long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
					
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
			}
		}
	}

	private boolean getEstadoMultipleMensajes(Mensaje_estado_baLocal mensajeEstadoLineaLocal, Integer estado)
	{
		
		Mensaje_estado_baLocal mensaje_estado_baLocal2 = null;  
		Mensaje_estado_baKey mensaje_estado_baKey = (Mensaje_estado_baKey) mensajeEstadoLineaLocal.getPrimaryKey();
       
	   try
	   {
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoLineaLocal.getPeticion().getPrimaryKey();
			//Arreglo de registros de peticion pendientes
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			Collection estadoMensajes = mensajeEstadoBaLocalHome.findEstadoPeticion(peticionKey.peti_numero, estado);
	
			//Si que da el 1, siempre sera el ultimo
			log.debug("El numero de mensajes en estado espera es:" + estadoMensajes.size());
			if(estadoMensajes.size() == 1){
		
				for (Iterator iter= estadoMensajes.iterator();iter.hasNext();){
			
					mensaje_estado_baLocal2 = (Mensaje_estado_baLocal) iter.next();
					Mensaje_estado_baKey mensaje_estado_baKey2 = (Mensaje_estado_baKey) mensaje_estado_baLocal2.getPrimaryKey();
				//Comparacion de corralativos de mensajes
					log.debug("Correlativo1:" + mensaje_estado_baKey2.correlativo);
					log.debug("Correlativo2:" + mensaje_estado_baKey.correlativo);
					if(mensaje_estado_baKey2.correlativo.longValue() == mensaje_estado_baKey.correlativo.longValue())
					{
						log.debug("Es igual");
						return true;
					}
					else
					log.debug("NO Es igual");
			
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
	   catch (NamingException e) {
	   	log.debug(" Creacion de Local Home Nulls");
	   	return false;
	   }
	}
	
	public void enviaRefrescarRecursosTV(TR805S tr805s) throws ATiempoAppEx{

		log.debug("Se ingresa a enviaRefrescarRecursosTV con id:"+tr805s.getId());
		
		try {
			inicializarVariables ();
			Mensaje_estado_tvLocal mensaje_estado_tvLocal = this.buscaMensajeEstadoTv(tr805s.getId());
			Mensaje_estadoLocal mensaje_estadoLocal = mensaje_estado_tvLocal.getMensaje_estado();
			Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensaje_estadoLocal.getPrimaryKey();
			
			if(mensaje_estadoKey.cod_estado.intValue() == estadoEspera){
				PeticionLocal peticionLocal = mensaje_estado_tvLocal.getPeticion();
				PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
				
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
				
				String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
				String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
								
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);

				idCorrelativo=idCorrelativo.replaceAll("%2B","+");
				idCorrelativo=idCorrelativo.replaceAll("%2F","/");
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
				Mensaje_agenda_scLocal mensaje_agenda_local = almacenarMensajeAgendaSC(new Long(tr805s.getId()), tr805s.getIdSchedule(), tr805s.getApptNumber(), actDto, peticionKey);
				tr805s.setId(mensaje_estado_tvLocal.getDesc_error());
				
				Collection errostoa = tr805s.getError();
				String mensajeError = null;
				for(Iterator iter = errostoa.iterator();iter.hasNext();){
					ErrorRefreshTOA errors = (ErrorRefreshTOA)iter.next();
					mensajeError = errors.getErrorcodemessage();
				}
				
				if(mensajeError != null){
					String mensajeAux [] = mensajeError.split("-");
					
					co.com.telefonica.atiempo.vpistbba.TOA.GestionRefrescarRecursosTV gestionServiciosVariado = new co.com.telefonica.atiempo.vpistbba.TOA.GestionRefrescarRecursosTV();
					String repuestaInBound = gestionServiciosVariado.servicioInBound(tr805s,idAplicacion, mensaje_agenda_local, mensajeAux[0], mensajeError);
					tr805s.setId(mensaje_estado_tvLocal.getDesc_error());
					
					log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
					if(repuestaInBound != null){
						mensaje_agenda_local.setReintentos(new Long(1));
						gestionServiciosVariado.servicioOutBound(tr805s,idAplicacion, mensaje_agenda_local,mensajeAux [0]  );
					}
					
				}
			}
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	
	private Producto_servicio_peticionLocal buscaPsPetTipoPc (PeticionLocal peticion)
    {
        long idPeticion = ((PeticionKey) peticion.getPrimaryKey ()).peti_numero.longValue () ;
        
        Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
        
        while (iterPss.hasNext ())
        {
            Producto_servicio_peticionLocal productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
            
            Producto_servicioLocal productoServicio = productoServicioPeticion.getProducto_servicio () ;
            
            Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
            
            Familia_producto_servicioLocal familiaPS = productoServicio.getFamilia_producto_servicio () ;
            
            Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
            
            long idFamilia = familiaPSKey.faps_id.longValue () ;
            
            // busca si es el PS tipo PC
            if (idFamilia == familiaPcTV)
            {
                log.debug ("peticion: " + idPeticion + " ps tipo PC "  + productoServicioKey.ps_id) ;
                
                // paramos cuando encontramos el primer PS tipo PC (en teoria deberia haber 1 solo)
                return (productoServicioPeticion) ;
            }
        }
        
        // no se encontro el PS tipo PC: la rutina llamante decide si es un error o no
        return (null) ;
    }
    
	private Mensaje_estadoLocal buscaMensajeEstado (int llave)
    {
        try
        {
    		Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
            Mensaje_estadoKey key = new Mensaje_estadoKey (new Integer (llave)) ;
            
            Mensaje_estadoLocal mensajeEstado = msgEstadoLocalHome.findByPrimaryKey (key) ;
            
            return (mensajeEstado) ;
        }
        catch (FinderException fex)
        {
        	log.debug("No se encontro el MensajeEstado de llave=" + llave + " Exception: " + fex);
        	return (null) ;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			return (null) ;
        }
        
    }
    
	public InfoPostVentaTV recuperaInfoTvPostVenta(Long nroPeticion) throws ATiempoAppEx
	{
		try
		{
			boolean esBajaCompleta=false;
			boolean esBajaDeco=false;
			boolean esTraslado=false;
			int cantidadParBajaSTD=0;
			int cantidadParBajaPVR=0;
			int cantidadParBajaHD=0;
			boolean tieneSTD=false;
			boolean tienePVR=false;
			boolean tieneHD=false;

			
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			InfoPostVentaTV infoPostVentaTV=new InfoPostVentaTV(nroPeticion.longValue());
			infoPostVentaTV.setPeticionAt(nroPeticion.longValue());
			int faps_id = 0;
			String opco_tipo = null;
			String opco_tras = null;
			Operacion_comercialLocal operacion_comercialLocal=null;
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal ps=(Producto_servicio_peticionLocal) iterator.next();
				Familia_producto_servicioKey familia_producto_servicioKey=ps.getFamiliaKey();
				Producto_servicioKey producto_servicioKey=ps.getPsKey();
				operacion_comercialLocal=ps.getOperacion_comercial();
				//Primero tengo que determinar si es una baja completa
				faps_id = familia_producto_servicioKey.faps_id.intValue();
				opco_tipo = operacion_comercialLocal.getOpco_tipo();
				
				//TODO: Defecto 00035678, Raúl Ernesto Triviño - Ajustar para que no tenga en cuenta cuando es un traslado
				//TODO: Correctivo, Raúl Ernesto Triviño Alvarado - Ajuste para que tenga en cuenta los decos HD
				if((faps_id==familiaPcTV) && opco_tipo!=null && opco_tipo.equals("B") 
						&& operacion_comercialLocal.getOpco_nombre().indexOf("TRASLADO") == -1){
					esBajaCompleta=true;
					infoPostVentaTV.setOpCoDeco(ps.getIdOperacionComercial().longValue());
				}
				//End TODO
				
				if(faps_id==familiaDecoTV && opco_tipo!=null && (opco_tipo.equals("B") || (opco_tipo.equals("BCP"))))
				{
					esBajaDeco=true;
					infoPostVentaTV.setOpCoDeco(ps.getIdOperacionComercial().longValue());
					cantidadParBajaSTD++;
//					TODO PVR se agrego el seteo de la variable tipodecodificador para validar en la baja
					infoPostVentaTV.setTipodecodificador(desHCDecoSTD);
					tieneSTD=true;
					
				}
				//TODO PVR se agrego el seteo de la variable tipodecodificador para validar en la baja
				if(faps_id==familiaDecoPVRTV && opco_tipo!=null && (opco_tipo.equals("B")|| (opco_tipo.equals("BCP"))))
				{
					esBajaDeco=true;
					infoPostVentaTV.setOpCoDeco(ps.getIdOperacionComercial().longValue());
					infoPostVentaTV.setTipodecodificador(desHCDecoPVR);
					cantidadParBajaPVR++;
					tienePVR=true;
				}
				
				if(faps_id==familiaDecoHDTV && opco_tipo!=null && (opco_tipo.equals("B")|| (opco_tipo.equals("BCP"))))
				{
					esBajaDeco=true;
					infoPostVentaTV.setOpCoDeco(ps.getIdOperacionComercial().longValue());
					infoPostVentaTV.setTipodecodificador(desHCDecoHDTV);
					cantidadParBajaHD++;
					tieneHD=true;
				}
				
				
				opco_tras = operacion_comercialLocal.getOpco_tras();
				if(opco_tras!=null && opco_tras.equals("T"))
					esTraslado=true;
			}
			if(tienePVR & tieneSTD ){
			
				infoPostVentaTV.setEsBajaPVRYSTD(true);
			}
			
			for(Iterator iterator=peticionLocal.getDeco_tarjeta().iterator();iterator.hasNext();)
			{
				Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal) iterator.next();
				Deco_tarjetaKey deco_tarjetaKey=(Deco_tarjetaKey) deco_tarjetaLocal.getPrimaryKey();
				
				if(opco_tipo!=null && opco_tipo.equals("B") && operacion_comercialLocal.getOpco_nombre().indexOf("TRASLADO") == -1)
					infoPostVentaTV.addDecoTarjeta(deco_tarjetaKey.id_deco,deco_tarjetaKey.id_tarjeta,deco_tarjetaLocal.getDeco_reference(),deco_tarjetaLocal.getDeco_marca(),deco_tarjetaLocal.getSerial_deco());
				else
					infoPostVentaTV.addDecoTarjeta(deco_tarjetaKey.id_deco,deco_tarjetaKey.id_tarjeta,deco_tarjetaLocal.getDeco_reference());
			}
			infoPostVentaTV.setEsBajaCompleta(esBajaCompleta);
			infoPostVentaTV.setEsBajaDeco(esBajaDeco);
			infoPostVentaTV.setEsTraslado(esTraslado);
			infoPostVentaTV.setCantidadParesBajaPVR(cantidadParBajaPVR);
			infoPostVentaTV.setCantidadParesBajaSTD(cantidadParBajaSTD);
			infoPostVentaTV.setCantidadParesBajaHD(cantidadParBajaHD);
			infoPostVentaTV.setCantidadParesBaja(cantidadParBajaPVR+cantidadParBajaSTD+cantidadParBajaHD);
			if(esBajaCompleta)
				infoPostVentaTV.setCantidadParesBaja(infoPostVentaTV.getListaParesEnCas().size());
			return infoPostVentaTV;
		}
		catch (FinderException e)
		{
			log.debug("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
		
	}	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionCierreActuacion(co.com.telefonica.atiempo.interfaces.atiempo.tr811s)
	 */
	public void recepcionCierreActuacionTOA(TR811S tr811s) throws ATiempoAppEx {
		try{
			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			String idPeticion = tr811s.getIdSchedule().substring(2,tr811s.getIdSchedule().indexOf("-"));
			
			HashMap estadosHomologados = new HashMap();
			Estado_homologacionLocalHome estHomLocalHome = (Estado_homologacionLocalHome)HomeFactory.getHome(Estado_homologacionLocalHome.JNDI_NAME);
			PeticionesDelegate peticiones = new PeticionesDelegate();
			String codigoApp = peticiones.recuperarParametroFromPropertiesBD("EST_AGENDSC");
			Collection estados = estHomLocalHome.findByCodApp(codigoApp);
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
			
			
			boolean franqueoOK = false;
			boolean cierroActividad = true;
			boolean esPostventa = true;
			boolean tieneEquipoValido = false;
			boolean yaSeGuardoModem = false;
			boolean envioTr701EPGC = true;
			
			//Obtención de los tipos de equipo que se tendrán en cuanta para el envio de mensajes
			String materialesEquipos = VpistbbaConfig.getVariable("MATERIALES_EQUIPOS");
			String arrayMaterialesEquipos[] = materialesEquipos.split(",");
			
			String materialesDecos = VpistbbaConfig.getVariable("MATERIALES_DECOS");
			String arrayMaterialesDecos[] = materialesDecos.split(",");
			
			String materialesModems = VpistbbaConfig.getVariable("MATERIALES_MODEMS");
			String arrayMaterialesModems[] = materialesModems.split(",");
			
			String materialesCamara = VpistbbaConfig.getVariable("MATERIALES_CAMARA");
			log.debug("MATERIALES_CAMARA"+materialesCamara);
			String arrayMaterialesCamara[] = materialesCamara.split(",");
			
			Agenda_scLocal agendaSCLocal = null;
			
			String strObservaciones = "";
			//Obtención de la información basica del agendameinto
			try{
				String strQuiebres = "";
				
				if( tr811s.getBreaks().getBreakPairs() != null && tr811s.getBreaks().getBreakPairs() != null && tr811s.getBreaks().getBreakPairs().size() > 0 ){
					strQuiebres = this.recuperarQuiebresAgendaSc(tr811s.getBreaks().getBreakPairs());
					strObservaciones = this.recuperarObservacionesQuiebresAgendaSc(tr811s.getBreaks().getBreakPairs());
				}
				
				Agenda_scKey agendaSCKey = new Agenda_scKey(tr811s.getIdSchedule());
				agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
				//@idrincon  req 4990 gestion quiebres
				agendaSCLocal.setNombre_contratista(tr811s.getTechnician().getTechnicianName());
				agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
				agendaSCLocal.setCod_franqueo(tr811s.getPostageCode());
				agendaSCLocal.setQuiebre( strQuiebres );
				//fin modificacion
			}catch(FinderException ex){
				log.debug("No se encuentra el código de agendamiento, debe haber ocurrido un reagendamiento se procede a cerrar las actuaciones abiertas y crear esta");
				String idPeticionAux = tr811s.getIdSchedule().substring(2,tr811s.getIdSchedule().indexOf("-"));
				
				Collection agendaSCCollection = agendaSCLocalHome.findByPetiNumero(new Long(idPeticionAux));
				for (Iterator agendaSCIterator = agendaSCCollection.iterator(); agendaSCIterator.hasNext();){
					Agenda_scLocal agendaSCLocalAux = (Agenda_scLocal) agendaSCIterator.next();
					
					if (agendaSCLocalAux.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA){
						agendaSCLocalAux.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
					}
				}
				
				PeticionKey peticionKey = new PeticionKey(new Long(idPeticionAux));
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				
				
				agendaSCLocal = agendaSCLocalHome.create(tr811s.getIdSchedule());
				agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
				agendaSCLocal.setPeti_numero(new Long(idPeticionAux));
				agendaSCLocal.setPeticion(peticionLocal);
				agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			}
			
			PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
			//@idrincon 
			Collection productoSrvicioPeticionCollection = peticionLocal.getProducto_servicio_peticion();
			//fin modificacion
				
			//Implementación del llamado de la actividad donde me encuentro 
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
			
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
			
			Collection estado_ps_peticion = null;
			
			/**
			 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
			 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
			 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
			 * 
			 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
			 * tabla bintegrada, y se deben reconvertir a + o /.
			 */
			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
//			AgendaServicioDelegate agendaServicioDelegate = new AgendaServicioDelegate();
//			agendaServicioDelegate.setearDatosActividad(actDto);
			//Verificación si viene el mensaje con código de franqueo exitoso
			Codigos_franqueo_ok_agd_scLocalHome codFranqueoOKLocalHome = (Codigos_franqueo_ok_agd_scLocalHome) HomeFactory.getHome(Codigos_franqueo_ok_agd_scLocalHome.JNDI_NAME);
			
//			
//		if (tr811s.getItAnswer() != null && tr811s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM)){
//				//Proceso para agregar un traslado
//				log.debug("Voy a ingresar los siguientes datos a la peticion: Area de traspaso:"+tr811s.getItComplement()+" y diagnóstico:"+tr811s.getItClosing());
//				Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
//				Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByDescripcion(tr811s.getItClosing()) ;
//				if(catalogCausa != null){
//					Catalogo_causalKey catalogo_causalKey= (Catalogo_causalKey) catalogCausa.getPrimaryKey();
//					insertarCausalesCnaPeticion(peticionLocal, actDto.getCodigoActividad(),catalogo_causalKey.cod_causal, actDto.getIdActividadFlujo());
//				}
//			}
//			
			//@idrincon req 4990 date 01/01/2011
			String codBandeja = null;
			//fin modificacion
			try{
				Codigos_franqueo_ok_agd_scKey codFranqueoOkKey = new Codigos_franqueo_ok_agd_scKey(tr811s.getPostageCode());
				Codigos_franqueo_ok_agd_scLocal codFranqueoOKLocal = codFranqueoOKLocalHome.findByPrimaryKey(codFranqueoOkKey);
				//@idrincon req 4990 date 01/01/2011
				codBandeja = codFranqueoOKLocal.getBandeja();
				//fin modificacion
				franqueoOK = true;
			}catch(FinderException ex){
				log.debug("El codigo de franqueo: "+tr811s.getPostageCode()+" no es considerado como exitoso, se valida si tiene marca y si es así se envía a configurar inventarios");
				franqueoOK = false;
			}
			
			if (agendaSCLocal.getEstado() != null &&
					(agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA || agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA)){
				//Ejecuto el proceso solo si viene el código de franqueo OK o si viene el codigo de franqueo mal pero la petición tiene marca
				if (franqueoOK){
					//Proceso de organización de cada uno de los materiales 
					
					//@idrincon req 4990 date 01/01/2011
					if(tr811s.getBreaks().getBreakPairs()!= null && tr811s.getBreaks().getBreakPairs() != null && tr811s.getBreaks().getBreakPairs().size() > 0){
						this.setQuiebrePcAgendaSc( tr811s.getBreaks().getBreakPairs(), peticionKey.peti_numero, productoSrvicioPeticionCollection, actDto.getCodigoActividad() );
					}
					//fin modificacion
					
					Collection materiales = tr811s.getInstalledInventory().getMaterials();
					this.recorerEquipos(materiales, arrayMaterialesEquipos, arrayMaterialesDecos, arrayMaterialesModems, arrayMaterialesCamara, listaEquipos, listaCamaras, listaDecos, listaModems, listaEquiposNoSerializados, tr811s);
					materiales = tr811s.getDeinstalledInventory().getMaterials();
					this.recorerEquipos(materiales, arrayMaterialesEquipos, arrayMaterialesDecos, arrayMaterialesModems, arrayMaterialesCamara, listaEquipos, listaCamaras, listaDecos, listaModems, listaEquiposNoSerializados, tr811s);
					materiales = tr811s.getCustomerInventory().getMaterials();
					this.recorerEquipos(materiales, arrayMaterialesEquipos, arrayMaterialesDecos, arrayMaterialesModems, arrayMaterialesCamara, listaEquipos, listaCamaras, listaDecos, listaModems, listaEquiposNoSerializados, tr811s);
					
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
				
				//no hay concordancia del tag de answer con la tr811s
				//if(tr811s.getItAnswer() == null || !tr811s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM)){
					
					//Si la operacion comercial es autoinstalacion no se aplica delta de modem
					boolean esOCAutoInstalacion = esAutoinstalacion(agendaSCLocal);
			        if(!esOCAutoInstalacion && listaModems != null && listaModems.size() > 0){
			        	insertarModem(listaModems,tr811s, peticionKey, estadosHomologados, actDto, esOCAutoInstalacion, peticionLocal, cierroActividad);
			        }
					
					
					//Se envía mensaje tr-017 para actualizar inventario de decos/tarjeta
					if (listaDecos != null && listaDecos.size()>0){
						log.debug("Voy a configurar y registrar decos para el agendamiento:"+tr811s.getIdSchedule());
						//Gestion de Quiebres y Novedades Peticiones en Vuelo LFMM
						RecursosTVDelegate recursosTVDelegate = new RecursosTVDelegate();
						if (!this.esPostventa(peticionLocal)){
							this.marcarNovedadQuiebrePeticionesVueloAgendaSC(agendaSCLocal.getPeti_numero(),listaDecos);
						}
						this.agendamientoDecosTR811(listaDecos, peticionLocal, tr811s, actDto, actividadEJB,true ,codBandeja);
						//cierroActividad = false;
					}else if(hacerDelta){//se eliminan todos los decos
						log.debug("No vienen decos, Voy a eliminar los decos para el agendamiento:"+tr811s.getIdSchedule());
						Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
						Collection decoTarjeta = peticionLocal.getDeco_tarjeta();
						ArrayList decoTarjetaEliminar = new ArrayList(); 
						for(Iterator decoTarjetaIterator = decoTarjeta.iterator(); decoTarjetaIterator.hasNext();){
							Deco_tarjetaLocal decoTarjetaAuxLocal = (Deco_tarjetaLocal)decoTarjetaIterator.next();
							Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaAuxLocal.getPrimaryKey();
							/*RQ.8595 - mfmendez*/
							Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
							Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
							
							/*RQ.8595 - mfmendez*/
							// Se borran datos de SAP del deco
							try{			
								Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_deco, peticionKey.peti_numero);
								Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
								infoSAPDeco.remove();
							} catch (FinderException e) {
								log.debug("No se encontraron Decos para deco con id: "+decoTarjetaKey.id_deco+". Y id de peticion: "+peticionKey.peti_numero);
							} catch (Exception e) {
								log.error("RecursosBABean: Se presento Error borrando los datos de SAP para un Deco. "+e);
							}
							// se borran datos de SAP de la tarjeta
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
							/*RQ.8595 - mfmendez*/
							decoTarjetaDTO = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, decoTarjetaDTO, decoTarjetaKey.id_tarjeta, peticionKey.peti_numero);
							decoTarjetaDTO = this.setDatosSAPDeco(deco_tar_inf_sapLocalHome, decoTarjetaDTO, decoTarjetaKey.id_deco, peticionKey.peti_numero);
							/*FIN - RQ.8595 - mfmendez*/
							decoTarjetaEliminar.add(decoTarjetaDTO);
						}
						//Se envia mensaje tr-017-e para desactivacion de decos
						if(decoTarjetaEliminar != null && !decoTarjetaEliminar.isEmpty()){
							String idAgendaSC = tr811s.getIdSchedule()+"@"+tr811s.getId()+"@TR811"+"@"+true;
							this.enviaConfiguracionServiciosTVAgendaSC(peticionKey.peti_numero.longValue(), decoTarjetaEliminar, true, idAgendaSC);
							//cierroActividad = false;
						}
					}
					
					if(listaCamaras != null && listaCamaras.size()>0){
						log.debug("Voy a configurar y registrar cámaras para el agendamiento:"+tr811s.getIdSchedule());
						CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
						Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
						for (int i = 0; i < listaCamaras.size(); i++) {
							TR811SMaterials materialCamara = (TR811SMaterials)listaCamaras.get(i);
							CamaraKey camaraKey = new CamaraKey(new Long(idPeticion),materialCamara.getEquipmentSerialNumber());
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
													
													if(materialCamara.getCodeMaterial() != null){
														camaraLocal.setPosDocSap(new Integer(materialCamara.getCodeMaterial()));
													}else{
														camaraLocal.setPosDocSap(new Integer("0"));
													}
													
													camaraLocal.setNumMaterialSap(materialCamara.getMaterial());
													camaraLocal.setCentrCostSap(materialCamara.getCostCenter());
													camaraLocal.setFlagPetCurso(ComunInterfaces.FLAG_EQUIPO_PETICION);
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
					
					
					if (listaEquipos != null && listaEquipos.size()>0){
						log.debug("Voy a configurar y registrar equipos para el agendamiento:"+tr811s.getIdSchedule());							
						Elemento_agenda_scLocalHome elementoAgendaSC = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
						Tipo_Eq_ElementoLocalHome tipoEqElementoLocalHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
						Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
						Elemento_PeticionLocalHome elementoPeticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
						
						for(int i = 0; listaEquipos.size() > i; i++){
							TR811SMaterials equipo = (TR811SMaterials)listaEquipos.get(i);
							
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
											
											if(equipo.getCodeMaterial() != null){
												elementoPeticionLocal.setPos_doc_sap(Integer.parseInt(equipo.getCodeMaterial()));
											}else{
												elementoPeticionLocal.setPos_doc_sap(0);
											}
											
											elementoPeticionLocal.setNum_material_sap(equipo.getMaterial());
											elementoPeticionLocal.setUnd_medida_sap(equipo.getMaterialUnitMeasure());
											elementoPeticionLocal.setCentr_cost_sap(equipo.getCostCenter());
											elementoPeticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
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
								log.debug("tr811s: ENTRO POR LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE.");
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
														log.debug("tr811s: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, ENCONTRO PS QUE ESTA EN PS_TIPO_EQ.");
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
									log.debug("tr811s: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE VA A INTENTAR GUARDAR EL EQUIPO EN ELEMENTO_PETICION.");
									
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
									
									log.debug("tr811s: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE VA A INTENTAR CREAR EL REGISTRO EN LA TABLA.");
									Elemento_PeticionLocal elementoPeticionLocal = elementoPeticionLocalHome.create(serialEq,peticionLocal,noTel, accion,tipoEquipo,tipoInventario,elemento,psId);
									
									log.debug("tr811s: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE CREO EL REGISTRO EN LA TABLA Y SE VAN A GUARDAR LOS DEMAS DATOS.");
									if(equipo.getBrandEquipment() != null)
										elementoPeticionLocal.setMarca(equipo.getBrandEquipment());
									
									if(equipo.getModelEquipment() != null)
										elementoPeticionLocal.setModelo(equipo.getModelEquipment());
									
									if(equipo.getCodeMaterial() != null){
										elementoPeticionLocal.setPos_doc_sap(Integer.parseInt(equipo.getCodeMaterial()));
									}else{
										elementoPeticionLocal.setPos_doc_sap(0);
									}
									
									elementoPeticionLocal.setNum_material_sap(equipo.getMaterial());
									elementoPeticionLocal.setUnd_medida_sap(equipo.getMaterialUnitMeasure());
									elementoPeticionLocal.setCentr_cost_sap(equipo.getCostCenter());
									elementoPeticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
									
									tieneEquipoValido = true;
								}
							}
							/*FIN mfmendez - Cambio solicitado por padilla para que guarde todos los equipos que lleguen en el cirre*/
						}
					}else{//si no llegan equipos en la tr-711-s se eliminan los actuales de la base de datos
						log.debug("No vienen equipos, Voy a eliminar los equipos para el agendamiento:"+tr811s.getIdSchedule());
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
							log.debug("No se encontraron equipos para el agendamiento:"+tr811s.getIdSchedule());
						}
						
					}
					
					for (Iterator iter = listaEquiposNoSerializados.iterator(); iter.hasNext();) {
						TR811SMaterials materialTR = (TR811SMaterials) iter.next();
						log.debug("Voy a configurar y registrar equipos no serializados para el agendamiento:"+tr811s.getIdSchedule());
						DBManager manager;
						manager=new DBManager();
						manager.setDataSource(DBManager.JDBC_VPISTBBA);
						Long bi_id=new Long(manager.seqNextValLong("VPI_ELEM_NO_SERIAL_SEQ"));
						ElementoNoSerializadoLocalHome ensl = (ElementoNoSerializadoLocalHome)HomeFactory.getHome(ElementoNoSerializadoLocalHome.JNDI_NAME);
						ElementoNoSerializadoLocal elemento = ensl.create(bi_id,peticionLocal);
						elemento.setPeticion(peticionLocal);
						Integer cantidad = materialTR.getMaterialAmount();
						if(cantidad!=null){
							elemento.setCantidad(new Long(cantidad.longValue()));
						}
						elemento.setCentrCostSap(materialTR.getCostCenter());
						elemento.setMarca(materialTR.getBrandEquipment());
						elemento.setModelo(materialTR.getModelEquipment());
						elemento.setNumMaterialSap(materialTR.getMaterial());
						String posDocSap= "0";
						if(posDocSap!=null && !posDocSap.equals("")){
							elemento.setPosDocSap(new Integer(posDocSap));
						}
						elemento.setUndMedidaSap(materialTR.getMaterialUnitMeasure());
					}
					
					//}correspodne al if de answers
					
					//Cierro la actuación
					agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
					peticionLocal.setEstado_agend_sc(new Integer(ComunInterfaces.ESTADO_AGENDA_SC_SIN_MARCA));
					
					if (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
						if(strObservaciones.length() > 1000){
							strObservaciones = strObservaciones.substring(0, 999);
						}
						actDto.setObservacion(strObservaciones);
					}
					
					//Cierro la actividad solamente si no ejecute una actualización de decos y si estoy en control instalación 
					if (cierroActividad && (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)||codActividad.equals(ComunInterfaces.COD_ACT_PGACS))){
						//idrincon @req 4990
						log.debug("cierre actividad "+ cierroActividad +"actividad "+codActividad);
						
						RecursosBADelegate recursosDelegate = new RecursosBADelegate();
						boolean dePgacsAAutoinst = recursosDelegate.validarEnvioTrEnPGACS( tr811s.getIdSchedule() );
						if(codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA) && !dePgacsAAutoinst){
							this.cierrepeticion(actDto,actividadEJB, codBandeja, codActividad);
						}else{
							if(codActividad.equals(ComunInterfaces.COD_ACT_PGACS) && dePgacsAAutoinst){
								this.cierrepeticion(actDto,actividadEJB, codBandeja, codActividad);
							}
						}
						
					}else{
						if (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
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
						if (tr811s.getPostageCode().equals(codigos[i])){
							esCancelacion=true;
							break;
						}
					}
					
					if (esCancelacion){
						log.debug("Se cancela la actuación pues el mensaje trae un código de franqueo de cancelación, Se debe derivar a PGC : Franqueo: "+tr811s.getPostageCode());
						actDto.setObservacion("Se cancela la actuación pues el mensaje trae un código de franqueo de cancelación: Franqueo: "+tr811s.getPostageCode()
								+" Por favor revise, se deriva a bandeja PGC");
						agendaSCLocal.setEstado(new Integer(ACTUACION_CANCELADA));
						
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
						actividadEJB.terminar(actDto);
					}else{
						log.debug("Se cierra la actuación pues el mensaje trae un código de franqueo no existoso: Franqueo: "+tr811s.getPostageCode());
						actDto.setObservacion("Se cierra la actuación pues el mensaje trae un código de franqueo no existoso: Franqueo: "+tr811s.getPostageCode()
								+" Por favor revise, y realice la actualización de inventarios de forma manual");
						agendaSCLocal.setEstado(new Integer(ACTUACION_CERRADA));
					}
				}
			}else{
				log.debug("El mensaje de cierre con ID: "+tr811s.getId()+" no se tiene en cuenta, porque la actuación:"+tr811s.getIdSchedule()+" se encuentra cerrada");
				actDto.setObservacion("El mensaje de cierre con ID: "+tr811s.getId()+" no se tiene en cuenta, porque la actuación:"+tr811s.getIdSchedule()+" se encuentra cerrada");
			}
			//REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA 24/07/2014
			try {
				
				//instanciamos el local home
				Producto_servicio_peticionLocalHome  pss = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				//traemos los PS's de la peticion
				Collection psPeticion = pss.findAllByPetiNumero(actDto.getNumeroPeticion());
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				//recorremos el arreglo de PS's
				for(Iterator iter = psPeticion.iterator();iter.hasNext();)
				{
					//instanciamos cada ps del itrerator en la clase local
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					//validamos que la operacion comercial sea una alta o un traslado
					//log.debug("Averias de infancia: se valida si es una alta o un traslado");
					log.debug("La operacion comercial "+producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo()+
							"del ps "+producto_servicio_peticionLocal.getPsId()+ "de la peticion "+actDto.getNumeroPeticion());
					
//						//funcion que inserta en la nueva tabla el registro para llevar control y obtener peticiones de infancia
//						
						insertarAltaTraslado(actDto);
						break;
				//	}	
				}
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al consultar los PS's de la peticion "+e.toString());
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al inicar el Producto_servicio_peticionLocalHome "+e.toString());
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
		}catch(CreateException ex){
			log.error("Error en creación de la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		} catch (EJBException ex) {
			log.error("Error en creación de la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		} catch (RemoveException ex) {
			log.error("Error en eliminación de registro en la recepción del cierre de actuación " , ex);
			ex.printStackTrace();
		}
	}	
	/**
	 * @param materiales
	 * @param arrayMaterialesCamara
	 * @param arrayMaterialesModems
	 * @param arrayMaterialesDecos
	 * @param arrayMaterialesEquipos
	 * @param listaModems
	 * @param listaDecos
	 * @param listaCamaras
	 * @param listaEquipos
	 * @param listaEquiposNoSerializados
	 * @param tr811s
	 * 
	 */
	private void recorerEquipos(Collection materiales, String[] arrayMaterialesEquipos, String[] arrayMaterialesDecos, String[] arrayMaterialesModems, String[] arrayMaterialesCamara, ArrayList listaEquipos, ArrayList listaCamaras, ArrayList listaDecos, ArrayList listaModems, ArrayList listaEquiposNoSerializados, TR811S tr811s) {
		// TODO Apéndice de método generado automáticamente
		if (materiales != null){
			granFor: for(Iterator materialesIterator = materiales.iterator(); materialesIterator.hasNext();){
				boolean esEquipo = false;
				boolean esCamara = false;
				boolean esDeco = false;
				boolean esModem = false;
				TR811SMaterials material = (TR811SMaterials) materialesIterator.next();
				
				if (!esDeco && !esModem){
					for (int i=0; i<arrayMaterialesEquipos.length;i++){
						if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesEquipos[i]) != -1){							
							listaEquipos.add(material);
							esEquipo = true;
							log.debug("El agendamiento:"+tr811s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un equipo");
							continue granFor;
						}
					}
				}
				
				if (!esEquipo && !esModem){
					for (int i=0; i<arrayMaterialesDecos.length;i++){
						if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesDecos[i])  != -1){
							listaDecos.add(material);
							esDeco = true;
							log.debug("El agendamiento:"+tr811s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un deco");
							continue granFor;
						}
					}
				}
				
				if (!esEquipo && !esDeco){
					for (int i=0; i<arrayMaterialesModems.length;i++){
						if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesModems[i]) != -1){
							listaModems.add(material);
							esModem = true;
							log.debug("El agendamiento:"+tr811s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un modem");
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
							log.debug("El agendamiento:"+tr811s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es una cámara");
							continue granFor;
						}
					}
				}
				
				if (!esEquipo && !esDeco && !esModem){
					listaEquiposNoSerializados.add(material);
				}
			}
		}
	}

	/**
	 * @param actDto
	 * @param actividadEJB
	 * @param codBandeja
	 * @param codActividad
	 */
	private void cierrepeticion(ActividadEJBDTO actDto, IActividadEJB actividadEJB, String codBandeja, String codActividad) {
		// TODO Apéndice de método generado automáticamente
		try {
			AgendaServicioDelegate agendaServicioDelegate = new AgendaServicioDelegate();
			agendaServicioDelegate.setearDatosActividad(actDto);
			String[] llaveWF = null;
			if (codBandeja != null){
				//TOA FASE III derivar a gestion abonados
				log.debug("codigo bandeja"+ codBandeja);
				llaveWF = codBandeja.split("-");
				if(llaveWF[1].toString().equals(ComunInterfaces.ACT_CONFIG_STB)){
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb,"CONFSTB");
				}
				if(codActividad.equals(ComunInterfaces.COD_ACT_PGACS))
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre,"S");
				
				actDto.setDato(llaveWF[0],llaveWF[1]);
			}
			actividadEJB.terminar(actDto);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al terminar la actividad"+ e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al terminar la actividad"+ e);
		}
	}

	public void marcarNovedadQuiebrePeticionesVueloAgendaSC(Long petiNumero, ArrayList listaDecosTarjetasAgendaSC) throws ATiempoAppEx{
		try
		{
			String novedadAuto = VpistbbaConfig.getVariable("NOVEDADAUTOTV");
			String quiebreAuto = VpistbbaConfig.getVariable("QUIEBREAUTOTV");
			if(novedadAuto==null)
				return;
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
			/*Conteo Decos de Agenda SC **************************/
			ArrayList listaDecos = new ArrayList();
			for (int i=0; i < listaDecosTarjetasAgendaSC.size(); i++){
				TR811SMaterials decosTarjetas = (TR811SMaterials)listaDecosTarjetasAgendaSC.get(i);				
				if (decosTarjetas.getTypeEquipment().indexOf("DECO")!=-1){
					listaDecos.add(decosTarjetas);
				}
			}				
			
			int contadorHDASC = 0;
			int contadorPVRASC = 0;
			int contadorSTDASC = 0;			
			int contadorTotalDecosASC = 0;
			//Creación de los elementos de Decos Tarjetas
			for (int j=0;j<listaDecos.size();j++){
				TR811SMaterials deco = (TR811SMaterials)listaDecos.get(j);							
				if (deco.getTypeEquipment().indexOf(desHCDecoSTD) != -1){
					contadorSTDASC += 1;
				}else if (deco.getTypeEquipment().indexOf(desHCDecoPVR) != -1){
					contadorPVRASC += 1;
				}else if (deco.getTypeEquipment().indexOf(desHCDecoHDTV) != -1){
					contadorHDASC += 1;
				}				
			}
			contadorTotalDecosASC = contadorSTDASC + contadorPVRASC + contadorHDASC;
			
			/*******************/
			Collection psPeticion=peticionLocal.getProducto_servicio_peticion();
			Iterator psPeticionIt=null;						
			String tipoDeco="";
			int contadorHD = 0;
			int contadorPVR = 0;
			int contadorSTD = 0;
			int contadorTotalDecosPedido = 0;
			for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
				Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
				if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
					contadorHD += 1;						
					log.debug("Tiene deco HD ");
				}else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV){
					contadorPVR += 1;						
					log.debug("Tiene deco PVR");
				}
				else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV){
					contadorSTD += 1;
					log.debug("Tiene deco STD");
				}/*else if(familia_producto_servicioKey.faps_id.intValue()== ComunInterfaces.familiaPcTV){
					contadorSTD += 1;
					log.debug("Tiene deco STD");
				}*/
			}			
			contadorTotalDecosPedido = contadorHD + contadorPVR + contadorSTD;
			/********************/
			if (contadorTotalDecosPedido == contadorTotalDecosASC ){
				if(contadorSTDASC != contadorSTD || contadorPVRASC != contadorPVR || contadorHDASC != contadorHD ){
					marcarNovedadPs(novedadAuto, peticionLocal);
				}
			}else{				
				if(contadorSTDASC > contadorSTD || contadorPVRASC > contadorPVR || contadorHDASC > contadorHD){
					//Novedad
					marcarNovedadPs(novedadAuto, peticionLocal);
					//marcarNovedadPs(peticionLocal, contadorSTDASC-contadorSTD, contadorPVRASC-contadorPVR
							//, contadorHDASC-contadorHD,psPeticion,listaDecosTarjetasAgendaSC);
				}else{
					marcarNovedadPs(quiebreAuto, peticionLocal);
				}
			}

		}
		catch(NamingException ne)
		{
			log.error("NamingException",ne);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,ne);
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}
	private void marcarNovedadPs(String codCausal, PeticionLocal peticionLocal) throws NamingException, CreateException, FinderException {
		Fecha fecha=new Fecha();
			
		UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
		Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
		ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
		ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
		Long idAct=actividadSessionLocal.getIdActividad(VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONTROLI"),new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
/*				if(idAct==null)
			return;*/	
		boolean genero = false;		
		log.debug("Entro al for para generar la novedad");					
		for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext() && !genero;)
		{
			Producto_servicio_peticionLocal ppss=(Producto_servicio_peticionLocal) iterator.next();
			//----if(ppss.getFamiliaKey().faps_id.intValue()!=ComunInterfaces.familiaPcTV)
			//----	continue;
			// -- Se cambiaron las condiciones para generar las novedades CR- 12278
			
			/*if(genero)return;*/
			log.debug("Valor de la familia "+ppss.getFamiliaKey().faps_id.intValue());
			if(ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoHDTV||ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoTV ||ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV
					|| ppss.getPsId().intValue()== ComunInterfaces.familiaPcTV ){
				genero = true;					
				Producto_servicioLocal producto_servicioLocal=ppss.getProducto_servicio();
				Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
				Operacion_comercialLocal operacion_comercialLocal=ppss.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				
				
				
				Collection listaEstadoPsPet=ppss.getEstado_ps_peticion();
				Long codigoCausal = new Long(codCausal);
				
				if(listaEstadoPsPet.size()>0)
				{	
					
					Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey( codigoCausal );
					Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
					
					//Tengo una causa asociada tengo que actualizar
					Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPsPet.iterator().next();
					estado_ps_peticionLocal.setCod_actividad(idAct);
					estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
					estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
					estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					
					Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
					Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
					long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
					
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
				else
				{
					
					Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey( codigoCausal );
					Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
					
					long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
					Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),ppss.getProducto_servicio(),ppss);
					estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
					estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
					estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					estado_ps_peticionLocal.setCod_actividad(idAct);
					
					
					Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
					Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
					long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
			}
		}
	}

	private String recuperarQuiebresAgendaSc( Collection collectionBreaks ){
		String strReturn = "";
		if(collectionBreaks != null && collectionBreaks.size() > 0){
			for (Iterator iter = collectionBreaks.iterator(); iter.hasNext();) {
				TR811SBreakPair breakPairs = (TR811SBreakPair) iter.next();
				strReturn += breakPairs.getFamily() +"-"+ breakPairs.getBreakCode()+",";
			}
			
			return strReturn.substring(0, strReturn.length()-1); 
		}
		return strReturn;
	}
	
	private String recuperarObservacionesQuiebresAgendaSc( Collection collectionBreaks ){
		String strReturn = "";
		String observation = "";
		Set conjunto = new HashSet();
		if(collectionBreaks != null && collectionBreaks.size() > 0){
			for (Iterator iter = collectionBreaks.iterator(); iter.hasNext();) {
				TR811SBreakPair breakPairs = (TR811SBreakPair) iter.next();
				conjunto.add(breakPairs.getObservations());
			}
			
			if(conjunto.size() > 0){
				for (Iterator iter = conjunto.iterator(); iter.hasNext();) {
					String obs = (String) iter.next();
					strReturn += obs+"."; 
				}
			}
		}
		return strReturn;
	}
	
	private boolean esAutoinstalacion(Agenda_scLocal agendaSCLocal) throws NamingException, FinderException, ATiempoAppEx {
		Producto_servicio_peticionLocalHome  psph = (Producto_servicio_peticionLocalHome) 
			HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		Collection productosServicioPeticion = psph.findAllByPetiNumero(agendaSCLocal.getPeti_numero());
		PeticionesDelegate pDelegate = new PeticionesDelegate();
		Long OCAutoInstalacion=new Long(pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
		boolean esOCAutoInstalacion = false;
		for (Iterator iter = productosServicioPeticion.iterator(); iter.hasNext();) {
			Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iter.next();
			Familia_producto_servicioKey llaveFamilia = (Familia_producto_servicioKey) psp.getFamiliaKey();
		    int idFamiliaPsp = llaveFamilia.faps_id.intValue();
			
			Operacion_comercialKey opcoKey = (Operacion_comercialKey)psp.getOperacion_comercial().getPrimaryKey();
			if(opcoKey.opco_id.equals(OCAutoInstalacion)){
				esOCAutoInstalacion = true;
				break;
			}
		}
		return esOCAutoInstalacion;
	}
	
	private void setQuiebrePcAgendaSc( Collection collectionBreaks, Long petiNumero, Collection productoServicioPeticionCollection, String codActividad ){
		
		try {
			
			UsuarioLocalHome usuarioHome = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuarioLocal = usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(1)));
			
			if(estado_ps_peticionHome == null){
				estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			}
			
			if(catalogo_causalHome == null){
				catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			}
			
			if(estado_psHome == null){
				estado_psHome = (Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			}

			if(causal_peticionHome==null){
				causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			}
			
			ActividadLocal actividadLocal = null;
			ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			actividadLocal = actividadLocalHome.findByCodigoActividadIdAplicacion(codActividad,new Long (3));
			
			for (Iterator iter = collectionBreaks.iterator(); iter.hasNext();) {
				
				TR811SBreakPair breakPairs = (TR811SBreakPair) iter.next();
				String breakCode = breakPairs.getBreakCode();
				String breakFamily = breakPairs.getFamily();
				String observations = breakPairs.getObservations()!=null && breakPairs.getObservations().length() > 255 ? breakPairs.getObservations().substring(0,255) : breakPairs.getObservations();
				

				Homologacion_qb_Agd_scLocalHome agd_scLocalHome = (Homologacion_qb_Agd_scLocalHome) HomeFactory.getHome(Homologacion_qb_Agd_scLocalHome.JNDI_NAME);
				Homologacion_qb_Agd_scKey agd_scKey = new Homologacion_qb_Agd_scKey( breakFamily );
				Homologacion_qb_Agd_scLocal agd_scLocal = agd_scLocalHome.findByPrimaryKey( agd_scKey );
				String[] arrayAgd_scLocal = agd_scLocal.getHq_agd_id_familia_ps().split("-");
				
				for (int i = 0; i < arrayAgd_scLocal.length; i++) {
					for( Iterator iterator = productoServicioPeticionCollection.iterator(); iterator.hasNext();){
						Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)iterator.next();
						Producto_servicio_peticionKey producto_servicio_peticionKey = (Producto_servicio_peticionKey)producto_servicio_peticionLocal.getPrimaryKey();
						Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey)producto_servicio_peticionLocal.getFamiliaKey();
						if( familia_producto_servicioKey.faps_id.equals( new Long(arrayAgd_scLocal[i]))){

							Collection listaEstadoPsPeti = producto_servicio_peticionLocal.getEstado_ps_peticion();
							
							if( listaEstadoPsPeti.size() > 0 ){
								Catalogo_causalKey catalogo_causalKey = new Catalogo_causalKey( new Long( breakCode ) );
								Catalogo_causalLocal catalogo_causalLocal = catalogo_causalHome.findByPrimaryKey( catalogo_causalKey );
								
								//Tengo una causa asociada tengo que actualizar
								Estado_ps_peticionLocal estado_ps_peticionLocal = (Estado_ps_peticionLocal) listaEstadoPsPeti.iterator().next();
								estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
								estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
								estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
							    estado_ps_peticionLocal.setCod_actividad(((ActividadKey)actividadLocal.getPrimaryKey()).act_id);
								
								Estado_psKey estado_psKey=new Estado_psKey( new Long( 3 ) );
								Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
								long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
								Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
								
								causal_peticionLocal.setFecha_inicio(new Fecha().getFechaconFormato(9));
								causal_peticionLocal.setFecha_termino(new Fecha().getFechaconFormato(9));
								causal_peticionLocal.setNovedad(observations);
								causal_peticionLocal.setCod_actividad( ((ActividadKey)actividadLocal.getPrimaryKey()).act_id );
								if(catalogo_causalKey.cod_causal.equals(ComunInterfaces.CUASAL_SIRS)
										||catalogo_causalKey.cod_causal.equals(ComunInterfaces.CUASAL_AGENDA)){
									PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
									peticionLocal.setTipoErrorId(catalogo_causalKey.cod_causal);
								}
									
								
							}else{
								Catalogo_causalKey catalogo_causalKey = new Catalogo_causalKey( new Long( breakCode ) );
								Catalogo_causalLocal catalogo_causalLocal = catalogo_causalHome.findByPrimaryKey( catalogo_causalKey );
								
								long correlativo = dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
								Estado_ps_peticionLocal estado_ps_peticionLocal = estado_ps_peticionHome.create( new Long(correlativo), producto_servicio_peticionLocal.getProducto_servicio(), producto_servicio_peticionLocal );
								estado_ps_peticionLocal.setCod_causal( catalogo_causalKey.cod_causal );
								estado_ps_peticionLocal.setCod_estado_cierre( new Integer(3) );
								estado_ps_peticionLocal.setNovedad( catalogo_causalLocal.getDescripcion_causal() );
								estado_ps_peticionLocal.setCod_actividad( ((ActividadKey)actividadLocal.getPrimaryKey()).act_id );//va el codigo de la actividad
								
								Estado_psKey estado_psKey = new Estado_psKey( new Long( 3 ) );
								Estado_psLocal estado_psLocal = estado_psHome.findByPrimaryKey( estado_psKey );
								
								long id_causal_peticion = dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
								Causal_peticionLocal causal_peticionLocal = causal_peticionHome.create( new Long(id_causal_peticion), catalogo_causalLocal , estado_psLocal, estado_ps_peticionLocal,usuarioLocal );
								
								causal_peticionLocal.setFecha_inicio( new Fecha().getFechaconFormato(9) );
								causal_peticionLocal.setFecha_termino( new Fecha().getFechaconFormato(9) );
								causal_peticionLocal.setNovedad(observations);
								causal_peticionLocal.setCod_actividad( ((ActividadKey)actividadLocal.getPrimaryKey()).act_id );
								if(catalogo_causalKey.cod_causal.equals(ComunInterfaces.CUASAL_SIRS)
										||catalogo_causalKey.cod_causal.equals(ComunInterfaces.CUASAL_AGENDA)){
									PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
									peticionLocal.setTipoErrorId(catalogo_causalKey.cod_causal);
								}
							}
						}
						
					}
				}
			}
			
		} catch ( NamingException ne ) {
			log.debug("RecurosBABean setQuiebrePcAgendaSc: "+ne);
		} catch ( FinderException fe ) {
			log.debug("RecurosBABean setQuiebrePcAgendaSc: "+fe);
		} catch (CreateException ce) {
			log.debug("RecurosBABean setQuiebrePcAgendaSc: "+ce);
		} 
	}
	
	private boolean esPostventa (PeticionLocal peticionLocal){
		boolean esPostventa = true;
		Collection psPeticion = peticionLocal.getProducto_servicio_peticion();
		for (Iterator psPeticionIterator = psPeticion.iterator(); psPeticionIterator.hasNext();){
			Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)psPeticionIterator.next();
			
			Producto_servicioLocal productoServicioLocal = productoServicioPeticionLocal.getProducto_servicio();
			Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey)productoServicioLocal.getFamilia_producto_servicio().getPrimaryKey();
			
			if (familiaPSKey != null && 
					((familiaPSKey.faps_id.toString().equals(new Integer(familiaPcBA).toString()) || familiaPSKey.faps_id.toString().equals(new Integer(familiaPcLinea).toString()) || familiaPSKey.faps_id.toString().equals(new Integer(familiaIP).toString()) )
					|| familiaPSKey.faps_id.toString().equals(new Integer(familiaPcTV).toString()))){
				esPostventa = false;
				break;
			}
		}
		return esPostventa;
	}
	
	public void agendamientoDecosTR811 (ArrayList listaDecos, PeticionLocal peticionLocal, TR811S tr811s, ActividadEJBDTO actDto, IActividadEJB actividadEJB, boolean modemsOK, String codBandeja){
		try{
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);				
			Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
			Deco_Tarjeta_Info_SapLocal dec_tar_sapdecoLocal = null;
			ArrayList ArrayDecos = new ArrayList();
			ArrayList ArrayTarjetas = new ArrayList();
			ArrayList ArrayDiscosDuros = new ArrayList();
			ArrayList decoTarjetaTR811 = new ArrayList();
			ArrayList decoTarjetaInstall = new ArrayList();
			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
			boolean esPostventa = true;
			
			Collection psPeticion = peticionLocal.getProducto_servicio_peticion();
			for (Iterator psPeticionIterator = psPeticion.iterator(); psPeticionIterator.hasNext();){
				Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)psPeticionIterator.next();
				
				Producto_servicioLocal productoServicioLocal = productoServicioPeticionLocal.getProducto_servicio();
				Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey)productoServicioLocal.getFamilia_producto_servicio().getPrimaryKey();
				
				if (familiaPSKey != null && 
						((familiaPSKey.faps_id.toString().equals(new Integer(familiaPcBA).toString()) || familiaPSKey.faps_id.toString().equals(new Integer(familiaPcLinea).toString()) || familiaPSKey.faps_id.toString().equals(new Integer(familiaIP).toString()) )
						|| familiaPSKey.faps_id.toString().equals(new Integer(familiaPcTV).toString()))){
					esPostventa = false;
					break;
				}
			}

			String tiposDiscoDuro = VpistbbaConfig.getVariable("TIPOS_DISCOS_DUROS");
			List listTiposDiscoDuro = Arrays.asList(tiposDiscoDuro.split(","));
			
			for (int i=0; i < listaDecos.size(); i++){
				TR811SMaterials decosTarjetas = (TR811SMaterials)listaDecos.get(i);
				
				if(listTiposDiscoDuro.contains(decosTarjetas.getTypeEquipment())){
					ArrayDiscosDuros.add(decosTarjetas);
				}else if (decosTarjetas.getTypeEquipment().indexOf("DECO")!=-1){
					ArrayDecos.add(decosTarjetas);
				}else if (decosTarjetas.getTypeEquipment().indexOf("TARJETA")!=-1){
					ArrayTarjetas.add(decosTarjetas);
				}
			}
			
			String decosDiscoDuro = VpistbbaConfig.getVariable("DECO_DISCO_DURO");
			List listDecosDiscoDuro = Arrays.asList(decosDiscoDuro.split(","));
			
			//Si el mensaje trae decos Tarjetas
			if (ArrayDecos.size() == ArrayTarjetas.size()){
				//Creación de los elementos de Decos Tarjetas
				for (int j=0;j<ArrayDecos.size();j++){
					TR811SMaterials deco = (TR811SMaterials)ArrayDecos.get(j);
					TR811SMaterials tarjeta = (TR811SMaterials)ArrayTarjetas.get(j);
					
					TR811SMaterials discoDuro = null;
					if(!ArrayDiscosDuros.isEmpty()){
						//Se verifica si la marca de deco soporta disco duro
						if(listDecosDiscoDuro.contains(deco.getBrandEquipment())){
							discoDuro = (TR811SMaterials)ArrayDiscosDuros.get(0);
							ArrayDiscosDuros.remove(0);
						}
					}

					DecoTarDTO decoTarjetaDTO = new DecoTarDTO(deco.getCassId(), tarjeta.getEquipmentSerialNumber());
					decoTarjetaDTO.setAccion(new Integer(operacionParActivar));
					
					if (deco.getTypeEquipment().indexOf(desHCDecoSTD) != -1){
						decoTarjetaDTO.setDeco_reference(desHCDecoSTD);
					}else if (deco.getTypeEquipment().indexOf(desHCDecoPVR) != -1){
						decoTarjetaDTO.setDeco_reference(desHCDecoPVR);
					}else if (deco.getTypeEquipment().indexOf(desHCDecoHDTV) != -1){
						decoTarjetaDTO.setDeco_reference(desHCDecoHDTV);
					}
					
					decoTarjetaDTO.setOperationComercial(new Long(operacionParActivar));
					decoTarjetaDTO.setDecoSerial(deco.getEquipmentSerialNumber());
					decoTarjetaDTO.setDecoBrand(deco.getBrandEquipment());	
					
					/*RQ.8595 - mfmendez*/
					// Datos del Deco				
//					decoTarjetaDTO.setDecoMaterialCodeSAP(deco.getCodeMaterial().toString());					
					decoTarjetaDTO.setDecoMaterialSAP(deco.getMaterial());																
					decoTarjetaDTO.setDecoMeasurementUnitSAP(deco.getMaterialUnitMeasure());					
					decoTarjetaDTO.setDecoCostCenterSAP(deco.getCostCenter());									
//					decoTarjetaDTO.setCardMaterialCodeSAP(tarjeta.getCodeMaterial().toString());					
					decoTarjetaDTO.setCardMaterialSAP(tarjeta.getMaterial());															
					decoTarjetaDTO.setCardMeasurementUnitSAP(tarjeta.getMaterialUnitMeasure());					
					decoTarjetaDTO.setCardCostCenterSAP(tarjeta.getCostCenter());					
					//Datos del disco duro
					if(discoDuro != null){
						decoTarjetaDTO.setDdtvSerial(discoDuro.getEquipmentSerialNumber());
						if(discoDuro.getBrandEquipment().length() > 30){
							decoTarjetaDTO.setDdtvMarca(discoDuro.getBrandEquipment().substring(0,29));
						}else{
							decoTarjetaDTO.setDdtvMarca(discoDuro.getBrandEquipment());	
						}
						if(discoDuro.getModelEquipment().length() > 30){
							decoTarjetaDTO.setDdtvModelo(discoDuro.getModelEquipment().substring(0,29));
						}else{
							decoTarjetaDTO.setDdtvModelo(discoDuro.getModelEquipment());	
						}
					}
					/*FIN - RQ.8595 - mfmendez*/
					decoTarjetaTR811.add(decoTarjetaDTO);
				}
			}
			
			//Obtención de la diferencia entre los decos instalados y los que llegan nuevos
			Collection decoTarjetaOld = peticionLocal.getDeco_tarjeta();
			
			
			//Aqui se valida que los decos que traen el mensaje hallan para bajar es decir en la tabla hay 3 
			//y el mensaje trae tres se baja el que no trae el mensaje 
			for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
				boolean estaInstalado = false;
				Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
				
				for (Iterator equiposIterator=decoTarjetaTR811.iterator(); equiposIterator.hasNext();){
					DecoTarDTO equipo = (DecoTarDTO)equiposIterator.next();
						
					//Se cambio este if, para comparar seriales decos y no cass id
					if (!decoTarjetaLocal.getSerial_deco().equals(equipo.getDecoSerial())){
						estaInstalado = false;
					}else{
						 if (!decoTarjetaKey.id_tarjeta.equals(equipo.getTarjeta())){
						 	estaInstalado = false;
						 }else{
						 	estaInstalado = true;
						 	decoTarjetaLocal.setSerial_ddtv(equipo.getDdtvSerial());
						 	decoTarjetaLocal.setMarca_ddtv(equipo.getDdtvMarca());
						 	decoTarjetaLocal.setModelo_ddtv(equipo.getDdtvModelo());
						 	break;
						 }
					}
				}
					
				if (!estaInstalado && !esPostventa){
					DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
					decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
					decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
					decoTarjetaDTO.setOperationComercial(new Long(operacionParDesactivar));
					decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
					decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());						
					/*RQ.8595 - mfmendez*/
					decoTarjetaDTO = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, decoTarjetaDTO, decoTarjetaKey.id_tarjeta, peticionKey.peti_numero);
					decoTarjetaDTO = this.setDatosSAPDeco(deco_tar_inf_sapLocalHome, decoTarjetaDTO, decoTarjetaKey.id_deco, peticionKey.peti_numero);
					/*FIN - RQ.8595 - mfmendez*/
					decoTarjetaInstall.add(decoTarjetaDTO);
				}		
			}

			//Aqui se elimina el deco que se dio de baja en la primera parte
			for (Iterator decoTarjetaInstallIterator = decoTarjetaInstall.iterator(); decoTarjetaInstallIterator.hasNext();){
				DecoTarDTO decoTarjetaDTO = (DecoTarDTO)decoTarjetaInstallIterator.next();
				
				if (decoTarjetaDTO.getAccion().intValue() == operacionParDesactivar){
					
					Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(decoTarjetaDTO.getTarjeta(), 
							decoTarjetaDTO.getDeco(), (PeticionKey)peticionLocal.getPrimaryKey());
					Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
					//Se remueve el deco de la tabla DECO_TARJETA 
					decoTarjetaAuxLocal.remove();
				}
			}
			int contadorHD = 0;
			int contadorPVR = 0;
			int contadorSTD = 0;
			int reporteHD = 0;
			int reportePVR = 0;
			int reporteSTD = 0;
			int diaAdicional = 1;
			for(Iterator psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
				Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
				if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
					contadorHD += 1;						
					log.debug("Tiene deco HD ");
				}else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV){
					contadorPVR += 1;						
					log.debug("Tiene deco PVR");
				}
				else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV){
					contadorSTD += 1;
					log.debug("Tiene deco STD");
				}
			}			
			//Aqui se valida que el mensaje traiga nuevos decos, se da de alta el nuevo
			Collection ps=peticionLocal.getProducto_servicio_peticion();
			
			for(Iterator equiposIterator=decoTarjetaTR811.iterator(); equiposIterator.hasNext();){
				DecoTarDTO equipo = (DecoTarDTO)equiposIterator.next();
				boolean estaSinInstalar = true;
				RecursosBADelegate recursosbaDelegate = new RecursosBADelegate();
								
				try{
					Deco_Tarjeta_Info_SapKey decoTarjetaSAPKey = new Deco_Tarjeta_Info_SapKey(equipo.getDeco(),peticionKey.peti_numero );
					dec_tar_sapdecoLocal = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(decoTarjetaSAPKey);
					recursosbaDelegate.ingresoInformacionSAP(dec_tar_sapdecoLocal, equipo, peticionKey, deco_tar_inf_sapLocalHome);
				}catch(FinderException ex){
					log.debug("El elemento no se encuentra registrado en la tabla DECO_TARJETA_INF_SAP");
					dec_tar_sapdecoLocal = null;
				}
				for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
					
					if (decoTarjetaLocal.getSerial_deco().equals(equipo.getDecoSerial()) && decoTarjetaKey.id_tarjeta.equals(equipo.getTarjeta())){
						/*RQ.8595 - mfmendez*/
						if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoHDTV) != -1 && contadorHD > reporteHD){
							decoTarjetaLocal.setDeco_adicionales(new Long(0));
							reporteHD++;
						}else if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoPVR) != -1 && contadorPVR > reportePVR){
							decoTarjetaLocal.setDeco_adicionales(new Long(0));
							reportePVR++;
						}else if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoSTD) != -1 && contadorSTD > reporteSTD){
							decoTarjetaLocal.setDeco_adicionales(new Long(0));
							reporteSTD++;
						}else{
							SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
							Calendar fechaActual = Calendar.getInstance();
							fechaActual.add(Calendar.DATE,diaAdicional);
							Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formatoFecha.format(fechaActual.getTime()));
							decoTarjetaLocal.setDeco_adicionales(new Long(1));
							decoTarjetaLocal.setFec_ejec_atis(new Timestamp(date.getTime()));
							diaAdicional++;
							
						}
						try{
							decoTarjetaLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
							// se crean los datos del deco
							if(dec_tar_sapdecoLocal == null )
								recursosbaDelegate.ingresoInformacionSAP(dec_tar_sapdecoLocal, equipo, peticionKey, deco_tar_inf_sapLocalHome);
						} catch(Exception e){
							log.debug("recursosBaBean: Existente: No se pudieron crear los datos de SAP para el Deco con id: "+equipo.getDeco()+" y para la Tarjeta con id: "+equipo.getTarjeta()+". "+e);
						}	
						estaSinInstalar = false;
						break;
					}
				}
				
				if (estaSinInstalar){
					Deco_tarjetaLocal decoTarjetaLocal = decoTarjetaLocalHome.create(equipo.getTarjeta(), equipo.getDeco(), peticionLocal);
					
					decoTarjetaLocal.setAccion(new Integer(accionParActivar));
					decoTarjetaLocal.setEstado(new Integer(estadoOk));
					decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
					
					decoTarjetaLocal.setOpco_id(new Long(accionParActivar));
					decoTarjetaLocal.setDeco_reference(equipo.getDeco_reference());
					decoTarjetaLocal.setDeco_marca(equipo.getDecoBrand());
					decoTarjetaLocal.setSerial_deco(equipo.getDecoSerial());
					decoTarjetaLocal.setSerial_tarjeta(equipo.getTarjeta());
					decoTarjetaLocal.setCodigo_deco(equipo.getDecoType());
					decoTarjetaLocal.setSerial_ddtv(equipo.getDdtvSerial());
				 	decoTarjetaLocal.setMarca_ddtv(equipo.getDdtvMarca());
				 	decoTarjetaLocal.setModelo_ddtv(equipo.getDdtvModelo());
					/*RQ.8595 - mfmendez*/
					decoTarjetaLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
					if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoHDTV) != -1 && contadorHD > reporteHD){
						decoTarjetaLocal.setDeco_adicionales(new Long(0));
						reporteHD++;
					}else if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoPVR) != -1 && contadorPVR > reportePVR){
						decoTarjetaLocal.setDeco_adicionales(new Long(0));
						reportePVR++;
					}else if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoSTD) != -1 && contadorSTD > reporteSTD){
						decoTarjetaLocal.setDeco_adicionales(new Long(0));
						reporteSTD++;
					}else{
						SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
						Calendar fechaActual = Calendar.getInstance();
						fechaActual.add(Calendar.DATE,diaAdicional);
						Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formatoFecha.format(fechaActual.getTime()));
						decoTarjetaLocal.setDeco_adicionales(new Long(1));
						decoTarjetaLocal.setFec_ejec_atis(new Timestamp(date.getTime()));
						diaAdicional++;
						
					}
					decoTarjetaLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);			
					
					DecoTarDTO decoTarjetaDTO = new DecoTarDTO(equipo.getDeco(), equipo.getTarjeta());
					decoTarjetaDTO.setAccion(new Integer(accionParActivar));
					decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
					decoTarjetaDTO.setOperationComercial(decoTarjetaLocal.getOpco_id());
					decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
					decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
					/*RQ.8595 - mfmendez*/
					// Datos del Deco
					decoTarjetaDTO.setDecoPostingDateSAP(equipo.getDecoPostingDateSAP());					
					decoTarjetaDTO.setDecoMoveTypeSAP(equipo.getDecoMoveTypeSAP());					
//					decoTarjetaDTO.setDecoMaterialCodeSAP(equipo.getDecoMaterialCodeSAP());					
					decoTarjetaDTO.setDecoMaterialSAP(equipo.getDecoMaterialSAP());											
					decoTarjetaDTO.setDecoPlantSAP(equipo.getDecoPlantSAP());																	
					decoTarjetaDTO.setDecoStorageSAP(equipo.getDecoStorageSAP());											
					decoTarjetaDTO.setDecoBatchCodeSAP(equipo.getDecoBatchCodeSAP());					
					decoTarjetaDTO.setDecoMeasurementUnitSAP(equipo.getDecoMeasurementUnitSAP());					
					decoTarjetaDTO.setDecoCostCenterSAP(equipo.getDecoCostCenterSAP());					
					decoTarjetaDTO.setDecoFuncAreaLongSAP(equipo.getDecoFuncAreaLongSAP());					
					decoTarjetaDTO.setDecoPepElementSAP(equipo.getDecoPepElementSAP());
					decoTarjetaDTO.setDecoFlagMatSAP(equipo.getDecoFlagMatSAP());
					// Datos de la Tarjeta
					decoTarjetaDTO.setCardPostingDateSAP(equipo.getCardPostingDateSAP());					
					decoTarjetaDTO.setCardMoveTypeSAP(equipo.getCardMoveTypeSAP());					
//					decoTarjetaDTO.setCardMaterialCodeSAP(equipo.getCardMaterialCodeSAP());					
					decoTarjetaDTO.setCardMaterialSAP(equipo.getCardMaterialSAP());											
					decoTarjetaDTO.setCardPlantSAP(equipo.getCardPlantSAP());																	
					decoTarjetaDTO.setCardStorageSAP(equipo.getCardStorageSAP());											
					decoTarjetaDTO.setCardBatchCodeSAP(equipo.getCardBatchCodeSAP());					
					decoTarjetaDTO.setCardMeasurementUnitSAP(equipo.getCardMeasurementUnitSAP());					
					decoTarjetaDTO.setCardCostCenterSAP(equipo.getCardCostCenterSAP());					
					decoTarjetaDTO.setCardFuncAreaLongSAP(equipo.getCardFuncAreaLongSAP());					
					decoTarjetaDTO.setCardPepElementSAP(equipo.getCardPepElementSAP());
					decoTarjetaDTO.setCardFlagMatSAP(equipo.getCardFlagMatSAP());
					/*FIN - RQ.8595 - mfmendez*/
					
					decoTarjetaInstall.add(decoTarjetaDTO);
				}
			}	
		
			if (decoTarjetaInstall != null && decoTarjetaInstall.size() > 0){
				Collection mensajesEstadoTV = peticionLocal.getMensaje_estado_tv();
								
				String idAgendaSC = tr811s.getIdSchedule()+"@"+tr811s.getId()+"@TR811"+"@"+modemsOK;
				
				this.enviaConfiguracionServiciosTVAgendaSC(peticionKey.peti_numero.longValue(), decoTarjetaInstall, true, idAgendaSC);
			}else{
				log.debug("No se envía el mensaje: "+ tr811s.getId() +" a HC porque los equipos recibidos ya están instalados");
				String observaciones = actDto.getObservacion(); 
				if(observaciones != null && !observaciones.equals("")){
					actDto.setObservacion(observaciones + " No se envía el mensaje: "+ tr811s.getId() +" a HC porque los equipos recibidos ya están instalados");
				}else{
					actDto.setObservacion("No se envía el mensaje: "+ tr811s.getId() +" a HC porque los equipos recibidos ya están instalados");
				}
				
				String[] llaveWF = null;
				if (codBandeja != null){
					llaveWF = codBandeja.split("-");					
					actDto.setDato(llaveWF[0],llaveWF[1]);
				}
				actividadEJB.terminar(actDto);
			}
		}catch(NamingException ex){
			log.debug("Error en la instancia en la recepción del cierre de actuación " + ex);
			ex.printStackTrace();
		}catch(CreateException ex){
			log.debug("Error en la creación en la recepción del cierre de actuación " + ex);;
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error en la búsqueda en la recepción del cierre de actuación " + ex);
			ex.printStackTrace();
		}catch(RemoveException ex){
			log.debug("Error en la eliminación en la recepción del cierre de actuación " + ex);
			ex.printStackTrace();
		}catch(ATiempoAppEx ex){
			log.debug("Error en los procesos de la recepción del cierre de actuación " + ex);
			ex.printStackTrace();
		}catch(TnProcesoExcepcion ex){
			log.debug("Error en los procesos de la recepción del cierre de actuación " + ex);
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error en los procesos de la recepción del cierre de actuación " + e);
		}
	}
	
	private DecoTarDTO setDatosSAPTarjeta(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, DecoTarDTO tarjeta, String idCard, Long idPeticion){
		Deco_Tarjeta_Info_SapLocal infoSAPCard = null;
		Deco_Tarjeta_Info_SapKey keyInfoSAPCard = null;
		
		/*RQ.8595 - mfmendez*/
		// Datos de la tarjeta
		try{
			keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(idCard, idPeticion);
			infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
			
			if(infoSAPCard.getFec_cont_sap() != null)
				tarjeta.setCardPostingDateSAP(infoSAPCard.getFec_cont_sap());
			else
				tarjeta.setCardPostingDateSAP("");
			
			if(infoSAPCard.getClase_mov_sap() != null)
				tarjeta.setCardMoveTypeSAP(infoSAPCard.getClase_mov_sap());
			else
				tarjeta.setCardMoveTypeSAP("");
			
			tarjeta.setCardMaterialCodeSAP(Integer.toString(infoSAPCard.getPos_doc_sap()));
			
			if(infoSAPCard.getNum_material_sap() != null)
				tarjeta.setCardMaterialSAP(infoSAPCard.getNum_material_sap());
			else
				tarjeta.setCardMaterialSAP("");
			
			if(infoSAPCard.getCentro_sap() != null)
				tarjeta.setCardPlantSAP(infoSAPCard.getCentro_sap());
			else
				tarjeta.setCardPlantSAP("");
			
			if(infoSAPCard.getAlmacen_sap() != null)
				tarjeta.setCardStorageSAP(infoSAPCard.getAlmacen_sap());
			else
				tarjeta.setCardStorageSAP("");
			
			if(infoSAPCard.getCod_lote_sap() != null)
				tarjeta.setCardBatchCodeSAP(infoSAPCard.getCod_lote_sap());
			else
				tarjeta.setCardBatchCodeSAP("");
			
			if(infoSAPCard.getUnd_medida_sap() != null)
				tarjeta.setCardMeasurementUnitSAP(infoSAPCard.getUnd_medida_sap());
			else
				tarjeta.setCardMeasurementUnitSAP("");
			
			if(infoSAPCard.getCentr_cost_sap() != null)
				tarjeta.setCardCostCenterSAP(infoSAPCard.getCentr_cost_sap());
			else
				tarjeta.setCardCostCenterSAP("");
			
			if(infoSAPCard.getArea_func_sap() != null)
				tarjeta.setCardFuncAreaLongSAP(infoSAPCard.getArea_func_sap());
			else
				tarjeta.setCardFuncAreaLongSAP("");
			
			if(infoSAPCard.getElement_pep_sap() != null)
				tarjeta.setCardPepElementSAP(infoSAPCard.getElement_pep_sap());
			else
				tarjeta.setCardPepElementSAP("");
			
			if(infoSAPCard.getFlag_mat_sap() != null)
				tarjeta.setCardFlagMatSAP(infoSAPCard.getFlag_mat_sap());
			else
				tarjeta.setCardFlagMatSAP("");
			
		}catch (FinderException e) {
			log.debug("No se encontraron Tarjetas para Card con id: "+idCard+". Y id de peticion: "+idPeticion);
			tarjeta.setCardPostingDateSAP("");
			tarjeta.setCardMoveTypeSAP("");
			tarjeta.setCardMaterialCodeSAP("");
			tarjeta.setCardMaterialSAP("");
			tarjeta.setCardPlantSAP("");
			tarjeta.setCardStorageSAP("");
			tarjeta.setCardBatchCodeSAP("");
			tarjeta.setCardMeasurementUnitSAP("");
			tarjeta.setCardCostCenterSAP("");
			tarjeta.setCardFuncAreaLongSAP("");
			tarjeta.setCardPepElementSAP("");
			tarjeta.setCardFlagMatSAP("");
		} catch (Exception e) {
			log.error("RecursosBABean: setDatosSAPTarjeta: Se presento Error seteando los datos de SAP para una Tarjeta. "+e);
		}			
		/*FIN - RQ.8595 - mfmendez*/
		
		return tarjeta;
	}
	private DecoTarDTO setDatosSAPDeco(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, DecoTarDTO equipment, String idDeco, Long idPeticion){
		Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
		Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
		
		/*RQ.8595 - mfmendez*/
		// Datos del Deco
		try{			
			keyInfoSAPTmp = new Deco_Tarjeta_Info_SapKey(idDeco, idPeticion);
			infoSAPTmp = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPTmp);
			
			if(infoSAPTmp.getFec_cont_sap() != null)
				equipment.setDecoPostingDateSAP(infoSAPTmp.getFec_cont_sap());
			else
				equipment.setDecoPostingDateSAP("");
			
			if(infoSAPTmp.getClase_mov_sap() != null)
				equipment.setDecoMoveTypeSAP(infoSAPTmp.getClase_mov_sap());
			else
				equipment.setDecoMoveTypeSAP("");
			
			equipment.setDecoMaterialCodeSAP(Integer.toString(infoSAPTmp.getPos_doc_sap()));
			
			if(infoSAPTmp.getNum_material_sap() != null)
				equipment.setDecoMaterialSAP(infoSAPTmp.getNum_material_sap());
			else
				equipment.setDecoMaterialSAP("");
			
			if(infoSAPTmp.getCentro_sap() != null)
				equipment.setDecoPlantSAP(infoSAPTmp.getCentro_sap());
			else
				equipment.setDecoPlantSAP("");
			
			if(infoSAPTmp.getAlmacen_sap() != null)
				equipment.setDecoStorageSAP(infoSAPTmp.getAlmacen_sap());
			else
				equipment.setDecoStorageSAP("");
			
			if(infoSAPTmp.getCod_lote_sap() != null)
				equipment.setDecoBatchCodeSAP(infoSAPTmp.getCod_lote_sap());
			else
				equipment.setDecoBatchCodeSAP("");
			
			if(infoSAPTmp.getUnd_medida_sap() != null)
				equipment.setDecoMeasurementUnitSAP(infoSAPTmp.getUnd_medida_sap());
			else
				equipment.setDecoMeasurementUnitSAP("");
			
			if(infoSAPTmp.getCentr_cost_sap() != null)
				equipment.setDecoCostCenterSAP(infoSAPTmp.getCentr_cost_sap());
			else
				equipment.setDecoCostCenterSAP("");
			
			if(infoSAPTmp.getArea_func_sap() != null)
				equipment.setDecoFuncAreaLongSAP(infoSAPTmp.getArea_func_sap());
			else
				equipment.setDecoFuncAreaLongSAP("");
			
			if(infoSAPTmp.getElement_pep_sap() != null)
				equipment.setDecoPepElementSAP(infoSAPTmp.getElement_pep_sap());
			else
				equipment.setDecoPepElementSAP("");
			
			if(infoSAPTmp.getFlag_mat_sap() != null)
				equipment.setDecoFlagMatSAP(infoSAPTmp.getFlag_mat_sap());
			else
				equipment.setDecoFlagMatSAP("");
			
		} catch (FinderException e) {
			log.debug("No se encontraron Decos para deco con id: "+idDeco+". Y id de peticion: "+idPeticion);
			equipment.setDecoPostingDateSAP("");
			equipment.setDecoMoveTypeSAP("");
			equipment.setDecoMaterialCodeSAP("");
			equipment.setDecoMaterialSAP("");
			equipment.setDecoPlantSAP("");
			equipment.setDecoStorageSAP("");
			equipment.setDecoBatchCodeSAP("");
			equipment.setDecoMeasurementUnitSAP("");
			equipment.setDecoCostCenterSAP("");
			equipment.setDecoFuncAreaLongSAP("");
			equipment.setDecoPepElementSAP("");
			equipment.setDecoFlagMatSAP("");
		} catch (Exception e) {
			log.error("RecursosBABean: setDatosSAPEquipo: Se presento Error seteando los datos de SAP para un Deco. "+e);
		}
		/*FIN - RQ.8595 - mfmendez*/
		
		return equipment;
	}

//	REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA 24/07/2014
	//funcion que inserta en la tabla instalaciones_vip un registro para llevar control de las instalaciones y poder consultar si una averia de infancia
	public void insertarAltaTraslado (ActividadEJBDTO act)
	{
		try {
			log.debug("Se procedera a insertar un nuevo registro en instalaciones VIP");
			//instanciamos el localhome de instalaciones_vip
			Instalaciones_VIPLocalHome instalaciones_VIPLocalHome = (Instalaciones_VIPLocalHome) HomeFactory.getHome(Instalaciones_VIPLocalHome.JNDI_NAME);
			//instanciamos el localhome de peticion
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			//creamos la key con el numero depeticion
			PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
			//consultamos con la PK la peticion
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			//obtenemos la PK de peticion_atis
			Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
			//obtenemos la PK de departamento de la peticion
			DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
			//obtenemos la PK de la localidad de la peticion
			LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
			//creamos el nuevo registro en la tabla instalacion_VIP
			Instalaciones_VIPLocal instalaciones_VIPLocal=instalaciones_VIPLocalHome.create(act.getNumeroPeticion(),
					peticion_atisKey.cod_pet_cd,peticionLocal.getNum_ide_nu_stb(),peticionLocal.getNum_ide_nu_tv(),localidadKey.cod_loc,
					departamentoKey.cod_dpt,peticionLocal.getEspe_id(),peticionLocal.getTica_id());
			//setemos los campos no obligatorios fechas
			instalaciones_VIPLocal.setFecha_ingreso(peticionLocal.getPeti_fecha_ingreso());
			Calendar calendar = Calendar.getInstance();
			Date date = calendar.getTime();
			Timestamp currentTimestamp = new Timestamp(date.getTime());
			instalaciones_VIPLocal.setFecha_fin(currentTimestamp);
			log.debug("Se creo registro con exito los datos de la peticion "+act.getNumeroPeticion()+" en la tabla instalaciones_VIP");
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al iniciar el localHome "+e.toString());
		} catch (FinderException e) {
			//TODO Bloque catch generado automáticamente
			log.debug("Error al consultar la peticion "+e.toString());
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al crear el registro en la tabla Instalaciones_vip "+e.toString());
		}
	}	
	//FIN REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA
		
	public String getCodActividadBintegrada(BintegradaLocal bintegradaLocal){
		String codActividad = bintegradaLocal.getBi_url_detalle();
		int idInicio = codActividad.indexOf("actividad");
		
		if(idInicio!=-1){
			codActividad=codActividad.substring(idInicio,codActividad.length());
			int idFin=codActividad.indexOf("&");
			if(idFin!=-1){
				codActividad=codActividad.substring(10,idFin);
				codActividad = codActividad.replace('+', ' ');
			}
		}
		
		return codActividad;
	}
	
	public String getIdCorrelativoBintegrada(BintegradaLocal bintegradaLocal){
		String idCorrelativo=bintegradaLocal.getBi_url_detalle();
		int idInicio=idCorrelativo.indexOf("instanciaActividad");
		
		if(idInicio!=-1){
			idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
			int idFin=idCorrelativo.indexOf("&");
			if(idFin!=-1){
				idCorrelativo=idCorrelativo.substring(19,idFin);
			}
		}
		return idCorrelativo;
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#enviaRefrescarRecursosSTB(co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal)
	 */
	public void enviaRefrescarRecursosSTB(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try {
			inicializarVariables();
			log.debug("Entro a enviaRefrescarRecursosSTB...");
			PeticionLocal peticionLocal = recursos_linea_basicaLocal.getPeticion();
			peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scLocal tmp_agenda_scLocal = tmpAgendaSCLocalHome.findbyPeti_numero(peticionKey.peti_numero);
			
			TR803S tr803s = (TR803S) XMLUtilities.unmarshall(tmp_agenda_scLocal.getXml());
			xaTelephoneData = setXA_TELEPHONE_DATA(recursos_linea_basicaLocal);
			
			Long idCorrelativoAgenda = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
							
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);

			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
			
			Mensaje_agenda_scLocal mensaje_agenda_local = almacenarMensajeAgendaSC(idCorrelativoAgenda, tr803s.getIdSchedule(), tr803s.getApptNumber(), actDto, peticionKey);
			Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			
			GestionServiciosSTBBA gestionServiciosVariado = new GestionServiciosSTBBA();
			String repuestaInBound = gestionServiciosVariado.servicioInBound(tr803s,idAplicacion, mensaje_agenda_local, xaTelephoneData.toString(),"STB","0" );
			
			log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
			
			if(repuestaInBound != null){
				mensaje_agenda_local.setReintentos(new Long(0));
				gestionServiciosVariado.servicioOutBound(tr803s,idAplicacion, mensaje_agenda_local,"STB","0");
			}
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#enviaRefrescarRecursosSTB(co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal)
	 */
	public void enviaRefrescarRecursosBA(Recursos_baLocal recursos_baLocal) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try {
		
			log.debug("Entro a enviaRefrescarRecursosBA...");
			inicializarVariables();
			PeticionLocal peticionLocal = recursos_baLocal.getPeticion();
			peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scLocal tmp_agenda_scLocal = tmpAgendaSCLocalHome.findbyPeti_numero(peticionKey.peti_numero);
			
			TR804S tr804s = (TR804S) XMLUtilities.unmarshall(tmp_agenda_scLocal.getXml());
			Long idPeticion = new Long(tr804s.getIdSchedule().substring(2,tr804s.getIdSchedule().indexOf("-")));;
			xaBroadBandData = setXA_BROADBAND_DATA(idPeticion);
			
			log.debug("se mapeo con exito xaBroadBandData");
			
			Long idCorrelativoAgenda = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
							
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);

			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
			
			Mensaje_agenda_scLocal mensaje_agenda_local = almacenarMensajeAgendaSC(idCorrelativoAgenda, tr804s.getIdSchedule(), tr804s.getApptNumber(), actDto, peticionKey);
			Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			
			GestionServiciosSTBBA gestionServiciosVariado = new GestionServiciosSTBBA();
			String repuestaInBound = gestionServiciosVariado.servicioInBound(tr804s,idAplicacion, mensaje_agenda_local, xaBroadBandData.toString(),"BA","0" );
			
			log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
			
			if(repuestaInBound != null){
				mensaje_agenda_local.setReintentos(new Long(0));
				gestionServiciosVariado.servicioOutBound(tr804s,idAplicacion, mensaje_agenda_local,"BA","0");
			}
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
				log.debug("Error NumberFormatException enviaRefrescarRecursosBA "+e);
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error EJBException enviaRefrescarRecursosBA "+e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error NamingException enviaRefrescarRecursosBA "+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error FinderException enviaRefrescarRecursosBA "+e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ATiempoAppEx enviaRefrescarRecursosBA "+e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error TnProcesoExcepcion enviaRefrescarRecursosBA "+e);
		}
	}
	public Long enviaConfiguracionServiciosTVAgendaSC (long idPeticion, ArrayList decosAInstalar, boolean haEnviadoMensaje, String idAgendaSC) throws ATiempoAppEx{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
			RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
			
			// obtiene un nuevo id de mensaje
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			
			// obtiene el id de la peticion Atis
			String pcId=null;
	        
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
			long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            boolean existeConfiguracionDTH = false;
	            
            // busca
            // - el PS tipo PC de la familia TV (el PS tipo PC es como el padre de todos los PS)
            // - los PS correspondientes a los canales tematicos contratados por el cliente (por ejemplo parrilla de deportes, monos animados, cine, etc)
            
            Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
            Producto_servicioKey productoServicioPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey2 = null;
            
            List psTematicos = new ArrayList (10) ;
            List psDecos =new ArrayList(10);
            Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
            Producto_servicio_peticionLocal productoServicioPeticion = null;
            Producto_servicioLocal productoServicio = null;
            Operacion_comercialLocal operacion_comercialLocal = null;
            Familia_producto_servicioLocal familiaPS = null;
            Familia_producto_servicioKey familiaPSKey  = null;
            int idFamilia = 0;
            Agrupacion_atisLocal agrupacion_atisLocal = null;
            long opComercialAltaTraslado =  ComunInterfaces.altaTraslado;
            long opComercialBajaTraslado =  ComunInterfaces.bajaTraslado;
            
            while (iterPss.hasNext ()){
            	productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
            	productoServicio = productoServicioPeticion.getProducto_servicio () ;
            	operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
            	operacion_comerciaPcKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
            	
	            Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
	                
	            // filtra por familia TV y busca el PC y los ps tematicos
	                
		        familiaPS = productoServicio.getFamilia_producto_servicio () ;
	                
	            familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
	                
	            idFamilia = familiaPSKey.faps_id.intValue();
	                
	            // busca si es el PS PC
	            if (idFamilia == familiaPcTV){
	            	log.debug ("peticion: " + idPeticion + " ps tipo PC "  + productoServicioKey.ps_id) ;
	                String opCoProducto = operacion_comerciaPcKey.opco_id.toString();
	                if (productoServicioPeticionPcTV != null && opComercialAltaTraslado != Integer.parseInt(opCoProducto) && opComercialBajaTraslado != Integer.parseInt(opCoProducto)){
	                	String msg = "peticion: " + idPeticion + " tiene mas de un ps tipo PC (el ultimo encontrado fue el "  + productoServicioKey.ps_id + ")" ;
	                	log.error (msg) ;
	                	throw new ATiempoAppEx (msg) ;
	                }
	                
	                if(pcId==null){
	                	agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
	                	pcId=agrupacion_atisLocal.getNum_ide_nu();
	                }
	                
	                productoServicioPeticionPcTV = productoServicioPeticion ;
	                productoServicioPcKey = productoServicioKey ;
	                    
	                //Corrección en Código de Operación Comercial para el alta de TV
	                operacion_comerciaPcKey2=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
	                continue;
	            }

	            if (idFamilia == familiaTematicoTV || idFamilia==familiaPaqueteTV){
	            	log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
	            	if(pcId==null){
	            		agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
	            		pcId=agrupacion_atisLocal.getNum_ide_nu();
	            	}
	            	psTematicos.add (productoServicioPeticion) ;
	            	continue ;
	            }
	            
	            //TODO PVR VALIDAR SI VA ACA
	            if(idFamilia == familiaDecoTV ||idFamilia == familiaDecoPVRTV || idFamilia == familiaDecoHDTV){
	            	log.debug("peticion:"+idPeticion+" ps Deco "+productoServicioKey.ps_id);
	            	if(pcId==null){
	            		agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
	            		pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
	            	
	            	psDecos.add(productoServicioPeticion);
	            	continue;
	            }

	            log.debug ("peticion: " + idPeticion + " no es Pc TV ni tampoco Tematico TV ni tampoco Tematico Deco TV." + productoServicioKey.ps_id) ;
            }

            // crea y llena la representacion Java del mensaje
	        
            TR017E tr017e = new TR017E ();
	        tr017e.setId (idCorrelativoMensaje.toString ()) ;
	        
	        tr017e.setPcId(pcId);
	        
	        if (!haEnviadoMensaje){
	        	 if(productoServicioPcKey!=null)
		        	tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
		            
		        if(operacion_comerciaPcKey2!=null)
		        	tr017e.setPcCommercialOperation(operacion_comerciaPcKey2.opco_id.longValue());
		        else if(operacion_comerciaPcKey!=null)
		        	tr017e.setPcCommercialOperation(operacion_comerciaPcKey.opco_id.longValue());
	        }
	        
	       
	        
	        tr017e.setAtisRequestNumber (peticionAtis) ;
	        tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
	        tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
	        tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
	        tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
	        
	        if(peticion.getTip_cli_cd()!=null)
	        	tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
	        else
	        	tr017e.setClientType ('0') ;
	        
	        tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
	        tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
	        
	        // deco / tarjeta
	        List listDecoTarj = new ArrayList () ;
	        
			Iterator iterDecoTarj=decosAInstalar.iterator();
			DecoTarDTO decoTarjeta = null;
			Deco_tarjetaKey decoTarjetaKey = null;
	        
			while (iterDecoTarj.hasNext ()){
				decoTarjeta = (DecoTarDTO) iterDecoTarj.next () ;
				
				TR017EEquipment equipment=new TR017EEquipment();
				equipment.setCardSerial(decoTarjeta.getTarjeta()) ;
				equipment.setCasId(decoTarjeta.getDeco()) ;
				
				if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoSTD)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_DEFAULT")).longValue());
				}else if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoHDTV)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_HD")).longValue());
				}else if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoPVR)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_PVR")).longValue());
				}
					
	            equipment.setCommercialOperation(decoTarjeta.getOperationComercial().longValue());
	            equipment.setDecoSerial(decoTarjeta.getDecoSerial());
	            equipment.setDecoType(decoTarjeta.getDeco_reference());
	            equipment.setDecoBrand(decoTarjeta.getDecoBrand());
	            
	            listDecoTarj.add (equipment) ;
			}
	            
	        tr017e.setEquipments (listDecoTarj) ;
	        //TODO: Raúl Triviño - 13/10/2009 - ajuste del requerimiento 00029981_AltaTematicosEquipos
	        /*Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection peticiones = peticion_flujoLocalHome.findByIdPeticion(new Long(idPeticion));
				
			/*for (Iterator iter = peticiones.iterator(); iter.hasNext();) {					
			 	Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
					
				if (peticionFlujo.().intValue() == Integer.parseInt(ComunInterfaces.ACTI_ID_Configuracion_DTH) 
				        && peticionFlujo.getPefl_estado().equals(ComunInterfaces.PEFL_ESTADO_OK)
				        && (peticionFlujo.getPrse_id().longValue() == Long.parseLong(ComunInterfaces.PRSE_ID_Configuracion_DTH_1)
				        ||  peticionFlujo.getPrse_id().longValue() == Long.parseLong(ComunInterfaces.PRSE_ID_Configuracion_DTH_2))){
				    existeConfiguracionDTH = true;
				    break;
				}
			}*/
				
			boolean tienePcEnAlta=recursosTVDelegate.tienePcEnAlta(new Long(idPeticion));
				
			if(tienePcEnAlta && !haEnviadoMensaje){
				List listTematicos = new ArrayList () ;
	            Iterator iterPsTematicos = psTematicos.iterator () ;
	            while (iterPsTematicos.hasNext ()){
	            	Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) iterPsTematicos.next () ;
	                long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
	    			TR017EPackage package1=new TR017EPackage(); 
	    			package1.setProductServiceCode (psId) ;
	                package1.setCommercialOperation(psPaquete.getIdOperacionComercial().longValue());
	                listTematicos.add (package1) ;
	            }
	            tr017e.setPackages (listTematicos) ;
			}
	        //end TODO
	                       
	        // guarda el registro del mensaje
	        Mensaje_estado_tvLocal dbmsg ;
	        dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
	        dbmsg.setPeticion (peticion) ;
			//TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp());
	        dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
	        
	        if (idAgendaSC != null && idAgendaSC.length() > 0){
	         	dbmsg.setDesc_error(idAgendaSC);
            }
	                        
	        // llama a la rutina que envia el mensaje
	        new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;
	        return (idCorrelativoMensaje) ;
		}catch (NumberFormatException e){
			log.error ("NumberFormatException", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}catch (FinderException e){
			log.error ("FinderException", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}catch (CreateException cex){
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		} catch (NamingException e) {
			log.error ("error creando registro", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
	}
	//Metodos necesarios para el delta de los modems - no dejar fuera del merge - sfandino
	public void crearModem(TR811SMaterials modem, ModemLocal modemLocal, ModemLocalHome modemLocalhome, Long peti_numero, String serialModemEnviado, PeticionKey peticionKey, PeticionLocal peticionLocal) {
		try {
			// TODO Apéndice de método generado automáticamente
			log.debug("Se entra a crear Módem con serial:"+serialModemEnviado +" y petición: "+peti_numero);
			modemLocal = modemLocalhome.create(serialModemEnviado, peticionLocal, new Long(peticionLocal.getNum_ide_nu_stb()), new Short("1"));
			modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemOcupar).shortValue()));
			modemLocal.setModem_marca(modem.getBrandEquipment());
			modemLocal.setModelo(modem.getModelEquipment());
			modemLocal.setTipo(new Integer(ComunInterfaces.identificadorWiFi));
			if(modem.getCodeMaterial() != null){
				modemLocal.setPos_doc_sap(Integer.parseInt(modem.getCodeMaterial()));
			}else{
				modemLocal.setPos_doc_sap(0);
			}
			
			modemLocal.setNum_material_sap(modem.getMaterial());
			//modemLocal.setCentro_sap(modem.getPlantSAP());
			//modemLocal.setAlmacen_sap(modem.getStorageSAP());
			//modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
			modemLocal.setUnd_medida_sap(modem.getMaterialUnitMeasure());
			modemLocal.setCentr_cost_sap(modem.getCostCenter());
			//modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
			//modemLocal.setElement_pep_sap(modem.getPepElementSAP());
			modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
			//modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
		
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.error("Error en eliminación de registro en la recepción del cierre de actuación " , e);
			e.printStackTrace();
		}
		
	}
	private void insertarModem(ArrayList listaModems, TR811S tr811s, PeticionKey peticionKey, HashMap estadosHomologados, ActividadEJBDTO actDto, boolean esOCAutoInstalacion, PeticionLocal peticionLocal, boolean cierroActividad) {
		// TODO Apéndice de método generado automáticamente
		try {
			ACSServicioDelegate acsServicio = new ACSServicioDelegate();
			ModemLocalHome modemLocalhome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
			Collection modemsCollection = modemLocalhome.findPeticion(peticionKey.peti_numero);
			if (listaModems != null && listaModems.size()>0){
				log.debug("Voy a configurar y registrar modems para el agendamiento:"+tr811s.getIdSchedule());
				boolean yaSeGuardoModem = false;
				TR811SMaterials materialModemUtilizado = null;
				for(int i = 0; listaModems.size() > i; i++){
					TR811SMaterials modem = (TR811SMaterials)listaModems.get(i);
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
							cierroActividad = false;
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
								//no se encontraba se esta enviando doble vez a activar el modem
								yaSeGuardoModem = true;
							}
						}
						if (!yaSeGuardoModem){
							if(serialModemActual==null && serialModemEnviado!=null ){
								this.crearModem(modem, modemLocal2, modemLocalhome, peticionKey.peti_numero,  serialModemEnviado, peticionKey,peticionLocal);
							}
							ModemVpiDTO modemDTO = this.asignarModemDTO(modem, peticionKey);
							acsServicio.enviarAutoConfiguracion(modemDTO, actDto, peticionKey.peti_numero, esOCAutoInstalacion);
							cierroActividad = false;
						}
					}
			}else{//Se eliminan los modems previamente configurados
				log.debug("No vienen Modems, voy a eliminar modems para el agendamiento:"+tr811s.getIdSchedule());
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
					cierroActividad = false;
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
		
	}

	private ModemVpiDTO asignarModemDTO(TR811SMaterials modem, PeticionKey peticionKey) throws ATiempoAppEx {
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
		return modemDTO;
	}

	private void actualizarInfoModem(ModemLocal modemLocal, TR811SMaterials modem) {
		// TODO Apéndice de método generado automáticamente
		log.debug("Se actualiza la informacion SAP para el modem con serial: " + modem.getEquipmentSerialNumber());
		modemLocal.setCodigo_material(modem.getCodeMaterial());
		modemLocal.setNum_material_sap(modem.getMaterial());
		//modemLocal.setCentro_sap(modem.getPlantSAP());
		//modemLocal.setAlmacen_sap(modem.getStorageSAP());
		//modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
		modemLocal.setUnd_medida_sap(modem.getMaterialUnitMeasure());
		modemLocal.setCentr_cost_sap(modem.getCostCenter());
		//modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
		//modemLocal.setElement_pep_sap(modem.getPepElementSAP());
		modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
		//modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
	}
	//Fin metodos para delta de modems - sfandino
}