package co.com.telefonica.atiempo.sigres.emu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;
import co.com.telefonica.atiempo.sigres.emu.util.Utilidades;

/**
 * DaoService
 * 
 * Clase para acceso a la base de datos.
 * 
 * @author Gonzalo Arreche
 */
public class DaoService {
    private DataSource localDs;
    /** Nombre de los dataSource */
    public static final String JDBC_VPISTBBA="jdbc/vpistbba";
	public static final String JDBC_BANDEJA="jdbc/bandeja";
	public static final String JDBC_SOLTEC="jdbc/soltec";
    /** Nombre de la sequencia para los id de mensaje */
    private static final String ID_PETICION_SEQ = "CORRELATIVO_MENSAJES";
    /** Nombre del dataSource seleccionado*/
    private String selectedDataSource=null;
    
    public void init(){
        localDs = ServiceLocatorEmulator.getDataSource(getSelectedDataSource());
    }
    /**
     * getNextId
     * 
     * Devuelve el siguiente numero de peticion disponible.
     * 
     * @return
     */
    public long getNextId(){
        long id=0;
        Connection	conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = getNextFromSeq(ID_PETICION_SEQ);
            conn = localDs.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getLong(1)+1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Utilidades.closeDBConnection(conn, ps, rs);
        }
        return id;
    }
    /**
     * Obtiene el siguiente valor de una sequencia.
     * @param sequence es la sequencia
     * @return el valor
     */
    private String getNextFromSeq(String sequence){
        return "values nextval for " + sequence; 
    }
    private String getSelectedDataSource(){
        return selectedDataSource;
    }
    private void setSelectedDataSource(String name){
        selectedDataSource=name;
    }
}
