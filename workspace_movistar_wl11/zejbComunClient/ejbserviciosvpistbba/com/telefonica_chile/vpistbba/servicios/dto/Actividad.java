/*
 * Created on Nov 24, 2004
 *
 */
package com.telefonica_chile.vpistbba.servicios.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.director.algoritmo.DirectorException;
import com.telefonica_chile.director.dto.ProductoServicio;

/**
 * Representa una actividad dentro del flujo para poder provicionar e instalar un Producto Servicio.
 * Esta actividad esta asociada a los productos y/o servicios con una operacion comercial, para los 
 * cuales esta actividad debe ser realizada.
 * 
 * @author dfiguer
 * @version %I%, %G%
 */
public class Actividad implements Serializable{
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private Integer id;
	private String codigo;
	private String descripcion;
	private TreeSet productoServicios = new TreeSet();
	private HashSet dependencias = new HashSet();

	/**
	 * Setea el id de la actividad.
	 * @param integer
	 */
	public void setActividad(Integer idActividad) throws DirectorException {
		id = idActividad;
	}

	/**
	 * Setea la lista de Productos Servicios para los cuales esta actividad tiene que ser realizada.
	 * @param lista de Productos-Servicios
	 */
	public void setProductoServicios(TreeSet list) {
		productoServicios = list;
	}

	public void addProductoServicios(TreeSet list) {
		productoServicios.addAll(list);
	}

	/**
	 * Agrega al conjunto de dependencia el id de la actividad.
	 * @param actividad
	 */
	public void setDependencia(Integer dependenciaNueva) {
		if (dependenciaNueva!=null){
			dependencias.add(dependenciaNueva);
		}
	}

	/**
	 * Retorna el conjundo de id de las atividades de la cual esta actividad depende.
	 * @return
	 */
	public HashSet getDependencias() {
		return dependencias;
	}

	/**
	 * Retorna el id de la actividad
	 * @return 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Verifica si dos actividades son iguales.
	 * @return Verdadero si los id de actividad y los id de productos son iguales.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		Actividad actividadAComparar = (Actividad) obj;

		if (actividadAComparar != null && id.intValue() == actividadAComparar.id.intValue() && productoServicios.equals(actividadAComparar.productoServicios)) {
			return true;
		}
		return false;
	}

	/**
	 * Clona el objeto.
	 * @return el clon del objeto.
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() throws CloneNotSupportedException {
		Actividad a = new Actividad();
		a.id = new Integer(this.id.intValue());
		a.dependencias = (HashSet) this.dependencias.clone();
		a.productoServicios = (TreeSet) this.productoServicios.clone();
		return a;
	}

	/**
	 * @return
	 */
	public TreeSet getProductoServicios() {
		return productoServicios;
	}

	public String toString() {
		StringBuffer texto = new StringBuffer();
		texto.append(codigo);
		texto.append("(");
		for (Iterator iterator2 = productoServicios.iterator(); iterator2.hasNext();) {
			ProductoServicio ps = (ProductoServicio) iterator2.next();
			texto.append(ps.toString());
			texto.append(",");
		}
		texto.deleteCharAt(texto.length() - 1);
		texto.append(")");
		return texto.toString();
	}

	public boolean esVacio() {
		if (id != null) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param integer
	 */
	public void setCodigo(String integer) {
		codigo = integer;
	}

	/**
	 * @param integer
	 */
	public void setDescripcion(String integer) {
		descripcion = integer;
	}

}
