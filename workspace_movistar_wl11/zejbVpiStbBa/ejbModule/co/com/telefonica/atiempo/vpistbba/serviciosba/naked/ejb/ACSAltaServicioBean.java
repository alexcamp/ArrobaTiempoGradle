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
public class ACSAltaServicioBean  extends ACSServicioBean {

	/**
	 * 
	 */
	public ACSAltaServicioBean(ACSServicioBean acsServicioBean) {
		super(acsServicioBean);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.serviciosba.naked.ejb.ACSServicioBean#seleccionarAutoConfiguracionPorOC(co.com.atiempo.dto.ModemVpiDTO, co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public TR135E autoConfigurarPorOC(ModemVpiDTO modem) throws ATiempoAppEx {
		
		//Consultamos los productos servicios de la peticion
		Collection pSPCollecton = super.getPeticionLocal().getProducto_servicio_peticion();
		Long opComAutoInstalacion = new Long(super.getPDelegate().recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));

		//Identifica que es una operacion comercial de ALTA
		super.getTr135e().setOperationAcs(ComunInterfaces.ALT_SERVICIO);
				
		//Asignamos el producto que se le envia  a la TR135E 
		super.getTr135e().setServiceValue(asignarProductoDeAltaEnTR135E(pSPCollecton));
		
		//con el fin de obtener el id del PS y la OC de una peticion de ALTAS para BA o BA Naked
		if(!super.isTieneBaEnVuelo())
			super.getTr135e().setCommercialOperationType(asignarAccionYProductoServicio(pSPCollecton, opComAutoInstalacion));

		//Control de cambio y asignacion de la velocidad
		super.getTr135e().setSpeed(new Integer(asignarVelocidadDelPlan(pSPCollecton, opComAutoInstalacion)));
		super.getTr135e().setProductServiceCode(super.getPSCode());
		
		return super.getTr135e();
	}
	
	private String asignarProductoDeAltaEnTR135E (Collection pSPCollecton) {
		//Flags para determinar el producto que se envia a ACS por medio de BROKER
		boolean isBAConvencional = false;
		boolean isIPFija = false;
		boolean isBANaked = false;
		boolean isVozIP = false;
		
//		Asignacion de producto servicios
		for (Iterator PSPIterator = pSPCollecton.iterator(); PSPIterator.hasNext();) {
			//Obtenemos el PS de la peticion
			Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal)PSPIterator.next();
			//Obtenemos la familia del producto servicio de la peticion
			super.setFampsKey(PSPLocal.getFamiliaKey());
			//Obtenemos la llave de la Operacion Comercial
			Operacion_comercialKey opcoKey = (Operacion_comercialKey)PSPLocal.getOperacion_comercial().getPrimaryKey();
			
			//ES BA? ES BANaked? Es Ip_Fija? Es Voip?
			if (super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA)
				isBAConvencional = true;
			if (super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked)
				isBANaked = true;
			if (PSPLocal.getProducto_servicio().getPs_nombre().toUpperCase().matches(".*IP FIJA.*"))
				isIPFija = true;
			if (super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked)
				isVozIP = true;
		}
		//Asignacion de productos para ACS segun los PS de la peticion para altas
		if (isBAConvencional) {
			if (isIPFija)
				return "IPFIJA";
			else 
				return "IPDINAMICA";
		} else if (isBANaked){
			if (isIPFija){
				if (isVozIP)
					return "VOIPFIJA";
				else 
					return "IPFIJA";
			} else {
				if (isVozIP)
					return "IPFIJA";
				else 
					return "IPDINAMICA";
			}
		} else {
			super.setTr135e(null);
			log.error("ACSAltaServicioBean.seleccionarAutoConfiguracionPorOC: No es una Alta");
			return null;
		}
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
		
		String velocidadPlanModem = "4096";//SUGERENCIA DE CESAR, Preguntar a Hector 2014.04.14
		
		for (Iterator listaDePsIt = pSPCollecton.iterator(); listaDePsIt.hasNext();) {
//			Obtenemos el PS de la peticion
			Producto_servicio_peticionLocal prodServPetiLocal = (Producto_servicio_peticionLocal) listaDePsIt.next();
			//Obtenemos la familia del producto servicio de la peticion
			super.setFampsKey(prodServPetiLocal.getFamiliaKey());
			
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
			if (esPsTipoModem){
				
			} else {
				if (super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
					|| super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked) {
					super.setPSCode(prodServPetiLocal.getPsId());
					velocidadPlanModem = prodServPetiLocal.getProducto_servicio().getVelocidad();
				}
			}
			
			if (esPsTipoModem
					|| (super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
					|| super.getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked)) {
				
				super.setPSCode(prodServPetiLocal.getPsId());
				velocidadPlanModem = prodServPetiLocal.getProducto_servicio().getVelocidad();
			}
		}
		
		//si no encuentra velocidad del plan, significa que no hay cambio de velocidad
		//y retorna la velocidad del modem actual
		return Integer.parseInt(velocidadPlanModem);
	}
	
}
