package com.telefonica_chile.comun.asigna.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.HabilidadKey;
import co.com.telefonica.atiempo.ejb.eb.HabilidadLocal;
import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Parametros_PGCLocal;
import co.com.telefonica.atiempo.ejb.eb.Parametros_PGCLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ps_rolKey;
import co.com.telefonica.atiempo.ejb.eb.Ps_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Ps_rolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;
import co.com.telefonica.atiempo.ejb.eb.RolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Rol_habilidadKey;
import co.com.telefonica.atiempo.ejb.eb.Rol_habilidadLocal;
import co.com.telefonica.atiempo.ejb.eb.Rol_habilidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocalHome;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.helpers.UsuarioFactory;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;
import com.telefonica_chile.comun.asigna.dto.UsuarioRolDTO;
import com.telefonica_chile.comun.usuario.dto.UsuarioDTO;
/**
 * Bean implementation class for Enterprise Bean: AsignaSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class AsignaSessionBean implements javax.ejb.SessionBean {

	
	// CR21911 - Optimizacion algoritmo de asignaci�n - guido - 28/Ene/2009 - Inicio
	private static final Logger log = LoggerFactory.getLogger(AsignaSessionBean.class);

	private javax.ejb.SessionContext mySessionCtx;
	/** @deprecated Para algoritmo optimizado no se utiliza mas */
	private HashMap IDContador = new HashMap();
	/** @deprecated Para algoritmo optimizado no se utiliza mas */
	private HashMap rolUsuarioHash = new HashMap();
	/** @deprecated Para algoritmo optimizado no se utiliza mas */
	private HashMap userHabHash = new HashMap();
	
	// Esta variable mantiene por cada rol una posicion del ultimo usuario asignado. 
	// AsignaSessionBean es un session es stateless, por eso es una variable static. 
	private static final HashMap idContadorOpt = new HashMap(50);
	
	private static final HashMap idContadorUsuariosPGC = new HashMap(50);
	// CR21911 - Optimizacion algoritmo de asignaci�n - guido - 28/Ene/2009 - FIN
	//REQ ASISTENCIA REMOTA @DCARDENA 08/05/2015
	private static final HashMap idContUsuaAsistRemotaHab = new HashMap(50);//variable hash donde se alamcenran los usuarios por habildiad
	//REQ TOA FASE III DCARDENA 15/09/2015
	private static final HashMap idContUsuaProcesoBaja = new HashMap(50);//variable hash donde se alamcenran los usuarios por Rol
	
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
	//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
