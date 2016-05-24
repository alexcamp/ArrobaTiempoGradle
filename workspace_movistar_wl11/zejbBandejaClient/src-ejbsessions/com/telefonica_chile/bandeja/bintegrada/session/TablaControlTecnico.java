package com.telefonica_chile.bandeja.bintegrada.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Campo_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.ControlTecnicoDTO;
import com.telefonica_chile.bandeja.dto.MatrizTecnicaDTO;

public class TablaControlTecnico extends Tabla
{

	private DBManager db = new DBManager();
	public TablaControlTecnico(int paginaActual)
	{		
		super(paginaActual);
		db.setDataSource(DBManager.JDBC_BANDEJA);
	}

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	public long getNumeroTotalElementos(HashMap filtro)
	{
		int tot = getCantidadElementos(filtro);
		long aux = (long) tot;

		return aux;
	}

	/**
	 * @param filtro
	 * @return
	 */
	private int getCantidadElementos(HashMap filtro)
	{
		int cantElementos = 0;

		String sqlWhereFijo = getWhereFijo(filtro);
		HashMap auxMap = getWhereVariable(filtro);

		String sql = " select count(T.COD_TECNICO) AS TOTAL from agenda.tecnico T where T.EMPR_ID is not null and t.habilitado=1 " +
		sqlWhereFijo + ((String) auxMap.get("W_VAR"));

		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		
		Hashtable salida = db.selectReadUncommitted(sql);
		

		String[] Total = (String[]) salida.get("TOTAL");
		if (Total == null || Total.length == 0)
		{
			return 0;
		}
		try
		{
			cantElementos = Integer.parseInt(Total[0]);
		} catch (Exception e)
		{
			e.printStackTrace();
			cantElementos = 0;
		}
		return cantElementos;
	}

	public HashMap getWhereVariable(HashMap filtro)
	{
		HashMap filtrosVariables = getFiltrosVariables(filtro);
		HashMap mapAux = new HashMap();
		mapAux.put("T_VAR", "");
		mapAux.put("W_VAR", "");

		if (filtrosVariables == null || filtrosVariables.size() <= 0)
			return mapAux;

		String sqlWhereVariable = "";
		String sqlTabVar = "";
		HashMap auxCV = getAllCampoVariable();
		String key = "", valor = "";
		;
		String aux = "";
		String and = "";
		String and1 = "";
		String coma = "";
		String sqlWhereVar = "";
		String sqlWhereVar1 = "";
		int cont = 1;
		for (Iterator it = filtrosVariables.keySet().iterator(); it.hasNext();) {
			key = (String) it.next();
			valor = (String) filtrosVariables.get(key);
			aux = (String) auxCV.get(key);
			if (aux != null && valor != null && !"".equals(valor)) {
				sqlWhereVar += and + " (V" + cont + ".cv_id=" + aux + " and " + "V" + cont + ".valor='" + valor + "') ";

				sqlTabVar += coma + " VALOR_VARIABLE V" + cont + " ";

				if (cont > 1) {
					sqlWhereVar1 += and1 + " V" + (cont - 1) + ".bi_id=V" + cont + ".bi_id ";
					and1 = "and";

				}
				cont++;
				and = "and";
				coma = ",";
			}
		}

		if (!"".equals(sqlWhereVar)) {
			sqlWhereVariable = " and B.bi_id=V1.bi_id " + and1 + " " + sqlWhereVar1 + " " + and + " " + sqlWhereVar + " ";
			sqlTabVar = ", " + sqlTabVar;
		} else {
			sqlWhereVariable = "";
			sqlTabVar = "";
		}

		mapAux.put("T_VAR", sqlTabVar);
		mapAux.put("W_VAR", sqlWhereVariable);

		return mapAux;
	}

	/**
	 * @param filtro
	 * @return
	 */
	private String getWhereFijo(HashMap filtro)
	{
		String whereFijo = "";
		
		whereFijo += utilWhere("T.COD_TECNICO", (String) filtro.get("COD_TECNICO"), "S");
		
		String idContratista=(String)filtro.get("ID_CONTRA");
		if(idContratista!=null && !idContratista.equals(""))
			whereFijo+=" and T.EMPR_ID="+idContratista+" ";
		return whereFijo;
	}

