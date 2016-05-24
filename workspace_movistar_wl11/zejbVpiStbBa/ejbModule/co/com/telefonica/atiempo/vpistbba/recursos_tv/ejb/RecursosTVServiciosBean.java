package co.com.telefonica.atiempo.vpistbba.recursos_tv.ejb;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.atiempo.dto.EquipoDTO;
import co.com.atiempo.dto.PsVsOcVO;
import co.com.atiempo.dto.TematicoDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.AltapctvKey;
import co.com.telefonica.atiempo.ejb.eb.AltapctvLocal;
import co.com.telefonica.atiempo.ejb.eb.AltapctvLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BandejasDeSoporte;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapKey;
import co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapLocal;
import co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaKey;
import co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaLocal;
import co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.EmpresaKey;
import co.com.telefonica.atiempo.ejb.eb.EmpresaLocal;
import co.com.telefonica.atiempo.ejb.eb.EmpresaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_psKey;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Inventario_dthKey;
import co.com.telefonica.atiempo.ejb.eb.Inventario_dthLocal;
import co.com.telefonica.atiempo.ejb.eb.Inventario_dthLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.ejb.eb.TematicoLocal;
import co.com.telefonica.atiempo.ejb.eb.TematicoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tmp_deco_tarjetaLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.Cards;
import co.com.telefonica.atiempo.interfaces.atiempo.Equipment;
import co.com.telefonica.atiempo.interfaces.atiempo.ErrorAux;
import co.com.telefonica.atiempo.interfaces.atiempo.TR016E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR016S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017EPackage;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR019E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR019S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR605E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR605S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711SMaterials;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InfoInvTvDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InfoPostVentaTV;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.ActualizacionInventarioTVMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudConfiguracionMovistarPlayMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudConfiguracionServiciosTVMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudDecoTarjActualizarMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudInformacionTecnicaTV;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOALocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOALocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
import com.telefonica_chile.vpistbba.bitacora.dto.BitacoraDTO;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocal;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocalHome;

/**
 * This is the bean class for the RecursosTVBean enterprise bean.
 * Created 09-04-2007 10:49:19 AM
 * @author francois
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class RecursosTVServiciosBean
    extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
    implements RecursosTVInterfaces
{
    protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
    
    private DBManager dbSeq ;
    private SimpleDateFormat df ;
    
    
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext (SessionContext ctx)
    throws EJBException, RemoteException
    {
        super.setSessionContext (ctx) ;
        
        dbSeq = new DBManager ();
        
        dbSeq.setDataSource (DBManager.JDBC_VPISTBBA);
        
        buscaHome () ;
        
        df = new SimpleDateFormat ("dd/MM/yyyy") ;
    }
    
    /*
     *
     */
    
    //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
//    private Mensaje_estadoLocalHome msgEstadoLocalHome ;
//    private PeticionLocalHome peticionLocalHome ;
//    private Mensaje_estado_tvLocalHome msgEstadoTvLocalHome ;
//    private Tmp_deco_tarjetaLocalHome tmpDecoTarjetaLocalHome ;
//    private Deco_tarjetaLocalHome decoTarjetaLocalHome ;
//    private Operacion_comercialLocalHome operacion_comercialHome;
//    private EmpresaLocalHome empresaHome;
//	private UsuarioLocalHome usuarioHome;
//	private Catalogo_causalLocalHome catalogo_causalHome;
//	private Estado_psLocalHome estado_psHome;
//	private Causal_peticionLocalHome causal_peticionHome;
//	private Estado_ps_peticionLocalHome estado_ps_peticionHome;
//	private Inventario_dthLocalHome inventario_dthHome;
//	private Bitacora_peticionLocalHome bitacoraPeticionHome;
//	private AltapctvLocalHome altapctvHome;
//    private TematicoLocalHome tematicoLocalHome ;
    
    private void buscaHome ()
    {
//        try
//        {
//            msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
//            peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
//            msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
//            tmpDecoTarjetaLocalHome = (Tmp_deco_tarjetaLocalHome) HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME) ;
//            decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
//            tematicoLocalHome = (TematicoLocalHome) HomeFactory.getHome (TematicoLocalHome.JNDI_NAME) ;
//			operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
//			empresaHome=(EmpresaLocalHome) HomeFactory.getHome(EmpresaLocalHome.JNDI_NAME);
//			usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
//			catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
//			estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
//			causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
//			estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
//			inventario_dthHome=(Inventario_dthLocalHome) HomeFactory.getHome(Inventario_dthLocalHome.JNDI_NAME);
//			altapctvHome=(AltapctvLocalHome) HomeFactory.getHome(AltapctvLocalHome.JNDI_NAME);
//			
//        }
//        catch (NamingException nex)
//        {
//            log.error ("error durante inicializacion", nex) ;
//        }
        }
    
    
