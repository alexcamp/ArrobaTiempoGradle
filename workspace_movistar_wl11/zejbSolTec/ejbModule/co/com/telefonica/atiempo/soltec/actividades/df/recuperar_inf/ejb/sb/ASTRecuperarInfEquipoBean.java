package co.com.telefonica.atiempo.soltec.actividades.df.recuperar_inf.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.servicios.EquipoSTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;


import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: ASTRecuperarInfEquipo
 */
public class ASTRecuperarInfEquipoBean extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
     */
    protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
       
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
     */
    protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
      try {
      	
        	log.debug("ejecutando ASTRecuperarInfEquipoBean: onInicio --> nro de Incidencia = "+act.getNumeroPeticion());
        	Peticion_stLocalHome peticion_stLocalHome =  (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stKey peticion_stKey = new Peticion_stKey(act.getNumeroPeticion());
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey); 
			
			log.debug("Validacion de campo num_id_nu");
			if(peticion_stLocal.getNum_ide_nu().equals("")||peticion_stLocal.getNum_ide_nu()== null){
				log.debug("Se inhibe actividad por que el campo num_ide_nu esta vacio");
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC");
			
			}else
			{
				log.debug("El campo num_ide_nu contiene datos continua la actividad");
				EquipoSTDelegate eDelegate = new EquipoSTDelegate();
		        eDelegate.enviarConfiguracionActualEquipo(act.getNumeroPeticion().toString(),act.getCodigoActividad());
			}
        	
        	
          
    	} catch (NamingException e) {
		// TODO Auto-generated catch block
  		log.warn("Error en Actividad Recuperar Informacion Equipo",e);
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Auto-generated catch block
		log.warn("Error en Actividad Recuperar Informacion Equipo",e);
			e.printStackTrace();
    } catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
		log.warn("Error en Actividad Recuperar Informacion Equipo",e);
		e.printStackTrace();
		}
        
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
     */
    protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
        
        
    }
   
}
