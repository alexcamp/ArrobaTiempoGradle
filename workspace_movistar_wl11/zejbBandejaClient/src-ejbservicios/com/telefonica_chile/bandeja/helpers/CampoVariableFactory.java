package com.telefonica_chile.bandeja.helpers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class CampoVariableFactory {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private Campo_variableLocalHome homeCampo;
	private Valor_variableLocalHome homeValor;
	public Campo_variableLocalHome getHomeCampoVariable() throws FactoryException {
		if (homeCampo != null)
			return homeCampo;
		try {
			homeCampo =	(Campo_variableLocalHome) HomeFactory.getHome(Campo_variableLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas recuperando home de campo variable: " + Campo_variableLocalHome.JNDI_NAME, e);
			throw new FactoryException("Problemas manipulando campos variables", e);
		}
		
		return homeCampo;
	}

	public Valor_variableLocalHome getHomeValorVariable() throws FactoryException {
		if (homeValor != null)
			return homeValor;
		try {
			return	(Valor_variableLocalHome) HomeFactory.getHome(Valor_variableLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas en home de valor variable: " + Valor_variableLocalHome.JNDI_NAME, e);
			throw new FactoryException("Problemas manipulando valores variables", e);
		}
	}


	public void actualizaValoresVariables(BintegradaLocal bandejaEntity, String[][] valoresVariables) throws FactoryException {
		if (valoresVariables == null)
			return;
		
		
		HashMap mapaCamposVariables = getMapaCamposVariables();//obtengo tabla de CAMPOS VARIABLES===> CAMPO - ENTITY
		HashMap mapaValoresVariables = getMapaValoresVariables(bandejaEntity);
		for (int i = 0; valoresVariables != null && i < valoresVariables.length; i++) {
			String nombre = valoresVariables[i][0];
			String valor = valoresVariables[i][1];
			Campo_variableLocal cvEntity =
				(Campo_variableLocal) mapaCamposVariables.get(nombre);
			if (cvEntity == null)
				cvEntity = creaCampoVariable(nombre, nombre);
			Valor_variableLocal vvEntity = (Valor_variableLocal) mapaValoresVariables.get(nombre);
			if(null == valor){
				valor="";
			}	
			if (vvEntity == null)
				
					creaValorVariable(bandejaEntity, cvEntity, valor);
			else
				vvEntity.setValor(valor);
				
		}
	}

	public Valor_variableLocal creaValorVariable(
			BintegradaLocal bandejaEntity, Campo_variableLocal cvEntity, String valor) throws FactoryException {
		Valor_variableLocal vvEntity;
		try {
			vvEntity = getHomeValorVariable().create(cvEntity,bandejaEntity, valor);
		} catch (CreateException e) {
			log.error("No se pudo crear valor variable (campo, bandeja) = ("
					 + cvEntity.getCv_nombre_int() + ", " + bandejaEntity.getPrimaryKey() + ")", e);
			throw new FactoryException("Error creando valor variable (campo, bandeja) = ("
					 + cvEntity.getCv_nombre_ext() + ", " + bandejaEntity.getPrimaryKey() + ")");
		}
		return vvEntity;
	}

	private Campo_variableLocal creaCampoVariable(String nombreInterno, String nombreExterno) throws FactoryException {
		try {
			Campo_variableLocal cvEntity = getHomeCampoVariable().create(nombreInterno, nombreExterno);
			return cvEntity;
		} catch (CreateException e) {
			log.error("No se pudo crear campo variable: " + nombreInterno, e);
			throw new FactoryException("Error creando campo variable: " + nombreInterno, e);
		}
	}

	private HashMap getMapaValoresVariables(BintegradaLocal bandejaEntity)
	{
		HashMap map = new HashMap();
		for (Iterator it = bandejaEntity.getValor_variable().iterator(); it.hasNext(); ) {
			Valor_variableLocal vvEntity = (Valor_variableLocal) it.next();
			map.put(vvEntity.getFk_valcampo_campo().getCv_nombre_int(), vvEntity);
		}
		return map;
	}


	private HashMap getMapaCamposVariables() throws FactoryException {
		HashMap map = new HashMap();
		Collection campos;
		try {
			campos = getHomeCampoVariable().findAll();
		} catch (FinderException e) {
			log.warn("No se encontraron campos variables");
			return map;
		}
		for (Iterator it = campos.iterator(); it.hasNext(); ) {
			Campo_variableLocal campoEntity = (Campo_variableLocal) it.next();
			map.put(campoEntity.getCv_nombre_int(), campoEntity);
		}
		return map;
	}
}
