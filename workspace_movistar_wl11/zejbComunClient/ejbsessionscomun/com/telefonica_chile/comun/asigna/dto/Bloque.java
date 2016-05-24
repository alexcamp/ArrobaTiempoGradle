/*
 * Created on Feb 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ParametroLocal;
import co.com.telefonica.atiempo.ejb.eb.ParametroLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;

import com.tecnonautica.utiles.basicos.FechaUtil;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.entities.bloque.TipoBloque;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
import com.telefonica_chile.comun.ps.session.ProductoServicioSessionLocal;
import com.telefonica_chile.comun.ps.session.ProductoServicioSessionLocalHome;

public class Bloque implements Serializable{
	
	private Long 		bloqueId;
	private Integer 	bloqueJornadaId;
	private Long 		bloqueUsuarioId;
	private Date 		bloqueHoraInicio;
	private Date 		bloqueHoraTermino;
	private Character 	bloqueTipo;
	private double 		bloqueMinutos;
		
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(Bloque.class);
	
	public Long getBloqueId() {
		return bloqueId;
	}

	public Integer getBloqueJornadaId() {
		return bloqueJornadaId;
	}
	
	public Long getBloqueUsuarioId() {
		return bloqueUsuarioId;
	}
	
	public Date getBloqueHoraInicio() {
		return bloqueHoraInicio;
	}
	
	public Date getBloqueHoraTermino() {
		return bloqueHoraTermino;
	}
	
	public double getBloqueMinutos() {
		return bloqueMinutos;
	}
	
	public Character getBloqueTipo() {
		return bloqueTipo;
	}
	
	public void setBloqueId(Long bloqueId) {
		this.bloqueId = bloqueId;
	}

	public void setBloqueJornadaId(Integer bloqueJornadaId) {
		this.bloqueJornadaId = bloqueJornadaId;
	}

	public void setBloqueUsuarioId(Long bloqueUsuarioId) {
		this.bloqueUsuarioId = bloqueUsuarioId;
	}

	public void setBloqueHoraInicio(Date bloqueHoraInicio) {
		this.bloqueHoraInicio = bloqueHoraInicio;
	}

	public void setBloqueFechaTermino(Date bloqueHoraTermino) {
		this.bloqueHoraTermino = bloqueHoraTermino;
	}
	
	public void setBloqueTipo(Character bloqueTipo) {
		this.bloqueTipo = bloqueTipo;
	}
	
	/**
	 * 
	 * @param bloqueId
	 * @param bloqueJornadaId
	 * @param bloqueUsuarioId
	 * @param bloqueHoraInicio
	 * @param bloqueHoraTermino
	 * @param bloqueTipo
	 */
	public Bloque(Long bloqueId, Integer bloqueJornadaId, Long bloqueUsuarioId, Date bloqueHoraInicio, Date bloqueHoraTermino, Character bloqueTipo) {
		this.bloqueId = bloqueId;
		this.bloqueJornadaId = bloqueJornadaId;
		this.bloqueUsuarioId = bloqueUsuarioId;
		this.bloqueHoraInicio = bloqueHoraInicio;
		this.bloqueHoraTermino = bloqueHoraTermino;
		this.bloqueTipo = bloqueTipo;
		this.bloqueMinutos = diffMinutos(bloqueHoraInicio, bloqueHoraTermino);
	}
	
	
	
	/**
	 * 
	 * @param arregloPs
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 */

	public ArrayList busquedaProductoServicio(ArrayList arregloPs) throws RemoteException, NamingException, CreateException{
			//Acceso a Session remoto para obtener Datos
			ProductoServicioSessionLocal ejbPsRemoto = getProductoServicioSession();
			ArrayList listadoProductos = new ArrayList(); 
			for (int i = 0; i < arregloPs.size(); i++) {
				ProductoDTO  objProducto = new ProductoDTO();		
				Long idPs = new Long(arregloPs.get(i).toString());								 
				objProducto = (ProductoDTO) ejbPsRemoto.recuperarPS(idPs);
				listadoProductos.add(objProducto);			
			}
			return listadoProductos;
	}
	
	
	
	/**
	 * RECIBE EN BLOQUE TOTAL DE LA JORNADA Y EL BLOQUE A GRABAR
	 * @param bloqueJornada
	 * @param bloqueCompromiso
	 * @return
	 */
	public static List retornaParticionBloque(Bloque bloqueJornada, Bloque bloqueCompromiso) {
		
			//Listado Bloques de retorno
			List bloquesParticion = new ArrayList();
			
			Date intervalo1 = new Date();
			Date intervalo2 = new Date();
			boolean flag = true;
			/* TRAMO JORNADA */
			Date fechaInicio 	= bloqueJornada.getBloqueHoraInicio();
			Date fechaFinal 	= bloqueJornada.getBloqueHoraTermino();
			
			while ( (fechaInicio.before(fechaFinal)) && (flag) ) {
				
				intervalo1 = fechaInicio;				
				intervalo2 = bloqueCompromiso.getBloqueHoraInicio();				
				
				if(intervalo1.before(intervalo2)){					
					Bloque bloqueChico = new Bloque(
													bloqueJornada.getBloqueId(),
													bloqueJornada.getBloqueJornadaId(),
													bloqueJornada.getBloqueUsuarioId(),
													intervalo1,
													intervalo2,
													TipoBloque.JORNADA
													);
					bloquesParticion.add(bloqueChico);					
				}
					flag = false;
					fechaInicio = bloqueCompromiso.getBloqueHoraTermino();				
			}
			//queda jornada disponible aún para anexar como Bloque Jornada
			if(fechaInicio.before(fechaFinal)){
				Bloque bloqueLibre = new Bloque(
												bloqueJornada.getBloqueId(),
												bloqueJornada.getBloqueJornadaId(),
												bloqueJornada.getBloqueUsuarioId(),
												fechaInicio,
												fechaFinal,
												TipoBloque.JORNADA);
												
					bloquesParticion.add(bloqueLibre);					 
			}
			
			return bloquesParticion;

	}
	
	
	/**
	 * 
	 * @param arregloPs
	 * @return
	 */
	public Set comparaPsHabilidad(ArrayList arregloPs){
			Set setHabilidad = new HashSet(); 
			for (int i = 0; i < arregloPs.size(); i++) {
				ProductoDTO  objProducto = new ProductoDTO();						 
				objProducto = (ProductoDTO)arregloPs.get(i);
				if(objProducto.getIdHabilidad()!=0){
					setHabilidad.add(new Integer(objProducto.getIdHabilidad()));
				}		
			}
		return setHabilidad;
	}
	
		
	/**Acceso a Session Remoto*/
	private ProductoServicioSessionLocal getProductoServicioSession() throws NamingException, RemoteException, CreateException  {
			ProductoServicioSessionLocalHome  psRemotoEJB =(ProductoServicioSessionLocalHome) HomeFactory.getHome(ProductoServicioSessionLocalHome.JNDI_NAME);
			ProductoServicioSessionLocal ejbPsRemoto = null;
			ejbPsRemoto = psRemotoEJB.create();			
			return (ejbPsRemoto);
	}	
	
	
	/**
	 * Calcula la diferencia entre las fechas entregadas que vienen en
	 * milisegundos y la convierte a minutos.
	 * @param fIni
	 * @param fFin
	 * @return La diferencia en minutos entre ambas fechas.
	 */
	private double diffMinutos(Date fIni, Date fFin) {
		double diff = (double) (fFin.getTime() - fIni.getTime()) / (1000 * 60);
		if (diff < 0)
			diff = 0;
		return diff;
	}
		
	/**
	* RETORNA UNA LISTA DE BLOQUES AGENDADOS O BLOQUEADOS
	* ASIGNADOS AL USUARIO PARA UNA FECHA ESPECIFICA
	* @param Long idUsuario, Date fechaInicio, Date fechaTermino
	* @return ArrayList bloquesAsignados
	*/
   public static ArrayList recuperaBloquesAsignados(Long idUsuario, Date fechaInicio, Date fechaTermino) {
	   ArrayList bloquesAsignados = new ArrayList();

//	   Date tramoInicio = new Date();
//	   Date tramoFinal  = new Date();
//	   Character bloqOcup = new Character ('O');
//		
//	   BloqueLocalHome  home;
//	   try {
//		   home = (BloqueLocalHome)
//		   HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//	   } catch (NamingException e) {
//		   throw new NestedRuntimeException("Problemas recuperando jndi " + BloqueLocalHome.JNDI_NAME, e);
//	   }
//	   Collection bloquesOut = null;
//	   try {
//	   bloquesOut = home.findBloquesAsignados(idUsuario,fechaInicio,fechaTermino);
//	   if (log.isDebugEnabled())
//		   log.debug("Bloques Ocupados encontrados: " + bloquesOut.size());
//		   } catch (FinderException e) {
//		   log.info("No se encontraron items Bloques Ocupados : " + e.getMessage());
//	   return bloquesAsignados;
//	   }
//		
//	   for (Iterator it = bloquesOut.iterator(); it.hasNext(); ) {
//		   BloqueLocal bloqueEjb = (BloqueLocal) it.next();
//		   Long bloqId = (Long) bloqueEjb.getPrimaryKey();
//		
//
//		   if (log.isDebugEnabled())
//			   log.debug("BLOQUE_ID OCUPADO: <" + bloqId + "> HORA INICIO : [" + 
//			   FechaUtil.formatoFecha(bloqueEjb.getFechaInicio(), "H:mm") 
//					   + "]" + " HORA TERMINO [" + 
//			   FechaUtil.formatoFecha(bloqueEjb.getFechaTermino(),"H:mm") 
//			   + "]");
//		   /*
//			* bloque(Integer bloqueId, Integer bloqueJornId, Integer bloqueTecnId, Date bloqueFechaInicio, Date bloqueFechaTermino, Character bloqueTipo)
//			*/
//		   bloquesAsignados.add(new Bloque(
//					bloqId,
//					bloqueEjb.getIdJornada(),
//					bloqueEjb.getIdUsuario(),
//					bloqueEjb.getFechaInicio(),
//					bloqueEjb.getFechaTermino(),
//					bloqOcup)
//	   );
//	   }
		   return bloquesAsignados;
   }

   


	   /**
		* ORDENA DE MENOR  A MAYOR POR HORA DE INICIO LOS BLOQUES
		* RETORNA UNA LISTA ORDENADA
		* @param ArrayList bloques
		* @return ArrayList bloques
		*/
	
   public static ArrayList ordenaBloquesPorHoraInicio(ArrayList bloques) {
	 
	   int elementos 			= bloques.size();
	   int minHoraInicio  		= 0;
	   int minHoraInicio2 		= 0;
	   Date horaInicioBloque 	= new Date();
	   Date horaInicioBloque2 	= new Date();
			
	   Bloque bloqTemp 		= null;
	   /*
		* 	ALGORITMO
		* 	for (i=0;i < (elementos-1);i++) {
		*		for (j= (i + 1); j < = (elementos-1) ; j++) {
		*			if numeros[j] > numeros[i] {
		*  			tmp = numeros[i];
		*  			numeros[i] = numeros[j];
		*  			numeros[j] = tmp;
		*			}
		*  	}
		* }
		* 
		*/
	   for(int i=0;i <  (elementos-1) ; i++) {
								
		   for(int j = (i +1) ;j <= (elementos - 1) ; j ++) {
			
			Bloque bloqueJornada 	= (Bloque) bloques.get(i);
			horaInicioBloque 		= bloqueJornada.getBloqueHoraInicio();
			minHoraInicio 			= (Integer.parseInt(FechaUtil.formatoParaFecha(horaInicioBloque,"H"))*60) +
										   Integer.parseInt(FechaUtil.formatoParaFecha(horaInicioBloque,"mm"));
			
			Bloque bloqueJornada2 	= (Bloque) bloques.get(j);
			horaInicioBloque2 		= bloqueJornada2.getBloqueHoraInicio();
			minHoraInicio2 			= (Integer.parseInt(FechaUtil.formatoParaFecha(horaInicioBloque2,"H"))*60) +
									   Integer.parseInt(FechaUtil.formatoParaFecha(horaInicioBloque2,"mm"));
															
			if (minHoraInicio2 < minHoraInicio) {
				   bloqTemp = bloqueJornada;
				   bloques.set(i,bloqueJornada2);
				   bloques.set(j,bloqTemp);
			   }
		   }
	   }
	   return bloques;
   }

   /**
	* BUSCA ESPACIO DISPONIBLE EN BLOQUES DE JORNADA POR USUARIO A ASIGNAR
	* @param bloquesJornada
	* @param peticionAsignar
	* @return peticionLista
	*/
   public static peticion buscaEspacioBloque(peticion peticionAsignar) {
	   peticion peticionLista = null;
			
	   int nroUsuarios 	= 0;
	   int nroBloquesJornadas 	= 0;	
	   int nroBloques 		= 0;	
	   Date horaInicio  	= new Date();
	   Date horaTermino 	= new Date();
	   Date fechaInicio 	= new Date();
	   Date fechaTermino 	= new Date();
	   String fecha 		= null;
		double minutos = 0;
		String  minFin    = "0000";
		String  minInicio = "0000";	
		
		
	   ArrayList listaUsuarios				= new ArrayList();
	   ArrayList listaJornadasPorUsuario 	= new ArrayList();
	   ArrayList bloquesDeLaJornada 		= new ArrayList();
	   ArrayList bloquesAsignados 			= new ArrayList();
		
	   nroUsuarios 		= peticionAsignar.getPeticionUsuarios().size();
	   listaUsuarios 	= peticionAsignar.getPeticionUsuarios();
	   fecha			= peticionAsignar.getPeticionFecha();
		
	   if (log.isDebugEnabled())
		   log.debug("FECHA JORNADA: [ " + fecha + "]");
			
	   for(int i=0;i <  nroUsuarios ; i++) {
		   /* para cada usuario disponible */
		jornadaDelUsuario usuariosJornada = (jornadaDelUsuario) listaUsuarios.get(i);
		   /* recupera una lista de jornadas por usuario */
		listaJornadasPorUsuario = jornada.recuperaBloquesJornadaLibres(usuariosJornada.getJornUsuId(),fecha);
		listaJornadasPorUsuario = ordenaBloquesPorHoraInicio(listaJornadasPorUsuario);
		nroBloquesJornadas = listaJornadasPorUsuario.size();
		for(int j=0;j <  nroBloquesJornadas ; j++) {
			   /* para cada Bloque jornada del usuario disponible */
			   
				   Bloque bloqueJornada = (Bloque) listaJornadasPorUsuario.get(j);

				   horaInicio  = bloqueJornada.getBloqueHoraInicio();
				   horaTermino = bloqueJornada.getBloqueHoraTermino();
					
				   fechaInicio 	= FechaUtil.stringADateTime(fecha+" "+FechaUtil.formatoParaFecha(horaInicio,"H:mm:ss.00"));
				   fechaTermino = FechaUtil.stringADateTime(fecha+" "+FechaUtil.formatoParaFecha(horaTermino,"H:mm:ss.00"));
					
				   if (log.isDebugEnabled())
											   log.debug("HORA INICIO: [ " + 
						FechaUtil.formatoParaFecha(fechaInicio,"yyyy-MM-dd H:mm:ss.00") 
											   + " ] HORA TERMINO : [" + 
						FechaUtil.formatoParaFecha(fechaTermino,"yyyy-MM-dd H:mm:ss.00") 
											   + "] --> TECNICO :" + bloqueJornada.getBloqueUsuarioId());
						
				   /* VERIFICA QUE LOS BLOQUES DE LA JORNADA POSEAN ESPACIO PARA LA PETICION */	

		
					minInicio = FechaUtil.formatoParaFecha(fechaInicio , "Hmmss");
					minFin    = FechaUtil.formatoParaFecha(fechaTermino , "Hmmss");
					minutos   = FechaUtil.convierteHHMMaMM(minFin) - FechaUtil.convierteHHMMaMM(minInicio);
				   
				   log.debug("MINUTOS(Double): "+minutos);
					log.debug("MINUTOS (intValue): "+new Double(minutos).intValue());
				   log.debug("PeticionAsignar.getPeticionTimeActuacion: "+peticionAsignar.getPeticionTimeActuacion().intValue());
				   if (new Double(minutos).intValue() >= peticionAsignar.getPeticionTimeActuacion().intValue()) {
						peticionAsignar.setPeticionIdRolAsignado(bloqueJornada.getBloqueUsuarioId());
						peticionAsignar.setPeticionIdJornada(bloqueJornada.getBloqueJornadaId());
						peticionAsignar.setPeticionIdBloqueJornada(bloqueJornada.getBloqueId());
						peticionAsignar.setPeticionTimeAsignacion(fechaInicio);
					    return peticionAsignar;
				   }
		 }
		}
	  return peticionAsignar;
   }
   
   public static Bloque MezclaBloque(ArrayList bloquesDisponibles){
   	
   		Bloque bloqueLimite;
		Date intervaloInferior = new Date();
		Date intervaloSuperior = new Date();
   		
   		for (Iterator iter = bloquesDisponibles.iterator(); iter.hasNext();) {
			Bloque element = (Bloque) iter.next();
			if(element.getBloqueHoraInicio().before(intervaloInferior)){
				intervaloInferior = element.getBloqueHoraInicio();
			}
			if(element.getBloqueHoraTermino().after(intervaloSuperior)){
				intervaloSuperior = element.getBloqueHoraTermino();
			}
		}
		//Bloque Limite ...
		bloqueLimite = new Bloque(new Long(0),null,null,intervaloInferior,intervaloSuperior,TipoBloque.JORNADA);
		return (bloqueLimite);
   }
   
   
   public static peticion grabarJornadaAdminSistema(Date horaInicio,Date horaTermino,String numeroPeticion,ArrayList usuariosHabiles) throws CreateException, NamingException, FinderException{
		return null;
   }
   
   
   public static peticion grabarJornadaSupervisor(Date horaInicio,Date horaTermino,String numeroPeticion,ArrayList usuariosHabiles) throws CreateException, NamingException, FinderException{
   		
//		int flag = 0;		
//		int i = 0;
//		BloqueLocalHome home = (BloqueLocalHome) HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//		Long idSupervisor;
//		Long idBloque;
		peticion  objPeticion = new peticion();
//		objPeticion.setPeticionResultado(false);
//		UsuarioLocal usuarioEjb;
//		
//		while ( (i<usuariosHabiles.size()) && (flag==0)) {
//			//Obtengo Usuario 			
//			usuario element = (usuario)usuariosHabiles.get(i);
//			//Obtengo Supervisor del Usuario
//			idSupervisor = element.getUsuarioIdSupervisor();			
//			/*Creo un nuevo Bloque de tiempo de tipo para el Supervisor de Técnico)**/
//			idBloque = Boletero.getInstance().obtenerTicket("Bloque");
//			home.create(idBloque,getUsuario(idSupervisor),null,horaInicio,horaTermino,TipoBloque.AGENDADO,new Long(numeroPeticion));
//			objPeticion.setPeticionTimeAsignacion(horaInicio);
//			objPeticion.setPeticionTimeTermino(horaTermino);
//			objPeticion.setPeticionId(new Long(numeroPeticion));
//			objPeticion.setPeticionResultado(true);		
//			usuarioEjb = getUsuario(element.getUsuarioId());
//				if(usuarioEjb!=null){
//					objPeticion.setPeticionNombreUsuario(usuarioEjb.getLogin());
//				}			
//			flag = 1;
//			i++;			
//		}
	return (objPeticion);
   }
   
   public static peticion grabarJornadaAdminSistema(Date horaInicio, Date horaTermino, String numeroPeticion) throws NamingException, CreateException, FinderException {
		
//		int flag = 0;		
//		int i = 0;
		peticion  objPeticion = new peticion();		
//		objPeticion.setPeticionResultado(false);
//		BloqueLocalHome home = (BloqueLocalHome) HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//		ParametroLocal parametroLocal = getParametro();
//		Long idAdminSistema = new Long(parametroLocal.getValor());
//		Long idBloque = Boletero.getInstance().obtenerTicket("Bloque");
//		home.create(idBloque,getUsuario(idAdminSistema),null,horaInicio,horaTermino,TipoBloque.AGENDADO,new Long(numeroPeticion));
//		objPeticion.setPeticionTimeAsignacion(horaInicio);
//		objPeticion.setPeticionTimeTermino(horaTermino);
//		objPeticion.setPeticionId(new Long(numeroPeticion));
//		objPeticion.setPeticionResultado(true);		
//		UsuarioLocal usuarioEjb = getUsuario(idAdminSistema);
//			if(usuarioEjb!=null){
//				objPeticion.setPeticionNombreUsuario(usuarioEjb.getLogin());
//			}
		return (objPeticion);		
   }
   
   public static peticion grabarJornada(Date horaInicio,Date horaTermino,String numeroPeticion,ArrayList usuariosHabiles) throws FinderException, NamingException, CreateException{
		
//		int flag = 0;		
//		int i = 0;
//		BloqueLocalHome home = (BloqueLocalHome) HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//		Long idBloque;
		peticion  objPeticion = new peticion();
//		objPeticion.setPeticionResultado(false);
//		UsuarioLocal usuarioEjb; 
//		
//		while ( (i<usuariosHabiles.size()) && (flag==0)) {
//				
//				usuario element = (usuario)usuariosHabiles.get(i);				
//				Collection listadoJornadaDisponible = verificarJornada(element.getUsuarioId(),horaInicio,horaTermino);
//				if(listadoJornadaDisponible.size()>0){
//					BloqueLocal bloqueEjb = (BloqueLocal)listadoJornadaDisponible.iterator().next();
//					 
//					//Se Crea  Bloque Jornada
//					Bloque bloqueJornada = new Bloque(null,null,null,bloqueEjb.getFechaInicio(),bloqueEjb.getFechaTermino(),TipoBloque.JORNADA);
//					//Se crea Bloque Compromiso
//					Bloque bloqueCompromiso = new Bloque (null,null,null,horaInicio,horaTermino,TipoBloque.JORNADA);
//					//Se parte la Jornada del Usuario 
//					List listadoParticion = Bloque.retornaParticionBloque(bloqueJornada,bloqueCompromiso);
//					for (Iterator iter = listadoParticion.iterator();iter.hasNext();) {
//						//Creo la nueva Jornada restandole la del Compromiso
//						Bloque elementBloque = (Bloque) iter.next();
//						idBloque = Boletero.getInstance().obtenerTicket("Bloque");
//						home.create(idBloque,bloqueEjb.getUsuarios(),bloqueEjb.getJornadas(),elementBloque.getBloqueHoraInicio(),elementBloque.getBloqueHoraTermino(),TipoBloque.JORNADA,null);						
//					}
//					//Creada la Particion de la Jornada se crea el Bloque Compromiso
//					idBloque = Boletero.getInstance().obtenerTicket("Bloque");
//						home.create(idBloque,bloqueEjb.getUsuarios(),bloqueEjb.getJornadas(),bloqueCompromiso.getBloqueHoraInicio(),bloqueCompromiso.getBloqueHoraTermino(),TipoBloque.AGENDADO,new Long(numeroPeticion));
//					//Eliminar el Bloque Original					
//						peticion.eliminaBloqueJornada((Long)bloqueEjb.getPrimaryKey());
//						objPeticion.setPeticionTimeAsignacion(bloqueCompromiso.getBloqueHoraInicio());
//						objPeticion.setPeticionTimeTermino(bloqueCompromiso.getBloqueHoraTermino());
//						objPeticion.setPeticionId(new Long(numeroPeticion));
//						objPeticion.setPeticionResultado(true);
//						flag=1;
//						usuarioEjb = getUsuario(element.getUsuarioId());
//							if(usuarioEjb!=null){
//								objPeticion.setPeticionNombreUsuario(usuarioEjb.getLogin());
//							}					
//				}
//		i++;			
//		}
//		
		return objPeticion;
   }
   
   public static boolean verificarDisponibilidad(Date horaInicio,Date horaTermino,ArrayList usuariosHabiles) throws FinderException, NamingException {
		
		   int flag = 0;		
		   int i = 0;
		   boolean disponibilidad = false; 
		
		   while ( (i<usuariosHabiles.size()) && (flag==0)) {				
				   usuario element = (usuario)usuariosHabiles.get(i);				
				   Collection listadoJornadaDisponible = verificarJornada(element.getUsuarioId(),horaInicio,horaTermino);
				   if(listadoJornadaDisponible.size()>0){
				   		flag=1;
				   		disponibilidad = true;					   					
				   }
		   i++;			
		   }
		
		   return disponibilidad;
	  }
   
   public static Collection verificarJornada (Long idUsuario , Date horaInicio , Date horaTermino) throws FinderException, NamingException{
//		BloqueLocalHome home = (BloqueLocalHome)HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);									
//		Collection listJornada = home.findBusquedaJornada(idUsuario,horaInicio,horaTermino);
//		return listJornada;
		return new ArrayList();
   }
   
   public static UsuarioLocal getUsuario(Long idUsuario) throws NamingException, CreateException, FinderException {
	   UsuarioLocalHome home = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);									
	   UsuarioLocal usuarioEntity = home.findByPrimaryKey(new UsuarioKey(idUsuario));
	   return usuarioEntity;	
   }

   public static ParametroLocal getParametro() throws NamingException, CreateException, FinderException {
		  ParametroLocalHome home = (ParametroLocalHome)HomeFactory.getHome(ParametroLocalHome.JNDI_NAME);									
		  ParametroLocal parametroEntity = home.findBuscaValor("USUARIO_DEFAULT");
		  return parametroEntity;	
	}


   
   
   
   



}

