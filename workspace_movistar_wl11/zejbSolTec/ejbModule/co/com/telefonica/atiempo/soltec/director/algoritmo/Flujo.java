/*
 * Created on Nov 24, 2004
 *
 */
package co.com.telefonica.atiempo.soltec.director.algoritmo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Flujo_definicionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Flujo_definicionLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.director.algoritmo.DirectorException;
import com.telefonica_chile.director.dto.ProductoServicio;
import com.telefonica_chile.vpistbba.servicios.dto.Actividad;
import com.telefonica_chile.vpistbba.servicios.dto.FlujoDto;


/**
 * Representa el flujo con el que uno o muchos Productos y/o Servicios son Provisionados y/o Instalados. 
 * 
 * @author dfiguer
 * @version %I%, %G%
 */
public class Flujo implements Serializable {
	private ArrayList flujo;
	//TODO: Sacar el campo Ubicacion
	private HashMap ubicacion;
	private boolean mutacion;
	private boolean actividadMDF;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public Flujo() {
		flujo = new ArrayList();
		ubicacion = new HashMap();
	}

	protected Flujo(ArrayList a) throws DirectorException {
		flujo = new ArrayList();
		ubicacion = new HashMap();
		for (Iterator iter = a.iterator(); iter.hasNext();) {
			ArrayList paso = (ArrayList) iter.next();
			this.addPaso(paso);
		}
	}

	/**
	 * Agrega el conjunto de actividades indicadas en el HashSet paso al final del flujo
	 * @param HashMap paso
	 */
	protected void addPaso(ArrayList paso) throws DirectorException {
		int tamano = this.flujo.size();
		this.flujo.add(new ArrayList());
		this.addPaso(paso, tamano);
	}

	protected void addPaso(ArrayList paso, int i) throws DirectorException {
		for (Iterator iter = paso.iterator(); iter.hasNext();) {
			Actividad element = (Actividad) iter.next();
			this.addActividad(element, i);
		}
	}

	protected void addActividad(Actividad act) throws DirectorException {
		int tamano = flujo.size();
		flujo.add(new ArrayList());
		this.addActividad(act, tamano);
	}

	protected void addActividad(Actividad act, int pasoId) throws DirectorException {
		if (pasoId >= flujo.size()) {
			for (int i = flujo.size(); i <= pasoId; i++) {
				flujo.add(i, new ArrayList());
			}
		}
		if (pasoId >= 0 && pasoId < flujo.size()) {
			ArrayList paso = (ArrayList) flujo.get(pasoId);
			for (Iterator iter = paso.iterator(); iter.hasNext();) {
				Actividad actividad = (Actividad) iter.next();
				if (actividad.getId().equals(act.getId())) {
					actividad.addProductoServicios(act.getProductoServicios());
					return;
				}
			}
			paso.add(act);
			ubicacion.put(act.getId(), new Integer(pasoId));
		} else if (pasoId < 0) {
			for (Iterator iter = ubicacion.keySet().iterator(); iter.hasNext();) {
				Integer element = (Integer) iter.next();
				Integer i = (Integer) ubicacion.get(element);
				Integer cantidadaux = new Integer(i.intValue() + 1);
				ubicacion.put(element, cantidadaux);
			}
			ubicacion.put(act.getId(), new Integer(0));
			ArrayList paso = new ArrayList();
			paso.add(act);
			flujo.add(0, paso);
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("Se intento insertar en el Flujo una actividad en un paso ");
			sb.append("mayor que el mayor que existia hasta el momento. Flujo:");
			sb.append(this.toString());
			sb.append(", Actividad:");
			sb.append(act.toString());
			log.error(sb.toString());
			throw new DirectorException(sb.toString());
		}
	}

	/**
	 * Obtiene el conjunto de actividadesa realizar en el paso numero i
	 * @param i
	 * @return conjunto de actividades.
	 */
	/*public ArrayList getActividades(int i) {
		return (ArrayList) flujo.get(i);
	}*/

	public Actividad getActividad(Integer actividadId) {
		if (ubicacion.containsKey(actividadId)) {
			Integer paso = (Integer) ubicacion.get(actividadId);
			ArrayList hs1 = (ArrayList) flujo.get(paso.intValue());
			for (Iterator iter = hs1.iterator(); iter.hasNext();) {
				Actividad element = (Actividad) iter.next();
				if (actividadId.equals(element.getId())) {
					return element;
				}
			}
		}
		return null;
	}

