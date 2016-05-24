package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tipo_agendamiento_segmento
 */
public interface Tipo_agendamiento_segmentoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_agendamiento_segmento
	 */
	static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Tipo_agendamiento_segmentoLocalHome";

	public co.com.telefonica.atiempo.ejb.eb.Tipo_agendamiento_segmentoLocal create(java.lang.Long tasg_id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tipo_agendamiento_segmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tipo_agendamiento_segmentoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Tipo_agendamiento_segmentoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_agendamiento_segmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tipo_agendamiento_segmentoLocal create(java.lang.Long tasg_id, java.lang.Integer grse_id, java.lang.Long tiag_id)
		throws javax.ejb.CreateException;

	public java.util.Collection findTipoAgendamiento(java.lang.Integer idGrupoSegmento, java.lang.String operacionComercial) throws javax.ejb.FinderException;
	/**
	 * @return
	 */
	public Tipo_agendamiento_segmentoLocal findTipoAgendamientoNulo() throws javax.ejb.FinderException;

}
