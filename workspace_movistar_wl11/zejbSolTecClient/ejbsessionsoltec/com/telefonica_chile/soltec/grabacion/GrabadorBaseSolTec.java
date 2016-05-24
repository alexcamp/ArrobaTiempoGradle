/*
 * Created on Feb 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.soltec.grabacion;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import co.com.atiempo.dto.ElementoDTO;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocalHome;
import co.com.telefonica.atiempo.soltec.dto.ModemSTDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.soltec.mensajeria.ba.ejb.sb.RecursosBALocal;
import co.com.telefonica.atiempo.soltec.mensajeria.ba.ejb.sb.RecursosBALocalHome;
import co.com.telefonica.atiempo.soltec.servicios.EquipoSTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.cierre_observaciones.session.CierreActividadDTO;
import com.telefonica_chile.comun.cierre_observaciones.session.CierreActividadSessionLocal;
import com.telefonica_chile.comun.cierre_observaciones.session.CierreActividadSessionLocalHome;

/**
 * @author dfiguer
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GrabadorBaseSolTec extends GrabadorStComun {
    //	private Map datosModificados = new HashMap();
    protected Long idActividad;

    protected Long idPeticion;

    private boolean esClave = false;

    protected void grabarDatosPropios(HttpServletRequest request) {
    }

    //	protected IActividadEJB getActividad() throws TnProcesoExcepcion,
    // ATiempoAppEx {
    //		IActividadEJB actividad = super.getActividad();
    //		for (Iterator iter = datosModificados.keySet().iterator();
    // iter.hasNext();) {
    //			String o = (String) iter.next();
    //			actividad.setDato(o, (String) datosModificados.get(o));
    //		}
    //		return actividad;
    //
    //	}

    public void ejecutarLogica(HttpServletRequest request) {
        // parte implementada por subclases
        this.grabarDatosPropios(request);

        // parte común
        Long idPeticion = new Long(this.instanciaProceso);
        Long cierre = new Long(request.getParameter("cierre"));
        String codActividad = request.getParameter("codigoActividad");

        CierreActividadDTO cierreDto = null;
        if (cierre.intValue() != 0) {
            try {
                CierreActividadSessionLocalHome cierreLocalHome = (CierreActividadSessionLocalHome) HomeFactory.getHome(CierreActividadSessionLocalHome.JNDI_NAME);
                CierreActividadSessionLocal cierreSessionLocal = cierreLocalHome.create();
                cierreDto = cierreSessionLocal.recuperaCierreActividadId(cierre);
            } catch (NamingException e) {
                log.error("NamingException. CierreActividadSessionHome ["+ idProceso + "," + codActividad + "," + cierre + "]");
            } catch (CreateException e) {
                log.error("CreateException. CierreActividadSessionHome ["+ idProceso + "," + codActividad + "," + cierre + "]");
            }
        }

        String tipoTermino = request.getParameter("terminar_act_wf");
        log.debug("Tipo Termino:" + tipoTermino);
        //		if ( tipoTermino!=null && !"0".equals(tipoTermino) ) {
        if (!"0".equals(tipoTermino)) {
            log.debug("Terminando Actividad [" + idPeticion + ","+ codActividad + "," + cierreDto.getCodigo() + "," + cierreDto.getValor() + "]");
            setDatoWF(cierreDto.getCodigo(), cierreDto.getValor());
        }

//        this.grabarModemsBa(request);
        this.grabarFechaControlVisita(request);
        //CR19814 PDTI -- Pablo L -- 06/04/09
        this.grabarEquipos(request);
        //		this.grabarSolucion(request);

    }

    private void grabarFechaControlVisita(HttpServletRequest request) {
        try {
            if (request.getParameter("fechaHoraVisitaDesde") != null
                    && request.getParameter("fechaHoraVisitaHasta") != null) {
                Fecha fechaHoraVisitaDesde = new Fecha(request.getParameter("fechaHoraVisitaDesde"),"dd/MM/yyyy HH:mm");
                Fecha fechaHoraVisitaHasta = new Fecha(request.getParameter("fechaHoraVisitaHasta"),"dd/MM/yyyy HH:mm");
                Long codAveCd = new Long(request.getParameter("folio"));
                IncidentesInterfaces incidentesInterfaces = new IncidentesDelegate();
                incidentesInterfaces.grabarControlVisita(codAveCd,fechaHoraVisitaDesde, fechaHoraVisitaHasta);
            }
        } catch (Exception e) {
            log.debug(e);
        }

    }

    protected void setDatoWF(String clave, String valor) {
        datosModificados.put(clave, valor);
    }

    /**
     * @param request
     */
    private void grabarModemsBa(HttpServletRequest request) {
        try {
            if (request.getParameter("tengoModems") == null)
                return;
            ArrayList listaModems = new ArrayList();
            if (request.getParameter("nroTelAsig") == null) {
                log.info("No tengo Nro Telefono Asignado no puedo Grabar Modems");
                return;
            }
            Long nroTelAsig = new Long(request.getParameter("nroTelAsig"));
            Long petiNumero = new Long(request.getParameter("folio"));
            if (nroTelAsig == null || petiNumero == null
                    || nroTelAsig.equals(""))
                return;

            for (int i = 1; i <= new Integer(STConfig.getVariable("nroModems"))
                    .intValue(); i++) {
                if (request.getParameter("modem" + i) != null) {
                    String serialModem = request.getParameter("modem" + i);
                    if (serialModem.equals(""))
                        continue;
                    
                    String accionParameter = request.getParameter("accion_" + i);
                    short accion = 0;
                    if(accionParameter != null){
                    	accion = new Short(accionParameter).shortValue();
                    }
                    ModemSTDTO modemStDTO = new ModemSTDTO();
                    modemStDTO.setNum_peticion(petiNumero);
                    modemStDTO.setTelefono(nroTelAsig);
                    modemStDTO.setSerial(serialModem);
                    modemStDTO.setAccion(new Integer(accion));
                    /*
					 *	CR - 00026148 - Jun 26, 2009
					 *		Se agrega Marca - German P.
					 */
					String modem_marca =request.getParameter("marca_"+i);
					if (modem_marca == null){
						modem_marca = "";
					}
					modemStDTO.setMarca(modem_marca);
					
                    listaModems.add(modemStDTO);
                }
            }
            RecursosBALocalHome recursosBALocalHome = (RecursosBALocalHome) HomeFactory.getHome(RecursosBALocalHome.JNDI_NAME);
            RecursosBALocal local = recursosBALocalHome.create();
            local.grabaModemsBaST(petiNumero, nroTelAsig, listaModems);
        } catch (Exception e) {
            log.debug("Exception", e);
        }
    }

    private void grabarEquipos(HttpServletRequest request) {
        Long nroTelAsig = new Long(0);
        Long petiNumero = new Long(request.getParameter("folio"));

        try {
            Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
            Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(petiNumero));

            boolean exit = false;
            String equipo = null;
            ArrayList listaEquipos = new ArrayList();
            String cantEquipos = request.getParameter("cantEquipos");
            if (cantEquipos != null) {
            	 if(!peticionLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.IT)){
            	 	if (peticionLocal.getNum_ide_nu()==null || peticionLocal.getNum_ide_nu().equals(""))
    				{
            	 		nroTelAsig = new Long(peticionLocal.getNum_ide_nu_tv());
    				}else{
    					nroTelAsig = new Long(peticionLocal.getNum_ide_nu());
    				}
            	 
            	 }//DAVID: venta equipos ST, si la petición es venta equipos, se deja el teléfono en 0. Se setea el telefono_it con el valor peticionLocal.getNum_ide_nu().
                
            	int encontrados = 0;
                int i = 0;
                while (!exit) {
                    equipo = request.getParameter("equipo_" + i);
                    if (equipo != null) {
                        ElementoDTO e = new ElementoDTO();
                        ElementoDTO eViejo = new ElementoDTO();
                        short accion = Short.parseShort(request.getParameter("acc_" + i));
                        if (accion != ComunInterfaces.accionModemNoAction) {
                            short accionNueva = Short.valueOf(String.valueOf(ComunInterfaces.accionModemOcupar)).shortValue();
                            e.setAccion(accionNueva);//Poner Ocupar
                            eViejo.setAccion(accion);
                            e.setSerial(request.getParameter("serial_" + i));
                            eViejo.setSerial(request.getParameter("old_serial_"+i));
                            String tElemento = request.getParameter("type_" + i);
                            String tipo_eq = request.getParameter("eq_type_"+ i);
                            e.setPeti_numero(request.getParameter("folio"));
                            eViejo.setPeti_numero(request.getParameter("folio"));
                            String idPS = request.getParameter("ps_" + i);
                            e.setTipo_equipo(tipo_eq);
                            e.setTipo_elemento(new Long(tElemento));
                            eViejo.setTipo_equipo(tipo_eq);
                            eViejo.setTipo_elemento(new Long(tElemento));
                            e.setPs(new Long(idPS));
                            eViejo.setPs(new Long(idPS));
                            if (idPS != null && !idPS.equals("")) {
                                Grpe_PsLocalHome grpeLocalHome;
                                grpeLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
                                Grpe_PsLocal grupo = grpeLocalHome.findGrupoByPS(new Long(idPS));
                                if (grupo != null) {
                                    e.setTipo_inventario(grupo.getGrpe_id().toString());
                                    eViejo.setTipo_inventario(grupo.getGrpe_id().toString());
                                } else {
                                    e.setTipo_inventario("");
                                    eViejo.setTipo_inventario("");
                                }

                            } else {
                                e.setTipo_inventario("");
                                eViejo.setTipo_inventario("");
                            }
                            listaEquipos.add(e);
                            listaEquipos.add(eViejo);
                            encontrados++;
                        } else {
                        encontrados++;
                    }
                    if (encontrados == Integer.parseInt(cantEquipos)) {
                        exit = true;
                    }

                    i++;
                }

            }
            }
            EquipoSTDelegate eDelegate = new EquipoSTDelegate();
            if (listaEquipos.size() > 0)
                eDelegate.grabaEquiposST(petiNumero, nroTelAsig, listaEquipos);
        } catch (ATiempoAppEx at) {
            log.debug("No se han podido grabar los equipos ");

        } catch (NamingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (FinderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
