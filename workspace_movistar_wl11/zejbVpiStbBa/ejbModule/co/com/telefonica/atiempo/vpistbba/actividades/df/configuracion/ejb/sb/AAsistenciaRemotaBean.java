package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AAsistenciaRemota
 */
// RQ Cambio plan BA 25956 @dcardena 12/06/2014
//clase con logica principal para la actividad (bandeja nueva) Asistencia remota
public class AAsistenciaRemotaBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB {
	//instanciamos los localHome que se van a usar
	private UsuarioLocalHome usuarioHome;
	private Catalogo_causalLocalHome catalogo_causalHome;
	private Estado_psLocalHome estado_psHome;
	private Estado_ps_peticionLocalHome estado_ps_peticionHome;
	private Causal_peticionLocalHome causal_peticionHome;
	boolean seInhibe;
	String accion="";
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente	
	}
	//on inicio de la logica de la actividad asistencia Remota
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {	
		// TODO Apéndice de método generado automáticamente
		try {
		log.debug("Iniciando Bean con la actividad Asistencia Remota para visualizar la Bandeja");
		// se valida que la peticion venga en reversa para que se inhiba
		if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
			act.setObservacion("Se inhibe actividad, asistencia remota porque viene en reversa");
			act.setRealizarTerminoInmediato(true);
		}else{
//			se llama la funcion esSoloFamiliaBA y se le envia el activadEJBDTO con la cual se valida si es familia Banda ancha la peticion
			if(esSoloFamiliaBA(act))
			{
				// se llama la funcion esAsistenciaRemota en la cual validamos si hubo cambio de Recursos FISICO o DE IP o no cambio los recursos
				accion=esAsistenciaRemota(act.getNumeroPeticion());
				// se vlaida si el valor retornado es asistencia dodne cambia de recursos ip
				if(accion.equals("Asistencia")){
					log.debug("Continua con la actividad de asistencia remota ya que hay cambio de recursos IP LAN/IP WAN/Mascara");
					seInhibe=false;
				//se valida si no cambio osea que no cambio de recursos fisicos ni ip 
				}else if(accion.equals("NoCambio")){
					if(velocidadesIguales(act)){
						
						log.debug("Se inhibe la actividad de asistencia remota ya que las velocidades son iguales");
						act.setObservacion("Se inhibe la actividad de asistencia remota ya que las velocidades son iguales");				
						act.setRealizarTerminoInmediato(true);	
						seInhibe=true;
						
					}else{
						// se valida si hubo cambio de velocidad y si supera el umbral o lo iguala
						if(superaIgualaUmbral(act)){
							log.debug("Continua con la actividad de asistencia remota ya que la velocidad del Alta es mayor o igual al Umbral");
							seInhibe=false;
							
						}else{
						//no hubo cambio de velocidad o la velocidad era menor al umbral
							log.debug("Se inhibe la actividad de asistencia remota ya que la velocidad no igualo o supero al umbral");
							act.setObservacion("Se inhibe la actividad de asistencia remota ya que la velocidad no igualo o supero al umbral");					
							act.setRealizarTerminoInmediato(true);	
							seInhibe=true;
						}
					}
					// se valida si el valor es terre donde nos confirma que cambio de recursos fisico
				}else if(accion.equals("Terreno")){
					// se inhibe actividad ya que hay cambios en los recursos fisicos
					log.debug("Se inhibe la actividad de asistencia remota ya que hubo cambio de recursos Fisicos");
					act.setObservacion("Se inhibe la actividad de asistencia remota ya que hubo cambio de recursos Fisicos");					
					act.setRealizarTerminoInmediato(true);	
					seInhibe=true;
				}
			}else{
				// se inhibe actividad por no corresponder con familia BA
				act.setObservacion("Se inhibe la actividad de asistencia remota ya que hay mas PCs diferentes de Familia Banda ancha en la peticion");					
				act.setRealizarTerminoInmediato(true);	
				seInhibe=true;
			}
		}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error en asistencia remota"+ e);
		}
	}
		
