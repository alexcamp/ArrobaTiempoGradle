package co.com.telefonica.atiempo.vpistbba.accion_masiva.ejb.sb;

import co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaInterface;

/**
 * Local interface for Enterprise Bean: AccionMasivaServices
 */
public interface AccionMasivaServicesLocal 	extends AccionMasivaInterface,javax.ejb.EJBLocalObject {

	public void procesarArchivoCentralUsuario(
		Long idUser,
		String usuario,
		String archiPre)
		throws Exception;
	public void procesarArchivoODSInUsuario(
		Long idUser,
		String usuario,
		String archiPre)
		throws Exception;
	public void procesarArchivoODSOutUsuario(
		Long idUser,
		String usuario,
		String archiPre)
		throws Exception;
	public void procesarArchivoODSVpiUsuario(
		Long idUser,
		String usuario,
		String archiPre)
		throws Exception;
	public void procesarReporteTerrenoUsuario(
		Long idUser,
		String valores,
		String archiPre)
		throws Exception;
}
