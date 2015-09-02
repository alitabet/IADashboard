package org.kuali.kd2013.dataobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class DivisionValuesFinder extends KeyValuesBase {
	private static final long serialVersionUID = 1L;

	static List<KeyValue> VALUES = new ArrayList<KeyValue>(4);
	static {
		VALUES.add( new ConcreteKeyValue("", "") );
		VALUES.add( new ConcreteKeyValue("Biological and Environmental Science and Engineering", "BESE") );
		VALUES.add( new ConcreteKeyValue("Physical Science and Engineering", "PSE") );
		VALUES.add( new ConcreteKeyValue("Computer, Electrical and Mathematical Science & Engineering", "CEMSE") );
	}
	
	@Override
	public List<KeyValue> getKeyValues() {
		return Collections.unmodifiableList(VALUES);
	}
}
