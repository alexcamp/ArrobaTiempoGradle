/*
 * Created on Mar 13, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionTecnicoDTO implements DataTransferObject {
	private String nombreContratista;
	private String codigoTecnicoInicial;
	private String codigoTecnicoCierre;
	private String nombreTecnicoCierre;
	
	public InformacionTecnicoDTO()
	{
		
	}
	/**
	 * @return
	 */
	public String getCodigoTecnicoCierre() {
		return codigoTecnicoCierre;
	}

	/**
	 * @return
	 */
	public String getCodigoTecnicoInicial() {
		return codigoTecnicoInicial;
	}

	/**
	 * @return
	 */
	public String getNombreContratista() {
		return nombreContratista;
	}

	/**
	 * @return
	 */
	public String getNombreTecnicoCierre() {
		return nombreTecnicoCierre;
	}

	/**
	 * @param string
	 */
	public void setCodigoTecnicoCierre(String codigoTecnicoCierre) {
		this.codigoTecnicoCierre = codigoTecnicoCierre;
	}

	/**
	 * @param string
	 */
	public void setCodigoTecnicoInicial(String codigoTecnicoInicial) {
		this.codigoTecnicoInicial = codigoTecnicoInicial;
	}

	/**
	 * @param string
	 */
	public void setNombreContratista(String nombreContratista) {
		this.nombreContratista = nombreContratista;
	}

	/**
	 * @param string
	 */
	public void setNombreTecnicoCierre(String nombreTecnicoCierre) {
		this.nombreTecnicoCierre = nombreTecnicoCierre;
	}

}