//    private void validaHome ()
//    throws ATiempoAppEx
//    {
//        if (
//            msgEstadoLocalHome == null ||
//            peticionLocalHome == null ||
//            msgEstadoTvLocalHome == null ||
//            tmpDecoTarjetaLocalHome == null ||
//            decoTarjetaLocalHome == null ||
//            tematicoLocalHome == null ||
//            operacion_comercialHome==null ||
//            empresaHome==null ||
//            inventario_dthHome==null ||
//            catalogo_causalHome==null
//            )
//        {
//            throw new ATiempoAppEx (ATiempoAppEx.NAMING);
//        }
//    }
    
    
    /**
     * solicita a HC una lista de decos y tarjetas cuyos numero de series terminan con 4 digitos
     *
     * esta rutina es llamada directamente desde la interfaz al usuario
     *
     * @param idPeticion el id de la peticion
     * @param ult4digitosTarjeta ultimos 4 digitos del numero de la tarjeta
     * @param ult4digitosDeco ultimos 4 digitos del numero de serie del deco
     * @param idContratista id del contratista
     */
    
    public Long enviaDecoTarjetaPorUtilizar (long idPeticion, String ult4digitosTarjeta, String ult4digitosDeco, long idContratista, String tipoDeco)
    throws ATiempoAppEx
    {
        //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
        
        try
        {
        	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
        	Tmp_deco_tarjetaLocalHome tmpDecoTarjetaLocalHome = (Tmp_deco_tarjetaLocalHome) HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME) ;
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
        	
            // obtiene un nuevo id de mensaje
            
            Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
            
            // obtiene el id de la peticion Atis
            
            PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            
            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            
            long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            
            
            // limpia la tabla tmp_deco tarjeta
            
            Collection colTmpDecoTarjeta = tmpDecoTarjetaLocalHome.findByNroPeticion (new Long (idPeticion)) ;
            
            Iterator iterTmpDecoTarjeta = colTmpDecoTarjeta.iterator () ;
            //TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 18, 2009
            Tmp_deco_tarjetaLocal tmpDecoTarjeta = null;
            while (iterTmpDecoTarjeta.hasNext ())
            {
                tmpDecoTarjeta = (Tmp_deco_tarjetaLocal) iterTmpDecoTarjeta.next () ;
                
                try
                {
                    tmpDecoTarjeta.remove () ;
                }
                catch (EJBException e1)
                {
                    e1.printStackTrace ();
                }
                catch (RemoveException e1)
                {
                    e1.printStackTrace ();
                }
            }
            
            
            // crea y llena la representacion Java del mensaje
            
            TR016E tr016e = new TR016E ();
            tr016e.setId (idCorrelativoMensaje.toString ()) ;
            tr016e.setAtisRequestNumber (peticionAtis) ;
            tr016e.setCardFinalDigits (ult4digitosTarjeta) ;
            tr016e.setDecoFinalDigitsSerial (ult4digitosDeco) ;
            tr016e.setContractorId (idContratista) ;
            tr016e.setDecoReference(tipoDeco);
            
            
            // TODO borrar los tmp_deco_tarj asociados
            
            Mensaje_estado_tvLocal dbmsg ;
            
            dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
            
            dbmsg.setPeticion (peticion) ;
			
			//TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp()) ;
			           					
            dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
            
            // llama a la rutina que envia el mensaje
            new SolicitudDecoTarjActualizarMQ ().enviarMensaje (tr016e) ;
            
            return (idCorrelativoMensaje) ;
        }catch (NamingException e)
		{
        	log.error(" Creacion de Local Home Nulls",e);
        	throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
        }
        catch (NumberFormatException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
        catch (FinderException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.FINDER);
        }
        catch (CreateException cex)
        {
            log.error ("error creando registro", cex) ;
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        }
    }
    
    /**
     * almacena la lista de deco/tarjetas que terminan en 4 digitos
     *
     * se guarda el XML mismo porque le es mas pratico al codigo de la interfaz usuario
     */
    
    public void actualizaDecoTarjetaPorUtilizar (TR016S tr016s)
    throws ATiempoAppEx
    {
        
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
        try
        {
			bExecution = BackendTraceUtil.initExecution(tr016s, log);
			bExecution.setNumPetAtiempo(new Long(tr016s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr016s.getId());
			bExecution.startOperation();
 
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Tmp_deco_tarjetaLocalHome tmpDecoTarjetaLocalHome = (Tmp_deco_tarjetaLocalHome) HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME) ;
        
            // busca el registro del mensaje
            log.debug("Primero si es mensaje de error lo obviamos.");
            if(tr016s.isError())
            {
        		log.debug("Respuesta con error:"+XMLUtilities.marshall(tr016s));
        		return;
        	}
            Mensaje_estado_tvLocal mensajeEstadoTv = buscaMensajeEstadoTv (tr016s.getId ()) ;
            
            if (mensajeEstadoTv == null)
            {
                log.debug (
                    "No Existe Respuesta en la base de enviados\n "
                    + XMLUtilities.marshall (tr016s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr016s.getId(),ATiempoAppEx.EXCEPTION);
            }
            
            // Validacion del estado de la respuesta
            
            Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoTv.getMensaje_estado ().getPrimaryKey () ;
            
            if (mensaje_estadoKey.cod_estado.intValue () == estadoOk)
            {
                log.debug (
                    "Es estado de la respuesta es diferente para ser procesado\n "
                    + XMLUtilities.marshall (tr016s));
                return;
            }
            
            // TODO: ver si procesamos los errores o si la interfaz al usuario se va a encargar de
            //       parsear el XML
            
            // todo ok, se graba la respuesta
            
            Long idTmpDecoTarjeta = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_DECO_TARJ")) ;
            
            Tmp_deco_tarjetaLocal tmpDecoTarjetaLocal = tmpDecoTarjetaLocalHome.create (idTmpDecoTarjeta,mensajeEstadoTv.getPeticion (),XMLUtilities.marshall (tr016s)) ;
            
            // actualiza el estado del mensaje
            //tr016s.get
            Mensaje_estadoLocal mensajeEstadoOk = buscaMensajeEstadoOk () ;
            
            mensajeEstadoTv.setMensaje_estado (mensajeEstadoOk) ;
            
            // no hay que cerrar la actividad del WF porque es la interfaz al usuario
            // y no una actividad automatica que solicita este servicio
        }catch (NamingException e)
		{
        	log.error(" Creacion de Local Home Nulls",e);
        	throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
        }
        catch (NumberFormatException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT) ;
        }
        catch (CreateException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        }
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
    }
    
    }
    
    /**
     * envia el mensaje de configuracion de servicios TV (activa deco/tarjetas y canales tematicos)
     *
     * @param idPeticion el id de la peticion
     */
    
    public Long enviaConfiguracionServiciosTV (long idPeticion) throws ATiempoAppEx
    {
        //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
        try
        {
        	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
        	RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
            // obtiene un nuevo id de mensaje
            
            Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
            
            // obtiene el id de la peticion Atis
            String pcId=null;
            
            PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            
            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            
            long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            
            boolean existeConfiguracionDTH = false;
            
            // busca
            // - el PS tipo PC de la familia TV (el PS tipo PC es como el padre de todos los PS)
            // - los PS correspondientes a los canales tematicos contratados por el cliente (por ejemplo parrilla de deportes, monos animados, cine, etc)
            
            Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
            Producto_servicioKey productoServicioPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey2 = null;
            
            List psTematicos = new ArrayList (10) ;
            List psDecos =new ArrayList(10);
            Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
            Producto_servicio_peticionLocal productoServicioPeticion = null;
            Producto_servicioLocal productoServicio = null;
            Operacion_comercialLocal operacion_comercialLocal = null;
            Familia_producto_servicioLocal familiaPS = null;
            Familia_producto_servicioKey familiaPSKey  = null;
            int idFamilia = 0;
            Agrupacion_atisLocal agrupacion_atisLocal = null;
            while (iterPss.hasNext ())
            {
                productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
                productoServicio = productoServicioPeticion.getProducto_servicio () ;
                operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
                operacion_comerciaPcKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
                
                Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
                
                // filtra por familia TV y busca el PC y los ps tematicos
                
	            familiaPS = productoServicio.getFamilia_producto_servicio () ;
                
                familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
                
                idFamilia = familiaPSKey.faps_id.intValue();
                
                // busca si es el PS PC
                if (idFamilia == familiaPcTV)
                {
                    log.debug ("peticion: " + idPeticion + " ps tipo PC "  + productoServicioKey.ps_id) ;
                    
                    if (productoServicioPeticionPcTV != null)
                    {
                        String msg = "peticion: " + idPeticion + " tiene mas de un ps tipo PC (el ultimo encontrado fue el "  + productoServicioKey.ps_id + ")" ;
                        
                        log.error (msg) ;
                        throw new ATiempoAppEx (msg) ;
                    }
                    if(pcId==null)
                    {
						agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
                    }
                    productoServicioPeticionPcTV = productoServicioPeticion ;
                    productoServicioPcKey = productoServicioKey ;
                    
                    //Corrección en Código de Operación Comercial para el alta de TV
                    operacion_comerciaPcKey2=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
                    continue ;
                }
                
                if (idFamilia == familiaTematicoTV || idFamilia==familiaPaqueteTV)
                {
                    log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
					if(pcId==null)
					{
						agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
                    psTematicos.add (productoServicioPeticion) ;
                    continue ;
                }
                //TODO PVR VALIDAR SI VA ACA
                if(idFamilia == familiaDecoTV ||idFamilia == familiaDecoPVRTV || idFamilia == familiaDecoHDTV)
                {
                	log.debug("peticion:"+idPeticion+" ps Deco "+productoServicioKey.ps_id);
					if(pcId==null)
					{
						agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
                	psDecos.add(productoServicioPeticion);
                	continue;
                }

                log.debug ("peticion: " + idPeticion + " no es Pc TV ni tampoco Tematico TV ni tampoco Tematico Deco TV." + productoServicioKey.ps_id) ;
            }
            
            
            
            // crea y llena la representacion Java del mensaje
            
            TR017E tr017e = new TR017E ();
            
            tr017e.setId (idCorrelativoMensaje.toString ()) ;
            
            tr017e.setPcId(pcId);
            if(productoServicioPcKey!=null)
            	tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
            
            if(operacion_comerciaPcKey2!=null)
            	tr017e.setPcCommercialOperation(operacion_comerciaPcKey2.opco_id.longValue());
            else if(operacion_comerciaPcKey!=null)
            	tr017e.setPcCommercialOperation(operacion_comerciaPcKey.opco_id.longValue());
            
            tr017e.setAtisRequestNumber (peticionAtis) ;
            tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
            tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
            tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
            
            tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
            
            if(peticion.getTip_cli_cd()!=null)
            	tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
            else
				tr017e.setClientType ('0') ;
           
            tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
            tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
            
            // deco / tarjeta
            
            List listDecoTarj = new ArrayList () ;
            
            ///Iterator iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;
            Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
			Collection listaDecos= decoTarjetaLocalHome.findByPeticion(new Long(idPeticion));
			Iterator iterDecoTarj=listaDecos.iterator();
			Deco_tarjetaLocal decoTarjeta = null;
			Deco_tarjetaKey decoTarjetaKey = null;
            while (iterDecoTarj.hasNext ())
            {
                decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
                if(!decoTarjeta.esEsperaDeActivacion() && !decoTarjeta.esEsperaDeEliminacion())
                	continue;
                decoTarjetaKey = (Deco_tarjetaKey) decoTarjeta.getPrimaryKey () ;
                
				TR017EEquipment equipment=new TR017EEquipment();
                
				equipment.setCardSerial(decoTarjetaKey.id_tarjeta) ;
				equipment.setCasId(decoTarjetaKey.id_deco) ;

				//TODO: 10052010 - Raúl Triviño - Ajuste para pasar correctamente el ps de cada tipo de deco
				if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoSTD)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_DEFAULT")).longValue());
				}else if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoHDTV)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_HD")).longValue());
				}else if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoPVR)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_PVR")).longValue());
				}
				//End TODO
				
                equipment.setCommercialOperation(decoTarjeta.getOpco_id().longValue());
                equipment.setDecoSerial(decoTarjeta.getSerial_deco());
                equipment.setDecoType(decoTarjeta.getDeco_reference());
                equipment.setDecoBrand(decoTarjeta.getDeco_marca());
                
                listDecoTarj.add (equipment) ;
            }
            
            tr017e.setEquipments (listDecoTarj) ;
            
            //TODO: Raúl Triviño - 13/10/2009 - ajuste del requerimiento 00029981_AltaTematicosEquipos
            /*Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection peticiones = peticion_flujoLocalHome.findByIdPeticion(new Long(idPeticion));
			
			/*for (Iterator iter = peticiones.iterator(); iter.hasNext();) {					
				Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
				
				if (peticionFlujo.().intValue() == Integer.parseInt(ComunInterfaces.ACTI_ID_Configuracion_DTH) 
				        && peticionFlujo.getPefl_estado().equals(ComunInterfaces.PEFL_ESTADO_OK)
				        && (peticionFlujo.getPrse_id().longValue() == Long.parseLong(ComunInterfaces.PRSE_ID_Configuracion_DTH_1)
				        ||  peticionFlujo.getPrse_id().longValue() == Long.parseLong(ComunInterfaces.PRSE_ID_Configuracion_DTH_2))){
				    existeConfiguracionDTH = true;
				    break;
				}
			}*/
			
			boolean tienePcEnAlta=recursosTVDelegate.tienePcEnAlta(new Long(idPeticion));
			
            //if (!existeConfiguracionDTH){
			if(tienePcEnAlta){
    			List listTematicos = new ArrayList () ;
                Iterator iterPsTematicos = psTematicos.iterator () ;
                while (iterPsTematicos.hasNext ()){
                    Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) iterPsTematicos.next () ;
                    long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
    				TR017EPackage package1=new TR017EPackage(); 
    				package1.setProductServiceCode (psId) ;
                    package1.setCommercialOperation(psPaquete.getIdOperacionComercial().longValue());
                    listTematicos.add (package1) ;
                }
                tr017e.setPackages (listTematicos) ;

            }
            //end TODO
                       
            // guarda el registro del mensaje
            
            Mensaje_estado_tvLocal dbmsg ;
            dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
            dbmsg.setPeticion (peticion) ;
			//TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp());
            dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
            
            /*if (idAgendaSC != null && idAgendaSC.length() > 0){
            	dbmsg.setDesc_error(idAgendaSC);
            }*/
                        
            // llama a la rutina que envia el mensaje
            new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;
                       
            return (idCorrelativoMensaje) ;
        }
        catch (NumberFormatException e)
        {
			log.error ("NumberFormatException", e) ;
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
        catch (FinderException e)
        {
			log.error ("FinderException", e) ;
            throw new ATiempoAppEx (ATiempoAppEx.FINDER);
        }
        catch (CreateException cex)
        {
            log.error ("error creando registro", cex) ;
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        } catch (NamingException e) {
			
			log.error ("error creando registro", e) ;
		    throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
    }
        
	public Long enviaConfiguracionServiciosTVAuto (long idPeticion,String idActividad,Integer idActividadFlujo) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
			
			PeticionesDelegate delegate=new PeticionesDelegate();
			//Este metodo debiera llamarse solo en las peticiones de post-venta
			//Tengo los siguientes CASOS:
			//1.- Baja Completa
			//2.- Baja Deco
			//3.- Cambio de Plan (Solo Configuracion DTH sin Instalacion ni Control de Instalacion
			//4.- Traslado OR,I, CI, Sin Configurar.
			//Voy a enviar todos los pses de la vaina en el packages
			//PD-.
			//Actividades Involucradas en esta vaina:
			//Obtener Recursos DTH.
			//Configuracion DTH.
			//Instalacion
			//Control de Instalacion
			//Actualizar Inventario DTH
			boolean esPostVentaPC=false;
			
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			String pcId=null;
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
			long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
			Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
			Producto_servicioKey productoServicioPcKey = null;
			Producto_servicioKey productoServicioDecoKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
			Operacion_comercialKey operacion_comerciaDecoKey = null;
			Operacion_comercialKey operacion_comerciaTematicoKey = null;
			Operacion_comercialKey operacion_comerciaPaqueteKey = null;
			Operacion_comercialKey operacion_comercialCPKey=null;
			List psTematicos = new ArrayList (10) ;
			List psPaquetes = new ArrayList (10) ;
			List psDecos =new ArrayList(20);
			Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
			boolean tieneReseteoCP=false;
			String idDecoCP=null;
			String idTarjetaCP=null;
			InfoPostVentaTV infoPostVentaTV=recuperaInfoTvPostVenta(new Long(idPeticion));
			Producto_servicio_peticionLocal productoServicioPeticion = null;
			Producto_servicioLocal productoServicio = null;
			Operacion_comercialLocal operacion_comercialLocal = null;
			Producto_servicioKey productoServicioKey = null;
			Operacion_comercialKey operacion_comercialKey = null;
			Familia_producto_servicioLocal familiaPS = null;
			Familia_producto_servicioKey familiaPSKey = null;
			Agrupacion_atisLocal agrupacion_atisLocal = null;
			while (iterPss.hasNext ())
			{
				productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
				productoServicio = productoServicioPeticion.getProducto_servicio () ;
				operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
				productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
				operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				familiaPS = productoServicio.getFamilia_producto_servicio () ;
				familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
				
				
				if(productoServicioKey.ps_id.longValue()==psReseteoCP)
				{
					tieneReseteoCP=true;
					operacion_comercialCPKey=operacion_comercialKey;
					if(pcId==null)
					{
						agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
				}
				int idFamilia = familiaPSKey.faps_id.intValue();
				if(idFamilia == familiaPcTV)
				{
					if(pcId==null)
					{
						agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					operacion_comerciaPcKey=operacion_comercialKey;
					productoServicioPeticionPcTV = productoServicioPeticion ;
					productoServicioPcKey = productoServicioKey ;
					continue ;
				}
				else if(idFamilia == familiaTematicoTV )
				{
					log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
					if(pcId==null)
					{
						agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					operacion_comerciaTematicoKey=operacion_comercialKey;
					psTematicos.add (productoServicioPeticion) ;
					continue ;
				}
				else if(idFamilia == familiaPaqueteTV )
				{
					log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
					if(pcId==null)
					{
						agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					operacion_comerciaPaqueteKey=operacion_comercialKey;
					psPaquetes.add (productoServicioPeticion);
					psTematicos.add (productoServicioPeticion);
					continue ;
				}
//				TODO PVR validar se agrego  familia
				else if(idFamilia == familiaDecoTV ||idFamilia == familiaDecoPVRTV)
				{
					log.debug("peticion:"+idPeticion+" ps Deco "+productoServicioKey.ps_id);
					if(pcId==null)
					{
						agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					productoServicioDecoKey = productoServicioKey ;
					operacion_comerciaDecoKey=operacion_comercialKey;
					psDecos.add(productoServicioPeticion);
					continue;
				}
				if(tieneReseteoCP && idDecoCP==null)
				{
					Subpeticion_atisLocal local=productoServicioPeticion.getFk_01_subp_atis();
					for(Iterator iterator=local.getSubpeticion_caracteristicas().iterator();iterator.hasNext();)
					{
						Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterator.next();
						Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
						Long casId=new Long (VpistbbaConfig.getVariable("CASIDTELEV"));
						if (spk.cod_crc_cd.longValue()== casId.longValue())
						{
							log.debug("Informacion : Se obtuvo CASIDTELEV:" + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
							idDecoCP=subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
							break;
						}
					}
					if(idDecoCP!=null)
					{
						Iterator iterPares=peticion.getDeco_tarjeta().iterator();
						while(iterPares.hasNext())
						{
							Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal) iterPares.next();
							Deco_tarjetaKey deco_tarjetaKey=(Deco_tarjetaKey) deco_tarjetaLocal.getPrimaryKey();
							if(deco_tarjetaKey.id_deco.equals(idDecoCP))
							{
								idTarjetaCP=deco_tarjetaKey.id_tarjeta;
								break;
							}
						}
					}
				}
			}
			
			if(tieneReseteoCP && (idDecoCP==null || idTarjetaCP==null))
				return null;
			
			if(productoServicioPcKey!=null)
				esPostVentaPC=true;
			
			ArrayList listaNegra=mergeTematicos(psPaquetes);
			log.debug("Lista Negra Size:"+listaNegra.size());
			
			TR017E tr017e = new TR017E ();
			tr017e.setId (idCorrelativoMensaje.toString ()) ;
			tr017e.setPcId(pcId);
			if(productoServicioPcKey!=null)
				tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
			if(operacion_comerciaPcKey!=null)
				tr017e.setPcCommercialOperation(operacion_comerciaPcKey.opco_id.longValue());
			tr017e.setAtisRequestNumber (peticionAtis) ;
			tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
			tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
			tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
			tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
			if(peticion.getTip_cli_cd()!=null)
				tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
			else
				tr017e.setClientType ('0') ;       
			tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
			tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
			List listDecoTarj = new ArrayList () ;
        
			Iterator iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;
			//Aqui tengo que ver la logica si es baja completa o baja de deco.
			boolean esPostVentaDeco=false;
			if(psDecos.size()>0)
				esPostVentaDeco=true;
			
			log.debug(infoPostVentaTV.toString());
			
			if(tieneReseteoCP)
			{
				TR017EEquipment equipment=new TR017EEquipment();
				equipment.setCardSerial(idTarjetaCP) ;
				equipment.setCasId(idDecoCP) ;
				equipment.setProductServiceCode(psReseteoCP);
				equipment.setCommercialOperation(operacion_comercialCPKey.opco_id.longValue());
				listDecoTarj.add (equipment) ;
			}
			else
			{
				while (iterDecoTarj.hasNext () )
				{
					Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
					log.debug("DEco tarjeta "+decoTarjeta.toEquipo().toString() +" id estado:"+decoTarjeta.getIdEstado());
					log.debug("DEco tarjeta "+decoTarjeta.toEquipo().toString() +" desc:"+decoTarjeta.getDescEstado());
					//TODO: 08/10/2009 - RETA - Ajuste para req. 00029579
					if(infoPostVentaTV.isEsBajaDeco() || infoPostVentaTV.isEsBajaCompleta()){
						if(decoTarjeta.getIdEstado().intValue()!=0)
							continue;
					}else{
						if(!decoTarjeta.estaEnCas())
							continue;	
					}
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjeta.getPrimaryKey () ;
					TR017EEquipment equipment=new TR017EEquipment();
					equipment.setCardSerial(decoTarjetaKey.id_tarjeta) ;
					equipment.setCasId(decoTarjetaKey.id_deco) ;
					if(productoServicioDecoKey!=null)
						equipment.setProductServiceCode(productoServicioDecoKey.ps_id.longValue());
					else
						equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_DEFAULT")).longValue());
					if(operacion_comerciaDecoKey!=null)
						equipment.setCommercialOperation(operacion_comerciaDecoKey.opco_id.longValue());
					else if(operacion_comerciaPcKey!=null)
						equipment.setCommercialOperation(operacion_comerciaPcKey.opco_id.longValue());
					else if(operacion_comerciaTematicoKey!=null)
						equipment.setCommercialOperation(operacion_comerciaTematicoKey.opco_id.longValue());
					else if(operacion_comerciaPaqueteKey!=null)
						equipment.setCommercialOperation(operacion_comerciaPaqueteKey.opco_id.longValue());
					decoTarjeta.setAccion(new Integer(accionParEliminar));
					decoTarjeta.setEstado(null);
					listDecoTarj.add (equipment) ;
				}	
			}
        
			tr017e.setEquipments (listDecoTarj) ;
        
			List listTematicos = new ArrayList () ;
        
			Iterator iterPsTematicos = psTematicos.iterator () ;
			
			//DAVID: Req 30480 Migración TV sin fronteras
			List listaPsMigracionTVSinFront=(List)verificaMigracionPSTVSinFronteras(psTematicos);
			Iterator listaPsMigracionTVSinFrontIt;
			if(listaPsMigracionTVSinFront==null){
            //DAVID: Fin
        
				while (iterPsTematicos.hasNext ())
				{
					Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) iterPsTematicos.next () ;
					PsVsOcVO psVsOcVO=psPaquete.toPsVsOc();
					if(listaNegra.indexOf(psVsOcVO)>=0)
						continue;
					long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
					TR017EPackage package1=new TR017EPackage(); 
					package1.setProductServiceCode (psPaquete.getPsId().longValue()) ;
					package1.setCommercialOperation(psPaquete.getIdOperacionComercial().longValue());
					listTematicos.add (package1) ;
				}
			}//DAVID: Req 30480 Migración TV sin fronteras
			else{
				for (listaPsMigracionTVSinFrontIt=listaPsMigracionTVSinFront.iterator();listaPsMigracionTVSinFrontIt.hasNext ();)
				{
					Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) listaPsMigracionTVSinFrontIt.next () ;
					PsVsOcVO psVsOcVO=psPaquete.toPsVsOc();
					if(listaNegra.indexOf(psVsOcVO)>=0)
						continue;
					long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
					TR017EPackage package1=new TR017EPackage(); 
					
					package1.setProductServiceCode (psPaquete.getPsId().longValue()) ;
					package1.setCommercialOperation(psPaquete.getIdOperacionComercial().longValue());
					listTematicos.add (package1) ;
				}
			}//DAVID: Fin
			tr017e.setPackages (listTematicos) ;
			Mensaje_estado_tvLocal dbmsg ;
        
			dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
        
			dbmsg.setPeticion (peticion) ;
			
//			TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp()) ;
			
			dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
			dbmsg.setCod_actividad_generadora(idActividad);
			// llama a la rutina que envia el mensaje
			new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;
        
			return (idCorrelativoMensaje) ;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (CreateException cex)
		{
			cex.printStackTrace();
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
	}
//	DAVID: Req 30480 Migración TV sin fronteras
	/**
	 * DAVID: este método verifica si en la lista de temáticos cargada en el anterior método "enviaConfiguracionServiciosTVAuto()"
	 * contiene el PS 1242 (TV sin fronteras) con la operación comercial 70 (baja por migración de PS) y cualquier otro PS de la 
	 * familia de paquete de TV (305) con la operación comercial 69 (alta por migración de PS).  Devuelve el listado de PS's que se dan de alta
	 * si la condición se cumple.
	 * @author damartinezv
	 *
	 * TODO Para cambiar la plantilla de este comentario generado, vaya a
	 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
	 */
	private Object verificaMigracionPSTVSinFronteras(List listaTematicos){


			List listaPsMigracionTVSinFront=new ArrayList();
			boolean esBajaTVSinFronteras=false;
			Iterator listaTematicosIt;
			log.debug("El número de temáticos entrantes es: "+listaTematicos.size());
			for(listaTematicosIt=listaTematicos.iterator();listaTematicosIt.hasNext();){
				Producto_servicio_peticionLocal productoServicioPeticion = (Producto_servicio_peticionLocal) listaTematicosIt.next();
				if(productoServicioPeticion.getPsId().intValue()==psTVSinFronteras){
					if(productoServicioPeticion.getIdOperacionComercial().intValue()==bajaMigracionPS){
						log.debug("Viene El PS de TV sin fronteras para OC 70: "+productoServicioPeticion.getPsId().intValue());
						esBajaTVSinFronteras=true;
					}
				}
				else{
					Familia_producto_servicioKey familiaPSKey = productoServicioPeticion.getFamiliaKey();
					if(productoServicioPeticion.getIdOperacionComercial().intValue()==altaMigracionPS
							&&familiaPSKey.faps_id.intValue()==familiaPaqueteTV){
						log.debug("Hay un PS para migración con OC 69: "+productoServicioPeticion.getPsId().intValue());
						listaPsMigracionTVSinFront.add(productoServicioPeticion);
					}
				}
			}
			
			//TODO: 11022010 - Raúl Ernesto Triviño Alvarado - Ajuste error envío ps en casoq ue no sea tvSinFronteras
			if(listaPsMigracionTVSinFront!=null){
				if(listaPsMigracionTVSinFront.isEmpty()||!esBajaTVSinFronteras){
					listaPsMigracionTVSinFront = null;
				}
			}
			//End TODO
			
			log.debug("La lista de Ps's de migraciòn a retornar es: "+listaPsMigracionTVSinFront);
			return listaPsMigracionTVSinFront;
		
	}
	//DAVID: Fin
	
	private ArrayList mergeTematicos(List psPaquetes)
	{
		//Este metodo mete en una lista negra los ps y op comercial que no se envian a configurar.
		ArrayList listaNegra=new ArrayList();
		String opco_tipo = null;
		for(Iterator iterator=psPaquetes.iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal local1=(Producto_servicio_peticionLocal) iterator.next();
			Producto_servicioKey producto_servicioKey1=(Producto_servicioKey) local1.getProducto_servicio().getPrimaryKey();
			Operacion_comercialLocal opCo1=local1.getOperacion_comercial();
			Operacion_comercialKey opCoKey1=(Operacion_comercialKey) opCo1.getPrimaryKey();
			opco_tipo = opCo1.getOpco_tipo();
			if(opco_tipo!=null && opco_tipo.equals("A"))
			{
				for(Iterator iterator2=psPaquetes.iterator();iterator2.hasNext();)
				{
					Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator2.next();
					Producto_servicioKey producto_servicioKey2=(Producto_servicioKey) local2.getProducto_servicio().getPrimaryKey();
					Operacion_comercialLocal opCo2=local2.getOperacion_comercial();
					Operacion_comercialKey opCoKey2=(Operacion_comercialKey) opCo2.getPrimaryKey();
					if(producto_servicioKey1.ps_id.longValue()==producto_servicioKey2.ps_id.longValue() && opCoKey1.opco_id.longValue()==opCoKey2.opco_id.longValue())
						continue;
					if(producto_servicioKey1.ps_id.longValue()==producto_servicioKey2.ps_id.longValue() && opCo2.getOpco_tipo()!=null && opCo2.getOpco_tipo().equals("B"))
					{
						//Los dos van a la lista de Exclusion
						PsVsOcVO psVsOcVO1=new PsVsOcVO();
						psVsOcVO1.setOpComId(opCoKey1.opco_id);
						psVsOcVO1.setPsId(producto_servicioKey1.ps_id);
						PsVsOcVO psVsOcVO2=new PsVsOcVO();
						psVsOcVO2.setOpComId(opCoKey2.opco_id);
						psVsOcVO2.setPsId(producto_servicioKey2.ps_id);
						if(listaNegra.indexOf(psVsOcVO1)<0)
							listaNegra.add(psVsOcVO1);
						if(listaNegra.indexOf(psVsOcVO2)<0)
							listaNegra.add(psVsOcVO2);
					}
				}
			}
			else if(opco_tipo!=null && opco_tipo.equals("B"))
			{
				for(Iterator iterator2=psPaquetes.iterator();iterator2.hasNext();)
				{
					Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator2.next();
					Producto_servicioKey producto_servicioKey2=(Producto_servicioKey) local2.getProducto_servicio().getPrimaryKey();
					Operacion_comercialLocal opCo2=local2.getOperacion_comercial();
					Operacion_comercialKey opCoKey2=(Operacion_comercialKey) opCo2.getPrimaryKey();
					if(producto_servicioKey1.ps_id.longValue()==producto_servicioKey2.ps_id.longValue() && opCoKey1.opco_id.longValue()==opCoKey2.opco_id.longValue())
						continue;
					if(producto_servicioKey1.ps_id.longValue()==producto_servicioKey2.ps_id.longValue() && opCo2.getOpco_tipo()!=null && opCo2.getOpco_tipo().equals("A"))
					{
						//Los dos van a la lista de Exclusion
						PsVsOcVO psVsOcVO1=new PsVsOcVO();
						psVsOcVO1.setOpComId(opCoKey1.opco_id);
						psVsOcVO1.setPsId(producto_servicioKey1.ps_id);
						PsVsOcVO psVsOcVO2=new PsVsOcVO();
						psVsOcVO2.setOpComId(opCoKey2.opco_id);
						psVsOcVO2.setPsId(producto_servicioKey2.ps_id);
						if(listaNegra.indexOf(psVsOcVO1)<0)
							listaNegra.add(psVsOcVO1);
						if(listaNegra.indexOf(psVsOcVO2)<0)
							listaNegra.add(psVsOcVO2);
					}
				}
			}
		}
		return listaNegra;
	}
    

	public void actualizaConfiguracionServiciosTV (TR017S tr017s, boolean esAgendaSC) throws ATiempoAppEx{
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
        try{
        	BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
        	Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
        	
			bExecution = BackendTraceUtil.initExecution(tr017s, log);
			bExecution.setNumPetAtiempo(new Long(tr017s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr017s.getId());
			bExecution.startOperation();
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
   
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
        
        	ArrayList codigoError=new ArrayList(10);
        	ArrayList descErrores=new ArrayList(10);   
        	log.debug("Estoy procesando el tr017s");
        	
        	boolean esVacia = false;
        	
            Mensaje_estado_tvLocal mensajeEstadoTv = buscaMensajeEstadoTv (tr017s.getId ()) ;
            
            if (mensajeEstadoTv == null){
                log.debug ("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall (tr017s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr017s.getId(),ATiempoAppEx.EXCEPTION);
            }
            
            Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoTv.getMensaje_estado ().getPrimaryKey () ;
			PeticionLocal peticion = mensajeEstadoTv.getPeticion () ;
			PeticionKey peticionKey = (PeticionKey) peticion.getPrimaryKey () ;
			InfoPostVentaTV infoPostVentaTV=recuperaInfoTvPostVenta(peticionKey.peti_numero);
            
			if (mensaje_estadoKey.cod_estado.intValue () == estadoOk){
                log.debug ("Es estado de la respuesta es diferente para ser procesado\n "+ XMLUtilities.marshall (tr017s));
                return;
            }
            
			// CR22115 - adocarmo - Inicio
			// Si es una respuesta para una peticion de refresh, ignoro respuesta con error, asumo que la respuesta es ok
			if ((mensaje_estadoKey.cod_estado.intValue () == estadoEspera) && (mensajeEstadoTv.getEs_refresh().intValue() == 1)) {
				log.debug ("Recibí Respuesta del botón Refresh\n "+ XMLUtilities.marshall (tr017s));
				mensajeEstadoTv.setMensaje_estado(buscaMensajeEstadoOk());
				return;
			}
			// CR22115 - adocarmo - Fin			
           			
			Mensaje_estadoLocal mensajeEstadoError = buscaMensajeEstadoError();
			
			// POR QUE SE SETEA EL ESTADO DEL MENSAJE A ERROR???????
			mensajeEstadoTv.setMensaje_estado (mensajeEstadoError);
			
			//TODO: Raúl Triviño: Se adiciona esta modificación, para que no elimine la información de la petición y el id del mensaje de AgendaSC
			if (mensajeEstadoTv.getDesc_error() != null && mensajeEstadoTv.getDesc_error().indexOf("@")== -1){
				mensajeEstadoTv.setDesc_error (tr017s.getErrorMessage()) ;
			}
			
			String cod_actividad_generadora = mensajeEstadoTv.getCod_actividad_generadora();
			
			if(tr017s.getErrors () != null && tr017s.getErrors ().size () > 0){
				Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME);
				Iterator iterDecoTarjetaError = tr017s.getErrors ().iterator () ;
				long code = 0;
				String errorCodeMessage = null;
				while (iterDecoTarjetaError.hasNext()){
					ErrorAux errorDecoTarjeta = (ErrorAux) iterDecoTarjetaError.next();
					
					code = errorDecoTarjeta.getCode ();
					errorCodeMessage = errorDecoTarjeta.getErrorCodeMessage ();
					if (code == 0){
						try{
							Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey (errorDecoTarjeta.getCard (), errorDecoTarjeta.getCasId (), peticionKey) ;
							Deco_tarjetaLocal decoTarjeta = decoTarjetaLocalHome.findByPrimaryKey (decoTarjetaKey) ;

							//	TODO: CR 7304 Inicio - ana 
							Long Opco_id = decoTarjeta.getOpco_id();
							long opParDes = operacionParDesactivarLiberar;
							
							if ( Opco_id != null && Opco_id.longValue() == opParDes){
								decoTarjeta.setCodigo_error (new Integer ((int) code));
								
								if(errorCodeMessage!=null && errorCodeMessage.length()>99)
									errorCodeMessage=errorCodeMessage.substring(0,99);
								
								decoTarjeta.setMensaje_error (errorCodeMessage) ;
								//TODO CAMBIO MGRACION BAJA DECO
								decoTarjeta.setAccion(new Integer(accionParEliminar));
								decoTarjeta.setEstado (new Integer (estadoParOk)) ;
								decoTarjeta.setMarca_hora(new Fecha().getTimestamp());
								//log.debug("Eliminando Decodificador..");
								//decoTarjeta.remove();
								//log.debug("Despues de Eliminando Decodificador");
								actualizaAltaPc(peticionKey.peti_numero);
							}else{
								decoTarjeta.setCodigo_error (new Integer ((int) code)) ;
								
								if(errorCodeMessage!=null && errorCodeMessage.length()>99)
									errorCodeMessage=errorCodeMessage.substring(0,99);
								
								decoTarjeta.setMensaje_error (errorCodeMessage) ;
								decoTarjeta.setEstado (new Integer (estadoParOk)) ;
								decoTarjeta.setMarca_hora(new Fecha().getTimestamp());
								actualizaAltaPc(peticionKey.peti_numero);
							}
							//	TODO: ana CR 7304 - Fin
						}
						catch (FinderException fex){
							log.warn ("peticion " + peticionKey.peti_numero + ", mensaje TV " + tr017s.getId () + ": no puedo encontrar tarjeta/deco " + errorDecoTarjeta.getCard () + "/" + errorDecoTarjeta.getCasId ()) ;
							esVacia = true;
						}
						
						continue ;
					}
					
					else if(code!=0){
						codigoError.add(new Long(code));
						
						if(errorCodeMessage!=null)
							descErrores.add(errorCodeMessage);
					}
					
					Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey (errorDecoTarjeta.getCard (), errorDecoTarjeta.getCasId (), peticionKey) ;

					try{
						Deco_tarjetaLocal decoTarjeta = decoTarjetaLocalHome.findByPrimaryKey (decoTarjetaKey) ;

						decoTarjeta.setCodigo_error (new Integer ((int) code)) ;
						String msg=errorCodeMessage;
						
						if(msg!=null && msg.length()>99)
							msg=msg.substring(0,99);
						
						decoTarjeta.setMensaje_error (msg) ;
						decoTarjeta.setEstado (new Integer (estadoParNoOk)) ;
						
						if(decoTarjeta.esEliminacionFallida() &&  (cod_actividad_generadora==null || cod_actividad_generadora.equals(""))){
							decoTarjeta.setAccion(new Integer(accionParActivar));
							decoTarjeta.setEstado(new Integer(estadoParOk));
							msg="Eliminacion Fallida:"+errorCodeMessage;
							
							if(msg!=null && msg.length()>99)
								msg=msg.substring(0,99);
							
							decoTarjeta.setMensaje_error (msg) ;
						}
						
						decoTarjeta.setMarca_hora(new Fecha().getTimestamp());
					}
					catch (FinderException fex){
						log.warn ("peticion " + peticionKey.peti_numero + ", mensaje TV " + tr017s.getId () + ": no puedo encontrar tarjeta/deco " + errorDecoTarjeta.getCard () + "/" + errorDecoTarjeta.getCasId ()) ;
						esVacia = true;
						code = estadoParNoOk;
					}
				}	
			}
			
			if(esVacia && (tr017s.isError() || codigoError.size()>0)){
				Collection decosPet = peticion.getDeco_tarjeta();
				for(Iterator iter = decosPet.iterator();iter.hasNext();){
					long code = 1;
					String errorCodeMessage = tr017s.getErrorMessage();
					Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iter.next();
					
					decoTarjeta.setCodigo_error (new Integer ((int) code)) ;
					decoTarjeta.setMensaje_error(errorCodeMessage);
					decoTarjeta.setEstado(new Integer (estadoParNoOk));
					codigoError.add(new Long(code));
				}
			}
			if(cod_actividad_generadora!=null && !cod_actividad_generadora.equals("")){
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
							
				ActividadEJBDTO actDto = null;
				IActividadEJB actividadEJB = null;
				
				if (esAgendaSC && cod_actividad_generadora.equals(ComunInterfaces.ACT_CTRL_INSTALACION)){
					BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByIdActIdPetiAp(new Long(idActividadInstalar),peticionKey.peti_numero,idAplicacion);
					
					String idCorrelativo=bintegradaLocal.getBi_url_detalle();
					int idInicio=idCorrelativo.indexOf("instanciaActividad");
					
					if(idInicio!=-1){
						idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
						int idFin=idCorrelativo.indexOf("&");
						if(idFin!=-1){
							idCorrelativo=idCorrelativo.substring(19,idFin);
						}
					}
					/**
					 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
					 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
					 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
					 * 
					 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
					 * tabla bintegrada, y se deben reconvertir a + o /.
					 */
					idCorrelativo=idCorrelativo.replaceAll("%2B","+");
					idCorrelativo=idCorrelativo.replaceAll("%2F","/");
					
					actividadEJB = actividadFactoryEJB.getActividad (cod_actividad_generadora) ;
					actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, cod_actividad_generadora, idCorrelativo,null);
					
					//actividadEJB = actividadFactoryEJB.getActividad (cod_actividad_generadora) ;
					//actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, cod_actividad_generadora) ;
				}else{
					actividadEJB = actividadFactoryEJB.getActividad (cod_actividad_generadora) ;
					actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, cod_actividad_generadora) ;
				}


				if(tr017s.isError() || codigoError.size()>0){
					
					Long errorCode = (Long)codigoError.get(0);
					String nombreClase=TR017S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(errorCode.toString(),nombreClase);
					
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						String descripcionError = "";
						if(!descErrores.isEmpty() && descErrores.get(0)!=null){
							descripcionError = descErrores.get(0).toString();
						}
						if(errorLegado.getPlataforma() == null || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+descripcionError);
							actividadEJB.terminar (actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma();
						bandeja = getNombreBandeja(plataforma);
					}
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					String descTot="";
					if(descErrores.size()>0){
						for(Iterator iterator=descErrores.iterator();iterator.hasNext();){
							String desc=(String) iterator.next();
							descTot+=desc;
							if(iterator.hasNext())
								descTot+=",";
						}
					}
					
					if (esAgendaSC){
						actDto.setObservacion("Error en la Actividad de Configuracion DTH.Se deriva a PGI."+sinNull(tr017s.getErrorMessage())+"."+descTot, false);
					}else if (peticionesDelegate.tieneCodActividadNoAvance(cod_actividad_generadora)){
				    		actDto.setObservacion("Error en peticiones al vuelo, en la Actividad de Configuracion DTH."+sinNull(tr017s.getErrorMessage())+"."+descTot, false);
					    	this.reversarProductoPeticionEnVuelo(peticionKey.peti_numero);
				    }else{
				    	actDto.setObservacion("Error en la Actividad de Configuracion DTH.Se deriva a PGI."+sinNull(tr017s.getErrorMessage())+"."+descTot);
				    }
				    	
//					agonz 27/06/2008
					PeticionLocal peticionLocal = mensajeEstadoTv.getPeticion();
					
					//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR007S.class.getName());
					if (!peticionesDelegate.tieneCodActividadNoAvance(cod_actividad_generadora)){
						if(errorLegado != null){
							peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
							insertarCausalesCnaPeticion(mensajeEstadoTv, cod_actividad_generadora, errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
						}else {
							insertarCausalesCnaPeticion(mensajeEstadoTv, cod_actividad_generadora, new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
						}
					}
// 					fin agregado
					
					//insertarCausalesCnaPeticion(mensajeEstadoTv, mensajeEstadoTv.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					if(infoPostVentaTV.isEsBajaDeco() && !infoPostVentaTV.isEsBajaCompleta()){
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"N");
						peticion.setPeti_causal_baja(desconfiguracionInCorrecta);
					}	
				}
				else{
					actDto.setObservacion("Configuracion de Servicios TV realizada Satisfactoriamente");
					if(infoPostVentaTV.isEsBajaDeco() && !infoPostVentaTV.isEsBajaCompleta()){
						peticion.setPeti_causal_baja(desconfiguracionCorrecta);
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"S");
					}
				}
				
				if (peticionesDelegate.tieneCodActividadNoAvance(cod_actividad_generadora) || esAgendaSC){
					if (mensajeEstadoTv.getDesc_error() == null || mensajeEstadoTv.getDesc_error().indexOf("@")!= -1){
						actividadEJB.terminar (actDto) ;
					}else{
						actividadEJB.grabarSinTerminar(actDto);
					}
				}else{
					
					actividadEJB.terminar (actDto) ;
				}
			}
        }catch (NamingException e){
        	bExecution.setErrorOp(true);
        	log.error(" Creacion de Local Home Nulls",e);
        	throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
        }catch (NumberFormatException e){
			bExecution.setErrorOp(true);
        	log.debug("NumberFormatException",e);
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT,e);
        }catch (TnProcesoExcepcion e){
			bExecution.setErrorOp(true);
			log.debug("TnProcesoExcepcion",e);
			throw new ATiempoAppEx (ATiempoAppEx.TN_PROCESS,e);
		}
		catch (Exception e){
			bExecution.setErrorOp(true);
			log.debug("Excepcion",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION,e);
		}finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
		}
    }
    
	private void actualizaAltaPc(Long petiNumero) throws ATiempoAppEx
	{
		try
		{
			AltapctvLocalHome altapctvHome=(AltapctvLocalHome) HomeFactory.getHome(AltapctvLocalHome.JNDI_NAME);
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			try
			{
				AltapctvLocal local=altapctvHome.findByPrimaryKey(new AltapctvKey(new PeticionKey(petiNumero)));
				return;
			}
			catch(FinderException e)
			{
				PeticionKey peticionKey=new PeticionKey(petiNumero);
				PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(peticionKey);
				AltapctvLocal local=altapctvHome.create(peticionLocal);
				local.setMarca_hora(new Fecha().getTimestamp());
			}
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}


	public Long enviaActualizacionInventarioTV (long idPeticion, String idActividad)
    throws ATiempoAppEx
    {
        //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
        try
        {
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
        	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
        	Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
            // obtiene un nuevo id de mensaje
            
            Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
            
            // obtiene el id de la peticion Atis
            
            PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            
            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            
            long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            
            // el ps tipo PC
            InfoPostVentaTV infoPostVentaTV=recuperaInfoTvPostVenta(new Long(idPeticion));
            
            Producto_servicio_peticionLocal productoServicioPeticionPcTV = buscaPsPetTipoPc (peticion) ;
            
//            if (productoServicioPeticionPcTV == null)
//                throw new ATiempoAppEx ("peticion: " + idPeticion + " no encontre el PS tipo PC (familia PC TV)") ;
            
            Long idPsPcc =null;
            if(productoServicioPeticionPcTV!=null)
            	idPsPcc=((Producto_servicioKey) productoServicioPeticionPcTV.getProducto_servicio ().getPrimaryKey ()).ps_id ;
            
            //
            
            TR018E tr018e = new TR018E () ;
            
            tr018e.setId (idCorrelativoMensaje.toString ()) ;
            
            //
            tr018e.setAtisRequestNumber (peticionAtis) ;
            
            //
            tr018e.setPcId (peticion.getNum_ide_nu_tv()) ;
            
            List decos = new ArrayList (10) ;
            tr018e.setEquipments (decos) ;
            
            List tarjetas = new ArrayList (10) ;
            tr018e.setCards (tarjetas) ;
			
			Iterator iterDecoTarj=null;
			Iterator iterDecoTarj2=null;
            
            // busca los deco / tarjeta
            if(!infoPostVentaTV.isEsBajaDeco() && !infoPostVentaTV.isEsBajaCompleta())
            {
				iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;
				iterDecoTarj2 = peticion.getDeco_tarjeta ().iterator () ;

				ArrayList sDecosActivar=new ArrayList();
				ArrayList sDecosEliminar=new ArrayList();
				ArrayList sTarjesActivar=new ArrayList();
				ArrayList sTarjesEliminar=new ArrayList();
				Long opco_id = null;
				while (iterDecoTarj.hasNext ())
				{
					Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
					Deco_tarjetaKey dt1=(Deco_tarjetaKey) decoTarjeta.getPrimaryKey();
					if(!decoTarjeta.estaEliminado())
						continue;

					opco_id = decoTarjeta.getOpco_id();
					if(opco_id!=null && opco_id.intValue()== 41 )
						continue;
					while (iterDecoTarj2.hasNext ())
					{
						Deco_tarjetaLocal otra = (Deco_tarjetaLocal) iterDecoTarj2.next () ;
						Deco_tarjetaKey dt2=(Deco_tarjetaKey) otra.getPrimaryKey();
						//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 18, 2009
						//Se corrigió lógica
						//if(dt1.id_deco.equals(dt2.id_deco) && dt2.id_tarjeta.equals(dt2.id_tarjeta))
						if(dt1.id_deco.equals(dt2.id_deco) && dt1.id_tarjeta.equals(dt2.id_tarjeta))
							continue;
						if(dt1.id_deco.equals(dt2.id_deco))
						{
							if(otra.estaActivo())
								sDecosActivar.add(dt1.id_deco);
						}
						if(dt1.id_tarjeta.equals(dt2.id_tarjeta))
						{
							if(otra.estaActivo())
								sTarjesActivar.add(dt1.id_tarjeta);
						}
					}
					if(!sDecosActivar.contains(dt1.id_deco))
						sDecosEliminar.add(decoTarjeta);
					if(!sTarjesActivar.contains(dt1.id_tarjeta))
						sTarjesEliminar.add(dt1.id_tarjeta);
				}

				iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;

				while (iterDecoTarj.hasNext ())
				{
					Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
					Deco_tarjetaKey dt1=(Deco_tarjetaKey) decoTarjeta.getPrimaryKey();
					if(!decoTarjeta.estaActivo())
						continue;
	
					if(!sDecosActivar.contains(dt1.id_deco))
						sDecosActivar.add(decoTarjeta);
					if(!sTarjesActivar.contains(dt1.id_tarjeta))
						sTarjesActivar.add(dt1.id_tarjeta);
				}

				iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;
				while (iterDecoTarj.hasNext ())
				{
					Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
					Deco_tarjetaKey dt1=(Deco_tarjetaKey) decoTarjeta.getPrimaryKey();
					if(!decoTarjeta.esAccionDanhado() && !decoTarjeta.esAccionNoRecuperado() && !decoTarjeta.esEsperaDeEliminacion())
						continue;
					Cards tarjeta = new Cards () ;
					TR018EEquipment equipment=new TR018EEquipment();
					tarjeta.setAction(decoTarjeta.getAccion().intValue());
					tarjeta.setCardSerial(dt1.id_tarjeta);
					/*RQ.8595 - mfmendez*/
					tarjeta = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, tarjeta, dt1.id_tarjeta, new Long (idPeticion));
					equipment.setAction(decoTarjeta.getAccion().intValue());
					equipment.setCasId(dt1.id_deco);
					equipment.setDdtvSerial(decoTarjeta.getSerial_ddtv());
					if(decoTarjeta.getMarca_ddtv().length() > 30){
						equipment.setDdtvMarca(decoTarjeta.getMarca_ddtv().substring(0,29));	
					}else{
						equipment.setDdtvMarca(decoTarjeta.getMarca_ddtv());
					}
					if(decoTarjeta.getModelo_ddtv().length() > 30){
						equipment.setDdtvModelo(decoTarjeta.getModelo_ddtv().substring(0,29));	
					}else{
						equipment.setDdtvModelo(decoTarjeta.getModelo_ddtv());
					}
					
					/*RQ.8595 - mfmendez*/
					equipment = this.setDatosSAPEquipo(deco_tar_inf_sapLocalHome, equipment, dt1.id_deco, new Long (idPeticion));
					tarjetas.add(tarjeta);
					decos.add(equipment);
				}

				for(Iterator iterator=sDecosEliminar.iterator();iterator.hasNext();)
				{
					Deco_tarjetaLocal decoTarjeta=(Deco_tarjetaLocal) iterator.next();
					TR018EEquipment equipment=new TR018EEquipment();
					Deco_tarjetaKey decoKey=(Deco_tarjetaKey) decoTarjeta.getPrimaryKey();
					equipment.setAction(accionParEliminar);
					equipment.setCasId(decoKey.id_deco);
					equipment.setDdtvSerial(decoTarjeta.getSerial_ddtv());
					equipment.setDdtvMarca(decoTarjeta.getMarca_ddtv());
					equipment.setDdtvModelo(decoTarjeta.getModelo_ddtv());
					/*RQ.8595 - mfmendez*/
					decos.add (equipment);
				}

				for(Iterator iterator=sTarjesEliminar.iterator();iterator.hasNext();)
				{
					String idTarj=(String) iterator.next();
					Cards tarjeta = new Cards () ;
					tarjeta.setAction (accionParEliminar) ;
					tarjeta.setCardSerial (idTarj) ;
					/*RQ.8595 - mfmendez*/
					tarjeta = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, tarjeta, idTarj, new Long (idPeticion));
					tarjetas.add (tarjeta) ;
				}


				for(Iterator iterator=sDecosActivar.iterator();iterator.hasNext();)
				{
					Deco_tarjetaLocal decoTarjeta=(Deco_tarjetaLocal) iterator.next();
					TR018EEquipment equipment=new TR018EEquipment();
					Deco_tarjetaKey decoKey=(Deco_tarjetaKey) decoTarjeta.getPrimaryKey();
					equipment.setAction(accionParActivar);
					equipment.setCasId(decoKey.id_deco);
					equipment.setDdtvSerial(decoTarjeta.getSerial_ddtv());
					equipment.setDdtvMarca(decoTarjeta.getMarca_ddtv());
					equipment.setDdtvModelo(decoTarjeta.getModelo_ddtv());
					/*RQ.8595 - mfmendez*/
					equipment = this.setDatosSAPEquipo(deco_tar_inf_sapLocalHome, equipment, decoKey.id_deco, new Long (idPeticion));
					decos.add (equipment);
				}

				for(Iterator iterator=sTarjesActivar.iterator();iterator.hasNext();)
				{
					String idTarj=(String) iterator.next();
					Cards tarjeta = new Cards () ;
					tarjeta.setAction (accionParActivar) ;
					tarjeta.setCardSerial (idTarj) ;
					/*RQ.8595 - mfmendez*/
					tarjeta = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, tarjeta, idTarj, new Long (idPeticion));
					tarjetas.add (tarjeta) ;
				}
            }
            else if(infoPostVentaTV.isEsBajaDeco() && !infoPostVentaTV.isEsBajaCompleta())
			{
				iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;
				int intValue = 0;
				while (iterDecoTarj.hasNext ())
				{
					Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
					if(decoTarjeta.estaEnCas())
						continue;
					Deco_tarjetaKey deco_tarjetaKey=(Deco_tarjetaKey) decoTarjeta.getPrimaryKey();
					TR018EEquipment equipment=new TR018EEquipment();
					intValue = decoTarjeta.getAccion().intValue();
					equipment.setAction(intValue);
					equipment.setCasId(deco_tarjetaKey.id_deco);
					/*RQ.8595 - mfmendez*/
					equipment = this.setDatosSAPEquipo(deco_tar_inf_sapLocalHome, equipment, deco_tarjetaKey.id_deco, new Long (idPeticion));
					decos.add (equipment);
					Cards tarjeta = new Cards () ;
					tarjeta.setAction (intValue) ;
					tarjeta.setCardSerial (deco_tarjetaKey.id_tarjeta) ;
					/*RQ.8595 - mfmendez*/
					tarjeta = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, tarjeta, deco_tarjetaKey.id_tarjeta, new Long (idPeticion));
					tarjetas.add (tarjeta) ;
				}
			}
			else if(infoPostVentaTV.isEsBajaCompleta())
			{
				iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;
				int intValue = 0;
				while (iterDecoTarj.hasNext ())
				{
					Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
					
					if(decoTarjeta.getAccion()==null)
						continue;	
									
					Deco_tarjetaKey deco_tarjetaKey=(Deco_tarjetaKey) decoTarjeta.getPrimaryKey();
					TR018EEquipment equipment=new TR018EEquipment();
					intValue = decoTarjeta.getAccion().intValue();
					equipment.setAction(intValue);
					equipment.setCasId(deco_tarjetaKey.id_deco);
					/*RQ.8595 - mfmendez*/
					equipment = this.setDatosSAPEquipo(deco_tar_inf_sapLocalHome, equipment, deco_tarjetaKey.id_deco, new Long (idPeticion));
					decos.add (equipment);
					Cards tarjeta = new Cards () ;
					tarjeta.setAction (intValue) ;
					tarjeta.setCardSerial (deco_tarjetaKey.id_tarjeta) ;
					/*RQ.8595 - mfmendez*/
					tarjeta = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, tarjeta, deco_tarjetaKey.id_tarjeta, new Long (idPeticion));
					tarjetas.add (tarjeta) ;
				}
			}
			
            // guarda el registro del mensaje
            Mensaje_estado_tvLocal dbmsg ;
            
            dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
            
            dbmsg.setPeticion (peticion) ;
            
			//TODO CR10356- agonzalez cambio de tipo de String a Date
            //dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp()) ;
            
            dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
            dbmsg.setCod_actividad_generadora (idActividad) ;
            
            if(idActividad!=null && idActividad.equals("InvCI"))
            {
            	
            		Inventario_dthLocalHome inventario_dthHome=(Inventario_dthLocalHome) HomeFactory.getHome(Inventario_dthLocalHome.JNDI_NAME);
				Inventario_dthLocal local=null;
            	try
            	{
					local=inventario_dthHome.findByPrimaryKey(new Inventario_dthKey(new Long(idPeticion)));
					local.setEstado("2");//espera
					local.setMarca_hora(new Fecha().getTimestamp());
					local.setDescripcion(null);
            	}
            	catch(FinderException fe)
            	{
            		local=inventario_dthHome.create(peticion);
            	}
            }
            
            // llama a la rutina que envia el mensaje
            new ActualizacionInventarioTVMQ ().enviarMensaje (tr018e) ;
            
            return (idCorrelativoMensaje) ;
        }
        catch (NumberFormatException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
        catch (FinderException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.FINDER);
        }
        catch (CreateException cex)
        {
            log.error ("error creando registro", cex) ;
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        }
        catch (NamingException e)
        {
        	log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING);
		}
    }
	
	/*RQ.8595 - mfmendez*/
	/**
	 * Metodo para completar los datos de SAP para un Deco de tipo TR018EEquipment
	 * @param deco_tar_inf_sapLocalHome, localHome de la tabla DECO_TARJETA_INFO_SAP
	 * @param equipment, el equipo que ya tiene seteados algunos datos
	 * @param idDeco, id del Deco que se debe buscar en base de datos
	 * @param idPeticion, id de la peticion para buscar el equipo correspondiente
	 * @return el equipo que entro con los datos de SAP seteados
	 */
	private TR018EEquipment setDatosSAPEquipo(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, TR018EEquipment equipment, String idDeco, Long idPeticion){
		Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
		Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
		
		/*RQ.8595 - mfmendez*/
		// Datos del Deco
		try{			
			keyInfoSAPTmp = new Deco_Tarjeta_Info_SapKey(idDeco, idPeticion);
			infoSAPTmp = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPTmp);
			
			if(infoSAPTmp.getFec_cont_sap() != null)
				equipment.setPostingDateSAP(infoSAPTmp.getFec_cont_sap());
			else
				equipment.setPostingDateSAP("");
			
			if(infoSAPTmp.getClase_mov_sap() != null)
				equipment.setMoveTypeSAP(infoSAPTmp.getClase_mov_sap());
			else
				equipment.setMoveTypeSAP("");
			
			equipment.setMaterialCodeSAP(Integer.toString(infoSAPTmp.getPos_doc_sap()));
			
			if(infoSAPTmp.getNum_material_sap() != null)
				equipment.setMaterialSAP(infoSAPTmp.getNum_material_sap());
			else
				equipment.setMaterialSAP("");
			
			if(infoSAPTmp.getCentro_sap() != null)
				equipment.setPlantSAP(infoSAPTmp.getCentro_sap());
			else
				equipment.setPlantSAP("");
			
			if(infoSAPTmp.getAlmacen_sap() != null)
				equipment.setStorageSAP(infoSAPTmp.getAlmacen_sap());
			else
				equipment.setStorageSAP("");
			
			if(infoSAPTmp.getCod_lote_sap() != null)
				equipment.setBatchCodeSAP(infoSAPTmp.getCod_lote_sap());
			else
				equipment.setBatchCodeSAP("");
			
			if(infoSAPTmp.getUnd_medida_sap() != null)
				equipment.setMeasurementUnitSAP(infoSAPTmp.getUnd_medida_sap());
			else
				equipment.setMeasurementUnitSAP("");
			
			if(infoSAPTmp.getCentr_cost_sap() != null)
				equipment.setCostCenterSAP(infoSAPTmp.getCentr_cost_sap());
			else
				equipment.setCostCenterSAP("");
			
			if(infoSAPTmp.getArea_func_sap() != null)
				equipment.setFuncAreaLongSAP(infoSAPTmp.getArea_func_sap());
			else
				equipment.setFuncAreaLongSAP("");
			
			if(infoSAPTmp.getElement_pep_sap() != null)
				equipment.setPepElementSAP(infoSAPTmp.getElement_pep_sap());
			else
				equipment.setPepElementSAP("");
			
			if(infoSAPTmp.getFlag_mat_sap() != null)
				equipment.setFlagMatSAP(infoSAPTmp.getFlag_mat_sap());
			else
				equipment.setFlagMatSAP("");
			
		} catch (FinderException e) {
			log.debug("No se encontraron Decos para deco con id: "+idDeco+". Y id de peticion: "+idPeticion);
			equipment.setPostingDateSAP("");
			equipment.setMoveTypeSAP("");
			equipment.setMaterialCodeSAP("");
			equipment.setMaterialSAP("");
			equipment.setPlantSAP("");
			equipment.setStorageSAP("");
			equipment.setBatchCodeSAP("");
			equipment.setMeasurementUnitSAP("");
			equipment.setCostCenterSAP("");
			equipment.setFuncAreaLongSAP("");
			equipment.setPepElementSAP("");
			equipment.setFlagMatSAP("");
		} catch (Exception e) {
			log.error("RecursosTVServiciosBean: setDatosSAPEquipo: Se presento Error seteando los datos de SAP para un Deco. "+e);
		}
		/*FIN - RQ.8595 - mfmendez*/
		
		return equipment;
	}
	
	/**
	 * Metodo para completar los datos de SAP para una tarjeta de tipo Cards}
	 * @param deco_tar_inf_sapLocalHome, localHome de la tabla DECO_TARJETA_INFO_SAP
	 * @param tarjeta
	 * @param idCard
	 * @param idPeticion
	 * @return
	 */
	private Cards setDatosSAPTarjeta(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, Cards tarjeta, String idCard, Long idPeticion){
		Deco_Tarjeta_Info_SapLocal infoSAPCard = null;
		Deco_Tarjeta_Info_SapKey keyInfoSAPCard = null;
		
		/*RQ.8595 - mfmendez*/
		// Datos de la tarjeta
		try{
			keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(idCard, idPeticion);
			infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
			
			if(infoSAPCard.getFec_cont_sap() != null)
				tarjeta.setPostingDateSAP(infoSAPCard.getFec_cont_sap());
			else
				tarjeta.setPostingDateSAP("");
			
			if(infoSAPCard.getClase_mov_sap() != null)
				tarjeta.setMoveTypeSAP(infoSAPCard.getClase_mov_sap());
			else
				tarjeta.setMoveTypeSAP("");
			
			tarjeta.setMaterialCodeSAP(Integer.toString(infoSAPCard.getPos_doc_sap()));
			
			if(infoSAPCard.getNum_material_sap() != null)
				tarjeta.setMaterialSAP(infoSAPCard.getNum_material_sap());
			else
				tarjeta.setMaterialSAP("");
			
			if(infoSAPCard.getCentro_sap() != null)
				tarjeta.setPlantSAP(infoSAPCard.getCentro_sap());
			else
				tarjeta.setPlantSAP("");
			
			if(infoSAPCard.getAlmacen_sap() != null)
				tarjeta.setStorageSAP(infoSAPCard.getAlmacen_sap());
			else
				tarjeta.setStorageSAP("");
			
			if(infoSAPCard.getCod_lote_sap() != null)
				tarjeta.setBatchCodeSAP(infoSAPCard.getCod_lote_sap());
			else
				tarjeta.setBatchCodeSAP("");
			
			if(infoSAPCard.getUnd_medida_sap() != null)
				tarjeta.setMeasurementUnitSAP(infoSAPCard.getUnd_medida_sap());
			else
				tarjeta.setMeasurementUnitSAP("");
			
			if(infoSAPCard.getCentr_cost_sap() != null)
				tarjeta.setCostCenterSAP(infoSAPCard.getCentr_cost_sap());
			else
				tarjeta.setCostCenterSAP("");
			
			if(infoSAPCard.getArea_func_sap() != null)
				tarjeta.setFuncAreaLongSAP(infoSAPCard.getArea_func_sap());
			else
				tarjeta.setFuncAreaLongSAP("");
			
			if(infoSAPCard.getElement_pep_sap() != null)
				tarjeta.setPepElementSAP(infoSAPCard.getElement_pep_sap());
			else
				tarjeta.setPepElementSAP("");
			
			if(infoSAPCard.getFlag_mat_sap() != null)
				tarjeta.setFlagMatSAP(infoSAPCard.getFlag_mat_sap());
			else
				tarjeta.setFlagMatSAP("");
			
		}catch (FinderException e) {
			log.debug("No se encontraron Tarjetas para Card con id: "+idCard+". Y id de peticion: "+idPeticion);
			tarjeta.setPostingDateSAP("");
			tarjeta.setMoveTypeSAP("");
			tarjeta.setMaterialCodeSAP("");
			tarjeta.setMaterialSAP("");
			tarjeta.setPlantSAP("");
			tarjeta.setStorageSAP("");
			tarjeta.setBatchCodeSAP("");
			tarjeta.setMeasurementUnitSAP("");
			tarjeta.setCostCenterSAP("");
			tarjeta.setFuncAreaLongSAP("");
			tarjeta.setPepElementSAP("");
			tarjeta.setFlagMatSAP("");
		} catch (Exception e) {
			log.error("RecursosTVServiciosBean: setDatosSAPTarjeta: Se presento Error seteando los datos de SAP para una Tarjeta. "+e);
		}			
		/*FIN - RQ.8595 - mfmendez*/
		
		return tarjeta;
	}
	/*FIN - RQ.8595 - mfmendez*/
    
    /**
     * procesa la respuesta de actualizacion de inventario TV
     */
    
    public void recibeActualizacionInventarioTV (TR018S tr018s)
    throws ATiempoAppEx
    {
        
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
        try
        {
			bExecution = BackendTraceUtil.initExecution(tr018s, log);
			bExecution.setNumPetAtiempo(new Long(tr018s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr018s.getId());
			bExecution.startOperation();
 
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
            // busca el registro del mensaje
            
            Mensaje_estado_tvLocal mensajeEstadoTv = buscaMensajeEstadoTv (tr018s.getId ()) ;
            
            if (mensajeEstadoTv == null)
            {
                log.debug (
                    "No Existe Respuesta en la base de enviados\n "
                    + XMLUtilities.marshall (tr018s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr018s.getId(),ATiempoAppEx.EXCEPTION);
            }
            
            // Validacion del estado de la respuesta
            
            
            Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoTv.getMensaje_estado ().getPrimaryKey () ;
            
            if (mensaje_estadoKey.cod_estado.intValue () == estadoOk)
            {
                log.debug (
                    "Es estado de la respuesta es diferente para ser procesado\n "
                    + XMLUtilities.marshall (tr018s));
                return;
            }
            
            Long idPeticion = ((PeticionKey) mensajeEstadoTv.getPeticion ().getPrimaryKey ()).peti_numero ;
            
            if (tr018s.isError ())
            {
                Mensaje_estadoLocal mensajeEstadoError = buscaMensajeEstadoError () ;
                
                mensajeEstadoTv.setMensaje_estado (mensajeEstadoError) ;
                
                // este tr018s, si tiene el error code.  (el tr017s no lo tiene)
                mensajeEstadoTv.setId_error (Long.toString (tr018s.getErrorCode ())) ;
                
                // pero tiene tambien tr018s.getErrorCodeMessage (): se concatena al
                mensajeEstadoTv.setDesc_error (tr018s.getErrorMessage () + " - " + tr018s.getErrorCodeMessage ()) ;
            }
            else
            {
                // el mensaje se proceso OK (ver el
                
                Mensaje_estadoLocal mensajeEstadoOk = buscaMensajeEstadoOk () ;
                
                mensajeEstadoTv.setMensaje_estado (mensajeEstadoOk) ;
            }
            
            String cod_actividad_generadora = mensajeEstadoTv.getCod_actividad_generadora();
			if(cod_actividad_generadora!=null && cod_actividad_generadora.equals("InvCI"))
            {
            	Inventario_dthLocalHome home=(Inventario_dthLocalHome) HomeFactory.getHome(Inventario_dthLocalHome.JNDI_NAME);
            	try
            	{
					Inventario_dthLocal local=home.findByPrimaryKey(new Inventario_dthKey(new PeticionKey(idPeticion)));
					if(tr018s.isError() || tr018s.getErrorCode()!=0)
					{
						local.setEstado("0");
						local.setDescripcion(sinNull(tr018s.getErrorMessage())+sinNull(tr018s.getErrorCodeMessage()));
					}
					else
					{
						local.setEstado("1");
						local.setDescripcion("Actualizacion de Inventario DTH Realizada Correctamente");	
					}
				}
				catch (FinderException e1)
				{
					log.debug("No se pudo encontrar el registro correspondiente de act. inv. dth para la peticion:"+idPeticion);
				}
            }
            else if(cod_actividad_generadora!=null)
            {
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (cod_actividad_generadora) ;
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(idPeticion, cod_actividad_generadora) ;
				if(tr018s.isError() || tr018s.getErrorCode()!=0)
				{
//					agregado por agonz
					PeticionLocal peticionLocal = mensajeEstadoTv.getPeticion();
					

					//Iterator iterator=tr018s.getErrors().iterator();
					String codError = String.valueOf(tr018s.getErrorCode());
					String nombreClase=TR018S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr018s.getErrorMessage());
							actividadEJB.terminar (actDto);
							return;
						}
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						plataforma = errorLegado.getPlataforma();
						bandeja = getNombreBandeja(plataforma);
						insertarCausalesCnaPeticion(mensajeEstadoTv, cod_actividad_generadora, errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else {
						insertarCausalesCnaPeticion(mensajeEstadoTv, cod_actividad_generadora, new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}
//					fin agregado
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actDto.setObservacion("Error en la Actividad de Actualizacion Inventario DTH.Se deriva a "+bandeja+"."+sinNull(tr018s.getErrorMessage())+" . "+sinNull(tr018s.getErrorCodeMessage()));
				}
				else
					actDto.setObservacion("Actualizacion de Inventario DTH Realizada Correctamente");
				actividadEJB.terminar (actDto) ;	
            }
        }
        catch (NumberFormatException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT) ;
        }
        catch (TnProcesoExcepcion e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.TN_PROCESS) ;
        } catch (NamingException e)
        {
			bExecution.setErrorOp(true);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
		}
		catch (Exception e)
	   {
			bExecution.setErrorOp(true);
		   throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
	   }
	   finally{  
		   bExecution.endAll();
		   // CR15338 - @Trace - ana santos - 04/08 - FIN 
    }
    
    }
    
    /**
     * envia el mensaje de solicitud de informacion tecnica del servicio TV
     *
     * @param idPeticion el id de la peticion
     * @param idActividad id de la actividad automatica que solicita el mensaje
     */
    
    public Long enviaSolicitudInformacionTecnicaTV (long idPeticion, String idActividad)
    throws ATiempoAppEx
    {
        try
        {
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
        	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
        	
            // obtiene un nuevo id de mensaje
            
            Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
            
            // obtiene el id de la peticion Atis
            
            PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            
            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            
            long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            
            
            TR019E tr019e = new TR019E () ;
            
            tr019e.setId (idCorrelativoMensaje.toString ()) ;
            
            //
            tr019e.setAtisRequestNumber (peticionAtis) ;
            
            log.debug("El identificador de Pc que tengo en la peticion es:"+peticion.getNum_ide_nu_tv());
            tr019e.setPcId (peticion.getNum_ide_nu_tv()) ;
            
            // guarda el registro del mensaje
            Mensaje_estado_tvLocal dbmsg ;
            
            dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
            
            dbmsg.setPeticion (peticion) ;
            
//			TODO CR10356- agonzalez cambio de tipo de String a Date
            //dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp()) ;
            
            dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
            dbmsg.setCod_actividad_generadora (idActividad) ;
            
            // llama a la rutina que envia el mensaje
            new SolicitudInformacionTecnicaTV ().enviarMensaje (tr019e) ;
            
            return (idCorrelativoMensaje) ;
        }catch (NamingException e)
		{
        	log.error(" Creacion de Local Home Nulls",e);
        	throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
        }
        catch (NumberFormatException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
        catch (FinderException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.FINDER);
        }
        catch (CreateException cex)
        {
            log.error ("error creando registro", cex) ;
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        }
    }
    
    /**
     * procesa la respuesta de la solicitud de informacion tecnica TV
     */
    
    public void recibeSolicitudInformacionTecnicaTV (TR019S tr019s)
    throws ATiempoAppEx
    {
        
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
        try
        {
			bExecution = BackendTraceUtil.initExecution(tr019s, log);
			bExecution.setNumPetAtiempo(new Long(tr019s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr019s.getId());
			bExecution.startOperation();
 
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
            // busca el registro del mensaje
            
            Mensaje_estado_tvLocal mensajeEstadoTv = buscaMensajeEstadoTv (tr019s.getId ()) ;
            
            if (mensajeEstadoTv == null)
            {
                log.debug (
                    "No Existe Respuesta en la base de enviados\n "
                    + XMLUtilities.marshall (tr019s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr019s.getId(),ATiempoAppEx.EXCEPTION);
            }
            
            // Validacion del estado de la respuesta
            
            
            Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoTv.getMensaje_estado ().getPrimaryKey () ;
            
            if (mensaje_estadoKey.cod_estado.intValue () == estadoOk)
            {
                log.debug (
                    "El estado de la respuesta es diferente para ser procesado\n "
                    + XMLUtilities.marshall (tr019s));
                return;
            }
            
            Long idPeticion = ((PeticionKey) mensajeEstadoTv.getPeticion ().getPrimaryKey ()).peti_numero ;
            
            if (tr019s.isError ()  || tr019s.getErrorCode()!=0)
            {
                Mensaje_estadoLocal mensajeEstadoError = buscaMensajeEstadoError () ;
                mensajeEstadoTv.setMensaje_estado (mensajeEstadoError) ;
                mensajeEstadoTv.setId_error (Long.toString (tr019s.getErrorCode ())) ;
                mensajeEstadoTv.setDesc_error (sinNull(tr019s.getErrorMessage ()) + " . " + sinNull(tr019s.getErrorCodeMessage ())) ;
            }
            else
            {
                // el mensaje se proceso OK (ver el
                
                Mensaje_estadoLocal mensajeEstadoOk = buscaMensajeEstadoOk () ;
                
                mensajeEstadoTv.setMensaje_estado (mensajeEstadoOk) ;
                
//				Iterator iterPackage = tr019s.getPackages().getIdPackage().iterator();
 
               Iterator iterPackage = tr019s.getPackages().iterator();
               TematicoLocalHome tematicoLocalHome = (TematicoLocalHome) HomeFactory.getHome (TematicoLocalHome.JNDI_NAME) ;
               Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
               Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
                
                while (iterPackage.hasNext ())
                {
                    // para no confundir con la clase estandar de Java
                    String valor=(String) iterPackage.next();
                    
                    Long idTematico = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TEMATICO"));                    
                    TematicoLocal tematico = tematicoLocalHome.create (idTematico, "3", mensajeEstadoTv.getPeticion(),valor, null) ; 
                }
                
                //
                Iterator iterEquipsments = tr019s.getEquipments ().iterator () ;
                
                
                //DAVID: defecto 34008, Marzo 18 2010
                InfoPostVentaTV infoPostVentaTV=recuperaInfoTvPostVenta(idPeticion);
                int accion=accionParActivoCas;
                if(infoPostVentaTV.isEsBajaCompleta()){
                	accion=accionParEliminar;
                }
                //FIN DAVID: defecto 34008, Marzo 18 2010
                while (iterEquipsments.hasNext ())
                {
                    Equipment equipment2 = (Equipment) iterEquipsments.next () ;
                    Deco_tarjetaLocal decoTarjeta = decoTarjetaLocalHome.create (equipment2.getCard (), 
                                                                                 equipment2.getDeco (),
																				 mensajeEstadoTv.getPeticion()
                                                                                ) ;
                    // le pondremos accion de consulta y estado Ok
                    decoTarjeta.setAccion(new Integer(accion));
                    decoTarjeta.setEstado(new Integer(estadoParOk));
                    //TODO PVR VALIDAR REFERENCE
                    decoTarjeta.setDeco_reference(equipment2.getDecoReference());
                    decoTarjeta.setSerial_deco(equipment2.getDecoSerial());
                    decoTarjeta.setDeco_marca(equipment2.getDecoMarca());
                    //Datos del disco duro
                    decoTarjeta.setSerial_ddtv(equipment2.getDdtvSerial());
                    decoTarjeta.setMarca_ddtv(equipment2.getDdtvMarca());
					decoTarjeta.setModelo_ddtv(equipment2.getDdtvModelo());
                    
                    /*RQ.8595 - mfmendez*/
					decoTarjeta.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
					// Datos de la Tarjeta
					if((equipment2.getCardPostingDateSAP() != null && !equipment2.getCardPostingDateSAP().equals("")) 
							|| (equipment2.getCardMoveTypeSAP() != null && !equipment2.getCardMoveTypeSAP().equals("")) 
							|| (equipment2.getCardMaterialCodeSAP() != null && !equipment2.getCardMaterialCodeSAP().equals(""))
							|| (equipment2.getCardMaterialSAP() != null && !equipment2.getCardMaterialSAP().equals("")) 
							|| (equipment2.getCardPlantSAP() != null && !equipment2.getCardPlantSAP().equals("")) 
							|| (equipment2.getCardStorageSAP() != null && !equipment2.getCardStorageSAP().equals(""))
							|| (equipment2.getCardBatchCodeSAP() != null && !equipment2.getCardBatchCodeSAP().equals("")) 
							|| (equipment2.getCardMeasurementUnitSAP() != null && !equipment2.getCardMeasurementUnitSAP().equals("")) 
							|| (equipment2.getCardCostCenterSAP() != null && !equipment2.getCardCostCenterSAP().equals(""))
							|| (equipment2.getCardFuncAreaLongSAP() != null && !equipment2.getCardFuncAreaLongSAP().equals("")) 
							|| (equipment2.getCardPepElementSAP() != null && !equipment2.getCardPepElementSAP().equals(""))){
						
						Deco_Tarjeta_Info_SapLocal dec_tar_sap_cardLocal = deco_tar_inf_sapLocalHome.create(equipment2.getCard(),((PeticionKey)mensajeEstadoTv.getPeticion().getPrimaryKey()).peti_numero);
						dec_tar_sap_cardLocal.setFec_cont_sap(equipment2.getCardPostingDateSAP());
						dec_tar_sap_cardLocal.setClase_mov_sap(equipment2.getCardMoveTypeSAP());
						if(equipment2.getCardMaterialCodeSAP() != null)
							dec_tar_sap_cardLocal.setPos_doc_sap(Integer.parseInt(equipment2.getCardMaterialCodeSAP()));
						else
							dec_tar_sap_cardLocal.setPos_doc_sap(0);
						dec_tar_sap_cardLocal.setNum_material_sap(equipment2.getCardMaterialSAP());
						dec_tar_sap_cardLocal.setCentro_sap(equipment2.getCardPlantSAP());
						dec_tar_sap_cardLocal.setAlmacen_sap(equipment2.getCardStorageSAP());
						dec_tar_sap_cardLocal.setCod_lote_sap(equipment2.getCardBatchCodeSAP());
						dec_tar_sap_cardLocal.setUnd_medida_sap(equipment2.getCardMeasurementUnitSAP());
						dec_tar_sap_cardLocal.setCentr_cost_sap(equipment2.getCardCostCenterSAP());
						dec_tar_sap_cardLocal.setArea_func_sap(equipment2.getCardFuncAreaLongSAP());
						dec_tar_sap_cardLocal.setElement_pep_sap(equipment2.getCardPepElementSAP());
						dec_tar_sap_cardLocal.setFlag_mat_sap(equipment2.getCardFlagMatSAP());
					}
					// Datos del Deco
					if((equipment2.getDecoPostingDateSAP() != null && !equipment2.getDecoPostingDateSAP().equals("")) 
							|| (equipment2.getDecoMoveTypeSAP() != null && !equipment2.getDecoMoveTypeSAP().equals("")) 
							|| (equipment2.getDecoMaterialCodeSAP() != null && !equipment2.getDecoMaterialCodeSAP().equals(""))
							|| (equipment2.getDecoMaterialSAP() != null && !equipment2.getDecoMaterialSAP().equals("")) 
							|| (equipment2.getDecoPlantSAP() != null && !equipment2.getDecoPlantSAP().equals("")) 
							|| (equipment2.getDecoStorageSAP() != null && !equipment2.getDecoStorageSAP().equals(""))
							|| (equipment2.getDecoBatchCodeSAP() != null && !equipment2.getDecoBatchCodeSAP().equals("")) 
							|| (equipment2.getDecoMeasurementUnitSAP() != null && !equipment2.getDecoMeasurementUnitSAP().equals("")) 
							|| (equipment2.getDecoCostCenterSAP() != null && !equipment2.getDecoCostCenterSAP().equals(""))
							|| (equipment2.getDecoFuncAreaLongSAP() != null && !equipment2.getDecoFuncAreaLongSAP().equals("")) 
							|| (equipment2.getDecoPepElementSAP() != null && !equipment2.getDecoPepElementSAP().equals(""))){
						
						Deco_Tarjeta_Info_SapLocal dec_tar_sapdecoLocal = deco_tar_inf_sapLocalHome.create(equipment2.getDeco(),((PeticionKey)mensajeEstadoTv.getPeticion().getPrimaryKey()).peti_numero);
						dec_tar_sapdecoLocal.setFec_cont_sap(equipment2.getDecoPostingDateSAP());
						dec_tar_sapdecoLocal.setClase_mov_sap(equipment2.getDecoMoveTypeSAP());
						if(equipment2.getDecoMaterialCodeSAP() != null)
							dec_tar_sapdecoLocal.setPos_doc_sap(Integer.parseInt(equipment2.getDecoMaterialCodeSAP()));
						else
							dec_tar_sapdecoLocal.setPos_doc_sap(0);
						dec_tar_sapdecoLocal.setNum_material_sap(equipment2.getDecoMaterialSAP());
						dec_tar_sapdecoLocal.setCentro_sap(equipment2.getDecoPlantSAP());
						dec_tar_sapdecoLocal.setAlmacen_sap(equipment2.getDecoStorageSAP());
						dec_tar_sapdecoLocal.setCod_lote_sap(equipment2.getDecoBatchCodeSAP());
						dec_tar_sapdecoLocal.setUnd_medida_sap(equipment2.getDecoMeasurementUnitSAP());
						dec_tar_sapdecoLocal.setCentr_cost_sap(equipment2.getDecoCostCenterSAP());
						dec_tar_sapdecoLocal.setArea_func_sap(equipment2.getDecoFuncAreaLongSAP());
						dec_tar_sapdecoLocal.setElement_pep_sap(equipment2.getDecoPepElementSAP());
						dec_tar_sapdecoLocal.setFlag_mat_sap(equipment2.getDecoFlagMatSAP());
					}
					/*FIN - RQ.8595 - mfmendez*/
                }
            }
            String cod_actividad_generadora = mensajeEstadoTv.getCod_actividad_generadora();
			if(cod_actividad_generadora==null || "".equals(cod_actividad_generadora.trim())){
            	//No cierro niguna actividad por que es solo un refresco
            	return;
            }
            ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
            IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (cod_actividad_generadora) ;
            ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(idPeticion, cod_actividad_generadora) ;
            if(tr019s.isError () || tr019s.getErrorCode()!=0)
            {
								
//				agregado por agonz
				PeticionLocal peticionLocal = mensajeEstadoTv.getPeticion();
					

				//Iterator iterator=tr019s.getErrors().iterator();
				String codError = String.valueOf(tr019s.getErrorCode());
				String nombreClase=TR019S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

				String plataforma = VpistbbaConfig.getVariable("IDPGI");
				String bandeja = "PGI";
				if(errorLegado != null){
					if(errorLegado.getPlataforma() == null || errorLegado.getPlataforma().trim().equals("")){
						actDto.setObservacion("Error: "
				 				+ errorLegado.getCodigoError() + "Descripcion: "+tr019s.getErrorMessage());
						actividadEJB.terminar (actDto);
						return;
					}
					peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					plataforma = errorLegado.getPlataforma();
					bandeja = getNombreBandeja(plataforma);
					insertarCausalesCnaPeticion(mensajeEstadoTv, cod_actividad_generadora, errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else {
					insertarCausalesCnaPeticion(mensajeEstadoTv, cod_actividad_generadora, new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				}
//				fin agregado
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
				actDto.setObservacion("No se pudo obtener los Recursos DTH.Se deriva a " + bandeja + "."+sinNull(tr019s.getErrorMessage ()) + " . " + sinNull(tr019s.getErrorCodeMessage ()));
					
				
            }
            else
            	actDto.setObservacion("Consulta Información Tecnica DTH realizada Satisfactoriamente");
            
            actividadEJB.terminar (actDto) ;
        }
        catch (NumberFormatException e)
        {
			bExecution.setErrorOp(true);
        	e.printStackTrace();
        	log.debug("NumberFormatException",e);
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT) ;
        }
        catch (TnProcesoExcepcion e)
        {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.debug("TnProcesoException",e);
            throw new ATiempoAppEx (ATiempoAppEx.TN_PROCESS) ;
        }
        catch (CreateException e) 
        {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.debug("CreateException",e);
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		} catch (ATiempoAppEx e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.debug("ATiempoAppEx",e);
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		} catch (NamingException e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.debug("NamingException",e);
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.debug("CreateException",e);
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
		catch (Exception e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.debug("Exception",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
    }
    
    }
    
    /*
     * busca el PS peticion tipo PC de la familia TV 
     */
    
	/**
	 * @param string
	 */
	private String sinNull(String string)
	{
		if(string==null)
			return "";
		return string;
	}
	
	
	private void insertarCausalesCnaPeticion(Mensaje_estado_tvLocal mensajeEstadoTv, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, CreateException, FinderException
	{
		PeticionLocal peticionLocal=mensajeEstadoTv.getPeticion();
		PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
		if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!=estadoPeticionEnCurso)
		{
			log.info("En reversa no se almacenan Quiebres Automáticos.PetAtiempo:"+peticionKey.peti_numero);
			return;
		}
		Fecha fecha=new Fecha();
		PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		DBManager manager=new DBManager ();
		manager.setDataSource (DBManager.JDBC_VPISTBBA);

		UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
		Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);

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

	private Producto_servicio_peticionLocal buscaPsPetTipoPc (PeticionLocal peticion)
    {
        long idPeticion = ((PeticionKey) peticion.getPrimaryKey ()).peti_numero.longValue () ;
        
        Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
        
        while (iterPss.hasNext ())
        {
            Producto_servicio_peticionLocal productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
            
            Producto_servicioLocal productoServicio = productoServicioPeticion.getProducto_servicio () ;
            
            Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
            
            Familia_producto_servicioLocal familiaPS = productoServicio.getFamilia_producto_servicio () ;
            
            Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
            
            long idFamilia = familiaPSKey.faps_id.longValue () ;
            
            // busca si es el PS tipo PC
            if (idFamilia == familiaPcTV)
            {
                log.debug ("peticion: " + idPeticion + " ps tipo PC "  + productoServicioKey.ps_id) ;
                
                // paramos cuando encontramos el primer PS tipo PC (en teoria deberia haber 1 solo)
                return (productoServicioPeticion) ;
            }
        }
        
        // no se encontro el PS tipo PC: la rutina llamante decide si es un error o no
        return (null) ;
    }
    
    /*
     *
     */
    
    private Mensaje_estadoLocal buscaMensajeEstadoOk ()
    {
        return (buscaMensajeEstado (estadoOk)) ;
    }
    
    
    /*
     *
     */
    
    private Mensaje_estadoLocal buscaMensajeEstadoError ()
    {
        return (buscaMensajeEstado (estadoError)) ;
    }
    
    /*
     *
     */
    
    private Mensaje_estadoLocal buscaMensajeEstado (int llave)
    {
        try
        {
    		Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
            Mensaje_estadoKey key = new Mensaje_estadoKey (new Integer (llave)) ;
            
            Mensaje_estadoLocal mensajeEstado = msgEstadoLocalHome.findByPrimaryKey (key) ;
            
            return (mensajeEstado) ;
        }
        catch (FinderException fex)
        {
        	log.debug("No se encontro el MensajeEstado de llave=" + llave + " Exception: " + fex);
        	return (null) ;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			return (null) ;
        }
        
    }
    
    /*
     *
     */
    
    private Mensaje_estado_tvLocal buscaMensajeEstadoTv (String correlativo)
    {
        try
        {
            return buscaMensajeEstadoTv (Long.parseLong (correlativo)) ;
        }
        catch (NumberFormatException nfex)
        {
        	log.debug("Error en buscaMensajeEstadoTv() correlativo=" + correlativo + " Exception: " + nfex);
        	return (null) ;
        }
    }
    
    /*
     *
     */
    
    private Mensaje_estado_tvLocal buscaMensajeEstadoTv (long correlativo)
    {
        try
        {
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
            Mensaje_estado_tvKey key = new Mensaje_estado_tvKey (new Long (correlativo)) ;
            
            Mensaje_estado_tvLocal mensajeEstadoTv = msgEstadoTvLocalHome.findByPrimaryKey (key) ;
            
            return (mensajeEstadoTv) ;
        }
        catch (FinderException fex)
        {
        	log.debug("No se encontro el MensajeEstadoTv de llave=" + correlativo + " Exception: " + fex);
            return (null) ;
        }catch (NamingException e){
			log.error(" Creacion de Local Home Nulls",e);
			return (null) ;
        }
        
    }
	public TR016S buscarRespuestaMensaje(Long idPeticion, Long idMensaje) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try {
			Mensaje_estado_tvLocal msgTVLocal = buscaMensajeEstadoTv(idMensaje.longValue());
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			// No hay Mensaje enviado.
			if (msgTVLocal == null)
				return null;
	
			Mensaje_estadoLocal msgEstadoLocal = msgTVLocal.getMensaje_estado();
	
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
			Collection c = pLocal.getTmp_deco_tarjeta();
			for (Iterator it = c.iterator(); it.hasNext();) {
				Tmp_deco_tarjetaLocal tmpdtLocal = (Tmp_deco_tarjetaLocal) it.next();
	
				TR016S tr016s = (TR016S) XMLUtilities.unmarshall(tmpdtLocal.getXml());
	
				return tr016s;
			}
		} catch (FinderException fex) {
			log.debug("No se encontro el MensajeEstado de idPeticion=" + idPeticion + " Exception: " + fex);
		}catch (NamingException e){
			log.error(" Creacion de Local Home Nulls",e);
			return (null) ;
		}
	
		return null;
	}

	public Long enviarActivacion(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			
			Collection c=decoTarjetaLocalHome.findByPeticion(idPeticion);
			
			for (Iterator it = c.iterator(); it.hasNext();)
			{
				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
				try
				{
					dtLocal.remove();
				}
				catch (EJBException e)
				{
					Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
					log.error("EJBException. No pude Borrar Registro Deco/Tarjeta ["+ idPeticion+ ","+ dtKey.id_deco+ ","+ dtKey.id_tarjeta+ "] : "+ e.getMessage());
				}
				catch (RemoveException e)
				{
					Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
					log.error("RemoveException. No pude Borrar Registro Deco/Tarjeta ["+ idPeticion+ ","+ dtKey.id_deco+ ","+ dtKey.id_tarjeta+"] : "+ e.getMessage());
				}
				catch(Exception e)
				{
					log.error("Exception",e);
				}
			}

			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);
			
			// Creo los registros en la Tabla.
			for (int i = 0; i < listaPares.size(); i++)
			{
				EquipoDTO eDto = (EquipoDTO) listaPares.get(i);
				try
				{
					Deco_tarjetaLocal local = decoTarjetaLocalHome.create(eDto.getIdTarjeta(), eDto.getIdDeco(), pLocal);
					local.setOpco_id(new Long(eDto.getIdOpCo()));
					local.setEmpr_id(eDto.getIdContratista());
					local.setAccion(new Integer(accionParActivar));
					//TODO PVR SE AGREGO EL SET DEL REFERENCE
					local.setDeco_reference(eDto.getDecoReference());
					local.setDeco_marca(eDto.getMarca());
					log.info("Agregando Deco/Tarjeta [" + idPeticion + ","+ eDto.getIdDeco() + "," + eDto.getIdTarjeta() + ", "+ eDto.getIdOpCo()+"]");
				}
				catch (EJBException e1)
				{
					log.error("EJBException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ eDto.getIdDeco()+ ","+ eDto.getIdTarjeta()+ "] : "+ e1.getMessage());
				}
				catch (CreateException e1)
				{
					log.error("CreateException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ eDto.getIdDeco()+ ","+ eDto.getIdTarjeta()+ "] : "+ e1.getMessage());
				}
				catch(NumberFormatException nf)
				{
					log.error("NumberFormatException",nf);
				}
			}
		}
		catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}

		Long idMensaje = enviaConfiguracionServiciosTV(idPeticion.longValue());

		return idMensaje;
		//return new Long (11111);
	}
	public Long enviarActivacionMigracion(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx {
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			try
			{
				Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
				
				Collection c=decoTarjetaLocalHome.findByPeticion(idPeticion);
			
				for (Iterator it = c.iterator(); it.hasNext();)
				{
					Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
					try
					{
						if (dtLocal.getAccion()== null || dtLocal.getAccion().intValue()!= 5){
							dtLocal.remove();
						}
					}
					catch (EJBException e)
					{
						Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
						log.error("EJBException. No pude Borrar Registro Deco/Tarjeta ["+ idPeticion+ ","+ dtKey.id_deco+ ","+ dtKey.id_tarjeta+ "] : "+ e.getMessage());
					}
					catch (RemoveException e)
					{
						Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
						log.error("RemoveException. No pude Borrar Registro Deco/Tarjeta ["+ idPeticion+ ","+ dtKey.id_deco+ ","+ dtKey.id_tarjeta+"] : "+ e.getMessage());
					}
					catch(Exception e)
					{
						log.error("Exception",e);
					}
				}

				PeticionKey pKey = new PeticionKey(idPeticion);
				PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);
				String idDeco = null;
				String idTarjeta = null;
				String idOpCo = null;
				// Creo los registros en la Tabla.
				for (int i = 0; i < listaPares.size(); i++)
				{
					EquipoDTO eDto = (EquipoDTO) listaPares.get(i);
					idDeco = eDto.getIdDeco();
					idTarjeta = eDto.getIdTarjeta();
					idOpCo = eDto.getIdOpCo();
					try
					{
						Deco_tarjetaLocal local = decoTarjetaLocalHome.create(idTarjeta, idDeco, pLocal);
						local.setOpco_id(new Long(idOpCo));
						local.setEmpr_id(eDto.getIdContratista());
						local.setAccion(new Integer(accionParActivar));
						//TODO PVR SE AGREGO EL SET DEL REFERENCE
						local.setDeco_reference(eDto.getDecoReference());
						local.setDeco_marca(eDto.getMarca());
						log.info("Agregando Deco/Tarjeta [" + idPeticion + ","+ idDeco + "," + idTarjeta + ", "+ idOpCo+"]");
					}
					catch (EJBException e1)
					{
						log.error("EJBException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ idDeco+ ","+ idTarjeta+ "] : "+ e1.getMessage());
					}
					catch (CreateException e1)
					{
						log.error("CreateException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ idDeco+ ","+ idTarjeta+ "] : "+ e1.getMessage());
					}
					catch(NumberFormatException nf)
					{
						log.error("NumberFormatException",nf);
					}
				}
			}
			catch (FinderException e)
			{
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			}

			Long idMensaje = enviaConfiguracionServiciosTV(idPeticion.longValue());

			return idMensaje;
			//return new Long (11111);
		}
	public Long enviarActivacionSoloEq(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			
			Collection c=decoTarjetaLocalHome.findByPeticion(idPeticion);
	
			for (Iterator it = c.iterator(); it.hasNext();)
			{
				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
				try
				{
					Deco_tarjetaKey dtKey=(Deco_tarjetaKey) dtLocal.getPrimaryKey();
					boolean esta=false;
					for(Iterator iterator=listaPares.iterator();iterator.hasNext();)
					{
						EquipoDTO eDto = (EquipoDTO)iterator.next();
						if(dtKey.id_deco.equals(eDto.getIdDeco()) && dtKey.id_tarjeta.equals(eDto.getIdTarjeta()))
						{
							dtLocal.setAccion(new Integer(accionParActivar));
							dtLocal.setEstado(null);
							esta=true;
						}
					}
					if(!esta)
						dtLocal.remove();
				}
				catch (EJBException e)
				{
					Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
					log.error("EJBException. No pude Borrar Registro Deco/Tarjeta ["+ idPeticion+ ","+ dtKey.id_deco+ ","+ dtKey.id_tarjeta+ "] : "+ e.getMessage());
				}
				catch (RemoveException e)
				{
					Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
					log.error("RemoveException. No pude Borrar Registro Deco/Tarjeta ["+ idPeticion+ ","+ dtKey.id_deco+ ","+ dtKey.id_tarjeta+"] : "+ e.getMessage());
				}
				catch(Exception e)
				{
					log.error("Exception",e);
				}
			}

			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);
			String idTarjeta = null;
			String idDeco = null;
			// Creo los registros en la Tabla.
			for (int i = 0; i < listaPares.size(); i++)
			{
				EquipoDTO eDto = (EquipoDTO) listaPares.get(i);
				idTarjeta = eDto.getIdTarjeta();
				idDeco = eDto.getIdDeco();
				try
				{
					Deco_tarjetaKey key = new Deco_tarjetaKey(idTarjeta,idDeco,pKey);
					Deco_tarjetaLocal local = null;
					try
					{
						local=decoTarjetaLocalHome.findByPrimaryKey(key);
					}
					catch(FinderException fe)
					{
						local=decoTarjetaLocalHome.create(idTarjeta, idDeco, pLocal);
					}
					local.setOpco_id(new Long(eDto.getIdOpCo()));
					local.setEmpr_id(eDto.getIdContratista());
					local.setAccion(new Integer(accionParActivar));
					local.setEstado(null);
					//TODO PVR SE AGREGO SET REFERENCE VALIDAR
					local.setDeco_reference(eDto.getDecoReference());
					local.setDeco_marca(eDto.getMarca());
					log.info("Agregando Deco/Tarjeta [" + idPeticion + ","+ idDeco + "," + idTarjeta + ", "+ eDto.getIdOpCo()+"]");
				}
				catch (EJBException e1)
				{
					log.error("EJBException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ idDeco+ ","+ idTarjeta+ "] : "+ e1.getMessage());
				}
				catch (CreateException e1)
				{
					log.error("CreateException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ idDeco+ ","+ idTarjeta+ "] : "+ e1.getMessage());
				}
				catch(NumberFormatException nf)
				{
					log.error("NumberFormatException",nf);
				}
			}
		}
		catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}

		Long idMensaje = enviaConfiguracionServiciosTVSoloEq(idPeticion.longValue(), false);

		return idMensaje;
	}

	public Long enviaConfiguracionServiciosTVSoloEq(long idPeticion, boolean tieneInstaladosDecos) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
        	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
        	boolean esAgendaSC = false;
        	boolean tieneDecosParActivo = false;
			RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
			// obtiene un nuevo id de mensaje
    
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
    
			// obtiene el id de la peticion Atis
			String pcId=null;
    
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
    
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
    
			long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
    
			boolean tienePcEnAlta=recursosTVDelegate.tienePcEnAlta(new Long(idPeticion));
			// busca
			// - el PS tipo PC de la familia TV (el PS tipo PC es como el padre de todos los PS)
			// - los PS correspondientes a los canales tematicos contratados por el cliente (por ejemplo parrilla de deportes, monos animados, cine, etc)
    
			Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
			Producto_servicioKey productoServicioPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
			Long operacionComercialPC = new Long(0);
    
			List psTematicos = new ArrayList (10) ;
			List psDecos =new ArrayList(10);
			Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
    
			
			try{
				Localidad_agenda_scLocalHome localidadAgendaSCLocalHome = (Localidad_agenda_scLocalHome) HomeFactory.getHome(Localidad_agenda_scLocalHome.JNDI_NAME);
				Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);				
				
				LocalidadLocal localidad= peticion.getFk_01_localidad();
				LocalidadKey localidadKey = (LocalidadKey)localidad.getPrimaryKey();
				Localidad_agenda_scKey localidadAgendakey = new Localidad_agenda_scKey(localidadKey.cod_loc);
				Localidad_agenda_scLocal localidadAgendaSCLocal = localidadAgendaSCLocalHome.findByPrimaryKey(localidadAgendakey);
				esAgendaSC = true;
			}catch(FinderException ex){
				log.debug("La localidad no esta entre las catálogadas como Agenda SC");
				esAgendaSC = false;
			}
			
			while (iterPss.hasNext ()){
				Producto_servicio_peticionLocal productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
				Producto_servicioLocal productoServicio = productoServicioPeticion.getProducto_servicio () ;
				Operacion_comercialLocal operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
				operacion_comerciaPcKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;

				// filtra por familia TV y busca el PC y los ps tematicos
				Familia_producto_servicioLocal familiaPS = productoServicio.getFamilia_producto_servicio () ;
				Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
				int idFamilia = familiaPSKey.faps_id.intValue();
        
				// busca si es el PS PC
				if (idFamilia == familiaPcTV || idFamilia == familiaTV){
					log.debug ("peticion: " + idPeticion + " ps tipo PC "  + productoServicioKey.ps_id) ;
					if (productoServicioPeticionPcTV != null){
						String msg = "peticion: " + idPeticion + " tiene mas de un ps tipo PC (el ultimo encontrado fue el "  + productoServicioKey.ps_id + ")" ;
						log.error (msg) ;
						throw new ATiempoAppEx (msg) ;
					}

					if(pcId==null){
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					
					productoServicioPeticionPcTV = productoServicioPeticion ;
					productoServicioPcKey = productoServicioKey ;
					operacionComercialPC = operacion_comerciaPcKey.opco_id;
            
					continue ;
				}
        
				if (idFamilia == familiaTematicoTV || idFamilia == familiaPaqueteTV){
					log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
					
					if(pcId==null){
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					
					psTematicos.add (productoServicioPeticion) ;
					continue ;
				}
//				TODO PVR validar se agrego  familia
				if(idFamilia == familiaDecoTV || idFamilia == familiaDecoPVRTV || idFamilia == familiaDecoHDTV){
					log.debug("peticion:"+idPeticion+" ps Deco "+productoServicioKey.ps_id);
					if(pcId==null){
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					psDecos.add(productoServicioPeticion);
					continue;

				}

				/**
                 * DAVID: se adiciona el siguiente código para RQ 28274, cobro incidencias.
                 */
				if(idFamilia == familiaMantenimiento&&productoServicioKey.ps_id.longValue()==ComunInterfaces.psMNTTV||
						idFamilia == familiaTV&&productoServicioKey.ps_id.longValue()==ComunInterfaces.psTrasladoInternoTV){
					log.debug("peticion:"+idPeticion+" ps Deco "+productoServicioKey.ps_id);
					if(pcId==null){
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					psDecos.add(productoServicioPeticion);
					continue;
				}
                //DAVID: Fin RQ 28274
				log.debug ("peticion: " + idPeticion + " no es Pc TV ni tampoco Tematico TV ni tampoco Tematico Deco TV." + productoServicioKey.ps_id) ;
			}

			// crea y llena la representacion Java del mensaje
			TR017E tr017e = new TR017E ();
			tr017e.setId (idCorrelativoMensaje.toString ()) ;
			tr017e.setPcId(pcId);
			
			if (esAgendaSC && !tieneInstaladosDecos){
				if (tienePcEnAlta){
					if(productoServicioPcKey!=null)
						tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
					else
						tr017e.setPcProductServiceCode(0);
					
					if(operacionComercialPC!=null)
						tr017e.setPcCommercialOperation(operacionComercialPC.longValue());
					else
						tr017e.setPcCommercialOperation(0);	
				}
			}
			
			
			tr017e.setAtisRequestNumber (peticionAtis) ;
			tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
			tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
			tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
    
			tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
    
			if(peticion.getTip_cli_cd()!=null)
				tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
			else
				tr017e.setClientType ('0') ;
   
			tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
			tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
    
			// deco / tarjeta
    
			List listDecoTarj = new ArrayList () ;
			
			Deco_tarjetaLocalHome decoTarjetaLocalHome=(Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Collection listaDecosOrdenadosXOper=decoTarjetaLocalHome.findByPeticionOpCoAsc(new Long(idPeticion));
			Iterator iterDecoTarj = listaDecosOrdenadosXOper.iterator () ;
			
			while (iterDecoTarj.hasNext ())
			{
				Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjeta.getPrimaryKey () ;
				
				if(!decoTarjeta.esEsperaDeActivacion() && !decoTarjeta.esEsperaDeEliminacion())
					continue;
				        
				TR017EEquipment equipment=new TR017EEquipment();
        
				equipment.setCardSerial(decoTarjetaKey.id_tarjeta) ;
				equipment.setCasId(decoTarjetaKey.id_deco) ;

				equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_DEFAULT")).longValue());
				equipment.setCommercialOperation(decoTarjeta.getOpco_id().longValue());
				listDecoTarj.add (equipment) ;
			}
    
			tr017e.setEquipments (listDecoTarj) ;
    
			if (esAgendaSC && !tieneInstaladosDecos){
				if(tienePcEnAlta){
	    			List listTematicos = new ArrayList () ;
	                Iterator iterPsTematicos = psTematicos.iterator () ;
	                while (iterPsTematicos.hasNext ()){
	                    Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) iterPsTematicos.next () ;
	                    long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
	    				TR017EPackage package1=new TR017EPackage(); 
	    				package1.setProductServiceCode (psId) ;
	                    package1.setCommercialOperation(psPaquete.getIdOperacionComercial().longValue());
	                    listTematicos.add (package1) ;
	                }
	                tr017e.setPackages (listTematicos) ;
	            }else{
	            	tr017e.setPackages (new ArrayList()) ;
	            }
			}else if (esAgendaSC && tieneInstaladosDecos){

				Collection decosCollection = peticion.getDeco_tarjeta();
				
				for(Iterator decosIterator = decosCollection.iterator(); decosIterator.hasNext();){
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decosIterator.next();
					
					if (decoTarjetaLocal.getEstado() != null && decoTarjetaLocal.getAccion() != null 
							&& decoTarjetaLocal.getEstado().intValue() == ComunInterfaces.estadoParOk
							&& decoTarjetaLocal.getAccion().intValue() == ComunInterfaces.accionParActivar ){
						tieneDecosParActivo = true;
					}
				}
				
				if(tienePcEnAlta && !tieneDecosParActivo){
	    			List listTematicos = new ArrayList () ;
	                Iterator iterPsTematicos = psTematicos.iterator () ;
	                while (iterPsTematicos.hasNext ()){
	                    Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) iterPsTematicos.next () ;
	                    long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
	    				TR017EPackage package1=new TR017EPackage(); 
	    				package1.setProductServiceCode (psId) ;
	                    package1.setCommercialOperation(psPaquete.getIdOperacionComercial().longValue());
	                    listTematicos.add (package1) ;
	                }
	                tr017e.setPackages (listTematicos) ;
	            }else{
	            	tr017e.setPackages (new ArrayList()) ;
	            }
			}else{
				tr017e.setPackages (new ArrayList()) ;
			}
    
			Mensaje_estado_tvLocal dbmsg ;
    
			dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
    
			dbmsg.setPeticion (peticion) ;
			
//			TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp()) ;
			
			dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
    
			// llama a la rutina que envia el mensaje
			new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;
    
			return (idCorrelativoMensaje) ;
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.NAMING);
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (CreateException cex)
		{
			cex.printStackTrace();
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
	}




	public ArrayList getListaDecoTarjetasEnCas(Long idPeticion) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		ArrayList listaDeco = new ArrayList(); 
		try
		{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);

			Collection c = pLocal.getDeco_tarjeta();
			Timestamp marca_hora = null;
			Long opco_id = null;
			for (Iterator it = c.iterator(); it.hasNext();)
			{
				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
				if(!dtLocal.estaEnCas())
					continue;
				Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();

				EquipoDTO eDto = new EquipoDTO();
				eDto.setIdDeco( dtKey.id_deco );
				eDto.setIdTarjeta( dtKey.id_tarjeta );
				eDto.setTipoEquipo( "TV" );
				eDto.setObservacion( dtLocal.getMensaje_error() );
				//TODO PVR se agrego para que agrege al dto el tipo de deco
				eDto.setDecoReference(dtLocal.getDeco_reference());
				//TODO: AT-1035 - PVR - Inicio - guido
				eDto.setAccion(dtLocal.getAccion());
				//AT-1035 - Fin
				opco_id = dtLocal.getOpco_id();
				if(opco_id!=null)
				{
					Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
					Operacion_comercialLocal operacion_comercialLocal=operacion_comercialHome.findByPrimaryKey(new Operacion_comercialKey(opco_id));
					eDto.setIdOpCo(String.valueOf(opco_id));
					eDto.setDescOperacionComer(operacion_comercialLocal.getOpco_nombre());					
				}
				if(dtLocal.getEmpr_id()!=null)
				{
					EmpresaLocalHome empresaHome=(EmpresaLocalHome) HomeFactory.getHome(EmpresaLocalHome.JNDI_NAME);
					EmpresaLocal empresaLocal=empresaHome.findByPrimaryKey(new EmpresaKey(dtLocal.getEmpr_id()));
					eDto.setContratista(empresaLocal.getEmpresa_nombre());
				}
				eDto.setEstado(dtLocal.getDescEstado());
				eDto.setCodError(dtLocal.getCodigo_error());
				marca_hora = dtLocal.getMarca_hora();
				if(marca_hora!=null)
				{
					Fecha fecha=new Fecha(marca_hora);
					eDto.setFechaMarcaHora(fecha.getFechaconFormato());
				}
				
				/*
				 * CR 00026144 - 17/06/2009
				 * 		Se obtiene el valor Marca para el Deco Tarjeta - Germán P. 
				 */
				eDto.setMarca(dtLocal.getDeco_marca());
				
				listaDeco.add( eDto );
			}
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}

		return listaDeco;
	}
	public ArrayList getListaDecoTarjetas(Long idPeticion) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		ArrayList listaDeco = new ArrayList(); 
		try {
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			
			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);
			LocalidadLocal localidadLocal = pLocal.getFk_01_localidad();

			Collection c = pLocal.getDeco_tarjeta();
			Timestamp marca_hora = null;
			Long empr_id = null;
			Long opco_id = null;
			for (Iterator it = c.iterator(); it.hasNext();)
			{
				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
				Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();

				EquipoDTO eDto = new EquipoDTO();
				eDto.setIdDeco( dtKey.id_deco );
				eDto.setIdTarjeta( dtKey.id_tarjeta );
				eDto.setTipoEquipo( "TV" );
				eDto.setObservacion( dtLocal.getMensaje_error() );
				//TODO PVR se agrego para que cargue el tipo de deco al dto
				eDto.setDecoReference(dtLocal.getDeco_reference());
				// TODO: AT-1035 - PVR - Inicio - guido
				eDto.setAccion(dtLocal.getAccion());
				// guido AT-1035 - Fin
				
				opco_id = dtLocal.getOpco_id();
				if(opco_id!=null)
				{
					Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
					Operacion_comercialLocal operacion_comercialLocal=operacion_comercialHome.findByPrimaryKey(new Operacion_comercialKey(opco_id));
					eDto.setIdOpCo(String.valueOf(opco_id));
					eDto.setDescOperacionComer(operacion_comercialLocal.getOpco_nombre());					
				}
				empr_id = dtLocal.getEmpr_id();
				if(empr_id!=null)
				{
					EmpresaLocalHome empresaHome=(EmpresaLocalHome) HomeFactory.getHome(EmpresaLocalHome.JNDI_NAME);
					EmpresaLocal empresaLocal=empresaHome.findByPrimaryKey(new EmpresaKey(empr_id));
					eDto.setContratista(empresaLocal.getEmpresa_nombre());
				}
				eDto.setIdEstado(dtLocal.getIdEstado());
				log.debug("ID ESTADO DECO: "+ dtLocal.getIdEstado());
				if(localidadLocal.getLocalidad_toa().intValue() == LOCALIDAD_TOA){
					Estados_TOALocalHome estadoTOAHome = (Estados_TOALocalHome) HomeFactory.getHome(Estados_TOALocalHome.JNDI_NAME);
					//Estados_TOAKey estadoTOaKey = new Estados_TOAKey(dtLocal.getEstado());
					//dacadena se cambia consulta en tabla estados toa, por estado y familia para retornar la descripcion del estado
//					Estados_TOALocal estadoTOALocal = estadoTOAHome.findEstadoFam(new Integer(dtLocal.getEstado().toString()),ComunInterfaces.TV);
					Estados_TOALocal estadoTOALocal = estadoTOAHome.findEstadoFam(dtLocal.getIdEstado(),ComunInterfaces.TV);
					eDto.setEstado(estadoTOALocal.getDescripcion_estado());
				}else{
					eDto.setEstado(dtLocal.getDescEstado());
				}				
				eDto.setCodError(dtLocal.getCodigo_error());
				marca_hora = dtLocal.getMarca_hora();
				if(marca_hora!=null)
				{
					Fecha fecha=new Fecha(marca_hora);
					eDto.setFechaMarcaHora(fecha.getFechaconFormato());
				}
				
				/*
				 * CR 00026144 - 17/06/2009
				 * 		Se obtiene el valor Marca para el Deco Tarjeta - Germán P. 
				 */
				eDto.setMarca(dtLocal.getDeco_marca());
				eDto.setDdtvSerial(dtLocal.getSerial_ddtv());
				
				listaDeco.add( eDto );
			}

		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}

		return listaDeco;
	}
	
	public ArrayList getListaDecoTarjetasActivas(Long idPeticion) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		ArrayList listaDeco = new ArrayList(); 
		try {
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);

			Collection c = pLocal.getDeco_tarjeta();
			Timestamp marca_hora = null;
			Long opco_id = null;
			Long empr_id =  null;
			for (Iterator it = c.iterator(); it.hasNext();)
			{
				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
				if(!dtLocal.estaActivo())
					continue;
				Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();

				EquipoDTO eDto = new EquipoDTO();
				eDto.setIdDeco( dtKey.id_deco );
				eDto.setIdTarjeta( dtKey.id_tarjeta );
				eDto.setTipoEquipo( "TV" );
				eDto.setObservacion( dtLocal.getMensaje_error() );
				//TODO PVR se agrego para que cargue al dto el deco Reference
				eDto.setDecoReference(dtLocal.getDeco_reference());
				// TODO: AT-1035 - PVR - Inicio - guido
				eDto.setAccion(dtLocal.getAccion());
				// guido AT-1035 - Fin
				
				opco_id = dtLocal.getOpco_id();
				if(opco_id!=null)
				{
					Operacion_comercialLocalHome operacion_comercialHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
					Operacion_comercialLocal operacion_comercialLocal=operacion_comercialHome.findByPrimaryKey(new Operacion_comercialKey(opco_id));
					eDto.setIdOpCo(String.valueOf(opco_id));
					eDto.setDescOperacionComer(operacion_comercialLocal.getOpco_nombre());					
				}
				empr_id = dtLocal.getEmpr_id();
				if(empr_id!=null)
				{
					EmpresaLocalHome empresaHome=(EmpresaLocalHome) HomeFactory.getHome(EmpresaLocalHome.JNDI_NAME);
					EmpresaLocal empresaLocal=empresaHome.findByPrimaryKey(new EmpresaKey(empr_id));
					eDto.setContratista(empresaLocal.getEmpresa_nombre());
				}
				eDto.setEstado(dtLocal.getDescEstado());
				eDto.setCodError(dtLocal.getCodigo_error());
				marca_hora = dtLocal.getMarca_hora();
				if(marca_hora!=null)
				{
					Fecha fecha=new Fecha(marca_hora);
					eDto.setFechaMarcaHora(fecha.getFechaconFormato());
				}
				
				/*
				 * CR 00026144 - 17/06/2009
				 * 		Se obtiene el valor Marca para el Deco Tarjeta - Germán P. 
				 */
				eDto.setMarca(dtLocal.getDeco_marca());
								
				listaDeco.add( eDto );
			}

		}
		catch (FinderException e)
		{
			e.printStackTrace();
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}

		return listaDeco;
	}

	public Long enviarActivacionCI(Long idPeticion, ArrayList listaActivar,ArrayList listaEliminar) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		boolean tieneInstaladosDecos = false;
		
		try
		{
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			Collection c=decoTarjetaLocalHome.findByPeticion(idPeticion);
			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);
			
			
