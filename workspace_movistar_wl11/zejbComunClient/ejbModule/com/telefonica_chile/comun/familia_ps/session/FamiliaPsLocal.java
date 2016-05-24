package com.telefonica_chile.comun.familia_ps.session;
import com.telefonica_chile.comun.familia_ps.dto.FamPsRemotoDTO;
/**
 * Local interface for Enterprise Bean: FamiliaPs
 */
public interface FamiliaPsLocal extends javax.ejb.EJBLocalObject {
	/**
	 * recuperarFamPs
	 */
	
	// CR16429 FindAll - ana santos
	//public java.util.ArrayList recuperarFamPs();
	
	public FamPsRemotoDTO getFamiliaPS(Long idFaPS);
}