//	Habilidad_usuarioLocalHome habUsrHome = null;
//	Usuario_rolLocalHome urHome = null;
//	UsuarioLocalHome uHome = null;
//	ActividadSessionLocalHome aHome = null;
//	Rol_habilidadLocalHome rHome = null;
	
	public void ejbCreate() throws javax.ejb.CreateException {
		log.debug("AsignaSessionBean created!");
		//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
//		try {
//			habUsrHome = (Habilidad_usuarioLocalHome) HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);
//			urHome = (Usuario_rolLocalHome) HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
//			uHome = (UsuarioLocalHome) Homeactory.getHome(UsuarioLocalHome.JNDI_NAME);
//			aHome = (ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
//			rHome = (Rol_habilidadLocalHome) HomeFactory.getHome(Rol_habilidadLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			log.error("NamingException. Create de AsignacionSession.");
//		}
		
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
		log.debug("AsignaSessionBean activated!!");
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

	/*
	 * Entrega el usuario que puede atender la peticion
	 *
	 * codAct : Codigo de la actividad
	 * hab : HashMap con las Habilidades a considerar.
	 */
	 
	private HashMap getFiltro (HashMap habAct, Long idRol){
		HashMap filtro = new HashMap(); 
		if ( habAct != null && !habAct.isEmpty() ) {
			try {
				//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
				Rol_habilidadLocalHome	rHome = (Rol_habilidadLocalHome) HomeFactory.getHome(Rol_habilidadLocalHome.JNDI_NAME);
				Rol_habilidadLocal rLocal = null;
				HabilidadLocal hLocal = null;
				Collection c = rHome.findHabilidadesPorRol(idRol);
				Iterator it = c.iterator();
				for (; it.hasNext(); ) {
					rLocal = (Rol_habilidadLocal) it.next();
					Rol_habilidadKey rolHKey=(Rol_habilidadKey) rLocal.getPrimaryKey();
					hLocal = rLocal.getF_fk_habi_2_roha();
					HabilidadKey hKey = (HabilidadKey) hLocal.getPrimaryKey();
//					if ( log.isDebugEnabled() )
//						log.debug("Filtro Encontrado:" +
//						"[" + idRol + "," + hKey.habi_id + "," + hLocal.getHabi_nombre() + "]");
					// Si no viene seteado desde el @T, no se utiliza.
					if ( habAct.get(hLocal.getHabi_nombre()) != null )
						filtro.put( hKey.habi_id, habAct.get(hLocal.getHabi_nombre()) );
				}
			} catch (FinderException e) {
				log.error("No se Encontro RolHabilidad: '" + idRol + "'. : " + e, e);
			}
			catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
			}
		}
		return filtro;		
	}
	/**
     * DAVID: se adiciona el par�metro idPetiNum en los siguientes m�todos para RQ 28274, cobro incidencias.
     */
    public Long getIdUsuario(String codAct, HashMap habAct,Long idAplicacion, String idPetiNum) {   
        if (isAlgByEJBEnabled()) {
            return getIdUsuarioByEJB(codAct, habAct, idAplicacion, idPetiNum);
        } else {
            return getIdUsuarioOpt(codAct, habAct, idAplicacion, idPetiNum);
        }
    }
	

	private final Long getIdUsuarioByEJB(String codAct, HashMap habAct,Long idAplicacion, String idPetiNum) {	
		Long idUsuario = null;
		
		String logUser = null;
		Long idRol = null;
		HashMap filtro = new HashMap(); 
		
		boolean desinstalarCRE=false;
		Long idUsuarioCRE=null;

		// Obtenemos El Rol de la Actividad
		try {
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			ActividadSessionLocalHome aHome = (ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
			ActividadSessionLocal aLocal = aHome.create();
			ActividadDTO actDto = aLocal.recuperaActividad( codAct,idAplicacion );
			
			/**
             * DAVID: se adiciona el siguiente c�digo para RQ 28274, cobro incidencias.
             */
            Long petiNum=new Long(idPetiNum);
            System.out.println("El n�mero de petici�n es: "+petiNum);
            boolean esMant=false;
            String nombrePS="";
            try{
                PeticionKey pk=new PeticionKey(petiNum);
                PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
                PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(pk);
                              
                Bitacora_peticionLocalHome bitacora = (Bitacora_peticionLocalHome)HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
                Collection bitacoraLocal= bitacora.findbyNumeroPeticion(petiNum); 
                log.debug("La actividad que llega es: "+codAct);
                if(codAct.equals(ComunInterfaces.ACT_PGC)){
                 	//REQ REAGENDAMIENTO PGC @DCARDENA 08/08/2014
                	//funciuon que retorna el adi de idusuario para asignar a la peticion PGC
                	log.debug("la peticion: "+idPetiNum+" es PGC :"+codAct+ " se validara si es reagendada");
                	idUsuario=asignarUsuarioPGC(bitacoraLocal,habAct,codAct,idPetiNum);
                	if(idUsuario!=null){
                		return idUsuario;
                	}
                }
                
                if(codAct.equals(ComunInterfaces.ACT_ASISTENCIA_REMOTA)){	
                	//REQ ASISTENCIRA REMOTA HABILDIADES 08/05/2015
                	//funciuon que retorna el id de idusuario para asignar a la peticion asistencia remota con habilidad (ip fija - asisencia remota)
                	log.debug("la peticion: "+idPetiNum+" es PGC :"+codAct+ " se validara si es reagendada");
                	idUsuario=asignarUsuarioAsistenciaRemota(bitacoraLocal,habAct,codAct,idPetiNum);
                	if(idUsuario!=null){
                	return idUsuario;
                	}
                }
                /**
                 * DAVID: se adiciona el siguiente c�digo para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
                 */
                log.debug("La localidad es: "+peticionLocal.getFk_01_localidad().getNombre_localidad());
                log.debug("El usuario asociado a la localidad es (Para CRE): "+peticionLocal.getFk_01_localidad().getUsuario());
                log.debug("El c�digo de la actividad es: "+actDto.getIdActividad());
    			if(actDto.getIdActividad().longValue() == ComunInterfaces.actDesInstDTHCRE || actDto.getIdActividad().longValue() == ComunInterfaces.actDesInstCRE){    				
    				if(peticionLocal.getFk_01_localidad().getUsuario()!=null){
    					UsuarioKey usuarioPk=(UsuarioKey)peticionLocal.getFk_01_localidad().getUsuario().getPrimaryKey();
        				idUsuarioCRE=usuarioPk.usua_id;
        				desinstalarCRE=true;
    				}    				
    			}
    			/**
                 * FIN DAVID: se adiciona el siguiente c�digo para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
                 */
                
                
                
                Collection listaPSP=peticionLocal.getProducto_servicio_peticion();
                Iterator PSPIt;
               
                for(PSPIt=listaPSP.iterator();PSPIt.hasNext();){
                    Producto_servicio_peticionLocal psp=(Producto_servicio_peticionLocal)PSPIt.next();
                    System.out.println("El psp es: "+psp.getNom_pro_ser_no());
                    Familia_producto_servicioKey familiaKey=psp.getFamiliaKey();
                    System.out.println("La familia del psp es: "+familiaKey.faps_id);
                    if(familiaKey.faps_id.intValue() == ComunInterfaces.familiaMantenimiento){
                        esMant=true;
                        nombrePS=psp.getNom_pro_ser_no();
                        break;
                    }
                }
            }
            catch(FinderException e){
                log.error("Problema adquiriendo petici�n...");
                
            }
           
            if(esMant){
            	if(nombrePS.indexOf("LB")!=-1){
					idRol=new Long(ComunInterfaces.rolGetorODSLB);;
				}
				else if(nombrePS.indexOf("BA")!=-1){
					idRol=new Long(ComunInterfaces.rolGetorODSBA);
				}
				else if(nombrePS.indexOf("TV")!=-1){
					idRol=new Long(ComunInterfaces.rolGetorODSTV);
				}
            }else{
                idRol = actDto.getIdRol();
            }           
            /**
             * FIN DAVID.
             */
			
		} catch (Exception e) {
			log.error("No se Encontro Actividad: '" + codAct + "'.", e);
			return null;
		}
		
		filtro=this.getFiltro(habAct,idRol);
		
		ArrayList lista = buscarCandidatos(idRol, filtro);
		
		if ( lista==null || lista.size() == 0 ) {
			log.error("No se encontro Usuario asignacion." +
				"[" + habAct.get("NPETICO") + "," + codAct + "," + idRol + "]");
			return new Long (VpistbbaConfig.getVariable("IDDUMMY"));
		}

/*
		for (int i=0; i< lista.size(); i++) {
			log.debug("Usuario Encontrados [" + (lista.get(i)) + "," + lista.size() + "," + 
				"" + habAct.get("NPETICO") + "," + codAct + "," + idRol + "]");
		}
*/
//		log.info("Usuarios Encontrados [" + lista.size() + "," + 
//			"" + habAct.get("NPETICO") + "," + codAct + "," + idRol + "]");


		// Aca hago lo Circular... deberia ser Serializable?
		Integer cont = (Integer)IDContador.get(idRol);
		int aux = 0;
		if (cont != null)
			aux = (cont.intValue() + 1 ) % lista.size();
		IDContador.put(idRol, new Integer(aux));
		
		/**
         * DAVID: se adiciona el siguiente c�digo para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
         */
		if(desinstalarCRE){
			idUsuario=idUsuarioCRE;
		}else{
			idUsuario = (Long) lista.get( aux );
		}
		/**
         * FIN DAVID: se adiciona el siguiente c�digo para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
         */
		//Long idUsuario = new Long("1");//lista.get( 1 );//TODO Borrar esto usuario Hardcodeado
		if (ServiceLocatorEmulator.emuladorActivado()){
		    return new Long("1");
		}
		return idUsuario;
	}

	/*
	 * Entrega un Listado con todos los Usuarios Candidatos.
	 * 
	 * idRol : Rol de la actividad
	 * hab : HashMap con las Habilidades a considerar.
	 */
	/**
	 * @deprecated lento!
	 * */
	public ArrayList buscarCandidatos(Long idRol, HashMap hab) {

//		Inicio @Trace
		TraceMainConfiguration traceConf = null;//TraceMainConfiguration.getInstance();		
		TraceManager traceManager = null;//traceConf.getManager();
		TraceOperation traceOp = null;
		
		
		// Tengo que obtener los Tecnicos que puedan Atender esta Peticion.
		// Busco las Habilidades para cada Usuario y las comparo con el Filtro.
		// Puedo Obtener Supervisores y Usuarios.

		ArrayList listaUsers = new ArrayList();
		ArrayList listaSuper = new ArrayList();
		//log.debug("Buscando Usuario en ROL ['" +  idRol + "]");
		try {
			
			// Busco los usuarios del ROL 
			ArrayList listaUserRol = getListaUsuarioRol( idRol );			
			if ( listaUserRol == null || listaUserRol.size()==0)
				return listaUsers;
//			log.debug("Listado de UsuarioRol [" + idRol + "," + listaUserRol.size() + "]");
			traceConf = TraceMainConfiguration.getInstance();		
			traceManager = traceConf.getManager();
			
			traceOp = traceManager.newOperation(TraceManager.OP_ASIGNA_SESSION);
			traceOp.setColumn(TraceManager.COL_TIPO_OPERACION, TraceAdapter.COL_TIPO_OP_ACCION_WEB);
			traceOp.setColumn(TraceManager.COL_ROL_ACTIVIDAD, idRol);
			traceOp.init(log);		
			traceManager.traceOpStart(traceOp);
			
			traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(listaUserRol.size()));
			
			// Busco el Listado de Usuarios que tengan las Habilidades de Planta Comercial o Pto. Venta.
			// Esto es para optimizar la busqueda de los Candidatos.
			HashMap mapPreUser = null;
			if ( listaUserRol.size() > 20 ) // Si son pocos sale mejor ir uno por uno. �o no?
				mapPreUser = Habilidad(idRol, hab);
			
			HashMap estaSuper = new HashMap();
			//log.debug("Recorriendo 16:03 usuarios para rolId=" + idRol + "  usuarios=" + listaUserRol.size());
			for (int i=0; i<listaUserRol.size();i++ ) {
				UsuarioRolDTO urDto = (UsuarioRolDTO) listaUserRol.get(i);
				boolean valido = false;

				// Obtengo un Usuario, entonces guardo
				Long idUser = urDto.getUsuaId();
				Long idSuper = urDto.getUsuaIdSup();

				// Reviso si esta como Pre-Candidato.
				if ( mapPreUser!=null && !mapPreUser.containsKey(""+idUser) ) {
//					if ( log.isDebugEnabled() )
//						log.debug("Usuario No es Pre-Candidato [" + idRol + "," + idUser + "]");
					continue;
				}

				if ( !estaSuper.containsKey( ""+idSuper ) ) {
					estaSuper.put( ""+idSuper, "1");
					// Valido si el Supervisor tiene las Habilidades.
					valido = validarFiltro( idSuper, hab );
					if (valido){
						//log.debug("Usuario candidato " + (listaSuper.size()+1) + " encontrado SUPERVISOR: idUsr=" + idSuper);
						listaSuper.add( idSuper);
					}
				}
				// Los Supervisores deben estar definidos como habilitados.
				// Pero No se deben considerar como Usuarios Normales.
				// No deben entrar en el POOL de Usuarios para asignar.
				if ( idSuper!=null && idUser.intValue() == idSuper.intValue() )
					continue;

				Long b = urDto.getUsroHabilitado();
				if ( b!=null && b.intValue()==1 ) { // Usuario Habilitado
					valido = validarFiltro( idUser, hab );
					if  ( valido ) {
						// El usuario esta habilitado y tiene las Habilidades.
						//log.debug("Usuario candidato " + (listaUsers.size()+1) + " encontrado COMUN: idUsr=" + idUser);
						listaUsers.add(idUser);
//						if ( log.isDebugEnabled() )
//							log.debug("Usuario Encontrado Habilitado y Skill: '" + idUser + "'");
					}
				}
			}
		} catch (Exception e) {
			log.error( "Exception . [" + idRol + "]", e);
		}finally{
			if(traceManager!=null)
				traceManager.traceOpEnd(traceOp);
		}

		if (listaUsers == null || listaUsers.size()==0) {
			log.info("No se Encontraron Usuarios uso Supervisores. [" + idRol + "," + listaSuper.size() + "]");
			listaUsers = listaSuper;
		}

		return (listaUsers);
	}

	private ArrayList getListaUsuarioRol(Long idRol) {
		refreshCache();
		
//		if ( rolUsuarioHash.containsKey( ""+ idRol) )
//			return (ArrayList) rolUsuarioHash.get( ""+idRol );
		
		try {
			ArrayList urLista = new ArrayList();
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			Usuario_rolLocalHome urHome = (Usuario_rolLocalHome) HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
			Collection c = urHome.findHabilitadosByRol(idRol);
			for ( Iterator it=c.iterator(); it.hasNext(); ) {
				Usuario_rolLocal urLocal = (Usuario_rolLocal) it.next();
				UsuarioRolDTO urDto = new UsuarioRolDTO();
				UsuarioKey usuarioKey=(UsuarioKey) urLocal.getFk_usuarol_usua().getPrimaryKey();
				UsuarioKey usuarioSup=null;
				urDto.setUsuaId( usuarioKey.usua_id );
				if(urLocal.getFk_supe_2_usro()!=null)
				{
					usuarioSup=(UsuarioKey) urLocal.getFk_supe_2_usro().getPrimaryKey();
					urDto.setUsuaIdSup( usuarioSup.usua_id );
				}
				
				
				if ( urLocal.getUsro_habilitado()==null )
					urDto.setUsroHabilitado( new Long(0) );
				else
					urDto.setUsroHabilitado( new Long( urLocal.getUsro_habilitado().intValue() ) );
				urLista.add( urDto );
			}
			rolUsuarioHash.put( ""+idRol, urLista );
			return urLista;
		} catch (FinderException e) {
			log.error("FinderException. Si Usuarios Habilitados Rol [" + idRol + "]");
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
		}
		return null;
	}
	/*
	 * Valida si el usuario cumple con el Filtro dado.
	 */
	/**
	 * @deprecated
	 * */
	private boolean validarFiltro(Long idUsuario, HashMap filtro) {
		if ( filtro.isEmpty() )
			return true;

		try {
//			long time = System.currentTimeMillis();
//			log.debug("Obteniendo habilidades para usuario=" + idUsuario);
			HashMap habUser = getHabilidadesUsuario( idUsuario );
//			long interval = System.currentTimeMillis()-time;
//			log.debug("Habilidades obtenidas para usuario=" + idUsuario + " time="+ interval );

			if ( habUser==null || habUser.isEmpty() )
				return false;

			// Ahora tengo que iterar sobre los Filtros.
			HashMap aux;
			Iterator it = filtro.keySet().iterator();
			for ( ; it.hasNext(); ) {
				Long idKey = (Long) it.next();
				String valor = (String) filtro.get(idKey); 

				aux = (HashMap) habUser.get(idKey);

//				log.debug("Revisando Valor [" + idKey + "," + valor + ",{" + aux + "}]");
				// Vemos si Tiene Habilidades. Si no tiene la Habilidad y no tiene Todas, 
				// entonces no la tiene 
				if ( aux==null || ( !aux.containsKey(valor) && !aux.containsKey("*") ) )
					return false;

				// Si tiene la habilidad habilidad Excluida. entonces false.
				if ( aux.containsKey("-" + valor) )
					return false;

			}

			return true;

		} catch (Exception e) {
			log.error("Exception. Validacion de Filtro.", e);
		}
		
		return false;

	}

	/**
	 * @param idUsuario
	 * @return
	 */
	/**
	 * @deprecated
	 * */
	public HashMap getHabilidadesUsuario(Long idUsuario) {
		refreshCache();
		
//		if ( userHabHash.containsKey(""+idUsuario) )
//			return (HashMap) userHabHash.get( ""+idUsuario );
		
		
		try {
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			Habilidad_usuarioLocalHome habUsrHome = (Habilidad_usuarioLocalHome) HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);
			Collection c = habUsrHome.findByUsuario(idUsuario);
			if ( c==null || c.size()<=0 )
				return null;
				
			// Comparo las Habilidades encontradas.
			HashMap habUser = new HashMap();
			HashMap aux;
			for ( Iterator ite = c.iterator(); ite.hasNext(); ) {
				Habilidad_usuarioLocal h = (Habilidad_usuarioLocal) ite.next();
				String husu_valor = h.getHusu_valor();
				if ( husu_valor==null || "".equals(husu_valor) )
					continue;
				HabilidadKey habilidadKey=(HabilidadKey) h.getFk_habi_2_husu().getPrimaryKey();
				aux = (HashMap) habUser.get( habilidadKey.habi_id );
				if (aux == null)
					aux = new HashMap();
			
				aux.put( husu_valor, "1");
				habUser.put(habilidadKey.habi_id, aux);
//				log.debug("Habilidad Usuario [" + idUsuario + "," + habilidadKey.habi_id + "," + h.getHusu_valor() + "]");
			}
			
			userHabHash.put( ""+idUsuario, habUser );
			return habUser;
		} catch (FinderException e) {
		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
		}
			
		return null;
	}

	/*
	 * Entrega un Listado con los Usuarios que tienen la Habilidad dada.
	 */
	/**
	 * @deprecated
	 * */
	public HashMap Habilidad(Long idRol, HashMap filtro) {
		// Preguntamos si Hay uqe filtrar por PC.
		Long idHabilidad = null;
		String valor = "";
		if ( filtro.containsKey(new Long(1)) ) { // Agencia
			idHabilidad = new Long(1);
		} else if ( filtro.containsKey(new Long(5)) ) { // Planta Comercial
			idHabilidad = new Long(5);
		} else if ( filtro.containsKey(new Long(3)) ) { // Punto Venta.
			idHabilidad = new Long(3);
		}
		
		if ( idHabilidad == null )
			return null;
		
		valor = (String) filtro.get( idHabilidad );
			
		HashMap map= new HashMap();
		
		try {
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			Habilidad_usuarioLocalHome habUsrHome = (Habilidad_usuarioLocalHome) HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);
			// Primero Busco los que Tienen Valor.		
			try {
				Collection c = habUsrHome.findUsuariosPorLaHabilidad(idHabilidad, valor);
				if ( c != null && c.size()>0 )
					for (Iterator ite = c.iterator(); ite.hasNext(); ) {
						Habilidad_usuarioLocal h = (Habilidad_usuarioLocal) ite.next();
						UsuarioLocal fk_usua_2_husa = h.getFk_usua_2_husa();
						if ( h==null || fk_usua_2_husa==null)
							continue;
						UsuarioKey usuarioKey=(UsuarioKey) fk_usua_2_husa.getPrimaryKey();
						if(fk_usua_2_husa!=null) if( map.containsKey(""+usuarioKey.usua_id))
							continue;
						map.put( ""+usuarioKey.usua_id, "1");
					}
			} catch (FinderException e) {
				log.debug("FinderException. Buscando Habilidad de Usuario [" + idHabilidad + "," + valor + "]");
			}
			// Ahora busco los que tienen '*'.
			try {
				Collection c = habUsrHome.findUsuariosPorLaHabilidad(idHabilidad, "*");
				if ( c != null && c.size()>0 )
					for (Iterator ite = c.iterator(); ite.hasNext(); ) {
						Habilidad_usuarioLocal h = (Habilidad_usuarioLocal) ite.next();
						UsuarioLocal fk_usua_2_husa = h.getFk_usua_2_husa();
						if ( h==null || fk_usua_2_husa==null)
							continue;
						UsuarioKey usuarioKey=(UsuarioKey) fk_usua_2_husa.getPrimaryKey();
						if(fk_usua_2_husa!=null) if(map.containsKey(""+usuarioKey.usua_id))
							continue;
						map.put( ""+usuarioKey.usua_id, "1");
					}
			} catch (FinderException e) {
				log.debug("FinderException. Buscando Habilidad de Usuario [" + idHabilidad + "," + valor + "]");
			}
		}catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls",e);
		}
		
		//		if ( log.isDebugEnabled() )
		//			log.debug("Se encontraron Pre-Candidatos[" + idRol + "," + idHabilidad + "," + 
		//				valor + "," + map.size() + "]");			 
		
		return map;
	}

	Date dateCache = null;
	long maxDiff = 24*3600*1000; // 24 Hrs.

	private void refreshCache() {
		if ( dateCache == null ) {
			dateCache = new Date();
			return;
		}
		long diffMS = new Date().getTime() - dateCache.getTime();
		if ( diffMS < maxDiff )
			return;
		dateCache = new Date();
		rolUsuarioHash = new HashMap();
		userHabHash = new HashMap();
	}
	
	private int esPsAsistProv1o2(Producto_servicio_peticionLocal psp){
		
		boolean esAsistencia1=false;
		
		boolean esAsistencia2=false;
		
		String psProv1 = VpistbbaConfig.getVariable("PS_PROV_1");
		String[] listaPsProv1 = psProv1.split(",");
		
		String psProv2 = VpistbbaConfig.getVariable("PS_PROV_2");
		String[] listaPsProv2 = psProv2.split(",");
		
		for (int contPsProv1 = 0; contPsProv1 <= listaPsProv1.length - 1; contPsProv1++) {
			int psProv1Int = Integer.parseInt(listaPsProv1[contPsProv1]);
			if (psp.getPsId().intValue() == psProv1Int) {
				log.debug("Es un ps de Asistencia Prov 1..."+psProv1Int);
				esAsistencia1 = true;
				break;
			}
		}
		
		for (int contPsProv2 = 0; contPsProv2 <= listaPsProv2.length - 1; contPsProv2++) {
			int psProv2Int = Integer.parseInt(listaPsProv2[contPsProv2]);
			if (psp.getPsId().intValue() == psProv2Int) {
				log.debug("Es un ps de Asistencia Prov 2..."+psProv2Int);
				esAsistencia2 = true;
				break;
			}
		}
		
		if(esAsistencia1){
			return 1;
		}else if (esAsistencia2){
			return 2;
		}else{
			return 0;
		}
		
	}
	
	private Long extraerIdProveedor1o2(String loginUsuario){
		Long idUsuarioProv=null;
		try{
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuarioProv=usuarioHome.findByLogin(loginUsuario);
			UsuarioKey usuarioProvKey=(UsuarioKey) usuarioProv.getPrimaryKey();
			idUsuarioProv=usuarioProvKey.usua_id;	
			}catch(Exception e){
				log.debug("Error al extraer los usuarios del proveedor 1 y 2: "+e.toString());
			}
			return idUsuarioProv;
	}
	/**
	 * Usado por ST
	 * */
	public Collection getUsuariosCompatibles(Long  idUser, String codAct, Long idAplicacion, HashMap habilidades){
		Collection listaUser= new ArrayList();

		try{		
			//Obtengo el Rol
			ActividadLocalHome actividadHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ActividadLocal actividadLocal = actividadHome.findByCodigoActividadIdAplicacion(codAct,idAplicacion);
			RolKey rolKey = (RolKey) actividadLocal.getFk_act_rol().getPrimaryKey();
			//Obtengo filtro Habilidades
			HashMap filtro = this.getFiltro(habilidades,rolKey.rol_id);		
			//Obtengo los user compatibles
			Collection userCompatibles = null;
			// CR21911 - Optimizacion algoritmo de asignaci�n - guido - 28/Ene - Inicio
			long start = System.currentTimeMillis();
			boolean byEjbEnabled = isAlgByEJBEnabled();
			if (byEjbEnabled) {
				userCompatibles = buscarCandidatos(rolKey.rol_id,filtro);
			}  else {
				userCompatibles = buscarCandidatosOpt(rolKey.rol_id,filtro);
			}
			long interval = System.currentTimeMillis() - start;
			log.debug("Usuarios compatibles obtenidos: idUser=" + idUser + " rolId=" + rolKey.rol_id + " habliidades=" +filtro.size()+ " usrsObtenidos=" + userCompatibles.size() + " byEjbEnabled=" + byEjbEnabled + " time=" + interval);
			// CR21911 - Optimizacion algoritmo de asignaci�n - guido - 28/Ene - FIN

			//creo DTO con los user compatibles y los agrego a la lista
			UsuarioFactory usuarioFactory = new UsuarioFactory();
			for (Iterator iterCom=userCompatibles.iterator();iterCom.hasNext();){
				Long idUsuario = (Long) iterCom.next();
				UsuarioLocal usuarioEntity = usuarioFactory.getUsuarioPorId(idUsuario);
				UsuarioDTO userDto = new UsuarioDTO();
				UsuarioKey userK = (UsuarioKey) usuarioEntity.getPrimaryKey();
				//Si es el mismo usuario no lo agrego a la lista
				if(idUser.longValue()==userK.usua_id.longValue()){
					continue;
				}
				userDto.setIdUsuario(userK.usua_id);
				userDto.setLogin(usuarioEntity.getUsua_login());
				listaUser.add(userDto);
			}
		}catch (Exception e){
			log.debug("Error al buscar usuarios Compatibles." ,e);
		}
			return listaUser;
		}

	/**
	 * Estable si el segmento pasado como par�metro se encuentra dentro de los valores
	 * configurados en la tabla ATIEMPO.PROPERTIES_BD en el valor SEGMENTOS_REINTENTOS_PGC
	 * @param segmentoPeticion Segmento de la petici�n
	 * @return true s� existe el segmento, false en caso contrario
	 * @throws Exception Cualquier error no contemplado en la operaci�n.
	 */
	private boolean existeSegmento(Long segmentoPeticion) throws Exception{
		boolean retorno = false;
		Properties_BDLocalHome property = (Properties_BDLocalHome)HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
		Properties_BDLocal propertyLocal = property.findByPrimaryKey("SEGMENTOS_REINTENTOS_PGC");
		String[] segmentos = propertyLocal.getValor().split(",");
		for (int i = 0; i < segmentos.length; i++) {
			if(segmentoPeticion!=null && segmentos[i].equals(segmentoPeticion.toString())){
				retorno = true;
				break;
			}
		}
		log.debug("existeSegmento()= "+retorno+", Segmento petici�n: "+segmentoPeticion+", SEGMENTOS_REINTENTOS_PGC: "+propertyLocal.getValor());
		return retorno;
	}
	//REQ REAGENDAMIENTO DESDE PGC @DCARDENA 08/08/2014
	//Funcion que asigna un usuario a un peticion que se envia a PGC se retorna el id del usuario 
	private Long asignarUsuarioPGC (Collection bitacoraLocal,HashMap habAct,String codAct,String idPetiNum) throws NamingException{
		//se declara una variable que cuenta los reintentos de la peticion en pgc
		int canPGC=1;
		//se declara la variable que alamacena el segmento y subsegmento de la peticion
    	Long segmento = Long.valueOf("-1");
    	Long subSegmento = Long.valueOf("-1");
    	//se crea la variable para el idUsuaio
    	Long idUsuario = null;
    	//recorremos el la collection de la tabla bitacora dodne sabremos las veces q ha pasado la peticion por PGC
        for(Iterator iter = bitacoraLocal.iterator();iter.hasNext();){
  
	        Bitacora_peticionLocal bitLocal = (Bitacora_peticionLocal)iter.next();
	        //se obtiene el segmento y subsegmento de la peticion
	        segmento = bitLocal.getFk_peticion().getCod_sgm_cta_cd();
	        subSegmento=bitLocal.getFk_peticion().getCod_sbg_cta_cd();
	        log.debug("Actividades a las que ha llegado la peticion:"+idPetiNum+" es "+bitLocal.getIdActividad());
	        // se contaviliza las veces que ha pasado por PGC
	        if(bitLocal.getIdActividad().equals(new Long(ComunInterfaces.idActividadPGC)))
	        	canPGC++;                     		
         }  
        
        log.debug("Numero de llegadas a PGC: "+canPGC+" el segmento es: "+segmento+" el subsegmento es: "+subSegmento);
         // se inicializa el localHome
        Parametros_PGCLocalHome parametros_PGCLocalHome =(Parametros_PGCLocalHome)HomeFactory.getHome(Parametros_PGCLocalHome.JNDI_NAME);
        // try que maneja el finder exception
        try{
        	//se consulta por el segmento subsegmento y numeor de reintentos los usuarios para asignar
        	 log.debug("se va aconsultar si hay usuarios para los reintentos "+canPGC+" el segmento : "+segmento+" y el subsegmento : "+subSegmento+ " de la peticion: "+idPetiNum);
        	 Collection arregloUsuarios=parametros_PGCLocalHome.findByReinSegSub(segmento,subSegmento,new Long(canPGC));
        	Iterator listaUsuariosPGC = null;
        	ArrayList list = new ArrayList();
        	//recorremos la collecion de usuarios y creamos una lista con los idUsuario
        	for(listaUsuariosPGC=arregloUsuarios.iterator();listaUsuariosPGC.hasNext();){
        		Parametros_PGCLocal parametros_PGCLocal=(Parametros_PGCLocal)listaUsuariosPGC.next();
        		list.add(parametros_PGCLocal.getUsuario());
        		log.debug("se encontro el usuaio : "+parametros_PGCLocal.getUsuario()+" para la peticion :"+idPetiNum);
        	}
        	//se valida si la lista es nula o no contiene datos se asigna un usuario dummy
	        if ( list==null || list.size()== 0 ) {
			log.error("No se encontro Usuario asignacion." +"[" + habAct.get("NPETICO") + "," + codAct + "," + ComunInterfaces.rolGestorPGC + "]");
			//return new Long (VpistbbaConfig.getVariable("IDDUMMY"));
			return idUsuario=null;
	        }else{
	        	//se obtiene del arreglo la posicion del usuario
	         	Integer cont = (Integer)idContadorUsuariosPGC.get(new Long (canPGC));
	         	//inicializamos el contado de posiciones
	        	int aux = 0;
	        	// se valida si hay algun usuario en memoria
	        	if (cont != null) {
	        		//si no se crea un balanceo
	        		aux = (cont.intValue() + 1 ) % list.size();
	        	}
	        	// se agina al haspmap en memoria la posicion del usiario
	        	idContadorUsuariosPGC.put(new Long(canPGC), new Integer(aux));
	        	//obtenemos la posicion del nuevo usuario y lo asignamos a la variable
	        	String idUs=(String) list.get(aux); 
	        	//asignamos el valor del nuevo usuario a asignar 
	        	idUsuario=new Long(idUs);  
	        	//retornamos la variable
	        	log.debug("El usuario asignado para gestionar la peticion en PGC es:"+idUsuario+" Para la peticion: "+idPetiNum);
	        	return idUsuario;
	        }   
	    	}catch(FinderException e){
	    		log.debug("No se encontraron usuarios para los reintentos: "+canPGC+" segmento : "+segmento+" y el subsegmento : "+subSegmento+ " de la peticion: "+idPetiNum);
	    	}
	    	
				return idUsuario;
	}
	
	//REQ ASISTENCIA REMOTA @DCARDENA 08/05/2015
	//Funcion que asigna un usuario a un peticion de ipfija o una peticion de Posventa BA
	private Long asignarUsuarioAsistenciaRemota (Collection bitacoraLocal,HashMap habAct,String codAct,String idPetiNum) throws NamingException{
    	//se crea la variable para el idUsuaio
    	Long idUsuario = null;
    	String accion="";
    	Long habilidad= new Long(0);
    	//recorremos el la collection de la tabla bitacora dodne sabremos las veces q ha pasado la peticion por PGC
		try{
			// se llama la funcion esAsistenciaRemota en la cual validamos si hubo cambio de Recursos FISICO o DE IP o no cambio los recursos
			accion=esAsistenciaRemota(new Long(idPetiNum));
			//validamos sobre que hablidad consultamos usuarios 
			if(accion.equals("Asistencia")){
				log.debug("Hubo cambio en las IPs se Buscan usuarios de habilidad IP"+ComunInterfaces.habilidadAsistenciaRemotaCambIP+ " para la peticion "+idPetiNum);
				habilidad=ComunInterfaces.habilidadAsistenciaRemotaCambIP;
			}else if(accion.equals("Terreno")){
				log.debug("No hubo cambio en recrusos De Banda ancha no se consultan usuarios"+idPetiNum);
				return idUsuario;
			}else if(accion.equals("NoCambio")){
				log.debug("Hubo Cambio en velocidades se consulta usuarios con habilidad BA "+ComunInterfaces.habilidadAsistenciaRemota+ " para la peticion "+idPetiNum);
				habilidad=ComunInterfaces.habilidadAsistenciaRemota;
			}
			//se consulta por la habildaid los usuarios que tenga dicha habilidad
			Habilidad_usuarioLocalHome habilidad_usuarioLocalHome =(Habilidad_usuarioLocalHome) HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);		       
        	Collection arregloUsuarios=habilidad_usuarioLocalHome.findUsuariosByHabilidad(habilidad);
        	Iterator listaUsuariosAsistencia = null;
        	ArrayList list = new ArrayList();
        	//recorremos la collecion de usuarios y creamos una lista con los idUsuario
        	for(listaUsuariosAsistencia=arregloUsuarios.iterator();listaUsuariosAsistencia.hasNext();){
        		Habilidad_usuarioLocal habilidad_usuarioLocal=(Habilidad_usuarioLocal)listaUsuariosAsistencia.next();
        		UsuarioKey usuarioKey = (UsuarioKey) habilidad_usuarioLocal.getFk_usua_2_husa().getPrimaryKey();
        		
            	try{
            		Usuario_rolLocalHome usuario_rolLocalHome =(Usuario_rolLocalHome) HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);	
            		Collection usuariosConRol=usuario_rolLocalHome.findPoseeUsuarioRol(usuarioKey.usua_id,ComunInterfaces.rolGestorAsistenciaRemota);
            		list.add(usuarioKey.usua_id);
    				log.debug("se encontro el usuario : "+usuarioKey.usua_id.toString()+" para la peticion :"+idPetiNum);
            	}catch (Exception e) {
            		log.debug("El usuario : "+usuarioKey.usua_id.toString()+" no tiene el ROL Asistencia remota no se le asigna la peticion :"+idPetiNum);
				}        		
        	}
        	//se valida si la lista es nula o no contiene datos se asigna un usuario dummy
	        if ( list==null || list.size()== 0 ) {
			log.error("No se encontro Usuario asignacion." +"[" + habAct.get("NPETICO") + "," + codAct + "]");
			//return new Long (VpistbbaConfig.getVariable("IDDUMMY"));
			return idUsuario=null;
	        }else{
	        	//se obtiene del arreglo la posicion del usuario
	         	Integer cont = (Integer)idContUsuaAsistRemotaHab.get(habilidad);
	         	//inicializamos el contado de posiciones
	        	int aux = 0;
	        	// se valida si hay algun usuario en memoria
	        	if (cont != null) {
	        		//si no se crea un balanceo
	        		aux = (cont.intValue() + 1 ) % list.size();
	        	}
	        	// se agina al haspmap en memoria la posicion del usiario
	        	idContUsuaAsistRemotaHab.put(habilidad, new Integer(aux));
	        	//obtenemos la posicion del nuevo usuario y lo asignamos a la variable
	        	String idUs=list.get(aux).toString(); 
	        	//asignamos el valor del nuevo usuario a asignar 
	        	idUsuario=new Long(idUs);  
	        	//retornamos la variable
	        	log.debug("El usuario asignado para gestionar la peticion en la bandeja Asistencia Remota es:"+idUsuario+" Para la peticion: "+idPetiNum);
	        	return idUsuario;
	        }   
	    	}catch(FinderException e){
	    		log.debug("No se encontraron usuarios Con hablidades para Asistencia Remota o Cambio IP para la peticion: "+idPetiNum);
	    	}
	    	
				return idUsuario;
	}

	//	 funcion que realiza validacion de los cambio y cambio de recursos
	// esta misma funcion se encuentra en AsignasesionBean de comun ya que no se puede comunicar comun con VPISTBBA
	 public String esAsistenciaRemota (Long numeroPeticion)
	 {
	 	// se declara una variable detipo boolean la cual tendra el valor final al retornar
	 	String esAsistencia="";
	 	try{
	 	//se instancias los localHome que se van a usar
		Recursos_baLocalHome recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
		Recursos_baLocal RBA = recursos_baLocalHome.findbyPeti_numero(numeroPeticion);
		//se valida si cambio los recursos fisicos 
		//en este if se valida si los campos son idferentes de null si ambos son diferentes de null entoces se valida
		// contienen informacion diferente
		if(((RBA.getDir_ip_dslam_actual()!=null && RBA.getDir_ip_dslam_nueva()==null
				||RBA.getDir_ip_dslam_actual()==null && RBA.getDir_ip_dslam_nueva()!=null)
			||(RBA.getDir_ip_dslam_actual()!=null && RBA.getDir_ip_dslam_nueva() !=null
				&& !RBA.getDir_ip_dslam_actual().equals(RBA.getDir_ip_dslam_nueva())))
			
			||((RBA.getFrame_actual()!=null && RBA.getFrame_nuevo()==null 
					||RBA.getFrame_actual()==null && RBA.getFrame_nuevo()!=null)
			||(RBA.getFrame_actual()!=null && RBA.getFrame_nuevo()!=null
					&&!RBA.getFrame_actual().equals(RBA.getFrame_nuevo())))
			
			||((RBA.getSlot_actual()!=null && RBA.getSlot_nuevo()==null
					||RBA.getSlot_actual()==null && RBA.getSlot_nuevo()!=null)
			||(RBA.getSlot_actual()!=null && RBA.getSlot_nuevo()!=null
					&&!RBA.getSlot_actual().equals(RBA.getSlot_nuevo())))
			
			||((RBA.getPuerto_actual()!=null && RBA.getPuerto_nuevo()==null
					||RBA.getPuerto_actual()==null && RBA.getPuerto_nuevo()!=null)
			||(RBA.getPuerto_actual()!=null && RBA.getPuerto_nuevo()!=null
					&& !RBA.getPuerto_actual().equals(RBA.getPuerto_nuevo())))){
			// no se necesita que valla asistencia remota yaque hay que ir a terreno obligatoriamente
			esAsistencia="Terreno";
			//se valida que hayan cambiado los recursos de IPS
		}else if(((RBA.getDir_ip_lan_actual()!=null && RBA.getDir_ip_lan_nueva()==null 
					||RBA.getDir_ip_lan_actual()==null && RBA.getDir_ip_lan_nueva()!=null)
				||(RBA.getDir_ip_lan_actual()!=null && RBA.getDir_ip_lan_nueva()!=null
					&& !RBA.getDir_ip_lan_actual().equals(RBA.getDir_ip_lan_nueva())))
				||((RBA.getDir_ip_wan_actual()!=null&& RBA.getDir_ip_wan_nueva()==null
				    ||RBA.getDir_ip_wan_actual()==null&& RBA.getDir_ip_wan_nueva()!=null)
				||(RBA.getDir_ip_wan_actual()!=null&& RBA.getDir_ip_wan_nueva()!=null
					&&!RBA.getDir_ip_wan_actual().equals(RBA.getDir_ip_wan_nueva())))
				||((RBA.getMasc_actual()!=null && RBA.getMasc_lan_nueva()==null
						||RBA.getMasc_actual()==null && RBA.getMasc_lan_nueva()!=null)
				||(RBA.getMasc_actual()!=null && RBA.getMasc_lan_nueva()!=null
					&&!RBA.getMasc_actual().equals(RBA.getMasc_lan_nueva())))){
			//como no cambio recursos fisicos pero si hubo cambio de IPS se debe ir a asistencia remota
			esAsistencia="Asistencia";
		
		}else{
			//como no hubo cambio de recursos fisicos ni de IPs no se necesita que se ejecute la asistencia Remota
			esAsistencia="NoCambio";
		}
	} catch (Exception e) {
		log.warn("Error en Actividad Asistencia Remota",e);
	}
	//se retorna la variable con el resultado de la validacion
		return esAsistencia;
	}	 
	 
	 //FIN REQ ASISTENCIA REMOTA HABILIDADES
	
	// Optimizacion asignacion usuario - guido - 22-Ene-2009 - INICIO
	private Long getIdUsuarioOpt(String codAct, HashMap habAct,Long idAplicacion, String idPetiNum) {	
		Long idUsuario = null;
		
		String logUser = null;
		Long idRol = null;
		HashMap filtro = new HashMap(); 
		
		boolean desinstalarCRE=false;
		Long idUsuarioCRE=null;
        boolean esAsistencia1=false;    		
		boolean esAsistencia2=false;
		long rolVentaEquipos = 0;

		// Obtenemos El Rol de la Actividad
		try {
			
			Bitacora_peticionLocalHome bitacora = (Bitacora_peticionLocalHome)HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
            Collection bitacoraLocal= bitacora.findbyNumeroPeticion(new Long(idPetiNum)); 
            log.debug("La actividad que llega es: "+codAct);
            if(codAct.equals(ComunInterfaces.ACT_PGC)){	
            	//REQ REAGENDAMIENTO PGC @DCARDENA 08/08/2014
            	//funciuon que retorna el adi de idusuario para asignar a la peticion PGC
            	log.debug("la peticion: "+idPetiNum+" es PGC :"+codAct+ " se validara si es reagendada");
            	idUsuario=asignarUsuarioPGC(bitacoraLocal,habAct,codAct,idPetiNum);
            	if(idUsuario!=null){
            	return idUsuario;
            	}
            }

            if(codAct.equals(ComunInterfaces.ACT_ASISTENCIA_REMOTA)){	
            	//REQ ASISTENCIRA REMOTA HABILDIADES 08/05/2015
            	//funciuon que retorna el id de idusuario para asignar a la peticion asistencia remota con habilidad (ip fija - asisencia remota)
            	log.debug("la peticion: "+idPetiNum+" es PGC :"+codAct+ " se validara si es reagendada");
            	idUsuario=asignarUsuarioAsistenciaRemota(bitacoraLocal,habAct,codAct,idPetiNum);
            	if(idUsuario!=null){
            	return idUsuario;
            	}
            }
            
			ActividadSessionLocalHome aHome = (ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
			ActividadSessionLocal aLocal = aHome.create();
			ActividadDTO actDto = aLocal.recuperaActividad( codAct,idAplicacion );
			/**
             * DAVID: se adiciona el siguiente c�digo para RQ 28274, cobro incidencias.
             */
            Long petiNum=new Long(idPetiNum);
            System.out.println("El n�mero de petici�n es: "+petiNum);
            boolean esMant=false;
            boolean esIT=false;
            String nombrePS="";
            try{
                PeticionKey pk=new PeticionKey(petiNum);
                PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
                PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(pk);
                
                /**
                 * DAVID: se adiciona el siguiente c�digo para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
                 */
                log.debug("La localidad es: "+peticionLocal.getFk_01_localidad().getNombre_localidad());
                log.debug("El usuario asociado a la localidad es (Para CRE): "+peticionLocal.getFk_01_localidad().getUsuario());
                log.debug("El c�digo de la actividad es: "+actDto.getIdActividad());
    			if(actDto.getIdActividad().longValue() == ComunInterfaces.actDesInstDTHCRE || actDto.getIdActividad().longValue() == ComunInterfaces.actDesInstCRE){    				
    				if(peticionLocal.getFk_01_localidad().getUsuario()!=null){
    					UsuarioKey usuarioPk=(UsuarioKey)peticionLocal.getFk_01_localidad().getUsuario().getPrimaryKey();
        				idUsuarioCRE=usuarioPk.usua_id;
        				desinstalarCRE=true;
    				}    				
    			}
    			/**
                 * FIN DAVID: se adiciona el siguiente c�digo para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
                 */
                
                
                
                Collection listaPSP=peticionLocal.getProducto_servicio_peticion();
                Iterator PSPIt;
                
                for(PSPIt=listaPSP.iterator();PSPIt.hasNext();){
                    Producto_servicio_peticionLocal psp=(Producto_servicio_peticionLocal)PSPIt.next();
                    System.out.println("El psp es: "+psp.getNom_pro_ser_no());
                    Familia_producto_servicioKey familiaKey=psp.getFamiliaKey();
                    System.out.println("La familia del psp es: "+familiaKey.faps_id);
                    if(familiaKey.faps_id.intValue() == ComunInterfaces.familiaMantenimiento){
                        esMant=true;
                        nombrePS=psp.getNom_pro_ser_no();
                        break;
                    }else if(codAct.equals(ComunInterfaces.ACT_INSTALAR)||codAct.equals(ComunInterfaces.ACT_CTRL_INSTALACION)){ 
                		if(familiaKey.faps_id.intValue() == ComunInterfaces.familiaVisitaAsist){
                			if(esPsAsistProv1o2(psp)==1){
                				esAsistencia1=true;
                			}else if(esPsAsistProv1o2(psp)==2){
                				esAsistencia2=true;
                			}                    			
                    	}
                	}else if (codAct.equals(ComunInterfaces.ACT_INSTALAR_IE)||codAct.equals(ComunInterfaces.ACT_CONTROL_INSTALACION_IE)){
                		if (familiaKey.faps_id.intValue() == ComunInterfaces.familiaIntEquipado){
                        	Producto_servicioLocal productoServicioLocal = psp.getProducto_servicio();
                        	nombrePS=productoServicioLocal.getPs_nombre();
                        	Producto_servicioKey pspKey = (Producto_servicioKey)productoServicioLocal.getPrimaryKey();
                        	
                        	try{
	                        	Ps_rolLocalHome psRolLocalHome = (Ps_rolLocalHome)HomeFactory.getHome(Ps_rolLocalHome.JNDI_NAME);
	                        	
	                        	Ps_rolKey psRolKey = new Ps_rolKey(new Long(pspKey.ps_id.longValue()));
	                        	Ps_rolLocal psRolLocal = psRolLocalHome.findByPrimaryKey(psRolKey);
	                        	rolVentaEquipos = psRolLocal.getRol().longValue();
	                        	
	                        	esIT = true;
                        		break;
                        	}catch(FinderException ex){
                        		esIT = false;
                        	}
                		}
                	}else if(codAct.equals(ComunInterfaces.ACT_ASISTENCIA)||codAct.equals(ComunInterfaces.ACT_ASISTENCIA_RMT)){
                		if(familiaKey.faps_id.intValue() == ComunInterfaces.familiaAsistencia || familiaKey.faps_id.intValue() == ComunInterfaces.familiaAsistRemota){
                			
                			if(esPsAsistProv1o2(psp)==1){
                				esAsistencia1=true;
                			}else if(esPsAsistProv1o2(psp)==2){
                				esAsistencia2=true;
                			}                    			
                    	}
                	}
                }
            }
            catch(FinderException e){
                log.error("Problema adquiriendo petici�n...");
            }

           
            if(esMant){
            	if(nombrePS.indexOf("LB")!=-1){
					idRol=new Long(ComunInterfaces.rolGetorODSLB);;
				}
				else if(nombrePS.indexOf("BA")!=-1){
					idRol=new Long(ComunInterfaces.rolGetorODSBA);
				}
				else if(nombrePS.indexOf("TV")!=-1){
					idRol=new Long(ComunInterfaces.rolGetorODSTV);
				}
            }else if (esIT){
            	idRol = new Long(rolVentaEquipos);
            }else if(esAsistencia1||esAsistencia2){
            	Long idRolAsistencia=null;
            	
            	try{
            	RolLocalHome rolLocalHome=(RolLocalHome) HomeFactory.getHome(RolLocalHome.JNDI_NAME);
    			Collection listaRoles=rolLocalHome.findAll();    			
    			Iterator listaRolesIt = null; 
	    			for(listaRolesIt = listaRoles.iterator();listaRolesIt.hasNext(); ){
	    				RolLocal rol=(RolLocal)listaRolesIt.next();
	    				if(rol.getRol_nombre().equals("Asistencia cliente")){
	    					RolKey rolKey=(RolKey)rol.getPrimaryKey();
	    					idRolAsistencia=rolKey.rol_id;
	    					break;
	    				}
	    			}    			
            	}catch(Exception e){
            		log.debug("Error al buscar lista de roles..."+e.toString());
            	}
            	if(idRolAsistencia!=null){
            		idRol = idRolAsistencia;
            	}            	
            }else{
                idRol = actDto.getIdRol();
            }           
            /**
             * FIN DAVID.
             */
		} catch (Exception e) {
			log.error("No se Encontro Actividad: '" + codAct + "'.", e);
			return null;
		}

				
		filtro=this.getFiltro(habAct,idRol);
		
		//ArrayList lista = buscarCandidatos(idRol, filtro);
		ArrayList lista = buscarCandidatosOpt(idRol, filtro);		
		//validarResultados(idRol, lista, usrsOpt);
		if ( lista==null || lista.size() == 0 ) {
			log.error("No se encontro Usuario asignacion." +
				"[" + habAct.get("NPETICO") + "," + codAct + "," + idRol + "]");
			return new Long (VpistbbaConfig.getVariable("IDDUMMY"));
		}

/*
		for (int i=0; i< lista.size(); i++) {
			log.debug("Usuario Encontrados [" + (lista.get(i)) + "," + lista.size() + "," + 
				"" + habAct.get("NPETICO") + "," + codAct + "," + idRol + "]");
		}
*/
//		log.info("Usuarios Encontrados [" + lista.size() + "," + 
//			"" + habAct.get("NPETICO") + "," + codAct + "," + idRol + "]");

		// Aca hago lo Circular... deberia ser Serializable?
		Integer cont = (Integer)idContadorOpt.get(idRol);
		int aux = 0;
		if (cont != null) {
			aux = (cont.intValue() + 1 ) % lista.size();
		}
		idContadorOpt.put(idRol, new Integer(aux));
		
		/**
         * DAVID: se adiciona el siguiente c�digo para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
         */
         if(esAsistencia1){
			idUsuario=extraerIdProveedor1o2("PROV1");
		}else if(esAsistencia2){
			idUsuario=extraerIdProveedor1o2("PROV2");
		}else if(desinstalarCRE){
			idUsuario=idUsuarioCRE;
		}else{
			idUsuario = (Long) lista.get( aux );
		}
		/**
         * FIN DAVID: se adiciona el siguiente c�digo para RQ 33936, mejoras proceso CRE, Marzo 26 2010.
         */
		//Long idUsuario = new Long("1");//lista.get( 1 );//TODO Borrar esto usuario Hardcodeado
		if (ServiceLocatorEmulator.emuladorActivado()){
		    return new Long("1");
		}
		
		log.debug("Se retorna Usuario: "+idUsuario);
		return idUsuario;
	}
	private boolean validarResultados(Long idRol, ArrayList usrsOld, ArrayList usrsOpt) {
		boolean ok = true;
		if (usrsOld.size() != usrsOpt.size()) {
			log.error("INCORRECTO: Usuarios resultado DISTINTOS 18:10 usrsOld=" + usrsOld.size() + " usrsOpt=" + usrsOpt.size() + " idRol=" + idRol);
			ok = false;
		}
		Iterator it = usrsOld.iterator();
		while (it.hasNext()) {
			Long idUsrOld = (Long) it.next();
			int idx= usrsOpt.indexOf(idUsrOld);
			if (idx == -1) {
				ok = false;
				log.error("INCORRECTO: Usuario resultado en OLD idUsrOld="+idUsrOld+" NO encontrado en OPT usrsOld=" + usrsOld.size());
			}
		}
		if (ok) {
			log.info("Validacion de usuarios OLD y OPT OK!!! idRol=" + idRol + " cantidadUsuarios=" + usrsOpt.size());
		}
		return ok;
	}
	
//	 Pablo L -- 26/01/2009 -- CR21911 -- Optimizacion asignacion usuario
	private final void concatSQL(StringBuffer s, String query, String queryExcluidos){
		s.append(query);
		s.append("AND UR2.usua_id NOT IN (");
		s.append(queryExcluidos);
		s.append(")");
        
	}
//	 Pablo L -- 26/01/2009 -- CR21911 -- Optimizacion asignacion usuario
	public boolean cargarListaUsuarios(List parametros,ArrayList result,StringBuffer sb){
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		boolean found = false;
		try{
			conn = DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			pStmt = conn.prepareStatement(sb.toString());
			Iterator it = parametros.iterator();
			int paramIdx = 0;
			while (it.hasNext()) {
				paramIdx++;
				Object paramValue = it.next();
				pStmt.setObject(paramIdx, paramValue);
				log.debug("datos setiados: "+paramIdx+" - " + paramValue);
			}
			rs = pStmt.executeQuery();
			Long usrId = null;
			while (rs.next()) {
				found= true;
				usrId = new Long(rs.getLong(1));
				result.add(usrId);
			}
		}catch(SQLException e){
			StringBuffer params = new StringBuffer();
			for(int i =0; i< parametros.size();i++){
				params.append(parametros.get(i));
				params.append(" | ");
			}
			log.error("Error al ejecutar consulta usuario candidatos SQL=" + sb + " --- parametros enviados: " + params, e);
		}finally {
			closeJDBCResources(conn, pStmt, rs);
		}
		return found;
	}
	
//	 Pablo L -- 26/01/2009 -- CR21911 -- Optimizacion asignacion usuario
	private final ArrayList buscarCandidatosOpt(Long idRol, HashMap hab) {
		//		Inicio @Trace
		boolean found = false;
		TraceMainConfiguration traceConf = null;
		TraceManager traceManager = null;
		TraceOperation traceOp = null;
		ArrayList result = new ArrayList();
		String sql = "";
		String sqlExcluidos = "";
		List parametros = new ArrayList(20);
		StringBuffer sb = new StringBuffer();
		try {
			traceConf = TraceMainConfiguration.getInstance();
			traceManager = traceConf.getManager();
			traceOp = traceManager.newOperation(TraceManager.OP_ASIGNA_USUARIO);
			traceOp.init(log);

			parametros.add(idRol);
			sql = armarSQLCandidatosOpt(hab, parametros, false);

			if (hab.size() > 0) {
				parametros.add(idRol);
				sqlExcluidos = armarSQLCandidatosExcluidosOpt(hab, parametros, false);
				concatSQL(sb, sql, sqlExcluidos);
			} else {
				sb.append(sql);
			}
			traceOp.setColumn(TraceManager.COL_CANT_REGISTROS, String.valueOf(hab.size()));
			traceManager.traceOpStart(traceOp);
			traceOp.setColumn(TraceManager.COL_SQL, sb.toString());

			found = cargarListaUsuarios(parametros, result, sb);
		} finally {
			traceManager.traceOpEnd(traceOp);
		}

		//Busco por el supervisor
		if (!found) {
			TraceMainConfiguration traceConfSup = null;
			TraceManager traceManagerSup = null;
			TraceOperation traceOpSup = null;
			try {
				traceConfSup = TraceMainConfiguration.getInstance();
				traceManagerSup = traceConf.getManager();
				traceOpSup = traceManager.newOperation(TraceManager.OP_ASIGNA_USUARIO_SUP);
				traceOpSup.init(log);
				parametros.clear();
				parametros.add(idRol);
				sql = armarSQLCandidatosOpt(hab, parametros, true);

				sb = new StringBuffer();
				if (hab.size() > 0) {
					parametros.add(idRol);
					sqlExcluidos = armarSQLCandidatosExcluidosOpt(hab,parametros, true);
					concatSQL(sb, sql, sqlExcluidos);
				} else {
					sb.append(sql);
				}
				traceManagerSup.traceOpStart(traceOpSup);
				traceOpSup.setColumn(TraceManager.COL_SQL, sb.toString());

				cargarListaUsuarios(parametros, result, sb);
			} finally {
				traceManagerSup.traceOpEnd(traceOpSup);
			}
		}
		return result;
	}

	private final void closeJDBCResources(Connection conn, PreparedStatement pStmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pStmt != null) pStmt.close();
		} catch (SQLException e1) {
			log.error("Cerrando resultSet o stmt", e1);
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e1) {
			log.error("Cerrando conexion", e1);
		}
	}
