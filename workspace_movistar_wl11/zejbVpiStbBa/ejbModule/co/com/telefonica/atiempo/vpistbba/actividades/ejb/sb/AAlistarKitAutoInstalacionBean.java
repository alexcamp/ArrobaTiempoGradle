package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocalHome;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AAlistarKitAutoInstalacion
 */
public class AAlistarKitAutoInstalacionBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB
	implements
		javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
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
	
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
			
		Long nroPeticion = act.getNumeroPeticion();
		log.debug("Inicio Actividad Alistar Kit Auto Instalacion [" + nroPeticion + "]");	

		/*RQ5606 - Internet Total - mfmendez*/
		Properties_BDLocalHome propertiesBDLocalHome;
		try {
			propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.LLAVE_PROPERTIES_PS_INTERNET_MOVIL);
			
			String psIntTotal = propertiesBDLocal.getValor();
			String[] listaPsIntTotal = null;
	        boolean esPSIntTotal = false;
			
	        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
        	PeticionKey peticionKey = new PeticionKey(nroPeticion);
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
        						
			Collection peticionFlujoList = peticionLocal.getPeticion_flujo();
			
	        if(psIntTotal != null && !psIntTotal.equals("")){
	        	listaPsIntTotal = psIntTotal.split(",");        			        	
//			        Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
//	        		Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACTIVIDAD_INSTALACION));
        		/*Valida que la petición tenga por lo menos un PS de los configurados en la tabla de propiedades (PS_ENVIO_EQUIPOS_SAP)*/
        		for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext() && !esPSIntTotal;){
        			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
        			
        			for(int contPs=0;contPs<=listaPsIntTotal.length-1;contPs++){
        				if (peticionFlujoLocal.getPrse_id().toString().equals(listaPsIntTotal[contPs])){
        					esPSIntTotal = true;
        				}
        			}
        		}
			}
	        
	        /*Si la petición tiene por lo menos un PS de Internet Total*/
	        if(esPSIntTotal){
	        	/*Valida que la peticion tenga por lo menos un PS tipo PC o de Internet Equipado*/
	        	boolean esPSTipoPC = false;
	        	Producto_servicio_peticionLocalHome  psph = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		        Collection productosServicioPeticion = psph.findAllByPetiNumero(nroPeticion);
		       
		        PeticionesDelegate pDelegate = new PeticionesDelegate();
	        	Long OCAutoInstalacion=new Long(pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
	        	boolean esOCAutoInstalacion = false;
	        	
		        for (Iterator iter = productosServicioPeticion.iterator(); iter.hasNext() && !esPSTipoPC && !esOCAutoInstalacion;) {
	        		Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iter.next();
	        		Familia_producto_servicioKey llaveFamilia = (Familia_producto_servicioKey) psp.getFamiliaKey();
	        	    int idFamiliaPsp = llaveFamilia.faps_id.intValue();
//	        	  REQ BA NAKED adicion familia PC naked
	        		if(ComunInterfaces.familiaPcLinea == idFamiliaPsp 
	        				|| ComunInterfaces.familiaPcBA == idFamiliaPsp 
	        				|| ComunInterfaces.familiaPcPsBANaked == idFamiliaPsp 
	        				|| ComunInterfaces.familiaPcTV == idFamiliaPsp 
							|| ComunInterfaces.familiaIntEquipado == idFamiliaPsp){
	        			esPSTipoPC = true;
	        		}
	        		Operacion_comercialKey opcoKey = (Operacion_comercialKey)psp.getOperacion_comercial().getPrimaryKey();
	        		if(opcoKey.opco_id.equals(OCAutoInstalacion)){
	        			esOCAutoInstalacion = true;
	        		}
		        }
	        	
	        	/*Valida si la peticion tiene por lo menos un PS configurado en la tabla PS_TIPO_MODEM*/
	        	Ps_Tipo_ModemLocalHome psTipoModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
	        	Collection listTipoModemLocal = psTipoModemLocalHome.findAll();
	        	boolean esPsTipoModem = false;
	        	
	        	for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext() && !esPsTipoModem;){
        			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
        			
        			for(Iterator psTipoModemIter = listTipoModemLocal.iterator();psTipoModemIter.hasNext();){
        				Ps_Tipo_ModemLocal psTipoModemLocal = (Ps_Tipo_ModemLocal) psTipoModemIter.next();
        				psTipoModemLocal.getPd_id().toString();
        				if (peticionFlujoLocal.getPrse_id().toString().equals(psTipoModemLocal.getPd_id().toString())){
        					esPsTipoModem = true;
        				}
        			}
        		}
	        	
	        	boolean cambioPuertos=false;
	        	if(esPSTipoPC){
	        		//Si hay un ps de tipo pc y no hubo cambio de puerto no se inhibio la actividad de instalar
	        		//y tiene que entrar a alistar kit
	        		RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
	        		cambioPuertos=recursosBADelegate.huboCambiosPuertoIp(nroPeticion);
	        	}
	        	
	        	/*Si existe por lo menos un PS tipo PC o un PS de Venta de Equipos o un PS que se encuentre en la tabla
	        	 *  PS_TIPO_MODEM se inhibe la actividad*/
	        	if(((esPSTipoPC&&cambioPuertos) || esPsTipoModem) && !esOCAutoInstalacion){
	        		/*Se inhibe la actividad*/
	        		log.debug("Se inhibe la actividad de Alistar Kit por tener Internet Total y tener PS Tipo PC, o PS de Venta de Equipos, o PS en la tabla PS_TIPO_MODEM");
					act.setRealizarTerminoInmediato(true);
					act.setObservacion("Se inhibe la actividad de Activar Kit por tener PS de Internet Total y no cumplir validaciones adicionales.");
	        	}
	        }
		} catch (NamingException e) {
			log.debug("Error al obtener recursos para ubicar restricciones de Internet Total: "+e.toString());
		} catch (FinderException e) {
			log.debug("Error al obtener recursos para ubicar restricciones de Internet Total: "+e.toString());
		} catch (Exception e){
			log.debug("Error desconocido al obtener recursos para ubicar restricciones de Internet Total: "+e.toString());
		}
		/*Fin RQ5606 - Internet Total - mfmendez*/
	}

	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}
}
