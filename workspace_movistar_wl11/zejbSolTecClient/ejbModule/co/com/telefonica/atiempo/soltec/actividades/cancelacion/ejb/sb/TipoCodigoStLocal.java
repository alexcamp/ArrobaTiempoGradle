package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
import javax.ejb.FinderException;

import co.com.telefonica.atiempo.soltec.dto.TipoCodigoDto;
/**
 * Local interface for Enterprise Bean: TipoCodigoSt
 */
public interface TipoCodigoStLocal extends javax.ejb.EJBLocalObject {
	public TipoCodigoDto getTipoCodigo(Integer idTipoCod)
		throws FinderException;
	public TipoCodigoDto getTipoCodigoByAtributo(String atributo)
		throws FinderException;
}