	protected HashSet getActividades() {
		return new HashSet(ubicacion.keySet());
	}

	protected int tamanoPaso(int i) {
		try {
			ArrayList a = (ArrayList) flujo.get(i);
			if (a != null && !a.isEmpty()) {
				return a.size();
			}
			return -1;
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}

	}

	/**
	 * Retorna el numero de pasos que tiene un flujo.
	 * @return numero de pasos.
	 */
	public int size() {
		return flujo.size();
	}

	/**
	 * Recupara de la base de datos el Flujo que posee el id idFlujo, y lo relaciona a los productos en listaProductos
	 * @param idFlujo Id del Flujo a recuperar
	 * @param listaProductos Lista de los ProductosServicios-OperacionComercial que Utilizan este flujo.
	 */
	public void setFlujo(Integer idFlujo, TreeSet listaProductos) throws DirectorException {
		flujo = new ArrayList();
		ubicacion = new HashMap();
		ArrayList aux = new ArrayList();
		Comparator comparador = new Comparator() {
			public int compare(Object o1, Object o2) {
				Actividad a1 = (Actividad) o1;
				Actividad a2 = (Actividad) o2;
				HashSet dependencias1 = a1.getDependencias();
				HashSet dependencias2 = a2.getDependencias();
				//A1 depende de a2 por lo que quedan ordenados asi a2a1,
				// osea, a1 es mayor que a2, por lo que retorno 1
				if (dependencias1.contains(a2.getId())) {
					return 1;
				}
				//a2 depende de a1 por lo que quedan ordenados asi a1a2,
				// osea, a1 es menor que a2, por lo que retorno -1
				if (dependencias2.contains(a1.getId())) {
					return -1;
				}
				//Son independientes.
				return 0;
			}
		};
		try {
			Flujo_definicionLocalHome flujoActividadLocalHome = (Flujo_definicionLocalHome) HomeFactory.getHome(Flujo_definicionLocalHome.JNDI_NAME);

			Collection actividadesPS = flujoActividadLocalHome.findByIdFlujo(idFlujo);
			Actividad act = new Actividad();
			for (Iterator iter = actividadesPS.iterator(); iter.hasNext();) {
				Flujo_definicionLocal element = (Flujo_definicionLocal) iter.next();
				Actividad_flujoKey actK = (Actividad_flujoKey) element.getActividad_flujo().getPrimaryKey();
				Integer actId = actK.acti_id;
				if (act.getId() == null || !actId.equals(act.getId())) {
					if (act.getId() != null) {
						aux.add(act);
					}
					act = new Actividad();
					act.setActividad(actId);
					try {
						Actividad_flujoLocalHome actividadLocalHome = (Actividad_flujoLocalHome) HomeFactory.getHome(Actividad_flujoLocalHome.JNDI_NAME);
						Actividad_flujoKey actFlujoKey = new Actividad_flujoKey( actId );
						Actividad_flujoLocal actividadBase = actividadLocalHome.findByPrimaryKey( actFlujoKey );
						act.setCodigo(actividadBase.getActi_codigo());
						act.setDescripcion(actividadBase.getActi_descripcion());
					} catch (NamingException e) {
						log.fatal("No encontro el home de la Tabla ACTIVIDAD ", e);
						throw new DirectorException("No encontro el home de la Tabla ACTIVIDAD ", e);
					} catch (FinderException e) {
						log.error("No encontro Actividad " + actId + "en la base.", e);
						throw new DirectorException("No encontro Actividad " + actId + "en la base.", e);
					}

					act.setProductoServicios(listaProductos);

				}
				act.setDependencia(element.getAct_acti_id());
			}
			aux.add(act);

			//Ordena las actividades segun sus dependencias
			Collections.sort(aux, comparador);
			ArrayList a = new ArrayList();
			for (int i = 0; i < aux.size(); i++) {
				Actividad actividad = (Actividad) aux.get(i);
				if (actividad.getDependencias() != null) {
					if (this.esDependiente(a, actividad)) {
						flujo.add(a);
						a = new ArrayList();
					}
				}
				a.add(actividad);
				ubicacion.put(actividad.getId(), new Integer(flujo.size()));
			}
			if (!a.isEmpty()) {
				flujo.add(a);
			}
		} catch (FinderException e) {
			log.error("No encontró la definicion del Flujo " + idFlujo + ", en la base de datos", e);
			throw new DirectorException("No encontró la definicion del Flujo " + idFlujo + ", en la base de datos", e);
		} catch (NamingException e) {
			log.error("No encontró el home de una tabla FlujoActividad", e);
			throw new DirectorException("No encontro el home de una tabla FlujoActividad", e);
		}

	}

