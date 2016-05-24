package co.com.atiempo.dto;

import java.util.ArrayList;

public class SuperAgrupPsPeticion {
	private Integer codAgruSubNu;
	private ArrayList listPsPeticion;
	
	public SuperAgrupPsPeticion()
	{
		listPsPeticion=new ArrayList();
	}
	/**
	 * @return
	 */
	public Integer getCodAgruSubNu() {
		return codAgruSubNu;
	}

	/**
	 * @return
	 */
	public ArrayList getListPsPeticion() {
		return listPsPeticion;
	}

	/**
	 * @param integer
	 */
	public void setCodAgruSubNu(Integer integer) {
		codAgruSubNu = integer;
	}

	/**
	 * @param list
	 */
	public void setListPsPeticion(ArrayList list) {
		listPsPeticion = list;
	}

}
