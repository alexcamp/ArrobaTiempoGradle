package co.com.telefonica.atiempo.vpistbba.dto;

import java.util.ArrayList;

import co.com.atiempo.dto.DecoTarDTO;

public class InfoPostVentaTV
{

	private boolean esBajaCompleta;
	private boolean esBajaDeco;
	private boolean esTraslado;
	private boolean esMigracionPs;
	private ArrayList listaParesEnCas;
	private long peticionAt;
	private int cantidadParesBajaSTD;
	private int cantidadParesBajaPVR;
	private int cantidadParesBajaHD;
	
	private boolean esBajaPVRYSTD;
	private long opCoDeco;
	private int cantidadParesBaja;
	//TODO Se agrega el el tipo de deco para valida en la baja que deco dejar que marque
	private String tipodecodificador;
	
	public InfoPostVentaTV(long peticionAt)
	{
		esBajaCompleta=false;
		esBajaDeco=false;
		esMigracionPs=false;
		listaParesEnCas=new ArrayList();
		this.peticionAt=peticionAt;
	}


	public void addDecoTarjeta(String idDeco,String idTarjeta, String tipodeco)
	{
		DecoTarDTO decoTarDTO=new DecoTarDTO(idDeco,idTarjeta,tipodeco);
		listaParesEnCas.add(decoTarDTO);
	}
	
	/**
	 * @param id_deco
	 * @param id_tarjeta
	 * @param deco_reference
	 * @param deco_marca
	 * @param serial_deco
	 */
	public void addDecoTarjeta(String id_deco, String id_tarjeta, String deco_reference, String deco_marca, String serial_deco) {
		// TODO Apéndice de método generado automáticamente
		DecoTarDTO decoTarDTO=new DecoTarDTO(id_deco,id_tarjeta,deco_reference);
		decoTarDTO.setDecoBrand(deco_marca);
		decoTarDTO.setDecoSerial(serial_deco);
		listaParesEnCas.add(decoTarDTO);
	}
	/**
	 * @return
	 */
	public boolean isEsBajaCompleta() {
		return esBajaCompleta;
	}

	/**
	 * @return
	 */
	public boolean isEsBajaDeco() {
		return esBajaDeco;
	}

	/**
	 * @return
	 */
	public boolean isEsMigracionPs() {
		return esMigracionPs;
	}

	/**
	 * @return
	 */
	public boolean isEsTraslado() {
		return esTraslado;
	}

	/**
	 * @return
	 */
	public ArrayList getListaParesEnCas() {
		return listaParesEnCas;
	}

	/**
	 * @return
	 */
	public long getPeticionAt() {
		return peticionAt;
	}

	/**
	 * @param b
	 */
	public void setEsBajaCompleta(boolean b) {
		esBajaCompleta = b;
	}

	/**
	 * @param b
	 */
	public void setEsBajaDeco(boolean b) {
		esBajaDeco = b;
	}

	/**
	 * @param b
	 */
	public void setEsMigracionPs(boolean b) {
		esMigracionPs = b;
	}

	/**
	 * @param b
	 */
	public void setEsTraslado(boolean b) {
		esTraslado = b;
	}

	/**
	 * @param list
	 */
	public void setListaParesEnCas(ArrayList list) {
		listaParesEnCas = list;
	}

	/**
	 * @param l
	 */
	public void setPeticionAt(long l) {
		peticionAt = l;
	}

	/**
	 * @return
	 */
	public long getOpCoDeco() {
		return opCoDeco;
	}

	/**
	 * @param l
	 */
	public void setOpCoDeco(long l) {
		opCoDeco = l;
	}

	public String toString()
	{
		String retorno="";
		retorno+= "esBajaCompleta:"+esBajaCompleta+"\n";
		retorno+= "esBajaDeDeco:"+esBajaDeco+"\n";
		retorno+= "esMigracionPs:"+esMigracionPs+"\n";
		retorno+= "cantidadParesBajaPVR:"+cantidadParesBajaPVR+"\n";
		retorno+= "cantidadParesBajaSTD:"+cantidadParesBajaSTD+"\n";
		retorno+= "cantidadParesBajaHD:"+cantidadParesBajaHD+"\n";
		return retorno;
	}

	/**
	 * @return
	 */
	public String getTipodecodificador() {
		return tipodecodificador;
	}

	/**
	 * @param string
	 */
	public void setTipodecodificador(String string) {
		tipodecodificador = string;
	}

	/**
	 * @return
	 */
	public int getCantidadParesBajaPVR() {
		return cantidadParesBajaPVR;
	}

	/**
	 * @return
	 */
	public int getCantidadParesBajaSTD() {
		return cantidadParesBajaSTD;
	}

	/**
	 * @return
	 */
	public boolean isEsBajaPVRYSTD() {
		return esBajaPVRYSTD;
	}

	/**
	 * @param i
	 */
	public void setCantidadParesBajaPVR(int i) {
		cantidadParesBajaPVR = i;
	}

	/**
	 * @param i
	 */
	public void setCantidadParesBajaSTD(int i) {
		cantidadParesBajaSTD = i;
	}

	/**
	 * @param b
	 */
	public void setEsBajaPVRYSTD(boolean b) {
		esBajaPVRYSTD = b;
	}

	/**
	 * @return
	 */
	public int getCantidadParesBaja() {
		return cantidadParesBaja;
	}

	/**
	 * @param i
	 */
	public void setCantidadParesBaja(int i) {
		cantidadParesBaja = i;
	}

	
	/**
	 * @return Returns the cantidadParesBajaHD.
	 */
	public int getCantidadParesBajaHD() {
		return cantidadParesBajaHD;
	}
	/**
	 * @param cantidadParesBajaHD The cantidadParesBajaHD to set.
	 */
	public void setCantidadParesBajaHD(int cantidadParesBajaHD) {
		this.cantidadParesBajaHD = cantidadParesBajaHD;
	}



}
