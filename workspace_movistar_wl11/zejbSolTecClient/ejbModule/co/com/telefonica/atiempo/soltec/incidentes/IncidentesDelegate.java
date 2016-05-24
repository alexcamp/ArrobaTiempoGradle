/*
 * Created on Feb 21, 2007
 */
package co.com.telefonica.atiempo.soltec.incidentes;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.interfaces.atiempo.TR005S;
import co.com.telefonica.atiempo.soltec.dto.CierrePeticionDTO;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.servicios.IncidentesServicesLocal;
import co.com.telefonica.atiempo.soltec.servicios.IncidentesServicesLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author TCS
 */
public class IncidentesDelegate implements IncidentesInterfaces {

	private IncidentesServicesLocal servicios;

	public IncidentesDelegate() throws ATiempoAppEx {
		try {
			servicios =
				((IncidentesServicesLocalHome) HomeFactory.getHome(IncidentesServicesLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}

	/**
	 * @see co.com.telefonica.atiempo.soltec.peticiones.business.PeticionesServicesBusinessInterface#obtenerPeticion(java.lang.String)
	 */
//	public TR005S obtenerIncidenteATIS(Long numeroPeticionAtis) throws ATiempoAppEx {
//		return servicios.obtenerIncidenteATIS(numeroPeticionAtis);
//	}

	/**
	 * @see co.com.telefonica.atiempo.soltec.peticiones.business.PeticionesServicesBusinessInterface#salvarPeticion(com.telefonica_chile.vpistbba.peticion.Peticion)
	 */
	public Peticion_stLocal salvarIncidenteATIS(TR005S peticionAtis, boolean masiva) throws ATiempoAppEx {
		return servicios.salvarIncidenteATIS(peticionAtis,masiva);
	}

	/**
	 * @see co.com.telefonica.atiempo.soltec.peticiones.business.PeticionesServicesBusinessInterface#existePeticionATIS(java.lang.String)
	 */
	public boolean existeIncidenteATIS(Long numeroIncidenteAtis) throws ATiempoAppEx {
		return servicios.existeIncidenteATIS(numeroIncidenteAtis);
	}

    // fbd
    public void cerrarIncidente (Long numeroIncidente) throws ATiempoAppEx {
    	servicios.cerrarIncidente(numeroIncidente) ;
	}

	public void cancelarIncidente (Long numeroIncidente) throws ATiempoAppEx {
		servicios.cancelarIncidente(numeroIncidente) ;
	}
	public int reiterarIncidente (TR005S incidenteAtis, boolean masiva) throws ATiempoAppEx {
		return servicios.reiterarIncidente(incidenteAtis,masiva) ;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces#agregarSolucion(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	public CierrePeticionDTO agregarSolucion(Long numeroPeticion, String codigoActividad, Long idUser,String codCierre, String fecha) throws ATiempoAppEx {
		return servicios.agregarSolucion(numeroPeticion,codigoActividad,idUser,codCierre,fecha);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces#historicoSoluciones(java.lang.Long)
	 */
	public Collection historicoSoluciones(Long numeroPeticion) throws ATiempoAppEx {
		return servicios.historicoSoluciones(numeroPeticion);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces#terminarIncidente(java.lang.Long)
	 */
	public Long terminarIncidente(TR005S incidenteAtis, boolean masiva) throws ATiempoAppEx {
		return servicios.terminarIncidente(incidenteAtis,masiva);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces#getDatosPeticion(java.lang.Long)
	 */
	public Peticion_stDTO getDatosPeticion(Long codigoAve) {
		return servicios.getDatosPeticion(codigoAve);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces#grabarControlVisita(java.lang.Long, com.telefonica_chile.atiempo.utiles.Fecha, com.telefonica_chile.atiempo.utiles.Fecha)
	 */
	public void grabarControlVisita(Long codAveCd, Fecha fechaHoraVisitaDesde, Fecha fechaHoraVisitaHasta) throws ATiempoAppEx
	{
		servicios.grabarControlVisita(codAveCd,fechaHoraVisitaDesde,fechaHoraVisitaHasta);	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces#ActividadManualMarcaCierreIncidente(java.lang.Long)
	 */
	public void ActividadManualMarcaCierreIncidente(Long numeroIncidente) throws ATiempoAppEx {
		servicios.ActividadManualMarcaCierreIncidente(numeroIncidente);		
	}

//	 CR17031 - adocarmo - inicio
	public boolean modificarCategoria (TR005S incidenteAtis) throws ATiempoAppEx {
		return servicios.modificarCategoria(incidenteAtis) ;
	}
//	 CR17031 - adocarmo - fin
	
	public String recuperarParametroFromPropertiesBD(String codigo) throws ATiempoAppEx{
		  return servicios.recuperarParametroFromPropertiesBD(codigo) ;
	}
}
