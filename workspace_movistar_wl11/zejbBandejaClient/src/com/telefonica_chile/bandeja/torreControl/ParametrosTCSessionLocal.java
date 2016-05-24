package com.telefonica_chile.bandeja.torreControl;
import java.util.HashMap;
import java.util.TreeMap;
/**
 * Local interface for Enterprise Bean: ParametrosTCSession
 */
public interface ParametrosTCSessionLocal extends javax.ejb.EJBLocalObject {
	//Metodo que retorna los nombres de la entidad Segmento
	public TreeMap getNombreAplicacion();
	//Metodo que retorna los nombres de la entidad Segmento
	
	// CR16429 FindAll - ana santos
	//public TreeMap getNombreFamiilaProductoServicio();
	
	//Metodo que retorna los nombres de la entidad Segmento
	
	//public TreeMap getNombreSegmento();
	
	//	Metodo que retorna los nombres de la entidad Tipo Trabajo
	public TreeMap getNombreTiopTrabajo();
	public TreeMap getNombreDepartamento();
	public HashMap buscarLocalidadesByDepartamento(String codDepto);
}
