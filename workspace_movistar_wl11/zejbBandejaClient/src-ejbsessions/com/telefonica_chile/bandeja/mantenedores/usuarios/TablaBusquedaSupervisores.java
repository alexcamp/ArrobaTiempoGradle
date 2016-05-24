package com.telefonica_chile.bandeja.mantenedores.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.bandeja.dto.UsuarioDTO;

public class TablaBusquedaSupervisores extends Tabla {

	public TablaBusquedaSupervisores(int paginaActual) {
		super(paginaActual);
	}

	protected long getNumeroTotalElementos(HashMap filtro) {
		StringBuffer where = getWhere(filtro);

		// 2) Calculo la cantidad de datos
		StringBuffer sql = new StringBuffer("select count(*) c from usuario u, usuario_rol ur where u.usua_id = ur.usua_id");
		if (where.length() > 0)
			sql.append(where);
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommit a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida =  db.selectReadUncommitted(sql.toString());
		String[] cantidad = (String[]) salida.get("C");
       
		return Long.parseLong(cantidad[0]);
	}

	protected List getElementosPagina(HashMap filtro) {
		StringBuffer where = getWhere(filtro);

		StringBuffer sql = new StringBuffer()
			.append("select * from (")
			.append(" select")
			.append(" 	u.usua_id,")
			.append(" 	u.usua_login,")
			.append(" 	u.usua_nombre,")
			.append("	row_number() over() as row")
			.append(" from")
			.append(" 	usuario u, usuario_rol ur")
			.append(" where")
			.append(" 	u.usua_id = ur.usua_id");

		if (where.length() > 0)
			sql.append(where);
		sql.append(") subtabla where ")
			.append(" row >= ").append(getIdxInicial()).append(" and row <= ").append(getIdxFinal());

		
		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		log.debug("get Elementos Pagina Tabla busqueda supervisores =>  " + sql.toString());
//		Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de selectCommit a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		String[] id = (String[]) salida.get("USUA_ID");
		String[] login = (String[]) salida.get("USUA_LOGIN");
		String[] nombre = (String[]) salida.get("USUA_NOMBRE");

		List lista = new ArrayList();
		for (int i = 0; id != null && i < id.length; i++) {
			UsuarioDTO u = new UsuarioDTO();
			u.setId(new Long(id[i]));
			u.setUsername(login[i]);
			u.setNombre(nombre[i]);
			lista.add(u);
		}
		
		return lista;
	}

	private StringBuffer getWhere(HashMap filtro) {
		StringBuffer where = new StringBuffer();

		if (filtro.containsKey("idRolSup"))
			where.append(" and ur.rol_id = ").append(filtro.get("idRolSup"));

		if (filtro.containsKey("idUsuSub")) {
			where.append(" and ur.usua_id != ").append(filtro.get("idUsuSub"))
				.append(" 	and u.usua_id not in")
				.append(" 		(select jer_sup_uid from jer_usuario_rol where jer_rol = ")
				.append(filtro.get("idRolSup")).append(" and jer_sub_uid = ")
				.append(filtro.get("idUsuSub")).append(")");
		}
		

		if (filtro.containsKey("usernameBusqueda"))
			where.append(" and u.usua_login like '%").append(filtro.get("usernameBusqueda")).append("%'");






		return where;
	}
}
