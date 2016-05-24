package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.DecoTarDTO;

/**
 * Key class for Entity Bean: Deco_tarjeta
 */
public class Deco_tarjetaKey implements java.io.Serializable {
	/**
	 * @param decoTarDTO
	 */
	public Deco_tarjetaKey(DecoTarDTO decoTarDTO,Long petiNumero)
	{
		this.peticion_peti_numero=petiNumero;
		this.id_deco=decoTarDTO.getDeco();
		this.id_tarjeta=decoTarDTO.getTarjeta();
	}
	
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_tarjeta
	 */
	public java.lang.String id_tarjeta;
	/**
	 * Implementation field for persistent attribute: id_deco
	 */
	public java.lang.String id_deco;
	/**
	 * Implementation field for persistent attribute: peticion_peti_numero
	 */
	public java.lang.Long peticion_peti_numero;
	/**
	 * Creates an empty key for Entity Bean: Deco_tarjeta
	 */
	public Deco_tarjetaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaKey) {
			co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaKey o =
				(co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaKey) otherKey;
			return (
				(this.id_tarjeta.equals(o.id_tarjeta))
					&& (this.id_deco.equals(o.id_deco))
					&& (this.peticion_peti_numero.equals(o.peticion_peti_numero)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			id_tarjeta.hashCode()
				+ id_deco.hashCode()
				+ peticion_peti_numero.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Deco_tarjeta
	 */
	public Deco_tarjetaKey(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticion) {
		this.id_tarjeta = id_tarjeta;
		this.id_deco = id_deco;
		privateSetPeticionKey(argPeticion);
	}
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionKey getPeticionKey() {
		co.com.telefonica.atiempo.ejb.eb.PeticionKey temp =
			new co.com.telefonica.atiempo.ejb.eb.PeticionKey();
		boolean peticion_NULLTEST = true;
		peticion_NULLTEST &= (peticion_peti_numero == null);
		temp.peti_numero = peticion_peti_numero;
		if (peticion_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetPeticionKey(
		co.com.telefonica.atiempo.ejb.eb.PeticionKey inKey) {
		boolean peticion_NULLTEST = (inKey == null);
		peticion_peti_numero = (peticion_NULLTEST) ? null : inKey.peti_numero;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Id Tarjeta:"+id_tarjeta+" -- Id Deco:"+id_deco;
	}

}
