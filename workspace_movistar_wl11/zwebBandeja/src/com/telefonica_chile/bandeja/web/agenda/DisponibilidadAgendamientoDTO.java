/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.agenda;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;


public class DisponibilidadAgendamientoDTO {

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	String dia = null;
	int tipoDia = -1;
	int idDia = -1;
	
	Integer cargaMaxima = null;
	HashMap rango = null;
	
	Integer porcentajeSegmento = null;

	Long idFamiliaPs = null;

	public DisponibilidadAgendamientoDTO() {
	}


	/**
	 * @return
	 */
	public Integer getCargaMaxima() {
		return cargaMaxima;
	}

	/**
	 * @return
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * @param integer
	 */
	public void setCargaMaxima(Integer integer) {
		cargaMaxima = integer;
	}

	/**
	 * @param string
	 */
	public void setDia(String string) {
		dia = string;
	}

	public void setRango( RangoAgendamientoDTO r ) {
		if (r == null || r.getIdRango() == null)
			return;

		if ( rango == null )
			rango = new HashMap();
		
		rango.put(r.getIdRango(), r);
	}

	public RangoAgendamientoDTO getRango( Integer idRango) {
		if (rango == null || idRango == null)
			return null;

		return ( (RangoAgendamientoDTO) rango.get(idRango) );
	}

	public int getDisponibilidad(Integer idRango) {
		return 1;
	}

	public int getDisponibilidad(Integer idRango, ArrayList listaSegmentos) {
		Integer tt = cargaMaxima;
		RangoAgendamientoDTO r = getRango( idRango );

		
		if ( r==null || tt == null || tt.intValue()==0)
			return -1;

		double m = (double) tt.intValue();
		double p = (r.getPorcentaje() == null) ? 100 : ((double) r.getPorcentaje().intValue()); 
		double pp = (r.getPorcentajePadre() == null) ? 100 : ((double) r.getPorcentajePadre().intValue()); 
		double q = (porcentajeSegmento == null) ? 100 : ((double) porcentajeSegmento.intValue());

		double mr = (double)( (int) ((m * p) / 100)); // Total del Rango

		// Calculo la Disponiblidad del Padre primero, si no hay
		if ( r.getIdPadre() != null) {
			double mp = (double)( (int) ((m * pp) / 100)); // Total del Rango
			double restoPadre = getOcupados(r, true, listaSegmentos, mp);
			// Calculo formula.
			//log.debug("[Padre: " + idRango + "," + r.getIdPadre() + "," + r.getPorcentajePadre()+ "]: "+mp+","+pp+","+q+","+restoPadre+"]");
			double  dispPadre = (mp*q/100) - restoPadre;
			if ( dispPadre <= 0 )
				return -1;
		}

		double resto = getOcupados(r, false, listaSegmentos, mr);

		// Calculo formula.
		double d = (mr*q/100) - resto;  
		double aux = ((double)100 * d / mr);
		int res = (int) (aux * 100);

		if ( log.isDebugEnabled() )
			log.debug("["+ idFamiliaPs + "," + idDia + "," + idRango+"]: "+mr+","+p+","+q+","+resto+"] == [" + d + "," + aux + "," + res + "]");

		return ( res );
	}

	private double getOcupados(RangoAgendamientoDTO r, boolean padre, ArrayList listaSegmentos, double m) {
		// Calculo losOcupados de los rangos menor igual al actual.


		Integer idRango = r.getIdRango();
		if  ( padre )
			idRango = r.getIdPadre();

		double pasados = 0;
		double ocu = 0;
		HashMap map = r.getOcupadoSegmento();
		double ocpD = 0;
		for (int i=0; i<listaSegmentos.size(); i++) {
			SegmentoAgendamientoDTO seg = (SegmentoAgendamientoDTO) listaSegmentos.get(i);
			String key = "S_" + seg.getIdGrupoSegmento() + "_" + idRango + "_" + idFamiliaPs; 
			Integer ocp = (Integer) map.get(key);
			ocp = (ocp==null) ? new Integer(0) : ocp;
			ocpD = ocp.intValue();

			if (!padre && r.getIdPadre() != null) {
				// Aca Tengo que sumar lo del Padre. (50% ?)
				String key1 = "S_" + seg.getIdGrupoSegmento() + "_" + r.getIdPadre() + "_" + idFamiliaPs;
				Integer ocAux = (Integer) map.get(key1);
				
				if (ocAux != null) {
					double x = (double)ocAux.intValue() * 0.5;
					ocpD = ocpD + x;
				}
			}
			if ( padre && r.getIdPadre() != null) {
				// Veo lo de los Hermanos:::-(((((
				if (r.getListaHermanos() != null) {
					// Aca Tengo que sumar lo de los Hijos.
					for (int k=0; k<r.getListaHermanos().size(); k++) {
						Integer id = (Integer) r.getListaHermanos().get(k);
						String key1 = "S_" + seg.getIdGrupoSegmento() + "_" + id + "_" + idFamiliaPs;
						Integer ocAux = (Integer) map.get(key1);
						if (ocAux != null)
							ocpD = ocpD + ocAux.intValue();
					}
				}

			}

			// Veo lo de los Hijos:::-(((((
			if (r.getListaHijos() != null) {
				// Aca Tengo que sumar lo de los Hijos.
				for (int k=0; k<r.getListaHijos().size(); k++) {
					Integer id = (Integer) r.getListaHijos().get(k);
					String key1 = "S_" + seg.getIdGrupoSegmento() + "_" + id + "_" + idFamiliaPs;
					Integer ocAux = (Integer) map.get(key1);
					if (ocAux != null)
						ocpD = ocpD + ocAux.intValue();
				}
			}

			if ( seg.isSegmentoMayor() ) {
				// Tengo que ver si hay quienes se pasaron. (Minimo reserv - Ocupado)
				Integer n1 = seg.getPorcentajeMinimo();
				n1 = (n1==null) ? new Integer(0) : n1;
				double dif = (n1.intValue()*m/100) - ocpD;
				if (dif < 0)
					pasados += dif;
			} else {
				// Sumo los ocupados de los Segmentos Menores.
				ocu += ocpD;
			}
		}
		
		return ( ocu - pasados);

	}
	/**
	 * @return
	 */
	public Integer getPorcentajeSegmento() {
		return porcentajeSegmento;
	}

	/**
	 * @param integer
	 */
	public void setPorcentajeSegmento(Integer integer) {
		porcentajeSegmento = integer;
	}

	/**
	 * @return
	 */
	public int getTipoDia() {
		return tipoDia;
	}

	/**
	 * @param i
	 */
	public void setTipoDia(int i) {
		tipoDia = i;
	}

	/**
	 * @return
	 */
	public int getIdDia() {
		return idDia;
	}

	/**
	 * @param i
	 */
	public void setIdDia(int i) {
		idDia = i;
	}

	/**
	 * @return
	 */
	public Long getIdFamiliaPs() {
		return idFamiliaPs;
	}

	/**
	 * @param long1
	 */
	public void setIdFamiliaPs(Long long1) {
		idFamiliaPs = long1;
	}

}
