package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: CausalNovedad
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Vector;

import co.com.atiempo.dto.EstadoPsPeticionDTO;

import com.tecnonautica.utiles.db.DBManager;

//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class CausalNovedadBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	private static final String sqlPsQuiebreHist	=	" select eps.COD_PS,ps.PS_NOMBRE,aa.ACT_DESCRIPCION,ca.DESCRIPCION_CAUSAL,cp.FECHA_TERMINO,uu.USUA_LOGIN,cp.NOVEDAD" +
	" from atiempo.estado_ps_peticion eps,atiempo.causal_peticion cp, " +
	" atiempo.producto_servicio ps,atiempo.actividad aa,atiempo.catalogo_causal ca,atiempo.usuario uu " +
	" where eps.PETI_NUMERO=? and eps.CORRELATIVO=cp.CORRELATIVO and eps.COD_PS=ps.PS_ID " +
	" and cp.COD_ACTIVIDAD=aa.ACT_ID and cp.COD_CAUSAL=ca.COD_CAUSAL and uu.USUA_ID=cp.USUA_ID ";
															
	public Vector getPsQuiebreHist(Long petiNumero)throws SQLException 
	{
		
		Vector psVec = new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			conn = DBManager.getConnection(DBManager.JDBC_BANDEJA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			pstmt=conn.prepareStatement(sqlPsQuiebreHist);
			pstmt.setLong(1,petiNumero.longValue());
			rs=pstmt.executeQuery();
			while(rs.next())
			{	
				EstadoPsPeticionDTO estadoPsPeticion = estadoPsPeticion = new EstadoPsPeticionDTO();
				estadoPsPeticion.setCodPs(new Long(rs.getLong("COD_PS")));
				estadoPsPeticion.setDescAct(rs.getString("ACT_DESCRIPCION"));
				estadoPsPeticion.setDescPs(rs.getString("PS_NOMBRE"));
				estadoPsPeticion.setNovedad(rs.getString("NOVEDAD"));
				estadoPsPeticion.setPetiNumero(petiNumero);
				estadoPsPeticion.setDescCausa(rs.getString("DESCRIPCION_CAUSAL"));
				estadoPsPeticion.setUsuaNombre(rs.getString("USUA_LOGIN"));
				estadoPsPeticion.setFechaTermino(rs.getString("FECHA_TERMINO"));
				psVec.add(estadoPsPeticion);
			}
			
		}
		finally
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		if(psVec.size()>0)
			Collections.sort(psVec);
		return psVec;
	}
}
