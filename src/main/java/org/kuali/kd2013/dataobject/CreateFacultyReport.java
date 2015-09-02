package org.kuali.kd2013.dataobject;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.KradDataServiceLocator;
import org.kuali.rice.krad.data.jpa.KradEntityManagerFactoryBean;

/**
 * Servlet implementation class CreateFacultyReport
 */
public class CreateFacultyReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataObjectService dos = null;   
	KradEntityManagerFactoryBean kradBean = null;  
	
    public CreateFacultyReport() {
    	dos = KradDataServiceLocator.getDataObjectService();
    }

    public DataObjectService getDos() {
		return dos;
	}

	public void setDos(DataObjectService dos) {
		this.dos = dos;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		QueryResults<PrincipalInvestigator> piTempQueryResults = dos.findMatching(PrincipalInvestigator.class,QueryByCriteria.Builder.create().build()); 
		
		List<PrincipalInvestigator> piList = piTempQueryResults.getResults();
		DRDataSource dataSource = new DRDataSource("name", "id_number", "rank");
		for (PrincipalInvestigator pi : piList) {
			dataSource.add(pi.getName(), pi.getIdNumber(), pi.getRank());
		}
		
		JasperReportBuilder report = DynamicReports.report();//a new report report
		report
		.columns(
			      Columns.column("Name", "name", DataTypes.stringType()),
			      Columns.column("ID Number", "id_number", DataTypes.stringType()),
			      Columns.column("Rank", "rank", DataTypes.stringType()))
			  .title(//title of the report
			      Components.text("SimpleReportExample")
				  .setHorizontalAlignment(HorizontalAlignment.CENTER))
				  .pageFooter(Components.pageXofY())//show page number on the page footer
				  .setDataSource(dataSource);
		
		try {
            //show the report
			report.show();
			
			//export the report to a pdf file
			String fileName = "c:/report.pdf";
			report.toPdf(new FileOutputStream(fileName));
			/*File file = new File(fileName);
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);*/
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
