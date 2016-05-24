package co.com.telefonica.atiempo.ejb.eb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

/**
 * Local interface for Enterprise Bean: Mensaje_estado_linea
 */
public interface Mensaje_estado_lineaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_familia_ps
	 */
	public java.lang.Integer getCod_familia_ps();
	/**
	 * Set accessor for persistent attribute: cod_familia_ps
	 */
	public void setCod_familia_ps(java.lang.Integer newCod_familia_ps);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public java.lang.String getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public void setFecha_envio(java.lang.String newFecha_envio);
	/**
	 * Get accessor for persistent attribute: fecha_cierre
	 */
	public java.lang.String getFecha_cierre();
	/**
	 * Set accessor for persistent attribute: fecha_cierre
	 */
	public void setFecha_cierre(java.lang.String newFecha_cierre);
	/**
	 * Get accessor for persistent attribute: cod_actividad_generadora
	 */
	public java.lang.String getCod_actividad_generadora();
	/**
	 * Set accessor for persistent attribute: cod_actividad_generadora
	 */
	public void setCod_actividad_generadora(
		java.lang.String newCod_actividad_generadora);
	/**
	 * Get accessor for persistent attribute: telefono
	 */
	public java.lang.Long getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public void setTelefono(java.lang.Long newTelefono);
	/**
	 * Get accessor for persistent attribute: area
	 */
	public java.lang.Integer getArea();
	/**
	 * Set accessor for persistent attribute: area
	 */
	public void setArea(java.lang.Integer newArea);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect2_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRespuesta_conect2_apsc();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect2_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRespuesta_conect2_apsc(
		java.util.Collection aRespuesta_conect2_apsc);
	/**
	 * This method was generated for supporting the relationship role named f_reference_13.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ConectorLocal getF_reference_13();
	/**
	 * This method was generated for supporting the relationship role named f_reference_13.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setF_reference_13(
		co.com.telefonica.atiempo.ejb.eb.ConectorLocal aF_reference_13);
	/**
	 * This method was generated for supporting the relationship role named f_reference_14.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estadoLocal getF_reference_14();
	/**
	 * This method was generated for supporting the relationship role named f_reference_14.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setF_reference_14(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal aF_reference_14);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conec6_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRespuesta_conec6_apsc_linea();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conec6_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRespuesta_conec6_apsc_linea(
		java.util.Collection aRespuesta_conec6_apsc_linea);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect4_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRespuesta_conect4_apsc();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect4_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRespuesta_conect4_apsc(
		java.util.Collection aRespuesta_conect4_apsc);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect7_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRespuesta_conect7_apsc();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect7_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRespuesta_conect7_apsc(
		java.util.Collection aRespuesta_conect7_apsc);
	/**
	 * Get accessor for persistent attribute: id_error
	 */
	public java.lang.String getId_error();
	/**
	 * Set accessor for persistent attribute: id_error
	 */
	public void setId_error(java.lang.String newId_error);
	/**
	 * Get accessor for persistent attribute: desc_error
	 */
	public java.lang.String getDesc_error();
	/**
	 * Set accessor for persistent attribute: desc_error
	 */
	public void setDesc_error(java.lang.String newDesc_error);
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRecursos_linea_basica();
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRecursos_linea_basica(
		java.util.Collection aRecursos_linea_basica);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * @return
	 */
	public Long getPeti_numero();
	/**
	 * @param long1
	 */
	public void setPeti_numero(Long long1) throws FinderException, NamingException;
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public java.lang.String getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public void setAccion(java.lang.String newAccion);
}
