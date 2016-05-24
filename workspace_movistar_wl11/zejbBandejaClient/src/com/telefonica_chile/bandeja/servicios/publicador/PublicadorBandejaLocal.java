package com.telefonica_chile.bandeja.servicios.publicador;
import com.telefonica_chile.bandeja.helpers.ParametrosIncorrectosException;
/**
 * Local interface for Enterprise Bean: PublicadorBandeja
 */
public interface PublicadorBandejaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * nuevoRegistro
	 */
	public int nuevoRegistro(
		com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion dp)
		throws
			com.telefonica_chile.bandeja.helpers.ParametrosIncorrectosException,
			com
				.telefonica_chile
				.bandeja
				.servicios
				.publicador
				.PublicadorException,
			com.telefonica_chile.bandeja.helpers.RegistroDuplicadoException;
	/**
	 * actualizaRegistro
	 */
	public boolean actualizaRegistro(
		com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion dp)
		throws
			com.telefonica_chile.bandeja.helpers.ParametrosIncorrectosException,
			com
				.telefonica_chile
				.bandeja
				.helpers
				.CriterioBusquedaAmbiguoException,
			com
				.telefonica_chile
				.bandeja
				.servicios
				.publicador
				.PublicadorException;
	/**
	 * eliminaRegistro
	 */
	public int eliminaRegistro(
		com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion dp)
		throws
			com.telefonica_chile.bandeja.helpers.ParametrosIncorrectosException,
			com
				.telefonica_chile
				.bandeja
				.servicios
				.publicador
				.PublicadorException;

	public int cuentaPublicacionesVisiblesIgualImplCorrelID( Long nroPeticion, String nomAplicacion,String codActividad, String actImplCorrelID)throws ParametrosIncorrectosException, PublicadorException;

				
	public void borrarPeticionBandejaIntegrada(Long idBandeja)
		throws PublicadorException;
	/**
	 * Permite ocultar la peticion en la bandeja integrada.
	 * Cambia el valor del campo BI_VISIBLE a 0 en la tabla BINTEGRADA
	 * @param nroPeticion
	 * @throws PublicadorException
	 */
	public void ocultarPeticion(Long nroPeticion) throws PublicadorException;
	/**
	 * Permite visualizar la peticion en la bandeja integrada
	 * Cambia el valor del campo BI_VISIBLE a 1 en la tabla BINTEGRADA
	 * @param nroPeticion
	 * @throws PublicadorException
	 */
	public void muestraPeticion(Long nroPeticion) throws PublicadorException;

    /**
     * Permite ocultar la peticion en la bandeja integrada.
     * Cambia el valor del campo BI_VISIBLE a 0 en la tabla BINTEGRADA
     * Considera el numero de peticion y el id de aplicacion y deja no visible TODAS las peticiones 
     * que encuentre con ese filtro.
     * @param nroPeticion
     * @param idAplicacion
     * @throws PublicadorException
     */
    public void ocultarPeticion(Long nroPeticion, Long idAplicacion) throws PublicadorException;
    /**
     * Permite visualizar la peticion en la bandeja integrada
     * Cambia el valor del campo BI_VISIBLE a 1 en la tabla BINTEGRADA
     * Considera el numero de peticion y el id de aplicacion y deja visible TODAS las peticiones 
     * que encuentre con ese filtro.
     * @param nroPeticion
     * @param idAplicacion
     * @throws PublicadorException
     */
    public void muestraPeticion(Long nroPeticion, Long idAplicacion) throws PublicadorException;
	public int cambiaUsuario(DatosPeticion dp)
		throws ParametrosIncorrectosException, PublicadorException;
}
