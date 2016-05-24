/*
 * Creado el 24/10/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package co.com.telefonica.atiempo.ejb.eb;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class BandejasDeSoporte {

	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	private static SimpleDateFormat df  = new SimpleDateFormat ("dd/MM/yyyy") ;

	/**
	 * @param act
	 * @param mensaje
	 * @param long1
	 * @param mensajeEstadoLineaLocal
	 * @param numeroPeticion
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public void derivarPGILinea(ActividadEJBDTO act, String mensaje, Long causal, Mensaje_estado_lineaLocal mensajeEstadoLineaLocal, Long numeroPeticion) throws NumberFormatException, Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		String plataforma = VpistbbaConfig.getVariable("IDPGI");
		String bandeja = "PGI";
		
		mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
		mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
		mensajeEstadoLineaLocal.setDesc_error(mensaje);
		mensajeEstadoLineaLocal.setPeti_numero(numeroPeticion);

		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
		insertarCausalesCnaPeticionLB(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), causal, act.getIdActividadFlujo());
		act.setObservacion(mensaje);
		act.setRealizarTerminoInmediato(true);
		log.debug("finalizo proceso para derivar a PGI");
	}
	
	/**
	 * @param act
	 * @param mensaje
	 * @param long1
	 * @param mensajeEstadoLineaLocal
	 * @param numeroPeticion
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public void derivarPGIBA(ActividadEJBDTO act, String mensaje, Long causal, Mensaje_estado_baLocal mensajeEstadoBALocal, Long numeroPeticion,PeticionLocal peticion) throws NumberFormatException, Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		String plataforma = VpistbbaConfig.getVariable("IDPGI");
		String bandeja = "PGI";
		
		mensajeEstadoBALocal.setFecha_cierre(df.format (new java.util.Date ()));
		mensajeEstadoBALocal.setCod_actividad_generadora(act.getCodigoActividad());
		mensajeEstadoBALocal.setDesc_error(mensaje);
		mensajeEstadoBALocal.setPeticion(peticion);

		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
		insertarCausalesCnaPeticionBA(peticion, mensajeEstadoBALocal.getCod_actividad_generadora(), causal, act.getIdActividadFlujo());
		act.setObservacion(mensaje);
		act.setRealizarTerminoInmediato(true);
		log.debug("finalizo proceso para derivar a PGI");
	}
	
	public void derivarPGITV(ActividadEJBDTO act, String mensaje, Long causal, Mensaje_estado_tvLocal mensajeEstadoTVLocal,PeticionLocal peticion) throws NumberFormatException, Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		String plataforma = VpistbbaConfig.getVariable("IDPGI");
		String bandeja = "PGI";
		
		mensajeEstadoTVLocal.setFecha_cierre(new Fecha().getTimestamp());
		mensajeEstadoTVLocal.setCod_actividad_generadora(act.getCodigoActividad());
		mensajeEstadoTVLocal.setDesc_error(mensaje);
		mensajeEstadoTVLocal.setPeticion(peticion);

		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
		insertarCausalesCnaPeticionTV(mensajeEstadoTVLocal, mensajeEstadoTVLocal.getCod_actividad_generadora(), causal, act.getIdActividadFlujo());
		act.setObservacion(mensaje);
		act.setRealizarTerminoInmediato(true);
		log.debug("finalizo proceso para derivar a PGI");
	}
	private void insertarCausalesCnaPeticionLB(Mensaje_estado_lineaLocal mensaje_estado_lineaLocal, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws NumberFormatException, Exception 
	{
		PeticionLocal peticionLocal=mensaje_estado_lineaLocal.getPeticion();
		PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
		if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!=ComunInterfaces.estadoPeticionEnCurso)
		{
			log.info("En reversa no se almacenan Quiebres Autom�ticos.PetAtiempo:"+peticionKey.peti_numero);
			return;
		}
		Fecha fecha=new Fecha();
		PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		DBManager manager=new DBManager ();
		manager.setDataSource (DBManager.JDBC_VPISTBBA);
		//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
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
	
	private void insertarCausalesCnaPeticionBA(PeticionLocal peticionLocal, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, CreateException, FinderException 
	{
		UsuarioLocalHome usuarioHome =(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);;
		Catalogo_causalLocalHome catalogo_causalHome =(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		Estado_psLocalHome estado_psHome =(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		Estado_ps_peticionLocalHome estado_ps_peticionHome =(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
		Causal_peticionLocalHome causal_peticionHome =(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
		
//		PeticionLocal peticionLocal=mensajeEstadoBaLocal.getPeticion();
		PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
		if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!=ComunInterfaces.estadoPeticionEnCurso)
		{
			log.info("En reversa no se almacenan Quiebres Autom�ticos.PetAtiempo:"+peticionKey.peti_numero);
			return;
		}
		Fecha fecha=new Fecha();
		PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		DBManager manager=new DBManager ();
		manager.setDataSource (DBManager.JDBC_VPISTBBA);
		
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
	
	private void insertarCausalesCnaPeticionTV(Mensaje_estado_tvLocal mensajeEstadoTv, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, CreateException, FinderException
	{
		PeticionLocal peticionLocal=mensajeEstadoTv.getPeticion();
		PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
		if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!=ComunInterfaces.estadoPeticionEnCurso)
		{
			log.info("En reversa no se almacenan Quiebres Autom�ticos.PetAtiempo:"+peticionKey.peti_numero);
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
}
