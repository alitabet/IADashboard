package org.kuali.kd2013.dataobject;

import java.awt.Color;
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
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.Charts;
import net.sf.dynamicreports.report.builder.chart.Pie3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.KradDataServiceLocator;
import org.kuali.rice.krad.data.jpa.KradEntityManagerFactoryBean;

/**
 * Servlet implementation class CreateAwardsReport
 */
public class CreateAwardsReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataObjectService dos = null;   
	KradEntityManagerFactoryBean kradBean = null;  
	
    public CreateAwardsReport() {
    	dos = KradDataServiceLocator.getDataObjectService();
    }

    public DataObjectService getDos() {
		return dos;
	}

	public void setDos(DataObjectService dos) {
		this.dos = dos;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QueryResults<OcrfAward> tempQueryResults = dos.findMatching(OcrfAward.class,QueryByCriteria.Builder.create().build()); 
		
		List<OcrfAward> resultList = tempQueryResults.getResults();
		DRDataSource dataSource = new DRDataSource("item", "program", "award_number", "name", "division", "center",  "type", "total_budget");
		
		for (OcrfAward award : resultList) {
			String awardProgram = award.getProgram() + award.getAwardRound();
			dataSource.add(1,awardProgram, award.getAwardNumber(), award.getName(), award.getPi().getDivision(), 
					award.getPi().getCenter(), award.getType(), award.getTotalBudget());
		}
		
		StyleBuilder boldStyle = DynamicReports.stl.style().bold();
		StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(
				                                                          HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = DynamicReports.stl.style(boldCenteredStyle).setBorder(
				DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);
		
		TextColumnBuilder<String> programColumn = Columns.column("Program","program",
				                                          DataTypes.stringType()).setStyle(boldStyle);
		TextColumnBuilder<String> awardNumberColumn = Columns.column("Award Number","award_number",DataTypes.stringType());
		TextColumnBuilder<String> nameColumn = Columns.column("Principal Investogator","name",DataTypes.stringType());
		TextColumnBuilder<String> divisionColumn = Columns.column("Division","division",DataTypes.stringType());
		TextColumnBuilder<String> centerColumn = Columns.column("Center","center",DataTypes.stringType());
		TextColumnBuilder<String> typeColumn = Columns.column("Type of Award","type",DataTypes.stringType());
		TextColumnBuilder<Double> totalBudgetColumn = Columns.column("Budget","total_budget",DataTypes.doubleType());
		TextColumnBuilder<Integer> itemColumn = Columns.column("Item","item",DataTypes.integerType());
		
		Bar3DChartBuilder programDivisionChart = Charts.bar3DChart()
													.setTitle("Distribution of Awards")
													.setCategory(programColumn)
													.addSerie(Charts.serie(itemColumn).setSeries(divisionColumn));
		
		Bar3DChartBuilder programCenterChart = Charts.bar3DChart()
												.setTitle("Distribution of Awards")
												.setCategory(programColumn)
												.addSerie(Charts.serie(itemColumn).setSeries(centerColumn));
		
		JasperReportBuilder report = DynamicReports.report();
		
		try {
			report
				.setColumnTitleStyle(columnTitleStyle)
				.columns(programColumn,nameColumn,awardNumberColumn,divisionColumn,centerColumn,typeColumn,totalBudgetColumn)
				.groupBy(programColumn)
				.pageFooter(DynamicReports.cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
				.summary(programDivisionChart,programCenterChart)
				.setDataSource(dataSource)
            	.show();
			
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
