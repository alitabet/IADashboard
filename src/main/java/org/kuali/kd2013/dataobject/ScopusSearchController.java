package org.kuali.kd2013.dataobject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.controller.UifControllerBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Controller
@RequestMapping(value = "/scopusSearch")
public class ScopusSearchController extends UifControllerBase {

	@Override
    protected ScopusSearchForm createInitialForm(HttpServletRequest request) {
        return new ScopusSearchForm();
    }
	
	@RequestMapping(method=RequestMethod.POST, params="methodToCall=submit")
    public ModelAndView submit(@ModelAttribute("KualiForm") ScopusSearchForm uifForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		/*List<ScopusPI> scopusIds = new ArrayList<ScopusPI>();
		ScopusPI temp = new ScopusPI();
		temp.setScopusID("1111111"); temp.setScopusName("Ali Thabet"); temp.setAffiliation("KAUST");
		temp.setCountry("KSA"); temp.setCity("Thuwal"); 
		scopusIds.add(temp);
		uifForm.setScopusIds(scopusIds);*/
		
		if (uifForm.getName() != null)
		{
			String name = uifForm.getName();
			uifForm.setScopusIds(fecthScopusData(name));
		}
		
        Properties props = new Properties();
        props.put(UifParameters.METHOD_TO_CALL, "refresh");
        props.put(UifParameters.VIEW_ID, uifForm.getViewId());
        props.put(UifParameters.FORM_KEY, uifForm.getFormKey());
        props.put(UifParameters.AJAX_REQUEST, "false");

        return performRedirect(uifForm, uifForm.getFormPostUrl(), props);
    }
    
	@Override
    @RequestMapping(method=RequestMethod.POST, params="methodToCall=deleteLine")
    public ModelAndView deleteLine(@ModelAttribute("KualiForm") UifFormBase uifForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		ScopusSearchForm scopusForm = (ScopusSearchForm)uifForm;
		if (scopusForm.getScopusIds() != null) {
			return super.deleteLine(uifForm, result, request, response);
		} else {
			return getUIFModelAndView(uifForm);
		}
    }
    
