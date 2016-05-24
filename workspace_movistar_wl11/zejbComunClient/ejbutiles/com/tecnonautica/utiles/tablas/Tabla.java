package com.tecnonautica.utiles.tablas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * 
 * Solo almacena el clip actual de items que se muestran,
 * ademas de informacion sobre pagina siguiente / anterior
 *
 */
public abstract class Tabla implements Serializable
{
	public int orden;
	public static final int LARGO_PAGINA_DEFAULT = 10;
	protected List elementos = new ArrayList();
	public  List listaIdElements = new ArrayList();
	public  String idBI = new String();
	protected int paginaActual = 1;
	protected int totalPaginas;
	protected int largoPagina = LARGO_PAGINA_DEFAULT;
	protected int idxInicial;
	protected int idxFinal;
	protected int nroTotalElementos;
	public List listaopcional=new ArrayList();

	/**
	 * Obtencion de log.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(Tabla.class);

	public Tabla(int paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	public Tabla() {
		
	}
	public void retrieve(HashMap filtro) throws TablaException {
		long nroElementos = getNumeroTotalElementos(filtro);

		idxInicial = 1 + (paginaActual - 1) * largoPagina;
		idxFinal = paginaActual * largoPagina;
		this.totalPaginas = (int)Math.ceil(nroElementos / (float) largoPagina);

		if (nroElementos > 0)
		{
			this.elementos = getElementosPagina(filtro);	
		}
// else {
//			log.info("No hay elementos a desplegar, por lo tanto no se trata de recuperarlos");
//		}
		
		nroTotalElementos = (int) nroElementos;
	}

	public void retrieveListado(HashMap filtro) throws TablaException {
		long nroElementos = getNumeroTotalElementos(filtro);
//		if (nroElementos == 0) {
//			log.info("No hay elementos a desplegar, por lo tanto no se trata de recuperarlos");
//		}
		
	}
	
	public List retrieveList(HashMap filtro) throws TablaException {
		log.info("List retrieveList TABLA");
		long nroElementos = getNumeroTotalElementos(filtro);
		List elementos = new ArrayList();
		if (nroElementos > 0) {
			this.elementos = getElementosPagina(filtro);	
		}
//		else {
//			log.info("No hay elementos a desplegar, por lo tanto no se trata de recuperarlos");
//		}
		return elementos;
	}
	
	protected abstract long getNumeroTotalElementos(HashMap filtro) throws TablaException;
	protected abstract List getElementosPagina(HashMap filtro) throws TablaException;
	
	
	public Iterator iterator() {
		return elementos.iterator();
	}
	
	public boolean hayPaginaSiguiente() {
		return totalPaginas > paginaActual;
	}
	
	public boolean hayPaginaPrevia() {
		return paginaActual > 1;
	}
	
	public int getPaginaActual() {
		return paginaActual;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getLargoPagina() {
		return largoPagina;
	}

	public void setLargoPagina(int largoPagina) {
		if (largoPagina <= 0)
			throw new IllegalArgumentException("LargoPagina debe ser mayor que cero: " + largoPagina);

		this.largoPagina = largoPagina;
	}

	public List getElementos() {
		return elementos;
	}

	protected void add(Object elemento) {
		elementos.add(elemento);
	}
	
	public int size() {
		return elementos.size();
	}

	protected int getIdxFinal() {
		return idxFinal;
	}

	protected int getIdxInicial() {
		return idxInicial;
	}
	/**
	 * @return
	 */
	public List getListaIdElements() {
		return listaIdElements;
	}

	/**
	 * @param list
	 */
	public void setListaIdElements(List list) {
		listaIdElements = list;
	}

	/**
	 * @return
	 */
	public int getNroTotalElementos() {
		return nroTotalElementos;
	}

	/**
	 * @param i
	 */
	public void setNroTotalElementos(int i) {
		nroTotalElementos = i;
	}

