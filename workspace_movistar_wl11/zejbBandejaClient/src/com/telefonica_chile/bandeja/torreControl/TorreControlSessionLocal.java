package com.telefonica_chile.bandeja.torreControl;
import java.util.ArrayList;
import java.util.HashMap;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.bandeja.BandejaException;
/**
 * Local interface for Enterprise Bean: TorreControlSession
 */
public interface TorreControlSessionLocal extends javax.ejb.EJBLocalObject {
	//Metodo que setear dator filtrados
	public Tabla setearDatoTablaPeticion(
		HashMap filtros,
		int paginaActual,
		int paginacion)
		throws BandejaException;
	/*
	 * Busca Los Datos para llenar el Cuadro de Mando de Torre de Control 
	 */
	public Tabla getDatosCuadroMando(HashMap filtro);
	/*
	 * Obtiene el Listado de los Detalle de Actividad, ordenados por Orden. 
	 */
	public ArrayList getTitulosCuadroMando(HashMap filtro);
	/*
	 * Obtiene el Listado de los Detalle de Actividad, ordenados por Orden. 
	 */
	public ArrayList getActividadesCuadroMando(HashMap filtro);
	public ArrayList getTitulosCuadroMandoGI(HashMap filtro);
	public Tabla getDatosCuadroMandoGI(HashMap filtro);
	public ArrayList getActividadesCuadroMandoGI(HashMap filtro);
	public ArrayList getActividadesCuadroMandoGO(HashMap filtro);
	public Tabla getDatosCuadroMandoGO(HashMap filtro);
	public ArrayList getTitulosCuadroMandoGO(HashMap filtro);
	public ArrayList listaBuscadorAvanzadoInBound(Long idPeticionAtis,String rutCli,String rutDv) throws BandejaException;
	public ArrayList listaBuscadorAvanzadoOutBound(Long idPeticionAtis,String rutCli,String rutDv) throws BandejaException;
	public ArrayList listaBuscadorAvanzadoGestorOS(Long idPeticionAtis,String rutCli,String rutDv) throws BandejaException;
}
