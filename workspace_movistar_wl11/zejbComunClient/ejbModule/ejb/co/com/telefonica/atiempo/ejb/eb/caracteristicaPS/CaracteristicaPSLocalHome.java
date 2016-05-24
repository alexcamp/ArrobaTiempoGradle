package ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS;
/**
 * Local Home interface for Enterprise Bean: CaracteristicaPS
 */
public interface CaracteristicaPSLocalHome extends javax.ejb.EJBLocalHome {
	String JNDI_NAME = "ejb/ejb/co/com/telefonica/atiempo/ejb/eb/caracteristicaPS/CaracteristicaPSLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: CaracteristicaPS
	 */
	public ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal create(
		java.lang.Integer id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: CaracteristicaPS
	 */
	public ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal findByPrimaryKey(
		java.lang.Integer primaryKey) throws javax.ejb.FinderException;
	public ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal findByCaracteristica(java.lang.String caracteristica, java.lang.Long psPeticion) throws javax.ejb.FinderException;
}