//			if (c != null && c.size()>0){
//				tieneInstaladosDecos = true;
//			}
			//rtrivino
			 for (Iterator cIter = c.iterator();cIter.hasNext();){
			    Deco_tarjetaLocal decoTarLocal = (Deco_tarjetaLocal) cIter.next();
			    
			    if (decoTarLocal.getEstado() != null && decoTarLocal.getEstado().intValue() == ComunInterfaces.estadoParOk
			      && decoTarLocal.getAccion() != null && decoTarLocal.getAccion().intValue() == ComunInterfaces.accionParActivar){
			     tieneInstaladosDecos = true;
			    }
			 }
			 //fin rtrivino
			
			/* DAVID:  Req 27950 Deco adicional. Empieza codificación para manejo de decos adicionales no solicitados.
			 * DAVID: Se obtiene el número de decos que el usuario quiere eliminar. Con esto sabemos a cuantos decos se les debe asociar un 
			 * objeto Estado_ps_peticionLocal para registrar en este los parámetros codigo_causal, codigo_estado, entre otros.  De igual manera
			 * se relaciona el deco a eliminar con un objeto Estado_psLocal.
			 */
			int numDecosSTDAEliminar=0;
			/*for(Iterator iterator=listaEliminar.iterator();iterator.hasNext();)
			{
				EquipoDTO eDto = (EquipoDTO) iterator.next();
				Deco_tarjetaKey key=new Deco_tarjetaKey(eDto.getIdTarjeta(),eDto.getIdDeco(),pKey);
				Deco_tarjetaLocal local=decoTarjetaLocalHome.findByPrimaryKey(key);
				if(local.getDeco_reference().equals("STD")){
					log.debug("Hay un deco Standard a eliminar: "+local.getDeco_reference());
					numDecosSTDAEliminar++;
				}
				
				
			}
			//Fin DAVID
*/
			
			
			
			
			//Las 5 líneas siguientes se adicionaron en Merge, Enero 5 2010.
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
		/*	int numeroDecosSobrantes=listaEliminar.size();
			if(numeroDecosSobrantes>0){
				log.debug("El cliente quiere eliminar "+numeroDecosSobrantes+" decos");
				
				Catalogo_causalKey catalogo_causalKey=null;
				Long idAct=null;
				Catalogo_causalLocal catalogo_causalLocal=null;
				
				try{
				ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
				ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
				
				idAct=actividadSessionLocal.getIdActividad(VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONTROLI"),new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
			
				catalogo_causalKey=new Catalogo_causalKey(new Long(624));
				catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
					
					if(estado_ps_peticionHome==null)
						estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
					}
				
				catch (CreateException e) {
					e.printStackTrace();
				}catch (NamingException e) {
					e.printStackTrace();
				}
				
				List decosEliminables=new ArrayList();
				List ps_petDeDecoEliminable=new ArrayList();		
				
				Iterator ps_petLocalIt;
				
				for(ps_petLocalIt=pLocal.getProducto_servicio_peticion().iterator();ps_petLocalIt.hasNext();){
					
					Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal)ps_petLocalIt.next();
					Producto_servicioLocal producto_servicioLocal=(Producto_servicioLocal)producto_servicio_peticionLocal.getProducto_servicio();
					
					log.debug("El PSID del deco de la tabla ps_petición: "+producto_servicio_peticionLocal.getPsId());
					
					/*
					 * DAVID: Aquí se valida que el deco que se va a eliminar no sea el del PS 281, que corresponde al PS de Acceso Base de TV, el cual
					 * tiene un deco por defecto.  Si se elimina este deco y se hace el quiebre para este, el quiebre termina siendo para el PS 281
					 * lo cual no se quiere.  Se debe hacer para el PS 350.
					 */
						/*if(producto_servicio_peticionLocal.getPsId().intValue()==psDecoAdicional || producto_servicio_peticionLocal.getPsId().intValue()==psDecoAdicional1){
							decosEliminables.add(producto_servicioLocal);
							ps_petDeDecoEliminable.add(producto_servicio_peticionLocal);
							log.debug("El PS del deco eliminable es: "+producto_servicioLocal.getPs_nombre());
						}	/*		
				}
				/*
				 * DAVID: Según el número de decos que el usuario desea eliminar, se escogen de la lista los decos eliminables, que deben corresponder con 
				 * el PS=350, no con el 281.
				 */		
			/*	for(int contDecosSTDAEliminar=0;contDecosSTDAEliminar<numDecosSTDAEliminar;contDecosSTDAEliminar++){
					
					if(decosEliminables!=null&&decosEliminables.size()<=numDecosSTDAEliminar){
						
							Fecha fecha=new Fecha();
							
							try{
										
							    Producto_servicioLocal producto_servicioLocal_a_eliminar=(Producto_servicioLocal)decosEliminables.get(contDecosSTDAEliminar);
							    Producto_servicio_peticionLocal producto_servicio_peticionLocal_a_eliminar=(Producto_servicio_peticionLocal)ps_petDeDecoEliminable.get(contDecosSTDAEliminar);
							    System.out.println("DAVID: Se va a eliminar el siguiente PS: "+producto_servicioLocal_a_eliminar.getPs_nombre());
							    System.out.println("DAVID: Se va a eliminar el siguiente PSID: "+producto_servicio_peticionLocal_a_eliminar.getPsId());				    
							    
							    /*DAVID: Se agrega el código para registrar en la tabla EstadoPsPeticion que el cliente no solicitó los decos 
								 *adicionales que aquí se están eliminando.  Con eso al cerrar la petición, en la clase PeticionesServicesBean, en el 
								 *método llenaSubRequest1(), se valida que los ps's llegan con estado_ps_peticion. Aquí se fija el estado_ps_peticion en 3, y
								 *el método llenaSubRequest1() asume que es el caso estadoCierreError, con lo cual se fija el codeTypeError de la TR001 a enviar en el
								 *cierre en el valor NO_OK, o sea 08.  El codeError se fija en 624, que es el codigo causal para cuando el cliente no solicita el servicio.				 *
								 * 
								 */
				/*			    Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
							    
								Estado_ps_peticionLocal estado_ps_peticion_local= estado_ps_peticionHome.create(idCorrelativoMensaje,producto_servicioLocal_a_eliminar,producto_servicio_peticionLocal_a_eliminar);
								estado_ps_peticion_local.setProducto_servicio(producto_servicioLocal_a_eliminar);
								estado_ps_peticion_local.setCod_causal(catalogo_causalKey.cod_causal);//DAVID: Código causal para Cliente no solicitó servicio
								//DAVID: No se puso el cod_estado en 3 por problema en WF. Se puso 1, es decir novedad.
								estado_ps_peticion_local.setCod_estado_cierre(new Integer(1));
								estado_ps_peticion_local.setNovedad(catalogo_causalLocal.getDescripcion_causal());
								estado_ps_peticion_local.setCod_actividad(idAct);
								
								
								
								Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
								Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
								UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
								long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
								Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticion_local,usuarioLocal);
								causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
								causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
								causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
								causal_peticionLocal.setCod_actividad(idAct);
								
								
							}catch (CreateException e) {
								e.printStackTrace();
							}
						}
					}//end if(decosEliminables!=null)
					
				}//DAVID: FIN, end for(int contDecosSTDAEliminar=0*/
				
				

			for(Iterator iterator=listaEliminar.iterator();iterator.hasNext();)
			{
				EquipoDTO eDto = (EquipoDTO) iterator.next();
				try
				{
					Deco_tarjetaKey key=new Deco_tarjetaKey(eDto.getIdTarjeta(),eDto.getIdDeco(),pKey);
					Deco_tarjetaLocal local=decoTarjetaLocalHome.findByPrimaryKey(key);
					local.setAccion(new Integer(accionParEliminar));
					local.setEstado(null);
		
					//	TODO: CR 7304 Inicio - ana  
					//local.setOpco_id(new Long(operacionParDesactivar));
					local.setOpco_id(new Long(operacionParDesactivarLiberar));
					//	TODO: ana CR 7304 - Fin

					
					log.info("Tratando de Eliminar:" + local.toEquipo().toString());
				}
				catch(FinderException fe)
				{
					log.debug("No se puede eliminar par :"+eDto.toString());
				}
			}
			String idTarjeta = null;
			String idDeco = null;
			for(Iterator iterator=listaActivar.iterator();iterator.hasNext();)
			{
				EquipoDTO eDto = (EquipoDTO) iterator.next();
				idTarjeta = eDto.getIdTarjeta();
				idDeco = eDto.getIdDeco();
				try
				{
					Deco_tarjetaLocal local=null;
					Deco_tarjetaKey key=new Deco_tarjetaKey(idTarjeta,idDeco,pKey);
					try
					{
						local=decoTarjetaLocalHome.findByPrimaryKey(key);
						if(local.estaActivo())
							continue;
					}
					catch(FinderException fe)
					{
						local = decoTarjetaLocalHome.create(idTarjeta, idDeco, pLocal);
					}
					local.setAccion(new Integer(accionParActivar));
					local.setEstado(null);
					local.setOpco_id(new Long(operacionParActivar));
					local.setEmpr_id(eDto.getIdContratista());
					// PVR SE AGREGO SET REFERENCE
					log.debug("PVR SE AGREGO SET REFERENCE" +eDto.getDecoReference());
					local.setDeco_reference(eDto.getDecoReference());
					log.info("Tratando de Activar Marca:"+eDto.getMarca());
					local.setDeco_marca(eDto.getMarca());
					log.info("Tratando de Activar:"+local.toEquipo().toString());
				}
				catch (EJBException e1)
				{
					log.error("EJBException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ idDeco+ ","+ idTarjeta+ "] : "+ e1.getMessage());
				}
				catch (CreateException e1)
				{
					log.error("CreateException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ idDeco+ ","+ idTarjeta+ "] : "+ e1.getMessage());
				}
				catch(NumberFormatException nf)
				{
					log.error("NumberFormatException",nf);
				}
			}
		}
		catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}

		Long idMensaje = enviaConfiguracionServiciosTVSoloEq(idPeticion.longValue(), tieneInstaladosDecos);

		return idMensaje;
	}


	public InfoPostVentaTV recuperaInfoTvPostVenta(Long nroPeticion) throws ATiempoAppEx
	{
		try
		{
			boolean esBajaCompleta=false;
			boolean esBajaDeco=false;
			boolean esTraslado=false;
			int cantidadParBajaSTD=0;
			int cantidadParBajaPVR=0;
			int cantidadParBajaHD=0;
			boolean tieneSTD=false;
			boolean tienePVR=false;
			boolean tieneHD=false;

			
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			InfoPostVentaTV infoPostVentaTV=new InfoPostVentaTV(nroPeticion.longValue());
			infoPostVentaTV.setPeticionAt(nroPeticion.longValue());
			int faps_id = 0;
			String opco_tipo = null;
			String opco_tras = null;
			Operacion_comercialLocal operacion_comercialLocal=null;
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal ps=(Producto_servicio_peticionLocal) iterator.next();
				Familia_producto_servicioKey familia_producto_servicioKey=ps.getFamiliaKey();
				Producto_servicioKey producto_servicioKey=ps.getPsKey();
				operacion_comercialLocal=ps.getOperacion_comercial();
				//Primero tengo que determinar si es una baja completa
				faps_id = familia_producto_servicioKey.faps_id.intValue();
				opco_tipo = operacion_comercialLocal.getOpco_tipo();
				
				//TODO: Defecto 00035678, Raúl Ernesto Triviño - Ajustar para que no tenga en cuenta cuando es un traslado
				//TODO: Correctivo, Raúl Ernesto Triviño Alvarado - Ajuste para que tenga en cuenta los decos HD
				if((faps_id==familiaPcTV) && opco_tipo!=null && opco_tipo.equals("B") 
						&& operacion_comercialLocal.getOpco_nombre().indexOf("TRASLADO") == -1){
					esBajaCompleta=true;
					infoPostVentaTV.setOpCoDeco(ps.getIdOperacionComercial().longValue());
				}
				//End TODO
				
				if(faps_id==familiaDecoTV && opco_tipo!=null && (opco_tipo.equals("B") || (opco_tipo.equals("BCP"))))
				{
					esBajaDeco=true;
					infoPostVentaTV.setOpCoDeco(ps.getIdOperacionComercial().longValue());
					cantidadParBajaSTD++;
//					TODO PVR se agrego el seteo de la variable tipodecodificador para validar en la baja
					infoPostVentaTV.setTipodecodificador(desHCDecoSTD);
					tieneSTD=true;
					
				}
				//TODO PVR se agrego el seteo de la variable tipodecodificador para validar en la baja
				if(faps_id==familiaDecoPVRTV && opco_tipo!=null && (opco_tipo.equals("B")|| (opco_tipo.equals("BCP"))))
				{
					esBajaDeco=true;
					infoPostVentaTV.setOpCoDeco(ps.getIdOperacionComercial().longValue());
					infoPostVentaTV.setTipodecodificador(desHCDecoPVR);
					cantidadParBajaPVR++;
					tienePVR=true;
				}
				
				if(faps_id==familiaDecoHDTV && opco_tipo!=null && (opco_tipo.equals("B")|| (opco_tipo.equals("BCP"))))
				{
					esBajaDeco=true;
					infoPostVentaTV.setOpCoDeco(ps.getIdOperacionComercial().longValue());
					infoPostVentaTV.setTipodecodificador(desHCDecoHDTV);
					cantidadParBajaHD++;
					tieneHD=true;
				}
				
				
				opco_tras = operacion_comercialLocal.getOpco_tras();
				if(opco_tras!=null && opco_tras.equals("T"))
					esTraslado=true;
			}
			if(tienePVR & tieneSTD ){
			
				infoPostVentaTV.setEsBajaPVRYSTD(true);
			}
			
			for(Iterator iterator=peticionLocal.getDeco_tarjeta().iterator();iterator.hasNext();)
			{
				Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal) iterator.next();
				Deco_tarjetaKey deco_tarjetaKey=(Deco_tarjetaKey) deco_tarjetaLocal.getPrimaryKey();
				
				if(opco_tipo!=null && opco_tipo.equals("B") && operacion_comercialLocal.getOpco_nombre().indexOf("TRASLADO") == -1)
					infoPostVentaTV.addDecoTarjeta(deco_tarjetaKey.id_deco,deco_tarjetaKey.id_tarjeta,deco_tarjetaLocal.getDeco_reference(),deco_tarjetaLocal.getDeco_marca(),deco_tarjetaLocal.getSerial_deco());
				else
					infoPostVentaTV.addDecoTarjeta(deco_tarjetaKey.id_deco,deco_tarjetaKey.id_tarjeta,deco_tarjetaLocal.getDeco_reference());
			}
			infoPostVentaTV.setEsBajaCompleta(esBajaCompleta);
			infoPostVentaTV.setEsBajaDeco(esBajaDeco);
			infoPostVentaTV.setEsTraslado(esTraslado);
			infoPostVentaTV.setCantidadParesBajaPVR(cantidadParBajaPVR);
			infoPostVentaTV.setCantidadParesBajaSTD(cantidadParBajaSTD);
			infoPostVentaTV.setCantidadParesBajaHD(cantidadParBajaHD);
			infoPostVentaTV.setCantidadParesBaja(cantidadParBajaPVR+cantidadParBajaSTD+cantidadParBajaHD);
			if(esBajaCompleta)
				infoPostVentaTV.setCantidadParesBaja(infoPostVentaTV.getListaParesEnCas().size());
			return infoPostVentaTV;
		}
		catch (FinderException e)
		{
			log.debug("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
		
	}

	public void grabaDecosPostVenta(Long nroPeticion, ArrayList listaPares, long opCoPostPar) throws ATiempoAppEx
	{
		try
		{
			Deco_tarjetaLocalHome decoTarjetaLocalHome=(Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome .JNDI_NAME);
			for(Iterator iterator=listaPares.iterator();iterator.hasNext();)
			{
				DecoTarDTO decoTarDTO=(DecoTarDTO) iterator.next();
				Deco_tarjetaLocal local=decoTarjetaLocalHome.findByPrimaryKey(new Deco_tarjetaKey(decoTarDTO,nroPeticion));
				//local.setEstado(null);
				local.setAccion(new Integer(accionParNula));
				local.setOpco_id(new Long(opCoPostPar));
			}
		}
		catch(NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		} catch (FinderException e)
		{
			log.debug("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}
	
	public ArrayList recuperaDecosPostVenta(Long nroPeticion) throws ATiempoAppEx
	{
		try
		{
			ArrayList retorno=new ArrayList();
			Deco_tarjetaLocalHome decoTarjetaLocalHome=(Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome .JNDI_NAME);
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			Collection lista=local.getDeco_tarjeta();
			
			InfoPostVentaTV infoPostVentaTV=recuperaInfoTvPostVenta(nroPeticion);
			if(infoPostVentaTV.isEsBajaDeco() && !infoPostVentaTV.isEsBajaCompleta())
			{
				for(Iterator iterator=lista.iterator();iterator.hasNext();)
				{
					Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal) iterator.next();
					if(deco_tarjetaLocal.estaEnCas())
						continue;
					Deco_tarjetaKey deco_tarjetaKey=(Deco_tarjetaKey) deco_tarjetaLocal.getPrimaryKey();
					DecoTarDTO decoTarDTO=new DecoTarDTO(deco_tarjetaKey.id_deco,deco_tarjetaKey.id_tarjeta,deco_tarjetaLocal.getDeco_reference());
					retorno.add(decoTarDTO);
				}
			}
			else
			{
				Integer accion = null;
				for(Iterator iterator=lista.iterator();iterator.hasNext();)
				{
					Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal) iterator.next();
					accion = deco_tarjetaLocal.getAccion();
					if(accion==null)
						continue;
					/* 20/06/2008 agonz en el "if" se agrega la condicion diferente a "accionParEliminar"*/
					if(accion.intValue()!=accionParNula && accion.intValue()!=accionParEliminar)
						continue;
					Deco_tarjetaKey deco_tarjetaKey=(Deco_tarjetaKey) deco_tarjetaLocal.getPrimaryKey();
					DecoTarDTO decoTarDTO=new DecoTarDTO(deco_tarjetaKey.id_deco,deco_tarjetaKey.id_tarjeta,deco_tarjetaLocal.getDeco_reference());
					retorno.add(decoTarDTO);
				}
			}
			return retorno;
		}
		catch(NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}
	
	
	public void actualizaAccionParesPostVenta(Long nroPeticion,ArrayList listaPares) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Deco_tarjetaLocalHome decoTarjetaLocalHome=(Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome .JNDI_NAME);
			for(Iterator iterator=listaPares.iterator();iterator.hasNext();)
			{
				DecoTarDTO decoTarDTO=(DecoTarDTO) iterator.next();
				Deco_tarjetaKey deco_tarjetaKey=new Deco_tarjetaKey(decoTarDTO,nroPeticion);
				Deco_tarjetaLocal local=decoTarjetaLocalHome.findByPrimaryKey(deco_tarjetaKey);
				local.setAccion(decoTarDTO.getAccion());
//				local.setEstado(null);
			}
		}
		catch(NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}


	public boolean tienePcEnAlta(Long nroPeticion) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal ps=(Producto_servicio_peticionLocal) iterator.next();
				Long idFamilia=ps.getFamiliaKey().faps_id;
				if(idFamilia.intValue()!=familiaPcTV)
					continue;
				Operacion_comercialLocal op=ps.getOperacion_comercial();
				if(op.getOpco_tipo()!=null && op.getOpco_tipo().equals("A"))
					return true;
			}
			return false;
		}
		catch(NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}


	public void enviaRefrescoPares(Long nroPeticion) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
            
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			String pcId=null;
			PeticionKey key = new PeticionKey (nroPeticion) ;
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
			long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
			Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
			Producto_servicioKey productoServicioPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
			Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
			while (iterPss.hasNext ())
			{
				Producto_servicio_peticionLocal productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
				Producto_servicioLocal productoServicio = productoServicioPeticion.getProducto_servicio () ;
				Operacion_comercialLocal operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
				operacion_comerciaPcKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
				Familia_producto_servicioLocal familiaPS = productoServicio.getFamilia_producto_servicio () ;
				Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
				int idFamilia = familiaPSKey.faps_id.intValue();
				if (idFamilia == familiaPcTV)
				{   
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					productoServicioPeticionPcTV = productoServicioPeticion ;
					productoServicioPcKey = productoServicioKey ;
					continue ;
				}
			}
			TR017E tr017e = new TR017E ();
			tr017e.setId (idCorrelativoMensaje.toString ()) ;
			tr017e.setPcId(pcId);
			if(productoServicioPcKey!=null)
				tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
			tr017e.setPcCommercialOperation(operacionParRefresh);
			tr017e.setAtisRequestNumber (peticionAtis) ;
			tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
			tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
			tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
			tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
			if(peticion.getTip_cli_cd()!=null)
				tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
			else
				tr017e.setClientType ('0') ;
			tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
			tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
			List listDecoTarj = new ArrayList () ;
			tr017e.setEquipments (listDecoTarj) ;
			List listTematicos = new ArrayList () ;
			tr017e.setPackages (listTematicos) ;
			
			
			// CR22115 - adocarmo - Inicio
            // guarda el registro del mensaje
            
            Mensaje_estado_tvLocal dbmsg ;
            
            dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
            
            dbmsg.setPeticion (peticion) ;
			//TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp());
            
			dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
			
			// 1 indica que es refresh y 0 (valor por defecto indica que no es refresh)
			dbmsg.setEs_refresh(new Short((short)1));
			log.debug(">>>>>>>>>>>>>>>>>>ES REFRESH: " + dbmsg.getEs_refresh());

			// CR22115 - adocarmo - Fin
			
			new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#getListaTematicos(java.lang.Long)
	 */
	public ArrayList getListaTematicos(Long idPeticion) throws ATiempoAppEx
	{
		try
		{
			ArrayList retorno=new ArrayList();
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
			for(Iterator iterator=local.getTematico().iterator();iterator.hasNext();)
			{
				TematicoLocal tematicoLocal=(TematicoLocal) iterator.next();
				TematicoDTO tematicoDTO=new TematicoDTO();
				tematicoDTO.setCodTematico(tematicoLocal.getCod_tematico());
				retorno.add(tematicoDTO);
			}
			return retorno;
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION);
		}
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#enviaConfiguracionServiciosTVDesactivacion(long, java.lang.String, java.lang.Integer)
	 */
	public Long enviaConfiguracionServiciosTVDesactivacion(long idPeticion, String idActividad,Integer idActividadFlujo) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
			
			PeticionesDelegate delegate=new PeticionesDelegate();
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			String pcId=null;
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
			long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
			Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
			Producto_servicioKey productoServicioPcKey = null;
			Producto_servicioKey productoServicioDecoKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
			Operacion_comercialKey operacion_comerciaDecoKey = null;
			Operacion_comercialKey operacion_comerciaTematicoKey = null;
			Operacion_comercialKey operacion_comerciaPaqueteKey = null;
			Operacion_comercialKey operacion_comercialCPKey=null;
			List psTematicos = new ArrayList (10) ;
			List psPaquetes = new ArrayList (10) ;
			List psDecos =new ArrayList(20);
			Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
			boolean tieneReseteoCP=false;
			String idDecoCP=null;
			String idTarjetaCP=null;
			while (iterPss.hasNext ())
			{
				Producto_servicio_peticionLocal productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
				Producto_servicioLocal productoServicio = productoServicioPeticion.getProducto_servicio () ;
				Operacion_comercialLocal operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
				Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				Familia_producto_servicioLocal familiaPS = productoServicio.getFamilia_producto_servicio () ;
				Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey ();				
				if(productoServicioKey.ps_id.longValue()==psReseteoCP)
				{
					tieneReseteoCP=true;
					operacion_comercialCPKey=operacion_comercialKey;
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
				}
				int idFamilia = familiaPSKey.faps_id.intValue();
				if(idFamilia == familiaPcTV)
				{
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					operacion_comerciaPcKey=operacion_comercialKey;
					productoServicioPeticionPcTV = productoServicioPeticion ;
					productoServicioPcKey = productoServicioKey ;
					continue ;
				}
				else if(idFamilia == familiaTematicoTV )
				{
					log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					operacion_comerciaTematicoKey=operacion_comercialKey;
					psTematicos.add (productoServicioPeticion) ;
					continue ;
				}
				else if(idFamilia == familiaPaqueteTV )
				{
					log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					operacion_comerciaPaqueteKey=operacion_comercialKey;
					psPaquetes.add (productoServicioPeticion);
					psTematicos.add (productoServicioPeticion);
					continue ;
				}
//				TODO PVR validar se agrego  familia
				else if(idFamilia == familiaDecoTV || idFamilia== familiaDecoPVRTV)
				{
					log.debug("peticion:"+idPeticion+" ps Deco "+productoServicioKey.ps_id);
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					productoServicioDecoKey = productoServicioKey ;
					operacion_comerciaDecoKey=operacion_comercialKey;
					psDecos.add(productoServicioPeticion);
					continue;
				}
//				if(tieneReseteoCP && idDecoCP==null)
//				{
//					Subpeticion_atisLocal local=productoServicioPeticion.getFk_01_subp_atis();
//					for(Iterator iterator=local.getSubpeticion_caracteristicas().iterator();iterator.hasNext();)
//					{
//						Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterator.next();
//						Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
//						Long casId=new Long (VpistbbaConfig.getVariable("CASIDTELEV"));
//						if (spk.cod_crc_cd.longValue()== casId.longValue())
//						{
//							log.debug("Informacion : Se obtuvo CASIDTELEV:" + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
//							idDecoCP=subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
//							break;
//						}
//					}
//					if(idDecoCP!=null)
//					{
//						Iterator iterPares=peticion.getDeco_tarjeta().iterator();
//						while(iterPares.hasNext())
//						{
//							Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal) iterPares.next();
//							Deco_tarjetaKey deco_tarjetaKey=(Deco_tarjetaKey) deco_tarjetaLocal.getPrimaryKey();
//							if(deco_tarjetaKey.id_deco.equals(idDecoCP))
//							{
//								idTarjetaCP=deco_tarjetaKey.id_tarjeta;
//								break;
//							}
//						}
//					}
//				}
			}
	
