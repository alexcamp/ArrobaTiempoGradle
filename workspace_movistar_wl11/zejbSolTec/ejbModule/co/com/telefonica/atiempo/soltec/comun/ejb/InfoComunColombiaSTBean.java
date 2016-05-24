package co.com.telefonica.atiempo.soltec.comun.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.AgendaScDTO;
import co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoLocal;
import co.com.telefonica.atiempo.ejb.eb.CentralKey;
import co.com.telefonica.atiempo.ejb.eb.CentralLocal;
import co.com.telefonica.atiempo.ejb.eb.CentralLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.EmpresaKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RangoKey;
import co.com.telefonica.atiempo.ejb.eb.RangoLocal;
import co.com.telefonica.atiempo.ejb.eb.RangoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.TecnicoKey;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocal;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaKey;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.asignacion.ejb.sb.AsignacionSTLocal;
import co.com.telefonica.atiempo.soltec.asignacion.ejb.sb.AsignacionSTLocalHome;
import co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.soltec.dto.CatalogoPruebaLineaDTO;
import co.com.telefonica.atiempo.soltec.dto.CodigoDiagnosticoDTO;
import co.com.telefonica.atiempo.soltec.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.soltec.dto.InformacionCtrlVisitaStDTO;
import co.com.telefonica.atiempo.soltec.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.soltec.dto.PeticionStMasivaDTO;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.dto.PruebaLineaDTO;
import co.com.telefonica.atiempo.soltec.dto.RecursosBADTO;
import co.com.telefonica.atiempo.soltec.dto.TematicoDTO;
import co.com.telefonica.atiempo.soltec.dto.convertidores.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_diagnosticoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_diagnosticoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_diagnosticoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocalHome;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVDelegate;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.SessionBeanAdapter;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.asigna.dto.TecnicoDTO;
import com.telefonica_chile.comun.asigna.session.AsignaSessionLocal;
import com.telefonica_chile.comun.asigna.session.AsignaSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: InfoComunColombiaST
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class InfoComunColombiaSTBean 
	extends SessionBeanAdapter implements InfoComunColombiaBusinessInterface {
		
	private javax.ejb.SessionContext mySessionCtx;
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
//	private Producto_servicioLocalHome producto_servicioHome;
//	private Prueba_lineaLocalHome prueba_lineaHome;
//	private Catalago_prueba_lineaLocalHome catalago_prueba_lineaHome;
//	private ControlvisitaLocalHome controlvisitaHome;
	
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
		createHome();
	}
	
	private void createHome() {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
//		try {
//			producto_servicioHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
//			prueba_lineaHome = (Prueba_lineaLocalHome)HomeFactory.getHome(Prueba_lineaLocalHome.JNDI_NAME);
//			catalago_prueba_lineaHome = (Catalago_prueba_lineaLocalHome)HomeFactory.getHome(Catalago_prueba_lineaLocalHome.JNDI_NAME);
//			controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			log.error("NamingException:",e);
//		}
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
			log.error("NamingException:",e);
			return new InformacionEmpresaDTO();
		}
	}
	public Collection obtenerDecoTarjetas(Long idPeticion) throws ATiempoAppEx {
		RecursosTVInterfaces recursosInterface = new RecursosTVDelegate();
		Collection listadoDecos = recursosInterface.getListaDecoTarjetas( idPeticion );

		return listadoDecos;
	}
