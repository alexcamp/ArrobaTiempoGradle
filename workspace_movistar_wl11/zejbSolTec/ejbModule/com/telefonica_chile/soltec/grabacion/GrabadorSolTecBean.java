package com.telefonica_chile.soltec.grabacion;
/**
 * Bean implementation class for Enterprise Bean: GrabadorSolTec
 */

import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaKey;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.dto.PruebaLineaDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Bean implementation class for Enterprise Bean: GrabadorVIPSTBBA
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class GrabadorSolTecBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

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

	public void grabar(HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String nombreActividad = request.getParameter("codigoActividad");
		String nombreClaseGrabadora = null;
		// Tengo el hash con la asociación de Tarea -> Grabador
		Map grabadorMap = PropertiesStGrabadores.getDatos();
		
		if (!grabadorMap.containsKey(nombreActividad)) {
				nombreClaseGrabadora = GrabadorBaseSolTec.class.getName();
		}
		else{
			nombreClaseGrabadora = (String) grabadorMap.get(nombreActividad);			
		}
	

		/*
		 * Una vez que se ha determinado la clase grabadora, se ejecuta el método grabar(request)
		 * para proceder a procesar los campos ingresados por el usuario. La idea es que cada
		 * grabador está asociado a una (o unas pocas) actividades, pero tiene muy claro cuales son
		 * los campos que vienen de cada pantalla, así que sabe que cosas validar y cuales no.
		 */
		Class claseGrabadora = Class.forName(nombreClaseGrabadora);
		GrabadorSt grabador = (GrabadorSt) claseGrabadora.newInstance();

		if (null != request.getParameter("terminar_act_wf") && "0".equals(request.getParameter("terminar_act_wf")) ) {
			log.debug("Inicio GrabaSinTerminar [" + nombreActividad + "," + nombreClaseGrabadora + "]");
			grabador.grabarSinTerminar(request);
			log.debug("Termino GrabaSinTerminar [" + nombreActividad + "," + nombreClaseGrabadora + "]");
		}else if (null != request.getParameter("terminar_act_wf") && "2".equals(request.getParameter("terminar_act_wf")) ) {
			log.debug("Inicio Derivar [" + nombreActividad + "," + nombreClaseGrabadora + "]");
			grabador.derivar(request);
			log.debug("Termino Derivar [" + nombreActividad + "," + nombreClaseGrabadora + "]");
			
		}else
		{
			log.debug("Inicio Grabando [" + nombreActividad + "," + nombreClaseGrabadora + "]");
			grabador.grabar(request);
			log.debug("Termino Grabando [" + nombreActividad + "," + nombreClaseGrabadora + "]");
		}
	}
	
	public void setGrabaPruebaLinea(PruebaLineaDTO pruebaLineaDTO) throws ATiempoAppEx{
		
		DBManager dbSeq;
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
		
		long correlativo=dbSeq.seqNextValLong ("SOLTEC.PRUEBA_LINEA");
		log.debug("valores que debe llegar para cod prueba["+pruebaLineaDTO.getCodPrueba()+"]");
		log.debug("valores que debe llegar para cod averia["+pruebaLineaDTO.getCodAveCd()+"]");
		log.debug("valores que debe llegar para id familia["+pruebaLineaDTO.getIdFamilia()+"]");
		
		try{

			Prueba_lineaLocalHome prueba_lineaLocalHome;
			prueba_lineaLocalHome =(Prueba_lineaLocalHome) HomeFactory.getHome(Prueba_lineaLocalHome.JNDI_NAME);
			Prueba_lineaLocal prueba_lineaLocal = null;
			
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal peticion_stLocal = null;
			try{
				peticion_stLocal =  peticion_stLocalHome.findByPrimaryKey(new Peticion_stKey(pruebaLineaDTO.getCodAveCd()));
				
				log.debug("Codigo de localidad reuperado -> "+peticion_stLocal.getCod_loc());
			}catch(Exception e){
				log.error("Falla al cargar peticion_stLocal:",e);
			}
			
//			Familia_producto_servicio_stKey key=new Familia_producto_servicio_stKey(pruebaLineaDTO.getIdFamilia());
			Catalago_prueba_lineaKey cKey=new Catalago_prueba_lineaKey();
			cKey.cod_prueba=new Integer(pruebaLineaDTO.getCodPrueba());
			cKey.familia_producto_servicio_st_faps_id=pruebaLineaDTO.getIdFamilia();
			Catalago_prueba_lineaLocalHome cHome=(Catalago_prueba_lineaLocalHome) HomeFactory.getHome(Catalago_prueba_lineaLocalHome.JNDI_NAME);
			Catalago_prueba_lineaLocal cLocal=cHome.findByPrimaryKey(cKey);
			log.debug("Se crea prueba linea:");
			log.debug("Correlativo["+correlativo+"]");
			log.debug("Id de usuario["+pruebaLineaDTO.getIdUsuario()+"]");
			prueba_lineaLocal = prueba_lineaLocalHome.create(new Long(correlativo),peticion_stLocal,cLocal,pruebaLineaDTO.getIdUsuario());
			
			// German P. - Correccion de error en largo del atributo
			String obs = pruebaLineaDTO.getObservacion();
			if (obs.length() > 250){
				obs = obs.substring(0,250);
			}
			prueba_lineaLocal.setObservacion(obs);
			prueba_lineaLocal.setFecha(pruebaLineaDTO.getFecha());
			
		
		}
		catch(NamingException e)
		{
			log.error("NamingException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		} catch (CreateException e) {
			log.error("CreateException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (FinderException e) {
			log.error(e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		}
	}

}
