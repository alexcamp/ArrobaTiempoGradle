package com.telefonica_chile.atiempo.javaWf;
/**
 * Remote interface for Enterprise Bean: WFSession
 */
public interface WFSession extends javax.ejb.EJBObject {
	/**
	 * iniciarActividad
	 */
//	public void iniciarActividad(
//		com.telefonica_chile.atiempo.actividades.IActividad actividad)
//		throws
//			com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion,
//			java.rmi.RemoteException;
//	/**
//	 * terminarActividad
//	 */
//	public void terminarActividad(
//		com.telefonica_chile.atiempo.actividades.IActividad actividad)
//		throws
//			com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion,
//			java.rmi.RemoteException;
	/**
	 * iniciarProceso
	 */
	public void iniciarProceso(
		com.telefonica_chile.atiempo.actividades.IProceso proceso)
		throws
			com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion,
			java.rmi.RemoteException;
	/**
	 * getListaTemplates
	 */
//	public java.lang.String[] getListaTemplates()
//		throws
//			com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion,
//			java.rmi.RemoteException;
//	/**
//	 * getNombreCampos
//	 */
//	public java.lang.String[] getNombreCampos(
//		com.telefonica_chile.atiempo.actividades.IProceso proceso)
//		throws
//			com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion,
//			java.rmi.RemoteException;
//	/**
//	 * getValorCampos
//	 */
//	public java.util.Map getValorCampos(
//		com.telefonica_chile.atiempo.actividades.IProceso proceso)
//		throws
//			com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion,
//			java.rmi.RemoteException;
//	/**
//	 * getMapaDatos
//	 */
//	public java.util.Map getMapaDatos(
//		com.telefonica_chile.atiempo.actividades.IActividad actividad)
//		throws
//			com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion,
//			com
//				.telefonica_chile
//				.atiempo
//				.javaWf
//				.InstanciaActividadNoEncontradaException,
//			java.rmi.RemoteException;
//	/**
//	 * getListaActividadesTerminadas
//	 */
//	public java.util.List getListaActividadesTerminadas(
//		java.lang.String idProceso)
//		throws
//			com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion,
//			java.rmi.RemoteException;
}
