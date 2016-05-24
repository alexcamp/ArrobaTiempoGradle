/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.ArrayList;


public class CandidatosDTO implements Serializable {
	
	private ArrayList listaUsuarios = new ArrayList();
	private ArrayList listaSupervisores = new ArrayList();	

	public CandidatosDTO() {
	}


	/**
	 * @return
	 */
	public ArrayList getListaSupervisores() {
		return listaSupervisores;
	}

	/**
	 * @return
	 */
	public ArrayList getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param list
	 */
	public void setListaSupervisores(ArrayList list) {
		listaSupervisores = list;
	}

	/**
	 * @param list
	 */
	public void setListaUsuarios(ArrayList list) {
		listaUsuarios = list;
	}

}