//	req cambio de flujos postventa BA 14/11/2014 @dcardena
	//funcion que valida si la velocidad del PC padre de alta y de baja son iguales
	public boolean velocidadesIguales(ActividadEJBDTO act) throws TnProcesoExcepcion{
		
		//se declara una variable detipo boolean la cual tendra el valor final al retornar
		boolean sonIguales=false;
		Long velocidadALta=new Long (-1);
		Long velocidadBaja=new Long (-1);
		
		log.debug("entro a validar si las velocidades son iguales");
		try {
			// se intancia el localHome
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			//intanciamos la talba producto_servicio
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			
			//guardamos los ps en de la ptecion en un arreglo
			Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
			//se valida si hay PS en la peticion
			if(ps!=null && ps.size()>0){
				// se recorren los PS
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
					//obtenemos la informacion de cada PS en el FOR
					Producto_servicio_peticionLocal element = (Producto_servicio_peticionLocal) iter.next();
					// se crea la Key de consulta en el BEAN de operacion comercial
					Operacion_comercialKey operacion_comercialKey= (Operacion_comercialKey) element.getOperacion_comercial().getPrimaryKey();
					//validamos si la la operacion coemrcia el alta por cambio de producto
					log.debug("Se obtiene el tipo de operacion comercial "+element.getOperacion_comercial().getOpco_tipo()+ 
							"y la familia "+element.getFamiliaKey().faps_id.intValue()+" de la peticion "+act.getNumeroPeticion());
					
					 if(element.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)&&
					        (element.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
					          ||element.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked))
					{
						//se asigna la velocidad del alta de cambio de producto
						velocidadALta=new Long(element.getProducto_servicio().getVelocidad());
						log.debug("Se obtiene la velocidad de alta "+velocidadALta+ " de "+element.getOperacion_comercial().getOpco_tipo()+" de la peticion "+act.getNumeroPeticion());
						
		
					}else if(element.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd)&&
					        (element.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
							          ||element.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked))
					{
//						se asigna la velocidad del baja de cambio de producto
						velocidadBaja=new Long(element.getProducto_servicio().getVelocidad());
						log.debug("Se obtiene la velocidad de baja "+velocidadBaja+ " de "+element.getOperacion_comercial().getOpco_tipo()+" de la peticion "+act.getNumeroPeticion());
						
					}
					
					
				}
				if (velocidadALta.intValue()==velocidadBaja.intValue()){
					sonIguales=true;
					log.debug("Las velocidades de la peticion "+act.getNumeroPeticion()+" son iguales ");
					
				}
			}else{
				log.debug("No hay PS se retorna FALSE");
				//en caso de que no hayan PS se setea FALSE
				sonIguales=false;
			}
		} catch (Exception e) {
			log.warn("Error en Actividad Asistencia Remota",e);
			throw new TnProcesoExcepcion("Error en Actividad Asistencia Remota", e);	
		}
		// se retorna el resultado de la consulta (TRUE / FALSE)
		return sonIguales;
		
	}
	
	//req cambio de flujos postventa BA 14/11/2014 @dcardena
	//funcion que valida si la velocidad del PC padre a instalar en la ACP es igual o mayor al UMBRAL definido
	public boolean superaIgualaUmbral(ActividadEJBDTO act) throws TnProcesoExcepcion{
		
		//se declara una variable detipo boolean la cual tendra el valor final al retornar
		boolean superaIguaAlUmbral=false;
		Long velocidadALta=new Long (0);
		Long umbral=new Long (0);
		log.debug("entra a consultar el umbral configurado en la tabla properties_BD");
		try {
			// se intancia el localHome de la tabla propeties_BD
			Properties_BDLocal properties_BDLocal;
			Properties_BDLocalHome properties_BDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			// consulta el parametro para obtener el valor
			properties_BDLocal =properties_BDLocalHome.findByPrimaryKey("UMBRAL_CAMBIO_PLAN_BA");
			
			
			// se intancia el localHome
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			//intanciamos la talba producto_servicio
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			
			//guardamos los ps en de la ptecion en un arreglo
			Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
			//se valida si hay PS en la peticion
			if(ps!=null && ps.size()>0){
				// se recorren los PS
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
					//obtenemos la informacion de cada PS en el FOR
					Producto_servicio_peticionLocal element = (Producto_servicio_peticionLocal) iter.next();
					// se crea la Key de consulta en el BEAN de operacion comercial
					Operacion_comercialKey operacion_comercialKey= (Operacion_comercialKey) element.getOperacion_comercial().getPrimaryKey();
					//validamos si la la operacion coemrcia el alta por cambio de producto
					log.debug("Se obtiene el tipo de operacion comercial "+element.getOperacion_comercial().getOpco_tipo()+ 
							"y la familia "+element.getFamiliaKey().faps_id.intValue()+" de la peticion "+act.getNumeroPeticion());
					
					if(element.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)&&
					        (element.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
							          ||element.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked))
					{
						//se asigna la velocidad del alta de cambio de producto
						velocidadALta=new Long(element.getProducto_servicio().getVelocidad());
						log.debug("Se obtiene la velocidad "+velocidadALta+ " de "+element.getOperacion_comercial().getOpco_tipo()+" de la peticion "+act.getNumeroPeticion());
						
						// se valida que contenga datos y que no este nulo o sea mayor de 5 digitos
						if(properties_BDLocal.getValor().equals("")||properties_BDLocal.getValor()==null||properties_BDLocal.getValor().length()>5 || Integer.parseInt(properties_BDLocal.getValor())<0){
							log.debug("Se obtiene el umbral "+properties_BDLocal.getValor());
							// se setea 0 en la velocidad umbral
							properties_BDLocal.setValor("0");
							log.debug("Se setea el valor del umbral 0 por que esta nulo o supera el rango de digitos 5");
						}
						// se crea try encaso de que no halla un valor numerico
						try{
							// se setea el valor del umbral y se comvierte en long
							umbral=new Long(properties_BDLocal.getValor());
							log.debug("Se obtiene el umbral "+umbral);
						} catch (NumberFormatException e) {
							// si hay error de  parseo se setea 0 en el umbral
							properties_BDLocal.setValor("0");
							log.debug("Se setea el valor del umbral 0 porque el valor del umbral no es numerico");
						}
						// se valida que si la velocidad de alta sea mayor o igual al umbral
						log.debug("¿La velocidad de alta"+velocidadALta+ " Es mayor al umbral "+umbral+" ?");
						if(velocidadALta.longValue() >= umbral.longValue()){
							// se retorna true 
							superaIguaAlUmbral =true;
							log.debug("LA velocidad es mayor o igual "+superaIguaAlUmbral);
							break;
						}
					}
				}
			}else{
				log.debug("No hay PS se retorna FALSE");
				//en caso de que no hayan PS se setea FALSE
				superaIguaAlUmbral=false;
			}
		} catch (Exception e) {
			log.warn("Error en Actividad Asistencia Remota",e);
			throw new TnProcesoExcepcion("Error en Actividad Asistencia Remota", e);	
		}
		// se retorna el resultado de la consulta (TRUE / FALSE)
		return superaIguaAlUmbral;
		
	}
	
	// funcion esFamiliaBa la cual retorna un True o un false si la familia es unicamente BA
	public boolean esSoloFamiliaBA (ActividadEJBDTO act) throws TnProcesoExcepcion
	{
		// se declara una variable detipo boolean la cual tendra el valor final al retornar
		boolean esSoloFamiliaBA=true;
		try {
			// se intancia el localHome
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
//			intanciamos la talba producto_servicio
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			
			//guardamos los ps en de la ptecion en un arreglo
			Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
			//se valida si hay PS en la peticion
			if(ps!=null && ps.size()>0){
				// se recorren los PS
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
					//obtenemos la informacion de cada PS en el FOR
					Producto_servicio_peticionLocal element = (Producto_servicio_peticionLocal) iter.next();
					// se crea la Key de consulta en el BEAN de operacion comercial
					Operacion_comercialKey operacion_comercialKey= (Operacion_comercialKey) element.getOperacion_comercial().getPrimaryKey();
					//validamos si la familia del ps es 301 o 2 Banda Ancha
					if(element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaBandaAncha &&
							element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaPcBA
							&& element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaPcPsBANaked
							&& element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaBandaAnchaNaked
							&& element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaPaqueteTV
							&& element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaTematicoTV
							&& element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaPS_SVA
							&& element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaPC_GVP
							&& element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaPS_GVP
							&& element.getFamiliaKey().faps_id.intValue() != ComunInterfaces.familiaLinea)
					{
						// se setea TRUE en la variable
						esSoloFamiliaBA=false;
					}
				}
			}else{
				//en caso de que no hayan PS se setea FALSE
				log.debug("No hay PS en la peticion "+act.getNumeroPeticion());
				esSoloFamiliaBA=false;
			}
		} catch (Exception e) {
			log.warn("Error en Actividad Asistencia Remota",e);
			throw new TnProcesoExcepcion("Error en Actividad Asistencia Remota", e);	
		}
		// se retorna el resultado de la consulta (TRUE / FALSE)
		return esSoloFamiliaBA;
		
	}
	

	//	 funcion que realiza validacion de los cambio y cambio de recursos
	// esta misma funcion se encuentra en AsignasesionBean de comun ya que no se puede comunicar comun con VPISTBBA
	 public String esAsistenciaRemota (Long numeroPeticion) throws TnProcesoExcepcion
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
		throw new TnProcesoExcepcion("Error en Actividad Asistencia Remota", e);	
	}
	//se retorna la variable con el resultado de la validacion
		return esAsistencia;
	}	 
	
		//funcion on termino en la cual validamos los tag de workflow para saber si se 
		//deribo a instalar o se continuo flujo desde la bandeja de asistencia remota
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		// se valida que si el tag ACT_OK contiene S se setee en el tag de INST_OK el valos ASIST 
		// el cual se validara en la clase Bean de AinstalarBean.java
		if(act.getDato(DATOS_ATVPISTBBA.act_ok).equals("YES")|| !seInhibe){
			if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_inst).equals("S")&&!seInhibe)
			{
			//debe instalar
					log.debug("debe ir a instalar");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"X");
					try {
						PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
						PeticionLocal petiLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
						Recursos_baLocal recursos_ba = null;
						
						if (petiLocal.getRecursos_ba().size()>0){
							for(Iterator iter = petiLocal.getRecursos_ba().iterator();iter.hasNext(); ) {
								recursos_ba = (Recursos_baLocal) iter.next();
							}
						}
						recursos_ba.setPort_modification_flag("Si");
					} catch (NamingException e) {
						// TODO Bloque catch generado automáticamente
						log.debug("error al instanciar el bean:  "+ e);
					} catch (FinderException e) {
						// TODO Bloque catch generado automáticamente
						log.debug("error al buscando la informaciòn en el bean:  "+ e);
					}
			}else{
					log.debug("se inhibir a instalar");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"ASIST");
			
			}
		}
	}
}