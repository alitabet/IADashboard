package org.kuali.kd2013.dataobject;

import org.kuali.rice.krad.lookup.LookupForm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrincipalInvestigatorLookupableImpl extends GeneralLookupableImp {
	private static final long serialVersionUID = 1L;

	/**
     * Override the performSearch method so that we can perform any additional filtering of results from the standard query.
     * Here we are changing the search to encompass PI Name with *_*.
     *
     * @{inheritDoc}
     */
    @Override
    public Collection<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean bounded) {

        StringBuilder sb = super.getKeywords(form, searchCriteria);

        Map<String, Object> allResults = new HashMap<>();

        super.performGeneralSearch(form, searchCriteria, bounded, new PrincipalInvestigator(), sb, allResults);

        return new ArrayList<>(allResults.values());
    }

}
