/*
 * Created on 30-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto;

import java.sql.Timestamp;

/**
 * @author QA
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DatosCierreDTO {
	
	private String claveCancelacion;
	private String claveCausa;
	private String tecnicoCierre;
	private Timestamp fechaCierre;
	private String detalleCierre;
	private String nombreContacto1;
	private String nombreContacto2;
	private String fonoContacto2;
	private String nombreReclamante;
	private String ape1Reclamante;
	private String ape2Reclamante;
	private String prodComercial;
	private String idProdComercial;
	private String codigoAveria;
	private Long tipoPc;
	private String codAperAve;
	private Timestamp fechaApertura;
	private Timestamp fechaCompromiso;
	private String categorizacion;
	private String prioridadAve;
	private String obsAveria;
	private Integer estadoPet;	


	/**
	 * @return
	 */
	public String getClaveCancelacion() {
		return claveCancelacion;
	}

	/**
	 * @return
	 */
	public String getClaveCausa() {
		return claveCausa;
	}

	/**
	 * @return
	 */
	public String getDetalleCierre() {
		return detalleCierre;
	}

	/**
	 * @return
	 */
	public Timestamp getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @return
	 */
	public String getFonoContacto2() {
		return fonoContacto2;
	}

	/**
	 * @return
	 */
	public String getNombreContacto1() {
		return nombreContacto1;
	}

	/**
	 * @return
	 */
	public String getNombreContacto2() {
		return nombreContacto2;
	}

	/**
	 * @return
	 */
	public String getTecnicoCierre() {
		return tecnicoCierre;
	}

	/**
	 * @param string
	 */
	public void setClaveCancelacion(String string) {
		claveCancelacion = string;
	}

	/**
	 * @param string
	 */
	public void setClaveCausa(String string) {
		claveCausa = string;
	}

	/**
	 * @param string
	 */
	public void setDetalleCierre(String string) {
		detalleCierre = string;
	}

	/**
	 * @param timestamp
	 */
	public void setFechaCierre(Timestamp timestamp) {
		fechaCierre = timestamp;
	}

	/**
	 * @param string
	 */
	public void setFonoContacto2(String string) {
		fonoContacto2 = string;
	}

	/**
	 * @param string
	 */
	public void setNombreContacto1(String string) {
		nombreContacto1 = string;
	}

	/**
	 * @param string
	 */
	public void setNombreContacto2(String string) {
		nombreContacto2 = string;
	}

	/**
	 * @param string
	 */
	public void setTecnicoCierre(String string) {
		tecnicoCierre = string;
	}

	/**
	 * @return
	 */
	public String getApe1Reclamante() {
		return ape1Reclamante;
	}

	/**
	 * @return
	 */
	public String getApe2Reclamante() {
		return ape2Reclamante;
	}

	/**
	 * @return
	 */
	public String getNombreReclamante() {
		return nombreReclamante;
	}

	/**
	 * @param string
	 */
	public void setApe1Reclamante(String string) {
		ape1Reclamante = string;
	}

	/**
	 * @param string
	 */
	public void setApe2Reclamante(String string) {
		ape2Reclamante = string;
	}

	/**
	 * @param string
	 */
	public void setNombreReclamante(String string) {
		nombreReclamante = string;
	}

	/**
	 * @return
	 */
	public String getCodigoAveria() {
		return codigoAveria;
	}

	/**
	 * @param string
	 */
	public void setCodigoAveria(String string) {
		codigoAveria = string;
	}

	/**
	 * @param long1
	 */
	public void setTipoPc(Long long1) {
		tipoPc = long1;
	}

	/**
	 * @return
	 */
	public Long getTipoPc() {
		return tipoPc;
	}

	/**
	 * @param timestamp
	 */
	public void setFechaApertura(Timestamp timestamp) {
		fechaApertura = timestamp;
	}

	/**
	 * @return
	 */
	public Timestamp getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * @return
	 */
	public String getIdProdComercial() {
		return idProdComercial;
	}

	/**
	 * @param string
	 */
	public void setIdProdComercial(String string) {
		idProdComercial = string;
	}

	/**
	 * @return
	 */
	public String getCategorizacion() {
		return categorizacion;
	}

	/**
	 * @return
	 */
	public String getCodAperAve() {
		return codAperAve;
	}

	/**
	 * @return
	 */
	public String getObsAveria() {
		return obsAveria;
	}

	/**
	 * @return
	 */
	public String getPrioridadAve() {
		return prioridadAve;
	}

	/**
	 * @param string
	 */
	public void setCategorizacion(String string) {
		categorizacion = string;
	}

	/**
	 * @param string
	 */
	public void setCodAperAve(String string) {
		codAperAve = string;
	}

	/**
	 * @param string
	 */
	public void setObsAveria(String string) {
		obsAveria = string;
	}

	/**
	 * @param string
	 */
	public void setPrioridadAve(String string) {
		prioridadAve = string;
	}


	/**
	 * @return
	 */
	public String getProdComercial() {
		return prodComercial;
	}

	/**
	 * @param string
	 */
	public void setProdComercial(String string) {
		prodComercial = string;
	}

	/**
	 * @return
	 */
	public Integer getEstadoPet() {
		return estadoPet;
	}

	/**
	 * @param integer
	 */
	public void setEstadoPet(Integer integer) {
		estadoPet = integer;
	}

	/**
	 * @return
	 */
	public Timestamp getFechaCompromiso() {
		return fechaCompromiso;
	}

	/**
	 * @param timestamp
	 */
	public void setFechaCompromiso(Timestamp timestamp) {
		fechaCompromiso = timestamp;
	}

}
