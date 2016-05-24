package com.telefonica_chile.director.algoritmo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.FlujoKey;
import co.com.telefonica.atiempo.ejb.eb.Flujo_cambio_productoLocal;
import co.com.telefonica.atiempo.ejb.eb.Flujo_cambio_productoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Flujo_prod_serv_oper_comLocal;
import co.com.telefonica.atiempo.ejb.eb.Flujo_prod_serv_oper_comLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.director.dto.ProductoServicio;
import com.telefonica_chile.vpistbba.servicios.dto.Actividad;

/**
 * Bean implementation class for Enterprise Bean: CalculaFlujo
 */
public class CalculaFlujo implements Serializable {
	private TreeSet productos = new TreeSet();
	private boolean mutacion = false;
	private Long peticion = null;
	private boolean noActividadMDF = true;
	private Flujo flujoUnido = null;
	private HashMap contextoFiltros = null;

	private ArrayList listaPsFlujoCP = new ArrayList();
	private ArrayList listaPsFlujoNormal = new ArrayList();
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Calcula y/o Recuperar de la base de datos el Flujo que se debe utilizar para Provisionar e instalar la Peticion dada.
	 * @return  Flujo a utilizar.
	 */
	public Flujo calcularNuevoFlujo() throws DirectorException {
		HashMap listaFlujos = new HashMap();
		flujoUnido = new Flujo();
		ProductoServicio ps = null;

		try {
			//Verificar si existe un flujo definido para esa Combinacion
			if ( !flujoUnido.setFlujoCombinado(productos) ) {

				Integer flujoNulo = new Integer (VpistbbaConfig.getVariable("ID_FLUJO_NULO"));
				//log.debug("Flujo Nulo: " + flujoNulo);

				Flujo_prod_serv_oper_comLocalHome flujoProdServOperComLocalHome =
					(Flujo_prod_serv_oper_comLocalHome) HomeFactory.getHome(
					Flujo_prod_serv_oper_comLocalHome.JNDI_NAME);

				boolean flujoOK = true;
				boolean revisaFlujo = false;
				boolean cambioProducto = false;
				ProductoServicio psCambioAlta = null;
				ProductoServicio psCambioBaja = null;

				Exception miE = null;
				String msg = "";
				
				// Generamos los Listados para Los Flujos.
				// Lista Normal.
				// Lista Cambio Producto.
				generarListasPSFlujo();

				//log.debug("Tamaño lista Ps Normal:" + listaPsFlujoNormal.size());
				//log.debug("Tamaño lista Ps Cambio Producto:" + listaPsFlujoCP.size());
				// Ahora Revisamos el FLujo Normal.				
				for ( int i=0; i<listaPsFlujoNormal.size(); i++) {
					ps = (ProductoServicio) listaPsFlujoNormal.get(i);
					revisaFlujo = true;

					//log.debug("Buscando Flujo [" + ps.getIdProductoServicio() + "," + ps.getIdOperacionComercial() + "]");
					try {
						Flujo_prod_serv_oper_comLocal flujoProdServOperCom =
							flujoProdServOperComLocalHome
								.findByProdServOperCom(ps.getIdProductoServicio(), ps.getIdOperacionComercial());

						Integer flujoPS = flujoProdServOperCom.getFluj_id();
						if (flujoPS != flujoNulo) {
							if ( !listaFlujos.containsKey(flujoPS) ) {
								TreeSet pss = new TreeSet();
								pss.add(ps);
								listaFlujos.put(flujoPS, pss);
							} else {
								((TreeSet) listaFlujos.get(flujoPS)).add(ps);
							}
						}

					} catch (FinderException e1) {
						flujoOK = false;
						msg += "SIN FLUJO [PET,PS,OC] ["
							+ peticion + "," + ps.getIdProductoServicio() + ","
							+ ps.getIdOperacionComercial() + "]\n";
						miE = e1;
					}
				}
				for ( int i=0; i<listaPsFlujoCP.size(); i++) {
					ps = (ProductoServicio) listaPsFlujoCP.get(i);
					//El ps de origen es el PS que doy de baja
					Integer flujoPS = getFlujoCambioProducto( ps, ps.getPsBajaCP());
					if ( flujoPS==null ) {
						revisaFlujo = true;
						flujoOK = false;
						msg += "SIN FLUJO CP [PET,PSB,OCB,PSA,OCA] ["
						+ peticion
						+ ","
						+ ps.getPsBajaCP().getIdProductoServicio() + "," + ps.getPsBajaCP().getIdOperacionComercial()
						+ ","
						+ ps.getIdProductoServicio() + "," + ps.getIdOperacionComercial()
						+ "]\n";
						
					} else if (flujoPS != flujoNulo) {
						if ( !listaFlujos.containsKey(flujoPS) ) {
							TreeSet pss = new TreeSet();
							pss.add( ps );
							pss.add( ps.getPsBajaCP() );
							listaFlujos.put(flujoPS, pss);
						} else {
							TreeSet pss = (TreeSet) listaFlujos.get(flujoPS);
							pss.add( ps );
							pss.add( ps.getPsBajaCP() );
							listaFlujos.put( flujoPS, pss );
						}
					}
				}

				if (revisaFlujo && !flujoOK) {
					log.error(msg);
					throw new DirectorException(msg, miE);
				}

				if (listaFlujos.size() == 1) {
					//Solo es el flujo el que se ocupa, por lo que solo se debe recuperar 
					//este de la base de datos
					Set keys = listaFlujos.keySet();
					for (Iterator iter = keys.iterator(); iter.hasNext();) {
						Integer element = (Integer) iter.next();
						flujoUnido.setFlujo(
							element,
							(TreeSet) listaFlujos.get(element));
						
					}
					log.debug("Flujos RESULTANTE" + flujoUnido);

				} else {
					//No se encontro Flujo combinado en la base
					//Calcular el flujo combinado con la euristica.
					Flujos flujos = new Flujos(listaFlujos);
					log.debug("Flujos a UNIR:\n" + flujos);
					flujoUnido = flujos.unirFlujos();
					//TODO: REVISAR ESTO
					//flujoUnido.guardaFlujoCombinado();
					log.debug("Flujos RESULTANTE" + flujoUnido);
				}

			}

			if (peticion != null) {
				this.grabaFlujo();
			}
		} catch (NamingException e) {
			log.fatal(
				"No encontro el home de la Tabla FlujoProdServOperCom ",
				e);
			throw new DirectorException(
				"No encontro el home de la Tabla FlujoProdServOperCom ",
				e);
		} catch (CreateException e) {
			log.fatal("No se puedo crear un Objeto ParametroSession", e);
			throw new DirectorException(
				"No se puedo crear un Objeto ParametroSession",
				e);

		}

		return flujoUnido;
	}

