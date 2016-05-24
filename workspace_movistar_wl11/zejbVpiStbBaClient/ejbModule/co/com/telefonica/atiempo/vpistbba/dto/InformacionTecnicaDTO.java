/*
 * Created on 10-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.dto;

import java.util.ArrayList;
import java.util.Collection;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author Caudillo-Movil
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionTecnicaDTO implements DataTransferObject {

	public InformacionTecnicaDTO()
	{
		 
	}
	
	public class InfoTecnica
	{
		//Inf planta interna
		private String central;
		private Long cdCentral;
		private String telefono;
		private String len;
		private String lecCont;
		private String posicionHorizontal;

		//Inf planta externa
		private String distr;
		private String descripcion;
		private String direcDistr;
		private String liston;
		private String parListon;
		private String cable;
		private String parCable;
		private String armario;
		private String direcArmario;
		private String caja;
		private String direcCaja;
		private String parCaja;
		private String TipoCaja;
		
		//TODO: 20/10/2009 - Raúl Triviño - requerimiento 00029652
		private String estadoLinea;
 		//End TODO
		
		/*CR14657 -- Granite*/
		private String indDedicado;
		private String cdCentral_dedicada;
		private String cable_dedicado;		
		private String parCable_dedicado;
		private String armario_dedicado;
		private String direcArmario_dedicado;
		private String caja_dedicado;
		private String tipoCaja_dedicado;
		private String direcCaja_dedicado;
		private String parCaja_dedicado;
		private Integer distancia_caja;
		private String longitud;
		private String latitud;
		
		

		public Integer getDistancia_caja() {
			return distancia_caja;
		}

		public void setDistancia_caja(Integer distancia_caja) {
			this.distancia_caja = distancia_caja;
		}

		public String getLatitud() {
			return latitud;
		}

		public void setLatitud(String latitud) {
			this.latitud = latitud;
		}

		public String getLongitud() {
			return longitud;
		}

		public void setLongitud(String longitud) {
			this.longitud = longitud;
		}
		
		
		private Collection dslams=new ArrayList();
//		TODO zonasAtendimiento Inicio
		private Collection zonas =new ArrayList();