	/**
	 * Determina si una actividad depenmde o no de alguna de las actividades ya definidas en un conjunto.
	 * 
	 * @param paso Conjunto de actividades.
	 * @param actividad Actividad a decidir si depende o no.
	 * @return Verdadero si a depende de alguna de las actividades en el conjunto actividadesPaso.
	 */
	private boolean esDependiente(ArrayList paso, Actividad a) {
		HashSet dependencias = a.getDependencias();
		for (Iterator iter = paso.iterator(); iter.hasNext();) {
			Actividad actividadPaso = (Actividad) iter.next();
			Integer actividadPasoId = actividadPaso.getId();
			//Por cada actividad del paso recupero el id
			for (Iterator iterator = dependencias.iterator(); iterator.hasNext();) {
				//Ese id de la actividad paso lo comparo con los id de cada una de las dependencias
				Integer actividadDependencias = (Integer) iterator.next();
				if (actividadPasoId.equals(actividadDependencias)) {
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Se encarga de guardar en la base de datos este flujo combinado 
	 * para ser utilizado en ocaciones posteriores.
	 */
	public void guardaFlujoCombinado() throws DirectorException {
		//Guardar en la base de datos el tabla FlujosCombinados
		StringBuffer pssOps = new StringBuffer();
		ArrayList productos = new ArrayList();
		for (int i = 0; i < flujo.size(); i++) {
			ArrayList paso = (ArrayList) flujo.get(i);
			for (Iterator iter = paso.iterator(); iter.hasNext();) {
				Actividad actividad = (Actividad) iter.next();
				for (Iterator iterator = actividad.getProductoServicios().iterator(); iterator.hasNext();) {
					ProductoServicio element = (ProductoServicio) iterator.next();
					if (!productos.contains(element)) {
						productos.add(element);
					}

				}
			}
		}
		Collections.sort(productos);
		for (Iterator iter = productos.iterator(); iter.hasNext();) {
			ProductoServicio element = (ProductoServicio) iter.next();
			pssOps.append(element.getIdProductoServicio());
			pssOps.append("-");
			pssOps.append(element.getIdOperacionComercial());
			pssOps.append(",");
		}
		pssOps.deleteCharAt(pssOps.length() - 1);
//		Integer idFlujoCombinado = null;
//		log.debug("CREA DEFINICION FLUJO COMBINADO");
//		log.debug("id Flujo Combinado , Id Producto Servicio , Id Operacion Comercial, Actividad ID, ORDEN");
//		try {
//			FlujoCombinadoLocalHome flujoCombinadoLocalHome = (FlujoCombinadoLocalHome) HomeFactory.getHome(FlujoCombinadoLocalHome.JNDI_NAME);
//			FlujoCombinadoLocal flujoCombinado = flujoCombinadoLocalHome.create(pssOps.toString());
//			FlujoCombinadoDefinicionLocalHome flujoCombinadoDefLocalHome = (FlujoCombinadoDefinicionLocalHome) HomeFactory.getHome(FlujoCombinadoDefinicionLocalHome.JNDI_NAME);
//			idFlujoCombinado = (Integer) flujoCombinado.getPrimaryKey();
//			for (int i = 0; i < flujo.size(); i++) {
//				ArrayList paso = (ArrayList) flujo.get(i);
//				for (Iterator iter = paso.iterator(); iter.hasNext();) {
//					Actividad actividad = (Actividad) iter.next();
//					for (Iterator iterator = actividad.getProductoServicios().iterator(); iterator.hasNext();) {
//						ProductoServicio element = (ProductoServicio) iterator.next();
//						log.debug(idFlujoCombinado + ", " + element.getIdProductoServicio() + ", " + element.getIdOperacionComercial() + ", " + actividad.getId() + ", " + i);
//						FlujoCombinadoDefinicionLocal flujoCombinadoDef =
//							flujoCombinadoDefLocalHome.create(
//								idFlujoCombinado,
//								element.getIdProductoServicio(),
//								element.getIdOperacionComercial(),
//								actividad.getId(),
//								new Integer(i));
//					}
//				}
//			}
//
//		} catch (NamingException e) {
//			log.fatal("No encontró el home de una tabla", e);
//			throw new DirectorException("No encontro el home de una tabla", e);
//		} catch (CreateException e) {
//			log.error("No pudo crear una instancia de la Clase PeticionFLujo, para el Flujo Combinado " + idFlujoCombinado, e);
//			throw new DirectorException("No pudo crear una instancia de la Clase PeticionFLujo, para el Flujo Combinado  " + idFlujoCombinado, e);
//		}
	}

	/**
	 * Verifica si existe un flujo combinado ya guardado en la base de datos que involucre 
	 * a todos estos productos.
	 * Recupera esta definicion de flujo y la deja guardada en el atributo flujo
	 * 
	 * @param listaFlujos  Hashmap con los Flujos y sus productos involucrados.
	 * @return       Verdadero si existe un flujo combinado para los flujos y productos involucrados.
	 * @see Flujo
	 */
	public boolean setFlujoCombinado(TreeSet listaProductos) throws DirectorException {
		return false;
//		//Revisar la tabla Flujos Combinados - Flujos
//		//por lo que esta funcion seria llamada antes.
//		//Recuperar este flujo de la tabla, flujos combinados definicion y
//		//dejarla guardada en el atributo flujo
//		StringBuffer pssOps = new StringBuffer();
//		if (listaProductos.isEmpty()) {
//			log.error("FIXME: esto no esta verificado si la listaProductos esta vacia... abortando! -- Aldrin");
//			return false;
//		}
//		
//		for (Iterator iter = listaProductos.iterator(); iter.hasNext();) {
//			ProductoServicio element = (ProductoServicio) iter.next();
//			pssOps.append(element.getIdProductoServicio());
//			pssOps.append("-");
//			pssOps.append(element.getIdOperacionComercial());
//			pssOps.append(",");
//		}
//		if (pssOps.length() > 0) {
//			pssOps.deleteCharAt(pssOps.length() - 1);
//		}
//
//		try {
//			FlujoCombinadoLocalHome flujoCombinadoLocalHome = (FlujoCombinadoLocalHome) HomeFactory.getHome(FlujoCombinadoLocalHome.JNDI_NAME);
//			FlujoCombinadoLocal flujoCombinado = flujoCombinadoLocalHome.findByCodigo(pssOps.toString());
//
//			Collection definicion = flujoCombinado.getFlujocombinadodefinicion();
//			flujo = new ArrayList();
//			ArrayList paso = new ArrayList();
//			TreeSet productos = new TreeSet();
//			FlujoCombinadoDefinicionLocal elementoAnterior = null;
//			for (Iterator iter = definicion.iterator(); iter.hasNext();) {
//				FlujoCombinadoDefinicionLocal element = (FlujoCombinadoDefinicionLocal) iter.next();
//				if (elementoAnterior != null && !element.getIdActividad().equals(elementoAnterior.getIdActividad())) {
//					Actividad act = new Actividad();
//					act.setActividad(elementoAnterior.getIdActividad());
//					try {
//						ActividadFlujoLocalHome actividadLocalHome = (ActividadFlujoLocalHome) HomeFactory.getHome(ActividadFlujoLocalHome.JNDI_NAME);
//						ActividadFlujoLocal actividadBase = actividadLocalHome.findByPrimaryKey(element.getIdActividad());
//						act.setCodigo(actividadBase.getCodigo());
//						act.setDescripcion(actividadBase.getDescripcion());
//					} catch (NamingException e) {
//						log.fatal("No encontro el home de la Tabla ACTIVIDAD ", e);
//						throw new DirectorException("No encontro el home de la Tabla ACTIVIDAD", e);
//					} catch (FinderException e) {
//						log.error("No encontro Actividad " + element.getIdActividad() + "en la base.", e);
//						throw new DirectorException("No encontro Actividad " + element.getIdActividad() + "en la base.", e);
//					}
//					act.setProductoServicios(productos);
//					this.addActividad(act, elementoAnterior.getOrden().intValue());
//					productos = new TreeSet();
//				}
//				ProductoServicio ps = new ProductoServicio();
//				ps.setIdProductoServicio(element.getIdProductoServicio());
//				ps.setIdOperacionComercial(element.getIdOperacionComercial());
//				productos.add(ps);
//				elementoAnterior = element;
//			}
//			if (elementoAnterior != null) {
//				Actividad act = new Actividad();
//				act.setActividad(elementoAnterior.getIdActividad());
//				try {
//					ActividadFlujoLocalHome actividadLocalHome = (ActividadFlujoLocalHome) HomeFactory.getHome(ActividadFlujoLocalHome.JNDI_NAME);
//					ActividadFlujoLocal actividadBase = actividadLocalHome.findByPrimaryKey(elementoAnterior.getIdActividad());
//					act.setCodigo(actividadBase.getCodigo());
//					act.setDescripcion(actividadBase.getDescripcion());
//				} catch (NamingException e) {
//					log.fatal("No encontro el home de la Tabla ACTIVIDAD ", e);
//					throw new DirectorException("No encontro el home de la Tabla ACTIVIDAD ", e);
//				} catch (FinderException e) {
//					log.error("No encontro Actividad " + elementoAnterior.getIdActividad() + "en la base.", e);
//					throw new DirectorException("No encontro Actividad " + elementoAnterior.getIdActividad() + "en la base.", e);
//				}
//				act.setProductoServicios(productos);
//				this.addActividad(act, elementoAnterior.getOrden().intValue());
//			}
//			return true;
//		} catch (NamingException e) {
//			log.error("No encontró el home de una tabla FlujoCombinado", e);
//			throw new DirectorException("No encontro el home de una tabla FlujoCombinado", e);
//		} catch (FinderException e) {
//			// No existe Flujo combinado para ese conjunto de PS 
//			// por lo que tan solo se retorna false
//			return false;
//		}

	}

	/**
	 * Se encarga de agregar el flujo f al final del flujo existente.
	 * 
	 * @param Flujo f Flujo a agregar
	 * @see Flujo
	 */
	protected void addFlujo(Flujo f) throws DirectorException {
		//Agregar el flujo al final de lo que ya existia.
		for (int i = 0; i < f.flujo.size(); i++) {
			ArrayList paso = (ArrayList) f.flujo.get(i);
			this.addPaso(paso);
			for (Iterator iter = paso.iterator(); iter.hasNext();) {
				Actividad element = (Actividad) iter.next();
				ubicacion.put(element.getId(), new Integer(flujo.size() - 1));
			}

		}
	}

	protected Flujo getTrozoFlujoAnterior(Actividad actividad) throws DirectorException {
		int i = this.getPasoActividad(actividad);
		if (i >= 0) {
			ArrayList a = new ArrayList(flujo.subList(0, i));
			if (!a.isEmpty()) {
				return new Flujo(a);
			}
		}
		return new Flujo();
	}

	protected Flujo getTrozoFlujoPosterior(Actividad actividad) throws DirectorException {
		int i = this.getPasoActividad(actividad);
		if (i >= 0) {
			ArrayList a = new ArrayList(flujo.subList(i + 1, flujo.size()));
			if (!a.isEmpty()) {
				return new Flujo(a);
			}
		}
		return new Flujo();
	}

	/**
	 * Retorna el paso en el que se encuentra una actividad dentro del flujo
	 * @param actividad a buscar paso
	 * @return  n el paso en que se encuentra la actividad, -1 si no esta. 
	 */
	public int getPasoActividad(Actividad actividad) {
		if (actividad != null) {
			for (int j = 0; j < flujo.size(); j++) {
				ArrayList unPaso = (ArrayList) flujo.get(j);
				for (Iterator iter = unPaso.iterator(); iter.hasNext();) {
					Actividad unaActividad = (Actividad) iter.next();
					if (actividad.equals(unaActividad)) {
						return j;
					}
				}
			}
		}
		return -1;
	}

	/**
	 * Verifica si contiene algun flujo.
	 * @return verdadero si contiene algo, falso si no.
	 */
	protected boolean esVacio() {
		if (flujo != null && !flujo.isEmpty()) {
			for (Iterator iter = this.iterator(); iter.hasNext();) {
				Actividad element = (Actividad) iter.next();
				if (!element.esVacio()) {
					return false;
				}
			}
		}
		return true;
	}

	public String toString() {
		StringBuffer texto = new StringBuffer();
		int i = 0;
		for (Iterator iter = flujo.iterator(); iter.hasNext();) {
			i++;
			ArrayList paso = (ArrayList) iter.next();
			texto.append(":");
			texto.append(i);
			for (Iterator iterator = paso.iterator(); iterator.hasNext();) {
				Actividad a = (Actividad) iterator.next();
				texto.append("{");
				texto.append(a.toString());
				texto.append("}");
			}
		}
		texto.append(":");
		return texto.toString();
	}

	protected void uneFlujoParalelo(Flujo flujoParalelo) throws DirectorException {
		Flujo nuevoFlujo = new Flujo();
		int min = Math.min(flujo.size(), flujoParalelo.size());
		int max = Math.max(flujo.size(), flujoParalelo.size());
		for (int i = 0; i < min; ++i) {
			ArrayList s = (ArrayList) flujo.get(i);
			s.addAll((ArrayList) flujoParalelo.flujo.get(i));
			nuevoFlujo.addPaso(s);
		}
		for (int i = min; i < max; ++i) {
			if (flujo.size() > i) {
				nuevoFlujo.addPaso((ArrayList) flujo.get(i));
			} else {
				nuevoFlujo.addPaso((ArrayList) flujoParalelo.flujo.get(i));

			}
		}
		flujo = nuevoFlujo.flujo;
		ubicacion = nuevoFlujo.ubicacion;

	}

	/**
	 * Retorna un hashset con las actividades que son paralelas a la actividad pivoteId en este flujo
	 * @param pivoteId actividad
	 * @return
	 */
	protected HashSet getActividadesParalelas(Integer pivoteId) {
		Integer pasoId = (Integer) ubicacion.get(pivoteId);
		ArrayList paso = (ArrayList) flujo.get(pasoId.intValue());
		HashSet hs = new HashSet();
		for (Iterator iter = paso.iterator(); iter.hasNext();) {
			Actividad element = (Actividad) iter.next();
			if (!element.getId().equals(pivoteId)) {
				hs.add(element);
			}
		}
		return hs;
	}

	public Iterator iterator() {
		ArrayList todos = new ArrayList();
		for (Iterator iter = flujo.iterator(); iter.hasNext();) {
			ArrayList element = (ArrayList) iter.next();
			for (Iterator iterator = element.iterator(); iterator.hasNext();) {
				Actividad actividad = (Actividad) iterator.next();
				todos.add(actividad);
			}
		}
		return todos.iterator();
	}

	public boolean tieneActividad(String codigo) {
		for (int j = 0; j < flujo.size(); j++) {
			ArrayList unPaso = (ArrayList) flujo.get(j);
			for (Iterator iter = unPaso.iterator(); iter.hasNext();) {
				Actividad unaActividad = (Actividad) iter.next();
				if (codigo.equals(unaActividad.getCodigo())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean tieneActividad(Integer id) {
		for (int j = 0; j < flujo.size(); j++) {
			ArrayList unPaso = (ArrayList) flujo.get(j);
			for (Iterator iter = unPaso.iterator(); iter.hasNext();) {
				Actividad unaActividad = (Actividad) iter.next();
				if (id.equals(unaActividad.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean tieneParalela(Integer id) {
		for (int j = 0; j < flujo.size(); j++) {
			ArrayList unPaso = (ArrayList) flujo.get(j);
			for (Iterator iter = unPaso.iterator(); iter.hasNext();) {
				Actividad unaActividad = (Actividad) iter.next();
				if (id.equals(unaActividad.getId())) {
					if (unPaso.size() > 1) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	public void eliminaActividad(String act) throws DirectorException {
		Actividad actividadEliminada = null;
		HashSet pasosBorrar = new HashSet();
		for (int i = 0; i < flujo.size(); i++) {
			ArrayList paso = (ArrayList) flujo.get(i);
			for (Iterator iterator = paso.iterator(); iterator.hasNext();) {
				Actividad actividad = (Actividad) iterator.next();
				if (actividad.getCodigo().equals(act)) {
					actividadEliminada = actividad;
					break;
				}
			}
			if (actividadEliminada != null) {
				if (!paso.remove(actividadEliminada)) {
					log.error("No pudo borrar la Actividad " + actividadEliminada);
					throw new DirectorException("No pudo borrar la Actividad " + actividadEliminada);
				}
				if (paso.isEmpty()) {
					flujo.remove(i);
				}
				actividadEliminada = null;
			}
		}
		for (Iterator iter = pasosBorrar.iterator(); iter.hasNext();) {
			Integer i = (Integer) iter.next();
			flujo.remove(i.intValue());
		}
	}

	public FlujoDto getDto() {
		FlujoDto fdto = new FlujoDto();
		fdto.setFlujo(flujo);
		fdto.setUbicacion(ubicacion);
		return fdto;
	}
}
