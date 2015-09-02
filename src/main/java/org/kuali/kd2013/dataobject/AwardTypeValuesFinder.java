package org.kuali.kd2013.dataobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class AwardTypeValuesFinder extends KeyValuesBase {
	private static final long serialVersionUID = 1L;

	static List<KeyValue> VALUES = new ArrayList<KeyValue>(3);
	static {
		VALUES.add( new ConcreteKeyValue("", "") );
		VALUES.add( new ConcreteKeyValue("Full Proposal", "Full") );
		VALUES.add( new ConcreteKeyValue("Exploratory/Proof of Concept", "Exploratory") );
	}
	
	@Override
	public List<KeyValue> getKeyValues() {
		return Collections.unmodifiableList(VALUES);
	}
}
