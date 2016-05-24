package com.telefonica_chile.comun.asigna.session;
import java.util.Collection;
import java.util.HashMap;
/**
 * Local interface for Enterprise Bean: AsignaSession
 */
public interface AsignaSessionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * agendarPeticion
	 */
//	public com.telefonica_chile.comun.asigna.dto.peticion agendarPeticion(
//		java.util.Date horaInicio,
//		java.util.Date horaTermino,
//		java.lang.String numeroPeticion,
//		java.util.ArrayList usuariosHabiles)
//		throws
//			javax.ejb.FinderException,
//			javax.naming.NamingException,
//			javax.ejb.CreateException;
//	/**
//	 * asignarParametros
//	 */
//	public com
//		.telefonica_chile
//		.comun
//		.asigna
//		.dto
//		.parametrosParaFiltrar asignarParametros(
//			java.lang.String sistema,
//			java.lang.String codActividad,
//			java.lang.String tipoTrabajo,
//			java.lang.String TICA,
//			java.lang.String codSeg,
//			java.lang.String PComercial,
//			java.lang.String ptoVta,
//			java.lang.String agencia,
//			java.lang.String modNeg,
//			java.util.ArrayList arrayPS);
//	/**
//	 * asignarParametros
//	 */
//	public com
//		.telefonica_chile
//		.comun
//		.asigna
//		.dto
//		.parametrosParaFiltrar asignarParametros(
//			java.lang.String codPCO,
//			java.lang.String tipSVA,
//			java.lang.String ptoVta,
//			java.lang.String codAct,
//			java.lang.String modNeg,
//			java.lang.String tipUsu,
//			java.lang.String retEqp,
//			java.lang.String codSeg,
//			java.lang.String TICA,
//			java.lang.String agencia,
//			java.lang.String tipoTrabajo,
//			java.lang.String PComercial);
//	/**
//	 * asignarPeticion
//	 */
//	public com.telefonica_chile.comun.asigna.dto.peticion asignarPeticion(
//		com.telefonica_chile.comun.asigna.dto.peticion peticionOk);
//	/**
//	 * ordenaAgrupaJornadasUsuarios
//	 */
//	public java.util.ArrayList ordenaAgrupaJornadasUsuarios(
//		java.util.ArrayList jornadasUsuarios);
//	/**
//	 * recuperaHabilidadesDelRol
//	 */
//	public java.util.ArrayList recuperaHabilidadesDelRol(
//		java.lang.Long idRol,
//		com.telefonica_chile.comun.asigna.dto.parametrosParaFiltrar parametros);
//	/**
//	 * recuperaRolPorActividad
//	 */
//	public com
//		.telefonica_chile
//		.comun
//		.asigna
//		.dto
//		.rolActividad recuperaRolPorActividad(java.lang.String codActividad);
	/**
	 * recuperaUsuariosConHabilidades
	 */
//	public java.util.ArrayList recuperaUsuariosConHabilidades(
//		java.util.ArrayList habilidadesFiltro);
	/**
	 * recuperaJornadasUsuarios
	 */