//			if(tieneReseteoCP && (idDecoCP==null || idTarjetaCP==null))
//				return null;
			
			ArrayList listaNegra=mergeTematicos(psPaquetes);
	
			TR017E tr017e = new TR017E ();
			tr017e.setId (idCorrelativoMensaje.toString ()) ;
			tr017e.setPcId(pcId);
			if(productoServicioPcKey!=null)
				tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
			if(operacion_comerciaPcKey!=null)
				tr017e.setPcCommercialOperation(operacionParDesactivar);
			tr017e.setAtisRequestNumber (peticionAtis) ;
			tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
			tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
			tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
			tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
			if(peticion.getTip_cli_cd()!=null)
				tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
			else
				tr017e.setClientType ('0') ;       
			tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
			tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
			List listDecoTarj = new ArrayList () ;

			Iterator iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;
			
			while (iterDecoTarj.hasNext () && productoServicioPcKey!=null)
			{
				Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
				if(!decoTarjeta.estaEnCas())
					continue;
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjeta.getPrimaryKey () ;
				TR017EEquipment equipment=new TR017EEquipment();
				equipment.setCardSerial(decoTarjetaKey.id_tarjeta) ;
				equipment.setCasId(decoTarjetaKey.id_deco) ;
				if(productoServicioDecoKey!=null)
					equipment.setProductServiceCode(productoServicioDecoKey.ps_id.longValue());
				else
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_DEFAULT")).longValue());
				equipment.setCommercialOperation(operacionParDesactivar);
				decoTarjeta.setAccion(new Integer(accionParEliminar));
				decoTarjeta.setEstado(null);
				listDecoTarj.add (equipment) ;
			}

			tr017e.setEquipments (listDecoTarj) ;

			List listTematicos = new ArrayList () ;

			Iterator iterPsTematicos = psTematicos.iterator () ;

			while (iterPsTematicos.hasNext ())
			{
				Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) iterPsTematicos.next () ;
				PsVsOcVO psVsOcVO=psPaquete.toPsVsOc();
				if(listaNegra.indexOf(psVsOcVO)>=0)
					continue;
				long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
				TR017EPackage package1=new TR017EPackage(); 
				package1.setProductServiceCode (psPaquete.getPsId().longValue()) ;
				package1.setCommercialOperation(operacionParDesactivar);
				listTematicos.add (package1) ;
			}

			tr017e.setPackages (listTematicos) ;
			Mensaje_estado_tvLocal dbmsg ;

			dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);

			dbmsg.setPeticion (peticion) ;
			
