package org.kuali.kd2013.dataobject;

import org.kuali.rice.krad.lookup.LookupForm;
import org.kuali.rice.krad.lookup.LookupableImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thabetak on 10/22/2015.
 */
public class GeneralLookupableImp extends LookupableImpl {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LookupableImpl.class);
    private static final long serialVersionUID = 1L;

    /**
     * Override the performSearch method so that we can perform any additional filtering of results from the standard query.
     * Here we are changing the search to encompass PI Name with *_*.
     *
     * @{inheritDoc}
     */
    @Override
    public Collection<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean bounded) {

        StringBuilder sb = getKeywords(form, searchCriteria);

        Map<String, Object> allResults = new HashMap<>();

        try {
            Object obj = Class.forName(form.getDataObjectClassName()).getConstructor().newInstance();
            performGeneralSearch(form, searchCriteria, bounded, (DataObject) obj, sb, allResults);
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating class " + form.getDataObjectClassName() + e.getMessage());
//            LOG.error("Error instantiating class " + form.getDataObjectClassName(), e);
        }


        return new ArrayList<>(allResults.values());
    }

    public void performGeneralSearch(LookupForm form, Map<String, String> searchCriteria, boolean bounded, DataObject obj, StringBuilder sb, Map<String, Object> allResults) {
        for (String str : obj.getValues()) {
            Map<String, String> localCriteria = new HashMap<>(searchCriteria);
            StringBuilder temp = new StringBuilder(sb);

            if (!form.getLookupCriteria().containsKey(str)) form.getLookupCriteria().put(str, new String());

            if (!form.getLookupCriteria().get(str).isEmpty()) {//form.getLookupCriteria().containsKey("name")) {
                String keyString = form.getLookupCriteria().get(str);
                temp.append("*" + keyString + "*");
            }
            if (temp.charAt(temp.length() - 1) == '|') temp.deleteCharAt(temp.length() - 1); // Remove last '|'
            localCriteria.put(str, temp.toString());

            for (String subStr : obj.getValues()) {
                if (subStr.equals(str) || !form.getLookupCriteria().containsKey(subStr)) continue;

                if (!form.getLookupCriteria().get(subStr).isEmpty()) {//form.getLookupCriteria().containsKey("name")) {
                    String keyString = form.getLookupCriteria().get(subStr);
                    localCriteria.put(subStr, "*" + keyString + "*");
                }
            }

            Collection<?> results = new ArrayList<Object>(super.performSearch(form, localCriteria, bounded));
            for (Object o : results) {
                obj.addToCollection(o, allResults);
            }
        }
    }

    public StringBuilder getKeywords(LookupForm form, Map<String, String> searchCriteria) {
        List<String> keywords = new ArrayList<>();

        // General keyword search
        if (!form.getLookupCriteria().get("keywordSearch").isEmpty()) {
            String temp = form.getLookupCriteria().get("keywordSearch");
            keywords = Arrays.asList(temp.split(" "));
            searchCriteria.remove("keywordSearch");
        }

        StringBuilder sb = new StringBuilder();
        for (String str : keywords) {
            sb.append("*" + str + "*");
            sb.append("|");
        }

        return sb;
    }

//    // Custom exception class for IA Dashboard
//    private class IADashboardException extends Exception {
//        private static final long serialVersionUID = 1997753363232807009L;
//
//        public IADashboardException()
//        {
//        }
//
//        public IADashboardException(String message)
//        {
//            super(message);
//        }
//
//        public IADashboardException(Throwable cause)
//        {
//            super(cause);
//        }
//
//        public IADashboardException(String message, Throwable cause)
//        {
//            super(message, cause);
//        }
//
//        public IADashboardException(String message, Throwable cause,
//                                    boolean enableSuppression, boolean writableStackTrace)
//        {
//            super(message, cause, enableSuppression, writableStackTrace);
//        }
//    }
}
