/*
 * Created on Mar 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
import com.telefonica_chile.comun.ps.session.ProductoServicioSessionLocal;
import com.telefonica_chile.comun.ps.session.ProductoServicioSessionLocalHome;


public class ProductoServicio implements Serializable{
	
	
	public static ArrayList recuperaUsuariosHabilidadesPS(ArrayList usuariosConHabilidad,ArrayList arregloPs) throws  NamingException, CreateException {
		
		ArrayList arregloProductoDTO = busquedaProductoServicio(arregloPs);
		Set habilidades = comparaPsHabilidad(arregloProductoDTO);
		
		
		
		return null;
	}
	
	
	public static Set comparaPsHabilidad(ArrayList arregloPs){
		Set setHabilidad = new HashSet(); 
		for (int i = 0; i < arregloPs.size(); i++) {
			ProductoDTO  objProducto = new ProductoDTO();						 
			objProducto = (ProductoDTO)arregloPs.get(i);
			if(objProducto.getIdHabilidad()!=0){
				setHabilidad.add(new Integer(objProducto.getIdHabilidad()));
			}			
		}
		return setHabilidad;
	}

	public static ArrayList busquedaProductoServicio(ArrayList arregloPs) throws NamingException, CreateException {
		//Acceso a Session remoto para obtener Datos
		ProductoServicioSessionLocal ejbPsRemoto = getProductoServicioSession();
		ArrayList listadoProductos = new ArrayList(); 
		for (int i = 0; i < arregloPs.size(); i++) {
			ProductoDTO  objProducto = new ProductoDTO();		
			Long idPs = new Long(arregloPs.get(i).toString());								 
			objProducto = (ProductoDTO) ejbPsRemoto.recuperarPS(idPs);
			listadoProductos.add(objProducto);			
		}
		return listadoProductos;
	}
	
	/**Acceso a Session Remoto*/
	private static ProductoServicioSessionLocal getProductoServicioSession() throws NamingException, CreateException  {
		ProductoServicioSessionLocalHome  psRemotoEJB =(ProductoServicioSessionLocalHome) HomeFactory.getHome(ProductoServicioSessionLocalHome.JNDI_NAME);
		ProductoServicioSessionLocal ejbPsRemoto = null;
		ejbPsRemoto = psRemotoEJB.create();			
		return (ejbPsRemoto);
	}	
	
	

}
