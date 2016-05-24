package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Valor_variable
 */
public abstract class Valor_variableBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Valor_variableKey ejbCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_valcampo_campo,
		co.com.telefonica.atiempo.ejb.eb.BintegradaLocal argFk_valor_bi)
		throws javax.ejb.CreateException {
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableKey argFk_valcampo_campoPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Campo_variableKey) argFk_valcampo_campo
				.getPrimaryKey();
		setFk_valcampo_campo_cv_id(argFk_valcampo_campoPK.cv_id);
		co.com.telefonica.atiempo.ejb.eb.BintegradaKey argFk_valor_biPK =
			(co.com.telefonica.atiempo.ejb.eb.BintegradaKey) argFk_valor_bi
				.getPrimaryKey();
		setFk_valor_bi_bi_id(argFk_valor_biPK.bi_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_valcampo_campo,
		co.com.telefonica.atiempo.ejb.eb.BintegradaLocal argFk_valor_bi)
		throws javax.ejb.CreateException {
		setFk_valcampo_campo(argFk_valcampo_campo);
		setFk_valor_bi(argFk_valor_bi);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Valor_variableKey ejbCreate(
		java.lang.Short fk_valcampo_campo_cv_id,
		java.lang.Long fk_valor_bi_bi_id)
		throws javax.ejb.CreateException {
		setFk_valcampo_campo_cv_id(fk_valcampo_campo_cv_id);
		setFk_valor_bi_bi_id(fk_valor_bi_bi_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Short fk_valcampo_campo_cv_id,
		java.lang.Long fk_valor_bi_bi_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Valor_variableKey ejbCreate(
		java.lang.Short fk_valcampo_campo_cv_id,
		java.lang.Long fk_valor_bi_bi_id,
		java.lang.String valor)
		throws javax.ejb.CreateException {
		setFk_valcampo_campo_cv_id(fk_valcampo_campo_cv_id);
		setFk_valor_bi_bi_id(fk_valor_bi_bi_id);
		setValor(valor);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Short fk_valcampo_campo_cv_id,
		java.lang.Long fk_valor_bi_bi_id,
		java.lang.String valor)
		throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Valor_variableKey ejbCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_valcampo_campo,
		co.com.telefonica.atiempo.ejb.eb.BintegradaLocal argFk_valor_bi,
		java.lang.String valor)
		throws javax.ejb.CreateException {
		setValor(valor);
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableKey argFk_valcampo_campoPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Campo_variableKey) argFk_valcampo_campo
				.getPrimaryKey();
		setFk_valcampo_campo_cv_id(argFk_valcampo_campoPK.cv_id);
		co.com.telefonica.atiempo.ejb.eb.BintegradaKey argFk_valor_biPK =
			(co.com.telefonica.atiempo.ejb.eb.BintegradaKey) argFk_valor_bi
				.getPrimaryKey();
		setFk_valor_bi_bi_id(argFk_valor_biPK.bi_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_valcampo_campo,
		co.com.telefonica.atiempo.ejb.eb.BintegradaLocal argFk_valor_bi,
		java.lang.String valor)
		throws javax.ejb.CreateException {
		setFk_valcampo_campo(argFk_valcampo_campo);
		setFk_valor_bi(argFk_valor_bi);
	}
	/**
	 * Get accessor for persistent attribute: valor
	 */
	public abstract java.lang.String getValor();
	/**
	 * Set accessor for persistent attribute: valor
	 */
	public abstract void setValor(java.lang.String newValor);
	/**
	 * This method was generated for supporting the relationship role named fk_valor_bi.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.BintegradaLocal getFk_valor_bi();
	/**
	 * This method was generated for supporting the relationship role named fk_valor_bi.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_valor_bi(
		co.com.telefonica.atiempo.ejb.eb.BintegradaLocal aFk_valor_bi);
	/**
	 * This method was generated for supporting the relationship role named fk_valcampo_campo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_variableLocal getFk_valcampo_campo();
	/**
	 * This method was generated for supporting the relationship role named fk_valcampo_campo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_valcampo_campo(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal aFk_valcampo_campo);
	/**
	 * Get accessor for persistent attribute: fk_valor_bi_bi_id
	 */
	public abstract java.lang.Long getFk_valor_bi_bi_id();
	/**
	 * Set accessor for persistent attribute: fk_valor_bi_bi_id
	 */
	public abstract void setFk_valor_bi_bi_id(
		java.lang.Long newFk_valor_bi_bi_id);
	/**
	 * Get accessor for persistent attribute: fk_valcampo_campo_cv_id
	 */
	public abstract java.lang.Short getFk_valcampo_campo_cv_id();
	/**
	 * Set accessor for persistent attribute: fk_valcampo_campo_cv_id
	 */
	public abstract void setFk_valcampo_campo_cv_id(
		java.lang.Short newFk_valcampo_campo_cv_id);
}
