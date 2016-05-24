/*
 * Created on 28-jun-07
 */
package co.com.telefonica.atiempo.soltec.dto;
import java.util.ArrayList;

import co.com.telefonica.atiempo.utiles.DataTransferObject;


/**
 * @author rodrigo
 */
public class InformacionTVStDTO implements DataTransferObject {
	
	private int cantidadDeco = 0;
	private boolean ponerDecoDefault;
	private ArrayList listPsEquipos;
	private int activos;

	public InformacionTVStDTO()
	{
		listPsEquipos=new ArrayList();
		ponerDecoDefault=false;
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

	public void addPsEquipo(ProductoStDTO psDto)
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
	 * @param i
	 */
	public void setActivos(int i) {
		activos = i;
	}


}
