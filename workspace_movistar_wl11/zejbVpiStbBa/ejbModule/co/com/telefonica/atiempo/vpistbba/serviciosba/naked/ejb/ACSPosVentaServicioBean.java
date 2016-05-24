/*
 * Creado el 5/04/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba.naked.ejb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocalHome;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ACSPosVentaServicioBean extends ACSServicioBean {

	/**
	 * 
	 */
	public ACSPosVentaServicioBean (ACSServicioBean acsServicioBean) {
		super(acsServicioBean);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.serviciosba.naked.ejb.ACSServicioBean#seleccionarAutoConfiguracionPorOC(co.com.atiempo.dto.ModemVpiDTO, co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public TR135E autoConfigurarPorOC(ModemVpiDTO modem) throws ATiempoAppEx {
//		Consultamos los productos servicios de la peticion
		Collection pSPCollecton = super.getPeticionLocal().getProducto_servicio_peticion();
		Long opComAutoInstalacion = new Long(super.getPDelegate().recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));

		//Asignamos una opracion comercial de correspondiente a VOIP
		//Asignamos el producto que se le envia  a la TR135E 
		asignarProductoDePosventaEnTR135E(pSPCollecton);
		
		//con el fin de obtener el id del PS y la OC de una peticion de ALTAS para BA o BA Naked
		if(!super.isTieneBaEnVuelo())
			super.getTr135e().setCommercialOperationType(asignarAccionYProductoServicio(pSPCollecton, opComAutoInstalacion));

		//Control de cambio y asignacion de la velocidad
		super.getTr135e().setSpeed(new Integer(asignarVelocidadDelPlan(pSPCollecton, opComAutoInstalacion)));
		super.getTr135e().setProductServiceCode(super.getPSCode());
		
		return super.getTr135e();
	}

	private void asignarProductoDePosventaEnTR135E (Collection pSPCollecton) {
		//Flags para determinar el producto que se envia a ACS por medio de BROKER
		boolean altaVoIP = false;
		boolean bajaVoIP = false;
		boolean altaIPFija = false;
		boolean bajaIPFija = false;
		boolean cambiaVelocidad = false;
		boolean tieneBAConvenvional = false;
		boolean tieneBANaked = false;
		String PCNaked = null;
		String pcBA = null;
		//Asignacion de producto servicios
		for (Iterator PSPIterator = pSPCollecton.iterator(); PSPIterator.hasNext();) {
			//Obtenemos el PS de la peticion
			Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal)PSPIterator.next();
			//Obtenemos la familia del producto servicio de la peticion
			super.setFampsKey(PSPLocal.getFamiliaKey());
//			Obtenemos el tipo de operacion comercial (Alta por cambio de Prod, Baja por cambio de Prod)
			String opcoTipo= PSPLocal.getOperacion_comercial().getOpco_tipo();
			
			if (PSPLocal.getFamiliaKey().faps_id.equals(new Long (ComunInterfaces.familiaBandaAnchaNaked))
					&& (opcoTipo.equals(ComunInterfaces.operacionACP) || opcoTipo.equals(ComunInterfaces.operacionBCP))){
					if(tieneBANaked)
						cambiaVelocidad = true;
					tieneBANaked = true;
					PCNaked = PSPLocal.getNom_pro_ser_no();
			}
			else{ 
				if (PSPLocal.getFamiliaKey().faps_id.equals(new Long (ComunInterfaces.familiaBandaAncha))
						&& (opcoTipo.equals(ComunInterfaces.operacionACP) || opcoTipo.equals(ComunInterfaces.operacionBCP))){
					if(tieneBAConvenvional)
						cambiaVelocidad = true;
					tieneBAConvenvional = true;
					pcBA = PSPLocal.getNom_pro_ser_no();;
				}
			}
			//Si el tipo de operacion comercial es de ALTA CAMBIO PRODUCTO entonces
			if (opcoTipo.equals(ComunInterfaces.operacionACP)){
				if (PSPLocal.getProducto_servicio().getPs_nombre().toUpperCase().startsWith("IP FIJA"))
					altaIPFija = true;
				else if (super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked)
					altaVoIP = true;
				else {
					
				}
			//Si el tipo de operacion comercial es de BAJA CAMBIO PRODUCTO entonces
			} else if (opcoTipo.equals(ComunInterfaces.operacionBCP)) {
				if (PSPLocal.getProducto_servicio().getPs_nombre().toUpperCase().startsWith("IP FIJA"))
					bajaIPFija = true;
				else if (super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked)
					bajaVoIP = true;
				else {
					log.error("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: casos de modems sin implementar");
				}
			}
		}
		
		if(tieneBANaked && tieneBAConvenvional && !cambiaVelocidad){
			try {
				cambiaVelocidad = this.validarCambioVelocidad(PCNaked, pcBA);
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("CSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: Buscando Producto servicio");
			}
		}
		//Asignacion de productos y servicios para ACS segun los ProductoServicios de la peticion para posventas
		if(!cambiaVelocidad){
			if (altaIPFija) {
				if (altaVoIP) { 		//ALTA IP FIJA + ALTA VOIP
					super.getTr135e().setOperationAcs("VOIPFIJA");
					super.getTr135e().setServiceValue("ACTUALIZACION_SERVICIO_ALTA");
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA IP FIJA + ALTA VOIP");
				} else if (bajaVoIP){ 	//ALTA IP FIJA + BAJA VOIP
					super.getTr135e().setOperationAcs("IPFIJA");
					super.getTr135e().setServiceValue("ACTUALIZACION_SERVICIO_ALTA");
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA IP FIJA + BAJA VOIP");
				} else { 				//ALTA IP FIJA
					super.getTr135e().setOperationAcs("IPFIJA");
					super.getTr135e().setServiceValue("ACTUALIZACION_SERVICIO_ALTA");
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA IP FIJA");
				}
			} else if (bajaIPFija) {
				if (altaVoIP) { 		//BAJA IP FIJA + ALTA VOIP
					super.getTr135e().setOperationAcs("VOIP");
					super.getTr135e().setServiceValue("ACTUALIZACION_SERVICIO_ALTA");
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: BAJA IP FIJA + ALTA VOIP");
				} else if (bajaVoIP){ 	//BAJA IP FIJA + BAJA VOIP
					super.getTr135e().setOperationAcs("VOIPFIJA");
					super.getTr135e().setServiceValue("ACTUALIZACION_SERVICIO_BAJA");
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: BAJA IP FIJA + BAJA VOIP");
				} else { 				//BAJA IP FIJA
					super.getTr135e().setOperationAcs("IPFIJA");
					super.getTr135e().setServiceValue("ACTUALIZACION_SERVICIO_BAJA");
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: BAJA IP FIJA");
				}
			} else {
				if (altaVoIP) { 		//ALTA VOIP
					super.getTr135e().setOperationAcs("VOIP");
					super.getTr135e().setServiceValue("ACTUALIZACION_SERVICIO_ALTA");
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA VOIP");
				} else if (bajaVoIP){ 	//BAJA VOIP
					super.getTr135e().setOperationAcs("VOIP");
					super.getTr135e().setServiceValue("ACTUALIZACION_SERVICIO_BAJA");
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: BAJA VOIP");
				} else {
					log.error("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: casos de modems sin implementar");
				}
			}		
		}
	}

	/**
	 * @param naked
	 * @param pcBA
	 * @throws FinderException
	 */
	private boolean validarCambioVelocidad(String naked, String pcBA) throws FinderException {
		// TODO Apéndice de método generado automáticamente
		Producto_servicioLocal psNaked = this.getProducto_servicioLocalHome().findByPS_Nombre(naked);
		Producto_servicioLocal psBA = this.getProducto_servicioLocalHome().findByPS_Nombre(pcBA);
		if(psNaked.getVelocidad() != null && psBA.getVelocidad() != null ){
			int velocidadNaked = Integer.parseInt(psNaked.getVelocidad());
			int velocidadBA = Integer.parseInt(psBA.getVelocidad());
			if(velocidadNaked != velocidadBA)
				return true;
		}
		return false;
	}

	private Long asignarAccionYProductoServicio(Collection pSPCollecton, Long opComAutoInstalacion) {

		try {
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);

			for (Iterator PSPIterator = pSPCollecton.iterator(); PSPIterator.hasNext();) {
				//Obtenemos el PS de la peticion
				Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal) PSPIterator.next();
				//Obtenemos la familia del producto servicio de la peticion
				super.setFampsKey(PSPLocal.getFamiliaKey());
				//Obtenemos la llave de la Operacion Comercial
				Operacion_comercialKey opcoKey = (Operacion_comercialKey) PSPLocal.getOperacion_comercial().getPrimaryKey();
				
				//consulta el modem por PsId, si no existe va al catch para
				// decir que el PS no es tipo modem
				boolean esPsTipoModem = false;
				try {
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = ps_Tipo_ModemLocalHome.findByNroPs(PSPLocal.getPsId());
					esPsTipoModem = true;
				} catch (FinderException e) {
					log.debug("ps: "+ PSPLocal.getPsId() +" no es un ps tipo mòdem");	
					esPsTipoModem = false;
				}

				//REQ BA NAKED adicion familia PC y PS naked
				//Si es Autoinstalcion, Tipo Modem o de la familia de altas para BA,
				//hace una reasignacion del COCode y PSCode
				if (opcoKey.opco_id.longValue() == opComAutoInstalacion.longValue() || esPsTipoModem
						|| super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
						|| super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAncha
						|| super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked
						|| super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked) {

					super.setCOCode((super.getCOCode().intValue() == opComAutoInstalacion.intValue()) ? opcoKey.opco_id : new Long(ComunInterfaces.operacionParActivar));
					break;
				}
			}
			
			return super.getCOCode();

		} catch (NamingException namingException) {
			log.error("ACSAltaServicioBean.asignarAccionYProductoServicio: " + namingException);
		}
		return super.getCOCode();
	}
	
	private int asignarVelocidadDelPlan(Collection pSPCollecton, Long opComAutoInstalacion) {
		
		String velocidadPlanModem = null;
		
		for (Iterator listaDePsIt = pSPCollecton.iterator(); listaDePsIt.hasNext();) {
//			Obtenemos el PS de la peticion
			Producto_servicio_peticionLocal prodServPetiLocal = (Producto_servicio_peticionLocal) listaDePsIt.next();
			//Obtenemos la familia del producto servicio de la peticion
			super.setFampsKey(prodServPetiLocal.getFamiliaKey());
			//Obtenemos la llave de la Operacion Comercial
			Operacion_comercialKey opcoKey = (Operacion_comercialKey) prodServPetiLocal.getOperacion_comercial().getPrimaryKey();
			Operacion_comercialLocal opcoLocal= (Operacion_comercialLocal ) prodServPetiLocal.getOperacion_comercial();
			//consulta el modem por PsId, si no existe va al catch para
			// decir que el PS no es tipo modem
			boolean esPsTipoModem = false;
			try {
				Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = super.getPs_Tipo_ModemLocalHome().findByNroPs(prodServPetiLocal.getPsId());
				esPsTipoModem = true;
			} catch (FinderException e) {
				esPsTipoModem = false;
			}
			//REQ BA NAKED adicion familia PC naked
			if ((opcoKey.opco_id.longValue() == opComAutoInstalacion.longValue() || esPsTipoModem
					|| super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
					|| super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked)
					&& opcoLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)) {
				
				// si es de tipo módem o no..")
				log.debug("Es ps de BA, evaluamos si es de tipo módem o no..");
				if (esPsTipoModem) {
					super.setPSCode(prodServPetiLocal.getPsId());
					velocidadPlanModem = prodServPetiLocal.getProducto_servicio().getVelocidad();
				} else {
					log.debug("No es ps tipo módem pero si de BA, se extraerá su velocidad..");
					super.setPSCode(prodServPetiLocal.getPsId());
					return Integer.parseInt(prodServPetiLocal.getProducto_servicio().getVelocidad());
				}
			}
		}
		
		//si no encuentra velocidad del plan, significa que no hay cambio de velocidad
		//y retorna la velocidad del modem actual
		return Integer.parseInt(velocidadPlanModem);
	}
	
}
