/*
 * CR 00024805 - May 14, 2009 - 1
 *  	Clase auxiliar para el manejo de las consultas sql a utilizar en la ejecución de la busuqueda.
 */
package co.com.telefonica.atiempo.vpistbba.peticiones.ejb;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.com.atiempo.dto.BuscadorPeticionVpiDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.BuscadorPeticionUtiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * @author 810884
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PeticionesServicesUtils {
	
	/*private static final String QUERY_BUSQUEDA = "SELECT PSP.COD_PET_CD, PSP.PETI_NUMERO , A.agr_sub_pdr_cd, A.cod_agr_sub_nu, P.peti_fecha_ingreso, P.peti_fecha_compromiso " +
														"FROM vpistbba.peticion P, " + 
													    "vpistbba.agrupacion_atis A, " + 
													    "vpistbba.subpeticion_atis SP, " + 
													    "vpistbba.producto_servicio_peticion PSP " + 
													"WHERE " +
													"P.cod_pet_cd = A.cod_pet_cd AND " +
													"A.cod_pet_cd = SP.cod_pet_cd AND " +
													"A.cod_agr_sub_nu = SP.cod_agr_sub_nu AND " + 
													"PSP.COD_PET_CD = SP.COD_PET_CD AND " +
													"PSP.COD_AGR_SUB_NU = SP.COD_AGR_SUB_NU AND " + 
													"PSP.COD_SUB_CD = SP.COD_SUB_CD AND " +
													"PSP.PETI_NUMERO = P.PETI_NUMERO ";
	
	private static final String QUERY_ORDER_BY = "ORDER by PSP.COD_PET_CD desc, PSP.PETI_NUMERO desc, A.agr_sub_pdr_cd, A.cod_agr_sub_nu";
	
	private static final String FILTRO_PETICION = "AND PSP.COD_PET_CD = ? ";
	
	private static final String FILTRO_IDENTIFICADOR_CLIENTE = "AND P.NUM_DOC_CLI_CD = ? AND " +
																"P.DIG_CTL_DOC_CD = ? ";																
	
	private static final String FILTRO_IDENTIFICADOR_PC_LINEA = "AND A.NUM_IDE_NU = ? ";
	
	private static final String FILTRO_IDENTIFICADOR_PC_TV = "AND P.NUM_IDE_NU_TV = ? ";
	
	private static final String FILTRO_IDENTIFICADOR_PC_IC = "AND P.NUM_IDE_NU_IC = ? ";

	private static final int LIMITE_PETICIONES_DEFAULT = 100;*/
	
	
	
	private static final String SELECT_BUSQUEDA = "SELECT PSP.COD_PET_CD, " +
    "PSP.PETI_NUMERO , " +
    "A.agr_sub_pdr_cd, " +
    "A.cod_agr_sub_nu, " +
    "P.peti_fecha_ingreso, " +
    "P.peti_fecha_compromiso ";

private static final String FROM_BUSQUEDA = "FROM vpistbba.agrupacion_atis A, " + 
   "vpistbba.subpeticion_atis SP, " + 
   "vpistbba.producto_servicio_peticion PSP, " + 
   "vpistbba.peticion P " ;

private static final String WHERE_BUSQUEDA = "WHERE P.cod_pet_cd = A.cod_pet_cd AND " +
   "A.cod_pet_cd = SP.cod_pet_cd AND " +
   "A.cod_agr_sub_nu = SP.cod_agr_sub_nu AND " + 
   "PSP.COD_PET_CD = SP.COD_PET_CD AND " +
   "PSP.COD_AGR_SUB_NU = SP.COD_AGR_SUB_NU AND " + 
   "PSP.COD_SUB_CD = SP.COD_SUB_CD AND " +
   "PSP.PETI_NUMERO = P.PETI_NUMERO ";


private static final String WHERE_BUSQUEDA_INTEGRADA = "WHERE P.cod_pet_cd = A.cod_pet_cd AND " +
   "A.cod_pet_cd = SP.cod_pet_cd AND " +
   "A.cod_agr_sub_nu = SP.cod_agr_sub_nu AND " + 
   "PSP.COD_PET_CD = SP.COD_PET_CD AND " +
   "PSP.COD_AGR_SUB_NU = SP.COD_AGR_SUB_NU AND " + 
   "PSP.COD_SUB_CD = SP.COD_SUB_CD AND " +
   "PSP.PETI_NUMERO = P.PETI_NUMERO AND " +
   "bi.BI_VISIBLE = 1 ";


private static final String SELECT_SIN_BINTEGRADA_AUX = ", CAST (NULL AS INTEGER) AS bi_id, CAST (NULL AS INTEGER) AS act_id, CAST (NULL AS VARCHAR(500)) AS bi_url_detalle, CAST (NULL AS NUMERIC) AS rol_id ";

private static final String SELECT_BINTEGRADA = ", bi.bi_id, bi.act_id, bi.bi_url_detalle, bi.rol_id ";

private static final String FROM_BINTEGRADA = "LEFT OUTER JOIN atiempo.bintegrada bi " +
   "ON bi.bi_nro_peticion = p.peti_numero ";

private static final String QUERY_BUSQUEDA_SIN_BINTEGRADA = SELECT_BUSQUEDA + SELECT_SIN_BINTEGRADA_AUX +
      FROM_BUSQUEDA +
      WHERE_BUSQUEDA;

private static final String QUERY_BUSQUEDA_CON_BINTEGRADA = SELECT_BUSQUEDA + SELECT_BINTEGRADA + 
      FROM_BUSQUEDA + FROM_BINTEGRADA +
      WHERE_BUSQUEDA_INTEGRADA;

private static final String QUERY_ORDER_BY = "ORDER by PSP.COD_PET_CD desc, PSP.PETI_NUMERO desc, A.agr_sub_pdr_cd, A.cod_agr_sub_nu";

private static final String FILTRO_PETICION = "AND PSP.COD_PET_CD = ? ";

private static final String FILTRO_IDENTIFICADOR_CLIENTE = "AND P.NUM_DOC_CLI_CD = ? AND " +
      "P.DIG_CTL_DOC_CD = ? ";                

private static final String FILTRO_IDENTIFICADOR_PC_LINEA = "AND A.NUM_IDE_NU = ? ";

private static final String FILTRO_IDENTIFICADOR_PC_TV = "AND P.NUM_IDE_NU_TV = ? ";

private static final String FILTRO_IDENTIFICADOR_PC_IC = "AND P.NUM_IDE_NU_IC = ? ";

private static final int LIMITE_PETICIONES_DEFAULT = 100;	
	
	
	// 
	private List lista_agrupaciones = new ArrayList();
	
	private String peti_numero_proceso = null;
	
	private String delimitador;
	
	private static Integer limite;
	
	
	
	public PeticionesServicesUtils(String deli) {
		delimitador = deli;
	}

	
	// metodo para obtener la consulta segun los filtros ingresados
	/*public String obtenerConsultaSQL(int codigo){
		String query = QUERY_BUSQUEDA;
		switch (codigo){
			case BuscadorPeticionVpiDTO.COD_PETICION:
				query += FILTRO_PETICION;
				break;
			case BuscadorPeticionVpiDTO.COD_IDENTIFICADOR_CLIENTE:
				query += FILTRO_IDENTIFICADOR_CLIENTE;
				break;
			case BuscadorPeticionVpiDTO.COD_IDENTIFICADOR_PC_LINEA:
				query += FILTRO_IDENTIFICADOR_PC_LINEA;
				break;				
			case BuscadorPeticionVpiDTO.COD_IDENTIFICADOR_PC_TV:
				query += FILTRO_IDENTIFICADOR_PC_TV;
				break;
			case BuscadorPeticionVpiDTO.COD_IDENTIFICADOR_PC_IC:
				query += FILTRO_IDENTIFICADOR_PC_IC;
				break;	
		}
		
			query += QUERY_ORDER_BY;
		
		return query;
	}*/
	
	public String obtenerConsultaSQL(BuscadorPeticionVpiDTO buscadorPeticionVpiDTO){
	    int codigo = buscadorPeticionVpiDTO.getIdBusqueda();
	    String query = "";
	    if (!buscadorPeticionVpiDTO.isJoinBIntegrada()){
	     query = QUERY_BUSQUEDA_SIN_BINTEGRADA;
	    }else{
	     query = QUERY_BUSQUEDA_CON_BINTEGRADA;
	    }
	    switch (codigo){
	     case BuscadorPeticionUtiles.COD_PETICION:
	      query += FILTRO_PETICION;
	      break;
	     case BuscadorPeticionUtiles.COD_IDENTIFICADOR_CLIENTE:
	      query += FILTRO_IDENTIFICADOR_CLIENTE;
	      break;
	     case BuscadorPeticionUtiles.COD_IDENTIFICADOR_PC_LINEA:
	      query += FILTRO_IDENTIFICADOR_PC_LINEA;
	      break;    
	     case BuscadorPeticionUtiles.COD_IDENTIFICADOR_PC_TV:
	      query += FILTRO_IDENTIFICADOR_PC_TV;
	      break;
	     case BuscadorPeticionUtiles.COD_IDENTIFICADOR_PC_IC:
	      query += FILTRO_IDENTIFICADOR_PC_IC;
	      break; 
	    }
	    
	     query += QUERY_ORDER_BY;
	    
	    return query;
	   }	
	
	public void seteoParametros(PreparedStatement ps, int codigo, Long idPeticionAtis, String rutCli, String rutDv, String idPc) throws ATiempoAppEx{
		int pos = 0;
		try{
			switch (codigo){
				case BuscadorPeticionVpiDTO.COD_PETICION:
					ps.setLong(++pos, idPeticionAtis.longValue());
					break;
				case BuscadorPeticionVpiDTO.COD_IDENTIFICADOR_CLIENTE:
					ps.setString(++pos, rutCli);
					ps.setString(++pos, rutDv);
					break;
				case BuscadorPeticionVpiDTO.COD_IDENTIFICADOR_PC_LINEA:
					ps.setString(++pos, idPc);
					break;					
				case BuscadorPeticionVpiDTO.COD_IDENTIFICADOR_PC_TV:
					ps.setString(++pos, idPc);
					break;
				case BuscadorPeticionVpiDTO.COD_IDENTIFICADOR_PC_IC:
					ps.setString(++pos, idPc);
					break;	
			}
		}catch(SQLException e){
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	// metodo para obtener la agrupacion correspondiente para la peticion	
	public String obtenerAgrupacionString(String agr_sub_pdr_cd, String cod_agr_sub_nu, String peti_numero){
		String agrupacion = null;
		
		if (peti_numero_proceso == null){
			peti_numero_proceso = peti_numero;
		}else if (!peti_numero.equals(peti_numero_proceso)){
			agrupacion = construirAgrupacion();
			peti_numero_proceso = peti_numero;
			lista_agrupaciones.clear();
		}
		
		if (!agr_sub_pdr_cd.equals("0") && !lista_agrupaciones.contains(agr_sub_pdr_cd)){
			lista_agrupaciones.add(agr_sub_pdr_cd);
		}else if (!cod_agr_sub_nu.equals("0") && !lista_agrupaciones.contains(cod_agr_sub_nu)){
			lista_agrupaciones.add(cod_agr_sub_nu);
		}
		
		return agrupacion;
	}

	private String construirAgrupacion(){
		String agrupacion = "";
		
		Collections.sort(lista_agrupaciones);
		
		int cantidadElementos = lista_agrupaciones.size();
		if (cantidadElementos > 0){
			agrupacion = (String)lista_agrupaciones.get(0);
			for(int i=1; i<cantidadElementos; i++){
				agrupacion += delimitador;
				agrupacion += (String)lista_agrupaciones.get(i);
			}
		}
		return agrupacion;
	}
	
	public String getLastAgrupacion(){
		return construirAgrupacion();
	}
	
	public int getLimitePetisiones(){
		if (limite == null){
			String limiteProperties = VpistbbaConfig.getVariable("LIMITE_PETICIONES");
			if (limiteProperties == null || limiteProperties.equals("")){
				limite = new Integer(LIMITE_PETICIONES_DEFAULT);
			}else{
				limite = new Integer(limiteProperties);
			}	
		}		
		return limite.intValue();
	}
	
}
