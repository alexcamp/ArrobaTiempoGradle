/*
 * Created on 15-03-2007
 */
package co.com.atiempo.dto;

import java.util.HashMap;
import java.util.Vector;

/**
 * @author Respinoza
 */
public class CausalPsOcActividadDTO implements Comparable {

	public CausalPsOcActividadDTO() {
		super();
	}
	
	private Long codCausal;
	private Long correlativo;
	private Long codPs;
	private Long codActividad;
	private Long codOc;
	private Long idFamiliaPs;
	//CR23995 -- 23/04/2009 -- G.Quevedo
	private Short gestionable;
	
	private String descOc;
	private String descPs;
	private String descripcionCausa;
	
	private Integer actQiebre;
	
	private HashMap descCausales;
	
	private Vector catalogoCausales;
	
	private Long corrPSPeticion;
	
	private boolean psPasaPorAct = false;
	private boolean quiebre;
	private boolean asignado;
	
	

	/**
	 * @return
	 */
	public Long getCodActividad() {
		return codActividad;
	}

	/**
	 * @return
	 */
	public Long getCodCausal() {
		return codCausal;
	}

	/**
	 * @return
	 */
	public Long getCodOc() {
		return codOc;
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
	 * @param long1
	 */
	public void setCodActividad(Long long1) {
		codActividad = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodCausal(Long long1) {
		codCausal = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodOc(Long long1) {
		codOc = long1;
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
	 * @return
	 */
	public String getDescOc() {
		return descOc;
	}

	/**
	 * @return
	 */
	public String getDescPs() {
		return descPs;
	}

	/**
	 * @param string
	 */
	public void setDescOc(String string) {
		descOc = string;
	}

	/**
	 * @param string
	 */
	public void setDescPs(String string) {
		descPs = string;
	}

	/**
	 * @return
	 */
	public Integer getActQiebre() {
		return actQiebre;
	}

	/**
	 * @param integer
	 */
	public void setActQiebre(Integer integer) {
		actQiebre = integer;
	}

	/**
	 * @return
	 */
	public HashMap getDescCausales() {
		return descCausales;
	}

	/**
	 * @param map
	 */
	public void setDescCausales(HashMap map) {
		descCausales = map;
	}

	/**
	 * @return
	 */
	public Vector getCatalogoCausales() {
		return catalogoCausales;
	}

	/**
	 * @param vector
	 */
	public void setCatalogoCausales(Vector vector) {
		catalogoCausales = vector;
	}

	/**
	 * @return
	 */
	public Long getCorrPSPeticion() {
		return corrPSPeticion;
	}

	/**
	 * @param long1
	 */
	public void setCorrPSPeticion(Long long1) {
		corrPSPeticion = long1;
	}

	/**
	 * @return
	 */
	public boolean isPsPasaPorAct() {
		return psPasaPorAct;
	}

	/**
	 * @param b
	 */
	public void setPsPasaPorAct(boolean b) {
		psPasaPorAct = b;
	}

	/**
	 * @return
	 */
	public Long getIdFamiliaPs() {
		return idFamiliaPs;
	}

	/**
	 * @param long1
	 */
	public void setIdFamiliaPs(Long long1) {
		idFamiliaPs = long1;
	}

	/**
	 * @return
	 */
	public String getDescripcionCausa() {
		return descripcionCausa;
	}

	/**
	 * @param string
	 */
	public void setDescripcionCausa(String string) {
		descripcionCausa = string;
	}

	/**
	 * @return
	 */
	public boolean isQuiebre() {
		return quiebre;
	}

	/**
	 * @param b
	 */
	public void setQuiebre(boolean b) {
		quiebre = b;
	}

	/**
	 * @return
	 */
	public boolean isAsignado() {
		return asignado;
	}

	/**
	 * @param b
	 */
	public void setAsignado(boolean b) {
		asignado = b;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		CausalPsOcActividadDTO otro=(CausalPsOcActividadDTO) o;
		if(this.descripcionCausa!=null && otro.getDescripcionCausa()!=null)
			return this.descripcionCausa.compareTo(otro.getDescripcionCausa());
		return 0;
	}

	/**
	 * @return Returns the gestionable.
	 */
	public Short getGestionable() {
		return gestionable;
	}
	/**
	 * @param gestionable The gestionable to set.
	 */
	public void setGestionable(Short gestionable) {
		this.gestionable = gestionable;
	}
}
