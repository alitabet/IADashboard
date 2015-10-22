package org.kuali.kd2013.dataobject;

import org.kuali.rice.krad.lookup.LookupForm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class OcrfAwardLookupableImpl extends GeneralLookupableImp {
	private static final long serialVersionUID = 1L;

	/**
     * Override the performSearch method so that we can perform any additional filtering of results from the standard query.
     * Here we are changing the search to encompass PI Name with *_*.
     *
     * @{inheritDoc}
     */
    @Override
    public Collection<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean bounded) {
    	
    	List<String> stringKeys = new ArrayList<String>(); // Store keys to change
    	stringKeys.add("awardNumber"); stringKeys.add("name"); stringKeys.add("keyWords"); 
    	
    	for (String key : stringKeys) {
    		if (form.getLookupCriteria().containsKey(key)) {
    			String keyString = form.getLookupCriteria().get(key);
    		    searchCriteria.put(key, "*" + keyString + "*");
    		}
    	}
		
        return super.performSearch(form, searchCriteria,bounded);
    	
    }

}