//		TODO zonasAtendimiento fin
		private String ods;
		/**
		 * @return
		 */
		public String getCentral() {
			return central;
		}

		/**
		 * @return
		 */
		public String getLecCont() {
			return lecCont;
		}

		/**
		 * @return
		 */
		public String getLen() {
			return len;
		}

		/**
		 * @return
		 */
		public String getTelefono() {
			return telefono;
		}

		/**
		 * @param string
		 */
		public void setCentral(String string) {
			central = string;
		}

		/**
		 * @param string
		 */
		public void setLecCont(String string) {
			lecCont = string;
		}

		/**
		 * @param string
		 */
		public void setLen(String string) {
			len = string;
		}

		/**
		 * @param string
		 */
		public void setTelefono(String string) {
			telefono = string;
		}

		/**
		 * @return
		 */
		public String getArmario() {
			return armario;
		}

		/**
		 * @return
		 */
		public String getCable() {
			return cable;
		}

		/**
		 * @return
		 */
		public String getCaja() {
			return caja;
		}

		/**
		 * @return
		 */
		public String getDescripcion() {
			return descripcion;
		}

		/**
		 * @return
		 */
		public String getDirecArmario() {
			return direcArmario;
		}

		/**
		 * @return
		 */
		public String getDirecCaja() {
			return direcCaja;
		}

		/**
		 * @return
		 */
		public String getDirecDistr() {
			return direcDistr;
		}

		/**
		 * @return
		 */
		public String getDistr() {
			return distr;
		}

		/**
		 * @return
		 */
		public String getListon() {
			return liston;
		}

		/**
		 * @return
		 */
		public String getParCable() {
			return parCable;
		}

		/**
		 * @return
		 */
		public String getParCaja() {
			return parCaja;
		}

		/**
		 * @return
		 */
		public String getParListon() {
			return parListon;
		}

		/**
		 * @param string
		 */
		public void setArmario(String string) {
			armario = string;
		}

		/**
		 * @param string
		 */
		public void setCable(String string) {
			cable = string;
		}

		/**
		 * @param string
		 */
		public void setCaja(String string) {
			caja = string;
		}

		/**
		 * @param string
		 */
		public void setDescripcion(String string) {
			descripcion = string;
		}

		/**
		 * @param string
		 */
		public void setDirecArmario(String string) {
			direcArmario = string;
		}

		/**
		 * @param string
		 */
		public void setDirecCaja(String string) {
			direcCaja = string;
		}

		/**
		 * @param string
		 */
		public void setDirecDistr(String string) {
			direcDistr = string;
		}

		/**
		 * @param string
		 */
		public void setDistr(String string) {
			distr = string;
		}

		/**
		 * @param string
		 */
		public void setListon(String string) {
			liston = string;
		}

		/**
		 * @param string
		 */
		public void setParCable(String string) {
			parCable = string;
		}

		/**
		 * @param string
		 */
		public void setParCaja(String string) {
			parCaja = string;
		}

		/**
		 * @param string
		 */
		public void setParListon(String string) {
			parListon = string;
		}

		/**
		 * @return
		 */
		public String getOds() {
			return ods;
		}

		/**
		 * @param string
		 */
		public void setOds(String string) {
			ods = string;
		}

		/**
		 * @return
		 */
		public Long getCdCentral() {
			return cdCentral;
		}

		/**
		 * @param long1
		 */
		public void setCdCentral(Long long1) {
			cdCentral = long1;
		}

		
		/**
		 * @return
		 */
		public Collection getDslams() {
			return dslams;
		}

		/**
		 * @param collection
		 */
		public void setDslams(Collection collection) {
			dslams = collection;
		}

		/**
		 * @return
		 */
		public String getPosicionHorizontal() {
			return posicionHorizontal;
		}

		/**
		 * @param string
		 */
		public void setPosicionHorizontal(String string) {
			posicionHorizontal = string;
		}
		
		
		/**
		 * @return Returns the caja_dedicado.
		 */
		public String getCaja_dedicado() {
			return caja_dedicado;
		}
		/**
		 * @param caja_dedicado The caja_dedicado to set.
		 */
		public void setCaja_dedicado(String caja_dedicado) {
			this.caja_dedicado = caja_dedicado;
		}
		/**
		 * @return Returns the direcArmario_dedicado.
		 */
		public String getDirecArmario_dedicado() {
			return direcArmario_dedicado;
		}
		/**
		 * @param direcArmario_dedicado The direcArmario_dedicado to set.
		 */
		public void setDirecArmario_dedicado(String direcArmario_dedicado) {
			this.direcArmario_dedicado = direcArmario_dedicado;
		}
		/**
		 * @return Returns the direcCaja_dedicado.
		 */
		public String getDirecCaja_dedicado() {
			return direcCaja_dedicado;
		}
		/**
		 * @param direcCaja_dedicado The direcCaja_dedicado to set.
		 */
		public void setDirecCaja_dedicado(String direcCaja_dedicado) {
			this.direcCaja_dedicado = direcCaja_dedicado;
		}
		/**
		 * @return Returns the parCable_dedicado.
		 */
		public String getParCable_dedicado() {
			return parCable_dedicado;
		}
		/**
		 * @param parCable_dedicado The parCable_dedicado to set.
		 */
		public void setParCable_dedicado(String parCable_dedicado) {
			this.parCable_dedicado = parCable_dedicado;
		}
		/**
		 * @return Returns the parCaja_dedicado.
		 */
		public String getParCaja_dedicado() {
			return parCaja_dedicado;
		}
		/**
		 * @param parCaja_dedicado The parCaja_dedicado to set.
		 */
		public void setParCaja_dedicado(String parCaja_dedicado) {
			this.parCaja_dedicado = parCaja_dedicado;
		}
		/**
		 * @return Returns the armario_dedicado.
		 */
		public String getArmario_dedicado() {
			return armario_dedicado;
		}
		/**
		 * @param armario_dedicado The armario_dedicado to set.
		 */
		public void setArmario_dedicado(String armario_dedicado) {
			this.armario_dedicado = armario_dedicado;
		}
		/**
		 * @return Returns the cable_dedicado.
		 */
		public String getCable_dedicado() {
			return cable_dedicado;
		}
		/**
		 * @param cable_dedicado The cable_dedicado to set.
		 */
		public void setCable_dedicado(String cable_dedicado) {
			this.cable_dedicado = cable_dedicado;
		}

/**
 * @return
 */
public Collection getZonas() {
	return zonas;
}

/**
 * @param collection
 */
public void setZonas(Collection collection) {
	zonas = collection;
}

		/**
		 * @return Returns the cdCentral_dedicada.
		 */
		public String getCdCentral_dedicada() {
			return cdCentral_dedicada;
		}
		/**
		 * @param cdCentral_dedicada The cdCentral_dedicada to set.
		 */
		public void setCdCentral_dedicada(String cdCentral_dedicada) {
			this.cdCentral_dedicada = cdCentral_dedicada;
		}
		/**
		 * @return Returns the indDedicado.
		 */
		public String getIndDedicado() {
			return indDedicado;
		}
		/**
		 * @param indDedicado The indDedicado to set.
		 */
		public void setIndDedicado(String indDedicado) {
			this.indDedicado = indDedicado;
		}
		/**
		 * @return Returns the tipoCaja_dedicado.
		 */
		public String getTipoCaja_dedicado() {
			return tipoCaja_dedicado;
		}
		/**
		 * @param tipoCaja_dedicado The tipoCaja_dedicado to set.
		 */
		public void setTipoCaja_dedicado(String tipoCaja_dedicado) {
			this.tipoCaja_dedicado = tipoCaja_dedicado;
		}
		/**
		 * @return Returns the tipoCaja.
		 */
		public String getTipoCaja() {
			return TipoCaja;
		}
		/**
		 * @param tipoCaja The tipoCaja to set.
		 */
		public void setTipoCaja(String tipoCaja) {
			TipoCaja = tipoCaja;
		}
		
	   /**
         * @return Returns the estadoLinea.
         */
        public String getEstadoLinea() {
            return estadoLinea;
        }
        /**
         * @param estadoLinea The estadoLinea to set.
         */
        public void setEstadoLinea(String estadoLinea) {
            this.estadoLinea = estadoLinea;
        }
	}

	public InfoTecnica LineaNueva = new InfoTecnica();
	public InfoTecnica LineaExistente = new InfoTecnica();


}
