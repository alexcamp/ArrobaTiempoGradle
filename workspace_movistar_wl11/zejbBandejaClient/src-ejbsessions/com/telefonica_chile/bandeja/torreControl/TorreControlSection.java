/*
 * La clase que contiene una matriz de datos asociados con dos listas de agencia y actividades
 */
package com.telefonica_chile.bandeja.torreControl;

/**
 * @author Lai Chun-Hau
 */
public class TorreControlSection {
	private String[] agencias; //lista de agencias asociadas
	public String[] actividades; //lista de actividades asociadas

	private Object datos[][]; //Arreglo que contendra los datos de la cruzada
	//Instancia la agencia con la lista de agencias existentes	
	public TorreControlSection(String[] agencias, int cant) {
		this.agencias = agencias;
		datos = new Object[this.agencias.length + 1][cant + 1];
	}
	/*-----------------------------------------------------------------------------------------------------*/
	//Actualizar el contenido de datos con el elemento correspondiente
	//d.posicion indica el orden de la actividad correspondiente	
	public void setElemento(DatosGlobalTorreControl d) {
		datos[this.getPosicionAgencia(d.agen_desc)][this.getPosicionActividad(
			d.act_desc)] =
			d;
	}

	//	metodo que retorna la posicion de la actividad existente
	public int getPosicionActividad(String n) {
		for (int i = 0; i < actividades.length; i++)		
			  if (actividades[i].equalsIgnoreCase(n))
				  return i+1;
		return -1;
	}

	//metodo que retorna la posicion de la agencia existente
	public int getPosicionAgencia(String n) {
		for (int i = 0; i < agencias.length; i++)
			if (agencias[i].equalsIgnoreCase(n))
				return i + 1;
		return -1;
	}

	//metodo que completen los cabezales
	private void llenarCabezales() {
		for (int i = 1; i < datos.length; i++)
			datos[i][0] = this.agencias[i - 1];

		for (int i = 1; i < datos[0].length; i++)
			datos[0][i] = this.actividades[i - 1];

		datos[0][0] = "Agencia";
	}

	public Object[][] componerTabla() {
		this.llenarCabezales();
		return this.datos;
	}
}
