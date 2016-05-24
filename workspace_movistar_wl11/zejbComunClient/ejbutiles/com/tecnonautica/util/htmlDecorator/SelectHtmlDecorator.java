/*
 * Created on Mar 18, 2005
 *
 */
package com.tecnonautica.util.htmlDecorator;

/**
 * @author Pa-T!, el ídolo pop por antonomasia (Richard Andreu)
 *
 */
public class SelectHtmlDecorator extends HtmlDecorator {

	private String[] options;
	private String[] values;
	private String onchange = "";
	
	/**
	 * constructor de un objeto Select HTML
	 * @param nombre tag "name"
	 * @param options arreglo de nombres a desplegar dentro del SelectBox
	 * @param values identificadores asociados a los nombres
	 */
	public SelectHtmlDecorator(String nombre, String[] options, String[] values) {
		super(nombre, HtmlDecorator.TIPO_SELECT);
		this.options = options;
		this.values = values;
	}

	/**
	 * constructor de un objeto Select HTML, con una opción preseleccionada
	 * @param nombre tag "name"
	 * @param options arreglo de nombres a desplegar dentro del SelectBox
	 * @param values identificadores asociados a los nombres
	 * @param valor identificador de la opción preseleccionada
	 */
	public SelectHtmlDecorator(String nombre, String[] options, String[] values, String valor) {
		this(nombre, options, values);
		this.valor = valor;		
	}

	public SelectHtmlDecorator(String nombre, String[] options, String[] values, boolean editable) {
		super(nombre, HtmlDecorator.TIPO_SELECT);
		this.options = options;
		this.values = values;
		this.editable = editable;
	}
	
	/**
	 * constructor de un objeto Select HTML, con una opción preseleccionada, permite o no edición, y tiene asociado un método JavaScript para el evento onChange
	 * @param nombre tag "name"
	 * @param options arreglo de nombres a desplegar dentro del SelectBox
	 * @param values identificadores asociados a los nombres
	 * @param editable boolean que indica si se puede editar o no
	 * @param onchange método JS que maneja el evento onChange
	 */
	public SelectHtmlDecorator(String nombre, String[] options, String[] values, boolean editable, String onchange) {
		this(nombre, options, values, editable);
		this.onchange = onchange;
	}

	public SelectHtmlDecorator(String nombre, String[] options, String[] values, String valor, boolean editable) {
		this(nombre, options, values);
		this.valor = valor;
		this.editable = editable;		
	}
	
	/**
	 * constructor de un objeto Select HTML, que permite o no edición, y que tiene asociado un método JavaScript para el evento onChange
	 * @param nombre tag "name"
	 * @param options arreglo de nombres a desplegar dentro del SelectBox
	 * @param values identificadores asociados a los nombres
	 * @param valor identificador de la opción preseleccionada
	 * @param editable boolean que indica si se puede editar o no
	 * @param onchange método JS que maneja el evento onChange
	 */
	public SelectHtmlDecorator(String nombre, String[] options, String[] values, String valor, boolean editable, String onchange) {
		this(nombre, options, values, valor, editable);
		this.onchange = onchange;		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		int optionsLength = (this.options != null) ? this.options.length : 0;
		if (this.editable) {
			String onchange = "";
			if (!this.onchange.equals("")) {
				onchange = " onchange=\"" + this.onchange + "\"";
			}
			StringBuffer html = new StringBuffer("<select name=\"" + this.nombre + "\" id=\"" + this.nombre + "\"" + onchange + ">\n");
			for (int i = 0; i < optionsLength; i++) {
				String option = this.options[i];
				String value = this.values[i];
				String selected = "";
				if (value.equals(this.valor)) {
					selected = "selected";
				}
				html.append("\t<option value=\"" + value +"\" " + selected + ">" + option + "</option>\n");
			}
			html.append("</select>\n");
			return html.toString();
		} else {
			for (int i = 0; i < optionsLength; i++) {
				if (this.values[i].equals(this.valor)) {
					return this.options[i];
				}
			}
			return "";
		}
	}

}
