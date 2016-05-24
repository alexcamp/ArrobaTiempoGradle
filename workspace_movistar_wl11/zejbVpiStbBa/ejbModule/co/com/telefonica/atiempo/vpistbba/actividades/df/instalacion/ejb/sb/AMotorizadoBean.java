package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
/**
 * Bean implementation class for Enterprise Bean: AMotorizado
 */ 
public class AMotorizadoBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

		try{
			PeticionLocalHome home = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
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
				local.setNum_doc("TRIO LIR"+dbSeq.seqNextValLong ("CORRELATIVO_REFERENCIA_PETICION_TRIO"));
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
		}catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se pudo Terminar Actividad. : " + e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se pudo Terminar Actividad. : " + e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se pudo Terminar Actividad. : " + e);
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {			
		
	}
	
}