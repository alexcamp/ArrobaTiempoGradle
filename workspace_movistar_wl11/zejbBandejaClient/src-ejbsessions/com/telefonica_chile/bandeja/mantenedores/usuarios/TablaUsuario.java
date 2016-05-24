package com.telefonica_chile.bandeja.mantenedores.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.bandeja.dto.UsuarioDTO;

public class TablaUsuario extends Tabla {

	public TablaUsuario(int paginaActual) {
		super(paginaActual);
	}

	protected long getNumeroTotalElementos(HashMap filtro) {
		StringBuffer where = getWhere(filtro);

		// 2) Calculo la cantidad de datos
		StringBuffer sql = new StringBuffer("select count(*) c from usuario where 1 = 1");
		if (where.length() > 0)
			sql.append(where);
		DBManager db = new DBManager();
		db.setDataSource(DBManager.JDBC_BANDEJA);
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de ejecutaConsultaHash a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		String[] cantidad = (String[]) salida.get("C");

		return Long.parseLong(cantidad[0]);
	}

	protected List getElementosPagina(HashMap filtro) {
		StringBuffer where = getWhere(filtro);

		StringBuffer sql = new StringBuffer()
		.append("select * from (")
			.append(" select")
			.append(" 	usua_id,")
			.append(" 	usua_login,")
			.append(" 	usua_nombre,")
			.append(" 	usua_rut,")
			.append(" 	usua_email,")
			.append(" 	usua_habilitado,")
			.append("	row_number() over() as row")
			.append(" from")
			.append(" 	usuario")
			.append(" where")
			.append(" 	1 = 1");
		if (where.length() > 0)
			sql.append(where);
		sql.append(") subtabla where ")
			.append(" row >= ").append(getIdxInicial()).append(" and row <= ").append(getIdxFinal());

		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecución de ejecutaConsultaHash a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida = db.selectReadUncommitted(sql.toString());
		String[] id = (String[]) salida.get("USUA_ID");
		String[] login = (String[]) salida.get("USUA_LOGIN");
		String[] nombre = (String[]) salida.get("USUA_NOMBRE");
		String[] rut = (String[]) salida.get("USUA_RUT");
		String[] email = (String[]) salida.get("USUA_EMAIL");
		String[] habilitado = (String[]) salida.get("USUA_HABILITADO");

		List lista = new ArrayList();
		for (int i = 0; id != null && i < id.length; i++) {
			UsuarioDTO u = new UsuarioDTO();
			u.setId(new Long(id[i]));
			u.setUsername(login[i]);
			u.setNombre(nombre[i]);
			u.setRut(rut[i]);
			u.setEmail(email[i]);
			u.setHabilitado(habilitado[i]);
			lista.add(u);
		}
		
		return lista;
	}


	private StringBuffer getWhere(HashMap filtro) {
		StringBuffer where = new StringBuffer();
		if (filtro.containsKey("FILTRO_ID"))
			where.append(" and usua_id = ").append(filtro.get("FILTRO_ID"));
		if (filtro.containsKey("FILTRO_LOGIN"))
			where.append(" and usua_login like '%").append(filtro.get("FILTRO_LOGIN")).append("%'");
		return where;
	}
}
