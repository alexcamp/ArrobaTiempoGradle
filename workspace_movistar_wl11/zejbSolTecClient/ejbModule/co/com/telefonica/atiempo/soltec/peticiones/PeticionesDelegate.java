/*
 * Created on Feb 21, 2007
 */
package co.com.telefonica.atiempo.soltec.peticiones;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.PeticionesServiceSTLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.PeticionesServiceSTLocalHome;
import co.com.telefonica.atiempo.soltec.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author TCS
 */
public class PeticionesDelegate implements PeticionesInterfaces
{
	
	private PeticionesServiceSTLocal servicios;

	public PeticionesDelegate() throws ATiempoAppEx {
		try {
			servicios =
				((PeticionesServiceSTLocalHome) HomeFactory.getHome(PeticionesServiceSTLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}


	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario)
	{
		return servicios.obtenerInformacionEmpresa(idUsuario);
	}


	public ArrayList buscarPorCliente(String rutCli, String rutDv) {
		// TODO Auto-generated method stub
		return servicios.buscarPorCliente(rutCli,rutDv);
	}


	public ArrayList buscarAv(Long codAve, String rutCli, String rutDv, String idPC)
	{
		return servicios.buscarAv(codAve,rutCli,rutDv,idPC);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces#buscarPorIDPC(java.lang.String)
	 */
	public ArrayList buscarPorIDPC(String idPC) {
		return servicios.buscarPorIDPC(idPC);
	}
	
	public boolean usaGranite(Long nroPeticion) throws ATiempoAppEx{
		return servicios.usaGranite(nroPeticion);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#estaOKProductoServicioPeticion(java.lang.Long, co.com.atiempo.dto.PsVsOcVO)
	 */
	public Regla_RetencionesLocal verificarRetencion(Long peticion)throws ATiempoAppEx {
		return servicios.verificarRetencion(peticion);
	}
	
	public void marcarGranite(Long nroPeticion) throws ATiempoAppEx{
		 servicios.marcarGranite(nroPeticion);
		
	}
	
	public String recuperarParametroFromPropertiesBD(String codigo) {
		return servicios.recuperarParametroFromPropertiesBD(codigo);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces#recuperarListaMensajesConfModemsACS(java.lang.Long)
	 */
	public ArrayList recuperarListaMensajesConfModemsACS(Long petiNumero) {
		return servicios.recuperarListaMensajesConfModemsACS(petiNumero);
		}
}