	/**
	 * 
	 */
	private void generarListasPSFlujo() {
		ProductoServicio ps = null;
		ArrayList listaAlta = new ArrayList();
		ArrayList listaBaja = new ArrayList();
				
		for (Iterator iter = productos.iterator(); iter.hasNext();) {
			ps = (ProductoServicio) iter.next();
			log.debug("Buscando PS [" + ps.getIdProductoServicio() + "," + ps.getIdOperacionComercial() + "," + ps.getTipoOperacionComercial() + "," + ps.getCodigoTipoPC() + "]");

			// Vemos si es Cambio de Producto. Si no lo es, al listado Normal.
			String tipoOC = ps.getTipoOperacionComercial();
			if ( tipoOC==null )
				tipoOC = "";

			if ( tipoOC.equals( VpistbbaConfig.getVariable("TIPO_OC_CP_ALTA") ) )
				listaAlta.add( ps );
			else if ( tipoOC.equals( VpistbbaConfig.getVariable("TIPO_OC_CP_BAJA") ) )
				listaBaja.add( ps );
			else
				listaPsFlujoNormal.add( ps );
		}
		
		log.debug("Tamaño Lista ALTA CP: " + listaAlta.size());
		log.debug("Tamaño Lista Baja CP: " + listaBaja.size());
		log.debug("Tamaño Lista Normal: " + listaPsFlujoNormal.size());
		
		// Ahora buscamos los que realmente son Cambio de Producto, es decir, que estan pareados.
		// Primero buscamos
		ProductoServicio psBaja = null;
		HashMap hashBaja = new HashMap();
		boolean esPsPC = false;
		for (int i=0; i<listaAlta.size(); i++) {
			ps = (ProductoServicio) listaAlta.get(i);
			esPsPC = false;
			for (int j=0; j<listaBaja.size(); j++) {
				psBaja = (ProductoServicio) listaBaja.get(j);
				if ( ps.getIdProductoServicio().equals(psBaja.getIdProductoServicio()) ) {
					ps.setPsBajaCP( psBaja );
					hashBaja.put(psBaja.getIdProductoServicio() + "_" + ps.getIdOperacionComercial(), "1");
					break;
				} else if ( ps.getCodigoTipoPC()!=null && !ps.getCodigoTipoPC().equals("") && ps.getCodigoTipoPC().equals( psBaja.getCodigoTipoPC() ) ) {
					ps.setPsBajaCP( psBaja );
					esPsPC = true;
					hashBaja.put(psBaja.getIdProductoServicio() + "_" + ps.getIdOperacionComercial(), "1");
					break;
				} //CR20948 Altamira modicacion para CP - AT-1865  - Pablo L - 29/04/2009 (Para generar flujo para ps de altamira)
				 else if(ps.getCodigoTipoPC() != null && ps.getCodigoTipoPC().equals("PLAN-CTA") && ps.getCodigoTipoPC().equals( psBaja.getCodigoTipoPC() )){//@idrincon date 16/12/2010 error flujo - se agrego ultima validacion
					ps.setPsBajaCP( psBaja );
					esPsPC = true;
					hashBaja.put(psBaja.getIdProductoServicio() + "_" + ps.getIdOperacionComercial(), "1");
					break;
					}
//				Fin CR20948
			}
			if ( ps.getPsBajaCP() == null )
				listaPsFlujoNormal.add( ps );
			else if ( esPsPC )
				listaPsFlujoCP.add( ps );
		}

		// Ahora revisamos que ninguna Baja se quede afuera.
		for (int j=0; j<listaBaja.size(); j++) {
			psBaja = (ProductoServicio) listaBaja.get(j);
			if ( !hashBaja.containsKey(psBaja.getIdProductoServicio() + "_" + ps.getIdOperacionComercial()) )
				listaPsFlujoNormal.add( psBaja );
		}
		


	}

