package com.telefonica_chile.bandeja.bintegrada.session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
/**
 * @author lcaldera
 *
 */
public class TablaEsperaSigres extends Tabla
{
	private DBManager db = new DBManager();
	private static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public TablaEsperaSigres(int paginaActual,int orden)
	{
		super(paginaActual);
		db.setDataSource(DBManager.JDBC_BANDEJA);
		this.orden=orden;
	}
	
	protected long getNumeroTotalElementos(HashMap filtro) throws TablaException
	{
		int tot = getCantidadElementos(filtro);
		long aux = (long) tot;

		return aux;
	}

	protected List getElementosPagina(HashMap filtro) throws TablaException
	{
		String sqlWhereFijo = getWhereFijo(filtro);
		HashMap auxMap = getWhereVariable(filtro);
		String sqlOrden		= getOrden(super.orden);
	
		String sql = 		"select * from ( select b.COD_PET_CD,b.PETI_NUMERO,b.COD_LOC, b.COD_DPT,b.COD_SGM_CTA_CD,b.COD_SBG_CTA_CD," +
							"b.PETI_FECHA_INGRESO,b.ESPE_ID,b.PETI_FECHA_TERMINO,bi.BIPE_FECHA_INICIO, LOCA.NOMBRE_LOCALIDAD,DEPA.NOMBRE_DEPARTAMENTO," +
							"SEG.SEGM_DESCRIPCION,SUB.DESCRIPCION, ACT.ACT_DESCRIPCION, row_number() over( order by" + sqlOrden + ") as row" +
							" from vpistbba.peticion b left join atiempo.mensaje_estado_ba me on (me.PETI_NUMERO=b.PETI_NUMERO) " +
							" left join ATIEMPO.DEPARTAMENTO DEPA on (b.COD_DPT=DEPA.COD_DPT ) left join VPISTBBA.BITACORA_PETICION BI on (bi.PETI_NUMERO=B.PETI_NUMERO) " +
							" left join ATIEMPO.LOCALIDAD LOCA on (b.COD_LOC=LOCA.COD_LOC) " +
							" left join ATIEMPO.SEGMENTO SEG on (SEG.SEGM_ID=b.COD_SGM_CTA_CD)left join ATIEMPO.SUBSEGMENTO SUB on (b.COD_SBG_CTA_CD=SUB.SUBSEGM_ID)," +
							" ATIEMPO.ACTIVIDAD ACT " +
							" where	me.COD_ESTADO=3 and me.COD_ACTIVIDAD_GENERADORA in ('Director de Flujos.Configurar_Internet_Sigres.Configuracion_Internet_Sigres'," +
							" 'Director de Flujos.Obtener_cuneta_correo_SI.Obtener_Cuenta_Correo_SI'," +
							" 'Director de Flujos.Informar_Resultado_Instal_SI.Informar_Resultado_Instal_SI','Director de Flujos.Susp_Recon_SI.Susp_Recon_SI'," +
							" 'Director de Flujos.Desconfigurar_Int_SIGRES.Desconfigurar_Int_SIGRES','Director de Flujos.Mod_Identificador_Opera_SI.Mod_Identificador_Opera_SI')" +
							" and bi.BIPE_FECHA_FIN is null and bi.ACT_ID in (1090,1091,1092,1094,1095,1097)" +
							" And bi.ACT_ID = ACT.ACT_ID" 
							+ sqlWhereFijo 
							+ " ) subtabla where row >= "
							+ getIdxInicial()
							+ " and row <= "
							+ getIdxFinal()
							+ " "  ;

//			"select * from ( "
//				+ "   select b.COD_PET_CD,b.PETI_NUMERO,b.COD_LOC," +
//				  " b.COD_DPT,b.COD_SGM_CTA_CD,b.COD_SBG_CTA_CD," +
//				  " b.PETI_FECHA_INGRESO,b.ESPE_ID,b.PETI_FECHA_TERMINO,bi.BIPE_FECHA_INICIO," +
//				  " LOCA.NOMBRE_LOCALIDAD,DEPA.NOMBRE_DEPARTAMENTO,SEG.SEGM_DESCRIPCION,SUB.DESCRIPCION, ACT.ACT_DESCRIPCION, " +
//				  "  row_number() over( order by " + sqlOrden + " ) as row  "
//				+ " from vpistbba.peticion b,ATIEMPO.DEPARTAMENTO DEPA,ATIEMPO.LOCALIDAD LOCA,VPISTBBA.BITACORA_PETICION BI," +
//					" ATIEMPO.SEGMENTO SEG,ATIEMPO.SUBSEGMENTO SUB, ATIEMPO.ACTIVIDAD ACT, atiempo.mensaje_estado_ba me "
//				+ " where me.PETI_NUMERO=b.PETI_NUMERO AND b.COD_DPT=DEPA.COD_DPT " +
//					"  AND bi.PETI_NUMERO=B.PETI_NUMERO " +
//					" AND b.COD_LOC=LOCA.COD_LOC AND SEG.SEGM_ID=b.COD_SGM_CTA_CD AND b.COD_SBG_CTA_CD=SUB.SUBSEGM_ID  " +
//					"and me.COD_ESTADO=3 and me.COD_ACTIVIDAD_GENERADORA in ('Director de Flujos.Configurar_Internet_Sigres.Configuracion_Internet_Sigres','Director de Flujos.Obtener_cuneta_correo_SI.Obtener_Cuenta_Correo_SI','Director de Flujos.Informar_Resultado_Instal_SI.Informar_Resultado_Instal_SI','Director de Flujos.Susp_Recon_SI.Susp_Recon_SI'," +
//					"'Director de Flujos.Desconfigurar_Int_SIGRES.Desconfigurar_Int_SIGRES','Director de Flujos.Mod_Identificador_Opera_SI.Mod_Identificador_Opera_SI')"+
//					" and bi.BIPE_FECHA_FIN is null" +
//					" and bi.ACT_ID in (1090,1091,1092,1094,1095,1097) and bi.ACT_ID = ACT.ACT_ID"
//				+ sqlWhereFijo 
//				+ " ) subtabla where row >= "
//				+ getIdxInicial()
//				+ " and row <= "
//				+ getIdxFinal()
//				+ " "  ;

	
		log.debug("SQL->"+sql);

		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		Hashtable salida = db.selectReadUncommitted(sql.toString());
	
		String[] COD_PET_CD = (String[]) salida.get("COD_PET_CD");
		String[] PETI_NUMERO = (String[]) salida.get("PETI_NUMERO");
		String[] COD_LOC = (String[]) salida.get("COD_LOC");
		String[] COD_DPT = (String[]) salida.get("COD_DPT");
		String[] COD_SGM_CLI_CD = (String[]) salida.get("COD_SGM_CTA_CD");
		String[] COD_SBG_CLI_CD = (String[]) salida.get("COD_SBG_CTA_CD");
		String[] PETI_FECHA_INGRESO = (String[]) salida.get("PETI_FECHA_INGRESO");
		String[] PETI_FECHA_TERMINO = (String[]) salida.get("PETI_FECHA_TERMINO");
		String[] ESPE_ID = (String[]) salida.get("ESPE_ID");
		String[] NOMBRE_LOCALIDAD=(String[]) salida.get("NOMBRE_LOCALIDAD");
		String[] NOMBRE_DEPARTAMENTO=(String[]) salida.get("NOMBRE_DEPARTAMENTO");
		String[] SEGM_DESCRIPCION=(String[]) salida.get("SEGM_DESCRIPCION");
		String[] SUBGM_DESCRIPCION=(String[]) salida.get("DESCRIPCION");
		String[] ACT_DESCRIPCION=(String[]) salida.get("ACT_DESCRIPCION");
		String[] COD_SUB_ESTADO = (String[]) salida.get("COD_SUB_ESTADO");
	
		List lista = new ArrayList();
		for (int i = 0; PETI_NUMERO != null && i < PETI_NUMERO.length; i++)
		{
			PeticionDTO p = new PeticionDTO();
			p.setCod_pet_cd(new Long(COD_PET_CD[i]));
			p.setBiNroPeticion(new Long(PETI_NUMERO[i]));
			p.setDepartamento(NOMBRE_DEPARTAMENTO[i]);
			p.setLocalidad(NOMBRE_LOCALIDAD[i]);
			p.setSegmentoDescripcion(SEGM_DESCRIPCION[i]);
			p.setActividadDescripcion(ACT_DESCRIPCION[i]);

			if (PETI_FECHA_INGRESO != null && PETI_FECHA_INGRESO[i].trim().length() > 0)
			{
				Date fechaIngreso = parseFecha(PETI_FECHA_INGRESO[i]);
				p.setFechaInicio(new Fecha(fechaIngreso));
			}
			if (PETI_FECHA_TERMINO != null && PETI_FECHA_TERMINO[i].trim().length() > 0)
			{
				Date fechaTermino = parseFecha(PETI_FECHA_TERMINO[i]);
				p.setFechaFin(new Fecha(fechaTermino));
			}
			Integer valor=null;
			try
			{
				valor=new Integer(ESPE_ID[i]);
			}
			catch(NumberFormatException e)
			{
	
			}
			String desEstado="";
			if(valor!=null)
			{
				switch(valor.intValue())
				{
					case 1:
						desEstado="En Curso";
						break;
					case 2:
						desEstado="Terminada";
						break;
					case 3:
						desEstado="Cancelada";
						break;
				}
			}
			p.setEstadoPeticionNombre(desEstado);
			lista.add(p);
		}
		return lista;
	}

