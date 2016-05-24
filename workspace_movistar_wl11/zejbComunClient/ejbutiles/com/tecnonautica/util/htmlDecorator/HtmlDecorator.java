/*
 * Created on Mar 18, 2005
 *
 */
package com.tecnonautica.util.htmlDecorator;

import com.tecnonautica.utiles.basicos.StringUtil;

/**
 * @author Pa-T!, el ídolo pop por antonomasia (Richard Andreu)
 *
 */
public class HtmlDecorator {


	public static final int TIPO_TEXT = 0;
	public static final int TIPO_SELECT = 1;
	public static final int TIPO_CHECKBOX = 2;
	public static final int TIPO_RADIO = 3;
	public static final int TIPO_PASSWORD = 4;
	public static final int TIPO_TEXTAREA = 5;
	
	
	private static final String[] tipos = new String[]{"text", "select", "checkbox", "radio", "password","textarea"};
	
	private int tipo;
	private int cols;
	private int rows;
	protected boolean editable = false;
	private boolean visible = true;
	protected String valor;
	protected String nombre;
	protected boolean readOnly = false;
	private int width = 20;
	private int size = 0;
	
	/*** Mejora asociada a Alta Fibra Optica para el manejo de limites de caracteres en ingreso de datos. (MaxLength)
	*/	
	private int MaxLength = 0;
	
	public HtmlDecorator(String nombre, boolean editable) {
		this.tipo = HtmlDecorator.TIPO_TEXT;
		this.nombre = nombre;
		this.editable = editable;
	}
	
	public HtmlDecorator(String nombre, boolean editable, boolean readOnly) {
		this(nombre, editable);
		this.readOnly = readOnly;
	}
	
	public HtmlDecorator(String nombre, int tipo, boolean editable) {
		this(nombre);
		this.tipo = tipo;
		this.editable = editable;
	}
	
	public HtmlDecorator(String nombre, int tipo, boolean editable, boolean readOnly) {
		this(nombre, tipo, editable);
		this.readOnly = readOnly;
	}
	
	public HtmlDecorator(String nombre, int tipo, String valor, boolean editable) {
		this(nombre);
		this.tipo = tipo;
		this.valor = valor;
		this.editable = editable;
	}
	
	public HtmlDecorator(String nombre, int tipo, String valor, boolean editable, boolean readOnly) {
		this(nombre, tipo, valor, editable);
		this.readOnly = readOnly;
	}

	public HtmlDecorator(String nombre, int tipo, int cols, int rows, String valor, boolean editable) {
		this(nombre);
		this.tipo = tipo;
		this.cols = cols;
		this.rows = rows;
		this.valor = valor;
		this.editable = editable;
	}
	
	public HtmlDecorator(String nombre, int tipo, int cols, int rows, String valor, boolean editable, boolean readOnly) {
		this(nombre, tipo, cols, rows, valor, editable);
		this.readOnly = readOnly;
	}
	
	public HtmlDecorator(String nombre, String valor, boolean editable) {
		this(nombre);
		this.valor = valor;
		this.editable = editable;
	}
	
	public HtmlDecorator(String nombre, String valor, boolean editable, boolean readOnly) {
		this(nombre, valor, editable);
		this.readOnly = readOnly;
	}
	
	/**
	 * 
	 */
	public HtmlDecorator(String nombre) {
		this.tipo = HtmlDecorator.TIPO_TEXT;
		this.nombre = nombre;
	}
	
	public HtmlDecorator(String nombre, int tipo) {
		this(nombre);
		this.tipo = tipo;
	}
	
	public HtmlDecorator(String nombre, int tipo, String valor) {
		this(nombre);
		this.tipo = tipo;
		this.valor = valor;
	}
	
	public HtmlDecorator(String nombre, String valor) {
		this(nombre);
		this.valor = valor;
	}
	
	public HtmlDecorator(String nombre, String valor, boolean editable, int size) {
			this(nombre);
			this.valor = valor;
			this.editable = editable;
			this.size = size;
		}
	
	/*** Mejora asociada a Alta Fibra Optica para el manejo de limites de caracteres en ingreso de datos.
	*/
	public HtmlDecorator(String nombre, int tipo, int MaxLength, String valor, boolean editable) {
		this(nombre);
		this.tipo = tipo;
		this.MaxLength = MaxLength;		
		this.valor = valor;
		this.editable = editable;		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String readOnlyStr = (readOnly) ? " readonly=\"readonly\"" : "";
			
		if(this.tipo == HtmlDecorator.TIPO_TEXTAREA) {
			if(this.editable) {
				return "<textarea name=\"" + this.nombre + "\" cols ="+this.cols+" rows="+ this.rows + readOnlyStr + ">"+ StringUtil.sinNull(this.valor)+ "</textarea>";
			} else {
				return StringUtil.sinNull(this.valor);
			}
		} else {
			if(this.size > 0) {
				if(this.editable) {
					if(this.MaxLength == 0) {
						return "<input name=\"" + this.nombre + "\" id=\"" + this.nombre + "\" type=\"" + HtmlDecorator.tipos[this.tipo] + "\" value=\"" + StringUtil.sinNull(this.valor) + "\"" + readOnlyStr + " width=\"" + this.width + "\" size=\""+ this.size + "\" >";
					} else {
						return "<input name=\"" + this.nombre + "\" id=\"" + this.nombre + "\" type=\"" + HtmlDecorator.tipos[this.tipo] + "\" value=\"" + StringUtil.sinNull(this.valor) + "\"" + readOnlyStr + " width=\"" + this.width + "\" size=\""+ this.size + "\"" + " MaxLength=\"" + this.MaxLength + "\" >";
					}
					
				} else {
					return StringUtil.sinNull(this.valor);
				}
					}
			else {
				if(this.editable) {
					if(this.MaxLength == 0) {
						return "<input name=\"" + this.nombre + "\" id=\"" + this.nombre + "\" type=\"" + HtmlDecorator.tipos[this.tipo] + "\" value=\"" + StringUtil.sinNull(this.valor) + "\"" + readOnlyStr + " width=\"" + this.width + "\" >";						
					} else {
						return "<input name=\"" + this.nombre + "\" id=\"" + this.nombre + "\" type=\"" + HtmlDecorator.tipos[this.tipo] + "\" value=\"" + StringUtil.sinNull(this.valor) + "\"" + readOnlyStr + " width=\"" + this.width + "\"" + " MaxLength=\"" + this.MaxLength + "\" >";						
					}						

				} else {
					return StringUtil.sinNull(this.valor);
				}
			}		
		}
	}

	/**
	 * @return
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param string
	 */
	public void setValor(String string) {
		valor = string;
	}

	/**
	 * @param b
	 */
	public void setEditable(boolean b) {
		editable = b;
	}

	/**
	 * @param i
	 */
	public void setWidth(int i) {
		width = i;
	}

}