/**
	 * @param hab
	 * @param parametros
	 * @return
	 */
//	 Pablo L -- 26/01/2009 -- CR21911 -- Optimizacion asignacion usuario
	private String armarSQLCandidatosOpt(HashMap hab, List parametros, boolean supervisores) {
		Collection c = hab.keySet();
		Iterator it = c.iterator();
		StringBuffer query = new StringBuffer();
		query.append(SQL_USRS_COMUNES);
		String addQuery = " AND UR2.usua_id in   (SELECT  HU.usua_id FROM ATIEMPO.HABILIDAD_USUARIO HU "+
		                  "where HU.usua_id = UR2.usua_id and (HU.HABI_ID = ? and HU.HUSU_VALOR in (?, ?))) ";
		
		String addQuerySup = " AND UR2.usua_id_sup in  (SELECT  HU.usua_id FROM ATIEMPO.HABILIDAD_USUARIO HU "+
        "where HU.usua_id = UR2.usua_id_sup and (HU.HABI_ID = ? and HU.HUSU_VALOR in (?, ?))) ";
		
	
		while (it.hasNext()) {			
			Object idHabilidad = it.next();
			parametros.add(idHabilidad);
			parametros.add("*");
			parametros.add(hab.get(idHabilidad));
			if(supervisores){
				query.append(addQuerySup);
			}else{
				query.append(addQuery);
			}
		}		
		//se descomentarea para ver la consulta de los usuarios sirven 
		//log.debug(query.toString());
		return query.toString();
	}
