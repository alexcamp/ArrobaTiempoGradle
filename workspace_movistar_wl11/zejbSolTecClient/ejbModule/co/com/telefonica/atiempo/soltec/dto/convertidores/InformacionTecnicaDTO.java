/*
 * Created on 10-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto.convertidores;

import java.util.ArrayList;
import java.util.Collection;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author Caudillo-Movil
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionTecnicaDTO implements DataTransferObject {

	/**
	 * 
	 */
	public InformacionTecnicaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Inf planta interna
	private Long central;
	private String centralDesc;
	private Long telefono;
	private String len;
	private String lecCont;
	private String posicionHorizontal;

	//Inf planta externa

	private String distr;
	private String descripcionDistr;
	private String direcDistr;
	private String liston;
	private Long parListon;
	private Long cable;
	private Long parCable;
	private String armario;
	private String direcArmario;
	private String caja;
	private String direcCaja;
	private String parCaja;
	
	private Collection dslams=new ArrayList();
	private String ods;
	//sigres
	private Collection zonasAtendimiento;


	/**
	 * @return
	 */
	public String getLecCont() {
		return lecCont;
	}

	/**
	 * @return
	 */
	public String getLen() {
		return len;
	}


	/**
	 * @param string
	 */
	public void setLecCont(String string) {
		lecCont = string;
	}

	/**
	 * @param string
	 */
	public void setLen(String string) {
		len = string;
	}



	/**
	 * @return
	 */
	public String getArmario() {
		return armario;
	}


	/**
	 * @return
	 */
	public String getCaja() {
		return caja;
	}

	/**
	 * @return
	 */
	public String getDirecArmario() {
		return direcArmario;
	}

	/**
	 * @return
	 */
	public String getDirecCaja() {
		return direcCaja;
	}

	/**
	 * @return
	 */
	public String getDirecDistr() {
		return direcDistr;
	}

	/**
	 * @return
	 */
	public String getDistr() {
		return distr;
	}

	/**
	 * @return
	 */
	public String getListon() {
		return liston;
	}

	/**
	 * @return
	 */
	public String getParCaja() {
		return parCaja;
	}

	/**
	 * @param string
	 */
	public void setArmario(String string) {
		armario = string;
	}

	/**
	 * @param string
	 */
	public void setCaja(String string) {
		caja = string;
	}


	/**
	 * @param string
	 */
	public void setDirecArmario(String string) {
		direcArmario = string;
	}

	/**
	 * @param string
	 */
	public void setDirecCaja(String string) {
		direcCaja = string;
	}

	/**
	 * @param string
	 */
	public void setDirecDistr(String string) {
		direcDistr = string;
	}

	/**
	 * @param string
	 */
	public void setDistr(String string) {
		distr = string;
	}

	/**
	 * @param string
	 */
	public void setListon(String string) {
		liston = string;
	}

	/**
	 * @param string
	 */
	public void setParCaja(String string) {
		parCaja = string;
	}

	/**
	 * @return
	 */
	public String getOds() {
		return ods;
	}

	/**
	 * @param string
	 */
	public void setOds(String string) {
		ods = string;
	}

	/**
	 * @return
	 */
	public Long getCable() {
		return cable;
	}

	/**
	 * @return
	 */
	public Long getCentral() {
		return central;
	}

	/**
	 * @return
	 */
	public Long getParCable() {
		return parCable;
	}

	/**
	 * @return
	 */
	public Long getTelefono() {
		return telefono;
	}

	/**
	 * @param long1
	 */
	public void setCable(Long long1) {
		cable = long1;
	}

	/**
	 * @param long1
	 */
	public void setCentral(Long long1) {
		central = long1;
	}

	/**
	 * @param long1
	 */
	public void setParCable(Long long1) {
		parCable = long1;
	}

	/**
	 * @param long1
	 */
	public void setTelefono(Long long1) {
		telefono = long1;
	}

	/**
	 * @return
	 */
	public Long getParListon() {
		return parListon;
	}

	/**
	 * @param long1
	 */
	public void setParListon(Long long1) {
		parListon = long1;
	}

	/**
	 * @return
	 */
	public Collection getDslams() {
		return dslams;
	}

	/**
	 * @param collection
	 */
	public void setDslams(Collection collection) {
		dslams = collection;
	}

	/**
	 * @return
	 */
	public String getDescripcionDistr() {
		return descripcionDistr;
	}

	/**
	 * @param string
	 */
	public void setDescripcionDistr(String string) {
		descripcionDistr = string;
	}

	/**
	 * @return
	 */
	public String getCentralDesc() {
		return centralDesc;
	}

	/**
	 * @param string
	 */
	public void setCentralDesc(String string) {
		centralDesc = string;
	}

	/**
	 * @return
	 */
	public String getPosicionHorizontal() {
		return posicionHorizontal;
	}

	/**
	 * @param string
	 */
	public void setPosicionHorizontal(String string) {
		posicionHorizontal = string;
	}

	/**
	 * @return
	 */
	public Collection getZonasAtendimiento() {
		return zonasAtendimiento;
	}

	/**
	 * @param collection
	 */
	public void setZonasAtendimiento(Collection collection) {
		zonasAtendimiento = collection;
	}

}
