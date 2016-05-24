package co.com.telefonica.atiempo.soltec.asignacion.ejb.sb;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.dto.convertidores.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stLocal;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.asigna.session.AsignaSessionLocal;
import com.telefonica_chile.comun.asigna.session.AsignaSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: AsignacionST
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class AsignacionSTBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	
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
	
	public HashMap getHabilidades(Long idPeticion, String codigoActividad) throws ATiempoAppEx{
		HashMap hab = new HashMap();
		hab.put("NPETICO", ""+idPeticion);
		hab.put("ACTIVIDAD", ""+codigoActividad);
		try
			{
			IncidentesInterfaces iI = new IncidentesDelegate();
			Peticion_stDTO incidenteAtis = iI.getDatosPeticion(idPeticion);

			RecursosInterfaces ri = new RecursosDelegate();
			InformacionTecnicaDTO infoTec = ri.obtenerDatosTecnicosLB(idPeticion);
									
			String depto ="";
			String localidad ="";
			Long idCentral=null;
			String distribuidor ="";
			
			if (infoTec != null){
				idCentral = infoTec.getCentral();
				if(idCentral!=null){
					hab.put("Central",idCentral.toString());
				}
				distribuidor = infoTec.getDistr();
				if(distribuidor!=null && distribuidor.length()>0){
					hab.put("Distribuidor",distribuidor);
				}
			}
			// TODO: Hay que llenar el HashMap con los Valores para los Filtros.
			hab.put("Localidad",incidenteAtis.getCod_loc());
			hab.put("Departamento",incidenteAtis.getCod_dpt());
			hab.put("FamiliaPS",incidenteAtis.getIde_pro_cmr_cd());
		}catch (Exception e){
				log.debug("Exceptio en getHabilidades",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
		return hab;
	}

	/**
	 * David: Método para saber si hay ps de IT ST de PROV1 o PROV2, req 3947. este método se manejó de forma diferente que en el caso de
	 * esPsAsistProv1o2(Producto_servicio_peticionLocal), porque se puede extraer el psId del objeto Peticion_st, por eso lo recibe como parámetro.
	 * Con esto se evita tener que registrar todos los ps's de venta de equipos en la tabla Producto_servicio_st, tal como se hizo para 
	 * asistencia. Enero 25 2011.
	 * @param psp
	 * @return
	 */
	private int esPsITProv1o2(Peticion_stLocal peticionLocal){
		
		boolean esIT1=false;
		
		boolean esIT2=false;
		
		String psProv1 = STConfig.getVariable("PS_PROV_1_IT");
		String[] listaPsProv1 = psProv1.split(",");
		
		String psProv2 = STConfig.getVariable("PS_PROV_2_IT");
		String[] listaPsProv2 = psProv2.split(",");
		
		for (int contPsProv1 = 0; contPsProv1 <= listaPsProv1.length - 1; contPsProv1++) {
			int psProv1Int = Integer.parseInt(listaPsProv1[contPsProv1]);
			
			Long psId=peticionLocal.getCod_pro_ser_cd();
			
			if (psId.intValue() == psProv1Int) {
				log.debug("Es un ps de IT Prov 1..."+psProv1Int);
				esIT1 = true;
				break;
			}
		}
		
		for (int contPsProv2 = 0; contPsProv2 <= listaPsProv2.length - 1; contPsProv2++) {
			int psProv2Int = Integer.parseInt(listaPsProv2[contPsProv2]);
			
			Long psId=peticionLocal.getCod_pro_ser_cd();
			
			if (psId.intValue() == psProv2Int) {
				log.debug("Es un ps de IT Prov 2..."+psProv2Int);
				esIT2 = true;
				break;
			}
		}
		
		if(esIT1){
			return 1;
		}else if (esIT2){
			return 2;
		}else{
			return 0;
		}
		
	}
	
	
	/**
	 * David: Método para saber si hay ps de Asistencia ST de PROV1 o PROV2, req 1235
	 * @param psp
	 * @return
	 */
	private int esPsAsistProv1o2(co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal psp){
		
		boolean esAsistencia1=false;
		
		boolean esAsistencia2=false;
		
		String psProv1 = STConfig.getVariable("PS_PROV_1");
		String[] listaPsProv1 = psProv1.split(",");
		
		String psProv2 = STConfig.getVariable("PS_PROV_2");
		String[] listaPsProv2 = psProv2.split(",");
		
		for (int contPsProv1 = 0; contPsProv1 <= listaPsProv1.length - 1; contPsProv1++) {
			int psProv1Int = Integer.parseInt(listaPsProv1[contPsProv1]);
			
			Producto_servicio_stLocal ps=psp.getProducto_servicio_st();
			Producto_servicio_stKey key=(Producto_servicio_stKey)ps.getPrimaryKey();
			
			if (key.ps_id.intValue() == psProv1Int) {
				log.debug("Es un ps de Asistencia Prov 1..."+psProv1Int);
				esAsistencia1 = true;
				break;
			}
		}
		
		for (int contPsProv2 = 0; contPsProv2 <= listaPsProv2.length - 1; contPsProv2++) {
			int psProv2Int = Integer.parseInt(listaPsProv2[contPsProv2]);
			
			Producto_servicio_stLocal ps=psp.getProducto_servicio_st();
			Producto_servicio_stKey key=(Producto_servicio_stKey)ps.getPrimaryKey();
			
			if (key.ps_id.intValue() == psProv2Int) {
				log.debug("Es un ps de Asistencia Prov 2..."+psProv2Int);
				esAsistencia2 = true;
				break;
			}
		}
		
		if(esAsistencia1){
			return 1;
		}else if (esAsistencia2){
			return 2;
		}else{
			return 0;
		}
		
	}
	/**
	 * David, método para extraer el id de usuario para PROV1 o PRV2, req 1235
	 * @param idPeticion
	 * @param codigoActividad
	 * @return
	 */
	private Long extraerIdProveedor(String loginUsuario){
		Long idUsuarioProv=null;
		try{
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuarioProv=usuarioHome.findByLogin(loginUsuario);
			UsuarioKey usuarioProvKey=(UsuarioKey) usuarioProv.getPrimaryKey();
			idUsuarioProv=usuarioProvKey.usua_id;	
			}catch(Exception e){
				log.debug("Error al extraer los usuarios del proveedor: "+e.toString());
			}
			return idUsuarioProv;
	}
	
	/*
	 * Este metodo retorna el usuario que puede atender una Peticion.
	 */
	public Long getIdUsuario(String idPeticion, String codigoActividad) {
		Long loginUser = null;
		Long numPeti = new Long(idPeticion); 
		Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
		Date iniDate = new Date();
		try
		{
			AsignaSessionLocalHome asHome = (AsignaSessionLocalHome) HomeFactory.getHome(AsignaSessionLocalHome.JNDI_NAME);
			AsignaSessionLocal asLocal;
			asLocal = asHome.create();
			HashMap hab = this.getHabilidades(numPeti,codigoActividad);
			Date iniDate2 = new Date();
			
			//David, req 1235 Asistencia ST
	        boolean esAsistencia1=false;    		
			boolean esAsistencia2=false;
			
			boolean esKiosko=false;    		
			boolean esZWF=false;
			
			boolean esIT1=false;
			boolean esIT2=false;
					
			//David, req 1060 Kiosko y Zona WIFI
			//David, req 3947, venta equipos ST, Enero 25 2011
			if(codigoActividad.equals(ComunInterfaces.ACT_ASISTENCIA_ST)||codigoActividad.equals(ComunInterfaces.ACT_PE_KIOSKO_ZWF)||codigoActividad.equals(ComunInterfaces.ACT_PE_VE)){
				log.debug("Estamos en asistencia cliente ST, en PE Kiosko y ZWF o en PE Venta equipos");
				
				try{
					Peticion_stKey pk=new Peticion_stKey(new Long(idPeticion));
	                Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
	                Peticion_stLocal peticionLocal=peticionLocalHome.findByPrimaryKey(pk);

	                Collection listaPSP=peticionLocal.getProducto_servicio_peticion();
	                Iterator PSPIt;
	                
	                for(PSPIt=listaPSP.iterator();PSPIt.hasNext();){
	                    co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal psp=(co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal)PSPIt.next();
	                                    			
            			if(esPsAsistProv1o2(psp)==1){
            				esAsistencia1=true;
            			}else if(esPsAsistProv1o2(psp)==2){
            				esAsistencia2=true;
            			}else if(esPskioskoOZWF(psp)==1){
            				esKiosko=true;
            			}else if(esPskioskoOZWF(psp)==2){
            				esZWF=true;
            			}else if(esPsITProv1o2(peticionLocal)==1){
            				esIT1=true;
            			}else if(esPsITProv1o2(peticionLocal)==2){
            				esIT2=true;
            			}                        			

	                }
	                
				}catch(FinderException e){
					log.debug("Error al tratar de encontrar la petición soltec");
				}
			}
			
			if(esAsistencia1||esIT1){
				loginUser=extraerIdProveedor("PROV1");
			}else if(esAsistencia2||esIT2){
				loginUser=extraerIdProveedor("PROV2");
			}else if(esKiosko){
				loginUser=extraerIdProveedor("PROV_KIOSKO");
			}else if(esZWF){
				loginUser=extraerIdProveedor("PROV_ZWF");
			}else{
				loginUser = asLocal.getIdUsuario(codigoActividad, hab,idAplicacion, numPeti.toString());
			}
			//	Fin: David, req 1060 Kiosko y Zona WIFI
			//	Fin: David, req 1235 Asistencia ST
			//	Fin: David, req 3947, venta equipos ST, Enero 25 2011
			

			
			log.debug("Buscando Usuario: [" + idPeticion + "," + codigoActividad + "," + ( (new Date()).getTime() - iniDate2.getTime() ) + "]");
		} catch (CreateException e) {
			log.error("Error de Create Asignacion: '" + codigoActividad + "'.",e);
		} catch (NamingException e) {
			log.error("Error de Referencia Asignacion: '" + codigoActividad + "'.",e);
		} catch (ATiempoAppEx e) {
			log.error("Error en Asignacion: '" + codigoActividad + "'.",e);
		}
//		 catch (FinderException e) {
//			log.error("Error de Finder: [" + idPeticion + "," + codigoActividad + "].");
//		}

		log.info("Peticion Asignada: [" + idPeticion + "," + codigoActividad + "," + ( (new Date()).getTime() - iniDate.getTime() ) + "]");

		return loginUser;
	}
	
	/**
	 * David: Método para saber si hay ps de Kiosko o ZWF, req 1060
	 * @param psp
	 * @return
	 */
	private int esPskioskoOZWF(co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal psp){
		
		boolean esKiosko=false;
		
		boolean esZWF=false;
		
		String psKiosko = STConfig.getVariable("PS_KIOSKO");
		String[] listaPsKiosko = psKiosko.split(",");
		
		String psZWF = STConfig.getVariable("PS_ZWF");
		String[] listaPsZWF = psZWF.split(",");
		
		for (int contPsKiosko = 0; contPsKiosko <= listaPsKiosko.length - 1; contPsKiosko++) {
			int psKioskoInt = Integer.parseInt(listaPsKiosko[contPsKiosko]);
			
			Peticion_stLocal peticion_stLocal=psp.getPeticion_st();
			Long psId=peticion_stLocal.getCod_pro_ser_cd();			
			
			if (psId.intValue() == psKioskoInt) {
				log.debug("Es un ps de Kiosko..."+psKioskoInt);
				esKiosko = true;
				break;
			}
		}
		
		for (int contPsZWF = 0; contPsZWF <= listaPsZWF.length - 1; contPsZWF++) {
			int psZWFInt = Integer.parseInt(listaPsZWF[contPsZWF]);
			
			Peticion_stLocal peticion_stLocal=psp.getPeticion_st();
			Long psId=peticion_stLocal.getCod_pro_ser_cd();
			
			if (psId.intValue() == psZWFInt) {
				log.debug("Es un ps de ZWF..."+psZWFInt);
				esZWF = true;
				break;
			}
		}
		
		if(esKiosko){
			return 1;
		}else if (esZWF){
			return 2;
		}else{
			return 0;
		}
		
	}

	
}