//			TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp()) ;
			
			dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
			dbmsg.setCod_actividad_generadora(idActividad);
			// llama a la rutina que envia el mensaje
			new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;

			return (idCorrelativoMensaje) ;
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (CreateException cex)
		{
			cex.printStackTrace();
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
	}

	public InfoInvTvDTO recuperaInfoInvTv(Long idPeticion) throws ATiempoAppEx
	{
		try
		{
			Inventario_dthLocalHome inventario_dthHome=(Inventario_dthLocalHome) HomeFactory.getHome(Inventario_dthLocalHome.JNDI_NAME);
			InfoInvTvDTO infoInvTvDTO=new InfoInvTvDTO(idPeticion);
			try
			{ 
				Inventario_dthLocal inventario_dthLocal=inventario_dthHome.findByPrimaryKey(new Inventario_dthKey(idPeticion));
				infoInvTvDTO.setEstado(inventario_dthLocal.getEstado());
				infoInvTvDTO.setDescripcion(inventario_dthLocal.getDescripcion());
				infoInvTvDTO.setMarca_hora(new Fecha(inventario_dthLocal.getMarca_hora()));
			}
			catch(FinderException e)
			{
				log.debug("No tiene info inv tv para la peticion:"+idPeticion);
			}
			return infoInvTvDTO;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}

	public Long enviarActivacionEqYPares(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			
			Collection c=decoTarjetaLocalHome.findByPeticion(idPeticion);
	
			for (Iterator it = c.iterator(); it.hasNext();)
			{
				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
				try
				{
					Deco_tarjetaKey dtKey=(Deco_tarjetaKey) dtLocal.getPrimaryKey();
					boolean esta=false;
					for(Iterator iterator=listaPares.iterator();iterator.hasNext();)
					{
						EquipoDTO eDto = (EquipoDTO)iterator.next();
						if(dtKey.id_deco.equals(eDto.getIdDeco()) && dtKey.id_tarjeta.equals(eDto.getIdTarjeta()))
						{
							dtLocal.setAccion(new Integer(accionParActivar));
							dtLocal.setEstado(null);
							esta=true;
						}
					}
					if(!esta)
						dtLocal.remove();
				}
				catch (EJBException e)
				{
					Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
					log.error("EJBException. No pude Borrar Registro Deco/Tarjeta ["+ idPeticion+ ","+ dtKey.id_deco+ ","+ dtKey.id_tarjeta+ "] : "+ e.getMessage());
				}
				catch (RemoveException e)
				{
					Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
					log.error("RemoveException. No pude Borrar Registro Deco/Tarjeta ["+ idPeticion+ ","+ dtKey.id_deco+ ","+ dtKey.id_tarjeta+"] : "+ e.getMessage());
				}
				catch(Exception e)
				{
					log.error("Exception",e);
				}
			}

			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);

			// Creo los registros en la Tabla.
			String idDeco = null;
			String idTarjeta = null;
			for (int i = 0; i < listaPares.size(); i++)
			{
				EquipoDTO eDto = (EquipoDTO) listaPares.get(i);
				idDeco = eDto.getIdDeco();
				idTarjeta = eDto.getIdTarjeta();
				try
				{
					Deco_tarjetaKey key = new Deco_tarjetaKey(idTarjeta,idDeco,pKey);
					Deco_tarjetaLocal local = null;
					try
					{
						local=decoTarjetaLocalHome.findByPrimaryKey(key);
					}
					catch(FinderException fe)
					{
						local=decoTarjetaLocalHome.create(idTarjeta, idDeco, pLocal);
					}
					local.setOpco_id(new Long(eDto.getIdOpCo()));
					local.setEmpr_id(eDto.getIdContratista());
					local.setAccion(new Integer(accionParActivar));
					local.setEstado(null);
					//TODO PVR SE AGREGO  SET REFERENCE 
					local.setDeco_reference(eDto.getDecoReference());
					local.setDeco_marca(eDto.getMarca());
					log.info("Agregando Deco/Tarjeta [" + idPeticion + ","+ idDeco + "," + idTarjeta + ", "+ eDto.getIdOpCo()+"]");
				}
				catch (EJBException e1)
				{
					log.error("EJBException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ idDeco+ ","+ idTarjeta+ "] : "+ e1.getMessage());
				}
				catch (CreateException e1)
				{
					log.error("CreateException. No pude Crear Registro Deco/Tarjeta ["+ idPeticion+ ","+ idDeco+ ","+ idTarjeta+ "] : "+ e1.getMessage());
				}
				catch(NumberFormatException nf)
				{
					log.error("NumberFormatException",nf);
				}
			}
		}
		catch (FinderException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}

		Long idMensaje = enviaConfiguracionServiciosTVEqYPares(idPeticion.longValue());

		return idMensaje;
	}

	public Long enviaConfiguracionServiciosTVEqYPares(long idPeticion) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
        	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
        	
			// obtiene un nuevo id de mensaje
            
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
            
			// obtiene el id de la peticion Atis
			String pcId=null;
            
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            
			long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            
            
			// busca
			// - el PS tipo PC de la familia TV (el PS tipo PC es como el padre de todos los PS)
			// - los PS correspondientes a los canales tematicos contratados por el cliente (por ejemplo parrilla de deportes, monos animados, cine, etc)
            
			Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
			Producto_servicioKey productoServicioPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
            
			List psTematicos = new ArrayList (10) ;
			List psDecos =new ArrayList(10);
			Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
            
			while (iterPss.hasNext ())
			{
				Producto_servicio_peticionLocal productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
                
				Producto_servicioLocal productoServicio = productoServicioPeticion.getProducto_servicio () ;
                
				Operacion_comercialLocal operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
                
				operacion_comerciaPcKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
                
				Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
                
				// filtra por familia TV y busca el PC y los ps tematicos
                
				Familia_producto_servicioLocal familiaPS = productoServicio.getFamilia_producto_servicio () ;
                
				Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
                
				int idFamilia = familiaPSKey.faps_id.intValue();
                
				// busca si es el PS PC
				if (idFamilia == familiaPcTV)
				{
					log.debug ("peticion: " + idPeticion + " ps tipo PC "  + productoServicioKey.ps_id) ;
                    
					if (productoServicioPeticionPcTV != null)
					{
						String msg = "peticion: " + idPeticion + " tiene mas de un ps tipo PC (el ultimo encontrado fue el "  + productoServicioKey.ps_id + ")" ;
                        
						log.error (msg) ;
						throw new ATiempoAppEx (msg) ;
					}
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					productoServicioPeticionPcTV = productoServicioPeticion ;
					productoServicioPcKey = productoServicioKey ;
                    
					continue ;
				}
                
				if (idFamilia == familiaTematicoTV || idFamilia==familiaPaqueteTV)
				{
					log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					psTematicos.add (productoServicioPeticion) ;
					continue ;
				}
//				TODO PVR validar se agrego  familia
				if(idFamilia == familiaDecoTV || idFamilia == familiaDecoPVRTV)
				{
					log.debug("peticion:"+idPeticion+" ps Deco "+productoServicioKey.ps_id);
					if(pcId==null)
					{
						Agrupacion_atisLocal agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
						pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
					psDecos.add(productoServicioPeticion);
					continue;
				}

				log.debug ("peticion: " + idPeticion + " no es Pc TV ni tampoco Tematico TV ni tampoco Tematico Deco TV." + productoServicioKey.ps_id) ;
			}
            
            
            
			// crea y llena la representacion Java del mensaje
            
			TR017E tr017e = new TR017E ();
            
			tr017e.setId (idCorrelativoMensaje.toString ()) ;
            
			tr017e.setPcId(pcId);
			//if(productoServicioPcKey!=null)
			//	tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
			//if(operacion_comerciaPcKey!=null)
			//	tr017e.setPcCommercialOperation(operacion_comerciaPcKey.opco_id.longValue());
			tr017e.setAtisRequestNumber (peticionAtis) ;
			tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
			tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
			tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
            
			tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
            
			if(peticion.getTip_cli_cd()!=null)
				tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
			else
				tr017e.setClientType ('0') ;
           
			tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
			tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
            
			List listDecoTarj = new ArrayList () ;
            
			Iterator iterDecoTarj = peticion.getDeco_tarjeta ().iterator () ;
            
			while (iterDecoTarj.hasNext ())
			{
				Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
				if(!decoTarjeta.esEsperaDeActivacion() && !decoTarjeta.esEsperaDeEliminacion())
					continue;
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjeta.getPrimaryKey () ;
                
				TR017EEquipment equipment=new TR017EEquipment();
                
				equipment.setCardSerial(decoTarjetaKey.id_tarjeta) ;
				equipment.setCasId(decoTarjetaKey.id_deco) ;

				equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_DEFAULT")).longValue());
				equipment.setCommercialOperation(decoTarjeta.getOpco_id().longValue());
				listDecoTarj.add (equipment) ;
			}
            
			tr017e.setEquipments (listDecoTarj) ;
            
			// canales tematicos
            
			List listTematicos = new ArrayList () ;
            
			Iterator iterPsTematicos = psTematicos.iterator () ;
            
			while (iterPsTematicos.hasNext ())
			{
				Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) iterPsTematicos.next () ;
                
				long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
                
				TR017EPackage package1=new TR017EPackage(); 
                
                
				package1.setProductServiceCode (psId) ;
                
				package1.setCommercialOperation(psPaquete.getIdOperacionComercial().longValue());
                
				listTematicos.add (package1) ;
			}
            
            
			tr017e.setPackages (listTematicos) ;
            
			// guarda el registro del mensaje
            
			Mensaje_estado_tvLocal dbmsg ;
            
			dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
            
			dbmsg.setPeticion (peticion) ;
			
