/*
 * Created on Feb 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.torreControl;

/**
 * @author Lai Chun-Hau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DatosGlobalTorreControl {
	public Object datos[][];
	public String agen_id, act_id,agen_desc, act_desc, tipo_trabajo, segmento, familiaPs;
	public int cant=0;
	public int posicionArreglo=0;
	public int seccion=0;
	

	public DatosGlobalTorreControl(Object d[][]){
		datos = d;
		//elementos1 = datos;
	}
	public DatosGlobalTorreControl(
		String ag_id,
		String ag_desc,
		String ac_id,
		String ac_desc,
		String tipo_trabajo,
		String segmento,
		String familiaPs,
		String count) {
			
		
			this.agen_id = ag_id;
			this.agen_desc = ag_desc;
			this.act_id = ac_id;
			this.act_desc = ac_desc;
			this.tipo_trabajo = tipo_trabajo;
			this.segmento = segmento;
			this.familiaPs = familiaPs;
			try{
				this.cant = Integer.parseInt(count);
			}catch(NumberFormatException e){}
			
			
		
	}

	public DatosGlobalTorreControl(
		String ag_id,
		String ag_desc,
		String ac_id,
		String ac_desc,
		String count) {
			
		
			this.agen_id = ag_id;
			this.agen_desc = ag_desc;
			this.act_id = ac_id;
			this.act_desc = ac_desc;
			try{
				this.cant = Integer.parseInt(count);
			}catch(NumberFormatException e){}
			
			
		
	}
}