	private HashMap getWhereVariable(HashMap filtro)
	{
		return null;
	}

	private String getWhereFijo(HashMap filtro)
	{
		String whereFijo="";
	
		whereFijo += utilWhere("b.COD_SGM_CTA_CD", (String) filtro.get("SEGM_ID"), "N");
	
		whereFijo += utilWhere("b.PETI_FECHA_INGRESO",(String)filtro.get("fechaIni"),"FI");
		whereFijo += utilWhere("b.PETI_FECHA_INGRESO",(String)filtro.get("fechaFin"),"FH");
		log.debug("Cod_sub_estado " + filtro.get("COD_SUB_ESTADO"));
		if (filtro.get("COD_SUB_ESTADO").equals("null")){
			whereFijo += utilWhere("me.COD_SUB_ESTADO", (String) filtro.get("COD_SUB_ESTADO"), "IS");
		} else 
			whereFijo += utilWhere("me.COD_SUB_ESTADO", (String) filtro.get("COD_SUB_ESTADO"), "N");
		
		whereFijo += utilWhere("b.COD_DPT",(String)filtro.get("COD_DPT"),"S");
		whereFijo += utilWhere("b.COD_LOC",(String)filtro.get("COD_LOC"),"S");
		whereFijo += utilWhere("b.PETI_ID_INSTANCIA",(String)filtro.get("BI_FAMILIA_PS"),"S");
		whereFijo += utilWhere("bi.BIPE_FECHA_INICIO",(String)filtro.get("DIFDAYS"),"DD");
	
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

		if ("IS".equals(tipo))
			return (" and " + campo + " is " + valor);
			
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
		if("FI".equals(tipo))
		{
			try
			{
				Fecha fecha=new Fecha(valor,"dd/MM/yyyy");
				return (" and " + campo + " >= '" + fecha.getFechaconFormato(10)+"'");
			} catch (FechaException e)
			{
				e.printStackTrace();
			}
		}
		if("FH".equals(tipo))
		{
			try
			{
				Fecha fecha=new Fecha(valor,"dd/MM/yyyy");
				String fechaS=fecha.getYYYYMMDD();
				String horaminutoss="235959";
				Fecha nueva=new Fecha(fechaS+horaminutoss,"aaaammddhhmmss");
				return (" and " + campo + " <= '" + nueva.getFechaconFormato(10)+"'");
			} catch (FechaException e)
			{
				e.printStackTrace();
			}
		}
		if("DD".equals(tipo))
		{
			return " and day(current timestamp - "+campo+" ) >= " + valor;
		}
		return "";
	}


