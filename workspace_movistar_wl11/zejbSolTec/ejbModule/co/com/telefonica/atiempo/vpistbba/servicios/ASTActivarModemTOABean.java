package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.ActivarModemTOAST;

/**
 * Bean implementation class for Enterprise Bean: ASTActivarModemTOA
 */
public class ASTActivarModemTOABean extends co.com.telefonica.atiempo.utiles.MDServicioBean
implements
javax.ejb.MessageDrivenBean,
javax.jms.MessageListener {

public IServicio getServicio() {
// TODO Auto-generated method stub
return new ActivarModemTOAST();
}
}