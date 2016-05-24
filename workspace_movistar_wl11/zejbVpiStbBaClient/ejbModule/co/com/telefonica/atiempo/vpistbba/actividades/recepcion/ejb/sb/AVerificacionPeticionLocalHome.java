package co.com.telefonica.atiempo.vpistbba.actividades.recepcion.ejb.sb;



/**
 * Local Home interface for Enterprise Bean: AVerificacionPeticion
 */
public interface AVerificacionPeticionLocalHome
	extends javax.ejb.EJBLocalHome {

	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/actividades/recepcion/ejb/sb/AVerificacionPeticionLocalHome";
	
	public AVerificacionPeticionLocal create()
		throws javax.ejb.CreateException;
				
}
