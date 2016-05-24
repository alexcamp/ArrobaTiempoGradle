package co.com.telefonica.atiempo.ejb.eb;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.DatosSAPDTO;
import co.com.atiempo.dto.ElementoDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020EPositionHeader;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020ERequestHeader;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025SDecoCard;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR029E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR029EPosition;
import co.com.telefonica.atiempo.interfaces.atiempo.TR029S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR053E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR053S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR611E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR611S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.ConsultarEstadoDTHMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudEquipoMQ;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.servicios.ActualizaInventarioEquipoMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfirmacionPagoReciboMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.EnviarSAPInfoEquiposMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.NotificacionSAPMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.SolicitudConfiguracionEquiposMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.Tmp_Notificacion_SAPLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Tmp_Notificacion_SAPLocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.VistaGlobalMQ;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.OperationStatusHash;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;

import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal;
import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocalHome;

/**
 * Bean implementation class for Enterprise Bean: Equipo
 */
public class EquipoBean extends
        co.com.telefonica.atiempo.utiles.SessionBeanAdapter {

    protected Logger log = LoggerFactory.getLogger(getClass());

    private DBManager dbSeq;

    private SimpleDateFormat df;

    private OperationStatusHash opStatus = null;

    private Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome;

    private Mensaje_estadoLocalHome mensaje_estadoLocalHome;

    private PeticionLocalHome peticionLocalHome;

    private Tmp_equipoLocalHome tmp_equipoLocalHome;

    private Elemento_PeticionLocalHome elemento_peticionLocalHome;

    private ConectorLocalHome conectorLocalHome;

    private Recursos_baLocalHome recursos_baLocalHome;
    
    private Producto_servicioLocalHome producto_servicioLocalHome;

    private javax.ejb.SessionContext mySessionCtx;
    
    private UsuarioLocalHome usuarioHome;
	private Catalogo_causalLocalHome catalogo_causalHome;
	private Estado_psLocalHome estado_psHome;
	private Causal_peticionLocalHome causal_peticionHome;
    private LocalidadLocalHome localidadLocalHome;
    private Estado_ps_peticionLocalHome estado_ps_peticionHome;
    private Grpe_PsLocalHome grpeLocalHome;
    private Mensaje_descarga_SAPLocalHome mensajeDescargaSAPLocalHome;

    /**
     * getSessionContext
     */
    public javax.ejb.SessionContext getSessionContext() {
        return mySessionCtx;
    }

    /**
     * setSessionContext
     */
    public void setSessionContext(SessionContext ctx) throws EJBException,
            RemoteException {
        super.setSessionContext(ctx);
        // Creacion de DataSource
        dbSeq = new DBManager();
        dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
        df = new SimpleDateFormat("yyyy/MM/dd");

        opStatus = OperationStatusHash.getInstance(); // Pablo L

        buscaHome();
    }

    private void buscaHome() {

        try {

            //	Creacion de los Home's
            recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
            mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
            mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
            peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
            tmp_equipoLocalHome = (Tmp_equipoLocalHome) HomeFactory.getHome(Tmp_equipoLocalHome.JNDI_NAME);
            elemento_peticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
            conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
            producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
            localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);

            // German P.
            grpeLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
        } catch (NamingException e) {
            log.debug(" Creacion de Local Home Nulls");
        }
    }

    private void validaHome() throws ATiempoAppEx {
        // Validacion de los Home
        if (mensaje_estadoLocalHome == null || peticionLocalHome == null
                || mensajeEstadoBaLocalHome == null)
            throw new ATiempoAppEx(ATiempoAppEx.NAMING);
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

    public TR026S buscarRespuestaMensajeEquipo(Long idPeticion, Long idMensaje)
            throws ATiempoAppEx {
        validaHome();
        try {
            if (mensajeEstadoBaLocalHome == null)
                mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
            Mensaje_estado_baLocal msgMoLocal = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(idMensaje));
            if (msgMoLocal == null)
                return null;

            PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
            Collection c = pLocal.getTmp_equipo();
            for (Iterator it = c.iterator(); it.hasNext();) {
                Tmp_equipoLocal tmpdtLocal = (Tmp_equipoLocal) it.next();
                TR026S tr026s = (TR026S) XMLUtilities.unmarshall(tmpdtLocal.getXml());
                return tr026s;
            }
        } catch (FinderException e) {
            log.error("Error al buscar Respuesta Equipo.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (NamingException e) {
            log.error("Error al buscar Respuesta Equipo.", e);
            throw new ATiempoAppEx(ATiempoAppEx.NAMING);
        }
        return null;
    }

    public Long enviaEquipoPorUtilizar(long idPeticion, String ult4Digitos)
            throws ATiempoAppEx {
        validaHome();

        try {
            Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
            Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));

            // obtiene el id de la peticion Atis

            PeticionKey key = new PeticionKey(new Long(idPeticion));

            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey(key);
            
            Collection colTmpEquipo = tmp_equipoLocalHome.findByNroPeticion(new Long(idPeticion));
            //    
            Iterator iterTmpEquipo = colTmpEquipo.iterator();
            //    
            while (iterTmpEquipo.hasNext()) {
                Tmp_equipoLocal tmp_equipoLocal = (Tmp_equipoLocal) iterTmpEquipo.next();
                try {
                    tmp_equipoLocal.remove();
                } catch (EJBException e1) {
                    log.error("Error al enviar Equipo.", e1);
                } catch (RemoveException e1) {
                    log.error("Error al enviar Equipo.", e1);
                }
            }

            LocalidadKey localidadKey = (LocalidadKey) peticion.getFk_01_localidad().getPrimaryKey();
            StringTokenizer st = new StringTokenizer(ult4Digitos, ",");
            String ps = st.nextToken();
            ps = (ps.substring(ps.indexOf("-") + 1, ps.length())).trim();
            Long idPS = new Long(ps);
            Producto_servicioLocalHome psHome = null;
            try {
                psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
            } catch (NamingException e) {
                log.error("NamingException. ProductoServicioLocalHome", e);
            }
            Producto_servicioLocal psLocal = null;
            try {
                psLocal = psHome.findByPrimaryKey(new Producto_servicioKey(idPS));
            } catch (FinderException e) {
                log.error("FinderException. PS No encontrado. [" + idPS + "]");
            } catch (Exception e) {
                log.error("Exception. PS No encontrado. [" + idPS + "]:" + e.getMessage());
            }

            DepartamentoKey departamentoKey = (DepartamentoKey) peticion.getFk_02_departamento().getPrimaryKey();
            Long id_grupo = new Long(0);
            try {

                Grpe_PsLocalHome grpeLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
                Grpe_PsLocal grupo = grpeLocalHome.findGrupoByPS(idPS);
                id_grupo = grupo.getGrpe_id();
            } catch (NamingException e1) {
                e1.printStackTrace();
            }

            Collection equipos = new ArrayList();

            Collection eqLista = esTipoPDTI(new Long(ps));

            
            Tipo_Eq_ElementoLocal tipo_eqLocal = null;
            if (eqLista != null) {
                for (Iterator it = eqLista.iterator(); it.hasNext() && st.hasMoreTokens();) {
                    tipo_eqLocal = (Tipo_Eq_ElementoLocal) it.next();
                    TR026EEquipment eq = new TR026EEquipment();
                    eq.setElementType(String.valueOf(tipo_eqLocal.getId_elemento()));
                    eq.setEquipmentType(String.valueOf(tipo_eqLocal.getId_tipo_eq()));
                    eq.setElementSerial(st.nextToken());
                    equipos.add(eq);
                }
            }

            TR026E tr026e = new TR026E();
            tr026e.setInventoryType(id_grupo.toString());
            tr026e.setEquipments(equipos);
            tr026e.setContractorId(psLocal.getEmpr_id().longValue());
            tr026e.setDepartment(departamentoKey.cod_dpt);
            tr026e.setLocation(localidadKey.cod_loc);
            tr026e.setProductServiceCode(idPS.longValue());
            tr026e.setId(String.valueOf(idCorrelativoMensaje));            
            tr026e.setAtiempoRequestNumber(key.peti_numero.longValue());
            Peticion_atisKey petAtisK = (Peticion_atisKey)peticion.getFk_01_pet_atis().getPrimaryKey();
    		tr026e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());

            Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
            msgLocal.setPeticion(peticion);
            msgLocal.setFecha_envio(df.format(new java.util.Date()));
            msgLocal.setMensaje_estado(mensajeOk);

            new SolicitudEquipoMQ().enviarMensaje(tr026e);

            return (idCorrelativoMensaje);

        } catch (NumberFormatException e) {
            log.error("Error al enviar Equipo.", e);
            throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
        } catch (FinderException e) {
            log.error("Error al enviar Equipo.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (CreateException cex) {
            log.error("error creando registro", cex);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
        }
    }

    
    
    //UMTS Se copia el metodo "enviaEquipoPorUtilizar" agregando un nuevo parametro de entrada 
    public Long enviaEquipoPorUtilizar(long nroPet, String ult4Digitos,String tipoEquipo, String tipoElemento)
    throws ATiempoAppEx {
        validaHome();
		
		try
		{	log.debug("ult4Digitos :.."+ult4Digitos);
			log.debug("tipoEquipo :.."+tipoEquipo);
			log.debug("tipoElemento :.."+tipoElemento);
			
			//if(tmp_modemHome==null)
				//tmp_modemHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
			if(mensaje_estadoLocalHome==null)
				mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estadoLocal mensajeOk=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
	
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));

			PeticionKey key = new PeticionKey (new Long (nroPet)) ;

			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            Collection colTmpEquipo = tmp_equipoLocalHome.findByNroPeticion(new Long(nroPet));
      
            Iterator iterTmpEquipo = colTmpEquipo.iterator();
        
            while (iterTmpEquipo.hasNext()) {
                Tmp_equipoLocal tmp_equipoLocal = (Tmp_equipoLocal) iterTmpEquipo.next();
                try {
                    tmp_equipoLocal.remove();
                } catch (EJBException e1) {
                    log.error("Error al enviar Equipo.", e1);
                } catch (RemoveException e1) {
                    log.error("Error al enviar Equipo.", e1);
                }
    }
   
    Producto_servicioLocalHome psHome = null;
    try {
        psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
    } catch (NamingException e) {
        log.error("NamingException. ProductoServicioLocalHome", e);
    }
	//	 CR25996 UMTS - agonz - 20/07/2009
    
    Long idPS = new Long(0);
    PeticionesDelegate delegate = new PeticionesDelegate();
    Producto_servicio_peticionLocal producto_servicio_peticionLocal = null;
    Producto_servicioKey productoServicioKey = null;
    boolean esUmts = delegate.esGrupoUmts(new Long(nroPet));

	for(Iterator iterator= peticion.getProducto_servicio_peticion().iterator();iterator.hasNext();)
	{
	    producto_servicio_peticionLocal =(Producto_servicio_peticionLocal) iterator.next();
	    productoServicioKey = (Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
	    
		Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
		Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
		Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
		int idFamilia = familia_producto_servicioKey.faps_id.intValue();
		if((esUmts) && (idFamilia == ComunInterfaces.familiaPcBA || (idFamilia == ComunInterfaces.familiaPcLinea || idFamilia == ComunInterfaces.familiaIP))){
			idPS  = productoServicioKey.ps_id;
			break;
		}
			
	}
	log.debug("idPS :.."+idPS);
	//	 FIN CR25996 UMTS - agonz - 20/07/2009    
    Producto_servicioLocal psLocal = null;
    try {
        psLocal = psHome.findByPrimaryKey(new Producto_servicioKey(idPS));
    } catch (FinderException e) {
        log.error("FinderException. PS No encontrado. [" + idPS + "]");
    } catch (Exception e) {
        log.error("Exception. PS No encontrado. [" + idPS + "]:" + e.getMessage());
    }
    
    //DepartamentoKey departamentoKey = (DepartamentoKey) departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticion.getCod_dpt())).getPrimaryKey();
    DepartamentoKey departamentoKey = (DepartamentoKey) peticion.getFk_02_departamento().getPrimaryKey();
    
    Long id_grupo = new Long(0);        
    try {
        Grpe_PsLocalHome grpeLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
        Grpe_PsLocal grupo = grpeLocalHome.findGrupoByPS(idPS);
        id_grupo = grupo.getGrpe_id();
        
        
    } catch (NamingException e1) {
        e1.printStackTrace();
    }

    Collection equipos = new ArrayList();

    
    
    TR026EEquipment eq = new TR026EEquipment();
    eq.setElementType(tipoElemento);
    eq.setEquipmentType(tipoEquipo);
    eq.setElementSerial(ult4Digitos);
    equipos.add(eq);
        
    //LocalidadKey localidadKey= (LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticion.getCod_loc_cd())).getPrimaryKey();
    LocalidadKey localidadKey = (LocalidadKey) peticion.getFk_01_localidad().getPrimaryKey();
    
    TR026E tr026e = new TR026E();
    tr026e.setAtisRequestNumber(key.peti_numero.longValue());
    tr026e.setAtiempoRequestNumber(key.peti_numero.longValue());
    tr026e.setInventoryType(id_grupo.toString());
    tr026e.setEquipments(equipos);
    tr026e.setContractorId(psLocal.getEmpr_id().longValue());
    tr026e.setDepartment(departamentoKey.cod_dpt);
    tr026e.setLocation(localidadKey.cod_loc);
    tr026e.setProductServiceCode(idPS.longValue());
    tr026e.setId(String.valueOf(idCorrelativoMensaje));
  //  tr026e.setAtisRequestNumber(idPeticion);

    Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
    msgLocal.setPeticion(peticion);
    msgLocal.setFecha_envio(df.format(new java.util.Date()));
    msgLocal.setMensaje_estado(mensajeOk);

    new SolicitudEquipoMQ().enviarMensaje(tr026e);

   
	    return (idCorrelativoMensaje);
	} catch (NumberFormatException e) {
	    log.error("Error al enviar Equipo.", e);
	    throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
	} catch (FinderException e) {
	    log.error("Error al enviar Equipo.", e);
	    throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	} catch (NamingException e) {
		log.error("Error Envio Equipo.",e);
		throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
	} catch (CreateException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
}
    

        
    // German P. Se copia funcion anterior agregando atributo de idEmContratista
    public Long enviaEquipoPorUtilizar(long idPeticion, String ult4Digitos, Long idEmContratista) throws ATiempoAppEx {
		validaHome();
		
		try {
		    Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
		    Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		
		    // obtiene el id de la peticion Atis
		
		    PeticionKey key = new PeticionKey(new Long(idPeticion));
		
		    PeticionLocal peticion = peticionLocalHome.findByPrimaryKey(key);
		    
		    Collection colTmpEquipo = tmp_equipoLocalHome.findByNroPeticion(new Long(idPeticion));
		    //    
		    Iterator iterTmpEquipo = colTmpEquipo.iterator();
		    //    
		    while (iterTmpEquipo.hasNext()) {
		        Tmp_equipoLocal tmp_equipoLocal = (Tmp_equipoLocal) iterTmpEquipo.next();
		        try {
		            tmp_equipoLocal.remove();
		        } catch (EJBException e1) {
		            log.error("Error al enviar Equipo.", e1);
		        } catch (RemoveException e1) {
		            log.error("Error al enviar Equipo.", e1);
		        }
		    }
		
		    LocalidadKey localidadKey = (LocalidadKey) peticion.getFk_01_localidad().getPrimaryKey();
		    StringTokenizer st = new StringTokenizer(ult4Digitos, ",");
		    String ps = st.nextToken();
		    ps = (ps.substring(ps.indexOf("-") + 1, ps.length())).trim();
		    Long idPS = new Long(ps);
		    Producto_servicioLocalHome psHome = null;
		    try {
		        psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		    } catch (NamingException e) {
		        log.error("NamingException. ProductoServicioLocalHome", e);
		    }
		    		
		    DepartamentoKey departamentoKey = (DepartamentoKey) peticion.getFk_02_departamento().getPrimaryKey();
		    Long id_grupo = new Long(0);
		    try {
		
		        Grpe_PsLocalHome grpeLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
		        Grpe_PsLocal grupo = grpeLocalHome.findGrupoByPS(idPS);
		        id_grupo = grupo.getGrpe_id();
		    } catch (NamingException e1) {
		        e1.printStackTrace();
		    }
		
		    Collection equipos = new ArrayList();
		
		    Collection eqLista = esTipoPDTI(new Long(ps));
		
		    
		    Tipo_Eq_ElementoLocal tipo_eqLocal = null;
		    if (eqLista != null) {
		        for (Iterator it = eqLista.iterator(); it.hasNext() && st.hasMoreTokens();) {
		            tipo_eqLocal = (Tipo_Eq_ElementoLocal) it.next();
		            TR026EEquipment eq = new TR026EEquipment();
		            eq.setElementType(String.valueOf(tipo_eqLocal.getId_elemento()));
		            eq.setEquipmentType(String.valueOf(tipo_eqLocal.getId_tipo_eq()));
		            eq.setElementSerial(st.nextToken());
		            equipos.add(eq);
		        }
		    }
		
		    TR026E tr026e = new TR026E();
		    tr026e.setInventoryType(id_grupo.toString());
		    tr026e.setEquipments(equipos);
		    tr026e.setContractorId(idEmContratista.longValue());
		    tr026e.setDepartment(departamentoKey.cod_dpt);
		    tr026e.setLocation(localidadKey.cod_loc);
		    tr026e.setProductServiceCode(idPS.longValue());
		    tr026e.setId(String.valueOf(idCorrelativoMensaje));            
		    tr026e.setAtiempoRequestNumber(key.peti_numero.longValue());
		    Peticion_atisKey petAtisK = (Peticion_atisKey)peticion.getFk_01_pet_atis().getPrimaryKey();
			tr026e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());
		
		    Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
		    msgLocal.setPeticion(peticion);
		    msgLocal.setFecha_envio(df.format(new java.util.Date()));
		    msgLocal.setMensaje_estado(mensajeOk);
		
		    new SolicitudEquipoMQ().enviarMensaje(tr026e);
		
		    return (idCorrelativoMensaje);
		
		} catch (NumberFormatException e) {
		    log.error("Error al enviar Modem.", e);
		    throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
		    log.error("Error al enviar Modem.", e);
		    throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException cex) {
		    log.error("error creando registro", cex);
		    throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		}
		}

    
    public void actualizaEquipoPorUtilizar(TR026S tr026s) throws ATiempoAppEx {

        // CR15338 - @Trace - ana santos - 04/08 - Inicio
        BackendExecution bExecution = null;

        try {
            bExecution = BackendTraceUtil.initExecution(tr026s, log);
            bExecution.setNumPetAtiempo(new Long(tr026s.getAtisRequestNumber()));
            bExecution.setIdMensajeOp(tr026s.getId());
            bExecution.startOperation();

            validaHome();
            // busca el registro del mensaje
            log.debug("Primero si es mensaje de error lo obviamos.");
            if (tr026s.getErrorCode() != 0) {
                log.debug("Respuesta con error:"+ XMLUtilities.marshall(tr026s));
                //Correccion AT-2525 10/09/2009
                //return;
            }

            Mensaje_estado_baLocal mensaje_estado_ba;
            try {
                mensaje_estado_ba = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr026s.getId())));
            } catch (FinderException e1) {
                mensaje_estado_ba = null;
            }

            if (mensaje_estado_ba == null) {
                log.debug("No Existe Respuesta en la base de enviados\n "
                        + XMLUtilities.marshall(tr026s));
                throw new ATiempoAppEx(
                        "No Existe Respuesta en la base de enviados:"
                                + tr026s.getId(), ATiempoAppEx.EXCEPTION);
            }

            // Validacion del estado de la respuesta

            Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensaje_estado_ba.getMensaje_estado().getPrimaryKey();

            if (mensaje_estadoKey.cod_estado.intValue() != ComunInterfaces.estadoOk) {
                log.debug("Es estado de la respuesta es diferente para ser procesado\n " + XMLUtilities.marshall(tr026s));
                return;
            }

            // todo ok, se graba la respuesta

            Long idTmpEquipo = new Long(dbSeq.seqNextValLong("CORRELATIVO_TMP_MODEM"));
            if (tmp_equipoLocalHome == null)
                tmp_equipoLocalHome = (Tmp_equipoLocalHome) HomeFactory.getHome(Tmp_equipoLocalHome.JNDI_NAME);

            Tmp_equipoLocal tmpequipoLocal = tmp_equipoLocalHome.create(idTmpEquipo, mensaje_estado_ba.getPeticion(), XMLUtilities.marshall(tr026s));

        } catch (NumberFormatException e) {
            bExecution.setErrorOp(true);
            throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
        } catch (CreateException e) {
            bExecution.setErrorOp(true);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
        } catch (NamingException e) {
            bExecution.setErrorOp(true);
            throw new ATiempoAppEx(ATiempoAppEx.NAMING);
        } finally {
            bExecution.endAll();
            // CR15338 - @Trace - ana santos - 04/08 - FIN
        }

    }

    /**
     * Verifica si para el ps parametro el mismo pertenece a la relacion
     * Ps_Tipo_Eq en caso de pertenecer retorno una lista con los equipos de
     * dicho ps
     */
    public Collection esTipoPDTI(Long ps_id) throws ATiempoAppEx {
        Collection retorno = new ArrayList();
        Ps_Tipo_EqLocal ps_tipo_eqLocal = null;
        Ps_Tipo_EqLocalHome psTipoEqLocalHome = null;
        Tipo_Eq_ElementoLocalHome tipo_eqHome = null;
        Tipo_Eq_ElementoLocal tipo_eqLocal = null;
        ElementoLocalHome elementoHome = null;
        try {
            psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
            elementoHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
            tipo_eqHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
        } catch (NamingException e2) {
        	throw new ATiempoAppEx ("",e2);
        }

        try {

            ps_tipo_eqLocal  = psTipoEqLocalHome.findTipoByPs(ps_id.longValue());
            long idTipoEq = ps_tipo_eqLocal.getId_tipo_eq().longValue();
            Collection elementos = tipo_eqHome.findElementoByTipo(idTipoEq);
            return elementos;
        } catch (FinderException e1) {
            log.debug("No es un ps de PDTI");
        }
        return retorno;
    }

    /**
     * Retorna una lista de equipos para un ps
     */
    public Collection obtenerElementos(Long ps_id) throws ATiempoAppEx {
        Collection retorno = new ArrayList();
        Collection elementos = esTipoPDTI(ps_id);
        ElementoLocalHome elementoHome = null;
        try {
            elementoHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
        } catch (NamingException e2) {

        }
        Tipo_Eq_ElementoLocal tipo_eqLocal = null;
        if (elementos != null) {
            for (Iterator it = elementos.iterator(); it.hasNext();) {
                tipo_eqLocal = (Tipo_Eq_ElementoLocal) it.next();
                long idElemento = tipo_eqLocal.getId_elemento().longValue();
                try {
                    ElementoLocal e = (ElementoLocal) elementoHome.findElemento(idElemento);
                    retorno.add(e);
                } catch (FinderException e) {
                    e.printStackTrace();
                }
            }
        }
        return retorno;
    }

    /**
     * Retorna una lista con la descripci�n de los equipos para un ps
     */
    public Collection pestanaEquipos(Long ps_id) throws ATiempoAppEx {
        Collection retorno = new ArrayList();
        Collection aux = obtenerElementos(ps_id);
        if (aux != null) {
            ElementoLocal e = null;
            for (Iterator i = aux.iterator(); i.hasNext();) {
                e = (ElementoLocal) i.next();
                retorno.add(e.getDesc_elemento());
            }
        }

        return retorno;
    }
    

    
    public ArrayList recuperaEquiposVPi(Long nroPeticion){
//			log.debug("Voy a recuperar los Equipos para la peticion:"+nroPeticion);
			boolean esAlta=false;
			boolean esTraslado=false;
			boolean esCambioPlan = false;
			ArrayList retorno=new ArrayList();
			ArrayList list=new ArrayList();
			ElementoLocalHome eHome = null;
			 try {
			if(peticionLocalHome==null)               
                    peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			eHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			Collection collection=peticionLocal.getElemento_peticion();
			for(Iterator iterator=collection.iterator();iterator.hasNext();)
			{
				//log.debug("Sacando modem");
				Elemento_PeticionLocal elementoLocal=(Elemento_PeticionLocal) iterator.next();
				Elemento_PeticionKey elementoKey=(Elemento_PeticionKey) elementoLocal.getPrimaryKey();
				ElementoDTO equipoDTO=new ElementoDTO();
				//equipoDTO.setPeti_numero(nroPeticion);
				//equipoDTO.setTelefono(elementoLocal.getTelefono());
				equipoDTO.setPs(elementoLocal.getPs_id());
				Producto_servicioLocal psLocal = (Producto_servicioLocal)producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(elementoLocal.getPs_id()));
				equipoDTO.setDesPs(psLocal.getPs_nombre());
				equipoDTO.setSerial(elementoKey.serial.toString());				
				equipoDTO.setAccion(elementoLocal.getAccion().shortValue());
				log.debug("equipoDTO.setAccion = "+elementoLocal.getAccion().shortValue());
				equipoDTO.setTipo_equipo(elementoLocal.getTipo_equipo());
				ElementoLocal elocal = eHome.findElemento(elementoLocal.getTipo_elemento().longValue());
				//ElementoLocal elocal = eHome.findElemento(Long.parseLong(elementoLocal.getTipo_equipo()));
				equipoDTO.setDesElemento(elocal.getDesc_elemento());
				equipoDTO.setTipo_elemento(elementoLocal.getTipo_elemento());
				
				list.add(equipoDTO);
			}
			 } catch (FinderException e) {
                
                 e.printStackTrace();
             } catch (NamingException e) {
                
                 e.printStackTrace();
             }
             Collections.sort(list,new ElementoDTO());
			 return list;
	}
    

    public ArrayList recuperaEquiposVPiUmts(Long nroPeticion)
	{
		
			log.debug("Voy a recuperar los Equipos para la peticion:"+nroPeticion);
			boolean esAlta=false;
			boolean esTraslado=false;
			boolean esCambioPlan = false;
			ArrayList list=new ArrayList();
			ElementoLocalHome eHome = null;
			 try {
			if(peticionLocalHome==null)               
                    peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			eHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			Grpe_PsLocal grpeLocal = null;
			Collection collection=peticionLocal.getElemento_peticion();
			log.debug("collection:.."+collection);	
			boolean soloBA=peticionLocal.getPeti_id_instancia().equalsIgnoreCase(ComunInterfaces.BA);
			for(Iterator iterator=collection.iterator();iterator.hasNext();)
			{
				//log.debug("Sacando modem");
				Elemento_PeticionLocal elementoLocal=(Elemento_PeticionLocal) iterator.next();
				Elemento_PeticionKey elementoKey=(Elemento_PeticionKey) elementoLocal.getPrimaryKey();
				Long ps = elementoLocal.getPs_id();
				// German P.
				try{
				    log.debug("ps = " + ps);
					grpeLocal = grpeLocalHome.findGrupoByPS(ps);
					if (grpeLocal.getGrpe_id() != null && grpeLocal.getGrpe_id().longValue() == ComunInterfaces.ID_GRUPO_UMTS){
						
						Producto_servicioLocal psLocal = (Producto_servicioLocal)producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(elementoLocal.getPs_id()));
						Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) psLocal.getFamilia_producto_servicio().getPrimaryKey();
						//
						log.debug("familia PS == "+familia_producto_servicioKey.faps_id.intValue()+" .... ComunInterfaces.familiaPcLinea "+ComunInterfaces.familiaPcLinea);
						log.debug("soloBA = "+soloBA);	
						
						if ( (familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcLinea || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaIP) || soloBA)
						{
							
						    ElementoDTO equipoDTO=new ElementoDTO();
							//equipoDTO.setPeti_numero(nroPeticion);
							//equipoDTO.setTelefono(elementoLocal.getTelefono());
							equipoDTO.setPs(ps);
						    equipoDTO.setDesPs(psLocal.getPs_nombre());
							equipoDTO.setSerial(elementoKey.serial.toString());				
							equipoDTO.setAccion(elementoLocal.getAccion().shortValue());
							log.debug("equipoDTO.setAccion = "+elementoLocal.getAccion().shortValue());
							equipoDTO.setTipo_equipo(elementoLocal.getTipo_equipo());
							//ElementoLocal elocal = eHome.findElemento(elementoLocal.getTipo_elemento().longValue());
							ElementoLocal elocal = eHome.findElemento(elementoLocal.getTipo_elemento().longValue());
							equipoDTO.setDesElemento(elocal.getDesc_elemento());
							equipoDTO.setTipo_elemento(elementoLocal.getTipo_elemento());
						
							list.add(equipoDTO);
						}
					}else{
					 		log.debug("No se agrega equipoDTO.setAccion = "+elementoLocal.getAccion().shortValue());
					 		if (grpeLocal.getGrpe_id() != null){
					 		    log.debug("grupo = "+grpeLocal.getGrpe_id().longValue());    
							}
					}
				} catch (FinderException e) {
				    log.debug("No se encontro grupo para el ps = "+ps);
	            }
			}
			 } catch (FinderException e) {
                
                 e.printStackTrace();
             } catch (NamingException e) {
                
                 e.printStackTrace();
             }
             if(list!=null && list.size()>0)             
             Collections.sort(list,new ElementoDTO());
             
			 return list;
	}
    
    public ArrayList recuperaEquiposVPiPdti(Long nroPeticion)
	{
		
			log.debug("Voy a recuperar los Equipos para la peticion de pdti:"+nroPeticion);
			boolean esAlta=false;
			boolean esTraslado=false;
			boolean esCambioPlan = false;
			ArrayList list=new ArrayList();
			ElementoLocalHome eHome = null;
			 try {
			if(peticionLocalHome==null)               
                    peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			eHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			Grpe_PsLocal grpeLocal = null;
			Collection collection=peticionLocal.getElemento_peticion();
			log.debug("collection:.."+collection);	
			boolean soloBA=peticionLocal.getPeti_id_instancia().equalsIgnoreCase(ComunInterfaces.BA);
			for(Iterator iterator=collection.iterator();iterator.hasNext();)
			{
				//log.debug("Sacando modem");
				Elemento_PeticionLocal elementoLocal=(Elemento_PeticionLocal) iterator.next();
				Elemento_PeticionKey elementoKey=(Elemento_PeticionKey) elementoLocal.getPrimaryKey();
				Long ps = elementoLocal.getPs_id();
				// German P.
				try{
				    log.debug("ps = " + ps);
					grpeLocal = grpeLocalHome.findGrupoByPS(ps);
					if (grpeLocal.getGrpe_id() != null && grpeLocal.getGrpe_id().longValue() == ComunInterfaces.ID_GRUPO_PDTI){
						
						Producto_servicioLocal psLocal = (Producto_servicioLocal)producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(elementoLocal.getPs_id()));
						Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) psLocal.getFamilia_producto_servicio().getPrimaryKey();
						//
						log.debug("familia PS == "+familia_producto_servicioKey.faps_id.intValue()+" .... ComunInterfaces.familiaPcLinea "+ComunInterfaces.familiaPcLinea);
						log.debug("soloBA = "+soloBA);	
						
						if ( (familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcLinea || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaIP) || soloBA){
							
						    ElementoDTO equipoDTO=new ElementoDTO();
							//equipoDTO.setPeti_numero(nroPeticion);
							//equipoDTO.setTelefono(elementoLocal.getTelefono());
							equipoDTO.setPs(ps);
						    equipoDTO.setDesPs(psLocal.getPs_nombre());
							equipoDTO.setSerial(elementoKey.serial.toString());				
							equipoDTO.setAccion(elementoLocal.getAccion().shortValue());
							log.debug("equipoDTO.setAccion = "+elementoLocal.getAccion().shortValue());
							equipoDTO.setTipo_equipo(elementoLocal.getTipo_equipo());
							ElementoLocal elocal = eHome.findElemento(elementoLocal.getTipo_elemento().longValue());
							//ElementoLocal elocal = eHome.findElemento(Long.parseLong(elementoLocal.getTipo_equipo()));
							equipoDTO.setDesElemento(elocal.getDesc_elemento());
							equipoDTO.setTipo_elemento(elementoLocal.getTipo_elemento());
						
							list.add(equipoDTO);
						}
					}else{
					 		log.debug("No se agrega equipoDTO.setAccion = "+elementoLocal.getAccion().shortValue());
					 		if (grpeLocal.getGrpe_id() != null){
					 		   log.debug("grupo = "+grpeLocal.getGrpe_id().longValue());    
							}
					}
				} catch (FinderException e) {
				    log.debug("No se encontro grupo para el ps = "+ps);
	            }
			}
			 } catch (FinderException e) {
                
                 e.printStackTrace();
             } catch (NamingException e) {
                
                 e.printStackTrace();
             }
             Collections.sort(list,new ElementoDTO());
			 return list;
	}
    
    
    public Collection getEquipos(Long idPeticion) throws ATiempoAppEx {
        Collection labels = new ArrayList();
        EquipoLocal elocal = null;
        PeticionLocalHome pLocalHome = null;
        Producto_servicio_peticionLocal producto_servicio_peticionLocal = null;
        Producto_servicioKey productoServicioKey = null;
        try {
            elocal = ((EquipoLocalHome) HomeFactory.getHome(EquipoLocalHome.JNDI_NAME)).create();
            pLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
        } catch (CreateException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        PeticionKey pkey = new PeticionKey(idPeticion);
        PeticionLocal plocal = null;
        try {
            plocal = pLocalHome.findByPrimaryKey(pkey);
            // Obtengo los ps de la peticion e itero sobre la lista devuelta
            Collection psList = plocal.getProducto_servicio_peticion();
            Grpe_PsLocal grpeLocal = null;
            Producto_servicioLocal pslocal = null;
            boolean tieneQuiebre = false;
            
            for (Iterator iter = psList.iterator(); iter.hasNext();) {
                producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iter.next();
                productoServicioKey = (Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
                
                tieneQuiebre = false;
                
                Collection estPSPeticionList = producto_servicio_peticionLocal.getEstado_ps_peticion();
                for (Iterator estPSPeticionIterator = estPSPeticionList.iterator();estPSPeticionIterator.hasNext();){
                	Estado_ps_peticionLocal estadoPSPeticionLocal = (Estado_ps_peticionLocal)estPSPeticionIterator.next();
                	
                	if (estadoPSPeticionLocal.getCod_estado_cierre().intValue() != ComunInterfaces.estadoCierreOk){
                		tieneQuiebre = true;
                	}
                }
                
                Long ps = productoServicioKey.ps_id;
                Collection retorno = elocal.pestanaEquipos(ps);			
                if(retorno.size()>0){
					
                    boolean soloBA = plocal.getPeti_id_instancia().equalsIgnoreCase(ComunInterfaces.BA);
                    Producto_servicioLocal psLocal = (Producto_servicioLocal)producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(ps));
					Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) psLocal.getFamilia_producto_servicio().getPrimaryKey();
										
					grpeLocal = grpeLocalHome.findGrupoByPS(ps);
					if (grpeLocal.getGrpe_id() != null 
							&& (grpeLocal.getGrpe_id().longValue() != ComunInterfaces.ID_GRUPO_PDTI 
									&& grpeLocal.getGrpe_id().longValue() != ComunInterfaces.ID_GRUPO_VentaEquipos
									&& grpeLocal.getGrpe_id().longValue() != ComunInterfaces.ID_GRP_INTERNET_TOTAL)){
						
	                    if (familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcLinea || soloBA){
	                    	 if (!tieneQuiebre){
				                labels.add("PS");// Valor necesario para utilizarlo en la
				                 pslocal = producto_servicio_peticionLocal.getProducto_servicio();
				                labels.add(ps);log.debug(" getEquipos se carga en labels PS :. "+ ps);
				                labels.add(pslocal.getPs_nombre());
				                // creaci�n de la pantalla din�mica
				                // ingresoEquipos.jsp
		                	                
				                labels.addAll(retorno);
	                    	 }
	                    }
					}else{
					    if (!tieneQuiebre){
					    	labels.add("PS");// Valor necesario para utilizarlo en la
			                 pslocal = producto_servicio_peticionLocal.getProducto_servicio();
			                labels.add(ps);log.debug(" getEquipos se carga en labels PS :. "+ ps);
			                labels.add(pslocal.getPs_nombre());
			                // creaci�n de la pantalla din�mica
			                // ingresoEquipos.jsp
	                
	                
			                labels.addAll(retorno);
					    }
					}
                }
            }
        } catch (FinderException e1) {
            e1.printStackTrace();
        }

        return labels;
    }
    
    public Collection getEquiposGrupo(Long idPeticion, int ID_GRUPO) throws ATiempoAppEx {
        Collection labels = new ArrayList();
        EquipoLocal elocal = null;
        PeticionLocalHome pLocalHome = null;
        Producto_servicio_peticionLocal producto_servicio_peticionLocal = null;
        Producto_servicioKey productoServicioKey = null;
        try {
            elocal = ((EquipoLocalHome) HomeFactory.getHome(EquipoLocalHome.JNDI_NAME)).create();
            pLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
        } catch (CreateException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        PeticionKey pkey = new PeticionKey(idPeticion);
        PeticionLocal plocal = null;
        try {
            plocal = pLocalHome.findByPrimaryKey(pkey);
            Grpe_PsLocal grpeLocal = null;
            // Obtengo los ps de la peticion e itero sobre la lista devuelta
            Collection psList = plocal.getProducto_servicio_peticion();            
            for (Iterator iter = psList.iterator(); iter.hasNext();) {
                producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iter.next();
                productoServicioKey = (Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
                Long ps = productoServicioKey.ps_id;log.debug(" getEquipos se carga en labels PS :. "+ ps);
                Collection retorno = elocal.pestanaEquipos(ps);    
                if(retorno.size()>0){
					
                    boolean soloBA = plocal.getPeti_id_instancia().equalsIgnoreCase(ComunInterfaces.BA);
                    Producto_servicioLocal psLocal = (Producto_servicioLocal)producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(ps));
					Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) psLocal.getFamilia_producto_servicio().getPrimaryKey();
					grpeLocal = grpeLocalHome.findGrupoByPS(ps);
					if (grpeLocal.getGrpe_id() != null && grpeLocal.getGrpe_id().longValue() == ID_GRUPO){
	                    if ( (familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcLinea || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaIP) || soloBA){
			                labels.add("PS");// Valor necesario para utilizarlo en la
			                Producto_servicioLocal pslocal = producto_servicio_peticionLocal.getProducto_servicio();
			                labels.add(ps);
			                labels.add(pslocal.getPs_nombre());
			                // creaci�n de la pantalla din�mica
			                // ingresoEquipos.jsp
	                
	                
			                labels.addAll(retorno);
	                    }
					}
	                    //no pertenece al grupo
                
                }
            }
        } catch (FinderException e1) {
            e1.printStackTrace();
        }

        return labels;
    }
    

    public void grabaEquiposVpi(Long nroPeticion, Long telAsignado, ArrayList equipos) {
        try {
            if (peticionLocalHome == null)
                peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
            if (elemento_peticionLocalHome == null)
                elemento_peticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
            PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
            Object [] lista = peticionLocal.getElemento_peticion().toArray();
          //  for (Iterator iterator = peticionLocal.getElemento_peticion().iterator(); iterator.hasNext();) {
//          Elemento_PeticionLocal elemento_peticionLocal = (Elemento_PeticionLocal) iterator.next();
            for (int i = 0; i< lista.length;i++) {
                Elemento_PeticionLocal elemento_peticionLocal = (Elemento_PeticionLocal) lista[i];
                elemento_peticionLocal.remove();
            }            
            for (Iterator iterator = equipos.iterator(); iterator.hasNext();) {            
                ElementoDTO elementoVpiDTO = (ElementoDTO) iterator.next();
                
                if (elementoVpiDTO.getSerial() != null && elementoVpiDTO.getSerial().length()>0
                		&& elementoVpiDTO.getTipo_equipo() != null && elementoVpiDTO.getTipo_equipo().length()>0
						&& elementoVpiDTO.getTipo_elemento() != null 
						&& elementoVpiDTO.getTipo_inventario() != null && elementoVpiDTO.getTipo_inventario().length()>0 ){
                	Elemento_PeticionLocal elemento_peticionLocal = elemento_peticionLocalHome.create(elementoVpiDTO.getSerial(), peticionLocal,telAsignado,
                			new Short(elementoVpiDTO.getAccion()),elementoVpiDTO.getTipo_equipo(), elementoVpiDTO.getTipo_inventario(), 
							elementoVpiDTO.getTipo_elemento(),elementoVpiDTO.getPs()); 
                	/*fmendez - rq6895 - interfaz SAP*/
                	elemento_peticionLocal.setNum_celular(elementoVpiDTO.getNumCelular());
                	elemento_peticionLocal.setNum_material_sap(elementoVpiDTO.getMaterialSAP());
                	elemento_peticionLocal.setCentro_sap(elementoVpiDTO.getCentroSAP());
                	elemento_peticionLocal.setAlmacen_sap(elementoVpiDTO.getAlmacenSAP());
                	elemento_peticionLocal.setNum_doc_sap(elementoVpiDTO.getNumDocComprasSAP());
                	elemento_peticionLocal.setPos_doc_sap(elementoVpiDTO.getNumPosDocComprasSAP());
				 	/*Fin fmendez - rq6895 - interfaz SAP*/
                	/*fmendez - rq5606 - Internet Movil*/
                	elemento_peticionLocal.setMarca(elementoVpiDTO.getMarca());
                	elemento_peticionLocal.setModelo(elementoVpiDTO.getModelo());                	
                	/*Fin fmendez - rq5606 - Internet Movil*/
                }else{
                	log.debug("El elemento no se guardo porque tiene datos incompletos: Serial:"+elementoVpiDTO.getSerial()+" tipo equipo: "+elementoVpiDTO.getTipo_equipo()
                			+" tipo elemento: "+elementoVpiDTO.getTipo_elemento() + " tipo inventario: "+elementoVpiDTO.getTipo_inventario());
                }
            }
        } catch (Exception e) {
            log.debug("Exception", e);
            e.printStackTrace();
        }
    }

    public boolean noHayEquipoParaActualizarInventarioBa(Long nroPeticion)
            throws ATiempoAppEx {
        try {
        	boolean hayCamaras = false;
        	boolean hayEquipos = false;
        	
        	CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
        	Collection camaras = camaraLocalHome.findByPeticion(nroPeticion);
        	if(camaras!=null && camaras.size()>0){
        		hayCamaras = true;
        	}

        	PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
            Collection collection = peticionLocal.getElemento_peticion();
            if(collection!=null && collection.size()>0){
	            for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
	                Elemento_PeticionLocal elementoLocal = (Elemento_PeticionLocal) iterator.next();
	                if (elementoLocal.getAccion().shortValue() != 0){
	                    hayEquipos=true;
	                    break;
	                }
	            }
            }
            return !(hayEquipos || hayCamaras);
        } catch (FinderException finderException) {
            throw new ATiempoAppEx(ATiempoAppEx.FINDER, finderException);
        } catch (Exception e) {
            log.debug("Exception", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
        }
    }

    public void enviaActualizaInventarioEquipo(String peticion,String id_actividad) throws ATiempoAppEx {

        try {
        	boolean esVentaEquipos = false;
        	boolean noAsignoTelefono = false;
        	
            validaHome();
            Mensaje_estadoLocal mesajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));

            PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
            Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
            log.debug("Sacamos la Peticion para actualizar Intentario BA");
            
            // Proceso para la validacion de los tipos de ps, que estan en el mensaje
            StringBuffer append = new StringBuffer();
            boolean bip = true;
            Producto_servicio_peticionLocal producto_servicio_peticionLocal = null;
            Producto_servicioKey productoServicoKey = null;
            
            for (Iterator iter = producto_servicio_peticionArray.iterator(); iter.hasNext();) {
                producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iter.next();
                productoServicoKey = (Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();

                if (bip) {
                    append.append(productoServicoKey.ps_id);
                    bip = false;
                } else {
                    append.append(" ," + productoServicoKey.ps_id.toString());
                }
            }

            Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
            PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
            log.debug("Sacamos la PS para actualizar Intentario BA");

            //Se pregunta por la c�maras de esa petici�n, para ver s� hay ese tipo de equipos
            CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
            Collection camaras = camaraLocalHome.findByPeticion(new Long(peticion));
            boolean hayCamaras = camaras!=null && camaras.size()>0;
            Collection equipos = peticionLocal.getElemento_peticion();
            if (!hayCamaras && equipos.size() == 0) {
                log.debug("INFO: No existen ni c�maras ni Modems Asociados a la Peticion");
                return;
            }
            TR028E tr028e = new TR028E();

            String phoneNumber = peticionLocal.getNum_ide_nu_stb();
            
            try{
            	if (phoneNumber != null && !phoneNumber.trim().equals("")) {
                    if (phoneNumber.length() > 8) {
                        phoneNumber = phoneNumber.substring(0, 8);
                    }
                    
                    Integer phoneNumberAux = new Integer(phoneNumber);
                } else {
                    phoneNumber = "0";
                }	
            }catch(NumberFormatException ex){
            	 phoneNumber = "0";
            }
            
            if (phoneNumber.equals("0")){
            	noAsignoTelefono = true;
            }
            
            Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));

            tr028e.setId(String.valueOf(IdCorrelativo));
            
            Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
            Peticion_atisLocal peticionAtis = peticionLocal.getFk_01_pet_atis () ;
            Iterator iterAgrupacion = peticionAtis.getAgrupacion_atis ().iterator () ;
            
            
            /*Valida si alguna Agrupacion solo vienen con Ps de la Familia InternetEquipado*/
            

    		tr028e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());            
            tr028e.setAtiempoRequestNumber(peticionKey.peti_numero.longValue());
            
            ArrayList listaEquipos = new ArrayList();
			
			           //Req 17454
            PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
            ArrayList listaSubpeticiones=peticionesDelegate.obtenerSubpeticionesDesdePeticion(new Long(peticion));
            Iterator listaSubpeticionesIt=null;
            CaracteristicaPSLocal caracteristicaPSEquipo=null;
            
            for(listaSubpeticionesIt=listaSubpeticiones.iterator();listaSubpeticionesIt.hasNext();){
            	Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal)listaSubpeticionesIt.next();
            	if(caracteristicaPSEquipo==null){
            		caracteristicaPSEquipo=obtenerProductoServicio(subpeticion_atisLocal);
            	}
            }
            
            //De encontrar c�maras para la petici�n se env�an esos equipos
            if(hayCamaras){
            	for (Iterator iter = camaras.iterator(); iter.hasNext();) {
					CamaraLocal camara = (CamaraLocal) iter.next();
                    if(!camara.getAccion().toString().equals("0")){
                        if (camara.getTelefono().intValue() != new Integer(phoneNumber).intValue()){
                        	tr028e.setPcId(phoneNumber);
                        }else{
                        	tr028e.setPcId(camara.getTelefono().toString());
                        }
	                    TR028EEquipment tr028elemento = new TR028EEquipment();
	                    setValoresCamara(tr028elemento,camara);
	                    listaEquipos.add(tr028elemento);
                    }
                }
            	tr028e.setEquipments(listaEquipos);
            }

            for (Iterator iter = equipos.iterator(); iter.hasNext();) {
                Elemento_PeticionLocal elementoLocal = (Elemento_PeticionLocal) iter.next();
                if(!elementoLocal.getAccion().toString().equals("0")){
                    if (elementoLocal.getTelefono().intValue() != new Integer(phoneNumber).intValue()){
                    	tr028e.setPcId(phoneNumber);
                    }else{
                    	tr028e.setPcId(elementoLocal.getTelefono().toString());
                    }
                    tr028e.setPcId(tr028e.getAtiempoRequestNumber()+"_"+tr028e.getPcId());
                    
                    TR028EEquipment tr028elemento = new TR028EEquipment();
                    Elemento_PeticionKey elementoKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey();
                    
//                  Req 17454s
                    if(caracteristicaPSEquipo!=null){
                    	tr028elemento.setCaracteristica(caracteristicaPSEquipo.getPsId());
                    	elementoLocal.setCaracteristica(caracteristicaPSEquipo.getPsId().toString());
                    }else{
                    	if(elementoLocal.getCaracteristica() != null)
                    		tr028elemento.setCaracteristica(new Long(elementoLocal.getCaracteristica()));
                    }
                    
                    tr028elemento.setElementSerial(elementoKey.serial);
                    tr028elemento.setEquipmentState(elementoLocal.getAccion().toString());
                    tr028elemento.setElementType(elementoLocal.getTipo_elemento().toString());
                    tr028elemento.setEquipmentType(elementoLocal.getTipo_equipo());
                    tr028elemento.setInventoryType(elementoLocal.getTipo_inventario());
                    tr028elemento.setProductServiceCode(elementoLocal.getPs_id().longValue());
                
                    /*RQ.8595 - mfmendez*/
    				if(elementoLocal.getFec_cont_sap() != null)
    					tr028elemento.setInvPostingDateSAP(elementoLocal.getFec_cont_sap());
    				else
    					tr028elemento.setInvPostingDateSAP("");
    				
    				if(elementoLocal.getClase_mov_sap() != null)
    					tr028elemento.setInvMoveTypeSAP(elementoLocal.getClase_mov_sap());
    				else
    					tr028elemento.setInvMoveTypeSAP("");
    				
    				tr028elemento.setInvMaterialCodeSAP(Integer.toString(elementoLocal.getPos_doc_sap()));
    				
    				if(elementoLocal.getNum_material_sap() != null)
    					tr028elemento.setInvMaterialSAP(elementoLocal.getNum_material_sap());
    				else
    					tr028elemento.setInvMaterialSAP("");
    					
    				if(elementoLocal.getCentro_sap() != null)
    					tr028elemento.setInvPlantSAP(elementoLocal.getCentro_sap());
    				else
    					tr028elemento.setInvPlantSAP("");
    				
    				if(elementoLocal.getAlmacen_sap() != null)
    					tr028elemento.setInvStorageSAP(elementoLocal.getAlmacen_sap());
    				else
    					tr028elemento.setInvStorageSAP("");
    				
    				if(elementoLocal.getCod_lote_sap() != null)
    					tr028elemento.setInvBatchCodeSAP(elementoLocal.getCod_lote_sap());
    				else
    					tr028elemento.setInvBatchCodeSAP("");
    				
    				if(elementoLocal.getUnd_medida_sap() != null)
    					tr028elemento.setInvMeasurementUnitSAP(elementoLocal.getUnd_medida_sap());
    				else
    					tr028elemento.setInvMeasurementUnitSAP("");
    				
    				if(elementoLocal.getCentr_cost_sap() != null)
    					tr028elemento.setInvCostCenterSAP(elementoLocal.getCentr_cost_sap());
    				else
    					tr028elemento.setInvCostCenterSAP("");
    				
    				if(elementoLocal.getArea_func_sap() != null)
    					tr028elemento.setInvFuncAreaLongSAP(elementoLocal.getArea_func_sap());
    				else
    					tr028elemento.setInvFuncAreaLongSAP("");
    				
    				if(elementoLocal.getElement_pep_sap() != null)
    					tr028elemento.setInvPepElementSAP(elementoLocal.getElement_pep_sap());
    				else
    					tr028elemento.setInvPepElementSAP("");
    				
    				if(elementoLocal.getFlag_mat_sap() != null)
    					tr028elemento.setInvFlagMatSAP(elementoLocal.getFlag_mat_sap());
    				else
    					tr028elemento.setInvFlagMatSAP("");
    				/*FIN - RQ.8595 - mfmendez*/
                    
    	            while (iterAgrupacion.hasNext ()){
    	                Agrupacion_atisLocal agrupacion = (Agrupacion_atisLocal) iterAgrupacion.next () ;
    	                Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) agrupacion.getPrimaryKey();
    	                Iterator iterSubPeticion = agrupacion.getSubpeticion_atis ().iterator () ;
    	                boolean soloAgrupacionIT = true;
    	                while (iterSubPeticion.hasNext ()){
    	                    Subpeticion_atisLocal subPeticion = (Subpeticion_atisLocal) iterSubPeticion.next () ;
    	                    
    	                    Producto_servicioLocalHome psHome = null;
    	                    try {
    	                        psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
    	                    } catch (NamingException e) {
    	                        log.error("NamingException. ProductoServicioLocalHome", e);
    	                    }
    	                    
    	                    Producto_servicioLocal psLocal = null;
    	                    try {
    	                        psLocal = psHome.findByPrimaryKey(new Producto_servicioKey(subPeticion.getCod_pro_ser_cd()));
    	                    } catch (FinderException e) {
    	                        log.error("FinderException. PS No encontrado. [" + subPeticion.getCod_pro_ser_cd() + "]");
    	                    } catch (Exception e) {
    	                        log.error("Exception. PS No encontrado. [" + subPeticion.getCod_pro_ser_cd() + "]:" + e.getMessage());
    	                    }
    	        			int famPsIdLocal = ((Familia_producto_servicioKey)(((Familia_producto_servicioLocal) psLocal.getFamilia_producto_servicio()).getPrimaryKey())).faps_id.intValue();
    	        			
    	        			if (famPsIdLocal!=ComunInterfaces.familiaIntEquipado){
    	        				soloAgrupacionIT =false;
    	        			}
    	                }
    	                if (soloAgrupacionIT && elementoLocal.getAccion().intValue() == 2){
    	                	String psIntEquipado=VpistbbaConfig.getVariable("PS_INTERNET_EQUIPADO");
    	                	String[] listaIntEquipado=psIntEquipado.split(",");
    	                	for(int contPsIntEquipado=0;contPsIntEquipado<=listaIntEquipado.length-1;contPsIntEquipado++){
    	                		int psCobroIncInt=Integer.parseInt(listaIntEquipado[contPsIntEquipado]);
    	                		if (elementoLocal.getPs_id().intValue() == psCobroIncInt){
    	                			phoneNumber = elementoKey.serial;
    	                			tr028e.setPcId(tr028e.getAtiempoRequestNumber()+"_"+elementoKey.serial);
    	                			esVentaEquipos = true;
    	                			break;
    	                		}
    	                	}
    	                }                
    	            }

                    listaEquipos.add(tr028elemento);
                }

            }
            tr028e.setEquipments(listaEquipos);
            //Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
            Mensaje_estado_baLocal mensajeEstadoBALocal = mensajeEstadoBaLocalHome.create(IdCorrelativo);
            mensajeEstadoBALocal.setPeticion(peticionLocal);
            
            Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
            mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
            
            mensajeEstadoBALocal.setFecha_envio(df.format(new java.util.Date()));
            mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
            mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);

            int areaPhone = 0;
            int numeroPhone = 0;
            
            if (!esVentaEquipos){
            	if (phoneNumber.length() > 1) {
                    areaPhone = Integer.parseInt(phoneNumber.substring(0, 1));
                    numeroPhone = Integer.parseInt(phoneNumber.substring(1));
                }
            }else{
            	areaPhone = Integer.parseInt("0");
                //numeroPhone = Integer.parseInt(phoneNumber);
            }
            mensajeEstadoBALocal.setArea(new Integer(areaPhone));
            mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));

            ActualizaInventarioEquipoMQ actualizaInventarioEquipoMQ = new ActualizaInventarioEquipoMQ();
            actualizaInventarioEquipoMQ.enviarMensaje(tr028e);

        } catch (NumberFormatException e) {
            log.warn("NumberFormatException", e);
            throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
        } catch (CreateException e) {
            log.warn("CreateException", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

        } catch (FinderException e) {
            log.warn("FinderException", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

        } catch (Exception e) {
            log.debug("Exception", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
        }

    }
    
    /**
     * Coloca los valores del objeto equipment en el objeto camara
     * @param camara Camara
     * @param equipment Equipo
     * @param phoneNumber N�mero de tel�fono
     * @param accionModem Acci�n
     * @param ps PS
     */
    private void setValoresCamara(CamaraLocal camara, TR027SEquipment equipment, String phoneNumber, int accionModem, String ps){
    	camara.setCameraState(ComunInterfaces.CAMARA_ACTIVA);
    	camara.setCameraDescription("Activa");
    	if(phoneNumber!=null)
    		camara.setTelefono(new Long(phoneNumber.trim()));
   		camara.setAccion(new Short(new Integer(accionModem).shortValue()));
        camara.setTipoEquipo(equipment.getEquipmentType());
        camara.setTipoInventario(equipment.getInventoryType());
        if(equipment.getElementType()!=null)
        	camara.setTipoElemento(new Long(equipment.getElementType()));
        if(ps!=null)
        	camara.setPsId(new Long(ps.trim()));
        camara.setFecContSap(equipment.getInvPostingDateSAP());
    	camara.setClaseMovSap(equipment.getInvMoveTypeSAP());
		if(equipment.getInvMaterialCodeSAP() != null){
			camara.setPosDocSap(Integer.valueOf(equipment.getInvMaterialCodeSAP()));
		}else{
			camara.setPosDocSap(Integer.valueOf("0"));
		}
		camara.setNumMaterialSap(equipment.getInvMaterialSAP());
		camara.setCentroSap(equipment.getInvPlantSAP());
		camara.setAlmacenSap(equipment.getInvStorageSAP());
		camara.setCodLoteSap(equipment.getInvBatchCodeSAP());
		camara.setUndMedidaSap(equipment.getInvMeasurementUnitSAP());
		camara.setCentrCostSap(equipment.getInvCostCenterSAP());
		camara.setAreaFuncSap(equipment.getInvFuncAreaLongSAP());
		camara.setElementPepSap(equipment.getInvPepElementSAP());	
		camara.setFlagPetCurso(ComunInterfaces.FLAG_EQUIPO_PETICION);
		camara.setFlagMapSap(equipment.getInvFlagMatSAP());
    }
    
    /**
     * Coloca los valores del objeto camara en el objeto equipo
     * @param equipment Objeto Equipo
     * @param camara Objeto C�mara
     */
    private void setValoresCamara(TR028EEquipment equipment, CamaraLocal camara){
    	CamaraKey key = (CamaraKey) camara.getPrimaryKey();
        equipment.setElementSerial(key.cameraSerial);
        if(camara.getAccion()!=null)
        	equipment.setEquipmentState(camara.getAccion().toString());
        if(camara.getTipoElemento()!=null)
        	equipment.setElementType(camara.getTipoElemento().toString());
        equipment.setEquipmentType(camara.getTipoEquipo());
        equipment.setInventoryType(camara.getTipoInventario());
        if(camara.getPsId()!=null)
        	equipment.setProductServiceCode(camara.getPsId().longValue());
		
        if(camara.getFecContSap() != null)
			equipment.setInvPostingDateSAP(camara.getFecContSap());
		else
			equipment.setInvPostingDateSAP("");
		
		if(camara.getClaseMovSap() != null)
			equipment.setInvMoveTypeSAP(camara.getClaseMovSap());
		else
			equipment.setInvMoveTypeSAP("");
		
		if(camara.getPosDocSap() != null)
			equipment.setInvMaterialCodeSAP(camara.getPosDocSap().toString());
		else
			equipment.setInvMaterialCodeSAP("");
		
		if(camara.getNumMaterialSap() != null)
			equipment.setInvMaterialSAP(camara.getNumMaterialSap());
		else
			equipment.setInvMaterialSAP("");
			
		if(camara.getCentroSap() != null)
			equipment.setInvPlantSAP(camara.getCentroSap());
		else
			equipment.setInvPlantSAP("");
		
		if(camara.getAlmacenSap() != null)
			equipment.setInvStorageSAP(camara.getAlmacenSap());
		else
			equipment.setInvStorageSAP("");
		
		if(camara.getCodLoteSap() != null)
			equipment.setInvBatchCodeSAP(camara.getCodLoteSap());
		else
			equipment.setInvBatchCodeSAP("");
		
		if(camara.getUndMedidaSap() != null)
			equipment.setInvMeasurementUnitSAP(camara.getUndMedidaSap());
		else
			equipment.setInvMeasurementUnitSAP("");
		
		if(camara.getCentrCostSap() != null)
			equipment.setInvCostCenterSAP(camara.getCentrCostSap());
		else
			equipment.setInvCostCenterSAP("");
		
		if(camara.getAreaFuncSap() != null)
			equipment.setInvFuncAreaLongSAP(camara.getAreaFuncSap());
		else
			equipment.setInvFuncAreaLongSAP("");
		
		if(camara.getElementPepSap() != null)
			equipment.setInvPepElementSAP(camara.getElementPepSap());
		else
			equipment.setInvPepElementSAP("");
		
		if(camara.getFlagMapSap() != null)
			equipment.setInvFlagMatSAP(camara.getFlagMapSap());
		else
			equipment.setInvFlagMatSAP("");
    }

    public void recepcionActualizaInventarioBA(TR028S tr028s) throws ATiempoAppEx {
        // CR15338 - @Trace - ana santos - 04/08 - Inicio
        BackendExecution bExecution = null;

        try {
            bExecution = BackendTraceUtil.initExecution(tr028s, log);
            bExecution.setNumPetAtiempo(new Long(tr028s.getAtisRequestNumber()));
            bExecution.setIdMensajeOp(tr028s.getId());
            bExecution.startOperation();

            validaHome();

            Mensaje_estadoLocal mesajeOkLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
            Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
            Mensaje_estadoLocal mensajeManualLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEsperaManual)));

            Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr028s.getId()));
            Mensaje_estado_baLocal mensajeEstadoBaLocal;
            
            CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
            Collection camaras = camaraLocalHome.findByPeticion(new Long(tr028s.getAtiempoRequestNumber()));
            boolean hayCamaras = camaras!=null && camaras.size()>0;
            
            try {
                mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
            } catch (FinderException fex) {
                mensajeEstadoBaLocal = null;
            }

            /*
             * Validacion de existencia de la respuesta en la Base de Datos en
             * la tabla Mensaje_Estado_Linea
             */
            if (mensajeEstadoBaLocal == null) {
                log.debug("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr028s));
                throw new ATiempoAppEx(
                        "No Existe Respuesta en la base de enviados:"
                                + tr028s.getId(), ATiempoAppEx.EXCEPTION);
            }

            Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey();

            //Validacion del estado de la respuesta
            if (mensaje_estadoKey.cod_estado.intValue() == ComunInterfaces.estadoOk
                    || mensaje_estadoKey.cod_estado.intValue() == ComunInterfaces.estadoEsperaManual) {
                log.debug("Es estado de la respuesta es diferente para ser procesado\n " + XMLUtilities.marshall(tr028s));
                return;
            }

            PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();

            log.debug("Mensaje recibido.");
            /*******************************************************************
             * if (this.getEstadoMultipleMensajes(mensajeEstadoBaLocal, new
             * Integer(ComunInterfaces.estadoEspera))){ log.debug("Ultimo
             * mensaje -> se cierra la acitividad");
             ******************************************************************/
            // todo ok, se graba la respuesta
            Recursos_baLocal recursos_baLocal;
            Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

            if (recursosLineaBaBasica.size() == 0) {
                Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
                recursos_baLocal = recursos_baLocalHome.create(idConDos);
            } else {
                recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
            }

            //if (tr028s.getErrorCode() == 0 || tr028s.getErrorCode() == 0) {
            if (tr028s.getErrorCode() == 0){
                recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
                recursos_baLocal.setCod_error(new Integer(String.valueOf(tr028s.getErrorCode())));
                recursos_baLocal.setDesc_error(tr028s.getErrorMessage());
                recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
                if(hayCamaras){
                	for (Iterator iter = camaras.iterator(); iter.hasNext();) {
    					CamaraLocal camara = (CamaraLocal) iter.next();
    					camara.setCameraState(ComunInterfaces.CAMARA_INACTIVA);
    					camara.setCameraDescription("");
                    }
                }

            }

            mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
            PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();

            //TODO: Ra�l Ernesto Trivi�o Alvarado - 23/04/2010 - Requerimiento Req_2010_00034402_UMTS-PDTI
            if (tr028s.getErrorCode() != 0) {
            	recursos_baLocal.setCod_error(new Integer(String.valueOf(tr028s.getErrorCode())));
                recursos_baLocal.setDesc_error(tr028s.getErrorMessage());
                
                mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
                mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
            	
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				
				String codError = String.valueOf(tr028s.getErrorCode());
				String nombreClase=TR028S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				
				if(errorLegado != null){
					peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else{
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				}
				
				log.debug("Tengo un error en el mensaje");
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
            	
                actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,VpistbbaConfig.getVariable("IDPGI"));
                actDto.setObservacion("Error en la Actualizacion de Inventario.Se deriva a PGI.Descripcion:" + tr028s.getErrorCode()+ "."
                		+ tr028s.getErrorMessage());
 
                actividadEJB.terminar(actDto);

                return;
            }
            //End TODO

            ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
            IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
            ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());

            actividadEJB.terminar(actDto);

            mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
            mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);

        } catch (NumberFormatException e) {
            bExecution.setErrorOp(true);
            log.warn("NumberFormatException", e);
            throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

        } catch (CreateException e) {
            bExecution.setErrorOp(true);
            log.warn("CreateException", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

        } catch (FinderException e) {
            bExecution.setErrorOp(true);
            log.warn("FinderException", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

        } catch (TnProcesoExcepcion e) {
            bExecution.setErrorOp(true);
            log.warn("TnProcesoExcepcion", e);
            throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
        } catch (Exception e) {
            bExecution.setErrorOp(true);
            log.debug("Exception", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
        } finally {
            bExecution.endAll();
            // CR15338 - @Trace - ana santos - 04/08 - FIN
        }
    }

    public Integer identificarOperacion(Long nroPeticion) {
        boolean esAlta = false;
        boolean esTraslado = false;
        boolean esCambioPlan = false;
        boolean cambProdLBToUmts = false;
        ArrayList retorno = new ArrayList();
        PeticionLocal peticionLocal = null;
        if (peticionLocalHome == null)
            try {
                peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    	
    		try{
            peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));    
    		}catch (FinderException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        for (Iterator iterator = peticionLocal.getProducto_servicio_peticion().iterator(); iterator.hasNext();) {
            Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iterator.next();
            Producto_servicioLocal producto_servicioLocal = producto_servicio_peticionLocal.getProducto_servicio();
            Familia_producto_servicioLocal familia_producto_servicioLocal = producto_servicioLocal.getFamilia_producto_servicio();
            Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
            Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();
            Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
            Collection listaEstado = producto_servicio_peticionLocal.getEstado_ps_peticion();
            /**
             * Req correctivo 0381, las siguientes l�neas(comentadas) retonan 3 sin importar el ps que est� en estdo 3. P.e si el ps quebrado es de TV,
             * se retornar� 3 con lo cual se deshabilitar�n el ingreso de m�dems o quipos pdti, lo cual no tiene sentido.
             * 
             * Se cambia el retorno a 4, con lo cual se indica que hay un ps quebrado y luego se valida si el ps quebrado es de BA o PDTI.  Este m�todo s�lo se
             * llama en controlInstalaci�n.
             */
            if (listaEstado.size() > 0) {
                Estado_ps_peticionLocal estado_ps_peticionLocal = (Estado_ps_peticionLocal) listaEstado.iterator().next();
                if (estado_ps_peticionLocal.getCod_estado_cierre().intValue() == ComunInterfaces.estadoCierreError) {
                    Integer valAlta = new Integer(4);
                    return valAlta;
                }
            }
            if (operacion_comercialLocal.getOpco_tipo().equals("ACP")) {
                
                boolean esUmts = false;
                try{
                    PeticionesDelegate delegate = new PeticionesDelegate();
        			esUmts = delegate.esGrupoUmts(nroPeticion);
            	}catch (ATiempoAppEx e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            	
            	log.debug("esUmts = "+esUmts+" y Id.Operacion.Comercial para el Cambio de producto Umts .."+operacion_comercialKey.opco_id);
                
            	if(esUmts){
            	    log.debug("El ps id es .."+producto_servicio_peticionLocal.getPsId());
                    //if(operacion_comercialKey.opco_id.equals(new Long("46"))){
                    //ahora veo si el PS de ALTA pertenece al grupo de UMTS o no
            	    //para eso  utilizo la tabla PS_TIPO_EQ
            	    Long ps_id = producto_servicio_peticionLocal.getPsId();
            	    Ps_Tipo_EqLocal ps_tipo_eqLocal = null;
            	    Ps_Tipo_EqLocalHome psTipoEqLocalHome = null;
            	    
            	    try{
            	        psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
            	        ps_tipo_eqLocal  = psTipoEqLocalHome.findTipoByPs(ps_id.longValue());
            	        esCambioPlan = true;
            	        cambProdLBToUmts = true;
            	    } catch (FinderException e1) {
                        log.debug("No es un ps de UMTS");
                        esCambioPlan = false;
                    } catch (NamingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }  
                    
                }else{
                    esCambioPlan = true;
                }
                
                
            } else if (operacion_comercialLocal.getOpco_tipo().equals("A")) {
                esAlta = true;
                if (operacion_comercialLocal.getOpco_tras() != null) {
                    if (operacion_comercialLocal.getOpco_tras().equals("T"))
                        esTraslado = true;
                }
            }
        }
        Integer valAlta = null;
        if (esAlta) {
            if (esTraslado)
                valAlta = new Integer(2);
            else
                valAlta = new Integer(1);

        } else {
            if (esCambioPlan){
                if(cambProdLBToUmts)
                    valAlta = new Integer(1);
                else
                    valAlta = new Integer(3);
            }else
                valAlta = new Integer(0);
        }
        return valAlta;
    }
    
    public ArrayList loadJSP(TR026S tr026s, Long idPeticion){
    
    Collection titulos = 	null;
	ElementoLocalHome elementoHome = null;
    ArrayList retorno = new ArrayList();
	try {
		elementoHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
		titulos = 	elementoHome.findAll();	
	
	int tamanoLista = 0 ; 
	int cantidadTipos = 0;
	//Correccion AT-2525 10/09/2009
	 if (tr026s.getErrorCode() == 0) {
      
		 if(tr026s.getEquipments()!=null){
			 tamanoLista = tr026s.getEquipments().size();
		     ArrayList [] columnas = new ArrayList[titulos.size()+1];
		     String [] titles = new String[titulos.size()+1];	   
		
			 for(java.util.Iterator iterator=tr026s.getEquipments().iterator();iterator.hasNext();)
			 {
			 	TR026SEquipment equipo=(TR026SEquipment)iterator.next();
			 	ElementoDTO eDTO = new ElementoDTO();
			 	eDTO.setMarca(equipo.getBrand());
			 	eDTO.setModelo(equipo.getModel());
			 	eDTO.setSerial(equipo.getElementSerial());
			 	/*fmendez - rq6895 - interfaz SAP*/
			 	eDTO.setNumCelular(equipo.getCellphone());
			 	eDTO.setMaterialSAP(equipo.getMaterialSAP());
			 	eDTO.setCentroSAP(equipo.getPlantSAP());
			 	eDTO.setAlmacenSAP(equipo.getStorageSAP());
			 	eDTO.setNumDocComprasSAP(equipo.getNumPurchasingDocSAP());
			 	eDTO.setNumPosDocComprasSAP(equipo.getNumPositionPurchDocSAP());
			 	/*Fin fmendez - rq6895 - interfaz SAP*/
			 	 ElementoLocal el = elementoHome.findByPrimaryKey(new Integer(equipo.getElementType()));
			 	eDTO.setIdElemento(el.getId_elemento().intValue());
			 	 eDTO.setTipo_equipo(el.getDesc_elemento());
			 	 retorno.add(eDTO);	
			 }
			  Collections.sort(retorno, new ElementoDTO());
	
		}
	 }else{

	     return null;
	     
	 }
	 // fin Correccion AT-2525 10/09/2009
	} catch (NamingException e2) {
		
	} catch (FinderException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
	 return retorno; 
    }
   
    
	public void enviarConfiguracionActualEquipos(String peticion, String id_actividad) throws ATiempoAppEx {
		this.enviarConfiguracionActualEquipos(null,peticion,id_actividad);	
}
	
public void enviarConfiguracionActualEquipos(String telefonoConsulta, String peticion, String id_actividad) throws ATiempoAppEx{		
	try
	{	
		validaHome ();

		Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));

		boolean esRefrescar=false;
		boolean esRefrescarNew=false;
		if(id_actividad.equals(""))
		{
			esRefrescar=true;
			esRefrescarNew=false;
			id_actividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_CONF_BA");
		}else if(id_actividad.equals("RN")){
			esRefrescar=false;
			esRefrescarNew=true;
			id_actividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_CONF_BA");
		}

		PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
		Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();

		Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		TR027E tr027e = new TR027E();
		tr027e.setId(String.valueOf(IdCorrelativo));
		PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();

		//TODO: RETA: 11022010 - Correcci�n defecto para traer el telefono de la l�nea origen 
		String phoneNumber = peticionLocal.getIdentificadorOriLinea();  //Correccion Agonz 24/06/2009 peticionLocal.getIdentificadorOriLinea() = telefono ANTERIOR
		//String phoneNumber = peticionLocal.getNum_ide_nu_stb();
		//End TODO
		
		//si viene un telefono, se consulta por ese telefono y se asocia a la peticion
		/*if (telefonoConsulta != null && !"".equals(telefonoConsulta.trim()))
		{
			phoneNumber = telefonoConsulta;							
		}*/
		if (phoneNumber!=null && phoneNumber.trim()!= ""){
			if (phoneNumber.length()>8) 
				phoneNumber=phoneNumber.substring(0,8);		
		}			
		else{
			phoneNumber="";
		}	
		
		 Producto_servicio_peticionLocal producto_servicio_peticionLocal = null;
         Producto_servicioKey productoServicoKey = null;
         for (Iterator iter = producto_servicio_peticionArray.iterator(); iter.hasNext();) {
             producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iter.next();
             productoServicoKey = (Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
         }
     	
		tr027e.setPcId(phoneNumber);
		tr027e.setProductServiceCode(productoServicoKey.ps_id.longValue());
		tr027e.setAtiempoRequestNumber(peticionKey.peti_numero.longValue());
		Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
		tr027e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());
		
		
		// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
		Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
		mensajeEstadoBALocal.setPeticion(peticionLocal);
		//Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
		//mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
		mensajeEstadoBALocal.setCod_familia_ps(new Integer(ComunInterfaces.familiaPcBA));
		
		//TODO: Verificar el Conector Correcto
		//mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSiete))));
		
		if(esRefrescar){
			mensajeEstadoBALocal.setAccion("R");
		}else if(esRefrescarNew){
			mensajeEstadoBALocal.setAccion("RN");
		}
		
		mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
		mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
		mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);

		int areaPhone= 0;
		int numeroPhone= 0;
		if (phoneNumber.length()>1){
			areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
			numeroPhone=Integer.parseInt(phoneNumber.substring(1));
		}
		mensajeEstadoBALocal.setArea(new Integer(areaPhone));
		mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
		//AT-1633 12-09-2008
		//SolicitudConfiguracionBAMQ  configuracionActualBAMQ = new SolicitudConfiguracionBAMQ();
		SolicitudConfiguracionEquiposMQ  configuracionActualEquiposMQ = new SolicitudConfiguracionEquiposMQ();
		configuracionActualEquiposMQ.enviarMensaje(tr027e);
	}
	catch (NumberFormatException e){
		log.warn("NumberFormatException",e);
		throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
	}
	catch (CreateException e){
		log.warn("CreateException",e);
		throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
	}
	catch (FinderException e){
		log.warn("FinderException",e);
		throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
	} 
	catch(Exception e){
		log.debug("Exception",e);
		throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
	}		
	}
    

public void recepcionConfiguracionActualEquipo(TR027S tr027s) throws ATiempoAppEx{
	
	BackendExecution bExecution = null;
	try
		{	
			bExecution = BackendTraceUtil.initExecution(tr027s, log);
			bExecution.setIdMensajeOp(tr027s.getId());
			bExecution.startOperation();
			int accionModem = ComunInterfaces.accionModemOcupar;
			validaHome ();

			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
			Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEsperaManual)));
			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr027s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;

			try{
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			}catch (FinderException fex){
					mensajeEstadoBaLocal = null ;
			}
			


			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			if (mensajeEstadoBaLocal == null)
			{
				log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr027s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr027s.getId(),ATiempoAppEx.EXCEPTION);
			}

			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;

			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() == ComunInterfaces.estadoOk || mensaje_estadoKey.cod_estado.intValue() == ComunInterfaces.estadoEsperaManual)
			{
				log.debug("Es estado de la respuesta es diferente para ser procesado\n "+ XMLUtilities.marshall(tr027s));
				return;
			}

			//Recursos_baLocal recursos_baLocal;
			PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
			//String phoneNumber = peticionLocal.getIdentificadorOriLinea(); Correccion Agonz 24/06/2009 peticionLocal.getIdentificadorOriLinea() = telefono ANTERIOR
			String phoneNumber = peticionLocal.getNum_ide_nu_stb();


			mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
            
			//int codigoAccion = Integer.parseInt(tr027s.getAction());
			Ps_Tipo_EqLocalHome ps_TipoHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
			log.debug("Lista de equipos: "+ tr027s.getEquipments());
			
			if (tr027s.getErrorCode() == 0 && tr027s.getEquipments() !=null){
			    log.debug("No tengo Error en el mensaje");
				 for (Iterator iterator = tr027s.getEquipments().iterator(); iterator.hasNext();) {
				        TR027SEquipment elementoVpiDTO = (TR027SEquipment) iterator.next();
				        String ps = String.valueOf(elementoVpiDTO.getProductServiceCode());

				        Collection psCollection = mensajeEstadoBaLocal.getPeticion().getProducto_servicio_peticion();
				        for (Iterator psIter = psCollection.iterator(); psIter.hasNext();){
				        	Producto_servicio_peticionLocal psServicioPetLocal = (Producto_servicio_peticionLocal) psIter.next();
				        	
				        	if (psServicioPetLocal.getPsId().toString().equals(ps)){
				        		if (psServicioPetLocal.getOperacion_comercial().getOpco_tipo() != null &&
						        		(psServicioPetLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja)
						        			|| psServicioPetLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd))){
						        		accionModem = ComunInterfaces.accionModemLiberar;
						        		break;
						        	}
				        		
				        	}
				        }
				        
				        
				        //CR25996- Umts
				        Ps_Tipo_EqLocal psLocal = ps_TipoHome.findTipoByPs(elementoVpiDTO.getProductServiceCode());
				        String tipo_e = psLocal.getId_tipo_eq().toString();
		                log.debug("Graba en la Base");
		                
		                //S� el tipo de equipo es c�mara entonces se agregan los valores de la camara
		                PeticionesDelegate delegate = new PeticionesDelegate();
		                String cameraEquipmentType[] = delegate.recuperarParametroFromPropertiesBD(ComunInterfaces.CAMERA_EQUIPMENT_TYPE).split(",");
		                
		                log.debug("tipo_e: "+tipo_e);
		                boolean esCamara = false;
		                for(int i = 0;i<cameraEquipmentType.length;i++){
		                	if(cameraEquipmentType[i].equals(tipo_e))
		                		esCamara = true;
		                }
		                log.debug("Es c�mara : "+esCamara);
				        if(esCamara){
				        	CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
				        	CamaraLocal camara = camaraLocalHome.create(peticionKey.peti_numero,elementoVpiDTO.getElementSerial());
				        	setValoresCamara(camara,elementoVpiDTO, phoneNumber, accionModem, ps);
				        }else{
					        Elemento_PeticionLocal elemento_peticionLocal = elemento_peticionLocalHome.create(elementoVpiDTO.getElementSerial(), 
			                        peticionLocal,new Long(phoneNumber), new Short(String.valueOf(accionModem)), 
			                        elementoVpiDTO.getEquipmentType(),elementoVpiDTO.getInventoryType(), new Long(elementoVpiDTO.getElementType()) ,new Long(ps));
			                //Fin CR25996- Umts
					        /*RQ.8595 - mfmendez*/
		                	elemento_peticionLocal.setFec_cont_sap(elementoVpiDTO.getInvPostingDateSAP());
		                	elemento_peticionLocal.setClase_mov_sap(elementoVpiDTO.getInvMoveTypeSAP());
							if(elementoVpiDTO.getInvMaterialCodeSAP() != null){
								elemento_peticionLocal.setPos_doc_sap(Integer.parseInt(elementoVpiDTO.getInvMaterialCodeSAP()));
							}else{
								elemento_peticionLocal.setPos_doc_sap(0);
							}
							elemento_peticionLocal.setNum_material_sap(elementoVpiDTO.getInvMaterialSAP());
							elemento_peticionLocal.setCentro_sap(elementoVpiDTO.getInvPlantSAP());
							elemento_peticionLocal.setAlmacen_sap(elementoVpiDTO.getInvStorageSAP());
							elemento_peticionLocal.setCod_lote_sap(elementoVpiDTO.getInvBatchCodeSAP());
							elemento_peticionLocal.setUnd_medida_sap(elementoVpiDTO.getInvMeasurementUnitSAP());
							elemento_peticionLocal.setCentr_cost_sap(elementoVpiDTO.getInvCostCenterSAP());
							elemento_peticionLocal.setArea_func_sap(elementoVpiDTO.getInvFuncAreaLongSAP());
							elemento_peticionLocal.setElement_pep_sap(elementoVpiDTO.getInvPepElementSAP());	
							elemento_peticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
							elemento_peticionLocal.setFlag_mat_sap(elementoVpiDTO.getInvFlagMatSAP());
							if(elementoVpiDTO.getCaracteristica() != null)
								elemento_peticionLocal.setCaracteristica(elementoVpiDTO.getCaracteristica().toString());
							/*FIN - RQ.8595 - mfmendez*/
					        /*
					        Elemento_PeticionLocal elemento_peticionLocal = elemento_peticionLocalHome.create(elementoVpiDTO.getElementSerial(), 
			                        peticionLocal,new Long(phoneNumber), new Short(String.valueOf(ComunInterfaces.accionModemOcupar)), 
			                        elementoVpiDTO.getEquipmentType(),elementoVpiDTO.getInventoryType(), new Long(elementoVpiDTO.getElementType()) ,new Long(ps));
					        */
				        }
		            }
				mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
				
				//actividadEJB.terminar(actDto);
			}else{
				//TODO: 23/04/2010 - Ra�l Ernesto Trivi�o Alvarado - Ajuste Requerimiento Req_2010_00034402_UMTS-PDTI 
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				
				String codError = String.valueOf(tr027s.getErrorCode());
				String nombreClase=TR027S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				
				if(errorLegado != null){
					peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else{
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				}
				
				log.debug("Tengo un error en el mensaje");
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));

				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				actDto.setObservacion("Error, se deriva a PGI / " + " Respuesta:  " + tr027s.getErrorCode() + " -  " +  tr027s.getErrorMessage(),true);
				
				actividadEJB.terminar(actDto);
				return;
				//End TODO
			}
			
			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			if(mensajeEstadoBaLocal.getAccion()!=null && (mensajeEstadoBaLocal.getAccion().equals("R") || mensajeEstadoBaLocal.getAccion().equals("RN")))
				return;

			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
			actividadEJB.terminar(actDto);
		}		
		catch(FinderException e){
			bExecution.setErrorOp(true);	
			e.printStackTrace();	
			log.warn("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		}
		catch (TnProcesoExcepcion e){
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} 
		catch(Exception e){
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.debug("EXCEPTION",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}finally{  
			bExecution.endAll();
		
		}
}			
private void insertarCausalesCnaPeticion(PeticionLocal peticionLocal, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, CreateException, FinderException 
{
//	PeticionLocal peticionLocal=mensajeEstadoBaLocal.getPeticion();
	PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
	if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!= ComunInterfaces.estadoPeticionEnCurso)
	{
		log.info("En reversa no se almacenan Quiebres Autom�ticos.PetAtiempo:"+peticionKey.peti_numero);
		return;
	}
	Fecha fecha=new Fecha();
	PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
	DBManager manager=new DBManager ();
	manager.setDataSource (DBManager.JDBC_VPISTBBA);
	if(usuarioHome==null)
		usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
	if(catalogo_causalHome==null)
		catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
	if(estado_psHome==null)
		estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
	if(estado_ps_peticionHome==null)
		estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
	if(causal_peticionHome==null)
		causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);


	ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
	ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
	Long idAct=actividadSessionLocal.getIdActividad(cod_actividad,new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
	if(idAct==null)
		return;								
	
	log.debug("Estoy insertando los causales de CNA para la peticion:"+peticionKey.peti_numero);
	for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
	{
		Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
		Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
		Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
		Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
		Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
		if(peticionesDelegate.pasaPSyOcXActividad(peticionKey.peti_numero,producto_servicioKey.ps_id,operacion_comercialKey.opco_id,idActividadFlujo))
		{
			//Quiere decir que tengo que asociarle la causal a los ps que invocaron esta actividad.

			Collection listaEstadoPsPet=producto_servicio_peticionLocal.getEstado_ps_peticion();
			if(listaEstadoPsPet.size()>0)
			{	
			
				Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(codCausal);
				Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
			
				//Tengo una causa asociada tengo que actualizar
				Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPsPet.iterator().next();
				estado_ps_peticionLocal.setCod_actividad(idAct);
				estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
				estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
				estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());

				Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
				Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
				UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
				long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
				Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
				
				causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
				causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
				causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				causal_peticionLocal.setCod_actividad(idAct);
			}
			else
			{
			
				Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(codCausal);
				Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
			
				long correlativo=manager.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
				Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
				estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
				estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
				estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				estado_ps_peticionLocal.setCod_actividad(idAct);

			
				Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
				Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
				UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
				long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
				Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
				
				causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
				causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
				causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				causal_peticionLocal.setCod_actividad(idAct);
			}
		}
	}
}  

	private ErrorLegadoLocal getErrorLegado(String codigoError,String codigoTr){
		ErrorLegadoLocal errorLegado = null;
		try{
			ErrorLegadoLocalHome errorLegadoHome = (ErrorLegadoLocalHome) HomeFactory.getHome( ErrorLegadoLocalHome.JNDI_NAME);
			errorLegado = errorLegadoHome.findByErrorTr(codigoError, codigoTr);
		} catch (javax.ejb.FinderException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			log.debug("No se encontro el error legado..codigo..: " + codigoError +" TR..:"+ codigoTr + " se inserta tipo de error por defecto");
			
		}
		return errorLegado;
	}
	
	//Se modifica este m�todo para usarse como una tarea autom�tica y no una manual
	public void recibirPrimeraFacturaInternetEquipado(TR044S tr044s) throws ATiempoAppEx{ 
		System.out.println("Entro al m�todo recibirPrimeraFacturaInternetEquipado");
		
		
		Mensaje_estado_baLocal mensaje_estado_ba;
		try {
			mensaje_estado_ba =mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr044s.getId())));
			
			if (mensaje_estado_ba == null){
				log.debug ("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall (tr044s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr044s.getId(),ATiempoAppEx.EXCEPTION);
			}
		
			//Se almacena el mensaje en la tabla temporal tmp_equipo
			try{
				Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensaje_estado_ba.getMensaje_estado ().getPrimaryKey () ;
				
				Long idTmpEquipo = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_MODEM")) ;
				Tmp_equipoLocalHome tmp_equipoLocalHome=(Tmp_equipoLocalHome) HomeFactory.getHome(Tmp_equipoLocalHome.JNDI_NAME);
		
				Tmp_equipoLocal tmpDecoTarjetaLocal = tmp_equipoLocalHome.create (idTmpEquipo,mensaje_estado_ba.getPeticion(),XMLUtilities.marshall (tr044s));
			}catch (CreateException e){
				throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
			}catch (NamingException e){
				throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
			}	
			
			
			PeticionLocal peticionLocal = mensaje_estado_ba.getPeticion();
			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
			
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensaje_estado_ba.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensaje_estado_ba.getCod_actividad_generadora());
			
			mensaje_estado_ba.setDesc_error(tr044s.getErrorMessage());
			
			if (tr044s.isError()){
				mensaje_estado_ba.setId_error("1"); 	//present� error
				mensaje_estado_ba.setAccion("no");
				
				//Como presenta error entonces derivo a PGI
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,VpistbbaConfig.getVariable("IDPGI"));
                actDto.setObservacion("El legado informa que se gener� un error en el proceso de generaci�n de factura.Se deriva a PGI.Descripcion:" + tr044s.getErrorMessage());
                
                insertarCausalesCnaPeticion(mensaje_estado_ba.getPeticion(), mensaje_estado_ba.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
 
                actividadEJB.terminar(actDto);
                
			}else{
				mensaje_estado_ba.setId_error("0");		//Esta bien
				mensaje_estado_ba.setAccion("OK");
				
				actDto.setObservacion("Se recibe confirmaci�n del legado que se gener� una factura sin inconvenientes, continua a la siguiente actividad");
				actividadEJB.terminar(actDto);
			}
		}catch (FinderException e1){
			log.debug("Error FinderException en m�todo recibirPrimeraFacturaInternetEquipado:"+e1);
			e1.printStackTrace();
			mensaje_estado_ba=null;
		}catch (TnProcesoExcepcion ex){
			log.debug("Error TnProcesoExcepcion en m�todo recibirPrimeraFacturaInternetEquipado:"+ex);
			ex.printStackTrace();
			mensaje_estado_ba=null;
		}catch (CreateException ex){
			log.debug("Error CreateException en m�todo recibirPrimeraFacturaInternetEquipado:"+ex);
			ex.printStackTrace();
			mensaje_estado_ba=null;
		}catch (NamingException ex){
			log.debug("Error NamingException en m�todo recibirPrimeraFacturaInternetEquipado:"+ex);
			ex.printStackTrace();
			mensaje_estado_ba=null;
		}
		
		
		//No se genera factuaci�n ahora
		/*if (!tr044s.isError()){
			String strPdf = tr044s.getPdf();
			
			try{
				byte decoded[] = new sun.misc.BASE64Decoder().decodeBuffer(strPdf);
				
				FileOutputStream  fos = null;
				File directorio = new File(VpistbbaConfig.getVariable("DIRECCION_PDF"));
				if (!directorio.exists() || !directorio.isDirectory()){
					directorio.mkdir();
				}
				
				fos = new FileOutputStream(VpistbbaConfig.getVariable("DIRECCION_PDF")+"factura_"+tr044s.getNumeroPedido()+"_"+tr044s.getCodigoPS()+".pdf");
				fos.write(decoded);
				fos.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}*/
	}
	
	/*RQ 6895 - Interfaz Atiempo - MM SAP*/
	public boolean enviarInformacionEquiposMMSAP(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		boolean exito = false;
		
		try{
			log.debug("Ingresa a enviarInformacionEquiposMMSAP, para la petici�n Atiempo No. "+nroPeticion);
		
			/*Se obtiene propiedad con PSs para los que se debe enviar a SAP*/
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey("PS_ENVIO_EQUIPOS_SAP");
			
			String psEnvioSAPStr = propertiesBDLocal.getValor();
			String[] listaPsEnvioSAP = null;
	        boolean esPSPC = false;
	        
			 /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
	        PeticionKey key = new PeticionKey(nroPeticion);
	        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
	        
			if(psEnvioSAPStr != null && !psEnvioSAPStr.equals("")){
				listaPsEnvioSAP = psEnvioSAPStr.split(",");        
		        
		        Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
        		Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACTIVIDAD_INSTALAR_EQUIPO));
        		/*Valida que la petici�n tenga por lo menos un PS de los configurados en la tabla de propiedades (PS_ENVIO_EQUIPOS_SAP)*/
        		forPadre: for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
        			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
        			
        			for(int contPsIntEquipado=0;contPsIntEquipado<=listaPsEnvioSAP.length-1;contPsIntEquipado++){
        				if (peticionFlujoLocal.getPrse_id().toString().equals(listaPsEnvioSAP[contPsIntEquipado])){
        					esPSPC = true;
	    					break forPadre;
        				}
        			}
        		}
			}
			
			/*Valida que la petici�n tenga equipos configurados en caso contrario se inhibe la actividad*/
			boolean tieneEquipos = false;
			boolean tieneEquiposASC = false;
			Collection equipos = peticionLocal.getElemento_peticion();

			if (equipos != null && equipos.size() > 0) {

				tieneEquipos = true;
				/* Valida si tiene equipos de Agenda SC */
				for (Iterator i = equipos.iterator(); i.hasNext();) {
					Elemento_PeticionLocal elemPetLocal = (Elemento_PeticionLocal) i.next();

					if (elemPetLocal != null
							&& elemPetLocal.getFlag_mat_sap() != null
							&& (elemPetLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP) || 
									elemPetLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_NO_ES_MATERIAL_SAP))) {

						tieneEquiposASC = true;
						break;
					}
				}
			} else {
				log.debug("INFO: No existen Equipos Asociados a la Peticion: "+ nroPeticion+ ". No se enviar� la mensajeria hacia MM SAP");
				tieneEquipos = false;
			}

			/* Se valida si se debe inhibir la actividad o no */
			String mensajeBitacora = new String("");
			if (esPSPC && tieneEquipos && !tieneEquiposASC) {
				crearYEnviarMensajeSAP(nroPeticion, actGeneradora,peticionLocal);
			} else {
				log.debug("Se inhibe la actividad, para la petici�n Atiempo No. "+ nroPeticion);
				mensajeBitacora = "Se inhibe la actividad porque no existen PS's configurados para enviar info. a SAP, y/o no existen equipos asociados a la petici�n.";

				log.debug("Mensaje Bitacora = " + mensajeBitacora);
				act.setObservacion(mensajeBitacora, true);
				act.setRealizarTerminoInmediato(true);
			}
		}catch (FinderException e) {
            log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (CreateException e) {
        	log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (NamingException e) {
			log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		} catch (ATiempoAppEx e) {
			log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw e;
		} catch (Exception e) {
			log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		} 
		
		return exito;
	}
	
	private void crearYEnviarMensajeSAP(Long nroPeticion, String actGeneradora, PeticionLocal peticionLocal) throws CreateException, FinderException, ATiempoAppEx, Exception{
		TR029E tr029e = new TR029E();
		
		
		Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));

        /* Ingresa los datos del Encabezado de Agenda*/
        tr029e.setId(idCorrelativoMensaje.toString());
        tr029e.setSource("ATIEMPO");
        tr029e.setInterfaz("ENVIO_EQUIPOS_MM_SAP");
        tr029e.setDestination("ESB");
        tr029e.setVersion("1.0");
        
        /*Ingresa los datos de fecha de ejecucin de la interfaz*/
        Fecha fechaEnvioInterfaz = new Fecha();
        tr029e.setExecutionDate(fechaEnvioInterfaz.getYYYYMMDD());
        tr029e.setExecutionTime(fechaEnvioInterfaz.getHHMMSS());
        
        Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
        
        /*Validacion de la fecha de cierre contable de cada mes, parametrizable en la base de datos*/
        try{
        	/*Se obtiene propiedad con dia de inicio para el cierre contable de SAP de todos los meses*/
        	Properties_BDLocal propertiesBDLocalDiaCierre = propertiesBDLocalHome.findByPrimaryKey("DIA_INICIO_CIERRE_SAP");
    		
    		int diaIniCierreCont = Integer.parseInt(propertiesBDLocalDiaCierre.getValor());
            String fechaContabilizacionStr = fechaEnvioInterfaz.getYYYYMMDD();
            if(fechaContabilizacionStr != null){
            	int day = Integer.parseInt(fechaContabilizacionStr.substring(6));
            	int month = Integer.parseInt(fechaContabilizacionStr.substring(4,6));
            	int year = Integer.parseInt(fechaContabilizacionStr.substring(0,4));
            	
            	if(day >= diaIniCierreCont){
            		String dayNewStr = "01";
            		
            		String monthNewStr = "";
            		int monthNew = month + 1;
            		int yearNew = year;
            		if(monthNew >= 13){
            			monthNewStr = "01";
            			yearNew++;
            		}else if(monthNew <= 9){
            			monthNewStr = "0" + String.valueOf(monthNew);
            		}else{
            			monthNewStr = String.valueOf(monthNew);
            		}
            		String yearNewStr = String.valueOf(yearNew);
            		String fechaContNew = yearNewStr + monthNewStr + dayNewStr;
            		tr029e.setPostingDate(fechaContNew);
            	}else{
            		tr029e.setPostingDate(fechaEnvioInterfaz.getYYYYMMDD());
            	}
            }
        }catch(Exception e){
        	tr029e.setPostingDate(fechaEnvioInterfaz.getYYYYMMDD());
        }
		
        tr029e.setAtiempoRequestNumber(nroPeticion.longValue());	      	      

        /*obtiene el id de la peticion Atis*/
        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
		
		if(pkAtis.cod_pet_cd != null)
			tr029e.setAtisRequestNumber(pkAtis.cod_pet_cd.toString());
		else
			tr029e.setAtisRequestNumber("");
        
		Collection equipos = peticionLocal.getElemento_peticion() != null ? peticionLocal.getElemento_peticion() : new ArrayList();
		
		/*Se obtiene propiedad con PSs para los que se debe enviar a SAP*/
		Properties_BDLocal propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey("PS_ENVIO_EQUIPOS_SAP");
		
		String psEnvioSAPStr = propertiesBDLocal.getValor();
		String[] listaPsEnvioSAP = null;
		
		ArrayList listEnvioSapPS = new ArrayList();
		if(psEnvioSAPStr != null && !psEnvioSAPStr.equals("")){
			listaPsEnvioSAP = psEnvioSAPStr.split(",");			
			
			for(int i=0;i<listaPsEnvioSAP.length;i++){
				listEnvioSapPS.add(listaPsEnvioSAP[i]);
			}			
				
	        int contadorPosiciones = 1;
	        Collection posiciones = new ArrayList();
	        Collection seriales = new ArrayList();
			TR029EPosition tr029ePosition = new TR029EPosition();
	        for (Iterator iter = equipos.iterator(); iter.hasNext();) {
	            Elemento_PeticionLocal elementoLocal = (Elemento_PeticionLocal) iter.next();
	            
	            /*Si el PS asociado al equipo esta dentro de los configurados para envio, se agrega al mensaje*/
	            if(elementoLocal != null 
	            		&& elementoLocal.getPs_id() != null 
	            		&& listEnvioSapPS.contains(elementoLocal.getPs_id().toString())){
	            	
	            	if(posiciones.size() <= 0){
		            	tr029ePosition = new TR029EPosition();
		            	seriales = new ArrayList();
		            	tr029ePosition.setSerials(seriales);
		            	
		            	tr029ePosition.setPosDocMat(Integer.toString(contadorPosiciones));
		            	contadorPosiciones++;
		            	
		            	tr029ePosition.setMatQuantity("0");
		            	
		            	if(peticionLocal != null && peticionLocal.getCod_sgm_cta_cd() != null)
		    				tr029ePosition.setSegment(peticionLocal.getCod_sgm_cta_cd().toString());
		    			else
		    				tr029ePosition.setSegment("");
		            	
		            	asignarDatosPosicionMensajeSAP(elementoLocal, tr029ePosition);
		            	
		            	posiciones.add(tr029ePosition);
		            }else{
		            	boolean encontro = false;
		            	for (Iterator it = posiciones.iterator(); it.hasNext();) {
		            		TR029EPosition posicion = (TR029EPosition) it.next();
		            		
		            		if(posicion.getMaterial().equals(elementoLocal.getNum_material_sap())
		            				&& posicion.getPlant().equals(elementoLocal.getCentro_sap())
									&& posicion.getStorage().equals(elementoLocal.getAlmacen_sap())
									&& posicion.getNumPurchasingDoc().equals(elementoLocal.getNum_doc_sap())
									&& posicion.getNumPositionPurchDoc() == elementoLocal.getPos_doc_sap()){
		            			String cantMaterialStr = posicion.getMatQuantity();
		            	        int canMatNum = Integer.parseInt(cantMaterialStr) + 1;
		            	        
		            	        posicion.setMatQuantity(Integer.toString(canMatNum));
		            	        
		            	        if(elementoLocal != null && elementoLocal.getSerial() != null){
		            	        	if(elementoLocal.getSerial().length() > 18){
		            	        		posicion.getSerials().add(elementoLocal.getSerial().substring(0,18));
		            	        	}else{
		            	        		posicion.getSerials().add(elementoLocal.getSerial());
		            	        	}               
		            	        }
		            	        encontro = true;
		            		}                        
		            	}
		            	
		            	if(!encontro){
		            		tr029ePosition = new TR029EPosition();
		                	seriales = new ArrayList();
		                	tr029ePosition.setSerials(seriales);
		                	
		                	tr029ePosition.setPosDocMat(Integer.toString(contadorPosiciones));
		                	contadorPosiciones++;
		                	
		                	tr029ePosition.setMatQuantity("0");
		                	
		                	if(peticionLocal != null && peticionLocal.getCod_sgm_cta_cd() != null){                    		
		                		String segmentoStr = peticionLocal.getCod_sgm_cta_cd().toString();
		                		if(segmentoStr.length() > 10){
		                			segmentoStr = segmentoStr.substring(0,10);
		                		}
		                		
		        				tr029ePosition.setSegment(segmentoStr);
		                	}else{
		        				tr029ePosition.setSegment("");
		                	}
		                	
		                	asignarDatosPosicionMensajeSAP(elementoLocal, tr029ePosition);
		                	
		                	posiciones.add(tr029ePosition);
		            	}
		            }
	            }	             
	        }
	        tr029e.setPositions(posiciones);
		}

		
		
		/**/
		Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
        msgLocal.setPeticion(peticionLocal);
        msgLocal.setCod_actividad_generadora(actGeneradora);
        msgLocal.setFecha_envio(df.format(new java.util.Date()));
        msgLocal.setMensaje_estado(mensajeOk);
		
		EnviarSAPInfoEquiposMQ enviarSAPInfEqMQ = new EnviarSAPInfoEquiposMQ();
		enviarSAPInfEqMQ.enviarMensaje(tr029e);
	}
	
	/**
	 * Procedimiento privado para asignar los datos de la posicion a la TR029E que se enviar� 
	 * @author fmendez 
	 * @param elementoLocal
	 * @param tr029ePosition
	 */
	private void asignarDatosPosicionMensajeSAP(Elemento_PeticionLocal elementoLocal, TR029EPosition tr029ePosition){
		
		if(elementoLocal != null && elementoLocal.getNum_material_sap() != null)
        	tr029ePosition.setMaterial(elementoLocal.getNum_material_sap());
        else
        	tr029ePosition.setMaterial("");
        
        if(elementoLocal != null && elementoLocal.getCentro_sap() != null)
        	tr029ePosition.setPlant(elementoLocal.getCentro_sap());
        else
        	tr029ePosition.setPlant("");
        
        if(elementoLocal != null && elementoLocal.getAlmacen_sap() != null)
        	tr029ePosition.setStorage(elementoLocal.getAlmacen_sap());
        else
        	tr029ePosition.setStorage("");
        
        String cantMaterialStr = tr029ePosition.getMatQuantity();
        int canMatNum = Integer.parseInt(cantMaterialStr) + 1;
        
        tr029ePosition.setMatQuantity(Integer.toString(canMatNum));
        
        if(elementoLocal != null && elementoLocal.getNum_doc_sap() != null)
        	tr029ePosition.setNumPurchasingDoc(elementoLocal.getNum_doc_sap());
        else
        	tr029ePosition.setNumPurchasingDoc("");
        
        if(elementoLocal != null && elementoLocal.getPos_doc_sap() != 0){
        	String intAsStr = String.valueOf(elementoLocal.getPos_doc_sap());
        	
        	if(intAsStr.length() > 5){
        		intAsStr = intAsStr.substring(0,5);
    		}
        	tr029ePosition.setNumPositionPurchDoc(Integer.parseInt(intAsStr));
        }else{
        	tr029ePosition.setNumPositionPurchDoc(0);
        }
        
        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey();
        if(elemPeticionKey != null && elemPeticionKey.serial != null){
        	if(elemPeticionKey.serial.length() > 18){
        		tr029ePosition.getSerials().add(elemPeticionKey.serial.substring(0,18));
        	}else{
        		tr029ePosition.getSerials().add(elemPeticionKey.serial);
        	}               
        }
	}
	
	/*RQ 6895 - Interfaz Atiempo - MM SAP*/
	public void procesarRespuestaInformacionEquiposMMSAP(TR029S tr029s) throws ATiempoAppEx{
		
		BackendExecution bExecution = null;

		try {
	        bExecution = BackendTraceUtil.initExecution(tr029s, log);
	        bExecution.setNumPetAtiempo(new Long(tr029s.getAtiempoRequestNumber()));
	        bExecution.setIdMensajeOp(tr029s.getId());
	        bExecution.startOperation();
	        
	        Mensaje_estado_baLocal mensajeEstadoBa;
	        try {
	        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr029s.getId())));
	        } catch (FinderException e1) {
	        	mensajeEstadoBa = null;
	        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr029s));
	            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr029s.getId(), ATiempoAppEx.FINDER);
	        }
	        			
	        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
	    	ActividadEJBDTO actDto ;	   		    	
	    	
	    	if(tr029s != null && tr029s.getError() != null && tr029s.getError().equals("0")){
	    		/*Procesamiento de la respuesta*/
	    		/*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
//		        PeticionKey key = new PeticionKey(new Long(tr029s.getAtiempoRequestNumber()));
//		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
//		        
//		        if(tr029s.getMaterialDocument() > 0){
//		        	peticionLocal.setMat_doc_sap(new Long(tr029s.getMaterialDocument()));
//		        }
	        	
	        	/*Cierre de la actividad exitoso*/
				actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
				mensajeEstadoBa.setId_error(tr029s.getError());
				mensajeEstadoBa.setDesc_error(tr029s.getErrorMessage());
				mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
				actDto.setObservacion("Se recibe confirmaci�n del correcto env�o de la informaci�n hacia SAP.", true);
				actividadEJB.terminar (actDto);
	        }else{
	        	
	        	/*Cierre de la actividad con error*/	        	
				actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
				/*Inserci�n del quiebre*/
				insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
				
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				mensajeEstadoBa.setId_error(tr029s.getError());
				mensajeEstadoBa.setDesc_error(tr029s.getErrorMessage());
				mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
				actDto.setObservacion("Error al recibir respuesta de MM SAP (Info. Equipos), se deriva a PGI. Respuesta: "+tr029s.getError()+" - "+tr029s.getErrorMessage(), true);
				actividadEJB.terminar (actDto);
	        }
		} catch (EJBException e) {
			log.error("Error procesando la respuesta TR029S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion con id: " + tr029s.getId() +".", e);
		} catch (TnProcesoExcepcion e) {
			log.error("Error procesando la respuesta TR029S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion. con id: " + tr029s.getId() +".", e);
		} catch (NamingException e) {
			log.error("Error procesando la respuesta TR029S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion. con id: " + tr029s.getId() +".", e);
		} catch (CreateException e) {
			log.error("Error procesando la respuesta TR029S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion. con id: " + tr029s.getId() +".", e);
		} catch (FinderException e) {
			log.error("Error procesando la respuesta TR029S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion. con id: " + tr029s.getId() +".", e);
		}  catch (ATiempoAppEx e) {
			throw e;
		} finally{
			bExecution.endAll();			
		}	
	}
	
	public void recibirConfirmacionPagosTVSola(TR611S tr611s, String correlationID) throws ATiempoAppEx{
		log.debug("Entro el mensaje id: "+tr611s.getId()+" para la peticion"+tr611s.getAtiempoRequestNumber()+" y correlation id: "+correlationID+" al m�todo recibirConfirmacionPagosTVSola");
		BackendExecution bExecution = null;

		try {
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			
	        bExecution = BackendTraceUtil.initExecution(tr611s, log);
	        bExecution.setNumPetAtiempo(tr611s.getAtiempoRequestNumber());
	        bExecution.setIdMensajeOp(tr611s.getId());
	        bExecution.startOperation();
	        
	        
	        PeticionKey key = new PeticionKey(tr611s.getAtiempoRequestNumber());
	        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
	        	        
	        Mensaje_estado_baLocal mensajeEstadoBa = mensajeEstadoBaLocalHome.create(new Long(tr611s.getId()));
	        mensajeEstadoBa.setPeticion(peticionLocal);
	        mensajeEstadoBa.setCod_actividad_generadora(ComunInterfaces.COS_ACT_CONFIRMACION_PAGO_RECIBO);
	        mensajeEstadoBa.setFecha_envio(df.format(new java.util.Date()));
	        
	        if (peticionLocal.getPago_tv_sola_ok() == null && peticionLocal.getFecha_pago_tv_sola() == null){
	        	peticionLocal.setPago_tv_sola_ok(tr611s.getConfirmationFlag());
	        	peticionLocal.setFecha_pago_tv_sola(tr611s.getDate());
	        	
		        this.enviarConfirmacionPagosTVSola(tr611s, tr611s.getAtiempoRequestNumber(), tr611s.getAtisRequestNumber(), correlationID, "0", "");
	        }else{
		        this.enviarConfirmacionPagosTVSola(tr611s, tr611s.getAtiempoRequestNumber(), tr611s.getAtisRequestNumber(), correlationID, "2", "Esta petici�n ya tuvo un mensaje de confirmaci�n de pago anteriormente");
	        }
	        
        

	        
		} catch (EJBException e) {
			log.error("Error EJBException procesando la respuesta TR611S", e);
            //throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion con id: " + tr029s.getId() +".", e);
		} catch (NamingException e) {
			log.error("Error NamingException procesando la respuesta TR611S", e);
            //throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion. con id: " + tr029s.getId() +".", e);
		} catch (CreateException e) {
			log.error("Error CreateException procesando la respuesta TR611S", e);
            //throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion. con id: " + tr029s.getId() +".", e);
		} catch (FinderException e) {
			try{
				log.error("Error FinderException procesando la respuesta TR611S se env�a mensaje de error a gesrec");
				
				Mensaje_estado_baLocal mensajeEstadoBa = mensajeEstadoBaLocalHome.create(new Long(tr611s.getId()));
		        mensajeEstadoBa.setCod_actividad_generadora(ComunInterfaces.COS_ACT_CONFIRMACION_PAGO_RECIBO);
		        mensajeEstadoBa.setFecha_envio(df.format(new java.util.Date()));
		        mensajeEstadoBa.setAccion("1");
		        mensajeEstadoBa.setDesc_error("La petici�n ATIS: "+tr611s.getAtiempoRequestNumber()+" no existe en atiempo");
				
				this.enviarConfirmacionPagosTVSola(tr611s, tr611s.getAtiempoRequestNumber(), tr611s.getAtisRequestNumber(), correlationID, "1", "La petici�n ATIS"+tr611s.getAtiempoRequestNumber()+" no existe en atiempo");
				
			} catch (CreateException ex) {
				log.error("Error CreateException procesando la respuesta TR611S", ex);
	            //throw new ATiempoAppEx("Error procesando la respuesta TR029S o cerrando la peticion. con id: " + tr029s.getId() +".", e);
			}
		} finally{
			bExecution.endAll();
			
		}
	}
	
	public void enviarConfirmacionPagosTVSola(TR611S tr611s, Long atiempoRequestNumber, Long atisRequestNumber, String correlationID, String error, String descError) throws ATiempoAppEx{
		try{
			TR611E tr611e = new TR611E();
			
			tr611e.setId(tr611s.getId());
			tr611e.setError(error);
			tr611e.setErrorMessage(descError);
			tr611e.setDestination(ComunInterfaces.sistemaAgendaSC);
			tr611e.setSource(ComunInterfaces.sistemaAtiempo);
			tr611e.setInterfaz("CONFIG_TV_SOLA");
			tr611e.setVersion("1.0");
			
			tr611e.setAtiempoRequestNumber(atiempoRequestNumber);
			tr611e.setAtisRequestNumber(atisRequestNumber);
					
			ConfirmacionPagoReciboMQ confirmacionPagoRecibo = new ConfirmacionPagoReciboMQ();
			log.debug("Envio mensaje de Tv sola con id:"+tr611e.getId()+"y correlation id:"+correlationID);
			confirmacionPagoRecibo.enviarMensaje(tr611e, correlationID);
			
			if (error.equals("0")){
				PeticionKey key = new PeticionKey(tr611s.getAtiempoRequestNumber());
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
		        
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(ComunInterfaces.COS_ACT_CONFIRMACION_PAGO_RECIBO);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(key.peti_numero, ComunInterfaces.COS_ACT_CONFIRMACION_PAGO_RECIBO);
				
				Mensaje_estado_baLocal msgMoLocal = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long (tr611s.getId())));
				
				if(tr611s != null && tr611s.getConfirmationFlag() != null && !tr611s.getConfirmationFlag().equalsIgnoreCase(ComunInterfaces.INDICADOR_PAGO_OK)){
					boolean esQW = validarQW(peticionLocal);
					insertarCausalesCnaPeticion(msgMoLocal.getPeticion(),msgMoLocal.getCod_actividad_generadora(),new Long(768),actDto.getIdActividadFlujo());

					Mensaje_estadoLocal mensajeError = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
					msgMoLocal.setMensaje_estado(mensajeError);
			        
					msgMoLocal.setFecha_cierre(df.format(new java.util.Date()));
					if(!esQW){
						actDto.setObservacion("No se recibe respuesta de confirmacion de pago, el plazo de pago de factura venci� el d�a "+tr611s.getDate()+"."+tr611s.getDescription(), true);
						actividadEJB.terminar (actDto);
					}else{
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGF"));
						actDto.setObservacion("No se recibe respuesta de confirmacion de pago, el plazo de pago de factura venci� el d�a "+tr611s.getDate()+"."+tr611s.getDescription(), true);
						actividadEJB.terminar (actDto);
					}
				}else{
					Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
					msgMoLocal.setMensaje_estado(mensajeOk);
					
					msgMoLocal.setFecha_cierre(df.format(new java.util.Date()));
			        actDto.setObservacion("Se recibe respuesta de confirmacion de pago sin error. El pago se efectu� el d�a:"+tr611s.getDate()+"."+tr611s.getDescription(), true);
			        actividadEJB.terminar (actDto);
				}
			}else{
				Mensaje_estado_baLocal msgMoLocal = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long (tr611s.getId())));
				Mensaje_estadoLocal mensajeError = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				msgMoLocal.setMensaje_estado(mensajeError);
			}
		}catch (CreateException e) {
			log.error("Error CreateException procesando la respuesta TR611E", e);
		} catch (FinderException e) {
			log.error("Error FinderException procesando la respuesta TR611E", e);
		} catch (TnProcesoExcepcion e) {
			log.error("Error TnProcesoExcepcion procesando la respuesta TR611E", e);
		} catch (NamingException e) {
			log.error("Error NamingException procesando la respuesta TR611E", e);
		}
		

	}
	
	/**
	 * Indica si se esta realizando una venta de contado de Quick Wins
	 * @param peticionLocal
	 * @return
	 */
	private boolean validarQW(PeticionLocal peticionLocal) {
		Collection peticionFlujoList = peticionLocal.getPeticion_flujo();
		for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
			if(peticionFlujoLocal.getFk_opco_2_pefl().getOpco_nombre().equals(ComunInterfaces.FLUJO_VENTA_EQUIPO_CONTADO)){
				return true;
			}
		}
		return false;
	}

	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaVistaGlobal(TR025S tr025s) throws ATiempoAppEx{
		
		BackendExecution bExecution = null;

		try {
	        bExecution = BackendTraceUtil.initExecution(tr025s, log);
	        bExecution.setNumPetAtiempo(new Long(tr025s.getAtiempoRequestNumber()));
	        bExecution.setIdMensajeOp(tr025s.getId());
	        bExecution.startOperation();
	        
	        Mensaje_estado_baLocal mensajeEstadoBa;
	        try {
	        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr025s.getId())));
	        } catch (FinderException e1) {
	        	mensajeEstadoBa = null;
	        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr025s));
	            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr025s.getId(), ATiempoAppEx.FINDER);
	        }
	        			
	        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
	    	ActividadEJBDTO actDto ;	   		    	
	    	
	    	if(tr025s != null && tr025s.getError() != null && tr025s.getError().equals("0")){
	    		/*obtiene la peticion Local de acuerdo al numero atiempo que llega en el mensaje*/	    		
		        PeticionKey key = new PeticionKey(new Long(tr025s.getAtiempoRequestNumber()));
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
		        		        
		        /*Analisis de los MODEMS (Modem) para marcar los que se deben enviar a SAP*/
		        Collection mBD = peticionLocal.getModem() != null ? peticionLocal.getModem() : new ArrayList(); //Modem de la petici�n en BD
		        String modemSerialTr = tr025s.getModemSerial(); //Serial del modem de la vista global, en la tr
		        
	        	for (Iterator modemsBdIter = mBD.iterator(); modemsBdIter.hasNext();){
	        		ModemLocal modemLocal = (ModemLocal) modemsBdIter.next();
					ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
					
					/*Se valida si el modem se debe enviar a SAP o no. Se adiciona la validacion del nuevo flag flag_mat_sap 
					 * que debe llegar de Agenda*/
					if(modemKey.serial != null && 
							modemSerialTr == null &&
							(modemLocal.getAccion().intValue() == ComunInterfaces.accionParActivar 
									|| modemLocal.getAccion().intValue() == ComunInterfaces.accionModemConfigurado)&&
							modemLocal.getFlag_mat_sap() != null &&
							modemLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
						
						modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
					}else if(modemKey.serial != null && 
							modemSerialTr != null && 
							!modemKey.serial.equals(modemSerialTr) &&
							(modemLocal.getAccion().intValue() == ComunInterfaces.accionParActivar 
									|| modemLocal.getAccion().intValue() == ComunInterfaces.accionModemConfigurado) &&
							modemLocal.getFlag_mat_sap() != null &&
							modemLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
						
						modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
					}		        	
	        	}		        	
		        	
		        /*Analisis de los EQUIPOS (Elemento_Peticion) para marcar los que se deben enviar a SAP*/
		        Collection ePBD = peticionLocal.getElemento_peticion() != null ? peticionLocal.getElemento_peticion() : new ArrayList(); //Elemento_Peticion de la petici�n eb BD
		        Collection ePTr = tr025s.getEquipments() != null ? tr025s.getEquipments() : new ArrayList(); //Elemento_Peticion de la vista global, en la tr
		        
		        boolean elementoPeticionEstaEnHC = false;
		        for(Iterator elPetBdIter = ePBD.iterator();elPetBdIter.hasNext();){
		        	Elemento_PeticionLocal elemPetLocal = (Elemento_PeticionLocal) elPetBdIter.next();
		        	Elemento_PeticionKey elemPetKey = (Elemento_PeticionKey) elemPetLocal.getPrimaryKey();
		        	elementoPeticionEstaEnHC = false;
		        	forTr: for(Iterator elPetTrIter = ePTr.iterator();elPetTrIter.hasNext();){
		        		TR025SEquipment equipoTr = (TR025SEquipment) elPetTrIter.next();
		        		if(elemPetKey.serial.equals(equipoTr.getElementSerial())){
		        			elementoPeticionEstaEnHC = true;
		        			break forTr;
		        		}
		        	}
		        	/*Se valida si el Elemento_Peticion se debe enviar a SAP o no*/
		        	if(!elementoPeticionEstaEnHC &&
		        			elemPetLocal.getAccion().intValue() == ComunInterfaces.accionParActivar &&
		        			elemPetLocal.getFlag_mat_sap() != null && 
							elemPetLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        		
		        		elemPetLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
		        	}
		        }
		        
		        /*Analisis de los DECOS Y TARJETAS (Deco_tarjeta) para marcar los que se deben enviar a SAP*/
		        Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome = (Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
		        Collection dTBD = peticionLocal.getDeco_tarjeta() != null ? peticionLocal.getDeco_tarjeta() : new ArrayList(); //Deco_Tarjeta de la petici�n en BD		        
		        Collection dTTr = tr025s.getDecosCards() != null ? tr025s.getDecosCards() : new ArrayList(); //Deco_Tarjeta de la vista global, en la tr 		    

		        boolean decoTarjetaEstaEnHC = false;
		        for(Iterator decTarBdIter = dTBD.iterator();decTarBdIter.hasNext();){
		        	Deco_tarjetaLocal decoTarjLocal = (Deco_tarjetaLocal) decTarBdIter.next();
		        	
		        	/*Busqueda y validacion del flag_mat_sap para el DECO*/
		        	boolean esDecoSAP = false;
		        	try{
		        		Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco, new Long(tr025s.getAtiempoRequestNumber()));
		        		Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
		        		
		        		if(infoSAPDeco != null &&
		        				infoSAPDeco.getFlag_mat_sap() != null &&
		        				infoSAPDeco.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        			
		        			esDecoSAP = true;
		        		}else{
		        			esDecoSAP = false;
		        			log.debug("El Deco asociado al Id: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco+", y a la peticion: "+tr025s.getAtiempoRequestNumber()+", no tiene el FLAG_MAT_SAP o no es un equipo de SAP.");
		        		}
		        	}catch(FinderException e){
		        		log.debug("No se encontro ningun Deco asociado al Id: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco+", y a la peticion: "+tr025s.getAtiempoRequestNumber());
		        		esDecoSAP = false;
		        	}catch(Exception e){
		        		log.debug("EquipoBean: procesarRespuestaVistaGlobal: Exception: Error desconocido intentando obtener datos del Deco: "+ e);
		        		esDecoSAP = false;
		        	}
		        	
		        	/*Busqueda y validacion del flag_mat_sap para la TARJETA*/
		        	boolean esTarjetaSAP = false;
		        	try{		        		
		        		Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta, new Long(tr025s.getAtiempoRequestNumber()));
		        		Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
		        		
		        		if(infoSAPCard != null &&
		        				infoSAPCard.getFlag_mat_sap() != null &&
		        				infoSAPCard.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        			
		        			esTarjetaSAP = true;
		        		}else{
		        			esTarjetaSAP = false;
		        			log.debug("La tarjeta asociado al Id: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta+", y a la peticion: "+tr025s.getAtiempoRequestNumber()+", no tiene el FLAG_MAT_SAP o no es un equipo de SAP.");
		        		}
		        	}catch(FinderException e){
		        		log.debug("No se encontro ninguna Tarjeta asociado al Id: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta+", y a la peticion: "+tr025s.getAtiempoRequestNumber());
		        		esTarjetaSAP = false;
		        	}catch(Exception e){
		        		log.debug("EquipoBean: procesarRespuestaVistaGlobal: Exception: Error desconocido intentando obtener datos de la tarjeta: "+ e);
		        		esTarjetaSAP = false;
		        	}
		        	
		        	/*Solo se puede marcar con el FLAG_ENVIO_EQUIPO_SAP si el Deco es de SAP o la tarjeta es de SAP*/
		        	if(esDecoSAP || esTarjetaSAP){
		        		
		        		Deco_tarjetaKey decoTarjKey = (Deco_tarjetaKey) decoTarjLocal.getPrimaryKey();
			        	decoTarjetaEstaEnHC = false;
			        	forTrDT: for(Iterator decoTarjTrIter = dTTr.iterator();decoTarjTrIter.hasNext();){
			        		TR025SDecoCard decoCardTr = (TR025SDecoCard) decoTarjTrIter.next();
			        		if (decoTarjKey.id_deco.equals(decoCardTr.getDecoCasId()) && decoTarjKey.id_tarjeta.equals(decoCardTr.getCardSerial())){
			        			decoTarjetaEstaEnHC = true;
			        			break forTrDT;
			        		}
			        	}
			        	
			        	/*Se valida si el Par Deco Tarjeta se debe enviar a SAP.
			        	 * En la notificaci�n SAP se validan por separado el Deco y la Tarjeta ya que cada uno tendra un FLAG_MAT_SAP*/
			        	if(!decoTarjetaEstaEnHC && decoTarjLocal.getAccion().intValue() == ComunInterfaces.accionParActivar){
			        		
			        		decoTarjLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
			        	}
		        	}else{
		        		log.debug("En la vista global, ni el Deco ni la Tarjeta son de SAP por lo cual no se marca el Deco-Tarjeta para el envio a SAP.");
		        	}
		        }

	        	/*Cierre de la actividad exitoso*/
				actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
				mensajeEstadoBa.setId_error(tr025s.getError());
				mensajeEstadoBa.setDesc_error(tr025s.getErrorMessage());
				mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
				actDto.setObservacion("Se recibe correctamente la informaci�n de la Vista Global.", true);
				actividadEJB.terminar (actDto);
	        }else{	        	
	        	/*Cierre de la actividad con error*/	        	
				actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
				/*Inserci�n del quiebre*/
				insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
				
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				mensajeEstadoBa.setId_error(tr025s.getError());
				mensajeEstadoBa.setDesc_error(tr025s.getErrorMessage());
				mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
				actDto.setObservacion("Error al recibir la informaci�n de la Vista Global, se deriva a PGI. Respuesta: "+tr025s.getError()+" - "+tr025s.getErrorMessage(), true);
				actividadEJB.terminar (actDto);
	        }
		} catch (EJBException e) {
			log.error("EJBException: Error procesando la respuesta TR025S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S o cerrando la peticion con id: " + tr025s.getId() +".", e);
		} catch (TnProcesoExcepcion e) {
			log.error("TnProcesoExcepcion: Error procesando la respuesta TR025S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S o cerrando la peticion. con id: " + tr025s.getId() +".", e);
		} catch (NamingException e) {
			log.error("NamingException: Error procesando la respuesta TR025S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S o cerrando la peticion. con id: " + tr025s.getId() +".", e);
		} catch (CreateException e) {
			log.error("CreateException: Error procesando la respuesta TR025S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S o cerrando la peticion. con id: " + tr025s.getId() +".", e);
		} catch (FinderException e) {
			log.error("FinderException: Error procesando la respuesta TR025S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR025S o cerrando la peticion. con id: " + tr025s.getId() +".", e);
		}  catch (ATiempoAppEx e) {
			throw e;
		} finally{
			bExecution.endAll();			
		}		
	}
	
	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaNotificacionVentaMinoristasSAP(TR020S tr020s) throws ATiempoAppEx{
		
		BackendExecution bExecution = null;
		String estado = "Cerrado";
		Collection elementosSAP;

		try {
	        bExecution = BackendTraceUtil.initExecution(tr020s, log);
	        String strNumATconATM = tr020s.getAtiempoRequestNumber();
	        bExecution.setNumPetAtiempo(new Long(strNumATconATM.substring(3)));
	        bExecution.setIdMensajeOp(tr020s.getId());
	        bExecution.startOperation();
	        
	        
	        
	        Long correlativo= new Long(tr020s.getId());
	        Mensaje_estado_baLocal mensajeEstadoBa;
	        
	        Mensaje_descarga_SAPLocal mensaje_descarga_SAPLocal;
	        mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome)HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
	        
	        mensajeDescargaSAPLocalHome = (Mensaje_descarga_SAPLocalHome)HomeFactory.getHome(Mensaje_descarga_SAPLocalHome.JNDI_NAME);
	       
	        try {
	        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(correlativo));
	        	elementosSAP =mensajeDescargaSAPLocalHome.findByIdPeti(new Long(strNumATconATM.substring(3)));
	        	
	        } catch (FinderException e1) {
	        	mensajeEstadoBa = null;
	        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr020s));
	            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr020s.getId(), ATiempoAppEx.FINDER);
	        }
	        		
	         
	        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
	    	ActividadEJBDTO actDto ;	   		    	
	    	
	    	if(tr020s != null && tr020s.getError() != null && tr020s.getError().equals("0")){
	    		/*Procesamiento de la respuesta - No se debe procesar nada, solo cerrar la actividad*/
	        	/*Cierre de la actividad exitoso*/
	    		Mensaje_estadoLocal mensajeOK = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				mensajeEstadoBa.setMensaje_estado(mensajeOK);
				
				actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
				mensajeEstadoBa.setId_error(tr020s.getError());
				mensajeEstadoBa.setDesc_error(tr020s.getErrorMessage());
				mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
				
				for(Iterator iterElemSAP = elementosSAP.iterator(); iterElemSAP.hasNext();){
					mensaje_descarga_SAPLocal = (Mensaje_descarga_SAPLocal) iterElemSAP.next();
					mensaje_descarga_SAPLocal.setEst_pedido(estado);
					mensaje_descarga_SAPLocal.setFecha(tr020s.getFechaEjecucionSAP());
					mensaje_descarga_SAPLocal.setMotivo(tr020s.getErrorMessage());
					mensaje_descarga_SAPLocal.setDoc_mat(tr020s.getDocumentoMaterial());
					mensaje_descarga_SAPLocal.setFecha_doc(tr020s.getFechaDocumento());
					mensaje_descarga_SAPLocal.setHora_eje_inter(tr020s.getHoraEjecucionInterfaz());
				}
				
//				se almacena el mensaje que se envia a SAP en la tabla TMP_NOTIFICACION_SAP
	        	Long idCorrelativo = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_NOTIFICACION_SAP"));
	        	
	        	//se obtiene la fecha actual
	        	Date date = new Date();
	        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
	        	String fecha = sdf.format(date); 
	        	
	        	PeticionLocal petLocal = mensajeEstadoBa.getPeticion();
	        	Peticion_atisLocal petAtisLocal = petLocal.getFk_01_pet_atis();
	            Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();	
	            
	        	Tmp_Notificacion_SAPLocalHome mensajeHome = (Tmp_Notificacion_SAPLocalHome) HomeFactory.getHome(Tmp_Notificacion_SAPLocalHome.JNDI_NAME);
	        	Tmp_Notificacion_SAPLocal mensajeLocal = mensajeHome.create(idCorrelativo);
	        	mensajeLocal.setPeti_numero(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero);
	        	mensajeLocal.setCod_pet_cd(pkAtis.cod_pet_cd);
	        	mensajeLocal.setFec_ingreso( Timestamp.valueOf(fecha));
	        	mensajeLocal.setMensaje(XMLUtilities.marshall (tr020s));
				
				actDto.setObservacion("Se recibe confirmaci�n del correcto env�o de la informaci�n de ventas minoristas hacia SAP.", true);
				actividadEJB.terminar (actDto);
	        }else{	        	
	        	/*Cierre de la actividad con error*/	        	
				actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
				/*Inserci�n del quiebre*/
				insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
				
				//Se obtiene variable de error
				Mensaje_estadoLocal mensajeError = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				mensajeEstadoBa.setMensaje_estado(mensajeError);
				
				
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				mensajeEstadoBa.setId_error(tr020s.getError());
				mensajeEstadoBa.setDesc_error(tr020s.getErrorMessage());
				mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
				actDto.setObservacion("Error al recibir respuesta del env�o de la informaci�n de ventas minoristas hacia SAP, se deriva a PGI. Respuesta: "+tr020s.getError()+" - "+tr020s.getErrorMessage(), true);
				actividadEJB.terminar (actDto);
				
				for(Iterator iterElemSAP = elementosSAP.iterator(); iterElemSAP.hasNext();){
					mensaje_descarga_SAPLocal = (Mensaje_descarga_SAPLocal) iterElemSAP.next();
					mensaje_descarga_SAPLocal.setFecha(tr020s.getFechaEjecucionSAP());
					mensaje_descarga_SAPLocal.setMotivo(tr020s.getErrorMessage());
					mensaje_descarga_SAPLocal.setDoc_mat(tr020s.getDocumentoMaterial());
					mensaje_descarga_SAPLocal.setFecha_doc(tr020s.getFechaDocumento());
					mensaje_descarga_SAPLocal.setHora_eje_inter(tr020s.getHoraEjecucionInterfaz());
				}
	        }
		} catch (EJBException e) {
			log.error("Error procesando la respuesta TR020S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR020S o cerrando la peticion con id: " + tr020s.getId() +".", e);
		} catch (TnProcesoExcepcion e) {
			log.error("Error procesando la respuesta TR020S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR020S o cerrando la peticion. con id: " + tr020s.getId() +".", e);
		} catch (NamingException e) {
			log.error("Error procesando la respuesta TR020S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR020S o cerrando la peticion. con id: " + tr020s.getId() +".", e);
		} catch (CreateException e) {
			log.error("Error procesando la respuesta TR020S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR020S o cerrando la peticion. con id: " + tr020s.getId() +".", e);
		} catch (FinderException e) {
			log.error("Error procesando la respuesta TR020S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR020S o cerrando la peticion. con id: " + tr020s.getId() +".", e);
		}  catch (ATiempoAppEx e) {
			throw e;
		} finally{
			bExecution.endAll();		
		}		
	}
	
	/*RQ.8595 - mfmendez*/
	public void enviarSolicitudVistaGlobal(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		try{
		
			log.debug("Ingresa a enviarSolicitudVistaGlobal, para la petici�n Atiempo No. "+nroPeticion);
			Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
	        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
	        
	        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
	        PeticionKey key = new PeticionKey(nroPeticion);
	        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
	        
	        if(peticionLocal.getTica_id() != null && !peticionLocal.getTica_id().equals("A")){
	        	
	        	TR025E object = new TR025E();
		        /*Datos del encabezado*/
				object.setId(idCorrelativoMensaje.toString());
				object.setDestination("ESB");
				object.setSource(ComunInterfaces.sistemaAtiempo);
				object.setInterfaz(ComunInterfaces.INTERFAZ_VPI_VISTA_GLOBAL);
				object.setVersion("1.0");		
				
				/*Datos del body*/
				object.setAtiempoRequestNumber(nroPeticion.longValue());	      	      
		        /*obtiene el id de la peticion Atis*/
		        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
				
				if(pkAtis.cod_pet_cd != null)
					object.setAtisRequestNumber(pkAtis.cod_pet_cd.longValue());
				else
					object.setAtisRequestNumber(0);
					
				if(peticionLocal.getNum_ide_nu_stb() != null)
					object.setPhoneNumber(peticionLocal.getNum_ide_nu_stb());
				else
					object.setPhoneNumber("");
				
				if(peticionLocal.getNum_ide_nu_tv() != null)
					object.setIdPC(peticionLocal.getNum_ide_nu_tv());
				else if(peticionLocal.getNum_ide_nu_ic() != null)
					object.setIdPC(peticionLocal.getNum_ide_nu_ic());
				else
					object.setIdPC("");
				
				/*VALIDACION DE SI EXISTEN EQUIPOS ASOCIADOS A LA PETICION. SI NO HAY NINGUNO SE INHIBE LA ACTIVIDAD*/
				 /*Valida si la petici�n tiene equipos(Elemento_Peticion) configurados para la petici�n*/
				boolean tieneElementoPeticion = true;
				Collection equipos = peticionLocal.getElemento_peticion();
		        if (equipos == null || (equipos != null && equipos.size() == 0)) {
		            log.debug("INFO: No existen Equipos(Elemento_Peticion) Asociados a la Peticion: "+ nroPeticion);
		            tieneElementoPeticion = false;	            
		        }
		        /*Valida si la petici�n tiene decos y tarjetas (Deco_tarjeta) configurados para la petici�n*/
				boolean tieneDecoTarjeta = true;
				Collection decosTarjetas = peticionLocal.getDeco_tarjeta();
		        if (decosTarjetas == null || (decosTarjetas != null && decosTarjetas.size() == 0)) {
		            log.debug("INFO: No existen Decos y Tarjetas (Deco_Tarjeta) Asociados a la Peticion: "+ nroPeticion);
		            tieneDecoTarjeta = false;	            
		        }
		        /*Valida si la petici�n tiene decos y tarjetas (Deco_tarjeta) configurados para la petici�n*/
				boolean tieneModem= true;
				Collection modems = peticionLocal.getModem();
		        if (modems == null || (modems != null && modems.size() == 0)) {
		            log.debug("INFO: No existen modems (Modem) Asociados a la Peticion: "+ nroPeticion);
		            tieneModem = false;	            
		        }
		        /**/
		        if(!tieneElementoPeticion && !tieneDecoTarjeta && !tieneModem){
		        	log.error("Se inhibe la actividad de Vista Global VPI por no tener ningun equipo (equipo, deco-tarjeta, modem), para la petici�n: "+ nroPeticion);				
					act.setObservacion("Se inhibe la actividad por no tener configurado ningun equipo.", true);
					act.setRealizarTerminoInmediato(true);
		        }else{
		        	if((object.getPhoneNumber() != null && !object.getPhoneNumber().equals("")) || 
		        			(object.getIdPC() != null && !object.getIdPC().equals(""))){
		        		
		        		VistaGlobalMQ vistaGlobal = new VistaGlobalMQ();
				        vistaGlobal.enviarMensaje(object);
				        
						Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
				        msgLocal.setPeticion(peticionLocal);
				        msgLocal.setCod_actividad_generadora(actGeneradora);
				        msgLocal.setFecha_envio(df.format(new java.util.Date()));
				        msgLocal.setMensaje_estado(mensajeOk);			        			 
					}else{
						log.error("Error al enviar la solicitud de Vista Global de VPI por no tener n�mero de abonado ni idPC, para la petici�n: "+ nroPeticion +". Se deriva a PGI.");
						insertarCausalesCnaPeticion(peticionLocal,actGeneradora,/*Quiebre por defecto*/new Long(701),act.getIdActividadFlujo());
						
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
						act.setObservacion("Error al enviar la solicitud de Vista Global por no contener los datos necesarios para la consulta, se deriva a PGI.", true);
						act.setRealizarTerminoInmediato(true);
					}
		        }
	        } else{
	        	log.debug("Se inhibe la actividad porque en la tabla petici�n en el campo tica_id tiene A, que indica que la petici�n es de alta.");
	        	
	        	/*LOGICA PARA MARCAR TODOS LOS EQUIPOS PARA ENVIAR A SAP*/
	        	/*Analisis de los modems (Modem) para marcar los que se deben enviar a SAP*/
		        Collection mBD = peticionLocal.getModem() != null ? peticionLocal.getModem() : new ArrayList(); //Modem de la petici�n en BD
		        
	        	for (Iterator modemsBdIter = mBD.iterator(); modemsBdIter.hasNext();){
	        		ModemLocal modemLocal = (ModemLocal) modemsBdIter.next();
	        		
	        		if(modemLocal != null &&
	        				modemLocal.getFlag_mat_sap() != null &&
							modemLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
	        			
	        			modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
	        		}
	        	}	        	
		        	
		        /*Analisis de los equipos (Elemento_Peticion) para marcar los que se deben enviar a SAP*/
		        Collection ePBD = peticionLocal.getElemento_peticion() != null ? peticionLocal.getElemento_peticion() : new ArrayList(); //Elemento_Peticion de la petici�n eb BD
		        
		        for(Iterator elPetBdIter = ePBD.iterator();elPetBdIter.hasNext();){
		        	Elemento_PeticionLocal elemPetLocal = (Elemento_PeticionLocal) elPetBdIter.next();
		        	
		        	if(elemPetLocal != null &&
		        			elemPetLocal.getFlag_mat_sap() != null && 
							elemPetLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        		
		        		elemPetLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
		        	}
		        }
		        
		        /*Analisis de los decos y Tarjetas (Deco_tarjeta) para marcar los que se deben enviar a SAP*/
		        Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome = (Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
		        Collection dTBD = peticionLocal.getDeco_tarjeta() != null ? peticionLocal.getDeco_tarjeta() : new ArrayList(); //Deco_Tarjeta de la petici�n en BD		         		    

		        for(Iterator decTarBdIter = dTBD.iterator();decTarBdIter.hasNext();){
		        	Deco_tarjetaLocal decoTarjLocal = (Deco_tarjetaLocal) decTarBdIter.next();
		        	
		        	/*Busqueda y validacion del flag_mat_sap para el DECO*/
		        	boolean esDecoSAP = false;
		        	try{
		        		Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco, nroPeticion);
		        		Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
		        		
		        		if(infoSAPDeco != null &&
		        				infoSAPDeco.getFlag_mat_sap() != null &&
		        				infoSAPDeco.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        			
		        			esDecoSAP = true;
		        		}else{
		        			esDecoSAP = false;
		        			log.debug("El Deco asociado al Id: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco+", y a la peticion: "+nroPeticion.longValue()+", no tiene el FLAG_MAT_SAP o no es un equipo de SAP.");
		        		}
		        	}catch(FinderException e){
		        		log.debug("No se encontro ningun Deco asociado al Id: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_deco+", y a la peticion: "+nroPeticion.longValue());
		        		esDecoSAP = false;
		        	}catch(Exception e){
		        		log.debug("EquipoBean: procesarRespuestaVistaGlobal: Exception: Error desconocido intentando obtener datos del Deco: "+ e);
		        		esDecoSAP = false;
		        	}
		        	
		        	/*Busqueda y validacion del flag_mat_sap para la TARJETA*/
		        	boolean esTarjetaSAP = false;
		        	try{		        		
		        		Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta, nroPeticion);
		        		Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
		        		
		        		if(infoSAPCard != null &&
		        				infoSAPCard.getFlag_mat_sap() != null &&
		        				infoSAPCard.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
		        			
		        			esTarjetaSAP = true;
		        		}else{
		        			esTarjetaSAP = false;
		        			log.debug("La tarjeta asociado al Id: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta+", y a la peticion: "+nroPeticion.longValue()+", no tiene el FLAG_MAT_SAP o no es un equipo de SAP.");
		        		}
		        	}catch(FinderException e){
		        		log.debug("No se encontro ninguna Tarjeta asociado al Id: "+((Deco_tarjetaKey)decoTarjLocal.getPrimaryKey()).id_tarjeta+", y a la peticion: "+nroPeticion.longValue());
		        		esTarjetaSAP = false;
		        	}catch(Exception e){
		        		log.debug("EquipoBean: procesarRespuestaVistaGlobal: Exception: Error desconocido intentando obtener datos de la tarjeta: "+ e);
		        		esTarjetaSAP = false;
		        	}	
		        	
		        	if(decoTarjLocal != null &&
		        			(esDecoSAP || esTarjetaSAP)){
		        		
		        		decoTarjLocal.setFlag_pet_curso(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP);
		        	}
		        }
		        
	        	/*SE INHIBE LA ACTIVIDAD*/
	        	act.setObservacion("Se inhibe la actividad porque en alta no se debe ejecutar.", true);
				act.setRealizarTerminoInmediato(true);
	        }								       
		}catch (FinderException e) {
            log.error("Error al enviar solicitud de vista global.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (CreateException e) {
        	log.error("Error al enviar solicitud de vista global.", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (ATiempoAppEx e) {
			log.error("Error al enviar solicitud de vista global.", e);
            throw e;
		} catch (Exception e) {
			log.error("Error al enviar solicitud de vista global.", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		} 		
	}
	
	/*RQ.8595 - mfmendez*/
	public void enviarInformacionEquiposVentaMinoristasSAP(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{	
		try{
			log.debug("Ingresa a enviarInformacionEquiposVentaMinoristasSAP, para la petici�n Atiempo No. "+nroPeticion);
					
	        Collection elemPetiSAP = null;
			Collection decoTarSAP = null;
			Collection modemSAP = null;
			Collection elemNoSerialSAP = null;
	        
			String comentariosElementos="";
	        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
	        PeticionKey key = new PeticionKey(nroPeticion);
	        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
	        
	        /*VALIDACION DE SI EXISTEN EQUIPOS ASOCIADOS A LA PETICION. SI NO HAY NINGUNO SE INHIBE LA ACTIVIDAD*/
			 /*Valida si la petici�n tiene equipos(Elemento_Peticion) configurados para la petici�n*/
			boolean tieneElementoPeticion = false;
			Collection equipos = peticionLocal.getElemento_peticion();
	        if (equipos == null || (equipos != null && equipos.size() == 0)) {
	            log.debug("INFO: No existen Equipos(Elemento_Peticion) Asociados a la Peticion: "+ nroPeticion);

	        }else{
	        	elemPetiSAP = new ArrayList();
	        	for(Iterator iterEqu = equipos.iterator();iterEqu.hasNext();){
	        		Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal) iterEqu.next();
	        		/*Se seleccionan los elemento-peticion que deben ser enviados a SAP*/
	        		if(elementoPeticionLocal != null && elementoPeticionLocal.getFlag_pet_curso() != null 
	        				&& elementoPeticionLocal.getFlag_pet_curso().equals(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP)){
	        			elemPetiSAP.add(elementoPeticionLocal);
	        			tieneElementoPeticion=true;
	        			log.debug("El equipo con PS "+elementoPeticionLocal.getPs_id()+" se enviara a SAP.");
					}else{
						log.debug("El equipo con PS "+elementoPeticionLocal.getPs_id()+" no puede enviarse a SAP.");
	        			comentariosElementos+="El equipo con PS "+elementoPeticionLocal.getPs_id()+" no puede enviarse a SAP. ";
	        		}
	        	}
	        }
	        /*Valida si la petici�n tiene decos y tarjetas (Deco_tarjeta) configurados para la petici�n*/
			boolean tieneDecoTarjeta = false;
			Collection decosTarjetas = peticionLocal.getDeco_tarjeta();
	        if (decosTarjetas == null || (decosTarjetas != null && decosTarjetas.size() == 0)) {
	            log.debug("INFO: No existen Decos y Tarjetas (Deco_Tarjeta) Asociados a la Peticion: "+ nroPeticion);            

	        }else{
	        	decoTarSAP = new ArrayList();
	        	for(Iterator iterDecTar = decosTarjetas.iterator();iterDecTar.hasNext();){
	        		Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) iterDecTar.next();
	        		/*Se seleccionan los deco-tarjeta que deben ser enviados a SAP*/
	        		if(decoTarjetaLocal != null && decoTarjetaLocal.getFlag_pet_curso() != null 
	        				&& decoTarjetaLocal.getFlag_pet_curso().equals(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP)
							&& decoTarjetaLocal.getAccion().intValue() == ComunInterfaces.accionParActivar){
	        			decoTarSAP.add(decoTarjetaLocal);
	        			tieneDecoTarjeta=true;
	        			log.debug("El deco "+decoTarjetaLocal.getCodigo_deco()+" se enviara a SAP.");
	        		}else{
	        			log.debug("El deco "+decoTarjetaLocal.getCodigo_deco()+" no puede enviarse a SAP.");
	        			comentariosElementos+="El deco "+decoTarjetaLocal.getCodigo_deco()+" no puede enviarse a SAP. ";
	        		}
	        	}
	        }
	        /*Valida si la petici�n tiene modems (Modem) configurados para la petici�n*/
			boolean tieneModem= false;
			Collection modems = peticionLocal.getModem();
	        if (modems == null || (modems != null && modems.size() == 0)) {
	            log.debug("INFO: No existen modems (Modem) Asociados a la Peticion: "+ nroPeticion);

	        }else{
	        	modemSAP = new ArrayList();
	        	for(Iterator iterMod = modems.iterator();iterMod.hasNext();){
	        		ModemLocal modemLocal = (ModemLocal) iterMod.next();
	        		/*Se seleccionan los modems que deben ser enviados a SAP*/
	        		if(modemLocal != null && modemLocal.getFlag_pet_curso() != null 
	        				&& modemLocal.getFlag_pet_curso().equals(ComunInterfaces.FLAG_ENVIO_EQUIPO_SAP)){
	        			modemSAP.add(modemLocal);
	        			tieneModem=true;
	        			log.debug("El modem "+modemLocal.getCodigo_material()+" se enviara a SAP.");
	        		}else{
	        			log.debug("El modem "+modemLocal.getCodigo_material()+" no puede enviarse a SAP.");
	        			comentariosElementos+="El modem "+modemLocal.getCodigo_material()+" no puede enviarse a SAP. ";
	        		}
	        	}
	        }
	       
	        /*Valida si la petici�n tiene elementos no serializados(ELEMENTO_NO_SERIALIZADO) configurados para la petici�n*/
			boolean tieneElementoNoSerializado = false;
			Collection elemNoSerial = peticionLocal.getElementonoserializado();
	        if (elemNoSerial == null || (elemNoSerial != null && elemNoSerial.size() == 0)) {
	            log.debug("INFO: No existen Elementos no serializados(Elemento_No_Serializado) Asociados a la Peticion: "+ nroPeticion);

	        }else{
	        	elemNoSerialSAP = new ArrayList();
	        	for(Iterator iterEleNoSerial = elemNoSerial.iterator();iterEleNoSerial.hasNext();){
	        		ElementoNoSerializadoLocal elementoNoSerializadoLocal = (ElementoNoSerializadoLocal) iterEleNoSerial.next();
	        		/*Se seleccionan los elemento_no_serializado que deben ser enviados a SAP*/
	        		if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getFlagMatSap() != null 
	        				&& elementoNoSerializadoLocal.getFlagMatSap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
	        			elemNoSerialSAP.add(elementoNoSerializadoLocal);
	        			tieneElementoNoSerializado=true;
	        			log.debug("El elemento no serializado con ID "+elementoNoSerializadoLocal.getPrimaryKey()+" no puede enviarse a SAP.");
					}else{						
						ElementoNoSerializadoKey idElemNoSerial = (ElementoNoSerializadoKey)elementoNoSerializadoLocal.getPrimaryKey();
						if(idElemNoSerial != null){
							log.debug("El elemento no serializado con ID "+idElemNoSerial.id+" no puede enviarse a SAP.");
							comentariosElementos+="El elemento no serializado con ID "+idElemNoSerial.id+" no puede enviarse a SAP. ";
						} else{
							log.debug("El elemento no serializado con ID 0 no puede enviarse a SAP.");
							comentariosElementos+="El elemento no serializado con ID 0 no puede enviarse a SAP. ";
						}							
	        		}
	        	}
	        }
	        
	        /**/
	        log.debug("tieneElementoPeticion: "+ tieneElementoPeticion);
	        log.debug("tieneDecoTarjeta: "+ tieneDecoTarjeta);
	        log.debug("tieneModem: "+ tieneModem);
	        log.debug("tieneElementoNoSerializado: "+ tieneElementoNoSerializado);
	        
	        if(!tieneElementoPeticion && !tieneDecoTarjeta && !tieneModem && !tieneElementoNoSerializado){
	        	log.error("Se inhibe la actividad de Notificaci�n SAP VPI por no tener ningun equipo (equipo, deco-tarjeta, modem, elemento_no_serializado), para la petici�n: "+ nroPeticion);				
				act.setObservacion("Se inhibe la actividad por no tener configurado ningun equipo.", true);
				act.setRealizarTerminoInmediato(true);
	        }else{
	        	if(!comentariosElementos.equals("")){
	        		act.setObservacion(comentariosElementos);
	        	}
	        	crearYEnviarMensajeVentaMinoristasSAP(act, nroPeticion, actGeneradora, peticionLocal, elemPetiSAP, decoTarSAP, modemSAP, elemNoSerialSAP);
	        }	       
		} catch (FinderException e) {
            log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (CreateException e) {
        	log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (ATiempoAppEx e) {
			log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw e;
		} catch (Exception e) {
			log.error("Error al enviar informacion de equipos a MM SAP.", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		} 
	}			
	
	/**
	 * Metodo principal para armar y enviar el mensaje de Venta Minoristas a SAP
	 * @param act
	 * @param nroPeticion
	 * @param actGeneradora
	 * @param peticionLocal
	 * @param elemPetiSAP, colecci�n con los elemento_peticion que se deben enviar a SAP
	 * @param decoTarSAP, colecci�n con los deco_tarjeta que se deben enviar a SAP
	 * @param modemSAP, colecci�n con los modem que se deben enviar a SAP
	 * @throws CreateException
	 * @throws FinderException
	 * @throws ATiempoAppEx
	 * @throws Exception
	 */
	private void crearYEnviarMensajeVentaMinoristasSAP(ActividadEJBDTO act, Long nroPeticion, 
			String actGeneradora, PeticionLocal peticionLocal, Collection elemPetiSAP,
			Collection decoTarSAP, Collection modemSAP, Collection elemNoSerialSAP) throws CreateException, FinderException, ATiempoAppEx, Exception{
		
		TR020E tr020e = new TR020E();
		boolean tieneDecosOTarjetas = false;
		boolean codMaterialElemtoPeticion= false;
		boolean codMaterialDecoTarjeta= false;
		boolean codMaterialDecoTarjeta2= false;
		boolean codMaterialModem= false;
		boolean codMaterialElementoNoSerializado= false;
		
		  /*obtiene el id de la peticion Atis*/
        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();	
        
        mensajeDescargaSAPLocalHome = (Mensaje_descarga_SAPLocalHome)HomeFactory.getHome(Mensaje_descarga_SAPLocalHome.JNDI_NAME);
		
		Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome = (Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
		Mensaje_estadoLocal mensajePendiente = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));

        /* Ingresa los datos del Encabezado de Agenda*/
        tr020e.setId(idCorrelativoMensaje.toString());
        tr020e.setSource(ComunInterfaces.sistemaAtiempo);
        tr020e.setInterfaz(ComunInterfaces.INTERFAZ_VPI_NOTIFICACION_SAP);
        tr020e.setDestination("ESB");
        tr020e.setVersion("1.0");
        if(elemPetiSAP!=null && !elemPetiSAP.isEmpty()){
        	tr020e.setFlagMatGesrec("1");
        }else{
        	tr020e.setFlagMatGesrec("0");
        }
        tr020e.setPeticionAtis(pkAtis.cod_pet_cd.toString());
        /*Ingresa los datos de fecha de ejecucin de la interfaz*/
        Fecha fechaEnvioInterfaz = new Fecha();
        tr020e.setExecutionDate(fechaEnvioInterfaz.getYYYYMMDD());
        tr020e.setExecutionTime(fechaEnvioInterfaz.getHHMMSS());
        
        Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);        
        String peticionAtiempoConATM = ComunInterfaces.INDICADOR_ATIEMPO_MENSAJE_A_SAP + nroPeticion.toString();
        String fechaContNew = new String("");
        
        /*Validacion de la fecha de cierre contable de cada mes, parametrizable en la base de datos (es un valor entero entre 1 y 31)*/
        try{
        	/*Se obtiene propiedad con dia de inicio para el cierre contable de SAP de todos los meses*/
        	Properties_BDLocal propertiesBDLocalDiaCierre = propertiesBDLocalHome.findByPrimaryKey("DIA_INICIO_CIERRE_SAP");
    		
    		int diaIniCierreCont = Integer.parseInt(propertiesBDLocalDiaCierre.getValor());
            String fechaContabilizacionStr = fechaEnvioInterfaz.getYYYYMMDD();
            if(fechaContabilizacionStr != null && !fechaContabilizacionStr.equals("")){
            	int day = Integer.parseInt(fechaContabilizacionStr.substring(6));
            	int month = Integer.parseInt(fechaContabilizacionStr.substring(4,6));
            	int year = Integer.parseInt(fechaContabilizacionStr.substring(0,4));
            	
            	if(day >= diaIniCierreCont){
            		String dayNewStr = "01";
            		
            		String monthNewStr = "";
            		int monthNew = month + 1;
            		int yearNew = year;
            		if(monthNew >= 13){
            			monthNewStr = "01";
            			yearNew++;
            		}else if(monthNew <= 9){
            			monthNewStr = "0" + String.valueOf(monthNew);
            		}else{
            			monthNewStr = String.valueOf(monthNew);
            		}
            		String yearNewStr = String.valueOf(yearNew);
            		fechaContNew = yearNewStr + monthNewStr + dayNewStr;    
            	}else{
            		fechaContNew = fechaContabilizacionStr;
            	}
            }else{
            	fechaContNew = ComunInterfaces.FECHA_CONTABILIZACION_INVALIDO;
            }
        }catch(Exception e){
        	fechaContNew = ComunInterfaces.FECHA_CONTABILIZACION_INVALIDO;
        }
		
        if(!fechaContNew.equals(ComunInterfaces.FECHA_CONTABILIZACION_INVALIDO) 
        		&& ((elemPetiSAP != null && elemPetiSAP.size() > 0)
        				|| (decoTarSAP != null && decoTarSAP.size() > 0)
						|| (modemSAP != null && modemSAP.size() > 0)
						|| (elemNoSerialSAP != null && elemNoSerialSAP.size() > 0))){
        	
        	HashMap cabecerasPeticion = new HashMap();
        	String identificadorTipoEquipo = new String("");
        	
        	/*MANEJO DE LA CARGA DE LOS DATOS DE LOS EQUIPOS - ELEMENTO_PETICION*/
        	if(elemPetiSAP != null && elemPetiSAP.size() > 0){
        		identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_ELEMENTO_PETICION_SAP;
        		
        		for(Iterator iterElemPet = elemPetiSAP.iterator(); iterElemPet.hasNext();){
        			Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal) iterElemPet.next();
        			codMaterialElemtoPeticion= true;
        			if(cabecerasPeticion.size() <= 0){
        				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
        				requestHeaderNew.setPositionsHeader(new ArrayList());
        				
        				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
        				requestHeaderNew.setAccountingDate(fechaContNew);
        				requestHeaderNew.setMoveType(elementoPeticionLocal.getClase_mov_sap());
        				
        				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, elementoPeticionLocal, null, null, null, null, requestHeaderNew);
        				
        				cabecerasPeticion.put(elementoPeticionLocal.getClase_mov_sap(), requestHeaderNew);
        			}else{
        				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(elementoPeticionLocal.getClase_mov_sap());
        				
        				if(existeCabeceraPeticion){
        					asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, elementoPeticionLocal, null, null, null, null, (TR020ERequestHeader)cabecerasPeticion.get(elementoPeticionLocal.getClase_mov_sap()));
        				}else{
        					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
        					requestHeaderNewDos.setPositionsHeader(new ArrayList());
            				
        					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
        					requestHeaderNewDos.setAccountingDate(fechaContNew);
        					requestHeaderNewDos.setMoveType(elementoPeticionLocal.getClase_mov_sap());
            				
            				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, elementoPeticionLocal, null, null, null, null, requestHeaderNewDos);
            				
            				cabecerasPeticion.put(elementoPeticionLocal.getClase_mov_sap(), requestHeaderNewDos);
        				}
        			}
        		}
        	}
        	/*MANEJO DE LA CARGA DE LOS DATOS DE LOS DECOS Y LAS TARJETAS*/
        	if(decoTarSAP != null && decoTarSAP.size() > 0){
        		
        		for(Iterator iterDecoTarj = decoTarSAP.iterator(); iterDecoTarj.hasNext();){
        			Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) iterDecoTarj.next();
        			     		
        			try{			        				
        				Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco, nroPeticion);
        				Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
        				identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_DECO_SAP;
        				codMaterialDecoTarjeta= true;
        				/*Se incluye validacion del FLAG_MAT_SAP para cada deco*/
        				if(infoSAPDeco != null &&
        						infoSAPDeco.getFlag_mat_sap() != null &&
								infoSAPDeco.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        					
        					if(cabecerasPeticion.size() <= 0){
                				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
                				requestHeaderNew.setPositionsHeader(new ArrayList());
                				
                				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
                				requestHeaderNew.setAccountingDate(fechaContNew);
                				requestHeaderNew.setMoveType(infoSAPDeco.getClase_mov_sap());
                				
                				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, infoSAPDeco, decoTarjetaLocal, null, requestHeaderNew);
                				
                				cabecerasPeticion.put(infoSAPDeco.getClase_mov_sap(), requestHeaderNew);
                			}else{
                				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(infoSAPDeco.getClase_mov_sap());
                				
                				if(existeCabeceraPeticion){
                					asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, infoSAPDeco, decoTarjetaLocal, null, (TR020ERequestHeader)cabecerasPeticion.get(infoSAPDeco.getClase_mov_sap()));
                				}else{
                					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
                					requestHeaderNewDos.setPositionsHeader(new ArrayList());
                    				
                					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
                					requestHeaderNewDos.setAccountingDate(fechaContNew);
                					requestHeaderNewDos.setMoveType(infoSAPDeco.getClase_mov_sap());
                    				
                    				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, infoSAPDeco, decoTarjetaLocal, null, requestHeaderNewDos);
                    				
                    				cabecerasPeticion.put(infoSAPDeco.getClase_mov_sap(), requestHeaderNewDos);
                				}
                			}  				
        				}        				
        			} catch (FinderException e) {
        				log.debug("No se encontraron datos de SAP para el Deco con id: "+ ((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco+", para la peticion: "+ nroPeticion.toString());
        			} catch (Exception e){
        				log.debug("No se encontraron datos de SAP para el Deco con id: "+ ((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco+", para la peticion: "+ nroPeticion.toString());
        			}

        			try{			        				
        				Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_tarjeta, nroPeticion);
        				Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
        				identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_TARJETA_SAP;
        				codMaterialDecoTarjeta2 = true;
        				
        				/*Se incluye validacion del FLAG_MAT_SAP para cada tarjeta*/
        				if(infoSAPCard != null &&
        						infoSAPCard.getFlag_mat_sap() != null &&
        						infoSAPCard.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        					
        					if(cabecerasPeticion.size() <= 0){
                				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
                				requestHeaderNew.setPositionsHeader(new ArrayList());
                				
                				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
                				requestHeaderNew.setAccountingDate(fechaContNew);
                				requestHeaderNew.setMoveType(infoSAPCard.getClase_mov_sap());
                				
                				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, infoSAPCard, decoTarjetaLocal, null, requestHeaderNew);
                				
                				cabecerasPeticion.put(infoSAPCard.getClase_mov_sap(), requestHeaderNew);
                			}else{
                				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(infoSAPCard.getClase_mov_sap());
                				
                				if(existeCabeceraPeticion){
                					asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, infoSAPCard, decoTarjetaLocal, null, (TR020ERequestHeader)cabecerasPeticion.get(infoSAPCard.getClase_mov_sap()));
                				}else{
                					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
                					requestHeaderNewDos.setPositionsHeader(new ArrayList());
                    				
                					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
                					requestHeaderNewDos.setAccountingDate(fechaContNew);
                					requestHeaderNewDos.setMoveType(infoSAPCard.getClase_mov_sap());
                    				
                    				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, infoSAPCard, decoTarjetaLocal, null, requestHeaderNewDos);
                    				
                    				cabecerasPeticion.put(infoSAPCard.getClase_mov_sap(), requestHeaderNewDos);
                				}
                			}
        				}        				
        			} catch (FinderException e) {
        				log.debug("No se encontraron datos de SAP para la Tarjeta con id: "+ ((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_tarjeta+", para la peticion: "+ nroPeticion.toString());
        			} catch (Exception e){
        				log.debug("No se encontraron datos de SAP para la Tarjeta con id: "+ ((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_tarjeta+", para la peticion: "+ nroPeticion.toString());
        			}
        		}
        	}
        	/*MANEJO DE LA CARGA DE LOS DATOS DE LOS MODEMS*/
        	if(modemSAP != null && modemSAP.size() > 0){
        		identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_MODEM_SAP;
        		
        		
        		for(Iterator iterModem = modemSAP.iterator(); iterModem.hasNext();){
        			ModemLocal modemLocal = (ModemLocal) iterModem.next();
        			codMaterialModem = true;
        			if(cabecerasPeticion.size() <= 0){
        				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
        				requestHeaderNew.setPositionsHeader(new ArrayList());
        				
        				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
        				requestHeaderNew.setAccountingDate(fechaContNew);
        				requestHeaderNew.setMoveType(modemLocal.getClase_mov_sap());
        				
        				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, modemLocal, null, null, null, requestHeaderNew);
        				
        				cabecerasPeticion.put(modemLocal.getClase_mov_sap(), requestHeaderNew);
        			}else{
        				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(modemLocal.getClase_mov_sap());
        				
        				if(existeCabeceraPeticion){
        					asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, modemLocal, null, null, null, (TR020ERequestHeader)cabecerasPeticion.get(modemLocal.getClase_mov_sap()));
        				}else{
        					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
        					requestHeaderNewDos.setPositionsHeader(new ArrayList());
            				
        					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
        					requestHeaderNewDos.setAccountingDate(fechaContNew);
        					requestHeaderNewDos.setMoveType(modemLocal.getClase_mov_sap());
            				
            				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, modemLocal, null, null, null, requestHeaderNewDos);
            				
            				cabecerasPeticion.put(modemLocal.getClase_mov_sap(), requestHeaderNewDos);
        				}
        			}
        		}
        	}        	
        	/*MANEJO DE LA CARGA DE LOS DATOS DE LOS ELEMENTOS NO SERIALIZADOS - ELEMENTO_NO_SERIALIZADO*/
        	if(elemNoSerialSAP != null && elemNoSerialSAP.size() > 0){
        		identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP;
        		
        		for(Iterator iterElemNoSer = elemNoSerialSAP.iterator(); iterElemNoSer.hasNext();){
        			ElementoNoSerializadoLocal elementoNoSerializadoLocal = (ElementoNoSerializadoLocal) iterElemNoSer.next();
        			codMaterialElementoNoSerializado = true;
        			if(cabecerasPeticion.size() <= 0){
        				TR020ERequestHeader requestHeaderNew = new TR020ERequestHeader();
        				requestHeaderNew.setPositionsHeader(new ArrayList());
        				
        				requestHeaderNew.setAtiempoRequestNumber(peticionAtiempoConATM);
        				requestHeaderNew.setAccountingDate(fechaContNew);
        				requestHeaderNew.setMoveType(elementoNoSerializadoLocal.getClaseMovSap());
        				
        				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, null, null, elementoNoSerializadoLocal, requestHeaderNew);
        				
        				cabecerasPeticion.put(elementoNoSerializadoLocal.getClaseMovSap(), requestHeaderNew);
        			}else{
        				boolean existeCabeceraPeticion = cabecerasPeticion.containsKey(elementoNoSerializadoLocal.getClaseMovSap());
        				
        				if(existeCabeceraPeticion){
        					asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, null, null, elementoNoSerializadoLocal, (TR020ERequestHeader)cabecerasPeticion.get(elementoNoSerializadoLocal.getClaseMovSap()));
        				}else{
        					TR020ERequestHeader requestHeaderNewDos = new TR020ERequestHeader();
        					requestHeaderNewDos.setPositionsHeader(new ArrayList());
            				
        					requestHeaderNewDos.setAtiempoRequestNumber(peticionAtiempoConATM);
        					requestHeaderNewDos.setAccountingDate(fechaContNew);
        					requestHeaderNewDos.setMoveType(elementoNoSerializadoLocal.getClaseMovSap());
            				
            				asignarDatosCabeceraVentaMinoristasSAP(identificadorTipoEquipo, null, null, null, null, elementoNoSerializadoLocal, requestHeaderNewDos);
            				
            				cabecerasPeticion.put(elementoNoSerializadoLocal.getClaseMovSap(), requestHeaderNewDos);
        				}
        			}
        		}
        	}

        	/*Se convierten las cabeceras en una coleccion y se asignan al mensaje*/
        	Collection arrayCabecerasPeticion = cabecerasPeticion.values();
        	
        	tr020e.setRequestsHeader(arrayCabecerasPeticion);
        	
        	//se almacena el mensaje que se envia a SAP en la tabla TMP_NOTIFICACION_SAP
        	Long idCorrelativo = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_NOTIFICACION_SAP"));
        	
        	//se obtiene la fecha actual
        	Date date = new Date();
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
        	String fecha = sdf.format(date);
        	
        	Tmp_Notificacion_SAPLocalHome mensajeHome = (Tmp_Notificacion_SAPLocalHome) HomeFactory.getHome(Tmp_Notificacion_SAPLocalHome.JNDI_NAME);
        	Tmp_Notificacion_SAPLocal mensajeLocal = mensajeHome.create(idCorrelativo);
        	mensajeLocal.setPeti_numero(nroPeticion);
        	mensajeLocal.setCod_pet_cd(pkAtis.cod_pet_cd);
        	mensajeLocal.setFec_ingreso( Timestamp.valueOf(fecha));
        	mensajeLocal.setMensaje(XMLUtilities.marshall (tr020e));
          
	        NotificacionSAPMQ envioNotificacionSAPMQ = new NotificacionSAPMQ();
	        envioNotificacionSAPMQ.enviarMensaje(tr020e);		        
            /**/
			Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
	        msgLocal.setPeticion(peticionLocal);
	        msgLocal.setCod_actividad_generadora(actGeneradora);
	        msgLocal.setFecha_envio(df.format(new java.util.Date()));
	        msgLocal.setMensaje_estado(mensajePendiente);	       		
	        
	        //Cesar Remigio: se agrega a la tabla MENSAJE_DESCARGA_SAP los campos de ID_PETI, ID_ATIS,EST_PEDIDO, FECHA_EJE_INTER y ID_COD_MAT 
	        String estado = "Pendiente";
	        if(codMaterialElemtoPeticion){
	        	for(Iterator iterElemPet = elemPetiSAP.iterator(); iterElemPet.hasNext();){
        			Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal) iterElemPet.next();
        			if(elementoPeticionLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        				materialSAP(elementoPeticionLocal.getNum_material_sap(),  tr020e, estado, fechaContNew, nroPeticion);
        			}
	        	}
	        }
	        
	        if(codMaterialDecoTarjeta){
	        	for(Iterator iterDecoTarj = decoTarSAP.iterator(); iterDecoTarj.hasNext();){
        			Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) iterDecoTarj.next();
        			Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco, nroPeticion);
    				Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
    				if(infoSAPDeco.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
    					materialSAP(infoSAPDeco.getNum_material_sap(),  tr020e, estado, fechaContNew, nroPeticion);
        			}
	        	}
	        }
	        if(codMaterialDecoTarjeta2){
	        	for(Iterator iterDecoTarj = decoTarSAP.iterator(); iterDecoTarj.hasNext();){
	        		Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) iterDecoTarj.next();
	    			Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_deco, nroPeticion);
		        	Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(((Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey()).id_tarjeta, nroPeticion);
					Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
					identificadorTipoEquipo = ComunInterfaces.IDENTIFICADOR_TARJETA_SAP;
					if(infoSAPCard.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
						materialSAP(infoSAPCard.getNum_material_sap(),  tr020e, estado, fechaContNew, nroPeticion);
        			}
	        	}
	        	
	        }
	        if(codMaterialModem){
	        	for(Iterator iterModem = modemSAP.iterator(); iterModem.hasNext();){
        			ModemLocal modemLocal = (ModemLocal) iterModem.next();
        			if(modemLocal.getFlag_mat_sap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        				materialSAP(modemLocal.getNum_material_sap(),  tr020e, estado, fechaContNew, nroPeticion);
        			}
	        	}
	        	
	        }
	        if(codMaterialElementoNoSerializado){
	        	for(Iterator iterElemNoSer = elemNoSerialSAP.iterator(); iterElemNoSer.hasNext();){
        			ElementoNoSerializadoLocal elementoNoSerializadoLocal = (ElementoNoSerializadoLocal) iterElemNoSer.next();
        			if(elementoNoSerializadoLocal.getFlagMatSap().equals(ComunInterfaces.FLAG_ES_MATERIAL_SAP)){
        				materialSAP(elementoNoSerializadoLocal.getNumMaterialSap(),  tr020e, estado, fechaContNew, nroPeticion);
        			}
	        	}
	        }
        	
        } else if(fechaContNew.equals(ComunInterfaces.FECHA_CONTABILIZACION_INVALIDO)){
        	log.error("Se env�a la petici�n a PGI en la actividad de Notificaci�n SAP VPI por tener una fecha de contabilizacion invalida, para la petici�n: "+ nroPeticion);				
			insertarCausalesCnaPeticion(peticionLocal,actGeneradora,/*Quiebre por defecto*/new Long(701),act.getIdActividadFlujo());
			
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
			act.setObservacion("Se present� un problema al intentar calcular la fecha de contabilizaci�n que se env�a a SAP, se deriva a PGI.", true);
			act.setRealizarTerminoInmediato(true);
        } else if((elemPetiSAP==null ||elemPetiSAP.size() <= 0) && 
        		(decoTarSAP==null ||decoTarSAP.size() <= 0) && 
				(modemSAP==null || modemSAP.size() <= 0) &&
				(elemNoSerialSAP==null || elemNoSerialSAP.size() <= 0)){
        	log.error("Se inhibe la actividad de Notificaci�n SAP VPI por no tener equipos marcados para el envio a SAP, para la petici�n: "+ nroPeticion);				
			act.setObservacion("Se inhibe la actividad por no tener equipos marcados para el envio a SAP.", true);
			act.setRealizarTerminoInmediato(true);
        }           
	}
	
	/**
	 * Metodo para asignar los datos de una cabecera
	 * @param identificadorTipoEquipo
	 * @param elementoLocal
	 * @param modemLocal
	 * @param decoTarjInfoSAPLocal
	 * @param decoTarjetaLocal
	 * @param tr020eRequest
	 */
	private void asignarDatosCabeceraVentaMinoristasSAP(String identificadorTipoEquipo, 
			Elemento_PeticionLocal elementoLocal, ModemLocal modemLocal, 
			Deco_Tarjeta_Info_SapLocal decoTarjInfoSAPLocal, Deco_tarjetaLocal decoTarjetaLocal,
			ElementoNoSerializadoLocal elementoNoSerializadoLocal,
			TR020ERequestHeader tr020eRequest){
		
		TR020EPositionHeader tr020ePosition = new TR020EPositionHeader();
        Collection seriales = new ArrayList();
        tr020ePosition.setMatQuantity("0");
		
		if(tr020eRequest.getPositionsHeader() != null && tr020eRequest.getPositionsHeader().size() <= 0){
			tr020ePosition = new TR020EPositionHeader();
        	seriales = new ArrayList();
        	tr020ePosition.setSerials(seriales);
        	tr020ePosition.setMatQuantity("0");
        	
        	asignarDatosPosicionVentaMinoristasSAP(identificadorTipoEquipo, elementoLocal, modemLocal, decoTarjInfoSAPLocal, decoTarjetaLocal, elementoNoSerializadoLocal, tr020ePosition);
        	
        	tr020eRequest.getPositionsHeader().add(tr020ePosition);
        }else{
        	boolean encontro = false;
        	DatosSAPDTO datosSapDto = new DatosSAPDTO();
        	
        	for (Iterator it = tr020eRequest.getPositionsHeader().iterator(); it.hasNext() && !encontro;) {
        		TR020EPositionHeader posicion = (TR020EPositionHeader) it.next();
        		/*Inicializa las variables contra las que va a comparar para saber si es la misma Posicion*/
        		asignarDatosComparacionSAP(identificadorTipoEquipo, elementoLocal, modemLocal, decoTarjInfoSAPLocal, decoTarjetaLocal, elementoNoSerializadoLocal,
        				datosSapDto);
        		/*Manejo para los Elemento_Peticion*/
        		if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEMENTO_PETICION_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            	        long canMatNum = Long.parseLong(cantMaterialStr) + 1;
            	        
            	        posicion.setMatQuantity(Long.toString(canMatNum));
            	        
            	        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey(); 	        
            	        if(elemPeticionKey != null && elemPeticionKey.serial != null){
            	        	if(elemPeticionKey.serial.length() > 18){
            	        		posicion.getSerials().add(elemPeticionKey.serial.substring(0,18));
            	        	}else{
            	        		posicion.getSerials().add(elemPeticionKey.serial);
            	        	}               
            	        }

            	        encontro = true;
            		}                        
        		}        		        		
        		/*Manejo para los Modem*/
        		else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_MODEM_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            	        long canMatNum = Long.parseLong(cantMaterialStr) + 1;
            	        
            	        posicion.setMatQuantity(Long.toString(canMatNum));
            	        
            	        ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
            	        if(modemKey != null && modemKey.serial != null){
            	        	if(modemKey.serial.length() > 18){
            	        		posicion.getSerials().add(modemKey.serial.substring(0,18));
            	        	}else{
            	        		posicion.getSerials().add(modemKey.serial);
            	        	}               
            	        }

            	        encontro = true;
            		}
        		}        		        	
        		/*Manejo para los Deco de Deco_Tarjeta*/
        		else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_DECO_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            	        long canMatNum = Long.parseLong(cantMaterialStr) + 1;
            	        
            	        posicion.setMatQuantity(Long.toString(canMatNum));
            	        
            	        if(decoTarjetaLocal != null && decoTarjetaLocal.getSerial_deco() != null){
            	        	if(decoTarjetaLocal.getSerial_deco().length() > 18){
            	        		posicion.getSerials().add(decoTarjetaLocal.getSerial_deco().substring(0,18));
            	        	}else{
            	        		posicion.getSerials().add(decoTarjetaLocal.getSerial_deco());
            	        	}
            	        }
            	        
            	        encontro = true;
            		}
        		}
        		/*Manejo para las Tarjeta de Deco_Tarjeta*/
        		else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_TARJETA_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            	        long canMatNum = Long.parseLong(cantMaterialStr) + 1;

            	        posicion.setMatQuantity(Long.toString(canMatNum));
            	        
            	        Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
            	        if(decoTarjetaKey != null && decoTarjetaKey.id_tarjeta != null){
            	        	if(decoTarjetaKey.id_tarjeta.length() > 18){
            	        		posicion.getSerials().add(decoTarjetaKey.id_tarjeta.substring(0,18));
            	        	}else{
            	        		posicion.getSerials().add(decoTarjetaKey.id_tarjeta);
            	        	}               
            	        }

            	        encontro = true;
            		}
        		}
        		/*Manejo para los Elemento_No_Serializado*/
        		else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP)){
        			if(posicion.getSapMaterialCode().equals(datosSapDto.getMaterialCodeSap())
        					&& posicion.getMaterial().equals(datosSapDto.getMaterialSap())
            				&& posicion.getPlant().equals(datosSapDto.getPlantSap())
    						&& posicion.getStorage().equals(datosSapDto.getStorageSap())
    						&& posicion.getBatchCode().equals(datosSapDto.getBatchCodeSap())
    						&& posicion.getMeasurementUnit().equals(datosSapDto.getMeasurementUnitSap())
							&& posicion.getCostCenter().equals(datosSapDto.getCostCenterSap())
							&& posicion.getFuncAreaLong().equals(datosSapDto.getFuncAreaLongSap())
							&& posicion.getPepElement().equals(datosSapDto.getPepElementSap())){
        				
            			String cantMaterialStr = posicion.getMatQuantity();
            			Long longCantidadElemNoSerial = elementoNoSerializadoLocal.getCantidad();
            			if(longCantidadElemNoSerial != null){
            				long canMatNum = Long.parseLong(cantMaterialStr) + longCantidadElemNoSerial.longValue();
            				
            				posicion.setMatQuantity(Long.toString(canMatNum));
            			}

            			encontro = true;
            			
//            	        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey(); 	        
//            	        if(elemPeticionKey != null && elemPeticionKey.serial != null){
//            	        	if(elemPeticionKey.serial.length() > 18){
//            	        		posicion.getSerials().add(elemPeticionKey.serial.substring(0,18));
//            	        	}else{
//            	        		posicion.getSerials().add(elemPeticionKey.serial);
//            	        	}               
//            	        }

            		}                        
        		}
        	}
        	
        	if(!encontro){
            	tr020ePosition = new TR020EPositionHeader();
            	seriales = new ArrayList();
            	tr020ePosition.setSerials(seriales);
            	tr020ePosition.setMatQuantity("0");
            	
            	asignarDatosPosicionVentaMinoristasSAP(identificadorTipoEquipo, elementoLocal, modemLocal, decoTarjInfoSAPLocal, decoTarjetaLocal, elementoNoSerializadoLocal, tr020ePosition);
            	
            	tr020eRequest.getPositionsHeader().add(tr020ePosition);
        	}
        }          			
	}
	
	/**
	 * Metodo para asignar los datos de la posici�n
	 * @param identificadorTipoEquipo
	 * @param elementoLocal
	 * @param modemLocal
	 * @param decoTarjInfoSAPLocal
	 * @param decoTarjetaLocal
	 * @param tr020ePosition
	 */
	private void asignarDatosPosicionVentaMinoristasSAP(String identificadorTipoEquipo, 
			Elemento_PeticionLocal elementoLocal, ModemLocal modemLocal, 
			Deco_Tarjeta_Info_SapLocal decoTarjInfoSAPLocal, Deco_tarjetaLocal decoTarjetaLocal, ElementoNoSerializadoLocal elementoNoSerializadoLocal,
			TR020EPositionHeader tr020ePosition){
		
		if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEMENTO_PETICION_SAP)){
			if(elementoLocal != null && elementoLocal.getPos_doc_sap() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(elementoLocal.getPos_doc_sap()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(elementoLocal != null && elementoLocal.getNum_material_sap() != null)
				tr020ePosition.setMaterial(elementoLocal.getNum_material_sap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(elementoLocal != null && elementoLocal.getCentro_sap() != null)
	        	tr020ePosition.setPlant(elementoLocal.getCentro_sap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(elementoLocal != null && elementoLocal.getAlmacen_sap() != null)
	        	tr020ePosition.setStorage(elementoLocal.getAlmacen_sap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(elementoLocal != null && elementoLocal.getCod_lote_sap() != null)
	        	tr020ePosition.setBatchCode(elementoLocal.getCod_lote_sap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        String cantMaterialStr = tr020ePosition.getMatQuantity();
	        if(cantMaterialStr != null){
	        	long canMatNum = Long.parseLong(cantMaterialStr) + 1;		        
		        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
	        }else{
	        	tr020ePosition.setMatQuantity("1");
	        }
	        
	        if(elementoLocal != null && elementoLocal.getUnd_medida_sap() != null)
	        	tr020ePosition.setMeasurementUnit(elementoLocal.getUnd_medida_sap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(elementoLocal != null && elementoLocal.getCentr_cost_sap() != null)
	        	tr020ePosition.setCostCenter(elementoLocal.getCentr_cost_sap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(elementoLocal != null && elementoLocal.getArea_func_sap() != null)        	
	        	tr020ePosition.setFuncAreaLong(elementoLocal.getArea_func_sap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(elementoLocal != null && elementoLocal.getElement_pep_sap() != null)        	
	        	tr020ePosition.setPepElement(elementoLocal.getElement_pep_sap());
	        else
	        	tr020ePosition.setPepElement("");
	        
	        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey(); 	        
	        if(elemPeticionKey != null && elemPeticionKey.serial != null){
	        	if(elemPeticionKey.serial.length() > 18){
	        		tr020ePosition.getSerials().add(elemPeticionKey.serial.substring(0,18));
	        	}else{
	        		tr020ePosition.getSerials().add(elemPeticionKey.serial);
	        	}               
	        }
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_MODEM_SAP)){
			if(modemLocal != null && modemLocal.getPos_doc_sap() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(modemLocal.getPos_doc_sap()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(modemLocal != null && modemLocal.getNum_material_sap() != null)
				tr020ePosition.setMaterial(modemLocal.getNum_material_sap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(modemLocal != null && modemLocal.getCentro_sap() != null)
	        	tr020ePosition.setPlant(modemLocal.getCentro_sap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(modemLocal != null && modemLocal.getAlmacen_sap() != null)
	        	tr020ePosition.setStorage(modemLocal.getAlmacen_sap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(modemLocal != null && modemLocal.getCod_lote_sap() != null)
	        	tr020ePosition.setBatchCode(modemLocal.getCod_lote_sap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        String cantMaterialStr = tr020ePosition.getMatQuantity();
	        if(cantMaterialStr != null){
	        	long canMatNum = Long.parseLong(cantMaterialStr) + 1;		        
		        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
	        }else{
	        	tr020ePosition.setMatQuantity("1");
	        }
	        
	        if(modemLocal != null && modemLocal.getUnd_medida_sap() != null)
	        	tr020ePosition.setMeasurementUnit(modemLocal.getUnd_medida_sap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(modemLocal != null && modemLocal.getCentr_cost_sap() != null)
	        	tr020ePosition.setCostCenter(modemLocal.getCentr_cost_sap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(modemLocal != null && modemLocal.getArea_func_sap() != null)        	
	        	tr020ePosition.setFuncAreaLong(modemLocal.getArea_func_sap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(modemLocal != null && modemLocal.getElement_pep_sap() != null)        	
	        	tr020ePosition.setPepElement(modemLocal.getElement_pep_sap());
	        else
	        	tr020ePosition.setPepElement("");
	        
	        ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
	        if(modemKey != null && modemKey.serial != null){
	        	if(modemKey.serial.length() > 18){
	        		tr020ePosition.getSerials().add(modemKey.serial.substring(0,18));
	        	}else{
	        		tr020ePosition.getSerials().add(modemKey.serial);
	        	}               
	        }
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_DECO_SAP)){
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getPos_doc_sap() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(decoTarjInfoSAPLocal.getPos_doc_sap()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getNum_material_sap() != null)
				tr020ePosition.setMaterial(decoTarjInfoSAPLocal.getNum_material_sap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentro_sap() != null)
	        	tr020ePosition.setPlant(decoTarjInfoSAPLocal.getCentro_sap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getAlmacen_sap() != null)
	        	tr020ePosition.setStorage(decoTarjInfoSAPLocal.getAlmacen_sap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCod_lote_sap() != null)
	        	tr020ePosition.setBatchCode(decoTarjInfoSAPLocal.getCod_lote_sap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        String cantMaterialStr = tr020ePosition.getMatQuantity();
	        if(cantMaterialStr != null){
	        	long canMatNum = Long.parseLong(cantMaterialStr) + 1;		        
		        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
	        }else{
	        	tr020ePosition.setMatQuantity("1");
	        }
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getUnd_medida_sap() != null)
	        	tr020ePosition.setMeasurementUnit(decoTarjInfoSAPLocal.getUnd_medida_sap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentr_cost_sap() != null)
	        	tr020ePosition.setCostCenter(decoTarjInfoSAPLocal.getCentr_cost_sap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getArea_func_sap() != null)        	
	        	tr020ePosition.setFuncAreaLong(decoTarjInfoSAPLocal.getArea_func_sap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getElement_pep_sap() != null)        	
	        	tr020ePosition.setPepElement(decoTarjInfoSAPLocal.getElement_pep_sap());
	        else
	        	tr020ePosition.setPepElement("");
	        
	        if(decoTarjetaLocal != null && decoTarjetaLocal.getSerial_deco() != null){
	        	if(decoTarjetaLocal.getSerial_deco().length() > 18){
	        		tr020ePosition.getSerials().add(decoTarjetaLocal.getSerial_deco().substring(0,18));
	        	}else{
	        		tr020ePosition.getSerials().add(decoTarjetaLocal.getSerial_deco());
	        	}
	        }
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_TARJETA_SAP)){
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getPos_doc_sap() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(decoTarjInfoSAPLocal.getPos_doc_sap()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getNum_material_sap() != null)
				tr020ePosition.setMaterial(decoTarjInfoSAPLocal.getNum_material_sap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentro_sap() != null)
	        	tr020ePosition.setPlant(decoTarjInfoSAPLocal.getCentro_sap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getAlmacen_sap() != null)
	        	tr020ePosition.setStorage(decoTarjInfoSAPLocal.getAlmacen_sap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCod_lote_sap() != null)
	        	tr020ePosition.setBatchCode(decoTarjInfoSAPLocal.getCod_lote_sap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        String cantMaterialStr = tr020ePosition.getMatQuantity();
	        if(cantMaterialStr != null){
	        	long canMatNum = Long.parseLong(cantMaterialStr) + 1;		        
		        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
	        }else{
	        	tr020ePosition.setMatQuantity("1");
	        }
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getUnd_medida_sap() != null)
	        	tr020ePosition.setMeasurementUnit(decoTarjInfoSAPLocal.getUnd_medida_sap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentr_cost_sap() != null)
	        	tr020ePosition.setCostCenter(decoTarjInfoSAPLocal.getCentr_cost_sap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getArea_func_sap() != null)        	
	        	tr020ePosition.setFuncAreaLong(decoTarjInfoSAPLocal.getArea_func_sap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getElement_pep_sap() != null)        	
	        	tr020ePosition.setPepElement(decoTarjInfoSAPLocal.getElement_pep_sap());
	        else
	        	tr020ePosition.setPepElement("");

	        Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
	        if(decoTarjetaKey != null && decoTarjetaKey.id_tarjeta != null){
	        	if(decoTarjetaKey.id_tarjeta.length() > 18){
	        		tr020ePosition.getSerials().add(decoTarjetaKey.id_tarjeta.substring(0,18));
	        	}else{
	        		tr020ePosition.getSerials().add(decoTarjetaKey.id_tarjeta);
	        	}               
	        }
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP)){
			
			if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getPosDocSap() != null && elementoNoSerializadoLocal.getPosDocSap().intValue() != 0)
				tr020ePosition.setSapMaterialCode(Integer.toString(elementoNoSerializadoLocal.getPosDocSap().intValue()));
	        else
	        	tr020ePosition.setSapMaterialCode("");
			
			if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getNumMaterialSap() != null)
				tr020ePosition.setMaterial(elementoNoSerializadoLocal.getNumMaterialSap());
	        else
	        	tr020ePosition.setMaterial("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCentroSap() != null)
	        	tr020ePosition.setPlant(elementoNoSerializadoLocal.getCentroSap());
	        else
	        	tr020ePosition.setPlant("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getAlmacenSap() != null)
	        	tr020ePosition.setStorage(elementoNoSerializadoLocal.getAlmacenSap());
	        else
	        	tr020ePosition.setStorage("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCodLoteSap() != null)
	        	tr020ePosition.setBatchCode(elementoNoSerializadoLocal.getCodLoteSap());
	        else
	        	tr020ePosition.setBatchCode("");
	        
	        Long longCantidadElemNoSerial = elementoNoSerializadoLocal.getCantidad();	        
			if(longCantidadElemNoSerial != null){
				
				String cantMaterialStr = tr020ePosition.getMatQuantity();
		        if(cantMaterialStr != null){
		        	long canMatNum = Long.parseLong(cantMaterialStr) + longCantidadElemNoSerial.longValue();		        
			        tr020ePosition.setMatQuantity(Long.toString(canMatNum));
		        }else{
		        	tr020ePosition.setMatQuantity(longCantidadElemNoSerial.toString());
		        }
			}else{
				log.debug("Uno de los elementos no serializados no tiene asignada la cantidad. No se realiza ninguna accion sobre la catidad de la posicion.");
			}

	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getUndMedidaSap() != null)
	        	tr020ePosition.setMeasurementUnit(elementoNoSerializadoLocal.getUndMedidaSap());
	        else
	        	tr020ePosition.setMeasurementUnit("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCentrCostSap() != null)
	        	tr020ePosition.setCostCenter(elementoNoSerializadoLocal.getCentrCostSap());
	        else
	        	tr020ePosition.setCostCenter("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getAreaFuncSap() != null)        	
	        	tr020ePosition.setFuncAreaLong(elementoNoSerializadoLocal.getAreaFuncSap());
	        else
	        	tr020ePosition.setFuncAreaLong("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getElementPepSap() != null)        	
	        	tr020ePosition.setPepElement(elementoNoSerializadoLocal.getElementPepSap());
	        else
	        	tr020ePosition.setPepElement("");
	        
//	        Elemento_PeticionKey elemPeticionKey = (Elemento_PeticionKey) elementoLocal.getPrimaryKey(); 	        
//	        if(elemPeticionKey != null && elemPeticionKey.serial != null){
//	        	if(elemPeticionKey.serial.length() > 18){
//	        		tr020ePosition.getSerials().add(elemPeticionKey.serial.substring(0,18));
//	        	}else{
//	        		tr020ePosition.getSerials().add(elemPeticionKey.serial);
//	        	}               
//	        }
		}
	}
	
	/**
	 * Metodo para asignar los datos de comparaci�n a las variables
	 * @param identificadorTipoEquipo
	 * @param elementoLocal
	 * @param modemLocal
	 * @param decoTarjInfoSAPLocal
	 * @param decoTarjetaLocal
	 * @param datosSapDto
	 */
	private void asignarDatosComparacionSAP(String identificadorTipoEquipo, 
			Elemento_PeticionLocal elementoLocal, ModemLocal modemLocal, 
			Deco_Tarjeta_Info_SapLocal decoTarjInfoSAPLocal, Deco_tarjetaLocal decoTarjetaLocal, ElementoNoSerializadoLocal elementoNoSerializadoLocal,
			DatosSAPDTO datosSapDto){

		if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEMENTO_PETICION_SAP)){
			
			if(elementoLocal != null && elementoLocal.getPos_doc_sap() != 0)
				datosSapDto.setMaterialCodeSap(Integer.toString(elementoLocal.getPos_doc_sap()));
	        else
	        	datosSapDto.setMaterialCodeSap("");
			
			if(elementoLocal != null && elementoLocal.getNum_material_sap() != null)
				datosSapDto.setMaterialSap(elementoLocal.getNum_material_sap());
	        else
	        	datosSapDto.setMaterialSap("");
	        
	        if(elementoLocal != null && elementoLocal.getCentro_sap() != null)
	        	datosSapDto.setPlantSap(elementoLocal.getCentro_sap());
	        else
	        	datosSapDto.setPlantSap("");
	        
	        if(elementoLocal != null && elementoLocal.getAlmacen_sap() != null)
	        	datosSapDto.setStorageSap(elementoLocal.getAlmacen_sap());
	        else
	        	datosSapDto.setStorageSap("");
	        
	        if(elementoLocal != null && elementoLocal.getCod_lote_sap() != null)
	        	datosSapDto.setBatchCodeSap(elementoLocal.getCod_lote_sap());
	        else
	        	datosSapDto.setBatchCodeSap("");
	        
	        if(elementoLocal != null && elementoLocal.getUnd_medida_sap() != null)
	        	datosSapDto.setMeasurementUnitSap(elementoLocal.getUnd_medida_sap());
	        else
	        	datosSapDto.setMeasurementUnitSap("");
	        
	        if(elementoLocal != null && elementoLocal.getCentr_cost_sap() != null)
	        	datosSapDto.setCostCenterSap(elementoLocal.getCentr_cost_sap());
	        else
	        	datosSapDto.setCostCenterSap("");
	        
	        if(elementoLocal != null && elementoLocal.getArea_func_sap() != null)        	
	        	datosSapDto.setFuncAreaLongSap(elementoLocal.getArea_func_sap());
	        else
	        	datosSapDto.setFuncAreaLongSap("");
	        
	        if(elementoLocal != null && elementoLocal.getElement_pep_sap() != null)        	
	        	datosSapDto.setPepElementSap(elementoLocal.getElement_pep_sap());
	        else
	        	datosSapDto.setPepElementSap("");
	        
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_MODEM_SAP)){
			
			if(modemLocal != null && modemLocal.getPos_doc_sap() != 0)				
				datosSapDto.setMaterialCodeSap(Integer.toString(modemLocal.getPos_doc_sap()));
	        else
	        	datosSapDto.setMaterialCodeSap("");
			
			if(modemLocal != null && modemLocal.getNum_material_sap() != null)				
				datosSapDto.setMaterialSap(modemLocal.getNum_material_sap());
	        else
	        	datosSapDto.setMaterialSap("");
	        
	        if(modemLocal != null && modemLocal.getCentro_sap() != null)
	        	datosSapDto.setPlantSap(modemLocal.getCentro_sap());
	        else
	        	datosSapDto.setPlantSap("");
	        
	        if(modemLocal != null && modemLocal.getAlmacen_sap() != null)
	        	datosSapDto.setStorageSap(modemLocal.getAlmacen_sap());
	        else
	        	datosSapDto.setStorageSap("");
	        
	        if(modemLocal != null && modemLocal.getCod_lote_sap() != null)	        	
	        	datosSapDto.setBatchCodeSap(modemLocal.getCod_lote_sap());
	        else
	        	datosSapDto.setBatchCodeSap("");
	        
	        if(modemLocal != null && modemLocal.getUnd_medida_sap() != null)	        	
	        	datosSapDto.setMeasurementUnitSap(modemLocal.getUnd_medida_sap());
	        else
	        	datosSapDto.setMeasurementUnitSap("");
	        
	        if(modemLocal != null && modemLocal.getCentr_cost_sap() != null)	        	
	        	datosSapDto.setCostCenterSap(modemLocal.getCentr_cost_sap());
	        else
	        	datosSapDto.setCostCenterSap("");
	        
	        if(modemLocal != null && modemLocal.getArea_func_sap() != null)        	
	        	datosSapDto.setFuncAreaLongSap(modemLocal.getArea_func_sap());
	        else
	        	datosSapDto.setFuncAreaLongSap("");
	        
	        if(modemLocal != null && modemLocal.getElement_pep_sap() != null)        	
	        	datosSapDto.setPepElementSap(modemLocal.getElement_pep_sap());
	        else
	        	datosSapDto.setPepElementSap("");

		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_DECO_SAP) 
				|| identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_TARJETA_SAP)){
			
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getPos_doc_sap() != 0)				
				datosSapDto.setMaterialCodeSap(Integer.toString(decoTarjInfoSAPLocal.getPos_doc_sap()));
	        else
	        	datosSapDto.setMaterialCodeSap("");
			
			if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getNum_material_sap() != null)				
				datosSapDto.setMaterialSap(decoTarjInfoSAPLocal.getNum_material_sap());
	        else
	        	datosSapDto.setMaterialSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentro_sap() != null)
	        	datosSapDto.setPlantSap(decoTarjInfoSAPLocal.getCentro_sap());
	        else
	        	datosSapDto.setPlantSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getAlmacen_sap() != null)
	        	datosSapDto.setStorageSap(decoTarjInfoSAPLocal.getAlmacen_sap());
	        else
	        	datosSapDto.setStorageSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCod_lote_sap() != null)
	        	datosSapDto.setBatchCodeSap(decoTarjInfoSAPLocal.getCod_lote_sap());
	        else
	        	datosSapDto.setBatchCodeSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getUnd_medida_sap() != null)	        	
	        	datosSapDto.setMeasurementUnitSap(decoTarjInfoSAPLocal.getUnd_medida_sap());
	        else
	        	datosSapDto.setMeasurementUnitSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getCentr_cost_sap() != null)
	        	datosSapDto.setCostCenterSap(decoTarjInfoSAPLocal.getCentr_cost_sap());
	        else
	        	datosSapDto.setCostCenterSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getArea_func_sap() != null)        	
	        	datosSapDto.setFuncAreaLongSap(decoTarjInfoSAPLocal.getArea_func_sap());
	        else
	        	datosSapDto.setFuncAreaLongSap("");
	        
	        if(decoTarjInfoSAPLocal != null && decoTarjInfoSAPLocal.getElement_pep_sap() != null)        	
	        	datosSapDto.setPepElementSap(decoTarjInfoSAPLocal.getElement_pep_sap());
	        else
	        	datosSapDto.setPepElementSap("");
	        
		} else if(identificadorTipoEquipo.equals(ComunInterfaces.IDENTIFICADOR_ELEM_NO_SERIALIZADO_SAP)){
			
			if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getPosDocSap() != null && elementoNoSerializadoLocal.getPosDocSap().intValue() != 0)
				datosSapDto.setMaterialCodeSap(Integer.toString(elementoNoSerializadoLocal.getPosDocSap().intValue()));
	        else
	        	datosSapDto.setMaterialCodeSap("");
			
			if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getNumMaterialSap() != null)
				datosSapDto.setMaterialSap(elementoNoSerializadoLocal.getNumMaterialSap());
	        else
	        	datosSapDto.setMaterialSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCentroSap() != null)
	        	datosSapDto.setPlantSap(elementoNoSerializadoLocal.getCentroSap());
	        else
	        	datosSapDto.setPlantSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getAlmacenSap() != null)
	        	datosSapDto.setStorageSap(elementoNoSerializadoLocal.getAlmacenSap());
	        else
	        	datosSapDto.setStorageSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCodLoteSap() != null)
	        	datosSapDto.setBatchCodeSap(elementoNoSerializadoLocal.getCodLoteSap());
	        else
	        	datosSapDto.setBatchCodeSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getUndMedidaSap() != null)
	        	datosSapDto.setMeasurementUnitSap(elementoNoSerializadoLocal.getUndMedidaSap());
	        else
	        	datosSapDto.setMeasurementUnitSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getCentrCostSap() != null)
	        	datosSapDto.setCostCenterSap(elementoNoSerializadoLocal.getCentrCostSap());
	        else
	        	datosSapDto.setCostCenterSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getAreaFuncSap() != null)        	
	        	datosSapDto.setFuncAreaLongSap(elementoNoSerializadoLocal.getAreaFuncSap());
	        else
	        	datosSapDto.setFuncAreaLongSap("");
	        
	        if(elementoNoSerializadoLocal != null && elementoNoSerializadoLocal.getElementPepSap() != null)        	
	        	datosSapDto.setPepElementSap(elementoNoSerializadoLocal.getElementPepSap());
	        else
	        	datosSapDto.setPepElementSap("");
	        
		}
	}	
	
	public void materialSAP(String codmaterial, TR020E tr020e, String estado, String fechaContNew,Long nroPeticion ){
		Mensaje_descarga_SAPLocal mensajeDescargaSAPLocal;
		String consecutivo= null;
		log.debug("Insersa a materialSAP, para el material"+codmaterial+" y la petici�n: "+ nroPeticion);

		try{
			mensajeDescargaSAPLocal = mensajeDescargaSAPLocalHome.create(new Long(dbSeq.seqNextValLong("CORRELATIVO_DESCARGA_SAP")));
			mensajeDescargaSAPLocal.setId_peti(nroPeticion);
			mensajeDescargaSAPLocal.setId_atis(new Long(tr020e.getPeticionAtis()));
	    	mensajeDescargaSAPLocal.setEst_pedido(estado);
	    	mensajeDescargaSAPLocal.setFecha_eje_sap(fechaContNew);
	    	mensajeDescargaSAPLocal.setId_cod_mat(codmaterial);
		} catch (CreateException e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error a Crear la informacion en Notificaci�n SAP", e);
		} 
		
	}
	private CaracteristicaPSLocal obtenerProductoServicio(Subpeticion_atisLocal subpeticion_atisLocal) throws NamingException, ATiempoAppEx {
		Subpeticion_caracteristicasLocalHome subpeticionCaracteristicasLocalHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
		Subpeticion_caracteristicasKey sck=new Subpeticion_caracteristicasKey(new Long(ComunInterfaces.CODIGO_CARACTERISTICA_EQUIPO_QW),(Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey());
		Collection tmp = new ArrayList();
		Long ps= subpeticion_atisLocal.getCod_pro_ser_cd();
		try{
			Subpeticion_caracteristicasLocal sc=subpeticionCaracteristicasLocalHome.findByPrimaryKey(sck);
			String valorCaracteristica=sc.getVal_ral_crc_cd();
			CaracteristicaPSLocalHome cps =(CaracteristicaPSLocalHome)HomeFactory.getHome(CaracteristicaPSLocalHome.JNDI_NAME);
			return cps.findByCaracteristica(valorCaracteristica,ps);
		}catch (FinderException e) { 
			log.debug ("La subpeticion "+((Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey()).cod_sub_cd +" no tiene la caracteristica"+e.getMessage());
			return null;
		}
		
	}
	public void ConsultarEstadoDTH(ActividadEJBDTO act, Long numeroPeticion, String codigoActivida){
		log.debug ("Ingreso a ConsultarEstadoDTH.");
		
		try {
			Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
			Mensaje_estado_tvLocal msgEstadoTvLocal;
			Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
			Mensaje_estadoKey key = new Mensaje_estadoKey (new Integer (ComunInterfaces.estadoEspera));
            Mensaje_estadoLocal mensajeEstado = msgEstadoLocalHome.findByPrimaryKey (key) ;
			PeticionLocalHome pet = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME);
			PeticionLocal petLocal = pet.findByPrimaryKey(new PeticionKey(numeroPeticion));
			
			//Se crea secuencia del mensaje
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			
			log.debug ("Se crea nuevo consecutivo para la tr-053-e: "+idCorrelativoMensaje);
			
			//SE setean los datos en la TR053E
			TR053E tr053e = new TR053E();
			tr053e.setId(idCorrelativoMensaje.toString());
			tr053e.setPcid(petLocal.getNum_ide_nu_tv());
			tr053e.setAtiempoRequestNumber(numeroPeticion.toString());
			
			//se almacena secuencia ne la tabla mensaje estado TV
			msgEstadoTvLocal = msgEstadoTvLocalHome.create(idCorrelativoMensaje);
			msgEstadoTvLocal.setPeticion(petLocal);
			msgEstadoTvLocal.setFecha_envio(new Fecha().getTimestamp());
			msgEstadoTvLocal.setMensaje_estado(mensajeEstado);
			msgEstadoTvLocal.setCod_actividad_generadora(act.getCodigoActividad());
			
			//Se envia menensaje a HC
			log.debug ("Se envia mensaje tr-053-e.");
			new ConsultarEstadoDTHMQ().enviarMensaje (tr053e) ;
			
		} catch (NamingException e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error generado en ConsultarEstadoDTH", e);
		} catch (FinderException e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error al consultar en ConsultarEstadoDTH", e);
		} catch (CreateException e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error al crear mensaje estado tv en ConsultarEstadoDTH", e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error al tratar de enviar la TR053E en ConsultarEstadoDTH", e);
		}
		
	}
	
	public void recepcionConsultarEstadoDTH(TR053S tr053s){
		
		try {
		log.debug ("Ingreso a recepcionConsultarEstadoDTH.");
			Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
			Mensaje_estado_tvLocal msgEstadoTvLocal;
			Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
	        Mensaje_estadoLocal mesajeOkLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
            Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
            Mensaje_estadoLocal mensajeManualLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEsperaManual)));
            ErrorLegadoLocalHome errorHome = (ErrorLegadoLocalHome) HomeFactory.getHome (ErrorLegadoLocalHome.JNDI_NAME) ;
            ErrorLegadoLocal errorlocal = null;
            
	        BackendExecution bExecution = null;
	        bExecution = BackendTraceUtil.initExecution(tr053s, log);
            bExecution.setNumPetAtiempo(new Long(tr053s.getAtiempoRequestNumber()));
            bExecution.setIdMensajeOp(tr053s.getId());
            bExecution.startOperation();
            
            msgEstadoTvLocal = msgEstadoTvLocalHome.findByPrimaryKey(new Mensaje_estado_tvKey(new Long(tr053s.getId())));
            ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
            IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(msgEstadoTvLocal.getCod_actividad_generadora());
    		ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(new Long (tr053s.getAtiempoRequestNumber()), msgEstadoTvLocal.getCod_actividad_generadora());
    		String plataforma = VpistbbaConfig.getVariable("IDPGI");
    		
            if(tr053s.isError() || tr053s.getCodigoError().intValue() != 0){
            	
            	try{
            		errorlocal = errorHome.findByErrorTr(tr053s.getCodigoError().toString(),"TR053S");
        		} catch (javax.ejb.FinderException e) {
        			log.debug("no se encontr� ninguna bandeja para derivar");
        		}
            		
    				
    				if(errorlocal != null){
    					plataforma = errorlocal.getPlataforma();
    					insertarCausalesCnaPeticion(msgEstadoTvLocal.getPeticion(), msgEstadoTvLocal.getCod_actividad_generadora(), errorlocal.getIdCausa(), actDto.getIdActividadFlujo());
    				}else{
    					insertarCausalesCnaPeticion(msgEstadoTvLocal.getPeticion(), msgEstadoTvLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
    				}
            		
    				log.debug("Tengo un error en el mensaje. Se deriva a: "+plataforma);
    				msgEstadoTvLocal.setMensaje_estado(mensajeErrorLocal);
    				msgEstadoTvLocal.setFecha_cierre(new Fecha().getTimestamp());
    				
    				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,plataforma); 
    				actDto.setObservacion("Se deriva solicitud a Error porque viene con estado del pc:" + tr053s.getCodigoStado(),true);
     
                    actividadEJB.terminar(actDto);
                    
                    return;
            	
            }else{
            	if(tr053s.getCodigoStado() != null && !tr053s.getCodigoStado().equals("") ){
            		try{
            			errorlocal = errorHome.findByErrorTr(tr053s.getCodigoStado().toString(),"TR053S");
            		} catch (javax.ejb.FinderException e) {
            			log.debug("no se encontr� ninguna bandeja para derivar");
            		}
            	}
            		
            		
        		
				if(errorlocal != null){
					plataforma = errorlocal.getPlataforma();
					insertarCausalesCnaPeticion(msgEstadoTvLocal.getPeticion(), msgEstadoTvLocal.getCod_actividad_generadora(), errorlocal.getIdCausa(), actDto.getIdActividadFlujo());
					log.debug("Tengo un error en el mensaje");
					msgEstadoTvLocal.setMensaje_estado(mensajeErrorLocal);
					msgEstadoTvLocal.setFecha_cierre(new Fecha().getTimestamp());
	            	
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,plataforma);
					actDto.setObservacion("Se deriva solicitud a Error porque viene con estado del pc:" + tr053s.getCodigoStado(),true);
					
	                actividadEJB.terminar(actDto);

	                return;
				}
				log.debug("Se genera Proceso CONSULTAR ESTADO DTH de forma exitosa");
				msgEstadoTvLocal.setMensaje_estado(mesajeOkLocal);
				msgEstadoTvLocal.setFecha_cierre(new Fecha().getTimestamp());
				actDto.setObservacion("El usuario se encuentra activo, se continual el proceso de forma exitoso",true);
                actividadEJB.terminar(actDto);

                return;
            }
            
   		} catch (NamingException e) {
			// TODO Bloque catch generado autom�ticamente
   			log.error("Error en la recepci�n TR053S en recepcionConsultarEstadoDTH", e);
		} catch (FinderException e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error en la recepci�n TR053S en recepcionConsultarEstadoDTH", e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error en la recepci�n TR053S en recepcionConsultarEstadoDTH", e);
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error en la recepci�n TR053S en recepcionConsultarEstadoDTH", e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error en la recepci�n TR053S en recepcionConsultarEstadoDTH", e);
		} catch (CreateException e) {
			// TODO Bloque catch generado autom�ticamente
			log.error("Error en la recepci�n TR053S en recepcionConsultarEstadoDTH", e);
		}
		
	}
}