	/**
	 * @return
	 */
	public List getListaopcional() {
		return listaopcional;
	}
	
	
	public String getOrden(int orden)
	{
		String retorno=" b.BI_NRO_PETICION_ATIS DESC ";
		switch(orden )
		{
			case 1://N° Atis Creciente
				retorno = " b.COD_PET_CD DESC ";
				break;
			case 2://N° Atis DeCreciente
				retorno = " b.COD_PET_CD ASC ";
				break;
			case 3://N° Atiempo Creciente
				retorno = " b.PETI_NUMERO ASC ";
				break;
			case 4://N° Atiempo DeCreciente
				retorno = " b.PETI_NUMERO DESC ";
				break;
			case 5://Departamento Creciente
				retorno = " DEPA.NOMBRE_DEPARTAMENTO ASC ";
				break;
			case 6://Departamento DeCreciente
				retorno = " DEPA.NOMBRE_DEPARTAMENTO DESC ";
				break;
			case 7://Localidad Creciente
				retorno = " LOCA.NOMBRE_LOCALIDAD ASC ";
				break;
			case 8://Localidad DeCreciente
				retorno = " LOCA.NOMBRE_LOCALIDAD DESC ";
				break;
			case 9://Segmento Creciente
				retorno = " SEG.SEGM_DESCRIPCION ASC"; 
				break;
			case 10://Segmento DeCreciente
				retorno = " SEG.SEGM_DESCRIPCION DESC";
				break;
			case 11://SubSegmento Creciente
				retorno = " SUB.DESCRIPCION ASC ";
				break;
			case 12://SubSegmento DeCreciente
				retorno = " SUB.DESCRIPCION DESC ";
				break;
			case 13://Fecha Inicio Creciente
				retorno = " b.PETI_FECHA_INGRESO ASC ";
				break;
			case 14://Fecha Inicio DeCreciente
				retorno = " b.PETI_FECHA_INGRESO DESC ";
				break;
			case 15://Fecha Fin Creciente
				retorno = " b.PETI_FECHA_TERMINO ASC ";
				break;
			case 16://Fecha Fin DeCreciente
				retorno = " b.PETI_FECHA_TERMINO DESC ";
				break;
			/***********FIN Ordenes para la Bandeja Back Office Comercial**************/
	
			case 17:
				retorno = " B.BI_NRO_PETICION_ATIS ASC ";
				break;
			case 18:
				retorno = " B.BI_NRO_PETICION_ATIS DESC ";
				break;
			case 19:
				retorno= " B.DESC_LOCALIDAD ASC ";
				break;
			case 20:
				retorno= " B.DESC_LOCALIDAD DESC ";
				break;
			case 21:
				retorno = " B.DESC_CENTRAL ASC ";
				break;
			case 22:
				retorno = " B.DESC_CENTRAL DESC ";
				break;
			case 23:
				retorno = " A.act_descripcion ASC ";
				break;
			case 24:
				retorno = " A.act_descripcion DESC ";
				break;
			case 25:
				retorno = " B.bi_cliente_nombre ASC ";
				break;
			case 26:
				retorno = " B.bi_cliente_nombre DESC ";
				break;
			case 27:
				//Identificador Creciente
				retorno = " B.BI_CLIENTE_RUT ASC "; 
				break;
			case 28:
				retorno = " B.BI_CLIENTE_RUT DESC ";
				break;
			case 29:
				retorno = " B.BI_FAMILIA_PS ASC";
				break;
			case 30:
				retorno = " B.BI_FAMILIA_PS DESC ";
				break;
			case 31:
				retorno = " B.SEGM_DESCRIPCION ASC ";
				break;
			case 32:
				retorno = " B.SEGM_DESCRIPCION DESC ";
				break;
			case 33:
				retorno = " B.SUBSEGM_DESCRIPCION ASC ";
				break;
			case 34:
				retorno = " B.SUBSEGM_DESCRIPCION DESC ";
				break;
			case 35:
				retorno = " B.bi_fecha_inicio ASC ";
				break;
			case 36:
				retorno = " B.bi_fecha_inicio DESC ";
				break;
			case 37:
				retorno = " B.bi_fecha_apertura ASC ";
				break;
			case 38:
				retorno = " B.bi_fecha_apertura DESC ";
				break;
			case 39:
				retorno = " B.bi_fecha_compromiso ASC ";
				break;
			case 40:
				retorno = " B.bi_fecha_compromiso DESC ";
				break;
		}
		return retorno;		
	}

}
