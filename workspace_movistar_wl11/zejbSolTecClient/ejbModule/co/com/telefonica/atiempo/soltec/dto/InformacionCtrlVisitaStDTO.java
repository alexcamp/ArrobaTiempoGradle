package co.com.telefonica.atiempo.soltec.dto;

import com.telefonica_chile.atiempo.utiles.Fecha;

/**
 * @author lcaldera
 *
 */
public class InformacionCtrlVisitaStDTO implements Comparable
{
	private Long codAveCd;
	private Fecha fechaLlegada;
	private Fecha fechaSalida;
	private Fecha fechaRegistro;
	/**
	 * @return
	 */
	public Long getCodAveCd() {
		return codAveCd;
	}

	/**
	 * @return
	 */
	public Fecha getFechaLlegada() {
		return fechaLlegada;
	}

	/**
	 * @return
	 */
	public Fecha getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @return
	 */
	public Fecha getFechaSalida() {
		return fechaSalida;
	}

	/**
	 * @param long1
	 */
	public void setCodAveCd(Long long1) {
		codAveCd = long1;
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
	public void setFechaRegistro(Fecha fecha) {
		fechaRegistro = fecha;
	}

	/**
	 * @param fecha
	 */
	public void setFechaSalida(Fecha fecha) {
		fechaSalida = fecha;
	}

	public int compareTo(Object o)
	{
		InformacionCtrlVisitaStDTO otro=(InformacionCtrlVisitaStDTO) o;
		if(this.fechaRegistro!=null && otro.getFechaRegistro()!=null)
			return this.fechaRegistro.compareTo(otro.getFechaRegistro());
		return 0;
	}

}
