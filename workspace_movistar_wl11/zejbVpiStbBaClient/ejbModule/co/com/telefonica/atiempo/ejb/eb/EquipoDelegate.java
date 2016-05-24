/*
 * Created on Mar 23, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.ejb.eb;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR029S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR053S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR611S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EquipoDelegate implements EquipoLocal{
    
    
    private EquipoLocal servicios;

	public EquipoDelegate() throws ATiempoAppEx {
		try {
			
			servicios = ((EquipoLocalHome) HomeFactory.getHome(EquipoLocalHome.JNDI_NAME)).create();
			
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#enviaEquipoPorUtilizar(long, java.lang.String)
     */
    public Long enviaEquipoPorUtilizar(long idPeticion, String ult4Digitos) throws ATiempoAppEx {
        
        return servicios.enviaEquipoPorUtilizar(idPeticion,ult4Digitos);
    }

	// German P. - Se pasa contratista como argumento
    public Long enviaEquipoPorUtilizar(long idPeticion, String ult4Digitos, Long idEmContratista) throws ATiempoAppEx {
        return servicios.enviaEquipoPorUtilizar(idPeticion,ult4Digitos, idEmContratista);
    }
    
	
    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#esTipoPDTI(java.lang.Long)
     */
    public Collection esTipoPDTI(Long ps_id) throws ATiempoAppEx { 
        return servicios.esTipoPDTI(ps_id);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#buscarRespuestaMensajeEquipo(java.lang.Long, java.lang.Long)
     */
    public TR026S buscarRespuestaMensajeEquipo(Long idPeticion, Long idMensaje) throws ATiempoAppEx {
        return servicios.buscarRespuestaMensajeEquipo(idPeticion,idMensaje);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#actualizaEquipoPorUtilizar(co.com.telefonica.atiempo.interfaces.atiempo.TR026S)
     */
    public void actualizaEquipoPorUtilizar(TR026S tr026s) throws ATiempoAppEx {
        servicios.actualizaEquipoPorUtilizar(tr026s);        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#getEquipos(java.lang.Long)
     */
    public Collection getEquipos(Long idPeticion) throws ATiempoAppEx {
        return servicios.getEquipos(idPeticion);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#pestanaEquipos(java.lang.Long)
     */
    public Collection pestanaEquipos(Long ps_id) throws ATiempoAppEx {
        return servicios.pestanaEquipos(ps_id);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#obtenerElementos(java.lang.Long)
     */
    public Collection obtenerElementos(Long ps_id) throws ATiempoAppEx {
        return servicios.obtenerElementos(ps_id);
    }

	public void grabaEquiposVpi(Long nroPeticion, Long telAsignado, ArrayList modems)
	{
		servicios.grabaEquiposVpi(nroPeticion,telAsignado,modems);	
	}

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#noHayEquipoParaActualizarInventarioBa(java.lang.Long)
     */
    public boolean noHayEquipoParaActualizarInventarioBa(Long nroPeticion) throws ATiempoAppEx {       
        return servicios.noHayEquipoParaActualizarInventarioBa(nroPeticion);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#enviaActualizaInventarioEquipo(java.lang.String, java.lang.String)
     */
    public void enviaActualizaInventarioEquipo(String peticion, String id_actividad) throws ATiempoAppEx {
        servicios.enviaActualizaInventarioEquipo(peticion,id_actividad);
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#recepcionActualizaInventarioBA(co.com.telefonica.atiempo.interfaces.atiempo.TR028S)
     */
    public void recepcionActualizaInventarioBA(TR028S tr028s) throws ATiempoAppEx {
        servicios.recepcionActualizaInventarioBA(tr028s);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#identificarOperacion(java.lang.Long)
     */
    public Integer identificarOperacion(Long nroPeticion) {
        return servicios.identificarOperacion(nroPeticion);
    }

    /* (non-Javadoc)
     * @see javax.ejb.EJBLocalObject#getEJBLocalHome()
     */
    public EJBLocalHome getEJBLocalHome() throws EJBException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.ejb.EJBLocalObject#getPrimaryKey()
     */
    public Object getPrimaryKey() throws EJBException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.ejb.EJBLocalObject#remove()
     */
    public void remove() throws RemoveException, EJBException {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.ejb.EJBLocalObject#isIdentical(javax.ejb.EJBLocalObject)
     */
    public boolean isIdentical(EJBLocalObject arg0) throws EJBException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#loadJSP(co.com.telefonica.atiempo.interfaces.atiempo.TR026S)
     */
    public ArrayList loadJSP(TR026S tr026s, Long idPeticion) {        
        return servicios.loadJSP(tr026s,idPeticion);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#recepcionConfiguracionActualEquipo(co.com.telefonica.atiempo.interfaces.atiempo.TR027S)
     */
    public void recepcionConfiguracionActualEquipo(TR027S tr027s) throws ATiempoAppEx {
       servicios.recepcionConfiguracionActualEquipo(tr027s);
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#enviarConfiguracionActualEquipos(java.lang.String, java.lang.String, java.lang.String)
     */
    public void enviarConfiguracionActualEquipos(String telefonoConsulta, String peticion, String id_actividad) throws ATiempoAppEx {
       servicios.enviarConfiguracionActualEquipos(telefonoConsulta,peticion,id_actividad);
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#enviarConfiguracionActualEquipos(java.lang.String, java.lang.String)
     */
    public void enviarConfiguracionActualEquipos(String peticion, String id_actividad) throws ATiempoAppEx {
        servicios.enviarConfiguracionActualEquipos(peticion,id_actividad);        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#recuperaEquiposVPi(java.lang.Long)
     */
    public ArrayList recuperaEquiposVPi(Long nroPeticion) {        
        return servicios.recuperaEquiposVPi(nroPeticion);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#recuperaEquiposBuscador(java.lang.Long)
     */
  
  	//UMTS Se copia el metodo "enviaEquipoPorUtilizar" agregando un nuevo parametro de entrada 
    public Long enviaEquipoPorUtilizar(long nroPet, String ult4Digitos,String tipoEquipo, String tipoElemento)throws ATiempoAppEx {
        
        return servicios.enviaEquipoPorUtilizar(nroPet,ult4Digitos,tipoEquipo,tipoElemento);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#recuperaEquiposVPiUmts(java.lang.Long)
     */
    public ArrayList recuperaEquiposVPiUmts(Long nroPeticion) {
        return servicios.recuperaEquiposVPiUmts(nroPeticion);
    }

    public ArrayList recuperaEquiposVPiPdti(Long nroPeticion) {
        return servicios.recuperaEquiposVPiPdti(nroPeticion);
    }
    
    public Collection getEquiposGrupo(Long idPeticion, int ID_GRUPO_PDTI) throws ATiempoAppEx {
        return servicios.getEquiposGrupo(idPeticion,ID_GRUPO_PDTI);
    }
    
    public void recibirPrimeraFacturaInternetEquipado(TR044S tr044s) throws ATiempoAppEx{
    	servicios.recibirPrimeraFacturaInternetEquipado(tr044s);
    }
    
    /*RQ 6895 - Interfaz Atiempo - MM SAP*/
	public boolean enviarInformacionEquiposMMSAP(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		return servicios.enviarInformacionEquiposMMSAP(act, nroPeticion, actGeneradora);
	}
	/*RQ 6895 - Interfaz Atiempo - MM SAP*/
	public void procesarRespuestaInformacionEquiposMMSAP(TR029S tr029s) throws ATiempoAppEx{
		servicios.procesarRespuestaInformacionEquiposMMSAP(tr029s);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#recibirConfirmacionPagosTVSola(co.com.telefonica.atiempo.interfaces.atiempo.TR611S)
	 */
	public void recibirConfirmacionPagosTVSola(TR611S tr611s, String CorrelationID) throws ATiempoAppEx {
		servicios.recibirConfirmacionPagosTVSola(tr611s, CorrelationID);
	}
	
	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaVistaGlobal(TR025S tr025s) throws ATiempoAppEx{
		servicios.procesarRespuestaVistaGlobal(tr025s);
	}
	
	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaNotificacionVentaMinoristasSAP(TR020S tr020s) throws ATiempoAppEx{
		servicios.procesarRespuestaNotificacionVentaMinoristasSAP(tr020s);
	}
	
	/*RQ.8595 - mfmendez*/
	public void enviarSolicitudVistaGlobal(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		servicios.enviarSolicitudVistaGlobal(act, nroPeticion, actGeneradora);
	}
	
	/*RQ.8595 - mfmendez*/
	public void enviarInformacionEquiposVentaMinoristasSAP(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		servicios.enviarInformacionEquiposVentaMinoristasSAP(act, nroPeticion, actGeneradora);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.ejb.eb.EquipoLocal#ConsultarEstadoDTH(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String)
	 */
	public void ConsultarEstadoDTH(ActividadEJBDTO act, Long numeroPeticion, String codigoActividad) {
		// TODO Apéndice de método generado automáticamente
		servicios.ConsultarEstadoDTH(act,numeroPeticion, codigoActividad);
	}

	/**
	 * @param tr053s
	 */
	public void recepcionConsultarEstadoDTH(TR053S tr053s) {
		// TODO Apéndice de método generado automáticamente
		servicios.recepcionConsultarEstadoDTH(tr053s);
	}


}
