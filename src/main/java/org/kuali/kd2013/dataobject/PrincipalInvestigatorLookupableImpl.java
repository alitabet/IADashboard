package org.kuali.kd2013.dataobject;

import java.util.Collection;
import java.util.Map;

import org.kuali.rice.krad.lookup.LookupForm;
import org.kuali.rice.krad.lookup.LookupableImpl;

public class PrincipalInvestigatorLookupableImpl extends LookupableImpl {
	private static final long serialVersionUID = 1L;

	/**
     * Override the performSearch method so that we can perform any additional filtering of results from the standard query.
     * Here we are changing the search to encompass PI Name with *_*.
     *
     * @{inheritDoc}
     */
    @Override
    public Collection<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean bounded) {
        
		// PI Name
		if (form.getLookupCriteria().containsKey("name")) {
			String keyString = form.getLookupCriteria().get("name");
		    searchCriteria.put("name", "*" + keyString + "*");
		}
		
		// PI ID Number
		if (form.getLookupCriteria().containsKey("idNumber")) {
			String keyString = form.getLookupCriteria().get("idNumber");
		    searchCriteria.put("idNumber", "*" + keyString + "*");
		}
		//InquiryForm form2;
        return super.performSearch(form, searchCriteria,bounded);
    }

}
