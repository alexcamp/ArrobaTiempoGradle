/*
 * Created on Apr 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.senders;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Esta clase representa cualquier envio de TR, con un método abstracto para el seteo de los datos 
 * particulares de la TR segun sea el uso que se le de a la TR
 * @author guido
 * 
 */
public abstract class SenderAbstract {

	private static Logger log = LoggerFactory.getLogger(SenderAbstract.class);

//	protected RecursosInterfaces recursosLinea = null; 
//	protected RecursosBAInterfaces recursosBA = null; 
//	protected RecursosTVInterfaces recursosTV = null;
	protected ActividadEJBDTO actDto;
	protected Long petiNumero;
	private PeticionLocal peticionLocal = null;
	private Producto_servicio_peticionLocal producto_servicio_peticionLocal = null;
	private boolean rblLoaded = false;
	private Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
	private boolean pspLoaded = false;
	private static SimpleDateFormat dateFormat_ddMMyyyy = new SimpleDateFormat ("dd/MM/yyyy");
	private boolean reversa;
	/**
	 * Setea datos que son particulares a cada operacion (alta, traslado, cambio nro, etc)
	 * Cada seteo particular tiene una logica propia de que campo, tabla, etc, etc de donde obtener el campo  
	 */
	public abstract void setParticularData(Object tr) throws ATiempoAppEx, NamingException, FinderException;
	
//	public RecursosBAInterfaces getRecursosBA() {
//		return recursosBA;
//	}
//	public void setRecursosBA(RecursosBAInterfaces recursosBA) {
//		this.recursosBA = recursosBA;
//	}
//	public RecursosInterfaces getRecursosLinea() {
//		return recursosLinea;
//	}
//	public void setRecursosLinea(RecursosInterfaces recursosLinea) {
//		this.recursosLinea = recursosLinea;
//	}
//	public RecursosTVInterfaces getRecursosTV() {
//		return recursosTV;
//	}
//	public void setRecursosTV(RecursosTVInterfaces recursosTV) {
//		this.recursosTV = recursosTV;
//	}
	public boolean isReversa() {
		return reversa;
	}
	public void setReversa(boolean reversa) {
		this.reversa = reversa;
	}
	public void setActividadEJBDTO(ActividadEJBDTO actDto) {
		this.actDto = actDto;
		this.petiNumero = actDto.getNumeroPeticion();
	}
	protected PeticionLocal getPeticionLocal() throws NamingException, FinderException {
		if (peticionLocal != null) {
			return peticionLocal;
		}
		PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		PeticionKey peticionKey = new PeticionKey(petiNumero);
		peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
		return peticionLocal;
	}
	
