/*
 * Created on Dec 6, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.director.algoritmo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.vpistbba.servicios.dto.Actividad;

/**
 * @author dfiguer
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Flujos implements Serializable {
	private ArrayList flujos;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	//pasoActual es el conjunto de actividades que se podran realizar en este paso.
	//o sea, pivote + palalelas que se mantengan + paralelas que aparescan del flujo.
	private ArrayList pasoActual;
	private Integer pivoteId;
	private ArrayList paralelas;
	private Flujo flujoParalelo;
	private ArrayList pivoteEnFlujos;
	private Flujo flujoResultante;
	private Flujos anterior;
	private Flujos posterior;

	/**
	 * 
	 * 
	 */
	protected Flujos(ArrayList listaFlujos) {
		flujos = new ArrayList();
		pivoteEnFlujos = new ArrayList();
		for (int i = 0; i < listaFlujos.size(); i++) {
			this.addFlujo((Flujo) listaFlujos.get(i));
		}
	}

	public Flujos(HashMap listaFlujos) throws DirectorException, CreateException, NamingException {
		flujos = new ArrayList();
		pivoteEnFlujos = new ArrayList();

		Integer flujoNulo;
		try {
			flujoNulo = new Integer (VpistbbaConfig.getVariable("ID_FLUJO_NULO"));
		} catch (Exception e) {
			throw new DirectorException("Exception",e);
		}
		//Recuperar por id Flujo los flujos a combinar asignadole los ps involucrados
		for (Iterator iter = listaFlujos.keySet().iterator(); iter.hasNext();) {
			Integer element = (Integer) iter.next();
			Flujo f = new Flujo();
			//element, es el id del flujo y listaFlujos.get(element)es el conjunto de ps involucrados
			f.setFlujo(element, (TreeSet) listaFlujos.get(element));
			if (!f.esVacio()) {
				this.addFlujo(f);
			} else {
				if (null == flujoNulo || flujoNulo.intValue() != element.intValue()) {
					throw new DirectorException("No encontro la definicion para el Flujo: " + element);
				}
			}

		}
	}

	/**
	 * Funcion recursiva que se encarga de unir los flujo a travez de la siguiente euristica.
	 * 
	 * @return Flujo
	 */
	//TODO: COLOCAR LA DEFINICION DE LA EURISTICA PARA EL JAVADOC.
	public Flujo unirFlujos() throws DirectorException {
		//log.debug("Flujo ="+flujos);
		if (this.esVacio()) {
			return new Flujo();
		}
		this.deteminaPivote();

		//Determinar el pre y post flujo
		anterior = this.getFlujoAnteriorPivote();
		posterior = this.getFlujoPosteriorPivote();

		//Revisar flujos que no contengan el pivote
		this.revisarFlujosSinPivote();

		//Revisar actividades paralelas
		this.revisarParalelas();		

		flujoResultante = new Flujo();
		if (anterior != null && !anterior.esVacio()) {
			flujoResultante.addFlujo(anterior.unirFlujos());
			log.debug("Anterior: "+flujoResultante.toString());
		}
		flujoResultante.addPaso(pasoActual);
		if (posterior != null && !posterior.esVacio()) {
			flujoResultante.addFlujo(posterior.unirFlujos());
			log.debug("Posterior: "+flujoResultante.toString());
		}
		//Si se determino un flujo paralelo, hay que unirlos
		if (!flujoParalelo.esVacio()) {
			flujoResultante.uneFlujoParalelo(flujoParalelo);
			log.debug("Paralelas: "+flujoResultante.toString());
		}

		return flujoResultante;
	}

	/********************************************************/
	/*FUNCIONES PARA DETERMINAR EL PIVOTE*/
	/**
	 * Permite agregar flujos al conjunyo flujos
	 * 
	 * @param flujo es el flujo a agregar
	 */
	private void deteminaPivote() throws DirectorException {
		pasoActual = new ArrayList();
		HashMap actividadesCantidad = this.calculaRepeticionActividades();
		pivoteId = this.pivote(actividadesCantidad);
		if (pivoteId != null) {
			Actividad actividadNueva = this.ubicacionesPivote(pivoteId);
			pasoActual.add(actividadNueva);
		} else {
			throw new DirectorException("No encontro el pivote para los flujos :" + this.toString());
		}
	}

	/**
	 * Calcula cuantas veces aparece en los diferentes flujos cada 
	 * una de las actividades que aparecen en los flujos
	 * @return  Un HashMap que tiene como llaves los id de actividad 
	 * 			y como valores la cantidad de veces que sale en los flujos.
	 */
	private HashMap calculaRepeticionActividades() {
		HashMap aux = new HashMap();
		for (int i = 0; i < flujos.size(); i++) {
			Flujo unFlujo = (Flujo) flujos.get(i);
			HashSet actividades = unFlujo.getActividades();
			for (Iterator iter = actividades.iterator(); iter.hasNext();) {
				Integer unaActividad = (Integer) iter.next();
				if (aux.containsKey(unaActividad)) {
					Integer cantidad = (Integer) aux.get(unaActividad);
					Integer cantidadaux = new Integer(cantidad.intValue() + 1);
					aux.put(unaActividad, cantidadaux);
				} else {
					aux.put(unaActividad, new Integer(1));
				}
			}
		}
		return aux;
	}

	/**
	 * Identifica cual de las actividades es la mejor para ser el pivote.
	 * Criterio de eleccion: Grupo de Actividades que aparece mas veces, se
	 * elige cualquiera de las que no tenga actividades paralelas en el paso, 
	 * si no hay cualquiera de las con paralela. 
	 * 
	 * @param actividadesCantidad
	 * @return  Id de la Activiada pivote
	 */
	private Integer pivote(HashMap actividadesCantidad) {
		//Idenficar la o las actividades que aparecen mas veces
		HashSet candidatos = new HashSet();
		Integer max = (Integer) Collections.max(actividadesCantidad.values());
		for (Iterator iter = actividadesCantidad.keySet().iterator(); iter.hasNext();) {
			Integer actividadId = (Integer) iter.next();
			if (max.equals(actividadesCantidad.get(actividadId))) {
				candidatos.add(actividadId);
			}
		}

		//Revisar las paralelas.
		//Elegir la primera que no tenga paralela, o la ultima si todas tienen paralelas.
		for (Iterator iter = candidatos.iterator(); iter.hasNext();) {
			Integer candidato = (Integer) iter.next();
			boolean pivote = true;
			for (int i = 0; i < flujos.size(); i++) {
				Flujo flujo = (Flujo) flujos.get(i);
				//int idPaso = flujo.getPasoActividad(candidato);
				if (flujo.tieneActividad(candidato) && flujo.tieneParalela(candidato)) {
					pivote = false;
					break;
				}
			}
			if (pivote || !iter.hasNext()) {
				return candidato;
			}
		}
		return null;
	}

	/**
	 * Calcula la ubicacion del pivote en cada uno de los flujos y crea la actividad 
	 * con todos los datos necesarios para los flujos.
	 * @param act id actividad pivote
	 */
	private Actividad ubicacionesPivote(Integer actividadId) throws DirectorException {
		Actividad actividadPivote = new Actividad();
		actividadPivote.setActividad(actividadId);
		try {
			Actividad_flujoLocalHome actividadLocalHome = (Actividad_flujoLocalHome) HomeFactory.getHome(Actividad_flujoLocalHome.JNDI_NAME);
			Actividad_flujoKey afKey = new Actividad_flujoKey( actividadId );
			Actividad_flujoLocal actividadBase = actividadLocalHome.findByPrimaryKey( afKey );
			actividadPivote.setCodigo(actividadBase.getActi_codigo());
			actividadPivote.setDescripcion(actividadBase.getActi_descripcion());
			//log.debug("ACTIVIDAD PIVOTE por ID=>"+actividadId+"(ubicacionesPivote)- CODIGO["+actividadPivote.getCodigo()+"] - DESCRIPCION["+actividadPivote.getDescripcion()+"]");
		} catch (NamingException e) {
			log.fatal("No encontro el home de la Tabla ACTIVIDAD ", e);
			throw new DirectorException("No encontro el home de la Tabla ACTIVIDAD ", e);
		} catch (FinderException e) {
			log.error("No encontro Actividad " + actividadId + "en la base.", e);
			throw new DirectorException("No encontro Actividad " + actividadId + "en la base.", e);
		}
		paralelas = new ArrayList();
		for (int i = 0; i < flujos.size(); i++) {
			Flujo unFlujo = (Flujo) flujos.get(i);
			Actividad actPivFlujo = unFlujo.getActividad(actividadId);
			if (actPivFlujo != null) {
				int paso = unFlujo.getPasoActividad(actPivFlujo);
				paralelas.add(new Integer(paso));
				if (paso >= 0) {
					Actividad actividadFlujo = unFlujo.getActividad(actividadId);
					TreeSet productos = actividadFlujo.getProductoServicios();
					actividadPivote.addProductoServicios(productos);
					pivoteEnFlujos.add(actPivFlujo);
				} else {
					if (!unFlujo.esVacio()) {
						pivoteEnFlujos.add(null);
					}
				}
			} else {
				pivoteEnFlujos.add(null);
			}
		}
		//log.debug("RETORNARA actividadPivote => CODIGO["+actividadPivote.getCodigo()+"], DESCRIPCION["+actividadPivote.getDescripcion()+"]");
		return actividadPivote;
	}

	/********************************************************/
	/*FUNCIONES PARA IDENTIFICAR FLUJO ANTERIOR Y POSTERIOR*/

	/**
	 * Separa los flujos definidos y recupera los flujos a realizar antes del pivote
	 * @return Conjunto de flujos anteriores
	 */
	private Flujos getFlujoAnteriorPivote() throws DirectorException {
		//Con la ubicacion del pivote recuperar los arraylists antes de ese punto

		Flujo auxFlujo;
		ArrayList auxFlujos = new ArrayList();
		for (int i = 0; i < flujos.size(); i++) {
			Flujo flujo = (Flujo) flujos.get(i);
			auxFlujo = flujo.getTrozoFlujoAnterior((Actividad) pivoteEnFlujos.get(i));
			if (auxFlujo != null && !auxFlujo.esVacio()) {
				auxFlujos.add(auxFlujo);
			} else {
				auxFlujos.add(new Flujo());
			}
		}
		Flujos anterior = new Flujos(auxFlujos);
		//log.debug("AuxFlujos Anterior Size=" + anterior.flujos.size());
		return anterior;
	}

	/**
	 * Separa los flujos definidos y recupera los flujos a realizar despues del pivote
	 * @return Conjunto de flujos posteriores
	 */
	private Flujos getFlujoPosteriorPivote() throws DirectorException {
		//Con la ubicacion del pivote recuperar los arraylists despues de ese punto
		Flujo auxFlujo;
		ArrayList auxFlujos = new ArrayList();
		for (int i = 0; i < flujos.size(); i++) {
			Flujo flujo = (Flujo) flujos.get(i);
			auxFlujo = flujo.getTrozoFlujoPosterior((Actividad) pivoteEnFlujos.get(i));
			if (auxFlujo != null && !auxFlujo.esVacio()) {
				auxFlujos.add(auxFlujo);
			} else {
				auxFlujos.add(new Flujo());
			}

		}
		Flujos posterior = new Flujos(auxFlujos);
		//log.debug("AuxFlujos Posterior Size=" + posterior.flujos.size());
		return posterior;
	}

	/********************************************************/
	/*FUNCIONES PARA MANEJAR LAS PARALELAS AL PIVOTE*/
	private void revisarParalelas() throws DirectorException {
		for (int i = 0; i < flujos.size(); i++) {
			Flujo flujo = (Flujo) flujos.get(i);
			int j = flujo.tamanoPaso(flujo.getPasoActividad((Actividad) pivoteEnFlujos.get(i)));
			if (j > 1) {
				HashSet hs = flujo.getActividadesParalelas(pivoteId);
				ArrayList actAntes = new ArrayList();
				ArrayList actDespues = new ArrayList();
				for (Iterator iter = hs.iterator(); iter.hasNext();) {
					Actividad actParalela = (Actividad) iter.next();
					//Contar cuantas veces aparece en cada uno de los FLUJOSSSS
					int antes = anterior.aparicionesActividad(actParalela.getId());
					int despues = posterior.aparicionesActividad(actParalela.getId());
					//y decidir si pa delante o pa atras!
					if (antes == despues && despues == 0) {
						//Sigue como paralela por lo que la agrego al paso 
						pasoActual.add(actParalela);
					} else if (antes > despues) {
						//Debe ser enviada a Flujos anterior
						actAntes.add(actParalela);
					} else {
						//Debe ser enviada a Flujos posterior
						actDespues.add(actParalela);
					}
				}
				if (!actAntes.isEmpty()) {
					Flujo f = (Flujo) anterior.flujos.get(i);
					if (f == null) {
						anterior.flujos.add(i, new Flujo());
						f = (Flujo) anterior.flujos.get(i);
					}
					f.addPaso(actAntes);
				}
				if (!actDespues.isEmpty()) {
					Flujo f = (Flujo) posterior.flujos.get(i);
					if (f == null) {
						posterior.flujos.add(i, new Flujo());
						f = (Flujo) posterior.flujos.get(i);
					}
					f.addPaso(actDespues, -1);
				}
			}
		}
	}

	private int aparicionesActividad(Integer actividad) {
		int i = 0;
		for (Iterator iter = flujos.iterator(); iter.hasNext();) {
			Flujo element = (Flujo) iter.next();
			if (element.tieneActividad(actividad)) {
				i++;
			}
		}
		return i;
	}
	/********************************************************/
	/*FUNCIONES PARA MANEJAR FLUJOS SIN PIVOTE*/
	private void revisarFlujosSinPivote() throws DirectorException {
		if (!pivoteEnFlujos.isEmpty()) {
			ArrayList a = new ArrayList();
			boolean paralelo = true;
			boolean antes = true;
			//log.debug("Pivote en Flujos Size:" + pivoteEnFlujos.size());
			for (int i = 0; i < pivoteEnFlujos.size(); i++) {
				Actividad element = (Actividad) pivoteEnFlujos.get(i);
				Flujo f = (Flujo) flujos.get(i);
				if (element == null && !f.esVacio()) {
					antes = true;
					//log.debug("Flujo sin Pivote:" + f );
					//Revisar si el flujo tiene alguna actividad que este el los flujos anteriores o posteriores
					for (Iterator iterator = f.iterator(); iterator.hasNext();) {
						Actividad actividad = (Actividad) iterator.next();
						log.debug("Act ID:" + actividad.getId());
						int totalAnterior = anterior.aparicionesActividad(actividad.getId());
						int totalPosterior = posterior.aparicionesActividad(actividad.getId());
						//decidir si pa delante o pa atras!
						//log.debug("Total Anterior = " + totalAnterior + ". Total Posterior = " + totalPosterior);
						if (totalAnterior != 0 || totalPosterior != 0) {
							paralelo = false;
						}
						if (totalPosterior > totalAnterior) {
							//Separa el flujo en este punto y mandar lo primero pa delante y lo otro par atraz..
							Flujo flujoAnterior = f.getTrozoFlujoAnterior(actividad);
							anterior.addFlujo(flujoAnterior,i);
							Flujo flujoPosterior = f.getTrozoFlujoPosterior(actividad);
							flujoPosterior.addActividad(actividad, -1);
							posterior.addFlujo(flujoPosterior,i);
							HashSet paralaleasPivote = f.getActividadesParalelas(actividad.getId());
							for (Iterator iterador = paralaleasPivote.iterator(); iterador.hasNext();) {
								//log.debug("Hay Actividades Paralelas");
								Actividad actividadParalelaPivote = (Actividad) iterador.next();
								int totalAnteriorParalelaPivote = anterior.aparicionesActividad(actividadParalelaPivote.getId());
								int totalPosteriorParalelaPivote = posterior.aparicionesActividad(actividadParalelaPivote.getId());
								if (totalAnteriorParalelaPivote == totalPosteriorParalelaPivote && totalAnterior == 0) {
									pasoActual.add(actividadParalelaPivote);
								} else if (totalPosteriorParalelaPivote >= totalAnteriorParalelaPivote) {
									flujoPosterior.addActividad(actividadParalelaPivote, -1);
								} else {
									flujoAnterior.addActividad(actividadParalelaPivote);
								}
							}
							antes = false;
							break;
						}
					}
					if (paralelo) {
						a.add(f);
					}
					if (antes) {
						anterior.addFlujo(f,i);
					}
				}
			}

			Flujos fs = new Flujos(a);
			if (!fs.esVacio()) {
				flujoParalelo = fs.unirFlujos();
			} else {
				flujoParalelo = new Flujo();
			}
		} else {
			flujoParalelo = new Flujo();
		}

	}

	/********************************************************/
	/*FUNCIONES PARA LA RECURRENCIA*/
	/**
	 * Permite agregar un flujo al conjunto de flujos
	 * 
	 * @param flujo es el flujo a agregar
	 */
	private void addFlujo(Flujo f) {
		flujos.add(f);
	}

	private void addFlujo(Flujo f, int pos) {
		flujos.set(pos,f);
	}
	/**
	 * Verifica si contiene algun flujo.
	 * @return verdadero si contiene algo, falso si no.
	 */
	private boolean esVacio() {
		if (flujos == null || flujos.isEmpty()) {
			return true;
		} else {
			for (Iterator iter = flujos.iterator(); iter.hasNext();) {
				Flujo element = (Flujo) iter.next();
				if (!element.esVacio()) {
					return false;
				}
			}
			return true;
		}

	}

	/********************************************************/
	/*FUNCIONES UTILES*/
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (Iterator iter = flujos.iterator(); iter.hasNext();) {
			i++;
			Flujo element = (Flujo) iter.next();
			sb.append("Flujo");
			sb.append(i);
			sb.append("[");
			sb.append(element.toString());
			sb.append("]");
			sb.append("\n");
		}
		return sb.toString();
	}

}