//			TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp()) ;
			dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
//			  dbmsg.set
			// llama a la rutina que envia el mensaje
			new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;
            
			return (idCorrelativoMensaje) ;
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (CreateException cex)
		{
			cex.printStackTrace();
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
	}


	public void limpiarInventarioDTH(Long id_peticion) throws ATiempoAppEx
	{
		try
		{
			int cantidadPcTvOk=0;
			int cantidadDecoTvOk=0;
			int cantidadDecoTvNoOk=0;
			int cantidadPcTv=0;
			int cantidadDecoTv=0;
			
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionLocalHome.findByPrimaryKey(new PeticionKey(id_peticion));
			Collection estado_ps_peticion = null;
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				estado_ps_peticion = producto_servicio_peticionLocal.getEstado_ps_peticion();
				if(familia_producto_servicioKey.faps_id.intValue()==familiaPcTV)
				{
					cantidadPcTv++;
					if(estado_ps_peticion.size()>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) estado_ps_peticion.iterator().next();
						if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreOk)
							cantidadPcTvOk++;
					}
					else if(estado_ps_peticion.size()==0)
						cantidadPcTvOk++;
				}//TODO PVR validar se agrego  familia
				else if(familia_producto_servicioKey.faps_id.intValue()==familiaDecoTV || familia_producto_servicioKey.faps_id.intValue()==familiaDecoPVRTV)
				{
					cantidadDecoTv++;
					if(estado_ps_peticion.size()>0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) estado_ps_peticion.iterator().next();
						if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreOk)
							cantidadDecoTvOk++;
						else if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError)
							cantidadDecoTvNoOk++;	
					}
					else if(estado_ps_peticion.size()==0)
						cantidadDecoTvOk++;
				}
			}
			if(cantidadPcTv>0 && cantidadPcTvOk==0)
				return;
			if(cantidadPcTv==0 && cantidadDecoTv>0 && cantidadDecoTvOk==0)
				return;
			Inventario_dthLocalHome inventario_dthHome=(Inventario_dthLocalHome) HomeFactory.getHome(Inventario_dthLocalHome.JNDI_NAME);
			Inventario_dthLocal ilocal=inventario_dthHome.findByPeticion(id_peticion);
			ilocal.remove();
		}
		catch(FinderException finderException)
		{
			log.info("No hay inventario que limpiar para la peticion:"+id_peticion);	
		}
		catch (EJBException e)
		{
			log.error("EJBException",e);
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION,e);
		}
		catch (RemoveException e)
		{
			log.error("RemoveException",e);
			throw new ATiempoAppEx(ATiempoAppEx.REMOVEEXCEPTION,e);
		}
		catch (NamingException e)
		{
			log.error("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
	}


	public boolean tieneAltaPcYaEcha(Long idPeticion) throws ATiempoAppEx
	{
		try
		{
			Bitacora_peticionLocalHome bitacoraPeticionHome=(Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			AltapctvLocalHome altapctvHome=(AltapctvLocalHome) HomeFactory.getHome(AltapctvLocalHome.JNDI_NAME);
			Collection lista=bitacoraPeticionHome.findByPeticionActividadCerrada(idPeticion,new Long(1022));
			if(lista!=null && lista.size()>0)
			{
				try
				{
					AltapctvLocal local=altapctvHome.findByPrimaryKey(new AltapctvKey(new PeticionKey(idPeticion)));
					return true;
				}
				catch(FinderException e)
				{
					return false;
				}
			}
			return false;
		}
		catch(NamingException ne)
		{
			log.error("NamingException",ne);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,ne);
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}


	public void revisaYMarcaNovedadAutoTv(Long petiNumero) throws ATiempoAppEx
	{
		try
		{
			String novedadAuto=VpistbbaConfig.getVariable("NOVEDADAUTOTV");
			if(novedadAuto==null)
				return;
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
			int cantidadPsDecoAtis=0;
			boolean tienePcAlta=false;
			int cantidadParesActivos=0;
			for(Iterator iterator=local.getDeco_tarjeta().iterator();iterator.hasNext();)
			{
				Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal) iterator.next();
				if(deco_tarjetaLocal.estaActivo())
					cantidadParesActivos++;
			}
			if(cantidadParesActivos==0)
				return;
			String opco_tipo = null;
			for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal ppss=(Producto_servicio_peticionLocal) iterator.next();
				opco_tipo = ppss.getOperacion_comercial().getOpco_tipo();
				if(ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoTV ||ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV)
				{
					if(opco_tipo!=null && opco_tipo.equals("A"))
					{
						if(!peticionesInterfaces.estaOKProductoServicioPeticion(ppss))
							continue;
						cantidadPsDecoAtis++;
					}
				}
				else if(ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaPcTV)
				{
					if(opco_tipo!=null && opco_tipo.equals("A"))
					{
						if(!peticionesInterfaces.estaOKProductoServicioPeticion(ppss))
							continue;
						tienePcAlta=true;
					}
				}
			}
			
			if(tienePcAlta)
				cantidadPsDecoAtis++;
			if(cantidadParesActivos>cantidadPsDecoAtis)
			{
				Fecha fecha=new Fecha();
					
				UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
				Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
				Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
				Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
				Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
					
				ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
				ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
				Long idAct=actividadSessionLocal.getIdActividad(VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONTROLI"),new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
				if(idAct==null)
					return;	
				boolean genero = false;		
				log.debug("Entro al for para generar la novedad");					
				
				for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();){
					Producto_servicio_peticionLocal ppss=(Producto_servicio_peticionLocal) iterator.next();
					//----if(ppss.getFamiliaKey().faps_id.intValue()!=ComunInterfaces.familiaPcTV)
					//----	continue;
					// -- Se cambiaron las condiciones para generar las novedades CR- 12278
					
					if(genero)return;
						log.debug("Valor de la familia "+ppss.getFamiliaKey().faps_id.intValue());
					if(ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaPcTV||ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoTV ||ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV){
						genero = true;					
					
						Producto_servicioLocal producto_servicioLocal=ppss.getProducto_servicio();
						Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
						Operacion_comercialLocal operacion_comercialLocal=ppss.getOperacion_comercial();
						Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
						
						Collection listaEstadoPsPet=ppss.getEstado_ps_peticion();
						Long codNovedad=new Long(novedadAuto);
						
						if(listaEstadoPsPet.size()>0){					
							Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(codNovedad);
							Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
							
							//Tengo una causa asociada tengo que actualizar
							Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPsPet.iterator().next();
							estado_ps_peticionLocal.setCod_actividad(idAct);
							estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
							estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
							estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
						
							Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
							Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
							UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
							long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
							Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
						
							causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
							causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
							causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
							causal_peticionLocal.setCod_actividad(idAct);
						}else{
							Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(codNovedad);
							Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
							
							long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
							Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),ppss.getProducto_servicio(),ppss);
							estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
							estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
							estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
							estado_ps_peticionLocal.setCod_actividad(idAct);
											
							Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
							Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
							UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
							long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
							Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
							causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
							causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
							causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
							causal_peticionLocal.setCod_actividad(idAct);
						}
					}else{
						log.debug("No tiene ningun codigo de deco");
						//return;
					}
					//return;
				}
			}
		}
		catch(NamingException ne)
		{
			log.error("NamingException",ne);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,ne);
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}
	
	/**
			 * @param listadoPS
			 */
			// TODO: AT-1035 - PVR - Inicio - Juan - 25-Feb-2008 - Carga los datos de la Migracion
			//request.getParameter("cantidadDecoAlta");
			//request.getAttribute("tipoDecoBaja");
			//request.getAttribute("codigoDecoAlta");
	public int cargarInfoMigracionDeco(Long idPeticion)throws ATiempoAppEx {
		
				int cantidadBaja=0;
				
				try {
					InfoComunColombiaBusinessInterface infoComunColombiaBI;
		
					infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
		
					ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
		
				for (int i=0; listadoPS!=null && i<listadoPS.size(); i++)
				{
			
					ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
			
			
					if ( psDto.getIdFaps().intValue()== familiaDecoTV ||psDto.getIdFaps().intValue()==familiaDecoPVRTV || psDto.getIdFaps().intValue()==ComunInterfaces.familiaDecoHDTV){
				

						if ( ComunInterfaces.operacionBCP.equals(psDto.getTipoOperacionComercial())){
						
							cantidadBaja= cantidadBaja+1;
						
						}
					
					}
				}
				
				
				} catch (ATiempoAppEx e) {
						
					log.error("ATiempoAppEx",e);
					throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
				}
				
		return cantidadBaja;
			}
			
	/**
			 * @param listadoPS
			 */
			// TODO: AT-1035 - PVR - Inicio - Juan - 25-Feb-2008 - Carga los datos de la Migracion
			//request.getParameter("cantidadDecoAlta");
			//request.getAttribute("tipoDecoBaja");
			//request.getAttribute("codigoDecoAlta");
	public String cargarInfoMigracionDeco2(Long idPeticion) throws ATiempoAppEx{
		
				
				String tipodecoBaja="";
				try {
					InfoComunColombiaBusinessInterface infoComunColombiaBI;
		
					infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
		
					ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
		
				for (int i=0; listadoPS!=null && i<listadoPS.size(); i++)
				{
			
					ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
			
			
					if (psDto.getIdFaps().intValue()== familiaDecoTV ||psDto.getIdFaps().intValue()==familiaDecoPVRTV || psDto.getIdFaps().intValue()==ComunInterfaces.familiaDecoHDTV){
				

						
						if ( ComunInterfaces.operacionBCP.equals(psDto.getTipoOperacionComercial())){
						
							
						
							if(psDto.getIdFaps().intValue()== familiaDecoTV){
								tipodecoBaja=	desHCDecoSTD;
							}
							if(psDto.getIdFaps().intValue()== familiaDecoPVRTV){
								tipodecoBaja=desHCDecoPVR;
							}
							if(psDto.getIdFaps().intValue()== ComunInterfaces.familiaDecoHDTV){
								tipodecoBaja=desHCDecoHDTV;
							}
						}
					
					}
				}
				
			
				} catch (ATiempoAppEx e) {
						
					log.error("ATiempoAppEx",e);
					throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
				}
			
			return tipodecoBaja;
			}
