package co.com.telefonica.atiempo.soltec.actividades.df.recuperar_inf.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ASTRecuperarInfBA
 */
public class ASTRecuperarInfBABean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Obtener Informacion BA [" + act.getNumeroPeticion() + "]");

//TODO: LOGICA , lca
		
		try {
			RecursosBADelegate delegate=new RecursosBADelegate();
			if(delegate.isIDPC(act.getNumeroPeticion())){
				//TODO Inicio CR 12827
				Peticion_stLocalHome peticionLocalHome =  null;
				Producto_servicioLocalHome prodServLocalHome = null;
				Grpe_PsLocalHome grpeLocalHome = null;
				
				try {
					peticionLocalHome =	(Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
					prodServLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
					grpeLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
				} catch (NamingException e1) {
					throw new TnProcesoExcepcion(" Creacion de Local Home Null");
				}
				
				Peticion_stKey peticion_stkey = new Peticion_stKey(act.getNumeroPeticion());
				Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
				Producto_servicioLocal prod_serv_local = null;
				Long ps_id = peticionLocal.getCod_pro_ser_cd();
				
				Long grupo_id = new Long(ComunInterfaces.psGpon);
				
				boolean gpon = false;
				try{
					Grpe_PsLocal grupo = grpeLocalHome.findGrupoPs(grupo_id,ps_id);
					gpon = true;
				}catch(FinderException e){
					log.warn("No es una Peticion BA GPON-WIMAX");
				}
				
				//Producto_servicioKey productoServicioKey = new Producto_servicioKey(ps_id);
				///prod_serv_local= prodServLocalHome.findByPrimaryKey(productoServicioKey);
				
				// if(prod_serv_local.getGrps_id().longValue() == ComunInterfaces.psGpon){
				//if(grupo != null){
				//	   gpon = true;
				//   }
				
				if(!gpon){
					delegate.enviarConfiguracionActualBA(act.getNumeroPeticion().toString(),act.getCodigoActividad());
				}else{
					act.setRealizarTerminoInmediato(true);
					act.setObservacion("Es una Peticion de BA GPON-WIMAX, por lo tanto no se consulta HC");
				}
			}else{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC");
			}
			// TODO Fin CR 12827
			//	RecursosBADelegate delegate=new RecursosBADelegate();
			//	delegate.enviarConfiguracionActualBA(act.getNumeroPeticion().toString(),act.getCodigoActividad());
		} catch (ATiempoAppEx e) {
			log.warn("Error en Actividad Informacion de BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion BA", e);
		} catch (FinderException e) {
			
			log.warn("Error en la obtencion de la Peticion",e);
		}
		

		log.debug("Fin Actividad Obtener Informacion BA [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
}
