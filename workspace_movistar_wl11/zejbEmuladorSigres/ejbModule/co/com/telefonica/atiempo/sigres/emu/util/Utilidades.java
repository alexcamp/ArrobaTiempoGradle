package co.com.telefonica.atiempo.sigres.emu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Utilidades
 * 
 * Clase de utilitarios del Emulador.
 * 
 * @author Gonzalo Arreche
 *  
 */
public class Utilidades {

    /*public static void enviarMensaje(String mensaje, String JNDIName) {
        TextMessage message = new TextMessage();
        try {
            message.setText(mensaje);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }*/

    public static void closeDBConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
        }
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
