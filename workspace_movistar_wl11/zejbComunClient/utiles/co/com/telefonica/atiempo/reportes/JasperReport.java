package co.com.telefonica.atiempo.reportes;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
 
public class JasperReport {
	public static JasperDesign jasperDesign;
	public static JasperPrint jasperPrint;
	public static net.sf.jasperreports.engine.JasperReport jasperReport;
	public static String reportTemplateUrl = "C:/IBM/rationalsdp6.0/workspace_4_27/zwebVpiStbBa/JavaSource/co/com/telefonica/atiempo/vpistbba/reportes/RecogidaEquipos.jrxml";
	 
//	return the report filename
	public static String getReportFile(){
	    return reportTemplateUrl;
	}
	 
//	get a parameter to pass into jrxml
	public static Map getReportParameter() {
	    Map parameters = new HashMap();
	 
	    parameters.put("printer", "1010111");
	 
	    return parameters;
	}
	 
//	get the data to pass into jrxml
	public static List findReportData(HttpServletRequest request){
	    //declare a list of object
	    List reports = new LinkedList();
	 
	    //declare 2nd ReportObject and set the fields
	    ReportObject report = new ReportObject();
	    report.setDia_entrega("29");
	    report.setMes_entrega("7");
	    report.setAnio_entrega("2013");
	    reports.add((Object) report);
	 
	    return reports;
	}
	 
	public static void generarReporte (HttpServletRequest request) throws IOException {
	    try {
	        //get report file and then load into jasperDesign
	        jasperDesign = JRXmlLoader.load(getReportFile());
	        //compile the jasperDesign
	        jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        //fill the ready report with data and parameter
	        jasperPrint = JasperFillManager.fillReport(jasperReport, getReportParameter(), new JRBeanCollectionDataSource(
	        findReportData(request)));
	        //view the report using JasperViewer
	        JasperViewer.viewReport(jasperPrint);
	    } catch (JRException e) {
	        e.printStackTrace();
	    }
	}
 
    
}