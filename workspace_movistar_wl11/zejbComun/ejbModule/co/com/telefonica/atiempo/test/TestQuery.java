/*
 * Created on Dec 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author guido
 *
 * CR17800 - Clase para testeo masivo de queries. Lanza 10, 100 o N queries a la BD, 
 * logueando todo los tiempos a un archivo de log (mediante log4j).
 */
public class TestQuery {
	private static Logger log = Logger.getLogger(TestQuery.class);

	public static void main(String[] args) throws Exception {
//		String s = "a,b, c";
//		String[] valores = s.split(",");
//		System.out.println("valores: '" + valores + "'");
//		System.exit(0);
		
		PropertyConfigurator.configure("/home/atiemweb/etc/log4j-query.properties");
		log.info("------------- Starting Test Query "+TEST_NAME+" -----------------");
		TestQuery app = new TestQuery();
		Connection conn = null;
		try {
			conn = app.getConn();
			app.run(conn);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private void run(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select count( b.BI_ID ) AS TOTAL from (select * from ATIEMPO.BINTEGRADA where bi_visible=1  and ap_id = 2 and usua_id=1) B " +
//						" inner join " +  
//						" (SELECT max(pl.id_prueba), pl.cod_ave_cd  " + 
//						//"--(SELECT distinct pl.cod_ave_cd " 
//						" FROM soltec.catalogo_prueba_linea cpl, soltec.prueba_linea pl " + 
//						" WHERE cpl.tipo_incidencia in ('M' , 'I') " +
//						" 	and cpl.faps_id = pl.faps_id " +
//						"	and cpl.cod_prueba = pl.cod_prueba " + 
//						" group by cod_ave_cd" +
//						") tipo_inci on tipo_inci.cod_ave_cd = B.bi_nro_peticion";
//		String sql = "select count( b.BI_ID ) AS TOTAL from (select * from ATIEMPO.BINTEGRADA where bi_visible=1  and ap_id = 2 and usua_id=1) B  " +
//						" inner join " +
//						" (SELECT pl.cod_ave_cd " +
//						" FROM soltec.catalogo_prueba_linea cpl, soltec.prueba_linea pl " +
//						" WHERE cpl.tipo_incidencia = 'M' " +
//						" 	and cpl.faps_id = pl.faps_id " +
//						" 	and cpl.cod_prueba = pl.cod_prueba " +
//						" 	and pl.id_prueba in " +
//						" 		(select max(id_prueba) " +
//						" 		from soltec.prueba_linea pl " +
//						" 		group by cod_ave_cd)) tipo_inci on tipo_inci.cod_ave_cd = B.bi_nro_peticion ";
		// Sin optimizar tipo_inci
		//String sql1 = "select * from ( select B.bi_id, B.bi_cliente_nombre, B.bi_cliente_apellidos, B.bi_cliente_area, B.bi_cliente_servicio, B.bi_url_detalle, B.bi_fecha_inicio, bi_grupo_segmento, B.bi_fecha_apertura, B.bi_fecha_asignacion, B.bi_fecha_compromiso, B.ap_id, B.bi_nro_peticion , B.usua_id,B.COD_LOC,B.BI_NRO_PETICION_ATIS,B.BI_AGRUPACIONES,B.DESC_LOCALIDAD,B.BI_FAMILIA_PS,B.DESC_CENTRAL,B.SEGM_DESCRIPCION,B.SUBSEGM_DESCRIPCION,B.BI_CLIENTE_RUT,B.BI_CLIENTE_RUTDV,  A.rol_id, A.act_id, A.act_codigo, A.act_descripcion, A.act_nombre_reversa, B.bi_estado_peticion, B.bi_tipo_trabajo, B.bi_id_tipo_agendamiento, B.bi_id_rango, B.bi_hora_desde, B.bi_hora_hasta, row_number() over( order by  B.bi_fecha_apertura ASC  )     as row   from (select * from ATIEMPO.BINTEGRADA where bi_visible=1  and ap_id = 2 and usua_id=";
		//		"809" +
		//String sql2 = ")                                    B inner join ATIEMPO.ACTIVIDAD A on B.act_id=A.act_id  inner join (SELECT pl.cod_ave_cd FROM soltec.catalogo_prueba_linea cpl, soltec.prueba_linea pl WHERE cpl.tipo_incidencia = 'I' and cpl.faps_id = pl.faps_id and cpl.cod_prueba = pl.cod_prueba and pl.id_prueba in (select max(id_prueba) from soltec.prueba_linea pl group by cod_ave_cd)) tipo_inci on tipo_inci.cod_ave_cd = B.bi_nro_peticion  ) subtabla where row >= 1 and row <= 50";

		// Con optimizacion tipo_inci
		//String sql1 = "select * from ( select B.bi_id, B.bi_cliente_nombre, B.bi_cliente_apellidos, B.bi_cliente_area, B.bi_cliente_servicio, B.bi_url_detalle, B.bi_fecha_inicio, bi_grupo_segmento, B.bi_fecha_apertura, B.bi_fecha_asignacion, B.bi_fecha_compromiso, B.ap_id, B.bi_nro_peticion , B.usua_id,B.COD_LOC,B.BI_NRO_PETICION_ATIS,B.BI_AGRUPACIONES,B.DESC_LOCALIDAD,B.BI_FAMILIA_PS,B.DESC_CENTRAL,B.SEGM_DESCRIPCION,B.SUBSEGM_DESCRIPCION,B.BI_CLIENTE_RUT,B.BI_CLIENTE_RUTDV,  A.rol_id, A.act_id, A.act_codigo, A.act_descripcion, A.act_nombre_reversa, B.bi_estado_peticion, B.bi_tipo_trabajo, B.bi_id_tipo_agendamiento, B.bi_id_rango, B.bi_hora_desde, B.bi_hora_hasta, row_number() over( order by  b.BI_NRO_PETICION_ATIS DESC  ) as row   from (select * from ATIEMPO.BINTEGRADA where bi_visible=1  and ap_id = 2 and usua_id= ";
		//		"?" +
		//String sql2 =	") B inner join ATIEMPO.ACTIVIDAD A on B.act_id=A.act_id  inner join (SELECT distinct pl.cod_ave_cd  FROM soltec.catalogo_prueba_linea cpl, soltec.prueba_linea pl  WHERE cpl.tipo_incidencia = 'I'  and cpl.faps_id = pl.faps_id  and cpl.cod_prueba = pl.cod_prueba ) tipo_inci on tipo_inci.cod_ave_cd = B.bi_nro_peticion  ) subtabla where row >= 1  and row <= 50";
		
		//SQL comun
		String sql1 = "select * from ( select B.bi_id, B.bi_cliente_nombre, B.bi_cliente_apellidos, B.bi_cliente_area, B.bi_cliente_servicio, B.bi_url_detalle, B.bi_fecha_inicio, bi_grupo_segmento, B.bi_fecha_apertura, B.bi_fecha_asignacion, B.bi_fecha_compromiso, B.ap_id, B.bi_nro_peticion , B.usua_id,B.COD_LOC,B.BI_NRO_PETICION_ATIS,B.BI_AGRUPACIONES,B.DESC_LOCALIDAD,B.BI_FAMILIA_PS,B.DESC_CENTRAL,B.SEGM_DESCRIPCION,B.SUBSEGM_DESCRIPCION,B.BI_CLIENTE_RUT,B.BI_CLIENTE_RUTDV,  A.rol_id, A.act_id, A.act_codigo, A.act_descripcion, A.act_nombre_reversa, B.bi_estado_peticion, B.bi_tipo_trabajo, B.bi_id_tipo_agendamiento, B.bi_id_rango, B.bi_hora_desde, B.bi_hora_hasta, row_number() over( order by  b.BI_NRO_PETICION_ATIS DESC  ) as row   from (select * from ATIEMPO.BINTEGRADA where bi_visible=1  and               usua_id=";
		//		"836" +
		String sql2 =		")                                    B inner join ATIEMPO.ACTIVIDAD A on B.act_id=A.act_id                                                                                                                                                                                                                                                                                                                                                     ) subtabla where row >= 1 and row <= 10";
		int max = 1807, count=0, results=0;
		
		long executeTime, start, totalETime=0, resultTime, totalRTime=0;
		String sql = sql1 + "XXX" + sql2; 
		log.info("Executing: " + sql);
		try {
			for (int i = 808; i <= max ; i++) {
				count++;
				//sql = sql1 + i + sql2;
				sql = sql1 + "?" + sql2; 
				start = System.currentTimeMillis();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				executeTime = System.currentTimeMillis() - start;
				if (rs.next()) {
					results++;
				}
				rs.close();
				rs = null;
				pstmt.close();
				pstmt=null;
				
				resultTime = System.currentTimeMillis() - start;
				
				totalETime = totalETime + executeTime;
				totalRTime = totalRTime + resultTime;
				if (count <= 3 || count%10==0) {
					log.debug("Execution number " + count + " \t executeTime=" + executeTime + " \t Res&ExTime=" + resultTime + " \tresults=" + results + " i=" + i);
				}
			}
			log.info("============ Results of '" + TEST_NAME + "' =============");
			log.info("Number of executed queries    : " + count);
			log.info("Number of queries with results: " + results);
			log.info("Avg execute time              : " + (totalETime/count) + " " + TEST_NAME);
			log.info("Avg execute + result time     : " + (totalRTime/count));
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}

		
	}
	private static final String TEST_NAME = " [TEST_NAME] cambiando usua_id CON prepared stmt 1000 veces - sql standard ... - db pre-prod";
	public Connection getConn() throws SQLException, ClassNotFoundException {
		long start = System.currentTimeMillis();
		//DB local
		//String url = "jdbc:db2://172.50.55.52:50000/ATIEM_CO";
//		String user = "db2admin";
//		String pwd = "Tcs12345";
		
		// DB pre-prod
		String url = "";
		String user = "";
		String pwd = "";
		log.info("Connecting to url=" + url + " user=" + user);
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		Connection con = DriverManager.getConnection(url, user, pwd);
		long interval = System.currentTimeMillis() - start;
		log.debug("Get connection time=" + (interval/1000) + " sec.");
		return con;
	}
}
