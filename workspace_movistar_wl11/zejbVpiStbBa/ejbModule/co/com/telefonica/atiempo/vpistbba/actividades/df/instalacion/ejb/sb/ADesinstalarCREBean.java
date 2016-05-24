package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: ADesinstalarDTH
 */
public class ADesinstalarCREBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		PeticionLocalHome home = null;
				
		try {
			home = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticion=home.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			PeticionesInterfaces pI= new PeticionesDelegate();
			
			boolean esCRE = pI.getPeti_CRE(act.getNumeroPeticion());

			if(!esCRE){
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe la actividad de DesisntalarCRE ya que la petición contiene un ps que requiere ir a la casa del cliente");
				
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok, "N");
				log.debug("Inhibo DesinstalarCRE ya que la petición para por otra actividad que implica ir a la casa del cliente");				
				return;
			}
			
			//INICIO IDEM A INSTALAR
			String verif_Flujo = act.getDato(DATOS_ATVPISTBBA.RECEPCION.VERIFICACION_FLUJO.vefl_ok);//AT-1665
			Integer IdActividad = act.getIdActividadFlujo();
		    
			Long numPeti = act.getNumeroPeticion();
			LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			PeticionLocal peticionlocal = home.findByPrimaryKey(new PeticionKey(numPeti));
			LocalidadLocal localidadLocal = localidadHome.findByCod_loc(peticionlocal.getCod_ext_loc_cd());
			Ciclos_facturacionLocalHome cicloshome = (Ciclos_facturacionLocalHome) HomeFactory.getHome(Ciclos_facturacionLocalHome.JNDI_NAME);
			DecoModemPeticionLocalHome homeDeco = (DecoModemPeticionLocalHome) HomeFactory.getHome(DecoModemPeticionLocalHome.JNDI_NAME);
			String tipo_cre[] = null;
			String tipo_canal = null;
			if(localidadLocal.getCre()!= null)
				tipo_cre = localidadLocal.getCre().split(",");
			if(tipo_cre[0].equals("1"))
				tipo_canal = "CRE";
			if(tipo_cre[0].equals("2"))
				tipo_canal = "Centro Mensajeria";
			if(tipo_cre[0].equals("3"))
				tipo_canal = "Call center Interrapisimo";
			PuntosCreLocalHome puntoCRE = (PuntosCreLocalHome) HomeFactory.getHome(PuntosCreLocalHome.JNDI_NAME);
			PuntosCreLocal puntoCRELocal = puntoCRE.findbyPk(tipo_canal,localidadLocal.getNombre_provincia(),localidadLocal.getNombre_localidad());
			DBManager dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
			long consecutivo = dbSeq.seqNextValLong ("CORRELATIVO_DECOMODEM_PETICION");
			DecoModemPeticionLocal local = homeDeco.create(new Long(consecutivo));
			local.setPeticion(numPeti.toString());
			local.setId_act(new Long(act.getIdActividadFlujo().toString()));
			local.setHorariospuntaten(puntoCRELocal.getHorario());
			local.setDireccionpuntaten(puntoCRELocal.getDireccion());
			local.setCiclo(new Long(peticionlocal.getInf_cicl_fac()));
			Ciclos_facturacionLocal ciclosLocal = cicloshome.findByPrimary(Integer.parseInt(peticionlocal.getInf_cicl_fac()));
			if(peticionlocal.getPeti_id_instancia().equals("TV"))
				local.setNum_doc("TV LIR"+dbSeq.seqNextValLong ("CORRELATIVO_REFERENCIA_PETICION_TV"));
			if(peticionlocal.getPeti_id_instancia().equals("LBBA"))
				local.setNum_doc("BA LIR"+dbSeq.seqNextValLong ("CORRELATIVO_REFERENCIA_PETICION_BA"));
			if(peticionlocal.getPeti_id_instancia().equals("LBBATV"))
				local.setNum_doc("TV LIR"+dbSeq.seqNextValLong ("CORRELATIVO_REFERENCIA_PETICION_TRIO"));
			Calendar c = Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 String mes = null;
			 String fecha = null;
			try {
				c.setTime(df.parse(peticionlocal.getPeti_fecha_ingreso().toString().substring(0,10)));
			} catch (ParseException e) {
				// TODO Bloque catch generado automáticamente
				log.error("No se pudo Terminar Actividad. : " + e);
			}
			if(c.get(Calendar.MONTH)+1>=12) {
				mes = "1";
			} else
				mes = ""+(peticionlocal.getPeti_fecha_ingreso().getMonth()+2);
			fecha = ciclosLocal.getDia_final()+"-"+mes+"-"+c.get(Calendar.YEAR);
			local.setFechamaxentrega(fecha);
			local.setEstadopet(new Long(1));
			 if((verif_Flujo!=null) && (act.getIdActividadFlujo()!= null )&&(verif_Flujo.equals("S"))&&(IdActividad.intValue()== 9)){
					log.debug("Inicio Actividad Instalar para Cambio de plan [" + act.getNumeroPeticion() + "]");
							RecursosBADelegate recursosBADelegate;
							if(act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok)){
							// logica
								recursosBADelegate = new RecursosBADelegate();
						
								if(!recursosBADelegate.huboCambiosPuertoIp(act.getNumeroPeticion())){
									/*entra por aca si no hubo modificaciones en los ips o puertos */
									log.debug("No hubo cambios de puerto ni ip, ahora se pregunta si existen otros PS que necesiten entrar a la Actividad Instalar.");
									if (!recursosBADelegate.seDebeEntrarAInstalar (act.getNumeroPeticion(),act.getIdActividadFlujo())) {
										log.debug("No existen PS que deban pasar por la Actividad Instalar.");
										/*si entra a este if es por que NO se debe entrar a la Actividad instalar entonces se termina la actividad,
										este es el caso en el que no hubo modificaciones en las ips y puertos , y ademas no hay otros productos que necesiten instalacion*/
										log.debug("Seteamos RealizarTerminoInmediato en true!");
										act.setRealizarTerminoInmediato(true);
										act.setObservacion("Se inhibe la actividad de Instalacion por ser el mismo puerto");
										//act.setNoTerminar
										act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
										log.debug("Se finaliza la Actividad Instalar para Cambio de Plan[" + act.getNumeroPeticion() + "]");
									}	
								}
							}
					
						} 
						log.debug("Fin Actividad Desinstalar CRE para Cambio de Plan[" + act.getNumeroPeticion() + "]");
			 //FIN IDEM A INSTALAR
			 
		} catch (Exception e) {
			log.debug("Error en Actividad Desinstalar CRE " + e.getMessage());
		} 
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {			
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok)) {
			if (act.isRealizarTerminoInmediato() && !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok).equals("S")){
				// para que no instancie Control de Desintalacion CRE
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok,"N");
			}
		}else{
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok,"N");
		}
	}
		
}