//Optimizacion asignacion usuario - guido - 22-01-2009 - FIN

	
//	 Pablo L -- 26/01/2009 -- CR21911 -- Optimizacion asignacion usuario
	private String armarSQLCandidatosExcluidosOpt(HashMap hab, List parametros, boolean supervisores) {
		Collection c = hab.keySet();
		Iterator it = c.iterator();
		StringBuffer query = new StringBuffer();
		StringBuffer sb;
		query.append(SQL_USRS_COMUNES_EXLCUIDOS);
		
		String addQuery1 = " UR.usua_id_sup = HU.usua_id  and (HU.HABI_ID = ? and HU.HUSU_VALOR = ? )";
		String addQuery2 = " OR UR.usua_id = HU.usua_id  and (HU.HABI_ID = ? and HU.HUSU_VALOR = ? ) ";
		
		
		String addQuerySup1 = "UR.usua_id_sup = HU.usua_id  and (HU.HABI_ID = ? and HU.HUSU_VALOR = ? )";
		String addQuerySup2 = " OR UR.usua_id_sup = HU.usua_id  and (HU.HABI_ID = ? and HU.HUSU_VALOR = ? ) ";
		
		
		boolean first = true;	
		while (it.hasNext()) {
			if(supervisores){
				if(first){
					query.append(addQuerySup1);
					first = false;
				}else{
					query.append(addQuerySup2);
				}
				
			}else{
				if(first){
					query.append(addQuery1);
					first = false;
				}else{
					query.append(addQuery2);
				}
			}
			Object idHabilidad = it.next();
			parametros.add(idHabilidad);
			
			sb = new StringBuffer();
			sb.append("-");
			sb.append(hab.get(idHabilidad));
			parametros.add(sb.toString());
		}		
		
		return query.toString();
	}
	//Optimizacion asignacion usuario - pablo - 23-01-2009 - FIN	
	private static final String SQL_USRS_COMUNES_EXLCUIDOS = "SELECT UR.USUA_ID " +
	"FROM ATIEMPO.USUARIO_ROL UR, ATIEMPO.HABILIDAD_USUARIO HU " +
	"WHERE  UR.rol_id = ? AND ";
	//Pablo L -- 26/01/2009 -- CR21911 -- Optimizacion asignacion usuario
	private static final String SQL_USRS_COMUNES = "SELECT UR2.USUA_ID " +
	"FROM ATIEMPO.USUARIO_ROL UR2 " +
	"WHERE UR2.rol_id = ? AND UR2.USRO_HABILITADO = 1 ";

	// CR21911 - Optimizacion algoritmo de asignaci�n - guido - 28/Ene
	// Este se utiliza para mantener una puerta abierta mientras se implanta el nuevo algoritmo de asignaci�n optimizado, el cual practicamente solo usa SQL y no EJBs
	private static final boolean isAlgByEJBEnabled() {
		if(algAsignacionByEJB == null) {
			 String param = ApplicationConfig.getVariable("atiempo.asignaUsuarioByEJB");
			 //TODO: Verificar que si param = null,  el new no de NullPointer
			 algAsignacionByEJB = new Boolean(param);
			log.info("Leyendo configuraci�n. Algoritmo utilizado algByEJB=" + algAsignacionByEJB); // Se muestra una sola vez
		}
		return algAsignacionByEJB.booleanValue();
	}
	private static Boolean algAsignacionByEJB = null;


}