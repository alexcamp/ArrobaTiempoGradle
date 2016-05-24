package com.telefonica_chile.bandeja.bintegrada.session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.ejb.eb.CentralLocal;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.dto.AuxBandejaDTO;
import com.telefonica_chile.bandeja.dto.ReasignaResultDTO;
import com.telefonica_chile.bandeja.supervisor.sessions.SupervisorException;
/**
 * Local interface for Enterprise Bean: BIntegradaSession
 */
public interface BIntegradaSessionLocal extends javax.ejb.EJBLocalObject {
	public ArrayList recuperarAplicaciones() throws BandejaException;

	public boolean nuevaClave(String username, String email)
		throws BandejaException, UsuarioNoEncontradoException;

	public boolean cambiaClaveUsuario(
		Long idUsuario,
		String oldClave,
		String newClave)
		throws
			BandejaException,
			UsuarioNoAutenticadoException,
			UsuarioNoEncontradoException;
	public ArrayList getPeticiones(Vector arregloPeticiones)
		throws BandejaException;
	/**
	 * Este método retorna un ArrayList que son utilizados para despliegue del reclamo. 
	 * @param idLogin
	 * @param idAppST
	 * @return ArrayList
	 * @author rigarce
	 */
	public Vector getPeticionesUsuario(String numId, Long idRol) throws BandejaException;
	
	public ArrayList obtenerBIntegrada(String area, String telefono)
		throws NamingException, FinderException;
	/**
	 * Retorna las peticiones.
	 * 
	 * @param arregloPeticiones
	 * @return
	 * @throws BandejaException
	 */
	
	public ArrayList listaPeticion(
		HashMap filtros,
		int paginaActual,
		int paginacion)
		throws BandejaException;
	
	/**
		 * Obtiene el campo URL para una peticion dada.
		 * 
		 * @param idBintegrada id de la entrada en bintegrada
		 * @return el campo URL de la tabla bintegrada para esa peticion
		 * @throws BandejaException si la peticion no se encuentra u ocurre un error.
		 * @author amartoq
		 */
		public String getURLDetallePeticion(Long idBintegrada)
			throws BandejaException;	
	/**
	 * Cambia el usuario asignado de la peticion especificada, solo si se encuentra una y solo una peticion; si se encuentran 0 o mas de una peticion, se entrega un error.
	 * 
	 * @param idAplicacion aplicacion a la cual pertenece la peticion
	 * @param idPeticion numero de peticion a cambiar
	 * @param idUsuario identificador del nuevo usuario al cual se le asignara la peticion
	 * @throws BandejaException si no se encontraron peticiones; si se encontro mas de una peticion; o si algun otro error ocurre.
	 * @author amartoq
	 */
	public void cambiarUsuariodePeticion(
		Long idAplicacion,
		Long idPeticion,
		Long idUsuario)
		throws BandejaException;
	
	/** Obtener Peticiones de un Usuario **/
	public Tabla buscarListadoPeticiones(HashMap filtros)
		throws BandejaException;
	/**
	 *  Metodo que retorna la lista de todas las actividades pertenecientes a ATST.
	 *  Devuelve null si el rol no es de ATST
	 *  @author iescobar@interplanet.cl 
	 *  @param idRol
	 *  @return
	 */
	public String listaActividadesATST(Long idRol);
    /**
         * Cambia el valor del campo_variable PROPIETARIO al usuario especificado.
         * 
         * @param idAplicacion aplicacion a la cual pertenece la peticion
         * @param idPeticion numero de peticion a cambiar
         * @param idUsuario identificador del nuevo usuario al cual se le asignara la peticion
         * @throws BandejaException si no se encontraron peticiones; si se encontro mas de una peticion; si no se encuentra el campo variable PROPIETARIO; o si algun otro error ocurre.
         * @author amartoq
         */
    
    public ArrayList getPeticionesVisiblesByIdAplicacionIdNroPeticion(Long idAplicacion, Long nroPeticion) throws BandejaException;
	/**Retornamos todas las actividades */
	
    
    //public ArrayList recuperarActividades() throws BandejaException;
	
