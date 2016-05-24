/*
 * Created on 08-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.actividades;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface IActividadEJB {
//	public String getNombreEstructuraDatos();
//	public String getIdTemplateWf();
//	public Long getNumeroPeticion();
//	public Integer getIdActividadFlujo();
//	public String getCodigoActividad();
//	public String getActImplCorrelID();
	
	public ActividadEJBDTO getActividadEJBDTO(Long NumeroPeticion,String codigoActividad, String actImplCorreID, String entradaXML) throws TnProcesoExcepcion;
	public ActividadEJBDTO getActividadEJBDTO(Long NumeroPeticion,String codigoActividad) throws TnProcesoExcepcion;
	
//	public void cargaActividadEJB()throws TnProcesoExcepcion;
//	public void setInfo(String idTemplateWf, Long NumeroPeticion,String codigoActividad, String actImplCorreID) throws TnProcesoExcepcion;
//	public void setActImplCorrelID(String id);
//	public void setObservacion(String obs);
//	public void setIdUserCierre(Long idUuser);
//	public void setIdCausaCierre(Long idCausa);	
//	public void iniciar() throws TnProcesoExcepcion;
//	public void terminar() throws TnProcesoExcepcion;
//	public void grabarSinTerminar() throws TnProcesoExcepcion;
////	public void setDato(String llave, String valor);
//	public void cambiarUsuario(Long idUser)throws TnProcesoExcepcion;;
//	public String getDato(String llave);
//	public String getXMLDatos();
//	public Map getDatos();

	public void iniciar(ActividadEJBDTO act) throws TnProcesoExcepcion;
	public void terminar(ActividadEJBDTO act) throws TnProcesoExcepcion;
	public void grabarSinTerminar(ActividadEJBDTO act) throws TnProcesoExcepcion;
//	public void setDato(String llave, String valor);
	public void cambiarUsuario(ActividadEJBDTO act,Long idUser)throws TnProcesoExcepcion;


}
