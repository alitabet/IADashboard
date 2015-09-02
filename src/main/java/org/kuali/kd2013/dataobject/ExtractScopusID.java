package org.kuali.kd2013.dataobject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.KradDataServiceLocator;
import org.kuali.rice.krad.data.jpa.KradEntityManagerFactoryBean;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


//@WebServlet("/ExtractScopusID")
public class ExtractScopusID extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataObjectService dos = null;   
	KradEntityManagerFactoryBean kradBean = null;   
    
    public ExtractScopusID() {
    	dos = KradDataServiceLocator.getDataObjectService();
    }

    public DataObjectService getDos() {
		return dos;
	}

	public void setDos(DataObjectService dos) {
		this.dos = dos;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Integer scopusCase = 1; // ID Case
		Integer scopusCase = 2; // Publication Case
		PrintWriter out = response.getWriter();
		String title = "Fetching Scopus IDs";
		String docType =
		"<!doctype html public \"-//w3c//dtd html 4.0 " +
		"transitional//en\">\n";
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body bgcolor=\"#f0f0f0\">\n" +
				"<h1 align=\"center\">" + title + "</h1>\n" +
				"<table style=\"width:100%\">" + 
				"<tr><th>Name</th><th>Scopus ID(s)</th></tr>");
		try 
		{
			PrincipalInvestigator pi = dos.find(PrincipalInvestigator.class, "Jean Marie Basset");
			/*QueryResults<PrincipalInvestigator> piTempQueryResults = dos.findMatching(PrincipalInvestigator.class,QueryByCriteria.Builder.create().build()); 
			
			List<PrincipalInvestigator> piTempList = piTempQueryResults.getResults();
			System.out.println("Total number: " + piTempList.size());
			Integer count = 1;
			for (PrincipalInvestigator pi : piTempList) {
				System.out.println("Number " + count++ + ": " + pi.getName());*/
				out.println("<tr>");
				List<ScopusPI> scopusID = pi.getScopusID();
				List<PublicationData> scopusPublications = pi.getPublications();
				switch(scopusCase) {
				case 1:
					
					if (scopusID.isEmpty()) {
						pi = fecthScopusData(pi);
						//pi = fecthScopusPublications(pi);
						try {
							pi = dos.save(pi);
						} catch (Exception e) {
							System.out.println("Cannot save data for " + pi.getName());
						}
						
						out.println("<td>" + pi.getName() + "</td>");		
						out.println("<td>" + pi.getScopusIDString() + "</td>");
						
						out.println("</tr>");
					} else {
						out.println("<td>" + pi.getName() + "</td>");
						out.println("<td>Already populated</td>");
						out.println("</tr>");
					}
				case 2:
					
					if (true){//scopusID.size() > 1) {
						pi = fecthScopusPublications(pi);
						try {
							pi = dos.save(pi);	
						} catch (Exception e) {
							System.out.println("Cannot save data for " + pi.getName());
						}
						
						out.println("<td>" + pi.getName() + "</td>");		
						out.println("<td>" + pi.getScopusIDString() + "</td>");
						
						out.println("</tr>");
					} else {
						out.println("<td>" + pi.getName() + "</td>");
						out.println("<td>Already populated</td>");
						out.println("</tr>");
					}	
				}
			//}
		}
		catch(Exception e) {
			out.println("<tr>");
			out.println("<td>Data Service " + e.getMessage() + "</td>");
			out.println("</tr>");
		}
		finally {
			out.println("</table></body></html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected PrincipalInvestigator fecthScopusData(PrincipalInvestigator pi) {
		String name = new String(pi.getName());
		List<String> nameList = new ArrayList<String>();
		Collections.addAll(nameList,name.split(" "));
		String firstName = nameList.get(0);
		String lastName = nameList.get(nameList.size() - 1);
		String apiKey = "b208dbc860cf6afb2527406f94ac1431";
		try {
			URL scopus = new URL("http://api.elsevier.com/content/search/scopus?"
					   + "query=AFFIL%28King+Abdullah+University+of+Science+and+Technology%29+AND+AUTHOR-NAME%28" 
			           + lastName + "%2C" + firstName.charAt(0) + "%29&apiKey=" + apiKey + "&httpAccept=application/xml");
			URLConnection conn = scopus.openConnection();
			conn.setConnectTimeout(3600000);
			conn.setReadTimeout(3600000);

			DocumentBuilderFactory factory =
			       DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;

			builder = factory.newDocumentBuilder();
			Document document = builder.parse(conn.getInputStream());
			
			Integer documentCount = Integer.parseInt(document.getElementsByTagName("opensearch:totalResults").item(0).getTextContent());
			
			Set<String> scopusID = new HashSet<String>();
			for (int i = 0; i < Math.ceil(documentCount/100.0); i++) {
				builder.reset();
				Integer current = i*100;
				scopus = new URL("http://api.elsevier.com/content/search/scopus?start=" 
				           + current.toString() + "&count=100"
						   + "&query=AFFIL%28King+Abdullah+University+of+Science+and+Technology%29+AND+AUTHOR-NAME%28" 
				           + lastName + "%2C" + firstName.charAt(0) + "%29&apiKey=" + apiKey + "&httpAccept=application/xml");
				conn = scopus.openConnection();
				conn.setConnectTimeout(3600000);
				conn.setReadTimeout(3600000);
				document = builder.parse(conn.getInputStream());	
				NodeList list = document.getElementsByTagName("prism:url");
				String scopusLastName = null;
				for (int j = 0; j < list.getLength(); j++) {
					builder.reset();
					scopus = new URL(list.item(j).getTextContent() + "?field=author,affiliation&apiKey=" + apiKey + "&httpAccept=application/xml");
					conn = scopus.openConnection();
					conn.setConnectTimeout(3600000);
					conn.setReadTimeout(3600000);
					document = builder.parse(conn.getInputStream());	
					NodeList childList = document.getElementsByTagName("ce:surname");
					//Integer author_index = -1;
					scopusLastName = null;
					for (int k = 0; k < childList.getLength(); k = k + 2) {
						scopusLastName = childList.item(k).getTextContent();
	    				if (lastName.equals(scopusLastName)){
	    					NamedNodeMap nodeMap = document.getElementsByTagName("author").item(k/2).getAttributes();
	    					scopusID.add(nodeMap.item(0).getTextContent());
	    				}
					}
					/*if (author_index == -1){ // Author not found in this 
						continue;
					}
					NamedNodeMap nodeMap = document.getElementsByTagName("author").item(author_index).getAttributes();
					scopusID.add(nodeMap.item(0).getTextContent());*/
				}
			}
			
			/*Integer citedBy = 0, citations = 0, hIndex = 0;
			documentCount = 0;*/
			List<ScopusPI> tempList = new ArrayList<ScopusPI>();
			Iterator<String> it = scopusID.iterator();
	        while(it.hasNext()){
	        	String temp = it.next();
	        	
	        	// First check author details
	        	builder.reset();
				scopus = new URL("http://api.elsevier.com/content/author?author_id=" + temp + "&view=LIGHT&apiKey=" + apiKey + "&httpAccept=application/xml");
				conn = scopus.openConnection();
				conn.setConnectTimeout(3600000);
				conn.setReadTimeout(3600000);
				document = builder.parse(conn.getInputStream());
	        	
				ScopusPI spi = new ScopusPI();
				spi.setName(pi.getName());
				spi.setScopusID(temp);
				
				NodeList childList = document.getElementsByTagName("given-name");
				String givenName = childList.item(0).getTextContent();
				childList = document.getElementsByTagName("surname");
				String surname = childList.item(0).getTextContent();
				spi.setScopusName(new String(givenName + " " + surname));
				
				childList = document.getElementsByTagName("affiliation-city");
				if (childList.item(0) == null) {
					tempList.add(spi);
					continue;
				}
				spi.setCity(childList.item(0).getTextContent());
				
				childList = document.getElementsByTagName("affiliation-country");
				spi.setCountry(childList.item(0).getTextContent());
				
				childList = document.getElementsByTagName("affiliation-name");
				spi.setAffiliation(childList.item(0).getTextContent());
				
				tempList.add(spi);
				
	        	builder.reset();
				scopus = new URL("http://api.elsevier.com/content/author?author_id=" + temp + "&view=METRICS&apiKey=" + apiKey + "&httpAccept=application/xml");
				conn = scopus.openConnection();
				conn.setConnectTimeout(3600000);
				conn.setReadTimeout(3600000);
				document = builder.parse(conn.getInputStream());
				
				childList = document.getElementsByTagName("document-count");
				if (childList.item(0) != null) {
					spi.setNumber_publications(Integer.parseInt(childList.item(0).getTextContent()));
				}
				childList = document.getElementsByTagName("cited-by-count");
				if (childList.item(0) != null) {
					spi.setCitedBy(Integer.parseInt(childList.item(0).getTextContent()));
				}
				
				childList = document.getElementsByTagName("citation-count");
				if (childList.item(0) != null) {
					spi.setCitations(Integer.parseInt(childList.item(0).getTextContent()));
				}

				childList = document.getElementsByTagName("h-index");
				if (childList.item(0) != null) {
					spi.sethIndex(Integer.parseInt(childList.item(0).getTextContent()));
				}
				
				/*childList = document.getElementsByTagName("document-count");
				documentCount = documentCount + Integer.parseInt(childList.item(0).getTextContent());
				childList = document.getElementsByTagName("cited-by-count");
				citedBy = citedBy + Integer.parseInt(childList.item(0).getTextContent());
				childList = document.getElementsByTagName("citation-count");
				citations = citations + Integer.parseInt(childList.item(0).getTextContent());
				childList = document.getElementsByTagName("h-index");
				if (hIndex < Integer.parseInt(childList.item(0).getTextContent()))
				{
					hIndex = Integer.parseInt(childList.item(0).getTextContent());
				}*/
	        }
			
			pi.setScopusID(tempList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pi;
	}
	protected PrincipalInvestigator fecthScopusPublications(PrincipalInvestigator pi) {
		List<ScopusPI> scopusID = pi.getScopusID();
		String apiKey = "b208dbc860cf6afb2527406f94ac1431";
		System.out.println(scopusID.size() + " Scopus ID(s):");
		List<PublicationData> scopusPublications = new ArrayList<PublicationData>();
		for (ScopusPI sID : scopusID) {
			URL scopus = null;
			URLConnection conn = null;
			DocumentBuilderFactory factory = null;
			DocumentBuilder builder = null;
			Document document = null;
			try {
				//System.out.println("Scopus ID(s) " + sID.getScopusID() + ":");
				String q = "AU-ID(" + sID.getScopusID() + ")";
				scopus = new URL("http://api.elsevier.com/content/search/scopus?query="
						           + URLEncoder.encode(q, "UTF-8") + "&view=STANDARD&apiKey=" 
						           + apiKey + "&httpAccept=application/xml");
						//"query=AU-ID%28" + scopusID
				          // + "%29&view=STANDARD&apiKey=" + apiKey + "&httpAccept=application/xml");
				
				conn = scopus.openConnection();
				conn.setConnectTimeout(3600000);
				conn.setReadTimeout(3600000);
	
				factory = DocumentBuilderFactory.newInstance();
	
				builder = factory.newDocumentBuilder();
				document = builder.parse(conn.getInputStream());
			} catch (Exception e) {
				System.out.println("Cannot read info for scopus ID " + sID.getScopusID());
			}
			Integer documentCount = Integer.parseInt(document.getElementsByTagName("opensearch:totalResults").item(0).getTextContent());
			
			System.out.println("Scopus ID " + sID.getScopusID() + ": " + documentCount.toString() + " publications");
			for (int i = 0; i < Math.ceil(documentCount/100.0); i++) {
				builder.reset();
				Integer current = i*100;
				try {
					scopus = new URL("http://api.elsevier.com/content/search/scopus?"
						   + "start=" + current.toString() + "&count=100&query=AU-ID%28" + sID.getScopusID()
				           + "%29&view=COMPLETE&apiKey=" + apiKey + "&httpAccept=application/xml");

					conn = scopus.openConnection();
					conn.setConnectTimeout(3600000);
					conn.setReadTimeout(3600000);
					document = builder.parse(conn.getInputStream()); 
				} catch (Exception e) {
					System.out.println("Problem found while reading ID " + sID.getScopusID());
				}
				NodeList list = document.getElementsByTagName("prism:url");
				NodeList childList = null;
				for (int j = 0; j < list.getLength(); j++) {
					Integer count = current + j + 1; // For displaying purposed
					//System.out.println("Publication " + count.toString() + "/" + documentCount.toString());
					try {
						builder.reset();
						scopus = new URL(list.item(j).getTextContent() + "?view=META_ABS&apiKey=" + apiKey + "&httpAccept=application/xml");
						conn = scopus.openConnection();
						conn.setConnectTimeout(3600000);
						conn.setReadTimeout(3600000);
						document = builder.parse(conn.getInputStream());
						
						PublicationData tempPub = new PublicationData();
						
						childList = document.getElementsByTagName("dc:title");
						if (childList.item(0) != null) {
							tempPub.setTitle(childList.item(0).getTextContent());
						}
						
						childList = document.getElementsByTagName("prism:coverDate");
						if (childList.item(0) != null) {
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							tempPub.setDate(formatter.parse(childList.item(0).getTextContent()));
							//tempPub.setDate(childList.item(0).getTextContent());
						}
						
						childList = document.getElementsByTagName("prism:publicationName");
						if (childList.item(0) != null) {
							tempPub.setSource(childList.item(0).getTextContent());
						}
						
						childList = document.getElementsByTagName("prism:volume");
						if (childList.item(0) != null) {
							tempPub.setVolume(childList.item(0).getTextContent());
						}
						
						childList = document.getElementsByTagName("prism:issueIdentifier");
						if (childList.item(0) != null) {
							tempPub.setIssue(childList.item(0).getTextContent());
						}
						
						childList = document.getElementsByTagName("citedby-count");
						if (childList.item(0) != null) {
							tempPub.setCitedBy(Integer.parseInt(childList.item(0).getTextContent()));
						}
						
						childList = document.getElementsByTagName("prism:doi");
						if (childList.item(0) != null) {
							tempPub.setDoi(childList.item(0).getTextContent());
						}
						
						childList = document.getElementsByTagName("prism:aggregationType");
						if (childList.item(0) != null) {
							tempPub.setType(childList.item(0).getTextContent());
						}
						
						childList = document.getElementsByTagName("eid");
						if (childList.item(0) != null) {
							tempPub.setEid(childList.item(0).getTextContent());
						}
						
						// Get all the authors
						/*childList = document.getElementsByTagName("authors").item(0).getChildNodes();
						StringBuilder sb = new StringBuilder(); // String to store all names separated by ";"
						for (int k = 0; k < childList.getLength(); k++) {
							NodeList temp = childList.item(k).getChildNodes();
							sb.append(temp.item(3).getTextContent());
							sb.append(" ");
							sb.append(temp.item(2).getTextContent());
							sb.append(";");
						}*/
						
						childList = document.getElementsByTagName("preferred-name");
						StringBuilder sb = new StringBuilder(); // String to store all names separated by ";"
						for (int k = 1; k < childList.getLength(); k++) {
							NodeList temp = childList.item(k).getChildNodes();
							sb.append(temp.item(3).getTextContent());
							sb.append(" ");
							sb.append(temp.item(2).getTextContent());
							sb.append(";");
						}
						sb.deleteCharAt(sb.length() - 1); // Remove last ";"
						
						tempPub.setAuthors(sb.toString());
						tempPub.setName(pi.getName());
						tempPub.setAwardNumber("N/A");
						scopusPublications.add(tempPub);
					} catch (Exception e) {
						System.out.println("Could not add publication number " + count);
					}
				}
			}
			/*} catch (Exception e) {
				e.printStackTrace();
			}*/
		}
		pi.setPublications(scopusPublications);
		return pi;
	}
}