    @RequestMapping(method = RequestMethod.POST, params = "methodToCall=searchIdLine")
    public ModelAndView searchIdLine(@ModelAttribute("KualiForm") final ScopusSearchForm uifForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {

        final String selectedCollectionPath = uifForm.getActionParamaterValue(UifParameters.SELLECTED_COLLECTION_PATH);
        if (StringUtils.isBlank(selectedCollectionPath)) {
            throw new RuntimeException("Selected collection was not set for search line action, search seach line");
        }

        String selectedLine = uifForm.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        final int selectedLineIndex;
        if (StringUtils.isNotBlank(selectedLine)) {
            selectedLineIndex = Integer.parseInt(selectedLine);
        } else {
            selectedLineIndex = -1;
        }
        
        if (selectedLineIndex == -1) {
            throw new RuntimeException("Selected line index was not set for search line action, cannot search line");
        }
        
        List<ScopusPI> idList = uifForm.getScopusIds();
        String scopusID = idList.get(selectedLineIndex).getScopusID();
        
        try {
	        String apiKey = "b208dbc860cf6afb2527406f94ac1431";
	    	URL scopus = new URL("http://api.elsevier.com/content/author?author_id=" + scopusID + "&view=LIGHT&apiKey=" + apiKey + "&httpAccept=application/xml");
	    	URLConnection conn = scopus.openConnection();
			conn.setConnectTimeout(3600000);
			conn.setReadTimeout(3600000);
	
			DocumentBuilderFactory factory =
			       DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
	
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(conn.getInputStream());
			
			ScopusPI spi = new ScopusPI();
			spi.setName(uifForm.getName());
			spi.setScopusID(scopusID);
			
			InputStream is = scopus.openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

			JSONObject jsonScopus = (JSONObject) JSONValue.parseWithException(IOUtils.toString(scopus));
			
			NodeList childList = document.getElementsByTagName("given-name");
			String givenName = childList.item(0).getTextContent();
			childList = document.getElementsByTagName("surname");
			String surname = childList.item(0).getTextContent();
			spi.setScopusName(new String(givenName + " " + surname));
			
			childList = document.getElementsByTagName("affiliation-city");
			if (childList.item(0) != null) {
	
				spi.setCity(childList.item(0).getTextContent());
				
				childList = document.getElementsByTagName("affiliation-country");
				spi.setCountry(childList.item(0).getTextContent());
				
				childList = document.getElementsByTagName("affiliation-name");
				spi.setAffiliation(childList.item(0).getTextContent());
			}
	    	builder.reset();
			scopus = new URL("http://api.elsevier.com/content/author?author_id=" + scopusID + "&view=METRICS&apiKey=" + apiKey + "&httpAccept=application/xml");
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
			idList.remove(selectedLineIndex);
			idList.add(selectedLineIndex, spi);
			uifForm.setScopusIds(idList);
	    } catch (Exception e) {
			e.printStackTrace();
		}
        
        /*try {
	        String apiKey = "b208dbc860cf6afb2527406f94ac1431";
	    	URL scopus = new URL("http://api.elsevier.com/content/author?author_id=" + scopusID + "&view=LIGHT&apiKey=" + apiKey + "&httpAccept=application/xml");
	    	URLConnection conn = scopus.openConnection();
			conn.setConnectTimeout(3600000);
			conn.setReadTimeout(3600000);
	
			DocumentBuilderFactory factory =
			       DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
	
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(conn.getInputStream());
	    	
			ScopusPI spi = new ScopusPI();
			spi.setName(uifForm.getName());
			spi.setScopusID(scopusID);
			
			NodeList childList = document.getElementsByTagName("given-name");
			String givenName = childList.item(0).getTextContent();
			childList = document.getElementsByTagName("surname");
			String surname = childList.item(0).getTextContent();
			spi.setScopusName(new String(givenName + " " + surname));
			
			childList = document.getElementsByTagName("affiliation-city");
			if (childList.item(0) != null) {
	
				spi.setCity(childList.item(0).getTextContent());
				
				childList = document.getElementsByTagName("affiliation-country");
				spi.setCountry(childList.item(0).getTextContent());
				
				childList = document.getElementsByTagName("affiliation-name");
				spi.setAffiliation(childList.item(0).getTextContent());
			}
	    	builder.reset();
			scopus = new URL("http://api.elsevier.com/content/author?author_id=" + scopusID + "&view=METRICS&apiKey=" + apiKey + "&httpAccept=application/xml");
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
			idList.remove(selectedLineIndex);
			idList.add(selectedLineIndex, spi);
			uifForm.setScopusIds(idList);
	    } catch (Exception e) {
			e.printStackTrace();
		}*/
        
        Properties props = new Properties();
        props.put(UifParameters.METHOD_TO_CALL, "refresh");
        props.put(UifParameters.VIEW_ID, uifForm.getViewId());
        props.put(UifParameters.FORM_KEY, uifForm.getFormKey());
        props.put(UifParameters.AJAX_REQUEST, "false");

        return performRedirect(uifForm, uifForm.getFormPostUrl(), props);
    }
    
    protected List<ScopusPI> fecthScopusData(String name) {

		List<String> nameList = new ArrayList<String>();
		Collections.addAll(nameList,name.split(" "));
		String firstName = nameList.get(0);
		String lastName = nameList.get(nameList.size() - 1);
		String apiKey = "b208dbc860cf6afb2527406f94ac1431";
		
		List<ScopusPI> tempList = new ArrayList<ScopusPI>();
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

					scopusLastName = null;
					for (int k = 0; k < childList.getLength(); k = k + 2) {
						scopusLastName = childList.item(k).getTextContent();
	    				if (lastName.equals(scopusLastName)){
	    					NamedNodeMap nodeMap = document.getElementsByTagName("author").item(k/2).getAttributes();
	    					scopusID.add(nodeMap.item(0).getTextContent());
	    				}
					}
				}
			}
			
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
				spi.setName(name);
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
				
				tempList.add(spi);
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempList;
	}
}