//	public java.util.ArrayList recuperaJornadasUsuarios(
//		java.util.ArrayList listaUsuarios,
//		java.lang.String fechaActuacion);
//	/**
//	 * recuperaUsuariosHabilidadesRol
//	 */
//	public java.util.ArrayList recuperaUsuariosHabilidadesRol(
//		java.util.ArrayList usuariosConHabilidades,
//		java.lang.Long idRol);
//	/**
//	 * recuperaBloqueMezclado
//	 */
//	public com.telefonica_chile.comun.asigna.dto.Bloque recuperaBloqueMezclado(
//		java.util.ArrayList bloquesDisponibles);
//	/**
//	 * recuperaIdAgencia
//	 */
//	public java.lang.Long recuperaIdAgencia(java.lang.String codAgencia)
//		throws javax.naming.NamingException, javax.ejb.FinderException;
//	/**
//	 * busquedaFechaMinimaMaxima
//	 */
//	public com
//		.telefonica_chile
//		.comun
//		.asigna
//		.dto
//		.FiltroDiaDTO busquedaFechaMinimaMaxima(
//			java.util.ArrayList listadoFamiliasPs,
//			com.telefonica_chile.vpistbba.servicios.dto.FlujoDto flujoDto,
//			java.lang.Long grupoSegmentoId)
//		throws javax.naming.NamingException, javax.ejb.FinderException;
//	/**
//	 * recuperarFechaMinMax
//	 */
//	public com
//		.telefonica_chile
//		.comun
//		.asigna
//		.dto
//		.FiltroDia recuperarFechaMinMax(
//		java.lang.String codigoAgencia,
//		java.lang.String tica,
//		java.lang.String puntoVenta,
//		java.lang.String segmento);
//	/**
//	 * recuperaBloquesDisponibles
//	 */
//	public java.util.ArrayList recuperaBloquesDisponibles(
//		java.util.ArrayList usuarios,
//		com.telefonica_chile.comun.asigna.dto.FiltroDia objFiltroDia)
//		throws
//			javax.naming.NamingException,
//			javax.ejb.CreateException,
//			javax.ejb.FinderException;
//	/**
//	 * busquedaFamiliaSegmento
//	 */
//	public java.lang.Long busquedaFamiliaSegmento(java.lang.Long idSegmento)
//		throws javax.naming.NamingException, javax.ejb.FinderException;
//	/**
//	 * verificarDisponibilidadHoraria
//	 */
//	public boolean verificarDisponibilidadHoraria(
//		java.util.Date horaInicio,
//		java.util.Date horaTermino,
//		java.util.ArrayList usuariosHabiles)
//		throws javax.ejb.FinderException, javax.naming.NamingException;
//	/**
//	 * busquedaFamiliaPs
//	 */
//	public java.util.ArrayList busquedaFamiliaPs(java.util.ArrayList arregloPs)
//		throws
//			javax.ejb.FinderException,
//			javax.naming.NamingException,
//			javax.ejb.CreateException;
	/**
	 * agendarPeticionSupervisor
	 */
//	public com
//		.telefonica_chile
//		.comun
//		.asigna
//		.dto
//		.peticion agendarPeticionSupervisor(
//		java.util.Date horaInicio,
//		java.util.Date horaTermino,
//		java.lang.String numeroPeticion,
//		java.util.ArrayList usuariosHabiles)
//		throws
//			javax.ejb.FinderException,
//			javax.naming.NamingException,
//			javax.ejb.CreateException;
	/**
	 * agendarPeticionAdminSistema
	 */
//	public com
//		.telefonica_chile
//		.comun
//		.asigna
//		.dto
//		.peticion agendarPeticionAdminSistema(
//			java.util.Date horaInicio,
//			java.util.Date horaTermino,
//			java.lang.String numeroPeticion)
//		throws
//			javax.ejb.FinderException,
//			javax.naming.NamingException,
//			javax.ejb.CreateException;
	/**
	 * recuperaSupervisorPorDefecto
	 */
//	public java.lang.Long recuperaSupervisorPorDefecto(
//		java.lang.String usuarioPorDefecto)
//		throws javax.naming.NamingException, javax.ejb.FinderException;
	/**
	 * getUsuarioAsignado
	 */
//	public java.lang.String getUsuarioAsignado(
//		java.lang.String numeroPeticion,
//		java.lang.String codPCO,
//		java.lang.String tipSVA,
//		java.lang.String ptoVta,
//		java.lang.String codAct,
//		java.lang.String modNeg,
//		java.lang.String tipUsu,
//		java.lang.String retEqp,
//		java.lang.String codSeg,
//		java.lang.String TICA,
//		java.lang.String agencia,
//		java.lang.String tipoTrabajo,
//		java.lang.String PComercial);
	/*
	 * Entrega el usuario que puede atender la peticion
	 *
	 * codAct : Codigo de la actividad
	 * hab : HashMap con las Habilidades a considerar.
	 */
	public Long getIdUsuario(String codAct, HashMap habAct, Long idAplicacion, String idPetiNum);
	public Collection getUsuariosCompatibles(
		Long idUser,
		String codAct,
		Long idAplicacion,
		HashMap habilidades);
}
