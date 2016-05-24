package co.com.telefonica.atiempo.soltec.datos_publicacion.ejb.sb;

import java.util.Date;

import javax.ejb.FinderException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stLocalHome;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.bandeja.ejbutiles.UtilesWeb;
import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: DatosPublicacionST
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class DatosPublicacionSTBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
//	GetSessionLocalHome getSession =null;
	protected transient Logger log = LoggerFactory.getLogger(getClass());	
		
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
//		try {
//			getSession=(GetSessionLocalHome) HomeFactory.getHome(GetSessionLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			getSession=null;
//			// TODO Auto-generated catch block
//			log.debug("Error al levantar el session:" + GetSessionLocalHome.JNDI_NAME,e);
//		}
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	/*
	 * Retorna el Objeto para realizar la Publicacion en la BD.
	 * Aca se setean todos los Datos de la Peticion, incluidos los Campos Variables. 
	 */
	public DatosPeticion getDatosPublicacion(Long idPeticion, String codigoActividad) {

		DatosPeticion objDatoPeticion = new DatosPeticion(ApplicationConfig.APP_ATST, idPeticion, codigoActividad);

		//TODO: DATOS PUBLICACION Aca tengo que llenar el resto de Datos para la Bintegrada.
		try {
			//			InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			//			InformacionPeticionDTO peticoDto = infoComunColombiaBI.obtenerInformacionPeticion(idPeticion);

			IncidentesInterfaces iI = new IncidentesDelegate();
			Peticion_stDTO incidenteAtis = iI.getDatosPeticion(idPeticion);			
		

			objDatoPeticion.setNumeroPeticion(idPeticion);
			objDatoPeticion.setNroPeticionAtis(idPeticion);
			
			objDatoPeticion.setFechaAsignacion(new Date());
			objDatoPeticion.setFechaInicio(incidenteAtis.getFec_apt_ave_ts());//Fecha apertura
			String familiaInci=incidenteAtis.getIde_pro_cmr_cd();
//			CR17031 pcawen
//			Fecha compromiso 1, se ignora la fecha compromiso recibida, y se calcula por @tiempo
			Long segmento = incidenteAtis.getCodSgmCtaCd();
			Long subSegm = incidenteAtis.getCodSbgCtaCd();
			String producto = "";
			Long tipoLoc = new Long(0);
			if( segmento != null && subSegm != null){
				String localidad = incidenteAtis.getCod_loc();
				//Obtengo producto
				if(familiaInci!=null)
				{
					if(familiaInci.equals("L"))
						producto="LB";
					else if(familiaInci.equals("BA"))
						producto="BA";
					else if(familiaInci.equals("TV"))
						producto="TV";
					else if(familiaInci.equals("AS"))
						producto="AS";
					else if(familiaInci.equals("IT"))
						producto="IT";
					else if(familiaInci.equals("AP"))
						producto="AP";
					else if(familiaInci.equals("PD"))
						producto="PD";
				}
				//Obtengo tipo localidad
				LocalidadLocalHome locHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				LocalidadLocal locLocal = locHome.findByPrimaryKey(new LocalidadKey(localidad));
				tipoLoc = locLocal.getTipo_loc();
			}else{
				segmento = new Long(0); 
				subSegm = new Long(0);
				producto = "XX";
			}

//			TODO Calculo fecha compromiso y prioridad			
			try{
				Sla_stLocalHome slaHome = (Sla_stLocalHome) HomeFactory.getHome(Sla_stLocalHome.JNDI_NAME);
				Sla_stLocal slaLocal = slaHome.findByPrimaryKey(new Sla_stKey(segmento, subSegm, producto, tipoLoc));
				Integer sla1 = slaLocal.getSla1();
				Integer sla2 = slaLocal.getSla2();
				Integer prioridad = slaLocal.getPrioridad();

				Date fecComp1 = UtilesWeb.agregaHorasFecha(incidenteAtis.getFec_apt_ave_ts(), sla1.intValue());
				Date fecComp2 = UtilesWeb.agregaHorasFecha(incidenteAtis.getFec_apt_ave_ts(), sla2.intValue());

//				objDatoPeticion.setFechaCompromiso(incidenteAtis.getTim_fec_cps_ts());
				objDatoPeticion.setFechaCompromiso(fecComp1);
				objDatoPeticion.setFechaCompromisoSec(fecComp2);
			} catch (FinderException e) {
				log.debug("Exception: "+e);
//				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			}
//			CR17031 - Fin
			
//			objDatoPeticion.setFechaAsignacion(new Date());

			objDatoPeticion.setAreaCliente("");
			objDatoPeticion.setCodigoAgencia("0");

			objDatoPeticion.setEstadoPeticion(null);
//			String familiaInci=incidenteAtis.getIde_pro_cmr_cd();
			String familiaPs="";
			if(familiaInci!=null)
			{
				if(familiaInci.equals("L"))
					familiaPs=ComunInterfaces.LB;
				else if(familiaInci.equals("BA"))
					familiaPs=ComunInterfaces.BA;
				else if(familiaInci.equals("TV"))
					familiaPs=ComunInterfaces.TV;
				//David, req 1235 Asistencia ST
				else if(familiaInci.equals("AS"))
					familiaPs=ComunInterfaces.AS;
				//Fin: David, req 1235 Asistencia ST
				else if(familiaInci.equals("IT"))
					familiaPs=ComunInterfaces.IT;
				else if(familiaInci.equals("AP"))
					familiaPs="314";
			}
			objDatoPeticion.setFamiliaPS(familiaPs);
			
			objDatoPeticion.setIdGrupoSegmento(null);
			objDatoPeticion.setCodigoCentral(incidenteAtis.getCod_central());
			objDatoPeticion.setRutCliente(incidenteAtis.getNum_doc_rte_nu());
			objDatoPeticion.setRutdvCliente(incidenteAtis.getCct_doc_rte_cd());
//			El segmento puede ser nulo				
//			CR17031 - pcawen
			objDatoPeticion.setSegmentoCliente(Utiles.longSinNull(incidenteAtis.getCodSgmCtaCd(),""));
			objDatoPeticion.setSubSegmentoCliente(Utiles.longSinNull(incidenteAtis.getCodSbgCtaCd(),""));
			objDatoPeticion.setCategoria(Utiles.sinNull(incidenteAtis.getCod_ctz_cd(),""));
//			CR17031 - fin
			objDatoPeticion.setServicioCliente("");
			objDatoPeticion.setTipoTrabajo(null);

			//TODO:este el nombre del Cliente??
			objDatoPeticion.setNombreCliente(Utiles.sinNull(incidenteAtis.getNom_rte_sn(), ""));
			objDatoPeticion.setApellidosCliente(Utiles.sinNull(incidenteAtis.getPri_ape_rte_sn(), "") + Utiles.sinNull(incidenteAtis.getSeg_ape_rte_sn(), ""));

			//TODO: hay puntaje???
			objDatoPeticion.setPuntaje(new Integer(0));

			objDatoPeticion.setCodigoDepartamento(incidenteAtis.getCod_dpt());
			objDatoPeticion.setCodigoLocalidad(incidenteAtis.getCod_loc()); // dos primeros digitos departamento, el resto es localidad
			//La central puede ser nula
//			objDatoPeticion.setCodigoCentral(null);

//			} catch (ATiempoAppEx e) {
			} catch (Exception  e) {
			log.error("AtiempoException.", e);
		}

		// Valores variables.
		String[][] aux = null;
		objDatoPeticion.setValoresVariables(aux);

		return objDatoPeticion;
	}
	
}
