package co.com.telefonica.atiempo.ejb.eb;
import java.sql.SQLException;
import java.util.Vector;
/**
 * Local interface for Enterprise Bean: CausalNovedad
 */
public interface CausalNovedadLocal extends javax.ejb.EJBLocalObject {
	
	public Vector getPsQuiebreHist(Long petiNumero) throws SQLException;
}
