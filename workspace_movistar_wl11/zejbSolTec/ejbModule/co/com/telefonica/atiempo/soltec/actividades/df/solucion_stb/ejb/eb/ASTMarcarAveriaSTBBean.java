package co.com.telefonica.atiempo.soltec.actividades.df.solucion_stb.ejb.eb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTMarcarAveriaSTB
 */
public class ASTMarcarAveriaSTBBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Marca Averia STB [" + act.getNumeroPeticion() + "]");
		
		try {
			PeticionesInterfaces pI = new PeticionesDelegate();
		   	boolean esGranite = pI.usaGranite(act.getNumeroPeticion());
		   	RecursosDelegate delegate=new RecursosDelegate();		   	
		   	if(esGranite){
		   		delegate.envioMarcaLineaDefectuosaGR(act.getNumeroPeticion().toString(),act.getCodigoActividad());
		   	}else{			
		   		delegate.envioMarcaLineaDefectuosa(act.getNumeroPeticion().toString(),act.getCodigoActividad());
		   	}
		} catch (ATiempoAppEx e) {
			log.warn("Error al enviar marca averia STB",e);
			throw new TnProcesoExcepcion("Error al enviar actualizacion inventario STB", e);
		}
		
		log.debug("Fin Marca Averia STB [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}
}
