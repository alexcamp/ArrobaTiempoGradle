package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
/**
 * Key class for Entity Bean: Catalago_prueba_linea
 */
public class Catalago_prueba_lineaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_prueba
	 */
	public java.lang.Integer cod_prueba;
	/**
	 * Implementation field for persistent attribute: familia_producto_servicio_st_faps_id
	 */
	public java.lang.Long familia_producto_servicio_st_faps_id;
	/**
	 * Creates an empty key for Entity Bean: Catalago_prueba_linea
	 */
	public Catalago_prueba_lineaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co
				.com
				.telefonica
				.atiempo
				.soltec
				.actividades
				.cancelacion
				.ejb
				.sb
				.Catalago_prueba_lineaKey) {
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.actividades
				.cancelacion
				.ejb
				.sb
				.Catalago_prueba_lineaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.actividades
					.cancelacion
					.ejb
					.sb
					.Catalago_prueba_lineaKey) otherKey;
			return (
				(this.cod_prueba.equals(o.cod_prueba))
					&& (this
						.familia_producto_servicio_st_faps_id
						.equals(o.familia_producto_servicio_st_faps_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			cod_prueba.hashCode()
				+ familia_producto_servicio_st_faps_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Catalago_prueba_linea
	 */
	public Catalago_prueba_lineaKey(
		java.lang.Integer cod_prueba,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stKey argFamilia_producto_servicio_st) {
		this.cod_prueba = cod_prueba;
		privateSetFamilia_producto_servicio_stKey(argFamilia_producto_servicio_st);
	}
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stKey getFamilia_producto_servicio_stKey() {
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stKey temp =
			new co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Familia_producto_servicio_stKey();
		boolean familia_producto_servicio_st_NULLTEST = true;
		familia_producto_servicio_st_NULLTEST
			&= (familia_producto_servicio_st_faps_id == null);
		temp.faps_id = familia_producto_servicio_st_faps_id;
		if (familia_producto_servicio_st_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFamilia_producto_servicio_stKey(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stKey inKey) {
		boolean familia_producto_servicio_st_NULLTEST = (inKey == null);
		familia_producto_servicio_st_faps_id =
			(familia_producto_servicio_st_NULLTEST) ? null : inKey.faps_id;
	}
}