	public String utilWhere(String campo, String valor, String tipo)
	{

		if (valor == null || "".equals(valor))
			return "";

		if ("N".equals(tipo))
			return (" and " + campo + "=" + valor);

		if ("S".equals(tipo))
			return (" and " + campo + "='" + valor + "'");

		if ("LS".equals(tipo))
			return (" and " + campo + " like (" + valor + ")");

		if ("FC".equals(tipo))
			return (" and " + campo + " " + valor + " ");

		if ("IN".equals(tipo))
			return (" and " + campo + " in (" + valor + ") ");

		if("F".equals(tipo))
		{
			try
			{
				Fecha fecha=new Fecha(valor,"dd/MM/yyyy");
				return (" and " + campo + " >= '" + fecha.getFechaconFormato(10)+"' and "+campo+" <= '"+fecha.getFechaconFormato(10)+"'");
			} catch (FechaException e)
			{
				e.printStackTrace();
			}
		}
		return "";
	}

	protected List getElementosPagina(HashMap filtro) throws TablaException
	{
		String sqlWhereFijo = getWhereFijo(filtro);
		String sql ="select * from (select T.COD_TECNICO,T.NOMBRE,T.APELLIDO,T.EMPR_ID," +
			" row_number() over ( order by T.COD_TECNICO ) as row" +
		" from agenda.tecnico T where T.EMPR_ID is not null and t.habilitado=1 "
		+ sqlWhereFijo + " ) subtabla where row >= "
		+ getIdxInicial()
		+ " and row <= "
		+ getIdxFinal()
		+ " "  ;
		
		log.debug("sql->"+sql);
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		
		String[] COD_TECNICO = (String[]) salida.get("COD_TECNICO");
		String[] NOMBRE = (String[]) salida.get("NOMBRE");
		String[] APELLIDO = (String[]) salida.get("APELLIDO");
		String[] EMPR_ID = (String[]) salida.get("EMPR_ID");
		
		List lista = new ArrayList();
		for (int i = 0; COD_TECNICO != null && i < COD_TECNICO.length; i++)
		{
			ControlTecnicoDTO controlTecnicoDTO=new ControlTecnicoDTO();
			controlTecnicoDTO.setCodTecnico(COD_TECNICO[i]);
			controlTecnicoDTO.setNombre(NOMBRE[i]);
			controlTecnicoDTO.setApellido(APELLIDO[i]);
			controlTecnicoDTO.setEmprId(new Long(EMPR_ID[i]));
			lista.add(controlTecnicoDTO);
		}
		//log.debug("Voy a retornar una lista de tablacontroltecnico de size:"+lista.size());
		llenaMatrizTecnica(filtro);
		return lista;
	}

	protected HashMap getFiltrosVariables(HashMap filtros)
	{
		HashMap fv = new HashMap();
		HashMap salida = new HashMap();

		if (filtros.containsKey("FILTROS_VARIABLES")) {

			fv = (HashMap) filtros.get("FILTROS_VARIABLES");
			for (Iterator it = fv.keySet().iterator(); it.hasNext();) {
				String k = (String) it.next();
				String v = (String) fv.get(k);
				if (v != null && v.trim().length() > 0)
					salida.put(k, v);
			}
		}

		return salida;
	}
	
	public HashMap getAllCampoVariable()
	{
		HashMap map = new HashMap();

		try {
			Campo_variableLocalHome cvHome = (Campo_variableLocalHome) HomeFactory.getHome(Campo_variableLocalHome.JNDI_NAME);
			Campo_variableLocal cvLocal = null;
			Collection c = cvHome.findAll();
			for (Iterator it = c.iterator(); it.hasNext();) {
				cvLocal = (Campo_variableLocal) it.next();
				Campo_variableKey cvKey = (Campo_variableKey) cvLocal.getPrimaryKey();
				map.put(cvLocal.getCv_nombre_int(), "" + cvKey.cv_id);
			}
		} catch (EJBException e) {
		} catch (NamingException e) {
		} catch (FinderException e) {
		} catch (Exception e) {
		}

		return map;
	}
	
