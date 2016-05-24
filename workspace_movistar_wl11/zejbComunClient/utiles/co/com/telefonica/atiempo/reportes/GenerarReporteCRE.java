/*
 * Creado el 8/08/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.reportes;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;


/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GenerarReporteCRE {
	
	public static String reportTemplateUrl = "/home/atiemweb/Report/RecogidaEquipos.jrxml";
	//public static String OutReportTemplateUrl = "C:/home/atiemweb/Report/Generados/prueba.pdf";
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	public void setReporte(List reports, OutputStream file){
		try {
			Map parameters = new HashMap();
			 
			parameters.put("printer", "1010111");
	        JasperDesign jasperDesign = JRXmlLoader.load(reportTemplateUrl);
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(
	        		reports));
	        //view the report using JasperViewer
	        //JasperViewer.viewReport(jasperPrint);
	        JRExporter exporter = new JRPdfExporter();
	        log.debug("se visualiza el reporte");
	        //JasperExportManager.exportReportToPdfFile(jasperPrint,OutReportTemplateUrl);
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, file);
            exporter.exportReport();
	    } catch (JRException e) {
	    	log.error("Error al generar el archivo PDF: "+e.getMessage());
	    }
	}

}
