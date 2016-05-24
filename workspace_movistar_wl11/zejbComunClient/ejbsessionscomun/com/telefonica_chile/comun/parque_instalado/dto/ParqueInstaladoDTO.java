/*
 * Created on Dec 28, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.parque_instalado.dto;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ParqueInstaladoDTO {

	/**
	 * 
	 */
	public ParqueInstaladoDTO() {
		super();
	}
	
	public ParqueInstaladoDTO(int i) {
		
			switch (i) {
				case 1 :
				this.ps = "PS1";
				this.familia = "Familia1";
				this.descripcion = "Descrip1";				
				break;
			
				case 2 :
				this.ps = "PS2";
				this.familia = "Familia2";
				this.descripcion = "Descrip2";
				break;
			
				case 3 :				
				this.ps = "PS3";
				this.familia = "Familia3";
				this.descripcion = "Descrip3";			
				break;
			
				case 4 :				
				this.ps = "PS4";
				this.familia = "Familia4";
				this.descripcion = "Descrip4";			
				break;

				default :
				break;
			}
		}

		/*private String idProducto = "";
		private String nombreProducto = "";*/
		private String ps = "";
		private String familia = "";
		private String descripcion = "";

		/**
		 * @return
		 */
		/*public String getIdProducto() {
			return idProducto;
		}*/

		/**
		 * @return
		 */
		/*public String getNombreProducto() {
			return nombreProducto;
		}*/

		/**
		 * @return
		 */
		public String getPs() {
			return ps;
		}

		/**
		 * @return
		 */
		public String getFamilia() {
			return familia;
		}

		/**
		 * @return
		 */
		public String getDescripcion() {
			return descripcion;
		}

		/**
		 * @param string
		 */
		/*public void setIdProducto(String string) {
			idProducto = string;
		}*/

		/**
		 * @param string
		 */
		/*public void setNombreProducto(String string) {
			nombreProducto = string;
		}*/

		/**
		 * @param string
		 */
		public void setPs(String string) {
			ps = string;
		}

		/**
		 * @param string
		 */
		public void setFamilia(String string) {
			familia = string;
		}

		/**
		 * @param string
		 */
		public void setDescripcion(String string) {
			descripcion = string;
		}
	

}