	private void llenaMatrizTecnica(HashMap filtro) throws TablaException
	{
		String sql=" select  TP.TEPE_ID,TP.ID_RANGO,TP.HORA_DESDE,TP.HORA_HASTA,TP.ID_TECNICO,TP.PETI_NUMERO,TEC.NOMBRE,TEC.APELLIDO" +
		" from agenda.tecnico_peticion tp, agenda.tecnico TEC where TP.ID_TECNICO=TEC.COD_TECNICO " +
		" and TP.ESTADO=1 " +
		" and tec.habilitado=1 ";
		String whereMatriz=getWhereMatriz(filtro);
		sql+=whereMatriz;
		
		log.debug("sql-->"+sql);
		
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		
		String[] ID_RANGO = (String[]) salida.get("ID_RANGO");
		String[] HORA_DESDE = (String[]) salida.get("HORA_DESDE");
		String[] HORA_HASTA = (String[]) salida.get("HORA_HASTA");
		String[] ID_TECNICO = (String[]) salida.get("ID_TECNICO");
		for (int i = 0; ID_TECNICO != null && i < ID_TECNICO.length; i++)
		{
			MatrizTecnicaDTO matrizTecnicaDTO=new MatrizTecnicaDTO();
			matrizTecnicaDTO.setCodTecnico(ID_TECNICO[i]);
			matrizTecnicaDTO.setIdRango(new Integer(ID_RANGO[i]));
			listaopcional.add(matrizTecnicaDTO);
		}
	}
	
	private String getWhereMatriz(HashMap filtro)
	{
		String whereFiltro = "";
		
		whereFiltro += utilWhere("TP.FECHA", (String) filtro.get("FECHA_COM"), "F");
		whereFiltro += utilWhere("TEC.COD_TECNICO", (String) filtro.get("COD_TECNICO"),"S");
		
		Integer aplicacion=null;
		try
		{
			aplicacion = new Integer((String) filtro.get("AP_ID"));
		}
		catch (NumberFormatException e)
		{
			aplicacion=new Integer(3);//Voy a tomar por defecto para VPI
		}
		
		String departamento=(String)filtro.get("COD_DPT");
		String localidad=(String)filtro.get("COD_LOC");
		
		if(aplicacion!=null)
		{
			if(departamento!=null && localidad!=null)
			{
				switch(aplicacion.intValue())
				{
					case 3://vpistbba
						whereFiltro+=" and ( select count(pat.PETI_NUMERO) " +
							" from vpistbba.peticion pat where pat.PETI_NUMERO=TP.PETI_NUMERO" +
							" and PAT.COD_LOC='"+localidad+"' AND PAT.COD_DPT='"+departamento+"' ) > 0 "; 
						break;
					case 2://atst
						whereFiltro+=" and ( select count(pst.COD_AVE_CD) " +
							" from soltec.peticion_st pst where pst.COD_AVE_CD=TP.PETI_NUMERO " +
							" and pst.COD_LOC='"+localidad+"' AND pst.COD_DPT='"+departamento+"' ) > 0 ";
						break;
				}
			}
			else if(departamento!=null && localidad==null)
			{
				switch(aplicacion.intValue())
				{
					case 3://vpistbba
						whereFiltro+=" and ( select count(pat.PETI_NUMERO) " +
							" from vpistbba.peticion pat where pat.PETI_NUMERO=TP.PETI_NUMERO" +
							" AND PAT.COD_DPT='"+departamento+"' ) > 0 "; 
						break;
					case 2://atst
						whereFiltro+=" and ( select count(pst.COD_AVE_CD) " +
							" from soltec.peticion_st pst where pst.COD_AVE_CD=TP.PETI_NUMERO " +
							" AND pst.COD_DPT='"+departamento+"' ) > 0 ";
						break;
				}
			}
			else if(departamento==null && localidad!=null)
			{
				switch(aplicacion.intValue())
				{
					case 3://vpistbba
						whereFiltro+=" and ( select count(pat.PETI_NUMERO) " +
							" from vpistbba.peticion pat where pat.PETI_NUMERO=TP.PETI_NUMERO" +
							" and PAT.COD_LOC='"+localidad+"'  ) > 0 "; 
						break;
					case 2://atst
						whereFiltro+=" and ( select count(pst.COD_AVE_CD) " +
							" from soltec.peticion_st pst where pst.COD_AVE_CD=TP.PETI_NUMERO " +
							" and pst.COD_LOC='"+localidad+"'  ) > 0 ";
						break;
				}
			}
			
		}
		
		whereFiltro += utilWhere("TP.AP_ID", String.valueOf(aplicacion),"N");

		String idContratista=(String)filtro.get("ID_CONTRA");
		if(idContratista!=null && !idContratista.equals(""))
			whereFiltro+=utilWhere("TEC.EMPR_ID",idContratista,"N");

		return whereFiltro;
	}
	
	
	
}