	/**
	 * @param psCambioAlta
	 * @param psCambioBaja
	 * @return
	 */
	private Integer getFlujoCambioProducto(ProductoServicio psCambioAlta, ProductoServicio psCambioBaja) {

		//El Ps de origen es el PS que estoy dando de BAJA
		Long idPsOrigen = psCambioBaja.getIdProductoServicio();
		Long idPsDestino = psCambioAlta.getIdProductoServicio(); 
		try {
			Flujo_cambio_productoLocalHome fcpHome = (Flujo_cambio_productoLocalHome)  HomeFactory.getHome(Flujo_cambio_productoLocalHome.JNDI_NAME);
			Collection c = fcpHome.finderByPsOrigenPsDestino(idPsOrigen, idPsDestino);
			Flujo_cambio_productoLocal fcpLocal = null;
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				fcpLocal = (Flujo_cambio_productoLocal) it.next();
				FlujoKey flKey = (FlujoKey) fcpLocal.getFlujo().getPrimaryKey();
				return flKey.fluj_id;
			}
		} catch (NamingException e) {
		} catch (FinderException e) {
		}

		return null;
	}


	/**
	 * @param list
	 */
	public void setProducto(ProductoServicio producto) {
		productos.add(producto);
	}

	/**
	 * SOLO PARA PROTOTIPO BORRAR DESPUES o colocarla como private para ser llamada 
	 * desde buscarDatosPeticion.
	 * Setea el dato noActividadMDF del cual depende el resultado final del flujo.
	 * @param noActividadMDF 
	 */
	public void setNoActividadMDF(boolean noActividadMDF) {
		this.noActividadMDF = noActividadMDF;
	}

	/**
	 * SOLO PARA PROTOTIPO BORRAR DESPUES o colocarla como private para ser llamada 
	 * desde buscarDatosPeticion.
	 * Setea el dato Mutacion del cual depende el resultado final del flujo.
	 * @param mutacion
	 */
	public void setMutacion(boolean mutacion) {
		this.mutacion = mutacion;
	}

	private void grabaFlujo() throws DirectorException {
		boolean esCRE = false;
		if (flujoUnido == null) {
			return;
		}
			try {
				Peticion_flujoLocalHome peticionFlujoLocalHome =
					(Peticion_flujoLocalHome) HomeFactory.getHome(
					Peticion_flujoLocalHome.JNDI_NAME);
					Peticion_flujoKey pfk= new Peticion_flujoKey();
				try {
					Collection peticionFlujo =
						peticionFlujoLocalHome.findByIdPeticion(peticion);
					for (Iterator iter = peticionFlujo.iterator();
						iter.hasNext();
						) {
							Peticion_flujoLocal element =
							(Peticion_flujoLocal) iter.next();
							Actividad_flujoKey afk= (Actividad_flujoKey) element.getFk_acti_2_pefl().getPrimaryKey();								
						log.debug(
							"REMOVE???????????????  actividad ="
								+ afk.acti_id);
						element.remove();
					}
				} catch (FinderException e) {
					log.debug("Error al buscar: ");
					//Esta ok para ingresar el flujo de la peticion
				}
				DBManager manager=new DBManager();
				manager.setDataSource(DBManager.JDBC_VPISTBBA);
				for (Iterator iter = flujoUnido.iterator(); iter.hasNext();) {
					Actividad actividad = (Actividad) iter.next();
					if (actividad.getId()!=null){
						for (Iterator iterator =
							actividad.getProductoServicios().iterator();
							iterator.hasNext();
							) {
							if(actividad.getId().equals(ComunInterfaces.ACTIVIDAD_DESISTALAR_CRE)){
								log.debug("Entro a validar el tipo localidad");
								esCRE = validaLocalidad();
								if(!esCRE){
									obtenerMotorizado(actividad);
								}
							}
								
							ProductoServicio element =
								(ProductoServicio) iterator.next();
//--------------------Solucion Peticiones Sin Flujo Dcardena 15/02/2015---------------------------
//se re valida que elo correlativo no se repita al momneto de insertarse en la tabla peticion_flujo
							boolean existeCore=true;
							//se valida que el correlativo no exista
							Integer pefl_id=new Integer(0);
							while(existeCore){
								//se genera correlativo
								long id=manager.seqNextValLong("ATIEMPO.CORRELATIVO_PETICION_FLUJO"); 
								pefl_id=new Integer(new Long(id).intValue());
								log.debug("El correlativo generado para grabar flujo es: "+ pefl_id +" para la peticion "+peticion);
								//try donde se maneja la exepcion del find y el create en la tabla peticion_flujo
								try{
								Peticion_flujoKey key = new Peticion_flujoKey(pefl_id);
								Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
								Peticion_flujoLocal peticion_flujoLocal = peticion_flujoLocalHome.findByPrimaryKey(key);
								log.debug("El correlativo generado ya exste en la tabla peticion flujo: "+ pefl_id +" para la peticion "+peticion+" se calcula nuevo correlativo");
								}catch (FinderException e){
								//no se encontro correlativo puede continuar y salir de while
									existeCore=false;	
								}catch (Exception e){
									
								}
							}
							//try pra controlar en caso q halla error en el create 
							boolean hayError=true;
							
							while(hayError)
							try{
							Peticion_flujoLocal peticionFlujo =
								peticionFlujoLocalHome.create(pefl_id,
									peticion,
									element.getIdProductoServicio(),
									element.getIdOperacionComercial(),
									actividad.getId(),
									new Integer(
										flujoUnido.getPasoActividad(actividad)));
							
							hayError=false;	
							log.debug("Se genero flujo con exito: "+pefl_id+" para la peticion "+peticion);
							}catch (CreateException e) {
								// TODO Bloque catch generado automáticamente
								log.debug("Error al crear registro en la tabla peticion flujo para la peticion: "+peticion+" error: "+e);
								long id=manager.seqNextValLong("ATIEMPO.CORRELATIVO_PETICION_FLUJO"); 
								pefl_id=new Integer(new Long(id).intValue());
								log.debug("Se genera nuevo correlativo: "+pefl_id+" para le peticion "+peticion+" error: "+e);
							}
//--------------------------------fin solucion-----------------------------------------------------------------	
						}
					}

				}
				
				
				//REALIZA EL ULTIMO ORDEN DEL FLUJO, DE ACUERDO A LA PRIORIDAD QUE TIENEN LAS ACTIVIDADES
				
				try {
					Collection peticionFlujoOrden =
						peticionFlujoLocalHome.findByPeticionOrden(peticion);
					Integer anterior = null;
					Integer orden = new Integer(-1);
					for (Iterator iter = peticionFlujoOrden.iterator();
						iter.hasNext();
						) {
						Peticion_flujoLocal element =
							(Peticion_flujoLocal) iter.next();
						log.debug(
							"comparacion: "
								+ element.getIdActividad()
								+ " con "
								+ anterior);
						if (!element.getIdActividad().equals(anterior)) {
							anterior = element.getIdActividad();
							orden = new Integer(orden.intValue() + 1);
							log.debug("orden: " + orden);
							element.setPefl_orden(orden);
						} else {
							log.debug("orden: " + orden);
							element.setPefl_orden(orden);
						}
					}
				} catch (FinderException e) {
					log.debug("No hay Actividades con la Peticion: " + peticion, e);
				}
			} catch (EJBException e) {
				
				log.debug("Error al buscar: "+e);
			} catch (NamingException e) {
				
				log.debug("Error al buscar: "+e);
			} catch (RemoveException e) {
				
				log.debug("Error al buscar: "+e);
			} 

	}

	/**
	 * @param actividad
	 */
	private void obtenerMotorizado(Actividad actividad) {
		// TODO Apéndice de método generado automáticamente
		
		try {
			Actividad_flujoLocalHome actHome  = (Actividad_flujoLocalHome)HomeFactory.getHome (Actividad_flujoLocalHome.JNDI_NAME);
			Actividad_flujoLocal actLocal = actHome.findByPrimaryKey(new Actividad_flujoKey(ComunInterfaces.ACTIVIDAD_DESISTALAR_MOTORIZADO));
			log.debug("Entro a setiar Motorizado. ");
			actividad.setActividad(ComunInterfaces.ACTIVIDAD_DESISTALAR_MOTORIZADO);
			actividad.setCodigo(actLocal.getActi_codigo());
			actividad.setDescripcion(actLocal.getActi_descripcion());
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al guardar: "+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al guardar: "+e);
		} catch (DirectorException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al guardar: "+e);
		}
	}

	/**
	 * @param peticion2
	 * @return
	 */
	private boolean validaLocalidad() {
		try {
			// TODO Apéndice de método generado automáticamente
			PeticionLocalHome petInfoHome = (PeticionLocalHome)HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
			PeticionLocal  petInfoLocal = petInfoHome.findByPrimaryKey(new PeticionKey(peticion));
			//LocalidadLocalHome localidadHome = (LocalidadLocalHome)HomeFactory.getHome (LocalidadLocalHome.JNDI_NAME) ;
			LocalidadLocal localidad = petInfoLocal.getFk_01_localidad();
			log.debug("valido la localidad: "+localidad.getNombre_localidad());
			String tipo_loc[]=localidad.getCre().split(",");
			for(int i=0;i<tipo_loc.length;i++){
				if(tipo_loc[i]==null)
					return true;
				else{
					if(tipo_loc[i].equals("1")||tipo_loc[i].equals("3"))
						return true;
				}
			}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error instanciando el EJB: "+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al buscar: "+e);
		}
		return false;
	}

	/**
	 * @param integer
	 */
	public void setPeticion(Long integer) {
		peticion = integer;
	}

	/**
	 * @param map
	 */
	public void setContextoFiltros(HashMap map) {
		contextoFiltros = map;
	}
}