	public Tabla recuperaUsuariosSupervisadosByPaginacion(
		String loginUsuario,
		Long idRol,
		int pagina,
		int paginacion,
		HashMap datosFiltro)
		throws SupervisorException;
	public Tabla recuperaUsuariosSupervisadosByPaginacion(
		String loginUsuario,
		Long idRol,
		int pagina,
		int paginacion)
		throws SupervisorException;
	public Tabla recuperaUsuariosSupervisados(
		String loginUsuario,
		Long idRol,
		int pagina,
		HashMap datosFiltro)
		throws SupervisorException;
	
	
	/**
	     * Elimina una entrada en la bandeja utilizando el identificador de la entrada.
	     * 
	     * @param id
	     *            identificador de la entrada a eliminar.
	     * @throws BandejaException
	     *             si la entrada no existe u otro error ocurre.
	     * @author amartoq
	     */
//	public ArrayList recuperarLocalidadesCodNombre() throws BandejaException;
//	public ArrayList recuperarLocalidades() throws BandejaException;
	public ArrayList recuperarDepartamento() throws BandejaException;
	public ArrayList recuperaMunicipiosPorDepto(String cod_dpt);
	public ArrayList recuperaLocalidadesPorMunicipio(String cod_mun);
	public ArrayList recuperaSegmentos();
	public Tabla setearDatoTablaControlTecnico(HashMap filtros,int paginaActual,int paginacion) throws BandejaException;
	public ArrayList recuperaRangos();
	public ArrayList recuperaTecnicosTodos();
	public ArrayList recuperaTecnicosDeContratista(Long idContratista);
	public ArrayList recuperaEmpresasTodas();
	public Tabla setearDatoTablaSupCo(HashMap map, int pagina, int intPaginacion) throws BandejaException;
	public ArrayList recuperaUsuariosQueSuperviso(Long idUsuario) throws BandejaException;
	public ReasignaResultDTO reasignaPeticionesUsuario(Long usuaSupervisor,Long usuarioActual,Long usuarioNuevo,ArrayList peticiones) throws BandejaException;
	public boolean esAdministrador(Long idUsuario,Long idPerfil) throws BandejaException;
	public Tabla setearDatoTablaBackOffice(HashMap filtros,int paginaActual,int paginacion,int orden) throws BandejaException;
	public Tabla setearDatoTablaPeticion(HashMap filtros,int paginaActual,int paginacion,int orden) throws BandejaException;
	//TODO: CR11267
	public ArrayList recuperaPrioridadAveria();
	public ArrayList recuperaCategoriaAveria();
	//fin
	//TODO CR-11458
	public ArrayList recuperarGruposEspeciales()throws BandejaException;
	//cr16847 p.cawen
	public ArrayList recuperarCanales()throws BandejaException;
	//FIN
	//CR23110 - PCawen - filatro por regla
	public ArrayList recuperarReglasRet() throws BandejaException;
	
	public Tabla setearDatoTablaBandejaTorre(
		HashMap filtros,
		int paginaActual,
		int paginacion,
		int orden)
		throws BandejaException;
	public Tabla setearDatoTablaEsperaBA(
		HashMap filtros,
		int paginaActual,
		int paginacion,
		int orden)
		throws BandejaException;
	public void cambiarCentralPeticion(
		Long idAplicacion,
		Long idPeticion,
		CentralLocal central)
		throws BandejaException;
	public void setearFechaApertura(
		Long idActividad,
		Long idPeticion,
		Long apId)
		throws NamingException, FinderException;
	public void setearFechaInicio(Long idActividad, Long idPeticion, Long apId)
		throws NamingException, FinderException;
		
	//	CR9664 - adocarmo - inicio	
	/*	
	public AuxBandejaDTO recuperaAuxBandeja(Long nroPeticion)
		throws BandejaException;
	*/		
	public AuxBandejaDTO recuperaAuxBandeja(Long nroPeticion,String aula)
			throws BandejaException;			
//	CR9664 - adocarmo - fin
		
	public Tabla setearDatoTablaEsperaTerra(
		HashMap filtros,
		int paginaActual,
		int paginacion,
		int orden)
		throws BandejaException;
		
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - INICIO	
	public Tabla setearDatoTablaEsperaSigres(
		HashMap filtros,
		int paginaActual,
		int paginacion,
		int orden)
		throws BandejaException;
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - FIN
	
	public Tabla setearDatoTablaEsperaAula365(
				HashMap filtros,
				int paginaActual,
				int paginacion,
				int orden)
				throws BandejaException;

	/**
	 * @return
	 */
	public ArrayList recuperarLocalidadesCodNombre();
}
