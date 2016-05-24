package com.telefonica_chile.bandeja.web.acciones;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * El EAC esta en linea con el cliente y corresponde ofrecer
 * opciones para visitas de instalacion.
 * 
 * Entrada:
 * 	- Peticion
 */
public class DisponibilidadAcc extends Accion {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.tecnonautica.mvc.Accion#ejecutar(javax.servlet.http.HttpServletRequest)
	 */
	protected void ejecutar(HttpServletRequest request) throws Evento {
		
	}

//	protected void ejecutar(HttpServletRequest request) throws Evento {
//		HttpSession session = request.getSession(true);
//
//		try {
//
//			log.debug("DisponibilidadAcc...");
//			//COD_ACTIVIDAD = SIEMPRE INSTALACIÓN
//			//Verificar que busqueda sea por Id  o Codigo ...
//			String codActividad = "Terreno";
//			String numeroPeticion = request.getParameter("NP");
//			log.debug("Numero Peticion = "+ numeroPeticion);
//			String area = request.getParameter("A");
//			log.debug("Numero area = "+ area);
//			String fono = request.getParameter("F");
//			log.debug("Numero fono = "+ fono);
//			String sistema = request.getParameter("S");
//			log.debug("Numero sistema = "+ sistema);
//			String tipoTrabajo = request.getParameter("TT");
//			log.debug("Numero tipoTrabajo= "+ tipoTrabajo);
//			//String tipoTrabajo = "1";
//			String tica = request.getParameter("TC");
//			log.debug("Numero tica = "+ tica);
//			//String tica = "ALTA";
//			String segmento = request.getParameter("SG");
//			log.debug("Numero segmento = "+ segmento);
//			//String segmento = "141";
//			String plantaComercial = request.getParameter("PC")	;
//			log.debug("Numero plantaComercial = "+ plantaComercial);
//			//String plantaComercial = "PNUNOA";
//			String puntoVenta = request.getParameter("PV");
//			log.debug("Numero puntoVenta = "+ puntoVenta);
//			//String puntoVenta = "1";
//			String codigoAgencia = request.getParameter("AG");
//			log.debug("Numero codigoAgencia = "+ codigoAgencia);
//			//String codigoAgencia = "J100";
//			String modeloNegocio = request.getParameter("MN");
//			log.debug("Numero modeloNegocio = "+ modeloNegocio);
//			//String modeloNegocio = "1";
//			String respAfac = request.getParameter("RF");
//			log.debug("Numero respAfac = "+ respAfac);
//			String respApel = request.getParameter("RP");
//			log.debug("Numero respApel = "+ respApel);
//			int cantPs = Integer.parseInt(request.getParameter("CP"));
//			ArrayList arregloPs = new ArrayList();
//			for (int i = 1; i <= cantPs; i++) {
//				arregloPs.add(request.getParameter("P_" + i));
//			}
//			//Simulo parametros de URL valores de Operacion Comercial
//			log.debug("Tamaño arregloPs " + arregloPs.size());
//			log.debug("LEER ARREGLO COMERCIAL ");
//			int cantOperComercial = 3;
//			ArrayList arregloOperComercial = new ArrayList();
//			for (int i = 1; i <= cantOperComercial; i++) {
//				arregloOperComercial.add("1"); //Simulo que los 3 PS vienen con Operacion Comercial 1 = ALTA
//			}
//
//			Bloque bloqueLimite;
//			/**Filtros Asignación...**/
//			log.debug("Filtros Asignación...");
//			rolActividad rol = null;
//			ArrayList habilidadesDelRol = new ArrayList();
//			ArrayList usuariosCumpleHabilidad = new ArrayList();
//			ArrayList usuariosConHabilidadRol = new ArrayList();
//			ArrayList usuariosConHabilidadPS = new ArrayList();
//			ArrayList bloquesDisponibles = new ArrayList();
//			//Disponibilidad Previa
//			Integer disponibilidadHoraria[][] = new Integer[12][5];
//			log.debug("parametrosParaFiltrar filtroUsuario");
//			parametrosParaFiltrar filtroUsuario =
//				asignarParametros(
//					sistema,
//					codActividad,
//					tipoTrabajo,
//					tica,
//					segmento,
//					plantaComercial,
//					puntoVenta,
//					codigoAgencia,
//					modeloNegocio,
//					arregloPs);
//
//			rol = recuperaRolActividad(codActividad);
//			habilidadesDelRol = recuperaHabilidadesRol(rol.getRolId(), filtroUsuario);
//			usuariosCumpleHabilidad = recuperaUsuarioConHabilidades(habilidadesDelRol);
//			usuariosConHabilidadRol = recuperaUsuariosRol(usuariosCumpleHabilidad, rol.getRolId());
//
//			String ruta =	numeroPeticion + "&" + fono+  "&STB" +"&" + tipoTrabajo+"&" +tica + "&" +segmento;
//			ruta =          "&" +plantaComercial+"&" +puntoVenta+"&" +codigoAgencia+"&" +modeloNegocio;
//			
//			
//			//Lógica nueva de Fecha Mínima Máxima
//			log.debug("Lógica nueva de Fecha Mínima Máxima ");
//			rol = recuperaRolActividad(codActividad);
//			Long idGrupoSegmento = recuperaIdGrupoSegmento(new Long(segmento));
//			Long idAgencia = recuperaIdAgencia(codigoAgencia);
//			ArrayList listadoFamiliasPs = recuperaFamiliasPs(arregloPs);
//			//Obtener Actividades desde el Flujo  
//			FlujoDto flujoDto = recuperarActividadesSessionRemoto(arregloPs, arregloOperComercial, idAgencia);
//			FiltroDiaDTO objFiltroDia = recuperarFechaMinMax(listadoFamiliasPs, flujoDto, idGrupoSegmento);
//			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
//			SimpleDateFormat sdfHora = new SimpleDateFormat("ddMMyy HH:mm");
//			
//			if (request.getParameter("fecha") != null) {
//				try {
//					// convertir parametro "fecha" en Date
//					Date fechaMinima = sdf.parse(request.getParameter("fecha"));
//					objFiltroDia.setFechaMinimaAgenda(fechaMinima);
//				} catch (Exception ex) {
//				}
//			}
//
//			// Genero los Strings "fecha" "fecha_anterior" y "fecha_siguiente" para la vista
//			
//			Date fechaMinima = objFiltroDia.getFechaMinimaAgenda();
//			String fechaString = sdf.format(fechaMinima);
//			Date fechaLimite = objFiltroDia.getFechaMinimaLimite();
//			String fechaLim = sdfHora.format(fechaLimite);
//
//			GregorianCalendar gc = new GregorianCalendar();
//			gc.setTime(fechaMinima);
//			gc.add(GregorianCalendar.DAY_OF_YEAR, 5);
//			Date fechaSiguienteDate = gc.getTime();
//			String fechaSiguiente = sdf.format(fechaSiguienteDate);
//			
//			gc.setTime(fechaMinima);
//			gc.add(GregorianCalendar.DAY_OF_YEAR, -5);
//			Date fechaAnteriorDate = gc.getTime();
//			String fechaAnterior = sdf.format(fechaAnteriorDate);
//
//			request.setAttribute("fecha", fechaString);
//			request.setAttribute("fecha_anterior", fechaAnterior);
//			request.setAttribute("fecha_siguiente", fechaSiguiente);
//			request.setAttribute("fecha_limite", fechaLim);
//
//			//Disponibilidad Horaria 
//			//Simular datos pantalla JSP			
//			int horaInicio = 9;
//			int horaTermino = 10;
//			
//			for (int k = 0; k < 12; k++) {
//				for (int j = 0; j < 5; j++) {
//					String fecha =
//					UtilesWeb.convertirDate2String(agregaDiasDate(objFiltroDia.getFechaMinimaAgenda(), j), UtilesWeb.formatOnlyStandardDate);
//					String horaInicioStr = new Integer(horaInicio).toString() + ":00";
//					String horaTerminoStr = horaTermino + ":00";
//					String fechaInicio = fecha.trim() + " " + horaInicioStr;
//					String fechaTermino = fecha.trim() + " " + horaTerminoStr;
//
//					disponibilidadHoraria[k][j] = recuperarDisponibilidad(usuariosConHabilidadRol, fechaInicio, fechaTermino);
//				}
//				horaInicio++;
//				horaTermino++;
//			}
//			
//			MatrizDisponibilidad objMatriz = new MatrizDisponibilidad();
//			objMatriz.setDisponibilidadHoraria(disponibilidadHoraria);
//			request.setAttribute("disponibilidadHoraria", objMatriz);
//			request.setAttribute("numeroPeticion", numeroPeticion);
//			request.setAttribute("objFiltroDia", objFiltroDia);
//			session.setAttribute("usuarios", usuariosConHabilidadRol);
//
//			setResultado("exitoDisponibilidad");
//
//		} catch (Exception e) {
//			log.error("error del catch en Disponibilidad ", e);
//			request.setAttribute("msj", new String("Error al desplegar disponibilidad ..."));
//			setResultado("errorDisponibilidad");
//		}
//
//	}
//
//	/**
//	 * @param codigoAgencia
//	 * @return
//	 */
//	private Long recuperaIdAgencia(String codigoAgencia) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return (asignacion.recuperaIdAgencia(codigoAgencia));
//		} catch (Exception e) {
//			/*log.debug("ERROR :   AL RECUPERAR ID DE AGENCIA, codigo !!!> " +  codigoAgencia);
//			log.error("ERROR :  ", e);
//			throw new Evento("errorGenerico");*/
//			//Si no encuentera una agencia se le devuelve Agencia No Determinada con codigo 0000 
//			return new Long(1001);
//		}
//	}
//
//	/**
//	 * @param listadoFamiliasPs
//	 * @param actividades
//	 * @return
//	 */
//	private FiltroDiaDTO recuperarFechaMinMax(ArrayList listadoFamiliasPs, FlujoDto flujoDto, Long idGrupoSegmento) throws Evento {
//
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return (asignacion.busquedaFechaMinimaMaxima(listadoFamiliasPs, flujoDto, idGrupoSegmento));
//
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR FECHA MINIMA PARA AGENDAR!!!> ", e);
//			throw new Evento("errorGenerico");
//		}
//
//	}
//
//	/**
//	 * @param arreglo de Ps
//	 * @return Actividades
//	 */
//	private FlujoDto recuperarActividadesSessionRemoto(ArrayList arregloPs, ArrayList arregloOperComercial, Long idAgencia)
//		throws RemoteException, NamingException, CreateException {
//
//		ServicioSession servicioSession = getServicioSessionBean();
//		log.debug("ERROR :  paso por aca");
//		FlujoDto objFlujo = servicioSession.calcularNuevoFlujo(arregloPs, arregloOperComercial, idAgencia);
//		return (objFlujo);
//	}
//
//	/**
//	 * @param arregloPs
//	 * @return
//	 */
//	private ArrayList recuperaFamiliasPs(ArrayList arregloPs) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.busquedaFamiliaPs(arregloPs);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR FAMILIAS DE PS >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	/**
//	 * @param idSegmento
//	 * @return idGrupoSegmento
//	 */
//	private Long recuperaIdGrupoSegmento(Long idSegmento) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.busquedaFamiliaSegmento(idSegmento);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR GRUPO DE SEGMENTO >" + idSegmento.toString(), e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	public Date agregaDiasDate(Date fecha, int dias) {
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.setTime(fecha);
//		gc.add(GregorianCalendar.DAY_OF_YEAR, dias);
//		return (gc.getTime());
//	}
//
//	/**
//	 * @param usuariosConHabilidadRol
//	 * @param fechaInicio
//	 * @param fechaTermino
//	 * @return
//	 */
//	private Integer recuperarDisponibilidad(ArrayList usuariosConHabilidadRol, String fechaInicio, String fechaTermino)
//		throws RemoteException, NamingException, CreateException, Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			boolean valorDispo =
//				asignacion.verificarDisponibilidadHoraria(
//					UtilesWeb.convertirFecha(fechaInicio, UtilesWeb.formatStandard),
//					UtilesWeb.convertirFecha(fechaTermino, UtilesWeb.formatStandard),
//					usuariosConHabilidadRol);
//			if (valorDispo) {
//				return (new Integer(1));
//			} else {
//				return (new Integer(0));
//			}
//
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR DISPONIBILIDAD PREVIA >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	/**
//	 * @param bloquesDisponibles
//	 */
//	private Bloque mezclarBloques(ArrayList bloquesDisponibles) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.recuperaBloqueMezclado(bloquesDisponibles);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR BLOQUES DISPONIBLES >", e);
//			throw new Evento("errorGenerico");
//		}
//
//	}
//
//	/**
//	 * @param usuariosConHabilidadRol
//	 * @param objFiltroDia
//	 * @return
//	 */
//	private ArrayList recuperaBloques(ArrayList usuarios, FiltroDia objFiltroDia) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.recuperaBloquesDisponibles(usuarios, objFiltroDia);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR BLOQUES DISPONIBLES >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	/**
//	 * @param usuariosConHabilidadRol
//	 * @param arregloPs
//	 * @return
//	 */
//	private ArrayList recuperarUsuariosPs(ArrayList usuariosConHabilidad, ArrayList arregloPs) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR USUARIOS QUE CUMPLAN CON HABILIDAD + PS >", e);
//			throw new Evento("errorGenerico");
//		}
//		return null;
//	}
//
//	private rolActividad recuperaRolActividad(String codActividad) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.recuperaRolPorActividad(codActividad);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR ROL DE LA ACTIVIDAD >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	/**
//	 * RETORNA LAS HABILIDADES ASOCIADAS AL ROL CON SU VALOR
//	 * @param codActividad
//	 * @return
//	 * @throws Evento
//	 */
//	private ArrayList recuperaHabilidadesRol(Long idRol, parametrosParaFiltrar parametros) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.recuperaHabilidadesDelRol(idRol, parametros);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR HABILIDADES DEL ROL >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//	/**
//	 * RETORNA UNA LISTA DE USUARIOS QUE CUMPLEN CON UNA LISTA DE HABILIDADES-VALOR
//	 * @param habilidadesAFiltrar
//	 * @return
//	 * @throws Evento
//	 */
//	private ArrayList recuperaUsuarioConHabilidades(ArrayList habilidadesAFiltrar) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.recuperaUsuariosConHabilidades(habilidadesAFiltrar);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR USUARIOS QUE CUMPLAN CON LAS HABILIDADES >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	/**
//	 * RECUPERA USUARIOS QUE CUMPLEN CON EL ROL
//	 * @param usuariosHabilidades
//	 * @param idRol
//	 * @return
//	 * @throws Evento
//	 */
//	private ArrayList recuperaUsuariosRol(ArrayList usuariosHabilidades, Long idRol) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.recuperaUsuariosHabilidadesRol(usuariosHabilidades, idRol);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL RECUPERAR USUARIOS QUE CUMPLAN CON ROL >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	/**
//	 * @param codigoAgencia
//	 * @param tica
//	 * @param puntoVenta
//	 * @param segmento
//	 * @return FiltroDia (Fecha Min -Fecha Max - Duración Actividad)
//	 */
//	private FiltroDia recuperarFechaMinMax(String codigoAgencia, String tica, String puntoVenta, String segmento)
//		throws RemoteException, NamingException, CreateException, FinderException {
//		AsignaSessionLocal agendamiento = getAsignacion();
//		return (agendamiento.recuperarFechaMinMax(codigoAgencia, tica, puntoVenta, segmento));
//	}
//
//	private parametrosParaFiltrar asignarParametros(
//		String sistema,
//		String codActividad,
//		String tipoTrabajo,
//		String TICA,
//		String codSeg,
//		String PComercial,
//		String ptoVta,
//		String agencia,
//		String modNeg,
//		ArrayList arrayPS)
//		throws RemoteException, NamingException, CreateException {
//		AsignaSessionLocal agendamiento = getAsignacion();
//		return (agendamiento.asignarParametros(sistema, codActividad, tipoTrabajo, TICA, codSeg, PComercial, ptoVta, agencia, modNeg, arrayPS));
//	}
//
//	private AsignaSessionLocal getAsignacion() throws NamingException, RemoteException, CreateException {
//		AsignaSessionLocalHome home = (AsignaSessionLocalHome) HomeFactory.getHome(AsignaSessionLocalHome.JNDI_NAME);
//		AsignaSessionLocal local = home.create();
//		return local;
//	}
//
//	private ServicioSession getServicioSessionBean() throws NamingException, RemoteException, CreateException {
//		log.debug("Aca se cae ==> ServicioSession getServicioSessionBean");
//		ServicioSessionHome home = (ServicioSessionHome) HomeFactory.getHome(ServicioSessionHome.JNDI_NAME,HomeFactory.REMOTO_VPISTBBA);
//		ServicioSession remote = home.create();
//		return remote;
//	}

}
