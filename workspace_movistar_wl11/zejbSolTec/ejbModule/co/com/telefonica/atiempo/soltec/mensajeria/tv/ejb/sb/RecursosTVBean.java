package co.com.telefonica.atiempo.soltec.mensajeria.tv.ejb.sb;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ConectorKey;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.Cards;
import co.com.telefonica.atiempo.interfaces.atiempo.Equipment;
import co.com.telefonica.atiempo.interfaces.atiempo.ErrorAux;
import co.com.telefonica.atiempo.interfaces.atiempo.TR016E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR016S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR019E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR019S;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.dto.EquipoStDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Familia_producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.ActualizacionInventarioTVMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudConfiguracionServiciosTVMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudInformacionTecnicaTVMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudSTDecoTarjActualizarMQ;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: RecursosTV
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same
// information - dregueira - May 20, 2009
public class RecursosTVBean extends
		co.com.telefonica.atiempo.utiles.SessionBeanAdapter implements
		RecursosTVInterfaces {

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment -
	// atipoldi - May 25, 2009
	//		   private Mensaje_estado_stLocalHome msgEstadoSTLocalHome ;
	//		   private Peticion_stLocalHome peticionStLocalHome ;
	//		   private Dslam_apsc_lineaLocalHome dslam_apscLocalHome;
	//		   private Servicio_basico_supl_apsc_lineaLocalHome
	// servicio_basico_supl_apsc_lineaLocalHome;
	//		   private ConectorLocalHome conectorLocalHome;
	//		   private TematicoLocalHome tematicoLocalHome;
	//		   private Deco_tarjetaLocalHome deco_tarjetaLocalHome;

	//TODO Prueba
	private Tmp_deco_tarjetaLocalHome tmpDecoTarjetaLocalHome;

	private DBManager dbSeq;

	private SimpleDateFormat df;

	/*
	 * Metodo modificador del Session EJB (non-Javadoc)
	 * 
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(SessionContext ctx) throws EJBException,
			RemoteException {
		super.setSessionContext(ctx);
		// Creacion de DataSource
		dbSeq = new DBManager();
		dbSeq.setDataSource(DBManager.JDBC_SOLTEC);

		// TODO: revisar este formato
		df = new SimpleDateFormat("dd/MM/yyyy");

		buscaHome();
	}

	/*
	 * Metodo Generador de Home
	 */
	private void buscaHome() {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		//			   try {
		//
		//			   // Creacion de los Home
		//			   msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome)
		// HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
		//			   peticionStLocalHome = (Peticion_stLocalHome)
		// HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
		//			   conectorLocalHome = (ConectorLocalHome)
		// HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
		//
		//				tematicoLocalHome=(TematicoLocalHome)HomeFactory.getHome(TematicoLocalHome.JNDI_NAME);
		//				deco_tarjetaLocalHome=(Deco_tarjetaLocalHome)HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
		//				tmpDecoTarjetaLocalHome
		// =(Tmp_deco_tarjetaLocalHome)HomeFactory.getHome(Tmp_deco_tarjetaLocalHome.JNDI_NAME);
		//
		//			   } catch (NamingException e) {
		//				   log.error(" Creacion de Local Home Nulls",e);
		//			   }
	}

	/*
	 * Metodo validador Home
	 */
	//		   private void validaHome ()
	//		   throws ATiempoAppEx{
	//		   	
	//			   // Validacion de los Home
	//			   if (
	//				   msgEstadoSTLocalHome == null
	//				   || peticionStLocalHome == null
	//				   || conectorLocalHome == null
	//				   || deco_tarjetaLocalHome == null
	//				   //TODO Prueba
	//				   || tmpDecoTarjetaLocalHome == null
	//
	//
	//				   )
	//				   throw new ATiempoAppEx (ATiempoAppEx.NAMING);
	//		   }

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosTVInterfaces#solicitudInformacionTecnica(java.lang.String,
	 *      java.lang.String)
	 */
	public void solicitudInformacionTecnica(String peticion, String id_actividad)
			throws ATiempoAppEx {
		try {

			//TCS - CR25137 - Eliminación Homes de Instancia - Performance
			// Assessment - atipoldi - May 25, 2009
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
					.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory
					.getHome(ConectorLocalHome.JNDI_NAME);

			Long idPeticion = new Long(peticion);

			if (this.hayListaDecoTarjetasTrabajadas(idPeticion)) {//Si ya
																  // configure
																  // decos
				return; //No busco nueva configuracion. pk tengo que actualizar
						// el inventario con lo que hice
			}

			TR019E tr019e = new TR019E();
			Long IdCorrelativo = new Long(dbSeq
					.seqNextValLong("CORRELATIVO_MENSAJE"));
			Peticion_stKey peticion_stkey = new Peticion_stKey(idPeticion);
			Peticion_stLocal peticionLocal = peticionStLocalHome
					.findByPrimaryKey(peticion_stkey);
			Collection producto_servicio_peticionstArray = peticionLocal
					.getProducto_servicio_peticion();

			//Proceso para la validacion de los tipos de ps, que estan en el
			// mensaje
			StringBuffer append = new StringBuffer();
			boolean bip = true;
			Producto_servicio_peticionLocal producto_servicio_peticionstLocal = null;
			Producto_servicio_stKey productoServicostKey = null;

			for (Iterator iter = producto_servicio_peticionstArray.iterator(); iter
					.hasNext();) {

				producto_servicio_peticionstLocal = (Producto_servicio_peticionLocal) iter
						.next();
				productoServicostKey = (Producto_servicio_stKey) producto_servicio_peticionstLocal
						.getProducto_servicio_st().getPrimaryKey();

				if (bip) {
					append.append(productoServicostKey.ps_id);
					bip = false;
				} else {
					append.append(" ," + productoServicostKey.ps_id.toString());
				}

			}

			//TODO: Se debe realizar una Validacion de los PS

			tr019e.setId(IdCorrelativo.toString());
			tr019e.setAtisRequestNumber(peticion_stkey.cod_ave_cd.longValue());
			//Identificador Producto
			tr019e.setPcId(peticionLocal.getNum_ide_nu_tv());

			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado
			// Linea
			Mensaje_estado_stLocal mensajeEstadoLocal = msgEstadoSTLocalHome
					.create(IdCorrelativo);
			mensajeEstadoLocal.setPeti_numero(peticion_stkey.cod_ave_cd);

			Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal
					.getProducto_servicio_st()
					.getFamilia_producto_servicio_st().getPrimaryKey();
			mensajeEstadoLocal.setCod_familia_ps(new Integer(
					familia_producto_serviciostKey.faps_id.intValue()));

			ConectorKey conectorKey = (ConectorKey) conectorLocalHome
					.findByPrimaryKey(
							new ConectorKey(new Integer(codigoConectorCuatro)))
					.getPrimaryKey();
			mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);

			mensajeEstadoLocal.setFecha_envio(df.format(new java.util.Date()));
			mensajeEstadoLocal.setCod_estado(new Integer(estadoEsperaManual));
			mensajeEstadoLocal.setCod_actividad_generadora(id_actividad);
			//TODO: Revisar luego estos campos y su mapeo.
			if (peticionLocal.getCod_are_ges_cd() != null)
				mensajeEstadoLocal.setArea(new Integer(peticionLocal
						.getCod_are_ges_cd().intValue()));
			if (peticionLocal.getTel_cot_ds() != null)
				mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());

			mensajeEstadoLocal.setPeticion_st(peticionLocal);
			SolicitudInformacionTecnicaTVMQ solicitudInformacionTecnicaTVMQ = new SolicitudInformacionTecnicaTVMQ();
			solicitudInformacionTecnicaTVMQ.enviarMensajeReplyToQ(tr019e);

		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			log.error("FinderException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException e) {
			log.error("CreateException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosTVInterfaces#recepcionInformacionTecnica(co.com.telefonica.atiempo.interfaces.atiempo.TR019S)
	 */
	public void recepcionInformacionTecnica(TR019S tr019s) throws ATiempoAppEx {
		//	TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			// busca el registro del mensaje
			Mensaje_estado_stLocal mensajeEstadoTv = null;
			try {
				Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
						.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
				mensajeEstadoTv = msgEstadoSTLocalHome
						.findByPrimaryKey(new Mensaje_estado_stKey(new Long(
								tr019s.getId())));
			} catch (FinderException e) {
				mensajeEstadoTv = null;
			}

			if (mensajeEstadoTv == null) {
				log.warn("No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr019s));
				throw new ATiempoAppEx(
						"No Existe Respuesta en la base de enviados:"
								+ tr019s.getId(), ATiempoAppEx.EXCEPTION);
			}

			// Validacion del estado de la respuesta

			if (mensajeEstadoTv.getCod_estado().intValue() == estadoOk) {
				log
						.warn("El estado de la respuesta es diferente para ser procesado\n "
								+ XMLUtilities.marshall(tr019s));
				return;
			}

			Peticion_stKey pKey = (Peticion_stKey) mensajeEstadoTv
					.getPeticion_st().getPrimaryKey();
			Long idPeticion = pKey.cod_ave_cd;

			// veamos si hay un error
			if (tr019s.isError() || tr019s.getErrorCode() != 0) {
				//Mensaje_estadoLocal mensajeEstadoError =
				// msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new
				// Integer(estadoError))) ;
				mensajeEstadoTv.setCod_estado(new Integer(estadoError));
			} else {
				TematicoLocalHome tematicoLocalHome = (TematicoLocalHome) HomeFactory
						.getHome(TematicoLocalHome.JNDI_NAME);
				Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
						.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
				Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome = (Deco_Tarjeta_Info_SapLocalHome) HomeFactory
						.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);

				// el mensaje se proceso OK (ver el
				mensajeEstadoTv.setCod_estado(new Integer(estadoOk));

				//Borro los tematicos
				Collection temas = tematicoLocalHome.findByPeticion(idPeticion);
				for (Iterator iterTP = temas.iterator(); iterTP.hasNext();) {
					TematicoLocal temaTmp = (TematicoLocal) iterTP.next();
					temaTmp.remove();
				}

				Iterator iterPackage = tr019s.getPackages().iterator();
				//					Iterator iterPackage = tr019s.getPackages
				// ().getIdPackage().iterator();
				//Creo los nuevo tematicos
				while (iterPackage.hasNext()) {
					// para no confundir con la clase estandar de Java
					String _package = (String) iterPackage.next();
					Long idTematico = new Long(dbSeq
							.seqNextValLong("CORRELATIVO_TEMATICO"));
					TematicoLocal tematico = tematicoLocalHome.create(
							idTematico, "2", mensajeEstadoTv.getPeticion_st(),
							_package, null);
				}

				//Borro los equipos
				Collection ekipos = deco_tarjetaLocalHome
						.findPeticion(idPeticion);
				for (Iterator iterTE = ekipos.iterator(); iterTE.hasNext();) {
					Deco_tarjetaLocal ekipoTmp = (Deco_tarjetaLocal) iterTE
							.next();
					ekipoTmp.remove();
				}

				/* RQ.8595 - mfmendez */
				//Borro los datos de sap de los equipos
				Collection datosSAP = deco_tar_inf_sapLocalHome
						.findByPeticion(idPeticion);
				for (Iterator iterSAP = datosSAP.iterator(); iterSAP.hasNext();) {
					Deco_Tarjeta_Info_SapLocal infoSAPTmp = (Deco_Tarjeta_Info_SapLocal) iterSAP
							.next();
					infoSAPTmp.remove();
				}
				/* FIN - RQ.8595 - mfmendez */

				Iterator iterEquipsments = tr019s.getEquipments().iterator();

				Iterator iterEquipsments1 = tr019s.getEquipments().iterator();
				//creo los nuevos ekipos

				if (iterEquipsments1.hasNext()) {
					Equipment equipment = (Equipment) iterEquipsments1.next();

					if (equipment.getCard() == null
							&& equipment.getDeco() == null
							&& equipment.getDdtvSerial() == null) {

						ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
						IActividadEJB actividadEJB = actividadFactoryEJB
								.getActividad(mensajeEstadoTv
										.getCod_actividad_generadora());
						ActividadEJBDTO actDto = actividadEJB
								.getActividadEJBDTO(idPeticion, mensajeEstadoTv
										.getCod_actividad_generadora());
						log
								.debug("Actividad generadora: "
										+ mensajeEstadoTv
												.getCod_actividad_generadora());
						if (STConfig.getVariable(
								"COD_ACTIVIDAD_REC_BA_AUTOMATICA").equals(
								mensajeEstadoTv.getCod_actividad_generadora())) {
							actDto
									.setDato(
											DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba,
											"PE");
							actDto
									.setObservacion("Se redirige a Planta Externa Automáticamente");
							actDto.setRealizarTerminoInmediato(true);
							actDto.setGrabaEnBitacora(false);
						} else if (STConfig.getVariable(
								"COD_ACTIVIDAD_REC_TV_AUTOMATICA").equals(
								mensajeEstadoTv.getCod_actividad_generadora())) {

							actDto
									.setDato(
											DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv,
											"");
							actDto
									.setObservacion("Se redirige a Planta Externa Automáticamente");
							actDto.setRealizarTerminoInmediato(true);
							actDto.setGrabaEnBitacora(false);
						} else if (STConfig.getVariable(
								"COD_ACTIVIDAD_REC_STB_AUTOMATICA").equals(
								mensajeEstadoTv.getCod_actividad_generadora())) {

							actDto
									.setDato(
											DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb,
											"");
							actDto
									.setObservacion("Se redirige a Planta Externa Automáticamente por acción masiva");
							actDto.setRealizarTerminoInmediato(true);
							actDto.setGrabaEnBitacora(false);
						}

					}
				}
				while (iterEquipsments.hasNext()) {
					Equipment equipment2 = (Equipment) iterEquipsments.next();
					Deco_tarjetaLocal decoTarjeta = deco_tarjetaLocalHome
							.create(
									equipment2.getCard(),
									equipment2.getDeco(),
									((Peticion_stKey) mensajeEstadoTv
											.getPeticion_st().getPrimaryKey()).cod_ave_cd);
					// le pondremos accion de consulta y estado Ok
					decoTarjeta.setAccion(new Integer(accionParActivoCas));
					decoTarjeta.setEstado(new Integer(estadoParOk));
					decoTarjeta.setOriginal(new Integer(1));
					decoTarjeta
							.setDeco_reference(equipment2.getDecoReference());
					decoTarjeta.setSerial_deco(equipment2.getDecoSerial());
					decoTarjeta.setDeco_marca(equipment2.getDecoMarca());
					decoTarjeta.setSerial_tarjeta("");
					decoTarjeta.setCodigo_deco("");

					/* Datos de disco duro */
					decoTarjeta.setSerial_ddtv(equipment2.getDdtvSerial());
					decoTarjeta.setMarca_ddtv(equipment2.getDdtvMarca());
					decoTarjeta.setModelo_ddtv(equipment2.getDdtvModelo());

					/* RQ.8595 - mfmendez */
					decoTarjeta
							.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
					// Datos de la Tarjeta
					if ((equipment2.getCardPostingDateSAP() != null && !equipment2
							.getCardPostingDateSAP().equals(""))
							|| (equipment2.getCardMoveTypeSAP() != null && !equipment2
									.getCardMoveTypeSAP().equals(""))
							|| (equipment2.getCardMaterialCodeSAP() != null && !equipment2
									.getCardMaterialCodeSAP().equals(""))
							|| (equipment2.getCardMaterialSAP() != null && !equipment2
									.getCardMaterialSAP().equals(""))
							|| (equipment2.getCardPlantSAP() != null && !equipment2
									.getCardPlantSAP().equals(""))
							|| (equipment2.getCardStorageSAP() != null && !equipment2
									.getCardStorageSAP().equals(""))
							|| (equipment2.getCardBatchCodeSAP() != null && !equipment2
									.getCardBatchCodeSAP().equals(""))
							|| (equipment2.getCardMeasurementUnitSAP() != null && !equipment2
									.getCardMeasurementUnitSAP().equals(""))
							|| (equipment2.getCardCostCenterSAP() != null && !equipment2
									.getCardCostCenterSAP().equals(""))
							|| (equipment2.getCardFuncAreaLongSAP() != null && !equipment2
									.getCardFuncAreaLongSAP().equals(""))
							|| (equipment2.getCardPepElementSAP() != null && !equipment2
									.getCardPepElementSAP().equals(""))) {

						Deco_Tarjeta_Info_SapLocal dec_tar_sap_cardLocal = deco_tar_inf_sapLocalHome
								.create(equipment2.getCard(),
										((Peticion_stKey) mensajeEstadoTv
												.getPeticion_st()
												.getPrimaryKey()).cod_ave_cd);
						dec_tar_sap_cardLocal.setFec_cont_sap(equipment2
								.getCardPostingDateSAP());
						dec_tar_sap_cardLocal.setClase_mov_sap(equipment2
								.getCardMoveTypeSAP());
						if (equipment2.getCardMaterialCodeSAP() != null)
							dec_tar_sap_cardLocal.setPos_doc_sap(Integer
									.parseInt(equipment2
											.getCardMaterialCodeSAP()));
						else
							dec_tar_sap_cardLocal.setPos_doc_sap(0);
						dec_tar_sap_cardLocal.setNum_material_sap(equipment2
								.getCardMaterialSAP());
						dec_tar_sap_cardLocal.setCentro_sap(equipment2
								.getCardPlantSAP());
						dec_tar_sap_cardLocal.setAlmacen_sap(equipment2
								.getCardStorageSAP());
						dec_tar_sap_cardLocal.setCod_lote_sap(equipment2
								.getCardBatchCodeSAP());
						dec_tar_sap_cardLocal.setUnd_medida_sap(equipment2
								.getCardMeasurementUnitSAP());
						dec_tar_sap_cardLocal.setCentr_cost_sap(equipment2
								.getCardCostCenterSAP());
						dec_tar_sap_cardLocal.setArea_func_sap(equipment2
								.getCardFuncAreaLongSAP());
						dec_tar_sap_cardLocal.setElement_pep_sap(equipment2
								.getCardPepElementSAP());
						dec_tar_sap_cardLocal.setFlag_mat_sap(equipment2
								.getCardFlagMatSAP());
					}
					// Datos del Deco
					if ((equipment2.getDecoPostingDateSAP() != null && !equipment2
							.getDecoPostingDateSAP().equals(""))
							|| (equipment2.getDecoMoveTypeSAP() != null && !equipment2
									.getDecoMoveTypeSAP().equals(""))
							|| (equipment2.getDecoMaterialCodeSAP() != null && !equipment2
									.getDecoMaterialCodeSAP().equals(""))
							|| (equipment2.getDecoMaterialSAP() != null && !equipment2
									.getDecoMaterialSAP().equals(""))
							|| (equipment2.getDecoPlantSAP() != null && !equipment2
									.getDecoPlantSAP().equals(""))
							|| (equipment2.getDecoStorageSAP() != null && !equipment2
									.getDecoStorageSAP().equals(""))
							|| (equipment2.getDecoBatchCodeSAP() != null && !equipment2
									.getDecoBatchCodeSAP().equals(""))
							|| (equipment2.getDecoMeasurementUnitSAP() != null && !equipment2
									.getDecoMeasurementUnitSAP().equals(""))
							|| (equipment2.getDecoCostCenterSAP() != null && !equipment2
									.getDecoCostCenterSAP().equals(""))
							|| (equipment2.getDecoFuncAreaLongSAP() != null && !equipment2
									.getDecoFuncAreaLongSAP().equals(""))
							|| (equipment2.getDecoPepElementSAP() != null && !equipment2
									.getDecoPepElementSAP().equals(""))) {

						Deco_Tarjeta_Info_SapLocal dec_tar_sapdecoLocal = deco_tar_inf_sapLocalHome
								.create(equipment2.getDeco(),
										((Peticion_stKey) mensajeEstadoTv
												.getPeticion_st()
												.getPrimaryKey()).cod_ave_cd);
						dec_tar_sapdecoLocal.setFec_cont_sap(equipment2
								.getDecoPostingDateSAP());
						dec_tar_sapdecoLocal.setClase_mov_sap(equipment2
								.getDecoMoveTypeSAP());
						if (equipment2.getDecoMaterialCodeSAP() != null)
							dec_tar_sapdecoLocal.setPos_doc_sap(Integer
									.parseInt(equipment2
											.getDecoMaterialCodeSAP()));
						else
							dec_tar_sapdecoLocal.setPos_doc_sap(0);
						dec_tar_sapdecoLocal.setNum_material_sap(equipment2
								.getDecoMaterialSAP());
						dec_tar_sapdecoLocal.setCentro_sap(equipment2
								.getDecoPlantSAP());
						dec_tar_sapdecoLocal.setAlmacen_sap(equipment2
								.getDecoStorageSAP());
						dec_tar_sapdecoLocal.setCod_lote_sap(equipment2
								.getDecoBatchCodeSAP());
						dec_tar_sapdecoLocal.setUnd_medida_sap(equipment2
								.getDecoMeasurementUnitSAP());
						dec_tar_sapdecoLocal.setCentr_cost_sap(equipment2
								.getDecoCostCenterSAP());
						dec_tar_sapdecoLocal.setArea_func_sap(equipment2
								.getDecoFuncAreaLongSAP());
						dec_tar_sapdecoLocal.setElement_pep_sap(equipment2
								.getDecoPepElementSAP());
						dec_tar_sapdecoLocal.setFlag_mat_sap(equipment2
								.getDecoFlagMatSAP());
					}
					/* FIN - RQ.8595 - mfmendez */
				}
			}
			String cod_actividad_generadora = mensajeEstadoTv
					.getCod_actividad_generadora();
			if (cod_actividad_generadora == null
					|| "".equals(cod_actividad_generadora.trim())) {
				//No cierro niguna actividad por que es solo un refresco
				return;
			}
			// TODO ver si hay que retornar alguna variable al WF
			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB
					.getActividad(cod_actividad_generadora);
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(
					idPeticion, cod_actividad_generadora);

			if (tr019s.isError() || tr019s.getErrorCode() != 0) {

				//Raúl Triviño: Req 5935_Ajuste_Flujo_IT_Cons_Rec_APSC
				//actDto.setDato(
				// DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
			//	actDto.setObservacion("Error en la Obtencion de Recursos TV."
				//		+ tr019s.getErrorMessage());
				
				//REQ TOA FASE III Dcardena
				//METODO QUE ENVIA A PGI LA AVERIA
				RecursosDelegate rd = new RecursosDelegate();
				rd.hayPGIAveria(Long.toString(tr019s.getErrorCode()),tr019s.getErrorMessage(),"TR019S",mensajeEstadoTv.getPeti_numero().toString(),mensajeEstadoTv.getCod_actividad_generadora()  ,mensajeEstadoTv);
				return;
				//FIN REQ TOA FASE III 
			}
			actividadEJB.terminar(actDto);

		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (FinderException e) {
			log.error("FinderException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (CreateException e) {
			log.error("CreateException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (TnProcesoExcepcion e) {
			log.error("TnProcesoExcepcion:", e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS);
		} catch (EJBException e) {
			log.error("EJBException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION);
		} catch (RemoveException e) {
			log.error("RemoveException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.REMOVEEXCEPTION);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}

	}

	/*
	 * (no Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#enviaActualizacionInventarioTV(long,
	 *      java.lang.String)
	 */
	public boolean enviaActualizacionInventarioTV(long idPeticion,
			String idActividad) throws ATiempoAppEx {
		boolean hayEquipos = true;
		try {
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance
			// Assessment - atipoldi - May 25, 2009
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
					.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
					.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory
					.getHome(ConectorLocalHome.JNDI_NAME);
			Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome = (Deco_Tarjeta_Info_SapLocalHome) HomeFactory
					.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);

			//Mensaje_estadoLocal
			// mensajeManualLocal=msgEstadoLocalHome.findByPrimaryKey(new
			// Mensaje_estadoKey(new Integer(estadoEsperaManual)));

			TR018E tr018e = new TR018E();
			Peticion_stKey peticion_stkey = new Peticion_stKey(new Long(
					idPeticion));
			Peticion_stLocal peticionLocal = peticionStLocalHome
					.findByPrimaryKey(peticion_stkey);
			Collection producto_servicio_peticionstArray = peticionLocal
					.getProducto_servicio_peticion();

			Collection decocard = deco_tarjetaLocalHome
					.findPeticion(peticion_stkey.cod_ave_cd);

			//Marco los decos y tarjeta originales y los activos
			HashMap decoOri = new HashMap();
			HashMap tarjOri = new HashMap();
			HashMap decoAct = new HashMap();
			HashMap tarjAct = new HashMap();
			Iterator iterEq = decocard.iterator();
			while (iterEq.hasNext()) {
				Deco_tarjetaLocal deco_tarjetaLocal = (Deco_tarjetaLocal) iterEq
						.next();
				Deco_tarjetaKey deco_tarjetaKey = (Deco_tarjetaKey) deco_tarjetaLocal
						.getPrimaryKey();
				if (deco_tarjetaLocal.isOriginal()
						&& (deco_tarjetaLocal.getAccion().intValue() != ComunInterfaces.accionParActivar)) {
					decoOri.put(deco_tarjetaKey.id_deco,
							deco_tarjetaKey.id_deco);
					tarjOri.put(deco_tarjetaKey.id_tarjeta,
							deco_tarjetaKey.id_tarjeta);
				}
				if (deco_tarjetaLocal.estaActivo()) {
					decoAct.put(deco_tarjetaKey.id_deco,
							deco_tarjetaKey.id_deco);
					tarjAct.put(deco_tarjetaKey.id_tarjeta,
							deco_tarjetaKey.id_tarjeta);
				}
			}

			//Agrego decos y tarjetas a actualizar inventario
			Collection cards = new ArrayList();
			Collection decos = new ArrayList();
			Iterator iterActInv = decocard.iterator();
			/* RQ.8595 - mfmendez */
			// Variables para Decos
			Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
			Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
			// Variables para Tarjetas
			Deco_Tarjeta_Info_SapLocal infoSAPCard = null;
			Deco_Tarjeta_Info_SapKey keyInfoSAPCard = null;
			/* FIN - RQ.8595 - mfmendez */
			while (iterActInv.hasNext()) {

				TR018EEquipment equipment = new TR018EEquipment();
				Cards card = new Cards();

				Deco_tarjetaLocal deco_tarjetaLocal = (Deco_tarjetaLocal) iterActInv
						.next();
				Deco_tarjetaKey deco_tarjetaKey = (Deco_tarjetaKey) deco_tarjetaLocal
						.getPrimaryKey();

				if (!deco_tarjetaLocal.estaActivo()
						&& !deco_tarjetaLocal.estaEliminado()
						&& !deco_tarjetaLocal.estaEliminadoDanhado()) { // si un
																		// deco
																		// fue
																		// activado
																		// o
																		// eliminado,
																		// se
																		// nvia
																		// el
																		// mensaje
																		// de
																		// act
																		// inventario
					continue;
				} else {
					equipment.setCasId(deco_tarjetaKey.id_deco);
					equipment.setAction(deco_tarjetaLocal.getAccion()
							.intValue()); // Se envia Liberado o danhado
					equipment.setDdtvSerial(deco_tarjetaLocal.getSerial_ddtv());
					equipment.setDdtvMarca(deco_tarjetaLocal.getMarca_ddtv());
					equipment.setDdtvModelo(deco_tarjetaLocal.getModelo_ddtv());
					/* RQ.8595 - mfmendez */
					// Datos del Deco
					try {
						keyInfoSAPTmp = new Deco_Tarjeta_Info_SapKey(
								deco_tarjetaKey.id_deco,
								peticion_stkey.cod_ave_cd);
						infoSAPTmp = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome
								.findByPrimaryKey(keyInfoSAPTmp);

						if (infoSAPTmp.getFec_cont_sap() != null)
							equipment.setPostingDateSAP(infoSAPTmp
									.getFec_cont_sap());
						else
							equipment.setPostingDateSAP("");

						if (infoSAPTmp.getClase_mov_sap() != null)
							equipment.setMoveTypeSAP(infoSAPTmp
									.getClase_mov_sap());
						else
							equipment.setMoveTypeSAP("");

						equipment.setMaterialCodeSAP(Integer
								.toString(infoSAPTmp.getPos_doc_sap()));

						if (infoSAPTmp.getNum_material_sap() != null)
							equipment.setMaterialSAP(infoSAPTmp
									.getNum_material_sap());
						else
							equipment.setMaterialSAP("");

						if (infoSAPTmp.getCentro_sap() != null)
							equipment.setPlantSAP(infoSAPTmp.getCentro_sap());
						else
							equipment.setPlantSAP("");

						if (infoSAPTmp.getAlmacen_sap() != null)
							equipment
									.setStorageSAP(infoSAPTmp.getAlmacen_sap());
						else
							equipment.setStorageSAP("");

						if (infoSAPTmp.getCod_lote_sap() != null)
							equipment.setBatchCodeSAP(infoSAPTmp
									.getCod_lote_sap());
						else
							equipment.setBatchCodeSAP("");

						if (infoSAPTmp.getUnd_medida_sap() != null)
							equipment.setMeasurementUnitSAP(infoSAPTmp
									.getUnd_medida_sap());
						else
							equipment.setMeasurementUnitSAP("");

						if (infoSAPTmp.getCentr_cost_sap() != null)
							equipment.setCostCenterSAP(infoSAPTmp
									.getCentr_cost_sap());
						else
							equipment.setCostCenterSAP("");

						if (infoSAPTmp.getArea_func_sap() != null)
							equipment.setFuncAreaLongSAP(infoSAPTmp
									.getArea_func_sap());
						else
							equipment.setFuncAreaLongSAP("");

						if (infoSAPTmp.getElement_pep_sap() != null)
							equipment.setPepElementSAP(infoSAPTmp
									.getElement_pep_sap());
						else
							equipment.setPepElementSAP("");

						if (infoSAPTmp.getFlag_mat_sap() != null)
							equipment.setFlagMatSAP(infoSAPTmp
									.getFlag_mat_sap());
						else
							equipment.setFlagMatSAP("");

					} catch (FinderException e) {
						log.debug("No se encontraron Decos para deco con id: "
								+ deco_tarjetaKey.id_deco
								+ ". Y id de peticion: "
								+ peticion_stkey.cod_ave_cd);
						equipment.setPostingDateSAP("");
						equipment.setMoveTypeSAP("");
						equipment.setMaterialCodeSAP("");
						equipment.setMaterialSAP("");
						equipment.setPlantSAP("");
						equipment.setStorageSAP("");
						equipment.setBatchCodeSAP("");
						equipment.setMeasurementUnitSAP("");
						equipment.setCostCenterSAP("");
						equipment.setFuncAreaLongSAP("");
						equipment.setPepElementSAP("");
						equipment.setFlagMatSAP("");
					}
					/* FIN - RQ.8595 - mfmendez */
					card.setCardSerial(deco_tarjetaKey.id_tarjeta);
					card.setAction(deco_tarjetaLocal.getAccion().intValue());

					/* RQ.8595 - mfmendez */
					// Datos de la tarjeta
					try {
						keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(
								deco_tarjetaKey.id_tarjeta,
								peticion_stkey.cod_ave_cd);
						infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome
								.findByPrimaryKey(keyInfoSAPCard);

						if (infoSAPCard.getFec_cont_sap() != null)
							card.setPostingDateSAP(infoSAPCard
									.getFec_cont_sap());
						else
							card.setPostingDateSAP("");

						if (infoSAPCard.getClase_mov_sap() != null)
							card.setMoveTypeSAP(infoSAPCard.getClase_mov_sap());
						else
							card.setMoveTypeSAP("");

						card.setMaterialCodeSAP(Integer.toString(infoSAPCard
								.getPos_doc_sap()));

						if (infoSAPCard.getNum_material_sap() != null)
							card.setMaterialSAP(infoSAPCard
									.getNum_material_sap());
						else
							card.setMaterialSAP("");

						if (infoSAPCard.getCentro_sap() != null)
							card.setPlantSAP(infoSAPCard.getCentro_sap());
						else
							card.setPlantSAP("");

						if (infoSAPCard.getAlmacen_sap() != null)
							card.setStorageSAP(infoSAPCard.getAlmacen_sap());
						else
							card.setStorageSAP("");

						if (infoSAPCard.getCod_lote_sap() != null)
							card.setBatchCodeSAP(infoSAPCard.getCod_lote_sap());
						else
							card.setBatchCodeSAP("");

						if (infoSAPCard.getUnd_medida_sap() != null)
							card.setMeasurementUnitSAP(infoSAPCard
									.getUnd_medida_sap());
						else
							card.setMeasurementUnitSAP("");

						if (infoSAPCard.getCentr_cost_sap() != null)
							card.setCostCenterSAP(infoSAPCard
									.getCentr_cost_sap());
						else
							card.setCostCenterSAP("");

						if (infoSAPCard.getArea_func_sap() != null)
							card.setFuncAreaLongSAP(infoSAPCard
									.getArea_func_sap());
						else
							card.setFuncAreaLongSAP("");

						if (infoSAPCard.getElement_pep_sap() != null)
							card.setPepElementSAP(infoSAPCard
									.getElement_pep_sap());
						else
							card.setPepElementSAP("");

						if (infoSAPCard.getFlag_mat_sap() != null)
							card.setFlagMatSAP(infoSAPCard.getFlag_mat_sap());
						else
							card.setFlagMatSAP("");

					} catch (FinderException e) {
						log
								.debug("No se encontraron Tarjetas para Card con id: "
										+ deco_tarjetaKey.id_tarjeta
										+ ". Y id de peticion: "
										+ peticion_stkey.cod_ave_cd);
						card.setPostingDateSAP("");
						card.setMoveTypeSAP("");
						card.setMaterialCodeSAP("");
						card.setMaterialSAP("");
						card.setPlantSAP("");
						card.setStorageSAP("");
						card.setBatchCodeSAP("");
						card.setMeasurementUnitSAP("");
						card.setCostCenterSAP("");
						card.setFuncAreaLongSAP("");
						card.setPepElementSAP("");
						card.setFlagMatSAP("");
					}
					/* FIN - RQ.8595 - mfmendez */
					//						Algoritmo, actualizar inventario:
					//						1.Si es activo y es original, no se agrega.
					//						2.Si es activo y en nuevo, se envia
					//						3.Si es eliminado y es original, se agrega siempre y
					// cuando no este entre los decos activos
					//						4.Si es eliminado y es nuevo, no se agrega
					if (deco_tarjetaLocal.estaActivo()) {
						if (!decoOri.containsKey(deco_tarjetaKey.id_deco)) {
							decos.add(equipment);
						}
						if (!tarjOri.containsKey(deco_tarjetaKey.id_tarjeta)) {
							cards.add(card);
						}
					} else {//es eliminacion de originales siempre y cuando no
							// este en la lista de activos
						if (decoOri.containsKey(deco_tarjetaKey.id_deco)) {
							if (!decoAct.containsKey(deco_tarjetaKey.id_deco)) {
								decos.add(equipment);
							}
						}
						if (tarjOri.containsKey(deco_tarjetaKey.id_tarjeta)) {
							if (!tarjAct
									.containsKey(deco_tarjetaKey.id_tarjeta)) {
								cards.add(card);
							}
						}
					}
				}
			}
			// si no hay nada que actualizar en el inventario, se cierre la
			// actividad de una
			if (cards.size() < 1 && decos.size() < 1) {
				hayEquipos = false;
				return hayEquipos;
			}

			Long IdCorrelativo = new Long(dbSeq
					.seqNextValLong("CORRELATIVO_MENSAJE"));

			tr018e.setId(IdCorrelativo.toString());
			tr018e.setAtisRequestNumber(idPeticion);
//			if (peticionLocal.getNum_ide_nu() == null
//					|| peticionLocal.getNum_ide_nu().equals("")) {
				tr018e.setPcId(peticionLocal.getNum_ide_nu_tv());
//			} else {
//				tr018e.setPcId(peticionLocal.getNum_ide_nu());
//			}
			tr018e.setCards(cards);
			tr018e.setEquipments(decos);

			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado
			// Linea
			Mensaje_estado_stLocal mensajeEstadoLocal = msgEstadoSTLocalHome
					.create(IdCorrelativo);
			mensajeEstadoLocal.setPeti_numero(new Long(idPeticion));

			//				Familia_producto_servicio_stKey familia_producto_serviciostKey =
			// (Familia_producto_servicio_stKey)
			// producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
			mensajeEstadoLocal.setCod_familia_ps(new Integer(familiaTV));

			ConectorKey conectorKey = (ConectorKey) conectorLocalHome
					.findByPrimaryKey(
							new ConectorKey(new Integer(codigoConectorTres)))
					.getPrimaryKey();
			mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);

			mensajeEstadoLocal.setFecha_envio(df.format(new java.util.Date()));
			mensajeEstadoLocal.setCod_estado(new Integer(estadoEsperaManual));
			mensajeEstadoLocal.setCod_actividad_generadora(idActividad);
			//TODO: Revisar luego estos campos y su mapeo.
			if (peticionLocal.getCod_are_ges_cd() != null)
				mensajeEstadoLocal.setArea(new Integer(peticionLocal
						.getCod_are_ges_cd().intValue()));
			if (peticionLocal.getTel_cot_ds() != null)
				mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());

			mensajeEstadoLocal.setPeticion_st(peticionLocal);

			ActualizacionInventarioTVMQ actualizacionInventarioTV = new ActualizacionInventarioTVMQ();
			actualizacionInventarioTV.enviarMensajeReplyToQ(tr018e);

		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			log.error("FinderException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException e) {
			log.error("CreateException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}

		return hayEquipos;

	}

	/*
	 * (no Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#recibeActualizacionInventarioTV(co.com.telefonica.atiempo.interfaces.atiempo.TR018S)
	 */
	public void recibeActualizacionInventarioTV(TR018S tr018s)
			throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			// busca el registro del mensaje
			Mensaje_estado_stLocal mensajeEstadoTv = null;
			try {
				Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
						.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
				mensajeEstadoTv = msgEstadoSTLocalHome
						.findByPrimaryKey(new Mensaje_estado_stKey(new Long(
								tr018s.getId())));
			} catch (FinderException e) {
				mensajeEstadoTv = null;
			}
			if (mensajeEstadoTv == null) {
				log.warn("No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr018s));
				throw new ATiempoAppEx(
						"No Existe Respuesta en la base de enviados:"
								+ tr018s.getId(), ATiempoAppEx.EXCEPTION);
			}

			Peticion_stLocal peticion = mensajeEstadoTv.getPeticion_st();
			Peticion_stKey peticionKey = (Peticion_stKey) peticion
					.getPrimaryKey();

			// Validacion del estado de la respuesta

			if (mensajeEstadoTv.getCod_estado().intValue() == estadoOk) {
				log
						.warn("El estado de la respuesta es diferente para ser procesado\n "
								+ XMLUtilities.marshall(tr018s));
				return;
			}

			Long idPeticion = peticionKey.cod_ave_cd;

			// veamos si hay un error
			String cod_actividad_generadora = mensajeEstadoTv
					.getCod_actividad_generadora();
			if (tr018s.isError() || tr018s.getErrorCode() != 0) {
				//TODO reenviar?
				//					Mensaje_estadoLocal mensajeEstadoError =
				// msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new
				// Integer(estadoError))) ;

				mensajeEstadoTv.setCod_estado(new Integer(estadoError));

				ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB
						.getActividad(cod_actividad_generadora);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(
						idPeticion, cod_actividad_generadora);
				actDto
						.setDato(
								DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_TV.control_act_inv_tv,
								"S");
				String msgError = "Error en la actualizacion de Inventario.";
				if (tr018s.getErrorCode() != 0) { //Si hay error Code...
					msgError = msgError + "Error Code:" + tr018s.getErrorCode()
							+ ".";
					if (tr018s.getErrorCodeMessage() != null
							&& tr018s.getErrorCodeMessage().length() > 0) {
						msgError = msgError + tr018s.getErrorCodeMessage()
								+ ".";
					}
				}
				if (tr018s.isError()) {
					msgError = msgError + tr018s.getErrorMessage() + ".";
				}
				actDto.setObservacion(msgError);
				actividadEJB.terminar(actDto);
			} else {
				// el mensaje se proceso OK (ver el
				mensajeEstadoTv.setCod_estado(new Integer(estadoOk));

				ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB
						.getActividad(cod_actividad_generadora);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(
						idPeticion, cod_actividad_generadora);
				actDto
						.setDato(
								DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_TV.control_act_inv_tv,
								"N");
				actividadEJB.terminar(actDto);
			}

		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (TnProcesoExcepcion e) {
			log.error("TnProcesoExcepcion:", e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}

	}

	// ----------------------------------------------- PRUEBA
	// -----------------------------------

	/**
	 * solicita a HC una lista de decos y tarjetas cuyos numero de series
	 * terminan con 4 digitos
	 * 
	 * esta rutina es llamada directamente desde la interfaz al usuario
	 * 
	 * @param idPeticion
	 *            el id de la peticion
	 * @param ult4digitosTarjeta
	 *            ultimos 4 digitos del numero de la tarjeta
	 * @param ult4digitosDeco
	 *            ultimos 4 digitos del numero de serie del deco
	 * @param idContratista
	 *            id del contratista
	 */
	// PVR se modifico para que el buscar tenga el tipo Deco
	public Long enviaDecoTarjetaSTPorUtilizar(long idPeticion,
			String ult4digitosTarjeta, String ult4digitosDeco,
			long idContratista, String tipoDeco) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009

		try {
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
					.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			tmpDecoTarjetaLocalHome = (Tmp_deco_tarjetaLocalHome) HomeFactory
					.getHome(Tmp_deco_tarjetaLocalHome.JNDI_NAME);

			// obtiene un nuevo id de mensaje

			Long idCorrelativoMensaje = new Long(dbSeq
					.seqNextValLong("CORRELATIVO_MENSAJE"));

			// obtiene el id de la peticion Atis

			Peticion_stKey key = new Peticion_stKey(new Long(idPeticion));

			Peticion_stLocal peticionSt = peticionStLocalHome
					.findByPrimaryKey(key);

			Collection colTmpDecoTarjeta = tmpDecoTarjetaLocalHome
					.findByNroPeticion(new Long(idPeticion));

			if (colTmpDecoTarjeta != null) {
				Iterator iterTmpDecoTarjeta = colTmpDecoTarjeta.iterator();
				while (iterTmpDecoTarjeta.hasNext()) {
					Tmp_deco_tarjetaLocal tmpDecoTarjeta = (Tmp_deco_tarjetaLocal) iterTmpDecoTarjeta
							.next();
					try {
						tmpDecoTarjeta.remove();
					} catch (EJBException e1) {
						log.error("EJBException:", e1);
						throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION, e1);
					} catch (RemoveException e1) {
						log.error("EJBException:", e1);
						throw new ATiempoAppEx(ATiempoAppEx.REMOVEEXCEPTION, e1);
					}
				}
			}

			// crea y llena la representacion Java del mensaje

			TR016E tr016e = new TR016E();

			tr016e.setId(idCorrelativoMensaje.toString());
			tr016e.setAtisRequestNumber(idPeticion);
			tr016e.setCardFinalDigits(ult4digitosTarjeta);
			tr016e.setDecoFinalDigitsSerial(ult4digitosDeco);
			tr016e.setContractorId(idContratista);
			//TODO se agrego a la tr 16 el deco reference para sol tec
			tr016e.setDecoReference(tipoDeco);

			// TODO borrar los tmp_deco_tarj asociados

			Mensaje_estado_stLocal dbmsg;

			dbmsg = msgEstadoSTLocalHome.create(idCorrelativoMensaje);

			dbmsg.setPeticion_st(peticionSt);
			dbmsg.setFecha_envio(df.format(new java.util.Date()));
			dbmsg.setCod_estado(new Integer(estadoEspera));

			//llama a la rutina que envia el mensaje
			new SolicitudSTDecoTarjActualizarMQ().enviarMensajeReplyToQ(tr016e);

			return (idCorrelativoMensaje);
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			log.error("FinderException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException cex) {
			log.error("CreateException:", cex);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}

	}

	/**
	 * almacena la lista de deco/tarjetas que terminan en 4 digitos
	 * 
	 * se guarda el XML mismo porque le es mas pratico al codigo de la interfaz
	 * usuario
	 */

	public void actualizaDecoTarjetaSTPorUtilizar(TR016S tr016s)
			throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			// busca el registro del mensaje
			Mensaje_estado_stLocal mensajeEstadoTv = null;
			try {
				Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
						.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
				mensajeEstadoTv = msgEstadoSTLocalHome
						.findByPrimaryKey(new Mensaje_estado_stKey(new Long(
								tr016s.getId())));

				tmpDecoTarjetaLocalHome = (Tmp_deco_tarjetaLocalHome) HomeFactory
						.getHome(Tmp_deco_tarjetaLocalHome.JNDI_NAME);

			} catch (FinderException e) {
				mensajeEstadoTv = null;
			}
			if (mensajeEstadoTv == null) {
				log.warn("No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr016s));
				throw new ATiempoAppEx(
						"No Existe Respuesta en la base de enviados:"
								+ tr016s.getId(), ATiempoAppEx.EXCEPTION);
			}

			// Validacion del estado de la respuesta

			if (mensajeEstadoTv.getCod_estado().intValue() == estadoOk) {
				log
						.warn("El estado de la respuesta es diferente para ser procesado\n "
								+ XMLUtilities.marshall(tr016s));
				return;
			}

			// TODO: ver si procesamos los errores o si la interfaz al usuario
			// se va a encargar de
			//       parsear el XML

			// todo ok, se graba la respuesta

			Long idTmpDecoTarjeta = new Long(dbSeq
					.seqNextValLong("CORRELATIVO_TMP_DECO_TARJ"));

			Tmp_deco_tarjetaLocal tmpDecoTarjetaLocal = tmpDecoTarjetaLocalHome
					.create(idTmpDecoTarjeta, mensajeEstadoTv.getPeticion_st(),
							XMLUtilities.marshall(tr016s));

			// actualiza el estado del mensaje
			mensajeEstadoTv.setCod_estado(new Integer(estadoOk));
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (CreateException e) {
			log.error("CreateException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}

	}

	/*
	 *  
	 */

	//   private Mensaje_estadoLocal buscaMensajeEstadoOk ()
	//   {
	//	   return (buscaMensajeEstado (estadoOk)) ;
	//   }
	/*
	 *  
	 */

	//   private Mensaje_estadoLocal buscaMensajeEstado (int llave)
	//   {
	//	   try
	//	   {
	//		   Mensaje_estadoKey key = new Mensaje_estadoKey (new Integer (llave)) ;
	//            
	//		   Mensaje_estadoLocal mensajeEstado = msgEstadoLocalHome.findByPrimaryKey
	// (key) ;
	//            
	//		   return (mensajeEstado) ;
	//	   }
	//	   catch (FinderException fex)
	//	   {
	//		   return (null) ;
	//	   }
	//        
	//   }
	public TR016S buscarRespuestaMensaje(Long idPeticion, Long idMensaje)
			throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
					.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);

			Mensaje_estado_stLocal msgTVLocal = msgEstadoSTLocalHome
					.findByPrimaryKey(new Mensaje_estado_stKey(idMensaje));
			;

			// No hay Mensaje enviado.
			if (msgTVLocal == null)
				return null;

			Peticion_stLocal pStLocal = peticionStLocalHome
					.findByPrimaryKey(new Peticion_stKey(idPeticion));
			Collection c = pStLocal.getTmp_deco_tarjeta();
			for (Iterator it = c.iterator(); it.hasNext();) {
				Tmp_deco_tarjetaLocal tmpdtLocal = (Tmp_deco_tarjetaLocal) it
						.next();

				TR016S tr016s = (TR016S) XMLUtilities.unmarshall(tmpdtLocal
						.getXml());

				return tr016s;
			}
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (FinderException e) {
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#getListaDecoTarjetas(java.lang.Long)
	 */
	public Collection getListaDecoTarjetas(Long idPeticion) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		Collection listaDeco = new ArrayList();
		try {
			Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
					.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Collection decosTarj = deco_tarjetaLocalHome
					.findPeticion(idPeticion);
			Timestamp marca_hora = null;
			for (Iterator it = decosTarj.iterator(); it.hasNext();) {

				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
				Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal
						.getPrimaryKey();

				EquipoStDTO eDto = new EquipoStDTO();
				eDto.setIdDeco(dtKey.id_deco);
				eDto.setIdTarjeta(dtKey.id_tarjeta);
				//TODO PVR SE CARGA el deco reference desde la base
				eDto.setDecoReference(dtLocal.getDeco_reference());
				eDto.setTipoEquipo("TV");
				eDto.setCodError(dtLocal.getCodigo_error());
				eDto.setObservacion(dtLocal.getMensaje_error());
				eDto.setDdtvSerial(dtLocal.getSerial_ddtv());
				eDto.setOriginal(dtLocal.isOriginal());

				//				 if(dtLocal.getOpco_id()!=null)
				//				 {
				//				 	 Operacion_comercialLocal
				// operacion_comercialLocal=operacion_comercialHome.findByPrimaryKey(new
				// Operacion_comercialKey(dtLocal.getOpco_id()));
				//					 eDto.setIdOpCo(String.valueOf(dtLocal.getOpco_id()));
				//					 eDto.setDescOperacionComer(operacion_comercialLocal.getOpco_nombre());
				//				 }
				//				 if(dtLocal.getEmpr_id()!=null)
				//				 {
				//					 EmpresaLocal empresaLocal=empresaHome.findByPrimaryKey(new
				// EmpresaKey(dtLocal.getEmpr_id()));
				//					 eDto.setContratista(empresaLocal.getEmpresa_nombre());
				//				 }
				eDto.setIdEstado(dtLocal.getIdEstado());
				//				if ( dtLocal.getEstado() == null ){
				//				}
				//					eDto.setEstado( "Sin Activar" );
				//				else
				eDto.setEstado(dtLocal.getDescEstado());
				marca_hora = dtLocal.getMarca_hora();
				if (marca_hora != null)
					eDto.setFechaMarcaHora(new Fecha(marca_hora)
							.getFechaconFormato());
				listaDeco.add(eDto);
			}
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
		} catch (Exception e) {
			log.warn("Exception al buscar decos");
		}
		return listaDeco;
	}

	//	 public boolean hayListaDecoTarjetasConfiguradas(Long idPeticion) throws
	// ATiempoAppEx{
	//		validaHome();
	//		boolean hayDecos=false;
	//		try {
	//			Peticion_stKey pKey = new Peticion_stKey(idPeticion);
	//			Peticion_stLocal pLocal = peticionStLocalHome.findByPrimaryKey(pKey);
	//
	//			Collection c = pLocal.getDeco_tarjeta();
	//			for (Iterator it = c.iterator(); it.hasNext();) {
	//				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal)it.next();
	//				Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal.getPrimaryKey();
	//				if ( dtLocal.estaActivo() || dtLocal.estaEliminado() ||
	// dtLocal.estaEliminadoDanhado()){ // si un deco fue activado o eliminado,
	// se nvia el mensaje de act inventario
	//					hayDecos=true;
	//				}
	//			}
	//		 } catch (Exception e) {
	//			log.debug("Exception al buscar decos");
	//		 }
	//		 return hayDecos;
	//	 }

	private boolean hayListaDecoTarjetasTrabajadas(Long idPeticion)
			throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		boolean hayDecos = false;
		try {
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stKey pKey = new Peticion_stKey(idPeticion);
			Peticion_stLocal pLocal = peticionStLocalHome
					.findByPrimaryKey(pKey);

			Collection c = pLocal.getDeco_tarjeta();
			for (Iterator it = c.iterator(); it.hasNext();) {
				Deco_tarjetaLocal dtLocal = (Deco_tarjetaLocal) it.next();
				Deco_tarjetaKey dtKey = (Deco_tarjetaKey) dtLocal
						.getPrimaryKey();
				if (!dtLocal.estaEnCas()) { // si un deco fue trabajado
					hayDecos = true;
				}
			}
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
		} catch (Exception e) {
			log.warn("Exception al buscar decos", e);
		}
		return hayDecos;
	}

	//TODO PVR IMPORTANTE VALIDAR

	public Long enviarActivacionCI(Long idPeticion, ArrayList listaActivar,
			ArrayList listaEliminar) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
					.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);

			Collection c = deco_tarjetaLocalHome.findPeticion(idPeticion);
			Peticion_stKey pKey = new Peticion_stKey(idPeticion);
			Peticion_stLocal pLocal = peticionStLocalHome
					.findByPrimaryKey(pKey);

			for (Iterator iterator = listaEliminar.iterator(); iterator
					.hasNext();) {
				EquipoStDTO eDto = (EquipoStDTO) iterator.next();
				try {
					Deco_tarjetaKey key = new Deco_tarjetaKey(eDto
							.getIdTarjeta(), eDto.getIdDeco(), pKey);
					Deco_tarjetaLocal local = deco_tarjetaLocalHome
							.findByPrimaryKey(key);
					local.setAccion(eDto.getAccion()); // debe ser eliminar o
													   // dañado
					local.setEstado(null);
					//					local.setOpco_id(new Long(operacionParDesactivar));
					log.info("Tratando de Eliminar Deco-Tarjeta:"
							+ eDto.getEstado() + "-" + eDto.getIdTarjeta());
				} catch (FinderException fe) {
					log.debug("No se puede eliminar par :" + eDto.toString());
				}
			}

			for (Iterator iterator = listaActivar.iterator(); iterator
					.hasNext();) {
				EquipoStDTO eDto = (EquipoStDTO) iterator.next();
				try {
					Deco_tarjetaLocal local = null;
					Deco_tarjetaKey key = new Deco_tarjetaKey(eDto
							.getIdTarjeta(), eDto.getIdDeco(), pKey);
					try {
						local = deco_tarjetaLocalHome.findByPrimaryKey(key);
						if (local.estaActivo() || local.estaEnCas())
							continue;
					} catch (FinderException fe) {
						local = deco_tarjetaLocalHome.create(eDto
								.getIdTarjeta(), eDto.getIdDeco(), pLocal);
					}
					local.setAccion(eDto.getAccion());
					local.setEstado(null);
					//TODO PVR se agrego tipo de deco para que guarde en soltec
					System.out
							.println("ES DEL TIPO " + eDto.getDecoReference());
					local.setDeco_reference(eDto.getDecoReference());
					//					local.setOpco_id(new Long(operacionParActivar));
					//					local.setEmpr_id(eDto.getIdContratista());
					log.info("Tratando de Activar Deco-Tarjeta:"
							+ eDto.getEstado() + "-" + eDto.getIdTarjeta());
				} catch (EJBException e1) {
					log
							.error("EJBException. No Se Creao Registro Deco/Tarjeta ["
									+ idPeticion
									+ ","
									+ eDto.getIdDeco()
									+ ","
									+ eDto.getIdTarjeta()
									+ "] : "
									+ e1.getMessage());
				} catch (CreateException e1) {
					log
							.error("CreateException. No Se Creo Registro Deco/Tarjeta ["
									+ idPeticion
									+ ","
									+ eDto.getIdDeco()
									+ ","
									+ eDto.getIdTarjeta()
									+ "] : "
									+ e1.getMessage());
				} catch (NumberFormatException nf) {
					log.error("NumberFormatException", nf);
				}
			}
		} catch (FinderException e) {
			log.debug(e);
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
		}
		Long idMensaje = this.enviaConfiguracionServiciosTVSoloEq(idPeticion
				.longValue());

		return idMensaje;
	}

	//DAVID: Se convierte a public para ser utilizado en Agenda SC. Se deja de
	// utilizar para agenda SC, Nov 1 2010. se utiliza método de abajo
	// enviaConfiguracionServiciosTVSoloEqAgendaSC.
	public Long enviaConfiguracionServiciosTVSoloEq(long idPeticion)
			throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
					.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
					.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);

			// obtiene un nuevo id de mensaje

			Long idCorrelativoMensaje = new Long(dbSeq
					.seqNextValLong("CORRELATIVO_MENSAJE"));

			// obtiene el id de la peticion Atis

			Peticion_stKey pKey = new Peticion_stKey(new Long(idPeticion));
			Peticion_stLocal peticion = peticionStLocalHome
					.findByPrimaryKey(pKey);
			String pcId = null;

			List psTematicos = new ArrayList(10);
			List psDecos = new ArrayList(10);

			// crea y llena la representacion Java del mensaje

			TR017E tr017e = new TR017E();

			tr017e.setId(idCorrelativoMensaje.toString());

			tr017e.setPcId(peticion.getNum_ide_nu_tv());
			tr017e.setAtisRequestNumber(idPeticion);
			tr017e.setClientName(peticion.getNom_rte_sn());
			tr017e.setClientLastName(peticion.getPri_ape_rte_sn());
			tr017e.setClientSecondLastName(peticion.getSeg_ape_rte_sn());

			tr017e.setCity(peticion.getCod_loc());

			//De donde saco el tipo cliente en el tr05
			String tipoCHC = STConfig.getVariable("TIPO_CLIENTE_HC");
			tr017e.setClientType(tipoCHC.charAt(0));

			tr017e.setClientDocumentType(peticion.getTip_doc_rte_cd());
			tr017e.setClientDocumentNumber(Long.parseLong(peticion
					.getNum_doc_rte_nu()));

			// deco / tarjeta
			List listDecoTarj = new ArrayList();

			Collection decosTarj = deco_tarjetaLocalHome.findPeticion(new Long(
					idPeticion));
			Iterator iterDecoTarj = decosTarj.iterator();
			while (iterDecoTarj.hasNext()) {
				Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj
						.next();
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjeta
						.getPrimaryKey();

				if (!decoTarjeta.esEsperaDeActivacion()
						&& !decoTarjeta.esEsperaDeEliminacion()
						&& !decoTarjeta.esEsperaDeEliminacionDanhado())
					continue;

				TR017EEquipment equipment = new TR017EEquipment();

				equipment.setCardSerial(decoTarjetaKey.id_tarjeta);
				equipment.setCasId(decoTarjetaKey.id_deco);

				equipment.setProductServiceCode(new Long(STConfig
						.getVariable("PS_DECO_DEFAULT")).longValue());
				long opCoDec = 0;
				if (decoTarjeta.getAccion().intValue() == accionParActivar) {
					opCoDec = new Long(STConfig.getVariable("OP_CO_ALTA"))
							.longValue();
				} else {//es baja(liberar o Dañado)
					opCoDec = new Long(STConfig.getVariable("OP_CO_BAJA"))
							.longValue();
				}

				equipment.setCommercialOperation(opCoDec);
				listDecoTarj.add(equipment);
			}

			tr017e.setEquipments(listDecoTarj);

			tr017e.setPackages(new ArrayList());

			Mensaje_estado_stLocal dbmsg;

			dbmsg = msgEstadoSTLocalHome.create(idCorrelativoMensaje);

			dbmsg.setPeticion_st(peticion);
			dbmsg.setFecha_envio(df.format(new java.util.Date()));
			dbmsg.setCod_estado(new Integer(estadoEspera));

			// llama a la rutina que envia el mensaje
			new SolicitudConfiguracionServiciosTVMQ()
					.enviarMensajeReplyToQ(tr017e);

			return (idCorrelativoMensaje);
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			log.debug("FinderException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException cex) {
			log.debug("CreateException:", cex);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	/**
	 * Método creado porque para agenda SC se envía la tr007 con algunos campos
	 * adicionales y diferentes. El serial de tarjeta es el idTarjeta en BD, y
	 * el cardCode es el serial en BD. Raro pero así lo definieron para agenda
	 * SC.
	 * 
	 * @param idPeticion
	 * @return
	 * @throws ATiempoAppEx
	 */
	public Long enviaConfiguracionServiciosTVSoloEqAgendaSC(long idPeticion)
			throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
					.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
					.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);

			// obtiene un nuevo id de mensaje

			Long idCorrelativoMensaje = new Long(dbSeq
					.seqNextValLong("CORRELATIVO_MENSAJE"));

			// obtiene el id de la peticion Atis

			Peticion_stKey pKey = new Peticion_stKey(new Long(idPeticion));
			Peticion_stLocal peticion = peticionStLocalHome
					.findByPrimaryKey(pKey);
			String pcId = null;

			List psTematicos = new ArrayList(10);
			List psDecos = new ArrayList(10);

			// crea y llena la representacion Java del mensaje

			TR017E tr017e = new TR017E();

			tr017e.setId(idCorrelativoMensaje.toString());

			tr017e.setPcId(peticion.getNum_ide_nu_tv());
			tr017e.setAtisRequestNumber(idPeticion);
			tr017e.setClientName(peticion.getNom_rte_sn());
			tr017e.setClientLastName(peticion.getPri_ape_rte_sn());
			tr017e.setClientSecondLastName(peticion.getSeg_ape_rte_sn());

			tr017e.setCity(peticion.getCod_loc());

			//De donde saco el tipo cliente en el tr05
			String tipoCHC = STConfig.getVariable("TIPO_CLIENTE_HC");
			tr017e.setClientType(tipoCHC.charAt(0));

			tr017e.setClientDocumentType(peticion.getTip_doc_rte_cd());
			tr017e.setClientDocumentNumber(Long.parseLong(peticion
					.getNum_doc_rte_nu()));

			// deco / tarjeta
			List listDecoTarj = new ArrayList();

			Collection decosTarj = deco_tarjetaLocalHome.findPeticion(new Long(
					idPeticion));
			Iterator iterDecoTarj = decosTarj.iterator();
			while (iterDecoTarj.hasNext()) {
				Deco_tarjetaLocal decoTarjeta = (Deco_tarjetaLocal) iterDecoTarj
						.next();
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjeta
						.getPrimaryKey();

				if (!decoTarjeta.esEsperaDeActivacion()
						&& !decoTarjeta.esEsperaDeEliminacion()
						&& !decoTarjeta.esEsperaDeEliminacionDanhado())
					continue;

				TR017EEquipment equipment = new TR017EEquipment();

				//Estos 4 campos son los diferentes para agenda SC.
				equipment.setCardSerial(decoTarjetaKey.id_tarjeta);
				if (decoTarjeta.getSerial_deco() != null) {
					equipment.setDecoSerial(decoTarjeta.getSerial_deco());
				} else {
					equipment.setDecoSerial("");
				}
				if (decoTarjeta.getDeco_reference() != null) {
					equipment.setDecoType(decoTarjeta.getDeco_reference());
				} else {
					equipment.setDecoType("");
				}
				if (decoTarjeta.getDeco_marca() != null) {
					equipment.setDecoBrand(decoTarjeta.getDeco_marca());
				} else {
					equipment.setDecoBrand("");
				}

				equipment.setCasId(decoTarjetaKey.id_deco);

				equipment.setProductServiceCode(new Long(STConfig
						.getVariable("PS_DECO_DEFAULT")).longValue());
				long opCoDec = 0;
				if (decoTarjeta.getAccion().intValue() == accionParActivar) {
					opCoDec = new Long(STConfig.getVariable("OP_CO_ALTA"))
							.longValue();
				} else {//es baja(liberar o Dañado)
					opCoDec = new Long(STConfig.getVariable("OP_CO_BAJA"))
							.longValue();
				}

				equipment.setCommercialOperation(opCoDec);
				listDecoTarj.add(equipment);
			}

			tr017e.setEquipments(listDecoTarj);

			tr017e.setPackages(new ArrayList());

			Mensaje_estado_stLocal dbmsg;

			dbmsg = msgEstadoSTLocalHome.create(idCorrelativoMensaje);

			dbmsg.setPeticion_st(peticion);
			dbmsg.setFecha_envio(df.format(new java.util.Date()));
			dbmsg.setCod_estado(new Integer(estadoEspera));

			// llama a la rutina que envia el mensaje
			new SolicitudConfiguracionServiciosTVMQ()
					.enviarMensajeReplyToQ(tr017e);

			return (idCorrelativoMensaje);
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			log.debug("FinderException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException cex) {
			log.debug("CreateException:", cex);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	//Raúl Triviño: Se crea este método que incluya la lógica de bajas de
	// decos.
	public Long enviaConfiguracionServiciosTVSoloEqAgendaSC(long idPeticion,
			ArrayList decosAInstalar) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
					.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
					.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);

			// obtiene un nuevo id de mensaje

			Long idCorrelativoMensaje = new Long(dbSeq
					.seqNextValLong("CORRELATIVO_MENSAJE"));

			// obtiene el id de la peticion Atis

			Peticion_stKey pKey = new Peticion_stKey(new Long(idPeticion));
			Peticion_stLocal peticion = peticionStLocalHome
					.findByPrimaryKey(pKey);
			String pcId = null;

			List psTematicos = new ArrayList(10);
			List psDecos = new ArrayList(10);

			// crea y llena la representacion Java del mensaje

			TR017E tr017e = new TR017E();

			tr017e.setId(idCorrelativoMensaje.toString());

			tr017e.setPcId(peticion.getNum_ide_nu_tv());
			tr017e.setAtisRequestNumber(idPeticion);
			tr017e.setClientName(peticion.getNom_rte_sn());
			tr017e.setClientLastName(peticion.getPri_ape_rte_sn());
			tr017e.setClientSecondLastName(peticion.getSeg_ape_rte_sn());

			tr017e.setCity(peticion.getCod_loc());

			//De donde saco el tipo cliente en el tr05
			String tipoCHC = STConfig.getVariable("TIPO_CLIENTE_HC");
			tr017e.setClientType(tipoCHC.charAt(0));

			tr017e.setClientDocumentType(peticion.getTip_doc_rte_cd());
			tr017e.setClientDocumentNumber(Long.parseLong(peticion
					.getNum_doc_rte_nu()));

			// deco / tarjeta
			List listDecoTarj = new ArrayList();
			DecoTarDTO decoTarjeta = null;

			Iterator iterDecoTarj = decosAInstalar.iterator();
			while (iterDecoTarj.hasNext()) {
				decoTarjeta = (DecoTarDTO) iterDecoTarj.next();

				TR017EEquipment equipment = new TR017EEquipment();
				equipment.setCardSerial(decoTarjeta.getTarjeta());
				equipment.setCasId(decoTarjeta.getDeco());

				if (decoTarjeta.getDeco_reference().equals(
						ComunInterfaces.desHCDecoSTD)) {
					equipment.setProductServiceCode(new Long(VpistbbaConfig
							.getVariable("PS_DECO_DEFAULT")).longValue());
				} else if (decoTarjeta.getDeco_reference().equals(
						ComunInterfaces.desHCDecoHDTV)) {
					equipment.setProductServiceCode(new Long(VpistbbaConfig
							.getVariable("PS_DECO_HD")).longValue());
				} else if (decoTarjeta.getDeco_reference().equals(
						ComunInterfaces.desHCDecoPVR)) {
					equipment.setProductServiceCode(new Long(VpistbbaConfig
							.getVariable("PS_DECO_PVR")).longValue());
				}

				equipment.setCommercialOperation(decoTarjeta
						.getOperationComercial().longValue());
				equipment.setDecoSerial(decoTarjeta.getDecoSerial());
				equipment.setDecoType(decoTarjeta.getDeco_reference());
				equipment.setDecoBrand(decoTarjeta.getDecoBrand());

				listDecoTarj.add(equipment);
			}

			tr017e.setEquipments(listDecoTarj);

			tr017e.setPackages(new ArrayList());

			Mensaje_estado_stLocal dbmsg;

			dbmsg = msgEstadoSTLocalHome.create(idCorrelativoMensaje);

			dbmsg.setPeticion_st(peticion);
			dbmsg.setFecha_envio(df.format(new java.util.Date()));
			dbmsg.setCod_estado(new Integer(estadoEspera));

			// llama a la rutina que envia el mensaje
			new SolicitudConfiguracionServiciosTVMQ()
					.enviarMensajeReplyToQ(tr017e);

			return (idCorrelativoMensaje);
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			log.debug("FinderException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException cex) {
			log.debug("CreateException:", cex);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	/**
	 * procesa la respuesta de configuracion de servicios TV
	 * 
	 * antes del 17/04/2007: suponia que la lista de "errores" (tr017s.getErrors
	 * ()) contiene solamente deco/tarjetas con errores. O sea, si la lista esta
	 * vacia, no hubieron ningun error.
	 * 
	 * Despues del 17/04/2007: la lista r017s.getErrors () contiene todos los
	 * deco/tarjeta. Si un deco/tarjeta fue procesado sin error, el codigo de
	 * error es igual a cero
	 */

	public void actualizaConfiguracionServiciosTV(TR017S tr017s)
			throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory
					.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			//			  ArrayList codigoError=new ArrayList(10);
			log.debug("Estoy procesando el tr017s");
			// busca el registro del mensaje
			Mensaje_estado_stLocal mensajeEstadoTv = null;
			try {
				mensajeEstadoTv = msgEstadoSTLocalHome
						.findByPrimaryKey(new Mensaje_estado_stKey(new Long(
								tr017s.getId())));
			} catch (FinderException e) {
				mensajeEstadoTv = null;
			}
			if (mensajeEstadoTv == null) {
				log.warn("No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr017s));
				throw new ATiempoAppEx(
						"No Existe Respuesta en la base de enviados:"
								+ tr017s.getId(), ATiempoAppEx.EXCEPTION);
			}
			// Validacion del estado de la respuesta
			mensajeEstadoTv.getCod_estado();
			Peticion_stLocal peticion = mensajeEstadoTv.getPeticion_st();
			Peticion_stKey peticionKey = (Peticion_stKey) peticion
					.getPrimaryKey();
			if (mensajeEstadoTv.getCod_estado().intValue() == estadoOk) {
				log
						.warn("El estado de la respuesta es diferente para ser procesado\n "
								+ XMLUtilities.marshall(tr017s));
				return;
			}

			// veamos si hay un error "fisico"
			if (tr017s.isError()) {
				mensajeEstadoTv.setCod_estado(new Integer(estadoError));
			} else {
				mensajeEstadoTv.setCod_estado(new Integer(estadoOk));
			}

			// marca en la tabla deco_tarjeta los deco/tarjetas que generaron
			// problemas
			if (tr017s.getErrors() != null && tr017s.getErrors().size() > 0) {
				// setea el codigo de error y mensaje de error en la tabla
				// deco_tarjeta
				Iterator iterDecoTarjetaError = tr017s.getErrors().iterator();
				Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory
						.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
				while (iterDecoTarjetaError.hasNext()) {
					ErrorAux errorDecoTarjeta = (ErrorAux) iterDecoTarjetaError
							.next();
					try {
						Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(
								errorDecoTarjeta.getCard(), errorDecoTarjeta
										.getCasId(), peticionKey);
						Deco_tarjetaLocal decoTarjeta = deco_tarjetaLocalHome
								.findByPrimaryKey(decoTarjetaKey);

						decoTarjeta.setCodigo_error(new Integer(
								(int) errorDecoTarjeta.getCode()));
						String msgErr = errorDecoTarjeta.getErrorCodeMessage();
						if (msgErr.length() > 100)
							msgErr = msgErr.substring(0, 99);
						decoTarjeta.setMensaje_error(msgErr);
						if (errorDecoTarjeta.getCode() == 0)//sin error
						{
							decoTarjeta.setEstado(new Integer(estadoParOk));
						} else { //viene con error el deco
							decoTarjeta.setEstado(new Integer(estadoParNoOk));
							if (decoTarjeta.esEliminacionFallida()
									|| decoTarjeta
											.esEliminacionDanhadoFallida()) {
								decoTarjeta.setAccion(new Integer(
										accionParActivar));
								decoTarjeta.setEstado(new Integer(estadoParOk));
								msgErr = "Eliminacion Fallida:" + msgErr;
								if (msgErr.length() > 100)
									msgErr = msgErr.substring(0, 99);
								decoTarjeta.setMensaje_error(msgErr);
							} else {
								decoTarjeta
										.setEstado(new Integer(estadoParNoOk));
							}
						}
						decoTarjeta.setMarca_hora(new Fecha().getTimestamp());
					} catch (FinderException fex) {
						log.warn("peticion " + peticionKey.cod_ave_cd
								+ ", mensaje TV " + tr017s.getId()
								+ ": no puedo encontrar tarjeta/deco "
								+ errorDecoTarjeta.getCard() + "/"
								+ errorDecoTarjeta.getCasId());
					}
				}
			}

			//En ST es solo pantalla, nada de cerrar actividades
			//			  if(mensajeEstadoTv.getCod_actividad_generadora()!=null &&
			// !mensajeEstadoTv.getCod_actividad_generadora().equals(""))
			//			  {
			//				  ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB
			// () ;
			//            
			//				  IActividadEJB actividadEJB = actividadFactoryEJB.getActividad
			// (peticionKey.peti_numero,
			// mensajeEstadoTv.getCod_actividad_generadora ()) ;
			//				  if(codigoError.size()==0 && !tr017s.isError())
			//					  actividadEJB.setObservacion("Configuracion de Servicios TV
			// realizada Satisfactoriamente");
			//				  else
			//				  {
			//					  actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,
			// "18");
			//					  actividadEJB.setObservacion("Error en la Actividad de
			// Configuracion DTH.Se deriva a
			// PGI."+sinNull(tr017s.getErrorMessage()));
			//					  insertarCausalesCnaPeticion(mensajeEstadoTv,
			// mensajeEstadoTv.getCod_actividad_generadora(), new
			// Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")),
			// actividadEJB.getIdActividadFlujo());
			//				  }
			//				  actividadEJB.terminar () ;
			//			  }
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	public void enviaRefrescoPares(Long nroPeticion) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance
		// Assessment - atipoldi - May 25, 2009
		try {
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Long idCorrelativoMensaje = new Long(dbSeq
					.seqNextValLong("CORRELATIVO_MENSAJE"));
			String pcId = null;
			Peticion_stKey key = new Peticion_stKey(nroPeticion);
			Peticion_stLocal peticion = peticionStLocalHome
					.findByPrimaryKey(key);

			TR017E tr017e = new TR017E();
			tr017e.setId(idCorrelativoMensaje.toString());
			tr017e.setPcId(peticion.getNum_ide_nu_tv());

			tr017e.setPcProductServiceCode(peticion.getCod_pro_ser_cd()
					.longValue());
			tr017e.setPcCommercialOperation(operacionParRefresh);

			tr017e.setAtisRequestNumber(nroPeticion.longValue());
			tr017e.setClientName(peticion.getNom_rte_sn());
			tr017e.setClientLastName(peticion.getPri_ape_rte_sn());
			tr017e.setClientSecondLastName(peticion.getSeg_ape_rte_sn());
			tr017e.setCity(peticion.getCod_loc());

			String tipoCHC = STConfig.getVariable("TIPO_CLIENTE_HC");
			tr017e.setClientType(tipoCHC.charAt(0));

			tr017e.setClientDocumentType(peticion.getTip_doc_rte_cd());
			tr017e.setClientDocumentNumber(Long.parseLong(peticion
					.getNum_doc_rte_nu()));
			List listDecoTarj = new ArrayList();
			tr017e.setEquipments(listDecoTarj);
			List listTematicos = new ArrayList();
			tr017e.setPackages(listTematicos);
			new SolicitudConfiguracionServiciosTVMQ()
					.enviarMensajeReplyToQ(tr017e);
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls", e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (FinderException e) {
			log.error("FinderException:", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	/*
	 * Determina sí la petición contiene un flujo de telefición en en ekl campo
	 * SOLTEC.PETICION_ST.NUM_IDE_NU_TV
	 */
	public boolean isIdpcTV(Long idPeticion) throws ATiempoAppEx {
		try {
			Peticion_stLocalHome peticionLH = (Peticion_stLocalHome) HomeFactory
					.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal peticion = peticionLH
					.findByPrimaryKey(new Peticion_stKey(idPeticion));
			boolean retorno = false;
			if (peticion.getNum_ide_nu_tv() != null
					&& !peticion.getNum_ide_nu_tv().equals("")) {
				retorno = true;
			}
			return retorno;
		} catch (Exception e) {
			log.error("Exception:", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	public void enviaConfiguracionMovistarPlay(long petiNumero,
			ActividadEJBDTO act) throws ATiempoAppEx {

		log.debug("Ingreso a la Actividad enviaConfiguracionMovistarPlay");

	}
}