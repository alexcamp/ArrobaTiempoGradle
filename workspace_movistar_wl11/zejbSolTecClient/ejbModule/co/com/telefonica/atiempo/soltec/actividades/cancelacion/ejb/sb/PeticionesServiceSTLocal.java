package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
import java.util.ArrayList;

import co.com.telefonica.atiempo.soltec.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces;
/**
 * Local interface for Enterprise Bean: PeticionesServiceST
 */
public interface PeticionesServiceSTLocal extends PeticionesInterfaces,javax.ejb.EJBLocalObject {
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario);
	public ArrayList buscarPorCliente(String rutCli, String rutDv);

}
