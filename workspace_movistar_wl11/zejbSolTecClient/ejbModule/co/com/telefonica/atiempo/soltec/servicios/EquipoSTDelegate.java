/*
 * Created on Apr 2, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.soltec.servicios;


import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal;
import co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EquipoSTDelegate implements EquipoSTLocal{
    private EquipoSTLocal servicios;
	private EquipoSTLocalHome equipoSTLocalHome;

	public EquipoSTDelegate() throws ATiempoAppEx {
		try {
			
		    equipoSTLocalHome = (EquipoSTLocalHome) HomeFactory.getHome(EquipoSTLocalHome.JNDI_NAME);
			servicios = equipoSTLocalHome.create();
			
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
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
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#enviarConfiguracionActualBA(java.lang.String, java.lang.String)
     */
    public void enviarConfiguracionActualEquipo(String peticion, String id_actividad) throws ATiempoAppEx {
       servicios.enviarConfiguracionActualEquipo(peticion,id_actividad);
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#recepcionConfiguracionActualEquipo(co.com.telefonica.atiempo.interfaces.atiempo.TR027S)
     */
    public void recepcionConfiguracionActualEquipo(TR027S tr027s) throws ATiempoAppEx {
        servicios.recepcionConfiguracionActualEquipo(tr027s);
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#esTipoPDTI(java.lang.Long)
     */
    public Collection esTipoPDTI(Long ps_id) throws ATiempoAppEx {        
        return servicios.esTipoPDTI(ps_id);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#getEquipos(java.lang.Long)
     */
    public Collection getEquiposST(Long idPeticion) throws ATiempoAppEx {
        return servicios.getEquiposST(idPeticion);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#pestanaEquipos(java.lang.Long)
     */
    public Collection pestanaEquipos(Long ps_id) throws ATiempoAppEx {
        return servicios.pestanaEquipos(ps_id);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#obtenerElementos(java.lang.Long)
     */
    public Collection obtenerElementos(Long ps_id) throws ATiempoAppEx {        // TODO Auto-generated method stub
        return servicios.obtenerElementos(ps_id);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#recuperaEquiposST(java.lang.Long)
     */
    public ArrayList recuperaEquiposST(Long nroPeticion) {        
        return servicios.recuperaEquiposST(nroPeticion);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#buscarRespuestaMensajeEquipo(java.lang.Long, java.lang.Long)
     */
    public TR026S buscarRespuestaMensajeEquipo(Long codAveCd, Long idMensaje) throws ATiempoAppEx {        
        return servicios.buscarRespuestaMensajeEquipo(codAveCd,idMensaje);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#enviaEquipoPorUtilizar(long, java.lang.String)
     */
    public Long enviaEquipoPorUtilizar(long codAveCd, String ult4Digitos,String tipoEquipo, String tipoElemento, Long idEmContratista) throws ATiempoAppEx {      
        return servicios.enviaEquipoPorUtilizar(codAveCd,ult4Digitos,tipoEquipo,tipoElemento,idEmContratista);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#loadJSP(co.com.telefonica.atiempo.interfaces.atiempo.TR026S)
     */
    public ArrayList loadJSP(TR026S tr026s) {
        return servicios.loadJSP(tr026s);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#actualizaEquipoPorUtilizar(co.com.telefonica.atiempo.interfaces.atiempo.TR026S)
     */
    public void actualizaEquipoPorUtilizar(TR026S tr026s) throws ATiempoAppEx {
        servicios.actualizaEquipoPorUtilizar(tr026s);        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#grabaEquiposST(java.lang.Long, java.lang.Long, java.util.ArrayList)
     */
    public void grabaEquiposST(Long nroPeticion, Long telAsignado, ArrayList equipos) {
        servicios.grabaEquiposST(nroPeticion,telAsignado,equipos);
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#enviaActualizaInventarioEquipo(java.lang.String, java.lang.String)
     */
    public void enviaActualizaInventarioEquipo(String peticion, String id_actividad) throws ATiempoAppEx {
       servicios.enviaActualizaInventarioEquipo(peticion,id_actividad);
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#noHayEquipoParaActualizarInventarioST(java.lang.Long)
     */
    public boolean noHayEquipoParaActualizarInventarioST(Long nroPeticion) throws ATiempoAppEx {        
        return servicios.noHayEquipoParaActualizarInventarioST(nroPeticion);
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal#recepcionConfiguracionActualEquipo(co.com.telefonica.atiempo.interfaces.atiempo.TR028S)
     */
    public void recepcionActualizacionInventarioEquipoST(TR028S tr028s) throws ATiempoAppEx {
       servicios.recepcionActualizacionInventarioEquipoST(tr028s);
        
    }
    public String elementoPeticionDePSPCIT(Long idPeticion) throws ATiempoAppEx{
    	return servicios.elementoPeticionDePSPCIT(idPeticion);
    }
    
	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaVistaGlobalST(TR025S tr025s) throws ATiempoAppEx{
		servicios.procesarRespuestaVistaGlobalST(tr025s);
	}
	
	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaNotificacionVentaMinoristasSAPST(TR020S tr020s) throws ATiempoAppEx{
		servicios.procesarRespuestaNotificacionVentaMinoristasSAPST(tr020s);
	}
	
	/*RQ.8595 - mfmendez*/
	public void enviarSolicitudVistaGlobalST(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		servicios.enviarSolicitudVistaGlobalST(act, nroPeticion, actGeneradora);
	}
	
	/*RQ.8595 - mfmendez*/
	public void enviarInformacionEquiposVentaMinoristasSAPST(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		servicios.enviarInformacionEquiposVentaMinoristasSAPST(act, nroPeticion, actGeneradora);
	}
}
