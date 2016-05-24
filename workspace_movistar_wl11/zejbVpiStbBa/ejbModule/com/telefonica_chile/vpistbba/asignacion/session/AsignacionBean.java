package com.telefonica_chile.vpistbba.asignacion.session;

import java.util.Collection;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.CentralKey;
import co.com.telefonica.atiempo.ejb.eb.CentralLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.asigna.session.AsignaSessionLocal;
import com.telefonica_chile.comun.asigna.session.AsignaSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: Asignacion
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class AsignacionBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;

	// CR21911 - Optimizacion algoritmo de asignación - 28-Ene-2009 - guido
	private static final Logger log = LoggerFactory.getLogger(AsignacionBean.class);

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
	

	/*
	 * Este metodo retorna el usuario que puede atender una Peticion.
	 */
	public Long getIdUsuario(String idPeticion, Long idAmbito, String codigoActividad) {
		Long loginUser = null; 
		HashMap hab = new HashMap();
			
		Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));

		// TODO: Hay que llenar el HashMap con los Valores para los Filtros.
		hab.put("NPETICO", ""+idPeticion);
		hab.put("ACTIVIDAD", ""+codigoActividad);
		
		
		// Llenamos Resto de Filtros.
		try
		{
			PeticionLocalHome pHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal pLocal = pHome.findByPrimaryKey( new PeticionKey(new Long(idPeticion)) );
			/***********************************************/
			//Modificacion para hacer que la misma persona que hace instalacion haga control de instalacion.
			//El algoritmo funciona de la siguiente forma:
			//1.-En el grabador de instalacion se marca la persona que hizo la actividad seteando el AGEN_ID con el id del usuario
			//2.-Cuando se corre el algoritmo de asignacion si la actividad en la que cae es del tipo control <algo> se ocupa el id que está en AGEN_ID, se copia A LINE_TRAS_ID y se limpia AGEND_ID
			//3.-Si la asignacion llega a actividad instalacion nuevamente y tiene seteado el LINE_TRAS_ID la unica posibilidad es que ya haya hecho instalacion antes, por ende se ocupa ese id. Caso INSTALACION-CONTROL_INSTALACION_PGC-INSTALACION
			
			//log.debug("Codigo Actividad:"+codigoActividad);
			Long agen_id = pLocal.getAgen_id();
			if( agen_id!=null &&
			    (codigoActividad.equals("Director de Flujos.Instalacion.Control_Instalacion") ||
			    codigoActividad.equals("Director de Flujos.Desinstalacion_DTH.Control_Desinstalacion_DTH") ||
			    codigoActividad.equals("Director de Flujos.Desinstalacion.Control_Desinstalacion")
			    )
			  )
			{
				Long idAsignado=new Long(agen_id.longValue());
				log.debug("Peticion "+idPeticion+"ya Preasignada al usuario:"+agen_id);
				//pLocal.setLine_tras_id(pLocal.getAgen_id());
				pLocal.setAgen_id(null);
				return idAsignado;
			}
			/*if(pLocal.getLine_tras_id()!=null && (codigoActividad.equals("Director de Flujos.Instalacion.Instalar") || codigoActividad.equals("Director de Flujos.Desinstalacion.Desinstalar") ||  codigoActividad.equals("Director de Flujos.Desinstalacion_DTH.Desinstalar_DTH")))
			{
				log.debug("Peticion "+idPeticion+"ya Preasignada al usuario:"+pLocal.getLine_tras_id());
				pLocal.setAgen_id(pLocal.getLine_tras_id());
				pLocal.setLine_tras_id(null);
				return pLocal.getAgen_id();
			}*/
			/**********************************************/
			LocalidadLocal localidadLocal=pLocal.getFk_01_localidad();
			DepartamentoLocal departamentoLocal=pLocal.getFk_02_departamento();
			CentralLocal centralLocal=pLocal.getFk_03_central();
			LocalidadKey localidadKey=(LocalidadKey) localidadLocal.getPrimaryKey();
			DepartamentoKey departamentoKey=(DepartamentoKey) departamentoLocal.getPrimaryKey();
			
			//La central solo se tiene despues que se ha ido a consultar los recursos.
			if (centralLocal !=null)
			{
				CentralKey centralKey=(CentralKey) centralLocal.getPrimaryKey();
				hab.put("Central",String.valueOf(centralKey.cod_central));
			}
			hab.put("Localidad",String.valueOf(localidadKey.cod_loc));
			hab.put("Departamento",String.valueOf(departamentoKey.cod_dpt));
			hab.put("Segmento",String.valueOf(pLocal.getCod_sgm_cta_cd()));
			hab.put("SubSegmento",String.valueOf(pLocal.getCod_sbg_cta_cd()));
			hab.put("FamiliaVPI",pLocal.getPeti_id_instancia());
			hab.put("TipoOpCo",pLocal.getTica_id());
//			TODO :  Inicio - agonzalez- 03/04/2008 - Se agrega la habilidad TipoError y su correspondiente reseteo a Cero para cuando se derive a PGI desde una JSP
			hab.put("TipoError",String.valueOf(pLocal.getTipoErrorId()));
			pLocal.setTipoErrorId(new Long(0));
//			TODO :  Fin - agonzalez- 03/04/2008 - Se agrega la habilidad TipoError
			
			if(pLocal.getCod_cnl_ven_cd()!=null && !"".equals(pLocal.getCod_cnl_ven_cd())){
				hab.put("Canal",pLocal.getCod_cnl_ven_cd());
			}
			
			if(pLocal.getNom_slo_no()!=null)
				hab.put("Barrio",String.valueOf(pLocal.getNom_slo_no()));
			if(pLocal.isPBX())
				hab.put("PBX","1");
			else
				hab.put("PBX","0");
			
			
			/*
			 *	CR 00024857 - May 26, 2009 - 16
			 *		Se agrega habilidad Terra y se actualiza tabla peticion si corresponde - German P.
			 */

			// Si la actividad actual no es ni PGI ni PGC seteo en 0 el valor de la habilidad. 
			// En caso contrario mantengo el valor que viene en la petición.
			if(codigoActividad != null && !codigoActividad.equals("")){
				if (!(codigoActividad.startsWith("Director de Flujos.PGC") || codigoActividad.startsWith("Director de Flujos.PGI"))){				
						pLocal.setHab_terra(new Integer(0));
						log.debug("Se setea el valor 0 a la habilidad terra de la petición.");
				}
			}
			hab.put("Terra",String.valueOf(pLocal.getHab_terra()));
			
			/*******Nueva Habilidad distribuidor .. no queda otra que preguntar por la relacion***************/
			Collection recursos_linea_basica = pLocal.getRecursos_linea_basica();
			if(recursos_linea_basica!=null && recursos_linea_basica.size()>0)
			{
				Recursos_linea_basicaLocal recLocal=(Recursos_linea_basicaLocal) recursos_linea_basica.iterator().next();
				hab.put("Distribuidor",String.valueOf(recLocal.getDist_sec_asg()));
			}
			/*************************************************************************************************/
		} catch (NamingException e) {
			log.error("Error de Referencia: [" + idPeticion + "," + codigoActividad + "].");
		} catch (FinderException e) {
			log.error("Error de Finder: [" + idPeticion + "," + codigoActividad + "].");
		}
		
		//DEBUG lento -  Date iniDate = new Date();
		try
		{
			AsignaSessionLocalHome asHome = (AsignaSessionLocalHome) HomeFactory.getHome(AsignaSessionLocalHome.JNDI_NAME);
			AsignaSessionLocal asLocal;
			asLocal = asHome.create();

			//DEBUG lento - Date iniDate2 = new Date();
			// Pablo L -- 26/01/2009 -- CR21911 -- Optimizacion asignacion usuario
			//long start = System.currentTimeMillis(); 
			loginUser = asLocal.getIdUsuario(codigoActividad, hab,idAplicacion, idPeticion);
			//long interval = System.currentTimeMillis() - start;
			//log.debug("Algoritmo asignación: [" + idPeticion + "," + codigoActividad + "," + interval + " ms] usrAsignado=" + loginUser);
		} catch (CreateException e) {
			log.error("Error de Create Asignacion: '" + codigoActividad + "'.");
		} catch (NamingException e) {
			log.error("Error de Referencia Asignacion: '" + codigoActividad + "'.");
			return null;
		}

		//DEBUG log.info("Peticion Asignada: [" + idPeticion + "," + codigoActividad + "," + ( (new Date()).getTime() - iniDate.getTime() ) + "]");
		//return loginUser;
		
		if (ServiceLocatorEmulator.emuladorActivado()) {	
			
			//log.error("EMULO USR");
			loginUser = ServiceLocatorEmulator.getIdUsuario();
		}
		
		//log.error(">>>>>>>>>>>>>>>>ID USR:" + loginUser);
		return loginUser;

	}
}
