/*
 * Created on 15-03-2007
 */
package co.com.atiempo.dto;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;

/**
 * @author Respinoza
 */
public class EstadoPsPeticionDTO implements Comparable
{

	public EstadoPsPeticionDTO() {
		super();
	}
	
	private Integer codEstadoCierre;
	private Long petiNumero;
	private Long codPs;
	private Long correlativo;
	private Long codCausa;
	private String novedad;
	
	private String descPs;
	private String descAct;
	private String descCausa;
	
	private String quiebreCausa;
	private String actQuiebre;
	
	private Long correPspet;
	
	private Long usuaId;
	private String usuaNombre;
	
	private String fechaTermino;
	
	
	/**
	 * @return
	 */
	public Integer getCodEstadoCierre() {
		return codEstadoCierre;
	}

	/**
	 * @return
	 */
	public Long getCodPs() {
		return codPs;
	}

	/**
	 * @return
	 */
	public Long getCorrelativo() {
		return correlativo;
	}

	/**
	 * @return
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

	/**
	 * @param integer
	 */
	public void setCodEstadoCierre(Integer integer) {
		codEstadoCierre = integer;
	}

	/**
	 * @param long1
	 */
	public void setCodPs(Long long1) {
		codPs = long1;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(Long long1) {
		correlativo = long1;
	}

	/**
	 * @param long1
	 */
	public void setPetiNumero(Long long1) {
		petiNumero = long1;
	}


	/**
	 * @return
	 */
	public String getDescAct() {
		return descAct;
	}

	/**
	 * @return
	 */
	public String getDescPs() {
		return descPs;
	}

	/**
	 * @return
	 */
	public String getNovedad() {
		return novedad;
	}


	/**
	 * @param string
	 */
	public void setDescAct(String string) {
		descAct = string;
	}

	/**
	 * @param string
	 */
	public void setDescPs(String string) {
		descPs = string;
	}

	/**
	 * @param string
	 */
	public void setNovedad(String string) {
		novedad = string;
	}

	/**
	 * @return
	 */
	public Long getCodCausa() {
		return codCausa;
	}

	/**
	 * @param integer
	 */
	public void setCodCausa(Long integer) {
		codCausa = integer;
	}

	/**
	 * @return
	 */
	public String getDescCausa() {
		return descCausa;
	}

	/**
	 * @param string
	 */
	public void setDescCausa(String string) {
		descCausa = string;
	}

	/**
	 * @return
	 */
	public Long getCorrePspet() {
		return correPspet;
	}

	/**
	 * @param long1
	 */
	public void setCorrePspet(Long long1) {
		correPspet = long1;
	}

	/**
	 * @return
	 */
	public String getQuiebreCausa() {
		return quiebreCausa;
	}

	/**
	 * @param string
	 */
	public void setQuiebreCausa(String string) {
		quiebreCausa = string;
	}

	/**
	 * @return
	 */
	public String getActQuiebre() {
		return actQuiebre;
	}

	/**
	 * @param string
	 */
	public void setActQuiebre(String string) {
		actQuiebre = string;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @return
	 */
	public String getUsuaNombre() {
		return usuaNombre;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

	/**
	 * @param string
	 */
	public void setUsuaNombre(String string) {
		usuaNombre = string;
	}

	/**
	 * @return
	 */
	public String getFechaTermino() {
		return fechaTermino;
	}

	/**
	 * @param string
	 */
	public void setFechaTermino(String string)
	{
		fechaTermino = string;
	}

	public int compareTo(Object o)
	{
		EstadoPsPeticionDTO otro=(EstadoPsPeticionDTO) o;
		if(otro.getFechaTermino()==null || this.fechaTermino==null)
			return 0;
		try {
			Fecha fOtro = new Fecha(otro.getFechaTermino(),"dd/MM/yyyy HH:mm");
			Fecha fActual = new Fecha(this.fechaTermino,"dd/MM/yyyy HH:mm");
			return fActual.compareTo(fOtro);
		} catch (FechaException e) {
			return 0;
		}
		
	}

}
