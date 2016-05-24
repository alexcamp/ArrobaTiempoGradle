package com.telefonica_chile.bandeja.dto;

import java.util.ArrayList;

public class ReasignaResultDTO
{
	private Long idUsuarioSupervisor;
	private Long idUserIni;
	private Long idUserNew;
	private String loginUserIni;
	private String loginUserNew;
	private ArrayList peticiones;
	private ArrayList noAsignadas;
	
	public ReasignaResultDTO()
	{
		peticiones=new ArrayList();
		noAsignadas=new ArrayList();
	}
	public Long getIdUserIni() {
		return idUserIni;
	}

	/**
	 * @return
	 */
	public Long getIdUserNew() {
		return idUserNew;
	}

	/**
	 * @return
	 */
	public String getLoginUserIni() {
		return loginUserIni;
	}

	/**
	 * @return
	 */
	public String getLoginUserNew() {
		return loginUserNew;
	}

	/**
	 * @return
	 */
	public ArrayList getPeticiones() {
		return peticiones;
	}

	/**
	 * @param long1
	 */
	public void setIdUserIni(Long long1) {
		idUserIni = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdUserNew(Long long1) {
		idUserNew = long1;
	}

	/**
	 * @param string
	 */
	public void setLoginUserIni(String string) {
		loginUserIni = string;
	}

	/**
	 * @param string
	 */
	public void setLoginUserNew(String string) {
		loginUserNew = string;
	}

	/**
	 * @param list
	 */
	public void setPeticiones(ArrayList list) {
		peticiones = list;
	}

	public void addPeticion(String string)
	{
		peticiones.add(string);
		
	}
	
	public void addNoReasignada(String string)
	{
		noAsignadas.add(string);
	}

	/**
	 * @return
	 */
	public Long getIdUsuarioSupervisor() {
		return idUsuarioSupervisor;
	}

	/**
	 * @param long1
	 */
	public void setIdUsuarioSupervisor(Long long1) {
		idUsuarioSupervisor = long1;
	}

	/**
	 * @return
	 */
	public ArrayList getNoAsignadas() {
		return noAsignadas;
	}

	/**
	 * @param list
	 */
	public void setNoAsignadas(ArrayList list) {
		noAsignadas = list;
	}

}
