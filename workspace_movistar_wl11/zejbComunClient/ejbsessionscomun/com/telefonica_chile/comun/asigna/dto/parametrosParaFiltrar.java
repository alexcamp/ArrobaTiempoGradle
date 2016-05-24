/*
 * Created on Feb 24, 2005
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;




public class parametrosParaFiltrar  implements Serializable{
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private String 	parametroCodPCO;
	private String 	parametroTipSVA;
	private String 	parametroPtoVta;
	private String 	parametroCodAct;
	private String	parametroModNeg;
	private String	parametroTipUsu;
	private String 	parametroRetEqp;
	private String	parametroCodSeg;
	private String 	parametroTICA;
	private String 	parametroAgencia;
	private String 	parametroTipoTrabajo;
	private String 	parametroPComercial;
	
	//Parámetros Agenda
	private String 	parametroSistema;
	private ArrayList  	parametroPS;
	
	
			

	
	public String getParametroCodPCO() {
		return parametroCodPCO;
	}

	public String getParametroTipSVA() {
		return parametroTipSVA;
	}
	
	public String getParametroPtoVta() {
		return parametroPtoVta;
	}
	
	public String getParametroCodAct() {
		return parametroCodAct;
	}
	
	public String getParametroModNeg() {
		return parametroModNeg;
	}
	
	public String getParametroTipUsu() {
		return parametroTipUsu;
	}
	
	public String getParametroRetEqp() {
		return parametroRetEqp;
	}
	
	public String getParametroCodSeg() {
		return parametroCodSeg;
	}
	
	public String getParametroTICA() {
		return parametroTICA;
	}
	
	public String getParametroAgencia() {
		return parametroAgencia;
	}
	
	public String getParametroTipoTrabajo() {
		return parametroTipoTrabajo;
	}

	public String getParametroPComercial() {
		return parametroPComercial;
	}
	
		
	public void setParametroCodPSO(String codPCO) {
		this.parametroCodPCO = codPCO;
	}

	public void setParametroTipSVA(String tipSVA) {
		this.parametroTipSVA = tipSVA;
	}
	
	public void setParametroPtoVta(String ptoVta) {
		this.parametroPtoVta = ptoVta;
	}
	
	public void setParametroCodAct(String codAct) {
		this.parametroCodAct = codAct;
	}
	
	public void setParametroModNeg(String modNeg) {
		this.parametroModNeg = modNeg;
	}
	
	public void setParametroTipUsu(String tipUsu) {
		this.parametroTipUsu = tipUsu;
	}
	
	public void setParametroRetEqp(String retEqp) {
		this.parametroRetEqp = retEqp;
	}
	
	public void setParametroCodSeg(String codSeg) {
		segmento codGrupo = null;
		codGrupo = segmento.recuperaGrupoSegmento(codSeg);
		/* asigna al segmento el id del grupo */
		if (codGrupo==null) {
			this.parametroCodSeg 		=   null;
		} else {
			this.parametroCodSeg 		= 	String.valueOf(codGrupo.getSegmentoIdGrupo().intValue());
		}
	}
	
	public void setParametroTICA(String TICA) {
		this.parametroTICA = TICA;
	}
	
	public void setParametroAgencia(String agencia) {
		this.parametroAgencia = agencia;
	}
	
	public void setParametroTipoTrabajo(String tipoTrabajo) {
		this.parametroTipoTrabajo = tipoTrabajo;
	}
	
	public void setParametroPComercial(String PComercial) {
		this.parametroPComercial = PComercial;
	}
	
	public ArrayList getParametroPS() {
		return parametroPS;
	}
	
	public void setParametroPS(ArrayList list) {
		parametroPS = list;
	}
	
	
	/**
	 * OBJETO QUE ALMACENA LOS PARAMETROS RECIBIDOS DESDE LA PETICION
	 * @param codPCO
	 * @param tipSVA
	 * @param ptoVta
	 * @param codAct
	 * @param modNeg
	 * @param tipUsu
	 * @param retEqp
	 * @param codSeg
	 * @param TICA
	 * @param agencia
	 * @param tipoTrabajo
	 * @param PComercial
	 */
	public parametrosParaFiltrar(String codPCO, String tipSVA, String ptoVta, String codAct,
				String modNeg, String tipUsu, String retEqp, String codSeg,
				String TICA, String agencia, String tipoTrabajo, String PComercial)	{
		segmento codGrupo = null;
		codGrupo = segmento.recuperaGrupoSegmento(codSeg);
	
		this.parametroCodPCO 		= codPCO;
		this.parametroTipSVA 		= tipSVA;
		this.parametroPtoVta 		= ptoVta;
		this.parametroCodAct 		= codAct;
		this.parametroModNeg 		= modNeg;
		this.parametroTipUsu 		= tipUsu;
		this.parametroRetEqp 		= retEqp;
		/* asigna grupo al segmento */
		
		if (codGrupo==null) {
			this.parametroCodSeg 		= null;
		} else {
			this.parametroCodSeg 		= 	String.valueOf(codGrupo.getSegmentoIdGrupo().intValue());
		}
		
		this.parametroTICA 			= TICA;
		this.parametroAgencia 		= agencia;
		this.parametroTipoTrabajo 	= tipoTrabajo;
		this.parametroPComercial 	= PComercial;	
	}
	
	public parametrosParaFiltrar(String sistema,
								 String codActividad,
								 String tipoTrabajo,
								 String TICA,
								 String codSeg,
								 String PComercial,
								 String ptoVta,
								 String agencia,
								 String modNeg,			 
								 ArrayList arrayPS){	
			segmento codGrupo = null;
			codGrupo = segmento.recuperaGrupoSegmento(codSeg);	 							 
								 
			
			this.parametroCodAct		= codActividad;
			this.parametroSistema 		= sistema;
			this.parametroPtoVta 		= ptoVta;			
			this.parametroModNeg 		= modNeg;
			
			if (codGrupo==null) {
				this.parametroCodSeg 		= null;
			} else {
				this.parametroCodSeg 		= 	String.valueOf(codGrupo.getSegmentoIdGrupo().intValue());
			}
			
			this.parametroTICA 			= TICA;
			this.parametroAgencia 		= agencia;
			this.parametroTipoTrabajo 	= tipoTrabajo;
			this.parametroPComercial 	= PComercial;
			this.parametroPS			= arrayPS;	
		}
	/**
	 * RETORNA EL VALOR PARA UN PARAMETRO DE UNALISTA DE PARAMETROS
	 * @param parametros
	 * @param parametroNombre
	 * @return valorParametro
	 */
	public static String recuperaValorHabilidad(parametrosParaFiltrar parametros, String parametroNombre) {
		String valorParametro = null;

		if (parametroNombre.equals("COD_PCO")) {
			valorParametro = parametros.getParametroCodPCO();} 

		if (parametroNombre.equals("TIP_SVA")) {
					valorParametro = parametros.getParametroTipSVA();} 
		
		if (parametroNombre.equals("PTO_VTA")) {
					valorParametro = parametros.getParametroPtoVta();} 
					
		if (parametroNombre.equals("COD_ACT")) {
					valorParametro = parametros.getParametroCodAct();} 
		
		if (parametroNombre.equals("MOD_NEG")) {
					valorParametro = parametros.getParametroModNeg();}
		
		if (parametroNombre.equals("TIP_USU")) {
					valorParametro = parametros.getParametroTipUsu();} 
					
		if (parametroNombre.equals("RET_EQP")) {
					valorParametro = parametros.getParametroRetEqp();} 
					
		if (parametroNombre.equals("SEGM")) {
					valorParametro = parametros.getParametroCodSeg();} 
					
		if (parametroNombre.equals("TICA")) {
					valorParametro = parametros.getParametroTICA();} 
					
		if (parametroNombre.equals("AGENCIA")) {
					valorParametro = parametros.getParametroAgencia();} 
					
		if (parametroNombre.equals("TIPOTRABAJO")) {
					valorParametro = parametros.getParametroTipoTrabajo();} 
					
		if (parametroNombre.equals("PCOMERCIAL")) {
					valorParametro = parametros.getParametroPComercial();} 
					
		
		return valorParametro;
	}
	


	/**
	 * @return
	 */
	public String getParametroSistema() {
		return parametroSistema;
	}

	/**
	 * @param string
	 */
	public void setParametroCodPCO(String string) {
		parametroCodPCO = string;
	}

	/**
	 * @param string
	 */
	public void setParametroSistema(String string) {
		parametroSistema = string;
	}

}