/*
 * Created on 08-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.actividades;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocal;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocalHome;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.helpers.ParametrosIncorrectosException;
import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocal;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocalHome;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorException;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public abstract class ActividadEJB implements SessionBean, IActividadEJB {

	protected javax.ejb.SessionContext mySessionCtx;
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	protected abstract void onInicio(ActividadEJBDTO actEjb) throws TnProcesoExcepcion;
	protected abstract void onTermino(ActividadEJBDTO actEjb) throws TnProcesoExcepcion;
	protected abstract void inicializaEJB(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void postInicializaEJB(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onCambioUsuario(ActividadEJBDTO actEjb,Long idUser) throws TnProcesoExcepcion;
	protected abstract DatosPeticion generaDatosPublicacion(ActividadEJBDTO act);
	
	private ActividadDTO getInfActividadBD(ActividadEJBDTO actEjb) throws TnProcesoExcepcion{
		ActividadDTO act = null;
		try{
			log.debug ("ejecuto getInfActividadBD:" + actEjb.getNumeroPeticion() );
			ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
			ActividadSessionLocal actLocal = actHome.create();
			act = actLocal.recuperaActividad(actEjb.getCodigoActividad(), actEjb.getIdAplicacion());
			log.debug ("fin getInfActividadBD:" + actEjb.getNumeroPeticion() );
		}
		catch (Exception e){
			log.debug("Error al buscar actividad en BD:" + actEjb.getCodigoActividad(),e);
			log.debug("ActividadEJB.getInfActividadBD:" + actEjb.getCodigoActividad(),e);
			log.error("ActividadEJB.getInfActividadBD:" + actEjb.getCodigoActividad(),e);
			
			throw new TnProcesoExcepcion("Error al buscar actividad en BD:" + actEjb.getCodigoActividad() + ".  " + e.getMessage());
		}
		return act;
	}

	public final ActividadEJBDTO getActividadEJBDTO (Long numeroPeticion,String codigoActividad) throws TnProcesoExcepcion{
		return this.getActividadEJBDTO(numeroPeticion,codigoActividad,null,null);
	}

	public final ActividadEJBDTO getActividadEJBDTO (Long numeroPeticion,String codigoActividad, String actImplCorreID, String entradaXML) throws TnProcesoExcepcion{
		ActividadEJBDTO activ = new ActividadEJBDTO();
		log.debug("Inicio Seteo Informacion Actividad EJB:" + codigoActividad + " Peticion:" + numeroPeticion);
		activ.setNumeroPeticion(numeroPeticion);
		activ.setCodigoActividad(codigoActividad);
		activ.setActImplCorrelID(actImplCorreID);
				
		this.inicializaEJB(activ); //Aqui se setea el id aplicacion correpondiente, por lo tanto se puede buscar la actividad por aplicacion
		//Se setean las variables del WF
		log.debug("Se setean las variables del WF:" + codigoActividad + " Peticion:" + numeroPeticion);
		//TODO Inicio AT-1308
		if (entradaXML!=null && !entradaXML.equals("") && !entradaXML.equals("X")){
		//TODO Fin AT-1308
			try {
				new ParamsExtracterEJB(entradaXML, activ).setMapDatos();
			} catch (Exception e) {
				throw new TnProcesoExcepcion("Error al extraer las variables del WF",e);
			}
		}
				
		if(activ.isVacia()){
			activ.setRealizarTerminoInmediato(true);
			activ.setGrabarWfInstancia(false);
		}else{
			log.debug ("Cargo actividad:" + activ.getCodigoActividad() + " Peticion:" + activ.getNumeroPeticion() );
			//Se cargan los datos de la actividad desde la BD y se verifican los PS ok y no ok			
			activ.setActividadBD(this.getInfActividadBD(activ));
		
			boolean tieneActNoAvance = false;
			String actividadesNoAvance [] = VpistbbaConfig.getVariable("COD_ACTIVIDAD_NO_AVANCE").split(",");
			for (int i=0; i<actividadesNoAvance.length;i++){
				if (activ.getCodigoActividad().equals(actividadesNoAvance[i])){
					tieneActNoAvance = true;
					break;
				}
			}
			
			if (!tieneActNoAvance){
				//Si es nulo, se va a buscar a la tabla Wfinstancia actividad el ID y el mapa de datos
//				TODO Inicio AT-1308
				if (activ.getActImplCorrelID()==null || activ.getActImplCorrelID().equals("") || activ.getActImplCorrelID().equals("X")){
	            //TODO Fin AT-1308
//					Busco el mapa de datos de la BD
					log.debug ("Cargo mapa de variables de la actividad:" + activ.getNumeroPeticion() );
					WFSessionLocal wfSession = getWfSession();
					wfSession.setMapaDatosActividad(activ);	
				}
			}
			
			log.debug ("Ejecutando postInicializaEJB actividad:" + activ.getNumeroPeticion());
			//En caso de necesitar hacer algo posterior a toda esta configuracion
			log.debug("cod activ ActEJB = " + activ.getIdActividadFlujo());
			this.postInicializaEJB(activ);
			log.debug ("Fin postInicializaEJB actividad:" + activ.getNumeroPeticion());				
		}

		log.debug("Fin seteo Informacion Actividad EJB:" + numeroPeticion);
		
		return activ;
	}
	public final void iniciar(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Se realiza toda la lógica de la actividad y puede publicarse si corresponde
		log.debug("iniciar*****");
		if(!act.isVacia()){
			log.debug("OnInicio**");
			this.onInicio(act); 
		}else{
			log.debug("Terminar**");
			this.terminar(act);
		}
	}
	
	public final void terminar(ActividadEJBDTO act) throws TnProcesoExcepcion {

//		Se ejectuta la lógica de Termino y se despublica si corresponde
		this.onTermino(act);
	}
	
	public final void grabarSinTerminar(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setNoTerminar(true);
		this.onTermino(act);
	}
		
	public void cambiarUsuario(ActividadEJBDTO act,Long idUser) throws TnProcesoExcepcion{
		this.onCambioUsuario(act,idUser);
	}


	protected final int despublicar(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		int cantidadDespublicados = 0;
		//Despublicamos!!
		String msgOut = "";
		String msgErr = "";
		try {

			DatosPeticion objDatoPeticion = new DatosPeticion(
				act.getNombreAplicacion(), act.getNumeroPeticion(), act.getCodigoActividad());
			objDatoPeticion.setActImplCorrelID(act.getActImplCorrelID());

			//log.info( "Despublicando Peticion [" + this.getNombreAplicacion()+ "," +
			//	 Long.decode(this.idInstanciaProceso) + "," + this.codigoActividad + "]") ;

			msgOut += "\nDespublicando (" + objDatoPeticion.getNumeroPeticion() 
					+ "," + objDatoPeticion.getNombreAplicacion() + "," + objDatoPeticion.getCodigoActividad() + ").\n";
			Date iniDate = new Date();
			PublicadorBandejaLocalHome publicadorHome = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
			PublicadorBandejaLocal publicador = (PublicadorBandejaLocal) publicadorHome.create();
			cantidadDespublicados=publicador.eliminaRegistro(objDatoPeticion);
			msgOut += "DesPublicado OK. [" + ( (new Date()).getTime() - iniDate.getTime() ) + "]";
			return cantidadDespublicados;
			
		}
		catch (NamingException e)
		{
			msgErr = "NamingException: '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("NamingException en despublicar() : " + e.getMessage());
		} catch (CreateException e) {
			msgErr = "CreateException: '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("CreateException en despublicar() : " + e.getMessage());
		} catch (ParametrosIncorrectosException e) {
			msgErr = "ParametrosIncorrectosException : '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("ParametrosIncorrectosException en despublicar() : " + e.getMessage());
		} catch (PublicadorException e) {
			msgErr = "PublicadorException : '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("PublicadorException en despublicar() : " + e.getMessage());
		}
		catch(Exception e)
		{
			throw new TnProcesoExcepcion("Exception en despublicar() : " + e.getMessage());
		}
		
	}
	
	protected final void publicar(ActividadEJBDTO act) throws TnProcesoExcepcion{		
		act.setRealizarTerminoInmediato(false);
		String msgOut = "";
		String msgErr = "";

		DatosPeticion objDatoPeticion = this.generaDatosPublicacion(act);
		objDatoPeticion.setNumeroPeticion( act.getNumeroPeticion() );
//		log.debug("Central:" + objDatoPeticion.getCodigoCentral());
		
		try {
			
			//TODO: VMM Publicar en forma local si todos los proyectos se ejecutan en el mismo server como un unico ear.
			PublicadorBandejaLocalHome publicadorHome = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
			msgOut += "\nPublicando (" + objDatoPeticion.getNumeroPeticion() + ","
			+ objDatoPeticion.getIdResponsable() + "," + objDatoPeticion.getCodigoActividad() + "," + objDatoPeticion.getNombreAplicacion() + ").\n";
			Date iniDate = new Date();
			PublicadorBandejaLocal publicador =  publicadorHome.create();
			publicador.nuevoRegistro(objDatoPeticion);
			msgOut += "Publicado OK. [" + ( (new Date()).getTime() - iniDate.getTime() ) + "]";

		}  catch (NamingException e) {
			msgErr = "NamingException: '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("NamingException en publicar() : " + e.getMessage());
		} catch (CreateException e) {
			msgErr = "CreateException: '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("CreateException en publicar() : " + e.getMessage());
		} catch (ParametrosIncorrectosException e) {
			msgErr = "ParametrosIncorrectosException : '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("ParametrosIncorrectosException en publicar() : " + e.getMessage());
		} catch (PublicadorException e) {
			msgErr = "PublicadorException : '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("PublicadorException en publicar() : " + e.getMessage());
		}
		catch(Exception e)
		{
			log.info("Exception en publicar.",e);
			throw new TnProcesoExcepcion("Exception en publicar." + e.getMessage());
		}

	}
	
	protected final void cambiarUsuarioPublicacion(ActividadEJBDTO act,Long idUser) throws TnProcesoExcepcion{
		//Despublicamos!!
		String msgOut = "";
		String msgErr = "";
		try {

			DatosPeticion objDatoPeticion = new DatosPeticion(
				act.getNombreAplicacion(), act.getNumeroPeticion(), act.getCodigoActividad());
			//Seteo el nuevo responsable
			objDatoPeticion.setIdResponsable(idUser);

			//log.info( "Despublicando Peticion [" + this.getNombreAplicacion()+ "," +
			//	 Long.decode(this.idInstanciaProceso) + "," + this.codigoActividad + "]") ;

			msgOut += "\nCambiando Usuario Publicacion (" + objDatoPeticion.getNumeroPeticion() 
					+ "," + objDatoPeticion.getNombreAplicacion() + "," + objDatoPeticion.getCodigoActividad() + ", " + objDatoPeticion.getIdResponsable() + ").\n";
			Date iniDate = new Date();
			PublicadorBandejaLocalHome publicadorHome = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
//			if(this.idAplicacion.intValue()==new Integer(ApplicationConfig.getVariable("APP_ATST_ID")).intValue())
//				publicadorHome=(PublicadorBandejaHome) HomeFactory.getHome(PublicadorBandejaHome.JNDI_NAME, HomeFactory.REMOTO_BANDEJA_ST);
//			if(this.idAplicacion.intValue()==new Integer(ApplicationConfig.getVariable("APP_VPISTBBA_ID")).intValue())
//				publicadorHome=(PublicadorBandejaHome) HomeFactory.getHome(PublicadorBandejaHome.JNDI_NAME, HomeFactory.REMOTO_BANDEJA_VPI);
			PublicadorBandejaLocal publicador = (PublicadorBandejaLocal) publicadorHome.create();
			publicador.cambiaUsuario(objDatoPeticion);
			msgOut += "Cambiando Usuario Publicacion OK. [" + ( (new Date()).getTime() - iniDate.getTime() ) + "]";
			
		} catch (NamingException e) {
			msgErr = "NamingException: '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("NamingException en cambiarUsuarioPublicacion() : " + e.getMessage());
		} catch (CreateException e) {
			msgErr = "CreateException: '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("CreateException en cambiarUsuarioPublicacion() : " + e.getMessage());
		} catch (ParametrosIncorrectosException e) {
			msgErr = "ParametrosIncorrectosException : '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("ParametrosIncorrectosException en cambiarUsuarioPublicacion() : " + e.getMessage());
		} catch (PublicadorException e) {
			msgErr = "PublicadorException : '" + e.getMessage() + "'";
			log.info(msgOut + "\n" + msgErr,e);
			throw new TnProcesoExcepcion("PublicadorException en cambiarUsuarioPublicacion) : " + e.getMessage());
		}
		catch(Exception e)
		{
			throw new TnProcesoExcepcion("PublicadorException en cambiarUsuarioPublicacion) : " + e.getMessage());
		}
		finally {
			log.info(msgOut + "\n" + msgErr);
		}		
	}
	protected void crearNuevaIntanciaActividad(ActividadEJBDTO actividad)
		throws TnProcesoExcepcion {

		WFSessionLocal wfSession = getWfSession();
		wfSession.crearNuevaIntanciaActividadEJB(actividad);

	}
	
	protected void enviaRespuesta(ActividadEJBDTO actividad) throws TnProcesoExcepcion{
		WFSessionLocal wfSession = getWfSession();
		wfSession.enviarRespuesta(actividad);
	}
	
	protected void eliminarInstanciaActividad(ActividadEJBDTO actividad) throws TnProcesoExcepcion{
		WFSessionLocal wfSession = getWfSession();
		wfSession.eliminarInstanciaActividad(actividad);
	}
	
	
	private WFSessionLocal getWfSession() throws TnProcesoExcepcion {
		//TODO Optimizar esto retornando la misma copia del session!!
		WFSessionLocalHome wfSessionLocalHome = null;
		WFSessionLocal wfSession = null;
		try {
			// Busco el WFSessionBean
			wfSessionLocalHome = (WFSessionLocalHome) HomeFactory.getHome(WFSessionLocalHome.JNDI_NAME);
			wfSession = (WFSessionLocal) wfSessionLocalHome.create();
			return wfSession;
		} catch (NamingException e) {
			throw new TnProcesoExcepcion("NamingException en Actividad.getWfSession() : " + e.getMessage());
		} catch (CreateException e) {
			throw new TnProcesoExcepcion("CreateException en Actividad.getWfSession() : " + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx)
		throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {

	}
	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

}
