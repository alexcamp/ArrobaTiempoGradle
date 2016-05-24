/*
 * Created on Apr 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.senders;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR601E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Esta clase tiene todos los metodos/servicions base/genericos para los datos que se necesitan enviar
 * en los servicios a ALTMIRA. Aunque algunos de los metodos podrian "subirse" a la clase super/abstracta.
 *  
 * @author guido
 */
public class SenderTr601Base extends SenderAbstract {

	private static Logger log = LoggerFactory.getLogger(SenderTr601Base.class);
	private String atiempoServiceName;
	private PsVsOcVO psPrimario;
	
	public void setParticularData(Object tr) throws ATiempoAppEx, NamingException, FinderException {
		TR601E tr601e = (TR601E) tr;
		tr601e.setPsCode(getPsCode());
		tr601e.setPhoneNumber(getPhoneService());
		tr601e.setCycle(getCicloFacturacion()); // ciclo facturacion + use type son para homolagacion de broker
		tr601e.setUseType(getUseType());

	}
	public void setATiempoServiceName(String atiempoServiceName) {
		this.atiempoServiceName = atiempoServiceName;
	}
	public String getATiempoServiceName() {
		return this.atiempoServiceName;
	}

	public ActividadEJBDTO getActividadEJBDTO() {
		return this.actDto;
	}
	/**
	 * Retorna el PS que instanció a la actividad. Es decir el PS que esta en tabla PETICION_FLUJO y que no tiene error en ESTADO_PS_PETICION
	*/
	protected PsVsOcVO getPsPrimario() throws ATiempoAppEx {
		if (psPrimario != null) {
			return psPrimario;
		}
		ArrayList psOk = actDto.getPsOk();
		if (psOk == null || psOk.size() == 0) {
			throw new ATiempoAppEx("No hay PS OK para invocar al servicio de altamira: ["+atiempoServiceName+"] con petiNumero=" + petiNumero + " actividadFlujo=" + actDto.getIdActividadFlujo());
		}
		if (psOk.size() == 1) {
			psPrimario = (PsVsOcVO) psOk.get(0);
		} else {
			Iterator it = psOk.iterator();
			String tipoOp = null;
			while (it.hasNext()) {
				PsVsOcVO ps = (PsVsOcVO) it.next();
				tipoOp = ps.getOpComTipo();
				if (isTipoOpAcordePsPrimario(tipoOp)) { // Si es de tipo ACP, A o BCP, B
					psPrimario = ps;
					break;
				}
			}
		}
		if (psPrimario == null) {
			throw new ATiempoAppEx("NO hay PS OK para invocar al servicio de altamira: ["+atiempoServiceName+"] con petiNumero=" + petiNumero + " actividadFlujo=" + actDto.getIdActividadFlujo() + " PSs=" + psOk.size());
		} else {
			log.debug("Obteniendo PS primario: cantidad=" + psOk.size()+" Utilizado: " + psPrimario + " petiNumero=" + petiNumero + " atiempoServiceName=" + atiempoServiceName);
			return psPrimario;
		}
	}
	/**
	 * Este método se utiliza para el caso en que los PSs que instanciarion la actividad sean mas de uno,
	 * y esto ocurre en los flujos de "Cambio de Plan" o "Cambio de Producto"
	 * 
	 * Para estos flujos la regla que se toma para Altamira es la siguiente:
	 * Para las actividades de Alta (alta abonado o alta periodificación) o Cambio Tipo Prepago,
	 * se usa el PS DESTINO como el PS a utilizar y/o enviar a los servicios de Altamira, 
	 * y para las actividades de baja (baja abonado y baja periodificación) se utiliza el PS ORIGEN.
	 * 
	 * La obtención del PS DESTINO es a travez del tipo de operación comercial "A" o "ACP" (Alta por Cambio de Plan),
	 * mientras que la obtención del PS ORIGEN es a travez del tipo de operación comercial "B" o "BCP" (Baja por Cambio de Plan)
	 * 
	 * Para estos flujos (Cambio de Plan y Cambio de Producto) NO hay reversa, segun doc "ALTAMIRA Entendimiento.doc"
	 * Por lo cual no hay soporte para la reversa en esta logica.
	 *      
	 * @param tipoOp Tipo de Operación Comercial
	 * @return Retorna true si el tipo de operación comercial es la correspondiente con el PS que se utiliza,
	 * tipoOp A o ACP para altas, cambios, etc
	 * tipoOp B o BCP para bajajas  
	 */
	protected boolean isTipoOpAcordePsPrimario(String tipoOp) {
		if (tipoOp == null) {
			return false;
		}
		if (tipoOp.equals("A") || tipoOp.equals("ACP")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 *	Devuelve el numero de telefono para una peticion que se asume es de Linea Basica.  
	 */
	protected Integer getPhoneService() throws NamingException, FinderException {
		PeticionLocal pet = getPeticionLocal();
		String peticionPhone = pet.getNum_ide_nu_stb();
		Integer phone = null;
		if (peticionPhone != null && peticionPhone.trim().length() != 0) {
			log.debug("getPhoneService() - Caso 1 - peticion.num_ide_nu_stb - Utilizando phone=" + peticionPhone + " petiNumero=" + petiNumero);
			phone = new Integer(peticionPhone.trim());
		} else {
			Recursos_linea_basicaLocal rec = getRecursos_linea_basicaLocal();
			if (rec != null && rec.getTelefono_asg() != null) {
				phone = new Integer(rec.getTelefono_asg().intValue());
			}
			log.debug("getPhoneService() - Caso 2 - recursos_linea_basica.telefono_asg - Utilizando phone=" + phone + " petiNumero=" + petiNumero);
		}
		return phone;
	}
/**
 *	Devuelve el numero de telefono ANTERIOR para una peticion que se asume es de Linea Basica.  
 */
	protected Integer getPhoneServiceAnterior() throws NamingException, FinderException {
		Integer phoneAnterior = null;
		PeticionLocal pet = getPeticionLocal();
		Recursos_linea_basicaLocal rec = getRecursos_linea_basicaLocal();
		// El campo telefono_asg se setea en la Asignacion de Recursos, 
		// en la actividad asignacion manual cuando recibe una TR003s 
		if (rec != null && rec.getTelefono_ant() != null) {
			phoneAnterior = new Integer(rec.getTelefono_ant().intValue());
		}
		return phoneAnterior; 
	}

	protected Long getUseType() throws NamingException, ATiempoAppEx {
		PsVsOcVO psPrim = getPsPrimario();
		Producto_servicio_peticionLocal psp = getProducto_servicio_peticionLocal(psPrim.getPsId(),psPrim.getOpComId());
//No es necesario esto porque getProducto_servicio_peticionLocal() tira exception
//		if (psp == null) {
//			log.warn("Peticion sin ciclo de facturacion petiNumero=" + petiNumero);
//			return null;
//		}
		return psp.getCod_tip_uso(); // Idem TR010, tr010e.setUseType()
	}
	protected String getCicloFacturacion() throws NamingException, FinderException {
		PeticionLocal pet = getPeticionLocal();
		//return "0000"; //o 0001, 0002, 0004, 0005, 0007 para probar
		//CR - 28869 - Merge Actualizacion de Altamira con Cre-Umts 
		return pet.getInf_cicl_fac();
	}
//	protected String phoneToXDigits(String phoneNumber) {
//		if (phoneNumber!=null && !phoneNumber.trim().equals("")){
//			if (phoneNumber.length()>8){ 
//				phoneNumber=phoneNumber.substring(0,8);
//			}
//		} else {
//			phoneNumber="0";
//		}
//		return phoneNumber;
//	}
	/**
	 * Retorna true si el telefono anterior y el actual son iguales.
	 */
	public boolean equalCurrentPhoneAndOldPhone() throws NamingException, FinderException {
		Integer currentPhone = getPhoneService();
		Integer oldPhone = getPhoneServiceAnterior();
		log.debug("Comparando telefonos para petiNumero=" + petiNumero + " currentPhone=" + currentPhone + " oldPhone=" + oldPhone);
		int current = 0;
		int old = 0;
		if (currentPhone != null) {
			current = currentPhone.intValue();
		}
		if (oldPhone != null) {
			old = oldPhone.intValue();
		}
		return (current == old);
	}
	/**
	 * Devuelve true si el envio y por ende la actividad se inhiben,
	 * es decir que no se ejecuta la actividad.
	 */
	public boolean inhibirEnvio() throws NamingException, FinderException {
		return false;
	}
	/** 
	 * Devuelve el mensaje por el cual se inhibe el envio. 
	 * Este metodo es indendiente de lo que devuelva el metodo método inhibirEnvio()
	 */
	public String getMensajeInhibicion() {
		return null;
	}
}
