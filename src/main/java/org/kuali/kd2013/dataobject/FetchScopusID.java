package org.kuali.kd2013.dataobject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class FetchScopusID
 */
public class FetchScopusID extends HttpServlet {
	private static final long serialVersionUID = -1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String title = "Reading and Writing Excel Sheets";
		String docType =
		"<!doctype html public \"-//w3c//dtd html 4.0 " +
		"transitional//en\">\n";
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body bgcolor=\"#f0f0f0\">\n" +
				"<h1 align=\"center\">" + title + "</h1>\n");
		
		String apiKey = "b208dbc860cf6afb2527406f94ac1431";
		String firstName = "Boon";
		String lastName = "Ooi";
		try {
			URL scopus = new URL("http://api.elsevier.com/content/search/index:SCOPUS?httpAccept=application/"
			           + "xml&query=AFFIL(King%20Abdullah%20University%20of%20Science%20and%20Technology)%20AND%20AUTHOR-NAME(" 
			           + lastName + "," + firstName.charAt(0) + ")&apiKey=" + apiKey);
	
			DocumentBuilderFactory factory =
			       DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
		
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(scopus.openStream());
			
			Integer documentCount = Integer.parseInt(document.getElementsByTagName("opensearch:totalResults").item(0).getTextContent());
			out.println("Document count is" + documentCount.toString() +"<br/>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
