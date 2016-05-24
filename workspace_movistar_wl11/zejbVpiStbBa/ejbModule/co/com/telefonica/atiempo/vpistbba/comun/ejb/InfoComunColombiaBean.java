package co.com.telefonica.atiempo.vpistbba.comun.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PeticionDTO;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.CanalKey;
import co.com.telefonica.atiempo.ejb.eb.CanalLocal;
import co.com.telefonica.atiempo.ejb.eb.CanalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Causa_demoraKey;
import co.com.telefonica.atiempo.ejb.eb.Causa_demoraLocal;
import co.com.telefonica.atiempo.ejb.eb.Causa_demoraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CentralKey;
import co.com.telefonica.atiempo.ejb.eb.CentralLocal;
import co.com.telefonica.atiempo.ejb.eb.ControlvisitaLocal;
import co.com.telefonica.atiempo.ejb.eb.ControlvisitaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.Direccion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscKey;
import co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscLocal;
import co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocalHome;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Subtipo_pcKey;
import co.com.telefonica.atiempo.ejb.eb.Subtipo_pcLocal;
import co.com.telefonica.atiempo.ejb.eb.Subtipo_pcLocalHome;
import co.com.telefonica.atiempo.ejb.eb.TecnicoKey;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocal;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tipo_pcKey;
import co.com.telefonica.atiempo.ejb.eb.Tipo_pcLocal;
import co.com.telefonica.atiempo.ejb.eb.Tipo_pcLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.SessionBeanAdapter;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionControlVisitaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPeticionDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.opearacion_comercial.dto.OperacionComercialDTO;
import com.telefonica_chile.comun.operacion_comercial.session.OperacionComercialSessionLocal;
import com.telefonica_chile.comun.operacion_comercial.session.OperacionComercialSessionLocalHome;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
import com.telefonica_chile.comun.ps.session.ProductoServicioSessionLocal;
import com.telefonica_chile.comun.ps.session.ProductoServicioSessionLocalHome;

import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal;

