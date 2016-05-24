package co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb;

import java.util.ArrayList;
import java.util.Collection;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * Local interface for Enterprise Bean: EquipoST
 */
public interface EquipoSTLocal extends javax.ejb.EJBLocalObject {
    public void enviarConfiguracionActualEquipo(String peticion, String id_actividad) throws ATiempoAppEx;
    public void recepcionConfiguracionActualEquipo(TR027S tr027s) throws ATiempoAppEx;
    public Collection esTipoPDTI(Long ps_id) throws ATiempoAppEx ;
    Collection getEquiposST(Long idPeticion) throws ATiempoAppEx;
    public Collection pestanaEquipos(Long ps_id) throws ATiempoAppEx;
	public Collection obtenerElementos(Long ps_id) throws ATiempoAppEx;
	public ArrayList recuperaEquiposST(Long nroPeticion);
    public TR026S buscarRespuestaMensajeEquipo(Long codAveCd, Long idMensaje) throws ATiempoAppEx;
    public Long enviaEquipoPorUtilizar(long codAveCd, String ult4Digitos,String tipoEquipo, String tipoElemento,Long idEmContratista) throws ATiempoAppEx;
    public ArrayList loadJSP(TR026S tr026s);
    public void actualizaEquipoPorUtilizar(TR026S tr026s) throws ATiempoAppEx;
    public void grabaEquiposST(Long nroPeticion, Long telAsignado, ArrayList equipos);
    public void enviaActualizaInventarioEquipo(String peticion,String id_actividad) throws ATiempoAppEx ;
    public boolean noHayEquipoParaActualizarInventarioST(Long nroPeticion) throws ATiempoAppEx;
    public void recepcionActualizacionInventarioEquipoST(TR028S tr028s) throws ATiempoAppEx;
    public String elementoPeticionDePSPCIT(Long idPeticion)throws ATiempoAppEx;
    /*RQ.8595 - mfmendez*/
	public void procesarRespuestaVistaGlobalST(TR025S tr025s) throws ATiempoAppEx;	
	public void procesarRespuestaNotificacionVentaMinoristasSAPST(TR020S tr020s) throws ATiempoAppEx;
	public void enviarSolicitudVistaGlobalST(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx;	
	public void enviarInformacionEquiposVentaMinoristasSAPST(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx;
	/*FIN - RQ.8595 - mfmendez*/
}
