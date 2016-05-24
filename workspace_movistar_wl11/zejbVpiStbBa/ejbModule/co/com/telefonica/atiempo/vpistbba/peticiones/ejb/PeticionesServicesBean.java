package co.com.telefonica.atiempo.vpistbba.peticiones.ejb;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import co.com.atiempo.dto.AgendaScDTO;
import co.com.atiempo.dto.AgrupVsDirecDto;
import co.com.atiempo.dto.AgrupacionAtisDTO;
import co.com.atiempo.dto.BuscadorPeticionVpiDTO;
import co.com.atiempo.dto.CausaDemoraDTO;
import co.com.atiempo.dto.CausalPsOcActividadDTO;
import co.com.atiempo.dto.DireccionAtisDTO;
import co.com.atiempo.dto.ElementoDTO;
import co.com.atiempo.dto.MensajeACSDTO;
import co.com.atiempo.dto.MensajeConfModemACSDTO;
import co.com.atiempo.dto.MensajeSMSACSDTO;
import co.com.atiempo.dto.PeticionAtisDTO;
import co.com.atiempo.dto.PeticionDTO;
import co.com.atiempo.dto.PeticionVsGrupoDTO;
import co.com.atiempo.dto.ProductoServicioPeticionDTO;
import co.com.atiempo.dto.PsPetCausalONovedad;
import co.com.atiempo.dto.PsVsFechaCierreDTO;
import co.com.atiempo.dto.PsVsOcVO;
import co.com.atiempo.dto.SubpeticionAtisDTO;
import co.com.atiempo.dto.SubpeticionCaracteristicasDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.*;
import co.com.telefonica.atiempo.interfaces.atiempo.Address;
import co.com.telefonica.atiempo.interfaces.atiempo.Characteristic;
import co.com.telefonica.atiempo.interfaces.atiempo.Group;
import co.com.telefonica.atiempo.interfaces.atiempo.Group1;
import co.com.telefonica.atiempo.interfaces.atiempo.SubRequest;
import co.com.telefonica.atiempo.interfaces.atiempo.SubRequest1;
import co.com.telefonica.atiempo.interfaces.atiempo.TR001E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR001S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR052E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR137S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR703S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR705S;
import co.com.telefonica.atiempo.interfaces.atiempo.TRSMSE;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.SessionBeanAdapter;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.converters.PeticionConvertidor;
import co.com.telefonica.atiempo.vpistbba.dto.converters.ProductoServicioPeticionConvertidor;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.CierrePeticionATISMQ;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultarCliPotConstructoraMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocal;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocalHome;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.BuscadorPeticionUtiles;
import com.telefonica_chile.atiempo.utiles.FamiliaOPComercial;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;


/**
 * Bean implementation class for Enterprise Bean: PeticionesServices
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class PeticionesServicesBean extends SessionBeanAdapter implements PeticionesInterfaces
{
    private Logger log=Logger.getLogger (PeticionesServicesBean.class);
    
    private static String tokenActuacionCCF="AF:";
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
//    private Peticion_atisLocalHome peticionATISHome;
//    private PeticionLocalHome ;
//    private Regla_RetencionesLocalHome reglaRetencionHome;
//    // CR14525 - ana santos - inicio
//	  private CanalLocalHome canalHome;
//	  // CR14525 - ana santos - fin
//    
//    private Agrupacion_atisLocalHome agrupacionHome;
//    private LocalidadLocalHome localidadHome;
//    private DepartamentoLocalHome departamentoHome;
//    private CentralLocalHome centralHome;
//    private Direccion_atisLocalHome direccionHome;
//    private Subpeticion_atisLocalHome subPeticionHome;
//    private Subpeticion_caracteristicasLocalHome caracteristicaHome;
//    private Producto_servicio_peticionLocalHome psPetHome;
//    private Producto_servicioLocalHome producto_servicioLocalHome;
//    private Operacion_comercialLocalHome operacion_comercialHome;
//    private Familia_producto_servicioLocalHome familiaPsLocalHome ;
//	private Catalogo_causalLocalHome catalogo_causalHome ;
//	private RangoLocalHome rHome;
//	private Tecnico_peticionLocalHome cpHome; 
//	private UsuarioLocalHome usuarioHome;
//	private Servicio_basico_supl_conec6_apsc_lineaLocalHome ssbaHome;
//	private Tipo_pcLocalHome tipo_pcHome;
//	private Causal_ps_oc_actividadLocalHome causal_ps_oc_actividadHome;
//	private Causal_peticionLocalHome causal_peticionHome;
//	private Estado_ps_peticionLocalHome estado_ps_peticionHome;
//	private Estado_psLocalHome estado_psHome;
//	private Peticion_flujoLocalHome peticion_flujoHome;
//	private Traslado_solobaLocalHome traslado_solobaHome;
//	private SubsegmentoLocalHome subSegmentoHome;  
    
    private DBManager dbSeq;
    private SimpleDateFormat df;
    


	
    public PeticionesServicesBean () throws ATiempoAppEx
    {
    	
    }

	public void setSessionContext (SessionContext ctx)
	throws EJBException, RemoteException
	{
		super.setSessionContext (ctx) ;
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
		buscaHome ();
	}
    
	/*
	 * Metodo Generador de Home
	 */
	private void buscaHome ()
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
//			
//		try {
//
//			peticionATISHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
//			peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//			
//			// CR14525 - ana santos - inicio
//			canalHome = (CanalLocalHome) HomeFactory.getHome(CanalLocalHome.JNDI_NAME);
//			// CR14525 - ana santos - fin
//			
//			agrupacionHome = (Agrupacion_atisLocalHome) HomeFactory.getHome(Agrupacion_atisLocalHome.JNDI_NAME);
//			localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
//			departamentoHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
//			centralHome=(CentralLocalHome)HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
//			direccionHome = (Direccion_atisLocalHome) HomeFactory.getHome(Direccion_atisLocalHome.JNDI_NAME);
//			subPeticionHome = (Subpeticion_atisLocalHome) HomeFactory.getHome(Subpeticion_atisLocalHome.JNDI_NAME);
//			caracteristicaHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
//			psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
//			producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
//			operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
//			familiaPsLocalHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
//			catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
//			rHome = (RangoLocalHome) HomeFactory.getHome (RangoLocalHome.JNDI_NAME);
//			cpHome = (Tecnico_peticionLocalHome) HomeFactory.getHome (Tecnico_peticionLocalHome.JNDI_NAME);
//			ssbaHome = (Servicio_basico_supl_conec6_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_conec6_apsc_lineaLocalHome.JNDI_NAME);
//			tipo_pcHome=(Tipo_pcLocalHome) HomeFactory.getHome(Tipo_pcLocalHome.JNDI_NAME);
//			causal_ps_oc_actividadHome=(Causal_ps_oc_actividadLocalHome) HomeFactory.getHome(Causal_ps_oc_actividadLocalHome.JNDI_NAME);
//			causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
//			estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
//			catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
//			estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
//			usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
//			peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
//			traslado_solobaHome=(Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
//			subSegmentoHome=(SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
//		}
//		catch (NamingException e)
//		{
//			log.error(" Creacion de Local Home Nulls",e);
//		}
		}
    
	/*
	 * Metodo validador Home
	 */
//	private void validaHome ()
//	throws ATiempoAppEx
//	{
//		// Validacion de los Home
//		if( peticionATISHome==null || peticionHome==null || 
//		
//			// CR14525 - ana santos - inicio
//			canalHome==null ||
//			// CR14525 - ana santos - inicio
//		
//			agrupacionHome==null ||
//			localidadHome==null || departamentoHome==null || direccionHome==null ||
//			subPeticionHome==null || caracteristicaHome==null || psPetHome==null ||
//			producto_servicioLocalHome==null || operacion_comercialHome==null ||
//			familiaPsLocalHome==null || catalogo_causalHome==null || rHome==null || 
//			cpHome==null || traslado_solobaHome==null || centralHome==null || subSegmentoHome==null
//		)
//			throw new ATiempoAppEx (ATiempoAppEx.NAMING);
//	}
		
    public ArrayList salvarPeticionATIS (TR001S peticionAtisDTO) throws ATiempoAppEx	
    {    	
    		
        ArrayList nrosPets=new ArrayList ();
 		 
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
        try
        {
//    		TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
    		Peticion_atisLocalHome peticionATISHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
    		Agrupacion_atisLocalHome agrupacionHome = (Agrupacion_atisLocalHome) HomeFactory.getHome(Agrupacion_atisLocalHome.JNDI_NAME);
    		Direccion_atisLocalHome direccionHome = (Direccion_atisLocalHome) HomeFactory.getHome(Direccion_atisLocalHome.JNDI_NAME);
    		LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
    		DepartamentoLocalHome departamentoHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
    		Subpeticion_atisLocalHome subPeticionHome = (Subpeticion_atisLocalHome) HomeFactory.getHome(Subpeticion_atisLocalHome.JNDI_NAME);
    		Subpeticion_caracteristicasLocalHome caracteristicaHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
    	
			bExecution = BackendTraceUtil.initExecution(peticionAtisDTO, log);			
			bExecution.setNumPetAtis(new Long(peticionAtisDTO.getRequestNumber()));			
			bExecution.setIdMensajeOp(peticionAtisDTO.getId());
			bExecution.startOperation();
						
    		//validaHome();
            PeticionAtisDTO pet=new PeticionAtisDTO ();
            log.info("Entro la peticion Atis:"+peticionAtisDTO.getRequestNumber());
            
            pet.setCodPetCd (new Long (peticionAtisDTO.getRequestNumber ()));
            pet.setUsrAltNo (peticionAtisDTO.getEmittingUser ());
            pet.setFecSctPetFf (new Timestamp (peticionAtisDTO.getEmittingDate ().getTime ()));
            pet.setObsPetDs (peticionAtisDTO.getObservations ());
			String nombreCliente=peticionAtisDTO.getClientName();
			if(nombreCliente!=null)
				nombreCliente=nombreCliente.trim();
			if(nombreCliente.length()>40)
				nombreCliente=nombreCliente.substring(0,40);
            pet.setNomDs (nombreCliente);
            pet.setPriApeDs (peticionAtisDTO.getClientLastName ());
			pet.setSegApeDs(peticionAtisDTO.getClientSecondLastName());
			 
            pet.setCodSgmCliCd (new Long (peticionAtisDTO.getClientSegmentCode ()));
            pet.setCodSbgCliCd(new Long(peticionAtisDTO.getClientSubSegmentCode()));
            
            pet.setCodSgmCtaCd(new Long(peticionAtisDTO.getAccountSegmentCode()));
            pet.setCodSbgCtaCd(new Long(peticionAtisDTO.getAccountSubsegmentCode()));
            
            pet.setTipDocCd (peticionAtisDTO.getClientDocumentType());
            pet.setTipCliCd(peticionAtisDTO.getClientType());
            
			pet.setNomIntDs(peticionAtisDTO.getSubmitterName());
            pet.setPriApeIntDs(peticionAtisDTO.getSubmitterLastName ());
            pet.setSegApeIntDs(peticionAtisDTO.getSubmitterSecondLastName ());
            pet.setTelCotDs (peticionAtisDTO.getContactPhone ());
            pet.setCodCnlVenCd (new Long (peticionAtisDTO.getSaleChannel ()));
            
            /*
             * CR 00024071 - 2009/04/21
             * 		Se quitan los caracteres especiales del numero de documento del cliente - German P.  
             */
            String nrClientDoc = peticionAtisDTO.getClientDocumentNumber();
            nrClientDoc = replaceSpecialChars(nrClientDoc);
            
            pet.setNumDocCliCd(nrClientDoc);
            
            pet.setDigCtlDocCd(peticionAtisDTO.getClientDocumentVerification()); 
            pet.setCodCliCd(new Long(peticionAtisDTO.getClientCode())); // AT-2089 -- Pablo L -- 16/04/2009
            pet.setCodCtaCd(new Long(peticionAtisDTO.getAccountCode()));
            Peticion_atisLocal peticion_atisLocal=peticionATISHome.create (pet);
            long codpetcd=pet.getCodPetCd ().longValue ();
            int  codagrsubnu;
            int  codsubcd;
            for (Iterator i = peticionAtisDTO.getGroupings ().iterator (); i.hasNext ();)
            {
                Group agrupacionDTO = (Group) i.next ();
                
                //int tipo=chequearAgrupacion(agrupacionDTO);
                //if(tipo==tipoAgrupacionNoDeterminado)
				//	throw new ATiempoAppEx ("No es posible determinar el tipo de Agrupacion para la peticion:"+peticionAtisDTO.getRequestNumber()) ;
                AgrupacionAtisDTO at=new AgrupacionAtisDTO ();
                at.setCodPetCd (pet.getCodPetCd ());
                at.setCpsAgrSubFf (new Timestamp (agrupacionDTO.getCommitmentDate ().getTime ()));
                at.setObsAgrSubDs (agrupacionDTO.getObservations ());
                at.setCodAgrSubNu (new Integer (agrupacionDTO.getCode ()));
                at.setAgrSubPdrCd(new Integer(agrupacionDTO.getFatherGroupCode()));
                at.setCanArgHijNu(new Integer (agrupacionDTO.getChildrenGroupCount ()));
                
                log.debug("Se pasa a guardar el número de Identificador PC Linea: " + agrupacionDTO.getComercialProductIdentificationNumber());
                at.setNumIdeNu (agrupacionDTO.getComercialProductIdentificationNumber ());
                log.debug("Se guardó el número de Identificador PC Linea: " + at.getNumIdeNu());
                
                // Granite - adocarmo
                //log.debug(">>>>>>>>>>>>>>>>NUM IDE NU:" + at.getNumIdeNu());
                
                at.setIdeProCmrCd (agrupacionDTO.getComercialProductIdentification ());
                at.setTipOprCmrCd (new Long (agrupacionDTO.getComercialOperationType ()));
                at.setCodProCmrCd (new Long (agrupacionDTO.getComercialProductCode ()));
                at.setProCmrPdrSn (new Long (agrupacionDTO.getFatherComercialProductCode ()));
                at.setTipProCd (new Long (agrupacionDTO.getComercialProductType ()));
                at.setSbtProCmrCd (new Long (agrupacionDTO.getComercialProductSubtype ()));
                at.setCodTipUsoCd (new Long (agrupacionDTO.getUseTypeCode ()));
                at.setNomTipUsoNo (agrupacionDTO.getUseTypeName ());
                codagrsubnu=at.getAgrSubPdrCd ().intValue ();
                Agrupacion_atisLocal agrupacion_atisLocal=agrupacionHome.create (peticion_atisLocal,at);
				agrupacion_atisLocal.setFath_iden_line(agrupacionDTO.getFatherIdentificationLine());
                if(agrupacionDTO.getAddress ()==null)
                    throw new ATiempoAppEx ("No viene direccion Asociada a la Agrupacion.");
                if (agrupacionDTO.getSubRequests ()==null)
                    throw new ATiempoAppEx ("No vienen Ps asociados de la Agrupacion.");
                Address direccionDTO = agrupacionDTO.getAddress ();
                LocalidadLocal localidadLocal=localidadHome.findByPrimaryKey (new LocalidadKey (direccionDTO.getCityCode ()));
                DepartamentoLocal departamentoLocal=departamentoHome.findByPrimaryKey (new DepartamentoKey (direccionDTO.getDepartmentCode ()));
                if(localidadLocal==null || departamentoLocal==null)
					throw new ATiempoAppEx("Codigo de Localidad y/o Departamento no validos.Localidad:"+direccionDTO.getCityCode ()+" -- Departamento:"+direccionDTO.getDepartmentCode ());
                DireccionAtisDTO dDto=new DireccionAtisDTO ();
                dDto.setNumCalNu (direccionDTO.getStreetNumber ());
                dDto.setNomCalDs (direccionDTO.getStreetName ());
                dDto.setDirTipVia1 (direccionDTO.getPathType ());
                dDto.setDirNroVia1 (String.valueOf (direccionDTO.getPathNumber ()));
                dDto.setDirLt1Via1 (direccionDTO.getFirstPathCharacters ());
                dDto.setDirLt2Via2 (direccionDTO.getSecondPathCharacters ());
                dDto.setDirZonVia1 (direccionDTO.getPathZone ());
                dDto.setDirTipVia2 (String.valueOf (direccionDTO.getPathNumber ()));
                dDto.setDirZonVia2 (direccionDTO.getPathZone2 ());
                dDto.setTipCalAtisCd (direccionDTO.getStreetType ());
                dDto.setDirTipLg1 (direccionDTO.getPlaceType1 ());
                dDto.setDirNroLg1 (direccionDTO.getPlaceNumber1 ());
                dDto.setDirTipLg2 (direccionDTO.getPlaceType2 ());
                dDto.setDirNroLg2 (direccionDTO.getPlaceNumber2 ());
                dDto.setDirTipLg3 (direccionDTO.getPlaceType3 ());
                dDto.setDirNroLg3 (direccionDTO.getPlaceNumber3 ());
                dDto.setDscCmpPriDs (direccionDTO.getComplementsDescription1 ());
                dDto.setDscCmpSegDs (direccionDTO.getComplementsDescription2 ());
                dDto.setNomsloNo (direccionDTO.getSubCityName ());
                dDto.setCodExtLocCd (direccionDTO.getExternalCityCode ());
                dDto.setCodTerCd (direccionDTO.getTerritory ());
                Direccion_atisLocal direccion_atisLocal=direccionHome.create (agrupacion_atisLocal,dDto,departamentoLocal,localidadLocal);
                //superPeticionDto.addDireccionAtis (direccionDTO,agrupacionDTO.getSubRequests (),at.getCodAgrSubNu ());
                for (Iterator j=agrupacionDTO.getSubRequests ().iterator (); j.hasNext (); )
                {
                    SubRequest subPeticionDTO = (SubRequest) j.next ();
                    SubpeticionAtisDTO spa=new SubpeticionAtisDTO ();
                    spa.setCodProSerCd (new Long (subPeticionDTO.getProductServiceCode ()));
                    spa.setNomProSerNo (subPeticionDTO.getProductServiceName ());
                    spa.setCodOprCmrCd (new Long (subPeticionDTO.getComercialOperation ()));
                    spa.setTipProSerCd (new Long (subPeticionDTO.getProductServiceType ()));
                    spa.setCanProSubNu (new Long (subPeticionDTO.getQuantityOfProductServices ()));
                    spa.setIniSerProFf (new Timestamp (subPeticionDTO.getServiceInitialDate ().getTime ()));
                    spa.setFinSerProFf (new Timestamp (subPeticionDTO.getServiceEndDate ().getTime ()));
                    spa.setObsSubDs (subPeticionDTO.getObservations ());
                    spa.setCodSubCd (new Integer (new Long (subPeticionDTO.getCode ()).intValue ()));
                    spa.setNumCtoNu (new Long (subPeticionDTO.getContractNumber ()));
					spa.setIdSrvRefCd(subPeticionDTO.getServiceReferenceId());
                    codsubcd=spa.getCodSubCd ().intValue ();
                    Subpeticion_atisLocal subPeticion = subPeticionHome.create (agrupacion_atisLocal,spa);
                    
                    if(subPeticionDTO.getCharacteristic ()==null)
                        continue;
                    for(Iterator k=subPeticionDTO.getCharacteristic ().iterator (); k.hasNext ();)
                    {
                        Characteristic caracteristicaDTO = (Characteristic) k.next ();
                        SubpeticionCaracteristicasDTO spc=new SubpeticionCaracteristicasDTO ();
                        spc.setCodCrcCd (new Long (caracteristicaDTO.getCode ()));
                        spc.setNomCrcNo (caracteristicaDTO.getName ());
                        spc.setCodTipDatCd (caracteristicaDTO.getDataType ());
                        spc.setCodValCrcCd (new Long (caracteristicaDTO.getCodeValue ()));
                        spc.setValIniCrcNo (caracteristicaDTO.getInitialValue ());
                        spc.setValFinCrcNo (caracteristicaDTO.getFinalValue ());
                        spc.setValRalCrcCd (caracteristicaDTO.getRealValue ());
                        spc.setLngCrcNu (new Long (caracteristicaDTO.getLength ()));
                        spc.setFmtVdoCrcNo (caracteristicaDTO.getFormat ());
                        spc.setMomInfCrcIn (caracteristicaDTO.getMoment ());
                        Subpeticion_caracteristicasLocal caracteristica = caracteristicaHome.create (subPeticion,spc);//crea el resgistro en subpeticioncaracteristicas
                    }
                }
            }
			log.info("Grabando Peticion Atiempo INICIADO. Pet Atis:"+peticionAtisDTO.getRequestNumber());
            salvarPeticionAtiempo(peticion_atisLocal,peticionAtisDTO,nrosPets);
            setearFamiliasYOpDePet(nrosPets);
            if (nrosPets !=null && nrosPets.size()>0) {
				String nums = String.valueOf(nrosPets.get(0));	
				for (int i = 1; i < nrosPets.size(); i++) {
					nums = nums + "," + nrosPets.get(i);
				}
				bExecution.setColumnOp(TraceManager.COL_NUM_PET_ATIEMPO,nums);
            } 
            //log.info("Grabando Peticion Atiempo LISTO. Pet Atis:"+peticionAtisDTO.getRequestNumber());
			log.info("Petición/es creada/s Tengo:"+nrosPets.size()+" peticiones distintas para la petición ATIS nro: " + peticionAtisDTO.getRequestNumber());
            return nrosPets;
        }
        catch (CreateException e)
        {
			bExecution.setErrorOp(true);
			logeaError(peticionAtisDTO);
			log.debug("Descripcion error.",e);
            throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);
        }
        catch (FinderException e)
        {
			bExecution.setErrorOp(true);
            logeaError(peticionAtisDTO);
			log.debug("Descripcion error.",e);
            throw new ATiempoAppEx (ATiempoAppEx.FINDER, e);
        }
        catch (NamingException e)
        {
			bExecution.setErrorOp(true);
			logeaError(peticionAtisDTO);
			log.debug("Descripcion error.",e);
            throw new ATiempoAppEx (ATiempoAppEx.NAMING, e);
        }
		catch (NumberFormatException e)
		{
			bExecution.setErrorOp(true);
			logeaError(peticionAtisDTO);
			log.debug("Descripcion error.",e);			
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT, e);
		}
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			logeaError(peticionAtisDTO);
			log.debug("Descripcion error.",e);	
			throw new ATiempoAppEx (ATiempoAppEx.TN_PROCESS, e);
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
    }
    
    }
    
	private void setearFamiliasYOpDePet(ArrayList nrosPets) throws FinderException, NamingException
	{
		PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		CentralLocalHome centralHome =(CentralLocalHome)HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
		
		for(Iterator iterator=nrosPets.iterator();iterator.hasNext();)
		{
			boolean tieneLB=false,tieneBA=false,tieneTV=false,tieneIC=false, tieneIT=false, tieneMNT=false;
			boolean tienePcLineaAlta=false,
			tienePcBAAlta=false,
			tienePcTVAlta=false,
			tieneIcAlta=false,
			tieneITAlta=false,
			tieneMNTAlta=false,
			tieneAsistRemAlta=false,
			tieneAsistenciaAlta=false,
			tieneVisAsistAlta=false,
			tieneSuspension=false,tieneReconexion=false,
			tieneTransTecnica=false,tieneReinstalacion=false,
			esRetiro=false;
			boolean tieneVoIP = false;
			
        	boolean tieneAsistencia=false;
        	boolean tieneVisAsist=false;
        	boolean tieneAsistRemota=false;
        	//idrincon familia ip
        	boolean tieneFamIP=false;
			
			Long nroPet=(Long) iterator.next();
			PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(nroPet));
			for(Iterator iterator2=local.getProducto_servicio_peticion().iterator();iterator2.hasNext();)
			{
				Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator2.next();
				Producto_servicioLocal ps=local2.getProducto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) ps.getFamilia_producto_servicio().getPrimaryKey();
				Operacion_comercialLocal opco=local2.getOperacion_comercial();
				int idFamiliaPs=familia_producto_servicioKey.faps_id.intValue();
				if (idFamiliaPs==familiaIntEquipado){
				    tieneIT=true;
				}else if(idFamiliaPs==familiaLinea || idFamiliaPs==familiaPcLinea || idFamiliaPs==familiaIP){
					tieneLB=true;
					//@idrincon req familia ip
					if(idFamiliaPs==familiaIP){
						tieneFamIP=true;
					}
					//fin
					//REQ BA NAKED se agrega las nuevas familias
				}else if(idFamiliaPs==familiaBandaAncha || idFamiliaPs==familiaPcBA ||  idFamiliaPs==familiaPcBANaked || idFamiliaPs==familiaBandaAnchaNaked || idFamiliaPs==familiaPcBANaked)
					tieneBA=true;
					//TODO PVR SE AGREGO LA NUEVA FAMILIA PVR AL GUARDAR LA SOLICITUD
				else if(idFamiliaPs==familiaTV || idFamiliaPs==familiaPcTV || idFamiliaPs==familiaTematicoTV || idFamiliaPs== familiaDecoTV ||idFamiliaPs==familiaDecoPVRTV || idFamiliaPs==familiaPaqueteTV || idFamiliaPs==familiaDecoHDTV)
					tieneTV=true;
				else if(idFamiliaPs==familiaIC)
					tieneIC=true;
				else if(idFamiliaPs==familiaBandaAnchaNaked)
					tieneVoIP = true;
				/**
			     * DAVID: se adiciona el siguiente if para RQ 28274, cobro incidencias.
			     */
				else if(idFamiliaPs==familiaMantenimiento)
					tieneMNT=true;
				else if(idFamiliaPs==familiaAsistencia)
					tieneAsistencia=true;
				else if(idFamiliaPs==familiaVisitaAsist)
					tieneVisAsist=true;
				else if(idFamiliaPs==familiaAsistRemota)
					tieneAsistRemota=true;
				if(idFamiliaPs==familiaPcLinea || idFamiliaPs == familiaIP)
				{
					if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
						tienePcLineaAlta=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("R"))//TRANSFERENCIA TECNICA
						tieneTransTecnica=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//REINSTALACION
						tieneReinstalacion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("R"))//RECONEXION
						tieneReconexion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("S"))//SUSPENSION
						tieneSuspension=true;
					//Modificado 8-5-2008 CR 10394  Bajar PC 4,5,6
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("B") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//RETIROS
						esRetiro=true;
				}
				//REQ NAKED se adiciona la familia de pc naked
//				if(idFamiliaPs==familiaPcPsBANaked)
				if(idFamiliaPs==familiaPcBA || idFamiliaPs==familiaPcBANaked || idFamiliaPs==familiaPcPsBANaked)
				{
					if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
						tienePcBAAlta=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("R"))//TRANSFERENCIA TECNICA
						tieneTransTecnica=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//REINSTALACION
						tieneReinstalacion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("R"))//RECONEXION
						tieneReconexion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("S"))//SUSPENSION
						tieneSuspension=true;
//					Modificado 8-5-2008 CR 10394  Bajar PC 4,5,6
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("B") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//RETIROS
						esRetiro=true;
				}
				if(idFamiliaPs==familiaPcTV)
				{
					if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
						tienePcTVAlta=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("R"))//TRANSFERENCIA TECNICA
						tieneTransTecnica=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//REINSTALACION
						tieneReinstalacion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("R"))//RECONEXION
						tieneReconexion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("S"))//SUSPENSION
						tieneSuspension=true;
//					Modificado 8-5-2008 CR 10394  Bajar PC 4,5,6
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("B") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//RETIROS
						esRetiro=true;
				}
				if(idFamiliaPs==familiaIC)
				{
					if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
						tieneIcAlta=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("R"))//TRANSFERENCIA TECNICA
						tieneTransTecnica=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//REINSTALACION
						tieneReinstalacion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("R"))//RECONEXION
						tieneReconexion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("S"))//SUSPENSION
						tieneSuspension=true;
				}
				if(idFamiliaPs==familiaIntEquipado)
				{
					if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
					    tieneITAlta=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("R"))//TRANSFERENCIA TECNICA
						tieneTransTecnica=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//REINSTALACION
						tieneReinstalacion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("R"))//RECONEXION
						tieneReconexion=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("S"))//SUSPENSION
						tieneSuspension=true;
					else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("B") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//RETIROS
						esRetiro=true;
				}
				/**
                 * DAVID: se adiciona el siguiente if para RQ 28274, cobro incidencias.
                 */
                if(idFamiliaPs==familiaMantenimiento)
                {
                    if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
                        tieneMNTAlta=true;           
                }
                if(idFamiliaPs==familiaAsistRemota)
                {
                    if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
                        tieneAsistRemAlta=true;           
                }
                if(idFamiliaPs==familiaAsistencia)
                {
                    if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
                        tieneAsistenciaAlta=true;           
                }
                if(idFamiliaPs==familiaVisitaAsist)
                {
                    if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
                        tieneVisAsistAlta=true;           
                }
			}
			/*********Estos son los valores que van a ir en el filtro de operacion comercial*************/
			if(tieneReconexion)
				local.setTica_id("R");
			else if(tieneSuspension)
				local.setTica_id("S");
			else if(tienePcLineaAlta)
				local.setTica_id("A");
			else if(tienePcBAAlta)
				local.setTica_id("A");
			else if(tienePcTVAlta)
				local.setTica_id("A");
			else if(tieneIcAlta)
				local.setTica_id("A");
			else if(tieneITAlta)
				local.setTica_id("A");
			else if(tieneTransTecnica)
				local.setTica_id("T");
			else if(tieneReinstalacion)
				local.setTica_id("P");
			else if(esRetiro)
				local.setTica_id("B");
			/**
             * DAVID: se adiciona el siguiente if para RQ 28274, cobro incidencias.
             */
			else if(tieneMNTAlta)
				local.setTica_id("A");
			else if(tieneAsistRemAlta)
				local.setTica_id("A");
			else if(tieneAsistenciaAlta)
				local.setTica_id("A");
			else if(tieneVisAsistAlta)
				local.setTica_id("A");
			else if(tieneVoIP && !tieneBA) //Se genera como posventa
				local.setTica_id("P");
			else
				local.setTica_id("P");
			/********************************************************************************************/
			
			//Este es el valor que va a ir en el filtro de familia 
			local.setPeti_id_instancia(logicaFamiBI(tieneLB,tieneBA,tieneTV,tieneIC,tieneIT,tieneMNT,tieneAsistencia,tieneVisAsist,tieneAsistRemota,tieneFamIP));
			//Este es un valor fijo de central para television
			if(local.getPeti_id_instancia().equals(TV))
			{
				if(centralHome==null)
					centralHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
				CentralLocal centralLocal=centralHome.findByPrimaryKey(new CentralKey(new Long(centralGraldeTV)));
				local.setFk_03_central(centralLocal);
			}
			else if(local.getPeti_id_instancia().equals(TVIC))
			{
				if(centralHome==null)
					centralHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
				CentralLocal centralLocal=centralHome.findByPrimaryKey(new CentralKey(new Long(centralGraldeTV)));
				local.setFk_03_central(centralLocal);
			}
		}
	}

	private void logeaError(TR001S peticionAtisDTO)
	{
		try
		{
			log.error("Falla en la peticion Atis nº :"+peticionAtisDTO.getRequestNumber());
			for (Iterator i = peticionAtisDTO.getGroupings ().iterator (); i.hasNext ();)
			{
				Group agrupacionDTO = (Group) i.next ();
				log.error("Codigo de Agrupacion:"+agrupacionDTO.getCode());
				for (Iterator j=agrupacionDTO.getSubRequests ().iterator (); j.hasNext (); )
				{
					SubRequest subPeticionDTO = (SubRequest) j.next ();
					log.error("Ps informado en la Subpeticion"+subPeticionDTO.getProductServiceCode());
					log.error("Operacion comercial informada:"+subPeticionDTO.getComercialOperation());
				}
		   }
		}
		catch(Exception e)
		{
			log.error("Exception",e);
		}
	}

	private Integer retornaTipoAgrupacion(long idFamilia)
	{
		if (idFamilia == familiaLinea || idFamilia==familiaPcLinea || idFamilia==familiaIP)
			return tipoAgrupacionLinea ;
		//REQ BA NAKED se agrega la familia del PC y PS Naked 
		else if(idFamilia == familiaBandaAncha || idFamilia==familiaPcBA ||idFamilia==familiaPcBANaked ||idFamilia==familiaBandaAnchaNaked  || idFamilia==familiaPcPsBANaked)
			return tipoAgrupacionBA;
		else if (idFamilia==familiaTV || idFamilia==familiaPcTV || idFamilia==familiaDecoTV ||idFamilia==familiaDecoPVRTV|| idFamilia==familiaTematicoTV || idFamilia==familiaPaqueteTV || idFamilia==familiaDecoHDTV)
			return tipoAgrupacionTV ;
		else if (idFamilia==familiaIC)
			return tipoAgrupacionIC;
		/**
	     * DAVID: se adiciona el siguiente if para RQ 28274, cobro incidencias.
	     */
		else if (idFamilia==familiaMantenimiento)
			return tipoAgrupacionMNT;
		else if (idFamilia==familiaAsistencia)
			return tipoAgrupacionAsist;
		else if (idFamilia==familiaVisitaAsist)
			return tipoAgrupacionVisAsist;
		else if (idFamilia==familiaAsistRemota)
			return tipoAgrupacionAsistRem;
		else if (idFamilia==familiaIntEquipado)
			return tipoAgrupacionIT;
		else if (idFamilia==familiaPresenciaDigital)
			return tipoAgrupacionPDG;
	/**
	     * dcardena: se adiciona el siguiente if para RQ Napster Music.
	     */
		else if (idFamilia==familiaPS_SVA)
			return tipoAgrupacionPS_SVA;
		else if (idFamilia==familiaPS_GVP || idFamilia == familiaPC_GVP)
			return tipoAgrupacionPS_GVP;
		return tipoAgrupacionNoDeterminado;
	}

	private void salvarPeticionAtiempo(Peticion_atisLocal peticion_atisLocal,TR001S pet,ArrayList nroPets) throws CreateException, ATiempoAppEx, FinderException, NamingException
	{	
		ArrayList listPetAtiempoVsGroup=new ArrayList();
		ArrayList listAgrupacionVsDireccion=new ArrayList();
		Long nroPeticionAtis=new Long(pet.getRequestNumber());
		log.info("Nro de Agrupaciones a Almacenar:"+pet.getGroupings().size()+". Pet Atis:"+pet.getRequestNumber());
		for(Iterator iterator=pet.getGroupings().iterator();iterator.hasNext();)
		{
			Group group=(Group) iterator.next();
			int valTras=esTraslado(group);
			if(valTras!=0)//Es un traslado
			{
				log.info("Si es un traslado . Pet Atis:"+pet.getRequestNumber());
				if(!estaIngresadoGrupo(group,listAgrupacionVsDireccion)) 
				{
					log.info("Procesando un traslado . Pet Atis:"+pet.getRequestNumber());
					ArrayList retorno=agrupacionComplemento(pet,group);
					if(retorno==null)
					{
						log.info("Estoy en el caso de que tengo una agrupacion de Alta o Baja solo BA en peticiones atis distintas . Pet Atis:"+pet.getRequestNumber());
						ingresarAgrupacion(peticion_atisLocal,pet,group,listAgrupacionVsDireccion,listPetAtiempoVsGroup,nroPets);
					}
					else
					{
						log.info("Agrupacion Complemento Encontrada . Pet Atis:"+pet.getRequestNumber());
						String valSoloBa=(String)retorno.get(0);
						Group grupoRetorno=(Group)retorno.get(1);
						if(valSoloBa.equals("1"))
						{
							ingresarAgrupacion(peticion_atisLocal,pet,group,listAgrupacionVsDireccion,listPetAtiempoVsGroup,nroPets);
							ingresarAgrupacion(peticion_atisLocal,pet,grupoRetorno,listAgrupacionVsDireccion,listPetAtiempoVsGroup,nroPets);
						}
						else{
							//ingresarAgrupaciones(peticion_atisLocal,pet,group,grupoRetorno,listAgrupacionVsDireccion,listPetAtiempoVsGroup,nroPets,valTras);
							//@idrincon modificacion para agrupacion de peticion prueba
							//se valida que la peticion sea un traslado de trios que tenga cuatro grupos
							if(esTrasladoDeTrios( pet, listAgrupacionVsDireccion, group, grupoRetorno)){
								ingresarPeticionTrasladoTrio(pet, peticion_atisLocal, group, grupoRetorno, listAgrupacionVsDireccion, listPetAtiempoVsGroup, nroPets, valTras);
							}else{
								ingresarAgrupaciones(peticion_atisLocal,pet,group,grupoRetorno,listAgrupacionVsDireccion,listPetAtiempoVsGroup,nroPets,valTras);
							}
							//fin 
						}
					}	
				}
				else
				{
					log.info("Grupo Traslado ya ingresado . Pet Atis:"+pet.getRequestNumber());	
				}
			}
			else
			{
				log.info("Procesando NO traslado . Pet Atis:"+pet.getRequestNumber());
				ingresarAgrupacion(peticion_atisLocal,pet,group,listAgrupacionVsDireccion,listPetAtiempoVsGroup,nroPets);
			}
		}
	}

	private boolean estaIngresadoGrupo(Group group, ArrayList listAgrupacionVsDireccion)
	{
		int codagru=group.getCode();
		for(Iterator iterator=listAgrupacionVsDireccion.iterator();iterator.hasNext();)
		{
			AgrupVsDirecDto agrupVsDirecDto=(AgrupVsDirecDto) iterator.next();
			if(agrupVsDirecDto.getCod_agru_sub_nu()==codagru)
				return true;
		}
		log.info("NO Hay Agrupacion con igual Dir Ingresada");
		return false;
	}
	
	/**
	 * Busca la agrupacion compleneto de la agrupacion que esta recorriendo
	 * @param pet
	 * @param group
	 * @return
	 * @throws ATiempoAppEx
	 */
	private ArrayList agrupacionComplemento(TR001S pet, Group group) throws ATiempoAppEx
	{
		log.info("Buscando Agrupacion complemento . Pet Atis:"+pet.getRequestNumber());
		String comercialProductIdentificationNumber = group.getComercialProductIdentificationNumber();
		for(Iterator iterator=pet.getGroupings().iterator();iterator.hasNext();)//reocrre los grupos de la peticion
		{
			Group groupOtro=(Group) iterator.next();
			log.info("Comparando Buscando Grupo Complemento Para Group:"+group.getCode()+" con "+groupOtro.getCode() +". Pet Atis:"+pet.getRequestNumber());
			if(group.getCode()!=groupOtro.getCode())//valida que el grupo sea diferente al actual para el complemento
			{
				log.info("Comparando "+comercialProductIdentificationNumber+" con "+groupOtro.getComercialProductIdentificationNumber() +". Pet Atis:"+pet.getRequestNumber());
				if(comercialProductIdentificationNumber.equals(groupOtro.getComercialProductIdentificationNumber()))
				{
					log.info("Encontre Agrupacion Complemento por NUMIDENU:"+comercialProductIdentificationNumber+". Pet Atis:"+pet.getRequestNumber());
					ArrayList retorno=new ArrayList();
					retorno.add("0");//posicion 0 si son iguales
					retorno.add(groupOtro);//posicion 1 retorna el objeto grupoortro que tiene el mismo comercial-product-identification-number
					return retorno;
				}
				else
					log.info("Aun no encuentro Agrupacion Complemento. Pet Atis:"+pet.getRequestNumber());
			}
		}
		log.info("No tengo la agrupacion complemento puede ser un traslado solo BA. Pet Atis:"+pet.getRequestNumber());
		boolean esSoloBa=esSoloBA(group);
		if(esSoloBa)
		{
			int codeAgrupacionUno=group.getCode();
			boolean esAgrupacionSolaBAAlta=tipoAgrupacionSoloBA(group);
			for(Iterator iterator=pet.getGroupings().iterator();iterator.hasNext();)
			{
				Group otro=(Group) iterator.next();
				int codeAgrupacionDos=otro.getCode();
				if(codeAgrupacionUno==codeAgrupacionDos)
					continue;//Ignoro la misma Agrupacion
				if(esAgrupacionSolaBAAlta) //Tengo la agrupacion de traslado solo BA Alta tengo que encontrar la agrupacion de baja 
				{
					log.info("Tengo la agrupacion de traslado solo BA Alta tengo que encontrar la agrupacion de baja . Pet Atis:"+pet.getRequestNumber());
					if(esSoloBA(otro))
					{
						if(!tipoAgrupacionSoloBA(otro))
						{
							//encontre una agrupacion de solo Ba de Baja tengo que ver si es la que le corresponde
							log.info("encontre una agrupacion de solo Ba de Baja tengo que ver si es la que le corresponde. Pet Atis:"+pet.getRequestNumber());
							String numIdeNuBaja=otro.getComercialProductIdentificationNumber();
							for(Iterator iterator2=group.getSubRequests().iterator();iterator2.hasNext();)
							{
								SubRequest request=(SubRequest) iterator2.next();
								for(Iterator iterator3=request.getCharacteristic().iterator();iterator3.hasNext();)
								{
									Characteristic characteristic=(Characteristic) iterator3.next();
									if(characteristic.getCode()!=new Long(VpistbbaConfig.getVariable("NUMTELEF")).longValue())
										continue;
									if(characteristic.getRealValue()!=null && characteristic.getRealValue().equals(numIdeNuBaja))
									{
										ArrayList retorno=new ArrayList();
										if(esSoloBa)
											retorno.add("1");
										else
											retorno.add("0");
										retorno.add(otro);	
										return retorno; 
									}
								}
							}
						}
					}
				}
				else //tengo la agrupacion de traslado solo BA Baja tengo que encontrar la agrupacion de Alta 
				{
					log.info("tengo la agrupacion de traslado solo BA Baja tengo que encontrar la agrupacion de Alta. Pet Atis:"+pet.getRequestNumber());
					if(esSoloBA(otro))
					{
						if(tipoAgrupacionSoloBA(otro))
						{
							//encontre una agrupacion de solo ba de alta tengo que ver si es que le corresponde		
							log.info("encontre una agrupacion de solo ba de alta tengo que ver si es que le corresponde. Pet Atis:"+pet.getRequestNumber());
							String numIdeNuBaja=comercialProductIdentificationNumber;
						
							for(Iterator iterator2=otro.getSubRequests().iterator();iterator2.hasNext();)
							{
								SubRequest request=(SubRequest) iterator2.next();
								for(Iterator iterator3=request.getCharacteristic().iterator();iterator3.hasNext();)
								{
									Characteristic characteristic=(Characteristic) iterator3.next();
									if(characteristic.getCode()!=new Long(VpistbbaConfig.getVariable("NUMTELEF")).longValue())
										continue;
									if(characteristic.getRealValue()!=null && characteristic.getRealValue().equals(numIdeNuBaja))
									{
										ArrayList retorno=new ArrayList();
										if(esSoloBa)
											retorno.add("1");
										else
											retorno.add("0");
										retorno.add(otro);	
										return retorno;
									}
								}
							}
						}
					}
				}
			}
			return null;
		}
		else
			throw new ATiempoAppEx("Error de Proceso no se puede determinar agrupacion complemento");
	}

	private boolean tipoAgrupacionSoloBA(Group group) throws ATiempoAppEx //retorna false si es Baja , true si es Alta
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			for(Iterator iterator=group.getSubRequests().iterator();iterator.hasNext();)
			{
				SubRequest subPeticionDTO = (SubRequest) iterator.next ();
				Operacion_comercialLocal operacion_comercialLocal=operacion_comercialHome.findByPrimaryKey(new Operacion_comercialKey(new Long(subPeticionDTO.getComercialOperation())));
				String opco_tipo = operacion_comercialLocal.getOpco_tipo();
				if(opco_tipo!=null)
				{
					if(opco_tipo.equals("A") || opco_tipo.equals("ACP"))
						return true;
				}
			}
			return false;
		}
		catch(FinderException fe)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,fe);
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
	}
	}

	private boolean esSoloBA(Group group) throws ATiempoAppEx 
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			for(Iterator iterator=group.getSubRequests().iterator();iterator.hasNext();)
			{
				SubRequest subPeticionDTO = (SubRequest) iterator.next ();
				long ps_id=subPeticionDTO.getProductServiceCode ();
				Producto_servicioLocal ps=producto_servicioLocalHome.findByPrimaryKey (new Producto_servicioKey (new Long (ps_id)));
				Familia_producto_servicioKey key=(Familia_producto_servicioKey) ps.getFamilia_producto_servicio ().getPrimaryKey ();
				int idFam=key.faps_id.intValue();
				// REQ BA NAKED se agrega la familia pc y ps naked
				if(idFam!=familiaPcBA && idFam!=familiaBandaAncha && idFam!=familiaBandaAnchaNaked && idFam!=familiaPcBANaked && idFam!=familiaPcPsBANaked )
					return false;
			}
			return true;
		}
		catch(FinderException fe)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,fe);
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
	}
	}

	private void ingresarAgrupacion(Peticion_atisLocal peticion_atisLocal,TR001S pet, Group group, ArrayList listAgrupacionVsDireccion,ArrayList listPetAtiempoVsGroup,ArrayList nroPets) throws CreateException, ATiempoAppEx, FinderException, NamingException
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
		LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
		DepartamentoLocalHome departamentoHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
		PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
		Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		
		log.info("Ingresando Agrupacion Normal. Pet Atis:"+pet.getRequestNumber());
		if(estaIngresadoGrupoConIgualDir(group,listAgrupacionVsDireccion))
		{
			log.info("Si esta ingresado Grupo con la Misma Dir. Pet Atis:"+pet.getRequestNumber());
			ArrayList idsGruposIngresados=recuperaGrupoConMismaDir(group,listAgrupacionVsDireccion);
			ArrayList nochocantes=new ArrayList();
			boolean choca=false;
			for(Iterator iterGruposIngresados=idsGruposIngresados.iterator();iterGruposIngresados.hasNext();)
			{
				Integer id=(Integer) iterGruposIngresados.next();
				int idGrupoIngresado=id.intValue();
				if(chocanGrupos(idGrupoIngresado,group,pet))
				{
					choca=true;
					break;
				}
				else
					nochocantes.add(id);
			}
			if(choca)
			{
				LocalidadLocal localidadLocal=localidadHome.findByPrimaryKey (new LocalidadKey (group.getAddress ().getCityCode ()));
				DepartamentoLocal departamentoLocal=departamentoHome.findByPrimaryKey (new DepartamentoKey (group.getAddress ().getDepartmentCode ()));
				long peti_numero=dbSeq.seqNextValLong ("CORRELATIVO_PETICION");
				log.info("Chocan Grupos. Pet Atis:"+pet.getRequestNumber() + " Creando peticion ATiempo peti_numero=" + peti_numero);
				PeticionLocal peticionLocal=peticionHome.create (peti_numero,peticion_atisLocal,localidadLocal,departamentoLocal);
//				Seteo los datos del cliente, segmento, contacto y direccion en la tabla peticion
				PeticionConvertidor.setEntity (peticionLocal,group);
				
				//CR-14657 -Granite
				//peticionLocal.setGranite_code(group.getAddress().getGraniteCode());
				String actuacion="";
				for(Iterator iterator=group.getSubRequests().iterator();iterator.hasNext();)
				{ 
					SubRequest subPeticionDTO = (SubRequest) iterator.next ();
					long ps_id=subPeticionDTO.getProductServiceCode ();
					Producto_servicioLocal ps=producto_servicioLocalHome.findByPrimaryKey (new Producto_servicioKey (new Long (ps_id)));
					Operacion_comercialLocal op=operacion_comercialHome.findByPrimaryKey (new Operacion_comercialKey (new Long (subPeticionDTO.getComercialOperation ())));

					long correlativo=dbSeq.seqNextValLong ("CORRELATIVO_PRODUCTO_SERVICIO_PETICION");
					Producto_servicio_peticionLocal producto_servicio_peticionLocal=psPetHome.create (new Long (correlativo),peticionLocal,op,ps);
					ProductoServicioPeticionConvertidor.setEntity (producto_servicio_peticionLocal,subPeticionDTO,new Long(pet.getRequestNumber()),new Integer(group.getCode()));
					Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
					Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
					Integer tipo=primerTipoAgrupacion(agrupacion_atisLocal);
					log.debug("Tipo Agrupación: "+tipo);
					if(tipo.equals(tipoAgrupacionLinea) || tipo.equals(tipoAgrupacionBA) || tipo.equals(tipoAgrupacionAsist))
						peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal.getNum_ide_nu());
					else if(tipo.equals(tipoAgrupacionTV))
						peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal.getNum_ide_nu());
					else if(tipo.equals(tipoAgrupacionIC))
						peticionLocal.setNum_ide_nu_ic(agrupacion_atisLocal.getNum_ide_nu());
					/**
					 * DAVID: se adiciona el siguiente if para RQ 28274, cobro
					 * incidencias.
					 */
					else if (tipo.equals(tipoAgrupacionMNT)) {
						if (ps_id == psMNTTV) {
							peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal
									.getNum_ide_nu());
						} else {
							peticionLocal
									.setNum_ide_nu_stb(agrupacion_atisLocal
											.getNum_ide_nu());
						}

					} else if (tipo.equals(tipoAgrupacionPDG))
						peticionLocal.setNum_ide_nu_pdg(agrupacion_atisLocal
								.getNum_ide_nu());
					else if (tipo.equals(tipoAgrupacionIT))
						peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal
								.getNum_ide_nu());
					else if(tipo.equals(tipoAgrupacionPS_SVA))
					{
//						validamos si el numero comienza pot TV
						if(agrupacion_atisLocal.getNum_ide_nu().startsWith("TV"))
						{	
							log.debug("Hay agrupacion Napster con telefono en TV: "+agrupacion_atisLocal.getNum_ide_nu());
							//si contiene datos se debe eliminar TV y el numero final
							 peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal.getNum_ide_nu());
														
						}else{
							log.debug("Hay agrupacion Napster telefono en STB: "+agrupacion_atisLocal.getNum_ide_nu());
							//si hay un numero se setea el phone_number
							  peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal.getNum_ide_nu());
						}					
					}
//Movistar Play se agrega Num_ide_num_mp el valor del idpc
				    else if(tipo.equals(tipoAgrupacionPS_GVP))
				    	peticionLocal.setNum_ide_nu_mp(agrupacion_atisLocal.getNum_ide_nu());
					peticionLocal.setJornada_agnd_sc(pet.getJorney());
					peticionLocal.setCoord_x_agnd_sc(pet.getCoordinateX());
					peticionLocal.setCoord_y_agnd_sc(pet.getCoordinateY());
					if (actuacion.equals("") || actuacion.equals("0")){
						actuacion = extraerInf(subPeticionDTO, tokenActuacionCCF,peticionLocal,peti_numero);
					}
				}
				
				PeticionVsGrupoDTO peticionVsGrupoDTO=new PeticionVsGrupoDTO();
				peticionVsGrupoDTO.setNroPeticion(new Long(peti_numero));
				peticionVsGrupoDTO.setIdGrupo(new Integer(group.getCode()));
				listPetAtiempoVsGroup.add(peticionVsGrupoDTO);
				nroPets.add(new Long(peti_numero));
				peticionLocal.setCod_cli_cd(peticion_atisLocal.getCod_cli_cd());
			} else {
				//se lo tengo que agregar a uno.
				log.info("No Chocan Grupos. Pet Atis:"+pet.getRequestNumber());
				for(Iterator iterator=listPetAtiempoVsGroup.iterator();iterator.hasNext();)
				{
					PeticionVsGrupoDTO peticionVsGrupoDTO=(PeticionVsGrupoDTO) iterator.next();		
					Long idPeticion=peticionVsGrupoDTO.getNroPeticion();
					
					//saco el primero no chocante y le agrego los peses a ese
					int idGrupoIngresado=0;
					
					for(Iterator iterator2=nochocantes.iterator();iterator2.hasNext();)
					{
						Integer noChoc=(Integer) iterator2.next();
						idGrupoIngresado=noChoc.intValue();
						break;
					}
					
					if(idGrupoIngresado==0)
						throw new ATiempoAppEx("Error de Proceso en Salvado Atiempo.No se encuentra grupo no chocante:"+pet.getRequestNumber());
					
					if(idGrupoIngresado!=peticionVsGrupoDTO.getIdGrupo().intValue())
						continue;
					PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(idPeticion));
					agregarPesesDeGrupoNuevoAGrupoIngresado(pet.getRequestNumber(),peticionLocal,group);
					PeticionVsGrupoDTO peticionVsGrupoDTO2=new PeticionVsGrupoDTO();
					peticionVsGrupoDTO2.setNroPeticion(idPeticion);
					peticionVsGrupoDTO2.setIdGrupo(new Integer(group.getCode()));
					listPetAtiempoVsGroup.add(peticionVsGrupoDTO2);
					peticionLocal.setCod_cli_cd(peticion_atisLocal.getCod_cli_cd());
					break;
				}
			}

		}
		else
		{
			LocalidadLocal localidadLocal=localidadHome.findByPrimaryKey (new LocalidadKey (group.getAddress ().getCityCode ()));
			DepartamentoLocal departamentoLocal=departamentoHome.findByPrimaryKey (new DepartamentoKey (group.getAddress ().getDepartmentCode ()));
			long peti_numero=dbSeq.seqNextValLong ("CORRELATIVO_PETICION");
			log.info("NO esta ingresado Grupo con la Misma Dir. Pet Atis:"+pet.getRequestNumber() + " Creando peticion ATiempo peti_numero=" + peti_numero);
			PeticionLocal peticionLocal=peticionHome.create (peti_numero,peticion_atisLocal,localidadLocal,departamentoLocal);
//			Seteo los datos del cliente, segmento, contacto y direccion en la tabla peticion
			PeticionConvertidor.setEntity (peticionLocal,group);
			String actuacion="";
			for(Iterator iterator=group.getSubRequests().iterator();iterator.hasNext();)
			{
				SubRequest subPeticionDTO = (SubRequest) iterator.next ();
				long ps_id=subPeticionDTO.getProductServiceCode ();
				Producto_servicioLocal ps=producto_servicioLocalHome.findByPrimaryKey (new Producto_servicioKey (new Long (ps_id)));
				Operacion_comercialLocal op=operacion_comercialHome.findByPrimaryKey (new Operacion_comercialKey (new Long (subPeticionDTO.getComercialOperation ())));
				
				long correlativo=dbSeq.seqNextValLong ("CORRELATIVO_PRODUCTO_SERVICIO_PETICION");
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=psPetHome.create (new Long (correlativo),peticionLocal,op,ps);
				ProductoServicioPeticionConvertidor.setEntity (producto_servicio_peticionLocal,subPeticionDTO,new Long(pet.getRequestNumber()),new Integer(group.getCode()));
				Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
				Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
				Integer tipo=primerTipoAgrupacion(agrupacion_atisLocal);
				if(tipo.equals(tipoAgrupacionLinea) || tipo.equals(tipoAgrupacionBA) || tipo.equals(tipoAgrupacionAsist)
						|| tipo.equals(tipoAgrupacionPCNaked)|| tipo.equals(tipoAgrupacionPSNaked))
					peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal.getNum_ide_nu());
				else if(tipo.equals(tipoAgrupacionTV))
					peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal.getNum_ide_nu());
			    else if(tipo.equals(tipoAgrupacionIT))
			    	peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal.getNum_ide_nu());
				/**
				 * DAVID: se adiciona el siguiente if para RQ 28274, cobro
				 * incidencias.
				 */
				else if (tipo.equals(tipoAgrupacionMNT)) {
					if (ps_id == psMNTTV) {
						peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal
								.getNum_ide_nu());
					} else {
						peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal
								.getNum_ide_nu());
					}

				} else if (tipo.equals(tipoAgrupacionPDG))
					peticionLocal.setNum_ide_nu_pdg(agrupacion_atisLocal
							.getNum_ide_nu());
				else if (tipo.equals(tipoAgrupacionIC))
					peticionLocal.setNum_ide_nu_ic(agrupacion_atisLocal
							.getNum_ide_nu());
				else if(tipo.equals(tipoAgrupacionPS_SVA))
				{
					//validamos si el numero comienza pot TV
					if(agrupacion_atisLocal.getNum_ide_nu().startsWith("TV"))
					{	
						log.debug("Hay agrupacion Napster con telefono en TV: "+agrupacion_atisLocal.getNum_ide_nu());
						//si contiene datos se debe eliminar TV y el numero final
						 peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal.getNum_ide_nu());
													
					}else{
						log.debug("Hay agrupacion Napster telefono en STB: "+agrupacion_atisLocal.getNum_ide_nu());
						//si hay un numero se setea el phone_number
						  peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal.getNum_ide_nu());
					}
					//peticionLocal.setNum_ide_nu_mp(agrupacion_atisLocal.getNum_ide_nu());
				}				   

				//Movistar Play se agrega Num_ide_num_mp el valor del idpc
			    else if(tipo.equals(tipoAgrupacionPS_GVP))
			    	peticionLocal.setNum_ide_nu_mp(agrupacion_atisLocal.getNum_ide_nu());

				peticionLocal.setJornada_agnd_sc(pet.getJorney());
				peticionLocal.setCoord_x_agnd_sc(pet.getCoordinateX());
				peticionLocal.setCoord_y_agnd_sc(pet.getCoordinateY());
				if(actuacion.equals("") || actuacion.equals("0")){
					actuacion = extraerInf(subPeticionDTO, tokenActuacionCCF,peticionLocal,peti_numero);
				}
									
			}
			
			PeticionVsGrupoDTO peticionVsGrupoDTO=new PeticionVsGrupoDTO();
			peticionVsGrupoDTO.setNroPeticion(new Long(peti_numero));
			peticionVsGrupoDTO.setIdGrupo(new Integer(group.getCode()));
			listPetAtiempoVsGroup.add(peticionVsGrupoDTO);
			peticionLocal.setCod_cli_cd(peticion_atisLocal.getCod_cli_cd());
			nroPets.add(new Long(peti_numero));
		}
		marcarComoIngresada(group,listAgrupacionVsDireccion);
	}

	private void agregarPesesDeGrupoNuevoAGrupoIngresado(long cod_pet_cd,PeticionLocal pet, Group group) throws FinderException, NamingException, CreateException
	{
		Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);

		Integer tipo=primerTipoAgrupacion(group);
		if(tipo.equals(tipoAgrupacionLinea) || tipo.equals(tipoAgrupacionBA) || tipo.equals(tipoAgrupacionAsist)
				|| tipo.equals(tipoAgrupacionPCNaked)|| tipo.equals(tipoAgrupacionPSNaked))
			pet.setNum_ide_nu_stb(group.getComercialProductIdentificationNumber());
		else if(tipo.equals(tipoAgrupacionTV))
			pet.setNum_ide_nu_tv(group.getComercialProductIdentificationNumber());
		else if(tipo.equals(tipoAgrupacionIC))
			pet.setNum_ide_nu_ic(group.getComercialProductIdentificationNumber());
		else if(tipo.equals(tipoAgrupacionPDG))
			pet.setNum_ide_nu_pdg(group.getComercialProductIdentificationNumber());
	    else if(tipo.equals(tipoAgrupacionIT))
	    	pet.setNum_ide_nu_stb(group.getComercialProductIdentificationNumber());
			
		for(Iterator iterator=group.getSubRequests().iterator();iterator.hasNext();)
		{
			SubRequest subRequest=(SubRequest) iterator.next();
			long ps_id=subRequest.getProductServiceCode ();
			Producto_servicioLocal ps=producto_servicioLocalHome.findByPrimaryKey (new Producto_servicioKey (new Long (ps_id)));
			Operacion_comercialLocal op=operacion_comercialHome.findByPrimaryKey (new Operacion_comercialKey (new Long (subRequest.getComercialOperation())));
			long correlativo=dbSeq.seqNextValLong ("CORRELATIVO_PRODUCTO_SERVICIO_PETICION");
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=psPetHome.create (new Long (correlativo),pet,op,ps);
			ProductoServicioPeticionConvertidor.setEntity (producto_servicio_peticionLocal,subRequest,new Long(cod_pet_cd),new Integer(group.getCode()));
		}
	}

	//Este metodo no tiene sentido que recorra todos los peses pues solo interesa determinar
	//si la agrupacion es de linea o tv para setear el num_id_nu_stb o num_id_nu_tv
	//para eso solo basta verificar una familia de ps. Nada mas.
	private Integer primerTipoAgrupacion(Group group) throws FinderException, NamingException
	{
		
		Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		Familia_producto_servicioLocalHome familiaPsLocalHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
		
		SubRequest subRequest=(SubRequest)group.getSubRequests().iterator().next();
		Producto_servicioKey producto_servicioKey=new Producto_servicioKey(new Long(subRequest.getProductServiceCode()));
		Producto_servicioLocal ps=producto_servicioLocalHome.findByPrimaryKey(producto_servicioKey);
		Familia_producto_servicioLocal familiaPs = ps.getFamilia_producto_servicio () ;
		while (familiaPs.getFaps_id_padre () != null && familiaPs.getFaps_id_padre ().longValue () != 0)
		{
			Familia_producto_servicioKey key = new Familia_producto_servicioKey (familiaPs.getFaps_id_padre ()) ;

			familiaPs = familiaPsLocalHome.findByPrimaryKey (key) ;
		}
		long idFamilia = ((Familia_producto_servicioKey) familiaPs.getPrimaryKey ()).faps_id.longValue () ;
		return retornaTipoAgrupacion(idFamilia);
	}

	private boolean chocanGrupos(int idGrupoIngresado, Group grupoQueIngresa,TR001S pet) throws ATiempoAppEx, FinderException, NamingException
	{
		Tipo_pcLocalHome tipo_pcHome=(Tipo_pcLocalHome) HomeFactory.getHome(Tipo_pcLocalHome.JNDI_NAME);

		Group groupIngresado=null;
		for(Iterator iterator=pet.getGroupings().iterator();iterator.hasNext();)
		{
			Group group=(Group) iterator.next();
			if(group.getCode()==idGrupoIngresado)
			{
				groupIngresado=group;
				break;
			}
		}
		if(groupIngresado==null)
			throw new ATiempoAppEx("Error de Proceso.Pet Atis:"+pet.getRequestNumber());
		Long tipoPcGrupo1=new Long(groupIngresado.getComercialProductType());
		Long tipoPcGrupo2=new Long(grupoQueIngresa.getComercialProductType());
		if(tipoPcGrupo1!=tipoPcGrupo2)
		{
			Tipo_pcLocal tipo_pcLocal1=tipo_pcHome.findByPrimaryKey(new Tipo_pcKey(new Integer(tipoPcGrupo1.intValue())));
			Tipo_pcLocal tipo_pcLocal2=tipo_pcHome.findByPrimaryKey(new Tipo_pcKey(new Integer(tipoPcGrupo2.intValue())));
			int familiaTipoPc1=tipo_pcLocal1.getFamilia_pc().intValue();
			int familiaTipoPc2=tipo_pcLocal2.getFamilia_pc().intValue();
			
			if(tieneVentaContado(groupIngresado,grupoQueIngresa) ){
				return true;
			}
			
			//REQ 30793 - FAVG - ENE/20/2010 Inicio
			if(familiaTipoPc1 == ComunInterfaces.familiaLinea && familiaTipoPc2 == ComunInterfaces.familiaLinea &&
			   ((tipoPcGrupo1.intValue() == ComunInterfaces.tipoLB && tipoPcGrupo2.intValue() == ComunInterfaces.tipoIC) || 
			   	(tipoPcGrupo1.intValue() == ComunInterfaces.tipoIC && tipoPcGrupo2.intValue() == ComunInterfaces.tipoLB))
			   ){
			
				return false;//no Chocan
			}
			//REQ 30793 - FAVG - ENE/20/2010 Fin
			log.debug("Familia uno="+ familiaTipoPc1+" Familia dos= "+familiaTipoPc2);
			if(familiaTipoPc1==familiaTipoPc2)
				return true;//Si chocan
			if((familiaTipoPc1 == ComunInterfaces.familiaLinea && familiaTipoPc2 == ComunInterfaces.familiaIntEquipado)||
					(familiaTipoPc2 == ComunInterfaces.familiaLinea && familiaTipoPc1 == ComunInterfaces.familiaIntEquipado)){
				log.debug("Logrupos chocan por se familias LB y Venta Equipos se retorna true");
				return true;//Si chocan
			}
			log.debug("Familia uno="+ familiaTipoPc1+" Familia dos= "+familiaTipoPc2+"No chocan: se retorna false");
		}
		else
			return true;//Si chocan
		return false;//no Chocan
	}

	/**
	 * Valida una de las operacion comerciales es de venta de contado
	 * y la otra operacion comercial no 
	 * @param groupIngresado grupo que se encuentra en la bd 
	 * @param grupoQueIngresa que se va  a almacenar en la bd
	 * @return true si se tienen operaciones diferentes
	 */
	private boolean tieneVentaContado(Group groupIngresado, Group grupoQueIngresa) {
		Collection subpeticionesIngresadas =  groupIngresado.getSubRequests();
		Collection subpeticionesQueIngresan =  grupoQueIngresa.getSubRequests();
		for (Iterator iter = subpeticionesIngresadas.iterator(); iter.hasNext();) {
			SubRequest subPeticionIngresada = (SubRequest) iter.next();
			
			for (Iterator iterator = subpeticionesQueIngresan.iterator(); iterator.hasNext();) {
				SubRequest subPeticionQueIngresa = (SubRequest) iterator.next();
				if((subPeticionIngresada.getComercialOperation() == ComunInterfaces.ID_OP_ALTA_VENTA_CONTADO
						||subPeticionQueIngresa.getComercialOperation() == ComunInterfaces.ID_OP_ALTA_VENTA_CONTADO)
						&& subPeticionIngresada.getComercialOperation() != subPeticionQueIngresa.getComercialOperation() ){
					return true;
				}
			}
			
		}
		return false;
	}

	private ArrayList recuperaGrupoConMismaDir(Group group,ArrayList listAgrupacionVsDireccion) throws ATiempoAppEx
	{
		ArrayList retorno=new ArrayList();
		String direc=addressToString(group.getAddress());
		for(Iterator iterator=listAgrupacionVsDireccion.iterator();iterator.hasNext();)
		{
			AgrupVsDirecDto agrupVsDirecDto=(AgrupVsDirecDto) iterator.next();
			//if(direc.equals(agrupVsDirecDto.getDireccionJunta()))
			// Defecto 22487 -04/02/2009  
			if (Utiles.eliminaEspacios(direc).equals(Utiles.eliminaEspacios(agrupVsDirecDto.getDireccionJunta())))			
				retorno.add(new Integer(agrupVsDirecDto.getCod_agru_sub_nu()));	
		}
		if(retorno.size()==0)
			throw new ATiempoAppEx("Error de Proceso",ATiempoAppEx.TN_PROCESS);
		return retorno;
	}

	private void ingresarAgrupaciones(Peticion_atisLocal peticion_atisLocal,TR001S pet, Group group, Group group2, ArrayList listAgrupacionVsDireccion,ArrayList listPetAtiempoVsGroup,ArrayList nroPets,int valTras) throws CreateException, FinderException, NamingException
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
		LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
		DepartamentoLocalHome departamentoHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
		PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
		Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);

		long peti_numero=dbSeq.seqNextValLong ("CORRELATIVO_PETICION");
		log.info("Ingresando Agrupaciones Complementos / Traslado - Grupo 1 pet atis" + pet.getRequestNumber() + " Creando peticion ATiempo peti_numero=" + peti_numero);
		/*********************************************************************/
		//Grupo 1
		//log.info("Grupo 1 pet atis"+pet.getRequestNumber());
		LocalidadLocal localidadLocal=localidadHome.findByPrimaryKey (new LocalidadKey (group.getAddress ().getCityCode ()));
		DepartamentoLocal departamentoLocal=departamentoHome.findByPrimaryKey (new DepartamentoKey (group.getAddress ().getDepartmentCode ()));
		PeticionLocal peticionLocal=peticionHome.create (peti_numero,peticion_atisLocal,localidadLocal,departamentoLocal);
		peticionLocal.setCod_cli_cd(peticion_atisLocal.getCod_cli_cd());
		if(valTras==1)
			PeticionConvertidor.setEntity (peticionLocal,group);
		String actuacion="";
		for(Iterator iterator=group.getSubRequests().iterator();iterator.hasNext();)
		{
			SubRequest subPeticionDTO = (SubRequest) iterator.next ();
			long ps_id=subPeticionDTO.getProductServiceCode ();
			Producto_servicioLocal ps=producto_servicioLocalHome.findByPrimaryKey (new Producto_servicioKey (new Long (ps_id)));
			Operacion_comercialLocal op=operacion_comercialHome.findByPrimaryKey (new Operacion_comercialKey (new Long (subPeticionDTO.getComercialOperation ())));
			long correlativo=dbSeq.seqNextValLong ("CORRELATIVO_PRODUCTO_SERVICIO_PETICION");
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=psPetHome.create (new Long (correlativo),peticionLocal,op,ps);
			ProductoServicioPeticionConvertidor.setEntity (producto_servicio_peticionLocal,subPeticionDTO,new Long(pet.getRequestNumber()),new Integer(group.getCode()));
			Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
			Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
			Integer tipo=primerTipoAgrupacion(agrupacion_atisLocal);
			if(tipo.equals(tipoAgrupacionLinea) || tipo.equals(tipoAgrupacionBA) || tipo.equals(tipoAgrupacionAsist)
					|| tipo.equals(tipoAgrupacionPCNaked)|| tipo.equals(tipoAgrupacionPSNaked))
				peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal.getNum_ide_nu());
			else if(tipo.equals(tipoAgrupacionTV))
				peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal.getNum_ide_nu());
			else if(tipo.equals(tipoAgrupacionIC))
				peticionLocal.setNum_ide_nu_ic(agrupacion_atisLocal.getNum_ide_nu());
			else if(tipo.equals(tipoAgrupacionPDG))
				peticionLocal.setNum_ide_nu_pdg(agrupacion_atisLocal.getNum_ide_nu());
			else if(tipo.equals(tipoAgrupacionPS_SVA))
			{
//				validamos si el numero comienza pot TV
				if(agrupacion_atisLocal.getNum_ide_nu().startsWith("TV"))
				{	
					log.debug("Hay agrupacion Napster con telefono en TV: "+agrupacion_atisLocal.getNum_ide_nu());
					//si contiene datos se debe eliminar TV y el numero final
					 peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal.getNum_ide_nu());
												
				}else{
					log.debug("Hay agrupacion Napster telefono en STB: "+agrupacion_atisLocal.getNum_ide_nu());
					//si hay un numero se setea el phone_number
					  peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal.getNum_ide_nu());
				}
			}
//			Movistar Play se agrega Num_ide_num_mp el valor del idpc
		    else if(tipo.equals(tipoAgrupacionPS_GVP))
		    	peticionLocal.setNum_ide_nu_mp(agrupacion_atisLocal.getNum_ide_nu());
		    
			peticionLocal.setJornada_agnd_sc(pet.getJorney());
			peticionLocal.setCoord_x_agnd_sc(pet.getCoordinateX());
			peticionLocal.setCoord_y_agnd_sc(pet.getCoordinateY());
			if(actuacion.equals("") || actuacion.equals("0")){
				actuacion = extraerInf(subPeticionDTO, tokenActuacionCCF,peticionLocal,peti_numero);
			}
		}
		
		PeticionVsGrupoDTO peticionVsGrupoDTO=new PeticionVsGrupoDTO();
		peticionVsGrupoDTO.setNroPeticion(new Long(peti_numero));
		peticionVsGrupoDTO.setIdGrupo(new Integer(group.getCode()));
		listPetAtiempoVsGroup.add(peticionVsGrupoDTO);
		nroPets.add(new Long(peti_numero));
		log.info("Grupo 2. PetAtis"+pet.getRequestNumber() + " peti_numero=" + peti_numero);
		/*********************************************************************/
		/*********************************************************************/
		//Grupo 2
		LocalidadLocal localidadLocal2=localidadHome.findByPrimaryKey (new LocalidadKey (group2.getAddress ().getCityCode ()));
		DepartamentoLocal departamentoLocal2=departamentoHome.findByPrimaryKey (new DepartamentoKey (group2.getAddress ().getDepartmentCode ()));
		if(valTras==2)
			PeticionConvertidor.setEntity (peticionLocal,group2);
		for(Iterator iterator=group2.getSubRequests().iterator();iterator.hasNext();)
		{
			SubRequest subPeticionDTO2 = (SubRequest) iterator.next ();
			long ps_id=subPeticionDTO2.getProductServiceCode ();
			Producto_servicioLocal ps2=producto_servicioLocalHome.findByPrimaryKey (new Producto_servicioKey (new Long (ps_id)));
			Operacion_comercialLocal op2=operacion_comercialHome.findByPrimaryKey (new Operacion_comercialKey (new Long (subPeticionDTO2.getComercialOperation ())));
			long correlativo=dbSeq.seqNextValLong ("CORRELATIVO_PRODUCTO_SERVICIO_PETICION");
			Producto_servicio_peticionLocal producto_servicio_peticionLocal2=psPetHome.create (new Long (correlativo),peticionLocal,op2,ps2);
			ProductoServicioPeticionConvertidor.setEntity (producto_servicio_peticionLocal2,subPeticionDTO2,new Long(pet.getRequestNumber()),new Integer(group2.getCode()));
			Subpeticion_atisLocal subpeticion_atisLocal2=producto_servicio_peticionLocal2.getFk_01_subp_atis();
			Agrupacion_atisLocal agrupacion_atisLocal2=subpeticion_atisLocal2.getFk_agru_sub();
			Integer tipo=primerTipoAgrupacion(agrupacion_atisLocal2);
			if(tipo.equals(tipoAgrupacionLinea) || tipo.equals(tipoAgrupacionBA) || tipo.equals(tipoAgrupacionAsist)
					|| tipo.equals(tipoAgrupacionPCNaked)|| tipo.equals(tipoAgrupacionPSNaked))
				peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal2.getNum_ide_nu());
			else if(tipo.equals(tipoAgrupacionTV))
				peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal2.getNum_ide_nu());
			else if(tipo.equals(tipoAgrupacionIC))
				peticionLocal.setNum_ide_nu_ic(agrupacion_atisLocal2.getNum_ide_nu());
			
			peticionLocal.setJornada_agnd_sc(pet.getJorney());
			peticionLocal.setCoord_x_agnd_sc(pet.getCoordinateX());
			peticionLocal.setCoord_y_agnd_sc(pet.getCoordinateY());
		}
		PeticionVsGrupoDTO peticionVsGrupoDTO2=new PeticionVsGrupoDTO();
		peticionVsGrupoDTO2.setNroPeticion(new Long(peti_numero));
		peticionVsGrupoDTO2.setIdGrupo(new Integer(group2.getCode()));
		listPetAtiempoVsGroup.add(peticionVsGrupoDTO2);
		/*********************************************************************/
		marcarComoIngresada(group,listAgrupacionVsDireccion);
		marcarComoIngresada(group2,listAgrupacionVsDireccion);
	}
	
	/**
	 * req 5111 traslado trios
	 * @throws NamingException
	 * @throws FinderException
	 * @throws CreateException
	 * @idrincon
	 * metodo que ingresa una sola peticion atiempo en caso que la peticion
	 * sea un traslado de trios.
	 * 
	 */
	private void ingresarPeticionTrasladoTrio( TR001S tr001s, 
												Peticion_atisLocal peticion_atisLocal,  
												Group groupActual, 
												Group groupComplemento,
												ArrayList listAgrupacionVsDireccion,
												ArrayList listPetAtiempoVsGroup,
												ArrayList nroPets,
												int valTras) throws NamingException, FinderException, CreateException{
		
		log.info("Traslado de trios Pet Atis: "+tr001s.getRequestNumber());
		
		//Marca com ingresado el grupo actual para marcarlo como ingresado pero no se ha insertado en la BD 
		marcarComoIngresada( groupActual , listAgrupacionVsDireccion );
		marcarComoIngresada( groupComplemento , listAgrupacionVsDireccion );
		//recupero los dos grupos que faltan de la peticion de traslados de trios
		ArrayList listaGruposFaltantes = recuperarGrupoyComplementoFaltante( tr001s, listAgrupacionVsDireccion );
		
		if( listaGruposFaltantes.size() > 0){
			Group groupActual2 = (Group)listaGruposFaltantes.get(0);
			Group groupComplemento2 = (Group)listaGruposFaltantes.get(1);
			Long valTras2 = (Long)listaGruposFaltantes.get(2);;
			
			long peti_numero=dbSeq.seqNextValLong ("CORRELATIVO_PETICION");
			DepartamentoLocalHome departamentoHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
			LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			LocalidadLocal localidadLocal = localidadHome.findByPrimaryKey (new LocalidadKey (groupActual.getAddress ().getCityCode ()));
			DepartamentoLocal departamentoLocal = departamentoHome.findByPrimaryKey (new DepartamentoKey (groupActual.getAddress ().getDepartmentCode ()));
			PeticionLocal peticionLocal = peticionHome.create (peti_numero,peticion_atisLocal,localidadLocal,departamentoLocal);
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.PS_PBX);
			peticionLocal.setCod_cli_cd(peticion_atisLocal.getCod_cli_cd());
			
			String[] psPBX= propertiesBDLocal.getValor().split(",");
			long psValidar;
			boolean isPBX=false;
			Collection psp = groupActual.getSubRequests();
			Collection psp2 = groupActual2.getSubRequests();
			 for (Iterator j=psp.iterator(); j.hasNext (); )
             {
                 SubRequest subPeticionDTO = (SubRequest) j.next ();
                 for(int i = 0; i<psPBX.length;i++){
                 	psValidar = Long.parseLong(psPBX[i]);
                 	if(subPeticionDTO.getProductServiceCode()== psValidar)
                 		isPBX = true;
                 	log.debug("Se compara ps: "+subPeticionDTO.getProductServiceCode()+" con: "+psValidar);
                 }
             }
			 for (Iterator j=psp2.iterator(); j.hasNext (); )
             {
			 	SubRequest subPeticionDTO = (SubRequest) j.next ();
                for(int i = 0; i<psPBX.length;i++){
                	psValidar = Long.parseLong(psPBX[i]);
                	if(subPeticionDTO.getProductServiceCode()== psValidar)
                		isPBX = true;
                	log.debug("Se compara ps: "+subPeticionDTO.getProductServiceCode()+" con: "+psValidar);
                }
             }
			//inserta los valores en la tabla para las 4 agrupaciones
			ingresarAgrupacionesDeTrios( peti_numero, groupActual, groupComplemento, listAgrupacionVsDireccion, listPetAtiempoVsGroup, nroPets, valTras, peticion_atisLocal, tr001s, peticionLocal, localidadHome, departamentoHome );
			if(isPBX){
				nroPets.add(new Long(peti_numero));
				peti_numero=dbSeq.seqNextValLong ("CORRELATIVO_PETICION");
				peticionLocal=peticionHome.create (peti_numero,peticion_atisLocal,localidadLocal,departamentoLocal);
				peticionLocal.setCod_cli_cd(peticion_atisLocal.getCod_cli_cd());
				ingresarAgrupacionesDeTrios( peti_numero, groupActual2, groupComplemento2, listAgrupacionVsDireccion, listPetAtiempoVsGroup, nroPets, valTras2.intValue(), peticion_atisLocal, tr001s, peticionLocal, localidadHome, departamentoHome );
				log.debug("Es una petición PBX se crea un nuevo consecutivo de petición: "+peti_numero);
			}else{
				ingresarAgrupacionesDeTrios( peti_numero, groupActual2, groupComplemento2, listAgrupacionVsDireccion, listPetAtiempoVsGroup, nroPets, valTras2.intValue(), peticion_atisLocal, tr001s, peticionLocal, localidadHome, departamentoHome );
				log.debug("No es una petición PBX se crea un nuevo consecutivo de petición: "+peti_numero);
			}
			
			//marca como ingresados los otros dos grupos
			marcarComoIngresada( groupActual2,listAgrupacionVsDireccion );
			marcarComoIngresada( groupComplemento2,listAgrupacionVsDireccion );
			
			nroPets.add(new Long(peti_numero));
		}
	}
	
	/**
	 * req 5111 traslado trios
	 * @idrincon 
	 * Metodo que retorna los grupos faltantes req agrupacion traslado de trios
	 * @param tr001s
	 * @param valTras
	 * @return ArrayList de tre posiciones con los otros dos grupos de la peticion de traslados y el valor de traslado
	 */
	private ArrayList recuperarGrupoyComplementoFaltante( TR001S tr001s, ArrayList listAgrupacionVsDireccion ){
		
		ArrayList listaRetorno = new ArrayList();
		
		try {
			for(Iterator iterator = tr001s.getGroupings().iterator();iterator.hasNext();){
				Group groupo = (Group) iterator.next();
					int valorTraslado = esTraslado( groupo );
					if( valorTraslado != 0 ){
						log.info("Si es un traslado . Pet Atis:"+ tr001s.getRequestNumber());
						if( !estaIngresadoGrupo( groupo ,listAgrupacionVsDireccion ) ){
							log.info("Procesando un traslado . Pet Atis:"+ tr001s.getRequestNumber());
							ArrayList retorno=agrupacionComplemento( tr001s,groupo );
							if( retorno != null ){
								log.info("Agrupacion Complemento Encontrada . Pet Atis:"+tr001s.getRequestNumber());
								String valSoloBa = (String)retorno.get(0);
								Group grupoRetorno = (Group)retorno.get(1);
								if(valSoloBa.equals("0")){
									listaRetorno.add(groupo);
									listaRetorno.add(grupoRetorno);
									listaRetorno.add(new Long(valorTraslado));
									return listaRetorno;
								}
							}	
						}else{
							log.info("El grupo de trasldo ya se ingreso . Pet Atis:"+tr001s.getRequestNumber());	
						}
				}
			}
		} catch (ATiempoAppEx e) {
			log.error("Error en recuperarGrupoyComplementoFaltante");
		}
		return listaRetorno;
	}
	
	/**
	 * req 5111 traslado de trios
	 * guarda la informacion en la bd
	 * 
	 * @author idrincon
	 * @param peti_numero
	 * @param groupActual
	 * @param groupComplemento
	 * @param listAgrupacionVsDireccion
	 * @param listPetAtiempoVsGroup
	 * @param nroPets
	 * @param valTras
	 * @param peticion_atisLocal
	 * @param tr001s
	 */
	private void ingresarAgrupacionesDeTrios(long peti_numero, Group groupActual, Group groupComplemento, ArrayList listAgrupacionVsDireccion, ArrayList listPetAtiempoVsGroup, ArrayList nroPets, int valTras, Peticion_atisLocal peticion_atisLocal, TR001S tr001s, PeticionLocal peticionLocal,  LocalidadLocalHome localidadHome, DepartamentoLocalHome departamentoHome){
		
		try {
			
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			
			//Grupo 1
			//log.info("Grupo 1 pet atis"+pet.getRequestNumber());
			
			if( valTras == 1 ){
				PeticionConvertidor.setEntity ( peticionLocal, groupActual );
			}
			for( Iterator iterator = groupActual.getSubRequests().iterator(); iterator.hasNext(); )
			{
				SubRequest subPeticionDTO = (SubRequest) iterator.next ();
				long ps_id=subPeticionDTO.getProductServiceCode ();
				Producto_servicioLocal ps = producto_servicioLocalHome.findByPrimaryKey ( new Producto_servicioKey ( new Long ( ps_id ) ) );
				Operacion_comercialLocal op = operacion_comercialHome.findByPrimaryKey ( new Operacion_comercialKey ( new Long ( subPeticionDTO.getComercialOperation () ) ) );
				long correlativo = dbSeq.seqNextValLong ( "CORRELATIVO_PRODUCTO_SERVICIO_PETICION" );
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = psPetHome.create ( new Long ( correlativo ), peticionLocal, op, ps );
				
				ProductoServicioPeticionConvertidor.setEntity ( producto_servicio_peticionLocal, subPeticionDTO, new Long( tr001s.getRequestNumber() ),new Integer( groupActual.getCode() ) );
				Subpeticion_atisLocal subpeticion_atisLocal = producto_servicio_peticionLocal.getFk_01_subp_atis();
				Agrupacion_atisLocal agrupacion_atisLocal = subpeticion_atisLocal.getFk_agru_sub();
				Integer tipo = primerTipoAgrupacion(agrupacion_atisLocal);
				
				if(tipo.equals(tipoAgrupacionLinea) || tipo.equals(tipoAgrupacionBA) || tipo.equals(tipoAgrupacionAsist)
						|| tipo.equals(tipoAgrupacionPCNaked)|| tipo.equals(tipoAgrupacionPSNaked)){
					peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal.getNum_ide_nu());
				}else if(tipo.equals(tipoAgrupacionTV)){
					peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal.getNum_ide_nu());
				}else if(tipo.equals(tipoAgrupacionIC)){
					peticionLocal.setNum_ide_nu_ic(agrupacion_atisLocal.getNum_ide_nu());
				}
//				Movistar Play se agrega Num_ide_num_mp el valor del idpc
			    else if(tipo.equals(tipoAgrupacionPS_GVP))
			    	peticionLocal.setNum_ide_nu_mp(agrupacion_atisLocal.getNum_ide_nu());

				peticionLocal.setJornada_agnd_sc(tr001s.getJorney());
				peticionLocal.setCoord_x_agnd_sc(tr001s.getCoordinateX());
				peticionLocal.setCoord_y_agnd_sc(tr001s.getCoordinateY());
			}
			
			//Grup complemento
			PeticionVsGrupoDTO peticionVsGrupoDTO = new PeticionVsGrupoDTO();
			peticionVsGrupoDTO.setNroPeticion( new Long( peti_numero ) );
			peticionVsGrupoDTO.setIdGrupo( new Integer( groupActual.getCode() ) );
			
			listPetAtiempoVsGroup.add( peticionVsGrupoDTO );

			LocalidadLocal localidadLocal2 = localidadHome.findByPrimaryKey (new LocalidadKey (groupComplemento.getAddress ().getCityCode ()));
			DepartamentoLocal departamentoLocal2 = departamentoHome.findByPrimaryKey (new DepartamentoKey (groupComplemento.getAddress ().getDepartmentCode ()));
			
			if( valTras == 2 ){
				PeticionConvertidor.setEntity ( peticionLocal, groupComplemento );
			}
			for(Iterator iterator=groupComplemento.getSubRequests().iterator();iterator.hasNext();)
			{
				SubRequest subPeticionDTO2 = (SubRequest) iterator.next ();
				long ps_id = subPeticionDTO2.getProductServiceCode ();
				Producto_servicioLocal ps2 = producto_servicioLocalHome.findByPrimaryKey ( new Producto_servicioKey ( new Long ( ps_id ) ) );
				Operacion_comercialLocal op2 = operacion_comercialHome.findByPrimaryKey ( new Operacion_comercialKey ( new Long ( subPeticionDTO2.getComercialOperation () ) ) );
				
				long correlativo = dbSeq.seqNextValLong ("CORRELATIVO_PRODUCTO_SERVICIO_PETICION");
				Producto_servicio_peticionLocal producto_servicio_peticionLocal2 = psPetHome.create ( new Long ( correlativo ), peticionLocal , op2 , ps2);
				
				ProductoServicioPeticionConvertidor.setEntity (producto_servicio_peticionLocal2,subPeticionDTO2,new Long(tr001s.getRequestNumber()),new Integer(groupComplemento.getCode()));
				Subpeticion_atisLocal subpeticion_atisLocal2 = producto_servicio_peticionLocal2.getFk_01_subp_atis();
				Agrupacion_atisLocal agrupacion_atisLocal2 = subpeticion_atisLocal2.getFk_agru_sub();
				Integer tipo=primerTipoAgrupacion(agrupacion_atisLocal2);
				
				if(tipo.equals(tipoAgrupacionLinea) || tipo.equals(tipoAgrupacionBA) || tipo.equals(tipoAgrupacionAsist)
						|| tipo.equals(tipoAgrupacionPCNaked)|| tipo.equals(tipoAgrupacionPSNaked)){
					peticionLocal.setNum_ide_nu_stb(agrupacion_atisLocal2.getNum_ide_nu());
				}else if(tipo.equals(tipoAgrupacionTV)){
					peticionLocal.setNum_ide_nu_tv(agrupacion_atisLocal2.getNum_ide_nu());
				}else if(tipo.equals(tipoAgrupacionIC)){
					peticionLocal.setNum_ide_nu_ic(agrupacion_atisLocal2.getNum_ide_nu());
				}
//	Movistar Play se agrega Num_ide_num_mp el valor del idpc
			    else if(tipo.equals(tipoAgrupacionPS_GVP))
			    	peticionLocal.setNum_ide_nu_mp(agrupacion_atisLocal2.getNum_ide_nu());

				
				peticionLocal.setJornada_agnd_sc(tr001s.getJorney());
				peticionLocal.setCoord_x_agnd_sc(tr001s.getCoordinateX());
				peticionLocal.setCoord_y_agnd_sc(tr001s.getCoordinateY());
			}
			
			PeticionVsGrupoDTO peticionVsGrupoDTO2=new PeticionVsGrupoDTO();
			peticionVsGrupoDTO2.setNroPeticion(new Long(peti_numero));
			peticionVsGrupoDTO2.setIdGrupo(new Integer(groupComplemento.getCode()));
			
			listPetAtiempoVsGroup.add(peticionVsGrupoDTO2);

		} catch ( NamingException ne ) {
			log.debug( "NamingException en ingresarAgrupacionesDeTrios "+ne.getMessage() );
		} catch (FinderException fe) {
			log.debug( "FinderException en  ingresarAgrupacionesDeTrios "+fe.getMessage() );
		} catch (CreateException ce) {
			log.debug( "CreateException en ingresarAgrupacionesDeTrios "+ce.getMessage() );
		}
	}
	
	/**
	 * req 5111 traslado de trios
	 * @author idrincon
	 * @param tr001s
	 * @param listAgrupacionVsDireccion
	 * @return
	 * @throws ATiempoAppEx
	 */
	private boolean esTrasladoDeTrios( TR001S tr001s, ArrayList listAgrupacionVsDireccion, Group grupoActual, Group grupoComplemtno ) throws ATiempoAppEx{
		try{
			Operacion_comercialLocalHome operacionComercialLocalHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			boolean validaOpco = false;
			
			for(Iterator iterator = tr001s.getGroupings().iterator();iterator.hasNext();){
				validaOpco = false;
				Group grupo = (Group) iterator.next();
				if( ( grupo.getCode() != grupoActual.getCode() ) && (  grupo.getCode() != grupoComplemtno.getCode()) ){
					Collection subRequestList = grupoActual.getSubRequests();
					
					for (Iterator subRequestIter = subRequestList.iterator(); subRequestIter.hasNext();){
						SubRequest subrequest = (SubRequest) subRequestIter.next();
						long operacionComercial = subrequest.getComercialOperation();
						
						Operacion_comercialKey operacionComercialKey = new Operacion_comercialKey(new Long(operacionComercial));
						Operacion_comercialLocal operacionComercialLocal = operacionComercialLocalHome.findByPrimaryKey(operacionComercialKey);
						String opcoTipo = operacionComercialLocal.getOpco_tipo();
						String opcoTrans = operacionComercialLocal.getOpco_tras();
						
						if (opcoTrans.equals(ComunInterfaces.opCoTras_Traslado)){
							if (!opcoTipo.equals(ComunInterfaces.opCoTipoBajaCambioProd) && !opcoTipo.equals(ComunInterfaces.opCoTipoAltaCambioProd)){
								validaOpco = true;
							}
						}
					}
					
					ArrayList retorno = agrupacionComplementoTraslado( tr001s, grupo );
					
					if( retorno != null ){
						if (validaOpco){
							log.info("Agrupacion Complemento Encontrada . Pet Atis:"+tr001s.getRequestNumber());
							String valSoloBa = (String)retorno.get(0);
							Group grupoRetorno = (Group)retorno.get(1);
							if(valSoloBa.equals("0")){
								return true;
							}
						}
					}	
				}
			}
		}catch(NamingException ex){
			log.debug("Se presentó un error de tipo NamingException en el método esTrasladoDeTrios: "+ex);
			return false;
		}catch (FinderException ex){
			log.debug("Se presentó un error de tipo FinderException en el método esTrasladoDeTrios: "+ex);
			return false;
		}
		return false;
	}
	
	/**
	 * req 5111 traslado de trios
	 * @author idrincon
	 * @param pet
	 * @param group
	 * @return
	 * @throws ATiempoAppEx
	 * busca si hay otro grupo con grupo complemento
	 */
	private ArrayList agrupacionComplementoTraslado(TR001S pet, Group group) throws ATiempoAppEx{
		log.info("Buscando Agrupacion complemento . Pet Atis:"+pet.getRequestNumber());
		String comercialProductIdentificationNumber = group.getComercialProductIdentificationNumber();
		for(Iterator iterator=pet.getGroupings().iterator();iterator.hasNext();)//reocrre los grupos de la peticion
		{
			Group groupOtro=(Group) iterator.next();
			log.info("Comparando Buscando Grupo Complemento Para Group:"+group.getCode()+" con "+groupOtro.getCode() +". Pet Atis:"+pet.getRequestNumber());
			if(group.getCode()!=groupOtro.getCode())//valida que el grupo sea diferente al actual para el complemento
			{
				log.info("Comparando "+comercialProductIdentificationNumber+" con "+groupOtro.getComercialProductIdentificationNumber() +". Pet Atis:"+pet.getRequestNumber());
				if(comercialProductIdentificationNumber.equals(groupOtro.getComercialProductIdentificationNumber())){
					log.info("Encontre Agrupacion Complemento por NUMIDENU:"+comercialProductIdentificationNumber+". Pet Atis:"+pet.getRequestNumber());
					ArrayList retorno=new ArrayList();
					retorno.add("0");//posicion 0 si son iguales
					retorno.add(groupOtro);//posicion 1 retorna el objeto grupoortro que tiene el mismo comercial-product-identification-number
					return retorno;
				}
				else
					log.info("Aun no encuentro Agrupacion Complemento. Pet Atis:"+pet.getRequestNumber());
			}
		}
		log.info("No tengo la agrupacion complemento puede ser un traslado solo BA. Pet Atis:"+pet.getRequestNumber());
		return null;
	}
	
	private void marcarComoIngresada(Group group,ArrayList listAgrupacionVsDireccion)
	{
		String direc=addressToString(group.getAddress());
		AgrupVsDirecDto agrupVsDirecDto=new AgrupVsDirecDto();
		agrupVsDirecDto.setCod_agru_sub_nu(group.getCode());
		agrupVsDirecDto.setDireccionJunta(direc);
		listAgrupacionVsDireccion.add(agrupVsDirecDto);		
	}
	
	private boolean estaIngresadoGrupoConIgualDir(Group group,ArrayList listAgrupacionVsDireccion)
	{
		String direc=addressToString(group.getAddress());
		int codagru=group.getCode();
		for(Iterator iterator=listAgrupacionVsDireccion.iterator();iterator.hasNext();)
		{
			AgrupVsDirecDto agrupVsDirecDto=(AgrupVsDirecDto) iterator.next();
			//if(agrupVsDirecDto.getCod_agru_sub_nu()!=codagru && direc.equals(agrupVsDirecDto.getDireccionJunta()))
			// Defecto 22487 -04/02/2009
			if(agrupVsDirecDto.getCod_agru_sub_nu()!=codagru && Utiles.eliminaEspacios(direc).equals(Utiles.eliminaEspacios(agrupVsDirecDto.getDireccionJunta())))
			{
				log.info("Hay Agrupacion con igual Dir Ingresada");
				return true;
			}
		}
		log.info("No Hay Agrupacion con igual Dir Ingresada");
		return false;
	}

	// CR18865 - ana santos
	public boolean esBaja(Long peticion) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
		try{
		boolean esBaja = false;
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);

		PeticionKey peticionKey = new PeticionKey(peticion);
		PeticionLocal peticionLocal = null;
		try
		{
			peticionLocal = peticionHome.findByPrimaryKey(peticionKey);
		} catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}

		for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
			Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
			
			Operacion_comercialLocal operacion_comercialLocal = (Operacion_comercialLocal) producto_servicio_peticionLocal.getOperacion_comercial();
			
			Long idOpCo = ((Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey()).opco_id;
	
			String listaOpCoBaja = VpistbbaConfig.getVariable("ID_OPCO_BAJA");
			
			String[] var = Utiles.split(listaOpCoBaja, ',');
			for (int i = 0; var != null && i < var.length; i++) {
				if (Long.parseLong(var[i]) == idOpCo.longValue()){
					esBaja = true;
				}
			}		

		}
		return esBaja;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx("Error de Proceso");
	}
	}
	// CR18865 - ana santos

	
	// CR18865 - ana santos
	public boolean esAlta(Long peticion) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
		try{
		boolean esAlta = false;
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);

		PeticionKey peticionKey = new PeticionKey(peticion);
		PeticionLocal peticionLocal = null;
		try
		{
			peticionLocal = peticionHome.findByPrimaryKey(peticionKey);
		} catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}

		for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
			Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
			
			Operacion_comercialLocal operacion_comercialLocal = (Operacion_comercialLocal) producto_servicio_peticionLocal.getOperacion_comercial();
			
			Long idOpCo = ((Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey()).opco_id;
	
			String listaOpCoBaja = VpistbbaConfig.getVariable("OP_CO_ALTA");
			
			String[] var = Utiles.split(listaOpCoBaja, ',');
			for (int i = 0; var != null && i < var.length; i++) {
				if (Long.parseLong(var[i]) == idOpCo.longValue()){
					esAlta = true;
				}
			}		

		}
		return esAlta;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx("Error de Proceso");
	}
	}
	// CR18865 - ana santos
	/**
	 * Valida si una peticion es de traslado
	 * @return 1: si es de alta a baja
	 * 		   2: si es de baja a alta
	 */
	private int esTraslado(Group group) throws ATiempoAppEx
	{
		//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 26, 2009
		String opco_tras = null;
		for(Iterator iterator=group.getSubRequests().iterator();iterator.hasNext();)//recorre las subpeticiones del grupo actual
		{
			SubRequest subRequest=(SubRequest) iterator.next();
			try
			{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
				Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
				Operacion_comercialKey operacion_comercialKey=new Operacion_comercialKey(new Long(subRequest.getComercialOperation()));
				Operacion_comercialLocal operacion_comercialLocal=operacion_comercialHome.findByPrimaryKey(operacion_comercialKey);
				opco_tras = operacion_comercialLocal.getOpco_tras();
				if(opco_tras!=null)
				{
					if(opco_tras.equals("T"))
					{
						String opco_tipo = operacion_comercialLocal.getOpco_tipo();
						if(opco_tipo!=null)
						{
							if(opco_tipo.equals("ACP") || opco_tipo.equals("A"))
								return 1;//DE Alta a Baja
							else
								return 2;//De Baja a Alta
						}
						else
							return 1;
					}
				}
			}
			catch(FinderException fe)
			{
				log.error("FinderException",fe);
				throw new ATiempoAppEx("Error de Proceso");
			}
			catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx("Error de Proceso");
		}
		}
		//Cero para no traslado
		return 0;
	}
	
	
	public int esTraslado(Long peticion) throws ATiempoAppEx
	{
		PeticionKey peticionKey = new PeticionKey(peticion);
		PeticionLocal peticionLocal = null;
		try
		{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			peticionLocal = peticionHome.findByPrimaryKey(peticionKey);
		} catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		String opco_tras = null;
		for(Iterator iterator= peticionLocal.getFk_01_pet_atis().getAgrupacion_atis().iterator();iterator.hasNext();)
		{
			Agrupacion_atisLocal subRequest=(Agrupacion_atisLocal) iterator.next();
			for(Iterator iterator2= subRequest.getSubpeticion_atis().iterator(); iterator2.hasNext();){
				Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal) iterator2.next();
				for (Iterator iterator3= subpeticion_atisLocal.getProducto_servicio_peticion().iterator(); iterator3.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iterator3.next();
					Operacion_comercialLocal operacion_comercialLocal = (Operacion_comercialLocal) producto_servicio_peticionLocal.getOperacion_comercial();
		
					opco_tras = operacion_comercialLocal.getOpco_tras();
					if(opco_tras!=null)
					{
						if(opco_tras.equals("T"))
						{
							String opco_tipo = operacion_comercialLocal.getOpco_tipo();
							if(opco_tipo!=null)
							{
								if(opco_tipo.equals("ACP") || opco_tipo.equals("A"))
									return 1;//DE Alta a Baja
								else
									return 2;//De Baja a Alta
							}
							else
								return 1;
						}
					}
					
				}
			}
		}
			
		//Cero para no traslado
		return 0;
	}
	
	public boolean esTrasladoConIgualOrigenYDestino(Long peticion) throws ATiempoAppEx
	{
		PeticionKey peticionKey = new PeticionKey(peticion);
		PeticionLocal peticionLocal = null;
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			peticionLocal = peticionHome.findByPrimaryKey(peticionKey);
		} catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
				
		for(Iterator iterator= peticionLocal.getFk_01_pet_atis().getAgrupacion_atis().iterator();iterator.hasNext();)
		{
			Agrupacion_atisLocal subRequest=(Agrupacion_atisLocal) iterator.next();
			for(Iterator iterator2= subRequest.getSubpeticion_atis().iterator(); iterator2.hasNext();){
				Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal) iterator2.next();
				for (Iterator iterator3= subpeticion_atisLocal.getProducto_servicio_peticion().iterator(); iterator3.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iterator3.next();
					Operacion_comercialLocal operacion_comercialLocal = (Operacion_comercialLocal) producto_servicio_peticionLocal.getOperacion_comercial();
		
					String opco_tras = operacion_comercialLocal.getOpco_tras();
					log.debug("esTrasladoConIgualOrigenYDestino - OPERACION COMERCIAL:" + opco_tras);
					
					if(opco_tras!=null)
					{
						if(opco_tras.equals("T"))
						{
							Recursos_linea_basicaLocal recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) peticionLocal.getRecursos_linea_basica().iterator().next();
							
							Long telefono_asg = recursos_linea_basicaLocal.getTelefono_asg();
							log.debug("TEL ASIGNADO: " + telefono_asg);
							Long telefono_ant = recursos_linea_basicaLocal.getTelefono_ant();
							log.debug("TEL ANTERIOR: " + telefono_ant);
							String len_asg = recursos_linea_basicaLocal.getLen();
							log.debug("LEN ASIGNADO: " + len_asg);
							String len_ant = recursos_linea_basicaLocal.getLen_anterior();
							log.debug("LEN ANTERIOR: " + len_ant);
							if (telefono_ant!=null && telefono_asg!=null &&telefono_ant.longValue() == telefono_asg.longValue()) {
								return true;
							}else if(len_ant!=null && len_asg!=null && len_ant.equals(len_asg)){
								return true;
							}else {
								// Es un traslado pero los nros origen y destino son distintos
								return false; 
							}

						}
					}
					
				}
			}
		}
			
		//Caso en que no es traslado
		return false;
	}
	
	
	public boolean esTrasladoConIgualLen(Long peticion) throws ATiempoAppEx
	{
		PeticionKey peticionKey = new PeticionKey(peticion);
		PeticionLocal peticionLocal = null;
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			peticionLocal = peticionHome.findByPrimaryKey(peticionKey);
		} catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
				
		for(Iterator iterator= peticionLocal.getFk_01_pet_atis().getAgrupacion_atis().iterator();iterator.hasNext();)
		{
			Agrupacion_atisLocal subRequest=(Agrupacion_atisLocal) iterator.next();
			for(Iterator iterator2= subRequest.getSubpeticion_atis().iterator(); iterator2.hasNext();){
				Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal) iterator2.next();
				for (Iterator iterator3= subpeticion_atisLocal.getProducto_servicio_peticion().iterator(); iterator3.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iterator3.next();
					Operacion_comercialLocal operacion_comercialLocal = (Operacion_comercialLocal) producto_servicio_peticionLocal.getOperacion_comercial();
		
					String opco_tras = operacion_comercialLocal.getOpco_tras();
					log.debug("esTrasladoConIgualOrigenYDestino - OPERACION COMERCIAL:" + opco_tras);
					
					if(opco_tras!=null)
					{
						if(opco_tras.equals("T"))
						{
							Recursos_linea_basicaLocal recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) peticionLocal.getRecursos_linea_basica().iterator().next();
							
							Long telefono_asg = recursos_linea_basicaLocal.getTelefono_asg();
							log.debug("TEL ASIGNADO: " + telefono_asg);
							Long telefono_ant = recursos_linea_basicaLocal.getTelefono_ant();
							log.debug("TEL ANTERIOR: " + telefono_ant);
							String len_asg = recursos_linea_basicaLocal.getLen();
							log.debug("LEN ASIGNADO: " + len_asg);
							String len_ant = recursos_linea_basicaLocal.getLen_anterior();
							log.debug("LEN ANTERIOR: " + len_ant);
							if(len_ant!=null && len_asg!=null && len_ant.equals(len_asg)){
								return true;
							}else {
								// Es un traslado pero los nros origen y destino son distintos
								return false; 
							}

						}
					}
					
				}
			}
		}
			
		//Caso en que no es traslado
		return false;
	}	

	
	private String addressToString(Address address)
	{
		String retorno=new StringBuffer(address.getStreetNumber())
		.append(address.getDepartmentCode())
		.append(address.getCityCode())
		.append(address.getStreetName())
		.append(address.getPathType())
		.append(address.getPathNumber())
		.append(address.getFirstPathCharacters())
		.append(address.getSecondPathCharacters())
		.append(address.getPathZone())
		.append(address.getPathType2())
		.append(address.getPathNumber2())
		.append(address.getFirstPathCharacters2())
		.append(address.getSecondPathCharacters2())
		.append(address.getPathZone2())
		.append(address.getStreetType())
		.append(address.getPlaceType1())
		.append(address.getPlaceNumber1())
		.append(address.getPlaceType2())
		.append(address.getPlaceNumber2())
		.append(address.getPlaceType3())
		.append(address.getPlaceNumber3())
		.append(address.getComplementsDescription1())
		.append(address.getComplementsDescription2())
		.append(address.getSubCityName())
		.append(address.getExternalCityCode())
		.toString();
		return retorno;
	}

	private Integer primerTipoAgrupacion(Agrupacion_atisLocal agrupacion_atisLocal) throws FinderException, NamingException
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
		Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal)agrupacion_atisLocal.getSubpeticion_atis().iterator().next();
		Producto_servicioKey producto_servicioKey=new Producto_servicioKey(subpeticion_atisLocal.getCod_pro_ser_cd());
		Producto_servicioLocal ps=producto_servicioLocalHome.findByPrimaryKey(producto_servicioKey);
		Familia_producto_servicioLocal familiaPs = ps.getFamilia_producto_servicio () ;

		long idFamilia = ((Familia_producto_servicioKey) familiaPs.getPrimaryKey ()).faps_id.longValue () ;

		return retornaTipoAgrupacion(idFamilia);
	}
	
	/**
	 * Función para determinar si un codigo de quiebre se debe enviar con la TR052E o no, según
	 * se encuentre o no en el properties de configuración
	 * @return true si el codigo de quiebre se encuentra configurado en el properties
	 * @return false si el codigo de quiebre no se encuentra configurado en el properties
	 * @author mfmendez
	 */
	private boolean esQuiebreConfiguradoConstructora(Long codCausal){
		boolean esQuiebreConfigurado = false;
		
		try {
			String strCausalConfigConstructora = VpistbbaConfig.getVariable("COD_CAUSAL_CONSTRUCTORAS");
			String[] arrayCausalConfigConstructora = strCausalConfigConstructora.split(",");
			
			for (int i = 0; i < arrayCausalConfigConstructora.length && !esQuiebreConfigurado; i++) {
				if(codCausal.equals(new Long(arrayCausalConfigConstructora[i]))){
					esQuiebreConfigurado = true;
				}
			}
		} catch (NumberFormatException e) {
			log.debug("RecursosServiciosBean: esQuiebreConfiguradoConstructora: "+e);
		} catch (Exception e) {
			log.debug("RecursosServiciosBean: esQuiebreConfiguradoConstructora: "+e);
		} 
		
		return esQuiebreConfigurado;
	}
	
	/**
	 * funcion para determinar si se debe enviar mensaje de constructora
	 * @author mfmendez
	 * @return retorno, true si 
	 */
	private void validaEnvioQuiebreFamiliaConstructora(Long numeroPeticion){
		boolean enviaMensaje = false;
		boolean error = false;
		TR052E tr052e = new TR052E();
		
		PeticionLocalHome peticionHome;
		try {      
	        Producto_servicio_peticionLocalHome  psph = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
	        Collection productosServicioPeticion = psph.findAllByPetiNumero(numeroPeticion);
	        
	        for (Iterator iter = productosServicioPeticion.iterator(); iter.hasNext() && !enviaMensaje;) {
        		Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iter.next();
        		Familia_producto_servicioKey llaveFamilia = (Familia_producto_servicioKey) psp.getFamiliaKey();
        	    int idFamiliaPsp = llaveFamilia.faps_id.intValue();
//        	  REQ BA NAKED adicion familia PC naked
        		if(ComunInterfaces.familiaPcLinea == idFamiliaPsp || ComunInterfaces.familiaIP == idFamiliaPsp || ComunInterfaces.familiaPcBA == idFamiliaPsp || ComunInterfaces.familiaPcPsBANaked== idFamiliaPsp || ComunInterfaces.familiaPcBANaked == idFamiliaPsp){
        			Collection quiebresNovedades = psp.getEstado_ps_peticion();
        			
        			for(Iterator iterQuiebres = quiebresNovedades.iterator();iterQuiebres.hasNext() && !enviaMensaje;){
        				Estado_ps_peticionLocal epp = (Estado_ps_peticionLocal) iterQuiebres.next();
        			
        				Catalogo_causalLocalHome catCausalHome = (Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
        				Catalogo_causalKey llaveCausal = new Catalogo_causalKey(epp.getCod_causal());
        				Catalogo_causalLocal catalogoCausal = catCausalHome.findByPrimaryKey(llaveCausal);
        				
        				if(catalogoCausal != null && catalogoCausal.getQuiebre() != null && 
        						catalogoCausal.getQuiebre().intValue() == 1 && llaveCausal.cod_causal != null && 
								esQuiebreConfiguradoConstructora(llaveCausal.cod_causal)){
        					
        					enviaMensaje = true;        					        					
        					if(epp.getCod_causal() != null)
        						tr052e.setCauseId(epp.getCod_causal().intValue());
        					else
        						tr052e.setCauseId(0);
        					
        					if(catalogoCausal.getDescripcion_causal() != null)
        						tr052e.setCauseDescription(catalogoCausal.getDescripcion_causal());
        					else
        						tr052e.setCauseDescription("");        					
        				}        					        				
        			}        			
        		}        		        		        	        	
        	}
	        
	        if(enviaMensaje){				
				RecursosDelegate delegate = new RecursosDelegate();	
				error = delegate.enviarInformeQuiebreFamiliaBAoLB(numeroPeticion,tr052e);
				
				if(error){										
					log.debug("Se presentó error en el envío de la TR052E, aunque se debía enviar. [" + numeroPeticion + "].");						
					tr052e.setId("");
					tr052e.setSource("ATIEMPO");
					tr052e.setInterfaz("CLIENTE_POTENCIAL");
					tr052e.setDestination("ESB");
					tr052e.setError("Se presentó error en envío de mensaje de Clientes Potenciales. Cierre de Petición "+"[" + numeroPeticion + "].");
					tr052e.setVersion("1.0");

					tr052e = new TR052E();
					tr052e.setAtisRequestNumber(0);
					tr052e.setAttendanceAreas(new ArrayList());
					tr052e.setBox("");
					tr052e.setCauseDescription("");
					tr052e.setCauseId(0);
					tr052e.setCentral("");
					tr052e.setCloset("");
					tr052e.setCpSource("");
					tr052e.setDistributorCode("");
					tr052e.setDistrict("");
					tr052e.setEntryDate("");
					tr052e.setPrimaryCable("");
					tr052e.setSecondaryCable("");
					tr052e.setSegment("");
											
					ConsultarCliPotConstructoraMQ consultarCliPotConstructoraMQ = new ConsultarCliPotConstructoraMQ();
					consultarCliPotConstructoraMQ.enviarMensaje(tr052e);
				} else {
		        	log.debug("Se realizó el envío de la TR052E correctamente, para la petición [" + numeroPeticion + "].");
		        }			        			        	
	        }     	        
		} catch (NamingException e) {
			log.debug("Error en validaEnvioQuiebreFamiliaConstructora (Cierre Petición)[" + numeroPeticion + "].",e);
		} catch (FinderException e) {
			log.debug("Error en validaEnvioQuiebreFamiliaConstructora (Cierre Petición)[" + numeroPeticion + "].",e);
		} catch (NullPointerException e) {
			log.debug("Error en validaEnvioQuiebreFamiliaConstructora (Cierre Petición)[" + numeroPeticion + "].",e);
		} catch (ATiempoAppEx e) {
			log.debug("Error en validaEnvioQuiebreFamiliaConstructora (Cierre Petición)[" + numeroPeticion + "].",e);
		} catch (Exception e) {
			log.debug("Error en validaEnvioQuiebreFamiliaConstructora (Cierre Petición)[" + numeroPeticion + "].",e);
		}
	}
    
    /**
     * cierra la peticion
     *
     * cuando todas las peticiones VPI de una peticion ATIS se cierran, se envia el mensaje de cierre
     * a ATIS.
     *
     * @author francois
     */
    
    public void cerrarPeticion (Long numeroPeticion) throws ATiempoAppEx
    {
        try
        {
        	this.validaEnvioQuiebreFamiliaConstructora(numeroPeticion);			
			
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
        	PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
            // busca la peticion VPI
            
            PeticionKey key = new PeticionKey (numeroPeticion) ;
            
            PeticionLocal peticion = peticionHome.findByPrimaryKey (key) ;
            
            // cambia su estado
            if(peticion.getEspe_id()==null)
				throw new ATiempoAppEx ("Cierre Peticion[" + numeroPeticion + "].Estado de peticion no puede ser nulo");
            if (peticion.getEspe_id ().intValue()==estadoPeticionEnCurso)
                peticion.setEspe_id (new Integer (estadoPeticionTerminada)) ;
            
            else
            {
                int estadoActual = peticion.getEspe_id ().intValue () ;
                
                if (estadoActual == estadoPeticionEnCurso)
                    peticion.setEspe_id (new Integer (estadoPeticionTerminada)) ;
                
                else if (estadoActual == estadoPeticionTerminada)
                    log.warn ("Cierre Peticion. Peticion " + numeroPeticion + " ya esta marcada como terminada") ;
                
                else if (estadoActual == estadoPeticionCancelada)
                {
                    log.info ("Cierre Peticion. Peticion " + numeroPeticion + " fue cancelada") ;
					peticion.setEspe_id (new Integer (estadoPeticionTerminada)) ;
                }
                else
                    log.warn ("Cierre Peticion. Peticion " + numeroPeticion + " tiene un estado desconocido " + estadoActual) ;
            }
            
            // actualiza algunas fechas (sacado version VPI Chile)
            
            Timestamp ahora = new Fecha ().getTimestamp () ;
            
            peticion.setPeti_fecha_modificacion (ahora) ;
            
            //@idrincon req 3274
            //aca va lo del cierre con la fecha esa
            Timestamp fechaCierrTcSip = this.fechaCierrePsPsTroncalSip( numeroPeticion );
            if(fechaCierrTcSip != null){
            	//if (peticion.getPeti_fecha_termino () == null)
            		peticion.setPeti_fecha_termino (fechaCierrTcSip);
            }else{
            	if (peticion.getPeti_fecha_termino () == null)
            		peticion.setPeti_fecha_termino (ahora) ;
            }
            //fin modificacion
            //ivan documen
            //if (peticion.getPeti_fecha_termino () == null)
            //    peticion.setPeti_fecha_termino (ahora) ;
            //fin ivan doc
            
            // busca si existen peticiones hermanas (hijas de la misma peticion ATIS) que todavia estan en curso
            
            boolean hayHermanasEnCurso = false ;
            
            Peticion_atisLocal peticionAtis = peticion.getFk_01_pet_atis () ;
            
            Collection hermanas = peticionAtis.getPeticion () ;
            
            Iterator iterHermanas = hermanas.iterator () ;
            while (iterHermanas.hasNext ())
            {
                PeticionLocal unaHermana = (PeticionLocal) iterHermanas.next () ;
                PeticionKey petK=(PeticionKey)unaHermana.getPrimaryKey();
                if(petK.peti_numero.longValue()==numeroPeticion.longValue())
                	continue;
				log.debug("Cierre Peticion[" + numeroPeticion + "]. Hay Peticiones hermanas en curso. Peticion:" + petK.peti_numero);    
                if (unaHermana.getEspe_id ().intValue () != estadoPeticionTerminada)
                {
                    hayHermanasEnCurso = true ;
                    break ;
                }
            }
            
            revisaTrasladoBa(numeroPeticion);
            
            if (! hayHermanasEnCurso)
            {
                enviaMensajeCierreTecnico (peticionAtis, numeroPeticion) ;
            }
        }
        catch (NamingException nex)
        {
        	log.debug("Error en Cierre Peticion[" + numeroPeticion + "].",nex);
            throw new ATiempoAppEx (ATiempoAppEx.NAMING, nex);
        }
        catch (FinderException fex)
        {
			log.debug("Error en Cierre Peticion[" + numeroPeticion + "].",fex);
            throw new ATiempoAppEx (ATiempoAppEx.FINDER, fex);
        }
    }
    
//	CR 10394 -Pablo Cawen- Inicio - 13/05/2008
	/**
	 * Cierre primario
	 * Cierra en forma anticipada la peticion
	 *
	 * Se envia el mensaje de cierre a ATIS sin que todas las peticiones VPI de una peticion ATIS estén cerradas. 
	 *
	 * @author 804226
	 */

	public void cerrarPeticionPrimaria (Long numeroPeticion) throws ATiempoAppEx
	{
		log.debug("entró en cierre primario********");
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
        
			// busca la peticion VPI
        
			PeticionKey key = new PeticionKey (numeroPeticion) ;
        
			PeticionLocal peticion = peticionHome.findByPrimaryKey (key) ;
        
			// actualiza algunas fechas (sacado version VPI Chile)
        
			Timestamp ahora = new Fecha ().getTimestamp () ;
        
			peticion.setPeti_fecha_modificacion (ahora) ;
        
			if (peticion.getPeti_fecha_termino () == null)
				peticion.setPeti_fecha_termino (ahora) ;
        
			//busca si existen peticiones hermanas (hijas de la misma peticion ATIS) que todavia estan en curso
			//todas las hermanas en curso deben tener cierre anticipado ok(Peticion_flujo.acti_id = 50 o 51 y Peticion_flujo.pefl_estado = ok), en caso contrario no se envia cierreanticipado
			//todas la peticiones hermanas tienen igual peticion.cod_pet_cd y distinto peti_numero
			boolean envioCierrePrimario = true ;
        
			Peticion_atisLocal peticionAtis = peticion.getFk_01_pet_atis () ;
        
			Collection hermanas = peticionAtis.getPeticion () ;
        
			Iterator iterHermanas = hermanas.iterator () ;
			while (iterHermanas.hasNext ())//TODO calcula hermanas
			{
				PeticionLocal unaHermana = (PeticionLocal) iterHermanas.next () ;
				PeticionKey petK=(PeticionKey)unaHermana.getPrimaryKey();
				if(petK.peti_numero.longValue()==numeroPeticion.longValue())
					continue;
				Collection lstPetFluj=peticion_flujoHome.findByIdPeticion(petK.peti_numero);
				for(Iterator iterPetFlujo = lstPetFluj.iterator();iterPetFlujo.hasNext();){
					Peticion_flujoLocal unaPetFlujo = (Peticion_flujoLocal)iterPetFlujo.next();
					//Defecto cierre primario - 27 feb 2009
					if(unaPetFlujo.getIdActividad().intValue()== 50 || unaPetFlujo.getIdActividad().intValue()== 51){
						if(!"OK".equalsIgnoreCase(unaPetFlujo.getPefl_estado())){
						envioCierrePrimario = false;
					break;
				}
			}
			}
			}
        
			revisaTrasladoBa(numeroPeticion);
        
			if (envioCierrePrimario)
			{
				enviaMensajeCierreTecnicoPrimario (peticionAtis,peticion) ;
			}
		}
		catch (NamingException nex)
		{
			log.debug("Error en Cierre Primario Peticion[" + numeroPeticion + "].",nex);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING, nex);
		}
		catch (FinderException fex)
		{
			log.debug("Error en Cierre Primario Peticion[" + numeroPeticion + "].",fex);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER, fex);
		}
	}
    
	public void cerrarPeticionPrimariaTv (Long numeroPeticion) throws ATiempoAppEx
	{
		log.debug("entró en cierre primario TV ********");
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
    
			// busca la peticion VPI
    
			PeticionKey key = new PeticionKey (numeroPeticion) ;
    
			PeticionLocal peticion = peticionHome.findByPrimaryKey (key) ;
    
			// actualiza algunas fechas (sacado version VPI Chile)
    
			Timestamp ahora = new Fecha ().getTimestamp () ;
    
			peticion.setPeti_fecha_modificacion (ahora) ;
    
			if (peticion.getPeti_fecha_termino () == null)
				peticion.setPeti_fecha_termino (ahora) ;
    
			//busca si existen peticiones hermanas (hijas de la misma peticion ATIS) que todavia estan en curso
			//todas las hermanas en curso deben tener cierre anticipado ok(Peticion_flujo.acti_id = 50 o 51 y Peticion_flujo.pefl_estado = ok), en caso contrario no se envia cierreanticipado
			//todas la peticiones hermanas tienen igual peticion.cod_pet_cd y distinto peti_numero
			boolean envioCierrePrimario = true ;
    
			Peticion_atisLocal peticionAtis = peticion.getFk_01_pet_atis () ;
	
			Collection hermanas = peticionAtis.getPeticion () ;
    
			Iterator iterHermanas = hermanas.iterator () ;
			while (iterHermanas.hasNext ())//TODO calcula hermanas
			{
				PeticionLocal unaHermana = (PeticionLocal) iterHermanas.next () ;
				PeticionKey petK=(PeticionKey)unaHermana.getPrimaryKey();
				if(petK.peti_numero.longValue()==numeroPeticion.longValue())
					continue;  
				Collection lstPetFluj=peticion_flujoHome.findByIdPeticion(petK.peti_numero);
				for(Iterator iterPetFlujo = lstPetFluj.iterator();iterPetFlujo.hasNext();){
					Peticion_flujoLocal unaPetFlujo = (Peticion_flujoLocal)iterPetFlujo.next();
					//Defecto cierre primario - 27 feb 2009
					if(unaPetFlujo.getIdActividad().intValue()== 50 || unaPetFlujo.getIdActividad().intValue()== 51){
						if(!"OK".equalsIgnoreCase(unaPetFlujo.getPefl_estado())){
						envioCierrePrimario = false;
						break;
					}						
				}
			}
			}
    		//			 Se coloca la validación del código de franqueo, requerimiento
			// 24742.
			log.debug("se va a validar codigo de franqueo");
			validarCodFranqueo(numeroPeticion);
			log.debug("Salio de validar franqueo");
			revisaTrasladoBa(numeroPeticion);
    
			if (envioCierrePrimario)
			{
				enviaMensajeCierreTecnicoPrimario (peticionAtis,peticion) ;
			}
		}
		catch (NamingException nex)
		{
			log.debug("Error en Cierre Primario TV Peticion[" + numeroPeticion + "].",nex);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING, nex);
		}
		catch (FinderException fex)
		{
			log.debug("Error en Cierre Primario TV Peticion[" + numeroPeticion + "].",fex);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER, fex);
		}
	}
	public final String COD_FRANQUEO = "COD_FRANQUEO";

	/**
	 * Validación baja de deco adicional, valida si se puede cerrar
	 * satisfactoriamente la baja
	 * 
	 * @author JE
	 * @param numeroPeticion
	 *            el número de petición.
	 * @throws FinderException
	 *             Cuando no se encuentra registrado el número de petición
	 * @throws NamingException
	 *             Cuando no se encuentra el bean registrado en JNDI
	 */
	public void validarCodFranqueo(Long numeroPeticion) throws FinderException,
			NamingException {
		log.debug("se entra a validar el codigo de franqueo");
		Agenda_scLocalHome agendascLocalHome;
		agendascLocalHome = (Agenda_scLocalHome) HomeFactory
				.getHome(Agenda_scLocalHome.JNDI_NAME);
		Collection agendaScList = agendascLocalHome
				.findByPetiNumero(numeroPeticion);
		String codFranqueo = recuperarParametroFromPropertiesBD(COD_FRANQUEO);
		log.debug("Los codigos franqueo: " + codFranqueo);
		int result = 0;
		
		log.debug("Size: " + agendaScList.size());
		
		Producto_servicio_peticionLocalHome productoServicioPeticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory
		.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		Collection pspList = productoServicioPeticionLocalHome
		.findAllByPetiNumero(numeroPeticion);
		
		for (Iterator agendaScIterator = agendaScList.iterator(); agendaScIterator
				.hasNext();) {
			Agenda_scLocal agendascLocal = (Agenda_scLocal) agendaScIterator
					.next();
			if (agendascLocal.getCod_franqueo() != null) {
				log.debug("Valida Cod_Franqueo "
						+ codFranqueo.indexOf(agendascLocal.getCod_franqueo()
								.trim()));
				result = codFranqueo.indexOf(agendascLocal.getCod_franqueo()
						.trim()) == -1 ? 0 : 1;
				log.debug("Estado baja = " + result);
				for (Iterator pspIterator = pspList.iterator(); pspIterator.hasNext();) {
					Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal) pspIterator
							.next();
					pspLocal.setEstado_baja(new Long(result));
					log.debug("inserto estado baja");
				}
			}
		}

		Deco_tarjetaLocalHome decoTarjetaLocalHome;
		decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
				.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
		Collection decoTarjeta = decoTarjetaLocalHome
				.findByPeticion(numeroPeticion);
		int diaAdicional = 1;
		int cantidadDecos = pspList.size();
		
		for (Iterator decoTarjetaIterator = decoTarjeta.iterator(); decoTarjetaIterator
				.hasNext() && cantidadDecos > 0; cantidadDecos--) {
		
			try {
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				Calendar fechaActual = Calendar.getInstance();
				fechaActual.add(Calendar.DATE,diaAdicional);
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formatoFecha.format(fechaActual.getTime()));
				Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decoTarjetaIterator
				.next();
				decoTarjetaLocal.setFec_ejec_atis(new Timestamp(
						date.getTime()));
				diaAdicional++;
				log.debug("Agrega fecha en Fec_ejec_atis");
			} catch (ParseException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al ingresar la fecha "+ e);
			}
			
		}
		
	}
	//	CR 10394 -Pablo Cawen- Fin - 13/05/2008
    
	private void revisaTrasladoBa(Long numeroPeticion) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Traslado_solobaLocalHome traslado_solobaHome=(Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
			Traslado_solobaLocal traslado_solobaLocal=traslado_solobaHome.findByPetiAlta(numeroPeticion);
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			traslado_solobaLocal.setCierre_alta("S");
			String  cod_actividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_ESPERA_DESBLOQUEO");
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			if(traslado_solobaLocal.getPeti_baja()==null)
			{
				log.info("No ha llegado la baja .. no hay nada que desbloquear");
				return;
			}
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(traslado_solobaLocal.getPeti_baja(), cod_actividad);

			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(numeroPeticion));
			boolean tieneQuiebre=false;
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				int idFamilia=familia_producto_servicioKey.faps_id.intValue();
//				REQ BA NAKED adicion familia PC naked
				if(idFamilia!=familiaPcBA && idFamilia!=familiaPcBANaked)
					continue;
				Collection estadoPsPeticion=producto_servicio_peticionLocal.getEstado_ps_peticion();
				if(estadoPsPeticion.size()>0)
				{
					Estado_ps_peticionLocal primero=(Estado_ps_peticionLocal) estadoPsPeticion.iterator().next();
					if(primero.getCod_estado_cierre().intValue()==estadoCierreError)
						tieneQuiebre=true;
				}
			}
			if(!tieneQuiebre)
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,"");
			else
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,"3");
			
			actDto.setObservacion("Ya cerro la actividad de alta de traslado solo Ba para la peticion atiempo "+numeroPeticion);
			actividadEJB.terminar(actDto);
		}
		catch (FinderException e)
		{
			log.debug("No estoy cerrando traslado solo ba alta");	
		}
		catch (TnProcesoExcepcion e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS,e);
		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
	}

	private static final String OK = "00" ;
    private static final String OK_NOVEDAD = "04" ;
    private static final String NO_OK = "08" ;
    

	/**
	 * prepara y envia el mensaje de cierre a ATIS
	 * 
	 * la logica mas complejo corresponde a como se llena el tag
	 * Group1.setComercialProductIdNumber () (ver comentarios en mismo codigo)
	 * 
	 * @author francois
	 * 
	 * @param peticionAtis
	 *            la peticion ATIS
	 */

	private void enviaMensajeCierreTecnico(Peticion_atisLocal peticionAtis,
			Long numeroPeticion) throws ATiempoAppEx, FinderException,
			NamingException {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 15, 2009
		Familia_producto_servicioLocalHome familiaPsLocalHome = (Familia_producto_servicioLocalHome) HomeFactory
				.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
		Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory
				.getHome(Operacion_comercialLocalHome.JNDI_NAME);

		PeticionesInterfaces pI = new PeticionesDelegate();

		TR001E tr001e = new TR001E();

		Long idCorrelativoMensaje = new Long(dbSeq
				.seqNextValLong("CORRELATIVO_MENSAJE"));

		tr001e.setId(idCorrelativoMensaje.toString());

		Peticion_atisKey atisRequestKey = (Peticion_atisKey) peticionAtis
				.getPrimaryKey();

		long atisRequestNumber = atisRequestKey.cod_pet_cd.longValue();

		pI.insertarQuiebresFinalesPeticionesVuelo(numeroPeticion);

		tr001e.setAtisRequestNumber(atisRequestNumber);

		List groups = new ArrayList(peticionAtis.getAgrupacion_atis().size());

		tr001e.setGroups(groups);

		Iterator iterAgrupacion = peticionAtis.getAgrupacion_atis().iterator();

		while (iterAgrupacion.hasNext()) {
			Agrupacion_atisLocal agrupacion = (Agrupacion_atisLocal) iterAgrupacion
					.next();
			Agrupacion_atisKey agrupacion_atisKey = (Agrupacion_atisKey) agrupacion
					.getPrimaryKey();

			Group1 group1 = new Group1();

			int atisGroupNumber = ((Agrupacion_atisKey) agrupacion
					.getPrimaryKey()).cod_agr_sub_nu.intValue();

			log.info("Cierre Peticion Atis[" + atisRequestNumber
					+ "]. Agrupacion:" + atisGroupNumber);

			group1.setAtisGroupNumber(atisGroupNumber);

			List subRequests = new ArrayList(agrupacion.getSubpeticion_atis()
					.size());

			group1.setSubRequests(subRequests);

			// usado para saber que tipo de agrupacion es (Telefono o TV)
			Integer tipoAgrupacion = tipoAgrupacionNoDeterminado;

			// el primer ps en la Agrupacion (y cuya familia determina el tipo
			// de Agrupacion)
			Producto_servicio_peticionLocal psPeticionPrimerPs = null;

			Iterator iterSubPeticion = agrupacion.getSubpeticion_atis()
					.iterator();
			Long faps_id_padre = null;
			while (iterSubPeticion.hasNext()) {
				Subpeticion_atisLocal subPeticion = (Subpeticion_atisLocal) iterSubPeticion
						.next();

				SubRequest1 subRequest1 = new SubRequest1();

				Subpeticion_atisKey subPeticionKey = (Subpeticion_atisKey) subPeticion
						.getPrimaryKey();

				int atisSubRequestNumber = subPeticionKey.cod_sub_cd.intValue();

				log.info("Cierre Peticion Atis[" + atisRequestNumber
						+ "]. Subpeticion:" + atisSubRequestNumber);
				subRequest1.setAtisSubRequestNumber(atisSubRequestNumber);

				Collection pss = subPeticion.getProducto_servicio_peticion();

				// chequea que la relacion Subpeticion con
				// Producto_servicio_peticion es de 1 a 1

				if (pss.size() == 0) {
					throw new ATiempoAppEx("Cierre Peticion Atis[" + atisRequestNumber + "]: No encontre el producto_servicio_peticon asociado a la sub peticion ATIS: peticion_ATIS/agrupacion_ATIS/sub_peticion_ATIS = " + atisRequestNumber + "/" + atisGroupNumber + "/" + atisSubRequestNumber);
				}

				else if (pss.size() > 1) {
					throw new ATiempoAppEx("Cierre Peticion Atis[" + atisRequestNumber + "]: Encontre mas de un producto_servicio_peticon asociado a la sub peticion ATIS: peticion_ATIS/agrupacion_ATIS/sub_peticion_ATIS = " + atisRequestNumber + "/" + atisGroupNumber + "/" + atisSubRequestNumber);
				}

				//

				Producto_servicio_peticionLocal psPeticion = (Producto_servicio_peticionLocal) (pss.iterator().next());

				llenaSubRequest1(subRequest1, psPeticion);

				subRequests.add(subRequest1);

				// cambio 20-04-2007: no hay siempre 1 ps tipo PC en la
				// agrupacion
				//
				// ya no hace lo siguiente
				//
				// <<
				// busca el PS tipo PC de esta agrupacion para determinar si la
				// agrupacion es tipo "linea" o de tipo "TV"
				// es para saber si numIdeNu es un numero de telefono o un
				// numero de TV
				// >>
				//
				// y lo reemplaza por
				//
				// <<
				// - busca la familia del primer PS en la agrupacion
				// - si la familia es una hija recorre hasta encontrar la
				// "madre" original
				// - aplica la siguiente transformacion
				//   - si familia == familiaLinea o familiaBandaAncha -> tipo
				// agrupacion = tipoAgrupacionLinea
				//   - si familia == familiaTV -> tipo agrupacion =
				// tipoAgrupacionTV
				// >>

				if ((tipoAgrupacion == tipoAgrupacionIT)) {
					log.debug("Entro a Validar si la Agrupacion Tiene PS diferentes de Venta de Equipos");
					Producto_servicioLocal ps = psPeticion.getProducto_servicio();
					Familia_producto_servicioLocal familiaPs = ps.getFamilia_producto_servicio();
					long idFamilia = ((Familia_producto_servicioKey) familiaPs.getPrimaryKey()).faps_id.longValue();
					if (idFamilia != familiaIntEquipado) {
						psPeticionPrimerPs = null;
						tipoAgrupacion = tipoAgrupacionNoDeterminado;
					}
				}

				if (psPeticionPrimerPs == null) {
					psPeticionPrimerPs = psPeticion;

					Producto_servicioLocal ps = psPeticion.getProducto_servicio();
//              busca la familia "madre" (STB, BA o TV)

				Familia_producto_servicioLocal familiaPs = ps.getFamilia_producto_servicio();

				while (faps_id_padre != null && faps_id_padre.longValue() != 0) {
					Familia_producto_servicioKey key = new Familia_producto_servicioKey(faps_id_padre);

					familiaPs = familiaPsLocalHome.findByPrimaryKey(key);
					faps_id_padre = familiaPs.getFaps_id_padre();
				}

                long idFamilia = ((Familia_producto_servicioKey) familiaPs.getPrimaryKey()).faps_id.longValue();

					log.debug("La familia de la subpetición es: " + idFamilia);
					if (idFamilia == familiaLinea
							|| idFamilia == familiaBandaAncha
							|| idFamilia == familiaPcLinea
							|| idFamilia == familiaIP
							|| idFamilia == familiaPcBA
							|| idFamilia == familiaAsistencia
							|| idFamilia == familiaAsistRemota
							|| idFamilia == familiaVisitaAsist) {
						log.debug("Agrupación LB de la subpetición es: " + tipoAgrupacionLinea + " y tiene familia : " + idFamilia);
						tipoAgrupacion = tipoAgrupacionLinea;
					} else if (idFamilia == familiaPcTV
							|| idFamilia == familiaDecoTV
							|| idFamilia == familiaDecoPVRTV
							|| idFamilia == familiaDecoHDTV
							|| idFamilia == familiaTematicoTV
							|| idFamilia == familiaPaqueteTV
							|| idFamilia == familiaTV) {
						log.debug("Agrupación TV de la subpetición es: " + tipoAgrupacionLinea + " y tiene familia : " + idFamilia);
						tipoAgrupacion = tipoAgrupacionTV;
					} else if (idFamilia == familiaIC) {
						log.debug("Agrupación  IC de la subpetición es: " + tipoAgrupacionLinea + " y tiene familia : " + idFamilia);
						tipoAgrupacion = tipoAgrupacionIC;
					} else if (idFamilia == familiaIntEquipado) {
						log.debug("Agrupación IT de la subpetición es: " + tipoAgrupacionLinea + " y tiene familia : " + idFamilia);
						tipoAgrupacion = tipoAgrupacionIT;
					}
					/**
					 * DAVID: se adiciona el siguiente if para RQ 28274, cobro
					 * incidencias.
					 */
					else if (idFamilia == familiaMantenimiento) {
						tipoAgrupacion = tipoAgrupacionMNT;
					} else if (idFamilia == familiaPresenciaDigital){
						tipoAgrupacion = tipoAgrupacionPDG;
				
					//RQ Napster SE ADICIONA ESTE IF PARA LA FAMILIA NAPSTER 316 dcardena
					//RQ SVAs se modifica Napster
                    }else if (idFamilia==familiaPS_SVA)
                    {
                    	if(agrupacion.getNum_ide_nu ().startsWith("TV"))
                    	{
                    		log.debug("Agrupación TV SVAs de la subpetición es: "+tipoAgrupacionTV+" y tiene familia : "+idFamilia);
                    		tipoAgrupacion= tipoAgrupacionTV;
                    	}else{
                    		log.debug("Agrupación LB SVAs de la subpetición es: "+tipoAgrupacionLinea+" y tiene familia : "+idFamilia);                      	
                    	tipoAgrupacion= tipoAgrupacionLinea;
                    	}
//                    	REQ BA NAKED se agrega la familia PC y PS naked
                    }else if(idFamilia == familiaPcBANaked
							|| idFamilia == familiaBandaAnchaNaked || idFamilia ==familiaPcPsBANaked){
                    	log.debug("Agrupación BA NAKED de la subpetición es: "
								+ tipoAgrupacionLinea + " y tiene familia : "
								+ idFamilia);
                    	tipoAgrupacion= tipoAgrupacionLinea;
                    }

				    //Movistar Play se agrega Num_ide_num_mp el valor del idpc
				    else if(idFamilia==familiaPS_GVP || idFamilia == familiaPC_GVP)
				    	tipoAgrupacion= tipoAgrupacionPS_GVP;
					
					//                    else if (idFamilia==familiaAsistencia){
					//					    tipoAgrupacion = tipoAgrupacionAsist;
					//					}else if (idFamilia==familiaAsistRemota){
					//					    tipoAgrupacion = tipoAgrupacionAsistRem;
					//					}else if (idFamilia==familiaVisitaAsist){
					//					    tipoAgrupacion = tipoAgrupacionVisAsist;
					//					}

				}
			}

			// algunos chequeos simples

			if (tipoAgrupacion == tipoAgrupacionNoDeterminado)
				throw new ATiempoAppEx("Cierre Peticion Atis[" + atisRequestNumber + "]. No se puede determinar tipo de agrupacion (Linea o TV)");

			// en caso de TV se retorna el "TV" + num_ide_nu
			if (tipoAgrupacion == tipoAgrupacionTV) {
				String numIdeNu = agrupacion.getNum_ide_nu();

				if (numIdeNu == null) {
					numIdeNu = "TV" + "_" + atisRequestKey.cod_pet_cd + "_" + agrupacion_atisKey.cod_agr_sub_nu;
				} else if (numIdeNu.equals("")) {
					numIdeNu = "TV" + "_" + atisRequestKey.cod_pet_cd + "_" + agrupacion_atisKey.cod_agr_sub_nu;
				}

				group1.setComercialProductIdNumber(numIdeNu);
			}

			// en caso de linea se retorna el numero de telefono nuevo (si es
			// distinto de null) o el actual si no
			// cuidado en la bd: "anterior" es equivalente a "actual" y
			// "asignado" es equivalente a "nuevo"
			else if (tipoAgrupacion == tipoAgrupacionLinea) {
				// busca el recurso de linea basica

				PeticionLocal peticionVPI = psPeticionPrimerPs.getFk_psp_pet();

				Collection colRecursosLineaBasica = peticionVPI.getRecursos_linea_basica();

				if (colRecursosLineaBasica.size() == 0) {
					String esMuv = peticionVPI.getComercialProductIdentification();
					log.debug(esMuv);
					if(esMuv!=null && !esMuv.equals(VpistbbaConfig.getVariable("MM")))
					{
						//Si no hay recursos de linea basica se envia el numero original de la peticion
						//Ejemplo: Cambio Producto que no es necesario realizar ningun flujo y se va directo al Cierre
						group1.setComercialProductIdNumber (agrupacion.getNum_ide_nu()) ;
					}
				}

				else if (colRecursosLineaBasica.size () > 1)
					throw new ATiempoAppEx ("Cierre Peticion Atis[" + atisRequestNumber + "]. Hay mas de un registro en recursos_linea_basica") ;
				
				//Si no hay Recursos, no se setea el setPulseQuantityTo y por lo tanto se envia en 0.
				if(colRecursosLineaBasica.size()==1)
				{
					Recursos_linea_basicaLocal rlb = (Recursos_linea_basicaLocal) colRecursosLineaBasica.iterator ().next () ;

					//Ver el caso especial de Traslado.
					boolean esTraslado=esAgrupacionDeTraslado(agrupacion);
					if(esTraslado)
					{
						log.info("Procesando Cierre de Traslado");
						ArrayList tiposOpCom=retornaTiposOperAgrupacion(agrupacion);
						if(tiposOpCom.contains("ACP") || tiposOpCom.contains("A"))
						{
							//Agrupacion de Alta
							if (rlb.getTelefono_asg () != null)
							{
								group1.setComercialProductIdNumber (rlb.getTelefono_asg ().toString ()) ;
							}
							else
								group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
							if(rlb.getCont_linea_nueva()!=null)
								group1.setPulseQuantityTo(rlb.getCont_linea_nueva().longValue());
						}
						else
						{
							if (rlb.getTelefono_ant () != null)
								group1.setComercialProductIdNumber (rlb.getTelefono_ant ().toString ()) ;
							else
								group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
							if(rlb.getCont_linea_act()!=null)
								group1.setPulseQuantityFrom(rlb.getCont_linea_act().longValue());
						}
					}
					else
					{
						log.info("Procesando Cierre de NO Traslado");
						if (rlb.getTelefono_asg () != null)
						{
							group1.setComercialProductIdNumber (rlb.getTelefono_asg ().toString ()) ;
						}
						else if (rlb.getTelefono_ant () != null)
						{
							group1.setComercialProductIdNumber (rlb.getTelefono_ant ().toString ()) ;
						}
						else
							log.info("Cierre Peticion Atis[" + atisRequestNumber + "]. Registro en recursos_linea_basica no tiene ni el telefono asignado (o nuevo) o el telefono anterior (o actual), se enviara al cierre sin el numero telefono") ;
						if(rlb.getCont_linea_act()!=null)
							group1.setPulseQuantityFrom(rlb.getCont_linea_act().longValue());
						if(rlb.getCont_linea_nueva()!=null)
							group1.setPulseQuantityTo(rlb.getCont_linea_nueva().longValue());
					}
					// retorna el telefono nuevo si existe si no el actual
					if(group1.getComercialProductIdNumber()==null)
						group1.setComercialProductIdNumber("");	
				}
				else
				{
					group1.setComercialProductIdNumber (peticionVPI.getNum_ide_nu_stb()) ;
					group1.setPulseQuantityFrom(0);
					group1.setPulseQuantityTo(0);
				}
			} else if (tipoAgrupacion == tipoAgrupacionIC) {
				String val_ral_crc_cd = null;
				for (Iterator iterator = agrupacion.getSubpeticion_atis().iterator(); iterator.hasNext();) {
					Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal) iterator.next();
					Operacion_comercialLocal local = operacion_comercialHome.findByPrimaryKey(new Operacion_comercialKey(subpeticion_atisLocal.getCod_opr_cmr_cd()));
					if (local.getOpco_tipo() != null && local.getOpco_tipo().equals("A")) {
						Iterator iterCarac = subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();
						while (iterCarac.hasNext()) {
							Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal) iterCarac.next();
							Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
							Long codFatherEmail = new Long(VpistbbaConfig.getVariable("USUACC"));
							if (spk.cod_crc_cd.longValue() == codFatherEmail.longValue()) {
								val_ral_crc_cd = subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
								log.debug("Informacion : Se obtuvo Father Email " + val_ral_crc_cd);
								group1.setComercialProductIdNumber(val_ral_crc_cd);
								break;
							}
						}
					} else
						group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
				}
			} else if (tipoAgrupacion == tipoAgrupacionIT) {
				log.debug("Entro a procesar el cierre para venta de equipos");

				String numIdeNu = agrupacion.getNum_ide_nu();
				if ((numIdeNu != null) && (!numIdeNu.equals(""))) {
					log.debug("Es una peticion postventa de Venta de Equipos " + numIdeNu);
					group1.setComercialProductIdNumber(numIdeNu);
				}else{
	            	forAgrupaciones:for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();){
	            		Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
	            		Subpeticion_atisKey subpeticionAtisKey = (Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey();
	        			
	            		log.debug("Recorre las subpeticion:"+subpeticionAtisKey.cod_sub_cd+","+subpeticionAtisKey.fk_agru_sub_cod_agr_sub_nu+","+subpeticionAtisKey.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
	            		
	            		Agrupacion_atisLocal agrupacionAtisLocal = subpeticion_atisLocal.getFk_agru_sub();
	            		Peticion_atisLocal peticionAtisLocal = agrupacionAtisLocal.getFk_pet_agrupacion();
	            		Collection peticiones = peticionAtisLocal.getPeticion();
	            		            		
	            		for(Iterator iteratorPeticiones=peticiones.iterator();iteratorPeticiones.hasNext();){
	            			PeticionLocal peticionLocal = (PeticionLocal) iteratorPeticiones.next();
	            			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
	            			log.debug("Recorre la peticion: "+peticionKey.peti_numero);
	            			
	            			BintegradaLocalHome bintegradaLocalHome=null;
	            			try{
	            				bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
	            			}catch(NamingException e){
	            				log.error("NamingException. BintegradaLocalHome ", e);
	            			}
	            			BintegradaLocal bintegradaLocal = null;
	            			
	            			if(bintegradaLocalHome != null){
		            			try{
		            				Collection bintegradaCollection = bintegradaLocalHome.findListByPeticion(peticionKey.peti_numero);
		            				if(bintegradaCollection != null && bintegradaCollection.size() > 0){
		            					Iterator bintegradaIterator = bintegradaCollection.iterator();
		            					bintegradaLocal = (BintegradaLocal)bintegradaIterator.next();
		            					String agrupacionBintegrada = bintegradaLocal.getBi_agrupaciones().trim();
		            					Agrupacion_atisKey agrupacionKey = (Agrupacion_atisKey)agrupacionAtisLocal.getPrimaryKey();
		    	            			if(!agrupacionKey.cod_agr_sub_nu.toString().equals(agrupacionBintegrada)){
		    	            				continue;
		    	            			}
		            				}
		            			}catch(FinderException e){
		            				log.error("FinderException. Peticion no encontrada en BIntegrada. [" + peticionKey.peti_numero + "]");
		            			}
	            			}
	            			
	            				            			
	            			Collection elementos =peticionLocal.getElemento_peticion();
	            			if(elementos.isEmpty()){
	            				group1.setComercialProductIdNumber(peticionLocal.getIdentificadorOriLinea());
	            			}
	            			for(Iterator iteratorElementos=elementos.iterator();iteratorElementos.hasNext();){
	                			Elemento_PeticionLocal elementoPeticionLocal=(Elemento_PeticionLocal) iteratorElementos.next();
	                			log.debug("Los valores a comparar son elementoPeticion.getPs_id: "+elementoPeticionLocal.getPs_id().longValue()
	                					+" con  subpeticion_atis.getCod_pro_ser_cd:" + subpeticion_atisLocal.getCod_pro_ser_cd().longValue());
	                			
	                            Producto_servicioLocalHome psHome = null;
	                            try {
	                                psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
	                            } catch (NamingException e) {
	                                log.error("NamingException. ProductoServicioLocalHome", e);
	                            }
	                            Producto_servicioLocal psLocal = null;
	                            Producto_servicioLocal psLocalElemento = null;
	                            try {
	                                psLocal = psHome.findByPrimaryKey(new Producto_servicioKey(subpeticion_atisLocal.getCod_pro_ser_cd()));
	                                psLocalElemento = psHome.findByPrimaryKey(new Producto_servicioKey(elementoPeticionLocal.getPs_id()));
	                            } catch (FinderException e) {
	                                log.error("FinderException. PS No encontrado. [" + subpeticion_atisLocal.getCod_pro_ser_cd() + "]");
	                            } catch (Exception e) {
	                                log.error("Exception. PS No encontrado. [" + subpeticion_atisLocal.getCod_pro_ser_cd() + "]:" + e.getMessage());
	                            }
	                			Long famPsIdLocal = ((Familia_producto_servicioKey)(((Familia_producto_servicioLocal) psLocal.getFamilia_producto_servicio()).getPrimaryKey())).faps_id;
	                			Long famPsIdLocalElemento = ((Familia_producto_servicioKey)(((Familia_producto_servicioLocal) psLocalElemento.getFamilia_producto_servicio()).getPrimaryKey())).faps_id;
	                			 
	                			if (famPsIdLocal.longValue() == famPsIdLocalElemento.longValue()){
	                				log.debug("El elemento se encontró y se procede a asignarlo en la operación comercial");
	                				group1.setComercialProductIdNumber(peticionKey.peti_numero.toString()+"_"+elementoPeticionLocal.getSerial());
	                				break forAgrupaciones;
	            				}else{
	            					log.debug("El elemento no conincide y se procede a análizar el siguiente");
	            				}
	                		}
	           			}            		
	        		}					
				}
            }else if (tipoAgrupacion == tipoAgrupacionPDG)
            {
            	Collection peticiones = peticionAtis.getPeticion();
            for(Iterator iteratorPeticiones=peticiones.iterator();iteratorPeticiones.hasNext();){
					PeticionLocal peticionLocal = (PeticionLocal) iteratorPeticiones.next();
					String idPc=peticionLocal.getNum_ide_nu_pdg();
					if(idPc!=null && !idPc.equals("")){
						group1.setComercialProductIdNumber(idPc);
						break;
					}
            }
			 }else if (tipoAgrupacion == tipoAgrupacionPS_GVP){
        	
			 	Collection peticiones = peticionAtis.getPeticion();

				for (Iterator iteratorPeticiones = peticiones.iterator(); iteratorPeticiones.hasNext();) {
					PeticionLocal peticionLocal = (PeticionLocal) iteratorPeticiones.next();
					String idPc = peticionLocal.getNum_ide_nu_mp();
					if (idPc != null && !idPc.equals("")) {
						group1.setComercialProductIdNumber(idPc);
						break;
					}
				}
            }
            /**
		     * DAVID: se adiciona el siguiente if para RQ 28274, cobro incidencias.
		     */
            else if (tipoAgrupacion==tipoAgrupacionMNT){
            	log.debug("Se envia PS de mantenimiento, familia 308");
            }
//            else if (tipoAgrupacion==tipoAgrupacionAsist){
//            	log.debug("Se envia PS de Prov1, familia 309");
//            }
//            else if (tipoAgrupacion==tipoAgrupacionAsistRem){
//            	log.debug("Se envia PS de Asistencia remota, familia 311");
//            }
//            else if (tipoAgrupacion==tipoAgrupacionVisAsist){
//            	log.debug("Se envia PS de Asistencia remota, familia 310");
//            }
            else
                throw new ATiempoAppEx ("Cierre Peticion Atis[" + atisRequestNumber + "]. Tipo de agrupacion desconocido: no se sabe que enviar como numero de producto comercial") ;
            
            //
            
            /*mfmendez - no se envia campo comercial-product-identification-number*/
            if(group1.getComercialProductIdNumber() == null)
            	group1.setComercialProductIdNumber("");
            
            groups.add (group1) ;
        }
//		envia el mensaje. Leo: saque el envio del iterator de agrupacion. debe enviar un puro mensaje.
		log.info("Cierre Peticion Atis[" + atisRequestNumber + "]. Envio Mensaje de Cierre.") ;
		new CierrePeticionATISMQ ().enviarMensaje (tr001e) ;
		for(Iterator iterator=peticionAtis.getPeticion().iterator();iterator.hasNext();)
		{
			PeticionLocal local=(PeticionLocal) iterator.next();
			local.setEspe_id(new Integer(ComunInterfaces.estadoPeticionTerminada));
		}
    }
	//	CR 10394 -Pablo Cawen- Inicio - 13/05/2008
	/**
	 * Cierre Tecnico Primario
	 * prepara y envia el mensaje de cierre primario a ATIS
	 *
	 * @author 804226
	 *
	 * @param peticionAtis la peticion ATIS
	 */

	private void enviaMensajeCierreTecnicoPrimario (Peticion_atisLocal peticionAtis, PeticionLocal peticion)
	throws ATiempoAppEx, FinderException, NamingException
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
		Familia_producto_servicioLocalHome familiaPsLocalHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
		Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
		
		log.debug("Entre en enviar mensaje cierre tecnico");
		TR001E tr001e = new TR001E () ;
    
		Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
    
		tr001e.setId (idCorrelativoMensaje.toString ()) ;
		
		//Clave no utilizada, se usará para diferenciar el mensaje de cierre
		// anticipado del mensaje final.
		tr001e.setError(true);
    
		Peticion_atisKey atisRequestKey = (Peticion_atisKey) peticionAtis.getPrimaryKey () ;
		
		PeticionKey peticionkey = (PeticionKey) peticion.getPrimaryKey();
    
		long atisRequestNumber = atisRequestKey.cod_pet_cd.longValue () ;
    
		tr001e.setAtisRequestNumber (atisRequestNumber) ;
    
		List groups = new ArrayList (peticionAtis.getAgrupacion_atis ().size ()) ;
    
		tr001e.setGroups (groups) ;
    
		Iterator iterAgrupacion = peticionAtis.getAgrupacion_atis ().iterator () ;
    
		while (iterAgrupacion.hasNext ())
		{
			
			Agrupacion_atisLocal agrupacion = (Agrupacion_atisLocal) iterAgrupacion.next () ;
			Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) agrupacion.getPrimaryKey();
         
			Group1 group1 = new Group1 () ;
			
        
			int atisGroupNumber = ((Agrupacion_atisKey) agrupacion.getPrimaryKey ()).cod_agr_sub_nu.intValue () ;
		
			log.info("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Agrupacion:" + atisGroupNumber);
        
			group1.setAtisGroupNumber (atisGroupNumber) ;
			
			List subRequests; 
			
			if(esSVATemp(peticionkey.peti_numero))
				subRequests = new ArrayList (agrupacion.getSubpeticion_atis ().size ()-1) ;
			else
				subRequests = new ArrayList (agrupacion.getSubpeticion_atis ().size ()) ;
			
			group1.setSubRequests (subRequests) ;
        
			// usado para saber que tipo de agrupacion es (Telefono o TV)
			Integer tipoAgrupacion = tipoAgrupacionNoDeterminado ;
        
			// el primer ps en la Agrupacion (y cuya familia determina el tipo de Agrupacion)
			Producto_servicio_peticionLocal psPeticionPrimerPs = null ;
        
			Iterator iterSubPeticion = agrupacion.getSubpeticion_atis ().iterator () ;
        
			while (iterSubPeticion.hasNext ())
			{
				boolean esSVATemp= false;
				
				Subpeticion_atisLocal subPeticion = (Subpeticion_atisLocal) iterSubPeticion.next () ;
            
				SubRequest1 subRequest1 = new SubRequest1 () ;
            
				Subpeticion_atisKey subPeticionKey = (Subpeticion_atisKey) subPeticion.getPrimaryKey () ;
            
				int atisSubRequestNumber = subPeticionKey.cod_sub_cd.intValue () ;
            
				log.info("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Subpeticion:" + atisSubRequestNumber);
				subRequest1.setAtisSubRequestNumber (atisSubRequestNumber) ;
            
            
				Collection pss = subPeticion.getProducto_servicio_peticion () ;
            
				// chequea que la relacion Subpeticion con Producto_servicio_peticion es de 1 a 1
            
				if (pss.size () == 0)
				{
					throw new ATiempoAppEx ("Cierre Primario Peticion Atis[" + atisRequestNumber + "]: No encontre el producto_servicio_peticon asociado a la sub peticion ATIS: peticion_ATIS/agrupacion_ATIS/sub_peticion_ATIS = " + atisRequestNumber + "/" + atisGroupNumber + "/" + atisSubRequestNumber) ;
				}
            
				else if (pss.size () > 1)
				{
					throw new ATiempoAppEx ("Cierre Primario Peticion Atis[" + atisRequestNumber + "]: Encontre mas de un producto_servicio_peticon asociado a la sub peticion ATIS: peticion_ATIS/agrupacion_ATIS/sub_peticion_ATIS = " + atisRequestNumber + "/" + atisGroupNumber + "/" + atisSubRequestNumber) ;
				}
            
				//
            
				Producto_servicio_peticionLocal psPeticion = (Producto_servicio_peticionLocal) (pss.iterator ().next ()) ;
				
				Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);

				String psSVABA[]= propertiesLocal.getValor().split(",");
				for(int i = 0;i<psSVABA.length;i++){
					if(new Long(psSVABA[i]).equals(psPeticion.getPsId())){
						Properties_BDLocal OC_no_informada = propertiesHome.findByPrimaryKey(ComunInterfaces.OC_NO_INFORMADOS);
						String OCSVABA[]= OC_no_informada.getValor().split(",");
						for(int j = 0;j<OCSVABA.length;j++){
							Operacion_comercialKey oc = (Operacion_comercialKey) psPeticion.getOperacion_comercial().getPrimaryKey();
							if(new Long(OCSVABA[j]).equals(oc.opco_id))
								esSVATemp = true;
						}
					}
				}
				
				if(esSVATemp)
					continue;
				
				llenaSubRequest1 (subRequest1, psPeticion) ;
            
				subRequests.add (subRequest1) ;
            
				// - busca la familia del primer PS en la agrupacion
				// - si la familia es una hija recorre hasta encontrar la "madre" original
				// - aplica la siguiente transformacion
				//   - si familia == familiaLinea o familiaBandaAncha -> tipo agrupacion = tipoAgrupacionLinea
				//   - si familia == familiaTV -> tipo agrupacion = tipoAgrupacionTV
				if (psPeticionPrimerPs == null)
				{
					psPeticionPrimerPs = psPeticion ;
                
					Producto_servicioLocal ps = psPeticion.getProducto_servicio () ;
					Producto_servicioKey psKey = (Producto_servicioKey)ps.getPrimaryKey();
                
					// busca la familia "madre" (STB, BA o TV)
                
					Familia_producto_servicioLocal familiaPs = ps.getFamilia_producto_servicio () ;
                
					while (familiaPs.getFaps_id_padre () != null && familiaPs.getFaps_id_padre ().longValue () != 0)
					{
						Familia_producto_servicioKey key = new Familia_producto_servicioKey (familiaPs.getFaps_id_padre ()) ;
                    
						familiaPs = familiaPsLocalHome.findByPrimaryKey (key) ;
					}
                
					//
                
					long idFamilia = ((Familia_producto_servicioKey) familiaPs.getPrimaryKey ()).faps_id.longValue () ;
//                	REQ BA NAKED adicion familia PC naked
					if (idFamilia==familiaPcBANaked || idFamilia ==familiaPcPsBANaked ||idFamilia == familiaLinea || idFamilia == familiaBandaAncha || idFamilia==familiaPcLinea || idFamilia == familiaIP || idFamilia==familiaPcBA || idFamilia == familiaAsistencia)
					{
						tipoAgrupacion = tipoAgrupacionLinea ;
					}
					else if (idFamilia==familiaPcTV || idFamilia==familiaDecoTV ||idFamilia==familiaDecoPVRTV|| idFamilia==familiaTematicoTV || idFamilia==familiaPaqueteTV || idFamilia==familiaTV 
							|| idFamilia==familiaDecoHDTV )
					{
						tipoAgrupacion = tipoAgrupacionTV ;
					}
					else if(idFamilia==familiaIC)
					{
						tipoAgrupacion = tipoAgrupacionIC;
					} else if (idFamilia == familiaIntEquipado) {

						tipoAgrupacion = tipoAgrupacionIT;
					} else if (idFamilia == familiaMantenimiento) {

						tipoAgrupacion = tipoAgrupacionMNT;
					//RQ Napster SE ADICIONA ESTE IF PARA LA FAMILIA NAPSTER 316 dcardena
						//RQ SVas se modifica napster
                	}else if (idFamilia==familiaPS_SVA)
                	{
                	if(agrupacion.getNum_ide_nu ().startsWith("TV"))
                	{
                		log.debug("Agrupación TV SVAs de la subpetición es: "+tipoAgrupacionTV+" y tiene familia : "+idFamilia);
                		tipoAgrupacion= tipoAgrupacionTV;
                	}else{
                		log.debug("Agrupación LB SVAs de la subpetición es: "+tipoAgrupacionLinea+" y tiene familia : "+idFamilia);                      	
                	tipoAgrupacion= tipoAgrupacionLinea;
                	}
                }
				    //Movistar Play se agrega Num_ide_num_mp el valor del idpc
				    else if(idFamilia==familiaPS_GVP || idFamilia == familiaPC_GVP){
				    	tipoAgrupacion= tipoAgrupacionPS_GVP;
				    	log.debug("Se identificó que el ps: " + psKey.ps_id+ " con familia: " + idFamilia+ " es de agrupación: " + tipoAgrupacion);
                	}else if(idFamilia == familiaPcBANaked
							|| idFamilia == familiaBandaAnchaNaked || idFamilia ==familiaPcPsBANaked){
                    	log.debug("Agrupación BA NAKED de la subpetición es: " + tipoAgrupacionLinea + " y tiene familia : " + idFamilia);
                    	tipoAgrupacion= tipoAgrupacionLinea;
                    }
                	
						
				}
			}
        
			// algunos chequeos simples
        
			if (tipoAgrupacion == tipoAgrupacionNoDeterminado){
				throw new ATiempoAppEx ("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. No se puede determinar tipo de agrupacion (Linea o TV)") ;
			}
        
			// en caso de TV se retorna el "TV" + num_ide_nu
			if (tipoAgrupacion == tipoAgrupacionTV)
			{
				String numIdeNu = agrupacion.getNum_ide_nu () ;
            
				if (numIdeNu == null)
				 {
					numIdeNu="TV"+"_"+atisRequestKey.cod_pet_cd+"_"+agrupacion_atisKey.cod_agr_sub_nu;
				 }
				 else if(numIdeNu.equals(""))
				 {
					numIdeNu="TV"+"_"+atisRequestKey.cod_pet_cd+"_"+agrupacion_atisKey.cod_agr_sub_nu;
				 }
            
				group1.setComercialProductIdNumber (numIdeNu) ;
			}
        
			// en caso de linea se retorna el numero de telefono nuevo (si es distinto de null) o el actual si no
			// cuidado en la bd: "anterior" es equivalente a "actual" y "asignado" es equivalente a "nuevo"
			else if (tipoAgrupacion == tipoAgrupacionLinea)
			{
				// busca el recurso de linea basica
           
				PeticionLocal peticionVPI = psPeticionPrimerPs.getFk_psp_pet () ;
        
				Collection colRecursosLineaBasica = peticionVPI.getRecursos_linea_basica () ;

				if (colRecursosLineaBasica.size () == 0)
				{
					String esMuv=peticionVPI.getComercialProductIdentification();
					log.debug(esMuv);
					if(esMuv!=null && !esMuv.equals(VpistbbaConfig.getVariable("MM")))
					{
						//Si no hay recursos de linea basica se envia el numero original de la peticion
						//Ejemplo: Cambio Producto que no es necesario realizar ningun flujo y se va directo al Cierre
						group1.setComercialProductIdNumber (agrupacion.getNum_ide_nu()) ;
					}
				}

				else if (colRecursosLineaBasica.size () > 1)
					throw new ATiempoAppEx ("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Hay mas de un registro en recursos_linea_basica") ;
			
				//Si no hay Recursos, no se setea el setPulseQuantityTo y por lo tanto se envia en 0.
				if(colRecursosLineaBasica.size()==1)
				{
					Recursos_linea_basicaLocal rlb = (Recursos_linea_basicaLocal) colRecursosLineaBasica.iterator ().next () ;

					//Ver el caso especial de Traslado.
					boolean esTraslado=esAgrupacionDeTraslado(agrupacion);
					if(esTraslado)
					{
						log.info("Procesando Cierre Primario de Traslado");
						ArrayList tiposOpCom=retornaTiposOperAgrupacion(agrupacion);
						if(tiposOpCom.contains("ACP") || tiposOpCom.contains("A"))
						{
							//Agrupacion de Alta
							if (rlb.getTelefono_asg () != null)
							{
								group1.setComercialProductIdNumber (rlb.getTelefono_asg ().toString ()) ;
							}
							else
								group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
							if(rlb.getCont_linea_nueva()!=null)
								group1.setPulseQuantityTo(rlb.getCont_linea_nueva().longValue());
						}
						else
						{
							if (rlb.getTelefono_ant () != null)
								group1.setComercialProductIdNumber (rlb.getTelefono_ant ().toString ()) ;
							else
								group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
							if(rlb.getCont_linea_act()!=null)
								group1.setPulseQuantityFrom(rlb.getCont_linea_act().longValue());
						}
					}
					else
					{
						log.info("Procesando Cierre Primario de NO Traslado");
						if (rlb.getTelefono_asg () != null)
						{
							group1.setComercialProductIdNumber (rlb.getTelefono_asg ().toString ()) ;
						}
						else if (rlb.getTelefono_ant () != null)
						{
							group1.setComercialProductIdNumber (rlb.getTelefono_ant ().toString ()) ;
						}
						else
							log.info("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Registro en recursos_linea_basica no tiene ni el telefono asignado (o nuevo) o el telefono anterior (o actual), se enviara al cierre sin el numero telefono") ;
						if(rlb.getCont_linea_act()!=null)
							group1.setPulseQuantityFrom(rlb.getCont_linea_act().longValue());
						if(rlb.getCont_linea_nueva()!=null)
							group1.setPulseQuantityTo(rlb.getCont_linea_nueva().longValue());
					}
					// retorna el telefono nuevo si existe si no el actual
					if(group1.getComercialProductIdNumber()==null)
						group1.setComercialProductIdNumber("");	
				}
				else
				{
					group1.setComercialProductIdNumber (peticionVPI.getNum_ide_nu_stb()) ;
					group1.setPulseQuantityFrom(0);
					group1.setPulseQuantityTo(0);
				}	
			}
			else if (tipoAgrupacion==tipoAgrupacionIC)
			{
				for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();)
				{
					Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
					Operacion_comercialLocal local=operacion_comercialHome.findByPrimaryKey(new Operacion_comercialKey(subpeticion_atisLocal.getCod_opr_cmr_cd()));
					if(local.getOpco_tipo()!=null && local.getOpco_tipo().equals("A"))
					{
						Iterator iterCarac=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();
						while (iterCarac.hasNext())
						{
							Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
							Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
							Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
							if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue())
							{
								log.debug("Informacion : Se obtuvo Father Email " + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								group1.setComercialProductIdNumber(subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								break;
							}
						}
					}
					else
						group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
				}
			}
			else if (tipoAgrupacion==tipoAgrupacionIT){
            	log.debug("Entro a procesar el cierre primario para venta de equipos");
				String numIdeNu = agrupacion.getNum_ide_nu () ;
				
				if ((numIdeNu != null) && (!numIdeNu.equals(""))){
	            	log.debug("Es una peticion postventa de Venta de Equipos "+ numIdeNu);
					group1.setComercialProductIdNumber(numIdeNu);
				}else{
	            	log.debug("Es una peticion de Alta de Venta de Equipos "+ numIdeNu);
					for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();){
	            		Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
	            		Subpeticion_atisKey subpeticionAtisKey = (Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey();
	        			
	            		log.debug("Recorre las subpeticion:"+subpeticionAtisKey.cod_sub_cd+","+subpeticionAtisKey.fk_agru_sub_cod_agr_sub_nu+","+subpeticionAtisKey.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
	            		
	            		Agrupacion_atisLocal agrupacionAtisLocal = subpeticion_atisLocal.getFk_agru_sub();
	            		Peticion_atisLocal peticionAtisLocal = agrupacionAtisLocal.getFk_pet_agrupacion();
	            		Collection peticiones = peticionAtisLocal.getPeticion();
	            		            		
	            		for(Iterator iteratorPeticiones=peticiones.iterator();iteratorPeticiones.hasNext();){
	            			PeticionLocal peticionLocal = (PeticionLocal) iteratorPeticiones.next();
	            			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
	            			log.debug("Recorre la peticion: "+peticionKey.peti_numero);
	            			
	            			Collection elementos =peticionLocal.getElemento_peticion();
	            			if(elementos.isEmpty()){
	            				group1.setComercialProductIdNumber(peticionLocal.getIdentificadorOriLinea());
	            			}
	            			for(Iterator iteratorElementos=elementos.iterator();iteratorElementos.hasNext();){
	                			Elemento_PeticionLocal elementoPeticionLocal=(Elemento_PeticionLocal) iteratorElementos.next();
	                			log.debug("Los valores a comparar son elementoPeticion.getPs_id: "+elementoPeticionLocal.getPs_id().longValue()
	                					+" con  subpeticion_atis.getCod_pro_ser_cd:" + subpeticion_atisLocal.getCod_pro_ser_cd().longValue());
	                			
	                            Producto_servicioLocalHome psHome = null;
	                            try {
	                                psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
	                            } catch (NamingException e) {
	                                log.error("NamingException. ProductoServicioLocalHome", e);
	                            }
	                            Producto_servicioLocal psLocal = null;
	                            Producto_servicioLocal psLocalElemento = null;
	                            try {
	                                psLocal = psHome.findByPrimaryKey(new Producto_servicioKey(subpeticion_atisLocal.getCod_pro_ser_cd()));
	                                psLocalElemento = psHome.findByPrimaryKey(new Producto_servicioKey(elementoPeticionLocal.getPs_id()));
	                            } catch (FinderException e) {
	                                log.error("FinderException. PS No encontrado. [" + subpeticion_atisLocal.getCod_pro_ser_cd() + "]");
	                            } catch (Exception e) {
	                                log.error("Exception. PS No encontrado. [" + subpeticion_atisLocal.getCod_pro_ser_cd() + "]:" + e.getMessage());
	                            }
	                			Long famPsIdLocal = ((Familia_producto_servicioKey)(((Familia_producto_servicioLocal) psLocal.getFamilia_producto_servicio()).getPrimaryKey())).faps_id;
	                			Long famPsIdLocalElemento = ((Familia_producto_servicioKey)(((Familia_producto_servicioLocal) psLocalElemento.getFamilia_producto_servicio()).getPrimaryKey())).faps_id;
	                			 
	                			if (famPsIdLocal.longValue() == famPsIdLocalElemento.longValue()){
	                				log.debug("El elemento se encontró y se procede a asignarlo en la operación comercial");
	                				group1.setComercialProductIdNumber(elementoPeticionLocal.getSerial());
	            				}else{
	            					log.debug("El elemento no conincide y se procede a análizar el siguiente");
	            				}
	                		}
	           			}            		
	        		}					
				}
            }else if (tipoAgrupacion == tipoAgrupacionPS_GVP){
	        	
			 	Collection peticiones = peticionAtis.getPeticion();

				for (Iterator iteratorPeticiones = peticiones.iterator(); iteratorPeticiones
						.hasNext();) {
					PeticionLocal peticionLocal = (PeticionLocal) iteratorPeticiones
							.next();
					String idPc = peticionLocal.getNum_ide_nu_mp();
					if (idPc != null && !idPc.equals("")) {
						group1.setComercialProductIdNumber(idPc);
						break;
					}
				}
			}else
				throw new ATiempoAppEx ("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Tipo de agrupacion desconocido: no se sabe que enviar como numero de producto comercial") ;
                
			 /*mfmendez - no se envia campo comercial-product-identification-number*/
            if(group1.getComercialProductIdNumber() == null)
            	group1.setComercialProductIdNumber("");
            
			groups.add (group1) ;
		}
//			envia el mensaje. Leo: saque el envio del iterator de agrupacion. debe enviar un puro mensaje.
		log.info("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Envio Mensaje de Cierre Primario.") ;
		new CierrePeticionATISMQ ().enviarMensaje (tr001e) ;
		//no debo marcarlo como cerrada
		for(Iterator iterator=peticionAtis.getPeticion().iterator();iterator.hasNext();)
		{
			PeticionLocal local=(PeticionLocal) iterator.next();
			local.setEspe_id(new Integer(ComunInterfaces.estadoPeticionEnCurso));
		}
	}
    //Cierre tecnico Primario/
    
	private void enviaMensajeCierreTecnicoPrimarioTv (Peticion_atisLocal peticionAtis)
	throws ATiempoAppEx, FinderException, NamingException
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
		Familia_producto_servicioLocalHome familiaPsLocalHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
		Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
		
		log.debug("Entre en enviar mensaje cierre tecnico");
            
		TR001E tr001e = new TR001E () ;

		Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));

		tr001e.setId (idCorrelativoMensaje.toString ()) ;
	
		//Clave no utilizada, se usará para diferenciar el mensaje de cierre
		// anticipado del mensaje final.
		//Si está seteada en TRUE, entonces es el cierre anticipado.
		tr001e.setError(true);

		Peticion_atisKey atisRequestKey = (Peticion_atisKey) peticionAtis.getPrimaryKey () ;

		long atisRequestNumber = atisRequestKey.cod_pet_cd.longValue () ;

		tr001e.setAtisRequestNumber (atisRequestNumber) ;

		List groups = new ArrayList (peticionAtis.getAgrupacion_atis ().size ()) ;

		tr001e.setGroups (groups) ;

		Iterator iterAgrupacion = peticionAtis.getAgrupacion_atis ().iterator () ;

		while (iterAgrupacion.hasNext ())
		{
			Agrupacion_atisLocal agrupacion = (Agrupacion_atisLocal) iterAgrupacion.next () ;
			Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) agrupacion.getPrimaryKey();
     
			Group1 group1 = new Group1 () ;
    
			int atisGroupNumber = ((Agrupacion_atisKey) agrupacion.getPrimaryKey ()).cod_agr_sub_nu.intValue () ;
	
			log.info("Cierre Primario TV Peticion Atis[" + atisRequestNumber + "]. Agrupacion:" + atisGroupNumber);
    
			group1.setAtisGroupNumber (atisGroupNumber) ;
    
			List subRequests = new ArrayList (agrupacion.getSubpeticion_atis ().size ()) ;
    
			group1.setSubRequests (subRequests) ;
    
			// usado para saber que tipo de agrupacion es (Telefono o TV)
			Integer tipoAgrupacion = tipoAgrupacionNoDeterminado ;
    
			// el primer ps en la Agrupacion (y cuya familia determina el tipo de Agrupacion)
			Producto_servicio_peticionLocal psPeticionPrimerPs = null ;
    
			Iterator iterSubPeticion = agrupacion.getSubpeticion_atis ().iterator () ;
			Long faps_id_padre = null;
			while (iterSubPeticion.hasNext ())
			{
				Subpeticion_atisLocal subPeticion = (Subpeticion_atisLocal) iterSubPeticion.next () ;
        
				SubRequest1 subRequest1 = new SubRequest1 () ;
        
				Subpeticion_atisKey subPeticionKey = (Subpeticion_atisKey) subPeticion.getPrimaryKey () ;
        
				int atisSubRequestNumber = subPeticionKey.cod_sub_cd.intValue () ;
        
				log.info("Cierre Primario TV Peticion Atis[" + atisRequestNumber + "]. Subpeticion:" + atisSubRequestNumber);
				subRequest1.setAtisSubRequestNumber (atisSubRequestNumber) ;
        
        
				Collection pss = subPeticion.getProducto_servicio_peticion () ;
        
				// chequea que la relacion Subpeticion con Producto_servicio_peticion es de 1 a 1
        
				if (pss.size () == 0)
				{
					throw new ATiempoAppEx ("Cierre Primario TV Peticion Atis[" + atisRequestNumber + "]: No encontre el producto_servicio_peticon asociado a la sub peticion ATIS: peticion_ATIS/agrupacion_ATIS/sub_peticion_ATIS = " + atisRequestNumber + "/" + atisGroupNumber + "/" + atisSubRequestNumber) ;
				}
        
				else if (pss.size () > 1)
				{
					throw new ATiempoAppEx ("Cierre Primario TV Peticion Atis[" + atisRequestNumber + "]: Encontre mas de un producto_servicio_peticon asociado a la sub peticion ATIS: peticion_ATIS/agrupacion_ATIS/sub_peticion_ATIS = " + atisRequestNumber + "/" + atisGroupNumber + "/" + atisSubRequestNumber) ;
				}
        
				//
        
				Producto_servicio_peticionLocal psPeticion = (Producto_servicio_peticionLocal) (pss.iterator ().next ()) ;
        
				llenaSubRequest1 (subRequest1, psPeticion) ;
        
				subRequests.add (subRequest1) ;
        
				// - busca la familia del primer PS en la agrupacion
				// - si la familia es una hija recorre hasta encontrar la "madre" original
				// - aplica la siguiente transformacion
				//   - si familia == familiaLinea o familiaBandaAncha -> tipo agrupacion = tipoAgrupacionLinea
				//   - si familia == familiaTV -> tipo agrupacion = tipoAgrupacionTV
				if (psPeticionPrimerPs == null)
				{
					psPeticionPrimerPs = psPeticion ;
            
					Producto_servicioLocal ps = psPeticion.getProducto_servicio () ;
            
					// busca la familia "madre" (STB, BA o TV)
            
					Familia_producto_servicioLocal familiaPs = ps.getFamilia_producto_servicio () ;
            
					faps_id_padre = familiaPs.getFaps_id_padre ();
					while (faps_id_padre != null && faps_id_padre.longValue () != 0)
					{
						Familia_producto_servicioKey key = new Familia_producto_servicioKey (faps_id_padre) ;
                
						familiaPs = familiaPsLocalHome.findByPrimaryKey (key) ;
						faps_id_padre = familiaPs.getFaps_id_padre ();
					}
            
					//
            
					long idFamilia = ((Familia_producto_servicioKey) familiaPs.getPrimaryKey ()).faps_id.longValue () ;
            
					if (idFamilia==familiaPcBANaked || idFamilia ==familiaPcPsBANaked ||idFamilia == familiaLinea || idFamilia == familiaBandaAncha || idFamilia==familiaPcLinea || idFamilia == familiaIP || idFamilia==familiaPcBA || idFamilia == familiaAsistencia)
					{
						tipoAgrupacion = tipoAgrupacionLinea ;
					}
					else if (idFamilia==familiaPcTV || idFamilia==familiaDecoTV ||idFamilia==familiaDecoPVRTV|| idFamilia==familiaTematicoTV || idFamilia==familiaPaqueteTV || idFamilia==familiaTV)
					{
						tipoAgrupacion = tipoAgrupacionTV ;
					}
					else if(idFamilia==familiaIC)
					{
						tipoAgrupacion = tipoAgrupacionIC;
					}else if (idFamilia==familiaIntEquipado){
						
						tipoAgrupacion = tipoAgrupacionIT;
					}else if(idFamilia==familiaMantenimiento){
						
						tipoAgrupacion = tipoAgrupacionMNT;
					}else if (idFamilia==familiaPS_SVA)
	                {
	                	if(agrupacion.getNum_ide_nu ().startsWith("TV"))
	                	{
	                		log.debug("Agrupación TV SVAs de la subpetición es: "+tipoAgrupacionTV+" y tiene familia : "+idFamilia);
	                		tipoAgrupacion= tipoAgrupacionTV;
	                	}else{
	                		log.debug("Agrupación LB SVAs de la subpetición es: "+tipoAgrupacionLinea+" y tiene familia : "+idFamilia);                      	
	                	tipoAgrupacion= tipoAgrupacionLinea;
	                	}
//	                	REQ BA NAKED adicion familia PC naked
	                }else if(idFamilia==familiaBandaAnchaNaked 
	                		|| idFamilia==familiaPcBANaked){
	                	tipoAgrupacion= tipoAgrupacionBA;
	                }
				    //Movistar Play se agrega Num_ide_num_mp el valor del idpc
				    else if(idFamilia==familiaPS_GVP || idFamilia == familiaPC_GVP)
				    	tipoAgrupacion= tipoAgrupacionPS_GVP;

				}
			}
    
			// algunos chequeos simples
    
			if (tipoAgrupacion == tipoAgrupacionNoDeterminado)
				throw new ATiempoAppEx ("Cierre Primario TV Peticion Atis[" + atisRequestNumber + "]. No se puede determinar tipo de agrupacion (Linea o TV)") ;
    
			// en caso de TV se retorna el "TV" + num_ide_nu
			if (tipoAgrupacion == tipoAgrupacionTV)
			{
				String numIdeNu = agrupacion.getNum_ide_nu () ;
        
				if (numIdeNu == null)
				 {
					numIdeNu="TV"+"_"+atisRequestKey.cod_pet_cd+"_"+agrupacion_atisKey.cod_agr_sub_nu;
				 }
				 else if(numIdeNu.equals(""))
				 {
					numIdeNu="TV"+"_"+atisRequestKey.cod_pet_cd+"_"+agrupacion_atisKey.cod_agr_sub_nu;
				 }
        
				group1.setComercialProductIdNumber (numIdeNu) ;
			}
    
			// en caso de linea se retorna el numero de telefono nuevo (si es distinto de null) o el actual si no
			// cuidado en la bd: "anterior" es equivalente a "actual" y "asignado" es equivalente a "nuevo"
			else if (tipoAgrupacion == tipoAgrupacionLinea)
			{
				// busca el recurso de linea basica
       
				PeticionLocal peticionVPI = psPeticionPrimerPs.getFk_psp_pet () ;
    
				Collection colRecursosLineaBasica = peticionVPI.getRecursos_linea_basica () ;

				if (colRecursosLineaBasica.size () == 0)
				{
					String esMuv=peticionVPI.getComercialProductIdentification();
					log.debug(esMuv);
					if(esMuv!=null && !esMuv.equals(VpistbbaConfig.getVariable("MM")))
					{
						//Si no hay recursos de linea basica se envia el numero original de la peticion
						//Ejemplo: Cambio Producto que no es necesario realizar ningun flujo y se va directo al Cierre
						group1.setComercialProductIdNumber (agrupacion.getNum_ide_nu()) ;
					}
				}

				else if (colRecursosLineaBasica.size () > 1)
					throw new ATiempoAppEx ("Cierre Primario TV Peticion Atis[" + atisRequestNumber + "]. Hay mas de un registro en recursos_linea_basica") ;
		
				//Si no hay Recursos, no se setea el setPulseQuantityTo y por lo tanto se envia en 0.
				if(colRecursosLineaBasica.size()==1)
				{
					Recursos_linea_basicaLocal rlb = (Recursos_linea_basicaLocal) colRecursosLineaBasica.iterator ().next () ;

					//Ver el caso especial de Traslado.
					boolean esTraslado=esAgrupacionDeTraslado(agrupacion);
					if(esTraslado)
					{
						log.info("Procesando Cierre Primario de Traslado");
						ArrayList tiposOpCom=retornaTiposOperAgrupacion(agrupacion);
						if(tiposOpCom.contains("ACP") || tiposOpCom.contains("A"))
						{
							//Agrupacion de Alta
							if (rlb.getTelefono_asg () != null)
							{
								group1.setComercialProductIdNumber (rlb.getTelefono_asg ().toString ()) ;
							}
							else
								group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
							if(rlb.getCont_linea_nueva()!=null)
								group1.setPulseQuantityTo(rlb.getCont_linea_nueva().longValue());
						}
						else
						{
							if (rlb.getTelefono_ant () != null)
								group1.setComercialProductIdNumber (rlb.getTelefono_ant ().toString ()) ;
							else
								group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
							if(rlb.getCont_linea_act()!=null)
								group1.setPulseQuantityFrom(rlb.getCont_linea_act().longValue());
						}
					}
					else
					{
						log.info("Procesando Cierre Primario de NO Traslado");
						if (rlb.getTelefono_asg () != null)
						{
							group1.setComercialProductIdNumber (rlb.getTelefono_asg ().toString ()) ;
						}
						else if (rlb.getTelefono_ant () != null)
						{
							group1.setComercialProductIdNumber (rlb.getTelefono_ant ().toString ()) ;
						}
						else
							log.info("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Registro en recursos_linea_basica no tiene ni el telefono asignado (o nuevo) o el telefono anterior (o actual), se enviara al cierre sin el numero telefono") ;
						if(rlb.getCont_linea_act()!=null)
							group1.setPulseQuantityFrom(rlb.getCont_linea_act().longValue());
						if(rlb.getCont_linea_nueva()!=null)
							group1.setPulseQuantityTo(rlb.getCont_linea_nueva().longValue());
					}
					// retorna el telefono nuevo si existe si no el actual
					if(group1.getComercialProductIdNumber()==null)
						group1.setComercialProductIdNumber("");	
				}
				else
				{
					group1.setComercialProductIdNumber (peticionVPI.getNum_ide_nu_stb()) ;
					group1.setPulseQuantityFrom(0);
					group1.setPulseQuantityTo(0);
				}	
			}
			else if (tipoAgrupacion==tipoAgrupacionIC)
			{
				for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();)
				{
					Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
					Operacion_comercialLocal local=operacion_comercialHome.findByPrimaryKey(new Operacion_comercialKey(subpeticion_atisLocal.getCod_opr_cmr_cd()));
					if(local.getOpco_tipo()!=null && local.getOpco_tipo().equals("A"))
					{
						Iterator iterCarac=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();
						while (iterCarac.hasNext())
						{
							Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
							Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
							Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
							if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue())
							{
								log.debug("Informacion : Se obtuvo Father Email " + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								group1.setComercialProductIdNumber(subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								break;
							}
						}
					}
					else
						group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
				}
			}
			else if (tipoAgrupacion==tipoAgrupacionIT){
            	log.debug("Entro a procesar el cierre primario para venta de equipos");
				String numIdeNu = agrupacion.getNum_ide_nu () ;
				if ((numIdeNu != null) && (!numIdeNu.equals(""))){
	            	log.debug("Es una peticion postventa de Venta de Equipos "+ numIdeNu);
					group1.setComercialProductIdNumber(numIdeNu);
				}else{
	            	for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();){
	            		Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
	            		Subpeticion_atisKey subpeticionAtisKey = (Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey();
	        			
	            		log.debug("Recorre las subpeticion:"+subpeticionAtisKey.cod_sub_cd+","+subpeticionAtisKey.fk_agru_sub_cod_agr_sub_nu+","+subpeticionAtisKey.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
	            		
	            		Agrupacion_atisLocal agrupacionAtisLocal = subpeticion_atisLocal.getFk_agru_sub();
	            		Peticion_atisLocal peticionAtisLocal = agrupacionAtisLocal.getFk_pet_agrupacion();
	            		Collection peticiones = peticionAtisLocal.getPeticion();
	            		            		
	            		for(Iterator iteratorPeticiones=peticiones.iterator();iteratorPeticiones.hasNext();){
	            			PeticionLocal peticionLocal = (PeticionLocal) iteratorPeticiones.next();
	            			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
	            			log.debug("Recorre la peticion: "+peticionKey.peti_numero);
	            			
	            			Collection elementos =peticionLocal.getElemento_peticion();
	            			if(elementos.isEmpty()){
	            				group1.setComercialProductIdNumber(peticionLocal.getIdentificadorOriLinea());
	            			}
	            			for(Iterator iteratorElementos=elementos.iterator();iteratorElementos.hasNext();){
	                			Elemento_PeticionLocal elementoPeticionLocal=(Elemento_PeticionLocal) iteratorElementos.next();
	                			log.debug("Los valores a comparar son elementoPeticion.getPs_id: "+elementoPeticionLocal.getPs_id().longValue()
	                					+" con  subpeticion_atis.getCod_pro_ser_cd:" + subpeticion_atisLocal.getCod_pro_ser_cd().longValue());
	                			
	                            Producto_servicioLocalHome psHome = null;
	                            try {
	                                psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
	                            } catch (NamingException e) {
	                                log.error("NamingException. ProductoServicioLocalHome", e);
	                            }
	                            Producto_servicioLocal psLocal = null;
	                            Producto_servicioLocal psLocalElemento = null;
	                            try {
	                                psLocal = psHome.findByPrimaryKey(new Producto_servicioKey(subpeticion_atisLocal.getCod_pro_ser_cd()));
	                                psLocalElemento = psHome.findByPrimaryKey(new Producto_servicioKey(elementoPeticionLocal.getPs_id()));
	                            } catch (FinderException e) {
	                                log.error("FinderException. PS No encontrado. [" + subpeticion_atisLocal.getCod_pro_ser_cd() + "]");
	                            } catch (Exception e) {
	                                log.error("Exception. PS No encontrado. [" + subpeticion_atisLocal.getCod_pro_ser_cd() + "]:" + e.getMessage());
	                            }
	                			Long famPsIdLocal = ((Familia_producto_servicioKey)(((Familia_producto_servicioLocal) psLocal.getFamilia_producto_servicio()).getPrimaryKey())).faps_id;
	                			Long famPsIdLocalElemento = ((Familia_producto_servicioKey)(((Familia_producto_servicioLocal) psLocalElemento.getFamilia_producto_servicio()).getPrimaryKey())).faps_id;
	                			 
	                			if (famPsIdLocal.longValue() == famPsIdLocalElemento.longValue()){
	                				log.debug("El elemento se encontró y se procede a asignarlo en la operación comercial");
	                				group1.setComercialProductIdNumber(elementoPeticionLocal.getSerial());
	            				}else{
	            					log.debug("El elemento no conincide y se procede a análizar el siguiente");
	            				}
	                		}
	           			}            		
	        		}
				}
			}else if (tipoAgrupacion == tipoAgrupacionPS_GVP){
	        	
			 	Collection peticiones = peticionAtis.getPeticion();

				for (Iterator iteratorPeticiones = peticiones.iterator(); iteratorPeticiones
						.hasNext();) {
					PeticionLocal peticionLocal = (PeticionLocal) iteratorPeticiones
							.next();
					String idPc = peticionLocal.getNum_ide_nu_mp();
					if (idPc != null && !idPc.equals("")) {
						group1.setComercialProductIdNumber(idPc);
						break;
					}
				}
			}else
				throw new ATiempoAppEx ("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Tipo de agrupacion desconocido: no se sabe que enviar como numero de producto comercial") ;
    
			 /*mfmendez - no se envia campo comercial-product-identification-number*/
            if(group1.getComercialProductIdNumber() == null)
            	group1.setComercialProductIdNumber("");
            
			groups.add (group1) ;
		}
//				envia el mensaje. Leo: saque el envio del iterator de agrupacion. debe enviar un puro mensaje.
		log.info("Cierre Primario Peticion Atis[" + atisRequestNumber + "]. Envio Mensaje de Cierre Primario.") ;
		new CierrePeticionATISMQ ().enviarMensaje (tr001e) ;
		//TODO: no debo marcarlo como cerrada
		for(Iterator iterator=peticionAtis.getPeticion().iterator();iterator.hasNext();)
		{
			PeticionLocal local=(PeticionLocal) iterator.next();
//				local.setEspe_id(new Integer(ComunInterfaces.estadoPeticionTerminada));
			local.setEspe_id(new Integer(ComunInterfaces.estadoPeticionEnCurso));
			Integer estPet = new Integer(ComunInterfaces.estadoPeticionEnCurso);
			log.debug("seteo estado peticion:" + estPet);
		}
	}
    
//	CR 10394 -Pablo Cawen- Fin - 13/05/2008
	
	private boolean tipoAgrupacionSoloBA(Agrupacion_atisLocal local) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			String opco_tipo = null;
			for(Iterator iterator=local.getSubpeticion_atis().iterator();iterator.hasNext();)
			{
				Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
				Operacion_comercialLocal operacion_comercialLocal=operacion_comercialHome.findByPrimaryKey(new Operacion_comercialKey(subpeticion_atisLocal.getCod_opr_cmr_cd()));
				opco_tipo = operacion_comercialLocal.getOpco_tipo();
				if(opco_tipo!=null)
				{
					if(opco_tipo.equals("A") || opco_tipo.equals("ACP"))
						return true;
				}
			}
			return false;
		}
		catch(FinderException fe)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,fe);
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);

	}
	}

	private boolean esAgrupacionSoloBA(Agrupacion_atisLocal local) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			for(Iterator iterator=local.getSubpeticion_atis().iterator();iterator.hasNext();)
			{
				Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
				Producto_servicioKey producto_servicioKey=new Producto_servicioKey(subpeticion_atisLocal.getCod_pro_ser_cd());
				Producto_servicioLocal ps=producto_servicioLocalHome.findByPrimaryKey(producto_servicioKey);
				Familia_producto_servicioKey key=(Familia_producto_servicioKey) ps.getFamilia_producto_servicio().getPrimaryKey();
//				REQ BA NAKED adicion familia PC naked
				if(key.faps_id.intValue()!=familiaPcBA && key.faps_id.intValue()!=familiaBandaAncha && key.faps_id.intValue()!=familiaBandaAnchaNaked && key.faps_id.intValue()!=familiaPcBANaked)
					return false;
			}
			return true;
		}
		catch(FinderException fe)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,fe);
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);

	}
	}

	/*
         * @author francois
         */
    private boolean esAgrupacionDeTraslado(Agrupacion_atisLocal agrupacion)
    {
		for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();)
		{
			Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) subpeticion_atisLocal.getProducto_servicio_peticion().iterator().next();
			Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
			if(operacion_comercialLocal.getOpco_tras()!=null)
			{
				if(operacion_comercialLocal.getOpco_tras().equals("T"))
					return true;
			}
		}
		return false;
    }
	private ArrayList retornaTiposOperAgrupacion(Agrupacion_atisLocal agrupacion)
	{
		ArrayList retorno=new ArrayList();
		for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();)
		{
			Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) subpeticion_atisLocal.getProducto_servicio_peticion().iterator().next(); 
			Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
			if(operacion_comercialLocal.getOpco_tras()!=null)
			{
				if(operacion_comercialLocal.getOpco_tras().equals("T"))
				{
					if(operacion_comercialLocal.getOpco_tipo()!=null)
						retorno.add(operacion_comercialLocal.getOpco_tipo());
				}
			}
		}
		return retorno;
	}

	private void llenaSubRequest1 (SubRequest1 subRequest1, Producto_servicio_peticionLocal psPeticion )
	throws ATiempoAppEx, FinderException, NamingException
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
		Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
		
		Collection colEstadoPsPeticion = psPeticion.getEstado_ps_peticion () ;
        
//		if (colEstadoPsPeticion.size () > 1)//aunque tenga mas de uno siempre voy a sacar el primero.
//		{
//			Producto_servicio_peticionKey key = (Producto_servicio_peticionKey) psPeticion.getPrimaryKey () ;
//            
//			String llave = "" + key.fk_psp_pet_peti_numero.longValue () + " - " + key.correlativo ;
//            
//			throw new ATiempoAppEx ("producto_servicio_peticion tiene mas de 1 estado: " + llave)  ;
//		}
        
		String errorType = null;
        
		log.info("EstadoPsPeticionSize:"+colEstadoPsPeticion.size());
        
		if (colEstadoPsPeticion.size () == 0)
		{
			errorType = OK ;
        
		}
        
		else
		{
			Estado_ps_peticionLocal estadoPsPeticion =  (Estado_ps_peticionLocal) (colEstadoPsPeticion.iterator ().next ()) ;
            
			Integer codEstadoCierre = estadoPsPeticion.getCod_estado_cierre () ;
            
			if (codEstadoCierre == null || codEstadoCierre.intValue () == estadoCierreOk)
			{
				errorType = OK ;
                
				Long codigoCausal = estadoPsPeticion.getCod_causal () ;
                
				if (codigoCausal != null)
				{
					subRequest1.setSubRequestCodeError(estadoPsPeticion.getCod_causal ().toString ()) ;
    
					Catalogo_causalKey llaveCausa = new Catalogo_causalKey () ;
    
					llaveCausa.cod_causal = codigoCausal ;
    
					Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByPrimaryKey (llaveCausa) ;
    
					subRequest1.setSubRequestDescriptionError (catalogCausa.getDescripcion_causal ()) ;
				}
				else
				{
					subRequest1.setSubRequestCodeError ("") ;
					subRequest1.setSubRequestDescriptionError ("") ;
				}
			}
            
			else if (codEstadoCierre.intValue () == estadoCierreNovedad)
			{
				errorType = OK_NOVEDAD ;
                
				Long codigoCausal = estadoPsPeticion.getCod_causal () ;
                
				if (codigoCausal != null)
				{
					subRequest1.setSubRequestCodeError (estadoPsPeticion.getCod_causal ().toString ()) ;
    
					Catalogo_causalKey llaveCausa = new Catalogo_causalKey () ;
    
					llaveCausa.cod_causal = codigoCausal ;
    
					Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByPrimaryKey (llaveCausa) ;
    
					subRequest1.setSubRequestDescriptionError (catalogCausa.getDescripcion_causal ()) ;
				}
				else
				{
					subRequest1.setSubRequestCodeError ("") ;
					subRequest1.setSubRequestDescriptionError ("") ;
				}
			}
            
			else if (codEstadoCierre.intValue () == estadoCierreError)
			{
				errorType = NO_OK ;
                
				Long codigoCausal = estadoPsPeticion.getCod_causal () ;
                
				if (codigoCausal != null)
				{
					subRequest1.setSubRequestCodeError (estadoPsPeticion.getCod_causal ().toString ()) ;
                    
					Catalogo_causalKey llaveCausa = new Catalogo_causalKey () ;
                    
					llaveCausa.cod_causal = codigoCausal ;
                    
					Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByPrimaryKey (llaveCausa) ;
                    
					subRequest1.setSubRequestDescriptionError (catalogCausa.getDescripcion_causal ()) ;
				}
				else
				{
					subRequest1.setSubRequestCodeError ("") ;
					subRequest1.setSubRequestDescriptionError ("") ;
				}
			}
            
			else
			{
				errorType = OK ;
			}
		}
        
		subRequest1.setSubRequestTypeError (errorType) ;
        
        
		if (OK.equals (errorType))
		{
		   subRequest1.setSubRequestCodeError ("") ;
		   subRequest1.setSubRequestDescriptionError ("") ;
		}
        
		Timestamp ts = psPeticion.getPspe_fecha_fin () ;
        
		if (ts == null){
			ts = psPeticion.getFk_psp_pet ().getPeti_fecha_termino () ;
			psPeticion.setPspe_fecha_fin (ts) ;
		}
        
		subRequest1.setSubRequestEndTime(new Fecha().getTimestamp()) ;
	}


	//LCA
    public PeticionDTO obtenerPeticionVPI (Long nroPeticionVPI) throws ATiempoAppEx
    {
        try
        {
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
        	PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
        	SubsegmentoLocalHome subSegmentoHome=(SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
        	CanalLocalHome canalHome = (CanalLocalHome) HomeFactory.getHome(CanalLocalHome.JNDI_NAME);
        	Traslado_solobaLocalHome traslado_solobaHome=(Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
        	
        	boolean tieneLinea=false;
        	boolean tieneBa=false;
        	boolean tieneTV=false;
        	boolean tieneIC=false;
        	boolean tieneIT=false;
        	boolean tieneMNT=false;
        	boolean tieneAsistencia=false;
        	boolean tieneVisAsist=false;
        	boolean tieneAsistRemota=false;
        	boolean tienePresenciaDigital=false;
        	//@idrincon - familiaip
        	boolean tieneFamIP = false;
        	//fin
            PeticionDTO dto=new PeticionDTO ();
            PeticionLocal local=peticionHome.findByPrimaryKey (new PeticionKey (nroPeticionVPI));
            Peticion_atisLocal peticion_atisLocal=local.getFk_01_pet_atis();
            Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticion_atisLocal.getPrimaryKey();
            dto.setCodPetCd(peticion_atisKey.cod_pet_cd);
            
            boolean banderaTipoUso=true;
			boolean banderaPBX=false;
			Long sbt_pro_cmr_cd = null;
			String num_ide_nu = null;
			String fath_iden_line = null;
			Long sbt_pro_cmr_cd2 = null;
			String obsSubpeticion = "";
            for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
            {
            	Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();            	
            	Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
            	Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
            	if(subpeticion_atisLocal.getObs_sub_ds() != null){
            		obsSubpeticion = subpeticion_atisLocal.getObs_sub_ds();
            		if(obsSubpeticion.indexOf("|") > -1){
            			obsSubpeticion = obsSubpeticion.substring(obsSubpeticion.indexOf("|")+1);
            		}
            	}
            	
            	if(agrupacion_atisLocal.getTip_pro_cmr_cd().intValue()==tipoPBX)
            	{
            		dto.setTienePBX(true);
            		sbt_pro_cmr_cd = agrupacion_atisLocal.getSbt_pro_cmr_cd();
					if(sbt_pro_cmr_cd!=null)
            		{
            			if(sbt_pro_cmr_cd.intValue()==subTipoPbxPilotoNR || sbt_pro_cmr_cd.intValue()==subTipoPbxPilotoAutoConsumo)
            			{
            				//Aqui tengo el Piloto Padre
            				log.info("Estoy en piloto Padre");
            				dto.setPiloto(true);
            				dto.setCantidadTroncales(agrupacion_atisLocal.getCan_agr_hij_nu());
            				num_ide_nu = agrupacion_atisLocal.getNum_ide_nu();
							if(num_ide_nu!=null && !num_ide_nu.equals(""))
            					dto.setNroPiloto(num_ide_nu);
            				else
            					dto.setNroPiloto(local.getNum_ide_nu_stb());
							if(!banderaTipoUso)
								break;
            			}
            			else
            			{
							log.info("Estoy en TRONCAL");
            				dto.setTroncal(true);
							dto.setCantidadTroncales(agrupacion_atisLocal.getCan_agr_hij_nu());
							Integer codigoAgrupacionPadre=agrupacion_atisLocal.getAgr_sub_pdr_cd();
							fath_iden_line = agrupacion_atisLocal.getFath_iden_line();
							if(fath_iden_line!=null && !fath_iden_line.equals(""))
							{
								dto.setNroPiloto(fath_iden_line);
							}
							else
							{
								Peticion_atisLocal padre=local.getFk_01_pet_atis();
								for(Iterator iterator2=padre.getPeticion().iterator();iterator2.hasNext();)
								{
									PeticionLocal peticionLocal=(PeticionLocal) iterator2.next();
									PeticionKey key=(PeticionKey) peticionLocal.getPrimaryKey();
									if(key.peti_numero.longValue()==nroPeticionVPI.longValue())
										continue;
									Agrupacion_atisLocal agrupacionHermana=null;
									for(Iterator iterator3=peticionLocal.getProducto_servicio_peticion().iterator();iterator3.hasNext();)
									{
										Producto_servicio_peticionLocal pp=(Producto_servicio_peticionLocal) iterator3.next();
										 agrupacionHermana=pp.getFk_01_subp_atis().getFk_agru_sub();
										break;			
									}
									if(agrupacionHermana==null)
										continue;
									if(agrupacionHermana.getTip_pro_cmr_cd().intValue()!=tipoPBX)
										continue;
									sbt_pro_cmr_cd2 = agrupacionHermana.getSbt_pro_cmr_cd();
									if(sbt_pro_cmr_cd2==null)
										continue;
									if(sbt_pro_cmr_cd2.intValue()!=subTipoPbxPilotoNR && sbt_pro_cmr_cd2.intValue()!=subTipoPbxPilotoAutoConsumo)
										continue;
//									Si llego aqui quiere decir que toy mirando la agrupacion hermana que es piloto
									dto.setNroPiloto(peticionLocal.getNum_ide_nu_stb());
								}
							}
            			}
            		}
            	}
            	else if(agrupacion_atisLocal.getTip_pro_cmr_cd().intValue()==tipoIC)
            	{
            		for(Iterator iterator2=agrupacion_atisLocal.getSubpeticion_atis().iterator();iterator2.hasNext();)
            		{
            			Subpeticion_atisLocal subpeticion_atisLocal2=(Subpeticion_atisLocal) iterator2.next();
            			for(Iterator iterator3=subpeticion_atisLocal2.getSubpeticion_caracteristicas().iterator();iterator3.hasNext();)
            			{
            				Subpeticion_caracteristicasLocal cc=(Subpeticion_caracteristicasLocal) iterator3.next();
            				Subpeticion_caracteristicasKey key=(Subpeticion_caracteristicasKey) cc.getPrimaryKey();
            				if(key.cod_crc_cd.longValue()==new Long(VpistbbaConfig.getVariable("USUACC")).longValue())
            				{
            					dto.setUsuarioAcceso(cc.getVal_ral_crc_cd());
            					break;	
            				}
            			}
            			if(dto.getUsuarioAcceso()!=null)
            				break;
            		}
            	}
            	Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) agrupacion_atisLocal.getPrimaryKey();
				dto.agregarAgrupacion(agrupacion_atisKey.cod_agr_sub_nu);
				Integer tipo=primerTipoAgrupacion(agrupacion_atisLocal);
				if( (tipo.equals(tipoAgrupacionLinea) || tipo.equals(tipoAgrupacionBA) ) && banderaTipoUso)			
				{
					dto.setCodTipUsoCdAgrupacionLinea(agrupacion_atisLocal.getCod_tip_uso_cd());
					dto.setNomTipUsoNoAgrupacionLinea(agrupacion_atisLocal.getNom_tip_uso_no());
					banderaTipoUso=false;
				}
            }
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Familia_producto_servicioKey famKey=(Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				Long famId=famKey.faps_id;
				if(famId.intValue()==familiaIntEquipado)
				{
					if(dto.getIdOriginalPCLinea()==null)
						dto.setIdOriginalPCLinea(producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu());
					tieneIT=true;
				}
				if(famId.intValue()==familiaLinea || famId.intValue()==familiaPcLinea || famId.intValue() == familiaIP)
				{
					if(dto.getIdOriginalPCLinea()==null)
						dto.setIdOriginalPCLinea(producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu());
					tieneLinea=true;
					//idrincon req familiaip
					if(famId.intValue()==familiaIP){
						tieneFamIP=true;
					}
					if(famId.intValue() == familiaPcLinea){
						//CR-7390 - Yumbleiner - Linea Precableada
						if (dto.getCodigoProyecto() == null)
							dto.setCodigoProyecto(obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (VpistbbaConfig.getVariable("CODPROJECT"))));
					}
					//CR-7691 - Yumbleiner - Citofonia Virtual
					if (dto.getNumPadre() == null )
					dto.setNumPadre(obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (VpistbbaConfig.getVariable("NUMPADRE"))));
					if (dto.getNumExtension() == null)
					dto.setNumExtension(obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (VpistbbaConfig.getVariable("NUMEXTENSION"))));
				}
				/**
                 * DAVID: se adiciona el siguiente if para RQ 28274, cobro incidencias.
                 */
				else if(famId.intValue() == familiaPresenciaDigital){
					tienePresenciaDigital = true;
					dto.setFlagPublicidad(true);
				}
	        	else if(famId.intValue()==familiaAsistencia)
	               {  
	        	 	tieneAsistencia=true;
	               }
	        	else if(famId.intValue()==familiaVisitaAsist)
	               {  
	        	 	tieneVisAsist=true;
	               }
	        	else if(famId.intValue()==familiaAsistRemota)
	               {  
	        	 	tieneAsistRemota=true;
	               }
                else if(famId.intValue()==familiaMantenimiento)
                {  
        			if(producto_servicio_peticionLocal.getPsId().longValue()!=psMNTTV)
        				dto.setIdOriginalPCLinea(producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu());
                    tieneMNT=true;
                }
                //REQ BA NAKED adicion familia PC PS Naked
				else if(famId.intValue()==familiaBandaAncha || famId.intValue()==familiaPcBA)
				{
					if(dto.getIdOriginalPCLinea()==null)
						dto.setIdOriginalPCLinea(producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu());
					tieneBa=true;
				}else if ( famId.intValue()==familiaPcBANaked ||famId.intValue()==familiaPcPsBANaked || famId.intValue()==familiaBandaAnchaNaked){
					if(dto.getIdOriginalPCLinea()==null)
						dto.setIdOriginalPCLinea(local.getNum_ide_nu_stb());
					tieneBa=true;
				}
				else if(famId.intValue()==familiaTV || famId.intValue()==familiaPcTV || famId.intValue()==familiaDecoTV||famId.intValue()==familiaDecoPVRTV || famId.intValue()==familiaTematicoTV || famId.intValue()==familiaPaqueteTV ||famId.intValue()==familiaDecoHDTV)
				{
					tieneTV=true;
					//Parche para mostrar el Deco del Reseteo Control Parental
					if(producto_servicio_peticionLocal.getPsId().longValue()==psReseteoCP)
					{
						Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
						for(Iterator iterator2=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();iterator2.hasNext();)
						{
							Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterator2.next();
							Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
							Long casId=new Long (VpistbbaConfig.getVariable("CASIDTELEV"));
							if (spk.cod_crc_cd.longValue()== casId.longValue())
							{
								log.debug("Informacion : Se obtuvo CASIDTELEV:" + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								String idDecoCP = subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
								dto.setIdDecoCP(idDecoCP);
								break;
							}
						}
					}
				}
				else if(famId.intValue()==familiaIC)
					tieneIC=true;
			}
            
            dto.setFamiliasEnPeticion(logicaFamiBI(tieneLinea,tieneBa,tieneTV,tieneIC, tieneIT, tieneMNT, tieneAsistencia,tieneVisAsist,tieneAsistRemota,tieneFamIP));
            dto.setPetiNumero (nroPeticionVPI);
            dto.setAmbiId (local.getAmbi_id ());
            Integer espeId = local.getEspe_id();
			dto.setEspeId ( espeId );
			if ( espeId==null )
			{
				dto.setDscEstado("En Proceso");
				dto.setEspeId( new Integer(estadoPeticionEnCurso) );
			} 
			else if ( espeId.intValue()==estadoPeticionEnCurso ) 
				dto.setDscEstado("En Proceso"); 
			else if ( espeId.intValue()==estadoPeticionCancelada) 
				dto.setDscEstado("Cancelada"); 
			else if ( espeId.intValue()==estadoPeticionTerminada) 
				dto.setDscEstado("Terminada"); 
			
            dto.setTicaId (local.getTica_id ());
            dto.setAgenId (local.getAgen_id ());
            dto.setLineTrasId (local.getLine_tras_id ());
            dto.setCodCliCd (local.getCod_cli_cd ());
            dto.setCodCtaCd (local.getCod_cta_cd());
            dto.setPetiFechaIngreso (local.getPeti_fecha_ingreso ());
            dto.setPetiFechaCompromiso (local.getPeti_fecha_compromiso ());
            dto.setPetiFechaModificacion (local.getPeti_fecha_modificacion ());
            dto.setPetiObservacion (local.getPeti_observacion ());
            dto.setPetiUsuarioEmisor (local.getPeti_usuario_emisor ());
            dto.setPetiFechaTermino (local.getPeti_fecha_termino ());
            dto.setPetiIdInstancia (local.getPeti_id_instancia ());
            dto.setPetiRutVendedor (local.getPeti_rut_vendedor ());
            dto.setPetiCausalBaja (local.getPeti_causal_baja ());
            dto.setPetiTipoHora (local.getPeti_tipo_hora ());
            dto.setPetiHoraInicio (local.getPeti_hora_inicio ());
            dto.setPetiHoraFin (local.getPeti_hora_fin ());
            dto.setPetiNumeroNueva (local.getPeti_numero_nueva ());
            dto.setPetiOss (local.getPeti_ooss ());
            dto.setPetiRegistroAlta (local.getPeti_registro_alta ());
            dto.setPetiAvisoAlta (local.getPeti_aviso_alta ());
            dto.setPetiFechaModificacion (local.getPeti_fecha_modificacion ());
            dto.setPetiTipo (local.getPeti_tipo ());
            dto.setCodEmpCd (local.getCod_emp_cd ());
            dto.setNomDs (local.getNom_ds ());
            dto.setPriApeDs (local.getPri_ape_ds ());
            dto.setSegApeDs (local.getSeg_ape_ds ());
            dto.setCodCnlVenCd (local.getCod_cnl_ven_cd ());
            dto.setCodFzaVenCd (local.getCod_fza_ven_cd ());
            dto.setNomIntDs (local.getNom_int_ds ());
            dto.setPriApeIntDs (local.getPri_ape_int_ds ());
            dto.setSegApeIntDs (local.getSeg_ape_int_ds ());
            dto.setCodPetPdrCd (local.getCod_pet_pdr_cd ());
            dto.setCodCtaCd (local.getCod_cta_cd ());
            dto.setCmbEstPetFf (local.getCmb_est_pet_ff ());
            dto.setTipCliCd (local.getTip_cli_cd ());
            
            dto.setCodSgmCtaCd (local.getCod_sgm_cta_cd ());
            dto.setCodSbgCtaCd (local.getCod_sbg_cta_cd ());
            
            dto.setTelCotDs (local.getTel_cot_ds ());
//			TODO -- Seteos nuevos tels contacto -- CR-10120
			dto.setSegTelCotDs(local.getTel_cot_ds_1());
			dto.setTerTelCotDs(local.getTel_cot_ds_2());
            dto.setNomStePetSn (local.getNom_ste_pet_sn ());
            dto.setPriApePetSn (local.getPri_ape_pet_sn ());
            dto.setSegApePetSn (local.getSeg_ape_pet_sn ());
            dto.setObsPetDs (local.getObs_pet_ds ());
            dto.setFecSctPetFf (local.getFec_sct_pet_ff ());
            dto.setTipDocCd (local.getTip_doc_cd ());
            
            dto.setNumDocCliCd (local.getNum_doc_cli_cd ());
            dto.setDigCtlDocCd (local.getDig_ctl_doc_cd ());

            //CR-23444 - PCawen - Informacion del ciclo de facturacion
            dto.setInfCicFac(local.getInf_cicl_fac());

			try
			{
				dto.setCodSgmCtaCd( local.getCod_sgm_cta_cd ());
				dto.setCodSbgCtaCd (local.getCod_sbg_cta_cd ());
				if(local.getCod_sbg_cta_cd()!=null)
				{
					SubsegmentoLocal subsegmentoLocal=subSegmentoHome.findByPrimaryKey(new SubsegmentoKey(local.getCod_sbg_cta_cd()));
					dto.setDscSubSegmento(subsegmentoLocal.getDescripcion());
					dto.setDscSegmento(subsegmentoLocal.getSegmento().getSegm_descripcion());
				}
			}
			catch (FinderException e)
			{
				//log.debug("FinderException",e);
			}
			if(local.getFk_02_departamento()!=null)
			{
				DepartamentoLocal dptLocal = local.getFk_02_departamento ();
				dto.setDscDepartamento( dptLocal.getNombre_departamento() );
				DepartamentoKey departamentoKey=(DepartamentoKey) dptLocal.getPrimaryKey ();
				dto.setCodDpt (departamentoKey.cod_dpt);
			}
			if(local.getFk_01_localidad ()!=null)
			{
				LocalidadLocal locLocal = local.getFk_01_localidad ();
				dto.setDscLocalidad( locLocal.getNombre_localidad() );
				LocalidadKey localidadKey=(LocalidadKey)locLocal.getPrimaryKey ();
				dto.setCodLoc (localidadKey.cod_loc);	
			}
            if(local.getFk_03_central ()!=null)
            {
                CentralKey centralKey=(CentralKey) local.getFk_03_central ().getPrimaryKey ();
				dto.setCodCentral(centralKey.cod_central);
				//log.debug("Cod Central Peticion:"+dto.getCodCentral());
				dto.setDescCentral(local.getFk_03_central().getDesc_central());
            }
            else
			{
				dto.setCodCentral(null);
				dto.setDescCentral("");
				//log.debug("No Hay Central Asociada a la Peticion");
			}
            
            dto.setDirTipVia1 (local.getDir_tip_via_1 ());
            dto.setDirNroVia1 (local.getDir_nro_via_1 ());
            dto.setDirLt1Via1 (local.getDir_lt1_via_1 ());
            dto.setDirLt2Via1 (local.getDir_lt2_via_1 ());
            dto.setDirZonVia1 (local.getDir_zon_via_1 ());
            dto.setDirTipVia2 (local.getDir_tip_via_2 ());
            dto.setDirNroVia2 (local.getDir_nro_via_2 ());
            dto.setDirLt1Via2 (local.getDir_lt1_via_2 ());
            dto.setDirLt2Via2 (local.getDir_lt2_via_2 ());
            dto.setDirZonVia2 (local.getDir_zon_via_2 ());
            dto.setDirTipLg1 (local.getDir_tip_lg1 ());
            dto.setDirNroLg1 (local.getDir_nro_lg1 ());
            dto.setDirTipLg2 (local.getDir_tip_lg2 ());
            dto.setDirNroLg2 (local.getDir_nro_lg2 ());
            dto.setDirTipLg3 (local.getDir_tip_lg3 ());
            dto.setDirNroLg3 (local.getDir_nro_lg3 ());
            dto.setCodExtLocCd (local.getCod_ext_loc_cd ());
            dto.setCodTerCd (local.getCod_ter_cd ());
            dto.setCodAreTelCd (local.getCod_are_tel_cd ());
            dto.setAreSnTelCd (local.getAre_sn_tel_cd ());
            dto.setLocExtTelCd (local.getLoc_ext_tel_cd ());
            dto.setTipCalAtisCd (local.getTip_cal_atis_cd ());
            dto.setNomCalDs (local.getNom_cal_ds ());
            dto.setNumCalNu (local.getNum_cal_nu ());
            dto.setDscCmpPriDs (local.getDsc_cmp_pri_ds ());
            dto.setDscCmpSegDs (local.getDsc_cmp_seg_ds ());
            dto.setCodLocCd (local.getCod_loc_cd ());
            dto.setNomSloNo (local.getNom_slo_no ());
			dto.setNumIdeNumSTB(local.getNum_ide_nu_stb() );
			dto.setNumIdeNumTV(local.getNum_ide_nu_tv() );
			dto.setNumIdeNumIC(local.getNum_ide_nu_ic() );
			dto.setNumIdeNumMP(local.getNum_ide_nu_mp() );
			// CR14525 - ana santos - inicio
			
			if(canalHome==null)
				canalHome=(CanalLocalHome) HomeFactory.getHome(CanalLocalHome.JNDI_NAME);
			

			try {
				CanalLocal cnlLocal=canalHome.findByPrimaryKey (new CanalKey (local.getCod_cnl_ven_cd() ));
				dto.setDescripcionCanalVta("" + cnlLocal.getDsc_cnl_ven_cd());
			} catch (FinderException finderException2) {
				// TODO Auto-generated catch block
				log.debug("No hay descripcion para el canal. " + finderException2);
				dto.setDescripcionCanalVta("No hay descripcion para el canal");
				//finderException2.printStackTrace();
			}
			dto.setDscCanalVta( ""+local.getCod_cnl_ven_cd() );
			
			// CR14525 - ana santos - fin
			
			dto.setTipoUso( "");

			dto.setAgrupaciones(local.getAgrupacionesString(","));
			dto.setCategoriaOpCo(local.getTica_id());
			if(local.getPeti_id_instancia()!=null && local.getPeti_id_instancia().equals(BA))
			{
				if(traslado_solobaHome!=null)
					traslado_solobaHome=(Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
				try
				{
					Traslado_solobaLocal traslado_solobaLocal=traslado_solobaHome.findByPetiAlta(nroPeticionVPI);
					dto.setLineaAnteriorTrasSoloBA(traslado_solobaLocal.getLinea_anterior());
				}
				catch(Exception e)
				{
				}
			}
			dto.setObsPetDs(local.getObs_pet_ds());
			dto.setObsSubDs(obsSubpeticion);
			
			dto.setNumIdeNumPdg(local.getNum_ide_nu_pdg());
			//log.debug("Terminando obtener Peticion VPI");
            return dto;
        }
        catch (FinderException finderException)
        {
			log.debug("Stack Trace "+finderException);
            throw new ATiempoAppEx (ATiempoAppEx.FINDER,finderException);
        }
        catch(NamingException ne)
        {
			log.debug("Stack Trace "+ne);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,ne);
        }
    }
    
	private String logicaFamiBI(boolean tieneLinea, boolean tieneBa, boolean tieneTV,boolean tieneIC, boolean tieneIT, boolean tieneMNT, boolean tieneAsistencia, boolean tieneVisAsist, boolean tieneAsistRemo, boolean tieneFamIP)
	{
		
		if(tieneLinea && !tieneBa && !tieneTV && !tieneIC && !tieneIT && !tieneFamIP )//8//Primero los individuales.
			return LB;
		else if(!tieneLinea && tieneBa && !tieneTV && !tieneIC && !tieneIT && !tieneFamIP )//4
			return BA;
		else if(!tieneLinea && !tieneBa && tieneTV && !tieneIC && !tieneIT && !tieneFamIP )//2
			return TV;
		else if(tieneLinea && tieneBa && !tieneTV && !tieneIC && !tieneIT && !tieneFamIP )//12
			return LBBA;
		else if(tieneLinea && tieneBa && tieneTV && !tieneIC && !tieneIT && !tieneFamIP )//14
			return LBBATV;
		else if(tieneLinea && !tieneBa && tieneTV && !tieneIC && !tieneIT && !tieneFamIP )//10
			return LBTV;
		else if(!tieneLinea && !tieneBa && !tieneTV && tieneIC && !tieneIT && !tieneFamIP )//1
			return IC;
		else if(!tieneLinea && !tieneBa && tieneTV && tieneIC && !tieneIT && !tieneFamIP )//3
			return TVIC;
		else if(!tieneLinea && tieneBa && !tieneTV && tieneIC && !tieneIT && !tieneFamIP )//5
			return BAIC;
		else if(!tieneLinea && tieneBa && tieneTV && !tieneIC && !tieneIT && !tieneFamIP )//6
			return BATV;
		else if(!tieneLinea && tieneBa && tieneTV && tieneIC && !tieneIT && !tieneFamIP )//7
			return BATVIC;
		else if(tieneLinea && !tieneBa && !tieneTV && tieneIC && !tieneIT && !tieneFamIP )//9
			return LBIC;
		else if(tieneLinea && !tieneBa && tieneTV && tieneIC && !tieneIT && !tieneFamIP )//11
			return LBTVIC;
		else if(tieneLinea && tieneBa && !tieneTV && tieneIC && !tieneIT && !tieneFamIP )//13
			return LBBAIC;
		else if(tieneLinea && tieneBa && tieneTV && tieneIC && !tieneIT && !tieneFamIP )//15
			return LBBATVIC;
		else if(!tieneLinea && !tieneBa && !tieneTV && !tieneIC && tieneIT && !tieneFamIP )//16
			return IT;
		else if(tieneLinea && !tieneBa && !tieneTV && !tieneIC && tieneIT && !tieneFamIP )//12
			return LBIT;
		else if(tieneLinea && !tieneBa && tieneTV && !tieneIC && tieneIT && !tieneFamIP )//14
			return LBITTV;
		else if(!tieneLinea && !tieneBa && !tieneTV && tieneIC && tieneIT && !tieneFamIP )//5
			return ITIC;
		else if(!tieneLinea && !tieneBa && tieneTV && !tieneIC && tieneIT && !tieneFamIP )//6
			return ITTV;
		else if(!tieneLinea && !tieneBa && tieneTV && tieneIC && tieneIT && !tieneFamIP )//7
			return ITTVIC;
		else if(tieneLinea && !tieneBa && !tieneTV && tieneIC && tieneIT && !tieneFamIP )//13
			return LBITIC;
		else if(tieneLinea && !tieneBa && tieneTV && tieneIC && tieneIT && !tieneFamIP )//15
			return LBITTVIC;
		else if(tieneLinea && tieneBa && tieneTV && !tieneIC && tieneIT && !tieneFamIP )//15
			return LBITTVBA;
		else if(tieneLinea && tieneBa && !tieneTV && !tieneIC && tieneIT && !tieneFamIP )//12
			return LBITBA;
		else if(!tieneLinea && tieneBa && !tieneTV && tieneIC && tieneIT && !tieneFamIP )//5
			return ITICBA;
		else if(!tieneLinea && tieneBa && tieneTV && !tieneIC && tieneIT && !tieneFamIP )//6
			return ITTVBA;
		else if(!tieneLinea && tieneBa && tieneTV && tieneIC && tieneIT && !tieneFamIP )//7
			return ITTVICBA;
		else if(tieneLinea && tieneBa && !tieneTV && tieneIC && tieneIT && !tieneFamIP )//13
			return LBITICBA;
		else if(tieneLinea && tieneBa && tieneTV && tieneIC && tieneIT && !tieneFamIP )//15
			return LBITTVICBA;
		
		/*/
		 * Modificaion familia ip,
		 * 18/04/2011 inicio
		 */
		if(tieneLinea && tieneFamIP && !tieneBa && !tieneTV && !tieneIC && !tieneIT )//8//Primero los individuales.
			return IP;
		/*else if(!tieneLinea && !tieneFamIP && tieneBa && !tieneTV && !tieneIC && !tieneIT)//4
			return BA;
		else if(!tieneLinea && !tieneFamIP && !tieneBa && tieneTV && !tieneIC && !tieneIT)//2
			return TV;*/
		else if(tieneLinea && tieneFamIP && tieneBa && !tieneTV && !tieneIC && !tieneIT)//12
			return IPBA;
		else if(tieneLinea && tieneFamIP && tieneBa && tieneTV && !tieneIC && !tieneIT)//14
			return IPBATV;
		else if(tieneLinea && tieneFamIP && !tieneBa && tieneTV && !tieneIC && !tieneIT)//10
			return IPTV;
		/*else if(!tieneLinea && !tieneFamIP && !tieneBa && !tieneTV && tieneIC && !tieneIT)//1
			return IC;
		else if(!tieneLinea && !tieneFamIP && !tieneBa && tieneTV && tieneIC && !tieneIT)//3
			return TVIC;
		else if(!tieneLinea && !tieneFamIP && tieneBa && !tieneTV && tieneIC && !tieneIT)//5
			return BAIC;
		else if(!tieneLinea && !tieneFamIP && tieneBa && tieneTV && !tieneIC && !tieneIT)//6
			return BATV;
		else if(!tieneLinea && !tieneFamIP && tieneBa && tieneTV && tieneIC && !tieneIT)//7
			return BATVIC;*/
		else if(tieneLinea && tieneFamIP && !tieneBa && !tieneTV && tieneIC && !tieneIT)//9
			return IPIC;
		else if(tieneLinea && tieneFamIP && !tieneBa && tieneTV && tieneIC && !tieneIT)//11
			return IPTVIC;
		else if(tieneLinea && tieneFamIP && tieneBa && !tieneTV && tieneIC && !tieneIT)//13
			return IPBAIC;
		else if(tieneLinea && tieneFamIP && tieneBa && tieneTV && tieneIC && !tieneIT)//15
			return IPBATVIC;
		/*else if(!tieneLinea && !tieneFamIP && !tieneBa && !tieneTV && !tieneIC && tieneIT)//16
			return IT;*/
		else if(tieneLinea && tieneFamIP && !tieneBa && !tieneTV && !tieneIC && tieneIT)//12
			return IPIT;
		else if(tieneLinea && tieneFamIP && !tieneBa && tieneTV && !tieneIC && tieneIT)//14
			return IPITTV;
		/*else if(!tieneLinea && !tieneFamIP && !tieneBa && !tieneTV && tieneIC && tieneIT)//5
			return ITIC;
		else if(!tieneLinea && !tieneFamIP && !tieneBa && tieneTV && !tieneIC && tieneIT)//6
			return ITTV;
		else if(!tieneLinea && !tieneFamIP && !tieneBa && tieneTV && tieneIC && tieneIT)//7
			return ITTVIC;
		else if(tieneLinea && tieneFamIP && !tieneBa && !tieneTV && tieneIC && tieneIT)//13
			return IPITIC;
		else if(tieneLinea && tieneFamIP && !tieneBa && tieneTV && tieneIC && tieneIT)//15
			return IPITTVIC;
		else if(tieneLinea && tieneFamIP && tieneBa && tieneTV && !tieneIC && tieneIT)//15
			return IPITTVBA;
		else if(tieneLinea && tieneFamIP && tieneBa && !tieneTV && !tieneIC && tieneIT)//12
			return IPITBA;
		else if(!tieneLinea && !tieneFamIP && tieneBa && !tieneTV && tieneIC && tieneIT)//5
			return ITICBA;
		else if(!tieneLinea && !tieneFamIP && tieneBa && tieneTV && !tieneIC && tieneIT)//6
			return ITTVBA;
		else if(!tieneLinea && !tieneFamIP && tieneBa && tieneTV && tieneIC && tieneIT)//7
			return ITTVICBA;
		else if(tieneLinea && tieneFamIP && tieneBa && !tieneTV && tieneIC && tieneIT)//13
			return IPITICBA;
		else if(tieneLinea && tieneFamIP && tieneBa && tieneTV && tieneIC && tieneIT)//15
			return IPITTVICBA;
		*/
		/*
		 * fin
		 * */
		
		//else if(!tieneLinea && !tieneBa && !tieneTV && !tieneIC)//0
		//	return "NoDeter";
		/**
         * DAVID: se adiciona el siguiente if para RQ 28274, cobro incidencias.
         */
        else if(tieneMNT)
            return MNT;
		else if(tieneAsistencia){
			return Asistencia;
		}else if(tieneVisAsist){
			return visAsist;
		}else if(tieneAsistRemo){
			return asistRemo;
		}
		return "NoDeter";
	}

	public InformacionAgendamientoDTO obtenerDatosAgendamiento (Long nroPeticionVPI) throws ATiempoAppEx {
		InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();
		try {
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Tecnico_peticionLocalHome cpHome = (Tecnico_peticionLocalHome) HomeFactory.getHome (Tecnico_peticionLocalHome.JNDI_NAME);
			Tecnico_peticionLocal cpLocal = null;
			Collection c = cpHome.finderByPeticionyAp( nroPeticionVPI,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ) );
			log.info("Encontrado Asignacion Peticion [" + nroPeticionVPI + "," + c.size() + "]");
			for (Iterator it=c.iterator(); it.hasNext(); )
			{
				cpLocal = (Tecnico_peticionLocal) it.next();
				if ( cpLocal.getFecha() != null && cpLocal.getEstado()!=null && cpLocal.getEstado().intValue()==1 ) {
					infoAgenda.setFechaCompromiso(  new Date(cpLocal.getFecha().getTime()) );
					RangoLocal rgLocal = cpLocal.getRango();
					RangoKey rKey = (RangoKey) rgLocal.getPrimaryKey();
					infoAgenda.setIdRango( rKey.id_rango );
					infoAgenda.setNombreRango( rgLocal.getNombre_rango() );
					infoAgenda.setHoraDesde( rgLocal.getHora_desde() );
					infoAgenda.setHoraHasta( rgLocal.getHora_hasta() );
					
					TecnicoLocal tecLocal = cpLocal.getTecnico();
					TecnicoKey tecKey = (TecnicoKey) tecLocal.getPrimaryKey();
					String nombreTecnico = tecLocal.getNombre() + " " + tecLocal.getApellido();
					infoAgenda.setIdTecnicoInicial( tecKey.cod_tecnico );
					infoAgenda.setNombreTecnicoInicial( nombreTecnico );
					infoAgenda.setDescripcionEstado( "Activo");
					
					// CR21938 - ana santos - inicio
					infoAgenda.setUsuario( cpLocal.getNom_usua_logueado());
					infoAgenda.setFechaAsignacion( cpLocal.getFecha_agendamiento());
					// CR21938 - ana santos - fin

				}
			}
		}
		catch (FinderException e) {
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		return infoAgenda;

    }

	public ArrayList buscarPorCliente(Long idPeticionAtis, String rutCli, String rutDv) throws ATiempoAppEx {
		ArrayList listaPeticiones = new ArrayList();
		
		try {
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Peticion_atisLocalHome peticionATISHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Collection c = null;
			
			if ( idPeticionAtis != null ) {
				Peticion_atisLocal pAtisLocal = peticionATISHome.findByPrimaryKey( new Peticion_atisKey(idPeticionAtis) );
				c = pAtisLocal.getPeticion();
			} else { 
				c = peticionHome.findByCliente(rutCli, rutDv);
			}

			if ( c != null ) {
				// Parche para controlar caida del servidor en la superacion maxima de elementos - 2009/05/07 - German P
				int cantElementMax = 100;
				int cantElements = 0;
				for (Iterator it=c.iterator(); it.hasNext() && cantElements < cantElementMax; ) {
					PeticionLocal pLocal = (PeticionLocal) it.next();
					PeticionDTO pDto = new PeticionDTO();
					PeticionKey pKey = (PeticionKey) pLocal.getPrimaryKey();
					Peticion_atisKey peticion_atisKey=(Peticion_atisKey) pLocal.getFk_01_pet_atis().getPrimaryKey();
					pDto.setCodPetCd(peticion_atisKey.cod_pet_cd);
					pDto.setAgrupaciones(pLocal.getAgrupacionesString(","));
					pDto.setPetiNumero( pKey.peti_numero );
					pDto.setPetiFechaIngreso( pLocal.getPeti_fecha_ingreso() );
					pDto.setPetiFechaCompromiso( pLocal.getPeti_fecha_compromiso() );
					
					listaPeticiones.add( pDto );
				
					cantElements ++;
				}
			}
		} catch (EJBException e) {
		} catch (FinderException e) {
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}

		return listaPeticiones;

	}
	
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario)
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			
			//log.debug("Obteniendo Empresa para Usuario:"+idUsuario);
			usuarioHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(idUsuario));
			//log.debug(usuarioLocal.getUsua_nombre());
			if(usuarioLocal.getEmpresa()==null)
			{
				//log.debug("El usuario no tiene Empresa");
				return new InformacionEmpresaDTO();
			}
			InformacionEmpresaDTO empresaDTO=new InformacionEmpresaDTO();
			EmpresaLocal empresaLocal=usuarioLocal.getEmpresa();
			EmpresaKey empresaKey=(EmpresaKey) empresaLocal.getPrimaryKey();
			empresaDTO.setEmprId(empresaKey.empr_id);
			empresaDTO.setEmprNombre(empresaLocal.getEmpresa_nombre());
			empresaDTO.setEmprDescripcion(empresaLocal.getEmpresa_descripcion());
			//log.debug(empresaDTO.toString());
			return empresaDTO;
		}
		catch(FinderException e)
		{
			e.printStackTrace();
			return new InformacionEmpresaDTO();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new InformacionEmpresaDTO();
		}
	}

	
	/**
	 * Este metodo retorna una lista de los Ps vs Operacion comercial que invocan a la actividad x 
	 */
	public boolean pasaPSyOcXActividad(Long nroPeticion,Long psId,Long opcoId,Integer actividad_flujo_id) throws ATiempoAppEx
	{
		try
		{
			//log.debug("Chequeando que la peticion :"+nroPeticion+" con PsId:"+psId+" y opcoId:"+opcoId+" pasa por la actividad:"+actividad_flujo_id);
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection lista=peticion_flujoHome.findByIdActividadFPetPsOC(actividad_flujo_id,nroPeticion,psId,opcoId);
			if(lista.size()==0)
			{
		//		log.debug("NO pasa");
				return false;
			}
		//	log.debug("PASA");
			return true;
		}
		catch (FinderException e)
		{
		//	log.debug("no pasa");
			return false;
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
	}
	}
	
	public boolean estaOKProductoServicioPeticion(Producto_servicio_peticionLocal local)
	{
		Producto_servicioKey psKey=(Producto_servicioKey) local.getProducto_servicio().getPrimaryKey();
		Operacion_comercialKey opComKey=(Operacion_comercialKey) local.getOperacion_comercial().getPrimaryKey();
		boolean estaOk=true;
		Collection listaEstadoPs=local.getEstado_ps_peticion();
		if(listaEstadoPs.size()>0)
		{
			Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPs.iterator().next();
			Collection listaCausalPeticion=estado_ps_peticionLocal.getCausal_peticion();
			if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==ComunInterfaces.estadoCierreError)
				return false;
			else
				return true;
		}
		else
			return true;
	}
	
	public boolean estaOKProductoServicioPeticion(Long peticion,PsVsOcVO psVsOcVO)
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			boolean estaOk=false;
			Producto_servicio_peticionKey pspKey = new Producto_servicio_peticionKey();
			pspKey.correlativo=psVsOcVO.getCorrelativo();
			pspKey.fk_psp_pet_peti_numero=peticion;
			//Busca solo el PSP que corresponde
			Producto_servicio_peticionLocal local=psPetHome.findByPrimaryKey(pspKey);
//			Collection lista=psPetHome.findByPetiNumeroPsYOpCo(peticion,psVsOcVO.getPsId(),psVsOcVO.getOpComId());
//			for(Iterator iterator=lista.iterator();iterator.hasNext();)
//			{
//				Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
			Collection listaEstadoPs=local.getEstado_ps_peticion();
			if(listaEstadoPs.size()>0)
			{
				Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPs.iterator().next();
//				Collection listaCausalPeticion=estado_ps_peticionLocal.getCausal_peticion();
				if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==ComunInterfaces.estadoCierreError)
					return false;
			}
//			}
			return true;
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			return false;
		}
	}
	
	public ArrayList listaPsDePeticionQuePasaPorActividad(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx
	{
		log.debug("obtengo lista peticiones PetSB");
		//Este Metodo no me entrega el estado de los PS
		ArrayList arrayList=new ArrayList();
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			
			if(peticionHome==null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(peticion_flujoHome==null)
				peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey localkey=(Producto_servicio_peticionKey)local.getPrimaryKey();
				Producto_servicioKey psKey=(Producto_servicioKey) local.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey opComKey=(Operacion_comercialKey) local.getOperacion_comercial().getPrimaryKey();
				try
				{
					Collection lista=peticion_flujoHome.findByIdActividadFPetPsOC(actividad_flujo_id,nroPeticion,psKey.ps_id,opComKey.opco_id);
					if(lista.size()==0)
						continue;
					PsVsOcVO psVsOcVO=new PsVsOcVO();
					psVsOcVO.setPsId(psKey.ps_id);
					psVsOcVO.setOpComId(opComKey.opco_id);
					psVsOcVO.setOpComRevId(local.getOperacion_comercial().getOpco_rev());
					psVsOcVO.setOpComTipo( local.getOperacion_comercial().getOpco_tipo());
					Familia_producto_servicioKey fPsKey=(Familia_producto_servicioKey) local.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					psVsOcVO.setFaPsId(fPsKey.faps_id);
					psVsOcVO.setCorrelativo(localkey.correlativo);
					arrayList.add(psVsOcVO);
				}
				catch (FinderException e)
				{
					log.debug("catch *********");
				}
			}
			log.debug("return arraylist con nro PS = " + arrayList.size());
			return arrayList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	
	public ArrayList recuperaPsParaQuiebresYNovedad(ActividadDTO actividadDTO, Long idPeticionAtiempo,Integer estado)
	{
		try
		{
////			---------------------arreglo direcciones 
//			RecursosDelegate recursosDirec = new RecursosDelegate();
//			PeticionKey peticionKeyd = new PeticionKey(idPeticionAtiempo);
//			PeticionLocalHome peticionLocalHomed = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//			PeticionLocal peticionLocalD = peticionLocalHomed.findByPrimaryKey(peticionKeyd);
//			Collection producto_servicio_peticionArray = peticionLocalD.getProducto_servicio_peticion();	
//			Long numeroAtis=(new Long (0));
//			Collection s=peticionLocalD.getFk_01_pet_atis().getAgrupacion_atis();
//			for(Iterator iter3 = s.iterator();iter3.hasNext();)
//			{
//				Agrupacion_atisLocal agrupacion_atisLocal= (Agrupacion_atisLocal) iter3.next();
//				Agrupacion_atisKey ak=(Agrupacion_atisKey)	agrupacion_atisLocal.getPrimaryKey();
//				numeroAtis=ak.fk_pet_agrupacion_cod_pet_cd;
//			}
//			
//			recursosDirec.CorregirDireccionPeticionTraslado(numeroAtis,idPeticionAtiempo);
////-----------------------------FIN
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Causal_ps_oc_actividadLocalHome causal_ps_oc_actividadHome=(Causal_ps_oc_actividadLocalHome) HomeFactory.getHome(Causal_ps_oc_actividadLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
			
			if(estado==null)
				estado=new Integer(ComunInterfaces.estadoPeticionEnCurso);
			//log.debug("Cargando quiebre novedad para peticion:"+idPeticionAtiempo);
			ArrayList listaPsesDelaPeticion=new ArrayList();
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(idPeticionAtiempo));
			Collection listaProductoServicioPeticion=peticionLocal.getProducto_servicio_peticion();
			//log.debug("Es quiebre:"+actividadDTO.isQuiebre());
			for(Iterator iterator=listaProductoServicioPeticion.iterator();iterator.hasNext();)
			{
				Estado_ps_peticionLocal causaAsociada=null;
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey producto_servicio_peticionKey=(Producto_servicio_peticionKey) producto_servicio_peticionLocal.getPrimaryKey();
				ProductoServicioPeticionDTO productoServicioPeticionDTO=new ProductoServicioPeticionDTO();
				productoServicioPeticionDTO.setCorrelativo(producto_servicio_peticionKey.correlativo);
				productoServicioPeticionDTO.setPetiNumero(producto_servicio_peticionKey.fk_psp_pet_peti_numero);
				productoServicioPeticionDTO.setPsIdFam(producto_servicio_peticionLocal.getFamiliaKey().faps_id);
				//el Estado Ps peticion del ps mantiene el ultimo siempre va a tener uno.
				for(Iterator iterator2=producto_servicio_peticionLocal.getEstado_ps_peticion().iterator();iterator2.hasNext();)
				{
					Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) iterator2.next();
					if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()!=0)
					{
						//log.debug("Tengo estado de cierre que no es de solucion. para el ps:"+producto_servicio_peticionLocal.getPsId());
						productoServicioPeticionDTO.setIdCausaAsociada(estado_ps_peticionLocal.getCod_causal());
						causaAsociada=estado_ps_peticionLocal;
					}
					//else
					//	log.debug("El ultimo estado del Ps es de solucion. para el ps:"+producto_servicio_peticionLocal.getPsId());
					break;
				}
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey)operacion_comercialLocal .getPrimaryKey();
				Producto_servicioLocal producto_servicioLocal= producto_servicio_peticionLocal.getProducto_servicio();
				Producto_servicioKey producto_servicioKey=(Producto_servicioKey)producto_servicioLocal.getPrimaryKey();
				productoServicioPeticionDTO.setPsId(producto_servicioKey.ps_id);
				productoServicioPeticionDTO.setNombrePS(producto_servicioLocal.getPs_nombre());
				productoServicioPeticionDTO.setOpcoId(operacion_comercialKey.opco_id);
				productoServicioPeticionDTO.setDescOperComer(operacion_comercialLocal.getOpco_nombre());
				boolean mostrarCausas=false;
				log.debug("quiebres "+actividadDTO.getIdActFlujo().intValue());
				
				if(actividadDTO.isQuiebre() || actividadDTO.isEsGestionInbound()){
					//La Actividad es de Quiebre Tengo que: PONER EDITABLE SOLOS LOS PSES QUE TIENEN CAUSAS
					//si es pgc tengo que poner todos los pcs
					//si es pgi tengo que poner solo los que tienen causas
					
					if(actividadDTO.getIdActFlujo().intValue()==new Integer(VpistbbaConfig.getVariable("IDPGACS")).intValue() ||actividadDTO.getIdActFlujo().intValue()==new Integer(VpistbbaConfig.getVariable("IDPGF")).intValue() || actividadDTO.getIdActFlujo().intValue()==new Integer(VpistbbaConfig.getVariable("IDPGC")).intValue() || actividadDTO.getIdActFlujo().intValue()==new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue() || actividadDTO.isEsGestionInbound())
					{
						
						//req 568, david m.
						if(actividadDTO.getIdActFlujo().intValue()==new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue()){
							
							
							for(Iterator iterator3=producto_servicio_peticionLocal.getEstado_ps_peticion().iterator();iterator3.hasNext();)
							{
								Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) iterator3.next();
								if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()!=0)
								{
									if(estado_ps_peticionLocal.getCod_actividad().longValue()==ComunInterfaces.idActividadPGC){
										if(productoServicioPeticionDTO.getIdCausaAsociada()!=null){
											productoServicioPeticionDTO.setQuiebrePrevioAPGI(true);
										}
									}
								}
								break;
							}

						}
						//fin req 568, david m.
						mostrarCausas=true;	
					}
					else
					{
						if(productoServicioPeticionDTO.getIdCausaAsociada()!=null)
						{
							//log.debug("TEngo causa asociada para el ps:"+producto_servicio_peticionLocal.getPsId());	
							mostrarCausas=true;
						}
						//else
						//	log.debug("No Tengo causa asociada para el ps:"+producto_servicio_peticionLocal.getPsId());
					}
					if(actividadDTO.isEsGestionInbound())
					{
						boolean llamaAlaActividad=pasaPSyOcXActividad(idPeticionAtiempo,producto_servicioKey.ps_id,operacion_comercialKey.opco_id,actividadDTO.getIdActFlujo());
						boolean estaOK=estaOKProductoServicioPeticion(producto_servicio_peticionLocal);
						productoServicioPeticionDTO.setLlamaALaActividad(true);
						productoServicioPeticionDTO.setEstaOK(estaOK);
					}
				}else{
					//La Actividad NO es de Quiebre Tengo que: PONER TODOS LOS PESES QUE LLAMAN A LA ACTIVIDAD EN EL DIRECTOR (SE TIENEN QUE VER TODOS)
					if(actividadDTO.getIdActFlujo()==null){
						mostrarCausas=false;
					}
					else
					{					
						boolean llamaAlaActividad=pasaPSyOcXActividad(idPeticionAtiempo,producto_servicioKey.ps_id,operacion_comercialKey.opco_id,actividadDTO.getIdActFlujo());
						
						if((actividadDTO.getIdActFlujo().intValue()==141 && operacion_comercialLocal.getOpco_tipo()!=null&&operacion_comercialLocal.getOpco_tipo().equals("A"))||
								(actividadDTO.getIdActFlujo().intValue()==141 && operacion_comercialLocal.getOpco_tipo()!=null&&operacion_comercialLocal.getOpco_tipo().equals("ACP"))||
								(actividadDTO.getIdActFlujo().intValue()==141 && operacion_comercialLocal.getOpco_tipo()!=null&&operacion_comercialLocal.getOpco_sva().equals("A"))){
							llamaAlaActividad=true;
						
						}else if((actividadDTO.getIdActFlujo().intValue()==142 && operacion_comercialLocal.getOpco_tipo()!=null&&operacion_comercialLocal.getOpco_tipo().equals("B"))||
								(actividadDTO.getIdActFlujo().intValue()==142&& operacion_comercialLocal.getOpco_tipo()!=null&&operacion_comercialLocal.getOpco_tipo().equals("BCP"))||
								(actividadDTO.getIdActFlujo().intValue()==142 && operacion_comercialLocal.getOpco_tipo()!=null&&operacion_comercialLocal.getOpco_sva().equals("B"))){
							
							llamaAlaActividad=true;
						}
						
						boolean estaOK=estaOKProductoServicioPeticion(producto_servicio_peticionLocal);
						
						/**
						 * Para los ps's de la familia de Asistencia cliente no se guarda registro en la tabla VPISTBBA.PETICION_FLUJO
						 * en el momento de crear el flujo (ver clase: CalculaFlujo.calcularNuevoFlujo()).  Estos ps's solo están asociados
						 * a la actividad Asistencia Cliente y por eso el método pasaPSyOcXActividad siempre retorna false.
						 */
						
						
						Familia_producto_servicioKey familiakey=(Familia_producto_servicioKey)producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey();
						if(familiakey.faps_id.intValue()==ComunInterfaces.familiaAsistencia){
							Collection estadoPsPetAsistenciaList= producto_servicio_peticionLocal.getEstado_ps_peticion();
							for(Iterator listadoEstadoAsistenciaIt=estadoPsPetAsistenciaList.iterator();listadoEstadoAsistenciaIt.hasNext();){
								Estado_ps_peticionLocal estadoPsPetAsistencia=(Estado_ps_peticionLocal)listadoEstadoAsistenciaIt.next();
								//Si el estado es 3, es porque el ps de asistencia ya ha ido a plataforma de gestion, PGI o PGC.
								if(estadoPsPetAsistencia.getCod_estado_cierre().intValue()==ComunInterfaces.estadoCierreError){
									productoServicioPeticionDTO.setLlamaALaActividad(true);
								}
							}					
							
						}
						else{
							productoServicioPeticionDTO.setLlamaALaActividad(llamaAlaActividad);
						}
						//Fin Asistencia cliente	
						
						productoServicioPeticionDTO.setEstaOK(estaOK);
						
						if(!llamaAlaActividad)
							mostrarCausas=false;
						else
							mostrarCausas=true;	
					}
				}
				//log.debug("Mostrar Causas:"+mostrarCausas);
				Collection listaCausales=null;
				if(mostrarCausas)
					listaCausales=causal_ps_oc_actividadHome.findByFamPsOcACt(producto_servicio_peticionLocal.getFamiliaKey().faps_id,actividadDTO.getIdActividad(),operacion_comercialKey.opco_id);
				else
					listaCausales=new ArrayList();
				//log.debug("Traigo ListaCausales en:"+listaCausales.size());
				for(Iterator iterator2=listaCausales.iterator();iterator2.hasNext();) 
				{
					Causal_ps_oc_actividadLocal causal_ps_oc_actividadLocal=(Causal_ps_oc_actividadLocal) iterator2.next();
					CausalPsOcActividadDTO causalPsOcActividadDTO=new CausalPsOcActividadDTO();
					Catalogo_causalLocal catalogo_causalLocal=causal_ps_oc_actividadLocal.getCatalogo_causal();
					Catalogo_causalKey catalogo_causalKey=(Catalogo_causalKey) catalogo_causalLocal.getPrimaryKey();
					log.debug("cod causal:"+catalogo_causalKey.cod_causal);
					causalPsOcActividadDTO.setCodCausal(catalogo_causalKey.cod_causal);
					causalPsOcActividadDTO.setDescripcionCausa(catalogo_causalLocal.getDescripcion_causal());
					
					if(catalogo_causalLocal.getQuiebre().intValue()==1)
						causalPsOcActividadDTO.setQuiebre(true);
					else
						causalPsOcActividadDTO.setQuiebre(false);
					if(productoServicioPeticionDTO.getIdCausaAsociada()!=null && causalPsOcActividadDTO.getCodCausal()!=null){
					//log.debug("Causa Asociada Producto Servicio Peticion " + productoServicioPeticionDTO.getIdCausaAsociada().longValue());
					//log.debug("Codigo Causal PS OC Actividad " + causalPsOcActividadDTO.getCodCausal().longValue());
						if(productoServicioPeticionDTO.getIdCausaAsociada().longValue()==causalPsOcActividadDTO.getCodCausal().longValue())
						{
							causalPsOcActividadDTO.setAsignado(true);
						}
						else
							causalPsOcActividadDTO.setAsignado(false);
					}
					else
						causalPsOcActividadDTO.setAsignado(false);
					
					//log.debug("Asignado :..." + causalPsOcActividadDTO.isAsignado());
					//CR23995 -- 23/04/2009 -- G.Quevedo
					causalPsOcActividadDTO.setGestionable(catalogo_causalLocal.getGestionable());
					//log.debug("causalllll :..." + catalogo_causalLocal.getGestionable());
					productoServicioPeticionDTO.addCausalPsOCActividad(causalPsOcActividadDTO);
					if(productoServicioPeticionDTO.getIdCausaAsociada()!=null){
						//Si tiene una causa tengo que ver si le corresponde poder resolverla.
						if(catalogo_causalKey.cod_causal.longValue()==productoServicioPeticionDTO.getIdCausaAsociada().longValue())
						{
							//Traigo en la lista la causa . Por lo tanto la puedo resolver
							productoServicioPeticionDTO.setResolutor(true);
						}
					}
					productoServicioPeticionDTO.ordenarCausales();
				}
				
				//Verifico que si tiene causa asociada esta esté en la lista
				if(productoServicioPeticionDTO.getIdCausaAsociada()!=null)
				{
					boolean estaLaCausaEnLaLista=false;
					for(Iterator iterator2=productoServicioPeticionDTO.getListaCausales().iterator();iterator2.hasNext();)
					{
						CausalPsOcActividadDTO actividadDTO2=(CausalPsOcActividadDTO) iterator2.next();
						Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(new Catalogo_causalKey(actividadDTO2.getCodCausal()));
						Catalogo_causalKey catalogo_causalKey=(Catalogo_causalKey) catalogo_causalLocal.getPrimaryKey();
						if(productoServicioPeticionDTO.getIdCausaAsociada().longValue()==catalogo_causalKey.cod_causal.longValue())
						{
							estaLaCausaEnLaLista=true;
							break;
						}
					}
					if(!estaLaCausaEnLaLista)
					{
						if(causaAsociada!=null)
						{
							CausalPsOcActividadDTO causalPsOcActividadDTO=new CausalPsOcActividadDTO();
							causalPsOcActividadDTO.setCodCausal(causaAsociada.getCod_causal());
							Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(new Catalogo_causalKey(causaAsociada.getCod_causal()));
							causalPsOcActividadDTO.setDescripcionCausa(catalogo_causalLocal.getDescripcion_causal());
							
							// probemos
							
							causalPsOcActividadDTO.setGestionable(catalogo_causalLocal.getGestionable());
							if(catalogo_causalLocal.getQuiebre().intValue()==1)
								causalPsOcActividadDTO.setQuiebre(true);
							else
								causalPsOcActividadDTO.setQuiebre(false);
							causalPsOcActividadDTO.setAsignado(true);
							if(estado.intValue()!=ComunInterfaces.estadoPeticionCancelada)
								productoServicioPeticionDTO.setResolutor(true);
							productoServicioPeticionDTO.addCausalPsOCActividad(causalPsOcActividadDTO);
							productoServicioPeticionDTO.ordenarCausales();
						}
					}
				}
				
					
				listaPsesDelaPeticion.add(productoServicioPeticionDTO);
			}
			return listaPsesDelaPeticion;
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			return new ArrayList();
		}
	}
	
	public void propagaQuiebrePCaPS(Long petiNumero,Long idPs, Long idOC)throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);

			ArrayList listaPropagados=new ArrayList();
			//Obtengo el producto servicio peticion
			//victor te cambie este metodo el finder findByPetiNumeroPsYOpCo no era
			//correcto que trajera solo uno, pork en Tv nos llevan peticiones con dos ps de deco y la 
			//misma operacion comercial tonces se taba cayendo cuando trataba de propagar el quiebre
			log.info("Propagando quiebre PC a PS.Pet"+petiNumero+". Ps:"+idPs+" idOc:"+idOC);
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection lista= producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(petiNumero,idPs,idOC);
			for(Iterator iterator=lista.iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey localKey=(Producto_servicio_peticionKey)producto_servicio_peticionLocal.getPrimaryKey();

				Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				
				PsVsOcVO psVsOcVO=new PsVsOcVO();
				psVsOcVO.setPsId(producto_servicioKey.ps_id);
				psVsOcVO.setOpComId(operacion_comercialKey.opco_id);
				psVsOcVO.setCorrelativo(localKey.correlativo);
				if(listaPropagados.indexOf(psVsOcVO)>=0)
					return;
				listaPropagados.add(psVsOcVO);
				
				//Si el PC es de BA no puede cancelar los PS de STB de la agrupacion, al reves si.
				Familia_producto_servicioKey fPsKey=(Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				boolean esPCBA = false;
				//REQ BA NAKED se adiciona familia pc naked
				if (fPsKey.faps_id.intValue()==ComunInterfaces.familiaPcBA||fPsKey.faps_id.intValue()==ComunInterfaces.familiaPcBANaked)
				{
					log.info("Propagando: PC es PC BA");
					esPCBA=true;
				}

				boolean hayCausa=false;
				Collection listaEstado=producto_servicio_peticionLocal.getEstado_ps_peticion();

				Estado_ps_peticionLocal estado_ps_peticionLocal = null;
				Catalogo_causalLocal catalogo_causalLocal = null;
				Estado_psLocal estado_psLocal = null;
				UsuarioLocal usuarioLocal =null;
				String fechaIni = "";
				String fechaFin = "";

				if(listaEstado.size()>0)
				{
					//El ps tiene Estado asignado
					//Solo voy a propagar los quiebres.
					estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstado.iterator().next();
					Estado_ps_peticionKey estado_ps_peticionKey=(Estado_ps_peticionKey) estado_ps_peticionLocal.getPrimaryKey();
					//Si Tengo quiebre
					if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError)
					{	
						Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(estado_ps_peticionLocal.getCod_causal());
						catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
						Estado_psKey estado_psKey=new Estado_psKey(new Long(estadoCierreError));
						estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
						//primero saco la fecha de la ultima
						Collection listaCausalPeticion=causal_peticionHome.findCausalesDePsOrderDes(estado_ps_peticionKey.correlativo);
						Causal_peticionLocal ultimaCausa=(Causal_peticionLocal) listaCausalPeticion.iterator().next();
						fechaIni=ultimaCausa.getFecha_inicio();
						fechaFin=ultimaCausa.getFecha_termino();
						//Obtengo el usuario	
						usuarioLocal=ultimaCausa.getUsuario();		
						hayCausa=true;
					}
				}				

				//Obtengo la agrupacion del Producto Servicio con Quiebre		
				Agrupacion_atisLocal agrupacion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub();

				//Obtengo las subpeticiones de la agrupacion
				Iterator iter = agrupacion_atisLocal.getSubpeticion_atis().iterator();
				while (iter.hasNext() && hayCausa)
				{ 
					Subpeticion_atisLocal subPet = (Subpeticion_atisLocal) iter.next();
					Long psId = subPet.getCod_pro_ser_cd();
					Long ocId = subPet.getCod_opr_cmr_cd();
					log.info("Recorriendo Ps:"+ psId + " de Agrupacion PC:"+ idPs +" para propagar quiebres");
					if (psId == idPs){
						//Es el ps tipo pc al que le estoy propagando la causa, es decir ya la tiene.
						continue;
					}
					//Recupero el producto servicio asociado a la subpeticion
					Producto_servicio_peticionLocal psLocalTemp=(Producto_servicio_peticionLocal) subPet.getProducto_servicio_peticion().iterator().next();
					Familia_producto_servicioKey fPsKeyTemp=(Familia_producto_servicioKey) psLocalTemp.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					//Si es pc BA, solo propago el quiebre por los ps de la agrupacion que son de BA y no es el PC
					//REQ BA NAKED adiciona familia ps naked
					if (esPCBA && (fPsKeyTemp.faps_id.intValue() != ComunInterfaces.familiaBandaAncha ||fPsKeyTemp.faps_id.intValue() != ComunInterfaces.familiaBandaAnchaNaked ))
//					if (esPCBA && (fPsKeyTemp.faps_id.intValue() != ComunInterfaces.familiaPcBA && fPsKeyTemp.faps_id.intValue() != ComunInterfaces.familiaBandaAncha) )
					{
						log.info("No es ps BA. Paso al Sgte PS");
						continue;
					}
					if (esPCBA)
					{
						log.info("Ps:"+ psId + "es familia BA");
					}
					Collection listaEstadoPsPet=psLocalTemp.getEstado_ps_peticion();
					//Si ya tiene estado, se actualiza.
					if(listaEstadoPsPet.size()>0){
						Estado_ps_peticionLocal estado_ps_peticionLocalTemp=(Estado_ps_peticionLocal)listaEstadoPsPet.iterator().next();//estado_ps_peticionHome.create(new Long(correlativo),psLocalTemp.getProducto_servicio(),psLocalTemp);
						if(estado_ps_peticionLocalTemp.getCod_estado_cierre().intValue()==estadoCierreError){
							//Si ya tiene un error asociado el ps, no le propago el del pc
							continue;	
						}
						estado_ps_peticionLocalTemp.setCod_causal(estado_ps_peticionLocal.getCod_causal());
						estado_ps_peticionLocalTemp.setCod_estado_cierre(estado_ps_peticionLocal.getCod_estado_cierre());
						estado_ps_peticionLocalTemp.setNovedad(estado_ps_peticionLocal.getNovedad());
						estado_ps_peticionLocalTemp.setCod_actividad(estado_ps_peticionLocal.getCod_actividad());
	
						//asocio el nuevo estado del ps a la causa
						long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
						Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocalTemp,usuarioLocal);
						causal_peticionLocal.setNovedad(estado_ps_peticionLocalTemp.getNovedad());
						causal_peticionLocal.setCod_actividad(estado_ps_peticionLocalTemp.getCod_actividad());
						causal_peticionLocal.setFecha_inicio(fechaIni);
						causal_peticionLocal.setFecha_termino(fechaFin);	
					}
					else
					{
						//Si no tiene estado, se crea uno nuevo
						log.info("Estoy creando un nuevo quiebre o novedad para el Ps");
						long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
						Estado_ps_peticionLocal estado_ps_peticionLocalTemp=estado_ps_peticionHome.create(new Long(correlativo),psLocalTemp.getProducto_servicio(),psLocalTemp);
						estado_ps_peticionLocalTemp.setCod_causal(estado_ps_peticionLocal.getCod_causal());
						estado_ps_peticionLocalTemp.setCod_estado_cierre(estado_ps_peticionLocal.getCod_estado_cierre());
						estado_ps_peticionLocalTemp.setNovedad(estado_ps_peticionLocal.getNovedad());
						estado_ps_peticionLocalTemp.setCod_actividad(estado_ps_peticionLocal.getCod_actividad());
	
						//asocio el nuevo estado del ps a la causa
						long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
						Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocalTemp,usuarioLocal);
						causal_peticionLocal.setNovedad(estado_ps_peticionLocalTemp.getNovedad());
						causal_peticionLocal.setCod_actividad(estado_ps_peticionLocalTemp.getCod_actividad());
						causal_peticionLocal.setFecha_inicio(fechaIni);
						causal_peticionLocal.setFecha_termino(fechaFin);						
					}
							
					log.info("Propagado");				
				}	
			}
		} catch (NamingException e) {
			log.debug("Error",e);
			throw new ATiempoAppEx(e.getMessage(),ATiempoAppEx.NAMING);
		} catch (FinderException e) {
			log.debug("Error",e);
			throw new ATiempoAppEx(e.getMessage(),ATiempoAppEx.FINDER);
		} catch (CreateException e) {
			log.debug("Error",e);
			throw new ATiempoAppEx(e.getMessage(),ATiempoAppEx.CREATE);
		} catch (Exception e) {
		log.debug("Error",e);
		throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	/**
	 * Método para req de Asistencia, 33626
	 * @ param producto_servicioKey, sobre el cual se evalúa si es ps de asistencia Ba (1443 - 1446) o LB (1441,1442)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#esPsAsistenciaBAoLB(co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey)
	 */
	public int esPsAsistenciaBAoLB(Producto_servicioKey producto_servicioKey){

		
			int tipoPs=0;
			
			String psAsistBA = VpistbbaConfig.getVariable("PS_ASISTENCIA_BA");
			String[] listaPsAsistBA = psAsistBA.split(",");
			
			String psAsistLB = VpistbbaConfig.getVariable("PS_ASISTENCIA_LB");
			String[] listaPsAsistLB = psAsistLB.split(",");
			
			for (int contPsAsistBA = 0; contPsAsistBA <= listaPsAsistBA.length - 1; contPsAsistBA++) {
				int psAsistBAInt = Integer.parseInt(listaPsAsistBA[contPsAsistBA]);
				if (producto_servicioKey.ps_id.intValue() == psAsistBAInt) {
					log.debug("Es un ps de Asistencia cliente BA..."+psAsistBAInt);
					tipoPs=1;
					break;
				}
			}
			
			
			for (int contPsAsistLB = 0; contPsAsistLB <= listaPsAsistLB.length - 1; contPsAsistLB++) {
				int psAsistLBInt = Integer.parseInt(listaPsAsistLB[contPsAsistLB]);
				if (producto_servicioKey.ps_id.intValue() == psAsistLBInt) {
					log.debug("Es un ps de Asistencia cliente LB..."+psAsistLBInt);
					tipoPs=2;
					break;
				}
			}
		
		return tipoPs;
		
	}
	
	public void grabarQuiebresNovedades(ArrayList listaQN,Long idPeticionAtiempo) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);

			int i=1;
			for(Iterator iterator=listaQN.iterator();iterator.hasNext();)
			{
				i++;
				PsPetCausalONovedad psPetCausalONovedad=(PsPetCausalONovedad) iterator.next();
				if(psPetCausalONovedad.getCodEstadoCierre()==null)
				{
					log.info("psPetCausalONovedad.getCodEstadoCierre()==null. Se continua");
					continue;
				}

				PeticionKey peticionKey=new PeticionKey(idPeticionAtiempo);
				Producto_servicio_peticionKey key=new Producto_servicio_peticionKey(psPetCausalONovedad.getCorrelativoPs(),peticionKey);
				Producto_servicio_peticionLocal local=psPetHome.findByPrimaryKey(key);
				if(local.getEstado_ps_peticion()!=null)
				{
					if(local.getEstado_ps_peticion().size()>0)
					{
						log.info("Existe uno lo actualizo");
						//Existe uno lo actualizo
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) local.getEstado_ps_peticion().iterator().next();
						if(psPetCausalONovedad.getCodEstadoCierre().intValue()==0)
						{
							log.info("estoy solucionando el quiebre/novedad para el ps:"+local.getPsId());
							//Lo estoy solucionando
							estado_ps_peticionLocal.setCod_estado_cierre(new Integer(psPetCausalONovedad.getCodEstadoCierre().intValue()));
							UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(psPetCausalONovedad.getUsua_id()));
							estado_ps_peticionLocal.setNovedad("(Solucionado por "+usuarioLocal.getUsua_login()+" )");
							Estado_ps_peticionKey estado_ps_peticionKey=(Estado_ps_peticionKey) estado_ps_peticionLocal.getPrimaryKey();
							
							Estado_psKey estado_psKey=new Estado_psKey(psPetCausalONovedad.getCodEstadoCierre());
							Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
													
							Collection listaCausalPs=causal_peticionHome.findCausalesDePsOrderDes(estado_ps_peticionKey.correlativo);
							for(Iterator iterator2=listaCausalPs.iterator();iterator2.hasNext();)
							{
								Causal_peticionLocal causal_peticionLocal=(Causal_peticionLocal) iterator2.next();
								causal_peticionLocal.setNovedad("(Solucionado por "+usuarioLocal.getUsua_login()+" )");
								causal_peticionLocal.setEstado_ps(estado_psLocal);
								Fecha f=new Fecha(new Date());
								causal_peticionLocal.setFecha_termino(f.getFechaconFormato(9));
								break;
							}
							continue;
						}
						else
							log.debug("No lo estoy solucionando estoy ingresando un nuevo qUIEBRE O NOVEDAD");
							estado_ps_peticionLocal.setCod_actividad(psPetCausalONovedad.getCodActividad());
							estado_ps_peticionLocal.setCod_estado_cierre(new Integer(psPetCausalONovedad.getCodEstadoCierre().intValue()));	
							estado_ps_peticionLocal.setCod_causal(psPetCausalONovedad.getIdCausa());
							estado_ps_peticionLocal.setNovedad(psPetCausalONovedad.getDescripcion());
							
							Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(psPetCausalONovedad.getIdCausa());
							Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
							Estado_psKey estado_psKey=new Estado_psKey(psPetCausalONovedad.getCodEstadoCierre());
							Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
							UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(psPetCausalONovedad.getUsua_id()));
							long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
							Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
							causal_peticionLocal.setFecha_inicio(psPetCausalONovedad.getFechaInicio());
							causal_peticionLocal.setFecha_termino(psPetCausalONovedad.getFechaTermino());
							causal_peticionLocal.setNovedad(psPetCausalONovedad.getDescripcion());
							causal_peticionLocal.setCod_actividad(psPetCausalONovedad.getCodActividad());
					}
					else
					{
						//lo creo siempre y cuando tenga estado distinto de cero.
						//Si estoy en Control de Instalacion o Control Desinstalacion 
						//grabo la Fecha de Cierre para el Ps
						
						if(psPetCausalONovedad.getCodEstadoCierre().intValue()==0)
						{
							if(psPetCausalONovedad.getCodActividad().longValue()==idActividadControlInstalacion || psPetCausalONovedad.getCodActividad().longValue()==idActividadControlDesInstalacion)
								local.setPspe_fecha_fin(new Fecha(psPetCausalONovedad.getFechaTermino(),"dd/MM/yyyy HH:mm:ss").getTimestamp());	
							continue;
						}
						log.info("Estoy creando un nuevo quiebre o novedad para el Ps");
						long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
						Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),local.getProducto_servicio(),local);
						estado_ps_peticionLocal.setCod_causal(psPetCausalONovedad.getIdCausa());
						estado_ps_peticionLocal.setCod_estado_cierre(new Integer(psPetCausalONovedad.getCodEstadoCierre().intValue()));
						estado_ps_peticionLocal.setNovedad(psPetCausalONovedad.getDescripcion());
						estado_ps_peticionLocal.setCod_actividad(psPetCausalONovedad.getCodActividad());						

						Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(psPetCausalONovedad.getIdCausa());
						Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
						Estado_psKey estado_psKey=new Estado_psKey(psPetCausalONovedad.getCodEstadoCierre());
						Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
						UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(psPetCausalONovedad.getUsua_id()));
						long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
						Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
						causal_peticionLocal.setFecha_inicio(psPetCausalONovedad.getFechaInicio());
						causal_peticionLocal.setFecha_termino(psPetCausalONovedad.getFechaTermino());
						causal_peticionLocal.setNovedad(psPetCausalONovedad.getDescripcion());
						causal_peticionLocal.setCod_actividad(psPetCausalONovedad.getCodActividad());
					}
				}
			}

		}
		catch(Exception exception)
		{
			log.error("Exception",exception);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,exception);
		}
	}

	/**
	 * Método para req de Asistencia, 33626
	 * @param idPeticionAtiempo, la petición que se está manejando
	 * @param tipoAsistencia, si el quiebre es de BA o LB se da de baja el ps de asistencia de BA o LB
	 * @param codAct, para LB si ya se manejó el quiebre de LB por propagación de LB (método propagaAuiebrePCaPS()), entonces no se propaga el quiebre en este método.
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#propagaQuiebresLBoBAaAsistencia(java.lang.Long, java.lang.String, java.lang.Long)
	 */

	public void propagaQuiebresLBoBAaAsistencia(Long idPeticionAtiempo,String tipoAsistencia,Long codAct){
		
		
		try{
			Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
			
			log.debug("El código de la actividad es: "+codAct);

			/**
			 * req 33626
			 * Para Asistencia
			 */
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome (Producto_servicioLocalHome.JNDI_NAME);
			
			boolean tieneAsistencia=false;
			Collection listaPsAsistencia=psPetHome.findByIdFamiliaPs(new Long(familiaAsistencia),idPeticionAtiempo);
			Iterator listaPsAsistenciaIt=null;
			Producto_servicioLocal PsAsist=null;
			Producto_servicio_peticionLocal PsPetAsist=null;
			Estado_ps_peticionLocal estado_ps_pet_temp_ba=null;
			Collection listaEstadoPsPet=null;
			
			
			Collection listaFAmiliaAS=psPetHome.findByIdFamiliaPs(new Long(familiaAsistencia),idPeticionAtiempo);
			Iterator listaFAmiliaASIt=null;
			Producto_servicioLocal PsFamiliaAS=null;
			Producto_servicio_peticionLocal PsPetFamAS=null;
			for(listaFAmiliaASIt=listaFAmiliaAS.iterator();listaFAmiliaASIt.hasNext();){
				PsPetFamAS=(Producto_servicio_peticionLocal)listaFAmiliaASIt.next();
				PsFamiliaAS=(Producto_servicioLocal)PsPetFamAS.getProducto_servicio();
				log.debug("VIENE PS DE ASISTENCIA: "+PsPetFamAS.getNom_pro_ser_no());
				tieneAsistencia = true;
			}			
	
			
			boolean esFamiliaBA=false;
			Collection listaFAmiliaBA=psPetHome.findByIdFamiliaPs(new Long(familiaPcBA),idPeticionAtiempo);
			Iterator listaFAmiliaBAIt=null;
			Producto_servicioLocal PsFamiliaBA=null;
			Producto_servicio_peticionLocal PsPetFamBA=null;
	
			for(listaFAmiliaBAIt=listaFAmiliaBA.iterator();listaFAmiliaBAIt.hasNext();){
				PsPetFamBA=(Producto_servicio_peticionLocal)listaFAmiliaBAIt.next();
				
				if (PsPetFamBA.getEstado_ps_peticion() != null && PsPetFamBA.getEstado_ps_peticion().size() > 0){
					listaEstadoPsPet=PsPetFamBA.getEstado_ps_peticion();
					PsFamiliaBA=(Producto_servicioLocal)PsPetFamBA.getProducto_servicio();
					log.debug("VIENE PS DE BA: "+PsPetFamBA.getNom_pro_ser_no());
					esFamiliaBA = true;
					break;
				}
			}
			
			boolean esFamiliaLB=false;
			Collection listaFAmiliaLB = null;
			if(tipoAsistencia.equals("LB")){
				listaFAmiliaLB =psPetHome.findByIdFamiliaPs(new Long(familiaPcLinea),idPeticionAtiempo);
			}else if(tipoAsistencia.equals("IP")){
				listaFAmiliaLB =psPetHome.findByIdFamiliaPs(new Long(familiaIP),idPeticionAtiempo);
			}else{
				listaFAmiliaLB=new ArrayList();
			}
			Iterator listaFAmiliaLBIt=null;
			Producto_servicioLocal PsFamiliaLB=null;
			Producto_servicio_peticionLocal PsPetFamLB=null;
	
			for(listaFAmiliaLBIt=listaFAmiliaLB.iterator();listaFAmiliaLBIt.hasNext();){
				PsPetFamLB=(Producto_servicio_peticionLocal)listaFAmiliaLBIt.next();
				PsFamiliaLB=(Producto_servicioLocal)PsPetFamLB.getProducto_servicio();
				log.debug("VIENE PS DE LB: "+PsPetFamLB.getNom_pro_ser_no());
				esFamiliaLB = true;
			}

			if(tipoAsistencia.equals("BA")){
                
                Estado_ps_peticionLocal estado_ps_peticionLocalAS = null;

				//Se recupera el estado ps petición del ps de BA
				Estado_ps_peticionLocal estado_ps_peticionLocalBA = buscarQuiebrePSBALBParaAsistencia(
						PsPetFamBA, "BA");
				//DAVID: Se pone el siguiente código para que no se propague un
				// quiebre a un ps en la misma actividad. Marzo 10, 2011
				Collection estadoPSASLista = PsPetFamAS.getEstado_ps_peticion();
				Iterator estadoPSLIt = estadoPSASLista.iterator();
				if (estadoPSASLista != null && estadoPSASLista.size() > 0) {
					log.debug("Ya tiene estado...");
					for (estadoPSLIt = estadoPSASLista.iterator(); estadoPSLIt.hasNext();) {
						estado_ps_peticionLocalAS = (Estado_ps_peticionLocal) estadoPSLIt.next();

						//Si hay estadoPsPeticion para el ps de asistencia en
						// la actividad actual y con estado 3 no se hace
						// propagación.
						if (!estado_ps_peticionLocalAS.getCod_actividad().equals(codAct)&& estado_ps_peticionLocalAS.getCod_estado_cierre().intValue() != ComunInterfaces.estadoCierreError) {

							//Fin DAVID: Se pone el siguiente código para que
							// no se propague un quiebre a un ps en la misma
							// actividad. Marzo 10, 2011
							long correlativo = dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
							estado_ps_peticionLocalAS = estado_ps_peticionHome.create(new Long(correlativo), PsPetFamAS.getProducto_servicio(), PsPetFamAS);

							estado_ps_peticionLocalAS.setCod_actividad(estado_ps_peticionLocalBA.getCod_actividad());
							estado_ps_peticionLocalAS.setCod_estado_cierre(new Integer(estado_ps_peticionLocalBA.getCod_estado_cierre().intValue()));
							estado_ps_peticionLocalAS.setCod_causal(estado_ps_peticionLocalBA.getCod_causal());
							estado_ps_peticionLocalAS.setNovedad(estado_ps_peticionLocalBA.getNovedad());

							Estado_ps_peticionKey key = (Estado_ps_peticionKey) estado_ps_peticionLocalBA.getPrimaryKey();
							Collection listaCausalPs = causal_peticionHome.findCausalesDePsOrderDes(key.correlativo);
							Causal_peticionLocal causal_peticionLocalBA = null;

							//Se recupera la causal petición del ps de BA
							for (Iterator iterator2 = listaCausalPs.iterator(); iterator2.hasNext();) {
								causal_peticionLocalBA = (Causal_peticionLocal) iterator2.next();
								break;
							}

							//Se asigna el mismo causal petición de BA a los
							// ps's de Asistencia
							Catalogo_causalKey catalogo_causalKey = new Catalogo_causalKey(estado_ps_peticionLocalBA.getCod_causal());
							Catalogo_causalLocal catalogo_causalLocal = catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
							Estado_psKey estado_psKey = new Estado_psKey(new Long(estado_ps_peticionLocalBA.getCod_estado_cierre().intValue()));
							Estado_psLocal estado_psLocal = estado_psHome.findByPrimaryKey(estado_psKey);
							UsuarioLocal usuarioLocal = usuarioHome.findByPrimaryKey((UsuarioKey) (causal_peticionLocalBA.getUsuario().getPrimaryKey()));
							long id_causal_peticion = dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
							Causal_peticionLocal causal_peticionLocal = causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocalAS,usuarioLocal);
							causal_peticionLocal.setFecha_inicio(causal_peticionLocalBA.getFecha_inicio());
							causal_peticionLocal.setFecha_termino(causal_peticionLocalBA.getFecha_termino());
							causal_peticionLocal.setNovedad(estado_ps_peticionLocalBA.getNovedad());
							causal_peticionLocal.setCod_actividad(estado_ps_peticionLocalBA.getCod_actividad());
						} //fin raro
					}
				} else {//DAVID: Se pone el siguiente código para que no se
						// propague un quiebre a un ps en la misma actividad.
						// Marzo 10, 2011
					//Si no tiene estadoPsPetición previo
					long correlativo = dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");

					estado_ps_peticionLocalAS = estado_ps_peticionHome.create(new Long(correlativo), PsPetFamAS.getProducto_servicio(), PsPetFamAS);

					estado_ps_peticionLocalAS.setCod_actividad(estado_ps_peticionLocalBA.getCod_actividad());
					estado_ps_peticionLocalAS.setCod_estado_cierre(new Integer(estado_ps_peticionLocalBA.getCod_estado_cierre().intValue()));
					estado_ps_peticionLocalAS.setCod_causal(estado_ps_peticionLocalBA.getCod_causal());
					estado_ps_peticionLocalAS.setNovedad(estado_ps_peticionLocalBA.getNovedad());

					Estado_ps_peticionKey key = (Estado_ps_peticionKey) estado_ps_peticionLocalBA.getPrimaryKey();
					Collection listaCausalPs = causal_peticionHome.findCausalesDePsOrderDes(key.correlativo);
					Causal_peticionLocal causal_peticionLocalBA = null;

					//Se recupera la causal petición del ps de BA
					for (Iterator iterator2 = listaCausalPs.iterator(); iterator2.hasNext();) {
						causal_peticionLocalBA = (Causal_peticionLocal) iterator2.next();
						break;
					}

					//Se asigna el mismo causal petición de BA a los ps's de
					// Asistencia
					Catalogo_causalKey catalogo_causalKey = new Catalogo_causalKey(estado_ps_peticionLocalBA.getCod_causal());
					Catalogo_causalLocal catalogo_causalLocal = catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
					Estado_psKey estado_psKey = new Estado_psKey(new Long(estado_ps_peticionLocalBA.getCod_estado_cierre().intValue()));
					Estado_psLocal estado_psLocal = estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal = usuarioHome.findByPrimaryKey((UsuarioKey) (causal_peticionLocalBA.getUsuario().getPrimaryKey()));
					long id_causal_peticion = dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal = causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal, estado_psLocal,estado_ps_peticionLocalAS, usuarioLocal);
					causal_peticionLocal.setFecha_inicio(causal_peticionLocalBA.getFecha_inicio());
					causal_peticionLocal.setFecha_termino(causal_peticionLocalBA.getFecha_termino());
					causal_peticionLocal.setNovedad(estado_ps_peticionLocalBA.getNovedad());
					causal_peticionLocal.setCod_actividad(estado_ps_peticionLocalBA.getCod_actividad());
				}
			}//Fin, DAVID: Se pone el siguiente código para que no se propague
			 // un quiebre a un ps en la misma actividad. Marzo 10, 2011

			/**
			 * Este es el caso de LB.
			 */
			else if(tipoAsistencia.equals("LB")){
				
				Estado_ps_peticionLocal estado_ps_peticionLocalAS=null;
				
				//Se recupera el estado ps petición del ps de BA
				Estado_ps_peticionLocal estado_ps_peticionLocalLB=buscarQuiebrePSBALBParaAsistencia(PsPetFamLB,"LB");
				
				Collection estadoPSASLista=PsPetFamAS.getEstado_ps_peticion();
				Iterator estadoPSLIt=estadoPSASLista.iterator();
				if(estadoPSASLista!=null&&estadoPSASLista.size()>0){
					log.debug("Ya tiene estado...");
					for(estadoPSLIt=estadoPSASLista.iterator();estadoPSLIt.hasNext();){
						estado_ps_peticionLocalAS=(Estado_ps_peticionLocal)estadoPSLIt.next();
						/*
						 * Si ya tenía quiebre para esta petición en LB, es
						 * porque ya se había propagado por el método
						 * propagaQuiebrePCaPS(), en ese caso no se hace, por
						 * eso la siguiente comparación con el codAct
						 */
						
						if(!estado_ps_peticionLocalAS.getCod_actividad().equals(codAct)){
							
							long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
							
							
							
							estado_ps_peticionLocalAS=estado_ps_peticionHome.create(new Long(correlativo),PsPetFamAS.getProducto_servicio(),PsPetFamAS);
								
							estado_ps_peticionLocalAS.setCod_actividad(estado_ps_peticionLocalLB.getCod_actividad());
							estado_ps_peticionLocalAS.setCod_estado_cierre(new Integer(estado_ps_peticionLocalLB.getCod_estado_cierre().intValue()));	
							estado_ps_peticionLocalAS.setCod_causal(estado_ps_peticionLocalLB.getCod_causal());
							estado_ps_peticionLocalAS.setNovedad(estado_ps_peticionLocalLB.getNovedad());	
							
							Estado_ps_peticionKey key=(Estado_ps_peticionKey) estado_ps_peticionLocalLB.getPrimaryKey();
							Collection listaCausalPs=causal_peticionHome.findCausalesDePsOrderDes(key.correlativo);
							Causal_peticionLocal causal_peticionLocalBA=null;
							
							
							//Se recupera la causal petición del ps de LB
							for(Iterator iterator2=listaCausalPs.iterator();iterator2.hasNext();)
							{
								causal_peticionLocalBA=(Causal_peticionLocal) iterator2.next();
								break;
							}

							//Se asigna el mismo causal petición de LB a los
							// ps's de Asistencia
							Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(estado_ps_peticionLocalLB.getCod_causal());
							Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
							Estado_psKey estado_psKey=new Estado_psKey(new Long(estado_ps_peticionLocalLB.getCod_estado_cierre().intValue()));
							Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
							UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey((UsuarioKey)(causal_peticionLocalBA.getUsuario().getPrimaryKey()));
							long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
							Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocalAS,usuarioLocal);
							causal_peticionLocal.setFecha_inicio(causal_peticionLocalBA.getFecha_inicio());
							causal_peticionLocal.setFecha_termino(causal_peticionLocalBA.getFecha_termino());
							causal_peticionLocal.setNovedad(estado_ps_peticionLocalLB.getNovedad());
							causal_peticionLocal.setCod_actividad(estado_ps_peticionLocalLB.getCod_actividad());
							
						}
					}
				}
			} //fin raro
		}
		
		catch(Exception exception)
		{
			log.error("Exception",exception);
		
		}
		
	}
	/**
	 * Método para req de Asistencia, 33626
	 * 
	 * @param PsPet, es el productoServicioPetición del cual se va a sacar el estado y la causal que se le propaga al ps de asistencia.
	 * @param tipo, dice si es Ba o LB lo que se quiere propagar
	 * @return
	 */
	public Estado_ps_peticionLocal buscarQuiebrePSBALBParaAsistencia (Producto_servicio_peticionLocal PsPet, String tipo){
		Collection listaEstadoPsPet=PsPet.getEstado_ps_peticion();
		
		Iterator itListaEstadoPsPet=null;
		Estado_ps_peticionLocal estado_ps_peticionLocalGral=null;
		
		if(tipo.equals("BA")){
			for(itListaEstadoPsPet=listaEstadoPsPet.iterator();itListaEstadoPsPet.hasNext();){
				
				Estado_ps_peticionLocal estadoPsPet=(Estado_ps_peticionLocal)itListaEstadoPsPet.next();
				Producto_servicioLocal el_ps=(Producto_servicioLocal)estadoPsPet.getProducto_servicio();
				Familia_producto_servicioLocal fam_ps=el_ps.getFamilia_producto_servicio();
				Familia_producto_servicioKey fam_key=(Familia_producto_servicioKey)fam_ps.getPrimaryKey();

				if(fam_key.faps_id.equals(new Long(familiaPcBA))){
					estado_ps_peticionLocalGral=estadoPsPet;					
				}else{
					continue;
				}
			
			}
		}
		
		if(tipo.equals("LB")){
			for(itListaEstadoPsPet=listaEstadoPsPet.iterator();itListaEstadoPsPet.hasNext();){
				
				Estado_ps_peticionLocal estadoPsPet=(Estado_ps_peticionLocal)itListaEstadoPsPet.next();
				Producto_servicioLocal el_ps=(Producto_servicioLocal)estadoPsPet.getProducto_servicio();
				Familia_producto_servicioLocal fam_ps=el_ps.getFamilia_producto_servicio();
				Familia_producto_servicioKey fam_key=(Familia_producto_servicioKey)fam_ps.getPrimaryKey();

				if(fam_key.faps_id.equals(new Long(familiaPcLinea))){
					estado_ps_peticionLocalGral=estadoPsPet;					
				}else{
					continue;
				}
			
			}
		}
		
		return estado_ps_peticionLocalGral;
	}

	public ArrayList getListaAsignaciones(Long nroPeticion) {
		ArrayList listaAsig = new ArrayList();
		try {
			Tecnico_peticionLocalHome cpHome = (Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			Tecnico_peticionLocal cpLocal = null;
			Collection c = cpHome.finderByPeticionyAp( nroPeticion,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ) );
			//log.debug("Encontrado Asignacion Peticion [" + nroPeticion + "," + c.size() + "]");
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				cpLocal = (Tecnico_peticionLocal) it.next();
				if ( cpLocal.getFecha() != null) {
					InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();
					infoAgenda.setFechaCompromiso(  new Date(cpLocal.getFecha().getTime()) );
					RangoLocal rgLocal = cpLocal.getRango();
					infoAgenda.setNombreRango( rgLocal.getNombre_rango() );
					infoAgenda.setHoraDesde( rgLocal.getHora_desde() );
					infoAgenda.setHoraHasta( rgLocal.getHora_hasta() );
					
					TecnicoLocal tecLocal = cpLocal.getTecnico();
					TecnicoKey tecKey = (TecnicoKey ) tecLocal.getPrimaryKey();
					String nombreTecnico = tecLocal.getNombre() + " " + tecLocal.getApellido();
					infoAgenda.setIdTecnicoInicial( tecKey.cod_tecnico );
					infoAgenda.setNombreTecnicoInicial( nombreTecnico );

					// CR21938 - ana santos - inicio
					infoAgenda.setUsuario( cpLocal.getNom_usua_logueado());
					infoAgenda.setFechaAsignacion( cpLocal.getFecha_agendamiento());
					// CR21938 - ana santos - fin

					if ( cpLocal.getEstado() != null && cpLocal.getEstado().intValue()==1 )
						infoAgenda.setDescripcionEstado( "Activo");
					else
						infoAgenda.setDescripcionEstado( "Reagendado");

					Causa_reagendamientoLocal crLocal = cpLocal.getCausa_reagendamiento();
					if ( crLocal != null )
						infoAgenda.setDescripcionCausa( crLocal.getCare_descripcion() );

					listaAsig.add( infoAgenda );
				}
			}
		} catch (NamingException e) {
		} catch (FinderException e) {
		}
			
			
		return listaAsig;
	}

	
	public ArrayList listaDePsDelaActividadEstadoFinal(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx
	{
		ArrayList arrayList=new ArrayList();
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			
			if(peticionHome==null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(peticion_flujoHome==null)
				peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey localKey=(Producto_servicio_peticionKey)local.getPrimaryKey();
				Producto_servicioKey psKey=(Producto_servicioKey) local.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey opComKey=(Operacion_comercialKey) local.getOperacion_comercial().getPrimaryKey();
				boolean estaOk=true;
				try
				{
					Collection lista=peticion_flujoHome.findByIdActividadFPetPsOC(actividad_flujo_id,nroPeticion,psKey.ps_id,opComKey.opco_id);
					if(lista.size()==0)
						continue;
					Collection listaEstadoPs=local.getEstado_ps_peticion();
					if(listaEstadoPs.size()>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPs.iterator().next();
						Collection listaCausalPeticion=estado_ps_peticionLocal.getCausal_peticion();
						if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==ComunInterfaces.estadoCierreError)
							estaOk=false;
						else
							estaOk=true;
					}
					else
						estaOk=true;
					PsVsOcVO psVsOcVO=new PsVsOcVO();
					psVsOcVO.setPsId(psKey.ps_id);
					psVsOcVO.setOpComId(opComKey.opco_id);
					psVsOcVO.setOpComRevId(local.getOperacion_comercial().getOpco_rev());
					psVsOcVO.setOpComTipo( local.getOperacion_comercial().getOpco_tipo());
					Familia_producto_servicioKey fPsKey=(Familia_producto_servicioKey) local.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					psVsOcVO.setFaPsId(fPsKey.faps_id);
					psVsOcVO.setCorrelativo(localKey.correlativo);
					psVsOcVO.setOk(estaOk);
					arrayList.add(psVsOcVO);
				}
				catch (FinderException e)
				{
					log.error("FinderException",e);
					throw new ATiempoAppEx(e.getMessage());
				}
			}
			return arrayList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	/*public void insertarQuiebresFinalesPeticionesVuelo (Long nroPeticion){
		log.debug("Entro a insertar quiebres finales por productos en vuelo");
		
		try{		
			Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome) HomeFactory.getHome(Producto_servicio_en_vueloLocalHome.JNDI_NAME);
			Producto_servicio_peticionLocalHome productoServicioLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
			Fecha fecha=new Fecha();
			Long causalBA = new Long(766);
			Long causalTV = new Long(767);
			
			DBManager manager=new DBManager ();
			manager.setDataSource (DBManager.JDBC_VPISTBBA);
			
			Collection peticionesEnVuelo = productoServicioEnVueloLocalHome.findByPeti_Numero(nroPeticion);
			if (!peticionesEnVuelo.isEmpty()){
				Collection peticionesLocales = productoServicioLocalHome.findAllByPetiNumero(nroPeticion);
				
				if (!peticionesLocales.isEmpty()){
					for(Iterator iterator=peticionesLocales.iterator();iterator.hasNext();){
						Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
					
						Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(causalBA);
						Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
						
						long correlativo=manager.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
						Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
						estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
						estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
						estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						estado_ps_peticionLocal.setCod_actividad(new Long(ComunInterfaces.idActividadControlInstalacion));

						Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
						Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
						UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
						long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
						Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
								
						causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
						causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
						causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						causal_peticionLocal.setCod_actividad(new Long(ComunInterfaces.idActividadControlInstalacion));
					}
				}
			}
			
			
		}catch(NamingException ex){
				log.error("Error al invocar JDNI en insertarQuiebresFinalesPeticionesVuelo");
				ex.printStackTrace();
		}catch(FinderException ex){
				log.error("Error al buscar en insertarQuiebresFinalesPeticionesVuelo");
				ex.printStackTrace();
		}catch(CreateException ex){
				log.error("Error al insertar en insertarQuiebresFinalesPeticionesVuelo");
				ex.printStackTrace();
		}
	}*/
	
	public void insertarQuiebresFinalesPeticionesVuelo (Long nroPeticion){
		log.debug("Entro a insertar quiebres finales por productos en vuelo");
		
		try{		
			Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome) HomeFactory.getHome(Producto_servicio_en_vueloLocalHome.JNDI_NAME);
			Producto_servicio_peticionLocalHome productoServicioLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
			Fecha fecha=new Fecha();
			Long causalBA = new Long(767);
			//Long causalTV = new Long(767);
			
			DBManager manager=new DBManager ();
			manager.setDataSource (DBManager.JDBC_VPISTBBA);
			
			Collection peticionesEnVuelo = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.BA_EN_VUELO);
			
			if (!peticionesEnVuelo.isEmpty()){
				Collection peticionesLocales = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaBandaAncha), nroPeticion);
				
				Collection peticionesLocalesAux = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaPcBA), nroPeticion);
				for(Iterator iterator=peticionesLocalesAux.iterator();iterator.hasNext();){
					peticionesLocales.add(iterator.next());
				}
				
				/*peticionesLocalesAux = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaLinea), nroPeticion);
				for(Iterator iterator=peticionesLocalesAux.iterator();iterator.hasNext();){
					peticionesLocales.add(iterator.next());
				}
				
				peticionesLocalesAux = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaPcLinea), nroPeticion);
				for(Iterator iterator=peticionesLocalesAux.iterator();iterator.hasNext();){
					peticionesLocales.add(iterator.next());
				}*/
				
				if (!peticionesLocales.isEmpty()){
					for(Iterator iterator=peticionesLocales.iterator();iterator.hasNext();){
						Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
					
						Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(causalBA);
						Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
						
						long correlativo=manager.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
						Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
						estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
						estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
						estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						estado_ps_peticionLocal.setCod_actividad(new Long(ComunInterfaces.idActividadControlInstalacion));

						Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
						Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
						UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
						long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
						Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
								
						causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
						causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
						causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						causal_peticionLocal.setCod_actividad(new Long(ComunInterfaces.idActividadControlInstalacion));
					}
				}
			}
			
			peticionesEnVuelo  = null;
			peticionesEnVuelo = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_ADICIONAL_EN_VUELO);
			
			Collection peticionesEnVueloAux =  productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_ADICIONAL_EN_VUELO_S);
			for(Iterator iterator=peticionesEnVueloAux.iterator();iterator.hasNext();){
				peticionesEnVuelo.add(iterator.next());
			}

			
			if (!peticionesEnVuelo.isEmpty()){
				Collection peticionesLocales = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaTematicoTV), nroPeticion);
				
				if (!peticionesLocales.isEmpty()){
					for(Iterator iterator=peticionesLocales.iterator();iterator.hasNext();){
						Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
					
						Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(causalBA);
						Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
						
						long correlativo=manager.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
						Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
						estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
						estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
						estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						estado_ps_peticionLocal.setCod_actividad(new Long(ComunInterfaces.idActividadControlInstalacion));

						Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
						Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
						UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
						long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
						Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
								
						causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
						causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
						causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						causal_peticionLocal.setCod_actividad(new Long(ComunInterfaces.idActividadControlInstalacion));
					}
				}
			}
			
			peticionesEnVuelo  = null;
			peticionesEnVueloAux = null;
			peticionesEnVuelo = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_BASICO_EN_VUELO);
			
			peticionesEnVueloAux = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_BASICO_EN_VUELO_S);
			for(Iterator iterator=peticionesEnVueloAux.iterator();iterator.hasNext();){
				peticionesEnVuelo.add(iterator.next());
			}
			
			if (!peticionesEnVuelo.isEmpty()){
				Collection peticionesLocales = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaPaqueteTV), nroPeticion);
				Collection peticionesLocalesGeneral = new ArrayList();
				
				Collection peticionesLocalesAux = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaTematicoTV), nroPeticion);
				for(Iterator iterator=peticionesLocalesAux.iterator();iterator.hasNext();){
					peticionesLocalesGeneral.add(iterator.next());
				}
				
				peticionesLocalesAux = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaDecoPVRTV), nroPeticion);
				for(Iterator iterator=peticionesLocalesAux.iterator();iterator.hasNext();){
					peticionesLocalesGeneral.add(iterator.next());
				}
				
				peticionesLocalesAux = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaDecoTV), nroPeticion);
				for(Iterator iterator=peticionesLocalesAux.iterator();iterator.hasNext();){
					peticionesLocalesGeneral.add(iterator.next());
				}
				
				peticionesLocalesAux = productoServicioLocalHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaPcTV), nroPeticion);
				for(Iterator iterator=peticionesLocalesAux.iterator();iterator.hasNext();){
					peticionesLocalesGeneral.add(iterator.next());
				}
				
				for(Iterator iterator=peticionesLocales.iterator();iterator.hasNext();){
					peticionesLocalesGeneral.add(iterator.next());
				}
				
				if (!peticionesLocalesGeneral.isEmpty()){
					for(Iterator iterator=peticionesLocalesGeneral.iterator();iterator.hasNext();){
						Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
					
						Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(causalBA);
						Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
						
						long correlativo=manager.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
						Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
						estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
						estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
						estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						estado_ps_peticionLocal.setCod_actividad(new Long(ComunInterfaces.idActividadControlInstalacion));

						Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
						Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
						UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
						long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
						Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
								
						causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
						causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
						causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						causal_peticionLocal.setCod_actividad(new Long(ComunInterfaces.idActividadControlInstalacion));
					}
				}
			}
			
		}catch(NamingException ex){
			log.error("Error al invocar JDNI en insertarQuiebresFinalesPeticionesVuelo");
			ex.printStackTrace();
		}catch(FinderException ex){
			log.error("Error al buscar en insertarQuiebresFinalesPeticionesVuelo");
			ex.printStackTrace();
		}catch(CreateException ex){
			log.error("Error al insertar en insertarQuiebresFinalesPeticionesVuelo");
			ex.printStackTrace();
		}catch(Exception ex){
			log.error("Error en insertarQuiebresFinalesPeticionesVuelo");
			ex.printStackTrace();
		}
	}
	
	public ArrayList listaDePsDelaActividadEstadoOKFinal(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx
	{
		log.debug("lista de Ps con estado OK Final");
		ArrayList arrayList=new ArrayList();
		try
		{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			
			if(peticionHome==null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(peticion_flujoHome==null)
				peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey localKey=(Producto_servicio_peticionKey)local.getPrimaryKey();
				Producto_servicioKey psKey=(Producto_servicioKey) local.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey opComKey=(Operacion_comercialKey) local.getOperacion_comercial().getPrimaryKey();
				
//				PS de velocidad adicional
				Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
				String psSVABA[] = propertiesLocal.getValor().split(",");
				boolean esSVABA = false;
				
				boolean estaOk=true;
				try
				{
					//03 06 2009
					log.debug("actividad_flujo_id = "+actividad_flujo_id);
					log.debug("nroPeticion = "+nroPeticion);
					log.debug("psKey.ps_id = "+psKey.ps_id);
					log.debug("opComKey.opco_id = "+opComKey.opco_id);
					
					for(int i = 0; i<psSVABA.length;i++){
						if(new Long(psSVABA[i]).equals( psKey.ps_id))
							esSVABA = true;
					}
					Collection lista=peticion_flujoHome.findByIdActividadFPetPsOC(actividad_flujo_id,nroPeticion,psKey.ps_id,opComKey.opco_id);
					if(lista.size()==0 && !esSVABA)
						continue;
					
					log.debug("lista.size() > 0");
					Collection listaEstadoPs=local.getEstado_ps_peticion();
					if(listaEstadoPs.size()>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPs.iterator().next();
						Collection listaCausalPeticion=estado_ps_peticionLocal.getCausal_peticion();
						if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==ComunInterfaces.estadoCierreError)
							continue;
						else
							estaOk=true;
					}
					else
						estaOk=true;
					PsVsOcVO psVsOcVO=new PsVsOcVO();
					psVsOcVO.setPsId(psKey.ps_id);
					psVsOcVO.setOpComId(opComKey.opco_id);
					psVsOcVO.setOpComRevId(local.getOperacion_comercial().getOpco_rev());
					psVsOcVO.setOpComTipo( local.getOperacion_comercial().getOpco_tipo());
					Familia_producto_servicioKey fPsKey=(Familia_producto_servicioKey) local.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					psVsOcVO.setFaPsId(fPsKey.faps_id);
					psVsOcVO.setCorrelativo(localKey.correlativo);
					psVsOcVO.setOk(estaOk);
					arrayList.add(psVsOcVO);
				}
				catch (FinderException e)
				{
					log.debug("catch**");
				}
			}
			log.debug("arrayList "+ arrayList);
			return arrayList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
		
	public ArrayList listaDePsDelaActividadConEstadoOKEnAct(Long nroPeticion,Integer actividad_flujo_id,Long codigoActividad) throws ATiempoAppEx
	{
		ArrayList arrayList=new ArrayList();
		try
		{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
			if(peticionHome==null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(peticion_flujoHome==null)
				peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey localKey=(Producto_servicio_peticionKey)local.getPrimaryKey();
				Producto_servicioKey psKey=(Producto_servicioKey) local.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey opComKey=(Operacion_comercialKey) local.getOperacion_comercial().getPrimaryKey();
				try
				{
					Collection  lista=peticion_flujoHome.findByIdActividadFPetPsOC(actividad_flujo_id,nroPeticion,psKey.ps_id,opComKey.opco_id);
					if(lista.size()==0)
						continue;
					boolean estaOk=false;
					Collection listaEstadoPs=local.getEstado_ps_peticion();
					if(listaEstadoPs.size()>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPs.iterator().next();
						Estado_ps_peticionKey estado_ps_peticionKey=(Estado_ps_peticionKey) estado_ps_peticionLocal.getPrimaryKey();
						Collection listadoCausas=causal_peticionHome.findCausalesDePsOrdenDesByAct(estado_ps_peticionKey.correlativo,codigoActividad);
						if(listadoCausas.size()==0)
							estaOk=true;
						else
						{
							for(Iterator iterator2=listadoCausas.iterator();iterator2.hasNext();)
							{
								Causal_peticionLocal causal_peticionLocal=(Causal_peticionLocal) iterator2.next();
								Estado_psKey estado_psKey=(Estado_psKey) causal_peticionLocal.getEstado_ps().getPrimaryKey();
								if(estado_psKey.cod_estado_cierre.intValue()==ComunInterfaces.estadoCierreError)
									estaOk=false;
								else
									estaOk=true;
								break;
							}
						}
					}
					else
						estaOk=true;			
					if(estaOk)
					{
						//log.debug("De la Peticion:"+nroPeticion+" el ps:"+psKey.ps_id+"con op:"+opComKey.opco_id+" esta OK en la actividad "+actividad_flujo_id);
						PsVsOcVO psVsOcVO=new PsVsOcVO();
						psVsOcVO.setPsId(psKey.ps_id);
						psVsOcVO.setOpComId(opComKey.opco_id);
						psVsOcVO.setOpComRevId(local.getOperacion_comercial().getOpco_rev());
						psVsOcVO.setOpComTipo( local.getOperacion_comercial().getOpco_tipo());
						Familia_producto_servicioKey fPsKey=(Familia_producto_servicioKey) local.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						psVsOcVO.setFaPsId(fPsKey.faps_id);
						psVsOcVO.setCorrelativo(localKey.correlativo);
						psVsOcVO.setOk(estaOk);
						arrayList.add(psVsOcVO);
					}
				}
				catch (FinderException e)
				{
					log.debug("FinderException",e);
				}
			}
			return arrayList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	public ArrayList listaDePsDePeticion(Long nroPeticion) throws ATiempoAppEx
	{
		ArrayList arrayList=new ArrayList();
		try
		{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		
			if(peticionHome==null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			log.info("Peticion Buscar:" + nroPeticion);
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey localKey=(Producto_servicio_peticionKey)local.getPrimaryKey();
				Producto_servicioKey psKey=(Producto_servicioKey) local.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey opComKey=(Operacion_comercialKey) local.getOperacion_comercial().getPrimaryKey();
				boolean estaOk=false;
				Collection listaEstadoPs=local.getEstado_ps_peticion();
				if(listaEstadoPs.size()>0)
				{
					Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPs.iterator().next();
					Collection listaCausalPeticion=estado_ps_peticionLocal.getCausal_peticion();
					if(estado_ps_peticionLocal.getCod_estado_cierre() != null && estado_ps_peticionLocal.getCod_estado_cierre().intValue()==ComunInterfaces.estadoCierreError)
						estaOk=false;
					else
						estaOk=true;
				}
				else
					estaOk=true;
				
				PsVsOcVO psVsOcVO=new PsVsOcVO();
				psVsOcVO.setPsId(psKey.ps_id);
				psVsOcVO.setOpComId(opComKey.opco_id);
				psVsOcVO.setOpComRevId(local.getOperacion_comercial().getOpco_rev());
				psVsOcVO.setOpComTipo( local.getOperacion_comercial().getOpco_tipo());
				Familia_producto_servicioKey fPsKey=(Familia_producto_servicioKey) local.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				psVsOcVO.setFaPsId(fPsKey.faps_id);
				psVsOcVO.setCorrelativo(localKey.correlativo);
				psVsOcVO.setOk(estaOk);
				arrayList.add(psVsOcVO);
			}
			return arrayList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("PeticionesServicesBean.listaDePsDePeticion: ",e);
			log.error("PeticionesServicesBean.listaDePsDePeticion: ",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	public void propagaCausasPeticion(Long peticion,Long codActividadActual,Integer idActividadFlujoActual,Long idUsuario) throws ATiempoAppEx
	{
		try
		{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			
			log.debug("ENTRO A PROPAGAR CAUSAS PARA PETICION:"+peticion+" - "+codActividadActual);
			if(peticionHome==null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(peticion_flujoHome==null)
				peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(peticion));
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey producto_servicio_peticionKey=(Producto_servicio_peticionKey) local.getPrimaryKey();
				Producto_servicioKey psKey=(Producto_servicioKey) local.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey opComKey=(Operacion_comercialKey) local.getOperacion_comercial().getPrimaryKey();
				try
				{
					Collection lista1=peticion_flujoHome.findByIdActividadFPetPsOC(idActividadFlujoActual,peticion,psKey.ps_id,opComKey.opco_id);
					if(lista1.size()==0 ) 
						continue;
					Collection listaCausa=local.getEstado_ps_peticion();
					if(listaCausa.size()>0)
					{
						//El ps tiene Causa o Quiebre
						//Solo voy a propagar los quiebres.
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaCausa.iterator().next();
						Estado_ps_peticionKey estado_ps_peticionKey=(Estado_ps_peticionKey) estado_ps_peticionLocal.getPrimaryKey();
						
						if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError)//Tengo quiebre
						{	
							Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(estado_ps_peticionLocal.getCod_causal());
							Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
							Estado_psKey estado_psKey=new Estado_psKey(new Long(estadoCierreError));
							Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
							UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(idUsuario));
							long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
							
							//primero saco la fecha de la ultima
							Collection listaCausalPeticion=causal_peticionHome.findCausalesDePsOrderDes(estado_ps_peticionKey.correlativo);
							Causal_peticionLocal ultimaCausa=(Causal_peticionLocal) listaCausalPeticion.iterator().next();
							String fechaIni=ultimaCausa.getFecha_inicio();
							String fechaFin=ultimaCausa.getFecha_termino();
							
							Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
							
							causal_peticionLocal.setNovedad(estado_ps_peticionLocal.getNovedad());
							causal_peticionLocal.setCod_actividad(codActividadActual);
							
							causal_peticionLocal.setFecha_inicio(fechaIni);
							causal_peticionLocal.setFecha_termino(fechaFin);
							
														
							log.debug("Propagado Causas Peticion:" + peticion);
						}
					}
					else
						log.debug("Nada que propagar Peticion:" + peticion);
				}
				catch (FinderException e)
				{
					log.debug("FinderException",e);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	public int tipoTrasladoSoloBa(Long nroPeticion) throws ATiempoAppEx
	{
		PeticionLocalHome home;
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Traslado_solobaLocalHome traslado_solobaHome=(Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
			
			home=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=home.findByPrimaryKey(new PeticionKey(nroPeticion));
			int traslado=0;
			boolean soloBA=true;
			Agrupacion_atisLocal agrupacion_atisLocal=null;
			String opco_tipo = null;
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Familia_producto_servicioKey famKey=(Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				int idFam=famKey.faps_id.intValue();
				//REQ BA NAKED adicion familia pc y ps naked
				if((idFam!=familiaBandaAncha && idFam!=familiaPcBA)||(idFam!=familiaBandaAnchaNaked && idFam!=familiaPcBANaked))
				{
					soloBA=false;
					break;
				}
				if(operacion_comercialLocal.getOpco_tras()!=null)
				{
					if(operacion_comercialLocal.getOpco_tras().equals("T"))
					{
						opco_tipo = operacion_comercialLocal.getOpco_tipo();
						if(opco_tipo!=null)
						{
							if(opco_tipo.equals("ACP") || opco_tipo.equals("A"))
								traslado=1;
							else
								traslado=2;
						}
					}
				}
				agrupacion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub();
			}
			if(!soloBA)
				return 0;
			if(traslado==1)
			{
				log.info("Voy a ingresar un traslado Alta");
				String telefonoAnterior=null;
				for(Iterator iterator=agrupacion_atisLocal.getSubpeticion_atis().iterator();iterator.hasNext();)
				{
					Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
					Collection listaCaracteristicas=subpeticion_atisLocal.getSubpeticion_caracteristicas();
					for(Iterator iterator2=listaCaracteristicas.iterator();iterator2.hasNext();)
					{
						Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal=(Subpeticion_caracteristicasLocal) iterator2.next();
						Subpeticion_caracteristicasKey key=(Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
						if(key.cod_crc_cd.longValue()!=new Long(VpistbbaConfig.getVariable("NUMTELEF")).longValue())
							continue;
						telefonoAnterior=subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
					}
				}
				if(telefonoAnterior==null)
					throw new ATiempoAppEx("No se puede encontrar el telefono anterior en la peticion de alta traslado solo ba para la peticion "+nroPeticion);
				
				try
				{
					Traslado_solobaLocal traslado_solobaLocal=traslado_solobaHome.findByNumIdNuBaja(telefonoAnterior);
					log.info("Lo Actualizo siempre y cuando no este ingresando el mismo de nuevo");
					if(traslado_solobaLocal.getPeti_alta()!=null || traslado_solobaLocal.getLinea_anterior()!=null)
						return 3;
					traslado_solobaLocal.setPeti_alta(nroPeticion);
					traslado_solobaLocal.setLinea_anterior(telefonoAnterior);
				}
				catch(FinderException fe)
				{
					log.info("Lo ingreso");
					long id_trasoba=dbSeq.seqNextValLong ("CORRELATIVO_TRASLADO_SOBA");
					Traslado_solobaLocal traslado_solobaLocal=traslado_solobaHome.create(new Long(id_trasoba));
					traslado_solobaLocal.setPeti_alta(nroPeticion);
					traslado_solobaLocal.setLinea_anterior(telefonoAnterior);
				}
			}
			else if(traslado==2)
			{
				log.info("Voy a ingresar un traslado Baja");
				String numIdNuBaja=agrupacion_atisLocal.getNum_ide_nu();
				try
				{
					Traslado_solobaLocal traslado_solobaLocal=traslado_solobaHome.findByLineaAnterior(numIdNuBaja);
					log.info("Lo Actualizo siempre y cuando no este ingresando el mismo de nuevo");
					if(traslado_solobaLocal.getPeti_baja()!=null || traslado_solobaLocal.getNum_ide_nu_baja()!=null)
						return 3;
					traslado_solobaLocal.setPeti_baja(nroPeticion);
					traslado_solobaLocal.setNum_ide_nu_baja(numIdNuBaja);
				}
				catch(FinderException fe)
				{
					log.info("Lo ingreso");
					long id_trasoba=dbSeq.seqNextValLong ("CORRELATIVO_TRASLADO_SOBA");
					Traslado_solobaLocal traslado_solobaLocal=traslado_solobaHome.create(new Long(id_trasoba));
					traslado_solobaLocal.setPeti_baja(nroPeticion);
					traslado_solobaLocal.setNum_ide_nu_baja(numIdNuBaja);
					log.info("Lo ingresé con num ide nu baja:"+numIdNuBaja);
				}
			}
			return traslado;
		}
		catch (NamingException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		} catch (FinderException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS,e);
		}
	}
	
	
	public Integer estaCerradaElAlta(Long nroPeticionBaja) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			Traslado_solobaLocalHome traslado_solobaLocalHome=(Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
			PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticionBaja));
//			String numIdeNuBaja=local.obtenerCPINAGrupacionOriginal();
			String numIdeNuBaja=local.getIdentificadorOriLinea();
			log.info("Num id Nu Baja:"+numIdeNuBaja);
			Traslado_solobaLocal traslado_solobaLocal=null;
			try
			{
				 traslado_solobaLocal=traslado_solobaLocalHome.findByNumIdNuBaja(numIdeNuBaja);
			}
			catch(FinderException finderException)
			{
				finderException.printStackTrace();
				log.error(finderException);
				throw new ATiempoAppEx("Error tiene que estar ingresado el traslado para la peticion de baja "+nroPeticionBaja);
			}
			if(traslado_solobaLocal.getCierre_alta()!=null)
			{
				if(traslado_solobaLocal.getCierre_alta().equals("S"))
				{
					//verificar si el alta cerro ok o con quiebre completo
					PeticionLocal alta=peticionHome.findByPrimaryKey(new PeticionKey(traslado_solobaLocal.getPeti_alta()));
					boolean tieneQuiebre=false;
					for(Iterator iterator=alta.getProducto_servicio_peticion().iterator();iterator.hasNext();)
					{
						Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
						Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
						Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
						Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
						int idFamilia=familia_producto_servicioKey.faps_id.intValue();
						//REQ BA NAKED adicion familia pc naked
						if(idFamilia!=familiaPcBA || idFamilia!=familiaPcBANaked)
							continue;
						Collection estadoPsPeticion=producto_servicio_peticionLocal.getEstado_ps_peticion();
						if(estadoPsPeticion.size()>0)
						{
							Estado_ps_peticionLocal primero=(Estado_ps_peticionLocal) estadoPsPeticion.iterator().next();
							if(primero.getCod_estado_cierre().intValue()==estadoCierreError)
								tieneQuiebre=true;
						}
					}
					if(!tieneQuiebre)
						return new Integer(1);
					return new Integer(2);
				}
			}
			return new Integer(3);
		}
		catch (NamingException e)
		{
			log.error(e);
			return new Integer(3);
		} catch (FinderException e)
		{
			log.error(e);
			return new Integer(3);
		}
	}

	public int indicadorLecturaContador(ActividadDTO actividadDTO,Long nroPet)
	{
		try
		{
			Producto_servicio_peticionLocalHome home=(Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection lista=home.findByIdFamiliaPs(new Long(VpistbbaConfig.getVariable("FamiliaPcLinea")),nroPet);
			log.info("Lista Ps Tipo Pc para contador:"+lista.size());
			for(Iterator iterator=lista.iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
				ContadoresLocalHome contadoresLocalHome=(ContadoresLocalHome) HomeFactory.getHome(ContadoresLocalHome.JNDI_NAME);
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				boolean llamaAlaActividad=pasaPSyOcXActividad(nroPet,producto_servicioKey.ps_id,operacion_comercialKey.opco_id,actividadDTO.getIdActFlujo());
				if(!llamaAlaActividad)
					continue;
				ContadoresLocal contadoresLocal=contadoresLocalHome.findByPrimaryKey(new ContadoresKey(operacion_comercialKey));
				if(contadoresLocal.getDesde().shortValue()==1 && contadoresLocal.getHasta().shortValue()==1)
					return 3;
				else if(contadoresLocal.getDesde().shortValue()==0 && contadoresLocal.getHasta().shortValue()==0)
					return 0;
				else if(contadoresLocal.getHasta().shortValue()==1)
					return 2;
				else if(contadoresLocal.getDesde().shortValue()==1)
					return 1;
			}
			return 0;
		}
		catch(Exception e)
		{
			return 0;
		}
	
	}
	
	public String obtenerCuentaCorreoUsuarioAcceso(Long idPeticion) throws ATiempoAppEx
	{
		try
		{	
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			String retorno="";
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionKey key=new PeticionKey(idPeticion);
			PeticionLocal local=peticionHome.findByPrimaryKey(key);
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				boolean esta=false;
				Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator.next();
				for(Iterator iterator2=local2.getFk_01_subp_atis().getSubpeticion_caracteristicas().iterator();iterator2.hasNext();)
				{
					Subpeticion_caracteristicasLocal local3=(Subpeticion_caracteristicasLocal) iterator2.next();
					Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) local3.getPrimaryKey();
					Long codUsuaAcc=new Long (VpistbbaConfig.getVariable("USUACC"));
					if (spk.cod_crc_cd.longValue()== codUsuaAcc.longValue())
					{
						log.debug("Informacion : Se obtuvo Correo Usuario Acceso " + local3.getVal_ral_crc_cd());
						retorno=local3.getVal_ral_crc_cd();
						esta=true;
						break;
					}
				}
				if(esta)
					break;
			}
			return retorno;
		}
		catch(Exception e)
		{
			log.error(e);
			return "";
		}
	}

	public ArrayList estaOKPCTV(Long nroPeticion) throws ATiempoAppEx 
	{
		try
		{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			ArrayList retorno=new ArrayList(3);
			int cantidadPcTvOk=0;
			int cantidadDecoTvOk=0;
			int cantidadDecoTvNoOk=0;
			int cantidadPcTv=0;
			int cantidadDecoTv=0;
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				int size = producto_servicio_peticionLocal.getEstado_ps_peticion().size();
				if(familia_producto_servicioKey.faps_id.intValue()==familiaPcTV)
				{
					cantidadPcTv++;
					if(size>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
						if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreOk || estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreNovedad)
							cantidadPcTvOk++;
					}
					else if(size==0)
						cantidadPcTvOk++;
				}
				//TODO PVR SE AGREGO FAMILIA DUDA ACA
				else if(familia_producto_servicioKey.faps_id.intValue()==familiaDecoTV ||familia_producto_servicioKey.faps_id.intValue()==familiaDecoPVRTV
						||familia_producto_servicioKey.faps_id.intValue()==familiaDecoHDTV)
				{
					cantidadDecoTv++;
					if(size>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
						if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreOk || estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreNovedad)
							cantidadDecoTvOk++;
						else if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError)
							cantidadDecoTvNoOk++;	
					}
					else if(size==0)
						cantidadDecoTvOk++;
				}
			}
			retorno.add(new Integer(cantidadPcTvOk));
			retorno.add(new Integer(cantidadDecoTvOk));
			retorno.add(new Integer(cantidadDecoTvNoOk));
			retorno.add(new Integer(cantidadPcTv));
			retorno.add(new Integer(cantidadDecoTv));
			return retorno;
		}
		catch(Exception e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}
	
	public void insertarCausalesPeticion(Long peticion, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			Fecha fecha=new Fecha();
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
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(peticion));
			PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
			log.debug("Estoy insertando los causales de CNA para la peticion:"+peticionKey.peti_numero);
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
			
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
		catch(Exception e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}	
	}

	public boolean pasaXActividad(Long nroPeticion, Integer actividad_flujo_id) throws ATiempoAppEx {
		Collection lista;
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			
			if(peticion_flujoHome==null)
				peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			lista =	peticion_flujoHome.findByIdActividadAndPeticion(actividad_flujo_id,nroPeticion);
			if(lista!=null && lista.size()>0)
				return true;
			return false;
		}
		catch (FinderException e)
		{
			return false;
		} catch (NamingException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
	}

	public void grabarFechasCierrePorPs(ArrayList fechasCierrexPs,Long idPeticion) throws ATiempoAppEx
	{
		try
		{
			Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			for(Iterator iterator=fechasCierrexPs.iterator();iterator.hasNext();)
			{
				PsVsFechaCierreDTO dto=(PsVsFechaCierreDTO) iterator.next();
				PeticionKey peticionKey=new PeticionKey(idPeticion);
				Producto_servicio_peticionKey key=new Producto_servicio_peticionKey(dto.getCorrelativoPs(),peticionKey);
				Producto_servicio_peticionLocal local=psPetHome.findByPrimaryKey(key);
				local.setPspe_fecha_fin(new Fecha(dto.getFecha(),"dd/MM/yyyy HH:mm").getTimestamp());			
			}
		}
		catch(FinderException fe)
		{
			log.debug("FinderException",fe);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,fe);
		}
		catch (FechaException ef)
		{
			log.debug("FechaException",ef);
			throw new ATiempoAppEx(ATiempoAppEx.FECHAEXCEPTION,ef);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
	}

	public void setearFechaBajaPs(Long nroPeticion) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			Fecha fechaFin=new Fecha();
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal ps=(Producto_servicio_peticionLocal) iterator.next();
				log.debug(ps.toPsVsOc().toString()+fechaFin.getFechaconFormato());
				ps.setPspe_fecha_fin(fechaFin.getTimestamp());
			}
		}
		catch(NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			log.debug("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
	}

	public ArrayList getPsMasInfoGesQuiebre(Long idPeticion,Integer idActFlujo) throws ATiempoAppEx
	{
		try
		{	
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			ArrayList arrayList=new ArrayList();
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(idPeticion));
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey producto_servicio_peticionKey=(Producto_servicio_peticionKey) producto_servicio_peticionLocal.getPrimaryKey();
				
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
	
				ProductoDTO objPS = new ProductoDTO();
				Producto_servicioKey key=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
				objPS.setPetiNumero(producto_servicio_peticionKey.fk_psp_pet_peti_numero);
				objPS.setCorrelativo(producto_servicio_peticionKey.correlativo);
				objPS.setId(key.ps_id);
				objPS.setIdProducto(key.ps_id);
				objPS.setNombreProducto(producto_servicioLocal.getPs_nombre());
				objPS.setObservacion(producto_servicioLocal.getPs_observacion());
				objPS.setPcoObligatorio(new Byte(producto_servicioLocal.getPs_pco_obligatorio().byteValue()));
				objPS.setIdEmpresa(producto_servicioLocal.getEmpr_id());
				objPS.setIdAmbito(producto_servicioLocal.getAmbi_id());
				objPS.setIdGrupoProducto( producto_servicioLocal.getGrps_id() );
				objPS.setNombreFamiliaPS( familia_producto_servicioLocal.getFaps_nombre() );
				objPS.setIdFaps( familia_producto_servicioKey.faps_id );
				objPS.setCodigoFamiliaPS( familia_producto_servicioLocal.getFaps_codigo() );
	
				if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcLinea || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaIP || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcTV )
				{
					objPS.setTipoPC(true);
					Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
					Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
					String descTipoPC="";
					try
					{
						Tipo_pcLocalHome tipoPC = (Tipo_pcLocalHome)HomeFactory.getHome(Tipo_pcLocalHome.JNDI_NAME);
						Tipo_pcKey tPCK = new Tipo_pcKey();
						tPCK.id_tipo_pc= new Integer(agrupacion_atisLocal.getTip_pro_cmr_cd().intValue());
						Tipo_pcLocal tipoPcLocal = tipoPC.findByPrimaryKey(tPCK);
						descTipoPC= tipoPcLocal.getNombre_pc();
						objPS.setValTipoPc(tipoPcLocal.getFamilia_pc());
						objPS.setIdentificadorProducto(agrupacion_atisLocal.getNum_ide_nu());
					}
					catch (Exception e)
					{
						descTipoPC="" + agrupacion_atisLocal.getTip_pro_cmr_cd();
					}
					objPS.setDescTipo(descTipoPC);

					String descSubTipoPC="";
					try
					{

						Subtipo_pcLocalHome sTipoPC = (Subtipo_pcLocalHome)HomeFactory.getHome(Subtipo_pcLocalHome.JNDI_NAME);
						Subtipo_pcKey stPCK = new Subtipo_pcKey();
						stPCK.id_subtipo_pc= new Integer(agrupacion_atisLocal.getSbt_pro_cmr_cd().intValue());
						Subtipo_pcLocal sTipoPcLocal = sTipoPC.findByPrimaryKey(stPCK);
						descSubTipoPC= sTipoPcLocal.getNombre_subtipo();
					}
					catch (Exception e)
					{
						descSubTipoPC="" + agrupacion_atisLocal.getSbt_pro_cmr_cd();
					}							
					objPS.setDescSubTipo(descSubTipoPC);
					Collection direcAtis = agrupacion_atisLocal.getDireccion_atis();
					Iterator iterDirec = direcAtis.iterator();
					if (iterDirec.hasNext())
					{
						Direccion_atisLocal dAtis = (Direccion_atisLocal) iterDirec.next();
						objPS.setDescSubLocalidad(dAtis.getNom_slo_no().toString());
						LocalidadLocal localidadAgrup = dAtis.getFk_02_localidad();
						if(localidadAgrup!=null)
						{
							LocalidadKey localidadKey=(LocalidadKey) localidadAgrup.getPrimaryKey();
							objPS.setCodLocalidad(localidadKey.cod_loc);
							objPS.setDescLocalidad(localidadAgrup.getDescripcion_localidad());
						}
						else
						{
							log.debug("La direccion Atis de la Agrupacion no tiene localidad");
							objPS.setDescLocalidad("");
						}
						DepartamentoLocal departamentoLocal=dAtis.getFk_02_dir_depto();
						if(departamentoLocal!=null)
						{
							DepartamentoKey departamentoKey=(DepartamentoKey) departamentoLocal.getPrimaryKey();
							objPS.setCodDepartamento(departamentoKey.cod_dpt);
							objPS.setDescDepartamento(departamentoLocal.getDescripcion_departamento());
						}
						String dirInst = Utiles.sinNull(dAtis.getNom_cal_ds(),"") +" "+ Utiles.sinNull(dAtis.getNum_cal_nu(),"")+" "+Utiles.sinNull(dAtis.getDsc_cmp_pri_ds(),"")+" "+Utiles.sinNull(dAtis.getDsc_cmp_seg_ds(),"")+" "+ Utiles.sinNull(dAtis.getNom_slo_no(),"");
						objPS.setDireccion(dirInst);
			
					}
					try
					{
						Fecha fecha=new Fecha(agrupacion_atisLocal.getCps_agr_sub_ff());
						objPS.setFechaCompromiso(fecha.getFechaconFormato());
					}
					catch(Exception fe)
					{
			
					}
				}
				else
					objPS.setTipoPC(false);
				objPS.setId(key.ps_id);
				objPS.setIdProducto(key.ps_id);
				objPS.setNombreProducto(producto_servicioLocal.getPs_nombre());
				objPS.setObservacion(producto_servicioLocal.getPs_observacion());
				objPS.setPcoObligatorio(new Byte(producto_servicioLocal.getPs_pco_obligatorio().byteValue()));
				objPS.setIdEmpresa(producto_servicioLocal.getEmpr_id());
				objPS.setIdAmbito(producto_servicioLocal.getAmbi_id());
				objPS.setIdGrupoProducto( producto_servicioLocal.getGrps_id() );
				objPS.setNombreFamiliaPS( familia_producto_servicioLocal.getFaps_nombre());
				objPS.setIdFaps( familia_producto_servicioKey.faps_id );
				objPS.setCodigoFamiliaPS( familia_producto_servicioLocal.getFaps_codigo() );
				objPS.setIdOpComercial( operacion_comercialKey.opco_id);
				objPS.setOperacionComercial( operacion_comercialLocal.getOpco_nombre() );
				objPS.setTipoOperacionComercial( operacion_comercialLocal.getOpco_tipo() );
				objPS.setCantidad( producto_servicio_peticionLocal.getPspe_cantidad() );
				boolean llamaAlaActividad=pasaPSyOcXActividad(idPeticion,objPS.getId(),objPS.getIdOpComercial(),idActFlujo);
				boolean estaOK=estaOKProductoServicioPeticion(producto_servicio_peticionLocal);
				objPS.setLlamaAlaActividad(true);
				objPS.setEstaOK(estaOK);
				arrayList.add(objPS);
			}
			return arrayList;
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}

	public void marcaPeticionUsuario(Long nroPeticion, Long usuaId) throws ATiempoAppEx
	{
		try
		{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			local.setAgen_id(usuaId);
			log.debug("Peticion:"+nroPeticion+" premarcada a usuario:"+usuaId);
		}
		catch(NamingException e)
		{
			log.error("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			log.error("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		catch (Exception e)
		{
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}
	
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - INICIO
	public ArrayList buscarPorCliente(Long idPeticionAtis) throws ATiempoAppEx {
				return this.buscarPorCliente(idPeticionAtis,null,null);
	}
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - FIN
	
	/**
	 *  AT-1524 Reversa para Configurar Internet 08/12/2008
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#actualizarEstadoPS(java.lang.Long, java.lang.Integer, java.lang.Long)
	 */
	public void actualizarEstadoPS(Long nroPeticion,Integer actividad_flujo_id,Long codigoActividad) throws ATiempoAppEx
		{
			try
			{
//				TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
				Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
				
				if(peticionHome==null)
					peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				if(peticion_flujoHome==null)
					peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				
				PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
				int i =1;
				for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();){	

					Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
					Producto_servicio_peticionKey localKey=(Producto_servicio_peticionKey)local.getPrimaryKey();
					Producto_servicioKey psKey=(Producto_servicioKey) local.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey opComKey=(Operacion_comercialKey) local.getOperacion_comercial().getPrimaryKey();
					try
					{

						Collection  lista=peticion_flujoHome.findByIdActividadFPetPsOC(actividad_flujo_id,nroPeticion,psKey.ps_id,opComKey.opco_id);
						if(lista.size()==0){
							continue;
						}
						boolean estaOk=false;
						Collection listaEstadoPs=local.getEstado_ps_peticion();
					
						if(listaEstadoPs.size()>0){	

							Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPs.iterator().next();
							Estado_ps_peticionKey estado_ps_peticionKey=(Estado_ps_peticionKey) estado_ps_peticionLocal.getPrimaryKey();
							
							Collection listadoCausas=causal_peticionHome.findCausalesDePsOrdenDesByAct(estado_ps_peticionKey.correlativo,codigoActividad);
							
							if(listadoCausas.size()==0){
								estaOk=true;
							}else{
								for(Iterator iterator2=listadoCausas.iterator();iterator2.hasNext();)
								{
									Causal_peticionLocal causal_peticionLocal=(Causal_peticionLocal) iterator2.next();
									Estado_psKey estado_psKey=(Estado_psKey) causal_peticionLocal.getEstado_ps().getPrimaryKey();
															
									if(estado_psKey.cod_estado_cierre.intValue()==ComunInterfaces.estadoCierreError && causal_peticionLocal.getCod_actividad().equals(codigoActividad)){
										Estado_psKey estado_psKey2=new Estado_psKey(new Long(0));
										Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey2);
										causal_peticionLocal.setEstado_ps(estado_psLocal);
										Fecha f=new Fecha(new Date());
										causal_peticionLocal.setFecha_termino(f.getFechaconFormato(9));
									}
								}
							}
						}				
					}
					catch (FinderException e){
						log.debug("FinderException",e);
					}
				}	
			}
			catch(Exception e){
				e.printStackTrace();
				log.debug("Exception",e);
				throw new ATiempoAppEx(e.getMessage());
			}
		}
	
	
	/** 
	 * boolean usaGranite(nroPeticion), retorna true en caso de que la petición deba pasar por granite
	 * y false en otro caso
	 */
	public boolean usaGranite(Long nroPeticion) throws ATiempoAppEx{
		try{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			Long todasGrantite=new Long (VpistbbaConfig.getVariable("GRANITE"));
			if (ComunInterfaces.sinGranite== todasGrantite.longValue()){
				
				if(peticionHome==null)
					peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
				PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));				
				
				//Si el valor es uno es porque usa Granite
				if(peticionLocal.getGranite_code()==ComunInterfaces.conGranite){
					return true;
				}else{
					return false;
				}
			}else{
				//Si entra al else ya estan todas las localidades por Granite
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	public boolean centralSoportaConfAutomatica(Long nroPeticion)throws ATiempoAppEx{
		try {
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			boolean soporta = false;
			if(peticionHome==null) {
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			}	
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));	
			Recursos_linea_basicaLocal a = (Recursos_linea_basicaLocal) peticionLocal.getRecursos_linea_basica().iterator().next();
			
			
			if (a.getConfig_automatica()!=null) {
				soporta = (a.getConfig_automatica().intValue() == 1);
			}	
			
			log.debug("Soporta Configuracion Automatica = "+soporta);
			return soporta;
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		} 
	}
	//CR-22569 agonz 25 feb 2009
	/**
	 * @param act, famlia
	 * Metodo que busca PS's de la famPsId y que devuelve true en caso de que encuentre un ps de la misma familia que famPsId
	 * @author 808026
	 */
	public boolean tienePS (ActividadEJBDTO act, int famPsId)throws ATiempoAppEx{
		
		try {
			Iterator iterTemp = act.getPsOk().iterator();
			PsVsOcVO psTemp= null;
			log.debug(" Se compara famPsId =  " + ComunInterfaces.familiaPcLinea+" con..");
			while (iterTemp.hasNext()) {
				psTemp = (PsVsOcVO) iterTemp.next();
				log.debug("psTemp.getFaPsId() = " + psTemp.getFaPsId());
				if(psTemp.getFaPsId().intValue()== famPsId || psTemp.getFaPsId().intValue() == ComunInterfaces.familiaIP)
					return true;
			}
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		}
		
	}
	//log.debug("La Peticion: "+peticion+" esta afectada por ninguna Regla ");
	/**
	 * @param numero peticion atiempo
	 * Metodo que valida si una peticion esta siendo impactada por una regla de retencion y si es asi setea la misma en la tabla peticion 
	 * @author 801936
	 */
	public Regla_RetencionesLocal verificarRetencion(Long peticion)throws ATiempoAppEx {
		
		Regla_RetencionesLocal reglaRetencion=null;
		try {
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			if(peticionHome==null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			
			String activo = VpistbbaConfig.getVariable("REGLASVPI");
			if(activo!=null && activo.equalsIgnoreCase("1")){
				
				reglaRetencion=buscarRegla( peticion);
								
				if(reglaRetencion!=null){
					log.debug("La peticion: "+ peticion+" esta siendo afectada por la regla: "+ reglaRetencion.getDescripcion());
					PeticionLocal  peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(peticion));
					peticionLocal.setRegla_id(reglaRetencion.getRegla_id());
					
				}else{
					log.debug("No hay ninguna regla de retencion que afecte la peticion");
				}				
								
			}else{
				log.debug("No se esta validando ninguna regla de retencion ya que la variable no existe o no esta activa la validacion");
			}
		} catch (Exception e) {
			
			log.debug("Error intentando verificar regla de retencion",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	
		return reglaRetencion;
	}
	
	/** 
	 * se encarga de Marcar una peticion como afectada por Granite
	 */
	public void marcarGranite(Long nroPeticion) throws ATiempoAppEx{
		
		try{
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			Long todasGrantite=new Long (VpistbbaConfig.getVariable("GRANITE"));
			if (ComunInterfaces.sinGranite== todasGrantite.longValue()){
				
				if(peticionHome==null)
					peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
				PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));				
				
				if(localidadHome==null)
					localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				
				//String codLoc = peticionLocal.getCod_loc_cd().toString();
				LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
//				LocalidadKey localidadKey=(LocalidadKey) dptLocalidad.getPrimaryKey ();
//				String codLoc =localidadKey.cod_loc;
//				
//				LocalidadLocal localidadLocal=localidadHome.findByPrimaryKey(new LocalidadKey(codLoc));				
			
				//Si el valor es uno es porque usa Granite
				if(localidadLocal.getGranite_code()==ComunInterfaces.conGranite){
					log.debug("marcando la peticion: "+ nroPeticion+" como GRANITE");
					peticionLocal.setGranite_code(localidadLocal.getGranite_code());
				}
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug("Error validando si la peticion esta siendo afectada por granite",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	private Regla_RetencionesLocal buscarRegla(Long peticion) throws ATiempoAppEx {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String sql = null;
		String aster="*";
		Regla_RetencionesLocal reglaLocal=null;
		try {
			
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 15, 2009
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Regla_RetencionesLocalHome reglaRetencionHome=(Regla_RetencionesLocalHome) HomeFactory.getHome(Regla_RetencionesLocalHome.JNDI_NAME);
			
			if(peticionHome==null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(peticion));		
			
			conn = DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			
			
			sql = armarSQLCandidatosOpt(); 
			pStmt = conn.prepareStatement(sql);
				pStmt.setLong(1, ComunInterfaces.estadoRelaretencionActiva);
				pStmt.setLong(2, new Long("3").longValue());
				DepartamentoLocal dptLocal = peticionLocal.getFk_02_departamento ();
				DepartamentoKey departamentoKey=(DepartamentoKey) dptLocal.getPrimaryKey ();
				pStmt.setString(3,departamentoKey.cod_dpt);
				pStmt.setString(4,aster);
				LocalidadLocal dptLocalidad = peticionLocal.getFk_01_localidad();
				LocalidadKey localidadKey=(LocalidadKey) dptLocalidad.getPrimaryKey ();
				pStmt.setString(5,localidadKey.cod_loc);
				pStmt.setString(6,aster);
				pStmt.setString(7,peticionLocal.getTica_id());
				pStmt.setString(8,aster);
				
				pStmt.setString(9,peticionLocal.getPeti_id_instancia());
				pStmt.setString(10,aster);
				pStmt.setTimestamp(11,new Fecha().getTimestamp());

			
			rs = pStmt.executeQuery();
			Long idRegla = null;
			if (rs.next()) {
				idRegla = new Long(rs.getLong(1));
				if(reglaRetencionHome==null)
					reglaRetencionHome = (Regla_RetencionesLocalHome) HomeFactory.getHome(Regla_RetencionesLocalHome.JNDI_NAME);
			
				reglaLocal=reglaRetencionHome.findByPrimaryKey(new Regla_RetencionesKey(idRegla));		
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug("Error de sql buscando regla de retencion",e);
			throw new ATiempoAppEx(e.getMessage());
			
		} catch (NamingException e) {
			e.printStackTrace();
			log.debug("Error de sql buscando regla de retencion",e);
			throw new ATiempoAppEx(e.getMessage());
		} catch (FinderException e) {
			e.printStackTrace();
			log.debug("Error de sql buscando regla de retencion",e);
			throw new ATiempoAppEx(e.getMessage());
		} finally {
			closeJDBCResources(conn, pStmt, rs);
		}
		return reglaLocal;
	}
/**
	 * @param conn
	 * @param pStmt
	 * @param rs
	 */
	private final void closeJDBCResources(Connection conn, PreparedStatement pStmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pStmt != null) pStmt.close();
		} catch (SQLException e1) {
			log.error("Cerrando resultSet o stmt", e1);
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e1) {
			log.error("Cerrando conexion", e1);
		}
	}
/**
	 * @param hab
	 * @param parametros
	 * @return
	 */
	private String armarSQLCandidatosOpt() {
		
		StringBuffer query = new StringBuffer();
		query.append(SQL_USRS_COMUNES);
		return query.toString();
	}
	

private static final String SQL_USRS_COMUNES = "select * from atiempo.regla_retenciones re where re.estado=? and re.ap_id= ? "+
												"and re.cod_dpt in (?,?)and re.cod_loc in (?,?) and "+
												"re.tica_id in (?,?)and re.peti_id_instancia in(?,?) "+
												"and ? between RE.FECHA_DESDE and RE.FECHA_HASTA";

	//Gustavo - CR 25403
	public ArrayList recuperarCausaDemora(){
		ArrayList listaCausaDemora = new ArrayList();
		Causa_demoraLocal causaDemoraLocal;
		try {
			log.debug("Entre a recuperar Causas de Demora");
			log.debug("Voy a ir a obtener causas de demora");
			Causa_demoraLocalHome causaDemoraHome=(Causa_demoraLocalHome) HomeFactory.getHome(Causa_demoraLocalHome.JNDI_NAME) ;
			Collection causaDemora = causaDemoraHome.findAllCausasDemora();
			for(Iterator iterator=causaDemora.iterator();iterator.hasNext();){
				Causa_demoraLocal causaDemoraLocal1 = (Causa_demoraLocal) iterator.next();
				Causa_demoraKey causaDemoraKey = (Causa_demoraKey) causaDemoraLocal1.getPrimaryKey();
				CausaDemoraDTO causaDemoraDTO = new CausaDemoraDTO();
				causaDemoraDTO.setCodCaudem(causaDemoraKey.cod_caudem);
				causaDemoraDTO.setDescripcionCaudem(causaDemoraLocal1.getDescripcion_caudem());
				listaCausaDemora.add(causaDemoraDTO);
			}
			log.debug("Llenando causaDemora");
			return listaCausaDemora;
		} catch (FinderException e) {
			log.error("Excepcion " + e);
			return new ArrayList();
		} catch (NullPointerException e1){
			log.error("Excepcion1 " + e1);
			return new ArrayList();		
		} catch (NamingException ne) {
			log.error("NamingException " + ne);
			return new ArrayList();			
		}


	}
	
	public ArrayList recuperaPsVisita(Long idPeticionAtiempo)
	{
		try
		{
			ArrayList listaPsesDelaPeticion=new ArrayList();
			PeticionLocalHome peticionHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(idPeticionAtiempo));
			Collection listaProductoServicioPeticion=peticionLocal.getProducto_servicio_peticion();
			log.debug("Voy a entrar al for en recuperaPS");
			log.debug("Cantidad de PS Peticion " + listaProductoServicioPeticion.size());
			for(Iterator iterator=listaProductoServicioPeticion.iterator();iterator.hasNext();)
			{
				log.debug("Estoy recuperando Ps de la Peticion para el listado de tecnicos en la visita");
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicio_peticionKey producto_servicio_peticionKey=(Producto_servicio_peticionKey) producto_servicio_peticionLocal.getPrimaryKey();
				ProductoServicioPeticionDTO productoServicioPeticionDTO=new ProductoServicioPeticionDTO();
				productoServicioPeticionDTO.setPetiNumero(producto_servicio_peticionKey.fk_psp_pet_peti_numero);
				Producto_servicioLocal producto_servicioLocal= producto_servicio_peticionLocal.getProducto_servicio();
				Producto_servicioKey producto_servicioKey=(Producto_servicioKey)producto_servicioLocal.getPrimaryKey();
				productoServicioPeticionDTO.setPsId(producto_servicioKey.ps_id);
				productoServicioPeticionDTO.setNombrePS(producto_servicioLocal.getPs_nombre());
				
					
				listaPsesDelaPeticion.add(productoServicioPeticionDTO);
			}
			return listaPsesDelaPeticion;
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			return new ArrayList();
		}
	}	
	//Gustavo - CR 25403

		
	/* 
	 * CR 00024071 - 2009/04/21 - 1
	 * 		Funcion auxiliar para quitar los caracteres especiales del numero de cliente. 
	 */
	private String replaceSpecialChars(String nrDocClient){
		if (nrDocClient != null){
			return nrDocClient.replaceAll("[\\D]",""); //el \D representa a todos los caracteres menos 0 al 9
		}
		return nrDocClient;
	}		


	/*
	 * CR 00024805 - May 19, 2009 - 1
	 *  	Implementación de función para busqueda por cliente con nuevos campos y consultas sql.
	 */
	public ArrayList buscarPeticiones(BuscadorPeticionVpiDTO busquedaPeticionDTO) throws ATiempoAppEx {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList lsPeticiones = new ArrayList();
		PeticionesServicesUtils util = new PeticionesServicesUtils(","); // delimitador
		
		int codigo = busquedaPeticionDTO.getIdBusqueda();
		Long idPeticionAtis = busquedaPeticionDTO.getIdPeticionAtis();
		String rutCli = busquedaPeticionDTO.getRutCli();
		String rutDv = busquedaPeticionDTO.getRutDv();
		String idPc = busquedaPeticionDTO.getIdPc();
		
		int lim_peticiones = util.getLimitePetisiones();
		int cont_peticiones = 0;
		boolean alcanzo_limite = false;
		
		try {
			
			String query = util.obtenerConsultaSQL(busquedaPeticionDTO);
			log.info("Consulta SQL para la busqueda de peticiones segun filtro ingresado: " + query);
			
			conn = DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			
			ps = conn.prepareStatement(query);
			
			util.seteoParametros(ps, codigo, idPeticionAtis, rutCli, rutDv, idPc);
			
			rs = ps.executeQuery();
			
			// Peticion DTO para mantener la peticion anterior, asi poder actualizar la agrupacion
			PeticionDTO pDtoAux = null;
			String peti_numero;
			String agrupaciones = "";
			
			// CR 00027016 - Variables para BIntegrada
			int biId = 0;
			String biUrl = null;
			int actId = 0;
			long rol = 0;
			boolean agregarPeticion = true;
			
			while (rs.next() && !alcanzo_limite){
				
				PeticionDTO pDto = new PeticionDTO();
				
				pDto.setCodPetCd(new Long(rs.getLong("cod_pet_cd")));

				peti_numero = rs.getString("peti_numero");
				pDto.setPetiNumero(new Long(peti_numero));
				
				pDto.setPetiFechaIngreso(rs.getTimestamp("peti_fecha_ingreso"));
				pDto.setPetiFechaCompromiso(rs.getTimestamp("peti_fecha_compromiso"));

				String gr_sub_pdr_cd = rs.getString("agr_sub_pdr_cd");
				String cod_agr_sub_nu = rs.getString("cod_agr_sub_nu");
				
				agrupaciones = util.obtenerAgrupacionString(gr_sub_pdr_cd, cod_agr_sub_nu, peti_numero);
				if (pDtoAux != null && pDtoAux.getPetiNumero().compareTo(pDto.getPetiNumero())!=0){
					pDtoAux.setAgrupaciones(agrupaciones);

					// CR 00027016 : luego del seteo de agrupaciones se haria la logica de BIntegrada
					if (busquedaPeticionDTO.isJoinBIntegrada()){log.debug("busquedaPeticionDTO.isJoinBIntegrada()== true");
						agregarPeticion = this.seteoVariablesBIntegrada(pDtoAux, busquedaPeticionDTO, biId, biUrl, actId, rol);
						if (agregarPeticion){
							lsPeticiones.add(pDtoAux);
							cont_peticiones ++;
						}
					}else{
						lsPeticiones.add(pDtoAux);
						cont_peticiones ++;
					}
					
					pDtoAux = pDto;
					
					// Si no se hace Join con BIntegrada se devuelve Null en String, y 0 en otros
					biId = rs.getInt("bi_id");
					biUrl = rs.getString("bi_url_detalle");
					actId = rs.getInt("act_id");
					rol = rs.getLong("rol_id");
					
					if (cont_peticiones == lim_peticiones){
						alcanzo_limite = true;
					}
					
				}else if (pDtoAux == null){log.debug("pDtoAux == null");
					pDtoAux = pDto;

					// Si no se hace Join con BIntegrada se devuelve Null en String, y 0 en los otros casos
					biId = rs.getInt("bi_id");
					biUrl = rs.getString("bi_url_detalle");
					actId = rs.getInt("act_id");
					rol = rs.getLong("rol_id");
				}
			}
			if (pDtoAux != null && !alcanzo_limite){
				pDtoAux.setAgrupaciones(util.getLastAgrupacion());
				
				// CR 00027016 : luego del seteo de agrupaciones se haria la logica de BIntegrada
				if (busquedaPeticionDTO.isJoinBIntegrada()){
					agregarPeticion = this.seteoVariablesBIntegrada(pDtoAux, busquedaPeticionDTO, biId, biUrl, actId, rol);
					if (agregarPeticion)
						lsPeticiones.add(pDtoAux);
				}else				
					lsPeticiones.add(pDtoAux);
			}
		
			busquedaPeticionDTO.setLimitePeticiones(lim_peticiones);
			busquedaPeticionDTO.setLimiteAlcanzado(alcanzo_limite);
			
			// se logea mensaje
			StringBuffer buff = new StringBuffer();
			buff.append("Ejecuto consulta buscador VPI sql=");
			buff.append(query);
			buff.append(" peticionAtis=");
			buff.append(busquedaPeticionDTO.getIdPeticionAtis());
			buff.append(" rutCli=");
			buff.append(busquedaPeticionDTO.getRutCli());
			buff.append(" rutDv=");
			buff.append(busquedaPeticionDTO.getRutDv());
			buff.append(" idPC=");
			buff.append(busquedaPeticionDTO.getIdPc());
			buff.append(" tipoPC=");
			buff.append(busquedaPeticionDTO.getTypePc());
			buff.append(" sizeResultado=");
			buff.append(lsPeticiones.size());
			buff.append(" alcanzoLimite=");
			buff.append(alcanzo_limite);
			log.debug(buff);
			
		} catch (SQLException e) {
			log.error("Error en buscador VPI: " + e);
			throw new ATiempoAppEx(e.getMessage());
		} finally {
			try{
				if (rs != null){
					rs.close();
				}
				if (ps != null){
					ps.close();
				}
				if (conn != null){
					conn.close();
				}
			}catch(SQLException e){
				log.error("Error en buscador VPI: " + e);
				throw new ATiempoAppEx(e.getMessage());	
			}
		}
		
		return lsPeticiones;
	}
	
	
	// CR25865 CRE - adocarmo - inicio
	public String getPeti_id_instancia_con_pdti(Long nroPet) {
		
		PeticionLocal local = null;
		String petiIdInstancia = "";
		
		try {
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);

			local = peticionHome.findByPrimaryKey(new PeticionKey(nroPet));
			petiIdInstancia = local.getPeti_id_instancia();
			Long grupoPs = null;
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator.next();
				grupoPs = getGrupoPS(local2.getPsId());				
				if (grupoPs != null && grupoPs.equals(new Long(ComunInterfaces.ID_GRUPO_PDTI))) {
					petiIdInstancia = petiIdInstancia + "PDTI";
					break;
				}
			}
		} catch (FinderException e) {
			log.error("Error al buscar la peticion "  + nroPet + " - " + e.getMessage());
		
		} catch (NamingException ne) {
			log.error("NamingException "  + ne.getMessage());
		}		
		return petiIdInstancia;
	}
	
	public Long getGrupoPS(Long ps) {
		Grpe_PsLocalHome grpeLocalHome = null;
		Long grPs = null;
		try {
			grpeLocalHome=(Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
			Grpe_PsLocal grPsLocal = grpeLocalHome.findGrupoByPS(ps);
			grPs = grPsLocal.getGrpe_id();
			log.info("Retornando grupo para el ps:.."+ ps.longValue()+" el Grupo es :.. "+ grPs);
		} catch (NamingException e) {
			//e.printStackTrace();
			log.error("NamingException",e);
		} catch (FinderException e1) {
			//e1.printStackTrace();
			log.error("Error al buscar el grupo del ps " + ps + "(probablemente no pertenece a ningun grupo)  "  + " - " + e1.getMessage());			
		}
		return grPs;
	}	
	
	// CR25865 CRE - adocarmo - fin
	
	//	 CR25996 UMTS - agonz - 25/06/2009
	/**
	 * Metodo que carga un hash que sera utilizado en la ControlInstalacionEjecutor para definir las solapas (ingreso y modificacion de equipos)
	 * que se deben mostrar en la actividad Control de Instalacion segun las familias de las peticiones
	 * 0 - no se muestra niguna
	 * 1 - se muestra solo solapa de ingreso de equipos
	 * 2 - se muestra solo solapa de modificacion de equipos
	 * 3 - se muestran ambas solapas ingreso y modificacion de equipos
	 */
	
	
	public HashMap cargarHashFamilias()throws ATiempoAppEx {
	    HashMap hashInstancia = new HashMap();
        /** carga valores para altas opCoId= 1*/
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TV			,ComunInterfaces.opCoTipoAlta),"0");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LB			,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBA		,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTV		,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATV		,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBPDTI		,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBAPDTI	,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTVPDTI	,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATVPDTI	,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TVPDTI		,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.PDTI		,ComunInterfaces.opCoTipoAlta),"1");  
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BA			,ComunInterfaces.opCoTipoAlta),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATV		,ComunInterfaces.opCoTipoAlta),"2");      
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATVPDTI	,ComunInterfaces.opCoTipoAlta),"3");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BAPDTI		,ComunInterfaces.opCoTipoAlta),"3");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IP			,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPBA		,ComunInterfaces.opCoTipoAlta),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPTV		,ComunInterfaces.opCoTipoAlta),"1");
        
        /** carga valores para bajas opCoId= 6*/
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TV			,ComunInterfaces.opCoTipoBaja),"0");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LB			,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBA		,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTV		,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATV		,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBPDTI		,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBAPDTI	,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTVPDTI	,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATVPDTI	,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TVPDTI		,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.PDTI		,ComunInterfaces.opCoTipoBaja),"1");  
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BA			,ComunInterfaces.opCoTipoBaja),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATV		,ComunInterfaces.opCoTipoBaja),"2");      
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATVPDTI	,ComunInterfaces.opCoTipoBaja),"3");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BAPDTI		,ComunInterfaces.opCoTipoBaja),"3");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IP			,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPBA		,ComunInterfaces.opCoTipoBaja),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPTV		,ComunInterfaces.opCoTipoBaja),"1");
        
        
        /** carga valores para alta por cambio de producto opCoId= 46*/
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TV			,ComunInterfaces.opCoTipoAltaCambioProd),"0"); 
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LB			,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBA		,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTV		,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATV		,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBPDTI		,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBAPDTI	,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTVPDTI	,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATVPDTI	,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TVPDTI		,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.PDTI		,ComunInterfaces.opCoTipoAltaCambioProd),"1");  
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BA			,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATV		,ComunInterfaces.opCoTipoAltaCambioProd),"1");      
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATVPDTI	,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BAPDTI		,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IP			,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPBA		,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPTV		,ComunInterfaces.opCoTipoAltaCambioProd),"1");
        
        /** carga valores para baja por cambio de producto opCoId= 47*/
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TV			,ComunInterfaces.opCoTipoBajaCambioProd),"0"); 
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LB			,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBA		,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTV		,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATV		,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBPDTI		,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBAPDTI	,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTVPDTI	,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATVPDTI	,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TVPDTI		,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.PDTI		,ComunInterfaces.opCoTipoBajaCambioProd),"1");  
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BA			,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATV		,ComunInterfaces.opCoTipoBajaCambioProd),"1");      
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATVPDTI	,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BAPDTI		,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IP			,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPBA		,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPTV		,ComunInterfaces.opCoTipoBajaCambioProd),"1");
        
        /** carga valores para alta traslado opCoId= 50*/
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TV			,ComunInterfaces.opCoTipoAltaTraslado),"0"); 
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LB			,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBA		,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTV		,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATV		,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBPDTI		,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBAPDTI	,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTVPDTI	,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATVPDTI	,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TVPDTI		,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.PDTI		,ComunInterfaces.opCoTipoAltaTraslado),"2");  
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BA			,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATV		,ComunInterfaces.opCoTipoAltaTraslado),"2");      
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATVPDTI	,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BAPDTI		,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IP			,ComunInterfaces.opCoTipoAltaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPBA		,ComunInterfaces.opCoTipoAltaTraslado),"1");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IPTV		,ComunInterfaces.opCoTipoAltaTraslado),"1");
        
        /** carga valores para baja traslado opCoId= 51*/
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TV			,ComunInterfaces.opCoTipoBajaTraslado),"0"); 
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LB			,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBA		,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTV		,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATV		,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBPDTI		,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBAPDTI	,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTVPDTI	,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATVPDTI	,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TVPDTI		,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.PDTI		,ComunInterfaces.opCoTipoBajaTraslado),"2");  
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BA			,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATV		,ComunInterfaces.opCoTipoBajaTraslado),"2");      
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATVPDTI	,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BAPDTI		,ComunInterfaces.opCoTipoBajaTraslado),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IP			,ComunInterfaces.opCoTipoBajaTraslado),"2");
        
        /** carga valores para transferencia Tecnica opCoId= 54*/
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TV			,ComunInterfaces.opCoTipoTrasnfTecnica),"0"); 
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LB			,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBA		,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTV		,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATV		,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBPDTI		,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBAPDTI	,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBTVPDTI	,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.LBBATVPDTI	,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.TVPDTI		,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.PDTI		,ComunInterfaces.opCoTipoTrasnfTecnica),"2");  
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BA			,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATV		,ComunInterfaces.opCoTipoTrasnfTecnica),"2");      
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BATVPDTI	,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.BAPDTI		,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        hashInstancia.put(new FamiliaOPComercial(ComunInterfaces.IP			,ComunInterfaces.opCoTipoTrasnfTecnica),"2");
        
        return hashInstancia;
	
	}

	

	/**
	 * Metodo que retorna true en caso de que la peticion cuyo numero es nroPet sea del grupo UMTS
	 */
	public boolean esGrupoUmts (Long nroPet) throws ATiempoAppEx{
	    PeticionLocal peticion = null;
		String petiIdInstancia = null;
		boolean esUmts = false;
		try {
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);

		    peticion = peticionHome.findByPrimaryKey(new PeticionKey(nroPet));
			
			Collection producto_servicio_peticionArray = peticion.getProducto_servicio_peticion();			
			
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;

			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				//Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				//Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				//if(pasaPSyOcXActividad(nroPet,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
					Long grp = getGrupoPS(producto_servicio_peticionLocal.getPsId());
				    if (grp != null && grp.longValue()== ComunInterfaces.ID_GRUPO_UMTS) {
						esUmts = true;
						break;
					}
				//}
			}
			
		
		} catch (FinderException e) {
		    e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);

		}
		catch (NamingException ne) {
			log.error("NamingException "  + ne.getMessage());
		}		
		log.info("es grupo umts;...."+ esUmts);
		return esUmts;
	    
	}
	
	public long operacionUmts(Long nroPet) throws ATiempoAppEx{
		long resultado=0;
		try {
			
			
			String aux =operacionComercialUmts( nroPet);
			
			PeticionesDelegate delegate=new PeticionesDelegate();		
			ArrayList psPeticion = delegate.listaDePsDePeticion(nroPet);
			
			if (validaAltaDuoUmts ( nroPet, psPeticion.iterator())){
				resultado= ComunInterfaces.AltaDuoUmts;
			}
			if (validaBajaDuoUmts ( nroPet, psPeticion.iterator())){
				resultado= ComunInterfaces.BajaDuoUmts;
			}
			if (aux.indexOf("Traslado")>-1){
				if(validaTrasladoAUmts(nroPet, psPeticion.iterator())){
					resultado = 0;
				}else{
					resultado= ComunInterfaces.TrasladoUmts;
				}				
			}
		} catch (ATiempoAppEx e) {
			// TODO Auto-generated catch block
			
			log.info("Error validando Operacion umts;...."+ e.getMessage());
		}
		log.info("Resultado Operacion umts;...."+ resultado);
		return resultado;
	}
	
	
	public boolean validaDuoUmtsQuiebre(Long nroPet) throws ATiempoAppEx{
		 PeticionLocal peticion = null;
			boolean pcBA=false;
			boolean pcLinea=false;
			boolean Duo=false;
			boolean tieneQuiebre=false;
		
			
			try {
				PeticionesDelegate delegate=new PeticionesDelegate();		
				ArrayList psPeticion = delegate.listaDePsDePeticion(nroPet);
				
				Iterator iterTemp=psPeticion.iterator();
				
				//Iterator iterTemp = act.getPsOk().iterator(); //Debe venir al menos un ps
				PsVsOcVO psTemp= null;
				Long grupoPs = null;
				while(iterTemp.hasNext()){
					psTemp= (PsVsOcVO) iterTemp.next();
					grupoPs = getGrupoPS(psTemp.getPsId());				
					if (grupoPs != null && grupoPs.equals(new Long(ComunInterfaces.ID_GRUPO_UMTS))) {
						log.debug("validaAltaDuoUmts es Grupo UMTS = ");
						if(psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcBA){
							log.debug("Tengo PC BA en Alta es Grupo UMTS = ");
							 pcBA=true;
						}
						if(psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcLinea){
							log.debug("Tengo PC Linea en Alta es Grupo UMTS = ");
							pcLinea=true;
						}
						if(!psTemp.isOk()){
							tieneQuiebre=true;
						}
					}
				}
				if (pcLinea&&pcBA&&tieneQuiebre){
					log.debug("Es dúo con un ps en quiebre!");
				}else if(pcLinea&&pcBA&&!tieneQuiebre){
					log.debug("Es dúo sin ps's en quiebre!");
				}else{
					log.debug("Se intentó evaluar si es dúo con ps en quiebre pero no es dúo!");
				}
			
			} catch (Exception e) {
			    e.printStackTrace();
			    throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);
			}

			return tieneQuiebre;
	}
	
	
	/**
	 * Metodo que retorna el tipo de operacion Comercial asociada al grupo UMTS de una peticion 
	 */
	public boolean validaAltaDuoUmts (Long nroPet,Iterator iterTemp) throws ATiempoAppEx{
	    PeticionLocal peticion = null;
		boolean resultado=false;
		boolean pcBA=false;
		boolean pcLinea=false;
		boolean Duo=false;
		PeticionLocal local = null;
	
		
		try {
			//Iterator iterTemp = act.getPsOk().iterator(); //Debe venir al menos un ps
			PsVsOcVO psTemp= null;
			Long grupoPs = null;
			while(iterTemp.hasNext()){
				psTemp= (PsVsOcVO) iterTemp.next();
				grupoPs = getGrupoPS(psTemp.getPsId());				
				if (grupoPs != null && grupoPs.equals(new Long(ComunInterfaces.ID_GRUPO_UMTS))) {
					log.debug("validaAltaDuoUmts es Grupo UMTS = ");
					if(psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcBA){
						log.debug("Tengo PC BA en Alta es Grupo UMTS = ");
						 pcBA=true;
					}
					if(psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcLinea){
						log.debug("Tengo PC Linea en Alta es Grupo UMTS = ");
						
						// Defecto 30385 - inicio 
						//pcBA=true;
						// Defecto 30385 - fin						
						pcLinea=true;
					}
				}
			}
			if (pcLinea&&pcBA){
				Duo=true;
			}
			
			String opCoTipo = operacionComercialUmts (nroPet) ;
			log.debug("Duo = "+ Duo);
			 if(opCoTipo.equalsIgnoreCase("A")&& Duo){
			 	resultado=true;			 	
			 	
			 }
			 if(opCoTipo.equalsIgnoreCase("ACP")&& Duo){
			 	resultado=true;			 	
			 	
			 }
		
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);
		}
		
		log.debug("Es DUO Alta UMTS:.."+ resultado);
		return resultado;
	    
	}
	
	/**
	 * Metodo que retorna el tipo de operacion Comercial asociada al grupo UMTS de una peticion 
	 */
	public boolean validaBajaDuoUmts (Long nroPet,Iterator iterTemp) throws ATiempoAppEx{
		PeticionLocal peticion = null;
		boolean resultado=false;
		boolean pcBA=false;
		boolean pcLinea=false;
		boolean Duo=false;
		PeticionLocal local = null;
		
		
		try {
			//Iterator iterTemp = act.getPsOk().iterator(); //Debe venir al menos un ps
			PsVsOcVO psTemp= null;
			Long grupoPs = null;
			while(iterTemp.hasNext()){
				psTemp= (PsVsOcVO) iterTemp.next();
				grupoPs = getGrupoPS(psTemp.getPsId());				
				if (grupoPs != null && grupoPs.equals(new Long(ComunInterfaces.ID_GRUPO_UMTS))) {
					log.debug("Es PS de UMTS  = ");
					if(psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcBA){
						 pcBA=true;
					}
					if( psTemp.getFaPsId().intValue() == ComunInterfaces.familiaPcLinea || psTemp.getFaPsId().intValue() == ComunInterfaces.familiaIP){
						pcLinea=true;
					}
				}
			}
			if (pcLinea&&pcBA){
				Duo=true;
			}
			
			String opCoTipo = operacionComercialUmts (nroPet) ;
			log.debug("Duo = "+ Duo);
			 if(opCoTipo.equalsIgnoreCase("B")&& Duo){
			 	resultado=true;			 	
			 	
			 }
			 if(opCoTipo.equalsIgnoreCase("BCP")&& Duo){
			 	resultado=true;			 	
			 	
			 }
		
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);
		}
		
		log.debug("Es DUO Baja UMTS:.."+ resultado);
		return resultado;
	    
	}
	
	
	/**
	 * Metodo que retorna el tipo de operacion Comercial asociada al grupo UMTS de una peticion 
	 */
	public String operacionComercialUmts (Long nroPet) throws ATiempoAppEx{
	    PeticionLocal peticion = null;
		String petiIdInstancia = null;
		String opCoTipo = null;
		try {
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);

		    peticion = peticionHome.findByPrimaryKey(new PeticionKey(nroPet));
			
			Collection producto_servicio_peticionArray = peticion.getProducto_servicio_peticion();			
			
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
			Producto_servicio_peticionLocal local = null;
			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){

				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				//if(pasaPSyOcXActividad(nroPet,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
					Long grp = getGrupoPS(producto_servicio_peticionLocal.getPsId());
				    if (grp != null && grp.equals(new Long(ID_GRUPO_UMTS))) {
				        opCoTipo = producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo();
				        if((opCoTipo!=null) && (opCoTipo.equals("A")||opCoTipo.equals("B"))){
					        String opCoTras = producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tras();
					        if(opCoTras !=null && opCoTras.equals(opCoTras_Traslado)){
					            opCoTipo = opCoTipo+"Traslado";
					        }
					        if(opCoTras !=null && opCoTras.equals(opCoTras_Transferencia)){
					            opCoTipo = opCoTipo+"Transferencia";
					        }
					            
							break;
					    }
				    }
				//}
			}
			
		
		} catch (FinderException e) {
		    e.printStackTrace();
		    throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);
		}
		catch (NamingException ne) {
			log.error("NamingException "  + ne.getMessage());
		}			
		log.debug("Se retorna en operacionComerciallUmts .."+opCoTipo);
		return opCoTipo;
	    
	}
	
	/*
	 * CR - 00027016 - Jul 2, 2009
	 *	Funcion auxiliar para JOIN con Bintegrada. Se busca la simulacion de las funciones
	 *	que se encuentran en TorreControlSessionBean para cada una de las busquedas - German P.
	 */
	private boolean seteoVariablesBIntegrada(PeticionDTO pDto, BuscadorPeticionVpiDTO buscadorPeticionVpiDTO, int biId, String dsUrl, int actId, long rol){
		boolean agregarPeticion = true;
		log.debug("biId "+biId+", rol "+rol+", actId "+actId+", dsUrl "+dsUrl);
		if (biId != 0){
			if (actId != 0){
				pDto.setEstaEnBandeja(true);
				int codBuscador = buscadorPeticionVpiDTO.getCodBusqueda();
				log.debug("codBuscador "+codBuscador);
				switch(codBuscador){
					case BuscadorPeticionUtiles.COD_BUSCADOR_INBOUND:
						if (actId != 1023 && actId != 1028 && actId != 1030){
							pDto.setEstaEnActividadPermitida(false);log.debug("1 setEstaEnActividadPermitida(false)");
						}else{ 
							pDto.setEstaEnActividadPermitida(true);log.debug("2 setEstaEnActividadPermitida(true)");
						}
						break;
					case BuscadorPeticionUtiles.COD_BUSCADOR_OUTBOUND:
						//TODO: 06012010 - Raúl Ernesto Triviño Alvarado - Ajuste para corregir el acceso de peticiones outbound
						if (actId == 1039){
							pDto.setEstaEnActividadPermitida(true);log.debug("3 setEstaEnActividadPermitida(true)");
						}else{ 
							pDto.setEstaEnActividadPermitida(false);log.debug("4 setEstaEnActividadPermitida(false)");
						}
						//End TODO
						break;
					case BuscadorPeticionUtiles.COD_BUSCADOR_GES_OS:
						if (rol != 0){
							//TODO: 06012010 - Raúl Ernesto Triviño Alvarado - Ajuste para incluir el nuevo perfil
							if (rol == 5000 || rol == 30009){
								pDto.setEstaEnActividadPermitida(true);log.debug("5 setEstaEnActividadPermitida(true)");
							}else{ 
								pDto.setEstaEnActividadPermitida(false);log.debug("6 setEstaEnActividadPermitida(false)");
							}
						}else{
							agregarPeticion = false;
						}
						break;
					case BuscadorPeticionUtiles.COD_BUSCADOR_INSTALAR_EQUIPOS:
						if (actId == 1950){
							pDto.setEstaEnActividadPermitida(true);log.debug("3 setEstaEnActividadPermitida(true)");
						}else{ 
							pDto.setEstaEnActividadPermitida(false);log.debug("4 setEstaEnActividadPermitida(false)");
						}
						break;
				}
				pDto.setUrlBandeja(dsUrl);
			}else{
				agregarPeticion = false;
			}
		}else{
			pDto.setEstaEnBandeja(false);
		}
		log.debug("resultado agregarPeticion = "+agregarPeticion);
		return agregarPeticion;
	}
	
	
	
//	CR26362 - adocarmo - inicio
	public boolean existeBajaAsociada(Long numeroPeticion) {
		boolean existe=false;
	try{
		Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
		Traslado_solobaLocal traslado_solobaLocal = null;
		traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(numeroPeticion);
		existe = (traslado_solobaLocal.getPeti_baja() != null);
		//log.debug(">>>>>>>>>>>>>>>>>>>>>>>YA ENTRO LA BAJA: " + existe +  traslado_solobaLocal.getPeti_baja());
	} catch (FinderException e) {
		log.debug("No existe peti alta en la tabla traslado_solo_ba");
	} catch(NamingException e){
		log.error("",e);
	}
	return existe;
	}
//	CR26362 - adocarmo - fin	
	
	// CR26362 - adocarmo - inicio
	public Long petiAltaAsociada(Long numeroPeticion) {
		Long petiAlta = null;
	// Si entre a la actividad porque se canceló una peticion de alta solo BA que es par de la peticion actual
	// agrego en la bitacora una observacion
	try{
		Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
		Traslado_solobaLocal traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(numeroPeticion);
		petiAlta = traslado_solobaLocal.getPeti_alta();
		//act.setObservacion("La petición se anula ya que la peticion de ALTA por traslado " + peti_alta + " se canceló");
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		log.debug("Naming Exception en petiAltaAsosiada: " + e);
	} catch (FinderException e) {
		// no existe peticion en la tabla TRASLADO_SOLOBA
		log.debug("No existe peticion de alta asociada en la tabla TRASLADO_SOLOBA");
	}
	return petiAlta;
	}
	
	public void desbloqueaAltaTrasladoBa(Long numeroPeticion) throws ATiempoAppEx {
			try
			{
				Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
				Traslado_solobaLocal traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(numeroPeticion);
				String  cod_actividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_ESPERA_DESBLOQUEO");
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				if(traslado_solobaLocal.getPeti_alta()==null)
				{
					log.info("No ha llegado el alta .. no hay nada que desbloquear");
					return;
				}
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(traslado_solobaLocal.getPeti_alta(), cod_actividad);
		
				actDto.setObservacion("Ya llego la actividad de baja de traslado solo Ba para la peticion atiempo "+numeroPeticion);
				actividadEJB.terminar(actDto);
			}
			catch (FinderException e)
			{
				log.debug("No estoy cerrando traslado solo ba alta");	
			}
			catch (TnProcesoExcepcion e)
			{
				e.printStackTrace();
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS,e);
			} catch (NamingException e)
			{
				e.printStackTrace();
				throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
			}
		}
	
	public boolean nroOrigenyDestinoIguales(Long petiBaja, Long petiAlta) {
		
		String numPetiBaja;
		String numPetiAlta;
		boolean iguales = false;
        try {
        	PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
            PeticionKey key = new PeticionKey (petiBaja);
			PeticionLocal peticion = peticionHome.findByPrimaryKey (key);
			numPetiBaja = peticion.getNum_ide_nu_stb();
			
			key = new PeticionKey (petiAlta);
			peticion = peticionHome.findByPrimaryKey (key) ;
			numPetiAlta = peticion.getNum_ide_nu_stb();			
			
			iguales = numPetiBaja.equals(numPetiAlta);
			
			log.debug(">>>>>>>>>>>>>>>NRO DE LA BAJA:" + numPetiBaja);
			log.debug(">>>>>>>>>>>>>>>NRO DEL ALTA:" + numPetiAlta);
			
		} catch (FinderException e) {
          log.debug("Problemas al comparar si la peticion de alta y de baja por traslado solo ba tienen nros de telefono iguales");
		}
		catch (NamingException ne) {
	          log.debug("NamingException" + ne.getMessage());
		}
        
		return iguales;
	}
	
	public boolean existeBajaConIgualNro(Long petiAlta) {
		
		Traslado_solobaLocalHome traslado_solobaLocalHome;
		boolean existe = false;
		try {
			PeticionesDelegate delegate=new PeticionesDelegate();
			traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
			Traslado_solobaLocal traslado_solobaLocal = null;
			Long petiBaja;

			// pregunto si es peticion_alta
			traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(petiAlta);
			petiBaja = traslado_solobaLocal.getPeti_baja();
			
			existe = (petiBaja!=null) && delegate.nroOrigenyDestinoIguales(petiBaja,petiAlta);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.debug("Problemas de Naming en existeBajaConIgualNro: " + e);
		} catch (ATiempoAppEx e) {
			// TODO Auto-generated catch block
			log.debug("Problemas en existeBajaConIgualNro: " + e);
		} catch (FinderException e) {
			log.debug("No existe la peticion de alta!!!!");
		}
			
        return existe;
	}
	
	public boolean esBajaTrasladoSoloBA(Long idPeticion) {
		boolean es = false;
		
		try {
			PeticionesDelegate delegate=new PeticionesDelegate();		
			ArrayList psPeticion = delegate.listaDePsDePeticion(idPeticion);
		
			Iterator i = psPeticion.iterator();
			PsVsOcVO ps = null;
			while (i.hasNext() && !es) {
				ps = (PsVsOcVO) i.next();
				es = ps.getOpComId().equals(new Long(ComunInterfaces.ID_OP_BAJA_TRASLADO_SOLO_BA));
			}

		} catch (Exception e) {
			log.debug("Problemas al crear la instancia PeticionesDelegate");
		}
		return es;
	}	
	
	public boolean esBajaTrasladoSimple(Long idPeticion) {
		boolean es = false;
		
		try {
			PeticionesDelegate delegate=new PeticionesDelegate();		
			ArrayList psPeticion = delegate.listaDePsDePeticion(idPeticion);
		
			Iterator i = psPeticion.iterator();
			PsVsOcVO ps = null;
			while (i.hasNext() && !es) {
				ps = (PsVsOcVO) i.next();
				es = (ps.getOpComId().equals(new Long(ComunInterfaces.ID_OP_BAJA_TRASLADO)) || ps.getOpComId().equals(new Long(ComunInterfaces.ID_OP_ALTA_TRASLADO)) || ps.getOpComId().equals(new Long(ComunInterfaces.BAJA_POR_CAMBIO_PDTO_TRASL)) || ps.getOpComId().equals(new Long(ComunInterfaces.ALTA_POR_CAMBIO_PDTO_TRASL)));
			}

		} catch (Exception e) {
			log.debug("Problemas al crear la instancia PeticionesDelegate");
		}
		return es;
	}	
	
	public boolean pasoPorActividad(Long idPeticion, Integer idActividad) {
		boolean paso = false;
		Collection c = null;
		try {
			Peticion_flujoLocalHome petFlujoHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			c = petFlujoHome.findByActividadEstado(idPeticion, idActividad);

			//log.debug(">>>>>>>>>>>LARGO COLLECTION:" + c.size());
			
			Iterator i = c.iterator();
			if (i.hasNext()) {
				Peticion_flujoLocal local = (Peticion_flujoLocal) i.next();
				paso = local.getPefl_estado().equals("OK");
			
				//log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>ESTADO PASO: " + local.getPefl_estado());
			}
			
			//paso = !c.isEmpty();
		} catch (NamingException e1) {
			log.debug("Error al consultar si paso o no", e1);
		} catch (FinderException e) {
			log.debug("La Peticion petiNumero=" + idPeticion + " aun NO paso por la actividad Instalar "+ " Exception: " + e);
		}
		return paso;
	}
				
	// CR26362 - adocarmo - fin
	
	public String obtenerTipoOpCoPorPs(Long idPeticion) throws ATiempoAppEx{

		PeticionLocal peticion = null;
		String petiIdInstancia = null;
		String opCoTipo = null;
		try {
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);

		    peticion = peticionHome.findByPrimaryKey(new PeticionKey(idPeticion));
			
			Collection producto_servicio_peticionArray = peticion.getProducto_servicio_peticion();			
			
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
			Producto_servicio_peticionLocal local = null;
			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){

				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();

				int idFamilia = producto_servicio_peticionLocal.getFamiliaKey().faps_id.intValue();
				opCoTipo = producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo();
//				REQ BA NAKED adicion familia PC naked
				if(idFamilia == familiaPcBA || idFamilia==familiaBandaAncha  || idFamilia==familiaBandaAnchaNaked  || idFamilia==familiaPcBANaked){
					//TODO:  10022010 - Ajuste para que tome correctamente la operación comercial - RETA
					if(opCoTipo.equals("A")){
						log.info("opcotipo " + opCoTipo);
						break;
					}
					//End TODO
				}
				log.debug("Tiene tipo de Operacion Comercial: " + opCoTipo);
			}
		} catch (FinderException e) {
		    e.printStackTrace();
		    throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);
		}catch (NamingException e) {
		    e.printStackTrace();
		}
		log.debug("Se retorna en operacionComercial.."+opCoTipo);
		return opCoTipo;
	}
	
	public boolean getPeti_CRE(Long nroPet) {
		PeticionLocal local = null;
		String petiIdInstancia = "";
		boolean esCRE = false;
		
		try {
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			local = peticionHome.findByPrimaryKey(new PeticionKey(nroPet));
			petiIdInstancia = local.getPeti_id_instancia();
			Long grupoPs = null;
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();){
				Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator.next();
				
				Familia_producto_servicioKey familiaProductoServicio = local2.getFamiliaKey();
				Operacion_comercialLocal operacionComercial = local2.getOperacion_comercial();
				
				if (familiaProductoServicio.faps_id.intValue() == ComunInterfaces.familiaPcBA
						|| familiaProductoServicio.faps_id.intValue() == ComunInterfaces.familiaPcTV){
					if (operacionComercial.getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja)){
						esCRE = true;
					}
				}
			}
		} catch (FinderException e) {
			log.error("Error al buscar la peticion "  + nroPet + " - " + e.getMessage());
		}catch (NamingException e) {
		    e.printStackTrace();
		}
		
		return esCRE;
	}
	
	   /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#buscarEqInternetEquipado(java.lang.Long)
     */
    public Collection buscarEqInternetEquipado(Long nroPeticion) throws ATiempoAppEx {
		ArrayList equiposInternet = new ArrayList();
		
    	try{
	    	EquipoDelegate eDelegate = new EquipoDelegate();
			
			PeticionesDelegate delegate = new PeticionesDelegate();
			boolean esUmts = delegate.esGrupoUmts(nroPeticion);
			Collection listaEquipos = null;
			if(esUmts){
			    listaEquipos = eDelegate.recuperaEquiposVPiUmts(nroPeticion);
			}else{
			    listaEquipos = eDelegate.recuperaEquiposVPi(nroPeticion);
			}
			
			
			if(listaEquipos.size()>0){			       
			    String psIntEquipado=VpistbbaConfig.getVariable("PS_INTERNET_EQUIPADO");
		    	String[] listaIntEquipado=psIntEquipado.split(",");
			    
			    for(Iterator iterator=listaEquipos.iterator();iterator.hasNext();){
			    	ElementoDTO equipo = (ElementoDTO) iterator.next();
			    	
			    	for(int contPsIntEquipado=0;contPsIntEquipado<=listaIntEquipado.length-1;contPsIntEquipado++){
			    		int psCobroIncInt=Integer.parseInt(listaIntEquipado[contPsIntEquipado]);
			    		if (equipo.getPs().intValue() == psCobroIncInt){
			    			equiposInternet.add(equipo);
			    		}
			    	}		    	 
				}
			}
		}catch(ATiempoAppEx a){
			log.debug("No se ha podido crear la instancia de EquipoDelegate.");
			log.debug("Error mesage",a);
		}   	      	
		
		return equiposInternet;
    	
    }
    
    public void eliminarEqInternetEquipado(Long nroPeticion) throws ATiempoAppEx {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection equiposInternet = new ArrayList();
						
		try {
			equiposInternet = this.buscarEqInternetEquipado(nroPeticion);
			
			if (equiposInternet != null){
				    
				String query = "DELETE FROM ATIEMPO.ELEMENTO_PETICION WHERE PETI_NUMERO = ?";
				conn = DBManager.getConnection(DBManager.JDBC_VPISTBBA);	
				ps = conn.prepareStatement(query);			
				ps.setLong(1, nroPeticion.longValue());		
				ps.executeUpdate();
			}
				
		} catch (SQLException e) {
			log.error("Error en eliminarEqInternetEquipado  VPI: " + e);
			throw new ATiempoAppEx(e.getMessage());
		} finally {
			try{
				if (rs != null){
					rs.close();
				}
				if (ps != null){
					ps.close();
				}
				if (conn != null){
					conn.close();
				}
			}catch(SQLException e){
				log.error("Error en buscador VPI: " + e);
				throw new ATiempoAppEx(e.getMessage());	
			}
		}
    }
    
    /**
     * DAVID: se adiciona el siguiente código para RQ 28274, cobro incidencias.
     * 
     * Método para buscar las características de una petición específica
     * @author damartinezv
     *
     * TODO Para cambiar la plantilla de este comentario generado, vaya a
     * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
     */
    public ArrayList obtenerSubpeticionesDesdePeticion(Long petiNumero){
        ArrayList listaDeSubpeticiones=new ArrayList();
        
        ArrayList listaDeCaracteristicasPorPeticion=new ArrayList();
        try{
        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
        PeticionKey key = new PeticionKey (petiNumero) ;            
        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey (key) ;
        Peticion_atisLocal peticion_atisLocal=peticionLocal.getFk_01_pet_atis();
        

        Collection agrupacionAtis=peticion_atisLocal.getAgrupacion_atis();
        
            if(agrupacionAtis!=null){
                Iterator agrupacionAtisIt;
                for(agrupacionAtisIt=agrupacionAtis.iterator();agrupacionAtisIt.hasNext();){
                Agrupacion_atisLocal agrupacion_atisLocal=(Agrupacion_atisLocal)agrupacionAtisIt.next();
                Collection subPeticiones=agrupacion_atisLocal.getSubpeticion_atis();
                    if(subPeticiones!=null){
                        Iterator subPeticionesIt;
                        String[] agurpacionesPet= peticionLocal.getAgrupacionesString(",").split(",");
                        for(subPeticionesIt=subPeticiones.iterator();subPeticionesIt.hasNext();){
                            Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal)subPeticionesIt.next();
                			Subpeticion_atisKey subpeticion_atisKey = (Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey();
                			log.debug("subpeticion_atisKey. COD_PET_CD: "+subpeticion_atisKey.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd+", COD_AGR_SUB_NU: "+subpeticion_atisKey.fk_agru_sub_cod_agr_sub_nu+", COD_SUB_CD: "+subpeticion_atisKey.cod_sub_cd);
                            Integer agrupacion=((Agrupacion_atisKey)subpeticion_atisLocal.getFk_agru_sub().getPrimaryKey()).cod_agr_sub_nu;
                            for (int i = 0; i < agurpacionesPet.length; i++) {
								String agrupacionPet = agurpacionesPet[i];
								if(agrupacion.toString().equals(agrupacionPet)){
	                            	listaDeSubpeticiones.add(subpeticion_atisLocal);
	                            	break;
	                            }
							}
	                            
                        }
                    }
                }
            }
        }catch(NamingException e){
            log.debug("NamingException: ",e);
        }catch(FinderException e){
            log.debug("FinderException: ",e);
        }
        return listaDeSubpeticiones;
    }
    /**
     * DAVID: se adiciona el siguiente código para RQ 28274, cobro incidencias.
     * 
     * Método para obtener las características de una subPetición.
     * 
     * @param subpeticion_atisLocal
     * @return
     */
    public ArrayList obtenerCaracteristicasDeSubpeticion(Subpeticion_atisLocal subpeticion_atisLocal){
        
        Collection listaCaracteristicasPet=subpeticion_atisLocal.getSubpeticion_caracteristicas();
        Iterator listaCaracteristicasPetIt;
        ArrayList listaDeCaracteristicasSubPet=new ArrayList();
        if(listaCaracteristicasPet!=null){
            for(listaCaracteristicasPetIt=listaCaracteristicasPet.iterator();listaCaracteristicasPetIt.hasNext();){
                Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal=(Subpeticion_caracteristicasLocal)listaCaracteristicasPetIt.next();
                listaDeCaracteristicasSubPet.add(subpeticion_caracteristicasLocal);
            }
        }
        return listaDeCaracteristicasSubPet;
    }
    
    /*
     * Verifica si el ps PDTI está en estado 3 en la tabla EstadoPsPeticion o si hay un ps de BA familia 301 en la misma petición con estado
     * 3 en la misma tabla.
     *  (sin Javadoc)
     * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#verificarEstadoPsPeticionPDTI(java.lang.Long)
     */
	public Integer verificarEstadoPsPeticionPDTI(Long nroPeticion){			   
        
        /**
         * La lógica es la siguiente:
         * Si se quiebra un ps de BA familia 301, entonces se deben deshabilitar IngresoModems e IngreosEquipos, porque PDTI depende de BA.
         * No aplica si el ps es de BA = 2.
         * 
         * Si el ps quebrado es PDTI, entonces solo se deshabilta la pestaña IngresoEquipos.
         */
		PeticionLocal peticionLocal = null;
		PeticionLocalHome peticionLocalHomePDTI=null;
		Integer resultado=null;
		Grpe_PsLocal grpeLocal = null;
		
		boolean hayPDTIEn3=false;
		boolean hayBAEn3=false;
		try {
			peticionLocalHomePDTI = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
            peticionLocal = peticionLocalHomePDTI.findByPrimaryKey(new PeticionKey(nroPeticion));
            Grpe_PsLocalHome grpeLocalHome = (Grpe_PsLocalHome)HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
            
            
            for (Iterator iterator = peticionLocal.getProducto_servicio_peticion().iterator(); iterator.hasNext();) {
                Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iterator.next();
                Producto_servicioLocal producto_servicioLocal = producto_servicio_peticionLocal.getProducto_servicio();
                
                
                
                Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey)producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey();

                Collection listaEstado = producto_servicio_peticionLocal.getEstado_ps_peticion();            
                Producto_servicioKey psKey=(Producto_servicioKey)producto_servicioLocal.getPrimaryKey();                
                try{
                	grpeLocal = grpeLocalHome.findGrupoByPS(psKey.ps_id);
                	//Si el ps es de tipo PDTI, se evalua si tiene 3 en la tabla EstadoPsPeticion. si tiene 3 se deshabilita la pestaña ingreso equipos.                    
                	if(grpeLocal!=null){
                    	if(grpeLocal.getGrpe_id()!=null&&grpeLocal.getGrpe_id().longValue()==ID_GRUPO_PDTI){ 
                        	if (listaEstado!=null&&listaEstado.size() > 0) { 
                        		log.debug("Hay ps PDTI con estado ps peticion");
                        		Estado_ps_peticionLocal estado_ps_peticionLocal = (Estado_ps_peticionLocal) listaEstado.iterator().next();
                                if (estado_ps_peticionLocal.getCod_estado_cierre().intValue() == ComunInterfaces.estadoCierreError) {
                                	log.debug("El ps de equipo PDTI si tiene quiebre o estado 3 en la tabla EstadoPSPeticion");
                                	hayPDTIEn3=true;
                                }
                        	}                            
                        }
                    }
                }catch (FinderException e1) {
                    // TODO Auto-generated catch block
                    log.debug("El ps: "+psKey.ps_id+" no representa a ningún equipo.");
                }               
             
                //REQ BA NAKED adicion familia pc naked
                if(familia_producto_servicioKey.faps_id.intValue()==familiaPcBA || familia_producto_servicioKey.faps_id.intValue()==familiaPcBANaked){
                	if (listaEstado!=null&&listaEstado.size() > 0) { 
                		log.debug("Hay ps BA con estado ps peticion");
                		Estado_ps_peticionLocal estado_ps_peticionLocal = (Estado_ps_peticionLocal) listaEstado.iterator().next();
                        if (estado_ps_peticionLocal.getCod_estado_cierre().intValue() == ComunInterfaces.estadoCierreError) {
                        	log.debug("El ps de BA si tiene quiebre o estado 3 en la tabla EstdoPSPeticion");
                        	hayBAEn3=true;
                        }
                	}
                }
            }  
            
            if(hayPDTIEn3&&hayBAEn3){
            	resultado = new Integer(1);
            }else if(!hayPDTIEn3&&hayBAEn3){
            	resultado = new Integer(2);
            }else if(hayPDTIEn3&&!hayBAEn3){
            	resultado = new Integer(3);
            }
            
        }catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (FinderException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return resultado;
	
	}
	
	/**
	 * Metodo que retorna true en caso de que la peticion cuyo numero es nroPet sea del grupo PDTI
	 */
	public boolean esGrupoPdti (Long nroPet) throws ATiempoAppEx{
	    PeticionLocal peticion = null;
		String petiIdInstancia = null;
		boolean esPdti = false;
		try {
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);

		    peticion = peticionHome.findByPrimaryKey(new PeticionKey(nroPet));
			
			Collection producto_servicio_peticionArray = peticion.getProducto_servicio_peticion();			
			
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;

			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				//Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				//Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				//if(pasaPSyOcXActividad(nroPet,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
					Long grp = getGrupoPS(producto_servicio_peticionLocal.getPsId());
				    if (grp != null && grp.longValue()== ComunInterfaces.ID_GRUPO_PDTI) {
						esPdti = true;
						break;
					}
				//}
			}
			
		
		} catch (FinderException e) {
		    e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);

		}
		catch (NamingException ne) {
			log.error("NamingException "  + ne.getMessage());
		}		
		log.info("es grupo PDTI;...."+ esPdti);
		return esPdti;
	    
	}
	
	/**
	 * Metodo que valida si se trata de un traslado a UMTS con Oc 48 o 49
	 */
	public boolean validaTrasladoAUmts(Long nroPet,Iterator iterTemp) throws ATiempoAppEx{
	    PeticionLocal peticion = null;
		boolean resultado=false;
		PeticionLocal local = null;		
		try {
			//Iterator iterTemp = act.getPsOk().iterator(); //Debe venir al menos un ps
			PsVsOcVO psTemp= null;
			Long grupoPs = null;
			while(iterTemp.hasNext()){
				psTemp= (PsVsOcVO) iterTemp.next();
				grupoPs = getGrupoPS(psTemp.getPsId());				
				if (grupoPs != null && grupoPs.equals(new Long(ComunInterfaces.ID_GRUPO_UMTS))) {
					log.debug("validaAltaDuoUmts es Grupo UMTS = ");
					if(psTemp.getOpComId().longValue()==ComunInterfaces.ALTA_POR_CAMBIO_PDTO_TRASL
							|| psTemp.getOpComId().longValue() == ComunInterfaces.BAJA_POR_CAMBIO_PDTO_TRASL ){
						log.debug("Tengo PC Traslado de Grupo UMTS = ");
						resultado=true;
					}
				}
			}				
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new ATiempoAppEx (ATiempoAppEx.CREATE, e);
		}		
		log.debug("Es traslado a UMTS:.."+ resultado);
		return resultado;	    
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#recuperaIdActuacionAgendaSC(java.lang.Long)
	 */
	public AgendaScDTO recuperaIdActuacionAgendaSC(Long petiNumero) {
		
		//String strIdsActuaciones = null;
		//Agenda_scKey agenda_scKey = null;
		AgendaScDTO agendaScDTO = new AgendaScDTO();
		
		try {
			PeticionLocalHome peticionLocalHome;
			PeticionLocal peticionLocal;
			
			Agenda_scLocalHome agenda_scLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
//			peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//			PeticionKey peticionKey = new PeticionKey(petiNumero);
//			peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
//			Collection agendaSCList = peticionLocal.getAgenda_sc();
//			
//			for( Iterator agendaSCListIt = agendaSCList.iterator(); agendaSCListIt.hasNext(); ){
//				Agenda_scLocal agenda_scLocal = (Agenda_scLocal)agendaSCListIt.next();
//				if(agenda_scLocal.getEstado().intValue()== 1){
//				//if(agenda_scLocal.get)
//					agenda_scKey = (Agenda_scKey)agenda_scLocal.getPrimaryKey();
//					agenda_scLocalHome.
//				}
//			}
			
			//Agenda_scLocal agenda_scLocal = agenda_scLocalHome.findFechasByPetiNum(petiNumero);
			Agenda_scLocal agenda_scLocal = agenda_scLocalHome.findFechasByPetiNumFecha(petiNumero);
			Agenda_scKey agenda_scKey = (Agenda_scKey) agenda_scLocal.getPrimaryKey();
			
			agendaScDTO.setIdActucaion(agenda_scKey.id_actuacion);
			agendaScDTO.setPetiNumero(agenda_scLocal.getPeti_numero());
			agendaScDTO.setEstado(agenda_scLocal.getEstado());
			agendaScDTO.setFechaReagm(agenda_scLocal.getFecha_reagm());
			agendaScDTO.setFechaMod(agenda_scLocal.getFecha_mod());
			agendaScDTO.setNombreContratista(agenda_scLocal.getNombre_contratista());
			agendaScDTO.setQuiebres(agenda_scLocal.getQuiebre());
			return agendaScDTO;

		}catch(NamingException e){
			log.debug("Error en recuperaIdActuacionAgendaSC ", e);
			return null;
		}catch(FinderException e){
			log.debug("Error en recuperaIdActuacionAgendaSC ", e);
			return null;
		}
	} 
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#getEstadoAgendaSC(java.lang.Long)
	 */	
	public boolean getEstadoAgendaSC( Long idPeticion ){
		
		try{
			Agenda_scLocalHome agenda_scLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			Collection  collection =  agenda_scLocalHome.findByPetiNumero(idPeticion);
			
			for( Iterator collectionIterator = collection.iterator(); collectionIterator.hasNext();){
				Agenda_scLocal agenda_scLocal = (Agenda_scLocal) collectionIterator.next();
				//if(agenda_scLocal.getEstado().intValue() != 1){
				if(agenda_scLocal.getFecha_reagm() != null){
					return true;
				}
			}	
		}catch(FinderException e){
			log.debug("Error estadoAgendaSc ", e);
			return false;
		} catch (NamingException e) {
			log.debug("Error en estadoAgendaSc ", e);
			return false;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#setearFechaAgendaSC(java.lang.String, java.lang.String)
	 */
	public boolean setearFechaAgendaSC( String idActuacion, String fechaPactada, boolean instalacionServicios, Long petiNumero ) {

		
		log.info("Se ingresa al metodo setearFechaAgendaSC  fechaAgendamiento: "+fechaPactada+" idActuacion: "+idActuacion+ " instalacionServicios: "+instalacionServicios);
		
		DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date fechaDate = null;
		Timestamp fechaTimeStamp = null; 
		
		if(fechaPactada != null || fechaPactada.equalsIgnoreCase("")){
			try{
				fechaDate = simpleDateFormat.parse(fechaPactada);
			}catch (java.text.ParseException e) {
				log.debug("Error al realizar parse de la fecha en reagendamiento AgendaSC : "+e.toString());
			}
		
			fechaTimeStamp = new Timestamp(fechaDate.getTime());
		}
		
		try{
			Agenda_scLocalHome agenda_scLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionKey peticionKey = new PeticionKey(petiNumero);
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			
			if(instalacionServicios){
				peticionLocal.setEstado_agend_sc(new Integer(1));
			}else{
				peticionLocal.setEstado_agend_sc(new Integer(0));
			}

			Agenda_scKey agenda_scKey = new Agenda_scKey(idActuacion);
			Agenda_scLocal agenda_scLocal =  agenda_scLocalHome.findByPrimaryKey(agenda_scKey);
			
			if(fechaPactada != null || fechaPactada.equalsIgnoreCase("")){
				agenda_scLocal.setFecha_reagm(fechaTimeStamp);
			}
			
			return true;	
		}catch(FinderException e){
			log.debug("Error estadoAgendaSc ", e);
			return false;
		} catch (NamingException e) {
			log.debug("Error en estadoAgendaSc ", e);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#recuperarListaAgendaSC()
	 */
	public ArrayList recuperarListaAgendaSC(Long petiNumero) {

		AgendaScDTO agendaScDTO = null;
		ArrayList listaRetorno = new ArrayList();
		
		try {
			Agenda_scLocalHome agenda_scLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			Collection agendaScList = agenda_scLocalHome.findByPetiNumero( petiNumero );
			//ArrayList agendaScList = (ArrayList) agenda_scLocalHome.findByPetiNumero( petiNumero );
			//Collections.sort(agendaScList);
			
			for( Iterator agendaScIterator = agendaScList.iterator(); agendaScIterator.hasNext(); ){
				Agenda_scLocal agenda_scLocal = (Agenda_scLocal)agendaScIterator.next();
				
				agendaScDTO = new AgendaScDTO();
				
				Agenda_scKey agenda_scKey = (Agenda_scKey) agenda_scLocal.getPrimaryKey();
				
				agendaScDTO.setIdActucaion(agenda_scKey.id_actuacion);
				agendaScDTO.setEstado(agenda_scLocal.getEstado());
				agendaScDTO.setPetiNumero(agenda_scLocal.getPeti_numero());
				agendaScDTO.setFechaReagm(agenda_scLocal.getFecha_reagm());
				agendaScDTO.setFechaMod(agenda_scLocal.getFecha_mod());
				agendaScDTO.setNombreContratista(agenda_scLocal.getNombre_contratista());
				List listaCatalogoCausal = null;
				String temp = agenda_scLocal.getQuiebre();
				if(temp!=null){
					listaCatalogoCausal = this.obtenerDescripcionQuiebresAgendaSC(agenda_scLocal.getQuiebre());
					String strConcatQuiebre = "";
					
					if( listaCatalogoCausal != null && listaCatalogoCausal.size() > 0 ){
						String[] strQuiebre = (agenda_scLocal.getQuiebre().replaceAll("\\d","")).split(",");
						if(strQuiebre.length > 0){
							int cont = 0;
							for (Iterator iter = listaCatalogoCausal.iterator(); iter.hasNext();) {
								Catalogo_causalLocal catalogo_causalLocal = (Catalogo_causalLocal) iter.next();
								strConcatQuiebre += strQuiebre[cont] +  catalogo_causalLocal.getDescripcion_causal() + ",";
								cont++;
							}
							agendaScDTO.setQuiebres(strConcatQuiebre.substring(0,strConcatQuiebre.length()-1));
						}else{
							agendaScDTO.setQuiebres(agenda_scLocal.getQuiebre());
						}
					}else{
						agendaScDTO.setQuiebres(agenda_scLocal.getQuiebre());
					}
				}else{
					agendaScDTO.setQuiebres(agenda_scLocal.getQuiebre());
				}
				
				listaRetorno.add(agendaScDTO);
			}
			//Collections.sort(listaRetorno);
			return listaRetorno;
		}catch(NamingException e){
			log.debug("Error en recuperarListaAgedaSC ", e);
			return null;
		}catch(FinderException e){
			log.debug("Error en recuperarListaAgendaSC ", e);
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#buscarModemWifi(java.lang.Long)
	 */
	public String buscarModemWifi(Long idPd) {
		
		Ps_Tipo_ModemLocal psTipoModemLocal = null;
		String strRetorno = null;
		Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome;
		try {
			ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
			psTipoModemLocal = ps_Tipo_ModemLocalHome.findByNroPs( idPd );
			strRetorno = psTipoModemLocal.getPd_id()+"/"+psTipoModemLocal.getId_tipo_modem();
		} catch (NamingException e) {
			log.error("Error buscarModenWifi NamingException: ");
			return null;
		} catch (FinderException e) {
			log.debug("BuscarModenWifi FinderException ");
			return null;
		}
		return strRetorno;
	} 	
	
	/**
	 * 
	 *
	 */
	public Timestamp fechaCierrePsPsTroncalSip( Long numeroPeticion ){
		boolean flag = false;
		
		String strPsTroncalSip = VpistbbaConfig.getVariable("PS_TRONCAL_SIP");
		String[] arryPsTroncalSip = strPsTroncalSip.split(",");
		
		try{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(numeroPeticion));
			
			Collection collectionPsPeticion = peticionLocal.getProducto_servicio_peticion();
			
			if(collectionPsPeticion.size() > 0){
				for (Iterator iter = collectionPsPeticion.iterator(); iter.hasNext();) {
					Producto_servicio_peticionLocal productoServicio = (Producto_servicio_peticionLocal) iter.next();
					for (int i = 0; i < arryPsTroncalSip.length ; i++) {
						if(productoServicio.getPsId().equals(new Long(arryPsTroncalSip[i]))){
							//flag = true;
							return productoServicio.getPspe_fecha_fin();
						}
					}
				}
			}
			return null;
		}catch(NamingException ne){
			log.debug("PeticionesServicesBean: esPsTroncalSip: "+ne);
			return null;
		} catch (FinderException fe) {
			log.debug("PeticionesServicesBean: esPsTroncalSip: "+fe);
			return null;
		}
	}
	
	public boolean tienePCAltaLB (Long idPd){
		boolean esPCAlta = false;
		
		try{
			PeticionKey peticionKey = new PeticionKey(idPd);
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			
			Collection pspList = peticionLocal.getProducto_servicio_peticion();
			for (Iterator pspIterator = pspList.iterator(); pspIterator.hasNext();){
				Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal)pspIterator.next();
				
				if (pspLocal.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaPcLinea || pspLocal.getFamiliaKey().faps_id.intValue() ==  ComunInterfaces.familiaIP){
					Operacion_comercialLocal operacionComercialLocal =  pspLocal.getOperacion_comercial();
					
					if (operacionComercialLocal.getOpco_tipo().equalsIgnoreCase(ComunInterfaces.opCoTipoAlta)){
						esPCAlta = true;
						break;
					}
				}
			}
		}catch(NamingException ex){
			log.debug("Error en: tienePCAltaLB: "+ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error en: tienePCAltaLB: "+ex);
			ex.printStackTrace();			
		}		
		return esPCAlta;
	}
	/**
	 * DAVID: recupera la lista de mensajes ACS recibidos.
	 * @param petiNumero
	 * @return
	 */
	public ArrayList recuperarListaMensajesACS(Long petiNumero) {

		MensajeACSDTO mensajeACSDTO= null;
		ArrayList listaRetorno = new ArrayList();
		
		try {
			Mensaje_ACSLocalHome mensaje_ACSLocalHome = (Mensaje_ACSLocalHome) HomeFactory.getHome(Mensaje_ACSLocalHome.JNDI_NAME);
			Collection mensajeACSList = mensaje_ACSLocalHome.findByPetiNumero( petiNumero );
			
			for( Iterator mensajeACSIterator = mensajeACSList.iterator(); mensajeACSIterator.hasNext(); ){
				Mensaje_ACSLocal mensaje_ACSLocal = (Mensaje_ACSLocal)mensajeACSIterator.next();				
				mensajeACSDTO = new MensajeACSDTO();				
				TR137S tr137s = (TR137S)XMLUtilities.unmarshall(mensaje_ACSLocal.getXml());				
				mensajeACSDTO.setPetiNumero(mensaje_ACSLocal.getPeti_numero());
				mensajeACSDTO.setTr137s(tr137s);
				mensajeACSDTO.setFechaIngreso(mensaje_ACSLocal.getFecha_ingreso());
				listaRetorno.add(mensajeACSDTO);
			}
			return listaRetorno;
		}catch(ATiempoAppEx e){
			log.debug("ATiempoAppEx en recuperarListaMensajesACS ",e);
			return null;
		}catch(NamingException e){
			log.debug("NamingException en recuperarListaMensajesACS "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en recuperarListaMensajesACS "+e.toString());
			return null;
		}
	}
	/**
	 * DAVID: recupera la lista de mensajes SMS-ACS recibidos.
	 * @param petiNumero
	 * @return
	 */
	public ArrayList recuperarListaMensajesSMSACS(Long petiNumero) {

		MensajeSMSACSDTO mensajeSMSACSDTO  = null;
		ArrayList listaRetorno = new ArrayList();
		
		try {
			Mensaje_SMS_ACSLocalHome mensaje_SMS_ACSLocalHome = (Mensaje_SMS_ACSLocalHome) HomeFactory.getHome(Mensaje_SMS_ACSLocalHome.JNDI_NAME);
			Collection mensajeSMSACSList = mensaje_SMS_ACSLocalHome.findByPetiNumero(petiNumero);
			
			for( Iterator mensajeACSSMSIterator = mensajeSMSACSList.iterator(); mensajeACSSMSIterator.hasNext(); ){
				Mensaje_SMS_ACSLocal mensaje_SMS_ACSLocal = (Mensaje_SMS_ACSLocal)mensajeACSSMSIterator.next();				
				mensajeSMSACSDTO = new MensajeSMSACSDTO();				
				TRSMSE trsmse = (TRSMSE)XMLUtilities.unmarshall(mensaje_SMS_ACSLocal.getXml());	
				mensajeSMSACSDTO.setPetiNumero(mensaje_SMS_ACSLocal.getPeti_numero());
				mensajeSMSACSDTO.setFechaEnvio(mensaje_SMS_ACSLocal.getFecha_envio());
				mensajeSMSACSDTO.setTrsmse(trsmse);
				mensajeSMSACSDTO.setUsuario(mensaje_SMS_ACSLocal.getUsuario());
				listaRetorno.add(mensajeSMSACSDTO);
			}
			return listaRetorno;
		}catch(ATiempoAppEx e){
			log.debug("ATiempoAppEx en recuperarListaMensajesSMSACS ",e);
			return null;
		}catch(NamingException e){
			log.debug("NamingException en recuperarListaMensajesSMSACS "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en recuperarListaMensajesSMSACS "+e.toString());
			return null;
		}
	}
	
	/**
	 * Metdo que obtiene la descripcion de un quiebre para agendasc
	 * 
	 * @param codQuiebre
	 * @return
	 */
	private List obtenerDescripcionQuiebresAgendaSC( String codQuiebres ){
		
		String[] codQuiebresArray = codQuiebres.split("\\D");
		Catalogo_causalLocalHome catalogo_causalHome;
		List listaRetorno = null;
		
		try {
			catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome (Catalogo_causalLocalHome.JNDI_NAME);
			catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			listaRetorno = new ArrayList();
			for (int i = 0; i < codQuiebresArray.length; i++) {
				if(!codQuiebresArray[i].equals("")){
					try {
						Catalogo_causalLocal catalogo_causalLocal = catalogo_causalHome.findByPrimaryKey(new Catalogo_causalKey(new Long(codQuiebresArray[i])));
						listaRetorno.add(catalogo_causalLocal);
					} catch (NumberFormatException ne) {
						log.debug("PeticionesServicesBena: obtenerDescripcionQuiebresAgendaSC: valor codigoQuiebre ["+codQuiebresArray[i]+"]: "+ne);
						return null;
					} catch (FinderException fe) {
						log.debug("PeticionesServicesBena: obtenerDescripcionQuiebresAgendaSC: no se encontro quiebre: "+fe);
						return null;
					}
				}
			}
			return listaRetorno;
		} catch (NamingException e) {
			log.debug("PeticionesServicesBena: obtenerDescripcionQuiebresAgendaSC: NamingException "+e);
			return null;
		}
	}
	/**
	 * Raúl: recupera la lista de mensajes configuracion modem ACS.
	 * @param petiNumero
	 * @return
	 */
	public ArrayList recuperarListaMensajesConfModemsACS(Long petiNumero) {
		
		ArrayList listaRetorno = new ArrayList();
		MensajeConfModemACSDTO mensajeConfModemACSDTO = null;
		
		try {
			Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome =(Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
			Collection tmpModemList = mensajeConfACSLocalHome.findByPetiNumero(petiNumero);
			
			Mensaje_estado_baLocalHome mensajeEstadoBALocalHome = (Mensaje_estado_baLocalHome)HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			
			for( Iterator tmpModemIter = tmpModemList.iterator(); tmpModemIter.hasNext(); ){
				Mensaje_conf_ACSLocal mensajeConfACSLocal = (Mensaje_conf_ACSLocal)tmpModemIter.next();		
				mensajeConfModemACSDTO = new MensajeConfModemACSDTO();
				try{
					TR135S tr135s = (TR135S)XMLUtilities.unmarshall(mensajeConfACSLocal.getXml());				
					
					Mensaje_estado_baKey mensajeBAKey = new Mensaje_estado_baKey(new Long(tr135s.getId()));
					Mensaje_estado_baLocal mensajeEstadoBALocal = mensajeEstadoBALocalHome.findByPrimaryKey(mensajeBAKey);
					
					mensajeConfModemACSDTO.setId(new Long(tr135s.getId()));
					mensajeConfModemACSDTO.setPetiNumero(petiNumero);
					mensajeConfModemACSDTO.setFechaEnvio(mensajeConfACSLocal.getFecha_ingreso());
					mensajeConfModemACSDTO.setModemProcesado(mensajeConfACSLocal.getSerial_modem());
					
					if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.operacionParDesactivar).toString())){
						mensajeConfModemACSDTO.setOperacionMensaje("Baja");
					}else if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.operacionParActivar).toString())){
						mensajeConfModemACSDTO.setOperacionMensaje("Alta");
					}else if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.altaMigracionPS).toString())){
						mensajeConfModemACSDTO.setOperacionMensaje("Traslado");
					}else if (mensajeConfACSLocal.getAccion().equals("Nuevo")){
						mensajeConfModemACSDTO.setOperacionMensaje("Alta");
					}else{
						mensajeConfModemACSDTO.setOperacionMensaje("No hay acción definida");
					}
					
					if (tr135s.getErrorCode() != null && tr135s.getErrorCode().equals("1")){
						mensajeConfModemACSDTO.setEstadoMensaje("Error");
					}else{
						mensajeConfModemACSDTO.setEstadoMensaje("OK");
					}
					
					
					mensajeConfModemACSDTO.setDescMensaje(tr135s.getErrorDescription());
					
					listaRetorno.add(mensajeConfModemACSDTO);
				}catch(ClassCastException ex){
					log.debug("El mensaje no corresponde a la tr-135-s, no se procesa");
				}catch(ATiempoAppEx ex){
					log.debug("ATiempoAppEx en recuperarListaMensajesConfModemsACS ");
				}
			}
			return listaRetorno;
		}catch(NamingException e){
			log.debug("NamingException en recuperarListaMensajesConfModemsACS "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en recuperarListaMensajesConfModemsACS "+e.toString());
			return null;
		}
	}
	
	
	
	HashMap propertiesHash = new HashMap();
	Date dateCache = null;
	long maxDiff = 48*3600*1000; // 48 Hrs.
	
	/**
	 * Elimina el cache cada 48 horas
	 */
//	private void refreshCachePropertiesBD() {
//		if ( dateCache == null ) {
//			dateCache = new Date();
//			return;
//		}
//		long diffMS = new Date().getTime() - dateCache.getTime();
//		if ( diffMS < maxDiff )
//			return;
//		dateCache = new Date();
//		propertiesHash = new HashMap();
//		
//	}
	
	
	/**
	 * DAVID: Nuevo método para recuperar parámetros de la nueva tabla PropertiesBD.
	 * @param codigo
	 * @return
	 */
	public String recuperarParametroFromPropertiesBD(String codigo) {
		String valor=null;
		
//		refreshCachePropertiesBD();
//		String valor=(String) propertiesHash.get( codigo );
		
//		if ( valor!=null )
//			return valor;
		
		
		try {
			Properties_BDLocalHome properties_BDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal properties_BDLocal = properties_BDLocalHome.findByPrimaryKey(codigo);			
			valor=properties_BDLocal.getValor();
//			propertiesHash.put(codigo,valor);
		}catch(NamingException e){
			log.debug("NamingException en recuperarParametroFromPropertiesBD "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en recuperarParametroFromPropertiesBD "+e.toString());
			return null;
		}
		return valor;
	}
	
	/**
	 * DAVID: Método para extraer un número celular a partir de los 3 teléfonos de contacto.
	 * @param idPeticion
	 * @return
	 */
	public String extraerCellPhone(Long idPeticion){
		
		String telefonoCelular=null;
		try{

			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);			
			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));

			String telefono1=peticionLocal.getTel_cot_ds();
			String telefono2=peticionLocal.getTel_cot_ds_1();
			String telefono3=peticionLocal.getTel_cot_ds_2();
			
			if(telefono1!=null){
				if(telefono1.matches("3"+"\\d{9}")){
					log.debug("El celular es el teléfono de contacto 1...");
					telefonoCelular = telefono1;
				}
			}if(telefono2!=null){
				if(telefono2.matches("3"+"\\d{9}")){
					log.debug("El celular es el teléfono de contacto 2...");
					telefonoCelular = telefono2;
				}
			}if(telefono3!=null){
				if(telefono3.matches("3"+"\\d{9}")){
					log.debug("El celular es el teléfono de contacto 1...");
					telefonoCelular = telefono3;
				}
			}			
			
		}catch(NamingException e){
			log.debug("NamingException en metodo extraerCellPhone(): "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en metodo extraerCellPhone(): "+e.toString());
			return null;
		}
		
		return telefonoCelular;
	}
	/**
	 * DAVID: Devuelve la fecha del último SMS enviado para una petición de Auto instalación.
	 * @param petiNumero
	 * @return
	 */
	public Long recuperaUltimaFechaSMSACS(Long petiNumero){
		Long fechaUltimoSMS=null;
		try {
			Mensaje_SMS_ACSLocalHome mensaje_SMS_ACSLocalHome = (Mensaje_SMS_ACSLocalHome) HomeFactory.getHome(Mensaje_SMS_ACSLocalHome.JNDI_NAME);
			Mensaje_SMS_ACSLocal mensaje_SMS_ACSLocal = mensaje_SMS_ACSLocalHome.findByFechaMax(petiNumero);
			Timestamp timestamp = mensaje_SMS_ACSLocal.getFecha_envio();
			fechaUltimoSMS=new Long(timestamp.getTime());
		}catch(NamingException e){
			log.debug("NamingException en recuperaUltimaFEchaSMSACS: "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en recuperaUltimaFEchaSMSACS: "+e.toString());
			return null;
		}		
		return fechaUltimoSMS;
	}
	/**
	 * DAVID: Retorna el último Mensaje_ACSLocal
	 * @param petiNumero
	 * @return
	 */
	public Mensaje_ACSLocal recuperaUltimoMensajeACS(Long petiNumero){
		Mensaje_ACSLocal mensaje_ACSLocal=null;
		try {
			Mensaje_ACSLocalHome mensaje_ACSLocalHome = (Mensaje_ACSLocalHome) HomeFactory.getHome(Mensaje_ACSLocalHome.JNDI_NAME);
			mensaje_ACSLocal = mensaje_ACSLocalHome.findByFechaMax(petiNumero);
		}catch(NamingException e){
			log.debug("NamingException en recuperaUltimaFEchaSMSACS: "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en recuperaUltimaFEchaSMSACS: "+e.toString());
			return null;
		}		
		return mensaje_ACSLocal;
	}
	
	/**
	 * Retorna true si la petición tiene un ps de BA con OC=92, autoInstalación.
	 * @param petiNumero
	 * @return
	 */
	public boolean esAutoInstalacionSoloBA(Long petiNumero){
		
		boolean esAutoInstalacionSoloBA=false;
		try{
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection pspetList=producto_servicio_peticionLocalHome.findAllByPetiNumero(petiNumero);
			Iterator pspetListIt=null;
			for(pspetListIt=pspetList.iterator();pspetListIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)pspetListIt.next();
				Long operacionComercial = producto_servicio_peticionLocal.getIdOperacionComercial();
				Familia_producto_servicioKey familia_producto_servicioKey = producto_servicio_peticionLocal.getFamiliaKey();
				//Si se trata de OC=92 y el ps de la petición es de BA, 301, entonces es autoinstalación.
				
				Long OCAutoInstalacion=new Long(recuperarParametroFromPropertiesBD(OPCO_AUTOINSTALACION));
				//REQ NAKED se adiciona la familia PC naked
				if(operacionComercial.longValue()==OCAutoInstalacion.longValue()&& (familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcBA || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcBANaked)){
					log.debug("Se trata de una petición con autoInstalación.");
					esAutoInstalacionSoloBA = true;
					break;
				}
			}

		}catch(FinderException e){
			log.debug("FinderException en esAutoInstalacionSoloBA(): "+e.toString());
			return false;
		}catch(NamingException e){
			log.debug("NamingException en esAutoInstalacionSoloBA(): "+e.toString());
			return false;
		}
		return esAutoInstalacionSoloBA;
	}
	/**
	 * Retorna true si la petición tiene un ps de retencion con OC=96, autoInstalación.
	 * @param petiNumero
	 * @return
	 */
	public boolean esPSDeRetencion(Long petiNumero){
		
		boolean esPsRetencion=false;
		try{
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection pspetList=producto_servicio_peticionLocalHome.findAllByPetiNumero(petiNumero);
			Iterator pspetListIt=null;
			for(pspetListIt=pspetList.iterator();pspetListIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)pspetListIt.next();
				Long operacionComercial = producto_servicio_peticionLocal.getIdOperacionComercial();
				Familia_producto_servicioKey familia_producto_servicioKey = producto_servicio_peticionLocal.getFamiliaKey();
				//Si se trata de OC=92 y el ps de la petición es de BA, 301, entonces es autoinstalación.
				
				Long OCAutoInstalacion=new Long(recuperarParametroFromPropertiesBD(OPCO_AUTOINSTALACION));
				Long psIdRetencion=new Long(recuperarParametroFromPropertiesBD(PS_ID_RETENCION));
				//REQ BA NAKED adicion familia pc naked
				if(psIdRetencion.longValue()==producto_servicio_peticionLocal.getPsId().longValue()&&operacionComercial.longValue()==OCAutoInstalacion.longValue()&& (familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcBA || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcBANaked)){
					log.debug("Se trata de una petición con autoInstalación.");
					esPsRetencion = true;
					break;
				}
			}

		}catch(FinderException e){
			log.debug("FinderException en esAutoInstalacionSoloBA(): "+e.toString());
			return false;
		}catch(NamingException e){
			log.debug("NamingException en esAutoInstalacionSoloBA(): "+e.toString());
			return false;
		}
		return esPsRetencion;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#tieneCodActividadNoAvance(java.lang.String)
	 */
	public boolean tieneCodActividadNoAvance(String codActividad) {
		boolean tieneActNoAvance = false;
	
		String actividadesNoAvance [] = VpistbbaConfig.getVariable("COD_ACTIVIDAD_NO_AVANCE").split(",");
		for (int i=0; i<actividadesNoAvance.length;i++){
			if (codActividad.equals(actividadesNoAvance[i])){
				tieneActNoAvance = true;
				break;
			}
		}
		
		return tieneActNoAvance;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#obtenerEstadoMensajeAgendaSC(java.lang.Long)
	 */
	public TR703S obtenerMensajeAgendaSC(Long correlativoID) {
		TR703S mensaje = null;
		try{
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome) HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scKey tempAgendaSCKey = new Tmp_agenda_scKey(correlativoID);
			Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.findByPrimaryKey(tempAgendaSCKey);
			TR703S tr703s = (TR703S) XMLUtilities.unmarshall(tmpAgendaSCLocal.getXml());
			tmpAgendaSCLocal.remove();
			return tr703s;
		}catch(NamingException ex){
			log.debug("Error de tipo NamingException en el método obtenerEstadoMensajeAgendaSC:"+ex);
		}catch(FinderException ex){
			log.debug("Error de tipo FinderException en el método obtenerEstadoMensajeAgendaSC:"+ex);
		}catch(ATiempoAppEx ex){
			log.debug("Error de tipo ATiempoAppEx en el método obtenerEstadoMensajeAgendaSC:"+ex);
		} catch (RemoveException e) {
			log.debug("Error de tipo RemoveException en el método obtenerEstadoMensajeAgendaSC:"+e);
		}
		return mensaje;		
	}
	
	public String obtenerCaracteristicaPeticion(Producto_servicio_peticionLocal producto_servicio_peticionLocal, Long caracteristica){
		String valor = null;

		if (producto_servicio_peticionLocal != null){
			Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
			for(Iterator iterator2=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();iterator2.hasNext();)
			{
				Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterator2.next();
				Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
				if (spk.cod_crc_cd.longValue()== caracteristica.longValue())
				{
					log.debug("Informacion : Se obtuvo La Caracteristica " + caracteristica.longValue() + " : " +subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
					valor = subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
					break;
				}
			}
		}
		return valor;
	}
	
  	public String extraerInf(SubRequest subPeticionDTO, String coordenada, PeticionLocal peticionLocal, long peti_numero) throws NamingException, CreateException{
        String valor="";
        String complementos = subPeticionDTO.getObservations();
        
        try{
        	String cadena = complementos.substring(complementos.indexOf(coordenada)).replaceAll(coordenada,"").replaceAll("[a-zA-Z]","").trim();
            for (int i=0;i<cadena.length();i++){
                  if ( !(cadena.substring(i,i+1).matches("[0-9]*") || cadena.substring(i,i+1).equals("-")))
                        break;
                        valor = valor + cadena.substring(i,i+1);
            }
        }catch(Exception ex){
        	valor = "0";
        }
        
        if (valor.equals("") || valor.equals("0")){
			log.info("No se Recibe Numero de Actuacion en la Pet Atis : "+subPeticionDTO.getProductServiceCode());
		}else{
			Agenda_scLocalHome agenda_SCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			Agenda_scLocal agenda_SCLocal=agenda_SCLocalHome.create(valor);
			agenda_SCLocal.setPeticion(peticionLocal);
			agenda_SCLocal.setEstado(new Integer(1));
			agenda_SCLocal.setPeti_numero(new Long(peti_numero));
			agenda_SCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			peticionLocal.setEstado_agend_sc(new Integer(ComunInterfaces.ESTADO_AGENDA_SC_CON_MARCA));
		}
        return valor;
  }
	
  	public boolean esActuacionCCF(Long petiNumero){
  		
  		boolean esActuacionCCF=false;
  		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
  		
  		try{
  			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
  			
  			Collection agendamientos = agendaSCLocalHome.findByPetiNumero(petiNumero);
  			for (Iterator agendamientoIterator = agendamientos.iterator(); agendamientoIterator.hasNext();){
  				Agenda_scLocal agendaSCLocal = (Agenda_scLocal)agendamientoIterator.next();				
  				if (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
  						||agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
  					/*mfmendez*/
  					Agenda_scKey ascKey = (Agenda_scKey) agendaSCLocal.getPrimaryKey();
  					String [] campos = ascKey.id_actuacion.toString().split("-");
  					/*mfmendez*/
  					if (formatter.format(agendaSCLocal.getFecha_mod()) != campos[1]){
  						log.debug("Es una Peticion que creo la Actuacion CCF : " + petiNumero);
  						esActuacionCCF = true;
  						break;
  					}
  				}
  			}
  		}catch(FinderException e){
  			log.debug("FinderException en esActuacionCCF(): "+e.toString());
  			return false;
  		}catch(NamingException e){
  			log.debug("NamingException en esActuacionCCF(): "+e.toString());
  			return false;
  		}
  		return esActuacionCCF;
  	}
  	
  	/**
  	 * 
  	 */
	TR705S obtenerMensajeReagendamientoASC(Long correlativoID){
		TR705S mensaje = null;
		try{
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome) HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scKey tempAgendaSCKey = new Tmp_agenda_scKey(correlativoID);
			Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.findByPrimaryKey(tempAgendaSCKey);
			TR705S tr705s = (TR705S) XMLUtilities.unmarshall(tmpAgendaSCLocal.getXml());
			tmpAgendaSCLocal.remove();
			return tr705s;
		}catch(NamingException ex){
			log.debug("Error de tipo NamingException en el método obtenerEstadoMensajeAgendaSC:"+ex);
		}catch(FinderException ex){
			log.debug("Error de tipo FinderException en el método obtenerEstadoMensajeAgendaSC:"+ex);
		}catch(ATiempoAppEx ex){
			log.debug("Error de tipo ATiempoAppEx en el método obtenerEstadoMensajeAgendaSC:"+ex);
		} catch (RemoveException e) {
			log.debug("Error de tipo RemoveException en el método obtenerEstadoMensajeAgendaSC:"+e);
		}
		return mensaje;			
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#esSVATemp(java.lang.Long)
	 */
	public boolean esSVATemp(Long numeroPeticion) {
		// TODO Apéndice de método generado automáticamente
		
		try {
			Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
			Producto_servicio_peticionLocalHome pss = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection psPeticion = pss.findAllByPetiNumero(numeroPeticion);

//			if(psPeticion.size() == 1){
			for(Iterator iter = psPeticion.iterator();iter.hasNext();){
				Producto_servicio_peticionLocal psTemp = (Producto_servicio_peticionLocal) iter.next();
				String psSVABA[]= propertiesLocal.getValor().split(",");
				for(int i = 0;i<psSVABA.length;i++){
					if(new Long(psSVABA[i]).equals(psTemp.getPsId())){
					
						Properties_BDLocal OC_no_informada = propertiesHome.findByPrimaryKey(ComunInterfaces.OC_NO_INFORMADOS);
						String OCSVABA[]= OC_no_informada.getValor().split(",");
						for(int j = 0;j<OCSVABA.length;j++){
							Operacion_comercialKey oc = (Operacion_comercialKey) psTemp.getOperacion_comercial().getPrimaryKey();
							if(new Long(OCSVABA[j]).equals(oc.opco_id))
								return true;
						}
						
					}
				}
			}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		return false;
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#esSVATemp(java.lang.Long)
	 */
	public boolean centralSoportaConfAutomaticaEOC(Long numeroPeticion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
		try {
			log.debug("Se valida si la central soporta EOC");
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal = peticionHome.findByPrimaryKey(new PeticionKey (numeroPeticion));
			LocalidadLocal localidad = peticionLocal.getFk_01_localidad();
			CentralLocal central= peticionLocal.getFk_03_central();
			
			if(central.getCentral_eoc_apsc() != null && central.getCentral_eoc_apsc().intValue()== 1)
				return true;
			else return false;
			
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se encontrò central: ");
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se encontrò central: ");
		}
		// 
		return false;
	}
	
	public boolean centralAntSoportaConfAutomaticaEOC(Long codCentral) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
		try {
			log.debug("Se valida si la central soporta EOC: "+ codCentral);
			CentralLocalHome centralHome = (CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
			CentralLocal central= centralHome.findByPrimaryKey(new CentralKey(codCentral));
			
			if(central.getCentral_eoc_apsc() != null && central.getCentral_eoc_apsc().intValue()== 1)
				return true;
			else return false;
			
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se encontrò central: "+ codCentral);
			return false;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se encontrò central: "+ codCentral);
			return false;
		}catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se encontrò central: "+ codCentral);
			return false;
		}

	}
}

