/*
 * Created on 12-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.estructura_flujo;


/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DATOS_ATVPISTBBA {

	public static final String ini_es_bloqueante="INI_ES_BLOQUEANTE";
	public static final String act_ok="ACT_OK";
	public class RECEPCION{
		public static final String recep_es_cancela="RECEP_ES_CANCELA";
		public class VERIFICACION_FLUJO{
			public static final String vefl_ok="VEFL_OK";
		}
	}
	public class DIRECTOR_DE_FLUJOS{
		public static final String fluj_mult_asig_rec_stb="FLUJ_MULT_ASIG_REC_STB";
		public static final String fluj_mult_conf_stb="FLUJ_MULT_CONF_STB";
		public static final String fluj_mult_conf_ri="FLUJ_MULT_CONF_RI";
		public static final String fluj_mult_conf_ba="FLUJ_MULT_CONF_BA";
		public static final String fluj_mult_conf_int="FLUJ_MULT_CONF_INT";
		public static final String fluj_mult_conf_dth="FLUJ_MULT_CONF_DTH";	
		public static final String fluj_mult_conf_terra="FLUJ_MULT_CONF_TERRA";
		public static final String fluj_mult_obt_conf_ba_terra="FLUJ_MULT_OBT_CONF_BA_TERRA";
		public static final String fluj_mult_obt_ptos_red="FLUJ_MULT_OBT_PTOS_RED";
		public static final String fluj_mult_obt_ptos_red_ba="FLUJ_MULT_OBT_PTOS_RED_BA";
		public static final String fluj_mult_inst="FLUJ_MULT_INST";
		public static final String fluj_mult_desconf_stb="FLUJ_MULT_DESCONF_STB";
		public static final String fluj_mult_desconf_ri="FLUJ_MULT_DESCONF_RI";
		public static final String fluj_mult_desconf_int="FLUJ_MULT_DESCONF_INT";
		public static final String fluj_mult_desinst="FLUJ_MULT_DESINST";
		public static final String fluj_mult_desinst_dth="FLUJ_MULT_DESINST_DTH";
		public static final String fluj_mult_act_inv_stb="FLUJ_MULT_ACT_INV_STB";
		public static final String fluj_mult_act_inv_ss="FLUJ_MULT_ACT_INV_SS";
		public static final String fluj_mult_act_inv_ba="FLUJ_MULT_ACT_INV_BA";
		public static final String fluj_mult_act_inv_dth="FLUJ_MULT_ACT_INV_DTH";
		public static final String fluj_mult_pgi="FLUJ_MULT_PGI";
		public static final String fluj_mult_cierre_peticion="FLUJ_MULT_CIERRE_PETICION";
		public static final String fluj_cierre="FLUJ_CIERRE";
		public static final String fluj_reversa="FLUJ_REVERSA";
		public static final String primera_reversa="PRIMERA_REVERSA";
		public static final String fluj_act_sig_forza="FLUJ_ACT_SIG_FORZA";
		public static final String fluj_orden="FLUJ_ORDEN";
		public static final String primer_quiebre="PRIMER_QUIEBRE";
		public static final String hay_asignacion_recursos="HAY_ASIGNACION_RECURSOS";
		public static final String hay_recursos_ba="HAY_RECURSOS_BA";
		public static final String fluj_mult_auto_inst="FLUJ_MULT_AUTO_INST";
		
		public class ASIGNACION_RECURSOS{
			public static final String asig_manual_stb="ASIG_MANUAL_STB";
			public static final String gestion_recursos_stb="GESTION_RECURSOS_STB";
		}
		public class INSTALACION{
			public static final String inst_ok="INST_OK";
		}
		public class DESINSTALACION{
			public static final String desinst_ok="DESINST_OK";
		}
		public class DESINSTALACION_DTH{
			public static final String desinst_dth_ok="DESINST_DTH_OK";
		}
		public class PGI{
			public static final String pgi_ok="PGI_OK";
		}
		public class PGC{
			public static final String pgc_ok="PGC_OK"; 
		}
		public class CONFIGURACION_TERRA{
			public static final String control_terra="CONTROL_TERRA"; 
		}

		//CR 4860 - OBTENER CUENTA DE CORREO - GUSTAVOG- 28/04/08
		public static final String hay_cuenta_correo="HAY_CUENTA_CORREO";
		public static final String informa_resultado_instalacion = "INFORMA_RESULTADO_INSTALACION";

		/*
        adocarmo - 9664 - descomentar el método de abajo cuando entienda cómo funciona el flujo
        */
		public class CONFIGURACION_AULA{
			public static final String control_aula="FLUJ_MULT_CONF_INT"; 
		}
				
	}
	public class CANCELACION{
		public static final String recep_es_ok_anulacion="RECEP_ES_OK_ANULACION";
	}
	
	/**
	 * 
	 */
	public DATOS_ATVPISTBBA() {
		super();
		// TODO Auto-generated constructor stub
	}

}