	protected Recursos_linea_basicaLocal getRecursos_linea_basicaLocal() throws NamingException, FinderException {
		if (rblLoaded) {
			return recursos_linea_basicaLocal;
		}
		rblLoaded = true;
		PeticionLocal pet = getPeticionLocal();
		Collection coll = pet.getRecursos_linea_basica();
		int recursos = coll.size();
		if (recursos > 0){
			if (recursos > 1) {
				log.warn("Hay más de una línea básica ("+recursos+") petiNumero=" + petiNumero + " Se usará la primera");
			}
			for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); ) {
				recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
			}
		} else {
			log.warn("No existe recursos linea basica para petiNumero=" + petiNumero);
		}
		return recursos_linea_basicaLocal;
	}
	/**
	 * Retorna el producto_servicio_peticion que se asocia el PS primario.
	 * @throws NamingException
	 */
	protected Producto_servicio_peticionLocal getProducto_servicio_peticionLocal(Long idPs, Long opCo) throws NamingException, ATiempoAppEx {
		if (pspLoaded) {
			return producto_servicio_peticionLocal;
		}
		pspLoaded = true;
		Producto_servicio_peticionLocalHome home = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		Collection coll = null;;
		String msgError = "NO hay producto_servicio_peticion para petiNumero=" + petiNumero + " idPs=" + idPs + " opCo="+opCo;
		try {
			coll = home.findByPetiNumeroPsYOpCo(petiNumero, idPs, opCo);
		} catch (FinderException e) {
			log.error(msgError, e);
			throw new ATiempoAppEx(msgError, e);
		}
		Iterator it = coll.iterator();
		if (it.hasNext()) {
			producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) it.next(); 
		}
		int cant = coll.size();
		if (cant == 0) {
			log.error(msgError);
			throw new ATiempoAppEx(msgError);
		} else if (cant > 1) {
			Producto_servicio_peticionKey pk = (Producto_servicio_peticionKey) producto_servicio_peticionLocal.getPrimaryKey();
			log.warn("Hay MAS de un ("+cant+" registros) producto_servicio_peticion para petiNumero=" + petiNumero + " idPs=" + idPs + " opCo="+opCo + " Se utilizara el primero correlativo=" + pk.correlativo);
//		} else {
//			"se utilizara ...."
		}
		return producto_servicio_peticionLocal;
	}
	/**
	 * Retorna el PS que instanció a la actividad. Es decir el PS que esta en tabla PETICION_FLUJO y que no tiene error en ESTADO_PS_PETICION
	*/
	protected abstract PsVsOcVO getPsPrimario() throws ATiempoAppEx;

	protected String getPsCode() throws ATiempoAppEx {
		PsVsOcVO ps = getPsPrimario();
		return String.valueOf(ps.getPsId());
	}
	/**
	 * Inserta el Registro de Mesajeria en la Tabla Mensaje Estado Linea.
	 * Devuelve el bean insertado
	 */
	public Mensaje_estado_lineaLocal createMensajeEstadoLinea(Long idMsg, Integer phoneNumber) throws FinderException, NamingException, ATiempoAppEx, CreateException {
		
		PsVsOcVO psPrim = getPsPrimario();
		Producto_servicio_peticionLocal psp = getProducto_servicio_peticionLocal(psPrim.getPsId(),psPrim.getOpComId());

		Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
		Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(idMsg);
		mensajeEstadoLineaLocal.setPeti_numero(petiNumero);
		Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) psp.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
		mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
		//NO seteo conector - no se usa desde el codigo - y ademas supongo habria que crear nuevos conectores en la tabla CONECTOR  
		//mensajeEstadoLineaLocal.setF_reference_13();
		mensajeEstadoLineaLocal.setFecha_envio(dateFormat_ddMMyyyy.format (new java.util.Date ()));
		Mensaje_estadoLocal msgEspera = obtenerMensajeEstadoLocal(ComunInterfaces.estadoEspera);
		mensajeEstadoLineaLocal.setF_reference_14(msgEspera);
		mensajeEstadoLineaLocal.setCod_actividad_generadora(actDto.getCodigoActividad());
		
		int areaPhone= 0;
		int numeroPhone= 0;
		if (phoneNumber != null) {
			String sPhoneNumber = String.valueOf(phoneNumber);
			if (sPhoneNumber.length()>1) {
				areaPhone=Integer.parseInt(sPhoneNumber.substring(0,1));
				numeroPhone=Integer.parseInt(sPhoneNumber.substring(1));
			}
		}
		mensajeEstadoLineaLocal.setArea(new Integer(areaPhone));
		mensajeEstadoLineaLocal.setTelefono(new Long(numeroPhone));
		return mensajeEstadoLineaLocal;
	}
	private Mensaje_estadoLocal obtenerMensajeEstadoLocal(int idMsgEstado) throws NamingException {
        try {
            Mensaje_estadoKey key = new Mensaje_estadoKey (new Integer (idMsgEstado)) ;
            Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
            Mensaje_estadoLocal mensajeEstado = msgEstadoLocalHome.findByPrimaryKey (key) ;
            return (mensajeEstado) ;
        } catch (FinderException fex) {
        	log.warn("NO se encontró mensaje estado idMsgEstado=" + idMsgEstado, fex);
            return null;
        }
    }
}
