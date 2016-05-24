package co.com.telefonica.atiempo.vpistbba.dto;

import java.util.ArrayList;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

import com.telefonica_chile.atiempo.utiles.Fecha;

/**
 * @author lcaldera
 *
 */
public class InformacionControlVisitaDTO implements DataTransferObject
{
	private Long peticion;
	private Fecha fechaLlegada;
	private Fecha fechaSalida;
	private Fecha fechaRegistro;
	private String codigoPs;
	private String nomPS;
	private String codigoTecnico;
	private String nombreTecnicoInicial = null;
	private String codCausaDemora;
	private String nomCausaDemora;
	private String sFechaLlegada;
	private String sFechaSalida;
	/**
	 * @return
	 */
	private ArrayList listaV = new ArrayList();
	
	
	
	/**
	 * @return Devuelve listaV.
	 */
	public ArrayList getListaV() {
		return listaV;
	}
	/**
	 * @param listaV El listaV a establecer.
	 */
	public void setListaV(ArrayList list) {
		listaV = list;
	}
	public Fecha getFechaLlegada() {
		return fechaLlegada;
	}

	/**
	 * @return
	 */
	public Fecha getFechaSalida() {
		return fechaSalida;
	}

	/**
	 * @return
	 */
	public Long getPeticion() {
		return peticion;
	}

	/**
	 * @param fecha
	 */
	public void setFechaLlegada(Fecha fecha) {
		fechaLlegada = fecha;
	}

	/**
	 * @param fecha
	 */
	public void setFechaSalida(Fecha fecha) {
		fechaSalida = fecha;
	}

	/**
	 * @param long1
	 */
	public void setPeticion(Long long1) {
		peticion = long1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		InformacionControlVisitaDTO otro=(InformacionControlVisitaDTO) o;
		if(this.fechaRegistro!=null && otro.getFechaRegistro()!=null)
			return this.fechaRegistro.compareTo(otro.getFechaRegistro());
		return 0;
	}

	/**
	 * @return
	 */
	public Fecha getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fecha
	 */
	public void setFechaRegistro(Fecha fecha) {
		fechaRegistro = fecha;
	}

	/**
	 * @return Devuelve codigoPs.
	 */

	//Gustavo - CR 25403
	
	public String getCodigoPs() {
		return codigoPs;
	}
	/**
	 * @param codigoPs El codigoPs a establecer.
	 */
	public void setCodigoPs(String string) {
		codigoPs = string;
	}
	/**
	 * @return Devuelve codigoTecnico.
	 */
	public String getCodigoTecnico() {
		return codigoTecnico;
	}
	/**
	 * @param codigoTecnico El codigoTecnico a establecer.
	 */
	public void setCodigoTecnico(String string) {
		codigoTecnico = string;
	}
	/**
	 * @return Devuelve nombreTecnicoInicial.
	 */
	public String getNombreTecnicoInicial() {
		return nombreTecnicoInicial;
	}
	/**
	 * @param nombreTecnicoInicial El nombreTecnicoInicial a establecer.
	 */
	public void setNombreTecnicoInicial(String string) {
		nombreTecnicoInicial = string;
	}
	/**
	 * @return Devuelve codCausaDemora.
	 */
	public String getCodCausaDemora() {
		return codCausaDemora;
	}
	/**
	 * @param codCausaDemora El codCausaDemora a establecer.
	 */
	public void setCodCausaDemora(String string) {
		codCausaDemora = string;
	}
	/**
	 * @return Devuelve nomCausaDemora.
	 */
	public String getNomCausaDemora() {
		return nomCausaDemora;
	}
	/**
	 * @param nomCausaDemora El nomCausaDemora a establecer.
	 */
	public void setNomCausaDemora(String string) {
		nomCausaDemora = string;
	}
	/**
	 * @return Devuelve nomPS.
	 */
	public String getNomPS() {
		return nomPS;
	}
	/**
	 * @param nomPS El nomPS a establecer.
	 */
	public void setNomPS(String string) {
		nomPS = string;
	}
	/**
	 * @return Devuelve sFechaLlegada.
	 */
	public String getSFechaLlegada() {
		return sFechaLlegada;
	}
	/**
	 * @param fechaLlegada El sFechaLlegada a establecer.
	 */
	public void setSFechaLlegada(String string) {
		sFechaLlegada = string;
	}
	/**
	 * @return Devuelve sFechaSalida.
	 */
	public String getSFechaSalida() {
		return sFechaSalida;
	}
	/**
	 * @param fechaSalida El sFechaSalida a establecer.
	 */
	public void setSFechaSalida(String string) {
		sFechaSalida = string;
	}
	
	//Gustavo - CR 25403 - Fin
}
