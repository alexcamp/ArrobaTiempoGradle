package co.com.telefonica.atiempo.soltec.configuracion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.soltec.configuracion.ejb.ConfiguracionServicesLocal;
import co.com.telefonica.atiempo.soltec.configuracion.ejb.ConfiguracionServicesLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author TCS
 */
public class ConfiguracionServicesDelegate implements ConfiguracionServicesInterface{

	/**
	 * Referencia al componente de negocios en el cual esta implementada
	 * lo logica del servicio
	 */
	private ConfiguracionServicesLocal ejbConfiguracionServices;
	
	/**
	 * Constructor por defecto del proveedor de servicios
	 * Se encarga de buscar el componente adecuado que luego le brindara 
	 * servicios
	 * @throws ATiempoApplicationException en caso de algun error ya sea de 
	 * Naming o de Create del componente.
	 */
	public ConfiguracionServicesDelegate() throws ATiempoAppEx {
		try {
			ejbConfiguracionServices =
				((ConfiguracionServicesLocalHome) HomeFactory.getHome(
			ConfiguracionServicesLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING , nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE , cex);
		}
	}

/*	public ConfiguracionSTBDTO obtenerInformacionConfiguracionSTB() throws ATiempoAppEx {
		return ejbConfiguracionServices.obtenerInformacionConfiguracionSTB();
	}

	public ConfiguracionRIDTO obtenerInformacionConfiguracionRI() throws ATiempoAppEx {
		return ejbConfiguracionServices.obtenerInformacionConfiguracionRI();
	}
	
	public DesconfiguracionSTBDTO obtenerInformacionDesconfiguracionSTB() throws ATiempoAppEx {
		return ejbConfiguracionServices.obtenerInformacionDesconfiguracionSTB();
	}

	public void configurarSTB() throws ATiempoAppEx{	
		ejbConfiguracionServices.configurarSTB();	
	}

	public void desconfigurarSTB() throws ATiempoAppEx{
		ejbConfiguracionServices.desconfigurarSTB();
	}

	public void configurarRI() throws ATiempoAppEx{
		ejbConfiguracionServices.configurarRI();
	}

	public void desconfigurarRI() throws ATiempoAppEx{
		ejbConfiguracionServices.desconfigurarRI();
	}

	public void configurarInternet() throws ATiempoAppEx{
		ejbConfiguracionServices.configurarInternet();
	}

	public void desconfigurarInternet() throws ATiempoAppEx{
		ejbConfiguracionServices.desconfigurarInternet();
	}

	public void obtenerConfiguracionInternet() throws ATiempoAppEx{
		ejbConfiguracionServices.obtenerConfiguracionInternet();
	}

	public void solicitarConfiguracionBA(RecursoHCBADTO dto) throws ATiempoAppEx {
		ejbConfiguracionServices.solicitarConfiguracionBA(dto);
	}

	public void salvarConfiguracionSTB(ConfiguracionSTBDTO dto) throws ATiempoAppEx {
		ejbConfiguracionServices.salvarConfiguracionSTB(dto);
	}

	public void salvarConfiguracionRI(ConfiguracionSTBDTO dto) throws ATiempoAppEx {
		ejbConfiguracionServices.salvarConfiguracionRI(dto);
	}

	public void salvarConfiguracionInternet(ConfiguracionSTBDTO dto) throws ATiempoAppEx {
		ejbConfiguracionServices.salvarConfiguracionInternet(dto);
	}

	public void salvarConfiguracionPS(int nroPeticion, InformacionPSDTO infoPS) throws ATiempoAppEx {
		ejbConfiguracionServices.salvarConfiguracionPS(nroPeticion, infoPS);		
	}*/

}