//	public ArrayList getPsYTipoPcNoRealizado(Long idPeticion)
//	{
//		try
//		{
//			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//			PeticionLocal peticionEntity = peticionLocalHome.findByPrimaryKey(new PeticionKey( idPeticion ));
//			return peticionEntity.getPsYTipoPcNoRealizado();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			log.debug("Exception",e);
//			return new ArrayList();
//		}
//	}
	
	//TCS-gquevedo Jun 18, 2009 CR.25666 INICIO
	public ArrayList obtenerLocalizacionesPorActividad(Long idActividad) throws ATiempoAppEx{
		try{			
			ArrayList listaLoc = new ArrayList();
			Actividad_localizacionLocalHome actividad =(Actividad_localizacionLocalHome) HomeFactory.getHome(Actividad_localizacionLocalHome.JNDI_NAME);
			Collection localizaciones= actividad.findByIdAct(idActividad);
			for(Iterator iterLoc = localizaciones.iterator(); iterLoc.hasNext();){
				Actividad_localizacionLocal local = (Actividad_localizacionLocal)iterLoc.next();
				HashMap mapLoc = new HashMap();
				mapLoc.put("id",local.getLoc_id());
				mapLoc.put("desc",local.getDesc_loc());
				listaLoc.add(mapLoc);
			}
			return listaLoc;
		}catch(Exception e){
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}	
	//TCS-gquevedo Jun 18, 2009 CR.25666 FIN
	
	public ArrayList obtenerLocalizaciones(Long fapsId) throws ATiempoAppEx{
		try{
			ArrayList listaLoc = new ArrayList();
			LocalizacionLocalHome localizacionLocalHome = (LocalizacionLocalHome) HomeFactory.getHome(LocalizacionLocalHome.JNDI_NAME);
			Collection localizaciones= localizacionLocalHome.findByFAPSID(fapsId);
			for(Iterator iterLoc = localizaciones.iterator(); iterLoc.hasNext();){
				LocalizacionLocal local = (LocalizacionLocal)iterLoc.next();
				HashMap mapLoc = new HashMap();
				LocalizacionKey lK = (LocalizacionKey) local.getPrimaryKey();
				mapLoc.put("id",lK.cod_loc);
				mapLoc.put("desc",local.getLocalizacion());
				listaLoc.add(mapLoc);
			}
			return listaLoc;
		}catch(Exception e){
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}
	
	//La localizacion esta asociada al tipo de Producto(familia)
	public ArrayList obtenerCierresAverias(Integer codLocal) throws ATiempoAppEx{
		try{
			ArrayList listaCierres = new ArrayList();
			Codigo_cierreLocalHome codigo_cierreLocalHome = (Codigo_cierreLocalHome) HomeFactory.getHome(Codigo_cierreLocalHome.JNDI_NAME);
			Collection cierres= codigo_cierreLocalHome.findByLoc(codLocal);
			for(Iterator iterCierres = cierres.iterator(); iterCierres.hasNext();){
				Codigo_cierreLocal local = (Codigo_cierreLocal)iterCierres.next();
				HashMap mapLoc = new HashMap();
				Codigo_cierreKey cK = (Codigo_cierreKey) local.getPrimaryKey();
				//CR22146 Cierre Habilitado ST -- Pablo L
				if(local.getHabilitado() != 1){
					mapLoc.put("id",cK.cod_cierre);
					mapLoc.put("desc",local.getDescripcion_cierre());
					listaCierres.add(mapLoc);
				}
			}
			return listaCierres;
		}catch(Exception e){
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#obtenerUsuariosCompatibles(java.lang.Long, java.lang.String, java.lang.Long)
	 */
	public Collection obtenerUsuariosCompatibles(Long idPeticion,Long idUser, String codAct, Long idAplicacion) {
		Collection listaUser = new ArrayList();
		try{
			//Obtengo las habilidades Requeridas
			AsignacionSTLocalHome asignacionSTLocalHome= (AsignacionSTLocalHome)HomeFactory.getHome(AsignacionSTLocalHome.JNDI_NAME);
			AsignacionSTLocal asignacionSTLocal = asignacionSTLocalHome.create();
			HashMap habilidades = asignacionSTLocal.getHabilidades(idPeticion,codAct);
			AsignaSessionLocalHome asignaSessionLocalHome=(AsignaSessionLocalHome)HomeFactory.getHome(AsignaSessionLocalHome.JNDI_NAME);
			AsignaSessionLocal asignaSessionLocal = asignaSessionLocalHome.create();
			
			listaUser = asignaSessionLocal.getUsuariosCompatibles(idUser,codAct,idAplicacion,habilidades);
		}catch(Exception e){
			log.error("Error al buscar usuarios compatibles Peticion:" + idPeticion + " User:" + idUser,e);
		}finally{
			return listaUser;
		}
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#obtenerRecursosBA(java.lang.Long)
	 */
	public RecursosBADTO obtenerRecursosBA(Long codAveOri) throws ATiempoAppEx {
		RecursosBAInterfaces rBaI= new RecursosBADelegate();
		return rBaI.obtenerRecursosBA(codAveOri);
	}
	
	public ArrayList getDatosPeticionMasivo (ArrayList arrCodigoAve) throws ATiempoAppEx //TODO 
	{	
		ArrayList peticionesMasivas = new ArrayList();
		
		try
		{
			PeticionStMasivaDTO  datosPeticionDTO = null;

			
			for(int i = 0; i < arrCodigoAve.size();i++){
				
				datosPeticionDTO = new PeticionStMasivaDTO();	
		
				Peticion_stLocalHome peticionStHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stKey key=new Peticion_stKey( (Long) arrCodigoAve.get(i) );
				Peticion_stLocal peticionLocal=peticionStHome.findByPrimaryKey(key);
				
				if(key.cod_ave_cd != null)
				{
					datosPeticionDTO.setCod_ave_cd(key.cod_ave_cd);
					datosPeticionDTO.setNom_rte_sn(peticionLocal.getNom_rte_sn());
					datosPeticionDTO.setPri_ape_rte_sn(peticionLocal.getPri_ape_rte_sn());
					datosPeticionDTO.setSeg_ape_rte_sn(peticionLocal.getSeg_ape_rte_sn());
					datosPeticionDTO.setTip_doc_rte_cd(peticionLocal.getTip_doc_rte_cd());
					datosPeticionDTO.setNum_doc_rte_nu(peticionLocal.getNum_doc_rte_nu());
					datosPeticionDTO.setCod_rel_cli_cd(peticionLocal.getCod_rel_cli_cd());
					datosPeticionDTO.setRel_cli_otr_ds(peticionLocal.getRel_cli_otr_ds());
					datosPeticionDTO.setEst_ave_mas_cd(peticionLocal.getEst_ave_mas_cd());
					datosPeticionDTO.setTel_cot_ds(peticionLocal.getTel_cot_ds());
					datosPeticionDTO.setSeg_tel_cot_sn(peticionLocal.getSeg_tel_cot_sn());
					datosPeticionDTO.setIde_pro_cmr_cd(peticionLocal.getIde_pro_cmr_cd());
					datosPeticionDTO.setTip_cal_ati_cd(peticionLocal.getTip_cal_ati_cd());
					datosPeticionDTO.setNum_cal_nu(peticionLocal.getNum_cal_nu());
					datosPeticionDTO.setNom_cal_ds(peticionLocal.getNom_cal_ds());
					datosPeticionDTO.setCod_apt_ave_cd(peticionLocal.getCod_apt_ave_cd());
					datosPeticionDTO.setFec_apt_ave_ts(peticionLocal.getFec_apt_ave_ts());
					datosPeticionDTO.setTim_fec_cps_ts(peticionLocal.getTim_fec_cps_ts());
					//incializo la fecha de compromiso
					datosPeticionDTO.setFechaCompromiso(peticionLocal.getTim_fec_cps_ts());
					
					datosPeticionDTO.setCod_ctz_cd(peticionLocal.getCod_ctz_cd());
					datosPeticionDTO.setCod_pra_ave_cd(peticionLocal.getCod_pra_ave_cd());
					datosPeticionDTO.setInd_ave_rei_in(peticionLocal.getInd_ave_rei_in());
					datosPeticionDTO.setObs_ave_ds(peticionLocal.getObs_ave_ds());
					datosPeticionDTO.setEst_ave_mas_cd(peticionLocal.getEst_ave_mas_cd());
					datosPeticionDTO.setDsc_cmp_pri_ds(peticionLocal.getDsc_cmp_pri_ds());
					datosPeticionDTO.setDsc_cmp_seg_ds(peticionLocal.getDsc_cmp_seg_ds());
					datosPeticionDTO.setCod_pro_ser_cd(peticionLocal.getCod_pro_ser_cd());
					datosPeticionDTO.setCod_stm_ave_cd(peticionLocal.getCod_stm_ave_cd());
					datosPeticionDTO.setCod_ext_loc_cd(peticionLocal.getCod_ext_loc_cd());
					datosPeticionDTO.setNom_psn_cot_no(peticionLocal.getNom_psn_cot_no());
					datosPeticionDTO.setSeg_psn_cot_sn(peticionLocal.getSeg_psn_cot_sn());
					datosPeticionDTO.setTel_cot_ds(peticionLocal.getTel_cot_ds());
					datosPeticionDTO.setSeg_tel_cot_sn(peticionLocal.getSeg_tel_cot_sn());
					datosPeticionDTO.setMot_ccl_icd_cd(peticionLocal.getMot_ccl_icd_cd());
					datosPeticionDTO.setCod_apt_ave_cd(peticionLocal.getCod_apt_ave_cd());
					datosPeticionDTO.setCod_est_ave_cd(peticionLocal.getCod_est_ave_cd());
					datosPeticionDTO.setFec_cit_ff(peticionLocal.getFec_cit_ff());
					datosPeticionDTO.setInd_age_cit_in(peticionLocal.getInd_age_cit_in());
					datosPeticionDTO.setTip_ave_mas_cd(peticionLocal.getTip_ave_mas_cd());
					datosPeticionDTO.setEst_ave_mas_cd(peticionLocal.getEst_ave_mas_cd());
					datosPeticionDTO.setCie_ave_mas_ff(peticionLocal.getCie_ave_mas_ff());
					datosPeticionDTO.setObs_ave_mas_ds(peticionLocal.getObs_ave_mas_ds());
					datosPeticionDTO.setTip_ave_mas_cd(peticionLocal.getTip_ave_mas_cd());
					datosPeticionDTO.setTip_mdi_cmc_cd(peticionLocal.getTip_mdi_cmc_cd());
					datosPeticionDTO.setCod_ext_loc_cd(peticionLocal.getCod_ext_loc_cd());
					datosPeticionDTO.setFec_cie_ave_ts(peticionLocal.getFec_cie_ave_ts());
					datosPeticionDTO.setRpt_pru_ave_cd(peticionLocal.getRpt_pru_ave_cd());
					datosPeticionDTO.setCod_loc(peticionLocal.getCod_loc());
					datosPeticionDTO.setCod_dpt(peticionLocal.getCod_dpt());
					datosPeticionDTO.setCod_central(peticionLocal.getCod_central());
					datosPeticionDTO.setRpt_pru_ave_cd(peticionLocal.getRpt_pru_ave_cd());
					if (peticionLocal.getNum_ide_nu()==null || peticionLocal.getNum_ide_nu().equals(""))
					{
						datosPeticionDTO.setNum_ide_nu_tv(peticionLocal.getNum_ide_nu_tv());
					}else{
						datosPeticionDTO.setNum_ide_nu(peticionLocal.getNum_ide_nu());
					}
					
					datosPeticionDTO.setObs_cit_ds(peticionLocal.getObs_cit_ds());
				
					datosPeticionDTO.setCodPsAveria(peticionLocal.getCod_ps_averia());
				
					
					//Buscamos las descripiones para Localidad, Central, Departamento, PS
				
					if(peticionLocal.getCod_loc() != null){
						try{
							LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome)HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
							LocalidadLocal localidadLocal = localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc()));
					
							datosPeticionDTO.setDescLocalidad(localidadLocal.getDescripcion_localidad());
					
						}catch(Exception e){
							log.debug("Error al cargar la descricion de la localidad : "+e,e);
						}
					}
	
					if(peticionLocal.getCod_central() != null){
						try{
							CentralLocalHome centralLocalHome = (CentralLocalHome)HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
							CentralLocal centralLocal = centralLocalHome.findByPrimaryKey(new CentralKey(peticionLocal.getCod_central()));
					
							datosPeticionDTO.setDescCentral(centralLocal.getDesc_central());
					
						}catch(Exception e){
							log.debug("Error al cargar la descripcion de la central : "+e,e);
						}
					
					}
	
					if(peticionLocal.getCod_dpt() != null){
						try{
							DepartamentoLocalHome departamentoLocalHome = (DepartamentoLocalHome)HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
							DepartamentoLocal departamentoLocal = departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticionLocal.getCod_dpt()));
					
							datosPeticionDTO.setDescDepartamento(departamentoLocal.getDescripcion_departamento());
					
						}catch(Exception e){
							log.debug("Error al cargar la descripcion del departamento : "+e,e);
						}
					}
					if(peticionLocal.getCod_pro_ser_cd() != null){
					
						try{
						//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
							Producto_servicioLocalHome producto_servicioHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
							Producto_servicioLocal producto_servicioLocal = producto_servicioHome.findByPrimaryKey(new Producto_servicioKey(peticionLocal.getCod_pro_ser_cd()));
						
							datosPeticionDTO.setDescPsAveria(producto_servicioLocal.getPs_nombre());
						
						}
						catch (FinderException e) {
							log.debug("Error en la carga de descripcion de ps",e);
						}
					
					}
					
//					CR17031 pcawen
//					Datos para COD_SGM_CTA_CD;
					if(peticionLocal.getCod_sgm_cta_cd()!= null){
						try{
							SegmentoLocalHome segmHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
							SegmentoLocal segmLoc = segmHome.findByCodigo(peticionLocal.getCod_sgm_cta_cd().toString());
							
							datosPeticionDTO.setDescSgmto( segmLoc.getSegm_descripcion());
						}catch(Exception e){
							log.debug("Error al cargar la descripcion del segmento : "+e,e);
						}
					}
					
//					Datos para COD_SBG_CTA_CD;
					if(peticionLocal.getCod_sbg_cta_cd()!= null){
						try{
							SubsegmentoLocalHome subsegmHome=(SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
							SubsegmentoLocal subSegmLoc = subsegmHome.findByPrimaryKey(new SubsegmentoKey(peticionLocal.getCod_sbg_cta_cd()));
							
							datosPeticionDTO.setDescSubSgmto(subSegmLoc.getDescripcion());
						}catch(Exception e){
							log.debug("Error al cargar la descripcion del sub-segmento : "+e,e);
						}
					}
//					CR17031 - Fin
					
					//Informacion STB
					InformacionTecnicaDTO infoTecPet= this.obtenerDatosTecnicosLB(key.cod_ave_cd);
					if(infoTecPet!=null){
						datosPeticionDTO.setCentral(infoTecPet.getCentral());
						datosPeticionDTO.setTelefono(infoTecPet.getTelefono());
						datosPeticionDTO.setLen(infoTecPet.getLen());
						datosPeticionDTO.setPosicionHorizontal(infoTecPet.getPosicionHorizontal());
						datosPeticionDTO.setDistr(infoTecPet.getDistr());
						datosPeticionDTO.setCable(infoTecPet.getCable());
						datosPeticionDTO.setCaja(infoTecPet.getCaja());
						datosPeticionDTO.setDirecDistr(infoTecPet.getDirecDistr());
						datosPeticionDTO.setArmario(infoTecPet.getArmario());
						datosPeticionDTO.setDirecCaja(infoTecPet.getDirecCaja());
						datosPeticionDTO.setDirecArmario(infoTecPet.getDirecArmario());
						datosPeticionDTO.setListon(infoTecPet.getListon());
						datosPeticionDTO.setParListon(infoTecPet.getParListon());
						datosPeticionDTO.setParCable(infoTecPet.getParCable());
						datosPeticionDTO.setParCaja(infoTecPet.getParCaja());
						datosPeticionDTO.setDslams(infoTecPet.getDslams());
					}
					
					//Informacion tecnica de ADSL
					RecursosBADTO infoAdsl = this.obtenerRecursosBA(key.cod_ave_cd);
					if(infoAdsl!=null){
						datosPeticionDTO.setIdConector(infoAdsl.getIdConector());
						datosPeticionDTO.setCodError(infoAdsl.getCodError());
						datosPeticionDTO.setPuertoActual(infoAdsl.getPuertoActual());
						datosPeticionDTO.setPostActual(infoAdsl.getPostActual());
						datosPeticionDTO.setAdslActual(infoAdsl.getAdslActual());
						datosPeticionDTO.setMascLanActual(infoAdsl.getMascLanActual());
						datosPeticionDTO.setDirIpDslamActual(infoAdsl.getDirIpDslamActual());
						datosPeticionDTO.setDirIpWanActual(infoAdsl.getDirIpWanActual());
						datosPeticionDTO.setFrameActual(infoAdsl.getFrameActual());
						datosPeticionDTO.setPuertoNvo(infoAdsl.getPuertoNvo());
						datosPeticionDTO.setPostNvo(infoAdsl.getPostNvo());
						datosPeticionDTO.setAdslNvo(infoAdsl.getAdslNvo());
						datosPeticionDTO.setMascLanNva(infoAdsl.getMascLanNva());
						datosPeticionDTO.setDirIpDslamNva(infoAdsl.getDirIpDslamNva());
						datosPeticionDTO.setDirIpWanNva(infoAdsl.getDirIpWanNva());
						datosPeticionDTO.setFrameNvo(infoAdsl.getFrameNvo());
						datosPeticionDTO.setCorrelativo(infoAdsl.getCorrelativo());
						datosPeticionDTO.setPetiNumero(infoAdsl.getPetiNumero());
						datosPeticionDTO.setVpiVciActual(infoAdsl.getVpiVciActual());
						datosPeticionDTO.setVpiVciRedActual(infoAdsl.getVpiVciRedActual());
						datosPeticionDTO.setSlotActual(infoAdsl.getSlotActual());
						datosPeticionDTO.setDirIpLanNva(infoAdsl.getDirIpLanNva());
						datosPeticionDTO.setVpiVciNvo(infoAdsl.getVpiVciNvo());
						datosPeticionDTO.setVpiVciRedNvo(infoAdsl.getVpiVciRedNvo());
						datosPeticionDTO.setSlotNvo(infoAdsl.getSlotNvo());
						datosPeticionDTO.setDescError(infoAdsl.getDescError());
					}
					
					//Informacion de agendamiento
					Tecnico_peticionLocalHome cpHome = (Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
					Tecnico_peticionLocal cpLocal = null;
					Collection c = cpHome.finderByPeticionyAp( key.cod_ave_cd,new Long(ApplicationConfig.getVariable("APP_ATST_ID")) );
					log.debug("Encontrado Asignacion Peticion [" +key.cod_ave_cd + "," + c.size() + "]");
					for (Iterator it=c.iterator(); it.hasNext(); ) {
						cpLocal = (Tecnico_peticionLocal) it.next();
						if ( cpLocal.getFecha() != null) {
							datosPeticionDTO.setFechaCompromiso(  new Date(cpLocal.getFecha().getTime()) );
							RangoLocal rgLocal = cpLocal.getRango();
							datosPeticionDTO.setNombreRango( rgLocal.getNombre_rango() );
							datosPeticionDTO.setHoraDesde( rgLocal.getHora_desde() );
							datosPeticionDTO.setHoraHasta( rgLocal.getHora_hasta() );
				
							TecnicoLocal tecLocal = cpLocal.getTecnico();
							TecnicoKey tecKey = (TecnicoKey ) tecLocal.getPrimaryKey();
							String nombreTecnico = tecLocal.getNombre() + " " + tecLocal.getApellido();
							datosPeticionDTO.setIdTecnicoInicial( tecKey.cod_tecnico );
							datosPeticionDTO.setNombreTecnicoInicial( nombreTecnico );
		
							if ( cpLocal.getEstado() != null && cpLocal.getEstado().intValue()==1 )
								datosPeticionDTO.setDescripcionEstado( "Activo");
							else
								datosPeticionDTO.setDescripcionEstado( "Reagendado");
		
							Causa_reagendamientoLocal crLocal = cpLocal.getCausa_reagendamiento();
							if ( crLocal != null )
								datosPeticionDTO.setDescripcionCausa( crLocal.getCare_descripcion() );
						}
					}
					// Informacion Reclamo
					peticionesMasivas.add(datosPeticionDTO);
				}
			}//Fin del For
		}
		catch (Exception e)
		{
			log.error("Error al buscar datos masivos ODS",e);
		}
		return peticionesMasivas;		
	}

	public InformacionTecnicaDTO obtenerDatosTecnicosLB(Long codAveOri) throws ATiempoAppEx{
		RecursosInterfaces rI=new RecursosDelegate();
		return rI.obtenerDatosTecnicosLB(codAveOri);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getDatosPeticion(java.lang.Long)
	 */
	public Peticion_stDTO getDatosPeticion(Long codigoAve) throws ATiempoAppEx{
		IncidentesInterfaces iI = new IncidentesDelegate();
		return iI.getDatosPeticion(codigoAve);
	}
	
	public PruebaLineaDTO getUtlimaPruebaLineaByPeticion(Long idPeticion){
		
		log.debug("Valor del ID PETICION QUE LLEGA ->"+idPeticion);
		PruebaLineaDTO pruebaLineaDTO = new PruebaLineaDTO();
		String descPrueba = null;
		try
		{
			UsuarioLocalHome uHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Prueba_lineaLocalHome prueba_lineaHome = (Prueba_lineaLocalHome)HomeFactory.getHome(Prueba_lineaLocalHome.JNDI_NAME);
			Collection listaPruebas = prueba_lineaHome.findByPeticionOrderIdDesc(idPeticion);
			
			for(Iterator iterator = listaPruebas.iterator();iterator.hasNext();){
				Prueba_lineaLocal prueba_lineaLocal = (Prueba_lineaLocal)iterator.next();
				Catalago_prueba_lineaLocal catalago_prueba_lineaLocal=prueba_lineaLocal.getCatalago_prueba_linea();
				Catalago_prueba_lineaKey catalago_prueba_lineaKey=(Catalago_prueba_lineaKey)catalago_prueba_lineaLocal.getPrimaryKey();
				pruebaLineaDTO.setCodPrueba(catalago_prueba_lineaKey.cod_prueba.toString());
				pruebaLineaDTO.setIdFamilia(catalago_prueba_lineaKey.familia_producto_servicio_st_faps_id);
				pruebaLineaDTO.setDescPrueba(catalago_prueba_lineaLocal.getDescripcion());
				pruebaLineaDTO.setFecha(prueba_lineaLocal.getFecha());
				String obsTest = prueba_lineaLocal.getObservacion();
				//Correccion para las pruebas que ya tienen salto de linea en la bd
				if(obsTest.indexOf('\n') > -1 || obsTest.indexOf('\r') > -1){
					obsTest=obsTest.replace('\r','.');
					obsTest=obsTest.replace('\n',' ');	
					prueba_lineaLocal.setObservacion(obsTest);			
				}
//				Correccion para las pruebas que tienen coma simple en la bd
				if(obsTest.indexOf('\'') > -1 || obsTest.indexOf('\'') > -1){
						obsTest=obsTest.replace('\'',' ');
						prueba_lineaLocal.setObservacion(obsTest);			
				}				
				pruebaLineaDTO.setObservacion(obsTest);
				UsuarioLocal ulocal=uHome.findByPrimaryKey(new UsuarioKey(prueba_lineaLocal.getUsua_id()));
				UsuarioKey key=(UsuarioKey) ulocal.getPrimaryKey();
				pruebaLineaDTO.setUsuario(ulocal.getUsua_login());
				pruebaLineaDTO.setIdUsuario(key.usua_id);
				break; //Obtengo el primer registro que es el ultimo diagnostico.
			}
			
		}catch(Exception e){
			log.error("Exception al obtener ultimo diagnostico peticion:" + idPeticion,e);
		}
		return pruebaLineaDTO;
	}
	
	public Collection obtenerTematico(Long codAveOri){
		ArrayList tematicosST = new ArrayList();
		try {
		
			TematicoLocalHome tematicoHome = (TematicoLocalHome) HomeFactory.getHome(TematicoLocalHome.JNDI_NAME);
			Collection tematicos = tematicoHome.findByPeticion(codAveOri);
			Iterator iterTem =  tematicos.iterator();
			while(iterTem.hasNext()){
				TematicoLocal tematicoLocal = (TematicoLocal) iterTem.next();
				TematicoKey tematicoKey = (TematicoKey) tematicoLocal.getPrimaryKey();
				Peticion_stKey peticionKey = (Peticion_stKey) tematicoLocal.getPeticion_st().getPrimaryKey();
				TematicoDTO tematicoDTO = new TematicoDTO();
				tematicoDTO.setCodAveCd(peticionKey.cod_ave_cd);
				tematicoDTO.setCodTematico(tematicoLocal.getCod_tematico());
				tematicoDTO.setCorrelativo(tematicoLocal.getCorrelativo());
				tematicoDTO.setOrigen(tematicoLocal.getOrigen());
				tematicoDTO.setIdTematico(tematicoKey.id_tematico);
				tematicosST.add(tematicoDTO);				
			}
		} catch (Exception e) {
			log.error("Exception al obtener tematicos en peticion:" + codAveOri,e);
		}
		
		return tematicosST;
	}
	
	public ArrayList getCodPruebaLineaByFamilia(Long familia)
	{
		ArrayList codPruebaLinea = new ArrayList();
		CatalogoPruebaLineaDTO catalogoPruebaLineaDTO = null;
		
		try
		{
			Catalago_prueba_lineaLocalHome catalago_prueba_lineaHome = (Catalago_prueba_lineaLocalHome)HomeFactory.getHome(Catalago_prueba_lineaLocalHome.JNDI_NAME);
			Collection listaCodPruebas = catalago_prueba_lineaHome.findByFamilia(familia);
			
			for(Iterator iterator = listaCodPruebas.iterator();iterator.hasNext();)
			{
				Catalago_prueba_lineaLocal catalago_prueba_lineaLocal = ( Catalago_prueba_lineaLocal )iterator.next();
				
				catalogoPruebaLineaDTO = new CatalogoPruebaLineaDTO();
				
				Catalago_prueba_lineaKey key = ( Catalago_prueba_lineaKey )catalago_prueba_lineaLocal.getPrimaryKey();
				 
				catalogoPruebaLineaDTO.setFapsId(key.familia_producto_servicio_st_faps_id);
				catalogoPruebaLineaDTO.setCodPrueba(key.cod_prueba);
				catalogoPruebaLineaDTO.setDescripcion(catalago_prueba_lineaLocal.getDescripcion());
				catalogoPruebaLineaDTO.setGrupoTrabajo(catalago_prueba_lineaLocal.getGrupo_trabajo());
				catalogoPruebaLineaDTO.setTipoIncidencia(catalago_prueba_lineaLocal.getTipoIncidencia());
				
				codPruebaLinea.add(catalogoPruebaLineaDTO);
				
			}
			
		}catch(Exception e){
			log.error("Exception",e);
		}
		return codPruebaLinea;
	}

	public ArrayList getPruebasLineaByPeticion(Long idPeticion){
		
		log.debug("Valor del ID PETICION QUE LLEGA ->"+idPeticion);
		
		ArrayList arregloPruebas = new ArrayList();
		PruebaLineaDTO pruebaLineaDTO = null;
		String descPrueba = null;
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			UsuarioLocalHome uHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Prueba_lineaLocalHome prueba_lineaHome = (Prueba_lineaLocalHome)HomeFactory.getHome(Prueba_lineaLocalHome.JNDI_NAME);
			Collection listaPruebas = prueba_lineaHome.findByPeticion(idPeticion);
			
			for(Iterator iterator = listaPruebas.iterator();iterator.hasNext();){
				
				Prueba_lineaLocal prueba_lineaLocal = (Prueba_lineaLocal)iterator.next();
				
				pruebaLineaDTO = new PruebaLineaDTO();
				Catalago_prueba_lineaLocal catalago_prueba_lineaLocal=prueba_lineaLocal.getCatalago_prueba_linea();
				Catalago_prueba_lineaKey catalago_prueba_lineaKey=(Catalago_prueba_lineaKey)catalago_prueba_lineaLocal.getPrimaryKey();
				pruebaLineaDTO.setCodPrueba(catalago_prueba_lineaKey.cod_prueba.toString());
				descPrueba =catalago_prueba_lineaLocal.getDescripcion(); 
				pruebaLineaDTO.setIdFamilia(catalago_prueba_lineaKey.familia_producto_servicio_st_faps_id);
				pruebaLineaDTO.setDescPrueba(descPrueba);
				pruebaLineaDTO.setFecha(prueba_lineaLocal.getFecha());
				String obsTest = prueba_lineaLocal.getObservacion();				
				//Correccion para las pruebas que ya tienen salto de linea en la bd
				if(obsTest.indexOf('\n') > -1 || obsTest.indexOf('\r') > -1){
					obsTest=obsTest.replace('\r','.');
					obsTest=obsTest.replace('\n',' ');	
					prueba_lineaLocal.setObservacion(obsTest);			
				}
//				Correccion para las pruebas que tienen coma simple en la bd
				if(obsTest.indexOf('\'') > -1 || obsTest.indexOf('\'') > -1){
						obsTest=obsTest.replace('\'',' ');
						prueba_lineaLocal.setObservacion(obsTest);			
				}				
				pruebaLineaDTO.setObservacion(obsTest);
				UsuarioLocal ulocal=uHome.findByPrimaryKey(new UsuarioKey(prueba_lineaLocal.getUsua_id()));
				UsuarioKey key=(UsuarioKey) ulocal.getPrimaryKey();
				pruebaLineaDTO.setUsuario(ulocal.getUsua_login());
				pruebaLineaDTO.setIdUsuario(key.usua_id);
				arregloPruebas.add(pruebaLineaDTO);
				
			}
			
		}catch(Exception e){
			log.error("Exception Prueba de Linea en peticion:" + idPeticion,e);
		}
		
		return arregloPruebas;
	}

	public InformacionAgendamientoDTO obtenerInformacionAgendamiento(Long codAveOri){
			InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();
			try {
				Compromiso_peticionLocalHome cpHome = (Compromiso_peticionLocalHome) HomeFactory.getHome(Compromiso_peticionLocalHome.JNDI_NAME);
				Compromiso_peticionLocal cpLocal = cpHome.findAgendamientoActivo( codAveOri );
				if ( cpLocal.getDia_especifico() != null) {
					infoAgenda.setFechaCompromiso(  new Date(cpLocal.getDia_especifico().getTime()) );
					RangoLocalHome rgHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
					RangoLocal rgLocal = rgHome.findByPrimaryKey( new RangoKey(cpLocal.getId_rango()) );
					infoAgenda.setNombreRango( rgLocal.getNombre_rango() );
					infoAgenda.setHoraDesde( rgLocal.getHora_desde() );
					infoAgenda.setHoraHasta( rgLocal.getHora_hasta() );
				}
			} catch (Exception e) {
				//log.debug("FinderException",e);
				log.warn("No hay informacion de agendamiento para el incidente:"+codAveOri);
			}
		return infoAgenda;
	}

	public ArrayList getHistorico (Long codAveOri)
	{	
		ArrayList listaHistorico = new ArrayList();
			
		try
		{
			Peticion_stLocalHome peticionStHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Collection datosHisto= peticionStHome.findByCodAveOriSn(codAveOri);
			
			for (Iterator iter = datosHisto.iterator(); iter.hasNext();) {
			Peticion_stLocal peticionStLocal = (Peticion_stLocal) iter.next();
							
			Peticion_stDTO datosPeticionDTO = new Peticion_stDTO();

			datosPeticionDTO.setFec_apt_ave_ts(peticionStLocal.getFec_apt_ave_ts());
			datosPeticionDTO.setFec_cie_ave_ts(peticionStLocal.getFec_cie_ave_ts());
			datosPeticionDTO.setCod_cie_ave_cd(peticionStLocal.getCod_cie_ave_cd());

			listaHistorico.add(datosPeticionDTO);
			}
		}catch (Exception e) {
			log.error("Exception al cargar Historico peticion:"+ codAveOri,e);
		}			
		return listaHistorico;		
	}
	
	public ArrayList getTecnicos(Long idUsuario){
		ArrayList listaTecnicos = new ArrayList();
	
			try {
				TecnicoLocalHome tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
				UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
				UsuarioLocal local=usuarioHome.findByPrimaryKey(new UsuarioKey(idUsuario));
				if(local.getEmpresa()==null)
					return listaTecnicos;
				EmpresaKey empresaKey=(EmpresaKey) local.getEmpresa().getPrimaryKey();
				Collection listtecnicos = tecnicoHome.findByEmpresa(empresaKey.empr_id);
				
				for (Iterator iter = listtecnicos.iterator(); iter.hasNext();) {
					TecnicoLocal tecnicoLocal =(TecnicoLocal) iter.next();
					TecnicoDTO tecnicoDTO = new TecnicoDTO();
					
					tecnicoDTO.setCodigo(tecnicoLocal.getCod_tecnico());					
					tecnicoDTO.setNombre(tecnicoLocal.getNombre());
					tecnicoDTO.setApellido(tecnicoLocal.getApellido());
					
					listaTecnicos.add(tecnicoDTO);
				}
			} catch (Exception e) {
				log.error("Exception al cargar tecnicos de User:" + idUsuario,e);
			}
	
		return listaTecnicos;
	}

	public ArrayList getCodigoDiagnostico()
	{
		ArrayList arrCodDiag = new ArrayList();
		
		try{
			Codigo_diagnosticoLocalHome codigo_diagnosticoLocalHome = (Codigo_diagnosticoLocalHome)HomeFactory.getHome(Codigo_diagnosticoLocalHome.JNDI_NAME);
			Collection listaCodigos = codigo_diagnosticoLocalHome.findAll();
			
			for(Iterator iterator = listaCodigos.iterator();iterator.hasNext();){
				
				Codigo_diagnosticoLocal codigo_diagnosticoLocal = (Codigo_diagnosticoLocal)iterator.next();
				
				CodigoDiagnosticoDTO codigoDiagnosticoDTO = new CodigoDiagnosticoDTO();
				
				codigoDiagnosticoDTO.setCodTipoDef(codigo_diagnosticoLocal.getCod_tipo_def());
				codigoDiagnosticoDTO.setDescDefAper(codigo_diagnosticoLocal.getDesc_def_aper());
				codigoDiagnosticoDTO.setDescTipoDef(codigo_diagnosticoLocal.getDesc_tipo_def());
				
				Codigo_diagnosticoKey codigo_diagnosticoKey = (Codigo_diagnosticoKey)codigo_diagnosticoLocal.getPrimaryKey();
				codigoDiagnosticoDTO.setIdDefAper(codigo_diagnosticoKey.id_def_aper);			
				
				arrCodDiag.add(codigoDiagnosticoDTO);
			}
		}catch(Exception e){
			log.error("Error al cargar codigoDiagnostico : "+e, e);
		}
		return arrCodDiag;
	}

	public ArrayList recuperaControlesVisita(Long codAveCd) throws ATiempoAppEx
	{
		ArrayList retorno=new ArrayList();
		try
		{
			
			ControlvisitaLocalHome	controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
			Collection listaControl=controlvisitaHome.findByCodAveCd(codAveCd);
			for(Iterator iterator=listaControl.iterator();iterator.hasNext();)
			{
				ControlvisitaLocal local=(ControlvisitaLocal) iterator.next();
				ControlvisitaKey controlvisitaKey=(ControlvisitaKey) local.getPrimaryKey();
				InformacionCtrlVisitaStDTO dto=new  InformacionCtrlVisitaStDTO();
				dto.setCodAveCd(codAveCd);
				dto.setFechaLlegada(new Fecha(local.getFechahora_llegada()));
				dto.setFechaSalida(new Fecha(local.getFechahora_salida()));
				dto.setFechaRegistro(new Fecha(controlvisitaKey.fechahora_registro));
				retorno.add(dto);
			}
			if(retorno.size()>0)
				Collections.sort(retorno);
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
	/**
	 * DAVID: Se recupera la lista de registros de agendamiento ST para deplegarlas en bandeja.
	 * @param codAve
	 * @return
	 */
	public ArrayList recuperarListaAgendaSC(Long codAve) {

		AgendaScDTO agendaScDTO = null;
		ArrayList listaRetorno = new ArrayList();
		
		try {
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			Collection agendaScList = agendaSCSTLocalHome.findByCodAve( codAve );
			
			for( Iterator agendaScIterator = agendaScList.iterator(); agendaScIterator.hasNext(); ){
				AgendaSCSTLocal agendaSCSTLocal = (AgendaSCSTLocal)agendaScIterator.next();
				
				agendaScDTO = new AgendaScDTO();
				
				agendaScDTO.setIdActucaion(agendaSCSTLocal.getId_actuacion());
				agendaScDTO.setEstado(agendaSCSTLocal.getEstado());
				agendaScDTO.setPetiNumero(agendaSCSTLocal.getId_peticion_st());
				if(agendaSCSTLocal.getMensaje_act()!=null){
					agendaScDTO.setMensajeActuacionST(agendaSCSTLocal.getMensaje_act());
				}else{
					agendaScDTO.setMensajeActuacionST("");
				}
				agendaScDTO.setNombreContratista(agendaSCSTLocal.getNombre_contratista());
				agendaScDTO.setFechaMod(agendaSCSTLocal.getFecha_mod());
				listaRetorno.add(agendaScDTO);
			}
			return listaRetorno;
		}catch(NamingException e){
			log.debug("Error en recuperarListaAgedaSCST: ", e);
			return null;
		}catch(FinderException e){
			log.debug("Error en recuperarListaAgendaSCST: ", e);
			return null;
		}
	}
	
	
}
