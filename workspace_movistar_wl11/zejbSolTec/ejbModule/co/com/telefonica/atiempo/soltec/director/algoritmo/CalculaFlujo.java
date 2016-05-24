package co.com.telefonica.atiempo.soltec.director.algoritmo;

import java.io.Serializable;
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

import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.FlujoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Flujo_prod_serv_oper_comLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Flujo_prod_serv_oper_comLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.director.algoritmo.DirectorException;
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
//		ParametroSessionLocalHome ejbHome = null;
//		ParametroSessionLocal datos = null;

		try {
			//Verificar si existe un flujo definido para esa Combinacion
			if ( !flujoUnido.setFlujoCombinado(productos) ) {

//				ejbHome =
//					(ParametroSessionLocalHome) HomeFactory.getHome(
//						ParametroSessionLocalHome.JNDI_NAME);
//				datos = ejbHome.create();

				Integer flujoNulo = new Integer (STConfig.getVariable("ID_FLUJO_NULO"));
				log.debug("El flujo Nulo es: " + flujoNulo);

				Flujo_prod_serv_oper_comLocalHome flujoProdServOperComLocalHome =
					(Flujo_prod_serv_oper_comLocalHome) HomeFactory.getHome(
					Flujo_prod_serv_oper_comLocalHome.JNDI_NAME);

				boolean flujoOK = true;
				boolean revisaFlujo = false;
				Exception miE = null;
				String msg = "";
				for (Iterator iter = productos.iterator(); iter.hasNext();) {
					ps = (ProductoServicio) iter.next();
					revisaFlujo = true;

					log.debug(
						"Se calculará el Flujo con el Producto Servicio: "
							+ ps.getIdProductoServicio()
							+ " y Operacion Comercial: "
							+ ps.getIdOperacionComercial());

					try {
						Flujo_prod_serv_oper_comLocal flujoProdServOperCom =
							flujoProdServOperComLocalHome
								.findByProdServOperCom(
								ps.getIdProductoServicio(),
								ps.getIdOperacionComercial());

						FlujoKey flujoPK = (FlujoKey) flujoProdServOperCom.getFlujo().getPrimaryKey();
						Integer flujoPS = flujoPK.fluj_id;
						if (flujoPS != flujoNulo) {
							if (!listaFlujos.containsKey(flujoPS)) {
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
							+ peticion
							+ ","
							+ ps.getIdProductoServicio()
							+ ","
							+ ps.getIdOperacionComercial()
							+ "]\n";
						//log.error(msg);
						miE = e1;
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

				} else {
					//No se encontro Flujo combinado en la base
					//Calcular el flujo combinado con la euristica.
					Flujos flujos = new Flujos(listaFlujos);
					log.debug("Flujos a UNIR:\n" + flujos);
					flujoUnido = flujos.unirFlujos();
					//TODO: REVISAR ESTO
					//flujoUnido.guardaFlujoCombinado();
					log.debug("Flujos a RESULTANTE" + flujoUnido);
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
							Actividad_flujoKey afk= (Actividad_flujoKey) element.getActividad_flujo().getPrimaryKey();
						log.debug(
							"REMOVE???????????????  actividad ="
								+ afk.acti_id);
						element.remove();
					}
				} catch (FinderException e) {
					//Esta ok para ingresar el flujo de la peticion
				}
				DBManager manager=new DBManager();
				manager.setDataSource(DBManager.JDBC_VPISTBBA);
				for (Iterator iter = flujoUnido.iterator(); iter.hasNext();) {
					Actividad actividad = (Actividad) iter.next();
					for (Iterator iterator =
						actividad.getProductoServicios().iterator();
						iterator.hasNext();
						) {
						
						ProductoServicio element =
							(ProductoServicio) iterator.next();
							long id=manager.seqNextValLong("ATIEMPO.CORRELATIVO_PETICION_FLUJO"); 
							Integer pefl_id=new Integer(new Long(id).intValue());
							Peticion_flujoLocal peticionFlujo =
							peticionFlujoLocalHome.create(pefl_id,
								peticion,
								element.getIdProductoServicio(),
								element.getIdOperacionComercial(),
								actividad.getId(),
								new Integer(
									flujoUnido.getPasoActividad(actividad)));
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
							Actividad_flujoKey aFK= (Actividad_flujoKey) element.getActividad_flujo().getPrimaryKey();
							Integer aFId = aFK.acti_id;
						log.debug("comparacion: "
								+ aFId
								+ " con "
								+ anterior);
						if (!(aFId).equals(anterior)) {
							anterior = aFId;
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
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (RemoveException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			}

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