/**
 * Bean implementation class for Enterprise Bean: InfoComunColombia
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class InfoComunColombiaBean extends SessionBeanAdapter implements InfoComunColombiaBusinessInterface
{
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	//private PeticionLocalHome peticionHome;
	//private SubsegmentoLocalHome subSegmentoHome;
	//private ControlvisitaLocalHome controlvisitaHome;
	//private ActividadLocalHome actividadHome;

	// CR14525 - ana santos - inicio
	private CanalLocalHome canalHome;
	// CR14525 - ana santos - fin

	public InformacionAdslDTO obtenerInformacionAdsl(Long nroPeticion) throws ATiempoAppEx
	{
		RecursosBAInterfaces recursosBAInterface = new RecursosBADelegate();
		InformacionAdslDTO informacionAdsl = new InformacionAdslDTO();
		try
		{
			informacionAdsl = recursosBAInterface.obtenerDatosAdsl( nroPeticion );
			if(informacionAdsl != null)
			{
				if(informacionAdsl.getDirecIpDslam()==null || informacionAdsl.getDirecIpDslam().equals(""))
					informacionAdsl=null;
			}			
			return informacionAdsl;
		}
		catch (ATiempoAppEx e)
		{
			
		}
		return null;
	}
	
	/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface#obtenerInformacionAdsl()
		 */
	public InformacionAdslDTO obtenerInformacionAdslActual(Long nroPeticion) throws ATiempoAppEx {
			
			RecursosBAInterfaces recursosBAInterface = new RecursosBADelegate();
			InformacionAdslDTO informacionAdsl = new InformacionAdslDTO();

			try {
				informacionAdsl = recursosBAInterface.obtenerDatosActualAdsl( nroPeticion );
				if(informacionAdsl != null){
					if(informacionAdsl.getDirecIpDslam()==null || informacionAdsl.getDirecIpDslam().equals("")){
						informacionAdsl=null;
					}
				}
				return informacionAdsl;
			} catch (ATiempoAppEx e) {
			}
			return null;
		}
	
	public InformacionPeticionDTO obtenerSimpleInfoPeticion(Long nroPeticionVpi) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			SubsegmentoLocalHome subSegmentoHome=(SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);	
				
			// CR14525 - ana santos - inicio
			canalHome = (CanalLocalHome) HomeFactory.getHome(CanalLocalHome.JNDI_NAME);
			// CR14525 - ana santos - fin
				
			InformacionPeticionDTO info = new InformacionPeticionDTO();
			PeticionLocal local=peticionHome.findByPrimaryKey (new PeticionKey (nroPeticionVpi));
			Peticion_atisLocal atis=local.getFk_01_pet_atis();
			Peticion_atisKey keyAtis=(Peticion_atisKey) atis.getPrimaryKey();
			info.setPeticionAtis(String.valueOf(keyAtis.cod_pet_cd));
			DepartamentoLocal fk_02_departamento = local.getFk_02_departamento();
			if(fk_02_departamento!=null)
			{
				DepartamentoKey departamentoKey=(DepartamentoKey) fk_02_departamento.getPrimaryKey();
				info.setCdDepartamento(String.valueOf(departamentoKey.cod_dpt));
				info.setDscDepartamento( fk_02_departamento.getDescripcion_departamento());
			}
			LocalidadLocal fk_01_localidad = local.getFk_01_localidad();
			if(fk_01_localidad!=null)
			{
				LocalidadKey localidadKey=(LocalidadKey) fk_01_localidad.getPrimaryKey();
				info.setCdLocalidad(localidadKey.cod_loc);
				info.setDscLocalidad(fk_01_localidad.getNombre_localidad());
			}
			info.setCdpeticion(nroPeticionVpi.toString());
			
			info.setCdSegmento(Utiles.longSinNull(local.getCod_sgm_cta_cd(),""));
			info.setCdSubSegmen(Utiles.longSinNull(local.getCod_sgm_cta_cd(),""));
			
			info.setFamiliaPs(local.getPeti_id_instancia());
			info.setTipoOp(local.getTica_id());
			if(local.getCod_sbg_cta_cd()!=null)
			{
				SubsegmentoLocal subsegmentoLocal=subSegmentoHome.findByPrimaryKey(new SubsegmentoKey(local.getCod_sbg_cta_cd()));
				info.setDscSubSegmen(subsegmentoLocal.getDescripcion());
				info.setDscSegmento(subsegmentoLocal.getSegmento().getSegm_descripcion());
			}
			String dirInst = Utiles.sinNull(local.getNom_cal_ds(),"") +" "+ Utiles.sinNull(local.getNum_cal_nu(),"")+" "+Utiles.sinNull(local.getDsc_cmp_pri_ds(),"")+" "+Utiles.sinNull(local.getDsc_cmp_seg_ds(),"")+" "+ Utiles.sinNull(local.getNom_slo_no(),"");
			info.setDireccion( dirInst );
			CentralLocal fk_03_central = local.getFk_03_central();
			if(fk_03_central!=null)
			{
				CentralKey centralKey=(CentralKey) fk_03_central.getPrimaryKey();
				info.setCdCentral(String.valueOf(centralKey.cod_central));
				info.setDscCentral(fk_03_central.getDesc_central());
			}
			info.setCdEstado( ""+local.getEspe_id() );
			Integer espeId = local.getEspe_id();
			if ( espeId==null )
			{
				info.setDscEstado("En Proceso");
				info.setCdEstado(""+ new Integer(ComunInterfaces.estadoPeticionEnCurso) );
			} 
			else if ( espeId.intValue()==ComunInterfaces.estadoPeticionEnCurso ) 
				info.setDscEstado("En Proceso"); 
			else if ( espeId.intValue()==ComunInterfaces.estadoPeticionCancelada) 
				info.setDscEstado("Cancelada"); 
			else if ( espeId.intValue()==ComunInterfaces.estadoPeticionTerminada) 
				info.setDscEstado("Terminada"); 
			//info.setNomTipUsoNoAgrupacionLinea(peticionDTO.getNomTipUsoNoAgrupacionLinea());
			info.setFecAsigRecursos(Utiles.fechaSinNull(local.getFec_sct_pet_ff(),""));
			info.setFecCompromiso(Utiles.fechaSinNull(local.getPeti_fecha_compromiso(),""));
			info.setFecConfig(Utiles.fechaSinNull(local.getFec_sct_pet_ff(),""));
			info.setFecRegistro(Utiles.fechaSinNull(local.getPeti_fecha_ingreso(),""));
			info.setFecTermino(Utiles.fechaSinNull(local.getPeti_fecha_termino(),""));
			info.setIdCliente(local.getNum_doc_cli_cd());
			if(info.getIdCliente()!=null && local.getDig_ctl_doc_cd()!=null && !info.getIdCliente().equals("") && !local.getDig_ctl_doc_cd().equals(""))
				info.setIdCliente(info.getIdCliente()+"-"+local.getDig_ctl_doc_cd());
			info.setNombreCliente(Utiles.sinNull(local.getNom_ds(),"") 
				+ " " +Utiles.sinNull(local.getPri_ape_ds(),"") + " " + Utiles.sinNull(local.getSeg_ape_ds(),""));
			info.setTelContacto(Utiles.sinNull(local.getTel_cot_ds(),""));
			//TODO -- Se setean los nuevos tels de contacto -- Pablo L -- CR-10120
			info.setSegTelContacto(Utiles.sinNull(local.getTel_cot_ds_1(),""));
			info.setTerTelContacto(Utiles.sinNull(local.getTel_cot_ds_2(),""));

			info.setNombreSolicitante(Utiles.sinNull(local.getNom_int_ds(),""));
			info.setApellidoPatSolicitante(Utiles.sinNull(local.getPri_ape_int_ds(),""));
			info.setApellidoMatSolicitante(Utiles.sinNull(local.getSeg_ape_int_ds(),""));
			
			//CR-23444 - PCawen - Informacion del ciclo de facturacion
			info.setInfCicloFac(Utiles.sinNull(local.getInf_cicl_fac(),""));

			info.setBarrio( local.getNom_slo_no() );
			
			// CR14525 - ana santos - inicio
			
			if(canalHome==null)
				canalHome=(CanalLocalHome) HomeFactory.getHome(CanalLocalHome.JNDI_NAME);
			

			try {
				CanalLocal cnlLocal=canalHome.findByPrimaryKey (new CanalKey (local.getCod_cnl_ven_cd() ));
				log.debug("En InfoComunColombiaBean, cod= " + local.getCod_cnl_ven_cd() );
				info.setDescripcionCanalVta("" + cnlLocal.getDsc_cnl_ven_cd());
			} catch (FinderException finderException2) {
				// TODO Auto-generated catch block
				info.setDescripcionCanalVta("No hay descripcion para el canal");
				finderException2.printStackTrace();
			}
					
			// CR14525 - ana santos - fin				
			
			info.setDscCanalVta( ""+local.getCod_cnl_ven_cd());
			
			info.setTipoDocumento( local.getTip_doc_cd() );
			//info.setIdentificadorOriLinea(local.getIdentificadorOriLinea());
			info.setIdentificadorTV(Utiles.sinNull(local.getNum_ide_nu_tv(),""));
			info.setIdentificadorSTB(Utiles.sinNull(local.getNum_ide_nu_stb(),""));
			info.setNomTipUsoNoAgrupacionLinea(local.getNomTipoUso());
			info.setFecRegistro(Utiles.fechaSinNull(local.getPeti_fecha_ingreso(),""));
			//	CR10317 - ana santos - 13/May/2008
			info.setObservacion(local.getObs_pet_ds ());
			return info;
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}
	
	public InformacionPeticionDTO obtenerInformacionPeticion(Long nroPeticionVpi) throws ATiempoAppEx
	{
		PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
		PeticionDTO peticionDTO = peticionesInterface.obtenerPeticionVPI(nroPeticionVpi);
		InformacionPeticionDTO informacionPeticion = new InformacionPeticionDTO();
		informacionPeticion.setCdDepartamento(peticionDTO.getCodDpt() );
		informacionPeticion.setCdLocalidad(peticionDTO.getCodLoc());
		informacionPeticion.setPeticionAtis(Utiles.longSinNull(peticionDTO.getCodPetCd(),""));
		for(Iterator iterator=peticionDTO.getListAgrupaciones().iterator();iterator.hasNext();)
		{
			Integer codagrusubnu=(Integer) iterator.next();
			informacionPeticion.addCodigoAgrupacion(codagrusubnu);
		}
		informacionPeticion.setCdpeticion(Utiles.longSinNull(peticionDTO.getPetiNumero(), "") );
		
		informacionPeticion.setCdSegmento(Utiles.longSinNull(peticionDTO.getCodSgmCtaCd(),""));
		informacionPeticion.setCdSubSegmen(Utiles.longSinNull(peticionDTO.getCodSbgCtaCd(),""));
		
		String dirInst = Utiles.sinNull(peticionDTO.getNomCalDs(),"") +" "+ Utiles.sinNull(peticionDTO.getNumCalNu(),"")+" "+Utiles.sinNull(peticionDTO.getDscCmpPriDs(),"")+" "+Utiles.sinNull(peticionDTO.getDscCmpSegDs(),"")+" "+ Utiles.sinNull(peticionDTO.getNomSloNo(),"");
		informacionPeticion.setDireccion( dirInst );
		informacionPeticion.setCdDepartamento( peticionDTO.getCodDpt() );
		informacionPeticion.setCdLocalidad( peticionDTO.getCodLoc() );
		if(peticionDTO.getCodCentral()!=null)
		{
			informacionPeticion.setCdCentral(String.valueOf(peticionDTO.getCodCentral()));
			informacionPeticion.setDscCentral(peticionDTO.getDescCentral());
		}
		informacionPeticion.setDscDepartamento( peticionDTO.getDscDepartamento() );
		informacionPeticion.setDscSegmento( peticionDTO.getDscSegmento() );
		informacionPeticion.setDscSubSegmen( peticionDTO.getDscSubSegmento() );
		informacionPeticion.setDscLocalidad( peticionDTO.getDscLocalidad() );
		informacionPeticion.setCdEstado( ""+peticionDTO.getEspeId() );
		informacionPeticion.setDscEstado( peticionDTO.getDscEstado() );
		informacionPeticion.setNomTipUsoNoAgrupacionLinea(peticionDTO.getNomTipUsoNoAgrupacionLinea());
		informacionPeticion.setFecAsigRecursos(Utiles.fechaSinNull(peticionDTO.getFecSctPetFf(),""));
		informacionPeticion.setFecCompromiso(Utiles.fechaSinNull(peticionDTO.getPetiFechaCompromiso(),""));
		informacionPeticion.setFecConfig(Utiles.fechaSinNull(peticionDTO.getFecSctPetFf(),""));
		informacionPeticion.setFecRegistro(Utiles.fechaSinNull(peticionDTO.getPetiFechaIngreso(),""));
		informacionPeticion.setFecTermino(Utiles.fechaSinNull(peticionDTO.getPetiFechaTermino(),""));
		if(peticionDTO.getNumDocCliCd()!=null)
		{
			informacionPeticion.setIdCliente(peticionDTO.getNumDocCliCd());
			String digCtlDocCd = peticionDTO.getDigCtlDocCd();
			if(digCtlDocCd!=null)
			{
				if(!digCtlDocCd.equals(""))
					informacionPeticion.setIdCliente(informacionPeticion.getIdCliente()+"-"+digCtlDocCd);		
			}				
		}
		informacionPeticion.setIdentificadorOriLinea( peticionDTO.getIdOriginalPCLinea() );
		informacionPeticion.setNombreCliente(Utiles.sinNull(peticionDTO.getNomDs(),"") 
			+ " " +Utiles.sinNull(peticionDTO.getPriApeDs(),"") + " " + Utiles.sinNull(peticionDTO.getSegApeDs(),""));
		informacionPeticion.setTelContacto(Utiles.sinNull(peticionDTO.getTelCotDs(),""));
		
		// TODO -- Se agregaron los seteos para los 2 nuevos tel de contacto -- Pablo L -- CR-10120
		informacionPeticion.setSegTelContacto(Utiles.sinNull(peticionDTO.getSegTelCotDs(),""));
		informacionPeticion.setTerTelContacto(Utiles.sinNull(peticionDTO.getTerTelCotDs(),""));		
		// TODO -- Fin seteos -- Pablo L

		informacionPeticion.setBarrio( peticionDTO.getNomSloNo() );
		informacionPeticion.setDscCanalVta( peticionDTO.getDscCanalVta() );
		
		// CR14525 - ana santos - inicio
		informacionPeticion.setDescripcionCanalVta(peticionDTO.getDescripcionCanalVta());					
		// CR14525 - ana santos - fin		
	
		//CR-23444 - PCawen - Informacion de ciclo de facturacion
		informacionPeticion.setInfCicloFac(peticionDTO.getInfCicFac());
		
		informacionPeticion.setTipoUso( peticionDTO.getTipoUso() );
		informacionPeticion.setTipoDocumento( peticionDTO.getTipDocCd() );
		informacionPeticion.setIdentificadorTV(Utiles.sinNull(peticionDTO.getNumIdeNumTV(),""));
		informacionPeticion.setIdentificadorSTB(Utiles.sinNull(peticionDTO.getNumIdeNumSTB(),""));
		informacionPeticion.setIdentificadorIC(Utiles.sinNull(peticionDTO.getNumIdeNumIC(),""));
		informacionPeticion.setIdentificadorMP(Utiles.sinNull(peticionDTO.getNumIdeNumMP(),""));

		informacionPeticion.setCodTipUsoCdAgrupacionLinea(Utiles.longSinNull(peticionDTO.getCodTipUsoCdAgrupacionLinea(),""));
		informacionPeticion.setCodTipUsoCdAgrupacionLinea(Utiles.sinNull(peticionDTO.getCodTipUsoCdAgrupacionLinea(),""));
		informacionPeticion.setCantidadTroncales(peticionDTO.getCantidadTroncales());
		informacionPeticion.setNroPiloto(peticionDTO.getNroPiloto());
		
		informacionPeticion.setUsuarioAcceso(peticionDTO.getUsuarioAcceso());
		informacionPeticion.setIdDecoCP(peticionDTO.getIdDecoCP());
		informacionPeticion.setLineaAnteriorTrasSoloBA(peticionDTO.getLineaAnteriorTrasSoloBA());
		informacionPeticion.setObservacion(peticionDTO.getObsPetDs());
		informacionPeticion.setObservacionSubpeticion(peticionDTO.getObsSubDs());
		//CR 27922 -  Eliminar actividad en Atiempo (AT-2437) - agonz - 27/07/2009
		informacionPeticion.setTipoOp(peticionDTO.getTicaId());
		
		informacionPeticion.setNombreSolicitante(peticionDTO.getNomIntDs());
		informacionPeticion.setApellidoPatSolicitante(peticionDTO.getPriApeIntDs());
		informacionPeticion.setApellidoMatSolicitante(peticionDTO.getSegApeIntDs());
		
		//CR-7390 - Yumbleiner - Linea Precableada
		informacionPeticion.setCodigoProyecto(peticionDTO.getCodigoProyecto());
		//CR-7691 - Yumbleiner - Citofonia Virtual
		informacionPeticion.setNumPadre(peticionDTO.getNumPadre());
		informacionPeticion.setNumExtension(peticionDTO.getNumExtension());
		informacionPeticion.setFlagPublicidad(peticionDTO.isFlagPublicidad());
		informacionPeticion.setIdPCPublicidad(peticionDTO.getNumIdeNumPdg());

		return informacionPeticion;
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface#obtenerInformacionTecnica()
	 */
	public InformacionTecnicaDTO obtenerInformacionTecnica(Long nroPeticion) throws ATiempoAppEx {


		RecursosInterfaces recursosInterface = new RecursosDelegate();

		InformacionTecnicaDTO infoTecnica = recursosInterface.obtenerRecursosTecnicos( nroPeticion );

		return infoTecnica;
	}

/*
	public InformacionAgendamientoDTO obtenerInformacionAgendamiento(Long nroPeticion)
		throws ATiempoAppEx {
			InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();
			try {
				Tecnico_peticionLocal cpLocal = null;
				Collection c = cpHome.finderByPeticion( nroPeticion );
				log.debug("Encontrado Asignacion Peticion [" + nroPeticion + "," + c.size() + "]");
				for (Iterator it=c.iterator(); it.hasNext(); ) {
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
					}
				}
			} catch (FinderException e) {
			}

			return infoAgenda;
	}
*/
	public ArrayList getPsYTipoPcNoRealizado(Long idPeticion)
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionEntity = peticionLocalHome.findByPrimaryKey(new PeticionKey( idPeticion ));
			return peticionEntity.getPsYTipoPcNoRealizado();
		}
		catch(Exception e )
		{
			e.printStackTrace();
			log.debug("Exception",e);
			return new ArrayList();
		}
	}
	public ArrayList getProductoServicio( Long idPeticion ) throws ATiempoAppEx
	{
		ArrayList listaPS = new ArrayList();
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			ProductoServicioSessionLocalHome psHome = (ProductoServicioSessionLocalHome) HomeFactory.getHome(ProductoServicioSessionLocalHome.JNDI_NAME);
			ProductoServicioSessionLocal psLocal = psHome.create();
			OperacionComercialSessionLocalHome ocHome = (OperacionComercialSessionLocalHome) HomeFactory.getHome(OperacionComercialSessionLocalHome.JNDI_NAME);
			OperacionComercialSessionLocal ocLocal = ocHome.create();

			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionEntity = peticionLocalHome.findByPrimaryKey(new PeticionKey( idPeticion ));
			Collection pss = peticionEntity.getProducto_servicio_peticion();
			
			for (Iterator iter = pss.iterator(); iter.hasNext();)
			{
				Producto_servicio_peticionLocal ppsocEntity = (Producto_servicio_peticionLocal) iter.next();
					
				//TODO No se toma ProductoServicio con valor Realizado 1, porque refleja que ya esta instalado
				if (ppsocEntity.getPspe_realizado().shortValue() != 1) {					
					ProductoDTO ps = psLocal.recuperarPS( ppsocEntity.getPsId() );
					
					//Si es un ps tipo PC recuperamos sus tipos, subtipos, localidad y sublocalidad
					if ( ps.getIdFaps().intValue() == ComunInterfaces.familiaPcLinea || ps.getIdFaps().intValue() == ComunInterfaces.familiaIP || ps.getIdFaps().intValue()== ComunInterfaces.familiaPcTV ) {
							
							Subpeticion_atisLocal subpeticion_atisLocal=ppsocEntity.getFk_01_subp_atis();
							Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
							String descTipoPC="";
							try{
								Tipo_pcLocalHome tipoPC = (Tipo_pcLocalHome)HomeFactory.getHome(Tipo_pcLocalHome.JNDI_NAME);
								Tipo_pcKey tPCK = new Tipo_pcKey();
								tPCK.id_tipo_pc= new Integer(agrupacion_atisLocal.getTip_pro_cmr_cd().intValue());
								Tipo_pcLocal tipoPcLocal = tipoPC.findByPrimaryKey(tPCK);
								descTipoPC= tipoPcLocal.getNombre_pc();
							}catch (Exception e)
							{
								descTipoPC="" + agrupacion_atisLocal.getTip_pro_cmr_cd();
							}
							ps.setDescTipo(descTipoPC);
							
							String descSubTipoPC="";
							try{
									
								Subtipo_pcLocalHome sTipoPC = (Subtipo_pcLocalHome)HomeFactory.getHome(Subtipo_pcLocalHome.JNDI_NAME);
								Subtipo_pcKey stPCK = new Subtipo_pcKey();
								stPCK.id_subtipo_pc= new Integer(agrupacion_atisLocal.getSbt_pro_cmr_cd().intValue());
								Subtipo_pcLocal sTipoPcLocal = sTipoPC.findByPrimaryKey(stPCK);
								descSubTipoPC= sTipoPcLocal.getNombre_subtipo();
							}catch (Exception e)
							{
								descSubTipoPC="" + agrupacion_atisLocal.getSbt_pro_cmr_cd();
							}							
							ps.setDescSubTipo(descSubTipoPC);
							Collection direcAtis = agrupacion_atisLocal.getDireccion_atis();
							Iterator iterDirec = direcAtis.iterator();
							if (iterDirec.hasNext()){
								Direccion_atisLocal dAtis = (Direccion_atisLocal) iterDirec.next();
								ps.setDescSubLocalidad(dAtis.getNom_slo_no().toString());
								LocalidadLocal localidadAgrup = dAtis.getFk_02_localidad();
								if (localidadAgrup!=null){
									ps.setDescLocalidad(localidadAgrup.getDescripcion_localidad());
								}else{
									ps.setDescLocalidad("");
								}
							}
					}
					
					OperacionComercialDTO oc = ocLocal.recuperarOP( ppsocEntity.getIdOperacionComercial() );
					ps.setIdOpComercial( ppsocEntity.getIdOperacionComercial() );
					ps.setOperacionComercial( oc.getNombreOpco() );
					ps.setTipoOperacionComercial( oc.getTipoOC() );
					ps.setCantidad( ppsocEntity.getPspe_cantidad() );
					listaPS.add( ps );
				}
			}
			return listaPS;
		
		}
		catch (NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (CreateException e)
		{
			log.debug("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
		}
		catch (FinderException e)
		{
			log.debug("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		
	}

	public ArrayList obtenerDecoTarjetas(Long idPeticion) throws ATiempoAppEx {
		RecursosTVInterfaces recursosInterface = new RecursosTVDelegate();

		ArrayList listadoDecos = recursosInterface.getListaDecoTarjetas( idPeticion );

		return listadoDecos;
	}
	
	
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario)
	{
		try
		{	
			PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
			InformacionEmpresaDTO informacionEmpresaDTO = peticionesInterface.obtenerInformacionEmpresa(idUsuario);
			return informacionEmpresaDTO;
		}
		catch(Exception e)
		{
			return new InformacionEmpresaDTO();
		}
	}

	public ArrayList obtenerServiciosSuplementarios(Long idPeticion) throws ATiempoAppEx {
		RecursosInterfaces recursosInterface = new RecursosDelegate();
		ArrayList lista = recursosInterface.obtenerListaServiciosSuplementarios( idPeticion );

		try {
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			ProductoServicioSessionLocalHome psHome = (ProductoServicioSessionLocalHome) HomeFactory.getHome(ProductoServicioSessionLocalHome.JNDI_NAME);
			ProductoServicioSessionLocal psLocal = psHome.create();
			
			for (int i=0; lista!=null && i<lista.size(); i++) {
				String codSS = (String) lista.get(i);
				Long idSS = null;
				try {
					idSS = new Long( codSS );
					psLocal.recuperarPS( idSS );
					
				} catch (NumberFormatException e) {
				}
				
			}
		} catch (NamingException e) {
		} catch (CreateException e) {
		}

		return lista;
	}
	
	
//	public String obtenerCuentaCorreoPadre(Long idPeticion) throws ATiempoAppEx
//	{
//		PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
//		return peticionesInterface.obtenerCuentaCorreoPadre(idPeticion);
//	}

	public ArrayList getProductoServicioMasQuiebres(Long nroPeticion) throws ATiempoAppEx
	{
		ArrayList listaPS = new ArrayList();
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Catalogo_causalLocalHome catHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			Catalogo_causalLocal local=null;
			ProductoServicioSessionLocalHome psHome = (ProductoServicioSessionLocalHome) HomeFactory.getHome(ProductoServicioSessionLocalHome.JNDI_NAME);
			ProductoServicioSessionLocal psLocal = psHome.create();
			OperacionComercialSessionLocalHome ocHome = (OperacionComercialSessionLocalHome) HomeFactory.getHome(OperacionComercialSessionLocalHome.JNDI_NAME);
			OperacionComercialSessionLocal ocLocal = ocHome.create();

			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionEntity = peticionLocalHome.findByPrimaryKey(new PeticionKey( nroPeticion ));
			Collection pss = peticionEntity.getProducto_servicio_peticion();
			Collection estado_ps_peticion = null;
			for (Iterator iter = pss.iterator(); iter.hasNext();)
			{
				Producto_servicio_peticionLocal ppsocEntity = (Producto_servicio_peticionLocal) iter.next();
					
				//TODO No se toma ProductoServicio con valor Realizado 1, porque refleja que ya esta instalado
				if (ppsocEntity.getPspe_realizado().shortValue() != 1)//esta mierda nunca se ocupó
				{
					ProductoDTO ps = psLocal.recuperarPS( ppsocEntity.getPsId() );
					
					//Si es un ps tipo PC recuperamos sus tipos, subtipos, localidad y sublocalidad
					if ( ps.getIdFaps().intValue() == ComunInterfaces.familiaPcLinea || ps.getIdFaps().intValue() == ComunInterfaces.familiaIP || ps.getIdFaps().intValue()== ComunInterfaces.familiaPcTV ) {
							
							Subpeticion_atisLocal subpeticion_atisLocal=ppsocEntity.getFk_01_subp_atis();
							Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
							String descTipoPC="";
							try{
								Tipo_pcLocalHome tipoPC = (Tipo_pcLocalHome)HomeFactory.getHome(Tipo_pcLocalHome.JNDI_NAME);
								Tipo_pcKey tPCK = new Tipo_pcKey();
								tPCK.id_tipo_pc= new Integer(agrupacion_atisLocal.getTip_pro_cmr_cd().intValue());
								Tipo_pcLocal tipoPcLocal = tipoPC.findByPrimaryKey(tPCK);
								descTipoPC= tipoPcLocal.getNombre_pc();
							}catch (Exception e)
							{
								descTipoPC="" + agrupacion_atisLocal.getTip_pro_cmr_cd();
							}
							ps.setDescTipo(descTipoPC);
							
							String descSubTipoPC="";
							try{
									
								Subtipo_pcLocalHome sTipoPC = (Subtipo_pcLocalHome)HomeFactory.getHome(Subtipo_pcLocalHome.JNDI_NAME);
								Subtipo_pcKey stPCK = new Subtipo_pcKey();
								stPCK.id_subtipo_pc= new Integer(agrupacion_atisLocal.getSbt_pro_cmr_cd().intValue());
								Subtipo_pcLocal sTipoPcLocal = sTipoPC.findByPrimaryKey(stPCK);
								descSubTipoPC= sTipoPcLocal.getNombre_subtipo();
							}catch (Exception e)
							{
								descSubTipoPC="" + agrupacion_atisLocal.getSbt_pro_cmr_cd();
							}							
							ps.setDescSubTipo(descSubTipoPC);
							Collection direcAtis = agrupacion_atisLocal.getDireccion_atis();
							Iterator iterDirec = direcAtis.iterator();
							if (iterDirec.hasNext()){
								Direccion_atisLocal dAtis = (Direccion_atisLocal) iterDirec.next();
								ps.setDescSubLocalidad(dAtis.getNom_slo_no().toString());
								LocalidadLocal localidadAgrup = dAtis.getFk_02_localidad();
								if (localidadAgrup!=null){
									ps.setDescLocalidad(localidadAgrup.getDescripcion_localidad());
								}else{
									ps.setDescLocalidad("");
								}
							}
					}
					
					OperacionComercialDTO oc = ocLocal.recuperarOP( ppsocEntity.getIdOperacionComercial() );
					ps.setIdOpComercial( ppsocEntity.getIdOperacionComercial() );
					ps.setOperacionComercial( oc.getNombreOpco() );
					ps.setTipoOperacionComercial( oc.getTipoOC() );
					ps.setCantidad( ppsocEntity.getPspe_cantidad() );
					
					//Tengo que recuperar el Quiebre
					estado_ps_peticion = ppsocEntity.getEstado_ps_peticion();
					if(estado_ps_peticion!=null && estado_ps_peticion.size()>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) estado_ps_peticion.iterator().next();
						ps.setCodCausal(estado_ps_peticionLocal.getCod_causal());
						if(estado_ps_peticionLocal.getNovedad()!=null)
							ps.setObservacionCausal(estado_ps_peticionLocal.getNovedad().trim());
						local=catHome.findByPrimaryKey(new Catalogo_causalKey(ps.getCodCausal()));
						ps.setDescCausal(local.getDescripcion_causal());
					}
					listaPS.add( ps );
				}
			}
			return listaPS;
		}
		catch (NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (CreateException e)
		{
			log.debug("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
		}
		catch (FinderException e)
		{
			log.debug("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
	}
	
	public ArrayList recuperaControlesVisita(Long petiNumero) throws ATiempoAppEx
	{
		ArrayList retorno=new ArrayList();
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			ControlvisitaLocalHome controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
			Collection listaControl=controlvisitaHome.findByPetiNumero(petiNumero);
			for(Iterator iterator=listaControl.iterator();iterator.hasNext();)
			{
				ControlvisitaLocal local=(ControlvisitaLocal) iterator.next();
				InformacionControlVisitaDTO dto=new  InformacionControlVisitaDTO();

				//Gustavo - CR 25403
				Causa_demoraLocalHome causaDemoraHome = (Causa_demoraLocalHome)  HomeFactory.getHome(Causa_demoraLocalHome.JNDI_NAME);

				Causa_demoraLocal causaLocal = (Causa_demoraLocal) causaDemoraHome.findByPrimaryKey(new Causa_demoraKey(local.getCod_caudem()));
				String nomCausaDemora = causaLocal.getDescripcion_caudem();

				TecnicoLocalHome tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
				TecnicoLocal tecnicoLocal = (TecnicoLocal) tecnicoHome.findByPrimaryKey(new TecnicoKey(local.getTecnico()));
				String tecnico = tecnicoLocal.getNombre() + " " + tecnicoLocal.getApellido();

				Producto_servicioLocalHome productoServicioHome=(Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioLocal productoServicioLocal = (Producto_servicioLocal) productoServicioHome.findByPrimaryKey(new Producto_servicioKey(local.getPs_id()));
				String psNombre = productoServicioLocal.getPs_nombre();

				dto.setPeticion(petiNumero);
				dto.setFechaLlegada(new Fecha(local.getFechahora_llegada()));
				dto.setFechaSalida(new Fecha(local.getFechahora_salida()));
				dto.setFechaRegistro(new Fecha(local.getFechahora_registro()));
				dto.setCodigoPs(local.getPs_id().toString());
				dto.setNomPS(psNombre);
				dto.setCodigoTecnico(local.getTecnico());
				dto.setNombreTecnicoInicial(tecnico);
				dto.setCodCausaDemora(local.getCod_caudem().toString());
				dto.setNomCausaDemora(nomCausaDemora);
				
				//Gustavo - CR 25403 - Fin
				
				retorno.add(dto);
			}
			//if(retorno.size()>0)
			//	Collections.sort(retorno);
		}
		catch(NamingException e)
		{
			log.error(e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);	
		}
		catch (FinderException e)
		{
			log.error(e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		return retorno;
	}

	public ArrayList getPSQuiebresYChulo(Long nroPeticion, Long codigoActividad) throws ATiempoAppEx
	{
		ArrayList listaPS = new ArrayList();
		try
		{
			log.debug("Sacando quiebres con chulo para peticion nroPeticion:"+nroPeticion+" en actividad:"+codigoActividad);
			
			PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			ActividadLocalHome actividadHome=(ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ActividadLocal actividadLocal=actividadHome.findByPrimaryKey(new ActividadKey(codigoActividad));
			Actividad_flujoKey actividad_flujoKey=null;
			if(actividadLocal.getActividad_flujo()!=null)
			{
				actividad_flujoKey=(Actividad_flujoKey) actividadLocal.getActividad_flujo().getPrimaryKey();
			}
			
			log.debug("Sacando quiebres con chulo para peticion nroPeticion:"+nroPeticion+" en actividad:"+actividad_flujoKey.acti_id);
			
			Catalogo_causalLocalHome catHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			Catalogo_causalLocal local=null;
			ProductoServicioSessionLocalHome psHome = (ProductoServicioSessionLocalHome) HomeFactory.getHome(ProductoServicioSessionLocalHome.JNDI_NAME);
			ProductoServicioSessionLocal psLocal = psHome.create();
			OperacionComercialSessionLocalHome ocHome = (OperacionComercialSessionLocalHome) HomeFactory.getHome(OperacionComercialSessionLocalHome.JNDI_NAME);
			OperacionComercialSessionLocal ocLocal = ocHome.create();

			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionEntity = peticionLocalHome.findByPrimaryKey(new PeticionKey( nroPeticion ));
			Collection pss = peticionEntity.getProducto_servicio_peticion();
			Collection estado_ps_peticion = null;
			for (Iterator iter = pss.iterator(); iter.hasNext();)
			{
				Producto_servicio_peticionLocal ppsocEntity = (Producto_servicio_peticionLocal) iter.next();
				
				
				//TODO No se toma ProductoServicio con valor Realizado 1, porque refleja que ya esta instalado
				if (ppsocEntity.getPspe_realizado().shortValue() != 1)//esta mierda nunca se ocupó
				{
					ProductoDTO ps = psLocal.recuperarPS( ppsocEntity.getPsId() );
					
					//Si es un ps tipo PC recuperamos sus tipos, subtipos, localidad y sublocalidad
					if ( ps.getIdFaps().intValue() == ComunInterfaces.familiaPcLinea || ps.getIdFaps().intValue() == ComunInterfaces.familiaIP ||ps.getIdFaps().intValue()== ComunInterfaces.familiaPcTV ) {
							
							Subpeticion_atisLocal subpeticion_atisLocal=ppsocEntity.getFk_01_subp_atis();
							Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
							String descTipoPC="";
							try{
								Tipo_pcLocalHome tipoPC = (Tipo_pcLocalHome)HomeFactory.getHome(Tipo_pcLocalHome.JNDI_NAME);
								Tipo_pcKey tPCK = new Tipo_pcKey();
								tPCK.id_tipo_pc= new Integer(agrupacion_atisLocal.getTip_pro_cmr_cd().intValue());
								Tipo_pcLocal tipoPcLocal = tipoPC.findByPrimaryKey(tPCK);
								descTipoPC= tipoPcLocal.getNombre_pc();
							}catch (Exception e)
							{
								descTipoPC="" + agrupacion_atisLocal.getTip_pro_cmr_cd();
							}
							ps.setDescTipo(descTipoPC);
							
							String descSubTipoPC="";
							try{
									
								Subtipo_pcLocalHome sTipoPC = (Subtipo_pcLocalHome)HomeFactory.getHome(Subtipo_pcLocalHome.JNDI_NAME);
								Subtipo_pcKey stPCK = new Subtipo_pcKey();
								stPCK.id_subtipo_pc= new Integer(agrupacion_atisLocal.getSbt_pro_cmr_cd().intValue());
								Subtipo_pcLocal sTipoPcLocal = sTipoPC.findByPrimaryKey(stPCK);
								descSubTipoPC= sTipoPcLocal.getNombre_subtipo();
							}catch (Exception e)
							{
								descSubTipoPC="" + agrupacion_atisLocal.getSbt_pro_cmr_cd();
							}							
							ps.setDescSubTipo(descSubTipoPC);
							Collection direcAtis = agrupacion_atisLocal.getDireccion_atis();
							Iterator iterDirec = direcAtis.iterator();
							if (iterDirec.hasNext()){
								Direccion_atisLocal dAtis = (Direccion_atisLocal) iterDirec.next();
								ps.setDescSubLocalidad(dAtis.getNom_slo_no().toString());
								LocalidadLocal localidadAgrup = dAtis.getFk_02_localidad();
								if (localidadAgrup!=null){
									ps.setDescLocalidad(localidadAgrup.getDescripcion_localidad());
								}else{
									ps.setDescLocalidad("");
								}
							}
					}
					
					OperacionComercialDTO oc = ocLocal.recuperarOP( ppsocEntity.getIdOperacionComercial() );
					ps.setIdOpComercial( ppsocEntity.getIdOperacionComercial() );
					ps.setOperacionComercial( oc.getNombreOpco() );
					ps.setTipoOperacionComercial( oc.getTipoOC() );
					ps.setCantidad( ppsocEntity.getPspe_cantidad() );
					
					//Tengo que recuperar el Quiebre
					estado_ps_peticion = ppsocEntity.getEstado_ps_peticion();
					if(estado_ps_peticion!=null && estado_ps_peticion.size()>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) estado_ps_peticion.iterator().next();
						ps.setCodCausal(estado_ps_peticionLocal.getCod_causal());
						if(estado_ps_peticionLocal.getNovedad()!=null)
							ps.setObservacionCausal(estado_ps_peticionLocal.getNovedad().trim());
						local=catHome.findByPrimaryKey(new Catalogo_causalKey(ps.getCodCausal()));
						ps.setDescCausal(local.getDescripcion_causal());
					}
					
					if(actividad_flujoKey!=null)
					{
						boolean llamaAlaActividad=peticionesInterface.pasaPSyOcXActividad(nroPeticion,ppsocEntity.getPsId(),ppsocEntity.getIdOperacionComercial(),actividad_flujoKey.acti_id);
						boolean estaOK=peticionesInterface.estaOKProductoServicioPeticion(ppsocEntity);
						ps.setLlamaAlaActividad(llamaAlaActividad);
						ps.setEstaOK(estaOK);
					}
									
					listaPS.add( ps );
				}
			}
			return listaPS;
		}
		catch (NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (CreateException e)
		{
			log.debug("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
		}
		catch (FinderException e)
		{
			log.debug("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
	}
	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface#obtenerLocalidadAPSC(java.lang.Long, java.lang.String)
	 */
	public Long obtenerCodigoAPSC(Long codLoc, String subLocalidad) {
		Long codLocApsc = codLoc;
		try{
			Mapeo_loc_apscLocalHome mapeo_loc_apscLocalHome = (Mapeo_loc_apscLocalHome) HomeFactory.getHome (Mapeo_loc_apscLocalHome.JNDI_NAME);

			Mapeo_loc_apscKey mapeo_loc_apscKey = new Mapeo_loc_apscKey(codLoc,subLocalidad);
			Mapeo_loc_apscLocal mapeo = mapeo_loc_apscLocalHome.findByPrimaryKey(mapeo_loc_apscKey);
			
			codLocApsc = mapeo.getCod_loc_apsc();
		}catch (FinderException e){
			log.debug("No se obtuvo código de localidad APSC: " + e.getMessage());
		}catch (NamingException e){
			log.debug("No se obtuvo código de localidad APSC: " + e.getMessage());
		}
		log.info("Codigo localidad APSC: " + codLocApsc + " para localidad y sublocalidad ATIS (" + codLoc + "," + subLocalidad + ")");
		return codLocApsc;
	}
	
	public Subpeticion_caracteristicasLocal obtenerCaracteristica (Long idPeticion,Long codigoCaracteristica) throws ATiempoAppEx, NamingException{
		
		PeticionesInterfaces peticoInterface = new PeticionesDelegate();
		Iterator listaSubpeticionesIt=null;
		
		CaracteristicaPSLocal caracteristicaPSEquipo=null;
		Subpeticion_caracteristicasLocal caracteristicaPS=null;		
		
		Collection listaSubpeticiones=(Collection)peticoInterface.obtenerSubpeticionesDesdePeticion(idPeticion);
		for(listaSubpeticionesIt=listaSubpeticiones.iterator();listaSubpeticionesIt.hasNext();){
			Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal)listaSubpeticionesIt.next();
			Subpeticion_caracteristicasLocalHome subpeticionCaracteristicasLocalHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
			Subpeticion_caracteristicasKey sck=new Subpeticion_caracteristicasKey(codigoCaracteristica,(Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey());
			try{
				caracteristicaPS=subpeticionCaracteristicasLocalHome.findByPrimaryKey(sck);
				if ( codigoCaracteristica.equals(new Long(ComunInterfaces.CODIGO_CARACTERISTICA_CUOTAS_QW))){
					if(caracteristicaPS.getCod_val_crc_cd().intValue()>0 ){
						return caracteristicaPS;
					}
				}else{
					return caracteristicaPS;
				}
			}catch(FinderException e){
				log.debug ("La subpeticion "+((Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey()).cod_sub_cd +" no tiene la caracteristica de cuotas"+e.getMessage());
			}
		}
		return null;
	}
	
	public List obtenerCaracteristicas (Long idPeticion,Long codigoCaracteristica) throws ATiempoAppEx, NamingException{
		PeticionesInterfaces peticoInterface = new PeticionesDelegate();
		Iterator listaSubpeticionesIt=null;
		
		CaracteristicaPSLocal caracteristicaPSEquipo=null;
		Subpeticion_caracteristicasLocal caracteristicaPS=null;		
		
		Collection listaSubpeticiones=(Collection)peticoInterface.obtenerSubpeticionesDesdePeticion(idPeticion);
		List resultado = new ArrayList();
		for(listaSubpeticionesIt=listaSubpeticiones.iterator();listaSubpeticionesIt.hasNext();){
			Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal)listaSubpeticionesIt.next();
			Subpeticion_caracteristicasLocalHome subpeticionCaracteristicasLocalHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
			Subpeticion_caracteristicasKey sck=new Subpeticion_caracteristicasKey(codigoCaracteristica,(Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey());
			try{
				caracteristicaPS=subpeticionCaracteristicasLocalHome.findByPrimaryKey(sck);
				if ( codigoCaracteristica.equals(new Long(ComunInterfaces.CODIGO_CARACTERISTICA_CUOTAS_QW))){
					if(caracteristicaPS.getCod_val_crc_cd().intValue()>0 ){
						resultado.add(caracteristicaPS);
					}
				}else{
					resultado.add(caracteristicaPS);
				}
			}catch(FinderException e){
				log.debug ("La subpeticion "+((Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey()).cod_sub_cd +" no tiene la caracteristica de cuotas"+e.getMessage());
			}
		}
		return resultado;
	}

	/** 
	 * Obtiene las caracteristicas asociadas a al peticion que estan en configuradas para ser 
	 * visualizadas en la pantalla.
	 */
	public ArrayList recuperaCaracteristicasVisualizacion(Long petiNumero) throws Exception {
		ArrayList retorno=new  ArrayList();
		Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
		String caracteristicasVisualizar = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.CARACTERISTICAS_VISUALIZAR).getValor();
		String[] arregloCaracteristicas=caracteristicasVisualizar.split(",");
		
		for (int i = 0; i < arregloCaracteristicas.length; i++) {
			Long codCrcCd = new Long(arregloCaracteristicas[i]);
			Subpeticion_caracteristicasLocal sc = obtenerCaracteristica(petiNumero,codCrcCd);
			if(sc!=null){
				retorno.add(sc);
			}
		}
		Map map = new TreeMap();
		for (int i = 0; i < arregloCaracteristicas.length; i++) {
			Long codCrcCd = new Long(arregloCaracteristicas[i]);
			if(!map.containsKey(codCrcCd)){
				List sc = obtenerCaracteristicas(petiNumero,codCrcCd);
				if(sc!=null){
					retorno.addAll(sc);
				}
				map.put(codCrcCd,codCrcCd);
			}
		}
		
		return retorno;
	}

	
}