//	<!--TODO: Defecto AT-1138 Se agrega metodo que retorna la cantidad de Ps de alta PVR- PVR- Inicio - Juan-->
	public int retornarCantidadDecosAltaPvr(Long idPeticion)
			throws ATiempoAppEx {

		int retorno = 0;

		try {
			InfoComunColombiaBusinessInterface infoComunColombiaBI;

			infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();

			ArrayList listadoPS = infoComunColombiaBI
					.getPsYTipoPcNoRealizado(idPeticion);

			for (int i = 0; listadoPS != null && i < listadoPS.size(); i++) {

				ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);

				if (psDto.getIdFaps().intValue() == familiaDecoPVRTV) {

					if (ComunInterfaces.operacionA.equals(psDto
							.getTipoOperacionComercial())) {

						retorno = retorno + 1;

					}

				}
			}

		} catch (ATiempoAppEx e) {

			log.error("ATiempoAppEx", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		}
		return retorno;
	}
	
	private String getNombreBandeja(String plataforma){
		return VpistbbaConfig.getVariable("BANDEJA_"+plataforma);
	}
		
	private ErrorLegadoLocal getErrorLegado(String codigoError,String codigoTr){
		ErrorLegadoLocal errorLegado = null;
		try{
			ErrorLegadoLocalHome errorLegadoHome = (ErrorLegadoLocalHome) HomeFactory.getHome( ErrorLegadoLocalHome.JNDI_NAME);
			errorLegado = errorLegadoHome.findByErrorTr(codigoError, codigoTr);
		} catch (javax.ejb.FinderException e) {
			e.printStackTrace();
		} catch (NamingException e) {
		//TODO ver que pasa con esta excepcion
			e.printStackTrace();
		}
		return errorLegado;
	}
	
	public int pasoPorInstalacion(Long idPeticion) throws ATiempoAppEx {
		int retorno=0;
		// Obtengo la Bitacora de la Petición.
		try{
			BitacoraLocalHome bHome = (BitacoraLocalHome) HomeFactory.getHome(BitacoraLocalHome.JNDI_NAME);
			BitacoraLocal bLocal = bHome.create();
			ArrayList listadoBitacora = bLocal.getListadoBitacora(idPeticion);
		if (listadoBitacora!=null){
		
			Iterator itBi = listadoBitacora.iterator();		
			String actividadAnterior;
			while (itBi.hasNext()){
				
				actividadAnterior = ((BitacoraDTO) itBi.next()).getActividad();
				if (actividadAnterior.equals("Control Instalacion")) {
					retorno=1;				
				}
			}
		}
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return retorno;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#enviaConfiguracionServiciosTVEnVuelo(long)
	 */
    public Long enviaConfiguracionServiciosTVEnVuelo (long idPeticion, boolean elementosViejos, boolean tienefamiliaPaqueteTV, boolean tienefamiliaTematicoTV) throws ATiempoAppEx{
        //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
        try
        {
        	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME);
        	Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome) HomeFactory.getHome (Producto_servicio_en_vueloLocalHome.JNDI_NAME);
        	Producto_servicioLocalHome productoServicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome (Producto_servicioLocalHome.JNDI_NAME);
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME);

        	
        	RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
            // obtiene un nuevo id de mensaje
            
            Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
            
            // obtiene el id de la peticion Atis
            String pcId=null;
            PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            boolean existeConfiguracionDTH = false;
            List listTematicos = new ArrayList () ;
            boolean pintarPSOriginales = false;
            boolean excluyente = false;
            boolean tienefamiliaPaqueteTVEnVuelo = false;
            boolean tienefamiliaTematicoTVEnVuelo = false;
            
            // busca
            // - el PS tipo PC de la familia TV (el PS tipo PC es como el padre de todos los PS)
            // - los PS correspondientes a los canales tematicos contratados por el cliente (por ejemplo parrilla de deportes, monos animados, cine, etc)
            
            Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
            Producto_servicioKey productoServicioPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey2 = null;
            
            List psTematicos = new ArrayList () ;
            List psDecos =new ArrayList(10);
            
            
            Iterator iterPss = null;
            Iterator iterPeticionPss = null;
            
            if (!tienefamiliaPaqueteTV && !tienefamiliaTematicoTV){
            	pintarPSOriginales = false;
            }else{
            	pintarPSOriginales = true;
            }
            
            
            Producto_servicio_peticionLocal productoServicioPeticion = null;
            Producto_servicioLocal productoServicio = null;
            Operacion_comercialLocal operacion_comercialLocal = null;
            Familia_producto_servicioLocal familiaPS = null;
            Familia_producto_servicioKey familiaPSKey  = null;
            int idFamilia = 0;
            Agrupacion_atisLocal agrupacion_atisLocal = null;
            Producto_servicio_en_vueloLocal psevDTO = null;
            
            if (pintarPSOriginales){
            	Collection novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPeti_Numero(new Long(idPeticion));
            	Iterator iterAux = novedadEnVuelo.iterator();
            	
            	while (iterAux.hasNext()){
            		psevDTO = (Producto_servicio_en_vueloLocal) iterAux.next();
        	 	           	 	
            	 	Producto_servicioKey keyPS = new Producto_servicioKey(psevDTO.getPs_id());
    	        	Producto_servicioLocal psLocal = productoServicioLocalHome.findByPrimaryKey(keyPS);
    	        	
    	        	familiaPS = psLocal.getFamilia_producto_servicio () ;
                    familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
                    idFamilia = familiaPSKey.faps_id.intValue();

        	 	
                    if (idFamilia == familiaTematicoTV){
                		tienefamiliaTematicoTVEnVuelo = true; 
                	}else if (idFamilia==familiaPaqueteTV){
                		tienefamiliaPaqueteTVEnVuelo = true;
                	}
            	}
            	
            	
            	
            	iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
            }else{
            	Collection novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPeti_Numero(new Long(idPeticion));
            	iterPss = novedadEnVuelo.iterator();
            	
            	iterPeticionPss = peticion.getProducto_servicio_peticion ().iterator () ;
            }
            
          
            while (iterPss.hasNext ())
            {
            	 if (pintarPSOriginales){
                    productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
                    productoServicio = productoServicioPeticion.getProducto_servicio () ;
                    operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
                    operacion_comerciaPcKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
                    
                    Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
                    
                    // filtra por familia TV y busca el PC y los ps tematicos
                    
    	            familiaPS = productoServicio.getFamilia_producto_servicio () ;
                    familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
                    idFamilia = familiaPSKey.faps_id.intValue();           	 	
            	 }else{
            	 	psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
            	 	if (psevDTO.getTipo_peticion().indexOf("_OLD") != -1){
                	 	operacion_comerciaPcKey = new Operacion_comercialKey(new Long(psevDTO.getOpco_id().longValue()));
                	 	
                	 	Producto_servicioKey keyPS = new Producto_servicioKey(psevDTO.getPs_id());
        	        	Producto_servicioLocal psLocal = productoServicioLocalHome.findByPrimaryKey(keyPS);
        	        	
        	        	familiaPS = psLocal.getFamilia_producto_servicio () ;
                        familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
                        idFamilia = familiaPSKey.faps_id.intValue();
                        psevDTO.setPspe_cantidad(new Integer(idFamilia));
            	 	}
            	 }
                
                if (idFamilia == familiaTematicoTV || idFamilia==familiaPaqueteTV){
                	 if (pintarPSOriginales){
                	 	agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
    					pcId=agrupacion_atisLocal.getNum_ide_nu();
                	 	
                	 	if (tienefamiliaTematicoTVEnVuelo && tienefamiliaPaqueteTVEnVuelo){
                	 		productoServicioPeticion.setObs_sub_ds(new Integer(idFamilia).toString());
                            psTematicos.add (productoServicioPeticion) ;
                	 	}else if (tienefamiliaPaqueteTVEnVuelo && !tienefamiliaTematicoTVEnVuelo){
                	 		if (idFamilia==familiaPaqueteTV){
                	 			productoServicioPeticion.setObs_sub_ds(new Integer(idFamilia).toString());
                                psTematicos.add (productoServicioPeticion) ;
                	 		}
                	 	}else if (!tienefamiliaPaqueteTVEnVuelo && tienefamiliaTematicoTVEnVuelo){
                	 		if (idFamilia==familiaTematicoTV){
                	 			productoServicioPeticion.setObs_sub_ds(new Integer(idFamilia).toString());
                                psTematicos.add (productoServicioPeticion) ;
                	 		}
                	 	}
               	 		
                        continue ;
                	 }else{
                	 	if (psevDTO.getTipo_peticion().indexOf("_OLD") != -1){             
                	 		
                	 		 while (iterPeticionPss.hasNext ()){
                	 		 	int idFamiliaPeticion = 0;
                	 		 	
                	 		 	productoServicioPeticion = (Producto_servicio_peticionLocal) iterPeticionPss.next () ;
                                productoServicio = productoServicioPeticion.getProducto_servicio () ;
                                operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
                                operacion_comerciaPcKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
                                
                                Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
                                
                                // filtra por familia TV y busca el PC y los ps tematicos
                                
                	            familiaPS = productoServicio.getFamilia_producto_servicio () ;
                                familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
                                idFamiliaPeticion = familiaPSKey.faps_id.intValue();    
                                
                                if (idFamiliaPeticion == familiaTematicoTV || idFamiliaPeticion==familiaPaqueteTV){
                                	agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
                					pcId=agrupacion_atisLocal.getNum_ide_nu();
                					break;
                                }
                	 		 }
                	 		
                	 		psTematicos.add (psevDTO) ;
                	 	}
                	 }
                }
            }
            
            
            
            // crea y llena la representacion Java del mensaje
            TR017E tr017e = new TR017E ();
            tr017e.setId (idCorrelativoMensaje.toString ()) ;
            tr017e.setPcId(pcId);

            Collection peticionesTVBasico = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(new Long(idPeticion), ComunInterfaces.TV_BASICO_EN_VUELO);
            if (peticionesTVBasico.isEmpty()){
            	if(productoServicioPcKey!=null)
                	tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
                
                if(operacion_comerciaPcKey2!=null)
                	tr017e.setPcCommercialOperation(operacion_comerciaPcKey2.opco_id.longValue());
                else if(operacion_comerciaPcKey!=null)
                	tr017e.setPcCommercialOperation(operacion_comerciaPcKey.opco_id.longValue());
            }else{
                Iterator iterPsBasico = peticionesTVBasico.iterator () ;
                while (iterPsBasico.hasNext ()){
                	Producto_servicio_en_vueloLocal psVsTemp = (Producto_servicio_en_vueloLocal) iterPsBasico.next () ;
                	
                	tr017e.setPcProductServiceCode(ComunInterfaces.operacionParRefresh);
                	tr017e.setPcCommercialOperation(ComunInterfaces.operacionParRefresh);
                	
                	TR017EPackage package1=new TR017EPackage(); 
    				package1.setProductServiceCode (psVsTemp.getPs_id().longValue());
    				package1.setCommercialOperation(new Long(ComunInterfaces.altaMigracionPS).longValue());
    				listTematicos.add (package1) ;
                }           
            }
            
            tr017e.setAtisRequestNumber (peticionAtis) ;
            tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
            tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
            tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
            tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
            
            if(peticion.getTip_cli_cd()!=null)
            	tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
            else
				tr017e.setClientType ('0') ;
           
            tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
            tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
            
            // deco / tarjeta
            List listDecoTarj = new ArrayList () ;
            Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME) ;
			Collection listaDecos= decoTarjetaLocalHome.findByPeticion(new Long(idPeticion));
			Iterator iterDecoTarj=listaDecos.iterator();
			Deco_tarjetaLocal decoTarjeta = null;
			Deco_tarjetaKey decoTarjetaKey = null;

			while (iterDecoTarj.hasNext ()){
                decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj.next () ;
                if(!decoTarjeta.esEsperaDeActivacion() && !decoTarjeta.esEsperaDeEliminacion())
                	continue;
                decoTarjetaKey = (Deco_tarjetaKey) decoTarjeta.getPrimaryKey () ;
                
				TR017EEquipment equipment=new TR017EEquipment();
                
				equipment.setCardSerial(decoTarjetaKey.id_tarjeta) ;
				equipment.setCasId(decoTarjetaKey.id_deco) ;

                equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_DEFAULT")).longValue());
                equipment.setCommercialOperation(decoTarjeta.getOpco_id().longValue());
                listDecoTarj.add (equipment) ;
            }
            
            tr017e.setEquipments (listDecoTarj) ;
       
            
           
            Collection peticionesTVAdicional = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(new Long(idPeticion), ComunInterfaces.TV_ADICIONAL_EN_VUELO);
			if(!peticionesTVAdicional.isEmpty()){
                Iterator iterPsTematicos = peticionesTVAdicional.iterator () ;
                while (iterPsTematicos.hasNext ()){
                	Producto_servicio_en_vueloLocal psVsTemp = (Producto_servicio_en_vueloLocal) iterPsTematicos.next () ;
                	                	
    				TR017EPackage package1=new TR017EPackage(); 
    				package1.setProductServiceCode (psVsTemp.getPs_id().longValue());
    				
    	        	Producto_servicioKey keyPS = new Producto_servicioKey(psVsTemp.getPs_id());
    	        	Producto_servicioLocal psLocal = productoServicioLocalHome.findByPrimaryKey(keyPS);

    	        	familiaPS = psLocal.getFamilia_producto_servicio () ;
                    familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
                    idFamilia = familiaPSKey.faps_id.intValue();
    	        	
    	        	if (idFamilia == ComunInterfaces.familiaTematicoTV){
    	        		package1.setCommercialOperation(new Long(ComunInterfaces.operacionParActivar).longValue());
    	        	}else if (idFamilia == ComunInterfaces.familiaPaqueteTV){
    	        		package1.setCommercialOperation(new Long(ComunInterfaces.altaMigracionPS).longValue());
    	        	}
    				
                    
                    listTematicos.add (package1) ;
                }
            }
			
			if(!psTematicos.isEmpty()){
				Iterator iterPsTematicos = psTematicos.iterator () ;
				while (iterPsTematicos.hasNext ()){
				 	TR017EPackage package1=new TR017EPackage();
				 	
					 if (pintarPSOriginales){
					 	Producto_servicio_peticionLocal psLocal = (Producto_servicio_peticionLocal) iterPsTematicos.next();
					 	
	    				package1.setProductServiceCode (psLocal.getPsId().longValue());
    					if (new Integer(psLocal.getObs_sub_ds()).intValue() == ComunInterfaces.familiaTematicoTV){
    						package1.setCommercialOperation(new Long(ComunInterfaces.operacionParDesactivar).longValue());
    					}else if (new Integer(psLocal.getObs_sub_ds()).intValue() == ComunInterfaces.familiaPaqueteTV){
    						package1.setCommercialOperation(new Long(ComunInterfaces.bajaMigracionPS).longValue());
    					}
	    				
	    				listTematicos.add (package1) ;
					 }else{
					 	Producto_servicio_en_vueloLocal psLocal = (Producto_servicio_en_vueloLocal) iterPsTematicos.next();
					 	
					 	package1.setProductServiceCode (psLocal.getPs_id().longValue());
	    				if (psLocal.getPspe_cantidad().intValue() == ComunInterfaces.familiaTematicoTV){
	    					package1.setCommercialOperation(new Long(ComunInterfaces.operacionParDesactivar).longValue());
	    				}else if (psLocal.getPspe_cantidad().intValue() == ComunInterfaces.familiaPaqueteTV){
	    					package1.setCommercialOperation(new Long(ComunInterfaces.bajaMigracionPS).longValue());
	    				}
	    				
	    				if (psLocal.getTipo_peticion().indexOf("STOP") == -1){
	    					listTematicos.add (package1) ;
	    				}
	    				
					 }
					            
                    
				}
			}
			
			if (!listTematicos.isEmpty()){
				tr017e.setPackages (listTematicos) ;
			}
            //end TODO
                       
            // guarda el registro del mensaje
            Mensaje_estado_tvLocal dbmsg ;
            dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
            dbmsg.setPeticion (peticion) ;
			//TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp());
            dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
            dbmsg.setCod_actividad_generadora("Director de Flujos.Instalacion.Control_Instalacion") ;

            // llama a la rutina que envia el mensaje
            new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;
            return (idCorrelativoMensaje) ;
        }catch (NumberFormatException e){
			log.error ("NumberFormatException", e) ;
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }catch (FinderException e){
			log.error ("FinderException", e) ;
            throw new ATiempoAppEx (ATiempoAppEx.FINDER);
        }catch (CreateException cex){
            log.error ("error creando registro", cex) ;
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        } catch (NamingException e) {
			log.error ("error creando registro", e) ;
		    throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
    }
    
	   public void reversarProductoPeticionEnVuelo(Long nroPeticion) throws ATiempoAppEx {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection equiposInternet = new ArrayList();
		boolean elementosViejosTVAdicional = false;
		boolean elementosViejosTVBasico = false;
					
		try {
			Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome) HomeFactory.getHome (Producto_servicio_en_vueloLocalHome.JNDI_NAME);
			Collection novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_ADICIONAL_EN_VUELO);
			
			if (!novedadEnVuelo.isEmpty()){
				Iterator iterPss = novedadEnVuelo.iterator();
					
				while (iterPss.hasNext ()){
					Producto_servicio_en_vueloLocal psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
					psevDTO.remove();
				}
					
				elementosViejosTVAdicional = true;
			}
			
			if (elementosViejosTVAdicional){
				novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_ADICIONAL_EN_VUELO_OLD);
				
				if (!novedadEnVuelo.isEmpty()){
					Iterator iterPss = novedadEnVuelo.iterator();
						
					while (iterPss.hasNext ()){
						Producto_servicio_en_vueloLocal psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
						psevDTO.setTipo_peticion(ComunInterfaces.TV_ADICIONAL_EN_VUELO_S);
					}
				}			
			}
			
			novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_ADICIONAL_EN_VUELO);
			
			if (!novedadEnVuelo.isEmpty()){
				Iterator iterPss = novedadEnVuelo.iterator();
					
				while (iterPss.hasNext ()){
					Producto_servicio_en_vueloLocal psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
					psevDTO.remove();
				}
					
				elementosViejosTVAdicional = true;
			}
			
			if (elementosViejosTVAdicional){
				novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_ADICIONAL_EN_VUELO_OLD);
				
				if (!novedadEnVuelo.isEmpty()){
					Iterator iterPss = novedadEnVuelo.iterator();
						
					while (iterPss.hasNext ()){
						Producto_servicio_en_vueloLocal psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
						psevDTO.setTipo_peticion(ComunInterfaces.TV_ADICIONAL_EN_VUELO_S);
					}
				}			
			}
			
			novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_BASICO_EN_VUELO);
			
			if (!novedadEnVuelo.isEmpty()){
				Iterator iterPss = novedadEnVuelo.iterator();
					
				while (iterPss.hasNext ()){
					Producto_servicio_en_vueloLocal psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
					psevDTO.remove();
				}
					
				elementosViejosTVBasico = true;
			}
			
			if (elementosViejosTVBasico){
				novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(nroPeticion, ComunInterfaces.TV_BASICO_EN_VUELO_OLD);
				
				if (!novedadEnVuelo.isEmpty()){
					Iterator iterPss = novedadEnVuelo.iterator();
						
					while (iterPss.hasNext ()){
						Producto_servicio_en_vueloLocal psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
						psevDTO.setTipo_peticion(ComunInterfaces.TV_BASICO_EN_VUELO_S);
					}
				}			
			}
			
		} catch (NamingException e) {
			log.error("Error en reversarProductoPeticionEnVuelo: " + e);
			throw new ATiempoAppEx(e.getMessage());
		} catch (FinderException e) {
			log.error("Error en reversarProductoPeticionEnVuelo: " + e);
			throw new ATiempoAppEx(e.getMessage());
		}  catch (RemoveException e) {
			log.error("Error en reversarProductoPeticionEnVuelo: " + e);
			throw new ATiempoAppEx(e.getMessage());
		}  
    }
	   
	public Long enviaConfiguracionServiciosTVAgendaSC (long idPeticion, ArrayList decosAInstalar, boolean haEnviadoMensaje, String idAgendaSC) throws ATiempoAppEx{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
			RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
			
			// obtiene un nuevo id de mensaje
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			
			// obtiene el id de la peticion Atis
			String pcId=null;
	        
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
			long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            boolean existeConfiguracionDTH = false;
	            
            // busca
            // - el PS tipo PC de la familia TV (el PS tipo PC es como el padre de todos los PS)
            // - los PS correspondientes a los canales tematicos contratados por el cliente (por ejemplo parrilla de deportes, monos animados, cine, etc)
            
            Producto_servicio_peticionLocal productoServicioPeticionPcTV  = null;
            Producto_servicioKey productoServicioPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey = null;
			Operacion_comercialKey operacion_comerciaPcKey2 = null;
            
            List psTematicos = new ArrayList (10) ;
            List psDecos =new ArrayList(10);
            Iterator iterPss = peticion.getProducto_servicio_peticion ().iterator () ;
            Producto_servicio_peticionLocal productoServicioPeticion = null;
            Producto_servicioLocal productoServicio = null;
            Operacion_comercialLocal operacion_comercialLocal = null;
            Familia_producto_servicioLocal familiaPS = null;
            Familia_producto_servicioKey familiaPSKey  = null;
            int idFamilia = 0;
            Agrupacion_atisLocal agrupacion_atisLocal = null;
            long opComercialAltaTraslado =  ComunInterfaces.altaTraslado;
            long opComercialBajaTraslado =  ComunInterfaces.bajaTraslado;
            
            while (iterPss.hasNext ()){
            	productoServicioPeticion = (Producto_servicio_peticionLocal) iterPss.next () ;
            	productoServicio = productoServicioPeticion.getProducto_servicio () ;
            	operacion_comercialLocal=productoServicioPeticion.getOperacion_comercial();
            	operacion_comerciaPcKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
            	
	            Producto_servicioKey productoServicioKey = (Producto_servicioKey) productoServicio.getPrimaryKey () ;
	                
	            // filtra por familia TV y busca el PC y los ps tematicos
	                
		        familiaPS = productoServicio.getFamilia_producto_servicio () ;
	                
	            familiaPSKey = (Familia_producto_servicioKey) familiaPS.getPrimaryKey () ;
	                
	            idFamilia = familiaPSKey.faps_id.intValue();
	                
	            // busca si es el PS PC
	            if (idFamilia == familiaPcTV){
	            	log.debug ("peticion: " + idPeticion + " ps tipo PC "  + productoServicioKey.ps_id) ;
	                String opCoProducto = operacion_comerciaPcKey.opco_id.toString();
	                if (productoServicioPeticionPcTV != null && opComercialAltaTraslado != Integer.parseInt(opCoProducto) && opComercialBajaTraslado != Integer.parseInt(opCoProducto)){
	                	String msg = "peticion: " + idPeticion + " tiene mas de un ps tipo PC (el ultimo encontrado fue el "  + productoServicioKey.ps_id + ")" ;
	                	log.error (msg) ;
	                	throw new ATiempoAppEx (msg) ;
	                }
	                
	                if(pcId==null){
	                	agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
	                	pcId=agrupacion_atisLocal.getNum_ide_nu();
	                }
	                
	                productoServicioPeticionPcTV = productoServicioPeticion ;
	                productoServicioPcKey = productoServicioKey ;
	                    
	                //Corrección en Código de Operación Comercial para el alta de TV
	                operacion_comerciaPcKey2=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
	                continue;
	            }

	            if (idFamilia == familiaTematicoTV || idFamilia==familiaPaqueteTV){
	            	log.debug ("peticion: " + idPeticion + " ps tematico "  + productoServicioKey.ps_id) ;
	            	if(pcId==null){
	            		agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
	            		pcId=agrupacion_atisLocal.getNum_ide_nu();
	            	}
	            	psTematicos.add (productoServicioPeticion) ;
	            	continue ;
	            }
	            
	            //TODO PVR VALIDAR SI VA ACA
	            if(idFamilia == familiaDecoTV ||idFamilia == familiaDecoPVRTV || idFamilia == familiaDecoHDTV){
	            	log.debug("peticion:"+idPeticion+" ps Deco "+productoServicioKey.ps_id);
	            	if(pcId==null){
	            		agrupacion_atisLocal=productoServicioPeticion.getFk_01_subp_atis().getFk_agru_sub();
	            		pcId=agrupacion_atisLocal.getNum_ide_nu();
					}
	            	
	            	psDecos.add(productoServicioPeticion);
	            	continue;
	            }

	            log.debug ("peticion: " + idPeticion + " no es Pc TV ni tampoco Tematico TV ni tampoco Tematico Deco TV." + productoServicioKey.ps_id) ;
            }

            // crea y llena la representacion Java del mensaje
	        
            TR017E tr017e = new TR017E ();
	        tr017e.setId (idCorrelativoMensaje.toString ()) ;
	        
	        tr017e.setPcId(pcId);
	        
	        if (!haEnviadoMensaje){
	        	 if(productoServicioPcKey!=null)
		        	tr017e.setPcProductServiceCode(productoServicioPcKey.ps_id.longValue());
		            
		        if(operacion_comerciaPcKey2!=null)
		        	tr017e.setPcCommercialOperation(operacion_comerciaPcKey2.opco_id.longValue());
		        else if(operacion_comerciaPcKey!=null)
		        	tr017e.setPcCommercialOperation(operacion_comerciaPcKey.opco_id.longValue());
	        }
	        
	       
	        
	        tr017e.setAtisRequestNumber (peticionAtis) ;
	        tr017e.setClientName (peticion.getNom_ds ().trim ()) ;
	        tr017e.setClientLastName (peticion.getPri_ape_ds ().trim ()) ;
	        tr017e.setClientSecondLastName (peticion.getSeg_ape_ds ().trim ()) ;
	        tr017e.setCity ( ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ) ;
	        
	        if(peticion.getTip_cli_cd()!=null)
	        	tr017e.setClientType (peticion.getTip_cli_cd().charAt(0)) ;
	        else
	        	tr017e.setClientType ('0') ;
	        
	        tr017e.setClientDocumentType (peticion.getTip_doc_cd() ) ;
	        tr017e.setClientDocumentNumber (Long.parseLong (peticion.getNum_doc_cli_cd() )) ;
	        
	        // deco / tarjeta
	        List listDecoTarj = new ArrayList () ;
	        
			Iterator iterDecoTarj=decosAInstalar.iterator();
			DecoTarDTO decoTarjeta = null;
			Deco_tarjetaKey decoTarjetaKey = null;
	        
			while (iterDecoTarj.hasNext ()){
				decoTarjeta = (DecoTarDTO) iterDecoTarj.next () ;
				
				TR017EEquipment equipment=new TR017EEquipment();
				equipment.setCardSerial(decoTarjeta.getTarjeta()) ;
				equipment.setCasId(decoTarjeta.getDeco()) ;
				
				if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoSTD)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_DEFAULT")).longValue());
				}else if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoHDTV)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_HD")).longValue());
				}else if (decoTarjeta.getDeco_reference().equals(ComunInterfaces.desHCDecoPVR)){
					equipment.setProductServiceCode(new Long(VpistbbaConfig.getVariable("PS_DECO_PVR")).longValue());
				}
					
	            equipment.setCommercialOperation(decoTarjeta.getOperationComercial().longValue());
	            equipment.setDecoSerial(decoTarjeta.getDecoSerial());
	            equipment.setDecoType(decoTarjeta.getDeco_reference());
	            equipment.setDecoBrand(decoTarjeta.getDecoBrand());
	            
	            listDecoTarj.add (equipment) ;
			}
	            
	        tr017e.setEquipments (listDecoTarj) ;
	        //TODO: Raúl Triviño - 13/10/2009 - ajuste del requerimiento 00029981_AltaTematicosEquipos
	        /*Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection peticiones = peticion_flujoLocalHome.findByIdPeticion(new Long(idPeticion));
				
			/*for (Iterator iter = peticiones.iterator(); iter.hasNext();) {					
			 	Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
					
				if (peticionFlujo.().intValue() == Integer.parseInt(ComunInterfaces.ACTI_ID_Configuracion_DTH) 
				        && peticionFlujo.getPefl_estado().equals(ComunInterfaces.PEFL_ESTADO_OK)
				        && (peticionFlujo.getPrse_id().longValue() == Long.parseLong(ComunInterfaces.PRSE_ID_Configuracion_DTH_1)
				        ||  peticionFlujo.getPrse_id().longValue() == Long.parseLong(ComunInterfaces.PRSE_ID_Configuracion_DTH_2))){
				    existeConfiguracionDTH = true;
				    break;
				}
			}*/
				
			boolean tienePcEnAlta=recursosTVDelegate.tienePcEnAlta(new Long(idPeticion));
				
			if(tienePcEnAlta && !haEnviadoMensaje){
				List listTematicos = new ArrayList () ;
	            Iterator iterPsTematicos = psTematicos.iterator () ;
	            while (iterPsTematicos.hasNext ()){
	            	Producto_servicio_peticionLocal psPaquete = (Producto_servicio_peticionLocal) iterPsTematicos.next () ;
	                long psId = ((Producto_servicioKey) psPaquete.getProducto_servicio ().getPrimaryKey ()).ps_id.longValue () ;
	    			TR017EPackage package1=new TR017EPackage(); 
	    			package1.setProductServiceCode (psId) ;
	                package1.setCommercialOperation(psPaquete.getIdOperacionComercial().longValue());
	                listTematicos.add (package1) ;
	            }
	            tr017e.setPackages (listTematicos) ;
			}
	        //end TODO
	                       
	        // guarda el registro del mensaje
	        Mensaje_estado_tvLocal dbmsg ;
	        dbmsg = msgEstadoTvLocalHome.create (idCorrelativoMensaje);
	        dbmsg.setPeticion (peticion) ;
			//TODO CR10356- agonzalez cambio de tipo de String a Date
			//dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
			dbmsg.setFecha_envio (new Fecha().getTimestamp());
	        dbmsg.setMensaje_estado (this.buscaMensajeEstado (estadoEspera)) ;
	        
	        if (idAgendaSC != null && idAgendaSC.length() > 0){
	         	dbmsg.setDesc_error(idAgendaSC);
            }
	                        
	        // llama a la rutina que envia el mensaje
	        new SolicitudConfiguracionServiciosTVMQ ().enviarMensaje (tr017e) ;
	        return (idCorrelativoMensaje) ;
		}catch (NumberFormatException e){
			log.error ("NumberFormatException", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}catch (FinderException e){
			log.error ("FinderException", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}catch (CreateException cex){
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		} catch (NamingException e) {
			log.error ("error creando registro", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
	}
	
	public void marcarNovedadQuiebrePeticionesVueloAgendaSC(Long petiNumero, ArrayList listaDecosTarjetasAgendaSC) throws ATiempoAppEx{
		try
		{
			String novedadAuto = VpistbbaConfig.getVariable("NOVEDADAUTOTV");
			String quiebreAuto = VpistbbaConfig.getVariable("QUIEBREAUTOTV");
			if(novedadAuto==null)
				return;
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
			/*Conteo Decos de Agenda SC **************************/
			ArrayList listaDecos = new ArrayList();
			for (int i=0; i < listaDecosTarjetasAgendaSC.size(); i++){
				TR711SMaterials decosTarjetas = (TR711SMaterials)listaDecosTarjetasAgendaSC.get(i);				
				if (decosTarjetas.getTypeEquipment().indexOf("DECO")!=-1){
					listaDecos.add(decosTarjetas);
				}
			}				
			
			int contadorHDASC = 0;
			int contadorPVRASC = 0;
			int contadorSTDASC = 0;			
			int contadorTotalDecosASC = 0;
			//Creación de los elementos de Decos Tarjetas
			for (int j=0;j<listaDecos.size();j++){
				TR711SMaterials deco = (TR711SMaterials)listaDecos.get(j);							
				if (deco.getTypeEquipment().indexOf(desHCDecoSTD) != -1){
					contadorSTDASC += 1;
				}else if (deco.getTypeEquipment().indexOf(desHCDecoPVR) != -1){
					contadorPVRASC += 1;
				}else if (deco.getTypeEquipment().indexOf(desHCDecoHDTV) != -1){
					contadorHDASC += 1;
				}				
			}
			contadorTotalDecosASC = contadorSTDASC + contadorPVRASC + contadorHDASC;
			
			/*******************/
			Collection psPeticion=peticionLocal.getProducto_servicio_peticion();
			Iterator psPeticionIt=null;						
			String tipoDeco="";
			int contadorHD = 0;
			int contadorPVR = 0;
			int contadorSTD = 0;
			int contadorTotalDecosPedido = 0;
			for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
				Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
				if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
					contadorHD += 1;						
					log.debug("Tiene deco HD ");
				}else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV){
					contadorPVR += 1;						
					log.debug("Tiene deco PVR");
				}
				else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV){
					contadorSTD += 1;
					log.debug("Tiene deco STD");
				}/*else if(familia_producto_servicioKey.faps_id.intValue()== ComunInterfaces.familiaPcTV){
					contadorSTD += 1;
					log.debug("Tiene deco STD");
				}*/
			}			
			contadorTotalDecosPedido = contadorHD + contadorPVR + contadorSTD;
			/********************/
			if (contadorTotalDecosPedido == contadorTotalDecosASC ){
				if(contadorSTDASC != contadorSTD || contadorPVRASC != contadorPVR || contadorHDASC != contadorHD ){
					marcarNovedadPs(novedadAuto, peticionLocal);
				}
			}else{				
				if(contadorSTDASC > contadorSTD || contadorPVRASC > contadorPVR || contadorHDASC > contadorHD){
					//Novedad
					marcarNovedadPs(novedadAuto, peticionLocal);
					//marcarNovedadPs(peticionLocal, contadorSTDASC-contadorSTD, contadorPVRASC-contadorPVR
							//, contadorHDASC-contadorHD,psPeticion,listaDecosTarjetasAgendaSC);
				}else{
					marcarNovedadPs(quiebreAuto, peticionLocal);
				}
			}

		}
		catch(NamingException ne)
		{
			log.error("NamingException",ne);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,ne);
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}

	/**
	 * @param peticionLocal
	 * @param contadorSTDASC
	 * @param contadorPVRASC
	 * @param contadorHDASC
	 * @param psPeticion
	 * @param listaDecosTarjetasAgendaSC
	 */
		// TODO Apéndice de método generado automáticamente
	/*private void marcarNovedadPs(PeticionLocal peticionLocal, int contadorSTDASC, int contadorPVRASC, int contadorHDASC, Collection psPeticion, ArrayList listaDecosTarjetasAgendaSC) {
		Fecha fecha=new Fecha();
			
		if(contadorSTDASC > 0)
			actualizarEstado( psPeticion, listaDecosTarjetasAgendaSC, desHCDecoSTD,familiaDecoTV, fecha, contadorSTDASC);
		
		if(contadorPVRASC > 0)
			actualizarEstado( psPeticion, listaDecosTarjetasAgendaSC, desHCDecoPVR,familiaDecoPVRTV, fecha, contadorPVRASC);
		
		
		if(contadorHDASC > 0)
			actualizarEstado( psPeticion, listaDecosTarjetasAgendaSC, desHCDecoHDTV,familiaDecoHDTV, fecha, contadorHDASC);
	}*/

	/**
	 * @param psPeticion
	 * @param listaDecosTarjetasAgendaSC
	 * @param fecha
	 * @param familiaDecoHDTV
	 * @param desHCDecoSTD
	 */
	/*private void actualizarEstado(Collection psPeticion, ArrayList listaDecosTarjetasAgendaSC, String tipoDeco, int familiaDeco, Fecha fecha, int cantidad) {
		// TODO Apéndice de método generado automáticamente
		int contador= 0;
		log.debug("Se envia novedad para tipo deco: "+tipoDeco);
		try {
			UsuarioLocalHome usuarioHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
				
			ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
			ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
			Long idAct=actividadSessionLocal.getIdActividad(VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONTROLI"),new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
			
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal causa = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.CAUSA_CLIENTE);
			
			for(Iterator lista = listaDecosTarjetasAgendaSC.iterator();lista.hasNext();){
				TR711SMaterials deco = (TR711SMaterials)lista.next();
				log.debug("Cantidad de decos reportado en el cierre: "+cantidad+" contadoe: "+contador);
				if(deco.getTypeEquipment().indexOf(tipoDeco) != -1&& contador < cantidad){
					for(Iterator reportados = psPeticion.iterator(); reportados.hasNext();){
						
						Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)reportados.next();
						if(producto_servicio_peticionLocal != null && producto_servicio_peticionLocal.getFamiliaKey().faps_id.intValue()== familiaDeco){

							try {
								Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey( new Long (causa.getValor()) );
								Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
								long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
								Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
								
								estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
								estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
								estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
								estado_ps_peticionLocal.setCod_actividad(idAct);
								
								Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
								Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
								UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
								long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
								Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
								causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
								causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
								causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
								causal_peticionLocal.setCod_actividad(idAct);
								contador++;
								log.debug("Se registra novedad: "+contador);
								break;
							} catch (FinderException e) {
								// TODO Bloque catch generado automáticamente
								log.debug("error en el proceso de ingresar causales "+e);
							} catch (CreateException e) {
								// TODO Bloque catch generado automáticamente
								log.debug("error en el proceso de ingresar causales "+e);
							}
												
						}
					}
						
				}
			}
			if(contador==0){
				Producto_servicioLocalHome psh = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioLocal psl = null;
				if(familiaDeco == familiaDecoHDTV)
					psl = psh.findByPS_Nombre(HD);
				if(familiaDeco == familiaDecoPVRTV)
					psl = psh.findByPS_Nombre(PVR);
				if(familiaDeco == familiaDecoTV)
					psl = psh.findByPS_Nombre(STD);
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = null;
				Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey( new Long (causa.getValor()) );
				Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
				long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
				
				for(Iterator reportados = psPeticion.iterator(); reportados.hasNext();){
					producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)reportados.next();
					if(producto_servicio_peticionLocal != null && 
							(producto_servicio_peticionLocal.getFamiliaKey().faps_id.intValue()== familiaDecoHDTV
							|| producto_servicio_peticionLocal.getFamiliaKey().faps_id.intValue()== familiaDecoPVRTV
							||producto_servicio_peticionLocal.getFamiliaKey().faps_id.intValue()== familiaDecoTV))
						break;
						
				}
				Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
				estado_ps_peticionLocal.setProducto_servicio_peticion(producto_servicio_peticionLocal);
				estado_ps_peticionLocal.setProducto_servicio(psl);
				estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
				estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
				estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				estado_ps_peticionLocal.setCod_actividad(idAct);
				
				Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
				Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
				UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
				long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
				Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
				causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
				causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
				causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				causal_peticionLocal.setCod_actividad(idAct);
				contador++;
				log.debug("Se registra novedad: "+contador);
			}
		} catch (NamingException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} 
		
	}*/

	/**
	 * 
	 * @param codCausal
	 * @param peticionLocal
	 * @throws NamingException
	 * @throws CreateException
	 * @throws FinderException
	 */
	private void marcarNovedadPs(String codCausal, PeticionLocal peticionLocal) throws NamingException, CreateException, FinderException {
		Fecha fecha=new Fecha();
			
		UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
		Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
		ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
		ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
		Long idAct=actividadSessionLocal.getIdActividad(VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONTROLI"),new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
/*				if(idAct==null)
			return;*/	
		boolean genero = false;		
		log.debug("Entro al for para generar la novedad");					
		for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext() && !genero;)
		{
			Producto_servicio_peticionLocal ppss=(Producto_servicio_peticionLocal) iterator.next();
			//----if(ppss.getFamiliaKey().faps_id.intValue()!=ComunInterfaces.familiaPcTV)
			//----	continue;
			// -- Se cambiaron las condiciones para generar las novedades CR- 12278
			
			/*if(genero)return;*/
			log.debug("Valor de la familia "+ppss.getFamiliaKey().faps_id.intValue());
			if(ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoHDTV||ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoTV ||ppss.getFamiliaKey().faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV
					|| ppss.getPsId().intValue()== ComunInterfaces.familiaPcTV ){
				genero = true;					
				Producto_servicioLocal producto_servicioLocal=ppss.getProducto_servicio();
				Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
				Operacion_comercialLocal operacion_comercialLocal=ppss.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				
				
				
				Collection listaEstadoPsPet=ppss.getEstado_ps_peticion();
				Long codigoCausal = new Long(codCausal);
				
				if(listaEstadoPsPet.size()>0)
				{	
					
					Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey( codigoCausal );
					Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
					
					//Tengo una causa asociada tengo que actualizar
					Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPsPet.iterator().next();
					estado_ps_peticionLocal.setCod_actividad(idAct);
					estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
					estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
					estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					
					Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
					Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
					long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
					
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
				else
				{
					
					Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey( codigoCausal );
					Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
					
					long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
					Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),ppss.getProducto_servicio(),ppss);
					estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
					estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
					estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					estado_ps_peticionLocal.setCod_actividad(idAct);
					
					
					Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
					Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
					long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
			}
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#enviaConfiguracionMovistarPlay(long, co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void enviaConfiguracionMovistarPlay(long petiNumero, ActividadEJBDTO act)throws ATiempoAppEx{
		
			log.debug("Ingreso a la Actividad enviaConfiguracionMovistarPlay");
			
			try {
				Long numeroPeticion = new Long(petiNumero);
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal peticion = peticionHome.findByPrimaryKey(new PeticionKey(numeroPeticion));
				Peticion_flujoLocalHome peticion_flujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				Long codFatherEmail=new Long (VpistbbaConfig.getVariable("CORREOMOVISTARGO"));	
				boolean traePS = false;
				
				TR605E tr605e = new TR605E();
				Iterator psp =  peticion.getProducto_servicio_peticion().iterator();
				Long ps_actual = null;
				Long ps_anterior = null;
				String TipoOperacion= null;
				String caracteristica = null;
				int contPS = 0;
				
				while(psp.hasNext()){
					Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal)psp.next();
					Familia_producto_servicioKey familia_producto_servicioKey =  pspLocal.getFamiliaKey();
					Operacion_comercialLocal op=pspLocal.getOperacion_comercial();
					Operacion_comercialKey opKey = (Operacion_comercialKey) op.getPrimaryKey();
					if (familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaPS_GVP ){
						Collection lista=peticion_flujoHome.findByIdActividadFPetPsOC(act.getIdActividadFlujo(),numeroPeticion,pspLocal.getPsId(),opKey.opco_id);
						if(lista.size() > 0){
							if(!op.getOpco_tipo().equals("BCP") || act.getPsOk().size() == 1){
								ps_actual = pspLocal.getPsId();
							}else{
								ps_anterior = pspLocal.getPsId();
							}
							contPS++;
							TipoOperacion = op.getOpco_tipo();
							caracteristica = obtenerCaracteristicaPeticion(pspLocal,codFatherEmail);
							traePS = true;
						}
					}else{
						if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaPC_GVP &&
								(op.getOpco_tipo().equals("BCP") || op.getOpco_tipo().equals("ACP"))){
							if(!op.getOpco_tipo().equals("BCP") || act.getPsOk().size() == 1){
								ps_actual = pspLocal.getPsId();
							}else{
								ps_anterior = pspLocal.getPsId();
							}
							contPS++;
							TipoOperacion = op.getOpco_tipo();
							caracteristica = obtenerCaracteristicaPeticion(pspLocal,codFatherEmail);
							traePS = true;
						}
					}
				}
				if(!TipoOperacion.equals("A") && caracteristica == null)
					caracteristica = "";
				tr605e.setPs(ps_actual);
				tr605e.setPsAnterior(ps_anterior);
				
				Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
				tr605e.setId(idCorrelativoMensaje.toString());
				Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
				Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
				Mensaje_estado_tvLocal msgEstadoTvLocal = msgEstadoTvLocalHome.create(idCorrelativoMensaje);
				Mensaje_estadoKey key = new Mensaje_estadoKey (new Integer (ComunInterfaces.estadoEspera));
	            Mensaje_estadoLocal mensajeEstado = msgEstadoLocalHome.findByPrimaryKey (key) ;
				
				if(TipoOperacion.equals("A")){
					
					Properties_BDLocalHome correlativo = (Properties_BDLocalHome)HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
					Properties_BDLocal correlativoLocal = correlativo.findByPrimaryKey("IDPC_MOVISTAR_GO");
					Long idpcCorrelativo = new Long (correlativoLocal.getValor());
//					Peticion_atisLocal petAtisLocal = peticion.getFk_01_pet_atis();
//					Peticion_atisKey petAtisKey = (Peticion_atisKey) petAtisLocal.getPrimaryKey();
					
					if(idpcCorrelativo.equals("99999999999")){
						BandejasDeSoporte bs = new BandejasDeSoporte();
						bs.derivarPGITV(act, "Se ha llegado al rango final para la numeración de Movistar Play",new Long(939),
								msgEstadoTvLocal, peticion );
					}else{
						peticion.setNum_ide_nu_mp(idpcCorrelativo.toString());
						idpcCorrelativo = new Long(idpcCorrelativo.longValue()+ 1);
						correlativoLocal.setValor(idpcCorrelativo.toString());
					}

//					tr605e.setOc(new Long(1));
				}
				if(TipoOperacion.equals("A"))
					tr605e.setOc(new Long(1));
				if(TipoOperacion.equals("B"))
					tr605e.setOc(new Long(6));
				if(TipoOperacion.equals("BCP") && contPS == 1)
					tr605e.setOc(new Long(6));
				if(TipoOperacion.equals("ACP") && contPS == 1)
					tr605e.setOc(new Long(1));
				if(TipoOperacion.equals("BCP") && contPS > 1)
					tr605e.setOc(new Long(70));
				if(TipoOperacion.equals("ACP") && contPS > 1)
					tr605e.setOc(new Long(69));
				
				tr605e.setPcID(peticion.getNum_ide_nu_mp());
				tr605e.setIdenticationCard(peticion.getNum_doc_cli_cd());
				
	            
	            msgEstadoTvLocal.setPeticion(peticion);
				msgEstadoTvLocal.setFecha_envio(new Fecha().getTimestamp());
				msgEstadoTvLocal.setMensaje_estado(mensajeEstado);
				msgEstadoTvLocal.setCod_actividad_generadora(act.getCodigoActividad());
	            
	            if(caracteristica == null || !traePS){
					String plataforma = null;
					if(traePS)
						plataforma = VpistbbaConfig.getVariable("IDPGI");
					else
						plataforma = VpistbbaConfig.getVariable("IDPGC");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,plataforma); 
    				act.setObservacion("Se deriva a Bandeja de Soporte por no traer el correo del cliente o no traer PS de familia 317",true);
    				insertarCausalesCnaPeticion(msgEstadoTvLocal, msgEstadoTvLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), act.getIdActividadFlujo());

    				act.setRealizarTerminoInmediato(true);
    				return;
				}
				tr605e.setEmail(caracteristica);
	            
				
				new SolicitudConfiguracionMovistarPlayMQ().enviarMensaje(tr605e);
								
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Se presenta un error en la actividad de Configurar_Movistar_Play: "+e);
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Se presenta un error en la actividad de Configurar_Movistar_Play: "+e);
			} catch (CreateException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Se presenta un error en la actividad de Configurar_Movistar_Play: "+e);
			} catch (NumberFormatException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			
			
		}
	
	public String obtenerCaracteristicaPeticion(Producto_servicio_peticionLocal producto_servicio_peticionLocal, Long caracteristica){
		String valor = null;

		if (producto_servicio_peticionLocal != null){
			Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
			for(Iterator iterator2=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();iterator2.hasNext();)
			{
				Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterator2.next();
				Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
				if (spk.cod_crc_cd.longValue()== caracteristica.longValue())
				{
					log.debug("Informacion : Se obtuvo La Caracteristica " + caracteristica.longValue() + " : " +subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
					valor = subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
					break;
				}
			}
		}
		return valor;
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#respuestaConfigMovistarPlay(co.com.telefonica.atiempo.interfaces.atiempo.TR605S)
	 */
	public void respuestaConfigMovistarPlay(TR605S tr605s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		log.debug("Ingreso a la Actividad respuestaConfigMovistarPlay");
		
		try {
			Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
			Mensaje_estado_tvLocal msgEstadoTvLocal;
			Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
	        Mensaje_estadoLocal mesajeOkLocal = msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
	        Mensaje_estadoLocal mensajeErrorLocal = msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
	        Mensaje_estadoLocal mensajeManualLocal = msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEsperaManual)));
	        ErrorLegadoLocalHome errorHome = (ErrorLegadoLocalHome) HomeFactory.getHome (ErrorLegadoLocalHome.JNDI_NAME) ;
	        ErrorLegadoLocal errorlocal = null;
	        
	        msgEstadoTvLocal = msgEstadoTvLocalHome.findByPrimaryKey(new Mensaje_estado_tvKey(new Long(tr605s.getId())));
	        PeticionLocal peticion = msgEstadoTvLocal.getPeticion();
	        PeticionKey petKey = (PeticionKey) peticion.getPrimaryKey();
            ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
            IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(msgEstadoTvLocal.getCod_actividad_generadora());
    		ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(petKey.peti_numero, msgEstadoTvLocal.getCod_actividad_generadora());
    		String plataforma = VpistbbaConfig.getVariable("IDPGI");
	        
    		if(tr605s.isError() || tr605s.getCodeError().intValue() != 0){
    			try{
            		errorlocal = errorHome.findByErrorTr(tr605s.getCodeError().toString(),"TR605S");
        		} catch (javax.ejb.FinderException e) {
        			log.debug("no se encontrò ninguna bandeja para derivar");
        		}
            		
    				
    				if(errorlocal != null){
    					plataforma = errorlocal.getPlataforma();
    					insertarCausalesCnaPeticion(msgEstadoTvLocal, msgEstadoTvLocal.getCod_actividad_generadora(), errorlocal.getIdCausa(), actDto.getIdActividadFlujo());
    				}else{
    					insertarCausalesCnaPeticion(msgEstadoTvLocal, msgEstadoTvLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
    				}
            		
    				log.debug("Tengo un error en el mensaje. Se deriva a: "+plataforma);
    				msgEstadoTvLocal.setMensaje_estado(mensajeErrorLocal);
    				msgEstadoTvLocal.setFecha_cierre(new Fecha().getTimestamp());
    				
    				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,plataforma); 
    				actDto.setObservacion("Se deriva solicitud a Error con el còdigo:" + tr605s.getMessageError(),true);
     
                    actividadEJB.terminar(actDto);
                    
                    return;
    		}else{
    			log.debug("Se genera Proceso Configuraciòn Movistar Play de forma exitosa");
				msgEstadoTvLocal.setMensaje_estado(mesajeOkLocal);
				msgEstadoTvLocal.setFecha_cierre(new Fecha().getTimestamp());
				actDto.setObservacion("Se genera Proceso Configuraciòn Movistar Play de forma exitosa",true);
                actividadEJB.terminar(actDto);

                return;
    		}
	        
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta un error en la actividad de Configurar_Movistar_Play: "+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta un error en la actividad de Configurar_Movistar_Play: "+e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta un error en la actividad de Configurar_Movistar_Play: "+e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta un error en la actividad de Configurar_Movistar_Play: "+e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta un error en la actividad de Configurar_Movistar_Play: "+e);
		}
	}
}