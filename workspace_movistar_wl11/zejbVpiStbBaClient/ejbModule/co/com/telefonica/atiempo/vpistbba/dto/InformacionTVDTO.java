package co.com.telefonica.atiempo.vpistbba.dto;
import java.util.ArrayList;

import com.telefonica_chile.comun.ps.dto.ProductoDTO;

public class InformacionTVDTO {

	private int cantidadDecoAlta = 0;
	private int cantidadDecoBaja = 0;
	private boolean ponerDecoDefault;
	private ArrayList listPsEquipos;
	private int esperaActivacion;
	private int activos;
	private boolean tieneParesEnCas;

	public InformacionTVDTO()
	{
		listPsEquipos=new ArrayList();
		ponerDecoDefault=false;
		tieneParesEnCas=false;
	}
	/**
	 * @return
	 */
	public int getCantidadDecoAlta() {
		return cantidadDecoAlta;
	}

	/**
	 * @return
	 */
	public int getCantidadDecoBaja() {
		return cantidadDecoBaja;
	}

	/**
	 * @param i
	 */
	public void setCantidadDecoAlta(int i) {
		cantidadDecoAlta = i;
	}

	/**
	 * @param i
	 */
	public void setCantidadDecoBaja(int i) {
		cantidadDecoBaja = i;
	}

	/**
	 * @return
	 */
	public ArrayList getListPsEquipos() {
		return listPsEquipos;
	}

	/**
	 * @return
	 */
	public boolean isPonerDecoDefault() {
		return ponerDecoDefault;
	}

	/**
	 * @param list
	 */
	public void setListPsEquipos(ArrayList list) {
		this.listPsEquipos = list;
	}

	/**
	 * @param b
	 */
	public void setPonerDecoDefault(boolean b) {
		ponerDecoDefault = b;
	}

	public void addPsEquipo(ProductoDTO psDto)
	{
		listPsEquipos.add(psDto);
		
	}

	/**
	 * @return
	 */
	public int getActivos() {
		return activos;
	}

	/**
	 * @return
	 */
	public int getEsperaActivacion() {
		return esperaActivacion;
	}

	/**
	 * @param i
	 */
	public void setActivos(int i) {
		activos = i;
	}

	/**
	 * @param i
	 */
	public void setEsperaActivacion(int i) {
		esperaActivacion = i;
	}

	/**
	 * @return
	 */
	public boolean isTieneParesEnCas() {
		return tieneParesEnCas;
	}

	/**
	 * @param b
	 */
	public void setTieneParesEnCas(boolean b) {
		tieneParesEnCas = b;
	}

}