	private int getCantidadElementos(HashMap filtro)
	{
		int cantElementos = 0;
		String sqlWhereFijo = getWhereFijo(filtro);


		String sql = " select count(B.PETI_NUMERO) as TOTAL from vpistbba.peticion B,atiempo.mensaje_estado_ba me,vpistbba.bitacora_peticion bi" +
			" where me.COD_ESTADO=3 and me.COD_ACTIVIDAD_GENERADORA in ('Director de Flujos.Configurar_Internet_Sigres.Configuracion_Internet_Sigres','Director de Flujos.Obtener_cuneta_correo_SI.Obtener_Cuenta_Correo_SI','Director de Flujos.Informar_Resultado_Instal_SI.Informar_Resultado_Instal_SI','Director de Flujos.Susp_Recon_SI.Susp_Recon_SI'," +
			"'Director de Flujos.Desconfigurar_Int_SIGRES.Desconfigurar_Int_SIGRES','Director de Flujos.Mod_Identificador_Opera_SI.Mod_Identificador_Opera_SI')"+
			"  and me.PETI_NUMERO=b.PETI_NUMERO" +
			"  and bi.PETI_NUMERO=b.PETI_NUMERO" +
			"  and bi.ACT_ID in (1090,1091,1092,1094,1095,1097)" +
			"  and bi.BIPE_FECHA_FIN is null  " +
		sqlWhereFijo ;

		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);

		log.debug("SQL-->"+sql);


		Hashtable salida = db.selectReadUncommitted(sql);

		String[] Total = (String[]) salida.get("TOTAL");
		if (Total == null || Total.length == 0)
		{
			log.debug("Estoy retornando Cero");
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

	protected Date parseFecha(String fecha)
	{
		try
		{
			int sinMs = fecha.lastIndexOf(".");
			return formatoFecha.parse(fecha.substring(0, sinMs));
		} catch (Exception e) {
			log.debug("Problemas parseando fecha " + fecha + ". Default: Fecha Actual", e);
			return new Date();
		}
	}
	
	protected Object cast(String str, Object def, String obj) {
	if (str == null)
		return def;
	try {
		if ("LONG".equals(obj))
			return (new Long(str));
		if ("INTEGER".equals(obj))
			return (new Integer(str));
		if ("STRING".equals(obj))
			return (str);
	} catch (Exception e) {
		// No se pudo Trasnformar
	}

			return def;

		}
}
